USE netrade;

CREATE TABLE stockHistory(
	 StockSymbol CHAR(20),
     PricePerShare DECIMAL(10, 2),
     stockDate DATE,
     Primary key (StockSymbol, stockDate)
     
)