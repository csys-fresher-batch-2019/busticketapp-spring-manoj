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
<%
int userid=(Integer)session.getAttribute("userId");
%>
<br>
<br>
<br>
<center><h1>your UserId is</h1></center>
<center><h1><%out.println(userid);%></h1></center>
<br>
<br>
<center><a href="login.jsp"><button>Login</button></a></center> 
</body>
</html>