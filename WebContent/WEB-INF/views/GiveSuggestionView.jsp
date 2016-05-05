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
	<form method="POST" action="doGiveSuggestion">
			<input type="text" name="client_id" />
			<input type="submit" value="Submit" />
	</form>
	<c:if test="${not empty suggestions}">
		
	</c:if>
	
</body>
</html>