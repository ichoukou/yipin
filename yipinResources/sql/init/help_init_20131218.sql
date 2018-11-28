/*
SQLyog Ultimate v9.62 
MySQL - 5.5.33-log : Database - yipindb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `help` */

CREATE TABLE `help` (
  `helpId` int(11) NOT NULL AUTO_INCREMENT,
  `helpCategoryId` int(11) DEFAULT NULL,
  `content` longtext,
  PRIMARY KEY (`helpId`)
) ENGINE=InnoDB AUTO_INCREMENT=100016 DEFAULT CHARSET=utf8 COMMENT='网站帮助内容';

/*Data for the table `help` */

insert  into `help`(`helpId`,`helpCategoryId`,`content`) values (100000,100005,'<style type=\"text/css\">.shop-step h5{\nfont-size:14px;\ncolor:#ffffff;\nheight:25px;\nline-height:25px;\npadding-left:12px;\nbackground-color: #49cbcd;\nfont-family:\"宋体\";\nfont-weight:bold;\n}\n.shop-step .txt{\npadding: 8px 0 8px 12px;\n}\n.shop-step .img{\npadding: 15px 0 30px 20px;\n}\n.shop-step .img img{\nmax-width: 767px;\n}\n</style>\n<div class=\"shop-step\">\n<p>&nbsp;</p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:16px;\">Step 1 进入一城一品首页，点击页面上的&ldquo;免费注册&rdquo;</span></p>\n\n<p>&nbsp;</p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:16px;\">Step 2 按照网页提示，填写准确的注册信息后点击&ldquo;确认提交&rdquo;</span></p>\n\n<p>&nbsp;</p>\n\n<p>&nbsp;</p>\n\n<p>&nbsp;</p>\n</div>\n\n<p>&nbsp;</p>\n'),(100001,100006,'<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"186\" width=\"526\">\n	<tbody>\n		<tr>\n			<td nowrap=\"nowrap\" style=\"width:587px;height:23px;\">\n			<p>&nbsp;</p>\n\n			<p>&nbsp;</p>\n\n			<p><span style=\"font-size:16px;\">Step 1 选择页面上感兴趣的商品</span></p>\n\n			<p>&nbsp;</p>\n\n			<p><span style=\"font-size:16px;\">Step 2 点击进入商品也对您感兴趣的商品进行详细了解</span></p>\n\n			<p>&nbsp;</p>\n\n			<p><span style=\"font-size:16px;\">Step 3 点击预约购买/立即购买进入购买流程</span></p>\n\n			<p>&nbsp;</p>\n\n			<p><span style=\"font-size:16px;\">Step 4 选择您想要购买的商品详细参数：包括规格、颜色、数量</span></p>\n\n			<p>&nbsp;</p>\n\n			<p><span style=\"font-size:16px;\">Step 5 选择您需要的配送方案：包括用途、地址、支付方式、配送方式、发票信息</span><span style=\"font-size:16px;\">（如果您是首次购物，请按照提示步骤，首先填写收货人信息，之后选择支付方式，您可以选择支付宝支付；收货人信息可以进行保存；如果您需要发票可以在发票信息中选择您所需要的发票类型和填写抬头）</span></p>\n\n			<p>&nbsp;</p>\n\n			<p><span style=\"font-size:16px;\">Step 6 确认并提交订单，之后您的订单将可以在页面&quot;我的一品&quot;处进行查看</span></p>\n\n			<p>&nbsp;</p>\n\n			<p><span style=\"font-size:16px;\">Step 7 进入核对页面后请仔细核对付款信息，确认无误后进入支付宝支付</span></p>\n\n			<p>&nbsp;</p>\n\n			<p><span style=\"font-size:16px;\">Step 8 在支付宝完成支付后将会跳转回一城一品页面</span></p>\n\n			<p>&nbsp;</p>\n\n			<p><span style=\"font-size:16px;\">Step 9 订单支付成功后，我们会尽快为您安排发货（预售商品按指定的发货日期发货），货物发出后您可以根据您订单页面上的物流单号进行物流跟踪查询，请耐心等待您的商品送达。</span></p>\n			</td>\n		</tr>\n	</tbody>\n</table>\n'),(100002,100007,'<p>&nbsp;</p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:16px;\">订单查询：</span></p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:16px;\">Step 1 订购成功后，您可以在首页点击&ldquo;我的一品&rdquo;</span></p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:16px;\">Step 2 在我的一品中点击&ldquo;我的订单&rdquo;，您可以按照时间（最近一个月、一个月之前）和状态（全部、待付款、待发货、已完成、已取消）查询您所需查看的订单</span></p>\n\n<p><span style=\"font-size:16px;\">&nbsp;</span></p>\n\n<p><span style=\"font-size:16px;\">注意：在下单后不可以自主进行信息修改，如果您需要修改信息，请联系我司客服，我们会尽快为您处理。</span></p>\n\n<p>&nbsp;</p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:16px;\">配送信息跟踪：</span></p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:16px;\">Step 1 在&ldquo;我的订单&rdquo;中点击您所需查询的订单</span></p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:16px;\">Step 2 在订单详情中会显示详细的物流跟踪信息，您也可以根据显示的单号在快递公司官网上进行查询。</span></p>\n\n<p>&nbsp;</p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:16px;\">常见问题：</span></p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:16px;\">问题1：结算过程中，因系统错误未完成支付的订单，应该怎么处理？下单成功后还有其他可以操作吗？</span></p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:16px;\">回答1：建议您可先在我的一品查询&ldquo;我的订单&rdquo;，核实款项是否已支付成功。如未支付成功，请您在订单管理中，点击&ldquo;付款&rdquo;进行付款。如您的款项已支付成功，但订单状态显示未支付，请您联系我司客服，我们将尽快为您处理。</span></p>\n\n<p>&nbsp;</p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:16px;\">问题2：下单成功后，如何查询配送情况？</span></p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:16px;\">回答2：当您的订单状态为&ldquo;已发货&rdquo;，此时您的商品已从品牌供应商仓库发出，并由物流公司进行配送。您可在我的一品&ldquo;我的订单 &rdquo;中查看承运物流公司名称及运单号，也可到物流公司网站进行追踪查询。</span></p>\n\n<p>&nbsp;</p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:16px;\">问题3：下单后订单为什么没显示？</span></p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:16px;\">回答3：在订单提交高峰时段，新订单可能一段时间之后才会在&ldquo;我的帐户&rdquo;中显示。如果您在&ldquo;我的帐户&rdquo;中暂未找到这张订单，请您耐心等待。</span></p>\n'),(100003,100008,'<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"53\" width=\"1822\">\n	<tbody>\n		<tr>\n			<td nowrap=\"nowrap\" style=\"width: 587px; height: 23px;\">\n			<p>&nbsp;</p>\n\n			<p>&nbsp;</p>\n\n			<p><span style=\"font-size:16px;\">一城一品将通过专业的物流公司为会员提供快速便捷的包裹配送。</span></p>\n\n			<p>&nbsp;</p>\n\n			<p><span style=\"font-size:16px;\">会员在一城一品成功购买商品的每一个订单，都会根据您所订购的商品生成相应的包裹发送到您的手中。</span></p>\n\n			<p>&nbsp;</p>\n\n			<p><span style=\"font-size:16px;\">一城一品配送范围覆盖全国大部分地区（港澳台地区除外）</span></p>\n			</td>\n		</tr>\n	</tbody>\n</table>\n'),(100004,100009,'<p>&nbsp;</p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:16px;\">为保障您的权益，请您在收到商品时与配送员当面核对，核对范围包括但不限于：商品种类、规格、数量（包括商品最小包装单位）、金额、赠品、外包装、票据等是否与订单一致，准确无误再进行签收。签收后，&ldquo;一城一品&rdquo;不再为以上问题负责。根据不同食品的不同属性，在验货时您可能需要一并检查以下所列举情况中的一个或多个方面，包括但不限于：商品的保质期、商品的形状是否变化、商品本身标识是否可辨、商品的防伪标签是否已被完全刮开等。如果您在签收前已确认上述问题无误，请您在&ldquo;一城一品&rdquo;发货单上签字确认。如果您的订单交由他人代收，代收人享有与您等同的权利，我们将视为您本人签收。如果您在验货时发现商品存在上述问题，经配送员或配送员在场时与客服联络确认，可以选择拒收整个包裹。但如果包裹商品含有密封包装，且拆封后无质量问题时，不可拒收。拒收业务完成后，已支付的款项将按原支付方式和原路径安排退款。对于&ldquo;一城一品&rdquo;原因造成的订单拒收，客户无需支付配送费；客户原因造成的订单拒收，&ldquo;一城一品&rdquo;将收取您的单程配送费。</span></p>\n\n<p>&nbsp;</p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:16px;\"><strong>注意：长时间未收到货品可能出现的问题</strong></span></p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:16px;\">（1） 请您确保订单中的收货地址、邮编、电话、Email地址等各项信息的准确性，以便商品及时、准确地发出，并随时查看用户中心里&quot;我的订单&quot;的状态更新情况，如果长时间未收到货，可能是由于您填写的地址或电话号码有误，您可联系客服为您处理。</span></p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:16px;\">（2） 配送过程中如果我们联络您的时间超过7天未得到回复，此订单将被默认为您已经放弃订购。</span></p>\n'),(100005,100010,'<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:587px;\" width=\"587\">\n	<tbody>\n		<tr>\n			<td nowrap=\"nowrap\" style=\"width:587px;height:23px;\">\n			<p>&nbsp;</p>\n\n			<p>&nbsp;</p>\n\n			<p><span style=\"font-size:16px;\">目前一城一品仅提供支付宝在线支付方式，为保证购物便捷，其他支付方式正在紧急开发中。</span></p>\n			</td>\n		</tr>\n	</tbody>\n</table>\n'),(100006,100011,'<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:587px;\" width=\"587\">\n	<tbody>\n		<tr>\n			<td nowrap=\"nowrap\" style=\"width:587px;height:23px;\">\n			<p>&nbsp;</p>\n\n			<p>&nbsp;</p>\n\n			<p><span style=\"font-size:16px;\">1、发票抬头：</span></p>\n\n			<p><br />\n			<span style=\"font-size:16px;\">发票抬头不能为空，可填写个人姓名或单位名称。</span></p>\n\n			<p>&nbsp;</p>\n\n			<p><br />\n			<span style=\"font-size:16px;\">2、发票内容：</span></p>\n\n			<p><br />\n			<span style=\"font-size:16px;\">一城一品可开具发票内容为所购买商品明细</span></p>\n\n			<p>&nbsp;</p>\n\n			<p>&nbsp;</p>\n\n			<p><span style=\"font-size:16px;\">3、索取发票</span></p>\n\n			<p><br />\n			<span style=\"font-size:16px;\">请在提交订单页面，勾选&quot;需要发票&quot;，按提示填写发票抬头，发票将会随您的订单商品一起送达。如需发票配送其它地址，请选择或新增正确地址。</span></p>\n\n			<p>&nbsp;</p>\n\n			<p>&nbsp;</p>\n\n			<p><span style=\"font-size:16px;\">4、补开、换开发票说明</span></p>\n\n			<p><br />\n			<span style=\"font-size:16px;\">1）补开发票期限：订单提交之日起，一个月内。</span></p>\n\n			<p><br />\n			<span style=\"font-size:16px;\">2）如果收到商品时没有发票，请在补开发票期限之内联系我们，并提供订单号、有效的邮寄地址、邮编及收件人姓名，我们会在补开发票后以快递方式寄出。</span><br />\n			&nbsp;</p>\n\n			<p><span style=\"font-size:16px;\">3）如果收到发票后，发票抬头、金额与提交订单时所填不符，请先与客服联络，再将发票寄至以下地址，并注明订单号、发票抬头。我们收到发票后会重新开具发票，并以快递方式寄出。</span></p>\n\n			<p>&nbsp;</p>\n\n			<p>&nbsp;</p>\n\n			<p><span style=\"font-size:16px;\">开发票的注意事项</span></p>\n\n			<p>&nbsp;</p>\n\n			<p><span style=\"font-size:16px;\">1.个人及不具有一般纳税人资格的企业客户，均开具普通发票。</span></p>\n\n			<p>&nbsp;</p>\n\n			<p><span style=\"font-size:16px;\">2.同一卖家售出的商品对应一张发票</span></p>\n\n			<p>&nbsp;</p>\n\n			<p><span style=\"font-size:16px;\">3.发票金额不能高于订单金额，使用代金券支付的金额不开具发票</span></p>\n\n			<p>&nbsp;</p>\n\n			<p><span style=\"font-size:16px;\">4.促销直减金额不开具发票</span></p>\n\n			<p>&nbsp;</p>\n\n			<p><span style=\"font-size:16px;\">5.积分换购商品金额不开具发票</span></p>\n\n			<p>&nbsp;</p>\n\n			<p><span style=\"font-size:16px;\">6.收到发票后，如发现发票抬头、内容或金额等不符，请先与商家客服中心联系确认。</span></p>\n			</td>\n		</tr>\n		<tr>\n			<td nowrap=\"nowrap\" style=\"width:587px;height:23px;\">\n			<p>&nbsp;</p>\n\n			<p><span style=\"font-size:16px;\">7.发票快递费用，如因商品质量问题进行退货的，快递费用由买家先行垫付，卖家收到退货货后，将商品费用及快递费用一起打款给买家；如因买家自身原因进行退货的，退货及发票快递费用由买家自行承担。</span></p>\n			</td>\n		</tr>\n	</tbody>\n</table>\n'),(100007,100012,'<p align=\"left\">&nbsp;</p>\n\n<p align=\"left\">&nbsp;</p>\n\n<p><span style=\"font-size:16px;\">一城一品所售的商品均为正规品牌，供应商类型包括品牌生产商、品牌授权总代理</span></p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:16px;\">商、品牌授权总经销商、品牌分公司、品牌分支机构及国际品牌驻中国的办事处。</span></p>\n'),(100008,100013,'<p align=\"left\">&nbsp;</p>\n\n<p align=\"left\">&nbsp;</p>\n\n<p align=\"left\"><span style=\"font-size:16px;\">退货原则：</span></p>\n\n<p align=\"left\">&nbsp;</p>\n\n<p align=\"left\"><span style=\"font-size:16px;\">1、会员在一城一品购买商品如出现商品质量问题，会员可申请退货</span></p>\n\n<p align=\"left\">&nbsp;</p>\n\n<p align=\"left\"><span style=\"font-size:16px;\">2、出现以下任一情形时均不予退货</span></p>\n\n<p align=\"left\"><span style=\"font-size:16px;\">&nbsp; &nbsp;1）超过退货有效期限（商品订单生成后15天）的退货商品；</span></p>\n\n<p align=\"left\"><span style=\"font-size:16px;\">&nbsp;&nbsp; 2）未经网上申请退货或未通过商家客服中心申请退货而自行寄回至发货仓库的；&nbsp;&nbsp;&nbsp;</span></p>\n\n<p align=\"left\">&nbsp;</p>\n\n<p align=\"left\">&nbsp;</p>\n\n<p align=\"left\"><span style=\"font-size:16px;\">退货细则：</span></p>\n\n<p align=\"left\">&nbsp;</p>\n\n<p align=\"left\"><span style=\"font-size:16px;\">1、从一城一品站订购的食品类商品，为了保证食品安全，请在收货时务必仔细查验商品及保质期，如发现商品存在包装破损，或保质期问题：如商品过期或离过期不到2个月等，请您现场向配送人员指出后拒收整个包裹。此类商品，一旦签收后，不予接受退货。</span></p>\n\n<p align=\"left\">&nbsp;</p>\n\n<p align=\"left\"><span style=\"font-size:16px;\">2、如果由国家食品质量监督检验机构公告的有质量问题的商品，一城一品会提供退货。</span></p>\n\n<p align=\"left\">&nbsp;</p>\n\n<p align=\"left\">&nbsp;</p>\n\n<p align=\"left\"><span style=\"font-size:16px;\"><strong>退货办理流程：</strong></span></p>\n\n<p align=\"left\">&nbsp;</p>\n\n<p align=\"left\"><span style=\"font-size:16px;\">Step 1 进入用户中心点击&ldquo;我的订单&rdquo;找到需要退货的包裹点击&ldquo;申请退货&rdquo;，上传能显示商品质量问题的照片；</span></p>\n\n<p align=\"left\">&nbsp;</p>\n\n<p align=\"left\"><span style=\"font-size:16px;\">Step 2 一城一品会尽快确认是否准于退货；</span></p>\n\n<p align=\"left\">&nbsp;</p>\n\n<p align=\"left\"><span style=\"font-size:16px;\">Step 3 一城一品确认准予退货后，您可将货品邮寄商品给商家，需先垫付费用；</span></p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:16px;\">Step 4 退款说明：如因商品质量问题进行退货的，快递费用由买家先行垫付，卖家收到退货货后，将商品费用及快递费用一起打款给买家；如因买家自身原因进行退货的，退货及发票快递费用由买家自行承担。</span></p>\n'),(100011,100016,'<p>&nbsp;</p>\n\n<p>&nbsp;</p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:16px;\">&nbsp;&nbsp;&nbsp; &ldquo;一城一品&rdquo; 是上海圆通新龙电子商务有限公司旗下在2013年推出又一全新品牌，依托投资方圆通蛟龙投资发展（集团）有限公司强大的集团资源及行业优势，秉承上海圆通新龙电子商务有限公司为广大家庭提供一站式购物的理念，致力于打造一个值得消费者信赖的平台。&ldquo;一城一品&rdquo;将竭力寻找各地最优质的商品，经过严格筛选，专业选品，严格把控商品质量，让消费者不出家门就可以得到各地一等一品质商品；&ldquo;一城一品&ldquo;通过量身定制的各种服务项目和解决方案，帮助大量供应商在降低销售成本的同时快速开拓网络销售通路，扩大品牌影响力，实现供应商、消费者都满意的共赢局面。</span></p>\n'),(100013,100018,'<p>&nbsp;</p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:16px;\">如果您对我们的网站、商品和服务有任何意见或建议欢迎您随时与我们联络，我们将竭诚为您服务。</span></p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:16px;\">服务热线：021-60919872转8012</span></p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:16px;\">客服邮箱：service@yichengpin.com</span></p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:16px;\">工作时间：周一到周五，上午：9：00-11：30，下午13：00：00-18：00（节假日除外）</span></p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:16px;\">​办公地址：上海市徐汇区桂林路396号1号楼</span></p>\n'),(100015,100020,'<p>&nbsp;</p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:16px;\">一城一品本着互惠共赢的原则与广大供应商精诚合作，我们会及时准确地将各种销售信息和用户体验反馈给供应商，帮助供应商伙伴更好地了解销售状况，市场需求，并及时准确地作出货品调整，从而获得良好的业绩表现。</span></p>\n\n<p>&nbsp;</p>\n\n<p><span style=\"font-size:16px;\">诚邀国内外知名品牌的优质供应商，与一城一品携手合作共谋发展。</span></p>\n\n<p><span style=\"font-size:16px;\">​</span></p>\n\n<p><span style=\"font-size:16px;\">请供应商提供资料至一城一品招商部的邮箱：zhaoshang@yichengpin.com，并务必附上联系方式，以便我司能尽快与您联系洽谈合作事宜。</span></p>\n');

/*Table structure for table `help_category` */

CREATE TABLE `help_category` (
  `helpCategoryId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `rank` int(11) DEFAULT NULL,
  PRIMARY KEY (`helpCategoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=100021 DEFAULT CHARSET=utf8;

/*Data for the table `help_category` */

insert  into `help_category`(`helpCategoryId`,`name`,`parentId`,`rank`) values (100000,'购物指南',0,1),(100001,'关于配送',0,2),(100002,'关于支付',0,3),(100003,'售后服务',0,4),(100004,'关于我们',0,5),(100005,'新用户注册',100000,1),(100006,'购物流程',100000,2),(100007,'订单查询与操作',100000,3),(100008,'配送方式',100001,1),(100009,'验货与签收',100001,2),(100010,'支付方式',100002,1),(100011,'关于发票',100002,2),(100012,'正品保障',100003,1),(100013,'退换货政策',100003,2),(100016,'公司介绍',100004,1),(100018,'联系方式',100004,3),(100020,'商务合作',100004,5);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;