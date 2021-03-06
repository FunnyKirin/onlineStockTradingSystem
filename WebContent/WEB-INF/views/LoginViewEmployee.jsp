<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Netrade</title>
<link href="css/login.css" rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Nunito' rel='stylesheet' type='text/css'>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript" src="js/login.js"></script>
</head>
<body>
    <div class="cont">
  <div class="demo">
    <div class="login">
      <div class="login__logo"><a href="${pageContext.request.contextPath}/home"><img src="img/logo.png"></a></div>
      <div class="login__form">
      	<form action="${pageContext.request.contextPath}/doEmployeeLogin">
      	<p style="color: red;">${errorString}</p>
        <div class="login__row">
          <svg class="login__icon name svg-icon" viewBox="0 0 20 20">
            <path d="M0,20 a10,8 0 0,1 20,0z M10,0 a4,4 0 0,1 0,8 a4,4 0 0,1 0,-8" />
          </svg>
          <input type="text" name="username" value="${employee.username}" class="login__input name" placeholder="Employee" />
        </div>
        <div class="login__row">
          <svg class="login__icon pass svg-icon" viewBox="0 0 20 20">
            <path d="M0,20 20,20 20,8 0,8z M10,13 10,16z M4,8 a6,8 0 0,1 12,0" />
          </svg>
          <input type="password" name="password" value="${employee.password}" class="login__input pass" placeholder="Password"/>
        </div>
        <!-- <button type="submit" class="login__submit">Sign in</button> -->
        <input type="submit" class="login__submit" value="Sign in" />
        </form>
      </div>
    </div>
  </div>
</div>
</body>
</html>