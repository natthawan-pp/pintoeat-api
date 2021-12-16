-- MySQL dump 10.13  Distrib 5.5.27, for Win64 (x86)
--
-- Host: localhost    Database: pintoeatdb
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
-- Table structure for table `folder`
--

DROP TABLE IF EXISTS `folder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `folder` (
  `id` varchar(50) NOT NULL,
  `user_id` varchar(50) NOT NULL,
  `name_en` varchar(255) DEFAULT NULL,
  `name_th` varchar(255) DEFAULT NULL,
  `is_favorite` bit(1) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_fk` (`user_id`),
  CONSTRAINT `user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folder`
--

LOCK TABLES `folder` WRITE;
/*!40000 ALTER TABLE `folder` DISABLE KEYS */;
INSERT INTO `folder` VALUES ('ec890ec0-06ef-410b-a6fc-48ec3d699e69','cc301e84-9d16-40f2-ad9c-ba5dda141aa',NULL,'รวมร้านชาบู','','2021-12-16','2021-12-16');
/*!40000 ALTER TABLE `folder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image` (
  `id` varchar(50) NOT NULL,
  `pin_id` varchar(50) NOT NULL,
  `image` blob,
  `priority` int(1) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pin_id_pk` (`pin_id`),
  CONSTRAINT `pin_id_pk` FOREIGN KEY (`pin_id`) REFERENCES `pin` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES ('493be8ca-2792-479d-8184-8a84e0f45a9a','e56b3063-1b1b-43c0-8f77-e41fe7a0643d',NULL,1,'2021-12-16','2021-12-16'),('f7ed6f87-7261-4cf8-a6cc-1e85400b92af','e56b3063-1b1b-43c0-8f77-e41fe7a0643d',NULL,2,'2021-12-16','2021-12-16');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pin`
--

DROP TABLE IF EXISTS `pin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pin` (
  `id` varchar(50) NOT NULL,
  `folder_id` varchar(50) NOT NULL,
  `name_en` varchar(255) DEFAULT NULL,
  `name_th` varchar(255) DEFAULT NULL,
  `description_en` varchar(255) DEFAULT NULL,
  `description_th` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `is_favorite` bit(1) DEFAULT NULL,
  `is_bookmark` bit(1) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `folder_id_pk` (`folder_id`),
  CONSTRAINT `folder_id_pk` FOREIGN KEY (`folder_id`) REFERENCES `folder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pin`
--

LOCK TABLES `pin` WRITE;
/*!40000 ALTER TABLE `pin` DISABLE KEYS */;
INSERT INTO `pin` VALUES ('5ee1f777-52a7-46b5-bd57-4ffa23b3c728','ec890ec0-06ef-410b-a6fc-48ec3d699e69',NULL,'ร้าน FUFU SHABU สาขา Empire Tower',NULL,'อยู่ที่ตึก Empire Tower บริเวณชั้น 3 สำหรับบุฟเฟท์ที่นี่มีให้เลือกทั้งหมด 3 ราคาด้วยกันคือ ราคา 499 บาท++ ราคา 699 บาท++ และราคา 999 บาท++','13.720983596550681, 100.5299222539757','','\0','2021-12-16','2021-12-16'),('e56b3063-1b1b-43c0-8f77-e41fe7a0643d','ec890ec0-06ef-410b-a6fc-48ec3d699e69',NULL,'ร้าน Kagonoya Market Place ทองหล่อ',NULL,'จุดเด่นของร้านนี้จะอยู่ตรงที่น้ำซุป น้ำจิ้ม และเมนูอาหารต่างๆ ของทางร้านและเมนูเนื้อนำเข้าทั้งเนื้อ AUS เนื้อ US และเนื้อ Wagyu คุณภาพดี','13.729190129757892, 100.5811625460243','','\0','2021-12-16','2021-12-16');
/*!40000 ALTER TABLE `pin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` varchar(50) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` int(10) DEFAULT NULL,
  `gender` varchar(1) DEFAULT NULL,
  `is_confirm_email` bit(1) DEFAULT NULL,
  `image` blob,
  `is_login_by_facebook` bit(1) DEFAULT NULL,
  `is_login_by_gmail` bit(1) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('cc301e84-9d16-40f2-ad9c-ba5dda141aa','bob@gmail.com',123,'M','',NULL,'\0','\0','2021-12-16','2021-12-16');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'pintoeatdb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-16 16:43:53
