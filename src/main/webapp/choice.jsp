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
int userid=(Integer)session.getAttribute("Logged_in_userid");
%>
<br>
<br>
<br>
<br>
<br>
<br>

<center><a href="searchingbus.jsp"><button><h1>Search Bus</h1></button></a></center> 
<center><h1>OR</h1></center>
<center><a href="MyTickets.jsp?UserId=<%=userid%>"><h1><button><h1>My Tickets</h1></button></a></center> 
<br>

</body>
</html>