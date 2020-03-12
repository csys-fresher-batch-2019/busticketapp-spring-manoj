package com.chainsys.ebus.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.ebus.service.UserService;

@WebServlet("/fromLocation")
public class fromLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// busDetailsDAO dao= new busDetailsDAOImpl();
		UserService service = new UserService();
		try {
			List<String> fromLocation = service.getFromLocation();
			List<String> toLocation = service.getToLocation();
			request.setAttribute("fromLocation", fromLocation);
			request.setAttribute("toLocation", toLocation);

			RequestDispatcher dispatcher = request.getRequestDispatcher("searchingbus.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
