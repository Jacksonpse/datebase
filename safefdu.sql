/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : localhost:3306
 Source Schema         : safefdu

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 24/12/2022 15:29:00
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
INSERT INTO `campus` VALUES ('张江', '天河区天河路794号');
INSERT INTO `campus` VALUES ('枫林', '成华区二仙桥东三路621号');
INSERT INTO `campus` VALUES ('江湾', '锦江区红星路三段245号');
INSERT INTO `campus` VALUES ('邯郸', '东泰五街846号');

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
INSERT INTO `class` VALUES (1, '韩安琪', 5, '100003');
INSERT INTO `class` VALUES (2, '谢云熙', 1, '100005');
INSERT INTO `class` VALUES (3, '叶震南', 4, '100005');
INSERT INTO `class` VALUES (4, '常震南', 1, '100006');
INSERT INTO `class` VALUES (5, '胡岚', 6, '100005');
INSERT INTO `class` VALUES (6, '袁子韬', 4, '100006');
INSERT INTO `class` VALUES (7, '谢宇宁', 3, '100005');
INSERT INTO `class` VALUES (8, '陆晓明', 1, '100004');
INSERT INTO `class` VALUES (9, '孙晓明', 3, '100006');
INSERT INTO `class` VALUES (10, '周子韬', 1, '100009');
INSERT INTO `class` VALUES (11, '田宇宁', 2, '100009');
INSERT INTO `class` VALUES (12, '何杰宏', 4, '100004');

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
INSERT INTO `department` VALUES (1, '熊嘉伦', '枫林', '100006');
INSERT INTO `department` VALUES (2, '田岚', '江湾', '100007');
INSERT INTO `department` VALUES (3, '程子韬', '邯郸', '100005');
INSERT INTO `department` VALUES (4, '廖云熙', '邯郸', '100004');
INSERT INTO `department` VALUES (5, '郑晓明', '江湾', '100004');
INSERT INTO `department` VALUES (6, '冯詩涵', '江湾', '100004');

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
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of entry_record
-- ----------------------------
INSERT INTO `entry_record` VALUES (1, '2022-08-24 16:46:28', '20302010030', '张江');
INSERT INTO `entry_record` VALUES (2, '2022-07-16 13:19:03', '20302010054', '张江');
INSERT INTO `entry_record` VALUES (3, '2022-11-10 10:46:58', '20302010099', '江湾');
INSERT INTO `entry_record` VALUES (4, '2022-09-20 21:12:17', '20302010054', '张江');
INSERT INTO `entry_record` VALUES (5, '2022-05-27 10:03:23', '20302010068', '江湾');
INSERT INTO `entry_record` VALUES (6, '2022-04-22 23:22:25', '20302010099', '邯郸');
INSERT INTO `entry_record` VALUES (7, '2021-12-06 07:57:11', '20302010030', '张江');
INSERT INTO `entry_record` VALUES (8, '2022-07-28 14:58:09', '20302010066', '张江');
INSERT INTO `entry_record` VALUES (9, '2021-12-19 21:29:58', '20302010054', '江湾');
INSERT INTO `entry_record` VALUES (10, '2022-05-26 20:29:28', '20302010068', '枫林');

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
INSERT INTO `entry_reply` VALUES ('20302010054', '2022-08-15 20:58:36', 'Flexible settings enable you to set up a custom key for comparison and synchronization. The Navigation pane employs tree structure which allows you to take action upon the database                    ', '2022-10-20', 0, 'Navicat 15 has added support for the system-wide dark mode. The Information Pane shows the detailed object information, project activities, the DDL of database objects, object dependencies,           ');
INSERT INTO `entry_reply` VALUES ('20302010060', '2022-04-03 11:20:28', 'It can also manage cloud databases such as Amazon Redshift, Amazon RDS, Alibaba Cloud. Features in Navicat are sophisticated enough to provide professional developers for all their                    ', '2022-05-13', -2, 'The repository database can be an existing MySQL, MariaDB, PostgreSQL, SQL Server, or Amazon RDS instance. It wasn’t raining when Noah built the ark. To successfully establish a new                 ');
INSERT INTO `entry_reply` VALUES ('20302010066', '2022-07-06 14:23:07', 'It is used while your ISPs do not allow direct connections, but allows establishing HTTP connections. The Navigation pane employs tree structure which allows you to take action upon                   ', '2022-05-21', -2, 'Navicat Monitor requires a repository to store alerts and metrics for historical analysis. You will succeed because most people are lazy. Navicat Monitor requires a repository to store                ');
INSERT INTO `entry_reply` VALUES ('20302010068', '2022-06-14 16:31:42', 'Navicat Cloud provides a cloud service for synchronizing connections, queries, model files and virtual group information from Navicat, other Navicat family members, different machines                 ', '2022-05-06', 0, 'Navicat authorizes you to make connection to remote servers running on different platforms (i.e. Windows, macOS, Linux and UNIX), and supports PAM and GSSAPI authentication.');
INSERT INTO `entry_reply` VALUES ('20302010068', '2022-12-17 23:09:07', 'All journeys have secret destinations of which the traveler is unaware. Navicat 15 has added support for the system-wide dark mode. All journeys have secret destinations of which the                  ', '2022-06-24', -1, 'If your Internet Service Provider (ISP) does not provide direct access to its server, Secure Tunneling Protocol (SSH) / HTTP is another solution.');
INSERT INTO `entry_reply` VALUES ('20302010072', '2022-09-18 14:07:04', 'Navicat provides a wide range advanced features, such as compelling code editing capabilities, smart code-completion, SQL formatting, and more. Secure Sockets Layer(SSL) is a protocol                 ', '2021-10-10', 0, 'A man’s best friends are his ten fingers. A man’s best friends are his ten fingers. Such sessions are also susceptible to session hijacking, where a malicious user takes over your                 ');
INSERT INTO `entry_reply` VALUES ('20302010075', '2021-12-29 11:34:14', 'To open a query using an external editor, control-click it and select Open with External Editor. You can set the file path of an external editor in Preferences.', '2021-10-25', 1, 'Such sessions are also susceptible to session hijacking, where a malicious user takes over your session once you have authenticated.');
INSERT INTO `entry_reply` VALUES ('20302010075', '2022-12-12 15:44:45', 'Anyone who has ever made anything of importance was disciplined. Optimism is the one quality more associated with success and happiness than any other.', '2021-10-07', 2, 'You will succeed because most people are lazy. Navicat provides powerful tools for working with queries: Query Editor for editing the query text directly, and Query Builder, Find Builder              ');
INSERT INTO `entry_reply` VALUES ('20302010078', '2021-12-23 02:08:50', 'How we spend our days is, of course, how we spend our lives. Always keep your eyes open. Keep watching. Because whatever you see can inspire you. You must be the change you wish to see in the world.', '2022-05-22', -1, 'In a Telnet session, all communications, including username and password, are transmitted in plain-text, allowing anyone to listen-in on your session and steal passwords and other information.');
INSERT INTO `entry_reply` VALUES ('20302010078', '2022-05-30 15:18:51', 'To connect to a database or schema, simply double-click it in the pane. The past has no power over the present moment. It wasn’t raining when Noah built the ark.', '2022-11-02', 0, 'Champions keep playing until they get it right. You must be the change you wish to see in the world. To clear or reload various internal caches, flush tables, or acquire locks, control-click          ');

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
INSERT INTO `exit_apply` VALUES ('20302010019', '2022-06-08 13:10:38', 'In other words, Navicat provides the ability for data in different databases and/or schemas to be kept up-to-date so that each repository contains the same information. To successfully                ', '中国东莞坑美十五巷372号15号楼', '2022-11-04', '2022-09-25', 1, 'It is used while your ISPs do not allow direct connections, but allows establishing HTTP connections. If the Show objects under schema in navigation pane option is checked at the Preferences          ');
INSERT INTO `exit_apply` VALUES ('20302010037', '2022-05-21 18:13:20', 'Genius is an infinite capacity for taking pains. If your Internet Service Provider (ISP) does not provide direct access to its server, Secure Tunneling Protocol (SSH) / HTTP is another solution.', '中国深圳福田区深南大道759号4室', '2022-10-29', '2022-07-08', 2, 'HTTP Tunneling is a method for connecting to a server that uses the same protocol (http://) and the same port (port 80) as a web server does.');
INSERT INTO `exit_apply` VALUES ('20302010054', '2021-10-26 14:45:33', 'The Main Window consists of several toolbars and panes for you to work on connections, database objects and advanced tools.', '中国深圳罗湖区清水河一路880号3楼', '2022-03-15', '2022-04-27', -1, 'The Synchronize to Database function will give you a full picture of all database differences.');
INSERT INTO `exit_apply` VALUES ('20302010054', '2021-11-01 04:07:17', 'If opportunity doesn’t knock, build a door. To connect to a database or schema, simply double-click it in the pane. Navicat allows you to transfer data from one database and/or schema               ', '中国上海市闵行区宾川路619号华润大厦40室', '2022-08-17', '2022-09-17', 2, 'To successfully establish a new connection to local/remote server - no matter via SSL, SSH or HTTP, set the database login information in the General tab.');
INSERT INTO `exit_apply` VALUES ('20302010054', '2021-12-26 18:46:52', 'HTTP Tunneling is a method for connecting to a server that uses the same protocol (http://) and the same port (port 80) as a web server does. You must be the change you wish to see in the world.', '中国深圳龙岗区布吉镇西环路102号华润大厦20室', '2022-06-07', '2021-10-31', 0, 'After logged in the Navicat Cloud feature, the Navigation pane will be divided into Navicat Cloud and My Connections sections.');
INSERT INTO `exit_apply` VALUES ('20302010066', '2022-05-09 06:05:40', 'After comparing data, the window shows the number of records that will be inserted, updated or deleted in the target. Navicat Cloud provides a cloud service for synchronizing connections,             ', '中国深圳罗湖区田贝一路170号14楼', '2022-02-05', '2022-06-16', 1, 'All journeys have secret destinations of which the traveler is unaware. Remember that failure is an event, not a person.');
INSERT INTO `exit_apply` VALUES ('20302010068', '2021-11-14 02:43:24', 'Creativity is intelligence having fun. Remember that failure is an event, not a person. Navicat provides a wide range advanced features, such as compelling code editing capabilities,                  ', '中国深圳罗湖区蔡屋围深南东路552号8号楼', '2022-01-15', '2022-01-29', 1, 'Navicat allows you to transfer data from one database and/or schema to another with detailed analytical process. Navicat is a multi-connections Database Administration tool allowing                   ');
INSERT INTO `exit_apply` VALUES ('20302010072', '2022-08-23 23:52:42', 'You can select any connections, objects or projects, and then select the corresponding buttons on the Information Pane. If it scares you, it might be a good thing to try.', '中国中山京华商圈华夏街109号37楼', '2022-01-03', '2022-03-17', -1, 'If the plan doesn’t work, change the plan, but never the goal.');
INSERT INTO `exit_apply` VALUES ('20302010078', '2022-08-13 08:52:22', 'If opportunity doesn’t knock, build a door. Import Wizard allows you to import data to tables/collections from CSV, TXT, XML, DBF and more. A comfort zone is a beautiful place, but                  ', '中国北京市東城区東直門內大街459号48室', '2022-03-18', '2022-07-26', 0, 'Always keep your eyes open. Keep watching. Because whatever you see can inspire you. To connect to a database or schema, simply double-click it in the pane. In the Objects tab, you                    ');
INSERT INTO `exit_apply` VALUES ('20302010094', '2022-08-03 18:46:52', 'Sometimes you win, sometimes you learn. HTTP Tunneling is a method for connecting to a server that uses the same protocol (http://) and the same port (port 80) as a web server does.', '中国深圳罗湖区田贝一路597号16栋', '2021-10-25', '2022-08-31', 0, 'Navicat provides powerful tools for working with queries: Query Editor for editing the query text directly, and Query Builder, Find Builder or Aggregate Builder for building queries visually.');

