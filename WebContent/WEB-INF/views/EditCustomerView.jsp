<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Info</title>
</head>
<body>
	<h3>Edit Customer</h3>
	<c:if test="${not empty cust}">
		<form method="POST" action="doEditCustomer">
			<input type="hidden" name="username" value="${cust.username}" />
			<table border="0">
				<tr>
					<td>SSN</td>
					<td style="color: red;">${cust.SSN}</td>
				</tr>
				<tr>
					<td>Username</td>
					<td><input type="text" name="username"
						value="${cust.username}" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password"
						value="${cust.password}" /></td>
				</tr>
				<tr>
					<td>First Name</td>
					<td><input type="text" name="firstname"
						value="${cust.firstname}" /></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><input type="text" name="lastname"
						value="${cust.lastname}" /></td>
				</tr>
				<tr>
					<td>Telephone</td>
					<td><input type="text" name="telephone"
						value="${cust.telephone}" /></td>
				</tr>
				<tr>
					<td>Address</td>
					<td><input type="text" name="address" value="${cust.address}" /></td>
				</tr>
				<tr>
					<td>City</td>
					<td><input type="text" name="city"
						value="${cust.location.city}" /></td>
				</tr>
				<tr>
					<td>State</td>
					<td><input type="text" name="state"
						value="${cust.location.state}" /></td>
				</tr>
				<tr>
					<td>Zip Code</td>
					<td><input type="text" name="zipcode"
						value="${cust.location.zipcode}" /></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="text" name="email" value="${cust.email}" /></td>
				</tr>
				<tr>
					<td>Rating</td>
					<td><input type="text" name="rating" value="${cust.rating}" /></td>
				</tr>
				<tr>
					<td>Credit Card</td>
					<td><input type="text" name="creditcard"
						value="${cust.creditCardNum}" /></td>
				</tr>
				<tr>
					<td>Date Opened</td>
					<td style="color: blue;">${cust.dateOpened}</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Submit" /> <a
						href="${pageContext.request.contextPath}/customerList">Cancel</a>
					</td>
				</tr>
			</table>
		</form>
	</c:if>
</body>
</html>