<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Info</title>
</head>
<body>
	<h3>Edit Employee</h3>
	<c:if test="${not empty emp}">
		<form method="POST" action="doEditEmployee">
			<input type="hidden" name="SSN" value="${emp.SSN}" />
			<table border="0">
				<tr>
					<td>SSN</td>
					<td style="color: red;">${emp.SSN}</td>
				</tr>
				<tr>
					<td>Username</td>
					<td><input type="text" name="username" value="${emp.username}" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password"
						value="${emp.password}" /></td>
				</tr>
				<tr>
					<td>First Name</td>
					<td><input type="text" name="firstname"
						value="${emp.firstname}" /></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><input type="text" name="lastname" value="${emp.lastname}" /></td>
				</tr>
				<tr>
					<td>Telephone</td>
					<td><input type="text" name="telephone"
						value="${emp.telephone}" /></td>
				</tr>
				<tr>
					<td>Address</td>
					<td><input type="text" name="address" value="${emp.address}" /></td>
				</tr>
				<tr>
					<td>City</td>
					<td><input type="text" name="city"
						value="${emp.location.city}" /></td>
				</tr>
				<tr>
					<td>State</td>
					<td><input type="text" name="state"
						value="${emp.location.state}" /></td>
				</tr>
				<tr>
					<td>Zip Code</td>
					<td><input type="text" name="zipcode"
						value="${emp.location.zipcode}" /></td>
				</tr>
				<tr>
					<td>Hourly Rate</td>
					<td><input type="text" name="hourlyrate"
						value="${emp.hourlyRate}" /></td>
				</tr>
				<tr>
					<td>Is Manager</td>
					<td><select name="ismanager">
							<option value="Y">Y</option>
							<option value="N">N</option>
					</select></td>
				</tr>
				<tr>
					<td>Date Started</td>
					<td style="color: blue;">${emp.dateStarted}</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Submit" /> <a
						href="${pageContext.request.contextPath}/editEmployee">Cancel</a>
					</td>
				</tr>
			</table>
		</form>
	</c:if>
</body>
</html>