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
	<title>订单</title>
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
		<%-- <%@ include file="/jsp/com/zklc/weishangcheng/order/order_head.jsp"%> --%>
		<!-- 主要内容区域 -->
		<div class="con">
			<div class="money">
				<span class="sale"></span>
				<span class="mine"></span>
				
			</div>
			<!-- 推荐 -->
			
			<!-- list -->
			<!-- 等待支付 -->

			<p class="tj"> ${fromViewName }   </p>
			
			<c:forEach items="${mywOrder}" var="item" varStatus="status">
			
			<p class="tj">  ${ status.index + 1} 级会员  </p>
			<ul class="menu-list">
				<li ><a href="javascript:;"><i class="arrows rotated"></i><em>${item[3]}</em></a>
					<ul class="sub-menu" style="height:240px">
						<li>订单编号：<em>${item[5]}</em></li>
						<li>订单时间：<em>${item[1]}</em></li>
						<li>支付状态：<em><input type="button" onclick="moneyPay('${item[5]}')" value="去支付" class="order_btn"></em></li>
						<li>订单状态：<em>等待付款</em></li>
						<li>订单金额：<em>${item[4]}元</em></li>
											
					</ul>
				</li>
			</ul>
			
			
			<c:if test="${myMoney == false }">
						 	<p><span class="type">购买时间：</span><span class="val">${item[6]}</span></p>
						 	</c:if>
			
			
			</c:forEach>
			<!-- 等待收货 -->
			<c:if test="${fn:length(myfhOrder) > 0}">
			
			<p class="tj"> 已付款订单  </p>
			</c:if>
			
			<c:forEach items="${myfhOrder}" var="item" varStatus="status">
			<%-- <p class="tj">  ${ status.index + 1} 级会员  </p> --%>
			<ul class="menu-list">
				<li><a href="javascript:;"><i class="arrows rotated"></i><em>${item[3]}</em></a>
					<ul class="sub-menu" style="height:240px">
						<li>订单编号：<em>${item[5]}</em></li>
						<li>订单时间：<em>${item[1]}</em></li>
						<li>支付状态：<em>已付款</em></li>
						<li>晋升等级：<em>${item[6]}</em></li>
						<li>订单金额：<em>${item[4]}元</em></li>
										
					</ul>
				</li>
			</ul>
			</c:forEach>
			
			<c:if test="${fn:length(myshrder) > 0}">
			
			<p class="tj"> 待确认收货订单  </p>
			</c:if>
			<!-- 确认收货 -->
			<c:forEach items="${myshrder}" var="item" varStatus="status">
			<form id='frmList' namespace="order"  action="orderAction!updateOrderStatus.action?orderId=${item[0] }"  method="post">
			<%-- <p class="tj">  ${ status.index + 1} 级会员  </p> --%>
			<ul class="menu-list">
				<li><a href="javascript:;"><i class="arrows rotated"></i><em>${item[3]}</em></a>
					<ul class="sub-menu" style="height:300px">
						<li>订单编号：<em>${item[5]}</em></li>
						<li>订单时间：<em>${item[1]}</em></li>
						<li>支付状态：<em>已支付</em></li>
						<li>订单状态：<em><input type="button" onclick="javascript:qrshsubmit()" value="确认收货"></em></a></li>
						<li>订单金额：<em>${item[4]}元</em></li>
						<li>快递公司：<em><c:if test="${item[6] eq 0}">圆通</c:if><c:if test="${item[6] eq 1}">顺丰</c:if><c:if test="${item[6] eq 2}">申通</c:if><c:if test="${item[6] eq 3}">中通</c:if><c:if test="${item[6] eq 4}">韵达</c:if>
						<c:if test="${item[6] eq 5}">EMS</c:if><c:if test="${item[6] eq 6}">宅急送</c:if><c:if test="${item[6] eq 7}">全峰</c:if><c:if test="${item[6] eq 8}">天天快递</c:if><c:if test="${item[6] eq 9}">自提</c:if>
						</em></li>		
						<li>快递单号：<em>${item[2]}</em></li>				
					</ul>
				</li>
			</ul>
			</form>
			</c:forEach>
			
			<%-- <c:if test="${fn:length(myfcrder) > 0}">
			
			<p class="tj"> 完成订单  </p>
			</c:if>
			
			<!-- 完成订单 -->
			<c:forEach items="${myfcrder}" var="item" varStatus="status">
			<p class="tj">  ${ status.index + 1} 级会员  </p>
			<ul class="menu-list" >
				<li><a href="javascript:;"><i class="arrows rotated"></i><em>${item[3]}</em></a>
					<ul class="sub-menu" style="height:320px">
						<li>订单编号：<em>${item[5]}</em></li>
						<li>订单时间：<em>${item[1]}</em></li>
						<li>支付状态：<em>已付款</em></li>
						<li>订单状态：<em>完成</em></li>
						<li>订单金额：<em>${item[4]}元</em></li>
						<li>快递公司：<em><c:if test="${item[6] eq 0}">圆通</c:if><c:if test="${item[6] eq 1}">顺丰</c:if><c:if test="${item[6] eq 2}">申通</c:if><c:if test="${item[6] eq 3}">中通</c:if><c:if test="${item[6] eq 4}">韵达</c:if>
						<c:if test="${item[6] eq 5}">EMS</c:if><c:if test="${item[6] eq 6}">宅急送</c:if><c:if test="${item[6] eq 7}">全峰</c:if><c:if test="${item[6] eq 8}">天天快递</c:if><c:if test="${item[6] eq 9}">自提</c:if>
						</em></li>		
						<li>快递单号：<em>${item[2]}</em></li>					
					</ul>
				</li>
			</ul>
			
			</c:forEach> --%>
		</div>
	</div>
	<!-- footer start -->
	<footer class="footer">
		<div class="foot-nav">
			<a href="<%=request.getContextPath()%>/order/orderAction!products.action"><i class="foot-icon"><img src="<%=request.getContextPath()%>/images/i_buy.png" alt=""></i><span>立即购买</span></a>
			<a href="<%=request.getContextPath()%>/order/orderAction!orderPerList.action?orderNo=0&requestType=mo" class="nowpage"><i class="foot-icon"><img src="<%=request.getContextPath()%>/images/i_orders.png" alt=""></i><span>我的订单</span></a>
			<a href="<%=request.getContextPath()%>/user/userAction!phoneFamily.action" ><i class="foot-icon"><img src="<%=request.getContextPath()%>/images/i_family.png" alt=""></i><span>家族成就</span></a>
			<a href="<%=request.getContextPath()%>/user/userAction!qrcodePage.action"><i class="foot-icon"><img src="<%=request.getContextPath()%>/images/i_erweima.png" alt=""></i><span>我的二维码</span></a>
		</div><!-- /yingjun/src/main/webapp/images/i_buy.png -->
	</footer>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
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
	/*  var $sub_menu = $(this).next(".sub-menu");
			 if($sub_menu.height() == 0){
				 var sub_h = $sub_menu.find("li").length * 46;
				 $sub_menu.animate({"height":sub_h});		//添加动画
					$(this).find(".arrows").addClass("rotated");
			 } */
		
		function qrshsubmit(){
			$("#frmList").submit();
			<%-- $.ajax({
				   url : "<%=request.getContextPath()%>/order/orderAction!updateOrderStatus.action",
		           type: "post", 
		           data : {"orderId":orderId},
		           success: function(result) {
		        	  /*  if(result!=null||result!=""){
						if(result.message=="success"){
							parent.showTip("删除成功！",1);
							reloadwin();
						}else{
							parent.showTip("删除失败！",0);
						}
		        	   }else{
		        		   parent.showTip("删除失败！",0);
		        	   } */
		           },
		           error: function() {
		        	   parent.showTip("系统错误，请联系管理员！",0);
		           },
		       		beforeSend : function() {
		           }
		        }); --%>
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
//		        alert("您取消了支付。");  
		   }
			else{  
			   $("#btn_submit").html("支付订单");
				$("#btn_submit").removeAttr('disabled');
//		       alert("支付失败!");  
		   }  
		// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg 将在用户支付成功后返回 ok，但并不保证它绝对可靠。
		});
		}
		function moneyPay(orderNo){
			///var bh=$("#span_bh").html();
			 $.ajax({
			        type:"POST",
			        url:"<%=request.getContextPath()%>/user/userAction!ajaxWxPay.action?m="+new Date(),
			        data : {"orderNo":orderNo},
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

		<%-- //关闭网页
		function closeWeb(){
		 WeixinJSBridge.call('closeWindow'); 
		}
		//返回业务中心
		function toServiceCenter(){
			window.location.href="<%=request.getContextPath()%>/order/orderAction!serviceCenter.action";
		} --%>
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
