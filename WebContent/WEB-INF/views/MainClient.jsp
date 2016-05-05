<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Netrade</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link href="css/mainClient.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/WEB-INF/views/Header.jsp"/>
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<ul class="nav menu">
			<li><input type="submit" class="submit" id="currentStocks" value="Current Stocks" /></li>
			<li><input type="submit" class="submit" id="trailingHistory" value="Trailing-stop history" /></li>
			<li><input type="submit" class="submit" id="hiddenHistory" value="Hidden-stop history" /></li>
			<li><input type="submit" class="submit" id="stockPriceHis" value="Stock Price History" /></li>
			<li><input type="submit" class="submit" id="orderHistories" value="order histories" /></li>
			<li><input type="submit" class="submit" id="stockByType" value="Stocks and order Info" /></li>
			<li><input type="submit" class="submit" id="searchStock" value="Search Stock" /></li>
			<li><input type="submit" class="submit" id="Best-sellers" value="Best-sellers" /></li>
			<li><input type="submit" class="submit" id="Suggestions" value="Suggestions" /></li>
			<li class="parent">
				<a href="">
					<span data-toggle="collapse" href="#sub-item-1">stocks and order info</span>
				</a>
				<ul class="children collapse" id="sub-item-1">
					<li>
						<a class="" href="#">
							Sub Item 1
						</a>
					</li>
					<li>
						<a class="" href="#">
							Sub Item 2
						</a>
					</li>
					<li>
						<a class="" href="#">
							Sub Item 3
						</a>
					</li>
				</ul>
			</li>
			<li role="presentation" class="divider"></li>
			<li><a href="#" id="help">Help</a></li>
		</ul>

	</div><!--/.sidebar-->

	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row" id="dynamic">
		
			<form role="search" class="search" action="doClientMain" method="post">
				<div class="form-group">
<<<<<<< HEAD
					<input type="text" class="form-control" placeholder="Search Stocks">
					<input type="submit" class="form-control" value="Search by Name" id="searchButton">
					<input type="submit" class="form-control" value="Search by Type" id="searchButton1">
=======
					<input type="text" name="searchText" value=""  class="form-control" placeholder="Search Stocks">
					<input type="submit" name="searchButton" class="form-control" placeholder="Search">
>>>>>>> 6ed5eeef96d671ced3cdb2cc9fe7508d70d829f9
				</div>
			</form>
			<table class = "searchResult">
				<tr><td>Stock Symbol</td><td>Company</td><td>Stock Type</td><td>Price</td></tr>
    			<c:forEach var="stock" items="${searchResult}">
        			<tr>
            			<td>${stock.symbol}</td>
            			<td>${stock.company}</td>
            			<td>${stock.type}</td>
            			<td>${stock.PPS }</td>
        			</tr>
    			</c:forEach>
			
			</table>
			<table class = "orderHistories">
				<tr><td>Stock Symbol</td><td>number of shares</td><td>Price Type</td><td>Order Type</td><td>Date</td></tr>
    			<c:forEach var="order" items="${orders}">
        			<tr onclick="getStopHis(${order})">
            			<td>${order.symbol}</td>
            			<td>${order.numShares}</td>
            			<td>${order.priceType}</td>
            			<td>${order.orderType }</td>
            			<td>${order.date}
        			</tr>
    			</c:forEach>
			</table>
			<table class = "bestSellers">
				<tr><td>Stock Symbol</td><td>Company</td><td>Stock Type</td><td>Price</td></tr>
    			<c:forEach var="stock" items="${bestSellers}">
        			<tr>
            			<td>${stock.symbol}</td>
            			<td>${stock.company}</td>
            			<td>${stock.type}</td>
            			<td>${stock.PPS }</td>
        			</tr>
    			</c:forEach>
			</table>
			<table class = "suggestion">
				<tr><td>Stock Symbol</td><td>Company</td><td>Stock Type</td><td>Price</td></tr>
    			<c:forEach var="stock" items="${suggestions}">
        			<tr>
            			<td>${stock.symbol}</td>
            			<td>${stock.company}</td>
            			<td>${stock.type}</td>
            			<td>${stock.PPS }</td>
        			</tr>
    			</c:forEach>
			</table>
			
			<table class = "currentStocks">
				<tr><td>Stock Symbol</td><td>Company</td><td>Stock Type</td><td>price</td><td>number</td></tr>
    			<c:forEach var="stock" items="${stocks}">
        			<tr>
            			<td>${stock.stockSymbol}</td>
            			<td>${stock.companyName}</td>
            			<td>${stock.type}</td>
            			<td>${stock. pricePerShare}</td>
            			<td>${stock.numOfShares}</td>
        			</tr>
    			</c:forEach>
			</table>
			
			<table class = "trailingHistory">
				<tr><td>Stock Symbol</td><td>number of shares</td></tr>
    			<c:forEach var="trailingHistory" items="${trailingHistorys}">
        			<tr>
            			<td>${trailingHistory.stockSymbol}</td>
            			<td>${trailingHistory.numOfShares}</td>
        			</tr>
    			</c:forEach>
			</table>
			
			<table class = "hiddenHistory">
				<tr><td>Stock Symbol</td><td>number of shares</td></tr>
    			<c:forEach var="hiddenHistory" items="${hiddenHistorys}">
        			<tr>
            			<td>${hiddenHistory.stockSymbol}</td>
            			<td>${hiddenHistory.numOfShares}</td>
        			</tr>
    			</c:forEach>
			</table>
			
			<div class = "stockPriceHis">
			<form action="" id="datePicker">
				<input type="date" name="bday" max="1979-12-31">
				<input type="date" name="bday" min="2000-01-02">
				<input type="submit"> 
			</form>
			<table>
				<tr><td>Stock Symbol</td><td>number of shares</td></tr>
    			<c:forEach var="hiddenHistory" items="${hiddenHistorys}">
        			<tr>
            			<td>${hiddenHistory.stockSymbol}</td>
            			<td>${hiddenHistory.numOfShares}</td>
        			</tr>
    			</c:forEach>
			</table>
			</div>
		</div><!--/.row -->
	</div>	<!--/.main-->

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script src="js/mainClient.js"></script>
</body>
</html>