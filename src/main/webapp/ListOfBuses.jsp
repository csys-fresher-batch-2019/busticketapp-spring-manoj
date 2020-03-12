<%@page import="com.chainsys.ebus.model.FindBus"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.chainsys.ebus.dao.impl.FindBusDAOImpl"%>
<%@page import="com.chainsys.ebus.dao.FindBusDAO"%>
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
div.head {
	background-color: rgb(127, 140, 141);
}

.name {
	color: white;
}
</style>
<br>
<div class="head">
	<br>
	<center>
		<h1 class="name">eBus</h1>
	</center>
	<br>
</div>
<bodystyle"> <br>

<br>



<%
	List<FindBus> list = (ArrayList) request.getAttribute("list");
%> <%
 	int i = 1;
 	if (!list.isEmpty()) {
 %>
<center>
	<h1>List of Buses</h1>
</center>
<br>
<center>
	<table border="2">
		<thead>
			<tr>
				<th>S.No</th>
				<th>Bus Name</th>
				<th>Bus Id</th>
				<th>Ticket Price</th>
				<th>Travelling time</th>
				<th>Available seats
				<th>
			</tr>
		</thead>
		</tbody>
		<tbody>
				<c:set var="list" value="<%=list%>" ></c:set>
				<c:forEach items="${list}" var="l" varStatus="i">				
				<tr>
					<td>${i.index+1}</td>
					<td>${l.busName}</td>
					<td>${l.busId}</td>
					<td>${l.ticketPrice}</td>
					<td>${l.travellingTime}</td>
					<td><a href="availableSeats?busId=${l.busId}">view
					seats</td></a>
				</tr>
				</c:forEach>
				</tbody>
		
		
   
			<%} else {
		%>
		<center>
			<h1>No Buses are Available...try another date</h1>
			<br> <br> <br>
			<center>
				<a href="fromLocation"><button>Click here</button></a>
			</center>
		</center>
		<%
			}
		%>
		</tbody>
	</table>
</center>
</body>
</html>

