<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ResourceBundle"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="zh-CN"><head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>个人中心</title>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="x-dns-prefetch-control" content="on">   
    <meta http-equiv="x-dns-prefetch-control" content="on">
    <!--S 线上样式-->
    <link href="<%=request.getContextPath()%>/css/base.s.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/css/my_v2.s.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/css/footer.css" rel="stylesheet" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
    <!--E 线上样式-->

</head>
<body>
<div class="wx_wrap">
    <div id="wrapper" data-tag="wrapper">
        <!--用户信息-->
        <header class="my_header" id="myHeader">
            <div class="face"><a href="#"><img id="headUrl" id="headPic" src="${user.headUrl }" ></a></div>
            <div class="tip">
            <p>昵称：<span id="nicheng">${user.userName }</span></p>            
            <p>会员ID：${user.userId }</p>
            <p>您的头衔：
				<c:if test="${user.level == 0|| user.level == null}">星火种子</c:if>
				<c:if test="${user.level == 1}"><img style="width: 15px" src="../images/s1.png"/></c:if>
				<c:if test="${user.level == 2}"><img style="width: 15px" src="../images/s2.png"/></c:if>
				<c:if test="${user.level == 3}"><img style="width: 25px" src="../images/shenqing/4.png"/></c:if>
			</p>
            <p>店铺等级：<c:if test="${user.cardId== null||user.cardId == 0}">未开店</c:if>
            	<c:if test="${user.cardId== 1}">黄金店</c:if>
				<c:if test="${user.cardId== 2}">黑钻店</c:if>
				<c:if test="${user.cardId== 3}">蓝钻店</c:if>
				<c:if test="${user.cardId== 4}">黄钻店</c:if>
				<c:if test="${user.cardId== 5}">绿钻店</c:if>
				<c:if test="${user.cardId== 6}">红钻店</c:if>
				<c:if test="${user.cardId== 7}">皇冠店</c:if>
				<c:if test="${user.cardId== 8}">旗舰店</c:if>
				<c:if test="${user.cardId== 9}">星火总店</c:if>
            </p>
			<p>关注时间：${user.shappDate }</p>
            </div>
           <!-- <div class="edit_btn"><a href="#">修改收货地址</a></div>-->
            
        </header>
		
        <!--收藏内容-->
        <div class="my_header_links">
            <a href="<%=request.getContextPath()%>/user/userAction!viewPeoPage.action?viewLevel=1"><span id="xianshifensi"></span><span id="wodefensi">我的粉丝</span></a>
            <%-- <a href="<%=request.getContextPath()%>/user/userAction!viewPeoPage.action?viewLevel=2"><span id="shopFav">${user.child2 +0}</span>铁杆粉丝</a>
            <a href="<%=request.getContextPath()%>/user/userAction!viewPeoPage.action?viewLevel=3"><span id="recent">${user.child3 +0}</span>忠实粉丝</a> --%>
        </div>

        <!--订单信息-->
        <section class="my_section"  id="gongneng">
            <!-- <a href="javascript:void(0)" class="head head_order">功能模块</a> -->
            <ul class="list list_order">
            	<li><a href="<%=request.getContextPath()%>/user/userAction!jampshopIndex.action" class="item_3"><span>商城首页</span></a></li>
                <li><a href="<%=request.getContextPath()%>/user/userAction!gotoHuiyuanShop.action" class="item_0"><span>会员专区</span></a></li>
                <li><a href="<%=request.getContextPath()%>/user/userAction!gotoJifenShop.action" class="item_1" id="payItem"><span>微积分商城</span></a></li>
                <%-- <li><a href="<%=request.getContextPath()%>/pay/payDianAction!dianzhuBuy.action" class="item_1" id="payItem"><span>微积分商城</span></a></li> --%>
               <%-- <li><a href="<%=request.getContextPath()%>/user/userAction!queryLiuBuy.action" class="item_2" id="acceptItem"><span>流量商城</span></a></li> --%>
                <li><a href="<%=request.getContextPath()%>/orderAddress/orderAddressAction!orderAddress.action" class="item_4" id="commentItem"><span>修改收货地址</span></a></li>
            </ul>
        </section>

        <!--我的钱包-->
            <section  class="my_section" id="myWallet">
                <a href="javascript:zige();" class="head head_value">我的红包&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong id="totalMoney">0</strong></a>
                <ul class="list list_value list_value_center" onclick="jampUrl()">
                    <li><a href="#"><strong id="kdailiYJ">0</strong>卡主红包</a></li>
                    <li><a href="#"><strong id="kdianzhuYJ">0</strong>店铺红包</a></li>
                    <li><a href="#"><strong id="kliuliangYJ">0</strong>流量红包</a></li>
                    <li><a href="#"><strong id="kgoodYJ">0</strong>产品红包</a></li>
                </ul>
				<!--<ul class="list list_value list_value_center">
                    <li><a href="#"><strong>0</strong>已领代理佣金</a></li>
                    <li><a href="#"><strong>0</strong>已领店铺佣金</a></li>
                    <li><a href="#"><strong>0</strong>已领产品佣金</a></li>
                    <li><a href="#"><strong>0</strong>已领流量佣金</a></li>
                </ul>
				<ul class="list list_value list_value_center">
                    <li><a href="#"><strong>0</strong>流失代理佣金</a></li>
                    <li><a href="#"><strong>0</strong>流失店铺佣金</a></li>
                    <li><a href="#"><strong>0</strong>流失产品佣金</a></li>
                    <li><a href="#"><strong>0</strong>流失流量佣金</a></li>
                </ul>-->
            </section>
         <section class="my_section"  id="myXinghuoquan">
            <a href="<%=request.getContextPath()%>/xinghuoquan/xinghuoquanAction!myXingHuoQuanList.action" class="head head_value">星火券&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong id="xinghuoquan">${user.realSubmitMoney+0 }</strong></a>
		 </section>
		 
		 <!-- 星火微积分 -->
         <section class="my_section"  id="myJiFenList">
            <a  href="<%=request.getContextPath()%>/jifen/jifenAction!myJiFenList.action?status=69" class="head head_value">星火微积分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong id="totalJiFen">${usejifen }</strong></a>
		    <ul class="list list_value list_value_center">
              
               <li><a href="<%=request.getContextPath()%>/jifen/jifenAction!myJiFenList.action?status=1"><strong id="keyongJF">${zongJifen+0}</strong>其它类型积分</a></li>
               <li><a href="<%=request.getContextPath()%>/jifen/jifenAction!myJiFenList.action?status=1"><strong id="keyongJF">${zongnewuser+0}</strong>推荐用户积分</a></li>
              <li><a href="<%=request.getContextPath()%>/jifen/jifenAction!myJiFenList.action?status=2">
                  <strong id="xiaofeiJF">${xiaofeiJifen+0 }</strong>已消费积分</a>
              </li>
             
              <%--  <li><a href="#"><strong id="keyongJF">${notUse+0}</strong>未激活积分</a></li> --%>
            </ul>
		 </section>
		 
		 <section class="my_section"  id="myJiFenList">
            <a href="<%=request.getContextPath()%>/order/orderAction!orderPerList.action?orderNo=0&requestType=mo" class="head head_order">我的订单</a>
		 </section>

        <!--我的活动-->
        <section class="my_section" id="myActivity">
            <a href="javascript:void(0);" class="head head_act" data-tag="activity" ptag="" data-wxtag="7155.1.20" data-sqtag="7155.2.20">其他功能<span id="navigat"></span></a>
            <ul class="list list_act">
                <%-- <li><a href="<%=request.getContextPath()%>/user/userAction!loadRefs.action">推荐人信息</a></li>
                <li><a href="<%=request.getContextPath()%>/user/userAction!gotoUpdateRef.action">修改推荐人</a></li> --%>
                <li><a href="<%=request.getContextPath()%>/user/problemAction!problemList.action">问题反馈</a></li>
                <li><a href="<%=request.getContextPath()%>/pay/payDianAction!listBuDianHongBaos.action">店主红包补发</a></li>
                <!-- <li><a  href="javascript:listCards();">无卡pos邀请码</a></li> -->
				<li><a href="javascript:zanwu();">聊天记录</a></li>
                <li><a href="javascript:zanwu();">平台规则</a></li>
                
                <li><a href="javascript:zanwu();">平台注意事项</a></li>
               <!--  <li id="wxActivity"  style="display: none;"><a href="#">我的礼包</a></li> -->
                <li><a href="<%=request.getContextPath() %>/notice/noticeAction!noticeList.action" id="change">公告</a></li>
                <li id="last"></li>
            </ul>
        </section>

        <!--会员特权
        <section class="my_section" id="vip">
            <a href="shenqing.html" id="vipItem" class="head head_help">开发中<span style="color:#e4393c;" id="redContent"></span></a>
        </section>-->

        <!--幫助-->
        <%-- <section class="my_section">
            <a href="<%=request.getContextPath()%>/wenti.html" class="head head_help">常见问题及解答</a>
        </section> --%>

        <!--猜你喜歡
        <section class="my_section" id="interestPanel">
            <div class="head head_goods">猜你喜欢<a href="javascript:void(0)" id="changeBtn" >换一批</a></div>
            <ul class="list_goods">
              <li><a href="javascript:void(0);"><div class="cover"><img src="images/c/0.jpg"></div><p class="name">驰卡顿 运动鞋男女鞋休闲鞋情侣鞋新款秋冬鞋跑步鞋篮球户外保暖增高防水旅游登山鞋 男女深兰红 40</p><p class="price">¥118.00</p></a></li>
              <li><a href="javascript:void(0);"><div class="cover"><img src="images/c/0.jpg"></div><p class="name">拜格BAYCO双层沥水架 家用多功能厨具碗碟架BX3826</p><p class="price">¥59.00</p></a></li>
              <li><a href="javascript:void(0);"><div class="cover"><img src="images/c/0.jpg"></div><p class="name">拜格 BAYCO 不锈钢厨具七件套 勺铲套BC3509</p><p class="price">¥59.00</p></a></li>
            </ul>
        </section>-->

        <!--客服-->
        <p align="center">服务热线:4000 693 777 </p>
        <p align="center" style="color:red;">注:客服工作时间:早9点--晚6点</p>
        <div class="my_links">
            <a href="../huanxin.jsp" class="link_online" id="serviceItem" >在线客服</a>
        </div>
        
    </div>
	<input type="hidden" id="psocards" value=""/>
