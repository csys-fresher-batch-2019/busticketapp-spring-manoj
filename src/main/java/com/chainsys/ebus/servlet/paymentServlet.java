package com.chainsys.ebus.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/paymentServlet")
public class paymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long cardnum = Long.parseLong(request.getParameter("cardnumber"));
		int cvv = Integer.parseInt(request.getParameter("cvv"));
		String expiry = request.getParameter("expiry");
		LocalDate exp = LocalDate.parse(expiry);
		int totalAmount = Integer.parseInt(request.getParameter("totalAmount"));

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
