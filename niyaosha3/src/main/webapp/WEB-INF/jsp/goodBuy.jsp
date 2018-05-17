<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.ResourceBundle"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<%ResourceBundle res = ResourceBundle.getBundle("system"); %> 
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">
	<title><%=res.getString("company")%>商城</title>
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/novipbuy.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/payfor.js"></script>
	<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.mobile-1.4.5.min.js"></script> --%>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/family.css" />
	<link href="<%=request.getContextPath()%>/css/style-metro.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<!-- wrapper start -->
	<div class="wrapper">
		<!-- 头部产品图片 -->
		<div class="banner_box">
			<div class="banner">
				<a href="#" onclick="dianji(1)" ontouchmove="shows(1)">
					<img src="<%=request.getContextPath()%>/images/zengli1/lxzjntp.jpg" style="width: 100%">
				</a>
				<a href="#" onclick="dianji(2)" ontouchmove="shows(2)">
					<img src="<%=request.getContextPath()%>/images/zengli2/lxzdbftp.jpg" style="width: 100%">
				</a>
				<a href="#" onclick="dianji(3)" ontouchmove="shows(3)">
					<img src="<%=request.getContextPath()%>/images/zengli3/hjzhtp.jpg" style="width: 100%">
				</a>
				
			</div>
		</div>
		<form id='frmList_f'  action="<%=request.getContextPath()%>/order/orderAction!saveOrder.action?orderAddRessId=${orderAddress.id}"  method="post">
		<div class="buy_cont">
			<div class="number">
				<p>神六组合套装</p><br><span style="color:red;padding-left:10px">过年了!给自己一份事业!给家人一份安康!</span>
				<div class="reduce_add">
					<a class="reduce" onClick="setAmount.reduce('#qty_item_1')" href="javascript:void(0)"><img src="<%=request.getContextPath()%>/images/novipbuy/reduce.png" alt=""></a>
				    <input type="text" name="qty_item_1" value="1" id="qty_item_1" onKeyUp="setAmount.modify('#qty_item_1')" class="text" />
				    <a class="add" onClick="setAmount.add('#qty_item_1')" href="javascript:void(0)"><img src="<%=request.getContextPath()%>/images/novipbuy/add.png" alt=""></a>
			    </div>
			</div>
			<div class="price">
				<p>购物车总计</p>
					<c:if test="${user.cardId==null||user.cardId==0 }"><br><span style="padding-left:10px">您还不是店主,不享受优惠!请点击左下角成为店主</span></c:if>
					<c:if test="${user.cardId==1}">您是黄金店主,享受${7.5-0.5*user.cardId }折优惠!</c:if>
					<c:if test="${user.cardId==2}">您是黑钻店主,享受${7.5-0.5*user.cardId }折优惠!</c:if>
					<c:if test="${user.cardId==3}">您是蓝钻店主,享受${7.5-0.5*user.cardId }折优惠!</c:if>
					<c:if test="${user.cardId==4}">您是黄钻店主,享受${7.5-0.5*user.cardId }折优惠!</c:if>
					<c:if test="${user.cardId==5}">您是绿钻店主,享受${7.5-0.5*user.cardId }折优惠!</c:if>
					<c:if test="${user.cardId==6}">您是红钻店主,享受${7.5-0.5*user.cardId }折优惠!</c:if>
					<c:if test="${user.cardId==7}">您是皇冠店主,享受${7.5-0.5*user.cardId }折优惠!</c:if>
					<c:if test="${user.cardId==8}">您是旗舰店主,享受${7.5-0.5*user.cardId }折优惠!</c:if>
					<c:if test="${user.cardId==9}">您是星火总店主,享受${7.5-0.5*user.cardId }折优惠!</c:if>
					
				<div class="total">
					<span>商品单价：</span><span id="price_item_1">￥
						<c:if test="${user.cardId==null||user.cardId==0 }">2036.00</c:if>
						<c:if test="${user.cardId!=null&&user.cardId!=0 }"><fmt:formatNumber value="${2036.00*(0.75-0.05*user.cardId)}" type="currency" pattern="$.00"/>  </c:if>				
					<del id="orgin_price"><font color="#333">￥2036</font></del></span>
					<br><span>商品总价：</span><span class="total-font" id="total_item_1"></span>
					<span class="total_txt">运费：</span><span class="total-font" id="total_yunfei">30元/套</span>
			        <input type="hidden" name="total_price" id="total_price" value="" class="total-font"/>
				</div>
			</div>
		</div>
		</form>
		<div class="message">
			
			<c:if test="${null!=orderAddress}">
				
				 <ul class="menu-list">
				<li>
				   <a href="javascript:;"><i class="arrows rotated"></i><em>收货信息</em></a> 
					<ul class="sub-menu" style="height:180px">
					    <li><a href="<%=request.getContextPath()%>/orderAddress/orderAddressAction!orderAddress.action" style="background:#00aa3a; display:inline-block; color:#fff;">选择默认收货地址</a></li>
						<li>收货人：<em>${orderAddress.userName}</em></li>
						<li>联系方式：<em> ${orderAddress.mobile} </em></li>
						<li>收货地址：<em>${orderAddress.address}</em></li>
						
										
					</ul>
					
				</li>
					
					<li><a href="#" onclick="javascript:orderBuy()" class="btn green">立即购买</a></li>	
			</ul> 

			</c:if>
			<c:if test="${null==orderAddress}">
				<div align="center">
					<a href="<%=request.getContextPath()%>/orderAddress/orderAddressAction!orderAddress.action"  class="btn green" onclick="go();">选择一条收货地址</a>
				</div>
			</c:if>
	
		</div>
	</div>
	<!-- footer start -->
	<%-- <footer class="footer">
		<div class="foot-nav">
			<a href="<%=request.getContextPath()%>/pay/payDianAction!dianzhuBuy.action"  class="nowpage"><i class="foot-icon"><img src="../images/i_buy.png" alt=""></i><span>成为店主</span></a>
			<a href="<%=request.getContextPath()%>/order/orderAction!orderPerList.action"><i class="foot-icon"><img src="../images/i_orders.png" alt=""></i><span>我的订单</span></a>
			<a href="<%=request.getContextPath()%>/user/userAction!phoneFamily.action"><i class="foot-icon"><img src="../images/i_family.png" alt=""></i><span>个人中心</span></a>
			<a href="<%=request.getContextPath()%>/user/userAction!qrcodePage.action"><i class="foot-icon"><img src="../images/i_erweima.png" alt=""></i><span>我的二维码</span></a>
		</div><!-- /yingjun/src/main/webapp/images/i_buy.png -->
	</footer> --%>
	<script type="text/javascript">
	
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
	function dianji(a){///newy/src/main/webappproduct1.jsp
		window.location.href="<%=request.getContextPath()%>/product"+a+".jsp";
	}
	
		function orderBuy(){
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
			
			var num = $("#qty_item_1").val();
			if(num <=0){
				alert("至少购买一套!");
				return false;
			}
			if(confirm("确定购买"+num+"套吗?")){
				if(confirm("请确认您的地址够详细,联系方式能联系到您!")){
					if(confirm("请再次确认您的收货地址是:${orderAddress.address},联系电话是:${orderAddress.mobile}")){
						$.ajax({
					        type:"POST",
					        url:"<%=request.getContextPath()%>/pay/payGoodAction!saveOrderGood.action",
					        data : {
					        	"qty_item_1":num,
					        	"orderAddRessId":"${orderAddress.id}",
					        	"type":1
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
						       	 	$("#btn_submit").html("立即购买");
				       	 			$("#btn_submit").removeAttr('disabled');
					       	 		
					       	 	}
						 	},
						 	beforeSend : function() {
							 	$("#btn_submit").html("订单生成中!");
							 	$("#btn_submit").attr('disabled',"disabled");
							},
							error:function(){
								$("#btn_submit").html("立即购买");
			       	 			$("#btn_submit").removeAttr('disabled');
								alert("错误!");
							}
					 	});
					}
				}
				
			}
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
   	 		        url:"<%=request.getContextPath()%>/pay/payGoodAction!ajaxWxPay.action?m="+new Date(),
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
	
		go = function () {
			self.location.href="<%=request.getContextPath()%>/orderAddress/orderAddressAction!orderAddress.action";
		}
		
		
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