--添加订单类型
ALTER TABLE `order_head`
ADD COLUMN `orderType`  tinyint NULL COMMENT '订单类型' AFTER `userId`;
--订单备注
ALTER TABLE `order_head`
ADD COLUMN `orderRemark`  varchar(200) NULL COMMENT '订单备注' AFTER `invoiceTitle`;
--添加退货商品
ALTER TABLE `order_refund`
ADD COLUMN `orderItemId`  int NULL COMMENT '退货商品' AFTER `orderAddressId`;


DROP TABLE IF EXISTS `global_log`;
CREATE TABLE `global_log` (
  `logId` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `logKey` varchar(1000) DEFAULT NULL COMMENT '日志关键字',
  `logContent` text COMMENT '日志内容',
  `logType` tinyint(4) DEFAULT NULL COMMENT '日志类型',
  `logCreateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '日志创建时间',
  PRIMARY KEY (`logId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全局日志';


drop table if exists order_cancel;
create table order_cancel
(
   orderCancelId        int not null auto_increment,
   orderId              int comment '订单id',
   orderCancelNo        varchar(20) comment 'tk+时间戳',
   customerName         varchar(64) comment '用户身份标示',
   mobile               varchar(32) comment '客户联系电话',
   refundReason         varchar(1024) comment '手机号码',
   reviewDescribe       varchar(1024) comment '审核不通过是，必须填写原因',
   status               tinyint comment '卖家是否审核通过：1=未审核，2=审核通过，3=审核不通过，4=已经退款',
   refundAmount         decimal(10,2),
   sellerCheckUserId    int,
   sellerCheckTime      timestamp not null default '0000-00-00 00:00:00',
   adminCheckUserId     int,
   adminCheckTime       timestamp not null default '0000-00-00 00:00:00',
   createTime           timestamp not null default '0000-00-00 00:00:00' comment '创建时间',
   updateTime           timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '记录更新时间',
   primary key (orderCancelId)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单待发货退款信息';

