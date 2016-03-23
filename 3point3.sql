DELIMITER //
CREATE PROCEDURE listStock(IN CustID INTEGER)
BEGIN
	SELECT S.*, NumShares
    FROM Stock S, Account A, hasStcok H
    WHERE A.ClientID = CustID;
END;
DELIMITER;

DELIMITER //
CREATE PROCEDURE orderHistory()
BEGIN
	SELECT *
    FROM StockOrder;
END;
DELIMITER;

DELIMITER //
CREATE PROCEDURE orderHistory()
BEGIN
	SELECT *
    FROM StockOrder;
END;
DELIMITER;

DELIMITER //
CREATE PROCEDURE searchAvailStockByName(IN SName CHAR(20))
BEGIN
	SELECT *
    FROM Stock S
    WHERE CompanyName LIKE CONCAT('%', CONCAT(SNAME, '%'))
		AND NumShares > 0; 
END;
DELIMITER;

DELIMITER //
CREATE PROCEDURE searchAvailStockByType(IN SType CHAR(20))
BEGIN
	SELECT *
    FROM Stock S
    WHERE CompanyName LIKE CONCAT('%', CONCAT(SType, '%'))
		AND NumShares > 0;
END;
