<%@page import="java.util.List"%>
<%@page import="BusDetails.busDetailsDAOImpl"%>
<%@page import="BusDetails.busDetailsDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
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

<form  action="ListOfBuses.jsp">
<center><h1>Search buses here....</h1>

</br>
<%
busDetailsDAO dao= new busDetailsDAOImpl();
 List<String> fromLocation=dao.getFromLocation();
 List<String> toLocation=dao.getToLocation();
 
%>
<h2><center>Select From Location:
<input name=from_location list="from_location_list" required>
<datalist id="from_location_list">
<%
for(String a:fromLocation){
%>
<option value="<%=a %>"><%=a %></option>
<%} %>
</datalist></center></h2>

<h2><center>Select To Location:
<input name=to_location list="To_location_list" required>
<datalist id="To_location_list">
<%
for(String a:toLocation){
%>
<option value="<%=a %>"><%=a %></option>
<%} %>
</datalist></center></h2>

<h2><center>Enter Journey Date:
<input type="date" name="journey_date" min="2020-02-03" max="2020-04-30" required></center></h2>
</br>
<center><button>Search</button></center>


</form>
</body>
</html>