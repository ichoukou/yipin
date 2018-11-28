#执行该脚本时,region express help help_category 
#初始化角色
insert into urole(uroleId,uroleName,createByUserId,`status`,createTime) values('100000','前台买家',0,1,CURRENT_DATE);	
insert into urole(uroleId,uroleName,createByUserId,`status`,createTime) values('100002','后台管理员',0,1,CURRENT_DATE);
insert into urole(uroleId,uroleName,createByUserId,`status`,createTime) values('100003','后台卖家',0,1,CURRENT_DATE);
#初始化ytoxl
insert into user(userId,username,`password`,tel,email,status,createByUserId,lastLoginTime,createTime,updateTime,operateName,employCard) 
values(DEFAULT,'ytoxl','l1lAre2C7oXOtTmPC/jPKg==','13569596859','122345612@qq.com',1,0,CURRENT_DATE,CURRENT_DATE,CURRENT_DATE,NULL,NULL);
#初始化ytoxl user_info 
insert into user_info(userInfoId,userId,contactName,tel,mobile,qq,
email,fax,companyName,companyType,supplierType,
registerDate,registerCapital,paidUpCapital,businessScope,
companyWebSite,shiperAddress,shiperRegionId,receiverAddress,
receiverRegionId,companyCorporation,licenseImageUrl,taxNo,
companyCode,companyNo,alipayNo,alipayName,
bankName,bankAccount,companyAddress,companyRegionId,
remark,createTime,updateTime,gender,
birthday,friendIds,userType)VALUES(DEFAULT,(select userId from `user` where username='ytoxl'),
'ming','13569596859','13569596859','122345612''122345612@qq.com',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
CURRENT_DATE,CURRENT_DATE,NULL,CURRENT_DATE,NULL,3);
#ytoxl赋予管理员角色
insert into user_urole(userUroleId,uroleId,userId) values(DEFAULT,'100002',(select userId from user where username='ytoxl'));

#资源表
#买家
#我的订单=====
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,0,'我的订单','/order/order-myOrders',1,1,1,CURRENT_DATE);
#我的订单 ajax
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/order/order-myOrders') temp),'我的订单ajax','/order/order-myOrdersAjax',1,0,1,CURRENT_DATE);
#我的订单 订单详情
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/order/order-myOrders') temp),'订单详情','/order/order-myOrderDetail',1,0,2,CURRENT_DATE);


#我的收货地址=====
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,0,'收货地址','/address/address-getUserAddress',1,1,2,CURRENT_DATE);
#我的收货地址 个人收货地址
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/address/address-getUserAddress') temp),'个人收货地址','/address/address-getSingleReceiverAddress',1,0,1,CURRENT_DATE);
#我的收货地址 添加收货地址
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/address/address-getUserAddress') temp),'添加收货地址','/address/address-addReceiverAddress',1,0,2,CURRENT_DATE);
#我的收货地址 删除收货地址
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/address/address-getUserAddress') temp),'删除收货地址','/address/address-delReceiverAddress',1,0,3,CURRENT_DATE);
#我的收货地址 默认收货地址
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/address/address-getUserAddress') temp),'默认收货地址','/address/address-setDefualtReceiverAddress',1,0,4,CURRENT_DATE);


#退货管理======
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,0,'退货管理','/order/order-myRefundManage',1,1,3,CURRENT_DATE);
#退货管理 订单退货记录
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/order/order-myRefundManage') temp),'订单退货记录','/order/order-myRefundRecord',1,0,1,CURRENT_DATE);


#修改密码=====
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,0,'修改密码','/user/user-updatePassword',1,1,4,CURRENT_DATE);
#修改密码 密码校验
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/user/user-updatePassword') temp),'密码校验','/user/user-checkPwdVaild',1,0,1,CURRENT_DATE);
#修改密码 修改密码页面展示
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/user/user-updatePassword') temp),'密码校验','/user/user-changePsw',1,0,2,CURRENT_DATE);

#积分管理=====
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,0,'积分管理','/user/points',1,1,5,CURRENT_DATE);

#我的吐槽=====
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,0,'我的吐槽','/suggest/searchSuggest-1',1,1,6,CURRENT_DATE);

#我的发现=====
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,0,'我的发现','/suggest/searchSuggest-0',1,1,7,CURRENT_DATE);
#我的发现 查询地址
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/suggest/searchSuggest-0') temp),'查询地址','/suggest/ajaxSaveSuggest',1,0,1,CURRENT_DATE);

#我的资料=====
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,0,'我的资料','/user/user-userInfo',1,1,8,CURRENT_DATE);
#我的资料 邮箱校验
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/user/user-userInfo') temp),'邮箱校验','/user/user-checkEmailVaild',1,0,1,CURRENT_DATE);

