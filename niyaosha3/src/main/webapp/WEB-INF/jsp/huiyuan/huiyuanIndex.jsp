<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>会员专区</title>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="x-dns-prefetch-control" content="on">   
    <meta http-equiv="x-dns-prefetch-control" content="on">
    <!--S 线上样式-->
    <link href="<%=request.getContextPath()%>/css/huiyuanzhuanqu/vip.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/css/huiyuanzhuanqu/footer.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/huiyuanzhuanqu/flexslider.css"/>
    <script src="<%=request.getContextPath()%>/js/huiyuanzhuanqu/jquery-1.8.2.min.js" type="text/javascript" charset="utf-8"></script>
    <!--E 线上样式-->

</head>
<body>
<div class="wx_wrap">
	<div id="wrapper" data-tag="wrapper">
		<div class="vip-header">
			<ul>
				<li class="logo">
					<img src="<%=request.getContextPath()%>/images/huiyuanzhuanqu/img/logo.png"/>
					<p class="logoWord">
						<span>山人物语</span>
					</p>
				</li>
				<li class="vipT"><a href="#">会员专区</a></li>
				<li><a href="#" onclick="javascript:dianpuzhuanqu()">店铺专区</a></li>
			</ul>
		</div>
		<div style="height:2px;"></div>
        <div class="banner">
      		<div class="flexslider"> 
        			<ul class="slides"> 
	          			<li onclick="javascript:chooseUrl(1,1);"><img src="<%=request.getContextPath()%>/images/huiyuanzhuanqu/img/ban11.jpg" /></li>
	          			<li onclick="javascript:chooseUrl(1,2);"><img src="<%=request.getContextPath()%>/images/huiyuanzhuanqu/img/ban12.jpg" /></li>
	          			<li onclick="javascript:chooseUrl(1,3);"><img src="<%=request.getContextPath()%>/images/huiyuanzhuanqu/img/ban13.jpg" /></li>
	          			<li><a href="<%=request.getContextPath()%>/pay/payHuiYuanAction!toHuiYuanBuy.action?prodId=48"><img src="<%=request.getContextPath()%>/images/huiyuanzhuanqu/img/banna2.jpg" /></a></li>
        			</ul> 
      		</div>
    	</div>
    		<script type="text/javascript" src="<%=request.getContextPath()%>/js/huiyuanzhuanqu/jquery.flexslider-min.js"></script>
		<script>
			$(function(){
  				$(".flexslider").flexslider({
	  				animation: "slide",
	  				directionNav: false,
	  				touch: true
  				});
  
			});
			function zanwu(){
				alert("开发中......");
			}
			function dianpuzhuanqu(){
				alert("目前正在规划中，暂不开放...");
			}
		  
			function chooseUrl(type,prodId){
				  if(type==1){
					  window.location.href="<%=request.getContextPath()%>/product"+type+prodId+".jsp";
				  }else if(type==2){
					  window.location.href="<%=request.getContextPath()%>/companyInfo/companyInfoAction!editPage.action";
				  }else if(type==3){
					  window.location.href="<%=request.getContextPath()%>/user/userAction!queryLiuBuy.action";
				  }else if(type==4){
					  window.location.href="<%=request.getContextPath()%>/pay/payHuiYuanAction!toGoodBuy.action";
				  }else if(type==5){
					  window.location.href="<%=request.getContextPath()%>/pay/payHuiYuanAction!toMianMoBuy.action?prodId="+prodId;
				  }
			 }
			
		</script>
		<div style="height:10px"></div>
		<div class="vip-title">
			<span class="title-line"></span>
			<span class="title">会员专区</span>
			<span class="title-line"></span>
		</div>
		<div class="bigPro">
				<a href="<%=request.getContextPath()%>/pay/payHuiYuanAction!toHuiYuanBuy.action?prodId=48">
					<ul class="big">
						<li>多维30片*6瓶/+铁锌钙30片*12瓶/+松花蛋白粉*2罐/<span>市场价1268元</span></li>
						<li><img src="<%=request.getContextPath()%>/images/huiyuanzhuanqu/img/product/otherPro-1.jpg"/></li>
						<li>商城价: 684元+30元运费</li>
					</ul>
				</a>
		</div>
			<div class="otherR">
				<ul>
					<li>
						<a href="<%=request.getContextPath()%>/pay/payHuiYuanAction!toHuiYuanBuy.action?prodId=45">
							<div class="other-right-img"><img src="<%=request.getContextPath()%>/images/huiyuanzhuanqu/img/product/otherPro-2.jpg"/></div>
							<div class="other-right-shm"><span>松花蛋白粉*2罐/<span>市场价596元</span></span></div>
							<div class="other-right-money"><span>商城价: 338元+10元运费</span></div>
						</a>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/pay/payHuiYuanAction!toHuiYuanBuy.action?prodId=44">
							<div class="other-right-img"><img src="<%=request.getContextPath()%>/images/huiyuanzhuanqu/img/product/otherPro-3.jpg"/></div>
							<div class="other-right-shm"><span>铁锌钙30片*12瓶 /<span>市场价456元</span></span></div>
							<div class="other-right-money"><span>商城价: 228元+10元运费</span></div>
						</a>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/pay/payHuiYuanAction!toHuiYuanBuy.action?prodId=47">
							<div class="other-right-img" ><img src="<%=request.getContextPath()%>/images/huiyuanzhuanqu/img/product/otherPro-4.jpg"/></div>
							<div class="other-right-shm"><span>花蛋白粉*2罐/提+铁锌钙30片*12瓶<span>市场价1052元</span></span></div>
							<div class="other-right-money"><span>商城价: 566元+20元运费</span></div>
						</a>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/pay/payHuiYuanAction!toHuiYuanBuy.action?prodId=46">
							<div class="other-right-img" ><img src="<%=request.getContextPath()%>/images/huiyuanzhuanqu/img/product/product-3.jpg"/></div>
							<div class="other-right-shm"><Span></Span>多维片30片*6瓶+铁锌钙30片*12瓶/<span>市场价672元</span></span></div>
							<div class="other-right-money"><span>商城价: 346元+20元运费</span></div>
						</a>
					</li>
				</ul>
			</div>
		
		<div style="height:10px"></div>
		<div class="vip-title">
			<span class="title-line"></span>
			<span class="title">其他专区</span>
			<span class="title-line"></span>
		</div>
		<div style="height:10px"></div>
		<div class="otherCon">
			<div class="vip-product-list">
			<a href="<%=request.getContextPath()%>/pay/payHuiYuanAction!toHuiYuanBuy.action?prodId=48">
			<ul class="proCol-1">
				<li ><img src="<%=request.getContextPath()%>/images/huiyuanzhuanqu/img/product/product-1.jpg"/></li>
				<li><span>多维30片*60瓶/十铁锌钙30片*12瓶/十松花蛋白粉*2罐/<span>市场价1268元</span></span></li>
				<li><span  >商城价：684元+30元运费</span></li>
			</ul>
			</a>
			<a href="<%=request.getContextPath()%>/pay/payHuiYuanAction!toHuiYuanBuy.action?prodId=47">
			<ul class="proCol-2">
				<li  ><img src="<%=request.getContextPath()%>/images/huiyuanzhuanqu/img/product/product-2.jpg"/></li>
				<li><span>松花蛋白粉*2罐/提十铁锌钙30片*12瓶/<span>市场价1052元</span></span></li>
				<li><span  >商城价：566元+20元运费</span></li>
			</ul>
			</a>
			<a href="<%=request.getContextPath()%>/pay/payHuiYuanAction!toHuiYuanBuy.action?prodId=46">
			<ul class="proCol-3">
				<li  ><img src="<%=request.getContextPath()%>/images/huiyuanzhuanqu/img/product/product-3(1).jpg"/></li>
				<li><span>多维片30片*6瓶/十铁锌钙30片*12瓶/<span>市场价672元</span></span></li>
				<li><span  >商城价：346元+20元运费</span></li>
			</ul>
			</a>
			<a href="<%=request.getContextPath()%>/pay/payHuiYuanAction!toHuiYuanBuy.action?prodId=45">
			<ul class="proCol-4">
				<li  ><img src="<%=request.getContextPath()%>/images/huiyuanzhuanqu/img/product/product-4.jpg"/></li>
				<li><span>松花蛋白粉*2罐/<span>市场价596元</span></span></li>
				<li><span  >商城价：338元+10元运费</span></li>
			</ul>
			</a>
			<a href="<%=request.getContextPath()%>/pay/payHuiYuanAction!toHuiYuanBuy.action?prodId=44">
			<ul class="proCol-5">
				<li  ><img src="<%=request.getContextPath()%>/images/huiyuanzhuanqu/img/product/product-5.jpg"/></li>
				<li><span>铁锌钙30片*12瓶/<span>市场价456元</span></span></li>
				<li><span  >商城价：288元+10元运费</span></li>
			</ul>
			</a>
			<a href="<%=request.getContextPath()%>/pay/payHuiYuanAction!toHuiYuanBuy.action?prodId=43">
			<ul class="proCol-6">
				<li  ><img src="<%=request.getContextPath()%>/images/huiyuanzhuanqu/img/product/product-6.jpg"/></li>
				<li><span>多维片30片*6瓶/<span>市场价216元</span></span></li>
				<li><span  >商城价：118元+10元运费</span></li>
			</ul>
			</a>
		</div>
			<div style="height: 10px; overflow: hidden;"></div>
			<div class="otherBot">
				<ul>
					<li>
						<a href="<%=request.getContextPath()%>/pay/payHuiYuanAction!toHuiYuanBuy.action?prodId=43">
							<div class="other-bottom-img"  ><img src="<%=request.getContextPath()%>/images/huiyuanzhuanqu/img/product/otherPro-6.jpg"/> </div>
							<div class="other-bottom-shm">多维片30片*6瓶/<span>市场价216元</span></span></div>
							<div class="other-bottom-money"><span>商城价: 118元+10运费</span></div>
						</a>
					</li> 
					<li>
						<a href="<%=request.getContextPath()%>/pay/payHuiYuanAction!toHuiYuanBuy.action?prodId=44">
							<div class="other-bottom-img"  ><img src="<%=request.getContextPath()%>/images/huiyuanzhuanqu/img/product/otherPro-8.jpg"/> </div>
							<div class="other-bottom-shm">铁锌钙30片*12瓶 /<span>市场价456元</span></span></div>
							<div class="other-bottom-money"><span>商城价: 228元+10运费</span></div>
						</a>
					</li> 
					<li>
						<a href="<%=request.getContextPath()%>/pay/payHuiYuanAction!toHuiYuanBuy.action?prodId=45">
							<div class="other-bottom-img"  ><img src="<%=request.getContextPath()%>/images/huiyuanzhuanqu/img/product/otherPro-7.jpg"/> </div>
							<div class="other-bottom-shm">松花蛋白粉*2罐/<span>市场价596元</span></span></div>
							<div class="other-bottom-money"><span>商城价: 338元+10运费</span></div>
						</a>
					</li> 
				</ul>
			</div>
		</div>
		<div style="height: 20px; overflow: hidden;"></div>
		<!-- <div class="bottomLink">
			<a href="#">更多好礼 ➤➤➤</a>
		</div> -->
		<div class="bottomWord">
			<p>温馨提示：本产品将会累计消费记录，当您累计消费到达一定额度以后，将会成为我们商城的VIP，并且享受一切VIP特权。商城购物，首选山人物语微信商城。</div>
		</div>
	</div>
</div>
<!-- 底部s -->
<div id="footer">
  <div class="foot_nav">
    <div class="foot_nav_inner">
    <a href="<%=request.getContextPath()%>/user/userAction!phoneFamily.action"><span class="line"><i class="nav_f1"></i></span><span class="text">我的红包</span></a>
    <a href="<%=request.getContextPath()%>/user/userAction!jampshopIndex.action"><span class="line"><i class="nav_f2"></i></span><span class="text">商城首页</span></a>
    <a href="<%=request.getContextPath()%>/user/userAction!qrcodePage.action"><span class="line"><i class="nav_f3"></i></span><span class="text">二维码</span></a>
    <a href="javascript:zanwu();"><span class="line"><i class="nav_f4"></i></span><span class="text">购物车</span></a>
    <a href="<%=request.getContextPath()%>/user/userAction!gotoPersonalCenter.action"><span class="line"><i class="nav_f5"></i></span><span class="text">个人中心</span></a>
    </div>
  </div>
</div>
<!-- 底部e -->

</body>
</html>