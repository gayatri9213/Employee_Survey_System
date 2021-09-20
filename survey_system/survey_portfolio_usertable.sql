-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: survey_portfolio
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `usertable`
--

DROP TABLE IF EXISTS `usertable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usertable` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `user_email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `phone_no` varchar(45) NOT NULL,
  `role_name` varchar(45) NOT NULL,
  `gender` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usertable`
--

LOCK TABLES `usertable` WRITE;
/*!40000 ALTER TABLE `usertable` DISABLE KEYS */;
INSERT INTO `usertable` VALUES (1,'Prachi','prachi@gmail.com','\0\"1389de','1234567890','Manager','Female','Pune',NULL),(3,'Gayatri','gayatri@gamil.com','1)1$\"9cd','1234567890','Manager','Female','Nashik',NULL),(4,'Moiza','moiza@gmail.com','?9*1de','0987654321','Manager','Female','pune',NULL),(5,'Anjali','anjali@gmail.com','>:1<9ba','1234567890','Manager','Female','Nashik','null'),(6,'Atharva','atharva@gmail.com','$81\"&1cd','0987654321','Admin','Male','Pune','null'),(7,'Nandita','nandita@gmail.com','1>49$1cd','1234567890','Developer','Female','Nashik','Pending'),(9,'Mayur','mayur@gmail.com','1)%\"de','7249899915','Admin','Male','Pune','null'),(10,'Somanshu','somanshu@gmail.com','?=1>de','0987654321','Admin','Male','pune','null'),(11,'AS','as@gmail.com','\0\"1389cd','4444444444','Admin','Female','nsk	','null'),(12,'DS','ds@gmail.com','\0\"1389cd','1234567890','Developer','Female','Nsk','Pending');
/*!40000 ALTER TABLE `usertable` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-14 14:38:02
