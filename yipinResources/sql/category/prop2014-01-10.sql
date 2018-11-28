create table prop (
    propId int primary key AUTO_INCREMENT,
    name varchar(64) not null,
    code varchar(64),
    level int,
    parentid int,
    rank int,
    status int,
    createTime timestamp null,
    updateTime timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)  AUTO_INCREMENT=10000
;
create index IDX_PROP_CODE on PROP (CODE);
/**一级分类*/
insert into prop values(10000,'原产地','10000',0,0,1,1,now(),now());
insert into prop values(10001,'商品分类','10001',0,0,1,1,now(),now());
insert into prop values(10002,'适用人群','10002',0,0,1,1,now(),now());
/**适用人群*/
insert into prop values(10003,'老人','10002-10003',1,10002,1,1,now(),now());
insert into prop values(10004,'小孩','10002-10004',1,10002,2,1,now(),now());
insert into prop values(10005,'女人','10002-10005',1,10002,3,1,now(),now());
insert into prop values(10006,'男人','10002-10006',1,10002,4,1,now(),now());

/***米面杂粮*/
insert into prop values(10007,'米面杂粮','10001-10007',1,10001,1,1,now(),now());
insert into prop values(10008,'大米','10001-10007-10008',2,10007,1,1,now(),now());
insert into prop values(10009,'杂粮','10001-10007-10009',2,10007,2,1,now(),now());
insert into prop values(10010,'面粉','10001-10007-10010',2,10007,3,1,now(),now());
insert into prop values(10011,'挂面','10001-10007-10011',2,10007,4,1,now(),now());
/***坚果炒货*/
insert into prop values(10012,'坚果炒货','10001-10012',1,10001,2,1,now(),now());
insert into prop values(10013,'杏仁','10001-10012-10013',2,10012,1,1,now(),now());
insert into prop values(10014,'巴旦木','10001-10012-10014',2,10012,2,1,now(),now());
insert into prop values(10015,'腰果','10001-10012-10015',2,10012,3,1,now(),now());
insert into prop values(10016,'开心果','10001-10012-10016',2,10012,4,1,now(),now());
insert into prop values(10017,'核桃','10001-10012-10017',2,10012,5,1,now(),now());
insert into prop values(10018,'松子','10001-10012-10018',2,10012,6,1,now(),now());
insert into prop values(10019,'碧根果','10001-10012-10019',2,10012,7,1,now(),now());
insert into prop values(10020,'榛子','10001-10012-10020',2,10012,8,1,now(),now());
insert into prop values(10021,'花生','10001-10012-10021',2,10012,9,1,now(),now());
/***南北干货*/
insert into prop values(10022,'南北干货','10001-10022',1,10001,3,1,now(),now());
insert into prop values(10023,'香菇','10001-10022-10023',2,10022,1,1,now(),now());
insert into prop values(10024,'木耳','10001-10022-10024',2,10022,2,1,now(),now());
insert into prop values(10025,'枸杞','10001-10022-10025',2,10022,3,1,now(),now());
insert into prop values(10026,'银耳','10001-10022-10026',2,10022,4,1,now(),now());
insert into prop values(10027,'桂圆','10001-10022-10027',2,10022,5,1,now(),now());
insert into prop values(10028,'腐竹','10001-10022-10028',2,10022,6,1,now(),now());
insert into prop values(10029,'粉丝','10001-10022-10029',2,10022,7,1,now(),now());
insert into prop values(10030,'百合','10001-10022-10030',2,10022,8,1,now(),now());
insert into prop values(10031,'莲子','10001-10022-10031',2,10022,9,1,now(),now());
insert into prop values(10032,'菌类','10001-10022-10032',2,10022,10,1,now(),now());
insert into prop values(10033,'其它干货','10001-10022-10033',2,10022,11,1,now(),now());
/***蜜饯果脯*/
insert into prop values(10034,'蜜饯果脯','10001-10034',1,10001,4,1,now(),now());
insert into prop values(10035,'蜜饯','10001-10034-10035',2,10034,1,1,now(),now());
insert into prop values(10036,'肉干','10001-10034-10036',2,10034,2,1,now(),now());
insert into prop values(10037,'豆干','10001-10034-10037',2,10034,3,1,now(),now());
insert into prop values(10038,'花生','10001-10034-10038',2,10034,4,1,now(),now());
insert into prop values(10039,'瓜子','10001-10034-10039',2,10034,5,1,now(),now());
insert into prop values(10040,'大枣','10001-10034-10040',2,10034,6,1,now(),now());
insert into prop values(10041,'枸杞,栗子','10001-10034-10041',2,10034,7,1,now(),now());
insert into prop values(10042,'山楂类','10001-10034-10042',2,10034,8,1,now(),now());
insert into prop values(10043,'果干','10001-10034-10043',2,10034,9,1,now(),now());
insert into prop values(10044,'葡萄干','10001-10034-10044',2,10034,10,1,now(),now());
insert into prop values(10045,'莓类','10001-10034-10045',2,10034,11,1,now(),now());
insert into prop values(10046,'橄榄','10001-10034-10046',2,10034,12,1,now(),now());
insert into prop values(10047,'桂圆类','10001-10034-10047',2,10034,13,1,now(),now());
/***酒*/
insert into prop values(10048,'酒','10001-10048',1,10001,5,1,now(),now());
insert into prop values(10049,'白酒','10001-10048-10049',2,10048,1,1,now(),now());
insert into prop values(10050,'啤酒','10001-10048-10050',2,10048,2,1,now(),now());
insert into prop values(10051,'黄酒','10001-10048-10051',2,10048,3,1,now(),now());
insert into prop values(10052,'滋补酒','10001-10048-10052',2,10048,4,1,now(),now());
insert into prop values(10053,'葡萄酒','10001-10048-10053',2,10048,5,1,now(),now());
/***茶*/
insert into prop values(10054,'茶','10001-10054',1,10001,6,1,now(),now());
insert into prop values(10055,'乌龙茶','10001-10054-10055',2,10054,1,1,now(),now());
insert into prop values(10056,'普洱茶','10001-10054-10056',2,10054,2,1,now(),now());
insert into prop values(10057,'红茶','10001-10054-10057',2,10054,3,1,now(),now());
insert into prop values(10058,'绿茶','10001-10054-10058',2,10054,4,1,now(),now());
insert into prop values(10059,'袋泡茶','10001-10054-10059',2,10054,5,1,now(),now());
insert into prop values(10060,'黑茶','10001-10054-10060',2,10054,6,1,now(),now());
insert into prop values(10061,'白茶','10001-10054-10061',2,10054,7,1,now(),now());
insert into prop values(10062,'黄茶','10001-10054-10062',2,10054,8,1,now(),now());
/***生鲜食品*/
insert into prop values(10063,'生鲜食品','10001-10063',1,10001,7,1,now(),now());
insert into prop values(10064,'水果','10001-10063-10064',2,10063,1,1,now(),now());
insert into prop values(10065,'蔬菜','10001-10063-10065',2,10063,2,1,now(),now());
insert into prop values(10066,'禽蛋','10001-10063-10066',2,10063,3,1,now(),now());
insert into prop values(10067,'肉制品','10001-10063-10067',2,10063,4,1,now(),now());
insert into prop values(10068,'海鲜水产','10001-10063-10068',2,10063,5,1,now(),now());
insert into prop values(10069,'熟肉制品','10001-10063-10069',2,10063,6,1,now(),now());
insert into prop values(10070,'其他','10001-10063-10070',2,10063,7,1,now(),now());
/***参茸礼品*/
insert into prop values(10071,'参茸礼品','10001-10071',1,10001,8,1,now(),now());
insert into prop values(10072,'人参','10001-10071-10072',2,10071,1,1,now(),now());
insert into prop values(10073,'鹿茸','10001-10071-10073',2,10071,2,1,now(),now());
insert into prop values(10074,'高丽参','10001-10071-10074',2,10071,3,1,now(),now());
insert into prop values(10075,'海参','10001-10071-10075',2,10071,4,1,now(),now());
insert into prop values(10076,'冬虫夏草','10001-10071-10076',2,10071,5,1,now(),now());
insert into prop values(10077,'燕窝','10001-10071-10077',2,10071,6,1,now(),now());
insert into prop values(10078,'林蛙油','10001-10071-10078',2,10071,7,1,now(),now());
insert into prop values(10079,'其他','10001-10071-10079',2,10071,8,1,now(),now());
/***礼盒类*/
insert into prop values(10080,'礼盒类','10001-10080',1,10001,9,1,now(),now());
insert into prop values(10081,'送晚辈','10001-10080-10081',2,10080,1,1,now(),now());
insert into prop values(10082,'送长辈','10001-10080-10082',2,10080,2,1,now(),now());
insert into prop values(10083,'送老婆','10001-10080-10083',2,10080,3,1,now(),now());
insert into prop values(10084,'送老公','10001-10080-10084',2,10080,4,1,now(),now());
insert into prop values(10085,'送领导','10001-10080-10085',2,10080,5,1,now(),now());
insert into prop values(10086,'送亲朋','10001-10080-10086',2,10080,6,1,now(),now());
insert into prop values(10087,'送师长','10001-10080-10087',2,10080,7,1,now(),now());

