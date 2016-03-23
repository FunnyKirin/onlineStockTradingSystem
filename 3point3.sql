# A customer's current stock holdings 
DELIMITER //
CREATE PROCEDURE listStock(IN CustID INTEGER)
BEGIN
	SELECT S.*, NumShares
    FROM Stock S, Account A, hasStcok H
    WHERE A.ClientID = CustID;
END//
DELIMITER;

# A history of all current and past orders a customer has placed 
DELIMITER //
CREATE PROCEDURE orderHistory(CustID INTEGER)
BEGIN
	SELECT O.ID, S.StockSymbol, O.NumShares, O.PriceType, O.OrderType 
    FROM StockOrder O, Trade T, Stock S, Account A
    WHERE A.ClientID = CustID AND A.ID = T.AccountId 
		AND T.OrderId = O.ID AND S.StockSymbol = T.StockId;
END//
DELIMITER;

# Stocks available with a particular keyword or set of keywords in the stock name, and most-recent order info 
DELIMITER //
CREATE PROCEDURE searchAvailStockByName(IN SName CHAR(20))
BEGIN
	SELECT DISTINCT *
    FROM Stock S, StockOrder O
    WHERE CompanyName LIKE CONCAT('%', CONCAT(SNAME, '%'))
		AND O.StockSymbol = S.StockSymbol
	GROUP BY S.StockSymbol;
END//
DELIMITER;

# Stocks available of a particular type and most-recent order info 
DELIMITER //
CREATE PROCEDURE searchAvailStockByType(IN SType CHAR(20))
BEGIN
	SELECT *
    FROM Stock S, StockOrder O
    WHERE S.Type LIKE CONCAT('%', CONCAT(SType, '%'))
		AND O.StockSymbol = S.StockSymbol
	GROUP BY S.StockSymbol;
END//
DELIMITER;

# Personalized stock suggestion list 
DELIMITER //
CREATE PROCEDURE personalizedStocks(IN CustID INTEGER)
BEGIN
	CALL giveSuggestion(CustID);
END//
