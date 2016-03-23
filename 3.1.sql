#Set the share price of a stock (for simulating market fluctuations in a stock's share price)
DELIMITER //
CREATE PROCEDURE updateStockPrice(IN stockPrice INTEGER, stockSymbol CHAR(20))
 IF (stockPrice < 0 AND stockSymbol IS NULL) THEN
	 BEGIN
	 END;
 ELSE
	 BEGIN
		 UPDATE Stock
		 SET PricePerShare = stockPrice
		 WHERE StockSymbol = stockSymbol;
	 END;
DELIMITER;

#Add, Edit and Delete information for an employee
#ADD
DELIMITER //
CREATE PROCEDURE addEmployee(IN ID INTEGER, SSN INTEGER, StartDate DATE, HourlyRate INTEGER)
 IF (ID < 0 AND ID > 1000000000)THEN
	BEGIN
		 
	END;
 ELSE
	BEGIN
		INSERT INTO Employee
		VALUES(ID, SSN, StartDate, HourlyRate)
	END;
DELIMITER;

#EDIT
DELIMITER //
CREATE PROCEDURE editEmployeeSSN(IN ID INTEGER, SSN INTEGER)
  IF (ID < 0 AND ID > 1000000000) THEN
	 BEGIN
     
	 END;
 ELSE
	 BEGIN
		 UPDATE Stock
		 SET Stock.SSN = SSN
		 WHERE Stock.ID = ID;
	 END;
DELIMITER;

DELIMITER //
CREATE PROCEDURE editEmployeeStartDate(IN ID INTEGER, StartDate DATE)
  IF (ID < 0 AND ID > 1000000000) THEN
	 BEGIN

	 END;
 ELSE
	 BEGIN
		 UPDATE Stock
		 SET Stock.StartDate = StartDate
		 WHERE Stock.ID = ID;
	 END;
DELIMITER;

DELIMITER //
CREATE PROCEDURE editEmployeeHourlyRate(IN ID INTEGER, HourlyRate INTEGER)
  IF (ID < 0 AND ID > 1000000000) THEN
	 BEGIN 
		
	 END;
 ELSE
	 BEGIN
		 UPDATE Stock
		 SET HourlyRate = HourlyRate
		 WHERE Stock.ID = ID;
	 END;
DELIMITER;


#delete
DELIMITER //
CREATE PROCEDURE deleteEmployeeSSN(IN ID INTEGER)
  IF (ID < 0 AND ID > 1000000000) THEN
	 BEGIN
		
	 END;
 ELSE
	 BEGIN
		 UPDATE Stock
		 SET Stock.SSN = NULL
		 WHERE Stock.ID = ID;
	 END;
DELIMITER;

DELIMITER //
CREATE PROCEDURE deleteEmployeeStartDate(IN ID INTEGER)
  IF (ID < 0 AND ID > 1000000000) THEN
	 BEGIN
		 
	 END;
 ELSE
	 BEGIN
		 UPDATE Stock
		 SET Stock.StartDate = NULL
		 WHERE Stock.ID = ID;
	 END;
DELIMITER;

DELIMITER //
CREATE PROCEDURE deleteEmployeeHourlyRate(IN ID INTEGER)
 BEGIN
	 UPDATE Stock
	 SET HourlyRate = NULL
	 WHERE Stock.ID = ID;
 END //
DELIMITER;

#Obtain a sales report for a particular month
DELIMITER //
CREATE PROCEDURE monthlySalesReport(IN month INTEGER)
  IF (ID < 0 AND ID > 1000000000) THEN
	 BEGIN
		 
	 END;
 ELSE
	 BEGIN
		 SELECT StockOrder.*
		 FROM StockOrder
		 WHERE Month(StockOrder.DateTime) = month
	 END;
DELIMITER;

#Produce a comprehensive listing of all stocks
DELIMITER //
CREATE PROCEDURE stockListing()
 BEGIN
	 SELECT* FROM Stock
 END;
