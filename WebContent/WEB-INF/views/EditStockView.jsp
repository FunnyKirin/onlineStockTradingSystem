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
	<h3>${stock.company}</h3>
	<c:if test="${not empty stock}">
		<form method="POST" action="doEditStock">
			<input type="hidden" name="symbol" value="${stock.symbol}" />
			<table border="0">
				<tr>
					<td>Price/Share</td>
					<td><input type="text" name="pps" value="${stock.pps}" /></td>
					<td colspan="2"><input type="submit" value="Submit" /> <a
						href="${pageContext.request.contextPath}/managerMain">Cancel</a></td>
				</tr>
			</table>
		</form>
	</c:if>
</body>
</html>