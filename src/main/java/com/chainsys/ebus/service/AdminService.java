package com.chainsys.ebus.service;

import java.sql.SQLException;

import com.chainsys.ebus.dao.BusDetailsDAO;
import com.chainsys.ebus.dao.impl.BusDetailsDAOImpl;
import com.chainsys.ebus.exception.DbException;
import com.chainsys.ebus.exception.InfoMessages;
import com.chainsys.ebus.exception.ServiceException;
import com.chainsys.ebus.model.BusDetails;

public class AdminService {

	private BusDetailsDAO b = new BusDetailsDAOImpl();

	public void addBus(BusDetails a) throws ServiceException {
		try {
			b.addBus(a);
		} catch (DbException e  ) {
			e.printStackTrace();
			throw new ServiceException(InfoMessages.ADDBUS, e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public void removeBus(int busId) throws ServiceException {
		try {
			b.removeBus(busId);
		} catch (DbException e) {
			e.printStackTrace();
			throw new ServiceException(InfoMessages.REMOVEBUS, e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public void updateBusTiming(BusDetails a) throws ServiceException {
		try {
		b.updateBusTiming(a);
	}
	 catch (DbException e) {
			e.printStackTrace();
			throw new ServiceException(InfoMessages.BUSTIMEUPDATE, e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
}
