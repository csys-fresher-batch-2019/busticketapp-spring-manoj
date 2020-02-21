package PassengerInfo;

import java.util.ArrayList;

//import org.jdbi.v3.sqlobject.statement.SqlUpdate;



public interface passengerInfoDAO {
	public int insertPassengerInfo(passengerInfo a) throws Exception;
	//@SqlUpdate("select *from passenger_details where booking_id=?")
	public ArrayList<passengerInfo> BookingDetails(int bookingId) throws Exception ;
	public void cancelBooking(int bookingId) throws Exception;
	//@SqlUpdate("select booking_id from passenger_details where booking_id=?")
	 public boolean validateBookingId(int bookingId) throws Exception;
	//@SqlUpdate("select bus_id from bus_details where bus_id = ?")
	 public boolean validateBusId(int busId) throws Exception ;
	// @SqlUpdate("select total_price from payment_status where booking_id=? ")
		public int totalPrice(int bookingId) throws Exception;
	//	@SqlUpdate("select *from passenger_details where user_id=? and booking_status='booked'")
		public ArrayList<passengerInfo> MyBookings(int userId) throws Exception ;
	 
}
