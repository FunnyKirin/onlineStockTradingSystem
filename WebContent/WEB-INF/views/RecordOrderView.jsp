<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Netrade - Record Order</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link href="css/mainClient.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/views/Header.jsp"/>
	<h3>Record Order</h3>
	<h3 style="color: red;">${errorString}</h3>
	<form action="${pageContext.request.contextPath}/doRecordOrder">
		<table>
			<tr>
				<th>Stock Symbol: <select name="symbol">
						<c:forEach items="${stocks}" var="s">
							<option value="${s.symbol}">${s.company}</option>
						</c:forEach>
				</select>
				</th>
			</tr>
			<tr>
				<th>Buy/Sell: <select name="buy_sell">
						<option value="buy">Buy</option>
						<option value="sell">Sell</option>
				</select>
				</th>
			</tr>
			<tr>
				<th>Order Type: <select name="order_type">
						<option value="Market">Market</option>
						<option value="MarketOnClose">Market on Close</option>
						<option value="TrailingStop">Trailing Stop</option>
						<option value="HiddenStop">Hidden Stop</option>
				</select>
				</th>
			</tr>
			<tr>
				<th>Number of Shares: <input type="text" name="num_shares" />
				</th>
			</tr>
			<tr>
				<th>Fee: <input type="text" name="fee" />
				</th>
			</tr>
			<tr>
				<th>Account ID: <input type="text" name="account_id" />
				</th>
			</tr>
			<tr>
				<th><input type="submit" value="Submit" /></th>
			</tr>
			<tr>
				<th><a href="employeeMain">Cancel</a></th>
			</tr>
		</table>
	</form>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>