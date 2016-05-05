<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Revenue by Stock Type</title>
</head>
<body>
	<h3>Revenue by Stock Type</h3>
	<form action="doRevenueByStockType">
		Input: <select name="type">
			<c:forEach items="${stocks}" var="s">
				<option value="${s.type}">${s.type}</option>
			</c:forEach>
		</select>
		<input type="submit" value="Submit" />
	</form>
	<br />
	<br />
	<table border="1" cellpadding="5" cellspacing="1">
		<tr>
			<th>Type</th>
			<th>Symbol</th>
			<th>Company</th>
			<th>Price/Share</th>
			<th>Revenue</th>
		</tr>
		<tr>
			<td>${cust.type}</td>
			<td>${cust.symbol}</td>
			<td>${cust.company}</td>
			<td>${cust.pps}</td>
			<td>${cust.revenue}</td>
		</tr>
	</table>
	<br />
	<a href="managerMain">Back</a>
</body>
</html>