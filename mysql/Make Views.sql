CREATE VIEW OrderListingsBySymbols(Symbol, Shares, Price, PriceType, OrderType, CustLast, CustFirst) AS
	SELECT DISTINCT S.StockSymbol, O.NumShares, S.PricePerShare, O.PriceType, O.OrderType, P.LastName, P.FirstName
    FROM StockOrder O, Stock S, trade T, Account A, client C, person P
    WHERE T.StockId = S.StockSymbol AND T.OrderId = O.Id And T.AccountId = A.Id AND A.ClientID=C.ID And C.ID = P.SSN
    ORDER BY S.StockSymbol;
 
 CREATE VIEW OrderListingsByCustomerName(CustLast, CustFirst, Symbol, Shares, Price, PriceType, OrderType) AS
	SELECT DISTINCT  P.LastName, P.FirstName, S.StockSymbol, O.NumShares, S.PricePerShare, O.PriceType, O.OrderType
    FROM StockOrder O, Stock S, trade T, Account A, client C, person P
    WHERE T.StockId = S.StockSymbol AND T.OrderId = O.Id And T.AccountId = A.Id AND A.ClientID=C.ID And C.ID = P.SSN
    ORDER BY P.LastName;