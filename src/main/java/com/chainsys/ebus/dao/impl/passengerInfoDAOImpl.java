package com.chainsys.ebus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.chainsys.ebus.Connection.TestConnection;
import com.chainsys.ebus.Exception.DbException;
import com.chainsys.ebus.Exception.infoMessages;
import com.chainsys.ebus.dao.passengerInfoDAO;
import com.chainsys.ebus.model.passengerInfo;

public class passengerInfoDAOImpl implements passengerInfoDAO {
	int numTickets = 0;
    int busId=0;
    int userId=0;
    int bId=0;
    int totalprice=0;
	public int insertPassengerInfo(passengerInfo a) throws Exception {
		try (Connection con = TestConnection.connection();) {
			String sql = "insert into passenger_details(booking_id,user_id,bus_id,passenger_name,age,gender,mobile_number,no_of_tickets) values (sequence_booking_id.nextval,?,?,?,?,?,?,?)";
			try (PreparedStatement pst = con.prepareStatement(sql);) {
				pst.setInt(1, a.getUserId());
				pst.setInt(2, a.getBusId());
				pst.setString(3, a.getPassengerName());
				pst.setInt(4, a.getAge());
				pst.setString(5, a.getGender());
				pst.setLong(6, a.getMobileNumber());
				pst.setInt(7, a.getNoOfTickets());
				pst.executeUpdate();
				numTickets = a.getNoOfTickets();
				userId = a.getUserId();
				busId = a.getBusId();

				// System.out.println("you are successfully entered your details..\n");
				bId = getBookingId(userId);
				System.out.println(bId);
				return bId;
			}

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(infoMessages.INSERTPASSENGERINFO);

		} catch (Exception e) {

			e.printStackTrace();
			throw new DbException(infoMessages.CONNECTION);

		}

	}

