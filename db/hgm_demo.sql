/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : hgm_demo

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 16/08/2023 17:29:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mg_app
-- ----------------------------
DROP TABLE IF EXISTS `mg_app`;
CREATE TABLE `mg_app`  (
  `pid` int NOT NULL AUTO_INCREMENT,
  `app_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bg_0900_ai_ci NOT NULL,
  `app_secret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bg_0900_ai_ci NOT NULL,
  `access_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bg_0900_ai_ci NULL DEFAULT NULL,
  `expires_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bg_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bg_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mg_app
-- ----------------------------
INSERT INTO `mg_app` VALUES (1, '123', '123', NULL, NULL);
INSERT INTO `mg_app` VALUES (3, 'wx4aa386ee5db3a76d', 'a382ec4197020231642150274d5fb664', '71_SSJEdIJ0v2qSmaZgVphlZoPdxqsEb-KlMbC51yyo8hMOJRP5Mzk8MQshahCLS8dZUIdgq4RuFNkPBnWC_Z76iSAgp5z1RPDVfCDJkdfglQtL-6UCsSF4BwNlEiMTQRaACAJGH', '1692184616393');
INSERT INTO `mg_app` VALUES (4, 'SerMs', 'SerMs', '123', '123');

-- ----------------------------
-- Table structure for mg_video
-- ----------------------------
DROP TABLE IF EXISTS `mg_video`;
CREATE TABLE `mg_video`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `pid` int NULL DEFAULT NULL COMMENT '小程序id',
  `drama_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bg_0900_ai_ci NULL DEFAULT NULL COMMENT '微信剧目id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bg_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bg_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pid`(`pid` ASC) USING BTREE,
  CONSTRAINT `mg_video_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `mg_app` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bg_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mg_video
-- ----------------------------
INSERT INTO `mg_video` VALUES (1, 3, '123123', '我的中国梦', 'SerMs');

-- ----------------------------
-- Table structure for mg_video_num
-- ----------------------------
DROP TABLE IF EXISTS `mg_video_num`;
CREATE TABLE `mg_video_num`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `pid` int NULL DEFAULT NULL,
  `video_id` int NULL DEFAULT NULL,
  `video_num` int NULL DEFAULT NULL,
  `media_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bg_0900_ai_ci NULL DEFAULT NULL,
  `mp4_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bg_0900_ai_ci NULL DEFAULT NULL,
  `expires_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bg_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pid`(`pid` ASC) USING BTREE,
  INDEX `video_id`(`video_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 73 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bg_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mg_video_num
-- ----------------------------
INSERT INTO `mg_video_num` VALUES (61, 1, 1, 4, '-2069325380683212', 'https://developers.weixin.qq.com/test-encode.mp4?t=64bb36de&us=647488c4792c15185b8fd2a6&sign=631355adf06a6cf7e45e29be17c66820', '1689990878');
INSERT INTO `mg_video_num` VALUES (62, 1, 1, 3, '-6486175062514832', 'https://developers.weixin.qq.com/test-encode.mp4?t=64bb36de&us=647488c4792c15185b8fd2a6&sign=631355adf06a6cf7e45e29be17c66820', '1689990878');
INSERT INTO `mg_video_num` VALUES (63, 1, 1, 2, '5801449824955100', 'https://developers.weixin.qq.com/test-encode.mp4?t=64bb36de&us=647488c4792c15185b8fd2a6&sign=631355adf06a6cf7e45e29be17c66820', '1689990878');
INSERT INTO `mg_video_num` VALUES (64, 1, 1, 1, '8834319167365264', 'https://developers.weixin.qq.com/test-encode.mp4?t=64bb36de&us=647488c4792c15185b8fd2a6&sign=631355adf06a6cf7e45e29be17c66820', '1689990878');
INSERT INTO `mg_video_num` VALUES (65, 3, 1, 4, '-2069325380683212', 'https://developers.weixin.qq.com/test-encode.mp4?t=64bb36de&us=647488c4792c15185b8fd2a6&sign=631355adf06a6cf7e45e29be17c66820', '1689990878');
INSERT INTO `mg_video_num` VALUES (66, 3, 1, 3, '-6486175062514832', 'https://developers.weixin.qq.com/test-encode.mp4?t=64bb36de&us=647488c4792c15185b8fd2a6&sign=631355adf06a6cf7e45e29be17c66820', '1689990878');
INSERT INTO `mg_video_num` VALUES (67, 3, 1, 2, '5801449824955100', 'https://developers.weixin.qq.com/test-encode.mp4?t=64bb36de&us=647488c4792c15185b8fd2a6&sign=631355adf06a6cf7e45e29be17c66820', '1689990878');
INSERT INTO `mg_video_num` VALUES (68, 3, 1, 1, '8834319167365264', 'https://developers.weixin.qq.com/test-encode.mp4?t=64bb36de&us=647488c4792c15185b8fd2a6&sign=631355adf06a6cf7e45e29be17c66820', '1689990878');
INSERT INTO `mg_video_num` VALUES (69, 4, 1, 4, '-2069325380683212', 'https://developers.weixin.qq.com/test-encode.mp4?t=64bb36de&us=647488c4792c15185b8fd2a6&sign=631355adf06a6cf7e45e29be17c66820', '1689990878');
INSERT INTO `mg_video_num` VALUES (70, 4, 1, 3, '-6486175062514832', 'https://developers.weixin.qq.com/test-encode.mp4?t=64bb36de&us=647488c4792c15185b8fd2a6&sign=631355adf06a6cf7e45e29be17c66820', '1689990878');
INSERT INTO `mg_video_num` VALUES (71, 4, 1, 2, '5801449824955100', 'https://developers.weixin.qq.com/test-encode.mp4?t=64bb36de&us=647488c4792c15185b8fd2a6&sign=631355adf06a6cf7e45e29be17c66820', '1689990878');
INSERT INTO `mg_video_num` VALUES (72, 4, 1, 1, '8834319167365264', 'https://developers.weixin.qq.com/test-encode.mp4?t=64bb36de&us=647488c4792c15185b8fd2a6&sign=631355adf06a6cf7e45e29be17c66820', '1689990878');

SET FOREIGN_KEY_CHECKS = 1;
