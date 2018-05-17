<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<!doctype html>
<html lang="en"><head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>微积分商城</title>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="x-dns-prefetch-control" content="on">   
    <meta http-equiv="x-dns-prefetch-control" content="on">
    <!--S 线上样式-->
    <link href="<%=request.getContextPath()%>/css/jifencss.css" rel="stylesheet" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
    <!--E 线上样式-->

</head>

<body>
<div id="wrap">

<!-- 头部s -->

<div id="header">
  <div class="logo"><img src="<%=request.getContextPath()%>/images/logo.png" /></div>
  <div class="tsear"><form><input type="text" class="input" placeholder="请输入商品名称"><input type="submit" value="搜索" class="btn"></form></div>
</div>

<!-- 头部e -->


<!-- 中部s -->
<div id="main">

  <div class="itnav">
    <ul>
      <li><a href="<%=request.getContextPath()%>/jifenIndex.jsp" class="hover">花积分</a></li>
      <li><a href="javascript:zanwu();">赚积分</a></li>
      <li><a href="javascript:zanwu();">兑积分</a></li>
      <li><a href="<%=request.getContextPath()%>/jifen/jifenAction!myJiFenList.action">积分查询</a></li>
    </ul>
    <div class="clear"></div>
  </div>
    
  <link href="<%=request.getContextPath()%>/css/flexslider.css" rel="stylesheet" />
  <div id="banner">
    <div class="flexslider"> 
      <ul class="slides"> 
        <li><a href="<%=request.getContextPath()%>/pay/payGoodAction!toJifenBuy.action?prodId=89"><img src="<%=request.getContextPath()%>/images/jifenImg/DKThuliye/banna.jpg" /></a></li>
        <li><a href="<%=request.getContextPath()%>/pay/payGoodAction!toJifenBuy.action?prodId=90"><img src="<%=request.getContextPath()%>/images/jifenImg/DKTningjiao/banna.jpg" /></a></li>
        <li onclick="javascript:chooseUrl(1,1);"><img src="<%=request.getContextPath()%>/images/jifenImg/ban1.jpg" /></li>
        <li onclick="javascript:chooseUrl(1,2);"><img src="<%=request.getContextPath()%>/images/jifenImg/ban2.jpg" /></li>
        <li onclick="javascript:chooseUrl(1,3);"><img src="<%=request.getContextPath()%>/images/jifenImg/ban3.jpg" /></li>
        <li><a href="<%=request.getContextPath()%>/pay/payGoodAction!toJifenBuy.action?prodId=84"><img src="<%=request.getContextPath()%>/images/jifenImg/pisha/banna.jpg" /></a></li>      
      </ul> 
    </div>
  </div>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.flexslider-min.js"></script>
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
  function qidai(){
		alert("星火微积分兑换活动将于4.1号正式开启!");
	}
  
  function chooseUrl(type,prodId){
		  if(type==1){
			  window.location.href="<%=request.getContextPath()%>/product"+type+prodId+".jsp";
		  }else if(type==2){
			  window.location.href="<%=request.getContextPath()%>/companyInfo/companyInfoAction!editPage.action";
		  }else if(type==3){
			  window.location.href="<%=request.getContextPath()%>/user/userAction!queryLiuBuy.action";
		  }else if(type==4){
			  window.location.href="<%=request.getContextPath()%>/pay/payGoodAction!toGoodBuy.action";
		  }else if(type==5){
			  window.location.href="<%=request.getContextPath()%>/pay/payGoodAction!toMianMoBuy.action?prodId="+prodId;
		  }
	 }
  </script>
  
  <div class="itit">限量微积分商品</div>
  
   <div class="ilist3">
    <ul>
      <li><a href="<%=request.getContextPath()%>/pay/payGoodAction!toJifenBuy.action?prodId=84"><div class="tm"><p class="t">花花公子百搭披纱/</p><p class="t2">市场价147元</p></div><p class="img"><img src="<%=request.getContextPath()%>/images/jifenImg/pisha/a1.jpg" /></p><p class="price">商城价: 99积分+20运费</p><i class="buy">兑换</i></a></li>
      <li><a href="<%=request.getContextPath()%>/pay/payGoodAction!toJifenBuy.action?prodId=89"><div class="tm"><p class="t">DKT女性护理液-调节型/</p><p class="t2">市场价116元</p></div><p class="img"><img src="<%=request.getContextPath()%>/images/jifenImg/DKThuliye/a1.jpg" /></p><p class="price">商城价: 150积分+30运费</p><i class="buy">兑换</i></a></li>
      <li><a href="<%=request.getContextPath()%>/pay/payGoodAction!toJifenBuy.action?prodId=90"><div class="tm"><p class="t">DKT女性护理凝胶-修复型/</p><p class="t2">市场价199元</p></div><p class="img"><img src="<%=request.getContextPath()%>/images/jifenImg/DKTningjiao/a1.jpg" /></p><p class="price">商城价: 150积分+30运费</p><i class="buy">兑换</i></a></li>
    </ul>
    <div class="clear"></div>
  </div>
  <div class="itit">微积分商品</div>
  <div class="ilist1">
    <ul>
      <%-- <li><a href="<%=request.getContextPath()%>/pay/payGoodAction!toJifenBuy.action?prodId=84"><div class="tm"><p class="t">花花公子披纱/</p><p class="t2">市场价147元</p></div><p class="img"><img src="<%=request.getContextPath()%>/images/jifenImg/pisha/a1.jpg" /></p><p class="price">商城价: 99积分+20运费</p><i class="buy">兑换</i></a></li>
      <li><a href="<%=request.getContextPath()%>/pay/payGoodAction!toJifenBuy.action?prodId=89"><div class="tm"><p class="t">DKT女性护理液-调节型/</p><p class="t2">市场价116元</p></div><p class="img"><img src="<%=request.getContextPath()%>/images/jifenImg/DKThuliye/a1.jpg" /></p><p class="price">商城价: 150积分+30运费</p><i class="buy">兑换</i></a></li>
      <li><a href="<%=request.getContextPath()%>/pay/payGoodAction!toJifenBuy.action?prodId=90"><div class="tm"><p class="t">DKT女性护理凝胶-修复型/</p><p class="t2">市场价199元</p></div><p class="img"><img src="<%=request.getContextPath()%>/images/jifenImg/DKTningjiao/a1.jpg" /></p><p class="price">商城价: 150积分+30运费</p><i class="buy">兑换</i></a></li> --%>
      <li><a href="<%=request.getContextPath()%>/pay/payGoodAction!toJifenBuy.action?prodId=33"><div class="tm"><p class="t">多维片30片*6瓶/</p><p class="t2">市场价216元</p></div><p class="img"><img src="<%=request.getContextPath()%>/images/jifenImg/1.png" /></p><p class="price">商城价: 150积分+30运费</p><i class="buy">兑换</i></a></li>
      <li><a href="<%=request.getContextPath()%>/pay/payGoodAction!toJifenBuy.action?prodId=34"><div class="tm"><p class="t">铁锌钙30片*12瓶/</p><p class="t2">市场价456元</p></div><p class="img"><img src="<%=request.getContextPath()%>/images/jifenImg/2.png" /></p><p class="price">商城价: 300积分+30运费</p><i class="buy">兑换</i></a></li>
      <li><a href="<%=request.getContextPath()%>/pay/payGoodAction!toJifenBuy.action?prodId=35"><div class="tm"><p class="t">松花蛋白粉*2罐/</p><p class="t2">市场价596元</p></div><p class="img"><img src="<%=request.getContextPath()%>/images/jifenImg/3.png" /></p><p class="price">商城价: 450积分+30运费</p><i class="buy">兑换</i></a></li>
      <li><a href="<%=request.getContextPath()%>/pay/payGoodAction!toJifenBuy.action?prodId=36"><div class="tm"><p class="t">多维片30片*6瓶/<i>+</i><br>铁锌钙30片*12瓶/</p><p class="t2">市场价672元</p></div><p class="img"><img src="<%=request.getContextPath()%>/images/jifenImg/4.png" /></p><p class="price">商城价: 450积分+30运费</p><i class="buy">兑换</i></a></li>
      <li><a href="<%=request.getContextPath()%>/pay/payGoodAction!toJifenBuy.action?prodId=37"><div class="tm"><p class="t">松花蛋白粉*2罐/提<i>+</i>铁锌钙30片*12瓶</p><p class="t2">市场价1052元</p></div><p class="img"><img src="<%=request.getContextPath()%>/images/jifenImg/6.png" /></p><p class="price">商城价: 750积分+30运费</p><i class="buy">兑换</i></a></li>
      <li><a href="<%=request.getContextPath()%>/pay/payGoodAction!toJifenBuy.action?prodId=38"><div class="tm"><p class="t">多维30片*6瓶/<i>+</i>铁锌钙30片*12瓶/<i>+</i>松花蛋白粉*2罐/</p><p class="t2">市场价1268元</p></div><p class="img"><img src="<%=request.getContextPath()%>/images/jifenImg/5.png" /></p><p class="price">商城价: 900积分+30运费</p><i class="buy">兑换</i></a></li>
      
    </ul>
    <div class="clear"></div>
  </div>
  
  <div class="itit">积分夺宝</div>
  
  <div class="iduobao">
    <div class="iduobao_l"><a href="<%=request.getContextPath()%>/pay/payGoodAction!toJifenBuy.action?prodId=34"><div class="tm"><p class="t">铁锌钙30片*12瓶 /</p><p class="t2">市场价456元</p></div><p class="img"><img src="<%=request.getContextPath()%>/images/jifenImg/l1.png" /></p><p class="price">商城价: 300积分+30运费</p><i class="buy">兑换</i></a></div>
    <div class="ilist2">
      <ul>
        <li><a href="<%=request.getContextPath()%>/pay/payGoodAction!toJifenBuy.action?prodId=33"><div class="tm"><p class="t">多维片30片*6瓶/</p><p class="t2">市场价216元</p></div><p class="img"><img src="<%=request.getContextPath()%>/images/jifenImg/9.png" /></p><p class="price">商城价: 150积分+30运费</p><i class="buy">兑换</i></a></li>
        <li><a href="<%=request.getContextPath()%>/pay/payGoodAction!toJifenBuy.action?prodId=34"><div class="tm"><p class="t">铁锌钙30片*12瓶/</p><p class="t2">市场价456元</p></div><p class="img"><img src="<%=request.getContextPath()%>/images/jifenImg/8.png" /></p><p class="price">商城价: 300积分+30运费</p><i class="buy">兑换</i></a></li>
        <li><a href="<%=request.getContextPath()%>/pay/payGoodAction!toJifenBuy.action?prodId=35"><div class="tm"><p class="t">松花蛋白粉*2罐/</p><p class="t2">市场价596元</p></div><p class="img"><img src="<%=request.getContextPath()%>/images/jifenImg/7.png" /></p><p class="price">商城价: 450积分+30运费</p><i class="buy">兑换</i></a></li>
        <li><a href="<%=request.getContextPath()%>/pay/payGoodAction!toJifenBuy.action?prodId=36"><div class="tm"><p class="t">多维片30片*6瓶+铁锌/钙30片*12瓶</p><p class="t2">市场价672元</p></div><p class="img"><img src="<%=request.getContextPath()%>/images/jifenImg/10.png" /></p><p class="price">商城价: 450积分+30运费</p><i class="buy">兑换</i></a></li>
      </ul>
      <div class="clear"></div>
    </div>
    <div class="clear"></div>
  
  </div>
  
  <div class="ilist3">
    <ul>
      <li><a href="<%=request.getContextPath()%>/pay/payGoodAction!toJifenBuy.action?prodId=33"><div class="tm"><p class="t">多维片30片*6瓶/</p><p class="t2">市场价216元</p></div><p class="img"><img src="<%=request.getContextPath()%>/images/jifenImg/13.png" /></p><p class="price">商城价: 150积分+30运费</p><i class="buy">兑换</i></a></li>
      <li><a href="<%=request.getContextPath()%>/pay/payGoodAction!toJifenBuy.action?prodId=34"><div class="tm"><p class="t">铁锌钙30片*12瓶/</p><p class="t2">市场价456元</p></div><p class="img"><img src="<%=request.getContextPath()%>/images/jifenImg/15.png" /></p><p class="price">商城价: 300积分+30运费</p><i class="buy">兑换</i></a></li>
      <li><a href="<%=request.getContextPath()%>/pay/payGoodAction!toJifenBuy.action?prodId=35"><div class="tm"><p class="t">松花蛋白粉*2罐/</p><p class="t2">市场价596元</p></div><p class="img"><img src="<%=request.getContextPath()%>/images/jifenImg/14.png" /></p><p class="price">商城价: 450积分+30运费</p><i class="buy">兑换</i></a></li>
    </ul>
    <div class="clear"></div>
  </div>
  
  <!-- <div class="ifmore"><a href="#">更多好礼&gt;</a></div> -->
  <div class="iftip">温馨提示：星火草原微信商城拥有众多的商品积分好礼和积分活动，星火草原商城为您提供各种优质的积分商品，让您的积分购物体验、轻松愉快。积分购物商城，首选星火草原微信商城。</div>

</div>
<!-- 中部e -->


<!-- 底部s -->
<div id="footer">
	  <div class="foot_nav">
	    <div class="foot_nav_inner">
	    <a href="<%=request.getContextPath()%>/user/userAction!phoneFamily.action" class="hover"><span class="line"><i class="nav_f1"></i></span><span class="text">我的红包</span></a>
	    <a href="<%=request.getContextPath()%>/user/userAction!jampshopIndex.action"><span class="line"><i class="nav_f2"></i></span><span class="text">商城首页</span></a>
	    <a href="<%=request.getContextPath()%>/user/userAction!qrcodePage.action"><span class="line"><i class="nav_f3"></i></span><span class="text">二维码</span></a>
	    <a href="javascript:zanwu();"><span class="line"><i class="nav_f4"></i></span><span class="text">购物车</span></a>
	    <a href="<%=request.getContextPath()%>/user/userAction!gotoPersonalCenter.action" ><span class="line"><i class="nav_f5"></i></span><span class="text">个人中心</span></a>
	    </div>
	  </div>
	</div>
<!-- 底部e -->

</div>

</body>
</html>