package com.chainsys.ebus.service;

import java.sql.SQLException;

import com.chainsys.ebus.dao.BusDAO;
import com.chainsys.ebus.dao.impl.BusDAOImpl;
import com.chainsys.ebus.exception.DbException;
import com.chainsys.ebus.exception.InfoMessages;
import com.chainsys.ebus.exception.ServiceException;
import com.chainsys.ebus.model.Bus;

public class AdminService {

	private BusDAO b = new BusDAOImpl();

	public void addBus(Bus a) throws ServiceException {
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

	public void updateBusTiming(Bus a) throws ServiceException {
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
