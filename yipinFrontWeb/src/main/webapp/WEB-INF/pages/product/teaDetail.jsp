<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN" class="ua-window">
<head>
	<c:if test="${empty seoTitle }">
   	<title>西湖龙井-${_webSiteName }</title>
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
	<link rel="stylesheet" type="text/css" href="${_cssPath}/pages/productDetail.css" media="all" />
</head>
<body>
  	<!--头部导航 start-->
	<jsp:include page="/WEB-INF/pages/include/head.jsp"></jsp:include>
	<!--头部导航 end--> 
	<!--焦点图 start-->
	<div class="focus" id="dBanner">
		<div class="show_wp">
			<div class="pro_show" style="background-image:url('${_imagesPath}/uploadfolder/productDetail/pro_top.jpg');background-position:center top;background-repeat:no-repeat;">
				<div class="w_norm relative">
					<dl>
					 <dt>杭州 · 西湖龍井</dt>
					 <dd class="meta">西湖龙井礼盒
					 <p>150g*2包&nbsp;<em>${map['HZLG01'].unitPrice }元</em>
<%-- 					 <br />250g*2包&nbsp;<em>${map['HZLG02'].unitPrice }元</em> --%>
					 </p>
					 </dd>
					<dd>
					<p>西湖龙井茶，<br />
					因产于中国杭州西湖的龙井茶区而得名。<br />
					中国十大名茶之一。</p>
					<p>茶有“四绝”：色绿、香郁、味甘、形美。<br />
					特级西湖龙井茶扁平光滑挺直，<br />
					色泽嫩绿光润，香气鲜嫩清高，<br />
					滋味鲜爽甘醇，叶底细嫩呈朵。</p>
					<p>集名山、名寺、名湖、名泉和名茶于一体，泡一杯龙井茶，<br />
					喝出的却是世所罕见的独特而骄人的龙井茶文化。</p>
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
			<div class="bck1">
				<h2>天堂之都·杭州</h2>
				<table width="100%" class="bor">
					<tbody>
						<tr>
							<td><img src="${_imagesPath}/uploadfolder/productDetail/img1.jpg" /></td>
							<td width="45"></td>
							<td valign="top" width="524">
								<h5>杭州西湖</h5>
								<p>“上有天堂、下有苏杭”—— 这是中国人自古赞美杭州的民谚。“杭州，是世界最美丽华贵的天城”—— 这是十三世纪意大利著名旅行家马可波罗对杭州的赞叹，从此，杭州名扬海外。这个“八山半水分半田”的地区，地形多样、四季分明、气候宜人、雨量充沛，自然条件十分优越，茶叶品种丰富、品质优良。杭州珍视每一棵茶树，也重视每一杯茶的质量。</p>
							</td>
						</tr>
					</tbody>
				</table>
				<table width="100%" class="bor" style="margin-top:10px;">
					<tbody>
						<tr>
							<td width="57"></td>
							<td valign="top" width="524">
								<h5 style="text-align:right;">中国茶乡第一村</h5>
								<p><b>龍井村，因盛产顶级西湖龍井茶而闻名于世。</b>东临西子湖，西依五云山，南靠滔滔东去的钱塘江水，北抵插入云端的南北高峰，四周群山叠翠，云雾环绕，就如一颗镶嵌在西子湖畔的翡翠宝石。</p>
							</td>
							<td width="45"></td>
							<td><img src="${_imagesPath}/uploadfolder/productDetail/img2.jpg" alt="" /></td>
						</tr>
					</tbody>
				</table>
				<div class="sc cf J_anchor" id="tea1" data-name="查看所有礼盒">
					<div class="sale1">
						<div class="tit">西湖龍井</div>
						<img src="${_imagesPath}/uploadfolder/productDetail/p_b1.png" alt="" />
						<span class="sk relative">${map['HZLG01'].name }<br/>￥${map['HZLG01'].unitPrice }(150g*2)
							<a href="${_ctxPath}/buy-${brand.brandId}-${map['HZLG01'].productId }.htm" class="a_link1">
							<c:choose>
								<c:when test="${isPreSell }">预约购买</c:when>
								<c:otherwise>立即购买</c:otherwise>
							</c:choose>
							</a>
						</span>
					</div>
					<div class="sale2">
						<div class="tit">九曲红梅</div>
						<img src="${_imagesPath}/uploadfolder/productDetail/p_b2.png" alt="" />
						<span class="sk relative">${map['HZHM01'].name }<br />￥${map['HZHM01'].unitPrice }(50g*4)
							<a href="${_ctxPath}/buy-${brand.brandId}-${map['HZHM01'].productId }.htm" class="a_link1">
							<c:choose>
								<c:when test="${isPreSell }">预约购买</c:when>
								<c:otherwise>立即购买</c:otherwise>
							</c:choose>
							</a>
						</span>
					</div>
				</div>
				<div class="mptj">
					<div class="tit">更多名茶推荐</div>
					<div class="con cf">
						<ul>
							<li><img src="${_imagesPath}/uploadfolder/productDetail/p_s1.png" alt="" /><span>${map['HZLG01'].name }<br />￥${map['HZLG01'].unitPrice }(250g*2)</span></li>
							<li><img src="${_imagesPath}/uploadfolder/productDetail/p_s2.png" alt="" /><span>${map['HZQY01'].name }
	<br />￥${map['HZQY01'].unitPrice }(150g*4)</span></li>
							<li><img src="${_imagesPath}/uploadfolder/productDetail/p_s3.png" alt="" /><span>${map['HZYY01'].name }
	<br />￥${map['HZYY01'].unitPrice }(150g*4)</span></li>
							<li><img src="${_imagesPath}/uploadfolder/productDetail/p_s4.png" alt="" /><span>${map['HZCY01'].name }
	<br />￥${map['HZCY01'].unitPrice }(30g*3)</span></li>
							<li><img src="${_imagesPath}/uploadfolder/productDetail/p_s5.png" alt="" /><span>${map['HZHH01'].name }
	<br />￥${map['HZHH01'].unitPrice }(4.5g*4、5.1g*1)</span></li>
							<li><img src="${_imagesPath}/uploadfolder/productDetail/p_s6.png" alt="" /><span>${map['HZXQ01'].name }
	<br />￥${map['HZXQ01'].unitPrice }(20g*6)</span></li>
							<li><img src="${_imagesPath}/uploadfolder/productDetail/p_s7.png" alt="" /><span>${map['HZZX01'].name }
	<br />￥${map['HZZX01'].unitPrice }(50g*4)</span></li>
							<li><img src="${_imagesPath}/uploadfolder/productDetail/p_s8.png" alt="" /><span>${map['HZBB01'].name }
	<br />￥${map['HZBB01'].unitPrice }(30g*3)</span></li>
							<li><img src="${_imagesPath}/uploadfolder/productDetail/p_s9.png" alt="" /><span>${map['HZZS01'].name }
	<br />￥${map['HZZS01'].unitPrice }(50g*4)</span></li>
							<li><img src="${_imagesPath}/uploadfolder/productDetail/p_s10.png" alt="" /><span>${map['HZQM01'].name }
	<br />￥${map['HZQM01'].unitPrice }(50g*4)</span></li>
							<li><img src="${_imagesPath}/uploadfolder/productDetail/p_s11.png" alt="" /><span>${map['HZYC01'].name }
	<br />￥${map['HZYC01'].unitPrice }(50g*4)</span></li>
							<li><img src="${_imagesPath}/uploadfolder/productDetail/p_s12.png" alt="" /><span>${map['HZZH01'].name }
	<br />￥${map['HZZH01'].unitPrice }(50g*4)</span></li>
						</ul>
					</div>
				</div>
				<div class="cyjs J_anchor" id="tea2" data-name="产地环境介绍">
					<div class="tit">一城一品携手中国茶博会联合出品</div>
					<div class="qy1">
					<img src="${_imagesPath}/uploadfolder/productDetail/img3.jpg" alt="" />
					<p>一城一品联合中国茶叶博物馆出品，世代传承的正宗杭州西湖龍井，从种植、收货、炒制、加工、以及包装，<br/>层层把关，严格遵循茶博会的规则制度，打造最优质有品位的西湖龍井茶叶。</p>
					</div>
					<div class="qy2">
						<table width="100%">
							<tbody>
								<tr>
									<td width="530" valign="top" class="l">
										<h5>优越的生长环境是上等西湖龍井的根基石</h5>
										<p>
										西湖龍井茶区位于西子湖畔，环绕滋润土地的是钱塘江汇入长江的交接口水源，水质清爽温和，富含多重矿物质成分，得天独厚的生长环境中，使得这片区域土壤肥沃。
										<br /><br />
										植被茂密的山区环境气温常年比外界低3℃~5℃，微酸的土壤，土层深厚，山面朝南抵御了来自北方的冷空气，温度适宜、阳光充足；西子湖畔，常年雨水充沛，特别适宜茶树的生长，晨间阳光透过薄雾撒在茶树上，晶莹透亮，一片生机盎然。俗语曰“山饮西湖，雾生龙井”。
										<br /><br />
										正是好山好水出好茶，这种优越的生长环境，孕育了西湖龍井茶叶特有的品质——颜色翠绿，形状扁平，味甘醇美，香气馥郁，这四绝奠定了西湖龙井伫立于中国十大名茶之首的尊贵地位。
										</p>
									</td>
									<td><img src="${_imagesPath}/uploadfolder/productDetail/img4.jpg" alt="" /></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="d_item">
				<div class="hd"><img src="${_imagesPath}/uploadfolder/productDetail/pro_tit1.jpg" alt="" /></div>
				<div class="bd cf">
					<div class="l w_max tar">
						<img src="${_imagesPath}/uploadfolder/productDetail/p_b3.png" alt="" />
					</div>
					<div class="r w_min" style="margin:20px 109px 0 0;">
						<div class="meta">
							<table>
								<tbody>
									<tr>
										<td class="tit">品名：</td>
										<td>${map['HZLG01'].name }</td>
									</tr>
									<tr>
										<td class="tit">规格：</td>
										<td><i class="fz_s" style="margin-right:30px;">龍井150g*2</i><i class="fz_s">龍井250g*2</i></td>
									</tr>
									<tr>
										<td class="tit">价格：</td>
										<td><i class="red" style="margin-right:18px;">￥${map['HZLG01'].unitPrice }</i><i class="red">￥${map['HZLG02'].unitPrice }</i></td>
									</tr>
								</tbody>
							</table>
							<em class="line"></em>
						</div>
						<div class="deta">杭州的历史传奇，国之瑰宝，拥有千余年的历史，位于我国十大茗茶之首，享有“绿茶皇后”之美誉。以“形美、色翠、香郁、味甘”四绝闻名于世，其鲜明的特色、天赋的生长环境，优异的品种资源、精妙的炒制技艺、蕴含着的审美艺术和精神哲理，成就饱含春意的自信佳作。</div>
						<div class="btn">
						<a target="_blank" href="${_ctxPath}/buy-${brand.brandId}-${map['HZLG01'].productId }.htm" target="_blank" rel="nofollow" class="buy a_link2">
						<c:choose>
							<c:when test="${isPreSell }">预约购买</c:when>
							<c:otherwise>立即购买</c:otherwise>
						</c:choose>
						</a>
						</div>
					</div>
				</div>
			</div>
			<div class="d_item">
				<div class="hd"><img src="${_imagesPath}/uploadfolder/productDetail/pro_tit2.jpg" alt="" /></div>
				<div class="bd cf">
					<div class="l w_min" style="margin-left:92px;">
						<div class="meta">
							<table>
								<tbody>
									<tr>
										<td class="tit">品名：</td>
										<td>${map['HZHM01'].name }</td>
									</tr>
									<tr>
										<td class="tit">规格：</td>
										<td><i class="fz_s" style="margin-right:30px;">九曲红梅50g*4</i></td>
									</tr>
									<tr>
										<td class="tit">价格：</td>
										<td><i class="red" style="margin-right:18px;">￥${map['HZHM01'].unitPrice }</i></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="deta">九曲红梅——“山不在高，有仙则名，水不在深，有龙则灵。”<br />
