USE netrade;

--Set the share price of a stock (for simulating market fluctuations in a stock's share price)
DELIMITER //
CREATE PROCEDURE updateStockPrice(IN stockPrice INTEGER, stockSymbol CHAR(20))
	 BEGIN
		 UPDATE Stock
		 SET Stock.PricePerShare = stockPrice
		 WHERE Stock.StockSymbol = stockSymbol;
	 END//

--Add, Edit and Delete information for an employee
DELIMITER //
CREATE PROCEDURE addEmployee(IN ID INTEGER, SSN INTEGER, StartDate DATE, HourlyRate INTEGER)
	BEGIN
		INSERT INTO Employee
		VALUES(ID, SSN, StartDate, HourlyRate);
	END//

#EDIT
DELIMITER //
CREATE PROCEDURE editEmployee(IN ID INTEGER, StartDate DATE, HourlyRate INTEGER)
	 BEGIN
		 UPDATE Employee
		 SET Employee.StartDate = StartDate,
         HourlyRate = HourlyRate
		 WHERE Employee.ID = ID;
	 END//

DELIMITER //
CREATE PROCEDURE editEmployeeStartDate(IN ID INTEGER, StartDate DATE)
	 BEGIN
		 UPDATE Employee
		 SET Employee.StartDate = StartDate
		 WHERE Employee.ID = ID;
	 END//

DELIMITER //
CREATE PROCEDURE editEmployeeHourlyRate(IN ID INTEGER, HourlyRate INTEGER)
	 BEGIN
		 UPDATE Employee
		 SET HourlyRate = HourlyRate
		 WHERE Employee.ID = ID;
	 END//


#delete
DELIMITER //
CREATE PROCEDURE deleteEmployeeSSN(IN ID INTEGER)
	 BEGIN
		 UPDATE Employee
		 SET Employee.SSN = NULL
		 WHERE Employee.ID = ID;
	 END//

DELIMITER //
CREATE PROCEDURE deleteEmployeeStartDate(IN ID INTEGER)
	 BEGIN
		 UPDATE Employee
		 SET Employee.StartDate = NULL
		 WHERE Employee.ID = ID;
	 END//

DELIMITER //
CREATE PROCEDURE deleteEmployeeHourlyRate(IN ID INTEGER)
 BEGIN
	 UPDATE Employee
	 SET Employee.HourlyRate = NULL
	 WHERE Employee.ID = ID;
 END //

#Obtain a sales report for a particular month
DELIMITER //
CREATE PROCEDURE monthlySalesReport(IN month INTEGER)
	BEGIN
		SELECT T.*
		FROM Trade T, StockOrder O
		WHERE MONTH(O.DateTime) = month
			AND T.OrderId = O.Id;
	END//

#Produce a comprehensive listing of all stocks
DELIMITER //
CREATE PROCEDURE stockListing()
BEGIN
	 SELECT* FROM Stock;
END//

#Produce a list of orders by stock symbol
DELIMITER //
CREATE PROCEDURE stockListingBySymbol()
BEGIN
	SELECT * FROM Stock
	ORDER BY Stock.StockSymbol;
END //

#Produce a list of orders by customer name
DELIMITER //
CREATE PROCEDURE stockListingByName()
BEGIN
	SELECT * FROM Stock
	ORDER BY Stock.CompanyName;
END //

#Produce a summary listing of revenue generated by a particular stock 
DELIMITER //
CREATE PROCEDURE revenueByStockSymbol(IN Symbol CHAR(20))
BEGIN
	SELECT S.*, SUM(R.Fee) AS 'Revenue'
    FROM Stock S, Trade T, Transaction R, StockOrder O
    WHERE S.StockSymbol = Symbol AND O.ID = T.OrderId
		AND R.ID = T.TransactionId
        AND T.StockId = S.StockSymbol
	GROUP BY S.StockSymbol;
END //

#Produce a summary listing of revenue generated by a particular stock type
DELIMITER //
CREATE PROCEDURE revenueByStockType(IN SType CHAR(20))
BEGIN
	SELECT S.*, SUM(R.Fee) AS 'Revenue'
    FROM Stock S, Trade T, Transaction R, StockOrder O
    WHERE S.Type = SType AND O.ID = T.OrderId
		AND R.ID = T.TransactionId
        AND T.StockId = S.StockSymbol
	GROUP BY S.StockSymbol;
END //

#Produce a summary listing of revenue generated by a particular customer
DELIMITER //
CREATE PROCEDURE revenueByCustID(IN CID INTEGER)
 BEGIN
	 SELECT C.*, SUM(R.Fee) AS 'Revenue'
	 FROM Transaction R, Trade T, Account A, Client C, Stock S
     WHERE T.TransactionId = R.ID
		AND A.ID = T.AccountId
        AND C.ID = A.ClientID
        AND C.ID = CID;
 END //

#Determine which customer representative generated most total revenue
DELIMITER //
CREATE PROCEDURE mostRevenue_CustomerRepresentative()
BEGIN
	SELECT E.SSN AS 'Employee SSN', E.ID AS 'Employee ID', (R.PricePerShare - S.PricePerShare) * O.NumShares AS MaxRevenue
	FROM Employee E, Transaction R, StockOrder O, Stock S, Trade T
    WHERE T.StockId = S.StockSymbol
        AND T.TransactionId = R.ID
        AND T.OrderId = O.ID
        AND T.BrokerId = E.ID
    ORDER BY MaxRevenue DESC LIMIT 1;
END //
drop procedure mostRevenue_CustomerRepresentative;
#Determine which customer generated most total revenue
DELIMITER //
CREATE PROCEDURE customer_mostRevenue()
BEGIN
	SELECT C.ID AS 'Client ID', (R.PricePerShare - S.PricePerShare) * O.NumShares AS MaxRevenue
	FROM Client C, Transaction R, StockOrder O, Stock S, Trade T, Account A
    WHERE T.StockId = S.StockSymbol AND T.AccountId = A.ID
        AND T.TransactionId = R.ID AND T.OrderId = O.ID
        AND A.ClientID = C.ID
    ORDER BY MaxRevenue DESC LIMIT 1;
END //
drop procedure customer_mostRevenue;
#Produce a list of most actively traded stocks
DELIMITER //
CREATE PROCEDURE activeStocks()
BEGIN
    SELECT Trade.StockId AS 'Active Stocks'
	FROM Trade
	GROUP BY Trade.StockId
	ORDER BY COUNT(Trade.StockId) DESC;
END //

