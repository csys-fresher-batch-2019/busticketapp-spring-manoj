package com.chainsys.ebusapp.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import BusDetails.busDetails;
import BusDetails.busDetailsDAO;
import BusDetails.busDetailsDAOImpl;
import SearchBus.FindBus;
import SearchBus.FindBusDAO;
import SearchBus.FindBusDAOImpl;


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
	
	@DeleteMapping("/removeBus")
	public void removeBus(@RequestParam("bus_id") Integer bid) {
		
		try {
			a.removeBus(bid);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
		
		
	}
	}
	
	

