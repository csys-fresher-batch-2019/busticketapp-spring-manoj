package com.chainsys.ebus.dao;

import java.sql.SQLException;
import java.util.List;

import com.chainsys.ebus.exception.DbException;
import com.chainsys.ebus.model.FindBus;

public interface FindBusDAO {

	public List<FindBus> searchBus(String a,String b,String c) throws DbException,SQLException ;

}
