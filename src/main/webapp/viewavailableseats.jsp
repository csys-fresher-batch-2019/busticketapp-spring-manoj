<%@page import="AvailableSeats.availableSeatsDAOImpl"%>
<%@page import="AvailableSeats.availableSeatsDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
<br>
<br>
<br>
<br>
<center><h1>Available Seats are</h1></center>
<%
String Busid=request.getParameter("busId");
int busid=Integer.parseInt(Busid);
availableSeatsDAO dao=new availableSeatsDAOImpl();
int availableseats=dao.seatavail(busid);%>
<center><h1><%out.println(availableseats);%></h1></center>
<center><button><a href="passengerinfo.jsp?busId=<%=busid%>"><h1>Click here to Book</h1></button></center>

</body>
</html>