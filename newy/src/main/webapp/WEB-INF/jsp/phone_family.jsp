<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ResourceBundle"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!-- 
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.2
Version: 3.7.0
Author: KeenThemes
Website: http://www.keenthemes.com/
Contact: support@keenthemes.com
Follow: www.twitter.com/keenthemes
Like: www.facebook.com/keenthemes
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
License: You must have a valid license purchased only from themeforest(the above link) in order to legally use the theme for your project.
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->

<html lang="en">
<head>
<%ResourceBundle res = ResourceBundle.getBundle("system"); %> 
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- <meta content="width=device-width, initial-scale=1.0" name="viewport"/> -->
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta content="" name="description"/>
<meta content="" name="author"/>
	<title>我的红包</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/family.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttons.css">
    <link href="<%=request.getContextPath()%>/css/footer.css" rel="stylesheet" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/css/source/jquery.fancybox.pack.js?v=2.1.5"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/source/jquery.fancybox.css?v=2.1.5" media="screen" />
	
</head>
<body>
	<!-- wrapper start -->
	<div class="wrapper">
		<%@ include file="/WEB-INF/jsp/order_head.jsp"%>
		<!-- 主要内容区域 -->
		<div class="con">
			<div class="money">
				<span class="sale"></span>
				<span class="mine"></span>
			</div>
			<!-- 推荐 -->
			<p class="tj"><%-- 您是由【<em>${referee}</em>】推荐 --%>&nbsp;</p>
			<!-- list -->
			<ul class="menu-list">
				
				<%-- <li><a href="javascript:;"><i class="arrows"></i>产品入口<span class="sup-num"><em></em></span></a>
					<ul class="sub-menu">
						<li>
							<button class="button button-caution button-pill button-flat" type="button"   id="viewId1" onclick="goto1()">购买流量</button>
						</li>
						<li>
							<button class="button button-caution button-pill button-flat" type="button"   id="viewId1" onclick="goto2()">购买店铺产品</button>
						</li>
						<!-- <li>
							<button class="button button-caution button-pill button-flat" type="button"   id="viewId1" onclick="goto3()">修改默认收货地址</button>
						</li> -->
						<li>
						</li>
					</ul>
				</li>  --%>
			
		<%-- 		<li><a href="javascript:;"><i class="arrows"></i>家族成员<span class="sup-num"><em>${count1+count2+count3}</em>人</span></a>
					<ul class="sub-menu">
						<li><a href="<%=request.getContextPath()%>/user/userAction!viewPeoPage.action?viewLevel=1"><i class="arrows"></i>超级粉丝团会员<span class="sup-num"><em>${count1}人</em></span></a></li>
						<li><a href="<%=request.getContextPath()%>/user/userAction!viewPeoPage.action?viewLevel=2"><i class="arrows"></i>铁杆粉丝团会员<span class="sup-num"><em>${count2}人</em></span></a></li>
						<li><a href="<%=request.getContextPath()%>/user/userAction!viewPeoPage.action?viewLevel=3"><i class="arrows"></i>忠实粉丝团会员<span class="sup-num"><em>${count3}人</em></span></a></li>
						
						<li><a href="<%=request.getContextPath()%>/user/userAction!viewPeoPage.action?viewLevel=4">购买4台的会员</a></li>
						<li><a href="<%=request.getContextPath()%>/user/userAction!viewPeoPage.action?viewLevel=5">购买5台的会员</a></li>
						<li><a href="<%=request.getContextPath()%>/user/userAction!viewPeoPage.action?viewLevel=6">购买6台的会员</a></li>
						<li><a href="<%=request.getContextPath()%>/user/userAction!viewPeoPage.action?viewLevel=7">购买7台的会员</a></li>
						<li><a href="<%=request.getContextPath()%>/user/userAction!viewPeoPage.action?viewLevel=8">购买8台的会员</a></li>
						<li><a href="<%=request.getContextPath()%>/user/userAction!viewPeoPage.action?viewLevel=9">购买9台的会员</a></li>
						<li><a href="<%=request.getContextPath()%>/user/userAction!viewPeoPage.action?viewLevel=10">购买10台的会员</a></li>
						<li><a href="<%=request.getContextPath()%>/user/userAction!viewPeoPage.action?viewLevel=11">购买11台的会员</a></li>
						<li><a href="<%=request.getContextPath()%>/user/userAction!viewPeoPage.action?viewLevel=12">购买12台的会员</a></li>
						<li><a href="<%=request.getContextPath()%>/user/userAction!viewPeoPage.action?viewLevel=13">购买13台的会员</a></li>
						<li><a href="<%=request.getContextPath()%>/user/userAction!viewPeoPage.action?viewLevel=14">购买14台的会员</a></li>
						<li><a href="<%=request.getContextPath()%>/user/userAction!viewPeoPage.action?viewLevel=15">购买15台的会员</a></li>
						
					</ul>
				</li>
				 --%>
				 <li><a href="javascript:;"><i class="arrows"></i>VIP红包<span class="sup-num"><em></em></span></a>
					<ul class="sub-menu">
						<li><a href="#">红包总额<em>${totalMoney}</em></a></li>
						<li><a href="#">已领红包<em>${ytxMoney}</em></a></li>
						<li><a href="#">未领红包<em>${ktxMoney}</em></a></li>
						<li id="li1"><a href="<%=request.getContextPath()%>/user/userAction!viewHongbao.action?viewLevel=1"><i class="arrows"></i>发放超级粉丝团红包 <span class="sup-num"></span></a></li>
						<li id="li2"><a href="<%=request.getContextPath()%>/user/userAction!viewHongbao.action?viewLevel=2"><i class="arrows"></i>发放铁杆粉丝团红包<span class="sup-num"></span></a></li>
						<li id="li3"><a href="<%=request.getContextPath()%>/user/userAction!viewHongbao.action?viewLevel=3"><i class="arrows"></i>发放忠实粉丝团红包<span class="sup-num"></span></a></li>
						<li></li>
						<li></li>
					</ul>
				</li> 
				<c:if test="${user.level==3 }">
					<li id="dphb"><a href="javascript:;" onclick="listDianHBs()"><i class="arrows"></i>店铺红包<span class="sup-num"><em></em></span></a>
						<ul class="sub-menu" id="dzhb">
						</ul>
					</li>
				</c:if>
		
				 
				
				<%-- <li><a href="javascript:;"><i class="arrows"></i>店铺佣金<span class="sup-num"><em></em></span></a>
					<ul class="sub-menu">
						<input type="checkbox" id="tongyixieyi" onclick="tongyi()" style="width:50px;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="http://xhcy.andortech.cn/xieyi.html" style="text-decoration: underline;">同意《星火草原协议》</a>
						<li id="li1"><a href="<%=request.getContextPath()%>/user/userAction!viewHongbao.action?viewLevel=1"><i class="arrows"></i>发放超级粉丝团红包 <span class="sup-num"></span></a></li>
						<li id="li2"><a href="<%=request.getContextPath()%>/user/userAction!viewHongbao.action?viewLevel=2"><i class="arrows"></i>发放铁杆粉丝团红包<span class="sup-num"></span></a></li>
						<li id="li3"><a href="<%=request.getContextPath()%>/user/userAction!viewHongbao.action?viewLevel=3"><i class="arrows"></i>发放忠实粉丝团红包<span class="sup-num"></span></a></li>
						<li></li>
					</ul>
				</li> --%>  
				
				<%-- <li><a href="javascript:;"><i class="arrows"></i>邀请码提供商<span class="sup-num"><em></em></span></a>
					<ul class="sub-menu">
						<li>ID:${p1.userId};微信号:${orderAddress1.zipcode };电话:${orderAddress1.mobile }</li>
						<li>ID:${p2.userId};微信号:${orderAddress2.zipcode };电话:${orderAddress2.mobile }</li>
						<li>ID:${p3.userId};微信号:${orderAddress3.zipcode };电话:${orderAddress3.mobile }</li>
					</ul>
				</li>  
				
				<li><a href="javascript:;" onclick="listCards()"><i class="arrows"></i>我的邀请码<span class="sup-num"><em></em></span></a>
					<ul class="sub-menu" id="cards">
					</ul>
				</li>
				         --%>
			 	<li ><a href="javascript:;" id="sqtxpl"><i class="arrows"></i>流量红包<span class="sup-num"><em></em></span></a>
					<ul class="sub-menu sub04">
						<li><a href="#">红包总额:${tolMoney}</a></li>
						<li><a href="#">已领红包:${yiMoney}</a></li>
						<li><a href="#">未领红包:${ktMoney}</a></li>
						<li><a href="#"><input type="text" id="tixianId" />提现金额为10的整数倍</em></a></li>
                        <li class="sub04-btn"><a href="#" id="btn_submit" onclick="goTixian()" class="ui-btn ui-shadow ui-corner-all ui-btn-active" >申请提现</a></li>
					</ul>
			
				</li> 
				<li ><a href="javascript:;"  onclick="listdianYJ()"><i class="arrows"></i>产品红包<span class="sup-num"><em></em></span></a>
					<ul class="sub-menu sub04" id="dpyj">
						
					</ul>
			
				</li>
				<%-- <li ><a href="javascript:;"  onclick="probleSystem()"><i class="arrows"></i>问题反馈<span class="sup-num"><em></em></span></a>
					<ul class="sub-menu sub04" id="dpyj">
						
					</ul>
			
				</li>
				<li ><a href="javascript:;"  onclick="dianpubufa()"><i class="arrows"></i>店铺红包补发<span class="sup-num"><em></em></span></a>
				</li>
				<li ><a href="http://xhcy.andortech.cn/wenti.html"><i class="arrows"></i>星火常见问题及回答<span class="sup-num"><em></em></span></a>
				</li> --%>
				
				
			</ul>
		</div>
	</div>
	<p class="tj">服务热线:4000 693 777 </p>
	<p class="tj" style="color: red;">注:客服工作时间:早9点--晚6点</p>
	<!-- <p class="tj" style="color: red;">常年法律顾问:北京市兆亿律师事务所</p> -->
	<br>
	<br>
	<br>
	<br>
	<br>
	<!-- footer start -->
	<%-- <footer class="footer">
		<div class="foot-nav">
			<a href="<%=request.getContextPath()%>/user/userAction!buy.action"><i class="foot-icon"><img src="../images/i_buy.png" alt=""></i><span>立即购买</span></a>
			<a href="<%=request.getContextPath()%>/order/orderAction!orderPerList.action?orderNo=0&requestType=mo"><i class="foot-icon"><img src="../images/i_orders.png" alt=""></i><span>我的订单</span></a>
			<a href="<%=request.getContextPath()%>/user/userAction!phoneFamily.action" class="nowpage"><i class="foot-icon"><img src="../images/i_family.png" alt=""></i><span>家族成就</span></a>
			<a href="<%=request.getContextPath()%>/user/userAction!qrcodePage.action"><i class="foot-icon"><img src="../images/i_erweima.png" alt=""></i><span>我的二维码</span></a>
		</div>
	</footer> --%>
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
	
	<script type="text/javascript">
	$(document).ready(function() { 
		var totalm = "${totalMoney}";
		totalm = parseFloat(totalm);
		if(totalm>=2700){
			$("#dphb").show();
		}else{
			$("#dphb").hide();
		}
		$("#tongyixieyi").attr('checked',"checked");
	}); 
	
	function dianpubufa(){
		
		window.location.href = "<%=request.getContextPath()%>/pay/payDianAction!listBuDianHongBaos.action";
	}
	
	function goto3(){
		window.location.href = "<%=request.getContextPath()%>/orderAddress/orderAddressAction!orderAddress.action";
	}
	function probleSystem(){
		window.location.href = "<%=request.getContextPath()%>/user/problemAction!problemList.action";
	}
	
	var ifdianYJ = true;
	function listdianYJ(){
		if(ifdianYJ){
			$.ajax({
		        type:"POST",
		        url:"<%=request.getContextPath()%>/pay/payGoodAction!listDianpuYJ.action",
		        dataType:"json",
		        success:function(data) {
		        	if(data.success){
		        		var ul= $("#dpyj");
	                    ul.html("");
		       	 		ul.append('<li><a href="#">红包总额:<em>'+data.totalGoodYJ+'</em></a></li>');
		       	 		ul.append('<li><a href="#">已领红包:<em>'+data.ytxGoodYJ+'</em></a></li>');
		       	 		ul.append('<li><a href="#">未领红包:<em>'+data.ktxGoodYJ+'</em></a></li>');
		       	 		ul.append('<li><a href="#">单次提现最高200</a></li>');
		       	 		ul.append('<li><li class="sub04-btn"><a href="#" id="btn_submit" onclick="shenqingTX()" class="ui-btn ui-shadow ui-corner-all ui-btn-active" >申请提现</a></li>');
		       	 		ul.animate({"height":ul.find("li").length * 50});
		        	}else{
		        		if(data.timeOut){
		        			alert("用户登录超时,请关闭网页重新进入!");
		        		}else{
		        			alert("功能异常,请截图并联系管理员!");
		        		}
		        	}
	       	 			
			 	}
		 	});
			ifdianYJ= false;
		}
	}
	
	function shenqingTX(){
		var str ='2016-03-11 02:00:00';
		str = str.replace(/-/g,"/");
		var end = '2016-03-11 04:30:00';
		end = end.replace(/-/g,"/");
		var date = new Date(str);
		var endDate = new Date(end);
		if(date < new Date() && new Date() < endDate){
			alert("微信系统升级维护中,请"+end+"以后再次操作!");
			return false;
		}
		$.ajax({
	        type:"POST",
	        url:"<%=request.getContextPath()%>/goodyj/goodYJAction!tixianGoodYJ.action",
	        dataType:"json",
	        success:function(data) {
        		alert(data.message);
        		ifdianYJ = true;
        		listdianYJ();       	 	
		 	}
	 	});
		
	}
	var ifdianHBS=true;
	function gotoDianzhu(){
		$.ajax({
	        type:"POST",
	        url:"<%=request.getContextPath()%>/pay/payDianAction!zigeChaXun.action",
	        dataType:"json",
	        success:function(data) {
	        	if(data.success){
	        		window.location.href = "<%=request.getContextPath()%>/pay/payDianAction!dianzhuBuy.action";       	 	
	        	}else{
	        		if(data.timeOut){
	        			alert("用户已超时,请关闭界面重新进入!");
	        		}else{
	        			alert("您还不够资格成为店主!");
	        		}
	        		
	        	}
		 	}
	 	});
		
	}
	function listDianHBs(){
		if(ifdianHBS){
			$.ajax({
		        type:"POST",
		        url:"<%=request.getContextPath()%>/pay/payDianAction!listDianHBs.action",
		        dataType:"json",
		        success:function(data) {
	       	 		var ul= $("#dzhb");
					/* ul.append('<li><button class="button button-caution button-pill button-flat" type="button"   id="viewId1" onclick="gotoDianzhu()">店主商城</button></li>'); */
	       	 		ul.append('<li><a href="#">红包总额:<em>'+data.totalMoney+'</em></a></li>');
	       	 		ul.append('<li><a href="#">已领红包<em>'+data.ytxMoney+'</em></a></li>');
	       	 		ul.append('<li><a href="<%=request.getContextPath()%>/pay/payDianAction!listDianHongBaos.action">未领红包:<em>'+data.ktxMoney+'</em></li>');
	       	 		ul.animate({"height":ul.find("li").length * 50});	
			 	}
		 	});
			ifdianHBS = false;
		}
	}
	
	var ifcard = true;
	function listCards(){
		if(ifcard){
			$.ajax({
		        type:"POST",
		        url:"<%=request.getContextPath()%>/user/userAction!listCards.action",
		        dataType:"json",
		        success:function(data) {
	       	 		var ul= $("#cards");
	       	 		if(data.list.length>0){
	       	 			for(var i=0;i<data.list.length;i++){
	       	 				ul.append('<li>'+data.list[i].cardpassword+'</li>');
	       	 			}
	       	 			ul.animate({"height":ul.find("li").length * 50});	
	       	 		}
	       	 	
			 	}
		 	});
			ifcard = false;
		}
		
	}
	
	
	function goTixian(){
		
		var str ='2016-03-11 02:00:00';
		str = str.replace(/-/g,"/");
		var end = '2016-03-11 04:30:00';
		end = end.replace(/-/g,"/");
		var date = new Date(str);
		var endDate = new Date(end);
		if(date < new Date() && new Date() < endDate){
			alert("微信平台维护请稍候提交，感谢您的支持！");
			return false;
		}
		
		$("#btn_submit").removeAttr('onclick');
		var tixian = $("#tixianId").val();
		tixian = parseInt(tixian);
		tixian = jQuery.trim(tixian);
		var ktmoney = '${ktMoney}';
		if(tixian== null || tixian == "" || tixian == 0){
			$("#tixianId").val(0);
			alert("体现金额不能为空");
			$("#btn_submit").attr("onclick","goTixian()");
			return false;
		}
		if(isNaN(tixian)){
			$("#tixianId").val(0);
			alert("只能输入数字");
			$("#btn_submit").attr("onclick","goTixian()");
			return false;
		}
		if(ktmoney < tixian){
			$("#tixianId").val(0);
			alert("您还没有"+tixian+"元佣金");
			$("#btn_submit").attr("onclick","goTixian()");
			return false;
		}
		var yu = tixian%10;
		if(yu != 0){
			$("#tixianId").val(0);
			alert("提现佣金为10的整数倍");
			$("#btn_submit").attr("onclick","goTixian()");
			return false;
		}
		if(parseInt(tixian) > 200){
			alert("单笔提现金额不能大于200元");
			$("#btn_submit").attr("onclick","goTixian()");
			return false;
		}
		
		$.ajax({
	        type:"POST",
	        url:"<%=request.getContextPath()%>/user/userAction!saveTixianLiu.action",
	        data : {
	        	"tixianMoney":tixian
	        	},
	        dataType:"json",
	        success:function(data) {
	       	 	if(data.message == 'false'){
	       	 		alert("保存失败");
	       	 	window.location.href="<%=request.getContextPath()%>/user/userAction!phoneFamily.action";
	       	 	
	       	 	}else{
	       	 		alert(data.message);
	       	 	window.location.href="<%=request.getContextPath()%>/user/userAction!phoneFamily.action";
	       	 	}
		 	}
	 	});
	}
	
	
	
	function go1 () {
		var url = arguments[0];
		$.fancybox.open('http://101.201.172.238/'+url);
	}
	
	function goto1(){
		window.location.href="<%=request.getContextPath()%>/user/userAction!queryLiuBuy.action";
	}
	
	function goto2(){
		window.location.href="<%=request.getContextPath()%>/pay/payGoodAction!toGoodBuy.action";
	}
	
	function check3 () {
		var files1 = $('#files1').val();
		if (files1=='') {
			alert("请上传您的微信二维码图片");
			return false;
		}
		$('.fileupload-form').submit();
	}
	
		$(function(){
			// 判断是否有sub-menu
			if($("ul.sub-menu")){
				$(".menu-list>li>a").click(function(){
					var $sub_menu = $(this).next(".sub-menu"),
						sub_h = $sub_menu.find("li").length * 50;	//27px表示每个sub-menu 下面li的高度
					if($sub_menu.height() == 0){	
						
						if($(this)[0].id == "sqtxpl"){
							//判断是否在可视区域
							$sub_menu.animate({"height":$sub_menu.find("li").length * 50});		//添加动画
							$(this).find(".arrows").addClass("rotated");
						}else{
							//判断是否在可视区域
							$sub_menu.animate({"height":sub_h});		//添加动画
							$(this).find(".arrows").addClass("rotated");
						}
					}else{
						$sub_menu.animate({"height":0});
						$(this).find(".arrows").removeClass("rotated");
					}
				});
			}else{
				
				return false;
			}
		});
		
	function onBridgeReady() {
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
	}
	</script>         
</body>
</html>
