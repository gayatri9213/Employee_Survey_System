-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: survey_system
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
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questions` (
  `ques_id` int NOT NULL AUTO_INCREMENT,
  `question` varchar(255) DEFAULT NULL,
  `category_category_id` int DEFAULT NULL,
  PRIMARY KEY (`ques_id`),
  KEY `questions_ibfk_1` (`category_category_id`),
  CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`category_category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,' Do you feel proud to be part of the Company?',1),(2,' How likely are you to recommend our company to your friends?',1),(3,'How likely are you to nominate the company as â??Best places to workâ???',1),(4,' Do you look forward to coming to work each morning?',1),(5,' Do you plan to be at this company in the next two years?',1),(6,' Do you feel comfortable contributing ideas and opinions in our workplace?',2),(7,' Do you feel comfortable asking for help if you do not have the skills required to meet your goals?',2),(8,'When you are in any problem relating to work, do you trust your managers to listen?',2),(9,' Do you feel like the management team is transparent?',2),(10,' Do you have a good working relationship like with colleagues?',2),(11,' Do you believe in the approach taken by leaders to take to reach company objectives?',3),(12,' Do you understand the strategic goals of the broader organization?',3),(13,' Does your manager care about you as a person?',3),(14,'Do you feel aligned with the company goals?',3),(15,'Do the people at the executive level contribute to a positive work culture?',3),(16,'Do you feel that the vibe of the workplace is positive and motivating?',4),(17,'How prominent is office politics in the workplace?',4),(18,'Do you have the basic amenities to feel comfortable and relaxed at work?',4),(19,'Does the company provide you with all the tools and materials you need to do your job?',4),(20,'Is your organization dedicated to fostering diversity and inclusion?',4),(21,' Do you think that the company cares about your physical and mental wellbeing?',5),(22,'Do you feel it is important to have a well-defined Corporate wellness program in an organization?',5),(23,' Would you like to have a healthy snacks station in the workplace?',5),(24,'Do you think that the companyâ??s wellness policies and fitness initiatives are enough?',5),(25,' Would you like to be updated on health-related news and participate in wellness events?',5);
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-14 16:39:30
