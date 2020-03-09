package com.chainsys.ebus.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.ebus.dao.paymentDAO;
import com.chainsys.ebus.dao.impl.paymentDAOImpl;
import com.chainsys.ebus.service.userService;


@WebServlet("/afterPaymentCard")
public class afterPaymentCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//paymentDAO dao =  new paymentDAOImpl();
		userService service=new userService();
		PrintWriter out=response.getWriter();
		String Booking = request.getParameter("bookingId");
		int bookingId = Integer.parseInt(Booking);
		//out.println("bookingId");
		System.out.println(bookingId);
		try {
		service.paymentSuccess(bookingId);
		response.sendRedirect("lastpage.jsp");

		
	}
		 catch (Exception e) {
		     	// TODO Auto-generated catch block
		     	e.printStackTrace();
		     	}
		    

	
}}
