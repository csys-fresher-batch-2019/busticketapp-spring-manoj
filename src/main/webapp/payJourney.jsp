<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<body>
<br>
<br>
<%
int bookingid=(Integer)session.getAttribute("BookingId");
%>
<center><h1>Your ticket is almost confirmed....</h1></center>
<br>
<br>

<center><h2>Amount to be Paid is</h2></center>
<%
int totalPrice=(Integer)session.getAttribute("totalPrice");
%>
<h1><center><%=totalPrice %></center></h1>
<br>
<br>
<center><button><a href="afterPayment?bookingId=<%=bookingid%>"><h1>Click here to confirm</h1></button></center>

</body>
</html>