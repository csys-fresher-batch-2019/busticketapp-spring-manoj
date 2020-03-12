package com.chainsys.ebus.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.ebus.model.FindBus;
import com.chainsys.ebus.service.UserService;

@WebServlet("/searchingbuses")
public class searchingbuses extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserService service = new UserService();
		String fromLocation = request.getParameter("from_location");
		String toLocation = request.getParameter("to_location");
		String journeyDate = request.getParameter("journey_date");
		try {
			List<FindBus> list = service.searchBus(fromLocation, toLocation, journeyDate);
			System.out.println(list);
			request.setAttribute("list", list);

			RequestDispatcher dispatcher = request.getRequestDispatcher("ListOfBuses.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