-- ----------------------------
-- Table structure for exit_record
-- ----------------------------
DROP TABLE IF EXISTS `exit_record`;
CREATE TABLE `exit_record`  (
  `ex_id` int NOT NULL AUTO_INCREMENT,
  `time` datetime NOT NULL,
  `stu_id` char(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `campus_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`ex_id`) USING BTREE,
  INDEX `stu_id`(`stu_id` ASC) USING BTREE,
  INDEX `campus_name`(`campus_name` ASC) USING BTREE,
  CONSTRAINT `exit_record_ibfk_1` FOREIGN KEY (`stu_id`) REFERENCES `student` (`stu_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `exit_record_ibfk_2` FOREIGN KEY (`campus_name`) REFERENCES `campus` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exit_record
-- ----------------------------
INSERT INTO `exit_record` VALUES (1, '2022-01-18 05:05:12', '20302010073', '枫林');
INSERT INTO `exit_record` VALUES (2, '2022-09-19 11:29:55', '20302010054', '枫林');
INSERT INTO `exit_record` VALUES (3, '2022-07-04 04:55:07', '20302010073', '张江');
INSERT INTO `exit_record` VALUES (4, '2022-10-02 00:48:24', '20302010078', '枫林');
INSERT INTO `exit_record` VALUES (5, '2021-12-11 12:01:28', '20302010094', '张江');
INSERT INTO `exit_record` VALUES (6, '2022-07-24 00:11:09', '20302010094', '枫林');
INSERT INTO `exit_record` VALUES (7, '2022-03-23 05:59:12', '20302010037', '邯郸');
INSERT INTO `exit_record` VALUES (8, '2022-11-23 19:41:30', '20302010030', '邯郸');
INSERT INTO `exit_record` VALUES (9, '2022-12-09 02:08:16', '20302010054', '邯郸');
INSERT INTO `exit_record` VALUES (13, '2022-02-04 09:19:32', '20302010037', '枫林');

-- ----------------------------
-- Table structure for health_report
-- ----------------------------
DROP TABLE IF EXISTS `health_report`;
CREATE TABLE `health_report`  (
  `stu_id` char(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `time` datetime NOT NULL,
  `state` int NOT NULL,
  `temperature` int NOT NULL,
  `location` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`stu_id`, `time`) USING BTREE,
  CONSTRAINT `health_report_ibfk_1` FOREIGN KEY (`stu_id`) REFERENCES `student` (`stu_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of health_report
-- ----------------------------
INSERT INTO `health_report` VALUES ('20302010019', '2022-05-23 20:22:03', 1, 38, '中国广州市越秀区中山二路492号48号楼');
INSERT INTO `health_report` VALUES ('20302010019', '2022-11-30 19:10:40', 1, 37, '中国东莞坑美十五巷438号5栋');
INSERT INTO `health_report` VALUES ('20302010030', '2022-01-17 05:38:53', 0, 40, '中国上海市闵行区宾川路91号45号楼');
INSERT INTO `health_report` VALUES ('20302010030', '2022-05-20 02:02:58', 1, 39, '中国深圳罗湖区清水河一路124号41室');
INSERT INTO `health_report` VALUES ('20302010037', '2022-02-06 09:49:09', 1, 38, '中国中山乐丰六路541号46栋');
INSERT INTO `health_report` VALUES ('20302010037', '2022-02-06 22:32:44', 1, 38, '中国深圳福田区景田东一街837号32号楼');
INSERT INTO `health_report` VALUES ('20302010054', '2022-12-24 14:52:37', 1, 36, '上海');
INSERT INTO `health_report` VALUES ('20302010060', '2022-11-11 16:10:31', 1, 39, '中国上海市浦东新区健祥路170号16楼');
INSERT INTO `health_report` VALUES ('20302010060', '2022-12-15 19:23:23', 1, 36, '中国成都市成华区二仙桥东三路695号32栋');
INSERT INTO `health_report` VALUES ('20302010060', '2022-12-23 15:04:31', 1, 37, '中国上海市杨浦区');
INSERT INTO `health_report` VALUES ('20302010066', '2021-10-22 09:01:35', 1, 39, '中国深圳罗湖区清水河一路886号华润大厦26室');
INSERT INTO `health_report` VALUES ('20302010066', '2021-11-23 20:34:40', 1, 39, '中国北京市海淀区清河中街68号177号华润大厦5室');
INSERT INTO `health_report` VALUES ('20302010066', '2022-02-10 10:54:08', 1, 37, '中国北京市朝阳区三里屯路882号24栋');
INSERT INTO `health_report` VALUES ('20302010068', '2021-10-07 09:42:35', 0, 36, '中国深圳罗湖区蔡屋围深南东路813号华润大厦35室');
INSERT INTO `health_report` VALUES ('20302010068', '2022-05-25 18:36:31', 1, 39, '中国广州市白云区小坪东路195号44号楼');
INSERT INTO `health_report` VALUES ('20302010078', '2022-02-26 03:18:56', 1, 37, '中国东莞坑美十五巷655号47楼');
INSERT INTO `health_report` VALUES ('20302010078', '2022-10-26 10:39:45', 1, 38, '中国北京市海淀区清河中街68号361号13栋');

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
INSERT INTO `instructor` VALUES ('100000', '田子异', 'dept_ins');
INSERT INTO `instructor` VALUES ('100001', '武岚', 'dept_ins');
INSERT INTO `instructor` VALUES ('100002', '卢嘉伦', 'sup_ins');
INSERT INTO `instructor` VALUES ('100003', '陆云熙', 'sup_ins');
INSERT INTO `instructor` VALUES ('100004', '黄晓明', 'dept_ins');
INSERT INTO `instructor` VALUES ('100005', '郑杰宏', 'sup_ins');
INSERT INTO `instructor` VALUES ('100006', '邵睿', 'cls_ins');
INSERT INTO `instructor` VALUES ('100007', '尹致远', 'dept_ins');
INSERT INTO `instructor` VALUES ('100008', '阎晓明', 'dept_ins');
INSERT INTO `instructor` VALUES ('100009', '孟子韬', 'cls_ins');

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
  `dept_id` int NOT NULL,
  PRIMARY KEY (`stu_id`) USING BTREE,
  INDEX `class_id`(`class_id` ASC) USING BTREE,
  INDEX `camp_name`(`camp_name` ASC) USING BTREE,
  INDEX `dept_id`(`dept_id` ASC) USING BTREE,
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`camp_name`) REFERENCES `campus` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_ibfk_3` FOREIGN KEY (`dept_id`) REFERENCES `department` (`d_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('20302010019', '龚致远', '13561277189', 'gz6@outlook.com', '36楼4032', '中国广州市越秀区中山二路729号39楼', '大陆身份证', '568201190187853026', 6, 0, '枫林', 11, 3);
INSERT INTO `student` VALUES ('20302010030', '常杰宏', '202824791', 'jiehong01@outlook.com', '34楼3499', '中国广州市海珠区江南西路937号华润大厦44室', '大陆身份证', '478295268212574535', 8, 1, '邯郸', 5, 3);
INSERT INTO `student` VALUES ('20302010037', '龚岚', '105588278', 'lag6@hotmail.com', '87楼1263', '中国北京市東城区東直門內大街735号华润大厦4室', '大陆身份证', '541593809299212630', 4, 0, '邯郸', 11, 4);
INSERT INTO `student` VALUES ('20302010040', '王子异', '216033206', 'wangziy@gmail.com', '45楼7600', '中国上海市浦东新区橄榄路898号50室', '大陆身份证', '373510310343119663', 8, 1, '张江', 4, 2);
INSERT INTO `student` VALUES ('20302010054', '任嘉伦', '15471179071', 'renjia@hotmail.com', '35楼0571', '中国广州市天河区天河路331号29栋', '大陆身份证', '425152556056185326', 11, 0, '枫林', 9, 4);
INSERT INTO `student` VALUES ('20302010060', '吕嘉伦', '17108302141', 'lujialu@outlook.com', '42楼6031', '中国成都市成华区双庆路762号华润大厦36室', '大陆身份证', '176024190486515955', 0, 0, '张江', 11, 6);
INSERT INTO `student` VALUES ('20302010066', '梁杰宏', '7603914988', 'ljie@mail.com', '51楼9854', '中国中山乐丰六路34号3号楼', '大陆身份证', '972172394934325047', 14, 0, '枫林', 3, 5);
INSERT INTO `student` VALUES ('20302010068', '朱晓明', '15782353104', 'zhux510@icloud.com', '58楼8307', '中国上海市黄浦区淮海中路234号华润大厦36室', '大陆身份证', '267499430923311186', 8, 1, '枫林', 1, 3);
INSERT INTO `student` VALUES ('20302010072', '高嘉伦', '14614954953', 'gaj@mail.com', '52楼3154', '中国上海市闵行区宾川路483号华润大厦12室', '大陆身份证', '906082893289581412', 10, 0, '张江', 7, 1);
INSERT INTO `student` VALUES ('20302010073', '陈云熙', '75556015211', 'yunxi522@mail.com', '02楼7219', '中国深圳罗湖区田贝一路765号华润大厦14室', '大陆身份证', '155758694911474723', 7, 1, '邯郸', 4, 3);
INSERT INTO `student` VALUES ('20302010075', '钟詩涵', '14557702381', 'sz1983@yahoo.com', '28楼2290', '中国上海市黄浦区淮海中路761号36号楼', '大陆身份证', '632423828552121411', 11, 0, '邯郸', 3, 6);
INSERT INTO `student` VALUES ('20302010077', '曾安琪', '16519349304', 'azeng@outlook.com', '46楼4205', '中国中山京华商圈华夏街667号18室', '大陆身份证', '428369685373014528', 3, 0, '江湾', 7, 3);
INSERT INTO `student` VALUES ('20302010078', '杜晓明', '2868940509', 'xiadu18@mail.com', '72楼4780', '中国成都市锦江区红星路三段43号4号楼', '大陆身份证', '031607502217795491', 12, 0, '邯郸', 1, 3);
INSERT INTO `student` VALUES ('20302010094', '朱震南', '17837215439', 'zhuzhennan@gmail.com', '90楼8837', '中国深圳福田区景田东一街885号34号楼', '大陆身份证', '272825111962070550', 13, 0, '枫林', 3, 4);
INSERT INTO `student` VALUES ('20302010099', '董致远', '1020465441', 'zhiyuandong@gmail.com', '27楼2303', '中国北京市東城区東直門內大街493号18栋', '大陆身份证', '093975018081731728', 15, 1, '枫林', 3, 4);

SET FOREIGN_KEY_CHECKS = 1;
