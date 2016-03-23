#Stocks avaliable of a particular type and most-recent order info
DELIMITER //
CREATE PROCEDURE specificStockWithRecentInfo()
 BEGIN
    SELECT Trade.StockId
	FROM Trade, StockOrder
	WHERE Trade.OrderId = StockOrder.ID
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
