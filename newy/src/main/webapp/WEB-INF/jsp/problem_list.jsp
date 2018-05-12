<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">
	<title>问题反馈</title>
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/base.css" />
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/family.css" />
	<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>

</head>
<body>
	<!-- wrapper start -->
	
	<div class="wrapper">
		<!-- 头部个人信息 start -->
		<%@ include file="/WEB-INF/jsp/order_head.jsp"%>
		<!-- 主要内容区域 -->
		
		<div class="con">
			<div class="money">
				<span class="sale"></span>
				<span class="mine"></span>
				
			</div>
			
			
			<input style="width:50%; height:30px;margin:10px 0 0 25%;background:#ff1a1a; border:none; color:#fff" type="button" onclick="addProblem()" value="添加问题" />
	
			
			<!-- 等待收货 -->
			<c:if test="${fn:length(weiList) > 0}">
			
			<p class="tj"> 未解决问题  </p>
			</c:if>
			
			<c:forEach items="${weiList}" var="item" varStatus="status">
			<ul class="menu-list">
				<li><a href="javascript:;"><i class="arrows rotated"></i> <em>${item.type}</em> </a>
					<ul class="sub-menu" style="height:240px">
						<li>问题类型：<em>${item.type}</em></li>
						<li>问题描述：<em>${item.describez}</em></li>
						<li>提交时间：<em>${item.createDate}</em></li>
						<li>查看问题：<em><input type="button" onclick="javascript:problemCon(1,${item.id})" value="查看问题"> <input type="button" onclick="javascript:problemCon(2,${item.id})" value="删除问题"></em></li>
					</ul>
				</li>
			</ul>
			</c:forEach>
			
			<c:if test="${fn:length(yiList) > 0}">
			<p class="tj"> 已解决  </p>
			</c:if>
			
			<!-- 完成订单 -->
			<c:forEach items="${yiList}" var="item" varStatus="status">
			<ul class="menu-list" >
				<li><a href="javascript:;"><i class="arrows rotated"></i> <em>${item.type}</em></a>
					<ul class="sub-menu" style="height:320px">
						<li>问题类型：<em>${item.type}</em></li>
						<li>问题描述：<em>${item.describez}</em></li>
						<li>提交时间：<em>${item.createDate}</em></li>
						<li>问题状态：<em>${item.status}</em></li>
						<li>解决方式：<em>${item.method}</em></li>
						<li>查看问题：<em><input type="button" onclick="javascript:problemCon(1,${item.id})" value="查看问题"> <input type="button" onclick="javascript:problemCon(2,${item.id})" value="删除问题"></em></li>
					</ul>
				</li>
			</ul>
			
			</c:forEach> 
		</div>
	</div>
	<!-- footer start -->
	<%-- <footer class="footer">
		<div class="foot-nav">
			<a href="<%=request.getContextPath()%>/user/userAction!buy.action"><i class="foot-icon"><img src="../images/i_buy.png" alt=""></i><span>立即购买</span></a>
			<a href="<%=request.getContextPath()%>/order/orderAction!orderPerList.action?orderNo=0&requestType=mo"><i class="foot-icon"><img src="../images/i_orders.png" alt=""></i><span>我的订单</span></a>
			<a href="<%=request.getContextPath()%>/user/userAction!phoneFamily.action" class="nowpage"><i class="foot-icon"><img src="../images/i_family.png" alt=""></i><span>家族成就</span></a>
			<a href="<%=request.getContextPath()%>/user/userAction!qrcodePage.action"><i class="foot-icon"><img src="../images/i_erweima.png" alt=""></i><span>我的二维码</span></a>
		</div>
	</footer> --%>
	
	<script type="text/javascript">
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
		
	function addProblem(){
		window.location.href="<%=request.getContextPath()%>/user/problemAction!goProblePage.action";
	}
function problemCon(type,pid){
	if(type == 1){
		window.location.href="<%=request.getContextPath()%>/user/problemAction!goProblePage.action?problemId="+pid;	
	}else{
		window.location.href="<%=request.getContextPath()%>/user/problemAction!delProblem.action?problemId="+pid;
	}
			
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
