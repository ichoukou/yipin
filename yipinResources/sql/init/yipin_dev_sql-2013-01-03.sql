
-- ----------------------------
-- Table structure for `global_log`
-- ----------------------------
DROP TABLE IF EXISTS `global_log`;
CREATE TABLE `global_log` (
  `logId` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `logKey` varchar(1000) DEFAULT NULL COMMENT '日志关键字',
  `logContent` text COMMENT '日志内容',
  `logType` tinyint(4) DEFAULT NULL COMMENT '日志类型',
  `logCreateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '日志创建时间',
  PRIMARY KEY (`logId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全局日志';

-- ----------------------------
-- Records of global_log
-- ----------------------------