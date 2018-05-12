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
	<title>修改推荐人</title>
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
			<div class="money">
				<span class="sale">推荐人ID:<input id="userId" style="width: 60px;color: blue;" type="text" name="userId" onchange="shuzi()"></span>
				<span class="mine"><input type="button" class="button-primary" onclick="souxun()" value="修改"></span>
			</div>
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
	<script src="<%=request.getContextPath()%>/bootstrap-3.3.5/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/css/source/jquery.fancybox.pack.js?v=2.1.5"></script>
	<script type="text/javascript">

	function shuzi() {
		var reg = new RegExp("^[0-9]*$");  
	    if(!reg.test($("#userId").val())){  
	        alert("请输入数字!");  
	        $("#userId").val("");
	    }  
	    /* if(!/^[0-9]*$/.test(obj.value)){  
	        alert("请输入数字!");  
	    }   */
	}
	
	function xiugai(userId){
		var url ='<%=request.getContextPath()%>/user/userAction!updateRef.action';
		$.ajax({
			cache: true,
			type: "POST",
			url:url,
			data:{
				"userId":userId
			},// 你的formid
			dataType: "json",
		    error: function(request) {
		        alert("Connection error111");
		    },
		    success: function(data) {
		    	if (data.success==true) {
		    		alert("修改成功!若自己已下单,请提交问题到公众号,让管理员给推荐人补发红包!");
		    		location = location;
		    	}else if(data.success=="yiyou"){
		    		alert("您已有推荐人,若关系不对请提交问题到公众号,让管理员协助处理!");
		    	}else{
		    		alert("修改失败,请提交问题到公众号,让管理员协助修改!");
		    	}
		    }
		}); 
	}
	
	function souxun() {
		var userId = $("#userId").val();
		if(userId ==null||userId ==""){
			alert("请输入推荐人ID号");
			return false;
		}
		if(userId>parseInt("${user.userId}")){
			alert("推荐人id只能小于被推荐人!");
			return false;
		}
		var url ='<%=request.getContextPath()%>/user/userAction!searcheRef.action';
		$.ajax({
			cache: true,
			type: "POST",
			url:url,
			data:{
				"userId":userId
			},// 你的formid
			dataType: "json",
		    error: function(request) {
		        alert("Connection error111");
		    },
		    success: function(data) {
		    	if (data.success==true) {
		    		if(confirm("只有一次修改机会,请确定修改为ID:"+data.userId+",昵称:"+data.userName+"吗?")){
		    			if(confirm("只有一次修改机会,再次确定修改为ID:"+data.userId+",昵称:"+data.userName+"吗?")){
			    			xiugai(data.userId);
			    		}
		    		}
		    	}else if(data.success=="yiyou"){
		    		alert("您已有推荐人,若关系不对请提交问题到公众号,让管理员协助处理!");
		    	}else{
		    		alert("此人不存在,请填写正确id号!");
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