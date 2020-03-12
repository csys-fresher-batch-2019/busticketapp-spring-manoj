package com.chainsys.ebus.dao;

import java.sql.SQLException;

import com.chainsys.ebus.exception.DbException;

public interface AvailableSeatsDAO {
	
 public int seatsAvailable (int busid) throws  DbException, SQLException;
}
