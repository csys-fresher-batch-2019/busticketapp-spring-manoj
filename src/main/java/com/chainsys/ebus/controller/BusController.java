package com.chainsys.ebus.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chainsys.ebus.dao.FindBusDAO;
import com.chainsys.ebus.dao.BusDetailsDAO;
import com.chainsys.ebus.dao.PassengerInfoDAO;
import com.chainsys.ebus.dao.PaymentDAO;
import com.chainsys.ebus.dao.UserAccountDetailsDAO;
import com.chainsys.ebus.dao.impl.FindBusDAOImpl;
import com.chainsys.ebus.dao.impl.BusDetailsDAOImpl;
import com.chainsys.ebus.dao.impl.PassengerInfoDAOImpl;
import com.chainsys.ebus.dao.impl.PaymentDAOImpl;
import com.chainsys.ebus.dao.impl.UserAccountDetailsDAOImpl;
import com.chainsys.ebus.model.FindBus;
import com.chainsys.ebus.model.BusDetails;
import com.chainsys.ebus.model.PassengerInfo;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("api")
public class BusController {

	BusDetailsDAO a = new BusDetailsDAOImpl();

	@PostMapping("/addbuses")
	public void addBus(@RequestParam("bus_id") Integer bid, @RequestParam("bus_name") String busname,
			@RequestParam("from_location") String fromLocation, @RequestParam("to_location") String toLocation,
			@RequestParam("journey_date") String date, @RequestParam("ticket_price") Integer tprice,
			@RequestParam("travelling_time") String travelTime, @RequestParam("maximun_seats") Integer maximumSeats,
			@RequestParam("available_seats") Integer availableSeats) {
		BusDetails b = new BusDetails();

		b.setBusId(bid);
		b.setBusName(busname);
		b.setFromLocation(fromLocation);
		b.setToLocation(toLocation);
		b.setJourneyDate(date);
		b.setTicketPrice(tprice);
		b.setTravellingTime(travelTime);
		b.setMaximumSeats(maximumSeats);
		b.setAvailableSeats(availableSeats);
		try {
			a.addBus(b);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@GetMapping("/searchbuses")
	public ArrayList<FindBus> searchbus(@RequestParam("from_location") String fromLocation,
			@RequestParam("to_location") String toLocation, @RequestParam("journey_date") String date) {

		FindBusDAO dao = new FindBusDAOImpl();
		ArrayList<FindBus> a = new ArrayList<FindBus>();
		try {
			a = dao.searchBus(fromLocation, toLocation, date);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return a;

	}
	
	@PostMapping("/updateBusTimings")
	public void updateBusTiming(@RequestParam("bus_id") Integer bid,@RequestParam("travelling_time") String travelTime) {
		BusDetails b = new BusDetails();
		//busDetailsDAO a = new busDetailsDAOImpl();
		b.setBusId(bid);
		b.setTravellingTime(travelTime);
		try {
	a.updateBusTiming(b);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//@DeleteMapping("/removeBus")
	@PostMapping("/removeBus")
	public void removeBus(@RequestParam("bus_id") Integer bid) {
		
		try {
			a.removeBus(bid);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
		 }
	
	UserAccountDetailsDAO dao= new UserAccountDetailsDAOImpl();

	
	@GetMapping("/login")
		public boolean validateLogin2(@RequestParam("userId") Integer userId,@RequestParam("password") String password) {
		boolean res=false;
		try {
			
			 res=dao.validateLogin(userId, password);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}
	
	PassengerInfoDAO p=new PassengerInfoDAOImpl();
	@PostMapping("/passengerDetails")
	public int insertPassengerInfo(@RequestParam("busid") Integer bid,@RequestParam("userid") Integer userid, @RequestParam("passengername") String passengerName,
			@RequestParam("age") Integer age,@RequestParam("gender") String gender,@RequestParam("mobileNumber") Long mobNum,@RequestParam("noOfTickets") Integer noOfTickets) {
		PassengerInfo i=new PassengerInfo();
		i.setBusId(bid);
		i.setUserId(userid);
		i.setPassengerName(passengerName);
		i.setAge(age);
		i.setGender(gender);
		i.setMobileNumber(mobNum);
		i.setNoOfTickets(noOfTickets);
		
		
		int bId = 0;
		try
		{
			bId=p.insertPassengerInfo(i);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		
		
		return bId;
	
	
}

	@GetMapping("/myTickets")
	public ArrayList<PassengerInfo> MyBookings(@RequestParam("userid") Integer userId){
		
		ArrayList<PassengerInfo> pi=new ArrayList<PassengerInfo>();
		try {
			pi=p.myBookings(userId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return pi;
	 
	
}
	PaymentDAO dao1=new PaymentDAOImpl();
	@PostMapping("/cashpay")
	public boolean cashPay(@RequestParam("bookingid") Integer bookingId){
		
		try {
			dao1.cashPayment(bookingId);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	
return true;
	}
	} 


	
	

