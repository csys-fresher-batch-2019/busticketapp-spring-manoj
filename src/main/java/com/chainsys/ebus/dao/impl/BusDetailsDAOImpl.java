package com.chainsys.ebus.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.chainsys.ebus.dao.BusDetailsDAO;
import com.chainsys.ebus.exception.DbException;
import com.chainsys.ebus.exception.InfoMessages;
import com.chainsys.ebus.model.BusDetails;
import com.chainsys.ebus.util.ConnectionUtil;

public class BusDetailsDAOImpl implements BusDetailsDAO {
	int busId = 0;

	public void addBus(BusDetails a) throws DbException,SQLException {

		String sql = "insert into bus_details (bus_id,bus_name,from_location,to_location,journey_date,ticket_price,travelling_time) values (?,?,?,?,?,?,?)";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, a.getBusId());
			pst.setString(2, a.getBusName());
			pst.setString(3, a.getFromLocation());
			pst.setString(4, a.getToLocation());
			java.sql.Date journeyDate = java.sql.Date.valueOf(a.getJourneyDate());
			pst.setDate(5, journeyDate);
			pst.setInt(6, a.getTicketPrice());
			pst.setString(7, a.getTravellingTime());
			pst.executeUpdate();
			busId = a.getBusId();
			addBusSeats(a);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(InfoMessages.ADDBUS,e);
		}

	}

	private void addBusSeats(BusDetails a) throws DbException,SQLException {

		String sql1 = "insert into seat_availability (bus_id,maximum_seats,available_seats) values(?,?,?)";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement pst1 = con.prepareStatement(sql1);) {
			pst1.setInt(1, busId);
			pst1.setInt(2, a.getMaximumSeats());
			pst1.setInt(3, a.getAvailableSeats());
			pst1.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(InfoMessages.ADDBUSSEATS,e);
		}

	}

	public void removeBus(int busId) throws DbException,SQLException {

		String sql = "delete from bus_details where bus_id=?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, busId);
			pst.executeUpdate();
			removeBusSeats(busId);

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(InfoMessages.REMOVEBUS,e);

		}
	}

	private void removeBusSeats(int busId) throws DbException,SQLException {

		String sql1 = "delete from seat_availability where bus_id=?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement pst1 = con.prepareStatement(sql1);) {
			pst1.setInt(1, busId);
			pst1.executeUpdate();

			System.out.println("Bus Details are deleted Successfully");

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(InfoMessages.REMOVEBUSSEATS,e);

		}

	}

	public void updateBusTiming(BusDetails a) throws DbException,SQLException {

		String sql = "update bus_details set travelling_time=? where bus_id=?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setString(1, a.getTravellingTime());
			pst.setInt(2, a.getBusId());
			pst.executeUpdate();

			System.out.println("Bus timings are Updated Successfully");

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(InfoMessages.BUSTIMEUPDATE,e);

		}

	}

	public List<String> getFromLocation() throws DbException,SQLException {

		String sql = "select distinct from_location from bus_details";
		try (Connection con = ConnectionUtil.connection(); Statement pst = con.createStatement();) {
			try (ResultSet rs = pst.executeQuery(sql);) {
				ArrayList<String> from = new ArrayList<String>();
				while (rs.next()) {
					String a = rs.getString("from_location");
					from.add(a);
				}

				return from;
			}

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(InfoMessages.FROMLOCATION,e);

		}
	}

	public List<String> getToLocation() throws DbException,SQLException {

		String sql = "select distinct to_location from bus_details";
		try (Connection con = ConnectionUtil.connection(); Statement pst = con.createStatement();) {
			try (ResultSet rs = pst.executeQuery(sql);) {
				ArrayList<String> to = new ArrayList<String>();
				while (rs.next()) {
					String a = rs.getString("to_location");
					to.add(a);
				}
				return to;
			}

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(InfoMessages.TOLOCATION,e);

		}
	}
}