DELIMITER;

#Produce a list of orders by stock symbol
DELIMITER //
CREATE PROCEDURE stockListingBySymbol()
 BEGIN
	 SELECT* FROM Stock
	 ORDER BY Stock.StockSymbol;
 END //
DELIMITER;

#Produce a list of orders by customer name
DELIMITER //
CREATE PROCEDURE stockListingByName()
 BEGIN
	 SELECT* FROM Stock
	 ORDER BY Stock.CompanyName;
 END //
DELIMITER;

#Produce a summary listing of revenue generated by a particular stock 
DELIMITER //
CREATE PROCEDURE revenueByStock(IN StockSymbol CHAR(20))
 BEGIN
	 SELECT hasStock.NumShares * Stock.PricePerShare
	 FROM Stock, hasStock
	 WHERE Stock.StockSymbol = StockSymbol
 END //
DELIMITER;

#Produce a summary listing of revenue generated by a particular stock type
DELIMITER //
CREATE PROCEDURE revenueByStockType(IN Type CHAR(20))
 BEGIN
	 SELECT hasStock.NumShares * Stock.PricePerShare
	 FROM Stock, hasStock
	 WHERE Stock.Type = Type;
 END //
DELIMITER;

#Produce a summary listing of revenue generated by a particular customer
DELIMITER //
CREATE PROCEDURE revenueByCustomerID(IN ID INTEGER)
 BEGIN
	 SELECT hasStock.NumShares * Stock.PricePerShare
	 FROM Client, Stock, hasStock
	 WHERE Client.ID = ID AND Stock.StockSymbol = hasStock.StockSymbol
 END //
DELIMITER;

#Determine which customer representative generated most total revenue
DELIMITER //
CREATE PROCEDURE mostRevenue_CustomerRepresentative()
 BEGIN
	 SELECT Employee.*
	 FROM Employee E
	 INNER JOIN
	    (SELECT E.ID, MAX(Stock.PricePerShare * hasStock.NumShares) AS MaxRevenue
	    FROM E AND Stock AND hasStock
	    GROUP BY E.ID) groupedE
	 ON E.home = groupedE.ID
	 AND E.datetime = groupedE.MaxRevenue
 END //
DELIMITER;

#Determine which customer generated most total revenue
DELIMITER //
CREATE PROCEDURE customer_mostRevenue()
 BEGIN
	SELECT C.*
	FROM Client C
	INNER JOIN
	    (SELECT C.ID, MAX(Stock.PricePerShare * hasStock.NumShares) AS MaxRevenue
	    FROM C AND S AND H
	    GROUP BY C.ID) groupedC
	ON C.ID = groupedC.ID 
	AND C.datetime = groupedC.MaxRevenue
 END //
DELIMITER;

#Produce a list of most actively traded stocks
DELIMITER //
CREATE PROCEDURE activeStocks()
 BEGIN
    SELECT Trade.StockId
	FROM Trade
	GROUP BY Trade.StockId
	ORDER BY COUNT(Trade.StockId) DESC
 END //
DELIMITER;

# Stocks available of a particular type and most-recent order info
DELIMITER //
CREATE PROCEDURE specificStockWithRecentInfo(IN PriceType CHAR(20))
 BEGIN
    SELECT StockOrder.*
	FROM Stock, StockOrder
	WHERE StockOrder.PriceType = PriceType AND StockOrder.PriceType = PriceType AND (
		SELECT MAX(StockOrder.DateTime)
		FROM StockOrder)
 END //
DELIMITER;

# Best-Seller list of stocks
DELIMITER //
CREATE PROCEDURE bestSellerStocks()
 BEGIN
    SELECT Trade.StockId
	FROM Trade, StockOrder
	WHERE Trade.OrderId = StockOrder.ID AND StockOrder.OrderType = 'sell'
	GROUP BY Trade.StockId
	ORDER BY COUNT(Trade.StockId) DESC
 END //
DELIMITER;
