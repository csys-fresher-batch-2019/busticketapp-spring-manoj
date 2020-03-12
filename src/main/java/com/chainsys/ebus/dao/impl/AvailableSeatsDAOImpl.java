package com.chainsys.ebus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chainsys.ebus.dao.AvailableSeatsDAO;
import com.chainsys.ebus.exception.DbException;
import com.chainsys.ebus.exception.InfoMessages;
import com.chainsys.ebus.util.ConnectionUtil;

public class AvailableSeatsDAOImpl implements AvailableSeatsDAO {

	public int seatsAvailable(int busid) throws DbException,SQLException {
		String sql = "select available_seats from seat_availability where bus_id=? ";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, busid);
			try (ResultSet row = pst.executeQuery();) {
				int seats = 0;
				if (row.next()) {
					seats = row.getInt("available_seats");

				}
				return seats;

			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(InfoMessages.AVAILABLESEATS,e);
		}

	}
}
