-- MySQL dump 10.13  Distrib 5.6.19, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: myprofile
-- ------------------------------------------------------
-- Server version	5.6.19-0ubuntu0.14.04.1

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
-- Table structure for table `edu_fac_tb`
--

DROP TABLE IF EXISTS `edu_fac_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `edu_fac_tb` (
  `eduid` int(2) NOT NULL,
  `facultyid` int(2) NOT NULL,
  PRIMARY KEY (`eduid`,`facultyid`),
  KEY `facultyid` (`facultyid`),
  CONSTRAINT `edu_fac_tb_ibfk_2` FOREIGN KEY (`facultyid`) REFERENCES `faculty_tb` (`facultyid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `edu_fac_tb_ibfk_1` FOREIGN KEY (`eduid`) REFERENCES `education_tb` (`eduid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edu_fac_tb`
--

LOCK TABLES `edu_fac_tb` WRITE;
/*!40000 ALTER TABLE `edu_fac_tb` DISABLE KEYS */;
/*!40000 ALTER TABLE `edu_fac_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `education_history_tb`
--

DROP TABLE IF EXISTS `education_history_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `education_history_tb` (
  `userid` int(3) NOT NULL DEFAULT '0',
  `eduid` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userid`,`eduid`),
  KEY `eduid` (`eduid`),
  CONSTRAINT `education_history_tb_ibfk_2` FOREIGN KEY (`eduid`) REFERENCES `education_tb` (`eduid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `education_history_tb_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `member_tb` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `education_history_tb`
--

LOCK TABLES `education_history_tb` WRITE;
/*!40000 ALTER TABLE `education_history_tb` DISABLE KEYS */;
/*!40000 ALTER TABLE `education_history_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `education_tb`
--

DROP TABLE IF EXISTS `education_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `education_tb` (
  `eduid` int(2) NOT NULL AUTO_INCREMENT,
  `edu_name` varchar(30) NOT NULL,
  PRIMARY KEY (`eduid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `education_tb`
--

LOCK TABLES `education_tb` WRITE;
/*!40000 ALTER TABLE `education_tb` DISABLE KEYS */;
/*!40000 ALTER TABLE `education_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty_tb`
--

DROP TABLE IF EXISTS `faculty_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `faculty_tb` (
  `facultyid` int(2) NOT NULL AUTO_INCREMENT,
  `faculty_name` varchar(50) NOT NULL,
  PRIMARY KEY (`facultyid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty_tb`
--

LOCK TABLES `faculty_tb` WRITE;
/*!40000 ALTER TABLE `faculty_tb` DISABLE KEYS */;
/*!40000 ALTER TABLE `faculty_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `major_tb`
--

DROP TABLE IF EXISTS `major_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `major_tb` (
  `majorid` int(2) NOT NULL AUTO_INCREMENT,
  `major_name` varchar(30) DEFAULT NULL,
  `facultyid` int(2) DEFAULT NULL,
  PRIMARY KEY (`majorid`),
  KEY `facultyid` (`facultyid`),
  CONSTRAINT `major_tb_ibfk_1` FOREIGN KEY (`facultyid`) REFERENCES `faculty_tb` (`facultyid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major_tb`
--

LOCK TABLES `major_tb` WRITE;
/*!40000 ALTER TABLE `major_tb` DISABLE KEYS */;
/*!40000 ALTER TABLE `major_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_tb`
--

DROP TABLE IF EXISTS `member_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_tb` (
  `userid` int(3) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  `fullname` varchar(30) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `height` float(5,2) DEFAULT NULL,
  `weight` float(5,2) DEFAULT NULL,
  `blood_type` char(1) DEFAULT NULL,
  `hobby` text,
  `phone` int(10) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `detail_edu` text,
  `detail_fav` text,
  `detail_lsk` text,
  `picture_path` text,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_tb`
--

LOCK TABLES `member_tb` WRITE;
/*!40000 ALTER TABLE `member_tb` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_tb` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-09-09 15:21:11
