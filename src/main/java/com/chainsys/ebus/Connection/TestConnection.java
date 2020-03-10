
package com.chainsys.ebus.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.chainsys.ebus.Exception.DbException;
import com.chainsys.ebus.Exception.infoMessages;



public class TestConnection {

	public static Connection connection() throws Exception {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DbException(infoMessages.CONNECTION);
		}
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@13.235.147.120:1521:XE", "manoj", "manoj");
	  //Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "oracle");

		return connection;

	}

}
	