<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN" class="ua-window">
<head>
	<c:if test="${empty seoTitle }">
   	<title>雪蛤油-${_webSiteName }</title>
	</c:if>
	<c:if test="${!empty seoTitle }">
	<title>${seoTitle}</title>
	</c:if>
	<c:if test="${empty seoKeyWords }">
   	<meta name="keywords" content="" >
	</c:if>
	<c:if test="${!empty seoKeyWords }">
	<meta name="keywords" content="${seoKeyWords}">
	</c:if>
	<c:if test="${empty seoDescription }">
   	<meta name="description" content="" >
	</c:if>
	<c:if test="${!empty seoDescription}">
	<meta name="description" content="${seoDescription}">
	</c:if>
	<jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
	<!-- page -->
	<link rel="stylesheet" type="text/css" href="${_cssPath}/pages/oilDetail.css" media="all" />
</head>
<body>
  	<!--头部导航 start-->
	<jsp:include page="/WEB-INF/pages/include/head.jsp"></jsp:include>
	<!--头部导航 end--> 
	<!--焦点图 start-->
	<div class="focus" id="dBanner">
		<div class="show_wp">
			<div class="pro_show" style="background-image:url('${_imagesPath}/uploadfolder/oil/oil1.jpg');background-position:center top;background-repeat:no-repeat;">
				<div class="w_norm relative">
					<dl>
						<dt>吉林 · 通化雪蛤油</dt>
                          <dd class="f63c30">${map['JLWY01'].unitPrice }元/5g</dd>
						<dd>
						<p>
								中国长白山林蛙又名蛤什蟆<br />
								,香港、广东人均称为雪蛤，属于珍稀两栖动物<br />
								是中国著名的集药用、滋补和美容于一体的经济蛙种
							</p>
						<p>雪蛤油是雌蛙怀卵成熟期的输卵管<br />
						其中雌二醇和睾酮分别是雌性激素和雄性激素中生理作用最强的激素，<br />
 						具有显著的滋阴强肾激发免疫功能和调节机理作用。
							</p>
 						</dd>
						<dd>
							<a target="_blank" href="${_ctxPath}/buy-${brand.brandId}.htm" target="_blank" rel="nofollow" class="buy">
							<c:choose>
								<c:when test="${isPreSell }">预约购买</c:when>
								<c:otherwise>立即购买</c:otherwise>
							</c:choose>
							</a>
						</dd>
					</dl>
				</div>
			</div>
		</div>
	</div>
	<!--焦点图 end-->
	<!--详情部分 start-->
	<div class="w_norm">
		<div class="prod_wp">
			<!--舌尖上的东北 · 吉林-->
			<div class="bck1">
				<h2>人间第一峰 | 长白山</h2>
				<div class="fungus_introduce">
					长白山的生态系统比较完整，自然环境复杂多样，山上气候变化无常，植物种类丰富多彩<br />
                    长白山自古是人参等高档药材的产地，满族先民因此经营药材生意历史悠久。
				</div>
				<div class="fungus_img">
					<img src="${_imagesPath}/uploadfolder/oil/oil2.jpg" alt="人间第一峰 | 长白山" width="999px" height="498px"/>
				</div>
			</div>
			<!--绿色软黄金 | 养颜圣品-->
			<div class="bck2 J_anchor" id="fungus2" data-name="营养功效介绍">
				<div class="cf">
					<div class="fn_left">
						<img src="${_imagesPath}/uploadfolder/oil/oil3.jpg" alt="绿色软黄金 | 养颜圣品" width="496px" height="350px"/>
					</div>
					<div class="fn_left fungus_right">
						<h2>绿色软黄金 | 养颜圣品</h2>
						<div class="fungus_introduce">
							<p>中国长白山林蛙又名蛤什蟆，香港、广东人均称为雪蛤，属于珍稀两栖动物，是中国著名的集药用、滋补和美容于一体的经济蛙种。秋天捕捉的林蛙肚子里的油长成，晒干后扒出来就是蛤蟆油。现在也称为雪蛤油或雪蛤膏。</p>
							<p>千百年来，一直享有林蛙油盛誉，女性更是将其视为珍宝，一为保持容貌俏丽，二为繁衍强壮后代以积蓄体力，妇女坐月子以其作为补身之食是必不可少的。至清朝，更成为皇室贡品；现在，因其珍奇的营养和保健价值，被称为“ 绿色软黄金”。</p>
						</div>
					</div>
				</div>
				<ul class="cf">
					<li>
						<img src="${_imagesPath}/uploadfolder/oil/oil4.jpg" alt="延缓衰老" />
						<p>延缓衰老</p>
					</li>
					<li>
						<img src="${_imagesPath}/uploadfolder/oil/oil5.jpg" alt="提高免疫" />
						<p>提高免疫</p>
					</li>
					<li>
						<img src="${_imagesPath}/uploadfolder/oil/oil6.jpg" alt="利冠心病" />
						<p>利冠心病</p>
					</li>
					<li class="p0">
						<img src="${_imagesPath}/uploadfolder/oil/oil21.jpg" alt="补肾益精" />
						<p>补肾益精</p>
					</li>
				</ul>
			</div>
			<!--高价值营养结构组成-->
			<div class="effect">
				<h2>高价值营养结构组成</h2>
				<div class="fungus_introduce">
					雪蛤油是雌蛙怀卵成熟期的输卵管，它含有18种氨基酸、13种无机元素、9种维生素和多种复合多肽等生物活性因子<br />
                    特别富含三种性激素，即雌二醇、睾酮、孕酮。其中雌二醇和睾酮分别是雌性激素和雄性激素中生理作用最强的激素<br />
                    具有显著的滋阴强肾激发免疫功能和调节机理作用
				</div>
				<div class="fungus_img">
					<img src="${_imagesPath}/uploadfolder/oil/oil7.jpg" alt="高价值营养结构组成" width="881px" height="548px"/>
				</div>
			</div>
			<!--真正的雪蛤油-->
			<div class="bck3 cf J_anchor" id="fungus4" data-name="鉴别方法介绍">
				<div class="fn_left fungus_right">
					<h2>真正的雪蛤油</h2>
					<div class="fungus_introduce">
						<p>
							长白山亚种，俗称大油，是唯一有保健食疗效果的正宗雪蛤。<br />
						     “人”字黑纹，长白山亚种为林蛙之冠<br />
							 凝山林精华，饮清泉甘露<br />
							 食百种昆虫，潜冰水冬眠，历数载霜雪。
						</p>
						<p>这一独有的生长发育特征，不仅能使雌性成蛙的输卵管具有较高的营养药用价值，同时还成为其明显区别于中国林蛙其他三个亚种的重要标志，也因此使该亚种成为世间罕有的奇珍蛙种。后颈部带“人”字黑纹的长白山亚种林蛙，其体内的干燥输卵管，方才为地道的药用雪蛤油。</p>
					</div>
				</div>
				<div class="fn_left fungus_image">
					<img src="${_imagesPath}/uploadfolder/oil/oil8.jpg" alt="绿色软黄金 | 养颜圣品" width="384px" height="414px"/>
				</div>
			</div>
			<!--野生黑木耳的特点-->
			<div class="trait">
				<ul class="cf">
					<li>
						<h3>分辨真伪 | 看外形</h3>
						<p><img src="${_imagesPath}/uploadfolder/oil/oil9.jpg" alt="分辨真伪 | 看外形" width="475px" height="275px" /></p>
						<p><img src="${_imagesPath}/uploadfolder/oil/oil11.jpg" alt="分辨真伪 | 看外形" width="475px" height="275px"/></p>
						<div>
							形状：不规则块状，弯曲而重叠。块大者长可达3厘米左右。<br />
							外表：表面黄色或浅褐色，具脂肪样光泽，偶带灰白薄状干皮，手摸有滑腻感。<br />
							质地：质地不十分坚硬，易折断（略有水分的雪蛤不会折断）。<br />
							断裂后呈块状，碎块断裂处可见灰白色脂样薄膜状物。
						</div>
					</li>
					<li class="p0">
						<h3>分辨真伪 | 看泡发形状</h3>
						<p><img src="${_imagesPath}/uploadfolder/oil/oil10.jpg" alt="分辨真伪 | 看发泡形状" width="475px" height="275px"/></p>
						<p><img src="${_imagesPath}/uploadfolder/oil/oil12.jpg" alt="分辨真伪 | 看发泡形状" width="475px" height="275px" /></p>
						<div>
							正品雪蛤泡开的样子，晶莹剔透，棉絮状，膨胀40-60倍，略有弹性。<br />
							假货泡开的样子，只有10-20倍，样子呈鸡肠状，色白、不透、气味难闻，手感没<br />
							什么弹性，粘手。
						</div>
					</li>
				</ul>
			</div>
			<!--做菜-->
			<div class="menu J_anchor" id="fungus6" data-name="食谱药疗推荐">
				<ul class="cf">
					<li>
						<h3>冰糖木瓜炖雪蛤</h3>
						<div class="menu_data">
						   <p>材料:  干品雪蛤油5克，冰糖250克，木瓜500克，白糖50克，水1000克</p>
						   <p>特点：清甜醇滑，瓜味郁香</p>
						</div>
						<img src="${_imagesPath}/uploadfolder/oil/oil13.jpg" alt="冰糖木瓜炖雪蛤" />
						<div class="menu_msg">
							<p>1、将雪蛤油盛在大碗里，先用60℃温水浸泡，浸约8-10个小时后。然后，再用清
