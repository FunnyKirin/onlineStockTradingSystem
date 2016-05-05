<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employees</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link href="css/mainClient.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/WEB-INF/views/Header.jsp"/>
	<h3>Employees</h3>
    <p style="color: red;">${errorString}</p>
    
	<table border="1" cellpadding="5" cellspacing="1">
		<tr>
			<th>ID</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Date Started</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>

		<c:forEach items="${empList}" var="emp">
			<tr>
				<td>${emp.SSN}</td>
				<td>${emp.firstname}</td>
				<td>${emp.lastname}</td>
				<td>${emp.dateStarted}</td>
				<td><a href="editEmployee?ssn=${emp.SSN}">Edit</a></td>
				<td><a href="deleteEmployee?ssn=${emp.SSN}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="addEmployee" >Add Employee</a><br />
	<a href="managerMain" >Back</a>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>