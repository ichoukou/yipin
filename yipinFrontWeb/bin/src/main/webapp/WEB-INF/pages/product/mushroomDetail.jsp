<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN" class="ua-window">
<head>
    <c:if test="${empty seoTitle }">
   	<title>猴头菇-${_webSiteName }</title>
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
	<!--page css-->
	<link rel="stylesheet" type="text/css" href="${_cssPath}/pages/mushroomDetail.css" media="all" />
</head>
<body>
  	<!--头部导航 start-->
	<jsp:include page="/WEB-INF/pages/include/head.jsp"></jsp:include>
	<!--头部导航 end--> 
	<!--焦点图 start-->
	<div class="focus" id="dBanner">
		<div class="show_wp">
			<div class="pro_show" style="background-image:url('${_imagesPath}/uploadfolder/mushroom/mush_top.jpg');background-position:center top;background-repeat:no-repeat;">
				<div class="w_norm relative">
					<dl>
						<dt>长白山 · 通化猴头菇</dt>
                          <dd class="f63c30">${map['JLHG01'].unitPrice }元/250g</dd>
						<dd>
						<p>相传早在3000年前的商代，已经有人采摘猴头菇食用。<br />
“物以稀为贵”，猴头菇的药用、食用功效已流芳百世，<br />
其美味的口感和强身健体的效用广为流传。
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
			<!--长白山 · 通化猴头菇-->
			<div class="bck1">
				<div class="pro_h3 pro_tac">天然之城 · 长白山</div>
				<div class="txt">
					<p class="f20">是世人瞩目的神奇之地，人称“千年积雪万年松，直上人间第一峰”。</p>
					<br />
					长白山春可踏青、夏可观景、秋可赏叶、冬可玩雪，大自然赋予了它无比丰富独特的资源。在亿万年以来的地质历史上，长白山地区经历了沧海桑田的变迁。长白山的生态系统比较完整，自然环境复杂多样，山上气候变化无常，植物种类丰富多彩。<br /><br />
                    在过去猴头菇一直都是野生的，多产于大山林中的荫蔽处。自古以来，中国人就有到深山密林里采集野生猴头菇作为食品和药物的习惯。据说，至今在北方一代的群众中还流传着山林中采食猴头菇的故事。
				</div>
				<div class="img">
					<img src="${_imagesPath}/uploadfolder/mushroom/mush1.jpg" alt="" />
				</div>
			</div>
			<!-- 边框 s-->
			<div class="pro_bdt"></div>
			<!-- 边框 e-->
			<div class="inner">
				<div class="buy_box cf J_anchor" id="mushroom1" data-name="营养功效介绍">
					<div class="fl"><img src="${_imagesPath}/uploadfolder/mushroom/mush2.jpg" alt="" /></div>
					<div class="ovh">
						<dl style="margin-top:45px;">
							<dt class="pro_h2">长白山通化猴头菇</dt>
							<dd class="meta">东北长白山通化特产猴头菇，又可称猴头菌，猴菇菌，猴菇，猴蘑，是中国传统的名贵菜肴，肉嫩、味香、鲜美可口，亦是四大名菜（猴头、熊掌、海参、鱼翅）之一。有“山珍猴头、海味燕窝”之称。</dd>
							<dd class="f20 pro_fwb">东北特产长白山通化猴头菇</dd>
							<dd class="pro_cor pro_h3">￥${map['JLHG01'].unitPrice }元/250g</dd>
							<dd>
							<a target="_blank" href="${_ctxPath}/buy-${brand.brandId}-${map['JLHG01'].productId }.htm" target="_blank" rel="nofollow" class="buy a_link2">
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
			<!-- 边框 s-->
			<div class="pro_bdt"></div>
			<!-- 边框 e-->
			<div class="inner">
				<div class="bck2">
					<div class="top">
						<div class="tit">产自长白山通化，人工培育自然养成猴头菇，严格品质把关，放心食用。</div>
						<div class="con">在菌类蔬菜里面，猴头菇是比较少见的一种，也是一种名贵的食用菌，被列入八大山珍之一。<br />
猴头菇具有很好的使用功效，具有营养与药用的结合。性平、味甘。利五脏，助消化；具有健胃，补虚，抗癌，益肾精之功效。</div>
					</div>
					<div class="img">
					<img src="${_imagesPath}/uploadfolder/mushroom/mush3.jpg" alt="" />
					</div>
				</div>
			</div>
			<!-- 边框 s-->
			<div class="pro_bdt"></div>
			<!-- 边框 e-->
			<div class="inner">
				<div class="bck3">
					<div class="top">
						<div class="txt_div">
							<h4>适宜人群</h4>
							<p>低免疫力人群，高脑力人群；对菌物食品过敏者慎用。婴儿和老人均可食用。有心血管疾病、有胃肠病的患者更应食用猴头菇。</p>
						</div>
						<div class="txt_div">
							<h4>营养价值丰富</h4>
							<p>猴头菌的营养成分很高，它含有氨基酸多达 17 种，其中人体所需的占 8 种。每百克猴头菇含脂肪4.2 克，是名副其实的高蛋白、低脂肪食品，另外还富含各种维生素和无机盐。</p>
						</div>
					</div>
					<div class="img">
					<img src="${_imagesPath}/uploadfolder/mushroom/mush4.jpg" alt="" />
					</div>
				</div>
				<div class="bck4 cf">
					<div class="fl">
					<img src="${_imagesPath}/uploadfolder/mushroom/mush5.jpg" alt="" />	
					</div>
					<div class="ovh">
						<div class="tit">中医治疗研究发现</div>
						<div class="meta">中医认为，猴头菇性平味甘，有利五脏、助消化、滋补身体等功效。</div>
						<p>20 世纪 70 年代以来，现代医学陆续证明猴头菌有良好的药用价值，临床应用表明，猴头菇可治疗消化不良、胃溃疡、胃窦炎、胃痛、胃胀及神经衰弱等疾病。但如果是轻度神经衰弱患者，食用猴头菇不失为较好的辅助治疗。</p>
					</div>
				</div>
			</div>
			<!-- 边框 s-->
			<div class="pro_bdt"></div>
			<!-- 边框 e-->
			<div class="inner J_anchor" id="mushroom2" data-name="鉴别方法介绍">
				<div class="tac_box">
				产品展示
				</div>
				<div class="img">
				<img src="${_imagesPath}/uploadfolder/mushroom/mush6.jpg" alt="" />
				</div>
				<div class="tac_box">
				猴头菇的质量鉴别
				<p>怎样挑选新鲜，优质的猴头菇</p>
				</div>
				<div class="qual cf">
					<div class="l" style="margin-left:65px;">
						<img src="${_imagesPath}/uploadfolder/mushroom/mush7.jpg" alt="" />
						<p>1.质量好的猴头菇菇体完整，无伤痕残缺，菇体干燥；质差的菇体残缺不全，或有伤痕，水分重。<br /><br />
