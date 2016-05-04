USE netrade;
# A customer's current stock holdings 
DELIMITER //
CREATE PROCEDURE listStock(IN CustID INTEGER)
BEGIN
	SELECT S.*, NumShares
    FROM Stock S, Account A, hasStcok H
    WHERE A.ClientID = CustID;
END//

# The share-price and trailing-stop history for a given conditional order.
DELIMITER //
CREATE PROCEDURE hiddenstopHistory(IN OrderID INTEGER)
BEGIN
	SELECT S.PricePerShare, TrailingStop*TA.PricePerShare
FROM Stock S, Trade T, StockOrder O, Transaction TA
WHERE S.StockSymbol=T.StockId AND T.OrderId = O.ID;

END//

# The share-price and trailing-stop history for a given conditional order.
DELIMITER //
CREATE PROCEDURE trailingHistory(IN OrderID INTEGER)
BEGIN
	SELECT S.PricePerShare, O.Percentage*TA.PricePerShare
FROM Stock S, Trade T, StockOrder O, Transaction TA
WHERE S.StockSymbol=T.StockId AND T.OrderId = O.ID;

END//

#The share-price history of a given stock over a sertain period of time
DELIMITER //
CREATE PROCEDURE priceHistory(IN StockSymbol CHAR(20))
BEGIN
	Select S.PricePerShare
    From Stock S
    Where StockSymbol = S.StockSymbol;
END//

# A history of all current and past orders a customer has placed 
DELIMITER //
CREATE PROCEDURE orderHistory(CustID INTEGER)
BEGIN
	SELECT O.ID, S.StockSymbol, O.NumShares, O.PriceType, O.OrderType 
    FROM StockOrder O, Trade T, Stock S, Account A
    WHERE A.ClientID = CustID AND A.ID = T.AccountId 
	AND T.OrderId = O.ID AND S.StockSymbol = T.StockId;
END//

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

# Personalized stock suggestion list 
DELIMITER //
CREATE PROCEDURE personalizedStocks(IN CustID INTEGER)
BEGIN
	CALL giveSuggestion(CustID);
END//

# Stocks available of a particular type and most-recent order info
DELIMITER //
CREATE PROCEDURE specificStockWithRecentInfo(IN PriceType CHAR(20))
 BEGIN
    SELECT StockOrder.*
	FROM Stock, StockOrder
	WHERE StockOrder.PriceType = PriceType AND StockOrder.PriceType = PriceType AND (
		SELECT MAX(StockOrder.DateTime)
		FROM StockOrder);
 END //

# Best-Seller list of stocks
DELIMITER //
CREATE PROCEDURE bestSellerStocks()
 BEGIN
    SELECT Trade.StockId
	FROM Trade, StockOrder
	WHERE Trade.OrderId = StockOrder.ID AND StockOrder.OrderType = 'sell'
	GROUP BY Trade.StockId
	ORDER BY COUNT(Trade.StockId) DESC;
 END //