九曲红梅是杭州的另一传奇，系工夫红茶，品质优异，风韵独特，色香味形俱佳，是优越的自然条件、优良的茶树品种与精细的采摘方法、精湛的加工工艺相结合的产物。其外形曲细如鱼钩，色泽乌泽多白毫，滋味浓郁，香气芬馥，汤色鲜亮，叶底红艳，具有解渴养胃，消食除腻，明目提神，健身祛病之功效。</div>
						<div class="btn">
						<a target="_blank" href="${_ctxPath}/buy-${brand.brandId}-${map['HZHM01'].productId }.htm" target="_blank" rel="nofollow" class="buy a_link2">
						<c:choose>
							<c:when test="${isPreSell }">预约购买</c:when>
							<c:otherwise>立即购买</c:otherwise>
						</c:choose>
						</a>
						</div>
					</div>
					<div class="r w_max">
						<img src="${_imagesPath}/uploadfolder/productDetail/p_b4.png" alt="" />
					</div>
				</div>
			</div>
			<div class="pic_show J_anchor" id="tea3" data-name="龍井冲泡解说" style="border-bottom:1px solid #cccccc;">
				<img src="${_imagesPath}/uploadfolder/productDetail/img5.jpg" alt="" />
				<img src="${_imagesPath}/uploadfolder/productDetail/img6.jpg" alt="" />
				<p>中国茶叶历史悠久，各种各样的茶类品种竟相争艳，犹如春天的百花园，使万里山河分外妖娆。
	圆通茶艺馆和中国茶叶文化博物馆联合推出各式名茶套装礼盒，正品授权，至尊享受。</p>
			</div>
			<div class="d_item">
				<div class="hd J_anchor" id="tea4" data-name="名茶组合礼盒"><img src="${_imagesPath}/uploadfolder/productDetail/pro_tit3.jpg" alt="" /></div>
				<div class="bd cf">
					<div class="l w_max" style="margin-left:92px;">
						<img src="${_imagesPath}/uploadfolder/productDetail/p_b5.png" alt="" />
					</div>
					<div class="r w_min" style="margin-right:10px;width:357px;">
						<div class="meta">
							<table>
								<tbody>
									<tr>
										<td class="tit">品名：</td>
										<td>${map['HZQY01'].name }</td>
									</tr>
									<tr>
										<td class="tit" valign="top">规格：</td>
										<td><p><i class="fz_s">正山小种*1、福鼎白茶*1</i></p><p><i class="fz_s">武夷岩茶*1、桂花龙井*1(150g*4)</i></p></td>
									</tr>
									<tr>
										<td class="tit">价格：</td>
										<td><i class="red" style="margin-right:18px;">￥${map['HZQY01'].unitPrice }</i></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="deta">桐木关正山小种——红茶鼻祖松烟香，桂圆蜜枣真滋味<br />
