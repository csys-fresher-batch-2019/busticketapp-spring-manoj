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
import com.chainsys.ebus.dao.availableSeatsDAO;
import com.chainsys.ebus.dao.passengerInfoDAO;
import com.chainsys.ebus.dao.impl.FindBusDAOImpl;
import com.chainsys.ebus.dao.impl.availableSeatsDAOImpl;
import com.chainsys.ebus.dao.impl.passengerInfoDAOImpl;
import com.chainsys.ebus.model.FindBus;
import com.chainsys.ebus.model.passengerInfo;
import com.chainsys.ebus.service.userService;

@WebServlet("/availableSeats")
public class availableSeats extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//availableSeatsDAO dao=new availableSeatsDAOImpl();
		userService service=new userService();
		 String bid=request.getParameter("busId");
	      int busid=Integer.parseInt(bid);
			
		try {
			int availableseats=service.seatavail(busid);
			
			request.setAttribute("seats",availableseats);
		
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("viewavailableseats.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
