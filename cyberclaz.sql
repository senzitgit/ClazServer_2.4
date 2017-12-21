CREATE DATABASE  IF NOT EXISTS `cyberclaz` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `cyberclaz`;
-- MySQL dump 10.13  Distrib 5.5.50, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: cyberclaz
-- ------------------------------------------------------
-- Server version	5.5.50-0ubuntu0.14.04.1

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
-- Table structure for table `addassignment`
--

DROP TABLE IF EXISTS `addassignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `addassignment` (
  `ASSIGNMENTID` int(11) NOT NULL,
  `SUBJECTID` varchar(255) DEFAULT NULL,
  `TOPIC` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ASSIGNMENTID`),
  KEY `FKC78B6A2ED1AB1F8E` (`SUBJECTID`),
  CONSTRAINT `FKC78B6A2ED1AB1F8E` FOREIGN KEY (`SUBJECTID`) REFERENCES `subject` (`SUBJECTID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addassignment`
--

LOCK TABLES `addassignment` WRITE;
/*!40000 ALTER TABLE `addassignment` DISABLE KEYS */;
INSERT INTO `addassignment` VALUES (21,'01','Trigonometry'),(22,'03','Organic Chemistry'),(23,'01','gsfdg'),(24,'03','dfhdfh'),(25,'02','gfjfgj'),(26,'02','fdhdhdh'),(27,'01','dfhdfh'),(28,'01','dfhdfh'),(29,'01','dfhdfh'),(30,'01','dfhdfh'),(31,'01','dfhdfhhdh'),(32,'04','rtur'),(33,'02','dfhdfh'),(34,'08','Digital Signal Processing'),(35,'01','DB Testing'),(36,'01','test1'),(37,'01','test2'),(38,'01','new entry'),(39,'01','test123'),(40,'02','lines and curves'),(41,'02','lines '),(46,'02','wires'),(47,'01','asd sg'),(48,'01','@fdg'),(51,'07','mechanics'),(53,'10','12345678901234567894567890123456789999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999'),(54,'09','abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefbjkkklllllllllllllllllllllllllll;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;'),(55,'10','abcdefghijklmnopqrstuvwxyzabc'),(56,'10','10255555555555555555'),(57,'04','topview'),(58,'01','dfdf'),(59,'01','gjhgh'),(60,'02','wereer'),(61,'02','f'),(62,'01','xc'),(63,'02','df'),(64,'01','sd'),(65,'02','ss'),(66,'01','gh'),(67,'01','csd'),(68,'01','x'),(69,'01','xxx'),(70,'03','xxx'),(71,'03','ss'),(72,'02','kl'),(73,'02','sss'),(74,'04','x'),(75,'02','zzz'),(76,'05','sd'),(77,'02','xx'),(78,'03','j'),(79,'02','tf'),(80,'03','dc'),(81,'03','sfsf'),(82,'01','dfd'),(83,'06','kjhkyhj'),(84,'06','kjhkyhjhkhg'),(85,'02','dddddddddddd'),(86,'03','cszf'),(87,'03','cbgg'),(88,'01','ccbfg'),(89,'03','xvvv'),(90,'01','cfhfh'),(91,'04','njnjh'),(92,'02','gdgff'),(93,'02','sfff'),(94,'02','wwwwwwwwwwwwwww'),(95,'01','1234'),(96,'01','algebra'),(97,'04','freehand sketching'),(98,'10','Basics of Workshop'),(99,'03','hgeuhw'),(100,'01','bhghj'),(101,'01','wetee');
/*!40000 ALTER TABLE `addassignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assignedtask`
--

DROP TABLE IF EXISTS `assignedtask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assignedtask` (
  `TASKID` int(11) NOT NULL,
  `USERID` varchar(255) DEFAULT NULL,
  `SUBJECTID` varchar(255) DEFAULT NULL,
  `ASSIGNMENTTOPIC` varchar(255) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`TASKID`),
  KEY `FK1CD19453750868B6` (`USERID`),
  KEY `FK1CD19453D1AB1F8E` (`SUBJECTID`),
  CONSTRAINT `FK1CD19453750868B6` FOREIGN KEY (`USERID`) REFERENCES `user` (`USERID`),
  CONSTRAINT `FK1CD19453D1AB1F8E` FOREIGN KEY (`SUBJECTID`) REFERENCES `subject` (`SUBJECTID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignedtask`
--

LOCK TABLES `assignedtask` WRITE;
/*!40000 ALTER TABLE `assignedtask` DISABLE KEYS */;
/*!40000 ALTER TABLE `assignedtask` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attachment`
--

DROP TABLE IF EXISTS `attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attachment` (
  `ATTACHMENTID` int(11) NOT NULL,
  `CLASSEVENTDETAILID` int(11) DEFAULT NULL,
  `ATTACHMENTNAME` varchar(255) DEFAULT NULL,
  `ATTACHMENTLINK` varchar(255) DEFAULT NULL,
  `ATTACHMENTTYPE` varchar(255) DEFAULT NULL,
  `CREATEDON` datetime DEFAULT NULL,
  `TYPE` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ATTACHMENTID`),
  KEY `FKA7E14523B98D186` (`CLASSEVENTDETAILID`),
  CONSTRAINT `FKA7E14523B98D186` FOREIGN KEY (`CLASSEVENTDETAILID`) REFERENCES `classeventdetail` (`CLASSEVENTDETAILID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachment`
--

LOCK TABLES `attachment` WRITE;
/*!40000 ALTER TABLE `attachment` DISABLE KEYS */;
INSERT INTO `attachment` VALUES (1,9,'drawing','1498016156875test.png','png','2017-01-01 00:15:28',1),(2,10,'drawing','1498102329018test.png','png','2017-01-01 00:17:48',1),(3,10,'drwa002','1498102397129test.png','png','2017-01-01 00:19:01',1),(4,10,'Eigen_Img','http://192.168.0.117:8080/ATTACHMENT/state-space-analysis-eign-values-and-eign-vectors-30-638.jpg','jpg','2017-06-22 09:07:55',0),(5,10,'drawing','http://192.168.0.117:8080/APPDATA/Recorder/10/1498102329018test.png','png','2017-06-22 09:07:55',0),(6,10,'drwa002','http://192.168.0.117:8080/APPDATA/Recorder/10/1498102397129test.png','png','2017-06-22 09:07:55',0),(7,10,'Eigen_Img','http://192.168.0.117:8080/ATTACHMENT/state-space-analysis-eign-values-and-eign-vectors-30-638.jpg','jpg','2017-06-22 09:07:55',0),(8,11,'draw001','1498188826949test.png','png','2017-01-01 00:28:06',1),(9,11,'draw002','1498188900576test.png','png','2017-01-01 00:29:21',1),(10,11,'Matrix_Pdf','http://192.168.0.117:8080/ATTACHMENT/Characteristic_Equation.pdf','pdf','2017-06-23 09:06:04',0),(11,11,'draw001','http://192.168.0.117:8080/APPDATA/Recorder/11/1498188826949test.png','png','2017-06-23 09:06:04',0),(12,11,'draw002','http://192.168.0.117:8080/APPDATA/Recorder/11/1498188900576test.png','png','2017-06-23 09:06:04',0),(13,11,'Eigen_Img','http://192.168.0.117:8080/ATTACHMENT/state-space-analysis-eign-values-and-eign-vectors-30-638.jpg','jpg','2017-06-23 09:06:04',0),(14,14,'Eigen_Img','http://192.168.0.117:8080/ATTACHMENT/state-space-analysis-eign-values-and-eign-vectors-30-638.jpg','jpg','2017-07-28 14:29:41',0),(15,32,'CyberClaz','http://null:null/ATTACHMENT/Doc1_arch-1.jpg','jpg','2017-12-08 00:11:19',0),(16,37,'CyberClaz','http://null:null/ATTACHMENT/Doc1_arch-1.jpg','jpg','2017-12-08 10:19:29',0),(17,40,'CyberClaz','http://null:null/ATTACHMENT/Doc1_arch-1.jpg','jpg','2017-08-26 09:58:02',0),(18,40,'CyberClaz','http://null:null/ATTACHMENT/Doc1_arch-1.jpg','jpg','2017-08-26 09:58:02',0),(19,40,'CyberClaz','http://null:null/ATTACHMENT/Doc1_arch-1.jpg','jpg','2017-08-26 09:58:02',0),(20,40,'CyberClaz','http://null:null/ATTACHMENT/Doc1_arch-1.jpg','jpg','2017-08-26 09:58:02',0),(21,42,'CyberClaz','http://null:null/ATTACHMENT/Doc1_arch-1.jpg','jpg','2017-08-26 10:08:05',0),(22,42,'Eigen_Img','http://192.168.0.117:8080/ATTACHMENT/state-space-analysis-eign-values-and-eign-vectors-30-638.jpg','jpg','2017-08-26 10:08:06',0),(23,46,'arch','http://192.168.10.50:8080/ATTACHMENT/Rebbon Archi.jpg','jpg','2017-08-26 13:17:39',0),(24,46,'Matrix_Pdf','http://192.168.10.50:8080/ATTACHMENT/Characteristic_Equation.pdf','pdf','2017-08-26 13:17:39',0),(25,46,'Matrix_Pdf','http://192.168.10.50:8080/ATTACHMENT/Characteristic_Equation.pdf','pdf','2017-08-26 13:17:39',0),(26,46,'Eigen_Img','http://192.168.10.50:8080/ATTACHMENT/state-space-analysis-eign-values-and-eign-vectors-30-638.jpg','jpg','2017-08-26 13:17:39',0),(27,47,'CyberClaz','http://192.168.10.50:8080/ATTACHMENT/Doc1_arch-1.jpg','jpg','2017-08-26 13:23:30',0),(28,53,'CyberClaz','http://192.168.10.50:8080/ATTACHMENT/Doc1_arch-1.jpg','jpg','2017-08-28 07:21:58',0),(29,53,'Eigen_Img','http://192.168.10.50:8080/ATTACHMENT/state-space-analysis-eign-values-and-eign-vectors-30-638.jpg','jpg','2017-08-28 07:21:58',0);
/*!40000 ALTER TABLE `attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attendance` (
  `USERID` varchar(255) NOT NULL,
  `ATTENDEDSESSION` int(11) DEFAULT NULL,
  `TOTALNUMBEROFSESSION` int(11) DEFAULT NULL,
  `COURSESUBJECTID` int(11) DEFAULT NULL,
  `CURRENTMONTH` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`USERID`),
  KEY `FK8E01CEE9750868B6` (`USERID`),
  KEY `FK8E01CEE9ED6A0F8` (`COURSESUBJECTID`),
  CONSTRAINT `FK8E01CEE9750868B6` FOREIGN KEY (`USERID`) REFERENCES `user` (`USERID`),
  CONSTRAINT `FK8E01CEE9ED6A0F8` FOREIGN KEY (`COURSESUBJECTID`) REFERENCES `coursesubject` (`COURSESUBJECTID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendancereport`
--

DROP TABLE IF EXISTS `attendancereport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attendancereport` (
  `USERID` varchar(255) NOT NULL,
  `TERM` varchar(255) DEFAULT NULL,
  `MONTH` varchar(255) DEFAULT NULL,
  `PERCENTAGE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`USERID`),
  KEY `FK425A855D750868B6` (`USERID`),
  CONSTRAINT `FK425A855D750868B6` FOREIGN KEY (`USERID`) REFERENCES `user` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendancereport`
--

LOCK TABLES `attendancereport` WRITE;
/*!40000 ALTER TABLE `attendancereport` DISABLE KEYS */;
/*!40000 ALTER TABLE `attendancereport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `batch`
--

DROP TABLE IF EXISTS `batch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `batch` (
  `BATCHID` varchar(255) NOT NULL,
  `BATCHNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`BATCHID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `batch`
--

LOCK TABLES `batch` WRITE;
/*!40000 ALTER TABLE `batch` DISABLE KEYS */;
INSERT INTO `batch` VALUES ('101','A'),('102','A'),('103','A'),('104','A'),('105','A'),('106','A'),('107','A');
/*!40000 ALTER TABLE `batch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classeventdetail`
--

DROP TABLE IF EXISTS `classeventdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classeventdetail` (
  `CLASSEVENTDETAILID` int(11) NOT NULL,
  `SCHEDULEID` int(11) DEFAULT NULL,
  `GENERALLOG` longtext,
  `CHAPTERNAME` varchar(255) DEFAULT NULL,
  `TOPICNAME` varchar(255) DEFAULT NULL,
  `FTPLOCATION` varchar(255) DEFAULT NULL,
  `ATTACHMENTFLAG` bit(1) DEFAULT NULL,
  `STARTTIME` datetime DEFAULT NULL,
  `ENDTIME` datetime DEFAULT NULL,
  `TOTALATTENDEES` int(11) DEFAULT NULL,
  `SCHEDULEFLAG` varchar(255) DEFAULT NULL,
  `NOOFTIMES` int(11) DEFAULT NULL,
  `SUBJECT` varchar(255) DEFAULT NULL,
  `SUBJECTNAME` varchar(255) DEFAULT NULL,
  `COURSENAME` varchar(255) DEFAULT NULL,
  `TEACHERNAME` varchar(255) DEFAULT NULL,
  `TEACHERID` varchar(255) DEFAULT NULL,
  `TEACHERPIC` varchar(255) DEFAULT NULL,
  `CLAZFLAG` int(11) DEFAULT NULL,
  PRIMARY KEY (`CLASSEVENTDETAILID`),
  KEY `FK859ACAF3AEBC48CE` (`SCHEDULEID`),
  CONSTRAINT `FK859ACAF3AEBC48CE` FOREIGN KEY (`SCHEDULEID`) REFERENCES `schedule` (`SCHEDULEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classeventdetail`
--

LOCK TABLES `classeventdetail` WRITE;
/*!40000 ALTER TABLE `classeventdetail` DISABLE KEYS */;
INSERT INTO `classeventdetail` VALUES (9,15,NULL,'Basic Electrical and Electronics ','',NULL,'','2017-06-21 09:01:31',NULL,0,'normalSchedule',NULL,NULL,'Basic Electrical and Electronics ','Basic Electrical and Electronics ','SenzIT','senzit','http://192.168.0.117:8080/ProfilePic/senzit.jpg',0),(10,22,'[{\"timestamp\":\"16/6/2017  12:17:14\",\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"raiseHandText\":\"0\",\"profilePic\":\"http://192.168.0.117:8080/ProfilePic/senzit.jpg\",\"logText\":\"Recording Started\"},{\"timestamp\":\"16/6/2017  12:17:43\",\"duration\":\"00:00:28\",\"raiseHandAnswer\":\"0\",\"raiseHandText\":\"0\",\"profilePic\":\"http://192.168.0.117:8080/ProfilePic/senzit.jpg\",\"logText\":\"Eigen_Img file shared\"},{\"timestamp\":\"16/6/2017  12:18:03\",\"duration\":\"00:00:48\",\"raiseHandAnswer\":\"0\",\"raiseHandText\":\"0\",\"profilePic\":\"http://192.168.0.117:8080/ProfilePic/senzit.jpg\",\"logText\":\"drawing file shared\"},{\"timestamp\":\"16/6/2017  12:18:45\",\"duration\":\"00:01:31\",\"raiseHandAnswer\":\"0\",\"raiseHandText\":\"0\",\"profilePic\":\"http://192.168.0.117:8080/ProfilePic/senzit.jpg\",\"logText\":\"Switch video\"},{\"timestamp\":\"16/6/2017  12:19:11\",\"duration\":\"00:01:56\",\"raiseHandAnswer\":\"0\",\"raiseHandText\":\"0\",\"profilePic\":\"http://192.168.0.117:8080/ProfilePic/senzit.jpg\",\"logText\":\"drwa002 file shared\"},{\"timestamp\":\"16/6/2017  12:19:16\",\"duration\":\"00:02:01\",\"raiseHandAnswer\":\"0\",\"raiseHandText\":\"0\",\"profilePic\":\"http://192.168.0.117:8080/ProfilePic/senzit.jpg\",\"logText\":\"Switch video\"},{\"timestamp\":\"16/6/2017  12:19:26\",\"duration\":\"00:02:12\",\"raiseHandAnswer\":\"0\",\"raiseHandText\":\"0\",\"profilePic\":\"http://192.168.0.117:8080/ProfilePic/senzit.jpg\",\"logText\":\"Eigen_Img file shared\"}]','Engineering Mathematics I','',NULL,'','2017-06-22 09:01:20','2017-06-22 09:07:55',0,'normalSchedule',NULL,NULL,'Engineering Mathematics I','Engineering Mathematics I','SenzIT','senzit','http://192.168.0.117:8080/ProfilePic/senzit.jpg',1),(11,29,'[{\"timestamp\":\"16/6/2017  12:26:57\",\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"raiseHandText\":\"0\",\"profilePic\":\"http://192.168.0.117:8080/ProfilePic/senzit.jpg\",\"logText\":\"Recording Started\"},{\"timestamp\":\"16/6/2017  12:27:41\",\"duration\":\"00:00:43\",\"raiseHandAnswer\":\"AAAAA1111\",\"raiseHandText\":\"aaaa11111111\",\"profilePic\":\"http://202.191.65.180:8080/ProfilePic/a4.png\",\"logText\":\"Shimla asked a question\"},{\"timestamp\":\"16/6/2017  12:28:02\",\"duration\":\"00:01:03\",\"raiseHandAnswer\":\"0\",\"raiseHandText\":\"0\",\"profilePic\":\"http://192.168.0.117:8080/ProfilePic/senzit.jpg\",\"logText\":\"Matrix_Pdf file shared\"},{\"timestamp\":\"16/6/2017  12:28:18\",\"duration\":\"00:01:19\",\"raiseHandAnswer\":\"0\",\"raiseHandText\":\"0\",\"profilePic\":\"http://192.168.0.117:8080/ProfilePic/senzit.jpg\",\"logText\":\"draw001 file shared\"},{\"timestamp\":\"16/6/2017  12:29:04\",\"duration\":\"00:02:06\",\"raiseHandAnswer\":\"CCCCCCC3333\",\"raiseHandText\":\"ccccc33333\",\"profilePic\":\"http://202.191.65.180:8080/ProfilePic/a4.png\",\"logText\":\"Shimla asked a question\"},{\"timestamp\":\"16/6/2017  12:29:09\",\"duration\":\"00:02:10\",\"raiseHandAnswer\":\"0\",\"raiseHandText\":\"0\",\"profilePic\":\"http://192.168.0.117:8080/ProfilePic/senzit.jpg\",\"logText\":\"Switch video\"},{\"timestamp\":\"16/6/2017  12:29:15\",\"duration\":\"00:02:16\",\"raiseHandAnswer\":\"0\",\"raiseHandText\":\"0\",\"profilePic\":\"http://192.168.0.117:8080/ProfilePic/senzit.jpg\",\"logText\":\"Switch video\"},{\"timestamp\":\"16/6/2017  12:29:31\",\"duration\":\"00:02:33\",\"raiseHandAnswer\":\"0\",\"raiseHandText\":\"0\",\"profilePic\":\"http://192.168.0.117:8080/ProfilePic/senzit.jpg\",\"logText\":\"draw002 file shared\"},{\"timestamp\":\"16/6/2017  12:29:37\",\"duration\":\"00:02:38\",\"raiseHandAnswer\":\"0\",\"raiseHandText\":\"0\",\"profilePic\":\"http://192.168.0.117:8080/ProfilePic/senzit.jpg\",\"logText\":\"Eigen_Img file shared\"},{\"timestamp\":\"16/6/2017  12:29:50\",\"duration\":\"00:02:51\",\"raiseHandAnswer\":\"DDD444\",\"raiseHandText\":\"ddddd44444\",\"profilePic\":\"http://202.191.65.180:8080/ProfilePic/a4.png\",\"logText\":\"Shimla asked a question\"},{\"timestamp\":\"16/6/2017  12:30:18\",\"duration\":\"00:03:19\",\"raiseHandAnswer\":\"FFF\",\"raiseHandText\":\"ffffff\",\"profilePic\":\"http://202.191.65.180:8080/ProfilePic/a4.png\",\"logText\":\"Shimla asked a question\"},{\"timestamp\":\"16/6/2017  12:30:35\",\"duration\":\"00:03:36\",\"raiseHandAnswer\":\"0\",\"raiseHandText\":\"0\",\"profilePic\":\"http://192.168.0.117:8080/ProfilePic/senzit.jpg\",\"logText\":\"Recording Stopped\"}]','Basic Electrical and Electronics ','',NULL,'','2017-06-23 09:02:26','2017-06-23 09:06:04',1,'normalSchedule',NULL,NULL,'Basic Electrical and Electronics ','Basic Electrical and Electronics ','SenzIT','senzit','http://192.168.0.117:8080/ProfilePic/senzit.jpg',1),(12,101,NULL,'mghmghmghm','ghmhgm',NULL,'\0','2017-06-25 21:48:06',NULL,0,'normalSchedule',NULL,NULL,'Engineering Physics','ghmghmghm','SenzIT','senzit','http://192.168.0.117:8080/ProfilePic/senzit.jpg',0),(13,101,NULL,'vxcvcxvcx','cxvxc',NULL,'\0','2017-07-27 23:47:20',NULL,0,'normalSchedule',NULL,NULL,'Engineering Physics','vcdvxc','SenzIT','senzit','http://192.168.0.117:8080/ProfilePic/senzit.jpg',0),(14,33,'[{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.0.117:8080/ProfilePic/senzit.jpg\",\"raiseHandText\":\"0\",\"timestamp\":\"28/07/2017 14:16:49\",\"logText\":\"Recording Started\"},{\"duration\":\"00:07:21\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.0.117:8080/ProfilePic/senzit.jpg\",\"raiseHandText\":\"0\",\"timestamp\":\"28/07/2017 14:24:25\",\"logText\":\"Eigen_Img file shared\"},{\"duration\":\"00:12:20\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.0.117:8080/ProfilePic/senzit.jpg\",\"raiseHandText\":\"0\",\"timestamp\":\"28/07/2017 14:29:30\",\"logText\":\"Recording Stopped\"}]','PC Installation','Installing and Repairing PC',NULL,'','2017-07-28 14:16:45','2017-07-28 14:29:40',0,'normalSchedule',NULL,NULL,'Engineering Chemistry','Computer Science ','SenzIT','senzit','http://192.168.0.117:8080/ProfilePic/senzit.jpg',1),(15,101,'[{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.0.117:8080/ProfilePic/senzit.jpg\",\"raiseHandText\":\"0\",\"timestamp\":\"28/07/2017 14:36:31\",\"logText\":\"Recording Started\"},{\"duration\":\"00:08:00\",\"raiseHandAnswer\":\"Yes Im Fine\",\"profilePic\":\"http://202.191.65.180:8080/ProfilePic/a9.png\",\"raiseHandText\":\"How are you?\",\"timestamp\":\"28/07/2017 14:44:33\",\"logText\":\"Ramu Asked A Question\"},{\"duration\":\"00:13:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.0.117:8080/ProfilePic/senzit.jpg\",\"raiseHandText\":\"0\",\"timestamp\":\"28/07/2017 14:49:34\",\"logText\":\"Recording Stopped\"}]','vcncvn','cvncvn',NULL,'\0','2017-07-28 14:36:30','2017-07-28 14:49:46',0,'normalSchedule',NULL,NULL,'Engineering Physics','cvncv','SenzIT','senzit','http://192.168.0.117:8080/ProfilePic/senzit.jpg',1),(16,101,'[{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"01/12/2017 13:01:11\",\"logText\":\"Recording Started\"},{\"duration\":\"00:03:31\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"01/12/2017 13:06:28\",\"logText\":\"Recording Stopped\"}]','sdgsdgsdgs','gsdgsdg',NULL,'\0','2017-12-01 13:01:06','2017-12-01 13:06:34',0,'normalSchedule',NULL,NULL,'Other','gsdg','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',1),(17,101,NULL,'Linear Functions','Algibra I',NULL,'\0','2017-12-01 13:07:38',NULL,0,'normalSchedule',NULL,NULL,'Other','Computer Science ','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',0),(18,101,'[{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"01/12/2017 13:10:52\",\"logText\":\"Recording Started\"},{\"duration\":\"00:01:08\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"01/12/2017 13:11:59\",\"logText\":\"Recording Stopped\"}]','Linear Functions','Algibra I',NULL,'\0','2017-12-01 13:10:50','2017-12-01 13:12:05',0,'normalSchedule',NULL,NULL,'Other','Computer Science ','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',1),(19,101,'[{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"01/12/2017 14:46:05\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:11\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"01/12/2017 14:46:15\",\"logText\":\"Recording Stopped\"}]','Linear Functions','Algibra I',NULL,'\0','2017-12-01 14:46:02','2017-12-01 14:46:21',0,'normalSchedule',NULL,NULL,'Other','Computer Science ','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',1),(20,101,'[{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"04/12/2017 12:19:19\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:11\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"04/12/2017 12:19:29\",\"logText\":\"Recording Stopped\"}]','Linear Functions','Algibra I',NULL,'\0','2017-12-04 12:19:14','2017-12-04 12:19:34',0,'normalSchedule',NULL,NULL,'Other','Computer Science ','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',1),(21,101,'[{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"04/12/2017 13:00:46\",\"logText\":\"Recording Started\"},{\"duration\":\"00:07:42\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"04/12/2017 13:12:25\",\"logText\":\"Recording Stopped\"}]','Linear Functions','Algibra I',NULL,'\0','2017-12-04 13:00:43','2017-12-04 13:12:32',0,'normalSchedule',NULL,NULL,'Other','Computer Science ','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',1),(22,101,'[]','Linear Functions','Algibra I',NULL,'\0','2017-12-06 14:01:11','2017-12-06 14:43:35',0,'normalSchedule',NULL,NULL,'Other','Computer Science ','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',1),(23,101,NULL,'Linear Functions','Algibra I',NULL,'\0','2017-12-06 15:21:40',NULL,0,'normalSchedule',NULL,NULL,'Other','Computer Science ','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',0),(24,101,NULL,'Linear Functions','Algibra I',NULL,'\0','2017-12-07 11:29:16',NULL,0,'normalSchedule',NULL,NULL,'Other','Computer Science ','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',0),(25,101,NULL,'Linear Functions','Algibra I',NULL,'\0','2017-12-07 11:34:38',NULL,0,'normalSchedule',NULL,NULL,'Other','Computer Science ','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',0),(26,101,NULL,'Linear Functions','Algibra I',NULL,'\0','2017-12-07 11:37:47',NULL,0,'normalSchedule',NULL,NULL,'Other','Computer Science ','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',0),(27,101,'[]','Linear Functions','Algibra I',NULL,'\0','2017-12-07 11:40:56','2017-12-07 11:55:30',0,'normalSchedule',NULL,NULL,'Other','Computer Science ','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',1),(28,101,'[{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"07/12/2017 20:07:44\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:37\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"07/12/2017 20:08:22\",\"logText\":\"Recording Stopped\"}]','Linear Functions','Algibra I',NULL,'\0','2017-12-07 20:07:40','2017-12-07 20:08:31',0,'normalSchedule',NULL,NULL,'Other','Computer Science ','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',1),(29,101,'[{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"07/12/2017 20:11:58\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"07/12/2017 20:12:24\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"07/12/2017 20:14:20\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"07/12/2017 20:15:40\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"07/12/2017 20:19:28\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:04\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"07/12/2017 20:19:33\",\"logText\":\"Recording Stopped\"}]','Linear Functions','Algibra I',NULL,'\0','2017-12-07 20:11:55','2017-12-07 20:19:41',0,'normalSchedule',NULL,NULL,'Other','Computer Science ','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',1),(30,101,'[{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"07/12/2017 23:00:47\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"07/12/2017 23:02:16\",\"logText\":\"Recording Started\"},{\"duration\":\"00:01:19\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"07/12/2017 23:03:38\",\"logText\":\"Recording Stopped\"}]','Linear Functions','Algibra I',NULL,'\0','2017-12-07 23:00:42','2017-12-07 23:04:12',0,'normalSchedule',NULL,NULL,'Other','Computer Science ','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',1),(31,101,'[{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"07/12/2017 23:46:36\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:59\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"07/12/2017 23:47:37\",\"logText\":\"Recording Stopped\"}]','First World War','History',NULL,'\0','2017-12-07 23:46:30','2017-12-07 23:47:52',0,'normalSchedule',NULL,NULL,'Other','Computer Science ','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',1),(32,101,'[{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"07/12/2017 23:52:08\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:36\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"07/12/2017 23:52:45\",\"logText\":\"CyberClaz file shared\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"07/12/2017 23:55:56\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"07/12/2017 23:56:14\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"07/12/2017 23:56:45\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"07/12/2017 23:57:02\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"07/12/2017 23:57:29\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"07/12/2017 23:57:38\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"07/12/2017 23:59:34\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"07/12/2017 23:59:50\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"08/12/2017 00:00:27\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"08/12/2017 00:00:41\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"08/12/2017 00:01:13\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"08/12/2017 00:01:31\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"08/12/2017 00:02:20\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"08/12/2017 00:04:58\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"08/12/2017 00:05:25\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"08/12/2017 00:06:30\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"08/12/2017 00:07:01\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"08/12/2017 00:08:01\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"08/12/2017 00:08:08\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"08/12/2017 00:08:27\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"08/12/2017 00:08:39\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"08/12/2017 00:10:04\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"08/12/2017 00:10:44\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:18\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"08/12/2017 00:11:04\",\"logText\":\"Recording Stopped\"}]','reyerye','sdgsdg',NULL,'','2017-12-07 23:52:05','2017-12-08 00:11:18',0,'normalSchedule',NULL,NULL,'Basic Mechanical Engineering','Test','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',1),(33,101,'[{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"08/12/2017 00:15:45\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"08/12/2017 00:16:02\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:20\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"08/12/2017 00:16:26\",\"logText\":\"Recording Stopped\"}]','Complex Functions','Algibra II',NULL,'\0','2017-12-08 00:15:40','2017-12-08 00:16:41',0,'normalSchedule',NULL,NULL,'Other','Computer Science ','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',1),(34,101,NULL,'yweywey','weyewyew',NULL,'','2017-12-08 00:39:15',NULL,0,'normalSchedule',NULL,NULL,'Engineering Chemistry','gtewgt','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',0),(35,101,NULL,'dhsh','hshs',NULL,'','2017-12-08 00:59:14',NULL,0,'normalSchedule',NULL,NULL,'Engineering Physics','sdgsd','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',0),(36,101,'[{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"08/12/2017 10:10:14\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:22\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"08/12/2017 10:10:43\",\"logText\":\"Recording Stopped\"}]','fgnfnfn','fnggfn',NULL,'\0','2017-12-08 10:10:10','2017-12-08 10:10:50',0,'normalSchedule',NULL,NULL,'Engineering Physics','fnfgn','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',1),(37,101,'[{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"08/12/2017 10:13:28\",\"logText\":\"Recording Started\"},{\"duration\":\"00:02:29\",\"raiseHandAnswer\":\"dbsdsdbsb\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a6.png\",\"raiseHandText\":\"hey\",\"timestamp\":\"08/12/2017 10:15:57\",\"logText\":\"Ermina Asked A Question\"},{\"duration\":\"00:03:28\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"08/12/2017 10:16:59\",\"logText\":\"CyberClaz file shared\"},{\"duration\":\"00:05:45\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"08/12/2017 10:19:22\",\"logText\":\"Recording Stopped\"}]','sdgsg','sdgsdg',NULL,'','2017-12-08 10:13:23','2017-12-08 10:19:29',0,'normalSchedule',NULL,NULL,'Engineering Graphics','sdgsdg','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',1),(38,36,NULL,'efwfe','sdsd',NULL,'\0','2017-08-26 09:43:39',NULL,0,'normalSchedule',NULL,NULL,'Engineering Mathematics I','Computer Science ','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',0),(39,101,NULL,'sdgsdg','dsgsg',NULL,'\0','2017-08-26 09:49:28',NULL,0,'normalSchedule',NULL,NULL,'Engineering Chemistry','sdgsg','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',0),(40,101,'[{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"26/08/2017 09:54:51\",\"logText\":\"Recording Started\"},{\"duration\":\"00:01:00\",\"raiseHandAnswer\":\"weerte\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a7.png\",\"raiseHandText\":\"scfsf\",\"timestamp\":\"26/08/2017 09:55:50\",\"logText\":\"Francyne Asked A Question\"},{\"duration\":\"00:01:05\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"26/08/2017 09:55:55\",\"logText\":\"CyberClaz file shared\"},{\"duration\":\"00:02:14\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"26/08/2017 09:57:04\",\"logText\":\"CyberClaz file shared\"},{\"duration\":\"00:02:14\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"26/08/2017 09:57:04\",\"logText\":\"CyberClaz file shared\"},{\"duration\":\"00:02:14\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"26/08/2017 09:57:05\",\"logText\":\"CyberClaz file shared\"},{\"duration\":\"00:02:40\",\"raiseHandAnswer\":\"2\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a7.png\",\"raiseHandText\":\"dfgdfgbd dfhzyhdrtf ;oi[;\",\"timestamp\":\"26/08/2017 09:57:30\",\"logText\":\"Francyne Asked A Question\"},{\"duration\":\"00:02:43\",\"raiseHandAnswer\":\"3\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a7.png\",\"raiseHandText\":\"asdas hrthjfth\",\"timestamp\":\"26/08/2017 09:57:34\",\"logText\":\"Francyne Asked A Question\"},{\"duration\":\"00:02:47\",\"raiseHandAnswer\":\"4\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a7.png\",\"raiseHandText\":\"ncvnfgfghd snrn\",\"timestamp\":\"26/08/2017 09:57:37\",\"logText\":\"Francyne Asked A Question\"},{\"duration\":\"00:02:51\",\"raiseHandAnswer\":\"5\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a7.png\",\"raiseHandText\":\"khukmghk\",\"timestamp\":\"26/08/2017 09:57:41\",\"logText\":\"Francyne Asked A Question\"},{\"duration\":\"00:02:55\",\"raiseHandAnswer\":\"6\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a7.png\",\"raiseHandText\":\"ngfnfgtjft vfghftxhn\",\"timestamp\":\"26/08/2017 09:57:45\",\"logText\":\"Francyne Asked A Question\"},{\"duration\":\"00:03:11\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"26/08/2017 09:58:01\",\"logText\":\"Recording Stopped\"}]','dsfdf','sdfsd',NULL,'','2017-08-26 09:54:49','2017-08-26 09:58:02',0,'normalSchedule',NULL,NULL,'Basic Civil Engineering','dfsdfds','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',1),(41,101,NULL,'Perpendicular Liness','Geometry',NULL,'','2017-08-26 09:58:17',NULL,0,'normalSchedule',NULL,NULL,'Other','Computer Science ','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',0),(42,101,'[{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"26/08/2017 10:01:54\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"26/08/2017 10:02:29\",\"logText\":\"Recording Started\"},{\"duration\":\"00:04:20\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"26/08/2017 10:06:51\",\"logText\":\"CyberClaz file shared\"},{\"duration\":\"00:04:22\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"26/08/2017 10:06:54\",\"logText\":\"Eigen_Img file shared\"},{\"duration\":\"00:04:56\",\"raiseHandAnswer\":\"yes\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a9.png\",\"raiseHandText\":\"hey\",\"timestamp\":\"26/08/2017 10:07:28\",\"logText\":\"Chaim Asked A Question\"},{\"duration\":\"00:05:12\",\"raiseHandAnswer\":\"kjhkjgk\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a9.png\",\"raiseHandText\":\"awrewrf wrwr\",\"timestamp\":\"26/08/2017 10:07:43\",\"logText\":\"Chaim Asked A Question\"},{\"duration\":\"00:05:34\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"26/08/2017 10:08:05\",\"logText\":\"Recording Stopped\"}]','regr','ergerg',NULL,'','2017-08-26 10:01:52','2017-08-26 10:08:05',0,'normalSchedule',NULL,NULL,'Basic Mechanical Engineering','lkhoi','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',1),(43,101,NULL,'nvcncv','cvbcvn',NULL,'','2017-08-26 10:18:08',NULL,0,'normalSchedule',NULL,NULL,'Engineering Chemistry','vncvn','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',0),(44,101,'[{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"26/08/2017 12:42:16\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"26/08/2017 12:43:09\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:13\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"26/08/2017 12:43:20\",\"logText\":\"Recording Stopped\"}]','wetwet','qwrwer',NULL,'\0','2017-08-26 12:42:14','2017-08-26 12:43:21',0,'normalSchedule',NULL,NULL,'Engineering Chemistry','erewrw','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',1),(45,101,'[{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"26/08/2017 12:43:40\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:09\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"26/08/2017 12:43:49\",\"logText\":\"Recording Stopped\"}]','Linear Functions','Algibra I',NULL,'\0','2017-08-26 12:43:39','2017-08-26 12:43:49',0,'normalSchedule',NULL,NULL,'Other','Computer Science ','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',1),(46,101,'[{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"26/08/2017 13:00:45\",\"logText\":\"Recording Started\"},{\"duration\":\"00:09:24\",\"raiseHandAnswer\":\"hhgho\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a8.png\",\"raiseHandText\":\"hello\",\"timestamp\":\"26/08/2017 13:10:10\",\"logText\":\"Lucky Asked A Question\"},{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"26/08/2017 13:15:26\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:25\",\"raiseHandAnswer\":\"yes\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a8.png\",\"raiseHandText\":\"hey helo\",\"timestamp\":\"26/08/2017 13:15:51\",\"logText\":\"Lucky Asked A Question\"},{\"duration\":\"00:00:28\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"26/08/2017 13:15:53\",\"logText\":\"arch file shared\"},{\"duration\":\"00:00:55\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"26/08/2017 13:16:20\",\"logText\":\"Matrix_Pdf file shared\"},{\"duration\":\"00:00:56\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"26/08/2017 13:16:21\",\"logText\":\"Matrix_Pdf file shared\"},{\"duration\":\"00:00:57\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"26/08/2017 13:16:23\",\"logText\":\"Eigen_Img file shared\"},{\"duration\":\"00:02:14\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"26/08/2017 13:17:39\",\"logText\":\"Recording Stopped\"}]','ShibiHome','ShibiHo',NULL,'','2017-08-26 13:00:44','2017-08-26 13:17:39',0,'normalSchedule',NULL,NULL,'Engineering Physics','Computer','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',1),(47,101,'[{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"26/08/2017 13:19:25\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:19\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"26/08/2017 13:19:43\",\"logText\":\"CyberClaz file shared\"},{\"duration\":\"00:01:59\",\"raiseHandAnswer\":\"yes\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a9.png\",\"raiseHandText\":\"doubt\",\"timestamp\":\"26/08/2017 13:21:23\",\"logText\":\"Chaim Asked A Question\"},{\"duration\":\"00:04:04\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"26/08/2017 13:23:29\",\"logText\":\"Recording Stopped\"}]','test chap','test topic',NULL,'','2017-08-26 13:19:24','2017-08-26 13:23:29',0,'normalSchedule',NULL,NULL,'Basic Civil Engineering','engg','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',1),(48,101,NULL,'Linear Functions','Algibra I',NULL,'\0','2017-08-27 16:28:30',NULL,0,'normalSchedule',NULL,NULL,'Other','Computer Science ','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',0),(49,101,NULL,'Linear Functions','Algibra I',NULL,'\0','2017-08-27 16:34:42',NULL,0,'normalSchedule',NULL,NULL,'Other','Computer Science ','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',0),(50,101,NULL,'Linear Functions','Algibra I',NULL,'','2017-08-27 17:03:44',NULL,0,'normalSchedule',NULL,NULL,'Other','Computer Science ','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',0),(51,101,NULL,'gdsgsdg','sdgds',NULL,'','2017-08-27 17:15:39',NULL,0,'normalSchedule',NULL,NULL,'Basic Civil Engineering','rgsfdg','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',0),(52,101,'[{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a10.png\",\"raiseHandText\":\"0\",\"timestamp\":\"28/08/2017 07:10:38\",\"logText\":\"Recording Started\"},{\"duration\":\"00:03:45\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a1.png\",\"raiseHandText\":\"0\",\"timestamp\":\"28/08/2017 07:14:22\",\"logText\":\"Recording Stopped\"}]','Linear Functions','Algibra I',NULL,'\0','2017-08-28 07:10:36','2017-08-28 07:14:23',0,'normalSchedule',NULL,NULL,'Other','Computer Science ','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a10.png',1),(53,101,'[{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a1.png\",\"raiseHandText\":\"0\",\"timestamp\":\"28/08/2017 07:17:03\",\"logText\":\"Recording Started\"},{\"duration\":\"00:01:54\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a1.png\",\"raiseHandText\":\"0\",\"timestamp\":\"28/08/2017 07:18:56\",\"logText\":\"CyberClaz file shared\"},{\"duration\":\"00:02:46\",\"raiseHandAnswer\":\"Yes.. Proceed..\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a6.png\",\"raiseHandText\":\"I have a doubt..\",\"timestamp\":\"28/08/2017 07:19:49\",\"logText\":\"Ermina Asked A Question\"},{\"duration\":\"00:03:20\",\"raiseHandAnswer\":\"Will discuss end of this session\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a6.png\",\"raiseHandText\":\"What is Algibra?\",\"timestamp\":\"28/08/2017 07:20:23\",\"logText\":\"Ermina Asked A Question\"},{\"duration\":\"00:04:16\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a1.png\",\"raiseHandText\":\"0\",\"timestamp\":\"28/08/2017 07:21:19\",\"logText\":\"Eigen_Img file shared\"},{\"duration\":\"00:04:54\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a1.png\",\"raiseHandText\":\"0\",\"timestamp\":\"28/08/2017 07:21:58\",\"logText\":\"Recording Stopped\"}]','Linear Functions','Algibra I',NULL,'','2017-08-28 07:17:01','2017-08-28 07:21:58',0,'normalSchedule',NULL,NULL,'Other','Computer Science ','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a1.png',1),(54,101,'[{\"duration\":\"00:00:00\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a1.png\",\"raiseHandText\":\"0\",\"timestamp\":\"28/08/2017 07:26:43\",\"logText\":\"Recording Started\"},{\"duration\":\"00:00:29\",\"raiseHandAnswer\":\"0\",\"profilePic\":\"http://192.168.10.50:8080/ProfilePic/a1.png\",\"raiseHandText\":\"0\",\"timestamp\":\"28/08/2017 07:27:11\",\"logText\":\"Recording Stopped\"}]','Lines','Straight Lines',NULL,'\0','2017-08-28 07:26:41','2017-08-28 07:27:11',0,'normalSchedule',NULL,NULL,'Engineering Graphics','Engineering Computer Science','SenzIT','senzit','http://192.168.10.50:8080/ProfilePic/a1.png',1);
/*!40000 ALTER TABLE `classeventdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classroom`
--

DROP TABLE IF EXISTS `classroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classroom` (
  `CLASSROOMID` varchar(255) NOT NULL,
  `CLASSROOMNO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CLASSROOMID`),
  UNIQUE KEY `CLASSROOMNO` (`CLASSROOMNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classroom`
--

LOCK TABLES `classroom` WRITE;
/*!40000 ALTER TABLE `classroom` DISABLE KEYS */;
INSERT INTO `classroom` VALUES ('101','101'),('102','102'),('103','103'),('104','104'),('105','105'),('106','106'),('107','107'),('108','108'),('109','109'),('110','110'),('111','111');
/*!40000 ALTER TABLE `classroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `COURSEID` varchar(255) NOT NULL,
  `COURSENAME` varchar(255) DEFAULT NULL,
  `COURSEDESCRIPTION` varchar(255) DEFAULT NULL,
  `COURSECATEGORY` varchar(255) DEFAULT NULL,
  `COURSEDURATION` varchar(255) DEFAULT NULL,
  `CURRENTSCHEME` varchar(255) DEFAULT NULL,
  `SEMORYEAR` varchar(255) DEFAULT NULL,
  `DEPARTMENT` varchar(255) DEFAULT NULL,
  `DISTANTORREGULAR` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`COURSEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('415','Computer Science & Engineering ','CS','UG','8','2008','Semester','CS','Regular'),('416','Electronics & Communication Engineering','EC','UG','8','2008','Semester','ECE','Regular'),('417','Mechanical Engineering','ME','UG','8','2008','Semester','ME','Regular'),('418','Civil Engineering','CE','UG','8','2008','Semester','CE','Regular'),('419','Aeronautical Engineering','AE','UG','8','2008','Semester','AE','Regular'),('420','Electrical Engineering','EEE','UG','8','2008','Semester','EEE','Regular');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coursebatch`
--

DROP TABLE IF EXISTS `coursebatch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coursebatch` (
  `COURSEBATCHID` int(11) NOT NULL,
  `COURSEID` varchar(255) DEFAULT NULL,
  `SEMESTERID` varchar(255) DEFAULT NULL,
  `BATCHID` varchar(255) DEFAULT NULL,
  `CLASSROOMID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`COURSEBATCHID`),
  UNIQUE KEY `CLASSROOMID` (`CLASSROOMID`),
  KEY `FK7B8263DF6A8E21D0` (`SEMESTERID`),
  KEY `FK7B8263DF2F153D7C` (`CLASSROOMID`),
  KEY `FK7B8263DF1FEFE2EA` (`BATCHID`),
  KEY `FK7B8263DF2F8CE016` (`COURSEID`),
  CONSTRAINT `FK7B8263DF1FEFE2EA` FOREIGN KEY (`BATCHID`) REFERENCES `batch` (`BATCHID`),
  CONSTRAINT `FK7B8263DF2F153D7C` FOREIGN KEY (`CLASSROOMID`) REFERENCES `classroom` (`CLASSROOMID`),
  CONSTRAINT `FK7B8263DF2F8CE016` FOREIGN KEY (`COURSEID`) REFERENCES `course` (`COURSEID`),
  CONSTRAINT `FK7B8263DF6A8E21D0` FOREIGN KEY (`SEMESTERID`) REFERENCES `semester` (`SEMID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coursebatch`
--

LOCK TABLES `coursebatch` WRITE;
/*!40000 ALTER TABLE `coursebatch` DISABLE KEYS */;
INSERT INTO `coursebatch` VALUES (101,'415','1','101','101'),(102,'416','1','102','102'),(103,'417','1','103','103'),(104,'418','1','104','104'),(105,'419','1','105','105'),(106,'420','1','106','106');
/*!40000 ALTER TABLE `coursebatch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coursesubject`
--

DROP TABLE IF EXISTS `coursesubject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coursesubject` (
  `COURSESUBJECTID` int(11) NOT NULL,
  `COURSEID` varchar(255) DEFAULT NULL,
  `SEMESTERID` varchar(255) DEFAULT NULL,
  `SUBJECTID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`COURSESUBJECTID`),
  KEY `FK48E948916A8E21D0` (`SEMESTERID`),
  KEY `FK48E948912F8CE016` (`COURSEID`),
  KEY `FK48E94891D1AB1F8E` (`SUBJECTID`),
  CONSTRAINT `FK48E948912F8CE016` FOREIGN KEY (`COURSEID`) REFERENCES `course` (`COURSEID`),
  CONSTRAINT `FK48E948916A8E21D0` FOREIGN KEY (`SEMESTERID`) REFERENCES `semester` (`SEMID`),
  CONSTRAINT `FK48E94891D1AB1F8E` FOREIGN KEY (`SUBJECTID`) REFERENCES `subject` (`SUBJECTID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coursesubject`
--

LOCK TABLES `coursesubject` WRITE;
/*!40000 ALTER TABLE `coursesubject` DISABLE KEYS */;
INSERT INTO `coursesubject` VALUES (1,'415','1','01'),(2,'415','1','02'),(3,'415','1','03'),(4,'415','1','04'),(5,'415','1','05'),(6,'415','1','06'),(7,'415','1','07'),(8,'415','1','08'),(9,'415','1','09'),(10,'415','1','10');
/*!40000 ALTER TABLE `coursesubject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `day`
--

DROP TABLE IF EXISTS `day`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `day` (
  `DAYID` varchar(255) NOT NULL,
  `DAYNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DAYID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `day`
--

LOCK TABLES `day` WRITE;
/*!40000 ALTER TABLE `day` DISABLE KEYS */;
INSERT INTO `day` VALUES ('1','Mon'),('2','Tue'),('3','Wed'),('4','Thu'),('5','Fri'),('6','Sat'),('7','Sun');
/*!40000 ALTER TABLE `day` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `futuregoals`
--

DROP TABLE IF EXISTS `futuregoals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `futuregoals` (
  `GOALID` int(11) NOT NULL,
  `USERID` varchar(255) DEFAULT NULL,
  `GOALS` varchar(255) DEFAULT NULL,
  `COURSEBATCHID` int(11) DEFAULT NULL,
  PRIMARY KEY (`GOALID`),
  KEY `FK62AE8BBD750868B6` (`USERID`),
  KEY `FKE25AF7DD9E00B6D4` (`COURSEBATCHID`),
  CONSTRAINT `FK62AE8BBD750868B6` FOREIGN KEY (`USERID`) REFERENCES `user` (`USERID`),
  CONSTRAINT `FKE25AF7DD9E00B6D4` FOREIGN KEY (`COURSEBATCHID`) REFERENCES `coursebatch` (`COURSEBATCHID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `futuregoals`
--

LOCK TABLES `futuregoals` WRITE;
/*!40000 ALTER TABLE `futuregoals` DISABLE KEYS */;
/*!40000 ALTER TABLE `futuregoals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `holiday`
--

DROP TABLE IF EXISTS `holiday`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `holiday` (
  `HOLIDAYID` int(11) NOT NULL,
  `DATE` date DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`HOLIDAYID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `holiday`
--

LOCK TABLES `holiday` WRITE;
/*!40000 ALTER TABLE `holiday` DISABLE KEYS */;
/*!40000 ALTER TABLE `holiday` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `USERID` varchar(255) NOT NULL,
  `SESSIONID` varchar(255) DEFAULT NULL,
  `DEVICEIP` varchar(255) DEFAULT NULL,
  `LOGINTIME` datetime DEFAULT NULL,
  `LASTREQTIME` datetime DEFAULT NULL,
  `REBBONID` varchar(255) DEFAULT NULL,
  `LOGINSTATUS` bit(1) DEFAULT NULL,
  PRIMARY KEY (`USERID`),
  KEY `FK127242C73DBC4` (`REBBONID`),
  KEY `FK12724750868B6` (`USERID`),
  CONSTRAINT `FK127242C73DBC4` FOREIGN KEY (`REBBONID`) REFERENCES `rebbon` (`REBBONID`),
  CONSTRAINT `FK12724750868B6` FOREIGN KEY (`USERID`) REFERENCES `user` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES ('eruperto3','113FF228A53D5AA2C9D36B5CFC44506A',NULL,NULL,NULL,'r101',''),('fmcmurrughg','E3FB7374BFA576DC1DA97863D1E09E5D',NULL,NULL,NULL,'r101','');
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `managersettings`
--

DROP TABLE IF EXISTS `managersettings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `managersettings` (
  `MANAGERID` int(11) NOT NULL,
  `USERID` varchar(255) DEFAULT NULL,
  `COURSEBATCHID` int(11) DEFAULT NULL,
  PRIMARY KEY (`MANAGERID`),
  KEY `FKE30BCED09E00B6D4` (`COURSEBATCHID`),
  KEY `FKE30BCED0750868B6` (`USERID`),
  CONSTRAINT `FKE30BCED0750868B6` FOREIGN KEY (`USERID`) REFERENCES `user` (`USERID`),
  CONSTRAINT `FKE30BCED09E00B6D4` FOREIGN KEY (`COURSEBATCHID`) REFERENCES `coursebatch` (`COURSEBATCHID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `managersettings`
--

LOCK TABLES `managersettings` WRITE;
/*!40000 ALTER TABLE `managersettings` DISABLE KEYS */;
INSERT INTO `managersettings` VALUES (1,'manager',101);
/*!40000 ALTER TABLE `managersettings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maximumhours`
--

DROP TABLE IF EXISTS `maximumhours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `maximumhours` (
  `MAXHOURID` int(11) NOT NULL,
  `USERID` varchar(255) DEFAULT NULL,
  `MINUTES` int(11) DEFAULT NULL,
  `COURSEBATCHID` int(11) DEFAULT NULL,
  PRIMARY KEY (`MAXHOURID`),
  KEY `FK2BD0E4EF750868B6` (`USERID`),
  KEY `FK2BD0E4EF9E00B6D4` (`COURSEBATCHID`),
  CONSTRAINT `FK2BD0E4EF750868B6` FOREIGN KEY (`USERID`) REFERENCES `user` (`USERID`),
  CONSTRAINT `FK2BD0E4EF9E00B6D4` FOREIGN KEY (`COURSEBATCHID`) REFERENCES `coursebatch` (`COURSEBATCHID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maximumhours`
--

LOCK TABLES `maximumhours` WRITE;
/*!40000 ALTER TABLE `maximumhours` DISABLE KEYS */;
INSERT INTO `maximumhours` VALUES (1,'senzit',-1225,101);
/*!40000 ALTER TABLE `maximumhours` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `multimedialibrary`
--

DROP TABLE IF EXISTS `multimedialibrary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `multimedialibrary` (
  `MEDIAID` int(11) NOT NULL,
  `USERID` varchar(255) DEFAULT NULL,
  `MEDIANAME` varchar(255) DEFAULT NULL,
  `MEDIADESCRIPTION` varchar(255) DEFAULT NULL,
  `FTPPATH` varchar(255) DEFAULT NULL,
  `UPLOADEDON` datetime DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`MEDIAID`),
  KEY `FK27C5B3F0750868B6` (`USERID`),
  CONSTRAINT `FK27C5B3F0750868B6` FOREIGN KEY (`USERID`) REFERENCES `user` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `multimedialibrary`
--

LOCK TABLES `multimedialibrary` WRITE;
/*!40000 ALTER TABLE `multimedialibrary` DISABLE KEYS */;
INSERT INTO `multimedialibrary` VALUES (76,'abid','Eigen_Img','Eigen Values Image file','http://192.168.10.50:8080/ATTACHMENT/mathseigenvalues-and-eigenvectors-3-638.jpg','2017-05-23 04:50:36','jpg'),(77,'senzit','Matrix_Pdf','Characteristic Equstions','http://192.168.10.50:8080/ATTACHMENT/Characteristic_Equation.pdf','2017-05-23 05:27:30','pdf'),(78,'senzit','Eigen_Img','Eigen Values Image','http://192.168.10.50:8080/ATTACHMENT/state-space-analysis-eign-values-and-eign-vectors-30-638.jpg','2017-05-23 05:28:16','jpg'),(79,'senzit','CyberClaz','Claz Architecture','http://192.168.10.50:8080/ATTACHMENT/Doc1_arch-1.jpg','2017-07-28 16:36:01','jpg'),(80,'senzit','Test','Test File','http://192.168.10.50:8080/ATTACHMENT/logo_cyberclaz_final-02.jpg','2017-08-28 07:23:05','jpg');
/*!40000 ALTER TABLE `multimedialibrary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `note`
--

DROP TABLE IF EXISTS `note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `note` (
  `NOTEID` int(11) NOT NULL AUTO_INCREMENT,
  `USERID` varchar(255) DEFAULT NULL,
  `CLASSEVENTDETAILID` int(11) DEFAULT NULL,
  `NOTES` longtext,
  PRIMARY KEY (`NOTEID`),
  KEY `FK24A7F2750868B6` (`USERID`),
  KEY `FK24A7F2B98D186` (`CLASSEVENTDETAILID`),
  CONSTRAINT `FK24A7F2750868B6` FOREIGN KEY (`USERID`) REFERENCES `user` (`USERID`),
  CONSTRAINT `FK24A7F2B98D186` FOREIGN KEY (`CLASSEVENTDETAILID`) REFERENCES `classeventdetail` (`CLASSEVENTDETAILID`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `note`
--

LOCK TABLES `note` WRITE;
/*!40000 ALTER TABLE `note` DISABLE KEYS */;
INSERT INTO `note` VALUES (112,'senzit',10,'[{\"time\":\"00:21\",\"notes\":\"Reminder0001\"},{\"time\":\"01:11\",\"notes\":\"Reminder0002\"},{\"time\":\"01:25\",\"notes\":\"Reminder0003\"},{\"time\":\"02:30\",\"notes\":\"Reminder0003\"}]'),(113,'senzit',11,'[{\"time\":\"00:58\",\"notes\":\"Reminder0001\"}]'),(114,'senzit',14,'[{\"notes\":\"dydydr\",\"time\":\"28/61/2017  2:27:25\"}]'),(115,'senzit',15,'[{\"notes\":\"I asked a question.\",\"time\":\"28/61/2017  2:48:07\"},{\"notes\":\"Session Completed.\",\"time\":\"28/61/2017  2:48:16\"}]'),(116,'senzit',16,'[]'),(117,'senzit',18,'[]'),(118,'senzit',19,'[]'),(119,'senzit',20,'[]'),(120,'senzit',21,'[]'),(121,'senzit',22,'[]'),(122,'senzit',22,'[]'),(123,'senzit',27,'[]'),(124,'senzit',27,'[]'),(125,'senzit',28,'[]'),(126,'senzit',29,'[]'),(127,'senzit',30,'[]'),(128,'senzit',31,'[]'),(129,'senzit',32,'[]'),(130,'senzit',33,'[]'),(131,'senzit',36,'[]'),(132,'senzit',37,'[]'),(133,'senzit',40,'[]'),(134,'senzit',42,'[]'),(135,'senzit',44,'[]'),(136,'senzit',45,'[]'),(137,'senzit',46,'[]'),(138,'senzit',47,'[{\"notes\":\"hello\",\"time\":\"12/8/2017  6:39:24\"}]'),(139,'senzit',52,'[]'),(140,'senzit',53,'[{\"notes\":\"First Class Note..\",\"time\":\"12/10/2017  12:37:28\"},{\"notes\":\"Second Note..\",\"time\":\"12/10/2017  12:37:38\"}]'),(141,'senzit',54,'[{\"notes\":\"Recording from Claz Recorder..\",\"time\":\"12/10/2017  12:46:34\"}]');
/*!40000 ALTER TABLE `note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `NOTIFICATIONID` int(11) NOT NULL,
  `FROMUSERID` varchar(255) DEFAULT NULL,
  `TOUSERID` varchar(255) DEFAULT NULL,
  `CLASSEVENTDETAILID` int(11) DEFAULT NULL,
  `NOTIFICATION` longtext,
  `CREATEDON` datetime DEFAULT NULL,
  `FLAG` varchar(255) DEFAULT NULL,
  `NOTIFICATIONANSWER` varchar(255) DEFAULT NULL,
  `NOTIFICATIONFLAG` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`NOTIFICATIONID`),
  KEY `FKAD9970EBB98D186` (`CLASSEVENTDETAILID`),
  CONSTRAINT `FKAD9970EBB98D186` FOREIGN KEY (`CLASSEVENTDETAILID`) REFERENCES `classeventdetail` (`CLASSEVENTDETAILID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,'hwhiteland5','senzit',34,'hello','2017-12-08 00:44:21','RH',NULL,NULL),(2,'hwhiteland5','senzit',34,'dfbdfbdfb','2017-12-08 00:49:24','RH',NULL,NULL),(3,'eruperto3','senzit',35,'hey','2017-12-08 01:03:24','RH',NULL,NULL),(4,'eruperto3','senzit',35,'hry','2017-12-08 01:20:43','RH',NULL,NULL),(5,'eruperto3','senzit',35,'yyyy','2017-12-08 01:24:49','RH',NULL,NULL),(6,'eruperto3','senzit',35,'yyyyuuu','2017-12-08 01:26:21','RH',NULL,NULL),(7,'eruperto3','senzit',35,'yeryeryer','2017-12-08 01:27:42','RH',NULL,NULL),(8,'eruperto3','senzit',35,'ewgewgweg','2017-12-08 01:29:49','RH',NULL,NULL),(9,'eruperto3','senzit',35,'eryeryery','2017-12-08 01:32:35','RH','dfhdfhdfh','RHQueue'),(10,'eruperto3','senzit',35,'trutyututururu','2017-12-08 01:33:13','RH','2','RHQueue'),(11,'eruperto3','senzit',35,'dfgdrgfed eryheryher','2017-12-08 01:33:28','RH','1','RHQueue'),(12,'eruperto3','senzit',37,'hey','2017-12-08 10:15:46','RH','dbsdsdbsb','RHQueue'),(13,'fmcmurrughg','senzit',38,'hi','2017-08-26 09:45:50','RH',NULL,NULL),(14,'eruperto3','senzit',39,'gdfgdg','2017-08-26 09:50:54','RH','hfhnfg','RHQueue'),(15,'fmcmurrughg','senzit',40,'scfsf','2017-08-26 09:55:42','RH','weerte','RHQueue'),(16,'fmcmurrughg','senzit',40,'ngfnfgtjft vfghftxhn','2017-08-26 09:57:14','RH','6','RHQueue'),(17,'fmcmurrughg','senzit',40,'khukmghk','2017-08-26 09:57:15','RH','5','RHQueue'),(18,'fmcmurrughg','senzit',40,'ncvnfgfghd snrn','2017-08-26 09:57:17','RH','4','RHQueue'),(19,'fmcmurrughg','senzit',40,'asdas hrthjfth','2017-08-26 09:57:20','RH','3','RHQueue'),(20,'fmcmurrughg','senzit',40,'dfgdfgbd dfhzyhdrtf ;oi[;','2017-08-26 09:57:23','RH','2','RHQueue'),(21,'cbuffyi','senzit',42,'hey','2017-08-26 10:07:20','RH','yes','RHQueue'),(22,'cbuffyi','senzit',42,'awrewrf wrwr','2017-08-26 10:07:33','RH','kjhkjgk','RHQueue'),(23,'lneilan4','senzit',46,'hello','2017-08-26 13:10:00','RH','hhgho','RHQueue'),(24,'lneilan4','senzit',46,'how are yuou?','2017-08-26 13:13:53','RH',NULL,NULL),(25,'lneilan4','senzit',46,'hey helo','2017-08-26 13:15:40','RH','yes','RHQueue'),(26,'lneilan4','senzit',46,'fdfgd dsgdfg','2017-08-26 13:16:44','RH',NULL,NULL),(27,'cbuffyi','senzit',47,'doubt','2017-08-26 13:21:14','RH','yes','RHQueue'),(28,'cbuffyi','senzit',47,'i have one doubt','2017-08-26 13:21:39','RH',NULL,NULL),(29,'senzit','senzit',47,'hello','2017-08-26 13:23:29','RN',NULL,NULL),(30,'eruperto3','senzit',50,'I have a doubt','2017-08-27 17:07:25','RH','yes.. Proceed..','RHQueue'),(31,'eruperto3','senzit',50,'What is Algibra?','2017-08-27 17:08:04','RH','Will discuss end of this session.','RHQueue'),(32,'eruperto3','senzit',53,'I have a doubt..','2017-08-28 07:19:36','RH','Yes.. Proceed..','RHQueue'),(33,'eruperto3','senzit',53,'What is Algibra?','2017-08-28 07:20:05','RH','Will discuss end of this session','RHQueue'),(34,'senzit','senzit',53,'First Class Note..','2017-08-28 07:21:58','RN',NULL,NULL),(35,'senzit','senzit',53,'Second Note..','2017-08-28 07:21:58','RN',NULL,NULL),(36,'senzit','senzit',53,'I have doubts in linear functions..','2017-01-02 07:25:00','doubt',NULL,NULL),(37,'senzit','senzit',54,'Recording from Claz Recorder..','2017-08-28 07:27:11','RN',NULL,NULL);
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `period`
--

DROP TABLE IF EXISTS `period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `period` (
  `PERIODID` varchar(255) NOT NULL,
  `STARTTIME` varchar(255) DEFAULT NULL,
  `ENDTIME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`PERIODID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `period`
--

LOCK TABLES `period` WRITE;
/*!40000 ALTER TABLE `period` DISABLE KEYS */;
INSERT INTO `period` VALUES ('1','540','600'),('10','1080','1140'),('11','1140','1200'),('12','1200','1260'),('13','1260','1320'),('14','1320','1380'),('15','1380','1440'),('2','600','660'),('3','660','720'),('4','720','780'),('5','840','900'),('6','900','960'),('7','960','1020'),('8','780','840'),('9','1020','1080');
/*!40000 ALTER TABLE `period` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `poll`
--

DROP TABLE IF EXISTS `poll`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `poll` (
  `POLLID` int(11) NOT NULL AUTO_INCREMENT,
  `POLLQUESTION` varchar(255) DEFAULT NULL,
  `CREATEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`POLLID`),
  KEY `FK3497BFCA87A7CF` (`CREATEDBY`),
  CONSTRAINT `FK3497BFCA87A7CF` FOREIGN KEY (`CREATEDBY`) REFERENCES `user` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `poll`
--

LOCK TABLES `poll` WRITE;
/*!40000 ALTER TABLE `poll` DISABLE KEYS */;
/*!40000 ALTER TABLE `poll` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `poll_option`
--

DROP TABLE IF EXISTS `poll_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `poll_option` (
  `OPTIONID` int(11) NOT NULL AUTO_INCREMENT,
  `OPTION_VALUE` varchar(255) DEFAULT NULL,
  `POLLID` int(11) DEFAULT NULL,
  PRIMARY KEY (`OPTIONID`),
  KEY `FKD4FE88F5FE7619F2` (`POLLID`),
  CONSTRAINT `FKD4FE88F5FE7619F2` FOREIGN KEY (`POLLID`) REFERENCES `poll` (`POLLID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `poll_option`
--

LOCK TABLES `poll_option` WRITE;
/*!40000 ALTER TABLE `poll_option` DISABLE KEYS */;
/*!40000 ALTER TABLE `poll_option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `poll_result`
--

DROP TABLE IF EXISTS `poll_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `poll_result` (
  `POLLRESULTID` int(11) NOT NULL AUTO_INCREMENT,
  `POLLOPTIONID` int(11) DEFAULT NULL,
  `USERID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`POLLRESULTID`),
  KEY `FKD981C89D8E92907C` (`POLLOPTIONID`),
  KEY `FKD981C89D750868B6` (`USERID`),
  CONSTRAINT `FKD981C89D750868B6` FOREIGN KEY (`USERID`) REFERENCES `user` (`USERID`),
  CONSTRAINT `FKD981C89D8E92907C` FOREIGN KEY (`POLLOPTIONID`) REFERENCES `poll_option` (`OPTIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `poll_result`
--

LOCK TABLES `poll_result` WRITE;
/*!40000 ALTER TABLE `poll_result` DISABLE KEYS */;
/*!40000 ALTER TABLE `poll_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privilege`
--

DROP TABLE IF EXISTS `privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `privilege` (
  `PRIVILEGEID` varchar(255) NOT NULL,
  `PRIVILEGENAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`PRIVILEGEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilege`
--

LOCK TABLES `privilege` WRITE;
/*!40000 ALTER TABLE `privilege` DISABLE KEYS */;
INSERT INTO `privilege` VALUES ('1','Recorder'),('2','Player'),('3','Attachment'),('4','Library'),('5','ReminderNotes'),('6','ClassNotes');
/*!40000 ALTER TABLE `privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `progressreports`
--

DROP TABLE IF EXISTS `progressreports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `progressreports` (
  `PROGRESSID` int(11) NOT NULL,
  `USERID` varchar(255) DEFAULT NULL,
  `SUBJECTID` varchar(255) DEFAULT NULL,
  `TERM` varchar(255) DEFAULT NULL,
  `MARKS` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`PROGRESSID`),
  KEY `FK9D0032F2750868B6` (`USERID`),
  KEY `FK9D0032F2D1AB1F8E` (`SUBJECTID`),
  CONSTRAINT `FK9D0032F2750868B6` FOREIGN KEY (`USERID`) REFERENCES `user` (`USERID`),
  CONSTRAINT `FK9D0032F2D1AB1F8E` FOREIGN KEY (`SUBJECTID`) REFERENCES `subject` (`SUBJECTID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `progressreports`
--

LOCK TABLES `progressreports` WRITE;
/*!40000 ALTER TABLE `progressreports` DISABLE KEYS */;
/*!40000 ALTER TABLE `progressreports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_option`
--

DROP TABLE IF EXISTS `question_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_option` (
  `OPTIONID` int(11) NOT NULL AUTO_INCREMENT,
  `OPTION_VALUE` varchar(255) DEFAULT NULL,
  `QNID` int(11) DEFAULT NULL,
  `ANSWERFLAG` bit(1) DEFAULT NULL,
  PRIMARY KEY (`OPTIONID`),
  KEY `FK6E1E3AEE1A5F4D6C` (`QNID`),
  CONSTRAINT `FK6E1E3AEE1A5F4D6C` FOREIGN KEY (`QNID`) REFERENCES `quiz_question` (`QNID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_option`
--

LOCK TABLES `question_option` WRITE;
/*!40000 ALTER TABLE `question_option` DISABLE KEYS */;
/*!40000 ALTER TABLE `question_option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz`
--

DROP TABLE IF EXISTS `quiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quiz` (
  `QUIZID` int(11) NOT NULL AUTO_INCREMENT,
  `SUBJECT` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `CREATEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`QUIZID`),
  KEY `FK352255CA87A7CF` (`CREATEDBY`),
  CONSTRAINT `FK352255CA87A7CF` FOREIGN KEY (`CREATEDBY`) REFERENCES `user` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz`
--

LOCK TABLES `quiz` WRITE;
/*!40000 ALTER TABLE `quiz` DISABLE KEYS */;
/*!40000 ALTER TABLE `quiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz_question`
--

DROP TABLE IF EXISTS `quiz_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quiz_question` (
  `QNID` int(11) NOT NULL AUTO_INCREMENT,
  `QN` varchar(255) DEFAULT NULL,
  `QUIZID` int(11) DEFAULT NULL,
  PRIMARY KEY (`QNID`),
  KEY `FK8BB1A907EE19E` (`QUIZID`),
  CONSTRAINT `FK8BB1A907EE19E` FOREIGN KEY (`QUIZID`) REFERENCES `quiz` (`QUIZID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz_question`
--

LOCK TABLES `quiz_question` WRITE;
/*!40000 ALTER TABLE `quiz_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `quiz_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz_user`
--

DROP TABLE IF EXISTS `quiz_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quiz_user` (
  `QUIZUSERID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(255) DEFAULT NULL,
  `QUIZID` int(11) DEFAULT NULL,
  `NO_OF_RIGHT_ANSWERS` int(11) DEFAULT NULL,
  PRIMARY KEY (`QUIZUSERID`),
  KEY `FK742DAF757EE19E` (`QUIZID`),
  KEY `FK742DAF759706BF66` (`USERNAME`),
  CONSTRAINT `FK742DAF757EE19E` FOREIGN KEY (`QUIZID`) REFERENCES `quiz` (`QUIZID`),
  CONSTRAINT `FK742DAF759706BF66` FOREIGN KEY (`USERNAME`) REFERENCES `user` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz_user`
--

LOCK TABLES `quiz_user` WRITE;
/*!40000 ALTER TABLE `quiz_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `quiz_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rebbon`
--

DROP TABLE IF EXISTS `rebbon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rebbon` (
  `REBBONID` varchar(255) NOT NULL,
  `REBBONLINK` varchar(255) DEFAULT NULL,
  `CLASSROOMID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`REBBONID`),
  UNIQUE KEY `REBBONLINK` (`REBBONLINK`),
  UNIQUE KEY `CLASSROOMID` (`CLASSROOMID`),
  KEY `FK8FD8BAD22F153D7C` (`CLASSROOMID`),
  CONSTRAINT `FK8FD8BAD22F153D7C` FOREIGN KEY (`CLASSROOMID`) REFERENCES `classroom` (`CLASSROOMID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rebbon`
--

LOCK TABLES `rebbon` WRITE;
/*!40000 ALTER TABLE `rebbon` DISABLE KEYS */;
INSERT INTO `rebbon` VALUES ('r101','127.0.0.1','101');
/*!40000 ALTER TABLE `rebbon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `ROLEID` varchar(255) NOT NULL,
  `ROLENAME` varchar(255) DEFAULT NULL,
  `ROLEDESCRIPTION` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ROLEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('1','Teacher',NULL),('2','Student',NULL),('3','Parent',NULL),('4','Manager',NULL),('5','Admin',NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roleprivilege`
--

DROP TABLE IF EXISTS `roleprivilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roleprivilege` (
  `ROLEPRIVILEGEID` varchar(255) NOT NULL,
  `ROLEID` varchar(255) DEFAULT NULL,
  `PRIVILEGEID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ROLEPRIVILEGEID`),
  KEY `FK198DB2DBD8F5BA98` (`PRIVILEGEID`),
  KEY `FK198DB2DB6FB3134C` (`ROLEID`),
  CONSTRAINT `FK198DB2DB6FB3134C` FOREIGN KEY (`ROLEID`) REFERENCES `role` (`ROLEID`),
  CONSTRAINT `FK198DB2DBD8F5BA98` FOREIGN KEY (`PRIVILEGEID`) REFERENCES `privilege` (`PRIVILEGEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roleprivilege`
--

LOCK TABLES `roleprivilege` WRITE;
/*!40000 ALTER TABLE `roleprivilege` DISABLE KEYS */;
INSERT INTO `roleprivilege` VALUES ('1','1','1'),('10','4','1'),('11','4','2'),('12','4','3'),('14','4','4'),('15','4','5'),('16','4','6'),('2','1','2'),('3','1','3'),('4','1','4'),('5','1','5'),('6','2','2'),('7','2','3'),('8','2','6'),('9','2','4');
/*!40000 ALTER TABLE `roleprivilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule` (
  `SCHEDULEID` int(11) NOT NULL,
  `DAYID` varchar(255) DEFAULT NULL,
  `PERIODID` varchar(255) DEFAULT NULL,
  `SUBJECTTEACHERID` int(11) DEFAULT NULL,
  `COURSEBATCHID` int(11) DEFAULT NULL,
  PRIMARY KEY (`SCHEDULEID`),
  KEY `FK50C82979E00B6D4` (`COURSEBATCHID`),
  KEY `FK50C829774FF39E2` (`PERIODID`),
  KEY `FK50C8297799CA26C` (`SUBJECTTEACHERID`),
  KEY `FK50C8297556365EE` (`DAYID`),
  CONSTRAINT `FK50C8297556365EE` FOREIGN KEY (`DAYID`) REFERENCES `day` (`DAYID`),
  CONSTRAINT `FK50C829774FF39E2` FOREIGN KEY (`PERIODID`) REFERENCES `period` (`PERIODID`),
  CONSTRAINT `FK50C8297799CA26C` FOREIGN KEY (`SUBJECTTEACHERID`) REFERENCES `subjectteacher` (`SUBJECTTEACHERID`),
  CONSTRAINT `FK50C82979E00B6D4` FOREIGN KEY (`COURSEBATCHID`) REFERENCES `coursebatch` (`COURSEBATCHID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (1,'1','1',1,101),(2,'1','2',12,101),(3,'1','3',11,101),(4,'1','4',15,101),(5,'1','5',13,101),(6,'1','6',2,101),(7,'1','7',14,101),(8,'2','1',3,101),(9,'2','2',16,101),(10,'2','3',14,101),(11,'2','4',17,101),(12,'2','5',16,101),(13,'2','6',11,101),(14,'2','7',1,101),(15,'3','1',3,101),(16,'3','2',2,101),(17,'3','3',15,101),(18,'3','4',12,101),(19,'3','5',2,101),(20,'3','6',15,101),(21,'3','7',16,101),(22,'4','1',1,101),(23,'4 ','2',13,101),(24,'4','3',14,101),(25,'4','4',12,101),(26,'4','5',3,101),(27,'4','6',2,101),(28,'4','7',11,101),(29,'5','1',3,101),(30,'5','2',13,101),(31,'5','3',15,101),(32,'5','4',3,101),(33,'5','5',1,101),(34,'5','6',2,101),(35,'5','7',11,101),(36,'6','1',1,101),(101,'1','1',1,NULL);
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semester`
--

DROP TABLE IF EXISTS `semester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `semester` (
  `SEMID` varchar(255) NOT NULL,
  `SEMNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`SEMID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester`
--

LOCK TABLES `semester` WRITE;
/*!40000 ALTER TABLE `semester` DISABLE KEYS */;
INSERT INTO `semester` VALUES ('1','Semester 1'),('2','Semester 2'),('3','Semester 3'),('4','Semester 4'),('5','Semester 5'),('6','Semester 6'),('7','Semester 7'),('8','Semester 8');
/*!40000 ALTER TABLE `semester` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sessions`
--

DROP TABLE IF EXISTS `sessions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sessions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `token` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sessions`
--

LOCK TABLES `sessions` WRITE;
/*!40000 ALTER TABLE `sessions` DISABLE KEYS */;
/*!40000 ALTER TABLE `sessions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studentbatch`
--

DROP TABLE IF EXISTS `studentbatch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `studentbatch` (
  `USERID` varchar(255) NOT NULL,
  `COURSEBATCHID` int(11) DEFAULT NULL,
  PRIMARY KEY (`USERID`),
  KEY `FK892269DF9E00B6D4` (`COURSEBATCHID`),
  KEY `FK892269DF750868B6` (`USERID`),
  CONSTRAINT `FK892269DF750868B6` FOREIGN KEY (`USERID`) REFERENCES `user` (`USERID`),
  CONSTRAINT `FK892269DF9E00B6D4` FOREIGN KEY (`COURSEBATCHID`) REFERENCES `coursebatch` (`COURSEBATCHID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentbatch`
--

LOCK TABLES `studentbatch` WRITE;
/*!40000 ALTER TABLE `studentbatch` DISABLE KEYS */;
INSERT INTO `studentbatch` VALUES ('astowte0',101),('brisingc',101),('cbuffyi',101),('ccrombleholmeh',101),('cgreen2',101),('dstruisj',101),('eruperto3',101),('fmcmurrughg',101),('gelion1',101),('hwhiteland5',101),('kdabinettd',101),('lneilan4',101),('lprothero7',101),('lzavattari8',101),('mgunther6',101),('nmacanellyee',101),('omorphetf',101),('rcuesta9',101),('tmalafeb',101),('wmcwarda',101),('ananninip',102),('aorrx',102),('bchishull11',102),('bfindlayz',102),('dclewettt',102),('djillingsl',102),('glandsborough13',102),('hblazewskim',102),('hbortolazzio',102),('kjensenv',102),('ljentgesq',102),('mbeark',102),('mcretney10',102),('mjojicu',102),('mmachansr',102),('mmingetn',102),('mstraffordw',102),('omcvittie12',102),('rdosdales',102),('rkenwelly',102);
/*!40000 ALTER TABLE `studentbatch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject` (
  `SUBJECTID` varchar(255) NOT NULL,
  `SUBJECTNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`SUBJECTID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES ('01','Engineering Mathematics I'),('02','Engineering Physics'),('03','Engineering Chemistry'),('04','Engineering Graphics'),('05','Basic Civil Engineering'),('06','Engineering Mechanics'),('07','Basic Mechanical Engineering'),('08','Basic Electrical and Electronics '),('09','Basic Communication and Information Engineering'),('10','Engineering Workshops'),('11','Other');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subjectperformance`
--

DROP TABLE IF EXISTS `subjectperformance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subjectperformance` (
  `PERFORMANCEID` int(11) NOT NULL,
  `USERID` varchar(255) DEFAULT NULL,
  `SUBJECTID` varchar(255) DEFAULT NULL,
  `RATING` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`PERFORMANCEID`),
  KEY `FK374A0784750868B6` (`USERID`),
  KEY `FK374A0784D1AB1F8E` (`SUBJECTID`),
  CONSTRAINT `FK374A0784750868B6` FOREIGN KEY (`USERID`) REFERENCES `user` (`USERID`),
  CONSTRAINT `FK374A0784D1AB1F8E` FOREIGN KEY (`SUBJECTID`) REFERENCES `subject` (`SUBJECTID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subjectperformance`
--

LOCK TABLES `subjectperformance` WRITE;
/*!40000 ALTER TABLE `subjectperformance` DISABLE KEYS */;
/*!40000 ALTER TABLE `subjectperformance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subjectteacher`
--

DROP TABLE IF EXISTS `subjectteacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subjectteacher` (
  `SUBJECTTEACHERID` int(11) NOT NULL,
  `USERID` varchar(255) DEFAULT NULL,
  `COURSESUBJECTID` int(11) DEFAULT NULL,
  `BATCHID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`SUBJECTTEACHERID`),
  KEY `FK2E671361FEFE2EA` (`BATCHID`),
  KEY `FK2E67136750868B6` (`USERID`),
  KEY `FK2E67136ED6A0F8` (`COURSESUBJECTID`),
  CONSTRAINT `FK2E671361FEFE2EA` FOREIGN KEY (`BATCHID`) REFERENCES `batch` (`BATCHID`),
  CONSTRAINT `FK2E67136750868B6` FOREIGN KEY (`USERID`) REFERENCES `user` (`USERID`),
  CONSTRAINT `FK2E67136ED6A0F8` FOREIGN KEY (`COURSESUBJECTID`) REFERENCES `coursesubject` (`COURSESUBJECTID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subjectteacher`
--

LOCK TABLES `subjectteacher` WRITE;
/*!40000 ALTER TABLE `subjectteacher` DISABLE KEYS */;
INSERT INTO `subjectteacher` VALUES (1,'senzit',1,'101'),(2,'senzit',3,'101'),(3,'senzit',8,'101'),(11,'abdullah',7,'101'),(12,'john',8,'101'),(13,'abdullah',9,'101'),(14,'abdullah',10,'101'),(15,'john',2,'101'),(16,'john',4,'101'),(17,'john',5,'101');
/*!40000 ALTER TABLE `subjectteacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `survey`
--

DROP TABLE IF EXISTS `survey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `survey` (
  `SURVEYID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `CREATEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`SURVEYID`),
  KEY `FKCAE3A75ACA87A7CF` (`CREATEDBY`),
  CONSTRAINT `FKCAE3A75ACA87A7CF` FOREIGN KEY (`CREATEDBY`) REFERENCES `user` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey`
--

LOCK TABLES `survey` WRITE;
/*!40000 ALTER TABLE `survey` DISABLE KEYS */;
/*!40000 ALTER TABLE `survey` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `survey_option`
--

DROP TABLE IF EXISTS `survey_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `survey_option` (
  `OPTIONID` int(11) NOT NULL AUTO_INCREMENT,
  `OPTION_VALUE` varchar(255) DEFAULT NULL,
  `QNID` int(11) DEFAULT NULL,
  PRIMARY KEY (`OPTIONID`),
  KEY `FK8E66C4FA88455B31` (`QNID`),
  CONSTRAINT `FK8E66C4FA88455B31` FOREIGN KEY (`QNID`) REFERENCES `survey_question` (`QUESTIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey_option`
--

LOCK TABLES `survey_option` WRITE;
/*!40000 ALTER TABLE `survey_option` DISABLE KEYS */;
/*!40000 ALTER TABLE `survey_option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `survey_question`
--

DROP TABLE IF EXISTS `survey_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `survey_question` (
  `QUESTIONID` int(11) NOT NULL AUTO_INCREMENT,
  `QUESTION` varchar(255) DEFAULT NULL,
  `SURVEYID` int(11) DEFAULT NULL,
  PRIMARY KEY (`QUESTIONID`),
  KEY `FK4F016B2BBE9EDC28` (`SURVEYID`),
  CONSTRAINT `FK4F016B2BBE9EDC28` FOREIGN KEY (`SURVEYID`) REFERENCES `survey` (`SURVEYID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey_question`
--

LOCK TABLES `survey_question` WRITE;
/*!40000 ALTER TABLE `survey_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `survey_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `survey_user`
--

DROP TABLE IF EXISTS `survey_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `survey_user` (
  `SURVEYUSERID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(255) DEFAULT NULL,
  `SURVEY_OPTION_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`SURVEYUSERID`),
  KEY `FK23528590A3F22B08` (`SURVEY_OPTION_ID`),
  KEY `FK235285909706BF66` (`USERNAME`),
  CONSTRAINT `FK235285909706BF66` FOREIGN KEY (`USERNAME`) REFERENCES `user` (`USERID`),
  CONSTRAINT `FK23528590A3F22B08` FOREIGN KEY (`SURVEY_OPTION_ID`) REFERENCES `survey_option` (`OPTIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey_user`
--

LOCK TABLES `survey_user` WRITE;
/*!40000 ALTER TABLE `survey_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `survey_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `targetattendancepercentage`
--

DROP TABLE IF EXISTS `targetattendancepercentage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `targetattendancepercentage` (
  `ATTENDANCEID` int(11) NOT NULL,
  `USERID` varchar(255) DEFAULT NULL,
  `COURSEBATCHID` int(11) DEFAULT NULL,
  `TARGETATTENDANCE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ATTENDANCEID`),
  KEY `FK3AD905549E00B6D4` (`COURSEBATCHID`),
  KEY `FK3AD90554750868B6` (`USERID`),
  CONSTRAINT `FK3AD90554750868B6` FOREIGN KEY (`USERID`) REFERENCES `user` (`USERID`),
  CONSTRAINT `FK3AD905549E00B6D4` FOREIGN KEY (`COURSEBATCHID`) REFERENCES `coursebatch` (`COURSEBATCHID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `targetattendancepercentage`
--

LOCK TABLES `targetattendancepercentage` WRITE;
/*!40000 ALTER TABLE `targetattendancepercentage` DISABLE KEYS */;
/*!40000 ALTER TABLE `targetattendancepercentage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `targetpasspercentage`
--

DROP TABLE IF EXISTS `targetpasspercentage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `targetpasspercentage` (
  `TARGETID` int(11) NOT NULL,
  `USERID` varchar(255) DEFAULT NULL,
  `COURSEBATCHID` int(11) DEFAULT NULL,
  `TARGET` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`TARGETID`),
  KEY `FKE771153C9E00B6D4` (`COURSEBATCHID`),
  KEY `FKE771153C750868B6` (`USERID`),
  CONSTRAINT `FKE771153C750868B6` FOREIGN KEY (`USERID`) REFERENCES `user` (`USERID`),
  CONSTRAINT `FKE771153C9E00B6D4` FOREIGN KEY (`COURSEBATCHID`) REFERENCES `coursebatch` (`COURSEBATCHID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `targetpasspercentage`
--

LOCK TABLES `targetpasspercentage` WRITE;
/*!40000 ALTER TABLE `targetpasspercentage` DISABLE KEYS */;
/*!40000 ALTER TABLE `targetpasspercentage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `targetprogress`
--

DROP TABLE IF EXISTS `targetprogress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `targetprogress` (
  `USERID` varchar(255) NOT NULL,
  `TARGET` varchar(255) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`USERID`),
  KEY `FKDF99F6BE750868B6` (`USERID`),
  CONSTRAINT `FKDF99F6BE750868B6` FOREIGN KEY (`USERID`) REFERENCES `user` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `targetprogress`
--

LOCK TABLES `targetprogress` WRITE;
/*!40000 ALTER TABLE `targetprogress` DISABLE KEYS */;
INSERT INTO `targetprogress` VALUES ('senzit','80% ','achieved');
/*!40000 ALTER TABLE `targetprogress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacherrecommendation`
--

DROP TABLE IF EXISTS `teacherrecommendation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacherrecommendation` (
  `TEACHERRID` int(11) NOT NULL,
  `USERID` varchar(255) DEFAULT NULL,
  `SUBJECTID` varchar(255) DEFAULT NULL,
  `TERM` varchar(255) DEFAULT NULL,
  `RATING` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`TEACHERRID`),
  KEY `FK9416981B750868B6` (`USERID`),
  KEY `FK9416981BD1AB1F8E` (`SUBJECTID`),
  CONSTRAINT `FK9416981B750868B6` FOREIGN KEY (`USERID`) REFERENCES `user` (`USERID`),
  CONSTRAINT `FK9416981BD1AB1F8E` FOREIGN KEY (`SUBJECTID`) REFERENCES `subject` (`SUBJECTID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacherrecommendation`
--

LOCK TABLES `teacherrecommendation` WRITE;
/*!40000 ALTER TABLE `teacherrecommendation` DISABLE KEYS */;
/*!40000 ALTER TABLE `teacherrecommendation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tempschedule`
--

DROP TABLE IF EXISTS `tempschedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tempschedule` (
  `TEMPSCHEDULEID` int(11) NOT NULL,
  `DAYID` varchar(255) DEFAULT NULL,
  `PERIODID` varchar(255) DEFAULT NULL,
  `SUBJECTTEACHERID` int(11) DEFAULT NULL,
  `COURSEBATCHID` int(11) DEFAULT NULL,
  PRIMARY KEY (`TEMPSCHEDULEID`),
  KEY `FKF255C26B9E00B6D4` (`COURSEBATCHID`),
  KEY `FKF255C26B74FF39E2` (`PERIODID`),
  KEY `FKF255C26B799CA26C` (`SUBJECTTEACHERID`),
  KEY `FKF255C26B556365EE` (`DAYID`),
  CONSTRAINT `FKF255C26B556365EE` FOREIGN KEY (`DAYID`) REFERENCES `day` (`DAYID`),
  CONSTRAINT `FKF255C26B74FF39E2` FOREIGN KEY (`PERIODID`) REFERENCES `period` (`PERIODID`),
  CONSTRAINT `FKF255C26B799CA26C` FOREIGN KEY (`SUBJECTTEACHERID`) REFERENCES `subjectteacher` (`SUBJECTTEACHERID`),
  CONSTRAINT `FKF255C26B9E00B6D4` FOREIGN KEY (`COURSEBATCHID`) REFERENCES `coursebatch` (`COURSEBATCHID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tempschedule`
--

LOCK TABLES `tempschedule` WRITE;
/*!40000 ALTER TABLE `tempschedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `tempschedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topperformer`
--

DROP TABLE IF EXISTS `topperformer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topperformer` (
  `TOPPERFORMERID` int(11) NOT NULL,
  `CLASSEVENTDETAILID` int(11) DEFAULT NULL,
  `NOOFTIMES` int(11) DEFAULT NULL,
  `USERID` varchar(255) DEFAULT NULL,
  `LIKES` int(11) DEFAULT NULL,
  PRIMARY KEY (`TOPPERFORMERID`),
  KEY `FK8778E19B98D186` (`CLASSEVENTDETAILID`),
  KEY `FK8778E19750868B6` (`USERID`),
  CONSTRAINT `FK8778E19750868B6` FOREIGN KEY (`USERID`) REFERENCES `user` (`USERID`),
  CONSTRAINT `FK8778E19B98D186` FOREIGN KEY (`CLASSEVENTDETAILID`) REFERENCES `classeventdetail` (`CLASSEVENTDETAILID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topperformer`
--

LOCK TABLES `topperformer` WRITE;
/*!40000 ALTER TABLE `topperformer` DISABLE KEYS */;
INSERT INTO `topperformer` VALUES (1,14,1,NULL,NULL),(2,42,1,NULL,NULL),(3,50,1,NULL,NULL),(4,51,2,NULL,NULL),(5,53,1,NULL,NULL);
/*!40000 ALTER TABLE `topperformer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topsession`
--

DROP TABLE IF EXISTS `topsession`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topsession` (
  `TOPSESSIONID` int(11) NOT NULL DEFAULT '0',
  `USERLIKE` int(11) DEFAULT NULL,
  `DISLIKE` int(11) DEFAULT NULL,
  `CLASSEVENTDETAILID` int(11) DEFAULT NULL,
  PRIMARY KEY (`TOPSESSIONID`),
  KEY `CLASSEVENTDETAILID` (`CLASSEVENTDETAILID`),
  CONSTRAINT `topsession_ibfk_1` FOREIGN KEY (`CLASSEVENTDETAILID`) REFERENCES `classeventdetail` (`CLASSEVENTDETAILID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topsession`
--

LOCK TABLES `topsession` WRITE;
/*!40000 ALTER TABLE `topsession` DISABLE KEYS */;
INSERT INTO `topsession` VALUES (1,1,0,53);
/*!40000 ALTER TABLE `topsession` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `USERID` varchar(255) NOT NULL,
  `FIRSTNAME` varchar(255) DEFAULT NULL,
  `LASTNAME` varchar(255) DEFAULT NULL,
  `PROFILEPIC` varchar(255) DEFAULT NULL,
  `DOB` varchar(255) DEFAULT NULL,
  `EMAILID` varchar(255) DEFAULT NULL,
  `MOBILENUMBER` varchar(255) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `REGFLAG` varchar(255) DEFAULT NULL,
  `MIDDLENAME` varchar(255) DEFAULT NULL,
  `ROLE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('abdullah','mohmd','abdullah','http://192.168.10.50:8080/ProfilePic/a1.png','06/02/1998','abdullah@gmail.com','097156478956','A2, Periyar Buiding, Technopark Campus,\nThiruvanathapuram, Kerala - 695581.','approved','aa','1'),('abid','abdullah','abid','http://192.168.10.50:8080/ProfilePic/a2.png','11/04/2000','abid@gmail.com','097112485477','A2, Periyar Buiding, Technopark Campus,\nThiruvanathapuram, Kerala - 695581.','approved','abid','1'),('ananninip','Alfredo','Ubsdale','http://192.168.10.50:8080/ProfilePic/a3.png','07/22/1366','ananninip@tripadvisor.com','63-(808)789-1450','44389 Stephen Terrace','approved','Nannini','2'),('aorrx','Allayne','Macbeth','http://192.168.10.50:8080/ProfilePic/a4.png','08/23/1523','aorrx@bloglovin.com','62-(604)184-3974','0 Elgar Court','approved','Orr','2'),('astowte0','Audrie','Child','http://192.168.10.50:8080/ProfilePic/a5.png','03/10/1911','astowte0@sfgate.com','63-(622)618-5594','773 Onsgard Avenue','approved','Stowte','2'),('bchishull11','Bernadette','Kupker','http://192.168.10.50:8080/ProfilePic/a6.png','07/07/1963','bchishull11@vk.com','49-(376)231-5363','67 Daystar Alley','approved','Chishull','2'),('bfindlayz','Boone','Livick','http://192.168.10.50:8080/ProfilePic/a7.png','01/21/1440','bfindlayz@oracle.com','81-(360)120-2228','501 Springs Alley','approved','Findlay','2'),('brisingc','Barnabas','Woolacott','http://192.168.10.50:8080/ProfilePic/a8.png','01/14/1244','brisingc@ca.gov','687-(320)882-1399','2 Mayer Parkway','approved','Rising','2'),('cbuffyi','Chaim','Shea','http://192.168.10.50:8080/ProfilePic/a9.png','02/04/1552','cbuffyi@wired.com','36-(504)492-4453','4060 8th Alley','approved','Buffy','2'),('ccrombleholmeh','Carleen','Talmadge','http://192.168.10.50:8080/ProfilePic/a1.png','02/06/1138','ccrombleholmeh@latimes.com','62-(184)852-9652','45 1st Junction','approved','Crombleholme','2'),('cgreen2','Cyril','Kondratovich','http://192.168.10.50:8080/ProfilePic/a2.png','04/13/1928','cgreen2@cbc.ca','389-(974)341-8855','2 Hollow Ridge Way','approved','Green','2'),('dclewettt','De witt','Rosario','http://192.168.10.50:8080/ProfilePic/a3.png','12/07/1967','dclewettt@accuweather.com','31-(450)230-8851','1621 Hollow Ridge Park','approved','Clewett','2'),('djillingsl','Dacia','Barlie','http://192.168.10.50:8080/ProfilePic/a4.png','01/20/1841','djillingsl@alexa.com','7-(921)609-5200','08 Washington Crossing','approved','Jillings','2'),('dstruisj','Dore','Sutlieff','http://192.168.10.50:8080/ProfilePic/a5.png','01/02/1160','dstruisj@dion.ne.jp','420-(988)255-5892','22764 Kenwood Avenue','approved','Struis','2'),('eruperto3','Ermina','Sheekey','http://192.168.10.50:8080/ProfilePic/a6.png','12/20/1113','eruperto3@dmoz.org','675-(947)447-6185','142 Merchant Avenue','approved','Ruperto','2'),('fmcmurrughg','Francyne','Rippingall','http://192.168.10.50:8080/ProfilePic/a7.png','08/04/1643','fmcmurrughg@wordpress.com','55-(665)404-1613','855 Warner Terrace','approved','McMurrugh','2'),('gelion1','Griffin','McAlester','http://192.168.10.50:8080/ProfilePic/a8.png','05/15/1781','gelion1@fc2.com','46-(439)274-3558','141 Crescent Oaks Avenue','approved','Elion','2'),('glandsborough13','Gallard','Thormann','http://192.168.10.50:8080/ProfilePic/a9.png','12/09/1281','glandsborough13@goo.gl','46-(474)272-9783','3 Clove Terrace','approved','Landsborough','2'),('hblazewskim','Hillard','Kelbie','http://192.168.10.50:8080/ProfilePic/a1.png','12/21/1790','hblazewskim@comsenz.com','63-(848)289-4274','5 Oneill Point','approved','Blazewski','2'),('hbortolazzio','Hymie','Bahl','http://192.168.10.50:8080/ProfilePic/a2.png','02/07/1517','hbortolazzio@dyndns.org','62-(518)736-5728','8566 Donald Park','approved','Bortolazzi','2'),('hwhiteland5','Hartwell','Raatz','http://192.168.10.50:8080/ProfilePic/a3.png','04/14/1303','hwhiteland5@slate.com','1-(501)497-8587','30 Westport Way','approved','Whiteland','2'),('john','john','hh','http://192.168.10.50:8080/ProfilePic/a4.png','14/02/1956','john@gmail.com','1478523698745','sdrwetertrtertrt','approved','honnay','1'),('kdabinettd','Kent','McVittie','http://192.168.10.50:8080/ProfilePic/a5.png','05/29/1088','kdabinettd@chron.com','46-(914)907-2808','78 Summit Alley','approved','Dabinett','2'),('kjensenv','Kalle','Purkins','http://192.168.10.50:8080/ProfilePic/a6.png','12/03/1898','kjensenv@jiathis.com','976-(695)535-6145','0451 Prentice Street','approved','Jensen','2'),('ljentgesq','Laurette','Slopier','http://192.168.10.50:8080/ProfilePic/a7.png','11/18/1344','ljentgesq@squarespace.com','1-(619)920-1583','3 Glacier Hill Way','approved','Jentges','2'),('lneilan4','Lucky','Kinlock','http://192.168.10.50:8080/ProfilePic/a8.png','10/11/1368','lneilan4@ebay.co.uk','7-(380)472-7571','16689 Fisk Crossing','approved','Neilan','2'),('lprothero7','Lorita','Grabb','http://192.168.10.50:8080/ProfilePic/a9.png','07/04/1132','lprothero7@yellowpages.com','56-(869)853-5377','07 Corben Place','approved','Prothero','2'),('lzavattari8','Lavinia','Cutmere','http://192.168.10.50:8080/ProfilePic/a1.png','06/29/1207','lzavattari8@webs.com','86-(420)993-2007','440 Meadow Vale Way','approved','Zavattari','2'),('manager','manager','mm','http://192.168.10.50:8080/ProfilePic/a2.png','1992-09-12','manager@gmail.com','9896352174','Senzit Solutions Pvt Ltd, A2 Periyar Building','approved','mm','2'),('mbeark','Marget','Heber','http://192.168.10.50:8080/ProfilePic/a3.png','01/05/1628','mbeark@linkedin.com','48-(264)498-4489','94 Mayer Court','approved','Bear','4'),('mcretney10','Mathe','Piggrem','http://192.168.10.50:8080/ProfilePic/a10.png','03/02/1801','mcretney10@tinyurl.com','86-(494)393-4486','97444 Sommers Hill','approved','Cretney','2'),('mgunther6','Minnie','Nester','http://192.168.10.50:8080/ProfilePic/a10.png','04/09/1286','mgunther6@accuweather.com','225-(523)508-1050','240 Upham Drive','approved','Gunther','2'),('mjojicu','Melisse','Matuszyk','http://192.168.10.50:8080/ProfilePic/a10.png','04/29/1820','mjojicu@about.com','216-(808)761-2708','16373 Nelson Way','approved','Jojic','2'),('mmachansr','Matthaeus','Gransden','http://192.168.10.50:8080/ProfilePic/a10.png','04/12/1504','mmachansr@bigcartel.com','63-(340)448-8438','5193 Eggendart Road','approved','Machans','2'),('mmingetn','Madelyn','Cattroll','http://192.168.10.50:8080/ProfilePic/a10.png','04/03/1175','mmingetn@army.mil','55-(598)492-9190','5357 Brown Place','approved','Minget','2'),('mstraffordw','Margi','Janus','http://192.168.10.50:8080/ProfilePic/a10.png','05/17/1262','mstraffordw@umich.edu','961-(834)428-3279','260 Sauthoff Place','approved','Strafford','2'),('nmacanellyee','Nathanael','Cuolahan','http://192.168.10.50:8080/ProfilePic/a10.png','01/29/1257','nmacanellyee@sina.com.cn','86-(339)258-6974','701 Havey Hill','approved','MacAnellye','2'),('omcvittie12','Ole','Haining','http://192.168.10.50:8080/ProfilePic/a10.png','05/12/1036','omcvittie12@merriam-webster.com','33-(236)357-4355','923 Badeau Center','approved','McVittie','2'),('omorphetf','Olimpia','Sheather','http://192.168.10.50:8080/ProfilePic/a10.png','06/30/1031','omorphetf@dell.com','63-(110)718-1159','4 Sherman Parkway','approved','Morphet','2'),('rcuesta9','Rosalyn','Copcote','http://192.168.10.50:8080/ProfilePic/a10.png','01/06/1447','rcuesta9@macromedia.com','84-(389)673-0609','23 Gulseth Pass','approved','Cuesta','2'),('rdosdales','Roderich','Sarl','http://192.168.10.50:8080/ProfilePic/a10.png','03/17/1133','rdosdales@lycos.com','51-(224)840-5353','3 Sage Lane','approved','Dosdale','2'),('rkenwelly','Roldan','Peabody','http://192.168.10.50:8080/ProfilePic/a10.png','09/02/1742','rkenwelly@timesonline.co.uk','86-(744)867-1886','675 Chinook Center','approved','Kenwell','2'),('senzit','SenzIT','Solutions','http://192.168.10.50:8080/ProfilePic/a1.png','1236-07-02','senzitemailid@email.com','9895198951','A2, Periyar Building, Technopark Campus,\nThiruvananthapuram, Kerala, India - 695581','approved','India','1'),('tmalafeb','Tawsha','Hedde','http://192.168.10.50:8080/ProfilePic/a10.png','02/16/0999','tmalafeb@nature.com','61-(657)743-9970','60 La Follette Parkway','approved','Malafe','2'),('wmcwarda','Wittie','Stansbie','http://192.168.10.50:8080/ProfilePic/a10.png','04/23/1220','wmcwarda@globo.com','33-(776)443-6844','7 Tennessee Lane','approved','McWard','2');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userlogin`
--

DROP TABLE IF EXISTS `userlogin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userlogin` (
  `USERID` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `CREATEDON` datetime DEFAULT NULL,
  `MODIFIEDON` datetime DEFAULT NULL,
  PRIMARY KEY (`USERID`),
  KEY `FKBB09BA5E750868B6` (`USERID`),
  CONSTRAINT `FKBB09BA5E750868B6` FOREIGN KEY (`USERID`) REFERENCES `user` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userlogin`
--

LOCK TABLES `userlogin` WRITE;
/*!40000 ALTER TABLE `userlogin` DISABLE KEYS */;
INSERT INTO `userlogin` VALUES ('ananninip','password',NULL,NULL),('aorrx','password',NULL,NULL),('astowte0','password',NULL,NULL),('bchishull11','password',NULL,NULL),('bfindlayz','password',NULL,NULL),('brisingc','password',NULL,NULL),('cbuffyi','password',NULL,NULL),('ccrombleholmeh','password',NULL,NULL),('cgreen2','password',NULL,NULL),('dclewettt','password',NULL,NULL),('djillingsl','password',NULL,NULL),('dstruisj','password',NULL,NULL),('eruperto3','password',NULL,NULL),('fmcmurrughg','password',NULL,NULL),('gelion1','password',NULL,NULL),('glandsborough13','password',NULL,NULL),('hblazewskim','password',NULL,NULL),('hbortolazzio','password',NULL,NULL),('hwhiteland5','password',NULL,NULL),('kdabinettd','password',NULL,NULL),('kjensenv','password',NULL,NULL),('ljentgesq','password',NULL,NULL),('lneilan4','password',NULL,NULL),('lprothero7','password',NULL,NULL),('lzavattari8','password',NULL,NULL),('manager','password',NULL,NULL),('mbeark','password',NULL,NULL),('mcretney10','password',NULL,NULL),('mgunther6','password',NULL,NULL),('mjojicu','password',NULL,NULL),('mmachansr','password',NULL,NULL),('mmingetn','password',NULL,NULL),('mstraffordw','password',NULL,NULL),('nmacanellyee','password',NULL,NULL),('omcvittie12','password',NULL,NULL),('omorphetf','password',NULL,NULL),('rcuesta9','password',NULL,NULL),('rdosdales','password',NULL,NULL),('rkenwelly','password',NULL,NULL),('senzit','password',NULL,NULL),('tmalafeb','password',NULL,NULL),('wmcwarda','password',NULL,NULL);
/*!40000 ALTER TABLE `userlogin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userrole`
--

DROP TABLE IF EXISTS `userrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userrole` (
  `USERID` varchar(255) NOT NULL,
  `ROLEID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`USERID`),
  KEY `FK1ED17EC16FB3134C` (`ROLEID`),
  KEY `FK1ED17EC1750868B6` (`USERID`),
  CONSTRAINT `FK1ED17EC16FB3134C` FOREIGN KEY (`ROLEID`) REFERENCES `role` (`ROLEID`),
  CONSTRAINT `FK1ED17EC1750868B6` FOREIGN KEY (`USERID`) REFERENCES `user` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userrole`
--

LOCK TABLES `userrole` WRITE;
/*!40000 ALTER TABLE `userrole` DISABLE KEYS */;
INSERT INTO `userrole` VALUES ('senzit','1'),('ananninip','2'),('aorrx','2'),('astowte0','2'),('bchishull11','2'),('bfindlayz','2'),('brisingc','2'),('cbuffyi','2'),('ccrombleholmeh','2'),('cgreen2','2'),('dclewettt','2'),('djillingsl','2'),('dstruisj','2'),('eruperto3','2'),('fmcmurrughg','2'),('gelion1','2'),('glandsborough13','2'),('hblazewskim','2'),('hbortolazzio','2'),('hwhiteland5','2'),('kdabinettd','2'),('kjensenv','2'),('ljentgesq','2'),('lneilan4','2'),('lprothero7','2'),('lzavattari8','2'),('mbeark','2'),('mcretney10','2'),('mgunther6','2'),('mjojicu','2'),('mmachansr','2'),('mmingetn','2'),('mstraffordw','2'),('nmacanellyee','2'),('omcvittie12','2'),('omorphetf','2'),('rcuesta9','2'),('rdosdales','2'),('rkenwelly','2'),('tmalafeb','2'),('wmcwarda','2'),('manager','4');
/*!40000 ALTER TABLE `userrole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videorating`
--

DROP TABLE IF EXISTS `videorating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `videorating` (
  `RATINGID` int(11) NOT NULL,
  `VIDEORATE` varchar(255) DEFAULT NULL,
  `CLASSEVENTDETAILID` int(11) DEFAULT NULL,
  PRIMARY KEY (`RATINGID`),
  KEY `FKF258D1B8B98D186` (`CLASSEVENTDETAILID`),
  CONSTRAINT `FKF258D1B8B98D186` FOREIGN KEY (`CLASSEVENTDETAILID`) REFERENCES `classeventdetail` (`CLASSEVENTDETAILID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videorating`
--

LOCK TABLES `videorating` WRITE;
/*!40000 ALTER TABLE `videorating` DISABLE KEYS */;
/*!40000 ALTER TABLE `videorating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `viewers`
--

DROP TABLE IF EXISTS `viewers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `viewers` (
  `VIEWERID` int(11) NOT NULL,
  `USERID` varchar(255) DEFAULT NULL,
  `CLASSEVENTDETAILID` int(11) DEFAULT NULL,
  `USERRATECOUNT` int(11) DEFAULT NULL,
  `TEACHERNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`VIEWERID`),
  KEY `FK1B1310A1750868B6` (`USERID`),
  KEY `FK1B1310A1B98D186` (`CLASSEVENTDETAILID`),
  CONSTRAINT `FK1B1310A1750868B6` FOREIGN KEY (`USERID`) REFERENCES `user` (`USERID`),
  CONSTRAINT `FK1B1310A1B98D186` FOREIGN KEY (`CLASSEVENTDETAILID`) REFERENCES `classeventdetail` (`CLASSEVENTDETAILID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `viewers`
--

LOCK TABLES `viewers` WRITE;
/*!40000 ALTER TABLE `viewers` DISABLE KEYS */;
INSERT INTO `viewers` VALUES (1,'senzit',14,0,'senzit'),(2,'senzit',42,0,'senzit'),(3,'senzit',50,0,'senzit'),(4,'senzit',51,0,'senzit'),(5,'senzit',53,1,'senzit');
/*!40000 ALTER TABLE `viewers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-28 10:07:29