质量好的菇体形如猴头，呈椭圆形或圆形，大小均匀，毛多细长，茸毛齐全；质差的菇体大小不均，形状不规整，毛粗而长。</p>
					</div>
					<div class="r" style="margin-right:65px;">
						<img src="${_imagesPath}/uploadfolder/mushroom/mush8.jpg" alt="" />
						<p>2.质好的菇呈金黄色或黄里带白；质差者色泽黑而软，有的伪劣品为了增白，用硫碘或化学药剂处理成不正常的白色，这种菇食后对人体有害无益，不可选购。<br /><br />
质好的菇不烂、不霉、不蛀；凡有烂、霉、蛀者，也不宜选购 </p>
					</div>
				</div>
			</div>
			<div class="tac_box J_anchor"  id="mushroom3" data-name="食谱搭配推荐">
				猴头菇的食用
				<p>猴头菇进入人们的饮食生活由来已久，其高营养价值与美食结合，可谓天作之合，让每一道菜都锦上添花，既是可口佳肴，亦可养身健体。</p>
			</div>
			<div class="g_list cf">
				<ul>
					<li>
						<dl>
							<dt>猴头菇扒油菜心</dt>
							<dd><img src="${_imagesPath}/uploadfolder/mushroom/mush9.jpg" alt="" /></dd>
							<dd class="txt">
								<p>1. 猴头菇用温水泡发，反复漂洗几次，再浸泡1天。【中途挤干换水】</p>
								<p>2. 泡发好的猴头菇挤去水分。加入上汤，蒸锅蒸制 1小时。</p>
								<p>3. 把猴头菇捞出挤干，撕成小朵。用水淀粉上浆。</p>
								<p>4. 把葱姜切碎，油菜摘洗干净。</p>
								<p>5. 锅中注水，加入几滴油和少量的盐烧开。下入油菜焯水</p>
								<p>6. 油菜沥干水分，放在盘中。</p>
								<p>7. 再把上浆的猴头菇微沸的水中滑至浆凝固。捞出。</p>
								<p>8. 另起锅，注入油爆香葱姜碎，入蒸猴头菇的汤烧开。</p>
								<p>9. 加入焯水的猴头菇。</p>
								<p>10. 小火烧至猴头菇入味，加入鸡精调味。</p>
								<p>11. 将烧好的猴头菇倒入带有油菜的盘中即可。</p>
							</dd>
						</dl>
					</li>
					<li>
						<dl>
							<dt>猴头菇骨头汤</dt>
							<dd><img src="${_imagesPath}/uploadfolder/mushroom/mush10.jpg" alt="" /></dd>
							<dd class="txt">
							<p>1、番茄切成月牙状、木耳发好洗净</p>
							<p>2. 将猴头菇放到温的洗米水里。</p>
							<p>3. 再用一个碟子盖上，目的是使猴头菇全部泡在水时，同时水温可以保持在40度左右，
泡发2个小时左右，使猴头菇充分吸收水分。</p>
							<p>4. 泡了2个小时的猴头菇，个个都吸饱了水分，而且洗米水也变成了黄色</p>
							<p>5. 把泡软的猴头菇，用手如挤海绵一样，用手轻轻的挤，反复搓洗，使黄色的水挤压出来。</p>
							<p>6. 再换两次清水，反复洗过以后，黄色的水就洗干净了，把水分挤干。</p>
							<p>7. 把挤干水分的猴头菇放案板上，切掉黑色的蒂部。</p>
							<p>8. 将买回的新鲜猪骨头，用水冲洗干净，红枣、桂圆肉、百合、枸杞清洗干净、猴头菇切小块备用。 </p>
							<p>9. 锅内放适量清水，将猪骨头放锅内。</p>
							<p>10. 将所有材料一起倒入汤煲内，开大火煮开，用汤勺将浮沫撇去。</p>
							<p>11. 再开小火慢慢煲1.5个小时左右，关火前5分钟加盐即可。</p>
							</dd>
						</dl>
					</li>
				</ul>
			</div>
			<div class="bm_img">
				<img src="${_imagesPath}/uploadfolder/mushroom/mush11.jpg" alt="" />
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