-- MySQL dump 10.13  Distrib 8.0.35, for Win64 (x86_64)
--
-- Host: localhost    Database: bank
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES gbk */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `bank`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `bank` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `bank`;

--
-- Table structure for table `bankcard`
--

DROP TABLE IF EXISTS `bankcard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bankcard` (
  `card_id` bigint NOT NULL AUTO_INCREMENT,
  `phone_number` varchar(11) NOT NULL,
  `card_number` varchar(19) NOT NULL,
  `payment_password` varchar(64) NOT NULL,
  `balance` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`card_id`),
  UNIQUE KEY `card_number` (`card_number`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bankcard`
--

LOCK TABLES `bankcard` WRITE;
/*!40000 ALTER TABLE `bankcard` DISABLE KEYS */;
INSERT INTO `bankcard` VALUES (1,'13641438982','1234567890123456789','888888',990000.00);
/*!40000 ALTER TABLE `bankcard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` bigint NOT NULL COMMENT '涓婚敭',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '濮撳悕',
  `username` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '鐢ㄦ埛鍚?,
  `password` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '瀵嗙爜',
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '鎵嬫満鍙?,
  `sex` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '鎬у埆',
  `id_number` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '韬唤璇佸彿',
  `status` int NOT NULL DEFAULT '1' COMMENT '鐘舵€?0:绂佺敤锛?:姝ｅ父',
  `create_time` datetime NOT NULL COMMENT '鍒涘缓鏃堕棿',
  `update_time` datetime NOT NULL COMMENT '鏇存柊鏃堕棿',
  `create_user` bigint NOT NULL COMMENT '鍒涘缓浜?,
  `update_user` bigint NOT NULL COMMENT '淇敼浜?,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin ROW_FORMAT=DYNAMIC COMMENT='鍛樺伐淇℃伅';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'管理员','admin','e10adc3949ba59abbe56e057f20f883e','13641438982','1','110101199001010047',1,'2023-12-21 17:20:07','2024-01-05 22:45:50',1,1),(1743282805171437570,'刘婧怡','ljy','f379eaf3c831b04de153469d1bec345e','18268078227','女','111111222222223330',0,'2024-01-19 09:58:08','2024-01-19 10:23:49',1743282805171437570,1),(1743526948141494273,'吴骏鸿','wjh','e10adc3949ba59abbe56e057f20f883e','18181818181','男','250000000000000000',1,'2024-01-19 09:24:30','2024-01-19 09:24:30',1743526948141494273,1743526948141494273),(1747847870629445634,'薛振文','xzw','e10adc3949ba59abbe56e057f20f883e','11111111111','1','111111111111111111',1,'2024-01-18 13:06:20','2024-01-18 13:06:20',1,1),(1748169743019941889,'吴欣达','wxd','e10adc3949ba59abbe56e057f20f883e','13999999999','1','440304200301022642',1,'2024-01-19 10:25:21','2024-01-19 10:25:21',1,1);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '涓婚敭',
  `number` varchar(50) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '璁㈠崟鍙?,
  `product_id` bigint DEFAULT NULL COMMENT '浜у搧',
  `user_phone` varchar(11) COLLATE utf8mb3_bin NOT NULL COMMENT '涓嬪崟璐︽埛',
  `amount` decimal(10,2) NOT NULL COMMENT '瀛樻',
  `time` datetime DEFAULT NULL COMMENT '涓嬪崟鏃堕棿',
  `status` int NOT NULL DEFAULT '1' COMMENT '璁㈠崟鐘舵€?1鏈埌鏈?2宸插埌鏈?,
  PRIMARY KEY (`id`),
  KEY `orders___pid` (`product_id`),
  KEY `orders_user_phone_fk` (`user_phone`),
  CONSTRAINT `orders___pid` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `orders_user_phone_fk` FOREIGN KEY (`user_phone`) REFERENCES `user` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='璁㈠崟琛?;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'111',3,'13641438982',2000.00,'2024-01-13 17:23:58',1),(2,'112',4,'18268078227',5000.00,'2024-01-13 17:25:02',1),(4,'113',15,'13641438982',5000.00,'2024-01-13 17:36:19',1),(7,'114',16,'13641438982',5000.00,'2024-01-18 18:15:45',1),(8,'115',8,'18268078227',8000.00,'2024-01-18 18:16:21',1),(9,'116',13,'13641438982',6000.00,'2024-01-18 18:17:08',1),(10,'117',14,'13641438982',7000.00,'2024-01-18 18:18:49',1),(12,'118',21,'13641438982',1000.00,'2024-01-19 10:33:51',1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
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
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `before1_insert_orders` BEFORE INSERT ON `orders` FOR EACH ROW SET NEW.time = IFNULL(NEW.time, NOW()) */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `before2_insert_orders` BEFORE INSERT ON `orders` FOR EACH ROW BEGIN

    DECLARE next_order_number INT;



    -- 鑾峰彇褰撳墠鏈€澶ц鍗曞彿

    SELECT MAX(CAST(number AS SIGNED)) + 1 INTO next_order_number

    FROM orders;



    -- 濡傛灉娌℃湁璁㈠崟鍙凤紝鍒欎粠1寮€濮?
    IF next_order_number IS NULL THEN

        SET next_order_number = 1;

    END IF;



    -- 璁剧疆鏂扮殑璁㈠崟鍙?
    SET NEW.number = next_order_number;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` bigint NOT NULL AUTO_INCREMENT COMMENT '浜у搧ID',
  `name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '鍚嶇О',
  `tenure` int NOT NULL COMMENT '浜у搧鏈熼檺锛堝崟浣嶏細鏈堬級',
  `annual_interest_rate` decimal(5,2) NOT NULL DEFAULT '0.00' COMMENT '骞村寲鍒╃巼锛?锛?,
  `min_deposit_amount` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '璧峰瓨閲戦锛堝厓锛?,
  `single_limit_amount` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '鍗曚汉闄愰锛堝厓锛?,
  `daily_limit_amount` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '鍗曟棩闄愰锛堝厓锛?,
  `risk_level` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '椋庨櫓绛夌骇',
  `start_date` date NOT NULL COMMENT '璧锋伅鏃?,
  `interest_payment` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '缁撴伅鏂瑰紡',
  `maturity_date` date DEFAULT NULL COMMENT '鍒版湡鏃?,
  `product_status` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '浜у搧鐘舵€?,
  `sort` int DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `update_user` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`product_id`) USING BTREE,
  UNIQUE KEY `idx_product_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin ROW_FORMAT=DYNAMIC COMMENT='浜у搧淇℃伅';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (3,'一年定期存款',12,2.05,2000.00,200000.00,10000.00,'中风险','2023-12-22','月结','2024-12-22','上线',NULL,NULL,'2024-01-13 07:58:35',NULL,NULL),(4,'1个月定期存款',1,4.00,1000.00,100000.00,10000.00,'高风险','2024-01-06','月结','2024-02-06','上线',NULL,NULL,'2024-01-13 07:58:35',NULL,NULL),(8,'三个月定期存款',3,1.25,1000.00,400000.00,20000.00,'低风险','2024-01-11','季度结','2024-02-18','已下线',NULL,NULL,'2024-01-13 07:58:35',NULL,NULL),(13,'4个月定期存款',4,4.20,1000.00,100000.00,5000.00,'高风险','2024-01-13','月结','2024-05-13','上线',NULL,NULL,'2024-01-13 07:58:35',NULL,NULL),(14,'6个月定期存款',6,2.33,20000.00,300000.00,10000.00,'中风险','2024-01-13','季度结','2024-07-13','上线',NULL,NULL,'2024-01-13 07:58:35',NULL,NULL),(15,'3年定期存款',36,2.50,10000.00,500000.00,3000.00,'低风险','2024-01-13','年结','2027-01-13','上线',NULL,NULL,'2024-01-13 08:39:18',NULL,NULL),(16,'1年活期存款',12,1.05,5000.00,100000.00,5000.00,'中风险','2024-01-17','季度结','2025-01-17','上线',NULL,NULL,NULL,NULL,NULL),(17,'3个月活期存款',3,2.10,5000.00,300000.00,10000.00,'中风险','2024-01-17','月结','2024-04-17','上线',NULL,NULL,NULL,NULL,NULL),(18,'现金5号F',12,2.79,500.00,200000.00,5000.00,'中风险','2024-01-18','季度结','2025-01-18','上线',NULL,'2024-01-18 19:24:38','2024-01-18 19:24:38','1','1'),(19,'灵活成长添利360天持有',12,4.13,10.00,5000000.00,100000.00,'中风险','2024-01-18','年结','2025-01-18','上线',NULL,'2024-01-18 19:26:53','2024-01-18 19:26:53','1','1'),(20,'天天成长3号9期C',24,2.53,0.01,1000000.00,100000.00,'低风险','2024-01-18','月结','2026-01-18','上线',NULL,'2024-01-18 19:28:36','2024-01-18 19:28:36','1','1'),(21,'测试',12,2.13,0.10,300000.00,5000.00,'中风险','2024-01-19','月结','2025-01-19','上线',NULL,'2024-01-19 02:25:56','2024-01-19 02:25:56','1','1');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `product_sale`
--

DROP TABLE IF EXISTS `product_sale`;
/*!50001 DROP VIEW IF EXISTS `product_sale`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `product_sale` AS SELECT 
 1 AS `product_id`,
 1 AS `name`,
 1 AS `total_sale`,
 1 AS `total_amount`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL COMMENT '涓婚敭',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '濮撳悕',
  `phone` varchar(11) COLLATE utf8mb3_bin NOT NULL COMMENT '鎵嬫満鍙?,
  `password` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `sex` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '鎬у埆',
  `card_number` varchar(19) COLLATE utf8mb3_bin NOT NULL COMMENT '閾惰鍗″彿',
  `status` int DEFAULT '0' COMMENT '鐘舵€?0:绂佺敤锛?:姝ｅ父',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `user_pk` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='鐢ㄦ埛淇℃伅';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'用户','13641438982','e10adc3949ba59abbe56e057f20f883e','男','1234567890123456789',1),(2,'刘婧怡','18268078227','e10adc3949ba59abbe56e057f20f883e','女','1111111111111111111',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `bank`
--

USE `bank`;

--
-- Final view structure for view `product_sale`
--

/*!50001 DROP VIEW IF EXISTS `product_sale`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `product_sale` AS select `orders`.`product_id` AS `product_id`,`product`.`name` AS `name`,count(`orders`.`product_id`) AS `total_sale`,sum(`orders`.`amount`) AS `total_amount` from (`orders` join `product`) where (`orders`.`product_id` = `product`.`product_id`) group by `orders`.`product_id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-20 16:27:53
