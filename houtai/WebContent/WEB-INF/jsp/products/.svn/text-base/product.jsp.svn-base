<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">
	<title>商城首页</title>
	<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/novipbuy.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/weixin/payfor.js"></script>
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/family.css" />
	<link href="<%=request.getContextPath()%>/css/style-metro.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<!-- wrapper start -->
	<div class="wrapper">
		<!-- 头部产品图片 -->
		<div class="buy_head">
			<img src="${product.headUrl }"/>
		</div>
		<div class="buy_cont">
			<div class="number">
				<p>${product.name }</p>
				<c:if test="${product.price != '0'}">
				<div class="reduce_add">
					<a class="reduce" onClick="setAmount.reduce('#qty_item_1')" href="javascript:void(0)"><img src="<%=request.getContextPath()%>/images/shopImgs/reduce.png" alt=""></a>
				    <input type="text" name="qty_item_1" value="1" id="qty_item_1" onKeyUp="setAmount.modify('#qty_item_1')" class="text" />
				    <a class="add" onClick="setAmount.add('#qty_item_1')" href="javascript:void(0)"><img src="<%=request.getContextPath()%>/images/shopImgs/add.png" alt=""></a>
			    </div>
			    </c:if>
			</div>
			<div class="price">
				<p>总计</p>
				<div class="total">
					<span>商品单价：</span>
					<span id="price_item_1">￥${product.price } <del id="orgin_price"><font color="#333">￥
					<c:choose>
						<c:when test="${product.oldPrice==null||product.oldPrice=='' }">0</c:when>
						<c:otherwise>${product.oldPrice}</c:otherwise>
					</c:choose>
					 </font></del></span>  
					<span class="total_txt">总价：</span><span class="total-font" id="total_item_1"></span>
			        <input type="hidden" name="total_price" id="total_price" value="" class="total-font"/>
			        <input type="hidden" name="productId"  value="${product.productsId}"/>
			        <input type="hidden" name="orderAddressId"  value="${orderAddress.id}"/>
				</div>
			</div>
		</div>
		<div>
			<ul><li>${product.productInfo }</li></ul>
		</div>
	</div>
	<!-- footer start -->
	<footer class="footer">
		<div class="foot-nav">
			<a href="#"  class="nowpage"><i class="foot-icon"><img src="<%=request.getContextPath()%>/images/shopImgs/i_buy.png" alt=""></i><span>立即购买</span></a>
			<a href="#"><i class="foot-icon"><img src="<%=request.getContextPath()%>/images/shopImgs/i_orders.png" alt=""></i><span>我的订单</span></a>
			<a href="#"><i class="foot-icon"><img src="<%=request.getContextPath()%>/images/shopImgs/i_family.png" alt=""></i><span>家族成就</span></a>
			<a href="#"><i class="foot-icon"><img src="<%=request.getContextPath()%>/images/shopImgs/i_erweima.png" alt=""></i><span>我的二维码</span></a>
			</div>
	</footer>
	
</body>
</html>