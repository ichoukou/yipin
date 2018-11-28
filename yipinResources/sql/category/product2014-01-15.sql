--
ALTER TABLE `product`
ADD COLUMN `sellType`  tinyint NULL COMMENT '售卖类型';
ALTER TABLE `product`
ADD COLUMN `preDeliveryTime`  timestamp NULL COMMENT '预发货时间';
ALTER TABLE `product`
ADD COLUMN `recommendedReason`  varchar(500) NULL COMMENT '推荐理由';
ALTER TABLE `product`
ADD COLUMN `hits`  int(11) NULL COMMENT '点击量';
ALTER TABLE `product`
ADD COLUMN `productProp`  varchar(500) NULL COMMENT '商品属性集合';

ALTER TABLE `product_sku`
ADD COLUMN `sellerSkuCode`  varchar(64) NULL COMMENT '商家sku';
ALTER TABLE `product_sku`
ADD COLUMN `isDefault`  tinyint NULL COMMENT 'sku默认选中';
ALTER TABLE `product_sku`
ADD COLUMN `marketPrice` decimal NULL COMMENT '市场价格';

--sku_option
update sku_option set skuOptionName = '规格' where skuOptionId = 100000;
--sku_option_value
update sku_option_value set skuOptionValue = '10g' where skuOptionValueId = 100000;

insert into sku_option_value(skuOptionId, skuOptionValue, rank) values
(100000, '15g', 2),(100000, '25g', 3),(100000, '30g', 4),(100000, '35g', 5), (100000, '40g', 6),(100000, '45g', 7),(100000, '50g', 8);