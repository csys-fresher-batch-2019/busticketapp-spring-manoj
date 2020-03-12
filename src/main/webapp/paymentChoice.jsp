<%@page import="com.chainsys.ebus.dao.impl.PassengerInfoDAOImpl"%>
<%@page import="com.chainsys.ebus.dao.PassengerInfoDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ebus</title>
</head>
<style>
div.head{
background-color:rgb(127, 140, 141);
}
.name{
color:white;
}
</style>
<br>
<div class="head"><br><center><h1 class ="name">eBus</h1></center><br></div>
<body >
<%
int bookingid=(Integer)session.getAttribute("BookingId");
%>



<br>
<br>
<br>
<center><h1>Amount to be Paid is</h1></center>
<%
int totalPrice=(Integer)session.getAttribute("totalPrice");
%>
<h1><center><%=totalPrice %></center></h1>
<br>
<br>
<center><a href="creditcard.jsp"><button><h1>Debit Card</h1></button></a></center> 
<center><h1>OR</h1></center>

<center><a href="payJourney.jsp?"><h1><button><h1>Pay while Journey</h1></button></a></center> 
<br>
</body>
</html>