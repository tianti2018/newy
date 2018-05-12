<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.ResourceBundle"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
<%ResourceBundle res = ResourceBundle.getBundle("system"); %> 
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">
	<meta http-equiv="x-dns-prefetch-control" content="on">
	<meta http-equiv="x-dns-prefetch-control" content="on">
	<title><%=res.getString("company")%>产品详情</title>	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttons.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/novipbuy.css">
	<link rel="stylesheet"  href="<%=request.getContextPath()%>/css/footer.css" />
	<link rel="stylesheet"  href="<%=request.getContextPath()%>/css/detail.css" />
	<link rel="stylesheet"  href="<%=request.getContextPath()%>/css/flexslider.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/source/jquery.fancybox.css?v=2.1.5" media="screen" />
	<link href="<%=request.getContextPath()%>/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/payfor.js"></script>
	<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.mobile-1.4.5.min.js"></script> --%>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/family.css" />
	<link href="<%=request.getContextPath()%>/css/style-metro.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
	 
</head>
<body id="bodyy">
		<div class="wrap">
			<div class="banner">
				<div class="flexslider">
					<ul class="slides">
					
						<c:if test="${prod.prodId==2}">
						<a href="#" ontouchmove="shows(1)">
					<li><img src="<%=request.getContextPath()%>/images/miaosha/mianmo/mm3banner.jpg" style="width: 100%"></li>
					</a>
				</c:if>
				
				<c:if test="${prod.prodId==3}">
				<a href="#" ontouchmove="shows(1)">
					<li><img src="<%=request.getContextPath()%>/images/miaosha/mianmo/mm3.jpg" style="width: 100%"></li>
					</a>
				</c:if>
				<c:if test="${prod.prodId==4}">
				<a href="#" ontouchmove="shows(1)">
					<li><img src="<%=request.getContextPath()%>/images/lunbotu/jhq.jpg" style="width: 100%"></li>
					</a>
				</c:if>
				<c:if test="${prod.prodId==5}">
				<a href="#" ontouchmove="shows(1)">
					<li><img src="<%=request.getContextPath()%>/images/lunbotu/jingshuiji.jpg" style="width: 100%"></li>
					</a>
				</c:if>
				<c:if test="${prod.prodType==6}">
				<a href="#" ontouchmove="shows(1)">
					<li><img src="<%=request.getContextPath()%>/images/miaosha/neiyi/hongse/ny1banna.jpg" style="width: 100%"></li>
				</a>
				</c:if>
				<c:if test="${prod.prodType==7}">
				<a href="#" ontouchmove="shows(1)">
					<li><img src="<%=request.getContextPath()%>/images/miaosha/neiyi/heise/ny2banna.jpg" style="width: 100%"></li>
				</a>
				</c:if>
				<c:if test="${prod.prodType==8}">
				<a href="#" ontouchmove="shows(1)">
					<li><img src="<%=request.getContextPath()%>/images/miaosha/neiyi/fense/ny3banna.jpg" style="width: 100%"></li>
				</a>
				</c:if>
				<c:if test="${prod.prodId==29}">
				<a href="#" ontouchmove="shows(1)">
					<li><img src="<%=request.getContextPath()%>/images/miaosha/yanzhao/yanzhao.jpg" style="width: 100%"></li>
				</a>
				</c:if>
				<c:if test="${prod.prodId==39}">
				<a href="#" ontouchmove="shows(1)">
					<li><img src="<%=request.getContextPath()%>/images/miaosha/wsj/wsjbanna.jpg" style="width: 100%"></li>
				</a>
				</c:if>
				<c:if test="${prod.prodId==40}">
				<a href="#" ontouchmove="shows(1)">
					<li><img src="<%=request.getContextPath()%>/images/miaosha/cha/gushuhongcha/hongchabanna.jpg" style="width: 100%"></li>
				</a>
				</c:if>
				<c:if test="${prod.prodId==41}">
				<a href="#" ontouchmove="shows(1)">
					<li><img src="<%=request.getContextPath()%>/images/miaosha/cha/puer/puerbanna.jpg" style="width: 100%"></li>
				</a>
				</c:if>
				<c:if test="${prod.prodId==49}">
				<a href="#" ontouchmove="shows(1)">
					<li><img src="<%=request.getContextPath()%>/images/miaosha/shuiguangzhen/shuiguangzhen_banna.jpg" style="width: 100%"></li>
				</a>
				</c:if>
					<c:if test="${prod.prodId==50}">
					<a href="#" ontouchmove="shows(1)">
					<li><img src="<%=request.getContextPath()%>/images/miaosha/huoliku/shuizuan/banna.jpg" style="width: 100%"></li>
				</a>
				</c:if>
				<c:if test="${prod.prodId==51}">
				<a href="#" ontouchmove="shows(1)">
					<li><img src="<%=request.getContextPath()%>/images/miaosha/huoliku/tu an/banna.jpg" style="width: 100%"></li>
				</a>
				</c:if>
				<c:if test="${prod.prodId==52}">
				<a href="#" ontouchmove="shows(1)">
					<li><img src="<%=request.getContextPath()%>/images/miaosha/huoliku/tuibuhuawen/banna.jpg" style="width: 100%"></li>
				</a>
				</c:if>
				<c:if test="${prod.prodId==53}">
				<a href="#" ontouchmove="shows(1)">
					<li><img src="<%=request.getContextPath()%>/images/miaosha/kebeieryanwenxiao/banna.jpg" style="width: 100%"></li>
				</a>
				</c:if>
				<c:if test="${prod.prodId==54}">
				<a href="#" ontouchmove="shows(1)">
					<li><img src="<%=request.getContextPath()%>/images/miaosha/guanjieling/banna.jpg" style="width: 100%"></li>
				</a>
				</c:if>
				<c:if test="${prod.prodId==55}">
				<a href="#" ontouchmove="shows(1)">
					<li><img src="<%=request.getContextPath()%>/images/miaosha/jingzhuibao/banna.jpg" style="width: 100%"></li>
				</a>
				</c:if>
				<c:if test="${prod.prodId==56}">
				<a href="#" ontouchmove="shows(1)">
					<li><img src="<%=request.getContextPath()%>/images/miaosha/yaoshudai/banna.jpg" style="width: 100%"></li>
				</a>
				</c:if>
				<c:if test="${prod.prodId==59}">
				<a href="#" ontouchmove="shows(1)">
					<li><img src="<%=request.getContextPath()%>/images/miaosha/neiyi/heiseduankuan/banna.jpg" style="width: 100%"></li>
				</a>
				</c:if>
			     <c:if test="${prod.prodId==81}">
			     <a href="#" ontouchmove="shows(1)">
					<li><img src="<%=request.getContextPath()%>/images/miaosha/nameibojinduotai/banna.jpg" style="width: 100%"></li>
				</a>
				</c:if>
				<c:if test="${prod.prodId==82}">
				<a href="#" ontouchmove="shows(3)">
					<img src="<%=request.getContextPath()%>/images/miaosha/jiayongjinghuaqi/banna.jpg" style="width: 100%">
				</a>
				</c:if>
				<c:if test="${prod.prodId==83}">
				<a href="#" ontouchmove="shows(3)">
					<img src="<%=request.getContextPath()%>/images/miaosha/jiemianmosi/banna.jpg" style="width: 100%">
				</a>
				</c:if>
				<c:if test="${prod.prodId==91}">
				<a href="#" ontouchmove="shows(3)">
					<img src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/banna.jpg" style="width: 100%">
				</a>
				</c:if>
				<c:if test="${prod.prodId==92}">
				<a href="#" ontouchmove="shows(3)">
					<img src="<%=request.getContextPath()%>/images/miaosha/meixiongyi/banna.jpg" style="width: 100%">
				</a>
				</c:if>
				<c:if test="${prod.prodId==93}">
				<a href="#" ontouchmove="shows(3)">
					<img src="<%=request.getContextPath()%>/images/miaosha/qinangzhen/banna.jpg" style="width: 100%">
				</a>
				</c:if>
				<c:if test="${prod.prodId==94}">
				<a href="#" ontouchmove="shows(3)">
					<img src="<%=request.getContextPath()%>/images/miaosha/yangshengyi/banna.jpg" style="width: 100%">
				</a>
				</c:if>
				<c:if test="${prod.prodId==95}">
				<a href="#" ontouchmove="shows(3)">
					<img src="<%=request.getContextPath()%>/images/miaosha/jianjingzhuililiaoyi/banna.jpg" style="width: 100%">
				</a>
				</c:if>
				<c:if test="${prod.prodId==96}">
				<a href="#" ontouchmove="shows(3)">
					<img src="<%=request.getContextPath()%>/images/miaosha/yaobao/banna.jpg" style="width: 100%">
				</a>
				</c:if>
				<c:if test="${prod.prodId==97}">
				<a href="#" ontouchmove="shows(3)">
					<img src="<%=request.getContextPath()%>/images/miaosha/lengfengzuodian/banna.jpg" style="width: 100%">
				</a>
				</c:if>
					</ul>
				</div>
			</div>
				<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.flexslider-min.js"></script>
			<script>
				$(function() {
					$(".flexslider").flexslider({
						animation: "slide",
						directionNav: false,
						touch: true
					});
				});
			</script>
			<div style="height:10px"></div>
			<div class="det-middle">
				<div class="det-pro-name">
					<span><c:out value="${prod.prodName}"/></span>
				</div>
				<div class="det-pro-num">
					<div class="det-pro-price">					
						<span>￥<em><c:out value="${prod.price }"/></em></span>
						<div class="pro-size-m">已选：
						<c:if test="${typeqty==1}">
						<span id="qty_item_3"><c:out value="${prod.prodName}"/></span>
						</c:if>
						<span id="qty_item_3"></span>
						/<span id="qty_item_2">1</span>件</div>
					</div>
					
					<div class="" id="size-wrap">
						<div class="" id="proSize">
							<div class="pro-color">
								<h2>规格：</h2>
								<c:forEach var="p" items="${typelist}" varStatus="vstatus">
								<c:if test="${p.prodState ==1 }">
								<a id="xuanze_${p.prodId}" class="xuanze" name="product" kuige="${p.prodColor}${p.prodSize}" stock="${p.stock}" transFee="${p.transFee}" prodId="${p.prodId}" price="${p.price}" onclick="checkProd(this)">
								<c:out value="${p.prodColor}${p.prodSize}"/> 
								<c:if test="${p.stock<500 }">
								库存：<c:out value="${p.stock }"/>件
								</c:if>
				               </a>
				               </c:if>
								</c:forEach>
							</div>
						</div>
					</div>					
					<div class="det-pro-buy">
						<div class="det-pro-sl">
							<span>数量</span>
					<a class="reduce" onclick="cut()" href="javascript:void(0)"><img src="<%=request.getContextPath()%>/images/novipbuy/reduce.png" alt=""></a>
				    <input type="text" name="qty_item_1" value="1" id="qty_item_1" onblur="inputqty()" class="text" size="4"/>
				    <a class="add" onclick="add()" href="javascript:void(0)"><img src="<%=request.getContextPath()%>/images/novipbuy/add.png" alt=""></a>
						</div>
						<div style="height: 2px;"></div>
					</div>
					<div class="det-pro-zj">
						<span>总计:</span>
						<span><em>￥<span id="total_prod_amt">${prod.price}</span></em> =</span>
						<span><em>￥<span id="total_prod_m">${prod.price}</span></em> +</span>
						<span><em>￥<span id="total_yunfei"><c:out value="${prod.transFee}"/></em>(运费)</span>
					</div>
					<c:if test="${typeqty>1}">
				 <input type="hidden" id="checkProdId" value=""/>
				 <input type="hidden" id="prodprice" value=""/>
				 <input type="hidden" id="productKC" value=""/>
				 </c:if>
				 <c:if test="${typeqty==1}">
				 <input type="hidden" id="checkProdId" value="${prod.prodId}"/>
				  <input type="hidden" id="prodprice" value="${prod.price}"/>				   
				   <input type="hidden" id="productKC" value="${prod.stock}"/>
				 </c:if>
				  <br>
				 <input type="checkbox" name="diyong" id="diyong" onclick="checkDiYong(this)">
				<label for="diyong" style="color:red;">
				 当前星火券：<span id="max_xhq_count"><c:out value="${xhq}"/></span>星火券，是否使用 ？</label>	
				<span id="diyiong_info" style="display:none;">
				<br>请输入星火券：<input type="text" name="diyongcount" value="0" id="diyongcount" onblur="inputDiYongAmt(this)";/>
				<input type="hidden" id="final_diyongamt" value="0">
				<input type="hidden" id="max_xhqamt" value="${xhq_money}">
				</div>
				<div class="message">
			<c:if test="${null!=orderAddress}">
				
				 <ul class="menu-list">
				<li>
				   <a href="javascript:;"><i class="arrows rotated"></i><em>收货信息</em></a> 
					<ul class="sub-menu" style="height:180px">
					    <li><a href="<%=request.getContextPath()%>/orderAddress/orderAddressAction!orderAddress.action" style="background:#00aa3a; display:inline-block; color:#fff;">选择默认收货地址</a></li>
						<li>收货人：<em>${orderAddress.userName}</em></li>
						<li>联系方式：<em> ${orderAddress.mobile} </em></li>
						<li>收货地址：<em>${orderAddress.address}</em></li>
						
										
					</ul>
					
				</li>
					
					<li><a href="#" id="lijibuy" onclick="javascript:orderBuy()" class="btn green">立即购买</a></li>	
			</ul> 

			</c:if>
			<c:if test="${null==orderAddress}">
				<div align="center">
					<a href="<%=request.getContextPath()%>/orderAddress/orderAddressAction!orderAddress.action"  class="btn green" onclick="go();">选择一条收货地址</a>
				</div>
			</c:if>
		</div>
			</div>
			</div>

			<div class="tab-wrap">
				<div class="tab">
					<ul class="tuwen">
						<li id="miaoshuB">图文详情</li>
						<li id="shouhouB">售后服务</li>
						<li id="pinglunB">商品评论(<span>${count}</span>)</li>
					</ul>
				</div>
				<div id="miaoshu" class="shangpintu">
					<div id="product-detail-1">
						<c:if test="${prod.manufacturer==3}">
		<ul>
			<c:if test="${prod.prodId==2||prod.prodId==3}">
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/mianmo/6400.jpg"></li>
			<li><h2 style="color: red;">产品介绍：</h2></li>
			<li><h3 style="color:green;">科技、自然、纯净，焕发您肌肤活力光彩</h3></li>
  			<li><h3> &nbsp;&nbsp;&nbsp;&nbsp;本产品采用特殊工艺定制面料秘制而成，如隐形一般，能和肌肤亲密服帖，并配以肽级生物活性因子，深入皮肤组织，快速补充养分，修复细小皱纹。多种生物提取物，经特殊萃取工艺，该产品来源于诺贝尔生理学及医学奖和国家863重点攻关项目研究成果，可促进肌肤新陈代谢延缓衰老，同时也为肌肤做到全面防护。</h3></li>
			<li>&nbsp;</li>
			<li><h2 style="color: red;">主要功效：</h2></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/mianmo/6401.jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/mianmo/6402.jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/mianmo/6403.jpg"></li>
			<li><h2 style="color: red;">使用方法：</h2></li>
			<li><h3>1、蚕丝膜共两层，开袋后将白色蚕丝膜对准口、鼻、眼直接敷上，抚平，2分钟后揭开上面的珠光膜，放松享受25-30分钟即可揭下面膜，无需清洁，保持至清晨，欣赏集联集带给你的美肤惊喜；</h3></li>
			<li><h3>2、开袋即用，初次使用时，可连续使用3天，每天一片，效果更佳。后期建议一周2次；</h3></li>
			<li><h3>3、夏天可置放冰箱后再开袋使用，对晒伤、晒后修复疗效显著</h3></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/mianmo/6404.jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/mianmo/6405.jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/mianmo/6406.jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/mianmo/6407.jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/mianmo/6408.jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/mianmo/6409.jpg"></li>
			<c:if test="${code !=null}">
						<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></li>
				</c:if>
			</c:if>
		</ul>
		</c:if>
		</div>
		
		<div id="product-detail-1">
		<c:if test="${prod.prodId==4}">
		<ul>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jinghuaqi/jhq_01.jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jinghuaqi/jhq_02.jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jinghuaqi/jhq_03.jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jinghuaqi/jhq_04.jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jinghuaqi/jhq_05.jpg"></li>
			<c:if test="${code !=null}">
						<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></li>
				</c:if>
		</ul>
		</c:if>
		</div>
		<div id="product-detail-1">
		<c:if test="${prod.prodId==5}">
		<ul>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jingshuiji/jsj (1).jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jingshuiji/jsj (2).jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jingshuiji/jsj (3).jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jingshuiji/jsj (4).jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jingshuiji/jsj (5).jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jingshuiji/jsj (6).jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jingshuiji/jsj (7).jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jingshuiji/jsj (8).jpg"></li>

			<c:if test="${code !=null}">
						<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></li>
				</c:if>
		</ul>
		</c:if>
		</div>
		<div id="product-detail-1">
		<c:if test="${prod.prodType==6}">
		<ul>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/hongse/hong_01.jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/hongse/hong_02.jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/hongse/hong_03.jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/hongse/hong_04.jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/hongse/hong_05.jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/hongse/hong_06.jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/hongse/hong_07.jpg"></li>
			<c:if test="${code !=null}">
						<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></li>
				</c:if>
		</ul>
		</c:if>
		</div>
		<div id="product-detail-1">
		<c:if test="${prod.prodType==7}">
		<ul>
			<li><h1 style="color: red">注:此产品不包括文胸</h1></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heise/heise1 (1).jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heise/heise1 (2).jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heise/heise1 (3).jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heise/heise1 (4).jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heise/heise1 (5).jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heise/heise1 (6).jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heise/heise1 (7).jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heise/heise1 (8).jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heise/heise1 (9).jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heise/heise1 (11).jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heise/heise1 (12).jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heise/heise1 (13).jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heise/heise1 (14).jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heise/heise1 (15).jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heise/heise1 (16).jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heise/heise1 (17).jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heise/heise1 (18).jpg"></li>
			<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heise/heise1 (19).jpg"></li>
			<c:if test="${code !=null}">
						<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></li>
				</c:if>					
		</ul>
		</c:if>
		</div>
       <div id="product-detail-1">
		<c:if test="${prod.prodType==8}">
			<ul>
				<li><h1 style="color: red">注:此产品不包括文胸</h1></li>
				<li><img style="width: 100%" alt=""
					src="<%=request.getContextPath()%>/images/miaosha/neiyi/fense/fen_01.jpg"></li>
				<li><img style="width: 100%" alt=""
					src="<%=request.getContextPath()%>/images/miaosha/neiyi/fense/fen_02.jpg"></li>
				<li><img style="width: 100%" alt=""
					src="<%=request.getContextPath()%>/images/miaosha/neiyi/fense/fen_03.jpg"></li>
				<li><img style="width: 100%" alt=""
					src="<%=request.getContextPath()%>/images/miaosha/neiyi/fense/fen_04.jpg"></li>
				<li><img style="width: 100%" alt=""
					src="<%=request.getContextPath()%>/images/miaosha/neiyi/fense/fen_05.jpg"></li>
				<li><img style="width: 100%" alt=""
					src="<%=request.getContextPath()%>/images/miaosha/neiyi/fense/fen_06.jpg"></li>

				<li><img style="width: 100%" alt=""
					src="<%=request.getContextPath()%>/images/miaosha/neiyi/fense/fen_07.jpg"></li>
				<li><img style="width: 100%" alt=""
					src="<%=request.getContextPath()%>/images/miaosha/neiyi/fense/fen_08.jpg"></li>
					<c:if test="${code !=null}">
						<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></li>
				</c:if>
			</ul>
		</c:if>
        </div>
