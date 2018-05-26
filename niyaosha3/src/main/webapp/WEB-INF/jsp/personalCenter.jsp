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
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/commenObject/massageCode.js"></script>
    <!--E 线上样式-->

</head>
<body>
<div class="wx_wrap">
    <div id="wrapper" data-tag="wrapper">
        <!--用户信息-->
        <%@ include file="/WEB-INF/jsp/order_head.jsp"%>
		
        <!--我的团队-->
        <c:if test="${userVo.usery!=null }">
	        <div class="my_header_links">
	            <a href="<%=request.getContextPath()%>/user/userAction!viewPeoPage.action"><span id="tuandui">0</span><span>我的团队</span></a>
	            <a href=""><span id="richengjiaoliang">0</span><span>日成交量</span></a>
	            <a href=""><span id="zongchengjiaoliang">0</span><span>总成交量</span></a>
	            <a href=""><span id="jinrishouyi">0</span><span>今日收益</span></a>
	        </div>
        </c:if>
        
		
        <!--订单信息-->
        <section class="my_section"  id="gongneng">
            <a href="<%=request.getContextPath()%>/order/orderAction!myOrderList.action" class="head head_order">我的订单</a>
            <ul class="list list_order">
                <li><a href="<%=request.getContextPath()%>/order/orderAction!myOrderList.action?orderType=0" class="item_1" id="payItem"><span>待付款</span></a></li> 
                <li><a href="<%=request.getContextPath()%>/order/orderAction!myOrderList.action?orderType=1" class="item_2" id="payItem"><span>待收货</span></a></li>
                <li><a href="<%=request.getContextPath()%>/order/orderAction!myOrderList.action?orderType=2" class="item_3" id="payItem"><span>待评价</span></a></li>
                <li><a href="<%=request.getContextPath()%>/order/orderAction!myOrderList.action?orderType=3" class="item_4" id="payItem"><span>退换货</span></a></li> 
            </ul>
        </section>
		<c:if test="${userVo.usery.dianPuId!=null }">
        <!--我的钱包-->
            <section  class="my_section" id="myWallet">
                <a href="javascript:zige();" class="head head_value">店铺账户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong id="yve">0</strong></a>
                <ul class="list list_value list_value_center">
                    <li><a href="<%=request.getContextPath()%>/shouyi/shouyiAction!myshouyiList.action?orderType=1"><strong id="zhichu">0</strong>支出</a></li>
                    <li><a href="<%=request.getContextPath()%>/shouyi/shouyiAction!myshouyiList.action?orderType=2"><strong id="shouru">0</strong>收入</a></li>
                    <li><a href="<%=request.getContextPath()%>/shouyi/shouyiAction!myshouyiList.action?orderType=3"><strong id="daishengxiao">0</strong>待生效</a></li>
                </ul>
            </section>
		 
		 <section class="my_section"  id="dianpuDingDan">
            <a href="<%=request.getContextPath()%>/order/orderAction!dianpuOrders.action" class="head head_order">店铺订单</a>
		 </section>
		</c:if>
        <!--我的活动-->
        <section class="my_section" id="myActivity">
            <a href="javascript:void(0);" class="head head_act" data-tag="activity" ptag="" data-wxtag="7155.1.20" data-sqtag="7155.2.20">其他功能<span id="navigat"></span></a>
            <ul class="list list_act">
            	<c:if test="${userVo.usery!=null }">
                <li><a href="<%=request.getContextPath()%>/dianpu/dianpuAction!gotoMyDianpu.action">店铺管理</a></li>
				<li><a href="<%=request.getContextPath()%>/dianpu/dianpuAction!gotoDianPu.action?dianpuId=${userVo.usery.dianPuId}">进入店铺</a></li>
				<li><a href="<%=request.getContextPath()%>/user/userAction!qrcodePage.action">我的二维码</a></li>
				</c:if>
				
                <li><a href="javascript:zanwu();">平台规则</a></li>
                
                <li><a href="javascript:zanwu();">注意事项</a></li>
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
        <!-- <div class="my_links">
            <a href="../huanxin.jsp" class="link_online" id="serviceItem" >在线客服</a>
        </div> -->
        
    </div>
	<input type="hidden" id="psocards" value=""/>
</div>


<!-- 底部s -->
<%@ include file="/WEB-INF/jsp/footer.jsp"%>
<!-- 底部e -->

</body>
<script type="text/javascript">
function zige(){
	/* if("${user.level}"=="0"||"${user.level}"==""||"${user.level}"=="null"){
		alert("抱歉您还没有资格进入本功能模块");
	}else{ */
	window.location.href="<%=request.getContextPath()%>/shouyi/shouyiAction!myshouyiList.action";
	/* } */
}

function zanwu(){
	alert("开发中......");
}
function onBridgeReady() {
	WeixinJSBridge.call('hideOptionMenu');
}

function loadtuandui(){//这个方法用来统计粉丝的数量
	var s=${userVo.usery.childNum +0};//247
	document.getElementById("xianshifensi").innerHTML=s;
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
		if("${userVo.usery!=null}"=="true"){
			if(ifValidate()){
				loadUserInfo();
				
			}
			loadTdAndChjl();//加载团队和成交量
			//loadUserMoneyWL();//加载
			loadUserMoneyAll();//加载收益
			
			//loadUserJifen();加载用户积分
		}
	}); 
	
	function loadTdAndChjl(){
		$.ajax({
	        type:"POST",
	        url:"<%=request.getContextPath()%>/user/userAction!loadTdAndChjl.action",
	        dataType:"json",
	        success:function(data) {
	        	var code = data.code;
	        	if(code.startsWith("00")){
	        		var tuandui=$("#tuandui");
	        		var richengjiaoliang=$("#richengjiaoliang");
	        		var zongchengjiaoliang=$("#zongchengjiaoliang");
	        		var jinrishouyi=$("#jinrishouyi");
	        		tuandui.html(data.tuandui);
	        		richengjiaoliang.html(data.richengjiaoliang);
	        		zongchengjiaoliang.html(data.zongchengjiaoliang);
	        		jinrishouyi.html(data.jinrishouyi);
	        	}else{
	        		alert(getMessageByCode(code));
	        	}
	        	
		 	}
	 	});
	}
	
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
	
	function loadUserMoneyAll(){//
		$.ajax({
	        type:"POST",
	        url:"<%=request.getContextPath()%>/shouyi/shouyiAction!loadUserMoneyAll.action",
	        dataType:"json",
	        success:function(data) {
	        	var code = data.code;
	        	if(code=="000000"){
	        		var yve=$("#yve");
	        		var zhichu=$("#zhichu");
	        		var shouru=$("#shouru");
	        		var daishengxiao = $("#daishengxiao");
	        		yve.html(data.yve);
	        		zhichu.html(data.zhichu);
	        		shouru.html(data.woaiwojia);
	        		daishengxiao.html(data.daishengxiao);
	        	}else{
	        		alert(getMessageByCode(code));
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
	        		window.location.reload(true);
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