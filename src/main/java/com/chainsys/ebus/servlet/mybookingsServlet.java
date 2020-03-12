package com.chainsys.ebus.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.ebus.dao.FindBusDAO;
import com.chainsys.ebus.dao.PassengerInfoDAO;
import com.chainsys.ebus.dao.impl.FindBusDAOImpl;
import com.chainsys.ebus.dao.impl.PassengerInfoDAOImpl;
import com.chainsys.ebus.model.FindBus;
import com.chainsys.ebus.model.PassengerInfo;
import com.chainsys.ebus.service.UserService;

@WebServlet("/mybookingsServlet")
public class mybookingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//passengerInfoDAO dao = new passengerInfoDAOImpl();
		UserService service=new UserService();
		 String userid=request.getParameter("UserId");
	      int userId=Integer.parseInt(userid);
		
		try {
			ArrayList<PassengerInfo> info = service.myBookings(userId);
			
			request.setAttribute("info",info);
		
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("MyTickets.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
