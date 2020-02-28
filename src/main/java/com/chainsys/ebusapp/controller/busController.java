package com.chainsys.ebusapp.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import BusDetails.busDetails;
import BusDetails.busDetailsDAO;
import BusDetails.busDetailsDAOImpl;


@RestController
@RequestMapping("api")
public class busController {
	
	busDetailsDAO a=new busDetailsDAOImpl();
	
	@PostMapping("/buses")
	public void addBus(@RequestParam("bus_id") Integer bid,@RequestParam("bus_name") String busname,
			@RequestParam("from_location") String fromLocation,@RequestParam("to_location") String toLocation,
			@RequestParam("journey_date") String date,@RequestParam("ticket_price") Integer tprice,@RequestParam("travelling_timr") String travelTime,
			@RequestParam("maximun_seats") Integer maximumSeats,@RequestParam("available_seats") Integer availableSeats)
	{
		busDetails b=new busDetails();

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
		
}

