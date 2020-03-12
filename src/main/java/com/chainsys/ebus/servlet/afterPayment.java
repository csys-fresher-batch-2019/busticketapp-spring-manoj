package com.chainsys.ebus.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.ebus.service.UserService;

@WebServlet("/afterPayment")
public class afterPayment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserService service = new UserService();

		String Booking = request.getParameter("bookingId");
		int bookingId = Integer.parseInt(Booking);

		System.out.println(bookingId);

		try {
			service.cashPayment(bookingId);
			response.sendRedirect("lastpage.jsp");

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
