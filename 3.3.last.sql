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
