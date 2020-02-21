<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<body><br>
<%
int bookingid=(Integer)session.getAttribute("BookingId");
%>
<br>
<center><h2>Your BookingId is....</h2></center>
<h1><center><%=bookingid %></center></h1>
<br>
<center><h1>You are Successfully Booked the ticket....</h1></center><br>
<br>
<br>
<center><a href="login.jsp"><button>Logout</button></a></center> 

</body>
</html>