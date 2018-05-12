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
	<title>卡主红包</title>
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
			<div class="mumber">
				<p>
					<c:if test="${viewLevel==1}">超级粉丝团会员</c:if>
					<c:if test="${viewLevel==2}">铁杆粉丝团会员</c:if>
					<c:if test="${viewLevel==3}">忠实粉丝团会员</c:if>
			</div>
			
			<ul class="mumber_list">
					<c:forEach items="${userList}" var="item" varStatus="status">
					<li>
					<span><img src="${item.headUrl}" alt=""></span>
					<h2>${item.userName}</h2>
					<h5>会员编号：${item.userId} </h5>
					<h5>关注时间：<i>${item.shappDate}</i></h5>
     			<h5>头衔：<i>${item.level}</i></h5>
     			 
					<c:if test="${item.level!=null&&item.level!=0}">
					<%-- 审核购买1台的会员 --%>
					<c:if test="${viewLevel==1}">
					<c:if test="${userLevel>=1}"> <%--当前用户购买了几台 如果大于等于1台 代表此用户至少购买了1台 --%>
						<c:if test="${item.level>=1}"><%--被审核用户购买了几台 如果大于等于1台 代表此用户至少购买了1台 --%>
							<c:if test="${item.oneFlag==null || item.oneFlag==0}"> <%-- 1台用户 被审核标志  代表被审核的用户还没有被审核 --%>
								<%-- <a href="javascript:void(0);" id="${item.userId}"  onclick="return fahongbao(${item.userId},90,1);"  class="btn red"> 发货1个卡密 </a> --%>
								 <button class="button button-caution button-pill button-flat" id="${item.userId}"  onclick="return fahongbao(${item.userId},90,1);">领取感恩红包</button>
							</c:if>
						<c:if  test="${ item.oneFlag!=null&&item.oneFlag!=0}">
								<font color="red">领取成功</font>
							</c:if>
						</c:if>
					</c:if>
					</c:if>
     			 		
					<%-- 审核购买2台的会员 --%>
					<c:if test="${viewLevel==2}">
						<c:if test="${userLevel>=2}"> <%--当前用户购买了几台 如果大于等于2台 代表此用户至少购买了1台 --%>
							<c:if test="${item.level>=2}"><%--被审核用户购买了几台 如果大于等于2台 代表此用户至少购买了1台 --%>
								<c:if test="${item.twoFlag1==null || item.twoFlag1==0}"> <%-- 2台用户 被审核标志  代表被审核的用户还没有被审核 --%>
									<%-- <a href="javascript:void(0);" id="${item.userId}"  onclick="return fahongbao(${item.userId},180,2);"  class="btn red"> 发货2个卡密 </a> --%>
									 <button class="button button-caution button-pill button-flat" id="${item.userId}"  onclick="return fahongbao(${item.userId},180,2);">领取感恩红包</button>
								</c:if>
							<c:if  test="${ item.twoFlag1!=null&&item.twoFlag1!=0}">
									<font color="red">领取成功</font>
								</c:if>
							</c:if>
						</c:if>
					</c:if>
					
					<%-- 审核购买3台的会员 --%>
					<c:if test="${viewLevel==3}">
						<c:if test="${userLevel>=3}"> <%--当前用户购买了几台 如果大于等于2台 代表此用户至少购买了1台 --%>
							<c:if test="${item.level>=3}"><%--被审核用户购买了几台 如果大于等于2台 代表此用户至少购买了1台 --%>
								<c:if test="${item.threeFlag1==null || item.threeFlag1==0}"> <%-- 2台用户 被审核标志  代表被审核的用户还没有被审核 --%>
									<%-- <a href="javascript:void(0);" id="${item.userId}"  onclick="return fahongbao(${item.userId},200,31);"  class="btn red"> 发货3个卡密 </a> --%>
									<button class="button button-caution button-pill button-flat" id="${item.userId}"  onclick="return fahongbao(${item.userId},200,31);">领取感恩红包</button>
								</c:if>
							<c:if  test="${ item.threeFlag1!=null&&item.threeFlag1!=0}">
									<font color="red">领取成功</font>
								</c:if>
								
								<c:if test="${item.threeFlag2==null || item.threeFlag2==0}"> <%-- 2台用户 被审核标志  代表被审核的用户还没有被审核 --%>
									<%-- <a href="javascript:void(0);" id="${item.userId}"  onclick="return fahongbao(${item.userId},70,32);"  class="btn red"> 发货 </a> --%>
									<button class="button button-caution button-pill button-flat" id="${item.userId}"  onclick="return fahongbao(${item.userId},70,32);" >领取感恩红包</button>
								</c:if>
							<c:if  test="${ item.threeFlag2!=null&&item.threeFlag2!=0}">
									<font color="red">领取成功</font>
								</c:if>
								
							</c:if>
						</c:if>
					</c:if>
     			 </c:if>
     				<c:if test="${item.level==null || item.level==0}">
     					 <font color="red">尚未购买</font>
     				</c:if>
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

	function go1 () {
		var url = arguments[0];
		$.fancybox.open('http://101.201.172.238/'+url);
	}
	
	function closefancybox() {
		jQuery.fancybox.close();
		$('#textMessage').val("");
	}
	
	function viewDialog() {
		$("#wxOpenid").val(arguments[0]);
		 $("#liuyan").fancybox({
		        'hideOnContentClick': true
		 });
	}
	
	function sendMessage() {
		var textMessage = $('#textMessage').val();
		var wxOpenid = $("#wxOpenid").val();;
		if (textMessage=="") {
			alert("留言内容不能为空");
			return false;
		}
		var url ='<%=request.getContextPath()%>/user/userAction!sendMessage.action';
		$.ajax({
			cache: true,
			type: "POST",
			url:url,
			data:"wxOpenid="+wxOpenid+"&textMessage="+textMessage,// 你的formid
			async: false,
		    error: function(request) {
		        alert("网络出现问题稍等再试!!!");
		    },
		    success: function(data) {
		    	if (data.success) {
		    		alert("留言成功");
		    		$('#textMessage').val("");
		    		jQuery.fancybox.close();
		    		
		    	}
		    }
		});
	}
	
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
		var level = ${viewLevel};
		var amount = arguments[1];
		var flagCount = arguments[2];
		//var bId = arguments[3];
		document.getElementById(userId).disabled = true;
		
		/* var activity = 0;
		switch (level) {
			case 1:activity = '${user.activiti1}';break;
			case 2:activity = '${user.activiti2}';break;
			case 3:activity = '${user.activiti3}';break;
		}
		if (null==activity||""==activity || activity==0) {
			message = "您还没有被您的第【 "+level+"级供应商激活】，请联系他为您激活";
			return false;
		} */
		
		var url ='<%=request.getContextPath()%>/user/userAction!fahongbao.action';
		$.ajax({
			cache: true,
			type: "POST",
			url:url,
			data:"userId2="+userId+"&level="+level+"&amount="+amount+"&flagCount="+flagCount,// 你的formid
			async: false,
		    error: function(request) {
		        alert("Connection error111");
		    },
		    success: function(data) {
		    	if (data.success) {
		    		alert(data.message);
		    		if (data.message=="发放成功") {
		    			self.location.href="<%=request.getContextPath()%>/user/userAction!viewHongbao.action?viewLevel="+level;
		    		}
		    	}
		    }
		}); 
	}
	
	function searchInfo() {
		var title=$.trim($("#search_text").val());
		
		$("#searchValue").val(title);
		$("#frmList").submit();
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