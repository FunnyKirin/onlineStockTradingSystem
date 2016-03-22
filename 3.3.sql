#A coustomer's current stock holding
CREATE VIEW CoustomerStock(LastName, FirstName, ClientID, CompanyName, NumShares) AS
Select lastname, firstname, C.ID, S.CompanyName, H.numshares
From Person P, hasStock H, Account A, Client C, Stock S
Where H.AccountId=A.ID and a.clientID = C.ID and C.ID = P.SSN and H.StockSymbol = S.StockSymbol;

#The share-price and trailing-stop history for a given conditional order

#The share-price and hidden-stop history for a given conditional order

#The share-price history of a given stock over a certain period of time (6 months)

#A history of all current and past orders a customer has placed

#Stocks available of a particular type and most-recent order info
CREATE VIEW typeAndOrder(StockType, )