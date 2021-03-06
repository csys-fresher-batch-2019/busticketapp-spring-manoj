package com.chainsys.ebus.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.ebus.service.UserService;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		UserService service = new UserService();
		String userid = request.getParameter("userid");
		int userId = Integer.parseInt(userid);
		boolean result = false;

		String password = request.getParameter("password");

		try {
			result = service.validateLogin(userId, password);

			if (result == true) {

				HttpSession session = request.getSession();
				session.setAttribute("Logged_in_userid", userId);

				RequestDispatcher dispatcher = request.getRequestDispatcher("choice.jsp");
				dispatcher.forward(request, response);
			} else {
				String result1 = "Invalid UserId / Password";
				response.sendRedirect("login.jsp?res=" + result1);

			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

}
