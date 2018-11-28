
/*==============================================================*/
/* Table: zone                                                   */
/*==============================================================*/
CREATE TABLE zone
(
   zoneId               INT NOT NULL AUTO_INCREMENT COMMENT '专区id',
   NAME                 VARCHAR(80) NOT NULL COMMENT '名称',
   zoneType             TINYINT NOT NULL COMMENT '类型(1抢购、2预售、3专区)',
   rank                 INT COMMENT '排序',
   STATUS               TINYINT COMMENT '状态(0未发布,1已发布)',
   createTime           TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
   updateTime           TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
   creator              INT(11) DEFAULT NULL COMMENT '创建人',
   lastModifier         INT(11) DEFAULT NULL COMMENT '最后修改人',
   PRIMARY KEY (zoneId)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

ALTER TABLE zone COMMENT '专区信息表';



/*==============================================================*/
/* Table: zone_sale                                             */
/*==============================================================*/
CREATE TABLE zone_sale
(
   zoneSaleId           INT NOT NULL AUTO_INCREMENT COMMENT '销售ID',
   zoneId               INT NOT NULL COMMENT '专区ID',
   productSkuId         INT COMMENT '商品skuID',
   rank                 INT COMMENT '权重排序',
   saleBeginTime        TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '抢购开始时间',
   saleEndTime          TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '抢购结束时间',
   createTime           TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
   updateTime           TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
   productId            INT COMMENT '商品Id',
   creator              INT(11) DEFAULT NULL COMMENT '创建人',
   lastModifier         INT(11) DEFAULT NULL COMMENT '最后修改人',
   PRIMARY KEY (zoneSaleId)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

ALTER TABLE zone_sale COMMENT '专区商品销售信息';



/*专区表添加显示行数字段*/
ALTER TABLE zone ADD  lineNo TINYINT NOT NULL  COMMENT '显示行数';;



