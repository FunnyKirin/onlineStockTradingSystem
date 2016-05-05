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
	<h3>Give Suggestion</h3>
	<form action="doReportByMonth">
		<select name="month">
			<option value="1">January</option>
			<option value="2">February</option>
			<option value="3">March</option>
			<option value="4">April</option>
			<option value="5">May</option>
			<option value="6">June</option>
			<option value="7">July</option>
			<option value="8">August</option>
			<option value="9">September</option>
			<option value="10">October</option>
			<option value="11">November</option>
			<option value="12">December</option>
		</select>
		<input type="submit" value="Submit" />
	</form>
	<br />
	<br />
	<p style="color: red;">${errorString}</p>

	<c:if test="${not empty trades}">
		<table border="1" cellpadding="5" cellspacing="1">
			<tr>
				<th>Stock Symbol</th>
				<th>Transaction ID</th>
				<th>Account ID</th>
				<th>Broker ID</th>
				<th>Order ID</th>
			</tr>

			<c:forEach items="${trades}" var="s">
				<tr>
					<td>${s.stock.symbol}</td>
					<td>${s.transaction.id}</td>
					<td>${s.account.id}</td>
					<td>${s.employee.id}</td>
					<td>${s.order.id}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>