福鼎白茶——太姥仙茶天地造，益身良草入万家<br />
武夷岩茶——凝得山川之灵气，蕴藏正岩真花香<br />
桂花龍井——叶含雷信三春雨，花带天香八月秋</div>
						<div class="btn">
						<a target="_blank" href="${_ctxPath}/buy-${brand.brandId}-${map['HZQY01'].productId }.htm" target="_blank" rel="nofollow" class="buy a_link2">
						<c:choose>
							<c:when test="${isPreSell }">预约购买</c:when>
							<c:otherwise>立即购买</c:otherwise>
						</c:choose>
						</a>
						</div>
					</div>
				</div>
			</div>
			<div class="d_item">
				<div class="hd"><img src="${_imagesPath}/uploadfolder/productDetail/pro_tit4.jpg" alt="" /></div>
				<div class="bd cf">
					<div class="l w_min" style="margin-left:93px;">
						<div class="meta">
							<table>
								<tbody>
									<tr>
										<td class="tit">品名：</td>
										<td>${map['HZYY01'].name }</td>
									</tr>
									<tr>
										<td class="tit" valign="top">规格：</td>
										<td><p><i class="fz_s">红袍*1、水仙*1、半天腰*1</i></p><p><i class="fz_s">铁罗汉*1、水金龟*1、肉桂*1(20g*6)</i></p></td>
									</tr>
									<tr>
										<td class="tit">价格：</td>
										<td><i class="red" style="margin-right:18px;">￥${map['HZYY01'].unitPrice }</i></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="deta">大紅袍——德高望重的族长<br />
