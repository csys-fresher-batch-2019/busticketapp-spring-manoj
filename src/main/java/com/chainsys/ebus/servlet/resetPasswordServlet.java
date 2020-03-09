package com.chainsys.ebus.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.ebus.dao.userAccountDetailsDAO;
import com.chainsys.ebus.dao.impl.userAccountDetailsDAOImpl;
import com.chainsys.ebus.service.userService;


@WebServlet("/resetPasswordServlet")
public class resetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//userAccountDetailsDAO dao =new userAccountDetailsDAOImpl();
		userService service=new userService();
		String userid=request.getParameter("userid");
	      int userId=Integer.parseInt(userid);
	   	String password = request.getParameter("password");
	   	String password2 = request.getParameter("password2");
	    boolean result=false;
	    try {
	    if(password.equals(password2))
	    {
	    	
	    	result=service.forgetPassword(userId, password2);
	    	if(result==true) {
	    		   
	         	
	      RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
	         	dispatcher.forward(request, response);
	         	}
	         	else {
	         		String result1 = "Invalid UserId";
	         		response.sendRedirect("resetpassword.jsp?res=" + result1);
	         		
	    }
	
	    	
	    }
	    else
	    {
	    	String result2 = "Password doesnot match";
     		response.sendRedirect("resetpassword.jsp?res1=" + result2);
     		
	    }
}
	    
	    catch (Exception e) {
	     	// TODO Auto-generated catch block
	     	e.printStackTrace();
	     	}
	    
	}
}


