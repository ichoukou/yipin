<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN" class="ua-window">
<head>
    <c:if test="${empty seoTitle }">
   	<title>秋木耳-${_webSiteName }</title>
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
	<link rel="stylesheet" type="text/css" href="${_cssPath}/pages/fungusDetail.css" media="all" />
</head>
<body>
  	<!--头部导航 start-->
	<jsp:include page="/WEB-INF/pages/include/head.jsp"></jsp:include>
	<!--头部导航 end--> 
	<!--焦点图 start-->
	<div class="focus" id="dBanner">
		<div class="show_wp">
			<div class="pro_show" style="background-image:url('${_imagesPath}/uploadfolder/fungus/fungus1.jpg');background-position:center top;background-repeat:no-repeat;">
				<div class="w_norm relative">
					<dl>
						<dt>吉林 · 野生黑木耳</dt>
                          <dd class="f63c30">${map['JLME01'].unitPrice }元/250g</dd>
						<dd>
						<p>
								野生黑木耳产于大兴安岭，又名木耳、树鸡、木蛾等。<br />
								野生黑木耳不仅是美味菜肴，而且具有多种药用价值。
							</p>
						<p>野生黑木耳不仅有营养作用，<br />
						它还具有益气强身、滋肾养胃、活血等功能，<br />
 						它能抗血凝、抗血栓、降血脂，降低血黏度，<br/>
                            软化血管，使血液流动顺畅，减少心血管病发生。
							</p>
 						</dd>
						<dd>
							<a target="_blank" href="${_ctxPath}/buy-${brand.brandId}.htm" target="_blank" rel="nofollow" class="buy a_link2">
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
			<div class="bck1 J_anchor" id="fungus1" data-name="产地环境介绍">
				<h2>舌尖上的东北 · 吉林</h2>
				<div class="fungus_introduce">
					吉林省简称“吉”，位于中国东北地区的中部。大自然赋予了吉林辽阔茂密的森林、连绵起伏的山林，植物种类丰富多彩，风景色彩纷呈，<br />
                    落叶缤纷，总能让人找到惊喜。行走在乡间，成熟的田地也一改往日的单调重复，在收获的时候也展现出丰富的色彩和形态。 
				</div>
				<div class="fungus_img">
					<img src="${_imagesPath}/uploadfolder/fungus/fungus2.jpg" alt="舌尖上的东北 · 吉林" width="999px" height="427px"/>
				</div>
			</div>
			<!--来自长白山的馈赠-->
			<div class="bck2 cf" id="fungus2">
				<div class="fn_left">
					<img src="${_imagesPath}/uploadfolder/fungus/fungus3.jpg" alt="来自长白山的馈赠" width="496px" height="372px"/>
				</div>
				<div class="fn_left fungus_right">
					<h2>来自长白山的馈赠</h2>
					<div class="fungus_introduce">
						<p>木耳中口感最好的黑木耳主产区位于东北吉林省的大小兴安岭和长白山上一带。野生黑木耳产于大兴安岭，又名木耳、树鸡、木蛾等。属木耳目木耳科，木耳属；野生黑木耳不仅是美味菜肴，而且具有多种药用价值。</p>
						<p>野生黑木耳天性奇特，喜欢阴凉，喜欢水汽，喜欢天然。中国东北气候寒冷，是黑木耳最喜欢的生长环境，尤其吉林、黑龙江大兴安岭一带。原始森林，树木繁茂富裕，水汽最盛，在山下种植的黑木耳，不释农药，空气清新，尽享山泉之润。这优越的生长环境所孕育出的是中国最好的黑木耳。</p>
					</div>
				</div>
			</div>
			<!--野生黑木耳的功效-->
			<div class="effect J_anchor" id="fungus3" data-name="功效与特征">
				<h2>野生黑木耳的功效</h2>
				<p class="f18">资料出自《中华本草》翻译编辑</p>
				<div class="fungus_introduce">
					《日用本草》治肠癖下血，又凉血。何为肠癖下血，即由饮食失节致肠胃横解，血不流通随大便而出，病虽寻常，然有终身不愈者。食者可改善治愈，调节肠胃平衡，润燥利肠。<br />
                    野生黑木耳的各种功效都较于普通黑木耳更为显著，与美食结合食用，养身健体，补进适宜，效果和口碑流传至今。 
				</div>
				<div class="fungus_img">
					<img src="${_imagesPath}/uploadfolder/fungus/fungus4.jpg" alt="野生黑木耳的功效" width="950px" height="773px"/>
				</div>
			</div>
			<!--野生黑木耳的特点-->
			<div class="trait" id="fungus4">
				<h2>野生黑木耳的特点</h2>
				<ul class="cf">
					<li>
						<img src="${_imagesPath}/uploadfolder/fungus/fungus6.jpg" alt="生长培育时长充足" width="320px" height="228px;"/>
						<h3>生长培育时长充足</h3>
						<div>野生黑木耳生长时间较之木耳更长，发育完善，在最适宜的秋季摘下，营养价值也更为持久完善。</div>
					</li>
					<li>
						<img src="${_imagesPath}/uploadfolder/fungus/fungus7.jpg" alt="外型朵小肥厚，口感佳" width="320px" height="228px;"/>
						<h3>外型朵小肥厚，口感佳</h3>
						<div>野生黑木耳色泽呈暗绿色，朵小肉质肥厚，口感较普通黑木耳更为爽脆有嚼劲，厚实滑嫩。</div>
					</li>
					<li>
						<img src="${_imagesPath}/uploadfolder/fungus/fungus8.jpg" alt="营养价值丰富" width="320px" height="228px;"/>
						<h3>营养价值丰富</h3>
						<div>由于生长时间较长，野生黑木耳所含有的各种营养元素是普通木耳的10倍之多，营养价值更高。</div>
					</li>
				</ul>
				<div class="fungus_img">
					<img src="${_imagesPath}/uploadfolder/fungus/fungus5.jpg" alt="野生黑木耳的特点" />
				</div>
			</div>
			<!--做菜-->
			<div class="menu J_anchor" id="fungus5" data-name="食谱搭配推荐">
				<ul class="cf">
					<li>
						<h3>凉拌木耳</h3>
						<p class="menu_data">材料：黑木耳，胡萝卜，香菜，红辣椒，葱姜蒜，植物油，六月鲜，盐，香醋，糖。</p>
						<img src="${_imagesPath}/uploadfolder/fungus/fungus9.jpg" alt="凉拌木耳" />
						<div class="menu_msg">
							<p>1、黑木耳用冷水泡发后，剪去根蒂，撕成小朵；</p>
							<p>2、锅中放清水烧开后，入木耳汆烫3分钟捞出，用冷开水洗去表面粘液；</p>
							<p>3、胡萝卜去皮切成牛眼片，入沸水烫1分钟捞出； </p>
							<p>4、葱姜蒜切末放小碗里，植物油烧热后浇在上面烹出香味； </p>
							<p>5、按照自己口味加入适量生抽、盐、香醋、糖调匀成味汁； </p>
							<p>6、木耳和胡萝卜一起放入碗里，将味汁倒入，撒上香菜末和红椒圈拌匀即可。</p>
						</div>
					</li>
					<li>
						<h3>蛋炒黄瓜木耳</h3>
						<p class="menu_data">材料：鸡蛋1个，小黄瓜2条，黑木耳1小把，生抽，醋，香油，葱，姜2片</p>
						<img src="${_imagesPath}/uploadfolder/fungus/fungus10.jpg" alt="蛋炒黄瓜木耳" />
						<div class="menu_msg">
							<p>1、生鸡蛋一个打匀；小黄瓜两条，洗净切片；黑木耳一小把，泡发洗净摘好，切小片，用开水焯一下，拌入一点生抽，醋和香油备用；葱半颗切葱花，姜两片切沫。</p>
							<p>2、炒锅热后放1大匙橄榄油，放入鸡蛋炒熟盛出，再放入0.5大匙橄榄油，下葱姜炒香，放入黄瓜稍翻炒几下，下黑木耳和炒熟的鸡蛋，加盐，翻炒均匀即可。</p>
						</div>
					</li>
					<li>
						<h3>木耳鸡汤</h3>
						<p class="menu_data">材料：仔鸡一只，人少，半只也可。干木耳</p>
						<img src="${_imagesPath}/uploadfolder/fungus/fungus11.jpg" alt="木耳鸡汤" />
						<div class="menu_msg">
							<p>1、仔鸡剁小块，切好之后洗干净。干木耳买回来做泡个半个小时。然后洗干净，去蒂切小块。 </p>
							<p>2、锅中倒冷水，倒入鸡块，加花椒粒20颗这样，不需要太多，放姜片，葱段，料酒。 </p>
							<p>3、大火烧开，撇出浮沫，转小火把鸡块烧八成烂，大概要15分钟。 </p>
							<p>4、倒入洗好待用的木耳。然后加盐小火10分钟就可以出锅了。</p>
							<p>5、出锅前捞出葱段，加点葱花就即可。木耳有滋润强壮，润肺补脑，轻身强志，补血活血，镇静止痛等功效，是天然的滋补剂。有润肺和清涤胃肠作用。 </p>
						</div>
					</li>
					<li>
						<h3>青椒木耳炒火腿</h3>
						<p class="menu_data">材料：泡发的黑木耳1小碗，火腿2根，青椒1只，大蒜2粒，姜1小块</p>
						<img src="${_imagesPath}/uploadfolder/fungus/fungus12.jpg" alt="青椒木耳炒火腿" />
						<div class="menu_msg">
							<p>1、热锅放油，放入姜、蒜煸出香味 </p>
							<p>2、放入青椒翻炒几下，再加入火腿继续炒 </p>
							<p>3、最后放木耳，炒至松软后，放入盐、生抽翻炒几下</p>
							<p>4、淋上香油后，出锅装盘食用</p>
						</div>
					</li>
					<li>
						<h3>山药木耳溜肉片</h3>
						<p class="menu_data">材料：山药，干木耳，猪肉，少许红甜椒 </p>
						<img src="${_imagesPath}/uploadfolder/fungus/fungus13.jpg" alt="山药木耳溜肉片" />
						<div class="menu_msg">
							<p>1、山药去皮洗净斜刀切片.木耳泡软，去根洗净</p>
							<p>2、猪肉切薄片，加盐，鸡精，料酒，蛋清，水淀粉。拌匀</p>
							<p>3、锅烧热，倒入油，油温2～3成热时下肉片滑嫩，同时下山药片，木耳滑熟，倒出控油</p>
							<p>4、葱姜蒜切末放小碗里，植物油烧热后浇在上面烹出香味； </p>
							<p>5、锅中留少许油，煸香葱花，加少许水，盐，鸡精，糖，胡椒粉调味，用水淀粉勾芡，再倒入刚做好的菜料翻炒均匀即可。</p>
						</div>
					</li>
					<li>
						<h3>番茄木耳蛋花汤</h3>
						<p class="menu_data">材料：番茄一个，鸡蛋一个，木耳若干</p>
						<img src="${_imagesPath}/uploadfolder/fungus/fungus14.jpg" alt="番茄木耳蛋花汤" />
						<div class="menu_msg">
							<p>1、番茄切成月牙状、木耳发好洗净</p>
							<p>2、锅里放一点油，油热后把番茄放进去炒大约3分钟左右，加水</p>
							<p>3、等水开了把木耳放进去，文火煮大约10分钟，让番茄充分的煮烂一点，这样汤的味道更浓一些</p>
							<p>4、然后把鸡蛋打在一个碗里搅拌均匀，倒入煮着番茄和木耳的锅里略微搅拌，关火放盐调味了</p>
						</div>
					</li>
				</ul>
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