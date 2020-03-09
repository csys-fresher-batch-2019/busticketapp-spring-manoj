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
import com.chainsys.ebus.dao.impl.FindBusDAOImpl;
import com.chainsys.ebus.model.FindBus;
import com.chainsys.ebus.service.userService;

@WebServlet("/searchingbuses")
public class searchingbuses extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//FindBusDAO dao1 = new FindBusDAOImpl();
		userService service=new userService();
		String fromLocation = request.getParameter("from_location");
		String toLocation = request.getParameter("to_location");
		String journeyDate = request.getParameter("journey_date");
		// FindBusDAO dao1 = new FindBusDAOImpl();
		try {
			ArrayList<FindBus> list = service.searchbus(fromLocation, toLocation, journeyDate);
			System.out.println(list);
			request.setAttribute("list",list);
		
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("ListOfBuses.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
