package com.chainsys.ebus.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chainsys.ebus.dao.FindBusDAO;
import com.chainsys.ebus.dao.AvailableSeatsDAO;
import com.chainsys.ebus.dao.BusDetailsDAO;
import com.chainsys.ebus.dao.PassengerInfoDAO;
import com.chainsys.ebus.dao.PaymentDAO;
import com.chainsys.ebus.dao.UserAccountDetailsDAO;
import com.chainsys.ebus.dao.impl.FindBusDAOImpl;
import com.chainsys.ebus.dao.impl.AvailableSeatsDAOImpl;
import com.chainsys.ebus.dao.impl.BusDetailsDAOImpl;
import com.chainsys.ebus.dao.impl.PassengerInfoDAOImpl;
import com.chainsys.ebus.dao.impl.PaymentDAOImpl;
import com.chainsys.ebus.dao.impl.UserAccountDetailsDAOImpl;
import com.chainsys.ebus.exception.DbException;
import com.chainsys.ebus.exception.InfoMessages;
import com.chainsys.ebus.exception.ServiceException;
import com.chainsys.ebus.model.FindBus;
import com.chainsys.ebus.model.PassengerInfo;
import com.chainsys.ebus.model.UserAccountDetails;

public class UserService {
	private AvailableSeatsDAO a = new AvailableSeatsDAOImpl();
	private BusDetailsDAO b = new BusDetailsDAOImpl();
	private FindBusDAO d = new FindBusDAOImpl();
	private PassengerInfoDAO e = new PassengerInfoDAOImpl();
	private PaymentDAO f = new PaymentDAOImpl();
	private UserAccountDetailsDAO g = new UserAccountDetailsDAOImpl();