水仙——贤惠通达的女子<br />
半天腰——被遗忘的贵族<br />
鐡羅漢——刚韵铁骨的罗汉<br />
水金龜——茶品美化雪的妙玉<br />
肉桂——辣妹子的王熙凤</div>
						<div class="btn">
						<a target="_blank" href="${_ctxPath}/buy-${brand.brandId}-${map['HZYY01'].productId }.htm" target="_blank" rel="nofollow" class="buy a_link2">
							<c:choose>
								<c:when test="${isPreSell }">预约购买</c:when>
								<c:otherwise>立即购买</c:otherwise>
							</c:choose>
						</a>
						</div>
					</div>
					<div class="r w_max" style="margin-right:45px;">
						<img src="${_imagesPath}/uploadfolder/productDetail/p_b6.png" alt="" />
					</div>
				</div>
			</div>
			<div class="d_item">
				<div class="hd"><img src="${_imagesPath}/uploadfolder/productDetail/pro_tit5.jpg" alt="" /></div>
				<div class="bd cf">
					<div class="l w_max" style="margin-left:92px;">
						<img src="${_imagesPath}/uploadfolder/productDetail/p_b7.png" alt="" />
					</div>
					<div class="r w_min" style="margin-right:10px;width:357px;">
						<div class="meta">
							<table>
								<tbody>
									<tr>
										<td class="tit">品名：</td>
										<td>${map['HZCY01'].name }</td>
									</tr>
									<tr>
										<td class="tit" valign="top">规格：</td>
										<td><p><i class="fz_s">武夷岩茶肉桂*1、武夷岩茶水仙*1</i></p><p><i class="fz_s">武夷岩茶大红袍*1(30g*3)</i></p></td>
									</tr>
									<tr>
										<td class="tit">价格：</td>
										<td><i class="red" style="margin-right:18px;">￥${map['HZCY01'].unitPrice }</i></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="deta">大紅袍——德高望重的族长<br />
