package com.chainsys.ebus.dao;

import java.util.ArrayList;

import com.chainsys.ebus.model.FindBus;

public interface FindBusDAO {

	public ArrayList<FindBus> searchbus(String a,String b,String c) throws Exception ;

}
