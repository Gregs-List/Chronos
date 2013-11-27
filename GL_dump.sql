-- MySQL dump 10.13  Distrib 5.5.34, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: GregsList
-- ------------------------------------------------------
-- Server version	5.5.34-0ubuntu0.12.04.1

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
  KEY `fk_bikeType` (`bikeTypeID`),
  CONSTRAINT `fk_bikeListing` FOREIGN KEY (`listingID`) REFERENCES `Listings` (`listingID`) ON DELETE CASCADE,
  CONSTRAINT `fk_bikeType` FOREIGN KEY (`bikeTypeID`) REFERENCES `BikeType` (`bikeTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bikes`
--

LOCK TABLES `Bikes` WRITE;
/*!40000 ALTER TABLE `Bikes` DISABLE KEYS */;
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
  KEY `fk_bookType` (`bookTypeID`),
  KEY `fk_bookCondition` (`conditionID`),
  CONSTRAINT `fk_bookListing` FOREIGN KEY (`listingID`) REFERENCES `Listings` (`listingID`) ON DELETE CASCADE,
  CONSTRAINT `fk_bookType` FOREIGN KEY (`bookTypeID`) REFERENCES `BookType` (`bookTypeID`),
  CONSTRAINT `fk_bookCondition` FOREIGN KEY (`conditionID`) REFERENCES `ConditionLookup` (`conditionID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Books`
--

LOCK TABLES `Books` WRITE;
/*!40000 ALTER TABLE `Books` DISABLE KEYS */;
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
  KEY `fk_elecType` (`electronicsTypeID`),
  CONSTRAINT `fk_elecListing` FOREIGN KEY (`listingID`) REFERENCES `Listings` (`listingID`) ON DELETE CASCADE,
  CONSTRAINT `fk_elecType` FOREIGN KEY (`electronicsTypeID`) REFERENCES `ElectronicsType` (`electronicsTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Electronics`
--

LOCK TABLES `Electronics` WRITE;
/*!40000 ALTER TABLE `Electronics` DISABLE KEYS */;
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
  KEY `fk_furnType` (`furnitureTypeID`),
  KEY `fk_furnCondition` (`conditionID`),
  CONSTRAINT `fk_furnListing` FOREIGN KEY (`listingID`) REFERENCES `Listings` (`listingID`) ON DELETE CASCADE,
  CONSTRAINT `fk_furnType` FOREIGN KEY (`furnitureTypeID`) REFERENCES `FurnitureType` (`furnitureTypeID`),
  CONSTRAINT `fk_furnCondition` FOREIGN KEY (`conditionID`) REFERENCES `ConditionLookup` (`conditionID`)
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
  KEY `fk_user` (`userID`),
  CONSTRAINT `fk_user` FOREIGN KEY (`userID`) REFERENCES `Users` (`userID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Listings`
--

LOCK TABLES `Listings` WRITE;
/*!40000 ALTER TABLE `Listings` DISABLE KEYS */;
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
  KEY `fk_meetType` (`meetupTypeID`),
  CONSTRAINT `fk_meetListing` FOREIGN KEY (`listingID`) REFERENCES `Listings` (`listingID`) ON DELETE CASCADE,
  CONSTRAINT `fk_meetType` FOREIGN KEY (`meetupTypeID`) REFERENCES `MeetupType` (`meetupTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Meetups`
--

LOCK TABLES `Meetups` WRITE;
/*!40000 ALTER TABLE `Meetups` DISABLE KEYS */;
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
  CONSTRAINT `fk_miscListing` FOREIGN KEY (`listingID`) REFERENCES `Listings` (`listingID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Miscellaneous`
--

LOCK TABLES `Miscellaneous` WRITE;
/*!40000 ALTER TABLE `Miscellaneous` DISABLE KEYS */;
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
  KEY `fk_photoListing` (`listingID`),
  CONSTRAINT `fk_photoListing` FOREIGN KEY (`listingID`) REFERENCES `Listings` (`listingID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Photos`
--

LOCK TABLES `Photos` WRITE;
/*!40000 ALTER TABLE `Photos` DISABLE KEYS */;
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
  CONSTRAINT `fk_rideListing` FOREIGN KEY (`listingID`) REFERENCES `Listings` (`listingID`) ON DELETE CASCADE
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
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,'adoyle@smu.edu','test','Amanda Doyle',5,'McElvaney','2148087313','\0'),(8,'gwalters@smu.edu','ItsMyList','Greg Walters',NULL,NULL,'9728367014','\0'),(9,'cdewey@smu.edu','password','Carter',NULL,NULL,NULL,'\0'),(10,'gregwalters@verizon.net','password','Greggy',NULL,NULL,'9728367014','\0'),(11,'we@verizon.com','qwertyui','j',NULL,NULL,NULL,'\0'),(12,'gregwwalters@gmail.com','password','Gregory',NULL,NULL,NULL,'\0'),(13,'rsantoski@smu.edu','password','Robert',NULL,NULL,'2','\0'),(14,'testman@smu.edu','password','Testman',NULL,NULL,'7135555555555555','\0'),(15,'jdoe@lyle.smu.edu','doeadeere','John',NULL,NULL,'edfhjkdflkjhdfsgkjlh','\0'),(16,'testmanjr@smu.edu','password','TestmanJr',NULL,NULL,NULL,'\0'),(17,'Dick@smu.edu','Booby123','Suck',NULL,NULL,NULL,'\0'),(18,'e@smu.edu','password','q',NULL,NULL,NULL,'\0'),(20,'test@smu.edu','password','test',NULL,NULL,NULL,'\0'),(21,'ckeith@smu.edu','hpcrew09','Cameron',NULL,NULL,'214-393-9984','\0'),(22,'fakemail@smu.edu','qqqqqqqq','first',NULL,NULL,'adsfasdf','\0'),(23,'mspringer@smu.edu','helloWolrd','Tyler',NULL,NULL,NULL,'\0'),(24,'asda@smu.edu','test','Amanda',NULL,NULL,NULL,'\0'),(26,'fakemail@live.com','13334444','Joe',NULL,NULL,'1-111-1111','\0'),(27,'letstest@gmail.com','13334444','test',NULL,NULL,'15802430000','\0'),(28,'tester@tcu.edu','password','Tester',NULL,NULL,NULL,'\0'),(29,'gcockrum@smu.edu','happypants','Guy',NULL,NULL,'heee','\0'),(30,'tesn@smu.edu','password','test',NULL,NULL,NULL,'\0'),(32,'mwinslow@smu.edu','gregslist','Morgan',NULL,NULL,NULL,'\0'),(36,'swoodruff@smu.edu','12345678','Sarah Woodruff',NULL,NULL,NULL,'\0'),(37,'aslyman@smu.edu','testing1','Abby Slyman',NULL,NULL,NULL,'\0'),(38,'cdewey@smu.edh','password','Carter Dewey',NULL,NULL,NULL,'\0'),(39,'kdonahoo@smu.edu','heysexy9','Kevin Donahoo',NULL,NULL,NULL,'\0'),(40,'notanemail@smu.edu','password','Gregor Watlers',NULL,NULL,NULL,'\0'),(41,'testt@smu.edu','password','test account',NULL,NULL,NULL,'\0'),(42,'rcstewart@smu.edu','12345678','Robert',NULL,NULL,NULL,'\0');
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

-- Dump completed on 2013-11-27 16:51:59
