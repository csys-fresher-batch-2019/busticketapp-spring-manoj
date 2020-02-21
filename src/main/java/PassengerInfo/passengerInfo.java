package PassengerInfo;

public class passengerInfo {
	public passengerInfo(int bookingId, int userId, int busId, String passengerName, Long mobileNumber, int noOfTickets,
			int age, String gender) {
		super();
		this.bookingId = bookingId;
		this.userId = userId;
		this.busId = busId;
		this.passengerName = passengerName;
		this.mobileNumber = mobileNumber;
		this.noOfTickets = noOfTickets;
		this.age = age;
		this.gender = gender;
		
	}
	public passengerInfo() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "passengerInfo [bookingId=" + bookingId + ", userId=" + userId + ", busId=" + busId + ", passengerName="
				+ passengerName + ", mobileNumber=" + mobileNumber + ", noOfTickets=" + noOfTickets + ", age=" + age
				+ ", gender=" + gender + "]";
	}
	private int bookingId;
	private int userId;
	private int busId;
	private String passengerName;
	private Long mobileNumber;
	private int noOfTickets;
	private int age;
	private String gender;
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBusId() {
		return busId;
	}
	public void setBusId(int busId) {
		this.busId = busId;
	}
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public int getNoOfTickets() {
		return noOfTickets;
	}
	public void setNoOfTickets(int noOfTickets) {
		this.noOfTickets = noOfTickets;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	

}
