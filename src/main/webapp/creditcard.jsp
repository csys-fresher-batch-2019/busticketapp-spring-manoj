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
<body>
<%
int bookingid=(Integer)session.getAttribute("BookingId");
int totalPrice = (Integer)session.getAttribute("totalPrice");
%>
<center>
<br>
<br>
<h2>Enter DebitCard number : <input type = "number" name = "cardnumber" placeholder = "Enter card no" required autofocus/></h2>
<h2>Enter cvv :<input type = "number" name = "cvv"
placeholder = "Enter cvv" required autofocus/> </h2>
<h2>Enter Expiry Month/year:<input type = "text" name = "expiry"
placeholder = "Enter expiry" required autofocus/></h2>

<h2>Amount to be paid:<input type = "number" name ="totalprice"value ="<%=totalPrice %>"readonly /> </h2>
<center><a href="afterPaymentCard?bookingId=<%=bookingid%>"><button><h1>To Pay</h1></button></center>

</center>x
</body>
</html>


