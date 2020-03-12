package com.chainsys.ebus.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.chainsys.ebus.exception.DbException;
import com.chainsys.ebus.exception.InfoMessages;



public class ConnectionUtil {

	public static Connection connection() throws DbException,SQLException{
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@13.235.147.120:1521:XE", "manoj", "manoj");
			//  Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "oracle");
			  return connection;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DbException(InfoMessages.CONNECTION, e);
		}
		
		

	}

}
	