水仙——贤惠通达的女子<br />
半天腰——被遗忘的贵族<br />
鐡羅漢——刚韵铁骨的罗汉<br />
水金龜——茶品美化雪的妙玉<br />
肉桂——辣妹子的王熙凤</div>
						<div class="btn">
						<a target="_blank" href="${_ctxPath}/buy-${brand.brandId}-${map['HZCY01'].productId }.htm" target="_blank" rel="nofollow" class="buy a_link2">
							<c:choose>
								<c:when test="${isPreSell }">预约购买</c:when>
								<c:otherwise>立即购买</c:otherwise>
							</c:choose>
						</a>
						</div>
					</div>
				</div>
			</div>
			<div class="d_item">
				<div class="hd"><img src="${_imagesPath}/uploadfolder/productDetail/pro_tit6.jpg" alt=""></div>
				<div class="bd cf">
					<div class="l w_min" style="margin-left:74px;width:350px;">
						<div class="meta">
							<table>
								<tbody>
									<tr>
										<td class="tit">品名：</td>
										<td>${map['HZHH01'].name }</td>
									</tr>
									<tr>
										<td class="tit" valign="top">规格：</td>
										<td>
										<p><i class="fz_s">情深意重：绿茶、玉蝴蝶、黄菊 1朵*4.6克</i></p>
										<p><i class="fz_s">花好月圆：绿茶、绿梅花 1朵*5.4克</i></p>
										<p><i class="fz_s">鸾凤和鸣：绿茶、红茶、百合花 1朵*4.6克</i></p>
										<p><i class="fz_s">嫦娥奔月：绿茶、茉莉花、金莲花 1朵*4.6克</i></p>
										<p><i class="fz_s">星语心愿：绿茶、金莲花、白菊 1朵*4.6克</i></p>
										</td>
									</tr>
									<tr>
										<td class="tit">价格：</td>
										<td><i class="red" style="margin-right:18px;">￥${map['HZHH01'].unitPrice }</i></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="deta">星语心愿•金莲花の孤寂：绿茶、金莲花、白菊<br />
功效：消炎止咳、条理长尾、帮助消化等。<br />
嫦娥奔月•茉莉花の贞洁：绿茶、茉莉花、金莲花<br />
功效：清肝明目、疏肝和胃、理气解郁等。<br />
鸾凤和鸣•百合の神圣：绿茶、红茶、百合花<br />
功效：去火安神、滋阴润肺、助睡眠。</div>
						<div class="btn">
						<a target="_blank" href="${_ctxPath}/buy-${brand.brandId}-${map['HZHH01'].productId }.htm" target="_blank" rel="nofollow" class="buy a_link2">
							<c:choose>
								<c:when test="${isPreSell }">预约购买</c:when>
								<c:otherwise>立即购买</c:otherwise>
							</c:choose>
						</a>
						</div>
					</div>
					<div class="r w_max" style="margin-right:40px;">
						<img src="${_imagesPath}/uploadfolder/productDetail/p_b8.png" alt="">
					</div>
				</div>
			</div>
			<div class="d_item">
				<div class="hd J_anchor" id="tea5" data-name="温馨红茶礼盒"><img src="${_imagesPath}/uploadfolder/productDetail/pro_tit7.jpg" alt="" /></div>
				<div class="bd cf">
					<div class="l w_max" style="margin-left:92px;">
						<img src="${_imagesPath}/uploadfolder/productDetail/p_b9.png" alt="" />
					</div>
					<div class="r w_min" style="margin-right:10px;width:357px;">
						<div class="meta">
							<table>
								<tbody>
									<tr>
										<td class="tit">品名：</td>
										<td>${map['HZXQ01'].name }</td>
									</tr>
									<tr>
										<td class="tit" valign="top">规格：</td>
										<td><p><i class="fz_s">尤溪红*1、正山小种*1、祁门红茶*1</i></p>
										<p><i class="fz_s">滇红*1、问山红*1、九曲红梅*1(20g*6)</i></p></td>
									</tr>
									<tr>
										<td class="tit">价格：</td>
										<td><i class="red" style="margin-right:18px;">￥${map['HZXQ01'].unitPrice }</i></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="deta">尤溪红——【苏醒】唤醒身体的每一个细胞，满怀憧憬和期待，迎接这活力的新一天。<br />
