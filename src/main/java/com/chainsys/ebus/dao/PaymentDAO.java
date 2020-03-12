package com.chainsys.ebus.dao;

import java.sql.SQLException;

import com.chainsys.ebus.exception.DbException;

public interface PaymentDAO {
	public void paymentSuccess(int bookingId) throws DbException,SQLException;
	public void paymentFailure(int bookingId) throws DbException,SQLException;
	public void cashPayment(int bookingId) throws DbException,SQLException;
}
