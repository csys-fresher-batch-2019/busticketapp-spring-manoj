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
import com.chainsys.ebus.dao.FindBusDAO;
import com.chainsys.ebus.model.FindBus;

public class FindBusDAOImpl implements FindBusDAO {
	int bid = 0;

	public ArrayList<FindBus> searchbus(String fromLocation, String toLocation, String journeyDate) throws Exception {

		try (Connection con = TestConnection.connection();) {
			String sql = "select bus_name,bus_id,ticket_price,travelling_time from bus_details where from_location= ? and to_location= ? and journey_date= ?";
			try (PreparedStatement pst = con.prepareStatement(sql);) {
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
						bid = f.getBusId();
						f.setTravellingTime(rows.getString("travelling_time"));
						f.setTicketPrice(rows.getInt("ticket_price"));
						int seats = seatavailable(bid);

						if (seats > 0) {
							buses1.add(f);
						}

					}
					return buses1;
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(infoMessages.FINDBUS);

		} catch (Exception e) {

			e.printStackTrace();
			throw new DbException(infoMessages.CONNECTION);

		}

	}

	public int seatavailable(int busid) throws Exception {
		try (Connection con = TestConnection.connection();) {
			String sql = "select available_seats from seat_availability where bus_id=?";
			try (PreparedStatement pst = con.prepareStatement(sql);) {
				pst.setInt(1, bid);
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
			throw new DbException(infoMessages.FINDBUS);

		} catch (Exception e) {

			e.printStackTrace();
			throw new DbException(infoMessages.CONNECTION);

		}

	}
}
