-- MySQL dump 10.13  Distrib 5.5.27, for Win64 (x86)
--
-- Host: localhost    Database: train
-- ------------------------------------------------------
-- Server version	5.5.27

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
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `deptno` decimal(10,0) NOT NULL DEFAULT '0' COMMENT 'à¸£à¸«à¸±à¸ªà¹?à¸?à¸?à¸?',
  `dname` varchar(100) DEFAULT NULL COMMENT 'à¸?à¸·à¹?à¸­à¹?à¸?à¸?à¸?',
  `loc` varchar(100) DEFAULT NULL COMMENT 'à¸ªà¸–à¸²à¸?à¸—à¸µà¹?à¸•à¸±à¹?à¸?',
  PRIMARY KEY (`deptno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='à¸•à¸²à¸£à¸²à¸?à¹?à¸?à¸?à¸?';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (70,'ADD',NULL),(71,'PM',NULL),(72,'Trainning',NULL),(73,'Sale',NULL),(101,NULL,NULL),(102,NULL,NULL),(103,NULL,NULL),(104,NULL,NULL);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dept`
--

DROP TABLE IF EXISTS `dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dept` (
  `id` decimal(10,0) NOT NULL DEFAULT '0' COMMENT 'à¸£à¸«à¸±à¸ªà¸•à¸³à¹?à¸«à¸?à¹?à¸?',
  `position_name` varchar(100) DEFAULT NULL COMMENT 'à¸?à¸·à¹?à¸­à¸•à¸³à¹?à¸«à¸?à¹?à¸?',
  `dept_code` decimal(10,0) DEFAULT NULL COMMENT 'à¸£à¸«à¸±à¸ªà¹?à¸?à¸?à¸?',
  `position_id` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dept`
--

LOCK TABLES `dept` WRITE;
/*!40000 ALTER TABLE `dept` DISABLE KEYS */;
INSERT INTO `dept` VALUES (40,'Programmer',700,NULL),(41,'Programmer Analyst',701,NULL),(42,'System Analyst',702,NULL),(43,'System Engineer',703,NULL);
/*!40000 ALTER TABLE `dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `employee_no` varchar(100) NOT NULL,
  `employee_name` varchar(100) DEFAULT NULL,
  `job` varchar(100) DEFAULT NULL,
  `manager_name` varchar(100) DEFAULT NULL,
  `hire_date` date DEFAULT NULL,
  `salary` decimal(10,0) DEFAULT NULL,
  `department_no` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`employee_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('1','Dollar','Saleman','Peter','1978-04-11',10000,73),('10','Toery','SE','Charn','1978-01-01',19000,102),('2','Smith','Programmer','John','1989-10-14',12000,70),('3','Obama','Programmer','John','1990-03-22',12500,70),('4','Ken','Saleman','Peter','1985-07-15',14000,73),('5','Rooney','Saleman','Peter','1979-08-08',17000,73),('6','Herry','Admin','Conta','1995-09-21',14500,71),('7','Ramsy','Admin','Conta','1987-12-30',18000,71),('8','Somchai','SE','Charn','1973-05-20',18000,101),('9','PornChai','SE','Charn','1987-02-25',16000,104);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `people`
--

DROP TABLE IF EXISTS `people`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `people` (
  `id` decimal(10,0) NOT NULL DEFAULT '0' COMMENT 'à¸£à¸«à¸±à¸ª',
  `first_name` varchar(100) DEFAULT NULL COMMENT 'à¸?à¸·à¹?à¸­',
  `last_name` varchar(100) DEFAULT NULL COMMENT 'à¸?à¸²à¸¡à¸ªà¸?à¸¸à¸¥',
  `dept_code` decimal(10,0) DEFAULT NULL,
  `dept_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `people`
--

LOCK TABLES `people` WRITE;
/*!40000 ALTER TABLE `people` DISABLE KEYS */;
INSERT INTO `people` VALUES (900,'Ampai','Jimprajong',700,NULL),(901,'Katika','Sumrittikul',703,NULL),(902,'Piyarat','Kulngamdee',700,NULL),(903,'pawarut','klaiarmon',701,NULL),(904,'Pawitra','Imcharoenkul',702,NULL),(905,'Manassiyaporn','Amornwechsavedtaporn',700,NULL),(906,'suwit','suriyasomboon',703,NULL),(907,'suttipong','kornrapat',701,NULL),(908,'Supamas','Manoonpol',700,NULL),(909,'Suphakorn','Amphava',702,NULL),(910,'Anjali','Lalwani',702,NULL);
/*!40000 ALTER TABLE `people` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `region`
--

DROP TABLE IF EXISTS `region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `region` (
  `id` decimal(10,0) NOT NULL DEFAULT '0' COMMENT 'à¸£à¸«à¸±à¸ª',
  `name` varchar(100) DEFAULT NULL COMMENT 'à¸?à¸·à¹?à¸­à¸•à¸¶à¸?',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region`
--

LOCK TABLES `region` WRITE;
/*!40000 ALTER TABLE `region` DISABLE KEYS */;
INSERT INTO `region` VALUES (100,'CDG'),(101,'Sathorn'),(102,'Empire Tower'),(103,'TPI');
/*!40000 ALTER TABLE `region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_customer`
--

DROP TABLE IF EXISTS `s_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_customer` (
  `id` decimal(10,0) NOT NULL DEFAULT '0' COMMENT 'à¸£à¸«à¸±à¸ª',
  `name` varchar(100) DEFAULT NULL COMMENT 'à¸?à¸·à¹?à¸­',
  `phone` varchar(100) DEFAULT NULL COMMENT 'à¹?à¸—à¸£à¸¨à¸±à¸?à¸—à¹?',
  `address` varchar(100) DEFAULT NULL COMMENT 'à¸—à¸µà¹?à¸­à¸¢à¸¹à¹?',
  `state` varchar(100) DEFAULT NULL COMMENT 'à¸ªà¸–à¸²à¸?à¸°',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_customer`
--

LOCK TABLES `s_customer` WRITE;
/*!40000 ALTER TABLE `s_customer` DISABLE KEYS */;
INSERT INTO `s_customer` VALUES (10,'Pattaporn','02-4588974','',''),(11,'Wassana','','',''),(12,'Natcha','099-45229741',NULL,NULL),(13,'Sukrit','02-4229804','',NULL),(14,'Winit','','',NULL),(15,'Chinja','099-5349043',NULL,NULL);
/*!40000 ALTER TABLE `s_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_dept`
--

DROP TABLE IF EXISTS `s_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_dept` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `region_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_dept`
--

LOCK TABLES `s_dept` WRITE;
/*!40000 ALTER TABLE `s_dept` DISABLE KEYS */;
INSERT INTO `s_dept` VALUES (1,'ADD',1),(2,'BI',1),(3,'HR',2),(4,'NETWORK',4),(5,'PM',3);
/*!40000 ALTER TABLE `s_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_emp`
--

DROP TABLE IF EXISTS `s_emp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_emp` (
  `emp_id` int(11) NOT NULL,
  `dept_id` int(11) DEFAULT NULL,
  `salary` decimal(10,0) DEFAULT NULL,
  `region_id` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_emp`
--

LOCK TABLES `s_emp` WRITE;
/*!40000 ALTER TABLE `s_emp` DISABLE KEYS */;
INSERT INTO `s_emp` VALUES (1,1,7000,'01'),(2,2,7000,'01'),(3,3,9000,'02'),(4,4,12000,'04'),(5,5,30000,'03'),(6,5,35000,'01'),(7,2,8000,'01'),(8,3,8000,'02');
/*!40000 ALTER TABLE `s_emp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_ord`
--

DROP TABLE IF EXISTS `s_ord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_ord` (
  `id` decimal(10,0) NOT NULL DEFAULT '0' COMMENT 'à¸£à¸«à¸±à¸ª',
  `customer_id` decimal(10,0) DEFAULT NULL,
  `payment_type` varchar(20) DEFAULT NULL COMMENT 'à¸?à¸£à¸°à¹€à¸ à¸—à¸?à¸²à¸£à¸?à¹?à¸²à¸¢à¹€à¸?à¸´à¸?',
  `amount` decimal(10,0) DEFAULT NULL COMMENT 'à¸?à¸³à¸?à¸§à¸?à¹€à¸?à¸´à¸?',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_ord`
--

LOCK TABLES `s_ord` WRITE;
/*!40000 ALTER TABLE `s_ord` DISABLE KEYS */;
INSERT INTO `s_ord` VALUES (150,10,'Cash',4000),(151,10,'Credit',3000),(152,12,'Cash',900),(153,12,'Cash',2000),(154,12,'Cash',2500),(155,10,'Credit',10000),(156,14,'Cash',400),(157,14,'Credit',3200);
/*!40000 ALTER TABLE `s_ord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s_region`
--

DROP TABLE IF EXISTS `s_region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_region` (
  `id` varchar(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_region`
--

LOCK TABLES `s_region` WRITE;
/*!40000 ALTER TABLE `s_region` DISABLE KEYS */;
INSERT INTO `s_region` VALUES ('01','CDG HOUSE'),('02','CDG SATHON'),('03','LPN'),('04','PANJATANEE');
/*!40000 ALTER TABLE `s_region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'train'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-05 21:01:44
