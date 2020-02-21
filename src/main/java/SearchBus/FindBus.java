package SearchBus;

public class FindBus {
	private int busId;
	private String busName;
	private int ticketPrice;
	private String travellingTime;
	
	@Override
	public String toString() {
		return "FindBus [busId=" + busId + ", busName=" + busName + ", ticketPrice=" + ticketPrice + ", travellingTime="
				+ travellingTime + "]";
	}
	public int getBusId() {
		return busId;
	}
	public void setBusId(int busId) {
		this.busId = busId;
	}
	public String getBusName() {
		return busName;
	}
	public void setBusName(String busName) {
		this.busName = busName;
	}
	public int getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public String getTravellingTime() {
		return travellingTime;
	}
	public void setTravellingTime(String travellingTime) {
		this.travellingTime = travellingTime;
	}
	public FindBus(int busId, String busName, int ticketPrice, String travellingTime) {
		super();
		this.busId = busId;
		this.busName = busName;
		this.ticketPrice = ticketPrice;
		this.travellingTime = travellingTime;
	}
	public FindBus() {
		// TODO Auto-generated constructor stub
	}
	
	
	

}