<div id="product-detail-1">
	<c:if test="${prod.prodId==29}">
			<ul>
				<li><img style="width: 100%" alt=""
					src="<%=request.getContextPath()%>/images/miaosha/yanzhao/yanzhaoxiangqing_01.jpg"></li>
			
			<li><img style="width: 100%" alt=""
					src="<%=request.getContextPath()%>/images/miaosha/yanzhao/yanzhaoxiangqing_02.jpg"></li>
					
					<li><img style="width: 100%" alt=""
					src="<%=request.getContextPath()%>/images/miaosha/yanzhao/yanzhaoxiangqing_03.jpg"></li>
					
					<li><img style="width: 100%" alt=""
					src="<%=request.getContextPath()%>/images/miaosha/yanzhao/yanzhaoxiangqing_04.jpg"></li>
			<li><img style="width: 100%" alt=""
					src="<%=request.getContextPath()%>/images/miaosha/yanzhao/yanzhaoxiangqing_05.jpg"></li>
			<li><img style="width: 100%" alt=""
					src="<%=request.getContextPath()%>/images/miaosha/yanzhao/yanzhaoxiangqing_06.jpg"></li>
			</ul>
			<c:if test="${code !=null}">
						<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></li>
				</c:if>
		</c:if>
</div>

<div id="product-detail-1">
<c:if test="${prod.prodId==39}">
			<ul>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/wsj/wsj01.jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/wsj/wsj02.jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/wsj/wsj03.jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/wsj/wsj04.jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/wsj/wsj05.jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/wsj/wsj06.jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/wsj/wsj07.jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/wsj/wsj08.jpg"></li>
				<c:if test="${code !=null}">
						<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></li>
				</c:if>			
			</ul>
		</c:if>
		</div>
		<div id="product-detail-1">
		<c:if test="${prod.prodId==40}">
			<ul>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/gushuhongcha/hongcha (1).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/gushuhongcha/hongcha (2).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/gushuhongcha/hongcha (3).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/gushuhongcha/hongcha (4).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/gushuhongcha/hongcha (5).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/gushuhongcha/hongcha (6).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/gushuhongcha/hongcha (7).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/gushuhongcha/hongcha (8).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/gushuhongcha/hongcha (9).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/gushuhongcha/hongcha (10).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/gushuhongcha/hongcha (11).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/gushuhongcha/hongcha (12).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/gushuhongcha/hongcha (13).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/gushuhongcha/hongcha (14).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/gushuhongcha/hongcha (15).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/gushuhongcha/hongcha (16).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/gushuhongcha/hongcha (17).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/gushuhongcha/hongcha (18).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/gushuhongcha/hongcha (19).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/gushuhongcha/hongcha (20).jpg"></li>
				<c:if test="${code !=null}">
						<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></li>
				</c:if>		
			</ul>
		</c:if>
		</div>
		<div id="product-detail-1">
		<c:if test="${prod.prodId==41}">
			<ul>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/puer/puer (1).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/puer/puer (2).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/puer/puer (3).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/puer/puer (4).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/puer/puer (5).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/puer/puer (6).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/puer/puer (7).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/puer/puer (8).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/puer/puer (9).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/puer/puer (10).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/puer/puer (11).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/puer/puer (12).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/puer/puer (13).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/puer/puer (14).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/puer/puer (15).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/puer/puer (16).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/puer/puer (17).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/puer/puer (18).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/puer/puer (19).jpg"></li>	
				<c:if test="${code !=null}">
						<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></li>
				</c:if>	
			</ul>
		</c:if>
		</div>
		<div id="product-detail-1">
		<c:if test="${prod.prodId==49}">
			<ul>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/shuiguangzhen/shuiguangzhen1 (1).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/shuiguangzhen/shuiguangzhen1 (2).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/shuiguangzhen/shuiguangzhen1 (3).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/shuiguangzhen/shuiguangzhen1 (4).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/shuiguangzhen/shuiguangzhen1 (5).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/shuiguangzhen/shuiguangzhen1 (6).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/shuiguangzhen/shuiguangzhen1 (7).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/shuiguangzhen/shuiguangzhen1 (8).jpg"></li>
				<c:if test="${code !=null}">
						<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></li>
				</c:if>
			</ul>
		</c:if>
		</div>
		<div id="product-detail-1">
         <c:if test="${prod.prodId==50}">
			<ul>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/shuizuan/a_1 (1).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/shuizuan/a_1 (2).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/shuizuan/a_1 (3).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/shuizuan/a_1 (4).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/shuizuan/a_1 (5).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/shuizuan/a_1 (6).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/shuizuan/a_1 (7).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/shuizuan/a_1 (8).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/shuizuan/a_1 (9).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/shuizuan/a_1 (10).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/shuizuan/a_1 (11).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/shuizuan/a_1 (12).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/shuizuan/a_1 (13).jpg"></li>
				<c:if test="${code !=null}">
						<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></li>
				</c:if>
			</ul>
		</c:if>
		</div>
		<div id="product-detail-1">
		<c:if test="${prod.prodId==51}">
			<ul>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/tu an/a_1 (1).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/tu an/a_1 (2).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/tu an/a_1 (3).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/tu an/a_1 (4).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/tu an/a_1 (5).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/tu an/a_1 (6).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/tu an/a_1 (7).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/tu an/a_1 (8).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/tu an/a_1 (9).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/tu an/a_1 (10).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/tu an/a_1 (11).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/tu an/a_1 (12).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/tu an/a_1 (13).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/tu an/a_1 (14).jpg"></li>	
				<c:if test="${code !=null}">
						<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></li>
				</c:if>
			</ul>
		</c:if>
		</div>
		<div id="product-detail-1">
		<c:if test="${prod.prodId==52}">
			<ul>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/tuibuhuawen/a_1 (1).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/tuibuhuawen/a_1 (2).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/tuibuhuawen/a_1 (3).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/tuibuhuawen/a_1 (4).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/tuibuhuawen/a_1 (5).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/tuibuhuawen/a_1 (6).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/tuibuhuawen/a_1 (7).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/tuibuhuawen/a_1 (8).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/tuibuhuawen/a_1 (9).jpg"></li>
				<c:if test="${code !=null}">
						<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></li>
				</c:if>	
			</ul>
		</c:if>
		</div>
		<div id="product-detail-1">
		<c:if test="${prod.prodId==53}">
			<ul>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/kebeieryanwenxiao/a1 (1).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/kebeieryanwenxiao/a1 (2).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/kebeieryanwenxiao/a1 (3).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/kebeieryanwenxiao/a1 (4).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/kebeieryanwenxiao/a1 (5).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/kebeieryanwenxiao/a1 (6).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/kebeieryanwenxiao/a1 (7).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/kebeieryanwenxiao/a1 (8).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/kebeieryanwenxiao/a1 (9).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/kebeieryanwenxiao/a1 (10).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/kebeieryanwenxiao/a1 (11).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/kebeieryanwenxiao/a1 (12).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/kebeieryanwenxiao/a1 (13).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/kebeieryanwenxiao/a1 (14).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/kebeieryanwenxiao/a1 (15).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/kebeieryanwenxiao/a1 (16).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/kebeieryanwenxiao/a1 (17).jpg"></li>
				<c:if test="${code !=null}">
						<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></li>
				</c:if>
			</ul>
		</c:if>
		</div>
		<div id="product-detail-1">
		<c:if test="${prod.prodId==54}">
			<ul>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/guanjieling/a_1 (1).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/guanjieling/a_1 (2).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/guanjieling/a_1 (3).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/guanjieling/a_1 (4).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/guanjieling/a_1 (5).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/guanjieling/a_1 (6).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/guanjieling/a_1 (7).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/guanjieling/a_1 (8).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/guanjieling/a_1 (9).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/guanjieling/a_1 (10).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/guanjieling/a_1 (11).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/guanjieling/a_1 (12).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/guanjieling/a_1 (13).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/guanjieling/a_1 (14).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/guanjieling/a_1 (15).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/guanjieling/a_1 (16).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/guanjieling/a_1 (17).jpg"></li>
				<c:if test="${code !=null}">
						<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></li>
				</c:if>
			</ul>
		</c:if>
		</div>
		<div id="product-detail-1">
		<c:if test="${prod.prodId==55}">
			<ul>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jingzhuibao/a1 (1).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jingzhuibao/a1 (2).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jingzhuibao/a1 (3).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jingzhuibao/a1 (4).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jingzhuibao/a1 (5).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jingzhuibao/a1 (6).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jingzhuibao/a1 (7).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jingzhuibao/a1 (8).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jingzhuibao/a1 (9).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jingzhuibao/a1 (10).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jingzhuibao/a1 (11).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jingzhuibao/a1 (12).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jingzhuibao/a1 (13).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jingzhuibao/a1 (14).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jingzhuibao/a1 (15).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jingzhuibao/a1 (16).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jingzhuibao/a1 (17).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jingzhuibao/a1 (18).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jingzhuibao/a1 (19).jpg"></li>
				<c:if test="${code !=null}">
						<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></li>
				</c:if>
			</ul>
		</c:if>
		</div>
		<div id="product-detail-1">
		<c:if test="${prod.prodId==56}">
			<ul>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/yaoshudai/a_1 (1).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/yaoshudai/a_1 (2).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/yaoshudai/a_1 (3).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/yaoshudai/a_1 (4).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/yaoshudai/a_1 (5).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/yaoshudai/a_1 (6).jpg"></li>
				<c:if test="${code !=null}">
						<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></li>
				</c:if>
			</ul>
		</c:if>
		</div>
		<div id="product-detail-1">
		<c:if test="${prod.prodId==59}">
			<ul>
			<li><h1 style="color: red">本产品不包括内裤</h1></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heiseduankuan/a_1 (1).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heiseduankuan/a_1 (2).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heiseduankuan/a_1 (3).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heiseduankuan/a_1 (4).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heiseduankuan/a_1 (5).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heiseduankuan/a_1 (6).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heiseduankuan/a_1 (7).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heiseduankuan/a_1 (8).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heiseduankuan/a_1 (9).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heiseduankuan/a_1 (10).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heiseduankuan/a_1 (11).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heiseduankuan/a_1 (12).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heiseduankuan/a_1 (13).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heiseduankuan/a_1 (14).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heiseduankuan/a_1 (15).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heiseduankuan/a_1 (16).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heiseduankuan/a_1 (17).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heiseduankuan/a_1 (18).jpg"></li>
				<c:if test="${code !=null}">
						<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></li>
				</c:if>
			
			</ul>
		</c:if>
		</div>
		<div id="product-detail-1">
		<c:if test="${prod.prodId==81}">
			<ul>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/nameibojinduotai/a1 (1).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/nameibojinduotai/a1 (2).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/nameibojinduotai/a1 (3).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/nameibojinduotai/a1 (4).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/nameibojinduotai/a1 (5).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/nameibojinduotai/a1 (6).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/nameibojinduotai/a1 (7).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/nameibojinduotai/a1 (8).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/nameibojinduotai/a1 (9).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/nameibojinduotai/a1 (10).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/nameibojinduotai/a1 (11).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/nameibojinduotai/a1 (12).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/nameibojinduotai/a1 (13).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/nameibojinduotai/a1 (14).jpg"></li>
				<c:if test="${code !=null}">
						<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></li>
				</c:if>
		
			</ul>
		</c:if>
		<c:if test="${prod.prodId==82}">
			<ul>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jiayongjinghuaqi/a (1).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jiayongjinghuaqi/a (2).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jiayongjinghuaqi/a (3).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jiayongjinghuaqi/a (4).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jiayongjinghuaqi/a (5).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jiayongjinghuaqi/a (6).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jiayongjinghuaqi/a (7).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jiayongjinghuaqi/a (8).jpg"></li>
				<c:if test="${code !=null}">
						<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></li>
				</c:if>
			</ul>
		</c:if>
		<c:if test="${prod.prodId==83}">
			<ul>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jiemianmosi/a1 (1).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jiemianmosi/a1 (2).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jiemianmosi/a1 (3).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jiemianmosi/a1 (4).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jiemianmosi/a1 (5).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jiemianmosi/a1 (6).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jiemianmosi/a1 (7).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jiemianmosi/a1 (8).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jiemianmosi/a1 (9).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jiemianmosi/a1 (10).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jiemianmosi/a1 (11).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jiemianmosi/a1 (12).jpg"></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jiemianmosi/a1 (13).jpg"></li>
				<c:if test="${code !=null}">
						<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></li>
				</c:if>		
			</ul>
		</c:if>
		<c:if test="${prod.prodId==91}">
			<ul>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (1).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (2).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (3).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (4).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (5).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (6).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (7).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (8).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (9).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (10).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (11).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (12).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (13).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (14).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (15).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (16).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (17).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (18).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (19).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (20).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (21).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (22).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (23).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (24).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (25).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (26).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (27).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (28).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (29).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (30).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (31).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (32).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (33).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1 (34).jpg"></li>	
				<c:if test="${code !=null}">
						<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></li>
				</c:if>
			</ul>
		</c:if>
		<c:if test="${prod.prodId==92}">
			<ul>
			    <li><h3 style="color: red">注:此产品不包括中药包，如有需要请在商城首页购买</h3></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/meixiongyi/a1 (1).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/meixiongyi/a1 (2).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/meixiongyi/a1 (3).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/meixiongyi/a1 (4.jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/meixiongyi/a1 (5).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/meixiongyi/a1 (6).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/meixiongyi/a1 (7).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/meixiongyi/a1 (8).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/meixiongyi/a1 (9).jpg"></li>	
				<c:if test="${code !=null}">
						<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></li>
				</c:if>
			</ul>
		</c:if>
		<c:if test="${prod.prodId==93}">
			<ul>
			    <li><h3 style="color: red">注:此产品不包括中药包，如有需要请在商城首页购买</h3></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/qinangzhen/a1 (1).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/qinangzhen/a1 (2).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/qinangzhen/a1 (3).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/qinangzhen/a1 (4).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/qinangzhen/a1 (5).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/qinangzhen/a1 (6).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/qinangzhen/a1 (7).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/qinangzhen/a1 (8).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/qinangzhen/a1 (9).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/qinangzhen/a1 (10).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/qinangzhen/a1 (11).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/qinangzhen/a1 (12).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/qinangzhen/a1 (13).jpg"></li>	

				<c:if test="${code !=null}">
						<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></li>
				</c:if>
			</ul>
		</c:if>
		<c:if test="${prod.prodId==94}">
			<ul>
			    <li><h3 style="color: red">注:此产品不包括中药包，如有需要请在商城首页购买</h3></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/yangshengyi/a1 (1).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/yangshengyi/a1 (2).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/yangshengyi/a1 (3).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/yangshengyi/a1 (4).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/yangshengyi/a1 (5).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/yangshengyi/a1 (6).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/yangshengyi/a1 (7).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/yangshengyi/a1 (8).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/yangshengyi/a1 (9).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/yangshengyi/a1 (10).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/yangshengyi/a1 (11).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/yangshengyi/a1 (12).jpg"></li>	
					
				<c:if test="${code !=null}">
						<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></li>
				</c:if>
			</ul>
		</c:if>
		<c:if test="${prod.prodId==95}">
			<ul>
			    <li><h3 style="color: red">注:此产品不包括中药包，如有需要请在商城首页购买</h3></li>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jianjingzhuililiaoyi/a1 (1).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jianjingzhuililiaoyi/a1 (2).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jianjingzhuililiaoyi/a1 (3).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jianjingzhuililiaoyi/a1 (4).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jianjingzhuililiaoyi/a1 (5).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jianjingzhuililiaoyi/a1 (6).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jianjingzhuililiaoyi/a1 (7).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jianjingzhuililiaoyi/a1 (8).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jianjingzhuililiaoyi/a1 (9).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jianjingzhuililiaoyi/a1 (10).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jianjingzhuililiaoyi/a1 (11).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jianjingzhuililiaoyi/a1 (12).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/jianjingzhuililiaoyi/a1 (13).jpg"></li>	

				<c:if test="${code !=null}">
						<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></li>
				</c:if>
			</ul>
		</c:if>
		<c:if test="${prod.prodId==96}">
			<ul>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/yaobao/a1 (1).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/yaobao/a1 (2).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/yaobao/a1 (3).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/yaobao/a1 (4).jpg"></li>	
	
				<c:if test="${code !=null}">
						<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></li>
				</c:if>
			</ul>
		</c:if>
		<c:if test="${prod.prodId==97}">
			<ul>
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/lengfengzuodian/a1 (1).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/lengfengzuodian/a1 (2).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/lengfengzuodian/a1 (3).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/lengfengzuodian/a1 (4).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/lengfengzuodian/a1 (5).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/lengfengzuodian/a1 (6).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/lengfengzuodian/a1 (7).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/lengfengzuodian/a1 (8).jpg"></li>	
				<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/miaosha/lengfengzuodian/a1 (9).jpg"></li>	

				<c:if test="${code !=null}">
						<li><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></li>
				</c:if>
			</ul>
		</c:if>
		</div>			
		</div>
		<div id="shouhou" class="shangpintu">
					<c:if test="${prod.manufacturer==3}">
		<ul>
			<li><h4 style="color: red">厂家售后电话:010-57732808，微信号：jlj968</h1></li>
			<li><h4 style="color: red">工作时间:9:00--17:30</h4></li>
		</ul>
		</c:if>
		
		<c:if test="${prod.prodId==4}">
		<ul>
			<li><h4 style="color: red">厂家售后电话:4006075315</h4></li>
			<li><h4 style="color: red">工作时间:8:00～11：30 13：30～18：00</h4></li>
		</ul>
		</c:if>
		
		<c:if test="${prod.prodId==5}">
		<ul>
			<li><h4 style="color: red">厂家售后电话:4006075315</h4></li>
			<li><h4 style="color: red">工作时间:8:00～11：30 13：30～18：00</h4></li>
		</ul>
		</c:if>
		<c:if test="${prod.prodId==29}">
			<ul>
				<li><h4 style="color: red">厂家售后电话:010-53341760</h4></li>
				<li><h4 style="color: red">工作时间:9:00--19:30</h4></li>
				</ul>
		</c:if>
		<c:if test="${prod.prodId==39}">
			<ul>
				<li><h4 style="color: red">厂家售后电话:010-53341760</h4></li>
				<li><h4 style="color: red">工作时间:9:00--19:30</h4></li>
			</ul>
		</c:if>
		<c:if test="${prod.manufacturer==9}">
			<ul>
				<li><h4 style="color: red">厂家售后电话:88223903</h1></li>
				<li><h4 style="color: red">工作时间:9:00--18:00</h4></li>
			</ul>
		</c:if>
		<c:if test="${prod.manufacturer==6}">
			<ul>
				<li><h4 style="color: red">厂家售后电话: 010一53341760 </h1></li>
				<li><h4 style="color: red">工作时间:9:00--18:00</h4></li>
			</ul>
		</c:if>
	</div>
				<div id="pingjia" class="shangpintu">
					<div id="em_all" class="assess">
					<c:if test="${statu==1}">
					<a id="liuyan" href="#inline" class="btnn" onclick="viewDialog();">发表评论</a>
					</c:if>
						<ul id="appendUl">
						<c:forEach var="c" items="${commentlist}" varStatus="comstatus">
							<li>
								<div class="assess-name">								
									<span><em>${c[8]}</em></span>
									<span><em>${c[3]}</em></span>
								</div>
								<div class="assess-con">
									<p><em>${c[2]}</em></p>
								</div>
							</li>
							</c:forEach>
						</ul>
						<button class="assess-btn" onclick="insertcode()"><span>点击加载更多</span><br /><img src="<%=request.getContextPath()%>/images/more.gif"/></button>
					</div>			
				</div>
			</div>
			<div class="panel panel-primary" id="inline" style="display: none;width:300px;height:320px;">
      			<div class="panel-heading">
        		<h3 class="panel-title">评论</h3>
     			 </div>
     			 <div class="panel-body">
      			<div>
      				<div class="form-group">
      			评论内容:<textarea class="form-control" rows="8" id="textMessage" style="height:175px;width:240px;"></textarea>
        			</div>
      			</div>
      			<div>
      			<input type="hidden" value="" id="wxOpenid" />
      			<a class="button button-pill button-primary" href="#" role="button" onclick="sendMessage();">发送</a>
        		<a class="button button-pill button-primary" href="#" role="button" onclick="closefancybox();">关闭</a>
      			</div>
        
      			</div>
   			 </div>
   			 <script type="text/javascript" src="<%=request.getContextPath()%>/css/source/jquery.fancybox.pack.js?v=2.1.5"></script>
			<script type="text/javascript">
			 wx.config({
				    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				    appId: "${appId}", // 必填，公众号的唯一标识
				    timestamp: '${timestamp}', // 必填，生成签名的时间戳
				    nonceStr: '${nonce_str}', // 必填，生成签名的随机串
				    signature: '${signature}',// 必填，签名，见附录1
				    jsApiList: ['menuItem:share:appMessage','onMenuShareAppMessage',
				                'menuItem:share:timeline','onMenuShareTimeline',
				                'menuItem:share:qq','onMenuShareQQ',
				                'menuItem:share:weiboApp','onMenuShareWeibo',
				                'menuItem:share:QZone','onMenuShareQZone'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
				});
			 
			  wx.ready(function(){
				    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
				 /* wx.checkJsApi({
					    jsApiList: ['menuItem:share:appMessage','onMenuShareAppMessage',
					                'menuItem:share:timeline','onMenuShareTimeline',
					                'menuItem:share:qq','onMenuShareQQ',
					                'menuItem:share:weiboApp','onMenuShareWeibo',
					                'menuItem:share:QZone','onMenuShareQZone'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
					    success: function(res) {
					        // 以键值对的形式返回，可用的api值true，不可用为false
					        // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
					    }
					});  */	
				  wx.onMenuShareTimeline({
					    title: '${prod.prodName}', // 分享标题
					    link: '${url}', // 分享链接
					    imgUrl: 'https://mmbiz.qlogo.cn/mmbiz/pKWIGyfSjUm56IyFxsNI5shwgexlbGhiaEOwrTI87P4ic8DE6xq2jVJYMmfO0ViaAla2PyveBsTxeNOvBCaueZibyg/0?wx_fmt=jpeg', // 分享图标
					    success: function () { 
					        // 用户确认分享后执行的回调函数
					    	alert("分享成功");
					    },
					    cancel: function () { 
					        // 用户取消分享后执行的回调函数
					    	alert("您取消了分享");
					    }
					});
				 wx.onMenuShareAppMessage({
					    title: '${prod.prodName}', // 分享标题
					    desc: '${prod.prodDescription}', // 分享描述
					    link: '${url}', // 分享链接
					    imgUrl: 'https://mmbiz.qlogo.cn/mmbiz/pKWIGyfSjUm56IyFxsNI5shwgexlbGhiaEOwrTI87P4ic8DE6xq2jVJYMmfO0ViaAla2PyveBsTxeNOvBCaueZibyg/0?wx_fmt=jpeg', // 分享图标
					    //type: '', // 分享类型,music、video或link，不填默认为link
					    //dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
					    success: function () { 
					        // 用户确认分享后执行的回调函数
					        alert("分享成功");
					    },
					    cancel: function () { 
					        // 用户取消分享后执行的回调函数
					    	alert("您取消了分享");
					    }
					});
				 wx.onMenuShareQQ({
					    title: '${prod.prodName}', // 分享标题
					    desc: '${prod.prodDescription}', // 分享描述
					    link: '${url}', // 分享链接
					    imgUrl: 'https://mmbiz.qlogo.cn/mmbiz/pKWIGyfSjUm56IyFxsNI5shwgexlbGhiaEOwrTI87P4ic8DE6xq2jVJYMmfO0ViaAla2PyveBsTxeNOvBCaueZibyg/0?wx_fmt=jpeg', // 分享图标
					    success: function () { 
					       // 用户确认分享后执行的回调函数
					    	alert("分享成功");
					    },
					    cancel: function () { 
					       // 用户取消分享后执行的回调函数
					    	alert("您取消了分享");
					    }
					});
				 wx.onMenuShareWeibo({
					    title: '${prod.prodName}', // 分享标题
					    desc: '${prod.prodDescription}', // 分享描述
					    link: '${url}', // 分享链接
					    imgUrl: 'https://mmbiz.qlogo.cn/mmbiz/pKWIGyfSjUm56IyFxsNI5shwgexlbGhiaEOwrTI87P4ic8DE6xq2jVJYMmfO0ViaAla2PyveBsTxeNOvBCaueZibyg/0?wx_fmt=jpeg', // 分享图标
					    success: function () { 
					       // 用户确认分享后执行的回调函数
					    	alert("分享成功");
					    },
					    cancel: function () { 
					        // 用户取消分享后执行的回调函数
					    	alert("您取消了分享");
					    }
					});
				 wx.onMenuShareQZone({
					    title: '${prod.prodName}', // 分享标题
					    desc: '${prod.prodDescription}', // 分享描述
					    link: '${url}', // 分享链接
					    imgUrl: 'https://mmbiz.qlogo.cn/mmbiz/pKWIGyfSjUm56IyFxsNI5shwgexlbGhiaEOwrTI87P4ic8DE6xq2jVJYMmfO0ViaAla2PyveBsTxeNOvBCaueZibyg/0?wx_fmt=jpeg', // 分享图标
					    success: function () { 
					       // 用户确认分享后执行的回调函数
					    	alert("分享成功");
					    },
					    cancel: function () { 
					        // 用户取消分享后执行的回调函数
					    	alert("您取消了分享");
					    }
					});
			 });
			  
			wx.error(function(res){
				    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
					
				    wx.hideOptionMenu();//被分享的页面无法再次分享
			});
			  
			
	var t;
	$(".banner img").hide();
	$(".banner img:first").show();
	var n = 0;
	var xx = 0;

	function showImg() {
		if (n < $(".banner img").length - 1) {
			n = n + 1;
		} else {
			n = 0;
		}
		//alert(n);
		$(".banner img").hide();
		$(".banner img").eq(n).fadeIn(1000);
		//$(".banner img").eq(n).show();			
	}
	var interval = setInterval(showImg, 4000);
	function shows(num) {
		if (xx != num) {
			showImg();
			xx = num;
			clearInterval(interval);
			interval = setInterval(showImg, 4000);
		}
	}
	function dianji(a){///newy/src/main/webappproduct1.jsp
		window.location.href="<%=request.getContextPath()%>/product"+a+".jsp";
	}
		function orderBuy(){
			$("#lijibuy").removeAttr("onclick");
			var str ='2016-03-10 02:00:00';
			str = str.replace(/-/g,"/");
			var end = '2016-04-12 08:00:00';
			end = end.replace(/-/g,"/");
			var date = new Date(str);
			var endDate = new Date(end);
			 //if("${user.userId}"!="1821")
			if(date < new Date() && new Date() < endDate){
				alert("系统升级维护中,请"+end+"以后再次操作!");
				$("#lijibuy").attr("onclick","orderBuy()");
				return false;
			}
			
			var num = $("#qty_item_1").val();
			var stock=$("#productKC").val()==""?"0":$("#productKC").val();
			var prodId=$("#checkProdId").val();
			var xhq_count=$("#diyongcount").val();
			xhq_count=xhq_count==""?"0":xhq_count;
			
			var inputamt = parseInt(parseInt(xhq_count) / 2);
			var totalamt = parseFloat($("#total_amt").val());
			if(num <=0){
				alert("至少购买一套!");
				$("#lijibuy").attr("onclick","orderBuy()");
				return false;
			}
			else if(parseInt("${prod.limitNum}")>0 && num>parseInt("${prod.limitNum}"))
			{
				alert("由于本产品倾销过快,防止厂家出现货源问题,所以最多只能购买${prod.limitNum}件,敬请谅解!");
				$("#lijibuy").attr("onclick","orderBuy()");
				return false;
			}
			else if(parseInt(num)>parseInt(stock))
				{
				  alert("库存不够");
				  $("#lijibuy").attr("onclick","orderBuy()");
				  return false;
				}
			else if(prodId=="")
				{
				  alert("请选择购买商品");
				  $("#lijibuy").attr("onclick","orderBuy()");
				  return false;
				}
			
			
			if(confirm("确定购买"+num+"套吗?")){
						$.ajax({
					        type:"POST",
					        url:"<%=request.getContextPath()%>/pay/payGoodAction!saveMianMoOrder.action",
					        data : {
					        	"qty_item_1":num,
					        	"orderAddRessId":"${orderAddress.id}",
					        	"prodId":prodId,
					        	 "xhq_count":xhq_count
					        	},
					        dataType:"json",
					        success:function(data) {
					       	 	if(data.success){
									//如果订单用火星券全部支付则不用调用微信的支付接口
								    if(data.need_pay)
								    {
									   moneyPay(data.ordersBh);
									 }
								    else 
								    {
								    	alert("订单保存成功");
								    	WeixinJSBridge.call('closeWindow'); 
								    }
					       	 	//alert(data.ordersBh);
					       	 	}else{
					       	 		if(data.timeOut){
					       	 			alert("用户已超时,请关闭网页重新进入!");
					       	 		}
					       	 		if(data.error){
					       	 			alert("参数错误!请重试");
					       	 		}
					       	 		if(data.overbuy)
					       	 			{
					       	 		$("#lijibuy").attr("onclick","orderBuy()");
					       	 			  alert("订购数量不能大于${prod.limitNum}");
					       	 			}
					       	 		else if(data.stockless)
					       	 			{
					       	 		$("#lijibuy").attr("onclick","orderBuy()");
					       	 			  alert("库存不够");
					       	 			}
					       	 		else if(data.xhq_nok)
					       	 			{
					       	 			  alert("星火券数量异常，请关闭网页重新进入");
					       	 			}
					       	 		else if(data.xhq_overbuy)
					       	 			{
					       	 			   alert("现在处于测试阶段, 一天只能用星火券购买两次");
					       	 			}
						       	 	$("#btn_submit").html("立即购买");
				       	 			$("#btn_submit").removeAttr('disabled');
					       	 		
					       	 	}
						 	},
						 	beforeSend : function() {
							 	$("#btn_submit").html("订单生成中!");
							 	$("#btn_submit").attr('disabled',"disabled");
							},
							error:function(){
								$("#btn_submit").html("立即购买");
			       	 			$("#btn_submit").removeAttr('disabled');
								alert("错误!");
							}
					 	});
			}
			$("#lijibuy").attr("onclick","orderBuy()");
		}
	
		//执行支付
   	 	function pay(appId, timeStamp, nonceStr, package1, signType, paySign) {
   	 		WeixinJSBridge.invoke('getBrandWCPayRequest', {
   	 		'appId' : appId, //公众号名称，由商户传入
   	 		'timeStamp' : timeStamp, //时间戳，自 1970 年以来的秒数
   	 		'nonceStr' : nonceStr, //随机串
   	 		'package' : package1,
   	 		'signType' : signType, //微信签名方式
   	 		'paySign' : paySign
   	 	//微信签名

   	 	}, function(res) {
   	 	$("#btn_submit").html("支付订单");
   	 	$("#btn_submit").removeAttr('disabled');
   	 	if(res.err_msg == "get_brand_wcpay_request:ok"){  
   	 		 WeixinJSBridge.call('closeWindow'); 
   	 	   }
   	 		else if(res.err_msg == "get_brand_wcpay_request:cancel"){  
   	 		   $("#btn_submit").html("支付订单");
   	 			$("#btn_submit").removeAttr('disabled');
//   	 	        alert("您取消了支付。");  
   	 		$("#lijibuy").attr("onclick","orderBuy()");
   	 	   }
   	 		else{  
   	 		   $("#btn_submit").html("支付订单");
   	 			$("#btn_submit").removeAttr('disabled');
//   	 	       alert("支付失败!");  
   	 	   }  
   	 	// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg 将在用户支付成功后返回 ok，但并不保证它绝对可靠。
   	 	});
   	 	}
   	 	function moneyPay(bh){
   	 		 $.ajax({
   	 		        type:"POST",
   	 		        url:"<%=request.getContextPath()%>/pay/payGoodAction!ajaxWxPay.action?m="+new Date(),
   	 		        data : {"orderNo":bh},
   	 		        success:function(data) {
   	 		       	 $("#btn_submit").html("支付中");
   	 		  			if(data!=null){
   	 		  				if(data.message=="success") {
   	 									pay(data.appId2,
   	 											data.timeStamp,
   	 											data.nonceStr2,
   	 											data.packages,
   	 											data.signType2,
   	 											data.paySign2);
   	 		  				}
   	 		        }
   	 			 },
   	 			 beforeSend : function() {
   	 				 $("#btn_submit").html("请求支付中");
   	 				 $("#btn_submit").attr('disabled',"disabled");
   	 				}
   	 		 });

   	 	}
	
		go = function () {
			self.location.href="<%=request.getContextPath()%>/orderAddress/orderAddressAction!orderAddress.action";
		}
		
		
		$(function(){
			// 判断是否有sub-menu
			if($("ul.sub-menu")){
				$(".menu-list>li>a").click(function(){
					var $sub_menu = $(this).next(".sub-menu"),
						sub_h = $sub_menu.find("li").length * 46;	//27px表示每个sub-menu 下面li的高度
					if($sub_menu.height() == 0){					//判断是否在可视区域
						$sub_menu.animate({"height":sub_h});		//添加动画
						$(this).find(".arrows").addClass("rotated");
					}else{
						$sub_menu.animate({"height":0});
						$(this).find(".arrows").removeClass("rotated");
					}
				})
			}else{
				return false;
			}
		})
	/* function onBridgeReady() {
		WeixinJSBridge.call('hideOptionMenu');
	}

	if (typeof WeixinJSBridge == "undefined") {
		if (document.addEventListener) {
			document.addEventListener('WeixinJSBridgeReady', onBridgeReady,
					false);
		} else if (document.attachEvent) {
			document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
			document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
		}
	} else {
		onBridgeReady();
	} */
	
	
	$(document).ready(function(){
		$("#shouhou,#pingjia").hide();
		 var price=parseFloat($("#prodprice").val());
	     var transFee=parseInt($("#total_yunfei").html());
	     var qty=$("#qty_item_1").val().trim()==""?1:parseInt($("#qty_item_1").val().trim());
	     var total_item_amt=parseFloat(parseFloat(price)*qty+transFee);
		 showAmt(qty,total_item_amt,transFee);
		
	});
	
	function checkProd(obj)
	{
		$(".xuanze").each(function(){
			$(".xuanze").removeClass("pro-sel");		
		});
		$("#xuanze_"+$(obj).attr("prodId")).addClass("pro-sel");
		var yunfei=$(obj).attr("transFee")==""?0:parseInt($(obj).attr("transFee"));
		var kuige=$(obj).attr("kuige");
		var qty=$("#qty_item_1").val().trim()==""?1:parseInt($("#qty_item_1").val().trim());
		var total_item_amt=parseFloat(parseFloat($(obj).attr("price"))+yunfei);
		showAmt2(qty,total_item_amt,yunfei,kuige);
		$("#checkProdId").val($(obj).attr("prodId"));
		$("#productKC").val($(obj).attr("stock"));
	}
	function showAmt2(qty,total_item_amt,transFee,kuige)
	{
		     $("#qty_item_1").val(1);
		     $("#qty_item_2").html(1);
		     $("#qty_item_3").html(kuige);
		     $("#total_prod_amt").html(total_item_amt);
			 $("#total_amt").val(total_item_amt+transFee);
			 $("#total_yunfei").html(transFee);
	}
	function showAmt(qty,total_item_amt,transFee,total_item_m)
	{
		     $("#qty_item_1").val(qty);
		     $("#qty_item_2").html(qty);
		     $("#total_prod_amt").html(total_item_amt);
		     $("#total_prod_m").html(total_item_m);
			 $("#total_amt").val(total_item_amt+transFee);
			 $("#total_yunfei").html(transFee);
	}
	//增加商品
	var typeqty=parseInt("${typeqty}");
	function add()
	{
		if(typeqty==1)
			{
			
			     var price=parseFloat($("#prodprice").val());
			     var transFee=parseInt($("#total_yunfei").html());
			     var qty=$("#qty_item_1").val().trim()==""?1:parseInt($("#qty_item_1").val().trim())+1;
			     var total_item_amt=parseFloat(parseFloat(price)*qty+transFee);
			     var total_prod_m=parseFloat(parseFloat(price)*qty);
				 showAmt(qty,total_item_amt,transFee,total_prod_m);
			}
		else 
			{
			  var prodId=$("#checkProdId").val();
			  if(prodId=="")
				  {
				    alert("请选择要购买的商品规格");
				  }
			  else
				  {
				       $(".xuanze").each(function(){
						if($(this).attr("prodId")==prodId)
							{
							 var price=parseFloat($(this).attr("price"));
							 var transFee=$(this).attr("transFee")==""?0:parseInt($(this).attr("transFee"));
							 var qty=$("#qty_item_1").val().trim()==""?1:parseInt($("#qty_item_1").val().trim())+1;
							 var total_item_amt=parseFloat(parseFloat(price)*qty+transFee);
							 var total_prod_m=parseFloat(parseFloat(price)*qty);
							 showAmt(qty,total_item_amt,transFee,total_prod_m);
							}
						
					});
				  }
			}
	}
	//减少商品
	function cut()
	{
		if(typeqty==1)
		{
		     var price=parseFloat($("#prodprice").val());
		     var transFee=parseInt($("#total_yunfei").html());
		     var qty=$("#qty_item_1").val().trim()==""?1:parseInt($("#qty_item_1").val().trim())-1;
		     if(qty<1){qty=1;}
		     var total_item_amt=parseFloat(parseFloat(price)*qty+transFee);
		     var total_prod_m=parseFloat(parseFloat(price)*qty);
		     showAmt(qty,total_item_amt,transFee,total_prod_m);
		}
		else 
			{
			 var prodId=$("#checkProdId").val();
			  if(prodId=="")
				  {
				    alert("请选择要购买的商品规格");
				  }
			  else
				  {
				       $(".xuanze").each(function(){
						if($(this).attr("prodId")==prodId)
							{
							 var price=parseFloat($(this).attr("price"));
							 var transFee=$(this).attr("transFee")==""?0:parseInt($(this).attr("transFee"));
							 var qty=$("#qty_item_1").val().trim()==""?1:parseInt($("#qty_item_1").val().trim())-1;
							 if(qty<1){qty=1;}
							 var total_item_amt=parseFloat(parseFloat(price)*qty+transFee);
							 var total_prod_m=parseFloat(parseFloat(price)*qty);
							 showAmt(qty,total_item_amt,transFee,total_prod_m);
							}
						
					});
				  }
			}
	}
	function inputqty()
	{
		var re = /^[0-9]+.?[0-9]*$/; 
		if (!re.test($("#qty_item_1").val().trim()))
			{
			$("#qty_item_1").val("1");
			}
		if(typeqty==1)
		{
		     var price=parseFloat($("#prodprice").val());
		     var transFee=parseInt($("#total_yunfei").html());
		     var qty=$("#qty_item_1").val().trim()==""?1:parseInt($("#qty_item_1").val().trim());
		     var total_item_amt=parseFloat(parseFloat(price)*qty+transFee);
			 showAmt(qty,total_item_amt,transFee);
		}
	else 
		{
		  var prodId=$("#checkProdId").val();
		  if(prodId=="")
			  {
			    alert("请选择要购买的商品规格");
			  }
		  else
			  {
			       $(".xuanze").each(function(){
					if($(this).attr("prodId")==prodId)
						{
						 var price=parseFloat($(this).attr("price"));
						 var transFee=$(this).attr("transFee")==""?0:parseInt($(this).attr("transFee"));
						 var qty=$("#qty_item_1").val().trim()==""?1:parseInt($("#qty_item_1").val().trim());
						 var total_item_amt=parseFloat(parseFloat(price)*qty+transFee);
						 showAmt(qty,total_item_amt,transFee);
						}
					
				});
			  }
		}
	}
	
		function checkDiYong(obj) {
			if ($(obj).prop("checked")) {
				$("#diyiong_info").show();
			} else {
				$("#diyiong_info").hide();
				$("#final_diyongamt").val("0");
				$("#diyongcount").val("0");
			}
		}

		
		
	
		function inputDiYongAmt(obj) {
			var dycount = $(obj).val().trim();
			var max_xhq_count =$("#max_xhq_count").html().trim();
			max_xhq_count = max_xhq_count == "" ? "0" : max_xhq_count;
			var re = /^[0-9]+.?[0-9]*$/;
			if (!re.test(dycount)) {
				$(obj).val("0");
			} else {
				if (parseInt(dycount) > parseInt(max_xhq_count)) {
					$(obj).val(max_xhq_count);
					dycount = max_xhq_count;
				}
				var transFee=parseInt($("#total_yunfei").html()==""?"0":$("#total_yunfei").html());
				var inputamt = parseFloat(parseInt(dycount) / 2);
				var prodamt = parseFloat($("#total_prod_amt").html());
				if (inputamt > prodamt+transFee) {
					$("#final_diyongamt").val(prodamt+transFee);
					$(obj).val(prodamt* 2);
				} else {
					$("#final_diyongamt").val(inputamt);
				}

			}
		}
		$("#shouhouB").click(function() {
			t=false;
			$("#shouhou").show();
			$("#pingjia,#miaoshu").hide();
			$(".tuwen li").removeClass("dangqian");
			$("#shouhouB").addClass("dangqian");
		});
		$("#miaoshuB").click(function() {
			t=false;
			$("#miaoshu").show();
			$("#pingjia,#shouhou").hide();
			$(".tuwen li").removeClass("dangqian");
			$("#miaoshuB").addClass("dangqian");
		});
		$("#pinglunB").click(function() {
			t=true;
			$("#pingjia").show();
			$("#miaoshu,#shouhou").hide();
			$(".tuwen li").removeClass("dangqian");
			$("#pinglunB").addClass("dangqian");
			if(pageNum==0)
				insertcode();
		});
		function viewDialog() {
			 $("#liuyan").fancybox({
			        'hideOnContentClick': true
			 });
		}
		function closefancybox() {
			jQuery.fancybox.close();
			$('#textMessage').val("");
		}
		function sendMessage() {
			var textMessage = $('#textMessage').val();
			var prodId = ${prod.prodId};
			if (textMessage=="") {
				alert("评论内容不能为空");
				closefancybox();
				return false;
			}
			if(textMessage.length<15){
				alert("评论长度不能少于15字");
				closefancybox();
				return false;
				
			}
			if(textMessage.length>200){
				alert("评论长度不能超过200字");
				closefancybox();
				return false;
			}
			var url ='<%=request.getContextPath()%>/comment/commentAction!saveCommet.action';
			$.ajax({
				cache: true,
				type: "POST",
				url:url,
				data:"prodId="+prodId+"&textMessage="+textMessage,// 你的formid
				async: false,
			    error: function(request) {
			        alert("网络出现问题稍等再试!!!");
			    },
			    success: function(data) {
			    	if(data){
			    		alert("评论成功");
			    		$('#textMessage').val("");
			    		jQuery.fancybox.close();
			    		window.location.href="<%=request.getContextPath()%>/pay/payGoodAction!toMianMoBuy.action?prodId=${prod.prodId}";
			    	}
			    }
			});
		}
		 
		 Date.prototype.Format = function (fmt) { //author: meizz 
			    var o = {
			        "M+": this.getMonth() + 1, //月份 
			        "d+": this.getDate(), //日 
			        "h+": this.getHours(), //小时 
			        "m+": this.getMinutes(), //分 
			        "s+": this.getSeconds(), //秒 
			        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
			        "S": this.getMilliseconds() //毫秒 
			    };
			    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
			    for (var k in o)
			    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
			    return fmt;
			}
		 
		 var pageNum=0;
		 function insertcode(){
			    /*加载内容*/
				$.ajax({
					type: "POST",
					url:"<%=request.getContextPath()%>/comment/commentAction!ajaxcommentList.action",
					data:{
						"prodId":'${prod.prodId}',
						"pageNum":pageNum
					},
					async: false,
				    error: function(request) {
				        alert("网络出现问题稍等再试!!!");
				    },
				    dataType:"json",
				    success: function(data) {
				    	if (data.success==true) {
				    		var children = data.children;
				    		var child = '';
				    		for(var i=0;i<children.length;i++){
				    			var time= new Date(children[i].createtime.time).Format("yyyy-MM-dd hh:mm:ss");
				    			child+=('<li>');
				    			child+=('<div class="assess-name">');
				    			child+=('<span><em>'+children[i].userName+'</em></span>');
								child+=('<span><em>'+time+'</em></span></div>');
								child+=('<div class="assess-con">');
							    child+=('<p><em>'+children[i].comment+'</em></p></div>');
				    			child+=('</li>');
				    		}
				    		var ul = $("#appendUl");
				    		ul.append(child);				    		
				    	}else{
				    		length = data.length;
				    		alert("已经加载完毕!");
				    	}
				    }
				});
				pageNum++;
			}
		 
	</script>
	</body>
</html>