package com.chainsys.ebus.model;

public class AvailableSeats {
	private int busId;
	private int availableSeats;
	private int maximumSeats;

	public int getBusId() {
		return busId;
	}

	public void setBusId(int busId) {
		this.busId = busId;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public int getMaxseats() {
		return maximumSeats;
	}

	public void setMaxseats(int maxseats) {
		this.maximumSeats = maxseats;
	}

}
