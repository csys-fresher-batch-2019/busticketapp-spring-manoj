package com.chainsys.ebus.servlet;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.ebus.model.UserAccount;
import com.chainsys.ebus.service.UserService;

@WebServlet("/registrationservlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserService service = new UserService();
		UserAccount p = new UserAccount();
		p.setUserName(request.getParameter("username"));
		p.setPassword(request.getParameter("password"));
		p.setGender(request.getParameter("Gender"));
		p.setDob(request.getParameter("DOB"));
		p.setContactNumber(Long.parseLong(request.getParameter("contactnumber")));
		p.setEmailId(request.getParameter("mailid"));

		boolean result = false;

		try {
			result = service.validateEmailId(p.getEmailId());
			if (result == true) {

				int userId = service.addUser(p);

				HttpSession session = request.getSession();
				session.setAttribute("userId", userId);

				RequestDispatcher dispatcher = request.getRequestDispatcher("userId.jsp");
				dispatcher.forward(request, response);
			} else {
				String result1 = "Account is already existing using given Email ID... try another Email ID";
				response.sendRedirect("registration.jsp?res=" + result1);

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