水漂洗，取出拣去黑点和杂质，洗净捞干，放进碗中，加入白糖50克，清水50克，
放进蒸笼，蒸约30分钟，取出滤干水分，待用。</p>
							<p>2、把木瓜的皮刨掉，用刀开成6条，去掉瓜籽，然后，用刀切成棱角状，放进餐盘
，入蒸笼蒸8分钟后，取出待用。</p>
							<p>3、把炒鼎洗净，放进清水，冰糖煮滚；滚至冰糖全部溶化，且汤面出现浮沫时，把
							浮沫舀去掉，然后把已蒸好的雪蛤油、木瓜块分别盛进10个小碗间，再把已煮滚的
							糖水淋入即成。</p>
						</div>
					</li>
					<li class="p0">
						<h3>银耳雪蛤汤</h3>
						<div class="menu_data">
						   <p>材料：枸杞10克、甘草3克、陈皮3克、泡发雪蛤油200克、木耳3-4朵，冰糖适量</p>
						   <p>功效：降火气，祛痰，增强记忆力，是成熟美人经常服用的养生驻颜甜品。</p>
						</div>
						<img src="${_imagesPath}/uploadfolder/oil/oil14.jpg" alt="银耳雪蛤汤" />
						<div class="menu_msg">
							<p>1、雪蛤油以水浸泡8-10小时，至其膨胀成半透明状，将表面的黑膜及杂质挑除，洗
