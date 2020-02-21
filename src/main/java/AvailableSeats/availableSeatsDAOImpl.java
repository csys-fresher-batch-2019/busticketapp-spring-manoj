package AvailableSeats;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import AdminRole.TestConnection;
import Exception.DbException;
import Exception.infoMessages;

public class availableSeatsDAOImpl implements availableSeatsDAO {

	public int seatavail(int busid) throws  Exception {
		try (Connection con = TestConnection.connection();) {
			String sql = "select available_seats from seat_availability where bus_id=? ";
			try (PreparedStatement pst = con.prepareStatement(sql);) {
				pst.setInt(1, busid);
				try (ResultSet row = pst.executeQuery();) {
					int seats = 0;
					if (row.next()) {
						seats = row.getInt("available_seats");

					}
					return seats;
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new DbException(infoMessages.AVAILABLESEATS);
	}catch (Exception e) {
			
			e.printStackTrace();
			throw new DbException(infoMessages.CONNECTION);
		}


}}
