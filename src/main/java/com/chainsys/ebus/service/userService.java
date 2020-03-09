package com.chainsys.ebus.service;

import java.util.ArrayList;
import java.util.List;

import com.chainsys.ebus.dao.FindBusDAO;
import com.chainsys.ebus.dao.availableSeatsDAO;
import com.chainsys.ebus.dao.busDetailsDAO;
import com.chainsys.ebus.dao.passengerInfoDAO;
import com.chainsys.ebus.dao.paymentDAO;
import com.chainsys.ebus.dao.userAccountDetailsDAO;
import com.chainsys.ebus.dao.impl.FindBusDAOImpl;
import com.chainsys.ebus.dao.impl.availableSeatsDAOImpl;
import com.chainsys.ebus.dao.impl.busDetailsDAOImpl;
import com.chainsys.ebus.dao.impl.passengerInfoDAOImpl;
import com.chainsys.ebus.dao.impl.paymentDAOImpl;
import com.chainsys.ebus.dao.impl.userAccountDetailsDAOImpl;
import com.chainsys.ebus.model.FindBus;
import com.chainsys.ebus.model.passengerInfo;
import com.chainsys.ebus.model.userAccountDetails;

public class userService {
	private availableSeatsDAO a = new availableSeatsDAOImpl();
	private busDetailsDAO b = new busDetailsDAOImpl();
	private FindBusDAO d = new FindBusDAOImpl();
	private passengerInfoDAO e= new passengerInfoDAOImpl();
	private paymentDAO f=new paymentDAOImpl();
	 private userAccountDetailsDAO g=new  userAccountDetailsDAOImpl();

	public int seatavail(int busid) throws Exception {

		int busid1 = a.seatavail(busid);

		return busid1;

	}

	public List<String> getFromLocation() throws Exception {
		List<String> from = b.getFromLocation();
		return from;

	}

	public List<String> getToLocation() throws Exception {
		List<String> to = b.getToLocation();
		
		return to;

	}

	public ArrayList<FindBus> searchbus(String a,String b,String c) throws Exception
	{
		ArrayList<FindBus> buses = d.searchbus(a,b,c);
		

		return buses;
		
		
	}
	
	public int insertPassengerInfo(passengerInfo a) throws Exception{
		
		int info=e.insertPassengerInfo(a);
		return info;
	}
	
	public ArrayList<passengerInfo> BookingDetails(int bookingId) throws Exception {
		
		ArrayList<passengerInfo> info=e.BookingDetails(bookingId);
		
		return info;
	
}
	
	public void cancelBooking(int bookingId) throws Exception{
		e.cancelBooking(bookingId);
}
	
	 public boolean validateBookingId(int bookingId) throws Exception
	 {
		 boolean bo=e.validateBookingId(bookingId);
		return bo;
		 
	 }
	 
	 public boolean validateBusId(int busId) throws Exception {
		 
		 
		 boolean bo1=e.validateBookingId(busId);
			return bo1;
	
	
	 }
	 
		public int totalPrice(int bookingId) throws Exception
		{
			int tp=e.totalPrice(bookingId);
			return tp;
		}
		
		public ArrayList<passengerInfo> MyBookings(int userId) throws Exception{
			 ArrayList<passengerInfo> pi=e.MyBookings(userId);
			 return pi;
		}
		
		
		public void paymentSuccess(int bookingId) throws Exception{
			f.paymentSuccess(bookingId);
		}
		
		public void paymentFailure(int bookingId) throws Exception{
			f.paymentFailure(bookingId);
		}
		 
		public boolean cashPay(int bookingId) throws Exception{
			 boolean bo2=f.cashPay(bookingId);
				return bo2;
			
		}
		
		public  int addUser (userAccountDetails a) throws Exception{
			int au=g.addUser(a);
			return au;
			
		}
		
		public  boolean forgetPassword(int userid,String password) throws Exception {
		boolean bo3=g.forgetPassword(userid, password);
		return bo3;
		}
		
		public boolean checkEmailId(String emailId) throws Exception{
			boolean bo4=g.checkEmailId(emailId);
			return bo4;
			
			
		}
		
		  public boolean validateLogin2(int userId,String password) throws Exception{
			 
			  boolean bo5=g.validateLogin2(userId, password);
				return bo5;
				  
		  }
		  
		  public boolean checkEmailId2(String emailId,int userId) throws Exception {
			  
			  boolean bo6=g.checkEmailId2(emailId, userId);
				return bo6;
		  }
		  
		
		
		
		
		}

