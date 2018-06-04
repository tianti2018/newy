<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="zh-CN"><head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>山人物语商城</title>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="x-dns-prefetch-control" content="on">   
    <meta http-equiv="x-dns-prefetch-control" content="on">
    <!--S 线上样式-->
    <link href="<%=request.getContextPath()%>/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/base.s.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/css/mod_nav_foot.s.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/css/wx_bar_v2.s.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/css/wx_bar_nian.s.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/css/wallet_v2.s.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/css/wallet_v2_guide.s.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/css/footer.css" rel="stylesheet" />
    
	<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
    <!--E 线上样式-->


    <style type="text/css">
		.wet_app_panel{min-height: 40px;}
        .wet_mat_panel{min-height: 100px;}
        .WX_backtop_active {display: block;bottom:60px;}
    </style>
</head>
<body>

<!-- 头部s -->

<div id="topsearchbar" class="wx_search wx_search_chanel_mode wx_search_nian wx_search_promote_ex">
	  <input type="hidden" id="atCity">
      <div class="wx_search_inner">
        <div class="wx_bar_cate"><a href="<%=request.getContextPath()%>/city/cityAction!cities.action" class="city_btn">
         <c:choose>
          <c:when test="${not empty city.shortname}">
          	<span id="cityNames"></span>
          </c:when>
          <c:otherwise> 
          	<span id="cityNames"></span>
          </c:otherwise>
         </c:choose>
       
        </a></div>
        <form action="" method="get" class="wx_search_form" onsubmit="return false;">
            <input name="key" class="wx_search_txt" placeholder="" type="search" id="topSearchTxt" autocomplete="off">
        </form>
    </div>

</div>

	<!-- 头部e -->


	<!-- 幻灯片s -->

	<div style="height: 45px;"></div>

	<div class="banner">
		<div class="flexslider">
			<ul class="slides">
				<c:forEach var="product" items="${products_lunbo}" varStatus="vstatus">
					<li onclick="javascript:chooseUrl(5,${product.productsId});"><img style="height: 400px" src="${product.headUrl}" /></li>
				</c:forEach>
			</ul>
		</div>
	</div>


<!-- zhang_wanqiang 城市定位-->
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=cVZOceoa2o3Vl3LOxAYKsKNPU1avOE4w"></script>
<div id="bdMapBox" style="display:none;"></div>
<script>



var msg="${requestScope.tipMessage}";
if(msg!=""){
	alert(msg);
}

/**
 * 发送给朋友: "menuItem:share:appMessage"
	 分享到朋友圈: "menuItem:share:timeline"
 */
$(function(){
	shwoCity();
	/* $(".quick-entry-link").each(function(){
		$(this).bind("click",function(){
			zanwu();
		})
	}); */
  $(".flexslider").flexslider({
	  animation: "slide",
	  directionNav: false,
	  touch: true
  });
});

function chooseUrl(type,prodId){
	 /*  var user = '${loginUser}';
	  if(user == null || user == "" || user == '' || user == " "){
		  alert("请刷新页面后再试哦！");
		  return false;
	  } */
	  if(type==1){
		  window.location.href="<%=request.getContextPath()%>/user/userAction!queryLiuBuy.action";
	  }else if(type==2){
		  window.location.href="<%=request.getContextPath()%>/companyInfo/companyInfoAction!editPage.action";
	  }else if(type==3){
		  window.location.href="<%=request.getContextPath()%>/user/userAction!queryLiuBuy.action";
	  }else if(type==4){
		  window.location.href="<%=request.getContextPath()%>/pay/payGoodAction!toGoodBuy.action";
	  }else if(type==5){
		  window.location.href="<%=request.getContextPath()%>/products/productsAction!product.action?prodId="+prodId;
	  }else if(type==6){
		  window.location.href="http://mp.weixin.qq.com/s?__biz=MzAwMzc2MDA1MA==&mid=554343834&idx=1&sn=1246afe1c8443c0d855bc44fa3a680e3#rd";
	  }
	  
 }
 

/**
 * 显示城市名称  zhangwanqiang 
 */
