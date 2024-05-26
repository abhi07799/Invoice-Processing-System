/*
 Navicat Premium Data Transfer

 Source Server         : demon
 Source Server Type    : MySQL
 Source Server Version : 80035 (8.0.35)
 Source Host           : localhost:3306
 Source Schema         : invoice_processing_system

 Target Server Type    : MySQL
 Target Server Version : 80035 (8.0.35)
 File Encoding         : 65001

 Date: 26/05/2024 11:11:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for clients
-- ----------------------------
DROP TABLE IF EXISTS `clients`;
CREATE TABLE `clients`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `client_mail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `client_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of clients
-- ----------------------------
INSERT INTO `clients` VALUES (1, 'abhishek@gmail.com', 'Abhishek');
INSERT INTO `clients` VALUES (2, 'abhimanyu@gmail.com', 'Abhimanyu');

-- ----------------------------
-- Table structure for invoices
-- ----------------------------
DROP TABLE IF EXISTS `invoices`;
CREATE TABLE `invoices`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `invoice_createddate` datetime(6) NOT NULL,
  `invoice_duedate` datetime(6) NOT NULL,
  `invoice_amount` int NOT NULL,
  `invoice_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `invoice_paiddate` datetime(6) NULL DEFAULT NULL,
  `invoice_status` enum('PENDING','PAID','OVERDUE','CANCELED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `client_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK9ioqm804urbgy986pdtwqtl0x`(`client_id` ASC) USING BTREE,
  CONSTRAINT `FK9ioqm804urbgy986pdtwqtl0x` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of invoices
-- ----------------------------
INSERT INTO `invoices` VALUES (1, '2024-05-15 00:37:34.911000', '2024-05-22 00:37:34.911000', 55000, 'Laptop invoice', NULL, 'PENDING', 1);
INSERT INTO `invoices` VALUES (2, '2024-05-15 00:38:09.994000', '2024-05-22 00:38:09.994000', 235000, 'Electronics invoice', NULL, 'PENDING', 2);
INSERT INTO `invoices` VALUES (3, '2024-05-15 00:38:33.854000', '2024-05-22 00:38:33.854000', 35890, 'Sports invoice', NULL, 'PENDING', 1);
INSERT INTO `invoices` VALUES (4, '2024-05-15 00:38:56.910000', '2024-05-22 00:38:56.910000', 65980, 'Bike invoice', '2024-05-15 01:03:30.431000', 'PAID', 2);

SET FOREIGN_KEY_CHECKS = 1;
