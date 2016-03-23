CREATE TABLE Location (
    ZipCode INTEGER,
    City CHAR(20) NOT NULL,
    State CHAR(20) NOT NULL,
    PRIMARY KEY (ZipCode)
);

CREATE TABLE Person (
    SSN INTEGER,
    CHECK(SSN < 1000000000 AND SSN > 99999999),
    LastName CHAR(20) NOT NULL,
    FirstName CHAR(20) NOT NULL,
    Address CHAR(20),
    ZipCode INTEGER,
    Telephone bigint,
    PRIMARY KEY (SSN),
    FOREIGN KEY (ZipCode)
	REFERENCES Location (ZipCode)
	ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE Employee (
    ID INTEGER AUTO_INCREMENT,
    CHECK (ID > 0 AND ID < 1000000000),
    SSN INTEGER,
    StartDate DATE,
    HourlyRate INTEGER,
    PRIMARY KEY (ID),
    FOREIGN KEY (SSN)
        REFERENCES Person (SSN)
        ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE Client (
    Email CHAR(32),
    Rating INTEGER,
    CreditCardNumber BIGINT,
    ID INTEGER,
    CHECK (ID > 99999999 AND ID < 1000000000),
    PRIMARY KEY (ID),
    FOREIGN KEY (ID)
        REFERENCES Person (SSN)
        ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE Account (
    ID INTEGER AUTO_INCREMENT,
    CHECK (ID > 0 AND ID < 1000000000),
    DateOpened DATE,
    ClientID INTEGER,
    CHECK (ClientID > 99999999 AND ClientID < 1000000000),
    PRIMARY KEY (ID),
    FOREIGN KEY (ClientID)
        REFERENCES Client (ID)
        ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE Stock (
    StockSymbol CHAR(20),
    CompanyName CHAR(20) NOT NULL,
    Type CHAR(20) NOT NULL,
    PricePerShare DECIMAL(10, 2),
    PRIMARY KEY (StockSymbol)
);

create TABLE hasStock(
	AccountId INTEGER,
    StockSymbol char(20),
    NumShares INTEGER,
    PRIMARY KEY (AccountId, StockSymbol, NumShares),
    
    FOREIGN KEY (AccountID)
	REFERENCES Account (ID)
	ON DELETE NO ACTION ON UPDATE CASCADE,
    FOREIGN KEY (StockSymbol)
    REFERENCES Stock (StockSymbol)
    ON DELETE NO ACTION ON UPDATE CASCADE
    
);

CREATE TABLE Transaction (
	Id INTEGER AUTO_INCREMENT,
	Fee INTEGER,
	DateTime DATETIME,
	PricePerShare INTEGER,
	PRIMARY KEY (Id) 
);

CREATE TABLE StockOrder (
	StockSymbol CHAR(20) NOT NULL,
    NumShares INTEGER,
    ID INTEGER AUTO_INCREMENT,
    DateTime DATETIME,
    Percentage DECIMAL(10, 2),
    PriceType CHAR(20),
    CHECK (PriceType IN ('Market' , 'MarketOnClose',
        'TrailingStop', 
        'HiddenStop')),
    OrderType CHAR(4),
    CHECK (OrderType IN ('buy' , 'sell')),
    PRIMARY KEY (ID),
    FOREIGN KEY (StockSymbol) REFERENCES
		Stock (StockSymbol)
);

CREATE TABLE Market (
	ID INTEGER AUTO_INCREMENT,
	BuySellType CHAR(4) NOT NULL,
    OrderID INTEGER,
    CHECK (BuySellType IN ('buy' , 'sell')),
    PRIMARY KEY (ID),
    FOREIGN KEY (OrderID) REFERENCES
		StockOrder (ID),
	FOREIGN KEY (BuySellType) REFERENCES
		StockOrder (OrderType)
);

CREATE TABLE MarketOnClose (
	ID INTEGER AUTO_INCREMENT,
	BuySellType CHAR(4),
    OrderID INTEGER,
    PRIMARY KEY (ID),
    FOREIGN KEY (OrderID) REFERENCES
		StockOrder (ID),
	FOREIGN KEY (BuySellType) REFERENCES
		StockOrder (OrderType)
);

CREATE TABLE TrailingStop (
	ID INTEGER AUTO_INCREMENT,
	Percentage DECIMAL(10, 2),
    OrderID INTEGER,
    PRIMARY KEY (ID),
    FOREIGN KEY (OrderID) REFERENCES
		StockOrder (ID),
	FOREIGN KEY (Percentage) REFERENCES
		StockOrder (Percentage)
);

CREATE TABLE HiddenStop (
	ID INTEGER AUTO_INCREMENT,
	PricePerShare DECIMAL(10, 2),
    OrderID INTEGER,
    PRIMARY KEY (ID),
    FOREIGN KEY (OrderID) REFERENCES
		StockOrder (ID),
	FOREIGN KEY (PricePerShare) REFERENCES
		StockOrder (PricePerShare)
);

CREATE TABLE Trade (
    AccountId INTEGER,
     CHECK (AccountId > 0 AND AccountId < 1000000000),
    BrokerId INTEGER,
    CHECK (BrokerId > 0 AND BrokerId < 1000000000),
    OrderId INTEGER,
    StockId CHAR(20),
	TransactionId INTEGER,

    PRIMARY KEY (AccountId , BrokerId, OrderId , StockId, TransactionId),
	
    FOREIGN KEY (AccountID)
        REFERENCES Account (ID)
        ON DELETE NO ACTION ON UPDATE CASCADE,
    FOREIGN KEY (BrokerId)
        REFERENCES Employee (ID)
        ON DELETE NO ACTION ON UPDATE CASCADE,
    FOREIGN KEY (OrderId)
        REFERENCES StockOrder (ID)
        ON DELETE NO ACTION ON UPDATE CASCADE,
    FOREIGN KEY (StockId)
        REFERENCES Stock (StockSymbol)
        ON DELETE NO ACTION ON UPDATE CASCADE,
	FOREIGN KEY (TransactionId)
		REFERENCES Transaction(Id)
		ON DELETE NO ACTION ON UPDATE CASCADE
);
