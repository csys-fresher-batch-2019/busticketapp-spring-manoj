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
<body>
<center><form action="resetPasswordServlet">
<h2><pre>Enter UserId:<input type="number" name="userid" placeholder="number" required/></pre></h2>
<h2><pre>Enter password:<input type="password" name="password" required /></pre></h2>
<h2><pre>Enter password Again:<input type="password" name="password2" required /></pre></h2>
<center><button>SUBMIT</button></center>
<%
String result1 = (String) request.getParameter("res");
if (result1 != null) {
out.println("<center><font color=red>" + result1 + "</font></center>");
}

String result2 = (String) request.getParameter("res1");
if (result2 != null) {
out.println("<center><font color=red>" + result2 + "</font></center>");
}
%>

</body>
</html>