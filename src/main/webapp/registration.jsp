<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<body>
<br>
<div class="head"><br><center><h1 class ="name">eBus</h1></center><br></div>
<body>
<br>

<center><h1>Enter your details....</h1>
<br>
<form action="registrationservlet">
     <h2><pre>Enter User Name:<input type="text" name="username" pattern="[A-Za-z]{3,}"required/></pre></h2>
     <h2><pre>Set Password:<input type="password" name="password" required/></pre></h2>
     <h2><pre>Enter Gender:<input type="radio" name="Gender" value="M" required>male <input type="radio" name="Gender" value="F" >female <input type="radio" name="Gender" value="Others" >others </pre></h2>
     <h2><pre>Enter DOB:<input type="date" name="DOB" placeholder="yyyy-mm-dd" required/></pre></h2>
     <h2><pre>Enter Contact Number:<input type="tel" name="contactnumber" required/></pre></h2>
     <h2><pre> Enter email ID:<input type="text" name="mailid"required/></pre></h2>
<br>
<center><button type="SUBMIT"><h2>REGISTER</h2></button></center>

</body>
</html>