/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : localhost:3306
 Source Schema         : safefdu

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 13/12/2022 00:05:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for campus
-- ----------------------------
DROP TABLE IF EXISTS `campus`;
CREATE TABLE `campus`  (
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `location` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of campus
-- ----------------------------
INSERT INTO `campus` VALUES ('邯郸校区', '邯郸路');

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `class_id` int NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `dept_id` int NOT NULL,
  `instructor_id` char(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`class_id`) USING BTREE,
  INDEX `instructor_id`(`instructor_id` ASC) USING BTREE,
  INDEX `dept_id`(`dept_id` ASC) USING BTREE,
  CONSTRAINT `class_ibfk_1` FOREIGN KEY (`instructor_id`) REFERENCES `instructor` (`w_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `class_ibfk_2` FOREIGN KEY (`dept_id`) REFERENCES `department` (`d_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES (0, '一班', 0, '12345678');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `d_id` int NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `campus_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `instructor_id` char(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`d_id`) USING BTREE,
  INDEX `instructor_id`(`instructor_id` ASC) USING BTREE,
  INDEX `campus_name`(`campus_name` ASC) USING BTREE,
  CONSTRAINT `department_ibfk_1` FOREIGN KEY (`instructor_id`) REFERENCES `instructor` (`w_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `department_ibfk_2` FOREIGN KEY (`campus_name`) REFERENCES `campus` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (0, '软件工程', '邯郸校区', '13568975');

-- ----------------------------
-- Table structure for entry_record
-- ----------------------------
DROP TABLE IF EXISTS `entry_record`;
CREATE TABLE `entry_record`  (
  `en_id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `time` datetime NOT NULL,
  `stu_id` char(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `campus_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`en_id`) USING BTREE,
  INDEX `stu_id`(`stu_id` ASC) USING BTREE,
  INDEX `campus_name`(`campus_name` ASC) USING BTREE,
  CONSTRAINT `entry_record_ibfk_1` FOREIGN KEY (`stu_id`) REFERENCES `student` (`stu_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `entry_record_ibfk_2` FOREIGN KEY (`campus_name`) REFERENCES `campus` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of entry_record
-- ----------------------------

-- ----------------------------
-- Table structure for entry_reply
-- ----------------------------
DROP TABLE IF EXISTS `entry_reply`;
CREATE TABLE `entry_reply`  (
  `stu_id` char(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `apply_time` datetime NOT NULL,
  `reason` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `arrive_date` date NOT NULL,
  `check_stage` int NOT NULL,
  `deny_comment` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`stu_id`, `apply_time`) USING BTREE,
  CONSTRAINT `entry_reply_ibfk_1` FOREIGN KEY (`stu_id`) REFERENCES `student` (`stu_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of entry_reply
-- ----------------------------
INSERT INTO `entry_reply` VALUES ('20302010065', '2022-12-07 16:03:13', '想要上大学', '2023-01-06', 0, NULL);

-- ----------------------------
-- Table structure for exit_apply
-- ----------------------------
DROP TABLE IF EXISTS `exit_apply`;
CREATE TABLE `exit_apply`  (
  `stu_id` char(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `apply_time` datetime NOT NULL,
  `reason` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `dest` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `leave_date` date NOT NULL,
  `return_date` date NOT NULL,
  `check_stage` int NOT NULL,
  `deny_comment` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`stu_id`, `apply_time`) USING BTREE,
  CONSTRAINT `exit_apply_ibfk_1` FOREIGN KEY (`stu_id`) REFERENCES `student` (`stu_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exit_apply
-- ----------------------------
INSERT INTO `exit_apply` VALUES ('20302010038', '2022-12-07 20:29:18', 'sad', 'sd', '2022-12-13', '2022-12-22', 0, NULL);
INSERT INTO `exit_apply` VALUES ('20302010065', '2022-12-06 21:11:45', 'as', 'sad', '2022-12-05', '2022-12-17', 0, NULL);
INSERT INTO `exit_apply` VALUES ('20302010065', '2022-12-07 15:35:28', 'tsa', '重点', '2022-11-30', '2022-12-23', 0, NULL);
INSERT INTO `exit_apply` VALUES ('20302010065', '2022-12-08 20:28:08', 'asd', 'xy', '2022-12-07', '2022-12-17', 0, NULL);
INSERT INTO `exit_apply` VALUES ('20302010065', '2022-12-09 14:19:47', '300', '300', '2022-12-10', '2022-12-17', 0, NULL);
INSERT INTO `exit_apply` VALUES ('20302010065', '2022-12-09 14:37:30', '想带你去浪漫的土耳其', '土耳其', '2023-01-01', '2022-12-31', 0, NULL);
INSERT INTO `exit_apply` VALUES ('20302010065', '2022-12-09 20:28:54', 'asd', 'xy', '2022-12-12', '2022-12-28', 0, NULL);
INSERT INTO `exit_apply` VALUES ('20302010065', '2022-12-11 13:38:55', '求你了让我逃离地狱', '天堂', '2022-12-18', '2022-12-23', 0, NULL);
INSERT INTO `exit_apply` VALUES ('20302010065', '2022-12-11 13:51:23', 'homesick', '徐汇区碧桂园', '2022-12-29', '2022-12-31', 0, NULL);
INSERT INTO `exit_apply` VALUES ('20302010065', '2022-12-11 21:57:42', '测试用', '测试时区', '2022-12-31', '2023-01-07', 0, NULL);

-- ----------------------------
-- Table structure for exit_record
-- ----------------------------
DROP TABLE IF EXISTS `exit_record`;
CREATE TABLE `exit_record`  (
  `ex_id` int NOT NULL,
  `time` datetime NOT NULL,
  `stu_id` char(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `campus_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`ex_id`) USING BTREE,
  INDEX `stu_id`(`stu_id` ASC) USING BTREE,
  INDEX `campus_name`(`campus_name` ASC) USING BTREE,
  CONSTRAINT `exit_record_ibfk_1` FOREIGN KEY (`stu_id`) REFERENCES `student` (`stu_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `exit_record_ibfk_2` FOREIGN KEY (`campus_name`) REFERENCES `campus` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exit_record
-- ----------------------------

-- ----------------------------
-- Table structure for health_report
-- ----------------------------
DROP TABLE IF EXISTS `health_report`;
CREATE TABLE `health_report`  (
  `stu_id` char(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `time` date NOT NULL,
  `state` int NOT NULL,
  `temperature` int NOT NULL,
  `location` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`stu_id`, `time`) USING BTREE,
  CONSTRAINT `health_report_ibfk_1` FOREIGN KEY (`stu_id`) REFERENCES `student` (`stu_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of health_report
-- ----------------------------
INSERT INTO `health_report` VALUES ('20302010038', '2022-12-07', 1, 36, '上海');
INSERT INTO `health_report` VALUES ('20302010038', '2022-12-08', 0, 35, '北京');
INSERT INTO `health_report` VALUES ('20302010038', '2022-12-09', 1, 36, '西安');
INSERT INTO `health_report` VALUES ('20302010065', '2022-12-06', 0, 300, '大便');
INSERT INTO `health_report` VALUES ('20302010065', '2022-12-07', 1, 36, '上海');
INSERT INTO `health_report` VALUES ('20302010065', '2022-12-09', 1, 36, '上海');

-- ----------------------------
-- Table structure for instructor
-- ----------------------------
DROP TABLE IF EXISTS `instructor`;
CREATE TABLE `instructor`  (
  `w_id` char(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `role` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`w_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of instructor
-- ----------------------------
INSERT INTO `instructor` VALUES ('12345678', '张三', 'cls_ins');
INSERT INTO `instructor` VALUES ('13568975', '老李', 'dept_ins');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `stu_id` char(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `building` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `home_addr` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `id_card_type` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `id_card_num` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `admission_level` int NULL DEFAULT NULL,
  `in_out_state` int NULL DEFAULT NULL,
  `camp_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `class_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`stu_id`) USING BTREE,
  INDEX `class_id`(`class_id` ASC) USING BTREE,
  INDEX `camp_name`(`camp_name` ASC) USING BTREE,
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`camp_name`) REFERENCES `campus` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('20302010038', '李飞', '18795688432', '5edu.cn', '371022', '五角场', '1', '1321585785', 10, 1, '邯郸校区', 0);
INSERT INTO `student` VALUES ('20302010065', '郭增', '110', 'zhuqi', '371023', '汤臣jjjjjjjjjjjjjjjjjj', '1', '45646464556', 10, 1, '邯郸校区', 0);

SET FOREIGN_KEY_CHECKS = 1;
