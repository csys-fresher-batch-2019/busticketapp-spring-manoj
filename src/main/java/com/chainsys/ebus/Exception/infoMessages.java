package com.chainsys.ebus.Exception;

public class infoMessages {
	private infoMessages() {
		throw new IllegalStateException("Utility class");
	}
	public static final String CONNECTION="Unable to establish Connection Server Error";
	public static final String AVAILABLESEATS="Unable to get no of seats";
	public static final String ADDBUS="Unable to add Bus";
	public static final String REMOVEBUS="Unable to remove Bus";
	public static final String BUSTIMEUPDATE="Unable to update bus time";
	public static final String FROMLOCATION="Unable to find from location";
	public static final String TOLOCATION="Unable to find to location";
	public static final String ADDUSER="Unable to add user";
	public static final String CHECKEMAIL="Unable to check email";
	public static final String VALIDATELOGIN="Unable to validate login";
	public static final String FORGETPASSWORD="Unable to find emailid or userid";
	public static final String INSERTPASSENGERINFO="Unable to insert passenger info";
	public static final String VALIDATEBOOKINGID="Unable to validate bookingId";
	public static final String BOOKINGDETAILS="Unable to find booking details";
	public static final String CANCELBOOKING="Unable to cancel booking";
	public static final String VALIDATEBUSID="Unable to validate busId";
	public static final String TOTALPRICE="Unable to calculate total price";
	public static final String MYBOOKINGDETAILS="Unable to find mybooking details";
	public static final String PAYMENTSUCCESS="Unable to update payment status";
	public static final String PAYMENTFAILURE="Unable to update payment status";
	public static final String PAYCASH="Unable to update payment status";
	public static final String FINDBUS="Unable to find bus details";













}
