package BusDetails;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import AdminRole.TestConnection;
import Exception.DbException;
import Exception.infoMessages;


public class busDetailsDAOImpl implements busDetailsDAO {
	public void addBus(busDetails a) throws Exception {
		try(Connection con = TestConnection.connection();){
		String sql = "insert into bus_details (bus_id,bus_name,from_location,to_location,journey_date,ticket_price,travelling_time) values (?,?,?,?,?,?,?)";
		try(PreparedStatement pst = con.prepareStatement(sql);){
		pst.setInt(1, a.getBusId());
		pst.setString(2, a.getBusName());
		pst.setString(3,a.getFromLocation());
		pst.setString(4,a.getToLocation());
		java.sql.Date journeyDate=java.sql.Date.valueOf(a.getJourneyDate());
		pst.setDate(5,journeyDate);
		pst.setInt(6, a.getTicketPrice());
		pst.setString(7,a.getTravellingTime());
        pst.executeUpdate();
        int busId=a.getBusId();
		
        
       
		
        
        String sql1="insert into seat_availability (bus_id,maximum_seats,available_seats) values(?,?,?)";
        try(PreparedStatement pst1 = con.prepareStatement(sql1);){
        pst1.setInt(1,busId);
        pst1.setInt(2,a.getMaximumSeats());
        pst1.setInt(3,a.getAvailableSeats());
        pst1.executeUpdate();
        
        
		System.out.println("Bus Details are added successfully\n");
		
        }}}
		catch(SQLException e) {
			e.printStackTrace();
			throw new DbException(infoMessages.ADDBUS);
		}
        catch (Exception e) {
			
			e.printStackTrace();
			throw new DbException(infoMessages.CONNECTION);
			
			
		}
		
		}
	public void removeBus(int busId) throws Exception {
  try( Connection con = TestConnection.connection();){
		
		String sql = "delete from bus_details where bus_id=?";
		try(PreparedStatement pst=con.prepareStatement(sql);){
		pst.setInt(1,busId);
         pst.executeUpdate();
         
         String sql1 = "delete from seat_availability where bus_id=?";
 		try(PreparedStatement pst1=con.prepareStatement(sql1);){
 		pst1.setInt(1,busId);
          pst1.executeUpdate();
		
		System.out.println("Bus Details are deleted Successfully");
 		}}}
  catch (SQLException e) {
		
		e.printStackTrace();
		throw new DbException(infoMessages.REMOVEBUS);
		
	}
  catch (Exception e) {
		
		e.printStackTrace();
		throw new DbException(infoMessages.CONNECTION);
		
	}
		
	}
	public void updateBusTiming(busDetails a) throws Exception {
		   try(Connection con = TestConnection.connection();){
				
				String sql = "update bus_details set travelling_time=? where bus_id=?";
				try(PreparedStatement pst=con.prepareStatement(sql);){
				
				pst.setString(1,a.getTravellingTime());
				pst.setInt(2,a.getBusId());
		        pst.executeUpdate();
		        int busId=a.getBusId();
		        
		       
				
				System.out.println("Bus timings are Updated Successfully");
				}}
		   catch (SQLException e) {
				
				e.printStackTrace();
				throw new DbException(infoMessages.BUSTIMEUPDATE);
				
		   }
				 catch (Exception e) {
						
						e.printStackTrace();
						throw new DbException(infoMessages.CONNECTION);
						
						
					}
				
			}
				


	
	public List<String> getFromLocation() throws Exception {
		
		try(Connection con = TestConnection.connection();){
		String sql="select distinct from_location from bus_details";
		try(Statement pst=con.createStatement();){
		try(ResultSet rs=pst.executeQuery(sql);){
		ArrayList<String> from=new ArrayList<String>();
		while(rs.next())
		{
			String a=rs.getString("from_location");
			from.add(a);
		}
		
		return from;
		}}}
		catch (SQLException e) {
			
			e.printStackTrace();
			throw new DbException(infoMessages.FROMLOCATION);
			
		
		}
		catch (Exception e) {
			
			e.printStackTrace();
			throw new DbException(infoMessages.CONNECTION);
			
			
		}
	}
	public List<String> getToLocation() throws Exception {
		
		try(Connection con = TestConnection.connection();){
		String sql="select distinct to_location from bus_details";
		try(Statement pst=con.createStatement();){
		try(ResultSet rs=pst.executeQuery(sql);){
		ArrayList<String> to=new ArrayList<String>();
		while(rs.next())
		{
			String a=rs.getString("to_location");
			to.add(a);
		}
		return to;
	}
		}}
	catch (SQLException e) {
		
		e.printStackTrace();
		throw new DbException(infoMessages.TOLOCATION);
		
		
		

	}
catch (Exception e) {
			
			e.printStackTrace();
			throw new DbException(infoMessages.CONNECTION);
			
			
		}
	
	}}