	public int getBookingId(int userId) throws Exception {
		try (Connection con = TestConnection.connection();) {
			String sql1 = "select max(booking_id) as id from passenger_details where user_id=?";
			try (PreparedStatement stmt = con.prepareStatement(sql1);) {
				stmt.setInt(1,userId);
				try (ResultSet rs = stmt.executeQuery();) {
					rs.next();
					bId = rs.getInt("id");
					getTotalPrice(busId);
					return bId;

				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(infoMessages.INSERTPASSENGERINFO);

		} catch (Exception e) {

			e.printStackTrace();
			throw new DbException(infoMessages.CONNECTION);

		}

	}

	public void getTotalPrice(int busId) throws Exception {
		try (Connection con = TestConnection.connection();) {
			int price = 0;
			String sql3 = "select ticket_price from bus_details where bus_id=?";
			try (PreparedStatement pst2 = con.prepareStatement(sql3);) {
				pst2.setInt(1,busId);
				try (ResultSet rs1 = pst2.executeQuery();) {
					rs1.next();
					price = rs1.getInt("ticket_price");
					totalprice = price * numTickets;
					System.out.println(userId+"  "+busId+"  "+bId+"  "+totalprice);
					updatePaymentInfo(userId,busId, bId,totalprice);
					
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(infoMessages.INSERTPASSENGERINFO);

		} catch (Exception e) {

			e.printStackTrace();
			throw new DbException(infoMessages.CONNECTION);

		}

	}

	public void updatePaymentInfo(int userId, int busId, int bId, int totalPrice) throws Exception {
		try (Connection con = TestConnection.connection();) {
			String sql4 = "insert into payment_status(user_id,bus_id,booking_id,total_price)values(?,?,?,?)";

			try (PreparedStatement stmt2 = con.prepareStatement(sql4);) {
				stmt2.setInt(1, userId);
				stmt2.setInt(2, busId);
				stmt2.setInt(3, bId);
				stmt2.setInt(4, totalPrice);
				stmt2.executeUpdate();
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(infoMessages.INSERTPASSENGERINFO);

		} catch (Exception e) {

			e.printStackTrace();
			throw new DbException(infoMessages.CONNECTION);

		}

	}

	public ArrayList<passengerInfo> BookingDetails(int bookingId) throws Exception {
		try (Connection con = TestConnection.connection();) {
			String sql5 = "select *from passenger_details where booking_id=?";
			try (PreparedStatement pst5 = con.prepareStatement(sql5);) {
				pst5.setInt(1, bookingId);
				try (ResultSet rows = pst5.executeQuery();) {
					ArrayList<passengerInfo> details = new ArrayList<passengerInfo>();
					while (rows.next()) {

						passengerInfo p = new passengerInfo();
						p.setBookingId(rows.getInt("booking_id"));
						p.setUserId(rows.getInt("user_id"));
						p.setBusId(rows.getInt("bus_id"));
						p.setPassengerName(rows.getString("passenger_name"));
						p.setMobileNumber(rows.getLong("mobile_number"));
						p.setNoOfTickets(rows.getInt("no_of_tickets"));
						p.setAge(rows.getInt("age"));
						p.setGender(rows.getString("gender"));

						details.add(p);
					}

					return details;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DbException(infoMessages.BOOKINGDETAILS);

		} catch (Exception e) {

			e.printStackTrace();
			throw new DbException(infoMessages.CONNECTION);

		}

	}

	public void cancelBooking(int bookingId) throws Exception {
		try (Connection con = TestConnection.connection();) {
			String sql6 = "update passenger_details set booking_status= 'cancelled' where booking_id=?";
			try (PreparedStatement pst6 = con.prepareStatement(sql6);) {
				pst6.setInt(1, bookingId);
				pst6.executeUpdate();

				updateSeatForCancel(bookingId);

				/*
				 * String sql9 =
				 * "update passenger_details set no_of_tickets= 0 where booking_id=?"; try
				 * (PreparedStatement pst9 = con.prepareStatement(sql9);) { pst9.setInt(1,
				 * bookingId); pst9.executeUpdate(); } }
				 */

			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(infoMessages.CANCELBOOKING);

		} catch (Exception e) {

			e.printStackTrace();
			throw new DbException(infoMessages.CONNECTION);

		}

	}

	public void updateSeatForCancel(int bookingId) throws Exception {
		try (Connection con = TestConnection.connection();) {
			String sql7 = "update seat_availability set available_seats=available_seats+(select no_of_tickets from passenger_details where booking_id=?) where bus_id=(select bus_id from passenger_details where booking_id=?)";
			try (PreparedStatement pst7 = con.prepareStatement(sql7);) {
				pst7.setInt(1, bookingId);
				pst7.setInt(2, bookingId);
				pst7.executeUpdate();
				updatePaymentStatusForCancel(bookingId);
			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(infoMessages.CANCELBOOKING);

		} catch (Exception e) {

			e.printStackTrace();
			throw new DbException(infoMessages.CONNECTION);

		}

	}

	public void updatePaymentStatusForCancel(int bookingId) throws Exception {
		try (Connection con = TestConnection.connection();) {
			String sql8 = "update payment_status set paid_status= 'cancelled' where booking_id=?";
			try (PreparedStatement pst8 = con.prepareStatement(sql8);) {
				pst8.setInt(1, bookingId);
				pst8.executeUpdate();
			}

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(infoMessages.CANCELBOOKING);

		} catch (Exception e) {

			e.printStackTrace();
			throw new DbException(infoMessages.CONNECTION);

		}

	}

	public boolean validateBookingId(int bookingId) throws Exception {

		try (Connection con = TestConnection.connection();) {
			String bId = "select booking_id from passenger_details where booking_id=?";
			try (PreparedStatement smt = con.prepareStatement(bId);) {
				smt.setInt(1, bookingId);
				try (ResultSet row = smt.executeQuery();) {
					int bookid = 0;
					if (row.next()) {
						bookid = row.getInt("booking_id");
					}
					if (bookid == bookingId) {

						return true;
					} else {

						return false;
					}
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(infoMessages.VALIDATEBOOKINGID);

		} catch (Exception e) {

			e.printStackTrace();
			throw new DbException(infoMessages.CONNECTION);

		}

	}

	public boolean validateBusId(int busId) throws Exception {
		try (Connection con = TestConnection.connection();) {
			String bus = "select bus_id from bus_details where bus_id = ?";
			try (PreparedStatement smt9 = con.prepareStatement(bus);) {
				smt9.setInt(1, busId);
				try (ResultSet row9 = smt9.executeQuery();) {
					int busid = 0;
					if (row9.next()) {
						busid = row9.getInt("bus_id");
					}
					if (busId == busid) {

						return true;
					} else {

						return false;
					}

				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(infoMessages.VALIDATEBUSID);

		} catch (Exception e) {

			e.printStackTrace();
			throw new DbException(infoMessages.CONNECTION);

		}
	}

	public int totalPrice(int bookingId) throws Exception {
		System.out.println(1234);
		try (Connection con = TestConnection.connection();) {
			String sql = "select total_price from payment_status where booking_id=? ";
			try (PreparedStatement pst = con.prepareStatement(sql);) {
				pst.setInt(1, bookingId);
				try (ResultSet row = pst.executeQuery();) {
					int price = 0;
					if (row.next()) {
						price = row.getInt("total_price");
						System.out.println("money"+price);
					}
					return price;
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(infoMessages.TOTALPRICE);

		} catch (Exception e) {

			e.printStackTrace();
			throw new DbException(infoMessages.CONNECTION);

		}
	}

	@Override
	public ArrayList<passengerInfo> MyBookings(int userId) throws Exception {
		try (Connection con = TestConnection.connection();) {
			String sql5 = "select *from passenger_details where user_id=? and booking_status='booked'";
			try (PreparedStatement pst5 = con.prepareStatement(sql5);) {
				pst5.setInt(1, userId);
				try (ResultSet rows = pst5.executeQuery();) {
					ArrayList<passengerInfo> details = new ArrayList<passengerInfo>();
					while (rows.next()) {

						passengerInfo p = new passengerInfo();
						p.setBookingId(rows.getInt("booking_id"));
						p.setUserId(rows.getInt("user_id"));
						p.setBusId(rows.getInt("bus_id"));
						p.setPassengerName(rows.getString("passenger_name"));
						p.setMobileNumber(rows.getLong("mobile_number"));
						p.setNoOfTickets(rows.getInt("no_of_tickets"));
						p.setAge(rows.getInt("age"));
						p.setGender(rows.getString("gender"));

						details.add(p);
					}

					return details;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DbException(infoMessages.MYBOOKINGDETAILS);

		} catch (Exception e) {

			e.printStackTrace();
			throw new DbException(infoMessages.CONNECTION);

		}

	}

}
