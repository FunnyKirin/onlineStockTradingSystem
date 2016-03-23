-- MySQL dump 10.13  Distrib 5.6.28, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: hello
-- ------------------------------------------------------
-- Server version	5.6.28-1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Account`
--

DROP TABLE IF EXISTS `Account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Account` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DateOpened` date DEFAULT NULL,
  `ClientID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ClientID` (`ClientID`),
  CONSTRAINT `Account_ibfk_1` FOREIGN KEY (`ClientID`) REFERENCES `Client` (`ID`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Account`
--

LOCK TABLES `Account` WRITE;
/*!40000 ALTER TABLE `Account` DISABLE KEYS */;
INSERT INTO `Account` VALUES (1,'2006-10-01',444444444),(2,'2006-10-15',222222222);
/*!40000 ALTER TABLE `Account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Client`
--

DROP TABLE IF EXISTS `Client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Client` (
  `Email` char(32) DEFAULT NULL,
  `Rating` int(11) DEFAULT NULL,
  `CreditCardNumber` bigint(20) DEFAULT NULL,
  `ID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  CONSTRAINT `Client_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `Person` (`SSN`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Client`
--

LOCK TABLES `Client` WRITE;
/*!40000 ALTER TABLE `Client` DISABLE KEYS */;
INSERT INTO `Client` VALUES ('syang@cs.sunysb.edu',1,1234567812345678,111111111),('vicdu@cs.sunysb.edu',1,5678123456781234,222222222),('jsmith@ic.sunysb.edu',1,2345678923456789,333333333),('pml@cs.sunysb.edu',1,6789234567892345,444444444);
/*!40000 ALTER TABLE `Client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Employee` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SSN` int(11) DEFAULT NULL,
  `StartDate` date DEFAULT NULL,
  `HourlyRate` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `SSN` (`SSN`),
  CONSTRAINT `Employee_ibfk_1` FOREIGN KEY (`SSN`) REFERENCES `Person` (`SSN`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` VALUES (1,123456789,'2005-11-01',60),(2,789123456,'2006-02-02',50);
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Location`
--

DROP TABLE IF EXISTS `Location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Location` (
  `ZipCode` int(11) NOT NULL DEFAULT '0',
  `City` char(20) NOT NULL,
  `State` char(20) NOT NULL,
  PRIMARY KEY (`ZipCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Location`
--

LOCK TABLES `Location` WRITE;
/*!40000 ALTER TABLE `Location` DISABLE KEYS */;
INSERT INTO `Location` VALUES (11790,'Stony Brook','NY'),(11794,'Stony Brook','NY'),(93536,'Los Angeles','CA');
/*!40000 ALTER TABLE `Location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Person`
--

DROP TABLE IF EXISTS `Person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Person` (
  `SSN` int(11) NOT NULL DEFAULT '0',
  `LastName` char(20) NOT NULL,
  `FirstName` char(20) NOT NULL,
  `Address` char(20) DEFAULT NULL,
  `ZipCode` int(11) DEFAULT NULL,
  `Telephone` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`SSN`),
  KEY `ZipCode` (`ZipCode`),
  CONSTRAINT `Person_ibfk_1` FOREIGN KEY (`ZipCode`) REFERENCES `Location` (`ZipCode`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Person`
--

LOCK TABLES `Person` WRITE;
/*!40000 ALTER TABLE `Person` DISABLE KEYS */;
INSERT INTO `Person` VALUES (111111111,'Yang','Shang','123 Success Street',11790,5166328959),(123456789,'Smith','David','123 College Road',11790,5162152345),(222222222,'Du','Victor','456 Fortune Street',11790,5166324360),(333333333,'Smith','John','789 Peace Blvd',93536,3154434321),(444444444,'Philip','Lewis','135 Knowledge Lane',11794,5166668888),(789123456,'Warren','David','456 Sunken Street',11794,6316329987);
/*!40000 ALTER TABLE `Person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Stock`
--

DROP TABLE IF EXISTS `Stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Stock` (
  `StockSymbol` char(20) NOT NULL DEFAULT '',
  `CompanyName` char(20) NOT NULL,
  `Type` char(20) NOT NULL,
  `PricePerShare` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`StockSymbol`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Stock`
--

LOCK TABLES `Stock` WRITE;
/*!40000 ALTER TABLE `Stock` DISABLE KEYS */;
INSERT INTO `Stock` VALUES ('F','Ford','automotive',9.00),('GM','General Motors','automotive',34.23),('IBM','IBM','computer',91.41);
/*!40000 ALTER TABLE `Stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `StockOrder`
--

DROP TABLE IF EXISTS `StockOrder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `StockOrder` (
  `NumShares` int(11) DEFAULT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DateTime` datetime DEFAULT NULL,
  `Percentage` decimal(10,2) DEFAULT NULL,
  `PriceperShare` int(11) DEFAULT NULL,
  `PriceType` char(20) DEFAULT NULL,
  `OrderType` char(4) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `StockOrder`
--

LOCK TABLES `StockOrder` WRITE;
/*!40000 ALTER TABLE `StockOrder` DISABLE KEYS */;
INSERT INTO `StockOrder` VALUES (75,1,'2014-02-01 00:00:00',0.00,0,'Market','buy'),(10,2,'2014-02-01 00:00:00',0.10,0,'TrailingStop','sell');
/*!40000 ALTER TABLE `StockOrder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Trade`
--

DROP TABLE IF EXISTS `Trade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Trade` (
  `AccountId` int(11) NOT NULL DEFAULT '0',
  `BrokerId` int(11) NOT NULL DEFAULT '0',
  `OrderId` int(11) NOT NULL DEFAULT '0',
  `StockId` char(20) NOT NULL DEFAULT '',
  `TransactionId` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`AccountId`,`BrokerId`,`OrderId`,`StockId`,`TransactionId`),
  KEY `BrokerId` (`BrokerId`),
  KEY `OrderId` (`OrderId`),
  KEY `StockId` (`StockId`),
  KEY `TransactionId` (`TransactionId`),
  CONSTRAINT `Trade_ibfk_1` FOREIGN KEY (`AccountId`) REFERENCES `Account` (`ID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `Trade_ibfk_2` FOREIGN KEY (`BrokerId`) REFERENCES `Employee` (`ID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `Trade_ibfk_3` FOREIGN KEY (`OrderId`) REFERENCES `StockOrder` (`ID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `Trade_ibfk_4` FOREIGN KEY (`StockId`) REFERENCES `Stock` (`StockSymbol`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `Trade_ibfk_5` FOREIGN KEY (`TransactionId`) REFERENCES `Transaction` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Trade`
--

LOCK TABLES `Trade` WRITE;
/*!40000 ALTER TABLE `Trade` DISABLE KEYS */;
INSERT INTO `Trade` VALUES (1,1,1,'GM',1),(2,2,2,'IBM',2);
/*!40000 ALTER TABLE `Trade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Transaction`
--

DROP TABLE IF EXISTS `Transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Transaction` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Fee` int(11) DEFAULT NULL,
  `DateTime` datetime DEFAULT NULL,
  `PricePerShare` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Transaction`
--

LOCK TABLES `Transaction` WRITE;
/*!40000 ALTER TABLE `Transaction` DISABLE KEYS */;
INSERT INTO `Transaction` VALUES (1,50,'2015-01-01 00:00:00',100),(2,0,'2015-01-01 00:00:00',100);
/*!40000 ALTER TABLE `Transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hasStock`
--

DROP TABLE IF EXISTS `hasStock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hasStock` (
  `AccountId` int(11) NOT NULL DEFAULT '0',
  `StockSymbol` char(20) NOT NULL DEFAULT '',
  `NumShares` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`AccountId`,`StockSymbol`,`NumShares`),
  KEY `StockSymbol` (`StockSymbol`),
  CONSTRAINT `hasStock_ibfk_1` FOREIGN KEY (`AccountId`) REFERENCES `Account` (`ID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `hasStock_ibfk_2` FOREIGN KEY (`StockSymbol`) REFERENCES `Stock` (`StockSymbol`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hasStock`
--

LOCK TABLES `hasStock` WRITE;
/*!40000 ALTER TABLE `hasStock` DISABLE KEYS */;
INSERT INTO `hasStock` VALUES (1,'F',150),(1,'GM',250),(2,'IBM',50);
/*!40000 ALTER TABLE `hasStock` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-23 15:50:17
