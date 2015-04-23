/*
Navicat MySQL Data Transfer

Source Server         : aa
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : jxnet

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2015-04-23 08:58:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for customers
-- ----------------------------
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `school` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customers
-- ----------------------------
INSERT INTO `customers` VALUES ('10', '李杰', '21', '32', '32', '32');
INSERT INTO `customers` VALUES ('11', '', '23', '222', '32', '111');
INSERT INTO `customers` VALUES ('21', '张磊', 'xasdaaa', 'asda', 'test', '1870000adattt');
INSERT INTO `customers` VALUES ('22', '李杰', '西电', '123456', 'ww@qq.com', '1871084');
INSERT INTO `customers` VALUES ('27', '李杰', '西电', '12313', 'qq@qq.com', '1123');
INSERT INTO `customers` VALUES ('28', null, null, '22222222', null, '1111111');
INSERT INTO `customers` VALUES ('29', null, null, '12312', null, '1213242');
INSERT INTO `customers` VALUES ('30', null, null, '12312', null, '12132422');
INSERT INTO `customers` VALUES ('31', null, null, 'asdd12', null, 'asf323422');
INSERT INTO `customers` VALUES ('32', null, null, 'asdasd', null, 'sfrgfhfj');
INSERT INTO `customers` VALUES ('33', null, null, '11111111', null, '11233');
INSERT INTO `customers` VALUES ('34', '李杰', '西电', '1', 'test@qq.com', '1');

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `discount` double(10,2) DEFAULT NULL,
  `info` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of school
-- ----------------------------
INSERT INTO `school` VALUES ('1', '鹏翔驾校', '1234', '123124', '2132', '西电老校区', '2343', '23.00', '10.00', '我是一个驾校', null);
INSERT INTO `school` VALUES ('2', '大鹏驾校', '2132', 'adsd', '132', '新校区', '2132', '32.00', '10.00', '欢迎光临西安鹏翔驾校，鹏翔驾校电话：029-88221229，西安鹏翔驾校怎么样，价格贵不贵？鹏翔驾校好不好？鹏翔驾校地址：西安市长安区韦斗路西段\r\n西安市鹏翔汽车驾驶员培训学校隶属陕西鹏翔投资发展有限公司，由西安鹏翔驾驶员生态考试场合西安鹏翔驾驶员培训基地组成，位于长安区韦斗路西段，距高新区仅仅3公里，占地面积423亩，下设25个服务站。属陕西省一级驾培机构。 \r\n我校拥有一支经验丰富、技术过硬、纪律严明...', null);
