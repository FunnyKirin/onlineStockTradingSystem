# Record an order
DELIMITER //
CREATE PROCEDURE recordOrder(IN NumShares INTEGER, IN ID INTEGER, IN PriceType CHAR(20), IN OrderType CHAR(4))
BEGIN
	INSERT INTO stockorder(NumShares, ID, PriceType, OrderType)
	VALUES(NumShares, ID, PriceType, OrderType);
	END;
DELIMITER;

# Produce a list of mailing list
DELIMITER //
CREATE PROCEDURE getMailingList()
BEGIN
	SELECT Email FROM Client;
	END;
DELIMITER;

# Suggests customers buy stuff they haven't seen before
DELIMITER //
CREATE PROCEDURE giveSuggestion(IN CustID INTEGER)
BEGIN
	SELECT *
	FROM Account A, Stock S, StockOrder O
	WHERE A.ClientID = CustID AND S.StockSymbol <> O.StockSymbol;
END;
DELIMITER;

# Successfully fish people in the business
DELIMITER //
CREATE PROCEDURE addCustomer(Email CHAR(32), Rating INTEGER, CreditCardNumber BIGINT,ID INTEGER)
BEGIN
	INSERT INTO Client(Email, Rating, CreditCardNumber, ID)
    VALUES(Email, Rating, CreditCardNumber, ID);
    INSERT IGNORE INTO Account(DateCreated, Number)
    VALUES(NOW(), CreditCardNumber);
END;
DELIMITER;

# Successfully fish people in the business
DELIMITER //
CREATE PROCEDURE editCustomer(E CHAR(32), R INTEGER, CCN BIGINT,I INTEGER)
BEGIN
	UPDATE Client(Email, Rating, CCN, ID)
    SET Email = E, SET Rating = R,
    SET CreditCardNumber = CCN,
    SET ID = I;
    INSERT IGNORE INTO Account(DateCreated, Number)
    VALUES(NOW(), CreditCardNumber);
END;
DELIMITER;

# Successfully fish people in the business
DELIMITER //
CREATE PROCEDURE deleteCustomer(CustID INTEGER)
BEGIN
	DELETE FROM Client
    WHERE ID = CustID;
END;
