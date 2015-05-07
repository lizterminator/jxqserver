/*
Navicat MySQL Data Transfer

Source Server         : aa
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : jxnet

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2015-05-07 16:45:56
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
  `token` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customers
-- ----------------------------
INSERT INTO `customers` VALUES ('46', '李杰', 'xidian', '49BA59ABBE56E057', null, '18710847003', null, 'dasar', 'male');
INSERT INTO `customers` VALUES ('47', null, null, '49BA59ABBE56E057', null, '18710847004', null, null, null);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `orderNumber` varchar(100) NOT NULL,
  `userId` int(11) NOT NULL,
  `jxId` int(11) NOT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `contactPhone` varchar(255) DEFAULT NULL,
  `local` int(3) DEFAULT NULL,
  `notLocal` int(3) DEFAULT NULL,
  `sum` double(10,0) DEFAULT NULL,
  `checked` int(1) DEFAULT NULL,
  `schoolName` varchar(255) DEFAULT NULL,
  `orderDate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`orderNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1430968615893', '46', '2', '李杰', '18710847003', '2', '0', '0', '0', '锦志程驾校', '2015-05-07 11:16:55');

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
  `info` varchar(999) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `sold` int(5) DEFAULT NULL,
  `life` varchar(255) DEFAULT NULL,
  `pic1` varchar(255) DEFAULT NULL,
  `pic2` varchar(255) DEFAULT NULL,
  `pic3` varchar(255) DEFAULT NULL,
  `pic4` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of school
-- ----------------------------
INSERT INTO `school` VALUES ('2', '锦志程驾校', '123456', '18710847003', '132', '西电新校区 老校区', 'http://map.sogou.com/card/index+s=800,460&p=1100&m=go2map&d=14309254645360084.html#', '32.00', '20.00', '陕西锦志程机动车驾驶技能培训有限公司成立于2011年12月。位于西安市雁塔区鱼化工业园双鱼路18号。\r\n（科技路西口向西过西三环鱼化桥即到）。 我公司是经省市有关部门核批的一所新的驾驶技能培训机构，\r\n下简称驾校）。驾校建有大型的训练及考试场地。训练场8650平方米，场内有练车道路4600米。\r\n符合新国标的科目二红外线考试设备及理论考试的所有仪器、模拟器材等教学设备，\r\n崭新的桑塔纳、比亚迪60余辆，以适应高速发展的驾培需求，更规范化的服务于教学。\r\n 陕西锦志程机动车驾驶技能培训有限公司，专门为有志学习机动车驾驶技术的人员提供培训 ...', null, '24', '2015/09/09', 'http://7xiyr8.com1.z0.glb.clouddn.com/jzc01.png', 'http://7xiyr8.com1.z0.glb.clouddn.com/jzc02.png', 'http://7xiyr8.com1.z0.glb.clouddn.com/jzc03.png', 'http://7xiyr8.com1.z0.glb.clouddn.com/jzc04.png');
INSERT INTO `school` VALUES ('3', '华英驾校', '123456', '18710847004', '123', '西电老校区', 'http://map.sogou.com/card/index+s=800,460&p=1100&m=go2map&d=14309858735360576.html#', '20.00', '10.00', ' 华英驾校属山西省交通厅、公安厅的培训学校，我校拥有考训场118亩，教练教学车辆398辆，科目二考试车36辆，考试频次全市最多，一天可容纳800~1000人进行科目二考试，无考试时学员可在考场练车及模拟考试。\r\n    华英驾校办学宗旨：学员至上，信誉第一，严格培训，热情服务。\r\n华英驾校属山西省交通厅、公安厅的培训学校，我校拥有考训场118亩，教练教学车辆398辆，科目二考试车36辆，考试频次全市最多，一天可容纳800~1000人进行科目二考试。外地4200~4300元（不收取任何隐性费用，补考费学员自己交，20人以上组团报名可享受4100元低价）...', null, '10', '2015/10/28', 'http://7xiyr8.com1.z0.glb.clouddn.com/hy01.JPG', 'http://7xiyr8.com1.z0.glb.clouddn.com/hy02.JPG', 'http://7xiyr8.com1.z0.glb.clouddn.com/hy03.JPG', 'http://7xiyr8.com1.z0.glb.clouddn.com/hy04.JPG');
INSERT INTO `school` VALUES ('4', '顺源驾校', '123456', '18710847005', '123', '西电老校区', 'http://map.sogou.com/card/index+s=800,460&p=1100&m=go2map&d=14309867115360588.html#', '20.00', '15.00', '顺源驾驶员培训学校是经政府主管部门批准成立的标准化汽车驾驶员培训学校，学校成立于2001年，历经十年的\r\n\r\n发展已形成规模化经营，拥有大、中型训练场4个，教练车60余辆，服务网点遍布全市。秉承“服务学员；回报社\r\n\r\n会”的经营理念，已为社会培养出大批优秀驾驶员。科学的管理体系、严谨的教学方法、完备的服务保障是我校的\r\n\r\n生存之道，我们一直在努力！ 选择我校使您轻松上路，一生平安。驾校圈网上报名大优惠！西安市报名费2600元，外地有居住证者报名4500元！', null, '15', '2015/10/29', 'http://7xiyr8.com1.z0.glb.clouddn.com/sy01.jpg', 'http://7xiyr8.com1.z0.glb.clouddn.com/sy02.jpg', 'http://7xiyr8.com1.z0.glb.clouddn.com/sy04.jpg', 'http://7xiyr8.com1.z0.glb.clouddn.com/sy04.jpg');
INSERT INTO `school` VALUES ('5', '富民驾校', '123456', '18710847006', '123', '西电老校区', 'http://map.sogou.com/card/index+s=800,460&p=1100&m=go2map&d=14309879125360612.html#', '15.00', '30.00', '西安市富民机动车驾驶培训学校是西安市扶贫基金会下属经济实体。该校创办于1994年5月，学校办公占地2000平方米，同有自由舰、比亚迪，桑塔纳小轿车百余辆；有教室专用训练场、修理间、办公室、休息室等完善的教学设施和训练场地，为学员提供了优越的学习和生活环幸境。多年来，该校本着为社会培养合格认真贯彻执行公安部《机动车驾驶培训管理办法》及省市所属行业有关规定的同时，始终坚持正规化教学、规范化训练，并以周到的服务、严格的要求、负责的精神、优质的质量为国家培养出优秀合格的汽车驾驶人才近万名。..', null, '20', '2016/07/07', 'http://7xiyr8.com1.z0.glb.clouddn.com/fm01.png', 'http://7xiyr8.com1.z0.glb.clouddn.com/fm02.png', 'http://7xiyr8.com1.z0.glb.clouddn.com/fm03.jpg', 'http://7xiyr8.com1.z0.glb.clouddn.com/fm04.png');
