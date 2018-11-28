DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `noticeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(256) DEFAULT NULL COMMENT '标题',
  `source` varchar(256) DEFAULT NULL COMMENT '公告来源',
  `type` tinyint(4) DEFAULT '1' COMMENT '公告类型,1-网站公告；2-活动资讯；',
  `content` longtext COMMENT '公告内容',
  `status` tinyint(4) DEFAULT NULL COMMENT '公告状态：0-未审核；1-审核；',
  `isDelete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除：0-未删除；1-删除；',
  `createUserId` int(11) DEFAULT NULL COMMENT '作者ID',
  `createTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `checkerId` int(11) DEFAULT NULL COMMENT '审核人ID',
  `publishTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '审核时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`noticeId`,`isDelete`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 COMMENT='公告表';

DROP TABLE IF EXISTS `advertisement`;
CREATE TABLE `advertisement` (
  `advertisementId` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(256) DEFAULT NULL COMMENT '跳转URL',
  `rank` int(11) DEFAULT NULL COMMENT '顺序',
  `imageUrl` varchar(256) DEFAULT NULL COMMENT '广告资源地址例 图片等',
  `advertisementName` varchar(64) DEFAULT NULL COMMENT '广告名',
  `target` tinyint(4) DEFAULT NULL COMMENT '页面打开方式：1-在本页面打开；2-另开页面',
  `positionId` int(11) DEFAULT NULL COMMENT '广告显示位置ID ',
  `advertisementPositionId` int(4) DEFAULT NULL COMMENT '广告所属位置',
  `createUserId` int(11) DEFAULT NULL COMMENT '创建者',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0正常1删除',
  PRIMARY KEY (`advertisementId`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='广告表';

DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `positionId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `positionName` varchar(64) DEFAULT NULL COMMENT '广告显示位置名称',
  `height` int(11) DEFAULT NULL COMMENT '高',
  `width` int(11) DEFAULT NULL COMMENT '宽',
  `code` varchar(64) DEFAULT NULL COMMENT '是否含有图片的标识',
  `createUserId` int(11) DEFAULT NULL COMMENT '创建者',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`positionId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='广告位表';
#广告位初始化数据
insert into position (positionId,positionName,height,width,code,createUserId,createTime,updateTime) values( DEFAULT,'轮播图',420,1200,'SHUFFLING_FIGURE',0,CURRENT_DATE,CURRENT_DATE)
insert into position (positionId,positionName,height,width,code,createUserId,createTime,updateTime) values( DEFAULT,'商品所在地',null,null,'ADRESS_PRODUCT',0,CURRENT_DATE,CURRENT_DATE)
insert into position (positionId,positionName,height,width,code,createUserId,createTime,updateTime) values( DEFAULT,'商品分类',null,null,'PRODUCT_CATEGORY',0,CURRENT_DATE,CURRENT_DATE)