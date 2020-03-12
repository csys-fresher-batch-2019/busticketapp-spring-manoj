package com.chainsys.ebus.dao;

import java.sql.SQLException;
import java.util.List;

import com.chainsys.ebus.exception.DbException;
import com.chainsys.ebus.model.Bus;

public interface BusDAO {
	public void addBus(Bus a) throws DbException,SQLException;
	public void removeBus(int busId) throws DbException,SQLException;
	public void updateBusTiming(Bus a) throws DbException,SQLException ;
	public  List<String> getFromLocation() throws DbException,SQLException ;
	public  List<String> getToLocation() throws DbException,SQLException ;
	
    
}