净，沥干水分，白木耳洗净，泡软，将蒂摘除，并摘成小朵</p>
							<p>2、陈皮以水泡软，将内面的白膜刮除，否则煮出的汤汁会苦。</p>
							<p>3、锅内放入6杯水，放入雪蛤油，再加入陈皮及甘草，以小火煮约30分钟后，加入白
木耳及枸杞，继续再煮20分钟，加入冰糖调味即可。</p>
						</div>
					</li>
				</ul>
			</div>
			<!--药方-->
			<div class="prescription">
				<ul class="cf">
					<li>
						<img src="${_imagesPath}/uploadfolder/oil/oil15.jpg" alt="治疗肺痨咳血" />
						<div class="menu_msg">
							治疗肺痨咳血：雪蛤油5克，白木耳2克，白糖适量<br />
                            加水浸泡蒸服，日服2次。
						</div>
					</li>
					<li>
						<img src="${_imagesPath}/uploadfolder/oil/oil16.jpg" alt="治疗神经衰弱" />
						<div class="menu_msg">
							治疗神经衰弱：雪蛤油5克，白糖适量，<br />
                            加水浸泡蒸服，日服2次。
						</div>
					</li>
					<li class="p0">
						<img src="${_imagesPath}/uploadfolder/oil/oil17.jpg" alt="治疗病后体虚" />
						<div class="menu_msg">
							治疗病后体虚或消耗性疾病：雪蛤油6克，白糖适量<br />
                           加水蒸服，每天早晨空腹服用
						</div>
					</li>
					<li>
						<img src="${_imagesPath}/uploadfolder/oil/oil18.jpg" alt="治疗老年慢性气管炎" />
						<div class="menu_msg">
							治疗老年慢性气管炎：雪蛤油10克，加水蒸服<br />
                            日服一次，10～15天一个疗程。
						</div>
					</li>
					<li>
						<img src="${_imagesPath}/uploadfolder/oil/oil19.jpg" alt="林蛙大补素片" />
						<div class="menu_msg">
							林蛙大补素片：处方为雪蛤油、红参等，将雪蛤油加工
							后粉碎，与红参粉混合，加入辅料，按常规制片，片重
							0.5克。具有补肾益精、润肺养阴、益血生精、大补元
							气、安神等作用。
						</div>
					</li>
					<li class="p0">
						<img src="${_imagesPath}/uploadfolder/oil/oil20.jpg" alt="虫草林蛙胶囊" />
						<div class="menu_msg">
							虫草林蛙胶囊：处方为雪蛤油、冬虫夏草等，将雪蛤油和冬虫夏草粉碎后制成胶囊。具有补肾益精、润肺
							养阴功能，经常吸烟者长期服用效果极佳。
						</div>
					</li>
				</ul>
			</div>
			<div class="slogan">
				一城一品一心致力于打造优质有品位的城市特色产品，严格把关品质与健康。您的满意就是对我们最大的支持与肯定。
			</div>
		</div>
	</div>
	<!--详情部分 end-->
	<!--吐槽部分start-->
	<jsp:include page="/WEB-INF/pages/product/productDetailMySpitslot.jsp"></jsp:include>
	<!--吐槽部分 end-->
	<!--底部 start-->
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
	<!--底部 end-->
	<script type="text/javascript">
	//传出对象dObject
	var dObject = {
		'dStatus': 1, //状态2种，0和1(0:预约购买，1:立即购买)
		'dUrl': '${_ctxPath}/buy-${brand.brandId}.htm' //2种购买方式的路径
	}
	if("${isPreSell}" == "true"){
		dObject.dStatus = 0;
	}
	</script>
	<script type="text/javascript" src="${_jsPath }/pages/productDetail.js"></script>
</body>
</html>
