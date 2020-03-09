package com.chainsys.ebus.service;

import com.chainsys.ebus.dao.busDetailsDAO;
import com.chainsys.ebus.dao.impl.busDetailsDAOImpl;
import com.chainsys.ebus.model.busDetails;

public class adminService {
	
	private busDetailsDAO b = new busDetailsDAOImpl();
	
	public void addBus(busDetails a) throws Exception{
		b.addBus(a);
		
	}
	
	public void removeBus(int busId) throws Exception{
		b.removeBus(busId);
	}
	
	public void updateBusTiming(busDetails a) throws Exception {
		b.updateBusTiming(a);
	}

}
