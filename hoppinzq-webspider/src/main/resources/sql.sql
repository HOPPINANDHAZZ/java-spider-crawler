/* spider表 */
DROP TABLE IF EXISTS `spider`;

CREATE TABLE `spider` (
  `id` INT(7) NOT NULL AUTO_INCREMENT,
  `title` LONGTEXT,
  `link` LONGTEXT,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=33771 DEFAULT CHARSET=utf8mb4;

/* 日志表 */
DROP TABLE IF EXISTS `my_log`;

CREATE TABLE `my_log` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `ip` varchar(32) DEFAULT NULL,
  `url` varchar(128) DEFAULT NULL,
  `httpMethod` varchar(128) DEFAULT NULL,
  `classMethod` varchar(128) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `logLevel` varchar(11) DEFAULT NULL,
  `requestParams` longblob,
  `timeCost` int(8) DEFAULT NULL,
  `result` longblob,
  `exception` longblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32101 DEFAULT CHARSET=utf8;

/* 反馈表 */
DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `id` INT(7) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) DEFAULT NULL,
  `contact` VARCHAR(128) DEFAULT NULL,
  `message` VARCHAR(2048) DEFAULT NULL,
  `create` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/* 蜘蛛配置主表 */
DROP TABLE IF EXISTS `spidermajor`;

CREATE TABLE `spidermajor` (
  `id` BIGINT(42) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) DEFAULT NULL,
  `create` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `description` VARCHAR(1024) DEFAULT NULL,
  `urldemo` VARCHAR(1024) DEFAULT NULL,
  `thread` INT(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=338653236275757057 DEFAULT CHARSET=utf8;

/* 蜘蛛配置主表数据 */

INSERT  INTO `spidermajor`(`id`,`name`,`create`,`description`,`urldemo`,`thread`) VALUES (1,'东方project','2022-07-22 13:15:18','爬全部东方project正作人物以及小图片','https://thwiki.cc/%E5%AE%98%E6%96%B9%E8%A7%92%E8%89%B2%E5%88%97%E8%A1%A8',1),(338649752176476160,'测试csdn爬取博客','2022-07-23 03:53:13','两个字段','https://blog.csdn.net/OneFlow_Official/article/details/125904049',1);

/* 蜘蛛配置次表 */
DROP TABLE IF EXISTS `spiderminor`;

CREATE TABLE `spiderminor` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `_mid` BIGINT(42) DEFAULT NULL,
  `_key` VARCHAR(32) DEFAULT NULL,
  `description` VARCHAR(1024) DEFAULT NULL,
  `xpath` VARCHAR(1024) DEFAULT NULL,
  `selector` VARCHAR(1024) DEFAULT NULL,
  `attr` VARCHAR(128) DEFAULT NULL,
  `links` TINYINT(1) DEFAULT '0',
  `addLinks` TINYINT(1) DEFAULT '0',
  `regex` VARCHAR(1024) DEFAULT NULL,
  `isAll` TINYINT(1) DEFAULT '0',
  `xpathFunction` VARCHAR(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/* 蜘蛛配置次表数据 */

INSERT  INTO `spiderminor`(`id`,`_mid`,`_key`,`description`,`xpath`,`selector`,`attr`,`links`,`addLinks`,`regex`,`isAll`,`xpathFunction`) VALUES (8,1,'name','人物名称','//*[@id=\"chara-list\"]/div[3]/div[@data-tag=\'正作游戏\']/div[1]/div[2]/a',NULL,NULL,0,0,NULL,1,'text()'),(9,1,'nickname','外号','//*[@id=\"chara-list\"]/div[3]/div[@data-tag=\'正作游戏\']/@data-nickname',NULL,NULL,0,0,NULL,1,''),(10,1,'link','人物详情链接','//*[@id=\"chara-list\"]/div[3]/div[@data-tag=\'正作游戏\']/div[1]/div[1]/a/@href',NULL,NULL,0,0,NULL,1,NULL),(11,1,'img','人物图片','//*[@id=\"chara-list\"]/div[3]/div[@data-tag=\'正作游戏\']/div[1]/div[1]/a/img/@v-lazy',NULL,NULL,0,0,NULL,1,NULL),(12,1,'description','人物描述','//*[@id=\"chara-list\"]/div[3]/div[@data-tag=\'正作游戏\']/div[4]',NULL,NULL,0,0,NULL,1,'text()'),(14,338649752176476160,'title','博客标题','//*[@id=\"articleContentId\"]','','',0,0,'',0,'text(0)'),(15,338649752176476160,'article','博客作者','//*[@id=\"mainBox\"]/main/div[1]/div[1]/div/div[2]/div[1]/div/a[1]','','',0,0,'',0,'text(0)');