</div>


<!-- 底部s -->
<div id="footer">
  <div class="foot_nav">
    <div class="foot_nav_inner">
    <a href="<%=request.getContextPath()%>/user/userAction!phoneFamily.action"><span class="line"><i class="nav_f1"></i></span><span class="text">我的红包</span></a>
    <a href="<%=request.getContextPath()%>/user/userAction!jampshopIndex.action"><span class="line"><i class="nav_f2"></i></span><span class="text">商城首页</span></a>
    <a href="<%=request.getContextPath()%>/user/userAction!qrcodePage.action"><span class="line"><i class="nav_f3"></i></span><span class="text">二维码</span></a>
    <a href="javascript:zanwu();"><span class="line"><i class="nav_f4"></i></span><span class="text">购物车</span></a>
    <a href="<%=request.getContextPath()%>/user/userAction!gotoPersonalCenter.action" class="hover"><span class="line"><i class="nav_f5"></i></span><span class="text">个人中心</span></a>
    </div>
  </div>
</div>
<!-- 底部e -->

</body>
<script type="text/javascript">
function zige(){
	/* if("${user.level}"=="0"||"${user.level}"==""||"${user.level}"=="null"){
		alert("抱歉您还没有资格进入本功能模块");
	}else{ */
	window.location.href="<%=request.getContextPath()%>/user/userAction!phoneFamily.action";
	/* } */
}

