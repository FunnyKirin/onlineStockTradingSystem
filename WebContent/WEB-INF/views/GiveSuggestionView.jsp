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
	<form action="doGiveSuggestion">
		<input type="text" name="client_id" /> <input type="submit"
			value="Submit" />
	</form>
	<br />
	<br />
	<c:if test="${empty stocks}">
	<h3>This customer hasn't placed an order yet</h3>
	</c:if>
	<c:if test="${not empty stocks}">
		<table border="1" cellpadding="5" cellspacing="1">
			<tr>
				<th>Symbol</th>
				<th>Company</th>
				<th>Type</th>
				<th>Price/Share</th>
			</tr>





			<c:forEach items="${stocks}" var="s">
				<tr>
					<td>${s.symbol}</td>
					<td>${s.company}</td>
					<td>${s.type}</td>
					<td>${s.pps}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>