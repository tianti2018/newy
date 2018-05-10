<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>订单详情</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/jquery.mobile-1.4.3.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/css.css">
<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>

<script src="<%=request.getContextPath()%>/js/index.js"></script>
<script
	src="<%=request.getContextPath()%>/js/jquery.mobile-1.4.3.js"></script>
</head>
<body>

	<div data-role="page" class="jqm-demos" data-quicklinks="true">
		<div class="ui-header ui-bar-inherit ui-btn-active" data-role="header"
			role="banner" data-position="fixed">
			
			<h1>订单详情</h1>
		</div>
		<div class="banner">
			 <img src="<%=request.getContextPath()%>/images/imagesForShop/order.jpg" alt="">  
		</div>
		<div class="ui-content main" role="main">
		<p>订单已生成，请您完成支付</p>
			<p>订单名称：${order.pname}</p>
			<p>订单号：<span id="span_bh">${order.ordersBH}</span></p>
			<p>下单时间：<s:date name="order.createDate"/>&nbsp;&nbsp;&nbsp;&nbsp;</p>
			<p>收货人：<span style="color:red">${order.toUserName }</span></p>
			<p>联系方式：<span style="color:red">${order.mobile }</span></p>
			<p>收货地址：<span style="color:red">${order.address }</span>
			<p>
			订单金额：${order.money}(元)
			</p>
			  <button type="button" id="btn_submit" onclick="moneyPay()" class="ui-btn ui-shadow ui-corner-all ui-btn-active" >支付订单</button>
			<div style="width:100%;height:1px;margin:0px auto;padding:0px;background-color:#D5D5D5;overflow:hidden;"></div>
			</div>
		</div>
	<script type="text/javascript">
	//执行支付
	function pay(appId, timeStamp, nonceStr, package1, signType, paySign) {
		WeixinJSBridge.invoke('getBrandWCPayRequest', {
		'appId' : appId, //公众号名称，由商户传入
		'timeStamp' : timeStamp, //时间戳，自 1970 年以来的秒数
		'nonceStr' : nonceStr, //随机串
		'package' : package1,
		'signType' : signType, //微信签名方式
		'paySign' : paySign
	//微信签名

	}, function(res) {
	$("#btn_submit").html("支付订单");
	$("#btn_submit").removeAttr('disabled');
	if(res.err_msg == "get_brand_wcpay_request:ok"){  
		 WeixinJSBridge.call('closeWindow'); 
	   }
		else if(res.err_msg == "get_brand_wcpay_request:cancel"){  
		   $("#btn_submit").html("支付订单");
			$("#btn_submit").removeAttr('disabled');
//	        alert("您取消了支付。");  
	   }
		else{  
		   $("#btn_submit").html("支付订单");
			$("#btn_submit").removeAttr('disabled');
//	       alert("支付失败!");  
	   }  
	// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg 将在用户支付成功后返回 ok，但并不保证它绝对可靠。
	});
	}
	function moneyPay(){
		var bh=$("#span_bh").html();
		 $.ajax({
		        type:"POST",
		        url:"<%=request.getContextPath()%>/user/userAction!ajaxWxPay.action?m="+new Date(),
		        data : {"orderNo":bh},
		        success:function(data) {
		       	 $("#btn_submit").html("支付中");
		  			if(data!=null){
		  				if(data.message=="success") {
									pay(data.appId2,
											data.timeStamp,
											data.nonceStr2,
											data.packages,
											data.signType2,
											data.paySign2);
		  				}
		        }
			 },
			 beforeSend : function() {
				 $("#btn_submit").html("请求支付中");
				 $("#btn_submit").attr('disabled',"disabled");
				}
		 });

	}

<%-- 	//关闭网页
	function closeWeb(){
	 WeixinJSBridge.call('closeWindow'); 
	}
	//返回业务中心
	function toServiceCenter(){
		window.location.href="<%=request.getContextPath()%>/order/orderAction!serviceCenter.action";
	} 
	
	/* function onBridgeReady() {
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
	} */--%>
	</script>
</body>
</html>
