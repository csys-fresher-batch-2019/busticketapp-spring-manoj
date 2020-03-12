package com.chainsys.ebus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.chainsys.ebus.dao.FindBusDAO;
import com.chainsys.ebus.exception.DbException;
import com.chainsys.ebus.exception.InfoMessages;
import com.chainsys.ebus.model.FindBus;
import com.chainsys.ebus.util.ConnectionUtil;

public class FindBusDAOImpl implements FindBusDAO {
	int busId = 0;

	public ArrayList<FindBus> searchBus(String fromLocation, String toLocation, String journeyDate) throws DbException,SQLException{

		String sql = "select bus_name,bus_id,ticket_price,travelling_time from bus_details where from_location= ? and to_location= ? and journey_date= ?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, fromLocation);
			pst.setString(2, toLocation);
			java.sql.Date journeyDate1 = java.sql.Date.valueOf(journeyDate);
			pst.setDate(3, journeyDate1);
			try (ResultSet rows = pst.executeQuery();) {
				ArrayList<FindBus> buses1 = new ArrayList<FindBus>();
				while (rows.next()) {

					FindBus f = new FindBus();

					f.setBusName(rows.getString("bus_name"));

					f.setBusId(rows.getInt("bus_id"));
					busId = f.getBusId();
					f.setTravellingTime(rows.getString("travelling_time"));
					f.setTicketPrice(rows.getInt("ticket_price"));
					int seats = seatsAvailable(busId);

					if (seats > 0) {
						buses1.add(f);
					}

				}
				return buses1;

			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(InfoMessages.FINDBUS,e);

		}

	}

	private int seatsAvailable(int busid) throws DbException,SQLException {

		String sql = "select available_seats from seat_availability where bus_id=?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, busId);
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
