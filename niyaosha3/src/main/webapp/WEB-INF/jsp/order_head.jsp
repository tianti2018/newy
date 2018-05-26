<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="<%=request.getContextPath()%>/css/my_v2.s.min.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/base.s.min.css" rel="stylesheet" />
		<header class="my_header" id="myHeader">
			<c:if test="${userVo.usery!=null }">
	            <div class="face" style="border-radius:100% !important;">
	            	<a href="#" style="border-radius:100% !important">
	            		<img id="headPic" src="${userVo.usery.headUrl }" style="border-radius:100% !important;">
	            	</a>
	            </div>
	            <div class="tip">
	            <p>昵称：<span id="nicheng">${userVo.usery.userName }</span></p>            
	            <p>ID：${userVo.usery.id }</p>
	            <p>您的头衔：
					<c:if test="${userVo.usery.level == 0|| userVo.usery.level == null}">准店小二</c:if>
					<c:if test="${userVo.usery.level == 1}">店小二<!-- <img style="width: 15px" src="../images/s1.png"/> --></c:if>
					<c:if test="${userVo.usery.level == 2}">掌柜<!-- <img style="width: 15px" src="../images/s2.png"/> --></c:if>
					<c:if test="${userVo.usery.level == 3}">大掌柜<!-- <img style="width: 25px" src="../images/shenqing/4.png"/> --></c:if>
				</p>
				<p>关注时间：${userVo.usery.appDate }</p>
	            </div>
            </c:if>
            <c:if test="${userVo.usery==null }">
	            <div class="face" style="border-radius:100% !important;">
	            	<a href="#" style="border-radius:100% !important">
	            		<img id="headPic" src="${userVo.usery.headUrl }" style="border-radius:100% !important;">
	            	</a>
	            </div>
	            <div class="tip">
	            <p>昵称：<span id="nicheng">${userVo.usery.userName }</span></p>            
	            <p>ID：${userVo.usery.id }</p>
	            <p>您的头衔：
					<c:if test="${userVo.usery.level == 0|| userVo.usery.level == null}">准店小二</c:if>
					<c:if test="${userVo.usery.level == 1}">店小二<!-- <img style="width: 15px" src="../images/s1.png"/> --></c:if>
					<c:if test="${userVo.usery.level == 2}">掌柜<!-- <img style="width: 15px" src="../images/s2.png"/> --></c:if>
					<c:if test="${userVo.usery.level == 3}">大掌柜<!-- <img style="width: 25px" src="../images/shenqing/4.png"/> --></c:if>
				</p>
				<p>关注时间：${userVo.usery.appDate }</p>
	            </div>
            </c:if>
           <div class="edit_btn"><a href="<%=request.getContextPath()%>/orderAddress/orderAddressAction!orderAddress.action">修改收货地址</a></div>
            
        </header>
	<script type="text/javascript">
	
	
	function createNewQrcode(userId){
		if(confirm("重置后旧二维码将不能用,确定重置吗?")){
			$.ajax({
				type: "POST",
				url:"<%=request.getContextPath()%>/user/userAction!createNewQrCode.action",
				data:{
					"userId":userId,
				},
				dataType:"json",
			    error: function(request) {
			        alert("Connection error111");
			    },
			    success: function(data) {
			    	if (data.success) {
						alert("二维码已重新生成,您还有"+data.cishu+"次修改机会!");		    		
			    	}else{
			    		alert("您已经没有机会重置二维码,请联系客服!");
			    	}
			    }
			});
		}
	}
	</script>
