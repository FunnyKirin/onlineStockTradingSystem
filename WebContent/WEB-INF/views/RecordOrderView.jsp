<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Netrade - Record Order</title>
</head>
<body>
	<h3>Record Order</h3>
	<h3 style="color: red;">${errorString}</h3>
	<form action="${pageContext.request.contextPath}/doRecordOrder">
		Stock Symbol: <select name="symbol">
			<c:forEach items="${stocks}" var="s">
				<option value="${s.symbol}">${s.company}</option>
			</c:forEach>
		</select> Buy/Sell: <select name="buy_sell">
			<option value="buy">Buy</option>
			<option value="sell">Sell</option>
		</select> Order Type: <select name="order_type">
			<option value="Market">Market</option>
			<option value="MarketOnClose">Market on Close</option>
			<option value="TrailingStop">Trailing Stop</option>
			<option value="HiddenStop">Hidden Stop</option>
		</select> <br />
		Number of Shares: <input type="text" name="num_shares" /><br /> 
		Fee: <input type="text"	name="fee" /> <br />
		Account ID: <input type="text" name="account_id" /><br />
		<input type="submit" value="Submit" />
	</form>

</body>
</html>