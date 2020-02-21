package payment;

public interface paymentDAO {
	public void paymentSuccess(int bookingId) throws Exception;
	public void paymentFailure(int bookingId) throws Exception;
	public boolean cashPay(int bookingId) throws Exception;
}
