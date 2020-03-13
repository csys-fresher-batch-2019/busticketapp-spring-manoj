package com.chainsys.ebus.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.ebus.service.UserService;

@WebServlet("/resetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		UserService service = new UserService();
		String userid = request.getParameter("userid");
		int userId = Integer.parseInt(userid);
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		boolean result = false;
		try {
			if (password.equals(password2)) {

				result = service.forgetPassword(userId, password2);
				if (result == true) {

					RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
					dispatcher.forward(request, response);
				} else {
					String result1 = "Invalid UserId";
					response.sendRedirect("resetpassword.jsp?res=" + result1);

				}

			} else {
				String result2 = "Password doesnot match";
				response.sendRedirect("resetpassword.jsp?res1=" + result2);

			}
		}

		catch (Exception e) {

			e.printStackTrace();
		}

	}
}
