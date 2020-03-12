package com.chainsys.ebus.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.ebus.model.PassengerInfo;
import com.chainsys.ebus.service.UserService;

@WebServlet("/passengerservlet")
public class passengerservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// passengerInfoDAO dao=new passengerInfoDAOImpl();
		UserService service = new UserService();
		PassengerInfo p = new PassengerInfo();
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
			int bookingId = service.insertPassengerInfo(p);
			System.out.println(bookingId);
			int totalprice = service.totalPrice(bookingId);
			System.out.println(totalprice);
			HttpSession session = request.getSession();
			session.setAttribute("BookingId", bookingId);
			HttpSession session1 = request.getSession();
			session1.setAttribute("totalPrice", totalprice);
			System.out.println("hello2");
			RequestDispatcher dispatcher = request.getRequestDispatcher("paymentChoice.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
