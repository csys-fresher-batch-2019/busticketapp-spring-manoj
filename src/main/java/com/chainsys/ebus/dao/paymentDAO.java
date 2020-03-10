package com.chainsys.ebus.dao;

public interface paymentDAO {
	public void paymentSuccess(int bookingId) throws Exception;
	public void paymentFailure(int bookingId) throws Exception;
	public void cashPay(int bookingId) throws Exception;
}
