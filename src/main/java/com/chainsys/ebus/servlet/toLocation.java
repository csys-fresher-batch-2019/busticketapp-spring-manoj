package com.chainsys.ebus.servlet;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.ebus.dao.BusDetailsDAO;
import com.chainsys.ebus.dao.impl.BusDetailsDAOImpl;
import com.chainsys.ebus.service.UserService;

@WebServlet("/toLocation")
public class toLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//busDetailsDAO dao= new busDetailsDAOImpl();
		UserService service=new UserService();
		 try {
			List<String> toLocation=service.getToLocation();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
