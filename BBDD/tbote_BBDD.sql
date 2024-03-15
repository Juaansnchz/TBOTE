-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: tbote
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `DNI` varchar(255) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `Saldo` int DEFAULT NULL,
  `respuesta_seg` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (12,'9999999','Jaaasssdo',NULL,NULL),(13,'kausdghi4do','3o8ey3489r7',NULL,NULL),(14,NULL,'Funciona',NULL,NULL),(15,'Porfavor','Funciona',NULL,NULL),(16,'Juan','47314357E',NULL,NULL),(17,'hola','adios',7912,NULL),(18,'buenas','tardes',4211,NULL),(19,'que','tal',7285,NULL),(20,'Luis','b3813027ed2150ec3449f0716cf53c5d4a632486136365bd23e19c372884553f',2219,NULL),(21,'47314357E','e633f4fc79badea1dc5db970cf397c8248bac47cc3acf9915ba60b5d76b0e88f',9939,NULL),(22,'47314357F','cb80be76d732c36bd5f71ecdd7b6964556730a19ceccd8b8c1869220bb4c7b7c',668,NULL),(23,'12345678A','cb80be76d732c36bd5f71ecdd7b6964556730a19ceccd8b8c1869220bb4c7b7c',9190,NULL),(24,'87654321B','73e29454bd930074942b1784b43dc4a57a669751871b38c92f2c7293e06a9720',327,'cb80be76d732c36bd5f71ecdd7b6964556730a19ceccd8b8c1869220bb4c7b7c'),(25,'10293847J','a5aa9d7a8778f06072731d356baf67a072e6ca0fbd35f92db47dce11186abf90',7644,'9f47616cf36181ab3caff967309cf0048328673757392abe29c787f2cfe98930'),(26,'99999999A','8dd3d0f12d706756295575bfc283da9e4ef2658cbb8531aa7261b8aed27518e5',8413,'0e1e847b6d1dfa0382c082a9850febd05bebad869f61e8c39e12992f9b87c0ff'),(27,'11111111Q','65064c9be72cfb1bfcc06e8a7ea6eb39ac7cad488e6f46a9056de9e523d7de8c',9103,'65064c9be72cfb1bfcc06e8a7ea6eb39ac7cad488e6f46a9056de9e523d7de8c'),(28,'77777777B','54c5ccf0f305a9a1529df4ebb6ec53598c969dfd2e5a425144cda91a29a90c2a',6396,'54c5ccf0f305a9a1529df4ebb6ec53598c969dfd2e5a425144cda91a29a90c2a'),(29,'00000000A','65064c9be72cfb1bfcc06e8a7ea6eb39ac7cad488e6f46a9056de9e523d7de8c',7349,'65064c9be72cfb1bfcc06e8a7ea6eb39ac7cad488e6f46a9056de9e523d7de8c'),(30,'22222222T','b221d9dbb083a7f33428d7c2a3c3198ae925614d70210e28716ccaa7cd4ddb79',7798,'317cc457b1ce5f22696b571d8a69f40dd2a037fd7db5c6c3001b89d0e2896f26');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `generar_numero_aleatorio` BEFORE INSERT ON `login` FOR EACH ROW SET NEW.saldo = FLOOR(RAND() * (10010)) - 10 */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-15 11:07:54
