<%@page import="SearchBus.FindBus"%>
<%@page import="java.util.ArrayList"%>
<%@page import="SearchBus.FindBusDAOImpl"%>
<%@page import="SearchBus.FindBusDAO"%>
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
<body style">
	<br>
	
	<br>



	<%
		String fromLocation = request.getParameter("from_location");
		String toLocation = request.getParameter("to_location");
		String journeyDate = request.getParameter("journey_date");
		FindBusDAO dao = new FindBusDAOImpl();
		ArrayList<FindBus> list = dao.searchbus(fromLocation, toLocation, journeyDate);
	%>

	<%
		int i = 1;
		if (list.size() > 0) {%>
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
					<th>Available seats<th>
				</tr>
			</thead>
			</tbody>
			<tr>
			<% for (FindBus f : list) {
	%>
	
	
				<td><%=i++%>
					</td>
				<td><%=f.getBusName()%></td>
				<td><%=f.getBusId()%></td>
				<td><%=f.getTicketPrice()%></td>
				<td><%=f.getTravellingTime()%></td>
				<td><a href="viewavailableseats.jsp?busId=<%=f.getBusId()%>">view
						seats</a>
			</tr>
			<%
				}
				} else {
			%>
			<center>
				<h1>No Buses are Available...try another date</h1>
				<br>
				<br>
				<br>
				<center><a href="searchingbus.jsp"><button>Click here </button></a></center>
			</center>
			<%
				}
			%>
			</tbody>
		</table>
	</center>
</body>

</html>

