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
int userid=(Integer)session.getAttribute("userid");
%>
<center><form action="resetPasswordServlet">
<h2><center>User Id:<input type="number" name="userid" value="<%=userid %>"readonly/></center></h2>
<h2><pre>Enter password:<input type="password" name="password"  pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Atleat 8 characters with atleast 1 uppercase and atleast 1 lowercase letters and atleast 1 number"required /></pre></h2>
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