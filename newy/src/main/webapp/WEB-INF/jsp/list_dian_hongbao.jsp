<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.ResourceBundle"%>
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
	<title>店铺红包</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/family.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttons.css">
	<link href="http://cdn.bootcss.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/css/style-metro.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/source/jquery.fancybox.css?v=2.1.5" media="screen" />
	<link href="<%=request.getContextPath()%>/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<!-- wrapper start -->
	<div class="wrapper">
		<!-- 头部个人信息 start -->
		<%@ include file="/WEB-INF/jsp/order_head.jsp"%>
		<!-- 主要内容区域 -->
		<div class="con">
			<ul class="mumber_list">
					<c:forEach items="${fhRecordDians}" var="item" varStatus="status">
					<li>
					<span><img src="${item[0]}" alt=""></span>
					<h2>${item[1]}</h2>
					<h5>会员编号：${item[8]} </h5>
					<h5>关注时间：<i>${item[3]}</i></h5>
     			 	<h5>红包类型：<i>
     			 		<c:if test="${item[9]==1}">金店红包</c:if>
	     				<c:if test="${item[9]==2}">黑钻红包</c:if>
	     				<c:if test="${item[9]==3}">蓝钻红包</c:if>
	     				<c:if test="${item[9]==4}">黄钻红包</c:if>
	     				<c:if test="${item[9]==5}">绿钻红包</c:if>
	     				<c:if test="${item[9]==6}">红钻红包</c:if>
	     				<c:if test="${item[9]==7}">皇冠红包</c:if>
	     				<c:if test="${item[9]==8}">旗舰红包</c:if>
	     				<c:if test="${item[9]==9}">总店红包</c:if>
     			 	</i></h5>
     			 	<h5>红包来自：<i>
     			 		<c:if test="${item[10]==0}">超级粉丝</c:if>
						<c:if test="${item[10]==1}">铁杆粉丝</c:if>
	     				<c:if test="${item[10]==2}">忠实粉丝</c:if>
					</i></h5>
					<button class="button button-caution button-pill button-flat" id="${item[8]}"  onclick="return fahongbao(${item[8]},${item[6]},${item[9]},${item[10]});">领红包</button>
    			</c:forEach>
			</ul>
			
		</div>
	</div>
	
	<!-- footer start -->
	<%-- <footer class="footer">
		<div class="foot-nav">
			<a href="<%=request.getContextPath()%>/user/userAction!buy.action"><i class="foot-icon"><img src="../images/i_buy.png" alt=""></i><span>立即购买</span></a>
			<a href="<%=request.getContextPath()%>/order/orderAction!orderPerList.action?orderNo=0&requestType=mo"><i class="foot-icon"><img src="../images/i_orders.png" alt=""></i><span>我的订单</span></a>
			<a href="<%=request.getContextPath()%>/user/userAction!phoneFamily.action" class="nowpage"><i class="foot-icon"><img src="../images/i_family.png" alt=""></i><span>家族成就</span></a>
			<a href="<%=request.getContextPath()%>/user/userAction!qrcodePage.action"><i class="foot-icon"><img src="../images/i_erweima.png" alt=""></i><span>我的二维码</span></a>
		</div><!-- /yingjun/src/main/webapp/images/i_buy.png -->
	</footer> --%>


		<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
	<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.10.2.min.js"></script> --%>
	<script src="<%=request.getContextPath()%>/bootstrap-3.3.5/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/css/source/jquery.fancybox.pack.js?v=2.1.5"></script>
	<script type="text/javascript">

	function fahongbao() {
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
	/* alert("升级维护中！"); */
		var userId = arguments[0];
		var amount = arguments[1];
		var level = arguments[2];
		var levelOne = arguments[3];
		document.getElementById(userId).disabled = true;
		
		var url ='<%=request.getContextPath()%>/pay/payDianAction!fahongbao.action';
		$.ajax({
			cache: true,
			type: "POST",
			url:url,
			data:"userId2="+userId+"&level="+level+"&amount="+amount+"&flagCount="+levelOne,// 你的formid
			async: false,
		    error: function(request) {
		        alert("Connection error111");
		    },
		    success: function(data) {
		    	if (data.success) {
		    		alert(data.message);
		    		if (data.message=="发放成功") {
		    			window.location = window.location;
		    		}
		    	}
		    }
		}); 
	}
	
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