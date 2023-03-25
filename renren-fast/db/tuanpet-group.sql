/*
 Navicat Premium Data Transfer

 Source Server         : yun
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : 43.140.198.154:3306
 Source Schema         : tuanpet-group

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 22/03/2023 15:19:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for association
-- ----------------------------
DROP TABLE IF EXISTS `association`;
CREATE TABLE `association`  (
  `associationId` int(11) NOT NULL AUTO_INCREMENT COMMENT '社群id号',
  `classify` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '星球类别',
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '地区',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '二维码url',
  `createdAt` datetime(0) NOT NULL COMMENT '创建时间',
  `updatedAt` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`associationId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of association
-- ----------------------------
INSERT INTO `association` VALUES (1, '狗', '广州', '1', '2023-03-22 14:38:28', '2023-03-22 14:38:32');

SET FOREIGN_KEY_CHECKS = 1;
