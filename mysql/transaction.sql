
start transaction;

insert into location(ZipCode, City, State)
values(11790, 'Stony Brook', 'NY'),
	(11794, 'Stony Brook', 'NY'), 
	(93536, 'Los Angeles', 'CA');

insert into person(LastName, FirstName, SSN, Address, Telephone, ZipCode)
values('Yang', 'Shang', 111111111, '123 Success Street', 5166328959, 11790),
	('Du', 'Victor', 222222222, '456 Fortune Street', 5166324360, 11790),
    ('Smith', 'John', 333333333, '789 Peace Blvd', 3154434321, 93536),
    ('Philip', 'Lewis', 444444444, '135 Knowledge Lane', 5166668888, 11794),
    ('Smith', 'David', 123456789, '123 College Road', 5162152345, 11790),
    ('Warren', 'David', 789123456, '456 Sunken Street', 6316329987, 11794);
    
insert into employee(ID, SSN, HourlyRate, StartDate)
values(1, 123456789, 60, '2005-11-01'),
	(2, 789123456, 50, '2006-02-02');

insert into client(Email, Rating, CreditCardNumber,ID)
values('syang@cs.sunysb.edu', 1, 1234567812345678, 111111111),
	('vicdu@cs.sunysb.edu', 1, 5678123456781234, 222222222),
    ('jsmith@ic.sunysb.edu', 1, 2345678923456789, 333333333),
    ('pml@cs.sunysb.edu', 1, 6789234567892345, 444444444);
    
insert into stock(StockSymbol, CompanyName, Type, PricePerShare)
values('GM', 'General Motors', 'automotive', 34.23),
	('IBM', 'IBM', 'computer', 91.41),
    ('F', 'Ford', 'automotive', 9.0);
    
insert into account(ID, DateOpened, ClientID)
values(1, '2006-10-01', 444444444),
    (2, '2006-10-15', 222222222);

insert into hasstock(AccountId,	StockSymbol, NumShares)
values	(1, 'GM', 	250),
		(1, 'F', 150),
		(2, 'IBM',	50);

insert into stockorder(NumShares, ID, PriceType, OrderType)
values(75, 1, 'Market', 'buy'),
	(10, 2, 'TrailingStop', 'sell');
    
insert into transaction(Id, Fee, DateTime, PricePerShare)
values(1, 50, '2015-01-01', 100),
(2, 0, '2015-01-01', 100);

insert into Trade(AccountId, BrokerId, OrderId, StockId, TransactionId)
values(1, 1, 1, 'GM', 1),
(2, 2, 2, 'IBM',2);

commit;