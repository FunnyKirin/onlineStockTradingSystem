# Record an order
DELIMITER //
CREATE PROCEDURE recordOrder(IN NumShares INTEGER, IN ID INTEGER, IN PriceType CHAR(20), IN OrderType CHAR(4))
BEGIN
	INSERT INTO stockorder(NumShares, ID, PriceType, OrderType)
	VALUES(NumShares, ID, PriceType, OrderType);
	END//
DELIMITER;

# Produce a list of customer mailing list
DELIMITER //
CREATE PROCEDURE getMailingList()
BEGIN
	SELECT Email FROM Client;
	END//
DELIMITER;

# Produce a list of stock suggestions for a given customer (based on that customer's past orders) 
DELIMITER //
CREATE PROCEDURE giveSuggestion(IN CustID INTEGER)
BEGIN
    #CREATE VIEW Suggestion(Symbol, Comp AS 'Company', Price AS 'Price/Share', Type)
	SELECT S.StockSymbol, S.CompanyName, S.PricePerShare, S.Type
	FROM Account A, Stock S, StockOrder O
	WHERE A.ClientID = CustID AND S.StockSymbol = O.StockSymbol;
END//
DELIMITER;

# Successfully hook people up in the business
DELIMITER //
CREATE PROCEDURE addCustomer(IN FirstName CHAR(20), IN LastName CHAR(20), IN Address CHAR(20),
    IN ZipCode INTEGER, IN Telephone BIGINT,
	Email CHAR(32), Rating INTEGER, CreditCardNumber BIGINT,ID INTEGER)
BEGIN
	INSERT INTO Person(FirstName, LastName, Address, ZipCode, Telephone, SSN)
    VALUES(FirstName, LastName, Address, ZipCode, Telephone, ID);
	INSERT INTO Client(Email, Rating, CreditCardNumber, ID)
    VALUES(Email, Rating, CreditCardNumber, ID);
    INSERT IGNORE INTO Account(DateCreated, Number)
    VALUES(NOW(), CreditCardNumber);
END//
DELIMITER;

# Edit customer information
DELIMITER //
CREATE PROCEDURE editCustomer(E CHAR(32), R INTEGER, CCN BIGINT,I INTEGER)
BEGIN
	UPDATE Client
    SET Email = E, Rating = R,
    CreditCardNumber = CCN,
    ID = I;
    INSERT IGNORE INTO Account(DateCreated, Number)
    VALUES(NOW(), CreditCardNumber);
END//
DELIMITER;

# Delete people
DELIMITER //
CREATE PROCEDURE deleteCustomer(CustID INTEGER)
BEGIN
	DELETE FROM Client
    WHERE ID = CustID;
    DELETE FROM Person
    WHERE SSN = CustID;
END//
