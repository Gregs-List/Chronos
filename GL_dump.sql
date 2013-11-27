-- MySQL dump 10.13  Distrib 5.5.32, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: GregsList
-- ------------------------------------------------------
-- Server version	5.5.32-0ubuntu0.12.04.1

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
-- Current Database: `GregsList`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `GregsList` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `GregsList`;

--
-- Table structure for table `BikeType`
--

DROP TABLE IF EXISTS `BikeType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BikeType` (
  `bikeTypeID` int(11) NOT NULL,
  `bikeType` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`bikeTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BikeType`
--

LOCK TABLES `BikeType` WRITE;
/*!40000 ALTER TABLE `BikeType` DISABLE KEYS */;
INSERT INTO `BikeType` VALUES (0,'other'),(1,'road'),(2,'city'),(3,'mountain'),(4,'cruiser'),(5,'hybrid'),(6,'unicycle'),(7,'electric');
/*!40000 ALTER TABLE `BikeType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Bikes`
--

DROP TABLE IF EXISTS `Bikes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Bikes` (
  `listingID` int(11) NOT NULL,
  `bikeTypeID` int(11) DEFAULT NULL,
  `make` varchar(20) DEFAULT NULL,
  `model` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`listingID`),
  KEY `bikeTypeID` (`bikeTypeID`),
  CONSTRAINT `Bikes_ibfk_1` FOREIGN KEY (`listingID`) REFERENCES `Listings` (`listingID`),
  CONSTRAINT `Bikes_ibfk_2` FOREIGN KEY (`bikeTypeID`) REFERENCES `BikeType` (`bikeTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bikes`
--

LOCK TABLES `Bikes` WRITE;
/*!40000 ALTER TABLE `Bikes` DISABLE KEYS */;
INSERT INTO `Bikes` VALUES (58,0,'Harley Davidson','Cruiser'),(86,4,'Schwinning!','Chuckles'),(87,4,'Schwinning!','Chuckles');
/*!40000 ALTER TABLE `Bikes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BookType`
--

DROP TABLE IF EXISTS `BookType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BookType` (
  `bookTypeID` int(11) NOT NULL,
  `bookType` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`bookTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BookType`
--

LOCK TABLES `BookType` WRITE;
/*!40000 ALTER TABLE `BookType` DISABLE KEYS */;
INSERT INTO `BookType` VALUES (0,'other'),(1,'textbook'),(2,'literature'),(3,'poetry'),(4,'reference'),(5,'nonfiction'),(6,'graphic novel');
/*!40000 ALTER TABLE `BookType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Books`
--

DROP TABLE IF EXISTS `Books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Books` (
  `listingID` int(11) NOT NULL,
  `bookTypeID` int(11) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  `isbn` varchar(20) DEFAULT NULL,
  `assignedCourse` varchar(40) DEFAULT NULL,
  `conditionID` int(11) DEFAULT NULL,
  PRIMARY KEY (`listingID`),
  KEY `bookTypeID` (`bookTypeID`),
  KEY `conditionID` (`conditionID`),
  CONSTRAINT `Books_ibfk_1` FOREIGN KEY (`listingID`) REFERENCES `Listings` (`listingID`),
  CONSTRAINT `Books_ibfk_2` FOREIGN KEY (`bookTypeID`) REFERENCES `BookType` (`bookTypeID`),
  CONSTRAINT `Books_ibfk_3` FOREIGN KEY (`conditionID`) REFERENCES `ConditionLookup` (`conditionID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Books`
--

LOCK TABLES `Books` WRITE;
/*!40000 ALTER TABLE `Books` DISABLE KEYS */;
INSERT INTO `Books` VALUES (56,2,'At First Sight','Nicholas Sparks','12345678','Personal Reading',4),(57,3,'The Notebook','Nicholas Sparks','87654321','Personal Reading',0),(63,5,'Game of Thrones 1','George Marin','11111111','Book Group',5),(64,5,'Game of Thrones 2','George Marin','22222222','Book Group',5),(74,1,'test','test','test','',1),(75,1,'test2','test2','test2','',1),(76,1,'test3','test3','test3','',1),(77,1,'test','test','test','',1),(78,1,'test','test','test','',1),(80,1,'test','test','test','',1),(81,1,'test','test','test','',1),(91,1,'cxzkvxzclkvj','as;ldkjf','1234567890','',1);
/*!40000 ALTER TABLE `Books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ConditionLookup`
--

DROP TABLE IF EXISTS `ConditionLookup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ConditionLookup` (
  `conditionID` int(11) NOT NULL,
  `itemCondition` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`conditionID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ConditionLookup`
--

LOCK TABLES `ConditionLookup` WRITE;
/*!40000 ALTER TABLE `ConditionLookup` DISABLE KEYS */;
INSERT INTO `ConditionLookup` VALUES (0,'unspecified'),(1,'new'),(2,'excellent'),(3,'light wear'),(4,'normal wear'),(5,'damaged');
/*!40000 ALTER TABLE `ConditionLookup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Electronics`
--

DROP TABLE IF EXISTS `Electronics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Electronics` (
  `listingID` int(11) NOT NULL,
  `electronicsTypeID` int(11) DEFAULT NULL,
  `make` varchar(20) DEFAULT NULL,
  `model` varchar(20) DEFAULT NULL,
  `size` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`listingID`),
  KEY `electronicsTypeID` (`electronicsTypeID`),
  CONSTRAINT `Electronics_ibfk_1` FOREIGN KEY (`listingID`) REFERENCES `Listings` (`listingID`),
  CONSTRAINT `Electronics_ibfk_2` FOREIGN KEY (`electronicsTypeID`) REFERENCES `ElectronicsType` (`electronicsTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Electronics`
--

LOCK TABLES `Electronics` WRITE;
/*!40000 ALTER TABLE `Electronics` DISABLE KEYS */;
INSERT INTO `Electronics` VALUES (55,3,'Apple','iPhone 5s','small'),(59,1,'LG','High-Def Model','large'),(60,2,'Apple','MacBook pro','15 inch'),(84,0,'','',NULL);
/*!40000 ALTER TABLE `Electronics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ElectronicsType`
--

DROP TABLE IF EXISTS `ElectronicsType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ElectronicsType` (
  `electronicsTypeID` int(11) NOT NULL,
  `electronicsType` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`electronicsTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ElectronicsType`
--

LOCK TABLES `ElectronicsType` WRITE;
/*!40000 ALTER TABLE `ElectronicsType` DISABLE KEYS */;
INSERT INTO `ElectronicsType` VALUES (0,'other'),(1,'TV'),(2,'computer'),(3,'phone'),(4,'speakers'),(5,'iPod');
/*!40000 ALTER TABLE `ElectronicsType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Furniture`
--

DROP TABLE IF EXISTS `Furniture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Furniture` (
  `listingID` int(11) NOT NULL,
  `furnitureTypeID` int(11) DEFAULT NULL,
  `conditionID` int(11) DEFAULT NULL,
  PRIMARY KEY (`listingID`),
  KEY `furnitureTypeID` (`furnitureTypeID`),
  KEY `conditionID` (`conditionID`),
  CONSTRAINT `Furniture_ibfk_1` FOREIGN KEY (`listingID`) REFERENCES `Listings` (`listingID`),
  CONSTRAINT `Furniture_ibfk_2` FOREIGN KEY (`furnitureTypeID`) REFERENCES `FurnitureType` (`furnitureTypeID`),
  CONSTRAINT `Furniture_ibfk_3` FOREIGN KEY (`conditionID`) REFERENCES `ConditionLookup` (`conditionID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Furniture`
--

LOCK TABLES `Furniture` WRITE;
/*!40000 ALTER TABLE `Furniture` DISABLE KEYS */;
/*!40000 ALTER TABLE `Furniture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FurnitureType`
--

DROP TABLE IF EXISTS `FurnitureType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FurnitureType` (
  `furnitureTypeID` int(11) NOT NULL,
  `furnitureType` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`furnitureTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FurnitureType`
--

LOCK TABLES `FurnitureType` WRITE;
/*!40000 ALTER TABLE `FurnitureType` DISABLE KEYS */;
INSERT INTO `FurnitureType` VALUES (0,'other'),(1,'couch'),(2,'bed'),(3,'chair'),(4,'table'),(5,'futon'),(6,'shelves');
/*!40000 ALTER TABLE `FurnitureType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Listings`
--

DROP TABLE IF EXISTS `Listings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Listings` (
  `listingID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) DEFAULT NULL,
  `title` varchar(30) DEFAULT NULL,
  `dateListed` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `category` varchar(20) DEFAULT NULL,
  `price` decimal(7,2) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`listingID`),
  KEY `userID` (`userID`),
  CONSTRAINT `Listings_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `Users` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Listings`
--

LOCK TABLES `Listings` WRITE;
/*!40000 ALTER TABLE `Listings` DISABLE KEYS */;
INSERT INTO `Listings` VALUES (1,9,'Dildo','2013-11-04 06:02:27','Dildos',NULL,'I love dildos. - Bobby'),(2,9,'hey','2013-11-04 06:18:54','up',NULL,'dude'),(3,13,'If','2013-11-04 06:27:22','Works',NULL,'then what'),(4,10,'','2013-11-04 06:58:30','',NULL,''),(5,14,'','2013-11-04 07:01:44','',NULL,''),(6,16,'','2013-11-04 07:05:04','',NULL,''),(7,16,'','2013-11-04 07:08:08','',NULL,''),(8,9,'','2013-11-04 07:09:24','',NULL,''),(9,9,'I','2013-11-04 07:11:45','a boobs',NULL,'guy'),(10,16,'See','2013-11-04 07:12:54','It',NULL,'work'),(11,8,'','2013-11-04 19:23:19','',NULL,''),(13,13,'','2013-11-04 19:28:07','',NULL,''),(14,13,'Test','2013-11-04 19:31:47','Test',NULL,'Test'),(15,21,'Thingy','2013-11-04 19:44:55','something',NULL,'A very nice thingy'),(16,22,'This New very cool ITEMZ','2013-11-04 21:04:49','phone',NULL,'shiny phone'),(17,1,'Listing','2013-11-05 17:01:53','category',NULL,'fnskdjfnsjkdhvakjsfbkja'),(18,26,'nothing','2013-11-06 06:36:41','nothing',NULL,'nothing'),(19,26,'adfasdf','2013-11-06 06:37:07','adsfadsf',NULL,'asdfasdf'),(20,26,'trial','2013-11-06 06:37:32','',NULL,''),(21,26,'test','2013-11-06 06:38:12','test',NULL,'test'),(22,26,'test','2013-11-06 06:38:24','test',NULL,'test'),(23,27,'first','2013-11-06 06:40:41','first',NULL,'first'),(24,27,'first','2013-11-06 06:42:45','first',NULL,'first'),(25,27,'one two','2013-11-06 06:43:53','one',NULL,'one'),(26,27,'one','2013-11-06 06:44:09','one',NULL,'one'),(27,27,'two','2013-11-06 06:44:28','two one',NULL,'two'),(28,27,'three','2013-11-06 06:44:46','three',NULL,'three four'),(29,27,' four','2013-11-06 06:45:11',' four',NULL,' four'),(30,27,'lets test some words','2013-11-06 06:46:04','no',NULL,'no'),(31,27,'/','2013-11-06 06:47:07','/',NULL,'/'),(32,27,'1','2013-11-06 06:50:07','',NULL,''),(33,27,'1','2013-11-06 06:50:09','',NULL,''),(34,27,'1','2013-11-06 06:50:11','',NULL,''),(35,27,'1','2013-11-06 06:50:14','',NULL,''),(36,27,'1','2013-11-06 06:50:16','',NULL,''),(37,27,'1','2013-11-06 06:50:18','',NULL,''),(38,27,'1','2013-11-06 06:50:20','',NULL,''),(39,27,'1','2013-11-06 06:50:23','',NULL,''),(40,27,'1','2013-11-06 06:50:25','',NULL,''),(41,27,'1','2013-11-06 06:50:28','',NULL,''),(42,27,'1','2013-11-06 06:50:30','',NULL,''),(43,27,'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa','2013-11-06 06:51:10','',NULL,''),(44,27,'http://ec2-50-112-191-198.us-w','2013-11-06 06:52:48','',NULL,''),(45,27,'Greg’s List is ','2013-11-06 06:53:30','',NULL,''),(46,27,'’','2013-11-06 06:54:33','',NULL,''),(47,29,'Test','2013-11-06 21:33:34','books',NULL,'i dont like this book'),(48,21,'asdf','2013-11-06 21:35:41','',NULL,''),(49,21,'as','2013-11-06 21:35:57','',NULL,''),(50,21,'','2013-11-06 21:36:02','',NULL,''),(51,21,'','2013-11-06 21:36:08','as',NULL,''),(52,21,'','2013-11-06 21:36:13','',NULL,'as'),(53,29,'cant','2013-11-06 21:41:07','',NULL,''),(54,30,'kkkkkkkkkkkkkkkkkkkkkkkkkkkkkk','2013-11-07 02:00:16','kkkkkkkkkkkkkkkkkkkk',NULL,'kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk'),(55,1,'Test Title 1','2013-11-18 20:20:32','Electronics',NULL,'Description 1'),(56,36,'Test Title 2','2013-11-18 20:21:51','Books',NULL,'Description 2'),(57,36,'Test Title 3','2013-11-18 20:22:03','Books',NULL,'Description 3'),(58,1,'Test Title 4','2013-11-18 20:22:23','Bikes',NULL,'Description 4'),(59,1,'Test Title 5','2013-11-18 20:22:38','Electronics',NULL,'Description 5'),(60,1,'Test Title 6','2013-11-18 20:22:51','Electronics',NULL,'Description 6'),(61,1,'Test Title 7','2013-11-18 20:23:07','Meetups',NULL,'Description 7'),(62,1,'Test Title 8','2013-11-18 20:23:19','Meetups',NULL,'Description 8'),(63,36,'Test Title 9','2013-11-18 20:23:38','Books',NULL,'Description 9'),(64,36,'Test Title 10','2013-11-18 20:23:53','Books',NULL,'Description 10'),(74,13,'test','2013-11-22 22:38:46','Books',100.00,''),(75,13,'test2','2013-11-22 22:40:21','Books',102.00,''),(76,13,'test3','2013-11-22 22:46:35','Books',10000.00,'test description'),(77,13,'test4','2013-11-22 22:50:33','Books',10000.00,'test description'),(78,13,'test','2013-11-22 22:50:54','Books',10000.00,'					Add an extra description here.\r\n				'),(80,13,'Test','2013-11-22 23:15:17','Books',1000.00,'test					Add an extra description here.\r\n				'),(81,13,'Test','2013-11-22 23:17:15','Books',1000.00,'test					Add an extra description here.\r\n				'),(84,21,'','2013-11-25 19:25:21','Electronics',0.00,''),(85,21,'','2013-11-25 19:26:28','Miscellaneous',0.00,''),(86,10,'Sweet Ride','2013-11-25 19:47:45','Bikes',123.00,'So I have this awesome book--totally not broken--but I want to buy this other thing, so please buy it from me for my completely reasonable asking price.'),(87,10,'Sweet Ride','2013-11-25 19:48:14','Bikes',999.99,'So I have this awesome book--totally not broken--but I want to buy this other thing, so please buy it from me for my completely reasonable asking price.'),(89,42,'adfasdf','2013-11-26 20:08:37','Miscellaneous',999.00,'adfadfasdfasdf'),(90,42,'My new very cool item','2013-11-26 23:01:29','Miscellaneous',1.00,'adfa;ldsfkja'),(91,43,'x.,cm','2013-11-27 20:53:27','Books',123.00,'jkhkjhkjh');
/*!40000 ALTER TABLE `Listings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MeetupType`
--

DROP TABLE IF EXISTS `MeetupType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MeetupType` (
  `meetupTypeID` int(11) NOT NULL,
  `meetupType` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`meetupTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MeetupType`
--

LOCK TABLES `MeetupType` WRITE;
/*!40000 ALTER TABLE `MeetupType` DISABLE KEYS */;
INSERT INTO `MeetupType` VALUES (0,'other'),(1,'study group'),(2,'social'),(3,'group meeting');
/*!40000 ALTER TABLE `MeetupType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Meetups`
--

DROP TABLE IF EXISTS `Meetups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Meetups` (
  `listingID` int(11) NOT NULL,
  `meetupTypeID` int(11) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  PRIMARY KEY (`listingID`),
  KEY `meetupTypeID` (`meetupTypeID`),
  CONSTRAINT `Meetups_ibfk_1` FOREIGN KEY (`listingID`) REFERENCES `Listings` (`listingID`),
  CONSTRAINT `Meetups_ibfk_2` FOREIGN KEY (`meetupTypeID`) REFERENCES `MeetupType` (`meetupTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Meetups`
--

LOCK TABLES `Meetups` WRITE;
/*!40000 ALTER TABLE `Meetups` DISABLE KEYS */;
INSERT INTO `Meetups` VALUES (61,1,'Caruth','2013-11-25','05:30:00'),(62,3,'Einsteins','2013-11-23','09:00:00');
/*!40000 ALTER TABLE `Meetups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Miscellaneous`
--

DROP TABLE IF EXISTS `Miscellaneous`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Miscellaneous` (
  `listingID` int(11) NOT NULL,
  `itemName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`listingID`),
  CONSTRAINT `Miscellaneous_ibfk_1` FOREIGN KEY (`listingID`) REFERENCES `Listings` (`listingID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Miscellaneous`
--

LOCK TABLES `Miscellaneous` WRITE;
/*!40000 ALTER TABLE `Miscellaneous` DISABLE KEYS */;
INSERT INTO `Miscellaneous` VALUES (1,'This id a Dildo'),(2,'whats'),(3,'This'),(4,''),(5,''),(6,''),(7,''),(8,''),(9,'am'),(10,'looks'),(11,''),(13,''),(14,''),(15,''),(16,''),(17,''),(18,''),(19,''),(20,''),(21,''),(22,''),(23,''),(24,''),(25,''),(26,''),(27,''),(28,''),(29,''),(30,''),(31,''),(32,''),(33,''),(34,''),(35,''),(36,''),(37,''),(38,''),(39,''),(40,''),(41,''),(42,''),(43,''),(44,''),(45,''),(46,''),(47,''),(48,''),(49,''),(50,''),(51,''),(52,''),(53,''),(54,''),(85,NULL),(89,NULL),(90,NULL);
/*!40000 ALTER TABLE `Miscellaneous` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Photos`
--

DROP TABLE IF EXISTS `Photos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Photos` (
  `listingID` int(11) DEFAULT NULL,
  `photoID` int(11) NOT NULL,
  `photoURL` varchar(20) NOT NULL,
  PRIMARY KEY (`photoID`),
  UNIQUE KEY `photoURL` (`photoURL`),
  KEY `listingID` (`listingID`),
  CONSTRAINT `Photos_ibfk_1` FOREIGN KEY (`listingID`) REFERENCES `Listings` (`listingID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Photos`
--

LOCK TABLES `Photos` WRITE;
/*!40000 ALTER TABLE `Photos` DISABLE KEYS */;
INSERT INTO `Photos` VALUES (1,0,'notaphoto.jpg');
/*!40000 ALTER TABLE `Photos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Rides`
--

DROP TABLE IF EXISTS `Rides`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Rides` (
  `listingID` int(11) NOT NULL,
  `leavingFrom` varchar(100) DEFAULT NULL,
  `goingTo` varchar(100) DEFAULT NULL,
  `departureDate` date DEFAULT NULL,
  `departureTime` time DEFAULT NULL,
  `returnDate` date DEFAULT NULL,
  `returnTime` time DEFAULT NULL,
  PRIMARY KEY (`listingID`),
  CONSTRAINT `Rides_ibfk_1` FOREIGN KEY (`listingID`) REFERENCES `Listings` (`listingID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Rides`
--

LOCK TABLES `Rides` WRITE;
/*!40000 ALTER TABLE `Rides` DISABLE KEYS */;
/*!40000 ALTER TABLE `Rides` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(40) NOT NULL,
  `pword` varchar(20) DEFAULT NULL,
  `fullName` varchar(50) DEFAULT NULL,
  `feedbackRating` int(11) DEFAULT NULL,
  `location` varchar(20) DEFAULT NULL,
  `phoneNumber` varchar(20) DEFAULT NULL,
  `admin` bit(1) DEFAULT b'0',
  PRIMARY KEY (`userID`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,'adoyle@smu.edu','test','Amanda Doyle',5,'McElvaney','2148087313','\0'),(8,'gwalters@smu.edu','ItsMyList','Greg Walters',NULL,NULL,'9728367014','\0'),(9,'cdewey@smu.edu','password','Carter',NULL,NULL,NULL,'\0'),(10,'gregwalters@verizon.net','password','Greggy',NULL,NULL,'9728367014','\0'),(11,'we@verizon.com','qwertyui','j',NULL,NULL,NULL,'\0'),(12,'gregwwalters@gmail.com','password','Gregory',NULL,NULL,NULL,'\0'),(13,'rsantoski@smu.edu','password','Robert',NULL,NULL,'2','\0'),(14,'testman@smu.edu','password','Testman',NULL,NULL,'7135555555555555','\0'),(15,'jdoe@lyle.smu.edu','doeadeere','John',NULL,NULL,'edfhjkdflkjhdfsgkjlh','\0'),(16,'testmanjr@smu.edu','password','TestmanJr',NULL,NULL,NULL,'\0'),(17,'Dick@smu.edu','Booby123','Suck',NULL,NULL,NULL,'\0'),(18,'e@smu.edu','password','q',NULL,NULL,NULL,'\0'),(20,'test@smu.edu','password','test',NULL,NULL,NULL,'\0'),(21,'ckeith@smu.edu','hpcrew09','Cameron',NULL,NULL,'214-393-9984','\0'),(22,'fakemail@smu.edu','qqqqqqqq','first',NULL,NULL,'adsfasdf','\0'),(23,'mspringer@smu.edu','helloWolrd','Tyler',NULL,NULL,NULL,'\0'),(24,'asda@smu.edu','test','Amanda',NULL,NULL,NULL,'\0'),(26,'fakemail@live.com','13334444','Joe',NULL,NULL,'1-111-1111','\0'),(27,'letstest@gmail.com','13334444','test',NULL,NULL,'15802430000','\0'),(28,'tester@tcu.edu','password','Tester',NULL,NULL,NULL,'\0'),(29,'gcockrum@smu.edu','happypants','Guy',NULL,NULL,'heee','\0'),(30,'tesn@smu.edu','password','test',NULL,NULL,NULL,'\0'),(32,'mwinslow@smu.edu','gregslist','Morgan',NULL,NULL,NULL,'\0'),(36,'swoodruff@smu.edu','12345678','Sarah Woodruff',NULL,NULL,NULL,'\0'),(37,'aslyman@smu.edu','testing1','Abby Slyman',NULL,NULL,NULL,'\0'),(38,'cdewey@smu.edh','password','Carter Dewey',NULL,NULL,NULL,'\0'),(39,'kdonahoo@smu.edu','heysexy9','Kevin Donahoo',NULL,NULL,NULL,'\0'),(40,'notanemail@smu.edu','password','Gregor Watlers',NULL,NULL,NULL,'\0'),(41,'testt@smu.edu','password','test account',NULL,NULL,NULL,'\0'),(42,'rcstewart@smu.edu','12345678','Robert',NULL,NULL,NULL,'\0'),(43,'vkohli@smu.edu','greenguy123','Vipul',NULL,NULL,NULL,'\0');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-11-27 22:55:33
