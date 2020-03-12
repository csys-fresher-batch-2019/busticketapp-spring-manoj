package com.chainsys.ebus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chainsys.ebus.dao.PassengerInfoDAO;
import com.chainsys.ebus.exception.DbException;
import com.chainsys.ebus.exception.InfoMessages;
import com.chainsys.ebus.model.PassengerInfo;
import com.chainsys.ebus.util.ConnectionUtil;

public class PassengerInfoDAOImpl implements PassengerInfoDAO {
	int numOfTickets = 0;
	int busId = 0;
	int userId = 0;
	int bookingId = 0;
	int totalPrice = 0;

	public int insertPassengerInfo(PassengerInfo a) throws DbException,SQLException {

		String sql = "insert into passenger_details(booking_id,user_id,bus_id,passenger_name,age,gender,mobile_number,no_of_tickets) values (sequence_booking_id.nextval,?,?,?,?,?,?,?)";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, a.getUserId());
			pst.setInt(2, a.getBusId());
			pst.setString(3, a.getPassengerName());
			pst.setInt(4, a.getAge());
			pst.setString(5, a.getGender());
			pst.setLong(6, a.getMobileNumber());
			pst.setInt(7, a.getNoOfTickets());
			pst.executeUpdate();
			numOfTickets = a.getNoOfTickets();
			userId = a.getUserId();
			busId = a.getBusId();
			bookingId = getBookingId(userId);
			System.out.println(bookingId);
			return bookingId;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(InfoMessages.INSERTPASSENGERINFO,e);

		}

	}

	private int getBookingId(int userId) throws DbException,SQLException {

		String sql = "select max(booking_id) as id from passenger_details where user_id=?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement stmt = con.prepareStatement(sql);) {
			stmt.setInt(1, userId);
			try (ResultSet rs = stmt.executeQuery();) {
				rs.next();
				bookingId = rs.getInt("id");
				getTotalPrice(busId);
				return bookingId;

			}

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(InfoMessages.BOOKINGID,e);

		}

	}

	private void getTotalPrice(int busId) throws DbException,SQLException {

		int price = 0;
		String sql = "select ticket_price from bus_details where bus_id=?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, busId);
			try (ResultSet rs = pst.executeQuery();) {
				rs.next();
				price = rs.getInt("ticket_price");
				totalPrice = price * numOfTickets;
				updatePaymentInfo(userId, busId, bookingId, totalPrice);

			}

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(InfoMessages.TOTALPRICE,e);

		}

	}

	private void updatePaymentInfo(int userId, int busId, int bId, int totalPrice) throws DbException,SQLException {

		String sql = "insert into payment_status(user_id,bus_id,booking_id,total_price)values(?,?,?,?)";

		try (Connection con = ConnectionUtil.connection(); PreparedStatement stmt = con.prepareStatement(sql);) {
			stmt.setInt(1, userId);
			stmt.setInt(2, busId);
			stmt.setInt(3, bId);
			stmt.setInt(4, totalPrice);
			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(InfoMessages.PAYMENTDETAILS,e);

		}

	}

	public List<PassengerInfo> bookingDetails(int bookingId) throws DbException,SQLException {

		String sql = "select *from passenger_details where booking_id=?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, bookingId);
			try (ResultSet rows = pst.executeQuery();) {
				List<PassengerInfo> details = new ArrayList<>();
				while (rows.next()) {

					PassengerInfo p = new PassengerInfo();
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
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(InfoMessages.BOOKINGDETAILS,e);

		}

	}

	public void cancelBooking(int bookingId) throws DbException,SQLException {

		String sql = "update passenger_details set booking_status= 'cancelled' where booking_id=?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, bookingId);
			pst.executeUpdate();

			updateSeatForCancel(bookingId);

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(InfoMessages.CANCELBOOKING,e);

		}

	}

	private void updateSeatForCancel(int bookingId) throws DbException,SQLException {

		String sql = "update seat_availability set available_seats=available_seats+(select no_of_tickets from passenger_details where booking_id=?) where bus_id=(select bus_id from passenger_details where booking_id=?)";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, bookingId);
			pst.setInt(2, bookingId);
			pst.executeUpdate();
			updatePaymentStatusForCancel(bookingId);

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(InfoMessages.UPDATECANCELLEDSEATS,e);

		}
	}

	private void updatePaymentStatusForCancel(int bookingId) throws DbException,SQLException {

		String sql = "update payment_status set paid_status= 'cancelled' where booking_id=?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, bookingId);
			pst.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(InfoMessages.CANCELBOOKING,e);

		}

	}

	public boolean validateBookingId(int bookingId) throws DbException,SQLException {

		String bId = "select booking_id from passenger_details where booking_id=?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement smt = con.prepareStatement(bId);) {
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

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(InfoMessages.VALIDATEBOOKINGID,e);

		}

	}

	public boolean validateBusId(int busId) throws DbException,SQLException {

		String bus = "select bus_id from bus_details where bus_id = ?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement smt = con.prepareStatement(bus);) {
			smt.setInt(1, busId);
			try (ResultSet row = smt.executeQuery();) {
				int busid = 0;
				if (row.next()) {
					 busid = row.getInt("bus_id");
					return true;
				}
				 else {

					return false;
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(InfoMessages.VALIDATEBUSID,e);

		}
	}

	public int totalPrice(int bookingId) throws DbException,SQLException {

		String sql = "select total_price from payment_status where booking_id=? ";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, bookingId);
			try (ResultSet row = pst.executeQuery();) {
				int price = 0;
				if (row.next()) {
					price = row.getInt("total_price");
					System.out.println("money" + price);
				}
				return price;
			}

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(InfoMessages.GETTOTALPRICE,e);

		}
	}

	@Override
	public List<PassengerInfo> myBookings(int userId) throws DbException,SQLException {

		String sql = "select *from passenger_details where user_id=? and booking_status='booked'";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, userId);
			try (ResultSet rows = pst.executeQuery();) {
				List<PassengerInfo> details = new ArrayList<>();
				while (rows.next()) {

					PassengerInfo p = new PassengerInfo();
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DbException(InfoMessages.MYBOOKINGDETAILS,e);

		}

	}

}
