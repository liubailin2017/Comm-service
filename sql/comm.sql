/*
Navicat MySQL Data Transfer

Source Server         : 111
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : comm

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-07-06 18:13:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `CONTENT` varchar(2048) DEFAULT NULL,
  `COMMENT_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `com_COMMENT_ID` bigint(20) DEFAULT NULL,
  `STU_NMB` bigint(20) DEFAULT NULL,
  `DYN_ID` bigint(20) DEFAULT NULL,
  `Time` bigint(20) DEFAULT NULL,
  `ISNEW` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`COMMENT_ID`),
  KEY `FK_replay` (`com_COMMENT_ID`),
  KEY `FK_replay_dyn` (`DYN_ID`),
  KEY `FK_newc_comment` (`STU_NMB`),
  CONSTRAINT `FK_newc_comment` FOREIGN KEY (`STU_NMB`) REFERENCES `user` (`STU_NMB`),
  CONSTRAINT `FK_replay_dyn` FOREIGN KEY (`DYN_ID`) REFERENCES `dynamics` (`DYN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('test', '-1', null, null, null, null, '0');

-- ----------------------------
-- Table structure for dynamics
-- ----------------------------
DROP TABLE IF EXISTS `dynamics`;
CREATE TABLE `dynamics` (
  `DYN_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `STU_NMB` bigint(20) DEFAULT NULL,
  `CONTENT` varchar(2048) DEFAULT NULL,
  `TITLE` varchar(512) DEFAULT NULL,
  `IMGS` longblob,
  `Time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DYN_ID`),
  KEY `FK_push_dynamics` (`STU_NMB`),
  CONSTRAINT `FK_push_dynamics` FOREIGN KEY (`STU_NMB`) REFERENCES `user` (`STU_NMB`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `SCH_NMB` bigint(20) NOT NULL,
  `SCH_NAME` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`SCH_NMB`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of school
-- ----------------------------
INSERT INTO `school` VALUES ('-1', '');
INSERT INTO `school` VALUES ('1', '成都航空职业技术学院');
INSERT INTO `school` VALUES ('2', '成都信息工程大学');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `STU_NMB` bigint(20) NOT NULL,
  `SCH_NMB` bigint(20) DEFAULT NULL,
  `PASS_WD` varchar(128) DEFAULT NULL,
  `HEAD_IMG` longblob,
  `NICKNAME` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`STU_NMB`),
  KEY `FK_school_include_user` (`SCH_NMB`),
  CONSTRAINT `FK_school_include_user` FOREIGN KEY (`SCH_NMB`) REFERENCES `school` (`SCH_NMB`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('-1', null, null, null, null);
INSERT INTO `user` VALUES ('123456', '1', '1234567', null, '123456');
INSERT INTO `user` VALUES ('163796', '1', '123456', null, 'penguin');
INSERT INTO `user` VALUES ('163797', '1', '123456', null, 'penguin2');