function zanwu(){
	alert("开发中......");
}
function onBridgeReady() {
	WeixinJSBridge.call('hideOptionMenu');
}

function fengsi(){//这个方法用来统计粉丝的数量
	var s=${user.child1 +0}+${user.child2 +0}+${user.child3 +0};//247
	document.getElementById("xianshifensi").innerHTML=s;
	//alert(s);
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
}

function jampUrl(){
	window.location.href="<%=request.getContextPath()%>/user/userAction!phoneFamily.action";
}

function listCards(){
	var inputcards = $("#psocards");
	if(inputcards.val()==""){
		if("${user.level}"!=0&&"${user.level}"!=""){
			$.ajax({
		        type:"POST",
		        url:"<%=request.getContextPath()%>/user/userAction!listCards.action",
		        dataType:"json",
		        success:function(data) {
	       	 		if(data.list.length>0){
	       	 			var cards = "";
	       	 			for(var i=0;i<data.list.length;i++){
	       	 				cards +="邀请码"+(i+1)+":\t"+data.list[i].cardpassword+"\n";
	       	 				/* ul.append('<li>'+data.list[i].cardpassword+'</li>'); */
	       	 			}
	       	 			/* ul.animate({"height":ul.find("li").length * 50}); */	
	       	 			
	       	 			inputcards.val(cards);
	       	 			if(cards==""){
	       	 				alert("请联系管理员补充邀请码!");
	       	 			}else {
	       	 				alert(cards);
	       	 			}
	       	 		}
	       	 	
			 	}
		 	});
		}else{
			alert("您还不是卡主,没有邀请码!");
		}
	}else{
		alert(inputcards.val());
	}
	
}

	$(document).ready(function() { 
		if(ifValidate()){
			loadUserInfo();
			
		}
		fengsi();//测试粉丝
		loadUserMoneyWL();//加载未领取佣金
		loadUserMoneyAll();//加载总佣金
		
		//loadUserJifen();加载用户积分
	}); 
	
	function loadUserMoneyWL(){//未领取佣金
		$.ajax({
	        type:"POST",
	        url:"<%=request.getContextPath()%>/user/userAction!loadUserMoneyWL.action",
	        dataType:"json",
	        success:function(data) {
	        	if(data.success){
	        		var kdailiYJ=$("#kdailiYJ");
	        		var kdianzhuYJ=$("#kdianzhuYJ");
	        		var kgoodYJ=$("#kgoodYJ");
	        		var kliuliangYJ=$("#kliuliangYJ");
	        		kdailiYJ.html(data.kdailiYJ);
	        		kdianzhuYJ.html(data.kdianzhuYJ);
	        		kgoodYJ.html(data.kgoodYJ);
	        		kliuliangYJ.html(data.kliuliangYJ);
	        	}
		 	}
	 	});
	}
	
	function loadUserJifen(){//用户积分
		$.ajax({
	        type:"POST",
	        url:"<%=request.getContextPath()%>/jifen/jifenAction!myJifen.action",
	        dataType:"json",
	        success:function(data) {
	        	if(data.success){
	        		var totalJiFen=$("#totalJiFen");
	        		totalJiFen.html(data.kyjifen);
	        	}
		 	}
	 	});
	}
	
	function loadUserMoneyAll(){//总佣金
		$.ajax({
	        type:"POST",
	        url:"<%=request.getContextPath()%>/user/userAction!loadUserMoneyAll.action",
	        dataType:"json",
	        success:function(data) {
	        	if(data.success){
	        		var totalMoney=$("#totalMoney");
	        		totalMoney.html(data.totalMoney);
	        	}
		 	}
	 	});
	}
	
	function ifValidate(){
		var myDate = new Date();
		var nowDay=myDate.getDate();
		var oldDay="${user.createDate}";
		if(oldDay.indexOf(".")!=(-1)){
			oldDay = oldDay.substr(0,oldDay.indexOf("."));
		}
		//var str ='2012-08-12 23:13:15';
		//str = str.replace(/-/g,"/");
		//var date = new Date(str );
		oldDay = oldDay.replace(/-/g,"/");
		oldDay = new Date(oldDay).getDate();
		if(nowDay!=oldDay){
			return true;
		}
		return false;
	}
	
	function loadUserInfo(){
		
		$.ajax({
	        type:"POST",
	        url:"<%=request.getContextPath()%>/user/userAction!loadUserInfo.action",
	        dataType:"json",
	        success:function(data) {
	        	if(data.success){
	        		var userName=$("#nicheng");
	        		var child1=$("#goodsFav");
	        		var child2=$("#shopFav");
	        		var child3=$("#recent");
	        		var xinghuoquan=$("#xinghuoquan");
	        		userName.html(data.userName);
	        		child1.html(data.child1);
	        		child2.html(data.child2);
	        		child3.html(data.child3);
	        		xinghuoquan.html(data.xinghuoquan);
	        	}
		 	}
	 	});
	}
	
	function strToDate(str) {
		 var tempStrs = str.split(" ");
		 var dateStrs = tempStrs[0].split("-");
		 var year = parseInt(dateStrs[0], 10);
		 var month = parseInt(dateStrs[1], 10) - 1;
		 var day = parseInt(dateStrs[2], 10);
		 var timeStrs = tempStrs[1].split("-");
		 var hour = parseInt(timeStrs [0], 10);
		 var minute = parseInt(timeStrs[1], 10) - 1;
		 var second = parseInt(timeStrs[2], 10);
		 var date = new Date(year, month, day, hour, minute, second);
		 return date;
		}
</script>
</html>