function shwoCity(){
	var cityName = $("#cityNames");
	// 城市定位,把id放到users表中city字段
	var map = new BMap.Map("bdMapBox");
	var nowCity = new BMap.LocalCity();
	nowCity.get(bdGetPosition);
	function bdGetPosition(result){
		var cityNamee = result.name; //当前的城市名
		cityNames = cityNamee.substr(0, 2);
		//自定义代码
		cityName.html(cityNames);
		atCity.innerHTML = cityNames;
	}
} 
</script>
	<!-- 幻灯片e -->


	<!-- 领券s -->

	<div class="wet_space size_s top"></div>

	<nav class="quick-entry-nav">
		<a class="quick-entry-link"
			href="<%=request.getContextPath()%>/user/userAction!gotoHuiyuanShop.action"><img
			width="45" height="45"
			src="<%=request.getContextPath()%>/images/nav/4.png"><span>会员专区</span></a>
		<a class="quick-entry-link"
			href="<%=request.getContextPath()%>/user/userAction!gotoJifenShop.action"><img
			width="45" height="45"
			src="<%=request.getContextPath()%>/images/nav/7.png"><span>微积分商城</span></a>
		<a class="quick-entry-link" href="javascript:chooseUrl(1,0);"><img
			width="45" height="45"
			src="<%=request.getContextPath()%>/images/nav/1.png"><span>流量商城</span></a>
		<a class="quick-entry-link"
			href="<%=request.getContextPath()%>/companyInfo/companyInfoAction!editPage.action"><img
			width="45" height="45"
			src="<%=request.getContextPath()%>/images/nav/2.png"><span>厂家入驻</span></a>
		<%-- <a class="quick-entry-link" href="javascript:zanwu();"><img width="45" height="45" src="<%=request.getContextPath()%>/images/nav/5.png"><span>电话卡主</span></a>
            <a class="quick-entry-link" href="<%=request.getContextPath()%>/user/userAction!buy.action"><img width="45" height="45" src="<%=request.getContextPath()%>/images/nav/1.png"><span>VIP专区</span></a>
            <a class="quick-entry-link" href="javascript:zanwu();"><img width="45" height="45" src="<%=request.getContextPath()%>/images/nav/3.png"><span>店铺专区</span></a>
            <a class="quick-entry-link" href="javascript:zanwu();"><img width="45" height="45" src="<%=request.getContextPath()%>/images/nav/6.png"><span>秒杀专区</span></a> --%>
		<%-- <a class="quick-entry-link" href="<%=request.getContextPath()%>/user/ivmcAction!ivmCountryList.action"><img width="45" height="45" src="<%=request.getContextPath()%>/images/nav/6.png"><span>我爱家乡</span></a> --%>
	</nav>


	<div class="wet_space size_s top"></div>

	<div class="ind-not">
		<h2>☆ 公告 ☆</h2>
		<a
			href="<%=request.getContextPath()%>/notice/noticeAction!noticeList.action"><span
			id="noticeUl">最新公告最新公告最新公告</span></a>
	</div>

	<div class="wet_space size_s top"></div>
	<!-- 领券e -->
	<!-- 特价s -->

	<!-- 特价s -->
	<div class="wet_bnr " id="topTonglan">
		<c:forEach items="${products_dankuan}" var="product" varStatus="status">
			<div class="item">
				<a href="javascript:chooseUrl(5,${product.productsId });" class="url">
					<img style="height: 300px" src="${product.headUrl }" alt="">
				</a>
			</div>
			<div class="wet_space size_s btm"></div>
		</c:forEach>
	</div>

		<div class="wet_space"></div>

		<!-- 猜你喜欢s -->

		<div class="wet_section" id="cnxhShow">
			<div class="section_hd">
				<h3>商品列表</h3>
				<div class="desc">
					<span class="time">23:58已更新</span>
				</div>
			</div>
			<div class="wet_pro_panel wet_pro_v2_panel">

				<div class="wet_pro_list">
					<c:forEach var="product" items="${products_sanlie }" varStatus="status">
						<div class="item">
							<a href="javascript:chooseUrl(5,${product.productsId });" class="url">
								<div class="img">
									<img alt="" src="${product.headUrl }" loaded="117">
								</div>
							</a>
							<div class="info">
								<div class="name" style="color: red;">${product.name}</div>
								<div class="cost">
									<span class="price">¥${product.price }</span>
									<!-- <del>¥399.00</del> -->
									<!-- <div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div> -->
								</div>
							</div>
						</div>
					</c:forEach>

				<%-- <div class="item">
			          	<a href="javascript:chooseUrl(5,91);" class="url" >
			            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1.jpg" loaded="117"></div>
			            </a>
			            <div class="info">
			              <div class="name" style="color: red;">维美七氨基酸经典洗护三件套</div>
			              <div class="cost"><span class="price">¥199.00</span><del>¥399.00</del><!-- <div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div> --></div>
			            </div>
			          </div> --%>
				</div>
				<!-- /list -->
				<%-- <c:if test="${code !=null}">
					<div>
						<img style="width: 100%" alt=""
							src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" />
					</div>
				</c:if> --%>
				<div class="wet_pro_list_empty">
					<span>看完啦，待会儿再来~</span>
				</div>
			</div>
			<!-- /panel -->
		</div>
		<!-- 猜你喜欢e -->

		<a id="backToTop" href="#" class="WX_backtop WX_backtop_active"
			style="bottom: 60px;">返回顶部</a>


		<%@ include file="/WEB-INF/jsp/userLogin/login.jsp"%>
		<!-- 底部s -->
		<%@ include file="/WEB-INF/jsp/footer.jsp"%>
		<!-- 底部e -->
</body>
</html>