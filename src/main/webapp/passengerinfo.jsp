<<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
<br>
<body>

<%
int userid=(Integer)session.getAttribute("Logged_in_userid");
%>


<%
String busid=request.getParameter("busId");
%>
<h1><center>Enter passengers details...</center></h1>
<br>
<form action="passengerservlet">
<h2><center>Bus Id:<input type="number" name="busid" value="<%=busid %>"readonly/></center></h2>
<h2><center>User Id:<input type="number" name="userid" value="<%=userid %>"readonly/></center></h2>
<h2><center>Enter Passenger's Name:<input type="text" name="passengerName" placeholder="passengerName"/></center></h2>
<h2><center>Enter age:<input type="number" name="Age" placeholder="age"/></center></h2>
<h2><center>Enter Gender:<input type="radio" name="Gender" value="M" required>male
<input type="radio" name="Gender" value="F" >female
<input type="radio" name="Gender" value="Others" >others </center></h2>
<h2><center>Enter Mobile Number:<input type="number" name="mobileNumber" placeholder="mobileNo"/></center></h2>
<h2><center>Enter No of Tickets:<input type="number" name="noOfTickets" placeholder="noTickets"/></center></h2>
<br>
<center><button type="SUBMIT"><h2>BOOK</h2></button></center>
</form>
</body>
</html>