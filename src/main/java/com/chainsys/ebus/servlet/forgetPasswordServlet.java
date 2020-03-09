package com.chainsys.ebus.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.ebus.dao.userAccountDetailsDAO;
import com.chainsys.ebus.dao.impl.userAccountDetailsDAOImpl;
import com.chainsys.ebus.service.userService;


@WebServlet("/forgetPasswordServlet")
public class forgetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// userAccountDetailsDAO dao =new userAccountDetailsDAOImpl();
		userService service=new userService();
		String userid=request.getParameter("userid");
	      int userId=Integer.parseInt(userid);
	   	String mailId = request.getParameter("mailid");
	    boolean result=false;
	   try { 
	    result= service.checkEmailId2(mailId,userId);
	    if(result==true)
	    {
	    	 RequestDispatcher dispatcher = request.getRequestDispatcher("resetpassword.jsp");
	      	dispatcher.forward(request, response);
	      	HttpSession session = request.getSession();
	        session.setAttribute("user_id", userId);
	           	
	    }
	    else
	    {
	    	
	     		String result1 = "Invalid UserId / emailId";
	     		response.sendRedirect("forgetPassword.jsp?res=" + result1);
	     		
	    }
	} catch (Exception e) {
     	// TODO Auto-generated catch block
     	e.printStackTrace();
     	}

	}
	}

	
	

