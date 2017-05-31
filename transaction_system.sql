/*
Navicat MySQL Data Transfer

Source Server         : 工程设计
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : transaction_system

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-05-31 20:24:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `seller_name` varchar(20) NOT NULL,
  `image` varchar(30) NOT NULL,
  `price` varchar(10) NOT NULL,
  `info` varchar(200) DEFAULT NULL,
  `author` varchar(30) DEFAULT NULL,
  `publishing` varchar(50) DEFAULT NULL,
  `book_name` varchar(30) NOT NULL,
  PRIMARY KEY (`image`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for buyer
-- ----------------------------
DROP TABLE IF EXISTS `buyer`;
CREATE TABLE `buyer` (
  `buyer_name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`buyer_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `user` varchar(20) NOT NULL,
  `type` varchar(20) NOT NULL,
  `image` varchar(50) NOT NULL,
  PRIMARY KEY (`image`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for electronic_product
-- ----------------------------
DROP TABLE IF EXISTS `electronic_product`;
CREATE TABLE `electronic_product` (
  `seller_name` varchar(20) NOT NULL,
  `image` varchar(25) NOT NULL,
  `price` float(10,2) NOT NULL,
  `info` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`seller_name`,`image`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for life
-- ----------------------------
DROP TABLE IF EXISTS `life`;
CREATE TABLE `life` (
  `seller_name` varchar(20) NOT NULL,
  `image` varchar(25) NOT NULL,
  `price` float(10,2) NOT NULL,
  `info` varchar(100) DEFAULT NULL,
  `life_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`seller_name`,`image`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for seller
-- ----------------------------
DROP TABLE IF EXISTS `seller`;
CREATE TABLE `seller` (
  `seller_name` varchar(20) NOT NULL,
  `qq` varchar(12) DEFAULT NULL,
  `weixin` varchar(30) DEFAULT NULL,
  `tel` varchar(12) DEFAULT NULL,
  `now_cnt` int(11) NOT NULL,
  PRIMARY KEY (`seller_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sport
-- ----------------------------
DROP TABLE IF EXISTS `sport`;
CREATE TABLE `sport` (
  `seller_name` varchar(20) NOT NULL,
  `image` varchar(25) NOT NULL,
  `price` float(10,2) NOT NULL,
  `sport_name` varchar(50) DEFAULT NULL,
  `info` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`seller_name`,`image`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for suggest
-- ----------------------------
DROP TABLE IF EXISTS `suggest`;
CREATE TABLE `suggest` (
  `text` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
