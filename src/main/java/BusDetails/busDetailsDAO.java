package BusDetails;


import java.util.List;

//import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface busDetailsDAO {
	public void addBus(busDetails a) throws Exception;
	public void removeBus(int busId) throws Exception ;
	//@SqlUpdate("update bus_details set travelling_time=? where bus_id=?")
	public void updateBusTiming(busDetails a) throws Exception ;
	//@SqlUpdate("select distinct from_location from bus_details")
	public  List<String> getFromLocation() throws Exception ;
	//@SqlUpdate("select distinct to_location from bus_details")
	public  List<String> getToLocation() throws Exception ;
	
    
}
