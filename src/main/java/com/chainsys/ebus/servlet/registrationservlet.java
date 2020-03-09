package com.chainsys.ebus.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.ebus.dao.userAccountDetailsDAO;
import com.chainsys.ebus.dao.impl.userAccountDetailsDAOImpl;
import com.chainsys.ebus.model.userAccountDetails;
import com.chainsys.ebus.service.userService;



@WebServlet("/registrationservlet")
public class registrationservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//userAccountDetailsDAO dao=new userAccountDetailsDAOImpl();
		userService service=new userService();
		userAccountDetails p=new userAccountDetails();
		p.setUserName(request.getParameter("username"));
		p.setPassword(request.getParameter("password"));
		p.setGender(request.getParameter("Gender"));
		p.setDob(request.getParameter("DOB"));
        p.setContactNumber(Long.parseLong(request.getParameter("contactnumber")));
		p.setEmailId(request.getParameter("mailid"));
		
		PrintWriter out=response.getWriter();
		boolean result=false;
		
		try {
			result=service.checkEmailId(p.getEmailId());
		if(result==true)	{
		
		int userId=service.addUser(p);
		//out.println(userId);
		HttpSession session = request.getSession();
		  session.setAttribute("userId", userId);
		     	
		
		     	
		RequestDispatcher dispatcher = request.getRequestDispatcher("userId.jsp");
		dispatcher.forward(request, response);
		}
		else {
     		String result1 = "Account is already existing using given Email ID... try another Email ID";
     		response.sendRedirect("registration.jsp?res=" + result1);
     		

     	
     	
     	
     	}
		} catch (Exception e){
		// TODO Auto-generated catch block
		e.printStackTrace();
		}


}
}
