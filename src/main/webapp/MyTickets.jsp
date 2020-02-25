<%@page import="java.util.ArrayList"%>
<%@page import="PassengerInfo.passengerInfoDAOImpl"%>
<%@page import="PassengerInfo.passengerInfoDAO"%>
<%@page import="PassengerInfo.passengerInfo"%>
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
		String userid = request.getParameter("UserId");
		int Userid = Integer.parseInt(userid);
		passengerInfoDAO dao = new passengerInfoDAOImpl();
		ArrayList<passengerInfo> info = dao.MyBookings(Userid);
	%>
	<br>
	<br>
	<br>
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
			
					<%for (passengerInfo f : info) {
			%>
			<tr>
				<td><%=i++%></td>
				<td><%=f.getBookingId()%></td>
		
				<td><%=f.getBusId()%></td>
				<td><%=f.getPassengerName()%></td>
				<td><%=f.getAge()%></td>
				<td><%=f.getGender()%></td>
				<td><%=f.getMobileNumber()%></td>
				<td><%=f.getNoOfTickets()%></td>
			</tr>
			<%
				}
				}else {
				
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