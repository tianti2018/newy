<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<style type="text/css">
.div-xinxi li input{
	float: left;
	margin: 0;
	padding: 0;
	width: 65%;
	padding-left: 10px;
}
.dizhi span{
	float: left;
	width: 80px;
	line-height: 40px;
}
.ui-loader{
	display: none;
}
	</style>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>购买页面</title>
    <script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
    <script src="<%=request.getContextPath()%>/js/jquery.mobile-1.4.5.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bbt.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/mui.min.css"/>
   
</head>
<body>
<div class="banner_box">
	<div class="banner">
		<a href="#" onclick="dianji(1)" ontouchmove="shows(1)">
			<img src="<%=request.getContextPath()%>/images/pos1.jpg" >
		</a>
		<%-- <a href="#" onclick="dianji(2)" ontouchmove="shows(2)">
			<img src="<%=request.getContextPath()%>/images/shoutu.jpg" >
		</a> --%>
		<%-- <a href="#" ontouchmove="shows(3)">
			<img src="<%=request.getContextPath()%>/images/shoutu.jpg" >
		</a> --%>
	</div>
</div>
	<div class="div-xinxi">
		<ul>
			<li>产品：汇付天下蓝牙pos  价格:80元</li>
			<li class="dizhi"><span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</span><input id="xingming" type="text" placeholder="姓名" value="${orderAddress.userName }"></li>
			<li class="dizhi"><span>微&nbsp;信&nbsp;号&nbsp;：</span><input id="wxhao" type="text" placeholder="微信号" value="${orderAddress.zipcode }"></li>
			<li class="dizhi"><span>手&nbsp;机&nbsp;号&nbsp;：</span><input id="phone" type="text" placeholder="手机号" value="${orderAddress.mobile }"></li>
			<li class="dizhi"><span>详细地址：</span><input id="dizhi" type="text" placeholder="详细地址" value="${orderAddress.address }"></li>
			<li><input type="checkbox" id="tongyixieyi" onclick="tongyi()" style="width:50px;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/xieyi.html" style="text-decoration: underline;">同意《星火草原协议》</a></li>
			<input id="orderAddRessId" type="hidden" value="${orderAddress.id }"/>
		</ul>
	</div>
	<script type="text/javascript">
	jQuery(document).ready(function(){
		$("#tongyixieyi").attr('checked',"checked");
	});
	function tongyi(){
		if(document.getElementById('tongyixieyi').checked==true){
			$("#btn_submit").removeAttr('disabled');
		}else{
			$("#btn_submit").attr('disabled',"disabled");
		}
	}
	$(".banner img").hide();
	$(".banner img:first").show();
	var n = 0;
	var xx = 0;

	function showImg() {
		if (n < $(".banner img").length - 1) {
			n = n + 1;
		} else {
			n = 0;
		}
		//alert(n);
		$(".banner img").hide();
		$(".banner img").eq(n).fadeIn(1000);
		//$(".banner img").eq(n).show();			
	}
	var interval = setInterval(showImg, 4000);
	function shows(num) {
		if (xx != num) {
			showImg();
			xx = num;
			clearInterval(interval);
			interval = setInterval(showImg, 4000);
		}
	}
	function dianji(a){
		if(a==1){
			window.location.href="<%=request.getContextPath()%>/hui.jsp";
		}else if(a==2){
			window.location.href="<%=request.getContextPath()%>/user/userAction!queryLiuBuy.action";
		}
		
	}
	
		function goumai() {
		alert("已经无货,请等待货源补充！");
		return false;
			var xingming = $("#xingming").val();
			var jiage=90;
			var addressId = $("#orderAddRessId").val();
			var dizhi =$("#dizhi").val();
			var phone =$("#phone").val();
			var wxhao =$("#wxhao").val();
			if(xingming==null||xingming==""){
				alert("请填写姓名!");
				return;
			}
			if(wxhao==null||wxhao==""){
				alert("请填写微信号!");
				return;
			}
			if(phone==null||phone==""){
				alert("请填写联系电话!");
				return;
			}
			if(dizhi==null||dizhi==""){
				alert("请填写地址!");
				return;
			}
			
			if(jiage!=""&&jiage!=0){
			$.ajax({
		        type:"POST",
		        url:"<%=request.getContextPath()%>/order/orderAction!saveOrderMiaoSha.action",
		        data : {
		        	"total_price1":jiage,
		        	"orderAddress.id":addressId,
		        	"orderAddress.userName":xingming,
		        	"orderAddress.address":dizhi,
		        	"orderAddress.mobile":phone,
		        	"orderAddress.zipcode":wxhao,
		        	"qi":2,
		        	"miaoShaNum":0
		        	},
		        dataType:"json",
		        success:function(data) {
		       	 	if(data.success){
		       	 		moneyPay(data.ordersBh);
		       	 	//alert(data.ordersBh);
		       	 	}else{
		       	 		if(data.timeOut){
		       	 			alert("用户已超时,请关闭网页重新进入!");
		       	 		}
		       	 		if(data.error){
		       	 			alert("参数错误!请重试");
		       	 		}
			       	 	if(data.num==0){
		       	 			alert("本期秒杀产品已经销售完毕,请等待下期秒杀活动吧!");
		       	 		}
			       	 	$("#btn_submit").html("立即购买");
	       	 			$("#btn_submit").removeAttr('disabled');
		       	 		
		       	 	}
			 	},
			 	beforeSend : function() {
				 	$("#btn_submit").html("订单生成!");
				 	$("#btn_submit").attr('disabled',"disabled");
				},
				error:function(){
					$("#btn_submit").html("立即购买");
       	 			$("#btn_submit").removeAttr('disabled');
					alert("错误!");
				}
		 	});
			
		}else
			alert("参数出错!请关闭页面重新进入!"); 
	}
		
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
//   	 	        alert("您取消了支付。");  
   	 	   }
   	 		else{  
   	 		   $("#btn_submit").html("支付订单");
   	 			$("#btn_submit").removeAttr('disabled');
//   	 	       alert("支付失败!");  
   	 	   }  
   	 	// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg 将在用户支付成功后返回 ok，但并不保证它绝对可靠。
   	 	});
   	 	}
   	 	function moneyPay(bh){
   	 		 $.ajax({
   	 		        type:"POST",
   	 		        url:"<%=request.getContextPath()%>/user/userAction!ajaxMSpay.action?m="+new Date(),
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
	<div class="ft-but">
		<p class="xx-J">共￥<span id="jiage" class="xx-jia">90</span>元,含运费10元</p>
		<button id="btn_submit" class="mui-btn mui-btn-danger mui-btn-block" onclick="goumai()">立即购买</button>
	</div>
	
	
	
	
	
	
	
	
	
	
	
	</br>
	</br>
	</br>
	</br>
	</br>
</body>
</html>





























