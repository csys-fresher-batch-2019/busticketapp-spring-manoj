
package AdminRole;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Exception.DbException;
import Exception.infoMessages;



public class TestConnection {

	public static Connection connection() throws Exception {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DbException(infoMessages.CONNECTION);
		}
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@13.235.147.120:1521:XE", "manoj", "manoj");

		return connection;

	}

}
