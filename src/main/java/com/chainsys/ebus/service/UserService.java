package com.chainsys.ebus.service;

import java.sql.SQLException;
import java.util.List;

import com.chainsys.ebus.dao.FindBusDAO;
import com.chainsys.ebus.dao.AvailableSeatsDAO;
import com.chainsys.ebus.dao.BusDAO;
import com.chainsys.ebus.dao.PassengerInfoDAO;
import com.chainsys.ebus.dao.PaymentDAO;
import com.chainsys.ebus.dao.UserAccountDAO;
import com.chainsys.ebus.dao.impl.FindBusDAOImpl;
import com.chainsys.ebus.dao.impl.AvailableSeatsDAOImpl;
import com.chainsys.ebus.dao.impl.BusDAOImpl;
import com.chainsys.ebus.dao.impl.PassengerInfoDAOImpl;
import com.chainsys.ebus.dao.impl.PaymentDAOImpl;
import com.chainsys.ebus.dao.impl.UserAccountDAOImpl;
import com.chainsys.ebus.exception.DbException;
import com.chainsys.ebus.exception.InfoMessages;
import com.chainsys.ebus.exception.ServiceException;
import com.chainsys.ebus.model.FindBus;
import com.chainsys.ebus.model.PassengerInfo;
import com.chainsys.ebus.model.UserAccount;

public class UserService {
	private AvailableSeatsDAO a = new AvailableSeatsDAOImpl();
	private BusDAO b = new BusDAOImpl();
	private FindBusDAO d = new FindBusDAOImpl();
	private PassengerInfoDAO e = new PassengerInfoDAOImpl();
	private PaymentDAO f = new PaymentDAOImpl();
	private UserAccountDAO g = new UserAccountDAOImpl();

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

	public List<FindBus> searchBus(String a, String b, String c) throws ServiceException {
		List<FindBus> buses = null;
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

	public List<PassengerInfo> bookingDetails(int bookingId) throws ServiceException {

		List<PassengerInfo> info = null;
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
		boolean b = false;
		try {
			b = e.validateBookingId(bookingId);
		} catch (DbException e) {

			e.printStackTrace();
			throw new ServiceException(InfoMessages.VALIDATEBOOKINGID, e);
		} catch (SQLException e) {

			e.printStackTrace();
			throw new ServiceException(e);
		}
		return b;

	}

	public boolean validateBusId(int busId) throws ServiceException {

		boolean b = false;
		try {
			b = e.validateBookingId(busId);
		} catch (DbException e) {
			e.printStackTrace();
			throw new ServiceException(InfoMessages.VALIDATEBUSID, e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
		return b;

	}

	public int totalPrice(int bookingId) throws ServiceException {
		int t = 0;
		try {
			t = e.totalPrice(bookingId);
		} catch (DbException e) {

			e.printStackTrace();
			throw new ServiceException(InfoMessages.TOTALPRICE, e);
		} catch (SQLException e) {

			e.printStackTrace();
			throw new ServiceException(e);
		}
		return t;
	}

	public List<PassengerInfo> myBookings(int userId) throws ServiceException {
		List<PassengerInfo> p = null;
		try {
			p = e.myBookings(userId);
		} catch (DbException e) {

			e.printStackTrace();
			throw new ServiceException(InfoMessages.MYBOOKINGDETAILS, e);
		} catch (SQLException e) {

			e.printStackTrace();
			throw new ServiceException(e);
		}
		return p;
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

	public int addUser(UserAccount a) throws ServiceException {
		int add = 0;
		try {
			add = g.addUser(a);
		} catch (DbException e) {

			e.printStackTrace();
			throw new ServiceException(InfoMessages.ADDUSER, e);
		} catch (SQLException e) {

			e.printStackTrace();
			throw new ServiceException(e);

		}
		return add;

	}

	public boolean forgetPassword(int userid, String password) throws ServiceException {
		boolean b = false;
		try {
			b = g.forgetPassword(userid, password);
		} catch (DbException e) {

			e.printStackTrace();
			throw new ServiceException(InfoMessages.FORGETPASSWORD, e);
		} catch (SQLException e) {

			e.printStackTrace();
			throw new ServiceException(e);
		}
		return b;
	}

	public boolean validateEmailId(String emailId) throws ServiceException {
		boolean b = false;
		try {
			b = g.validateEmailId(emailId);
		} catch (DbException e) {

			e.printStackTrace();
			throw new ServiceException(InfoMessages.VALIDATEEMAIL, e);
		} catch (SQLException e) {

			e.printStackTrace();
			throw new ServiceException(e);
		}
		return b;

	}

	public boolean validateLogin(int userId, String password) throws ServiceException {

		boolean b = false;
		try {
			b = g.validateLogin(userId, password);
		} catch (DbException e) {

			e.printStackTrace();
			throw new ServiceException(InfoMessages.VALIDATELOGIN, e);
		} catch (SQLException e) {

			e.printStackTrace();
			throw new ServiceException(e);
		}
		return b;

	}

	public boolean validateEmailIdWithUserId(String emailId, int userId) throws ServiceException {

		boolean b = false;
		try {
			b = g.validateEmailIdWithUserId(emailId, userId);
		} catch (DbException e) {

			e.printStackTrace();
			throw new ServiceException(InfoMessages.VALIDATEEMAILWITHUSERID, e);
		} catch (SQLException e) {

			e.printStackTrace();
			throw new ServiceException(e);
		}
		return b;
	}

}
