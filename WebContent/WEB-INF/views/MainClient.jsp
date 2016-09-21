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
			<li><input type="submit" class="submit" id="stockPriceHis" value="Stock Price History" /></li>
			<li><input type="submit" class="submit" id="orderHistories" value="order histories" /></li>
			<li><input type="submit" class="submit" id="searchStock" value="Search Stock" /></li>
			<li><input type="submit" class="submit" id="Best-sellers" value="Best-sellers" /></li>
			<li><input type="submit" class="submit" id="Suggestions" value="Suggestions" /></li>
			<li role="presentation" class="divider"></li>
			<li><a href="#" id="help">Help</a></li>
		</ul>

	</div><!--/.sidebar-->

	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row" id="dynamic">
		<div class = "searchResult">
			<form role="search" class="search" action="doClientMain" method="post">
				<div class="form-group">
					
					<input type="text" name="searchText" value=""  class="form-control" placeholder="Search Stocks" >
					<input type="submit" name="searchNameButton" class="form-control" value="Search by Name" placeholder="Search" id="searchButton">
					<input type="submit" name="searchTypeButton" class="form-control" value="Search by Type" id="searchButton1">
				</div>
			</form>
			<table>
				<tr><td>Stock Symbol</td><td>Company</td><td>Stock Type</td><td>Price</td></tr>
    			<c:forEach var="stock" items="${searchResult}">
        			<tr>
            			<td>${stock.symbol}</td>
            			<td>${stock.company}</td>
            			<td>${stock.type}</td>
            			<td>${stock.pps }</td>
        			</tr>
    			</c:forEach>
			</table>
						<form role="search" class="search" action="doClientMain" method="post">
			
			<br><br><br>
				<input type="text" name="buysymbol" value=""  class="buyText" placeholder="Symbol" >
				<input type="text" name="buynumber" value=""  class="buyText" placeholder="number" >
				<input type="text" name="buytype" value=""  class="buyText" placeholder="Type" >
				<input type="text" name="buyvalue" value=""  class="buyText" placeholder="Value" >
				<input type="submit" name="buyButton" class="form-control" value="Buy">
			</form>
			</div>

<!-- 			<input type="text" name="searchText" value=""  class="form-control" placeholder="Search Stocks" >
			<input type="submit" name="searchNameButton" class="form-control" placeholder="Search"> -->

			<div class = "orderHistories">
			<table>
				<tr><td>ID</td><td>Stock Symbol</td><td>number of shares</td><td>Price Type</td><td>Order Type</td><td>Date</td></tr>
    			<c:forEach var="order" items="${orders}">
        			<tr onclick="getStopHis(${order})">
        				<td>${order.id}</td>
            			<td>${order.symbol}</td>
            			<td>${order.numShares}</td>
            			<td>${order.priceType}</td>
            			<td>${order.orderType}</td>
            			<td>${order.date}</td>
        			</tr>
    			</c:forEach>
			</table>
			<form role="search" class="search" action="doClientMain" method="post">
			<input type="text" name="searchHistoryText" value=""  class="form-control" placeholder="Search order history" >
			<input type="submit" name="searchHistoryButton" class="form-control" placeholder="Search">
			
			</form>
			
			<table>
				<tr><td>Stock Symbol</td><td>price</td><td>Order Type</td><td>Type value</td><td>sellPrice</td><td>number of Shares</td><td>Date</td></tr>
    			<c:forEach var="OrderHistory" items="${OrderHistorys}">
        			<tr onclick="getStopHis(${OrderHistory})">
			        	<td>${OrderHistory.stockSymbol}</td>
            			<td>${OrderHistory.price}</td>
            			<td>${OrderHistory.type}</td>
            			<td>${OrderHistory.value}</td>
            			<td>${OrderHistory.sellPrice}</td>
            			<td>${OrderHistory.numShares}</td>
            			<td>${OrderHistory.date}</td>

        			</tr>
    			</c:forEach>
			</table>
			
			</div>
			<table class = "bestSellers">
				<tr><td>Stock Symbol</td><td>Company</td><td>Stock Type</td><td>Price</td></tr>
    			<c:forEach var="stock" items="${bestSellers}">
        			<tr>
            			<td>${stock.symbol}</td>
            			<td>${stock.company}</td>
            			<td>${stock.type}</td>
            			<td>${stock.pps }</td>
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
            			<td>${stock.pps }</td>
        			</tr>
    			</c:forEach>
			</table>
			<div class = "currentStocks">
			<table>
				<tr><td>Stock Symbol</td><td>Company</td><td>Stock Type</td><td>price</td><td>number</td></tr>
    			<c:forEach var="stock" items="${stocks}">
        			<tr>
            			<td>${stock.stockSymbol}</td>
            			<td>${stock.companyName}</td>
            			<td>${stock.type}</td>
            			<td>${stock.pricePerShare}</td>
            			<td>${stock.numOfShares}</td>
        			</tr>
    			</c:forEach>
			</table>
									<form role="search" class="search" action="doClientMain" method="post">
			
				<input type="text" name="sellSymbolText" value="Stock Symbol"  class="form-control" placeholder="Stock Symbol" >
				<input type="text" name="sellNumberText" value="number"  class="form-control" placeholder="number" >
				<input type="submit" name="sellButton" value="Sell"  class="form-control" placeholder="Sell">
			</form>
			</div>
			
			<div class = "stockPriceHis">
						<form role="search" class="search" action="doClientMain" method="post">
			
				<input type="text" name="searchStockHistoryText" value="Stock Symbol"  class="form-control" placeholder="Search" >
				<input type="submit" name="searchStockHistoryButton" value="Search"  class="form-control" placeholder="Search Stock">
</form>

<!-- 			<form action="" id="datePicker">
				<input type="date" name="bday" max="1979-12-31">
				<input type="date" name="bday" min="2000-01-02">
				<input type="submit"> 
			</form> -->
			<table>
				<tr><td>Price</td><td>Date</td></tr>
    			<c:forEach var="OrderHistory" items="${StockHistory}">
        			<tr>
            			<td>${OrderHistory.price}</td>
            			<td>${OrderHistory.date}</td>
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