USE netrade;
# Record an order
DELIMITER //
CREATE PROCEDURE recordOrder(IN NumShares INTEGER, IN DateTime DateTime, IN PriceType CHAR(20), IN OrderType CHAR(4), IN Percentage INTEGER, IN PriceperShare INTEGER)
BEGIN
	INSERT INTO stockorder(NumShares,DateTime, PriceType, OrderType, Percentage, PriceperShare)
	VALUES(NumShares,DateTime, PriceType, OrderType, Percentage, PriceperShare);
	END//

# Produce a list of customer mailing list
DELIMITER //
CREATE PROCEDURE getMailingList()
BEGIN
	SELECT Email FROM Client;
	END//

# Produce a list of stock suggestions for a given customer (based on that customer's past orders) 
DELIMITER //
CREATE PROCEDURE giveSuggestion(IN CustID INTEGER)
BEGIN
    #CREATE VIEW Suggestion(Symbol, Comp AS 'Company', Price AS 'Price/Share', Type)
	SELECT S.StockSymbol, S.CompanyName, S.PricePerShare, S.Type
	FROM Account A, Stock S, StockOrder O, Trade T
	WHERE A.ID = CustID AND S.StockSymbol = T.StockId AND T.AccountId = A.ID AND T.OrderId= O.ID;
END//
drop procedure if exists giveSuggestion;
# Successfully hook people up in the business
DELIMITER //
CREATE PROCEDURE addCustomer(IN FirstName CHAR(20), IN LastName CHAR(20), IN Address CHAR(20),
    IN ZipCode INTEGER, IN City CHAR(20), IN State CHAR(20), IN Telephone BIGINT,
	Email CHAR(32), Rating INTEGER, CreditCardNumber BIGINT,ID INTEGER)
BEGIN
	INSERT ignore into location(ZipCode, City,State)
    values(ZipCode, City, State);
	INSERT INTO Person(FirstName, LastName, Address, ZipCode, Telephone, SSN)
    VALUES(FirstName, LastName, Address, ZipCode, Telephone, ID);
	INSERT INTO Client(Email, Rating, CreditCardNumber, ID)
    VALUES(Email, Rating, CreditCardNumber, ID);
    INSERT IGNORE INTO Account(DateOpened, ClientID)
    VALUES(NOW(), ID);
END//

# Edit customer information
DELIMITER //
CREATE PROCEDURE editCustomer(E CHAR(32), R INTEGER, CCN BIGINT,I INTEGER)
BEGIN
	UPDATE Client
    SET Email = E, Rating = R,
    CreditCardNumber = CCN,
    ID = I;
END//

# Delete people
DELIMITER //
CREATE PROCEDURE deleteCustomer(CustID INTEGER)
BEGIN
	DELETE FROM Client
    WHERE ID = CustID;
END//
