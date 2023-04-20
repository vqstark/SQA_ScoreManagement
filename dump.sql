-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: sqa_score_management
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `registed_subject`
--

DROP TABLE IF EXISTS `registed_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registed_subject` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int DEFAULT NULL,
  `subject_id` varchar(255) DEFAULT NULL,
  `semester_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`),
  KEY `subject_id` (`subject_id`),
  KEY `semester_id` (`semester_id`),
  CONSTRAINT `registed_subject_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  CONSTRAINT `registed_subject_ibfk_2` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`),
  CONSTRAINT `registed_subject_ibfk_3` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registed_subject`
--

LOCK TABLES `registed_subject` WRITE;
/*!40000 ALTER TABLE `registed_subject` DISABLE KEYS */;
INSERT INTO `registed_subject` VALUES (1,1,'BAS1201',1),(2,1,'BAS1203',1),(3,1,'INT1154',1),(4,1,'BAS1150',1),(5,1,'BAS1141',2),(6,1,'BAS1204',2),(7,1,'BAS1226',2),(8,1,'BAS1151',2),(9,1,'BAS1152',2),(10,1,'BAS1224',2),(11,1,'INT1155',2),(12,2,'BAS1201',1),(13,2,'BAS1203',1),(14,2,'INT1154',1),(15,2,'BAS1150',1),(16,2,'BAS1141',2),(17,2,'BAS1204',2),(18,2,'BAS1226',2),(19,2,'BAS1151',2),(20,2,'BAS1152',2),(21,2,'BAS1224',2),(22,2,'INT1155',2),(23,2,'BAS1150',3);
/*!40000 ALTER TABLE `registed_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `score` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cc` double DEFAULT NULL,
  `kt` double DEFAULT NULL,
  `th` double DEFAULT NULL,
  `bt` double DEFAULT NULL,
  `thi` double DEFAULT NULL,
  `registedSubject_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `registedSubject_id` (`registedSubject_id`),
  CONSTRAINT `score_ibfk_1` FOREIGN KEY (`registedSubject_id`) REFERENCES `registed_subject` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score`
--

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` VALUES (1,10,8,0,8,8.5,1),(2,10,9,0,9.5,6,2),(3,10,7,0,9,9.3,3),(4,10,8,0,0,4,4),(5,8.8,7.5,6.8,7,7.5,5),(6,10,9,0,10,7,6),(7,8,7,0,8,5,7),(8,9,8,0,0,5,8),(9,10,8,0,0,5,9),(10,10,8,9,0,5,10),(11,10,10,0,10,8,11),(12,10,8,0,8,8.5,12),(13,10,9,0,9.5,6,13),(14,10,7,0,9,9.3,14),(15,10,8,0,0,0,15),(16,8,7,6,7,7.5,16),(17,8,5,0,10,7,17),(18,8,7,0,8,5,18),(19,9,8,0,0,5,19),(20,10,8,0,0,5,20),(21,10,10,10,0,0,21),(22,10,8,0,10,5,22),(23,10,8,0,0,4,23);
/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semester`
--

DROP TABLE IF EXISTS `semester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `semester` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester`
--

LOCK TABLES `semester` WRITE;
/*!40000 ALTER TABLE `semester` DISABLE KEYS */;
INSERT INTO `semester` VALUES (1,'Học kì 1 - Năm học 2019-2020'),(4,'Học kì 1 - Năm học 2020-2021'),(7,'Học kì 1 - Năm học 2021-2022'),(10,'Học kì 1 - Năm học 2022-2023'),(2,'Học kì 2 - Năm học 2019-2020'),(5,'Học kì 2 - Năm học 2020-2021'),(8,'Học kì 2 - Năm học 2021-2022'),(11,'Học kì 2 - Năm học 2022-2023'),(3,'Học kì 3 - Năm học 2019-2020'),(6,'Học kì 3 - Năm học 2020-2021'),(9,'Học kì 3 - Năm học 2021-2022'),(12,'Học kì 3 - Năm học 2022-2023');
/*!40000 ALTER TABLE `semester` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `clas` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'B19DCCN535','B19DCCN535','Vũ Hữu Quân','D19CNPM08'),(2,'B19DCCN523','B19DCCN523','Phí Minh Quang','D19CNPM08'),(3,'B19DCCN539','B19DCCN539','Trần Phú Quý','D19CNPM08'),(4,'B19DCCN520','B19DCCN520','Đỗ Đức Quang','D19CNPM08');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `tc` int DEFAULT NULL,
  `cc_percent` double DEFAULT NULL,
  `kt_percent` double DEFAULT NULL,
  `th_percent` double DEFAULT NULL,
  `bt_percent` double DEFAULT NULL,
  `thi_percent` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES ('BAS1122','Tư tưởng Hồ Chí Minh',2,10,20,0,0,70),('BAS1141','Tiếng Anh A11',3,0,0,0,0,100),('BAS1142','Tiếng Anh A12',4,0,0,0,0,100),('BAS1143','Tiếng Anh A21',3,0,0,0,0,100),('BAS1144','Tiếng Anh A22',4,0,0,0,0,100),('BAS1150','Triết học Mác - Lênin',3,10,20,0,0,70),('BAS1151','Kinh tế chính trị Mác- Lênin',2,10,20,0,0,70),('BAS1152','Chủ nghĩa xã hội khoa học',2,10,20,0,0,70),('BAS1153','Lịch sử Đảng cộng sản Việt Nam',2,10,20,0,0,70),('BAS1201','Đại số',3,10,10,0,10,70),('BAS1203','Giải tích 1',3,10,10,0,10,70),('BAS1204','Giải tích 2',3,10,10,0,10,70),('BAS1224','Vật lí 1 và thí nghiệm',4,10,10,20,0,60),('BAS1226','Xác suất thống kê',2,10,10,0,10,70),('BAS1227','Vật lý 3 và thí nghiệm',4,10,10,20,0,60),('ELE1319','Lý thuyết thông tin',3,0,20,0,10,70),('ELE1330','Xử lý tín hiệu số',2,10,20,0,20,50),('ELE1433','Kỹ thuật số',2,10,10,10,0,70),('INT1154','Tin học cơ sở 1',2,10,10,0,10,70),('INT1155','Tin học cơ sở 2',2,10,10,0,10,70),('INT1303','An toàn và bảo mật hệ thống thông tin',3,10,10,0,20,60),('INT1306','Cấu trúc dữ liệu và giải thuật',3,10,20,10,0,60),('INT1313','Cơ sở dữ liệu',3,10,10,0,20,60),('INT13145','Kiến trúc máy tính',3,10,10,0,20,60),('INT13146','Xử lý ảnh',3,10,10,0,20,60),('INT13147','Thực tập cơ sở',3,10,20,20,0,50),('INT13162','Lập trình với Python',3,10,10,0,20,60),('INT1319','Hệ điều hành',3,10,10,10,0,70),('INT1332','Lập trình hướng đối tượng',3,10,20,20,0,50),('INT1336','Mạng máy tính',3,10,20,0,10,60),('INT1339','Ngôn ngữ lập trình C++',3,10,20,20,0,50),('INT1340','Nhập môn công nghệ phần mềm',3,10,20,0,20,50),('INT1341','Nhập môn trí tuệ nhân tạo',3,10,10,0,10,70),('INT1342M','Phân tích và thiết kế hệ thống thông tin',3,10,10,0,20,60),('INT1358','Toán rời rạc 1',3,10,10,0,10,70),('INT1359','Toán rời rạc 2',3,10,10,0,10,70),('INT14148','Cơ sở dữ liệu phân tán',3,10,10,0,20,60),('INT14149','IoT và ứng dụng',3,10,10,0,20,60),('INT14151','Phát triển các hệ thống thông minh',3,10,20,0,20,50),('INT1433','Lập trình mạng',3,10,20,0,20,50),('INT1434','Lập trình Web',3,10,20,20,0,50),('INT1450','Quản lý dự án phần mềm',2,10,0,0,30,60),('SKD1108','Phương pháp luận nghiên cứu khoa học',2,10,20,0,20,50);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-20 15:01:47