正山小种——【激活】慵懒的午后，一杯茶，一份甜点，静静思考，慢慢品味。<br />
祁门红茶——【默契】所有的相遇和回眸都是缘分，茶逢知己，只有默契，毫无倦意。<br />
滇红——【团圆】亲朋相聚，宴请宾客，久别重逢，以茶会友，其乐融融。<br />
问山红——【分享】品茶不光是品茶香，更是制茶人朴素的初心，与你，一起分享。<br />
九曲红梅——【安静】不需要浓烈相守，只求淡淡相依，安静的可以和自己对话。</div>
						<div class="btn">
						<a target="_blank" href="${_ctxPath}/buy-${brand.brandId}-${map['HZXQ01'].productId }.htm" target="_blank" rel="nofollow" class="buy a_link2">
							<c:choose>
								<c:when test="${isPreSell }">预约购买</c:when>
								<c:otherwise>立即购买</c:otherwise>
							</c:choose>
						</a>
						</div>
					</div>
				</div>
			</div>
			<div class="d_item">
				<div class="hd"><img src="${_imagesPath}/uploadfolder/productDetail/pro_tit8.jpg" alt=""></div>
				<div class="bd cf">
					<div class="l w_min" style="margin-left:51px;width:450px;">
						<div class="meta">
							<table>
								<tbody>
									<tr>
										<td class="tit">品名：</td>
										<td>${map['HZZX01'].name }</td>
									</tr>
									<tr>
										<td class="tit" valign="top">规格：</td>
										<td>
										<p><i class="fz_s">祁门红茶*1、九曲红梅*1、正山小种*1、滇红功夫*(150g*4)</i></p>
										</td>
									</tr>
									<tr>
										<td class="tit">价格：</td>
										<td><i class="red" style="margin-right:18px;">￥${map['HZZX01'].unitPrice }</i></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="deta">祁门红茶——高香群芳最，难得一知己，诉衷肠，话密语。<br />
九曲红梅——被遗忘的时光，孤傲的伫立回忆中，淡淡相依，一切如初。<br />
正山小种——醇厚甘香，独特柔和，古老纯正的正能量<br />
滇红——盎然的激情，团聚的热烈感，温馨的感动</div>
						<div class="btn">
						<a target="_blank" href="${_ctxPath}/buy-${brand.brandId}-${map['HZZX01'].productId }.htm" target="_blank" rel="nofollow" class="buy a_link2">
							<c:choose>
								<c:when test="${isPreSell }">预约购买</c:when>
								<c:otherwise>立即购买</c:otherwise>
							</c:choose>
						</a>
						</div>
					</div>
					<div class="r w_max" style="margin-right:10px;width:450px;">
						<img src="${_imagesPath}/uploadfolder/productDetail/p_b10.png" alt="">
					</div>
				</div>
			</div>
			<div class="d_item">
				<div class="hd"><img src="${_imagesPath}/uploadfolder/productDetail/pro_tit9.jpg" alt="" /></div>
				<div class="bd cf">
					<div class="l w_max" style="margin-left:92px;">
						<img src="${_imagesPath}/uploadfolder/productDetail/p_b11.png" alt="" />
					</div>
					<div class="r w_min" style="margin-right:10px;margin-top:50px;width:357px;">
						<div class="meta">
							<table>
								<tbody>
									<tr>
										<td class="tit">品名：</td>
										<td>${map['HZBB01'].name }</td>
									</tr>
									<tr>
										<td class="tit" valign="top">规格：</td>
										<td><p><i class="fz_s">问山红*1、滇红*1、正山小种(30g*3)</i></p></td>
									</tr>
									<tr>
										<td class="tit">价格：</td>
										<td><i class="red" style="margin-right:18px;">￥${map['HZBB01'].unitPrice }</i></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="deta">问山红——朴素而坚韧，令人怦然心动的温柔<br />
