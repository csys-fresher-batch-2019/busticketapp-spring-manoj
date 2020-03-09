package com.chainsys.ebus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chainsys.ebus.Connection.TestConnection;
import com.chainsys.ebus.Exception.DbException;
import com.chainsys.ebus.Exception.infoMessages;
import com.chainsys.ebus.dao.availableSeatsDAO;

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
