package SearchBus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import AdminRole.TestConnection;
import Exception.DbException;
import Exception.infoMessages;

public class FindBusDAOImpl implements FindBusDAO{
	public ArrayList<FindBus> searchbus(String fromLocation, String toLocation, String journeyDate) throws Exception  {
		
		
		
		try(Connection con = TestConnection.connection();){
		String sql="select bus_name,bus_id,ticket_price,travelling_time from bus_details where from_location= ? and to_location= ? and journey_date= ?";
		try(PreparedStatement pst=con.prepareStatement(sql);){
		pst.setString(1,fromLocation);
		pst.setString(2,toLocation);
		java.sql.Date journeyDate1=java.sql.Date.valueOf(journeyDate);
		pst.setDate(3,journeyDate1);
		try(ResultSet rows=pst.executeQuery();){
		ArrayList<FindBus> buses1=new ArrayList<FindBus>();
		while(rows.next()) {
			
			FindBus f=new FindBus();
	
			f.setBusName(rows.getString("bus_name"));
			f.setBusId(rows.getInt("bus_id"));
			f.setTravellingTime(rows.getString("travelling_time"));
			f.setTicketPrice(rows.getInt("ticket_price"));
			try(Statement stmt=con.createStatement();){
			try(ResultSet rs=stmt.executeQuery("select available_seats from seat_availability where bus_id="+f.getBusId());){
			while(rs.next())
			{
				int seats=rs.getInt("available_seats");
				if(seats>0)
				{
			buses1.add(f);
				}
			}
		}
			}
		}
		return buses1;}}}
		catch (SQLException e) {
			
			e.printStackTrace();
			throw new DbException(infoMessages.FINDBUS);
			
		
		}
catch (Exception e) {
			
			e.printStackTrace();
			throw new DbException(infoMessages.CONNECTION);
			
		}

	
	}
}
