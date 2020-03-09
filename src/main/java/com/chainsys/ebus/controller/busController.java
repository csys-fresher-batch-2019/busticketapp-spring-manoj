package com.chainsys.ebus.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chainsys.ebus.dao.FindBusDAO;
import com.chainsys.ebus.dao.busDetailsDAO;
import com.chainsys.ebus.dao.passengerInfoDAO;
import com.chainsys.ebus.dao.paymentDAO;
import com.chainsys.ebus.dao.userAccountDetailsDAO;
import com.chainsys.ebus.dao.impl.FindBusDAOImpl;
import com.chainsys.ebus.dao.impl.busDetailsDAOImpl;
import com.chainsys.ebus.dao.impl.passengerInfoDAOImpl;
import com.chainsys.ebus.dao.impl.paymentDAOImpl;
import com.chainsys.ebus.dao.impl.userAccountDetailsDAOImpl;
import com.chainsys.ebus.model.FindBus;
import com.chainsys.ebus.model.busDetails;
import com.chainsys.ebus.model.passengerInfo;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("api")
public class busController {

	busDetailsDAO a = new busDetailsDAOImpl();

	@PostMapping("/addbuses")
	public void addBus(@RequestParam("bus_id") Integer bid, @RequestParam("bus_name") String busname,
			@RequestParam("from_location") String fromLocation, @RequestParam("to_location") String toLocation,
			@RequestParam("journey_date") String date, @RequestParam("ticket_price") Integer tprice,
			@RequestParam("travelling_time") String travelTime, @RequestParam("maximun_seats") Integer maximumSeats,
			@RequestParam("available_seats") Integer availableSeats) {
		busDetails b = new busDetails();

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
			a = dao.searchbus(fromLocation, toLocation, date);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return a;

	}
	
	@PostMapping("/updateBusTimings")
	public void updateBusTiming(@RequestParam("bus_id") Integer bid,@RequestParam("travelling_time") String travelTime) {
		busDetails b = new busDetails();
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
	
	userAccountDetailsDAO dao= new userAccountDetailsDAOImpl();

	
	@GetMapping("/login")
		public boolean validateLogin2(@RequestParam("userId") Integer userId,@RequestParam("password") String password) {
		boolean res=false;
		try {
			
			 res=dao.validateLogin2(userId, password);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}
	
	passengerInfoDAO p=new passengerInfoDAOImpl();
	@PostMapping("/passengerDetails")
	public int insertPassengerInfo(@RequestParam("busid") Integer bid,@RequestParam("userid") Integer userid, @RequestParam("passengername") String passengerName,
			@RequestParam("age") Integer age,@RequestParam("gender") String gender,@RequestParam("mobileNumber") Long mobNum,@RequestParam("noOfTickets") Integer noOfTickets) {
		passengerInfo i=new passengerInfo();
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
	public ArrayList<passengerInfo> MyBookings(@RequestParam("userid") Integer userId){
		
		ArrayList<passengerInfo> pi=new ArrayList<passengerInfo>();
		try {
			pi=p.MyBookings(userId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return pi;
	 
	
}
	paymentDAO dao1=new paymentDAOImpl();
	@PostMapping("/cashpay")
	public boolean cashPay(@RequestParam("bookingid") Integer bookingId){
		
		try {
			dao1.cashPay(bookingId);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	
return true;
	}
	} 


	
	