#分配商品
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,0,'分配商品','/order/order-getOrderInfo',1,1,9,CURRENT_DATE);

#提交订单
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,0,'提交订单','/order/order-submitOrder',1,1,10,CURRENT_DATE);

#订单付款
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,0,'订单付款','/netpay/payit',1,1,11,CURRENT_DATE);
#订单付款1
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,0,'订单付款1','/netpay/confirmOrder',1,1,12,CURRENT_DATE);

#查看购物车
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,0,'查看购物车','/order/order-showCart',1,1,13,CURRENT_DATE);





#买家 权限资源添加
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='前台买家'),(select uresourceId from uresource where url='/order/order-myOrders'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='前台买家'),(select uresourceId from uresource where url='/order/order-myOrdersAjax'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='前台买家'),(select uresourceId from uresource where url='/order/order-myOrderDetail'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='前台买家'),(select uresourceId from uresource where url='/address/address-getUserAddress'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='前台买家'),(select uresourceId from uresource where url='/address/address-getSingleReceiverAddress'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='前台买家'),(select uresourceId from uresource where url='/address/address-addReceiverAddress'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='前台买家'),(select uresourceId from uresource where url='/address/address-delReceiverAddress'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='前台买家'),(select uresourceId from uresource where url='/address/address-setDefualtReceiverAddress'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='前台买家'),(select uresourceId from uresource where url='/order/order-myRefundManage'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='前台买家'),(select uresourceId from uresource where url='/order/order-myRefundRecord'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='前台买家'),(select uresourceId from uresource where url='/user/user-updatePassword'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='前台买家'),(select uresourceId from uresource where url='/user/user-checkPwdVaild'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='前台买家'),(select uresourceId from uresource where url='/user/points'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='前台买家'),(select uresourceId from uresource where url='/suggest/searchSuggest-1'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='前台买家'),(select uresourceId from uresource where url='/suggest/searchSuggest-0'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='前台买家'),(select uresourceId from uresource where url='/user/user-userInfo'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='前台买家'),(select uresourceId from uresource where url='/user/user-checkEmailVaild'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='前台买家'),(select uresourceId from uresource where url='/suggest/ajaxSaveSuggest'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='前台买家'),(select uresourceId from uresource where url='/order/order-getOrderInfo'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='前台买家'),(select uresourceId from uresource where url='/order/order-submitOrder'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='前台买家'),(select uresourceId from uresource where url='/netpay/payit'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='前台买家'),(select uresourceId from uresource where url='/user/user-changePsw'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='前台买家'),(select uresourceId from uresource where url='/netpay/confirmOrder'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='前台买家'),(select uresourceId from uresource where url='/order/order-showCart'));



#商家
#商品=====
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,0,'商品','/seller/product/product-searchSellerProducts',1,1,1,CURRENT_DATE);
#商品 商品新增
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/seller/product/product-searchSellerProducts') temp),'商品新增','/seller/product/product-addProduct',1,0,1,CURRENT_DATE);
#商品 商品编辑
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/seller/product/product-searchSellerProducts') temp),'商品编辑','/seller/product/product-edit',1,0,2,CURRENT_DATE);
#商品 商品删除
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/seller/product/product-searchSellerProducts') temp),'商品删除','/seller/product/product-delete',1,0,3,CURRENT_DATE);
#商品 商品售罄
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/seller/product/product-searchSellerProducts') temp),'商品售罄','/seller/product/product-sellOut',1,0,4,CURRENT_DATE);


#订单=======
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,0,'订单','/admin/order/order-searchOrders4Seller',1,1,2,CURRENT_DATE);
#订单 同意付款
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/order/order-searchOrders4Seller') temp),'同意付款','/order/order-confirmPayment',1,0,1,CURRENT_DATE);
#订单 退货审核通过
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/order/order-searchOrders4Seller') temp),'退货审核通过','/order/order-passAudit',1,0,2,CURRENT_DATE);
#订单 退货审核不通过
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/order/order-searchOrders4Seller') temp),'退货审核不通过','/order/order-rejectAudit',1,0,3,CURRENT_DATE);
#订单 确认发货
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/order/order-searchOrders4Seller') temp),'确认发货','/admin/order/order-confirmSendProduct',1,0,4,CURRENT_DATE);
#订单 订单导出
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/order/order-searchOrders4Seller') temp),'订单导出','/admin/order/order-exportSellerOrders',1,0,5,CURRENT_DATE);


