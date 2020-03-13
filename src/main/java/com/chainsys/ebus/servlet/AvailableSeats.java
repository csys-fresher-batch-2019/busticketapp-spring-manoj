package com.chainsys.ebus.servlet;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.ebus.service.UserService;

@WebServlet("/availableSeats")
public class AvailableSeats extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
		UserService service=new UserService();
		 String bid=request.getParameter("busId");
	      int busid=Integer.parseInt(bid);
			
		try {
			int availableseats=service.seatsAvailble(busid);
			
			request.setAttribute("seats",availableseats);
		
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("viewavailableseats.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
