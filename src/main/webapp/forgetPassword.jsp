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
<center><form action="forgetPasswordServlet">
<br>
<br>
<h2><pre>Enter UserId:<input type="number" name="userid" placeholder="number"/></pre></h2>
<h2><pre>Enter email ID:<input type="text" name="mailid"required/></pre></h2>
<center><button>SUBMIT</button></center>
<%
String result = (String) request.getParameter("res");
if (result != null) {
out.println("<center><font color=red>" + result + "</font></center>");
}
%>
</body>
</html>