#发票管理=====
#INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,0,'发票管理','/admin/order/order-searchOrderInvoices4Seller',1,1,3,CURRENT_DATE);
#发票管理 寄出发票
#INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/order/order-searchOrderInvoices4Seller') temp),'寄出发票','/admin/order/order-confirmSendOrderInvoice',1,0,1,CURRENT_DATE);

#账号======
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,0,'账号','/admin/user/user-sellerDetailSeller',1,1,4,CURRENT_DATE);
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/user/user-sellerDetailSeller') temp),'修改密码','/admin/user/user-getPasswordSell',1,0,1,CURRENT_DATE);




#为商家添加权限 资源
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台卖家'),(select uresourceId from uresource where url='/seller/product/product-searchSellerproducts'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台卖家'),(select uresourceId from uresource where url='/seller/product/product-addProduct'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台卖家'),(select uresourceId from uresource where url='/seller/product/product-edit'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台卖家'),(select uresourceId from uresource where url='/seller/product/product-delete'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台卖家'),(select uresourceId from uresource where url='/seller/product/product-sellOut'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台卖家'),(select uresourceId from uresource where url='/admin/order/order-searchOrders4Seller'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台卖家'),(select uresourceId from uresource where url='/order/order-confirmPayment'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台卖家'),(select uresourceId from uresource where url='/order/order-passAudit'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台卖家'),(select uresourceId from uresource where url='/order/order-rejectAudit'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台卖家'),(select uresourceId from uresource where url='/admin/order/order-confirmSendProduct'));
#insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台卖家'),(select uresourceId from uresource where url='/admin/order/order-searchOrderInvoices4Seller'));
#insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台卖家'),(select uresourceId from uresource where url='/admin/order/order-confirmSendOrderInvoice'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台卖家'),(select uresourceId from uresource where url='/admin/user/user-sellerDetailSeller'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台卖家'),(select uresourceId from uresource where url='/admin/user/user-getPasswordSell'));



#后台管理员
#商品====
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,0,'商品','/admin/product/product-searchProducts',1,1,1,CURRENT_DATE);
#商品 查看商品
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/product/product-searchProducts') temp),'查看商品','/admin/product/product-view',1,0,1,CURRENT_DATE);
#商品 审核商品
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/product/product-searchProducts') temp),'审核商品','/admin/product/product-review',1,0,2,CURRENT_DATE);

#品牌====
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,0,'品牌','/admin/brand/brand-listUserBrans',1,1,2,CURRENT_DATE);
#品牌 编辑品牌
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/brand/brand-listUserBrans') temp),'编辑品牌','/admin/brand/brand-edit',1,0,1,CURRENT_DATE);
#品牌 新增品牌
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/brand/brand-listUserBrans') temp),'添加品牌','/admin/brand/brand-add',1,0,2,CURRENT_DATE);
#品牌 禁用品牌
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/brand/brand-listUserBrans') temp),'禁用品牌','/admin/brand/brand-forbiddenBrand',1,0,3,CURRENT_DATE);

#页面
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,0,'页面','/admin/suggest/suggest-searchSuggests',1,1,3,CURRENT_DATE);
#页面 底部信息
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/suggest/suggest-searchSuggests') temp),'底部信息','/admin/help/help-listHelps',1,0,1,CURRENT_DATE);
#页面 SEO信息
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/suggest/suggest-searchSuggests') temp),'SEO信息','/admin/seo/seo-listSeoConfigs',1,0,2,CURRENT_DATE);
#页面 友情链接
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/suggest/suggest-searchSuggests') temp),'友情链接','/admin/link/link-searchLinks',1,0,3,CURRENT_DATE);
#页面 吐槽发现
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/suggest/suggest-searchSuggests') temp),'吐槽发现','/admin/suggest/suggest-searchSuggests-sub',1,0,4,CURRENT_DATE);
#页面 广告位
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/suggest/suggest-searchSuggests') temp),'广告位','/admin/adv/show',1,0,5,CURRENT_DATE);






#订单====
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,0,'订单','/admin/order/order-searchOrders4Manager',1,1,5,CURRENT_DATE);
#订单 订单查询
#INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/order/order-searchOrders4Manager') temp),'订单查询','/admin/order/order-searchOrders4Manager',1,0,1,CURRENT_DATE);
#订单 订单导出
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/order/order-searchOrders4Manager') temp),'订单导出','/admin/order/order-exportOrders',1,0,2,CURRENT_DATE);
#订单 退货确认退款
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/order/order-searchOrders4Manager') temp),'退货确认退款','/order/order-confirmBackProduct',1,0,3,CURRENT_DATE);
#订单 客服事件
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/order/order-searchOrders4Manager') temp),'客服事件','/admin/orderCrm/orderCrm-addOrderCrm',1,0,4,CURRENT_DATE);


