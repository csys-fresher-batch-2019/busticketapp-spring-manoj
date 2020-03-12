package com.chainsys.ebus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.chainsys.ebus.dao.PaymentDAO;
import com.chainsys.ebus.exception.DbException;
import com.chainsys.ebus.exception.InfoMessages;
import com.chainsys.ebus.util.ConnectionUtil;

public class PaymentDAOImpl implements PaymentDAO {

	public void paymentSuccess(int bookingId) throws DbException,SQLException {

		String sql = "update payment_status set paid_status='success' where booking_id=?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, bookingId);
			pst.executeUpdate();

			updateBookingStatus(bookingId);

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(InfoMessages.PAYMENTSUCCESS,e);

		}
	}

	private void updateBookingStatus(int bookingId) throws DbException,SQLException {

		String sql1 = "update passenger_details set booking_status='booked' where booking_id=?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement pst1 = con.prepareStatement(sql1);) {
			pst1.setInt(1, bookingId);
			pst1.executeUpdate();
			updateSeatAvailability(bookingId);

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(InfoMessages.PAYMENTSUCCESS,e);

		}
	}

	private void updateSeatAvailability(int bookingId) throws DbException,SQLException {

		String sql2 = "update seat_availability set available_seats=available_seats -(select no_of_tickets from passenger_details where booking_id= ?)where bus_id=(select bus_id from passenger_details where booking_id=?)  ";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement pst2 = con.prepareStatement(sql2);) {
			pst2.setInt(1, bookingId);
			pst2.setInt(2, bookingId);
			pst2.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(InfoMessages.PAYMENTSUCCESS,e);

		}

	}

	public void paymentFailure(int bookingId) throws DbException,SQLException {

		String sql = "update payment_status set paid_status='failure' where booking_id=?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, bookingId);
			pst.executeUpdate();

			updateFailureBookingStatus(bookingId);

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(InfoMessages.PAYMENTFAILURE,e);

		}

	}

	private void updateFailureBookingStatus(int bookingId) throws DbException,SQLException {

		String sql2 = "update passenger_details set booking_status='cancelled' where booking_id=?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement pst1 = con.prepareStatement(sql2);) {
			pst1.setInt(1, bookingId);
			pst1.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(InfoMessages.PAYMENTFAILURE,e);

		}

	}

	public void cashPayment(int bookingId) throws DbException,SQLException {

		String sql = "update payment_status set paid_status='COD' where booking_id=?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, bookingId);
			pst.executeUpdate();

			updateBookingStatusCash(bookingId);

		}

		catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(InfoMessages.PAYCASH,e);

		}

	}

	private void updateBookingStatusCash(int bookingId) throws DbException,SQLException {

		String sql1 = "update passenger_details set booking_status='booked' where booking_id=?";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement pst1 = con.prepareStatement(sql1);) {
			pst1.setInt(1, bookingId);
			pst1.executeUpdate();

			updateSeatAvailabilityCash(bookingId);

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(InfoMessages.PAYCASH,e);

		}

	}

	private void updateSeatAvailabilityCash(int bookingId) throws DbException,SQLException {

		String sql2 = "update seat_availability set available_seats=available_seats -(select no_of_tickets from passenger_details where booking_id= ?)where bus_id=(select bus_id from passenger_details where booking_id=?)  ";
		try (Connection con = ConnectionUtil.connection(); PreparedStatement pst2 = con.prepareStatement(sql2);) {
			pst2.setInt(1, bookingId);
			pst2.setInt(2, bookingId);
			pst2.executeUpdate();

		}

		catch (SQLException e) {

			e.printStackTrace();
			throw new DbException(InfoMessages.PAYCASH,e);

		}

	}
}
