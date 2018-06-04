<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ResourceBundle"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%ResourceBundle res = ResourceBundle.getBundle("system"); %> 
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">
	<title>二维码</title>
	<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/css/base.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css">
	<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/css/family.css">
	<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/css/QR-code.css">
    <link href="<%=request.getContextPath()%>/css/footer.css" rel="stylesheet" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/novipbuy.css">
</head>
<body>
	<script type="text/javascript">
	function zanwu(){
		alert("开发中......");
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
	<div style="height:100%;width:100%;z-index:-1">  
		<c:if test="${qrCode!=null&&qrCode!='' }">
			<img width="100%" src="${qrCode}"/> 
		</c:if>
		<c:if test="${qrCode==null||qrCode=='' }">
			您还不是山人物语卡主,请<a href="<%=request.getContextPath()%>/user/userAction!buy.action">立即购买</a>成为代理!
			<%-- <img width="100%" src="<%=request.getContextPath()%>/images/qrcodeImgs/normal.png"/> --%> 
		</c:if>
  	</div>
	<div id="footer">
  <div class="foot_nav">
    <div class="foot_nav_inner">
    <a href="<%=request.getContextPath()%>/user/userAction!phoneFamily.action"><span class="line"><i class="nav_f1"></i></span><span class="text">我的红包</span></a>
    <a href="<%=request.getContextPath()%>/user/userAction!jampshopIndex.action"><span class="line"><i class="nav_f2"></i></span><span class="text">商城首页</span></a>
    <a href="<%=request.getContextPath()%>/user/userAction!qrcodePage.action" class="hover"><span class="line"><i class="nav_f3"></i></span><span class="text">二维码</span></a>
    <a href="javascript:zanwu();"><span class="line"><i class="nav_f4"></i></span><span class="text">购物车</span></a>
    <a href="<%=request.getContextPath()%>/user/userAction!gotoPersonalCenter.action" ><span class="line"><i class="nav_f5"></i></span><span class="text">个人中心</span></a>
    </div>
  </div>
</div>
</body>
</html>
