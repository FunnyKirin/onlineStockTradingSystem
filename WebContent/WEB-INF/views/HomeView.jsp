<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Netrade</title>


<link href="css/index.css" rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Nunito'
	rel='stylesheet' type='text/css'>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

</head>

<body>
	<div class="main">
		<img src="img/logo.png" align="middle" class="logo">
	</div>
	<div>
	<button onclick="location.href = '${pageContext.request.contextPath}/clientLogin';" class="button">Client</button>
	<button onclick="location.href = '${pageContext.request.contextPath}/employeeLogin';" class="button">Employee</button>
	</div>
</body>
</html>