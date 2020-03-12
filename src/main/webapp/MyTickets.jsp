<%@page import="java.util.ArrayList"%>
<%@page import="com.chainsys.ebus.dao.impl.PassengerInfoDAOImpl"%>
<%@page import="com.chainsys.ebus.dao.PassengerInfoDAO"%>
<%@page import="com.chainsys.ebus.model.PassengerInfo"%>
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
		ArrayList<PassengerInfo> info = (ArrayList) request.getAttribute("info");
	%>
	<br>
	<br>
	<br>
	<br>
	
	<%
				int i = 1;
				if (info.size()>0) {%>
				<center>
			<h1>My Bookings</h1>
		</center>
	<center>
		<table border="2">
			<thead>
				<tr>
					<th>S.No</th>
					<th>BookingID</th>
					
					<th>BusId</th>
					<th>Passenger Name</th>
					<th>Age</th>
					<th>Gender
					<th>Mobile Number</th>
					<th>No of Tickets</th>
					
				</tr>
			</thead>
			</tbody>
			<tbody>
				<c:set var="info" value="<%=info%>" ></c:set>
				<c:forEach items="${info}" var="l" varStatus="i">				
				<tr>
					<td>${i.index+1}</td>
					<td>${l.bookingId}</td>
					<td>${l.busId}</td>
					<td>${l.passengerName}</td>
					<td>${l.age}</td>
					<td>${l.gender}</td>
					<td>${l.mobileNumber}</td>
					<td>${l.noOfTickets}</td>
					
				</tr>
				</c:forEach>
				</tbody>
			
				
			<%	}else {
				
			%>
			
		
			<center>
				<h1>You have not made any Bookings..</h1>
				<br>
				<br>
				<br>
				
			<%
				}
			%>
		</table>
		<br>
		<center><a href="choice.jsp"><button>Back</button></a></center> 
</body>
</html>