#买家管理====
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,0,'买家管理','/admin/user/user-searchBuyers',1,1,6,CURRENT_DATE);


#商家管理====
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,0,'商家管理','/admin/user/user-sellerManage',1,1,7,CURRENT_DATE);
#商家管理 添加商家
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/user/user-sellerManage') temp),'添加商家','/admin/user/user-seller',1,0,1,CURRENT_DATE);
#商家管理 导出商家
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/user/user-sellerManage') temp),'导出商家','/admin/user/user-listExportSellers',1,0,2,CURRENT_DATE);
#商家管理 禁用商家 
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/user/user-sellerManage') temp),'禁用商家 ','/admin/user/user-updateUserStatus',1,0,3,CURRENT_DATE);
#商家管理 编辑商家
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/user/user-sellerManage') temp),'编辑商家','/admin/user/user-saveSeller',1,0,4,CURRENT_DATE);
#商家管理 重置商家密码 
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/user/user-sellerManage') temp),'重置商家密码','/admin/user/user-resetPassword',1,0,5,CURRENT_DATE);



#发票管理====
#INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,0,'发票管理','/admin/order/order-searchOrderInvoices4Manager',1,1,8,CURRENT_DATE);


#权限====
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,0,'权限','/admin/auth/auth-listRoles',1,1,9,CURRENT_DATE);
#权限 新增角色
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/auth/auth-listRoles') temp),'新增角色','/admin/auth/auth-toEdit',1,0,1,CURRENT_DATE);
#权限 添加子账号
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/auth/auth-listRoles') temp),'添加子账号','/admin/auth/auth-toEditUser',1,0,2,CURRENT_DATE);

#专区
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,0,'专区管理','/admin/zone/zone-searchZone',1,1,10,CURRENT_DATE);
#专区 新增预售
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/zone/zone-searchZone') temp),'新增预售','/admin/zone/zone-showSaleZone',1,0,1,CURRENT_DATE);
#专区 新增抢购
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/zone/zone-searchZone') temp),'新增抢购','/admin/zone/zone-showSpecialZone',1,0,2,CURRENT_DATE);
#专区 新增专区
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,(select uid from (select uresourceId uid from uresource where url='/admin/zone/zone-searchZone') temp),'新增专区','/admin/zone/zone-showDetailZone',1,0,3,CURRENT_DATE);

  
#所在地管理
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,0,'所在地管理','/admin/prop/prop-loadRegion',1,1,11,CURRENT_DATE);


 

  

#修改密码
INSERT INTO uresource(uresourceId,parentId,uresourceName,url,STATUS,isMenu,rank,createTime) VALUES(DEFAULT,0,'修改密码','/admin/user/user-editPassword',1,1,10,CURRENT_DATE);




#后台管理员添加权限资源
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/product/product-searchProducts'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/product/product-view'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/product/product-review'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/brand/brand-listUserBrans'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/brand/brand-edit'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/brand/brand-add'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/brand/brand-forbiddenBrand'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/order/order-searchOrders4Manager'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/order/order-exportOrders'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/user/user-searchBuyers'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/user/user-sellerManage'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/user/user-seller'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/user/user-listExportSellers'));
#insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/order/order-searchOrderInvoices4Manager'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/auth/auth-listRoles'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/auth/auth-toEdit'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/auth/auth-toEditUser'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/orderCrm/orderCrm-addOrderCrm'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/suggest/suggest-searchSuggests'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/help/help-listHelps'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/seo/seo-listSeoConfigs'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/link/link-searchLinks'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/suggest/suggest-searchSuggests-sub'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/order/order-confirmBackProduct'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/user/user-editPassword'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/user/user-updateUserStatus'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/user/user-saveSeller'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/user/user-resetPassword'));

insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/adv/show'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/zone/zone-searchZone'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/zone/zone-showSaleZone'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/zone/zone-showSpecialZone'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/zone/zone-showDetailZone'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台管理员'),(select uresourceId from uresource where url='/admin/prop/prop-loadRegion'));



insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台卖家'),(select uresourceId from uresource where url='/admin/auth/auth-listRoles'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台卖家'),(select uresourceId from uresource where url='/admin/auth/auth-toEdit'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台卖家'),(select uresourceId from uresource where url='/admin/auth/auth-toEditUser'));
insert into urole_uresource(uroleUresourceId,uroleId,uresourceId)values(DEFAULT,(select uroleId from urole where uroleName='后台卖家'),(select uresourceId from uresource where url='/admin/user/user-editPassword'));
#初始化城市数据













