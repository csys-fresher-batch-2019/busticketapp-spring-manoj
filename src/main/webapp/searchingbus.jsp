<%@page import="java.util.List"%>
<%@page import="com.chainsys.ebus.dao.impl.BusDAOImpl"%>
<%@page import="com.chainsys.ebus.dao.BusDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ebus</title>
</head>
<style>
div.head {
	background-color: rgb(127, 140, 141);
}

.name {
	color: white;
}
</style>
<br>
<div class="head">
	<br>
	<center>
		<h1 class="name">eBus</h1>
	</center>
	<br>
</div>
<body>
	<%
	List<String> fromLocation =(List<String>)request.getAttribute("fromLocation");
	List<String> toLocation =(List<String>)request.getAttribute("toLocation");
	%>

	<form action="searchingbuses">
		<center>
			<h1>Search buses here....</h1>

			</br>

			<h2>
				<center>
					Select From Location: <input name=from_location
						list="from_location_list" required>
					<datalist id="from_location_list">
						<%
							for (String a : fromLocation) {
						%>
						<option value="<%=a%>"><%=a%></option>
						<%
							}
						%>
					</datalist>
				</center>
			</h2>

			<h2>
				<center>
					Select To Location: <input name=to_location list="To_location_list"
						required>
					<datalist id="To_location_list">
						<%
							for (String a : toLocation) {
						%>
						<option value="<%=a%>"><%=a%></option>
						<%
							}
						%>
					</datalist>
				</center>
			</h2>

			<h2>
				<center>
					Enter Journey Date: <input type="date" name="journey_date"
						min="2020-02-03" max="2020-04-30" required>
				</center>
			</h2>
			</br>
			<center>
				<button>Search</button>
			</center>
	</form>
</body>
</html>