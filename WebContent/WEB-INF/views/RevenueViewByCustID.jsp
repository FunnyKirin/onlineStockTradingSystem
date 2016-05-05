<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Revenue by Customer Name</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link href="css/mainClient.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/WEB-INF/views/Header.jsp"/>
	<h3>Revenue by Customer Name</h3>
	<p style="color: red;">${errorString }</p>
	<form action="doRevenueByCustID">
		Input: <select name="cust_id">
			<c:forEach items="${acc}" var="s">
				<option value="${s.SSN}">${s.SSN}</option>
			</c:forEach>
		</select>
		<input type="submit" value="Submit" />
	</form>
	<br />
	<br />
	<table border="1" cellpadding="5" cellspacing="1">
		<tr>
			<th>Client ID</th>
			<th>Email</th>
			<th>Rating</th>
			<th>Credit Card Number</th>
			<th>Revenue</th>
		</tr>
		<tr>
			<td>${cust.SSN}</td>
			<td>${cust.email}</td>
			<td>${cust.rating}</td>
			<td>${cust.creditCardNum}</td>
			<td>${cust.revenue}</td>
		</tr>
	</table>
	<br />
	<a href="managerMain">Back</a>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>