	public int seatsAvailble(int busid) throws ServiceException {

		int seats = 0;
		try {
			seats = a.seatsAvailable(busid);

		} catch (DbException e) {
			e.printStackTrace();
			throw new ServiceException(InfoMessages.AVAILABLESEATS, e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}

		return seats;

	}

	public List<String> getFromLocation() throws ServiceException {
		List<String> from = null;
		try {
			from = b.getFromLocation();
		} catch (DbException e) {

			e.printStackTrace();
			throw new ServiceException(InfoMessages.FROMLOCATION, e);
		} catch (SQLException e) {

			e.printStackTrace();
			throw new ServiceException(e);
		}
		return from;

	}

	public List<String> getToLocation() throws ServiceException {
		List<String> to = null;
		try {
			to = b.getToLocation();
		} catch (DbException e) {

			e.printStackTrace();
			throw new ServiceException(InfoMessages.TOLOCATION, e);
		} catch (SQLException e) {

			e.printStackTrace();
			throw new ServiceException(e);
		}

		return to;

	}

	public ArrayList<FindBus> searchBus(String a, String b, String c) throws ServiceException {
		ArrayList<FindBus> buses = null;
		try {
			buses = d.searchBus(a, b, c);
		} catch (DbException e) {
			e.printStackTrace();
			throw new ServiceException(InfoMessages.FINDBUS, e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}

		return buses;

	}

	public int insertPassengerInfo(PassengerInfo a) throws ServiceException {

		int info = 0;
		try {
			info = e.insertPassengerInfo(a);
		} catch (DbException e) {
			e.printStackTrace();
			throw new ServiceException(InfoMessages.INSERTPASSENGERINFO, e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
		return info;
	}

	public ArrayList<PassengerInfo> bookingDetails(int bookingId) throws ServiceException {

		ArrayList<PassengerInfo> info = null;
		try {
			info = e.bookingDetails(bookingId);
		} catch (DbException e) {
			e.printStackTrace();
			throw new ServiceException(InfoMessages.BOOKINGDETAILS, e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}

		return info;

	}

	public void cancelBooking(int bookingId) throws ServiceException {
		try {
			e.cancelBooking(bookingId);
		} catch (DbException e) {
			e.printStackTrace();
			throw new ServiceException(InfoMessages.CANCELBOOKING, e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public boolean validateBookingId(int bookingId) throws ServiceException {
		boolean bo = false;
		try {
			bo = e.validateBookingId(bookingId);
		} catch (DbException e) {

			e.printStackTrace();
			throw new ServiceException(InfoMessages.VALIDATEBOOKINGID, e);
		} catch (SQLException e) {

			e.printStackTrace();
			throw new ServiceException(e);
		}
		return bo;

	}

	public boolean validateBusId(int busId) throws ServiceException {

		boolean bo1 = false;
		try {
			bo1 = e.validateBookingId(busId);
		} catch (DbException e) {
			e.printStackTrace();
			throw new ServiceException(InfoMessages.VALIDATEBUSID, e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
		return bo1;

	}

	public int totalPrice(int bookingId) throws ServiceException {
		int tp = 0;
		try {
			tp = e.totalPrice(bookingId);
		} catch (DbException e) {

			e.printStackTrace();
			throw new ServiceException(InfoMessages.TOTALPRICE, e);
		} catch (SQLException e) {

			e.printStackTrace();
			throw new ServiceException(e);
		}
		return tp;
	}

	public ArrayList<PassengerInfo> myBookings(int userId) throws ServiceException {
		ArrayList<PassengerInfo> pi = null;
		try {
			pi = e.myBookings(userId);
		} catch (DbException e) {

			e.printStackTrace();
			throw new ServiceException(InfoMessages. MYBOOKINGDETAILS, e);
		} catch (SQLException e) {

			e.printStackTrace();
			throw new ServiceException(e);
		}
		return pi;
	}

	public void paymentSuccess(int bookingId) throws ServiceException {
		try {
			f.paymentSuccess(bookingId);
		} catch (DbException e) {

			e.printStackTrace();
			throw new ServiceException(InfoMessages.PAYMENTSUCCESS, e);
		} catch (SQLException e) {

			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public void paymentFailure(int bookingId) throws ServiceException {
		try {
			f.paymentFailure(bookingId);
		} catch (DbException e) {
			e.printStackTrace();
			throw new ServiceException(InfoMessages.PAYMENTFAILURE, e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public void cashPayment(int bookingId) throws ServiceException {
		try {
			f.cashPayment(bookingId);
		} catch (DbException e) {

			e.printStackTrace();
			throw new ServiceException(InfoMessages.PAYCASH, e);
		} catch (SQLException e) {

			e.printStackTrace();
			throw new ServiceException(e);
		}

	}

	public int addUser(UserAccountDetails a) throws ServiceException {
		int au = 0;
		try {
			au = g.addUser(a);
		} catch (DbException e) {
			
			e.printStackTrace();
			throw new ServiceException(InfoMessages.ADDUSER, e);
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new ServiceException(e);
			
		}
		return au;

	}

	public boolean forgetPassword(int userid, String password) throws ServiceException {
		boolean bo3=false;
		try {
			bo3 = g.forgetPassword(userid, password);
		} catch (DbException e) {
			
			e.printStackTrace();
			throw new ServiceException(InfoMessages.FORGETPASSWORD, e);
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new ServiceException(e);
		}
		return bo3;
	}

	public boolean validateEmailId(String emailId) throws ServiceException {
		boolean bo4=false;
		try {
			bo4 = g.validateEmailId(emailId);
		} catch (DbException e) {
			
			e.printStackTrace();
			throw new ServiceException(InfoMessages.VALIDATEEMAIL, e);
		} catch (SQLException e) {
		
			e.printStackTrace();
			throw new ServiceException(e);
		}
		return bo4;

	}

	public boolean validateLogin(int userId, String password) throws ServiceException {

		boolean bo5=false;
		try {
			bo5 = g.validateLogin(userId, password);
		} catch (DbException e) {
		
			e.printStackTrace();
			throw new ServiceException(InfoMessages.VALIDATELOGIN, e);
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new ServiceException(e);
		}
		return bo5;

	}

	public boolean validateEmailIdWithUserId(String emailId, int userId) throws ServiceException {

		boolean bo6=false;
		try {
			bo6 = g.validateEmailIdWithUserId(emailId, userId);
		} catch (DbException e) {
		
			e.printStackTrace();
			throw new ServiceException(InfoMessages.VALIDATEEMAILWITHUSERID, e);
		} catch (SQLException e) {
		
			e.printStackTrace();
			throw new ServiceException(e);
		}
		return bo6;
	}

}
