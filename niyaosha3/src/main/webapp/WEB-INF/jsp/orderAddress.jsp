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
	<title>收货地址-星火草原</title>
	<link rel="stylesheet"	href="<%=request.getContextPath()%>/css/base.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/family.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.s.min.css" /> 
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/my_v2.s.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/footer.css" /> 
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/address.css" /> 
	<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>

</head>   
<body style="background-color: #EFEFEF;">
		<!-- 头部个人信息 start -->
		<%@ include file="/WEB-INF/jsp/order_head.jsp"%>
		
		<!-- 主要内容区域 -->
		
		<!-- 设为默认地址 -->
		 <s:form action="userAction!jampshopIndex.action"
							namespace="/user" method="post" id="frmSenList" theme="simple">
		 </s:form>
		 
		 <!-- 删除收货地址 -->
		 <s:form action="orderAddressAction!orderAddress.action"
							namespace="/orderAddress" method="post" id="frmList" theme="simple">
		 </s:form>
		 
		 <!-- 修改收货地址 -->
		 <s:form action="orderAddressAction!updatePage.action" namespace="/orderAddress" method="post" id="updateStatus1" theme="simple">
			<input type="hidden" id="id" name="id" />
		</s:form> 
		
		<div class="address-con">
		 <div class="addList">
			<c:forEach items="${addressList}" var="item" varStatus="status">			
			<ul>
				<li>
					<div class="add-list-con" onclick="defaultAddr('${item.id}')"> 
					 <p><span class="addName">${item.userName }</span>
					    <span class="addPhone">${item.mobile}</span>
					 </p>
					 <p>${item.sheng }${item.chengshi }${item.diqu }${item.address }</p>
					</div>
					<div class="add-list-btn">
					 <input type="radio" name="defaultAdd" class="radioclass" id="radio-${status.index+1 }" value=""
					 <c:if test="${item.isFirst==1}">checked="checked"</c:if> onclick="defaultAddr('${item.id}')"/>
					 <label for="radio-${status.index+1 }"></label>
						<span>设为默认地址</span>		 
					 <input type="button" name="" id="" value="删除" onclick="delAddr('${item.id}')">
					 <input type="button" name="" id="" value="修改" onclick="updateAddr('${item.id}')">					
					</div>
				</li>
			</ul>
			</c:forEach>
			<div class="newAddress"><a href="<%=request.getContextPath() %>/orderAddress/orderAddressAction!editPage.action?orderType=${orderType}">新增收货地址</a></div>
		</div>
	   </div>
	<!-- footer start -->
	<div id="footer">
	  <div class="foot_nav">
	    <div class="foot_nav_inner">
	    <a href="<%=request.getContextPath()%>/user/userAction!phoneFamily.action"><span class="line"><i class="nav_f1"></i></span><span class="text">我的红包</span></a>
	    <a href="<%=request.getContextPath()%>/user/userAction!jampshopIndex.action"><span class="line"><i class="nav_f2"></i></span><span class="text">商城首页</span></a>
	    <a href="<%=request.getContextPath()%>/user/userAction!qrcodePage.action"><span class="line"><i class="nav_f3"></i></span><span class="text">二维码</span></a>
	    <a href="javascript:zanwu();"><span class="line"><i class="nav_f4"></i></span><span class="text">购物车</span></a>
	    <a href="<%=request.getContextPath()%>/user/userAction!gotoPersonalCenter.action" class="hover"><span class="line"><i class="nav_f5"></i></span><span class="text">个人中心</span></a>
	    </div>
	  </div>
	</div>
	<script type="text/javascript">
	function defaultAddr(aid){
	    var radios = document.getElementsByName("defaultAdd");
	    var productId = '${productId}';
	    var pdId = '${pdId}';
	    for(var i=0;i<radios.length;i++){
	    //判断哪个单选按钮为选中状态  
	    if(radios[i].checked){
		if(aid!=null&&aid!=""){
			//ajax检测用户名是否可用
			$.ajax({
				   url : "<%=request.getContextPath()%>/orderAddress/orderAddressAction!ajaxDefaultAddr.action?"+new Date(),
		           type: "post", 
		           data : {"id":aid},
		           success: function(result) {
		        	   if(result.message=="success"){
		        		   if(productId!==''){
		        			   window.location.href="<%=request.getContextPath()%>/products/productsAction!product.action?prodId="+productId;
		        		   }else if(pdId!=''){
		        			   window.location.href="<%=request.getContextPath()%>/dianpu/dianpuAction!dianPuProduct.action?pdId="+pdId;
		        		   }else{
		        			   window.location.reload(true);
		        		   }
		        		  
		        	   }else{
		        		   alert("操作失败！");
		        	   }
		           },
		           error: function() {
		           }
		        });
		  }
		}
	  }
	}
	
	//修改收货地址
	function updateAddr(aid){
		
		$("#id").val(aid);
		$("#updateStatus1").submit();
	<%--	 if(aid!=null&&aid!=""){
			//ajax检测用户名是否可用
			$.ajax({
				   url : "<%=request.getContextPath()%>/orderAddress/orderAddressAction!editPage.action?"+new Date(),
		           type: "post", 
		           data : {"id":aid},
		           success: function(result) {
		        	   if(result.message=="success"){
		        		   $("#updateStatus1").submit();
		        		   alert("修改完成！");
		        	   }else{
		        		   alert("操作失败！");
		        	   }
		           },
		           error: function() {
		           }
		        });
		} --%>
	}
	
	//删除地址
	function delAddr(aid){
		if(aid!=null&&aid!=""){
			if(confirm("确认要删除这个地址吗？")){
			//ajax检测用户名是否可用
			$.ajax({
				   url : "<%=request.getContextPath()%>/orderAddress/orderAddressAction!ajaxDelAddr.action?"+new Date(),
		           type: "post", 
		           data : {"id":aid},
		           success: function(result) {
		        	   if(result.message=="success"){
		        		   window.location.reload(true);
		        		  alert("删除成功！");
		        	   }else{
		        		   alert("操作失败！");
		        	   }
		           },
		           error: function() {
		           }
		        });
			}
		}
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
	});
	
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