滇红——盎然的激情，团聚的热烈感，温馨的感动<br />
正山小种——醇厚甘香，独特柔和，古老纯正的正能量</div>
						<div class="btn">
						<a target="_blank" href="${_ctxPath}/buy-${brand.brandId}-${map['HZBB01'].productId }.htm" target="_blank" rel="nofollow" class="buy a_link2">
							<c:choose>
								<c:when test="${isPreSell }">预约购买</c:when>
								<c:otherwise>立即购买</c:otherwise>
							</c:choose>
						</a>
						</div>
					</div>
				</div>
			</div>
			<div class="d_item">
				<div class="hd J_anchor" id="tea6" data-name="经典茶叶礼盒"><img src="${_imagesPath}/uploadfolder/productDetail/pro_tit10.jpg" alt=""></div>
				<div class="bd cf">
					<div class="l w_min" style="margin-left:51px;width:433px;">
						<div class="meta">
							<table>
								<tbody>
									<tr>
										<td class="tit">品名：</td>
										<td>${map['HZZS01'].name }</td>
									</tr>
									<tr>
										<td class="tit" valign="top">规格：</td>
										<td>
										<p><i class="fz_s">正山小种*4(50g*4)</i></p>
										</td>
									</tr>
									<tr>
										<td class="tit">价格：</td>
										<td><i class="red" style="margin-right:18px;">￥${map['HZZS01'].unitPrice }</i></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="deta">正山小种红茶，是世界红茶的鼻祖。又称拉普山小种，是中国生产的一种红茶，茶叶是用松针或松柴熏制而成，有着非常浓烈的香味。因为熏制的原因，茶叶呈黑色，茶汤为深红色，经久耐泡，滋味醇厚，似桂元汤味，气味芬芳浓烈，以醇馥的烟香和桂元汤、蜜枣味为其主要品质特色。一位茶人曾说：这是一种让人爱憎分明的茶，只要有一次你喜欢上它，便永远不会放弃它。</div>
						<div class="btn">
						<a target="_blank" href="${_ctxPath}/buy-${brand.brandId}-${map['HZZS01'].productId }.htm" target="_blank" rel="nofollow" class="buy a_link2">
							<c:choose>
								<c:when test="${isPreSell }">预约购买</c:when>
								<c:otherwise>立即购买</c:otherwise>
							</c:choose>
						</a>
						</div>
					</div>
					<div class="r w_max" style="margin-right:10px;width:450px;">
						<img src="${_imagesPath}/uploadfolder/productDetail/p_b12.png" alt="">
					</div>
				</div>
			</div>
			<div class="d_item">
				<div class="hd"><img src="${_imagesPath}/uploadfolder/productDetail/pro_tit11.jpg" alt="" /></div>
				<div class="bd cf">
					<div class="l w_max" style="margin-left:92px;">
						<img src="${_imagesPath}/uploadfolder/productDetail/p_b13.png" alt="" />
					</div>
					<div class="r w_min" style="margin-right:10px;margin-top:28px;width:357px;">
						<div class="meta">
							<table>
								<tbody>
									<tr>
										<td class="tit">品名：</td>
										<td>${map['HZQM01'].name }</td>
									</tr>
									<tr>
										<td class="tit" valign="top">规格：</td>
										<td><p><i class="fz_s">祁门红茶*4(50g*4)</i></p></td>
									</tr>
									<tr>
										<td class="tit">价格：</td>
										<td><i class="red" style="margin-right:18px;">￥${map['HZQM01'].unitPrice }</i></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="deta">祁门红茶，中国历史名茶。似花、似果、似蜜的“祁门香”闻名于世，位居世界三大高香名茶之首。祁门茶叶条索紧细秀长，汤色红艳明亮，特别是其香气酷似果香，又带兰花香，清鲜而且持久。既可单独泡饮，也可加入牛奶调饮。祁门红茶，是“红茶”中的佼佼者，向以“香高、味醇、形美、色艳”四绝驰名于世，祁门红茶始制于清代光绪年间，为工夫红茶的珍品。</div>
						<div class="btn">
						<a target="_blank" href="${_ctxPath}/buy-${brand.brandId}-${map['HZQM01'].productId }.htm" target="_blank" rel="nofollow" class="buy a_link2">
							<c:choose>
								<c:when test="${isPreSell }">预约购买</c:when>
								<c:otherwise>立即购买</c:otherwise>
							</c:choose>
					   </a>
					   </div>
					</div>
				</div>
			</div>
			<div class="d_item">
				<div class="hd"><img src="${_imagesPath}/uploadfolder/productDetail/pro_tit12.jpg" alt=""></div>
				<div class="bd cf">
					<div class="l w_min" style="margin-left:51px;width:433px;">
						<div class="meta">
							<table>
								<tbody>
									<tr>
										<td class="tit">品名：</td>
										<td>${map['HZYC01'].name }</td>
									</tr>
									<tr>
										<td class="tit" valign="top">规格：</td>
										<td>
										<p><i class="fz_s">武夷岩茶*4(50g*4)</i></p>
										</td>
									</tr>
									<tr>
										<td class="tit">价格：</td>
										<td><i class="red" style="margin-right:18px;">￥${map['HZYC01'].unitPrice }</i></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="deta">清朝美食大师袁枚说：“尝尽天下之茶，以武夷山顶所生，冲开白色者为第一。武夷岩茶具有绿茶之清香，红茶之甘醇，是中国乌龙茶中之极品。绿叶红镶边，形态艳丽；深橙黄亮，汤色如玛瑙；岩韵醇厚，花香怡人；清鲜甘爽回味悠悠。饮后齿颊留香、喉底回甘。它既有红茶的甘醇，又有绿茶的清香，是“活、甘、清、香”齐备的茶中珍品。</div>
					   <div class="btn">
					   <a target="_blank" href="${_ctxPath}/buy-${brand.brandId}-${map['HZYC01'].productId }.htm" target="_blank" rel="nofollow" class="buy a_link2">
							<c:choose>
								<c:when test="${isPreSell }">预约购买</c:when>
								<c:otherwise>立即购买</c:otherwise>
							</c:choose>
						</a>
						</div>
					</div>
					<div class="r w_max" style="margin-right:10px;width:450px;">
						<img src="${_imagesPath}/uploadfolder/productDetail/p_b14.png" alt="">
					</div>
				</div>
			</div>
			<div class="d_item">
				<div class="hd"><img src="${_imagesPath}/uploadfolder/productDetail/pro_tit13.jpg" alt="" /></div>
				<div class="bd cf">
					<div class="l w_max" style="margin-left:92px;">
						<img src="${_imagesPath}/uploadfolder/productDetail/p_b15.png" alt="" />
					</div>
					<div class="r w_min" style="margin-right:50px;margin-top:28px;">
						<div class="meta">
							<table>
								<tbody>
									<tr>
										<td class="tit">品名：</td>
										<td>${map['HZZH01'].name }</td>
									</tr>
									<tr>
										<td class="tit" valign="top">规格：</td>
										<td><p><i class="fz_s">滇红*4(50g*4)</i></p></td>
									</tr>
									<tr>
										<td class="tit">价格：</td>
										<td><i class="red" style="margin-right:18px;">￥${map['HZZH01'].unitPrice }</i></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="deta">滇红采用优良的云南大叶种茶树鲜叶，先经萎凋、揉捻或揉切、发酵、干燥等工序制成成品茶；再加工制成滇红功夫茶，又经揉切制成滇红碎茶。上述各道工序，长期以来，均以手工操作。成品茶外形条索紧结、雄壮、肥硕，色泽乌润，汤色鲜红，香气鲜浓，滋味醇厚，富有收敛性，叶底红润匀亮，金毫特显，毫色有淡黄、菊黄、金黄之分，为外销名茶。</div>
						<div class="btn">
						<a target="_blank" href="${_ctxPath}/buy-${brand.brandId}-${map['HZZH01'].productId }.htm" target="_blank" rel="nofollow" class="buy a_link2">
								<c:choose>
									<c:when test="${isPreSell }">预约购买</c:when>
									<c:otherwise>立即购买</c:otherwise>
								</c:choose>
						</a>
						</div>
					</div>
				</div>
			</div>
			<div class="pic_show">
				<img src="${_imagesPath}/uploadfolder/productDetail/img7.jpg" alt="" />
			</div>
		</div>
		<div class="slogan">
			一城一品致力于打造中国最有品质的城市商品。品茶、品境、品味聚散离合，人生百味。
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