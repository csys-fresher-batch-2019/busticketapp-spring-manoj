package payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import AdminRole.TestConnection;
import Exception.DbException;
import Exception.infoMessages;
import PassengerInfo.passengerInfo;
import PassengerInfo.passengerInfoDAOImpl;

public class paymentDAOImpl implements paymentDAO {

	
	public void paymentSuccess(int bookingId) throws Exception {
		
		try (Connection con = TestConnection.connection();) {
			String sql = "update payment_status set paid_status='success' where booking_id=?";
			try (PreparedStatement pst = con.prepareStatement(sql);) {
				pst.setInt(1,bookingId);
				pst.executeUpdate();

				String sql1 = "update passenger_details set booking_status='booked' where booking_id=?";
				try (PreparedStatement pst1 = con.prepareStatement(sql1);) {
					pst1.setInt(1,bookingId);
					pst1.executeUpdate();

					String sql2 = "update seat_availability set available_seats=available_seats -(select no_of_tickets from passenger_details where booking_id= ?)where bus_id=(select bus_id from passenger_details where booking_id=?)  ";
					try (PreparedStatement pst2 = con.prepareStatement(sql2);) {
						pst2.setInt(1,bookingId);
						pst2.setInt(2,bookingId);
						pst2.executeUpdate();

					}
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(infoMessages.PAYMENTSUCCESS);

		}
catch (Exception e) {
			
			e.printStackTrace();
			throw new DbException(infoMessages.CONNECTION);
			
		}

	}

	public void paymentFailure(int bookingId) throws Exception {
		try (Connection con = TestConnection.connection();) {
			String sql = "update payment_status set paid_status='failure' where booking_id=?";
			try (PreparedStatement pst = con.prepareStatement(sql);) {
				pst.setInt(1,bookingId);
				pst.executeUpdate();
				
				String sql2 = "update passenger_details set booking_status='cancelled' where booking_id=?";
				try (PreparedStatement pst1 = con.prepareStatement(sql2);) {
					pst.setInt(1,bookingId);
					pst.executeUpdate();


			}}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(infoMessages.PAYMENTFAILURE);

		}
catch (Exception e) {
			
			e.printStackTrace();
			throw new DbException(infoMessages.CONNECTION);
			
		}


	}

	@Override
	public boolean cashPay(int bookingId) throws Exception {
		// TODO Auto-generated method stub
		try (Connection con = TestConnection.connection();) {
			String sql = "update payment_status set paid_status='COD' where booking_id=?";
			try (PreparedStatement pst = con.prepareStatement(sql);) {
				pst.setInt(1,bookingId);
				pst.executeUpdate();

				String sql1 = "update passenger_details set booking_status='booked' where booking_id=?";
				try (PreparedStatement pst1 = con.prepareStatement(sql1);) {
					pst1.setInt(1,bookingId);
					pst1.executeUpdate();

					String sql2 = "update seat_availability set available_seats=available_seats -(select no_of_tickets from passenger_details where booking_id= ?)where bus_id=(select bus_id from passenger_details where booking_id=?)  ";
					try (PreparedStatement pst2 = con.prepareStatement(sql2);) {
						pst2.setInt(1,bookingId);
						pst2.setInt(2,bookingId);
						pst2.executeUpdate();

					}
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(infoMessages.PAYCASH);

		}
catch (Exception e) {
			
			e.printStackTrace();
			throw new DbException(infoMessages.CONNECTION);
			
		}
		return false;
		
	}
}
