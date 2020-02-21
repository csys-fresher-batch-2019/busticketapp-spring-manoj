package AvailableSeats;

import Exception.DbException;

public interface availableSeatsDAO {
	//@SqlUpdate("select available_seats from seat_availability where bus_id=: bus_id ")
 public int seatavail (int busid) throws  Exception;
}
