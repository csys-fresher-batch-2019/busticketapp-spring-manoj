package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import PassengerInfo.passengerInfo;
import PassengerInfo.passengerInfoDAO;
import PassengerInfo.passengerInfoDAOImpl;

@WebServlet("/passengerservlet")
public class passengerservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		passengerInfoDAO dao=new passengerInfoDAOImpl();
		passengerInfo p=new passengerInfo();
		String busid = request.getParameter("busid");
		p.setBusId(Integer.parseInt(busid));
		String userid = request.getParameter("userid");
		p.setUserId(Integer.parseInt(userid));
		p.setPassengerName(request.getParameter("passengerName"));
		String age = request.getParameter("Age");
		p.setAge(Integer.parseInt(age));
		p.setGender(request.getParameter("Gender"));
		String mobilenumber = request.getParameter("mobileNumber");
		p.setMobileNumber(Long.parseLong(mobilenumber));
		String NoOfTickets = request.getParameter("noOfTickets");
		p.setNoOfTickets(Integer.parseInt(NoOfTickets));
		
		try {
		int bookingId=dao.insertPassengerInfo(p);
		int totalprice=dao.totalPrice(bookingId);
		HttpSession session = request.getSession();
		  session.setAttribute("BookingId", bookingId);
		  HttpSession session1 = request.getSession();
		  session1.setAttribute("totalPrice", totalprice);
		RequestDispatcher dispatcher = request.getRequestDispatcher("paymentChoice.jsp");
		dispatcher.forward(request, response);

		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}


}
}
