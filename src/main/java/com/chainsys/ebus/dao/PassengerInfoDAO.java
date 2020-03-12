package com.chainsys.ebus.dao;

import java.sql.SQLException;
import java.util.List;

import com.chainsys.ebus.exception.DbException;
import com.chainsys.ebus.model.PassengerInfo;

public interface PassengerInfoDAO {
	public int insertPassengerInfo(PassengerInfo a) throws DbException,SQLException;
	public List<PassengerInfo> bookingDetails(int bookingId) throws DbException,SQLException ;
	public void cancelBooking(int bookingId) throws DbException,SQLException;
	public boolean validateBookingId(int bookingId) throws DbException,SQLException;
	public boolean validateBusId(int busId) throws DbException,SQLException ;
    public int totalPrice(int bookingId) throws DbException,SQLException;
    public List<PassengerInfo> myBookings(int userId) throws DbException,SQLException ;
	 
}
