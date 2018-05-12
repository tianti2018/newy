<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="<%=request.getContextPath()%>/css/my_v2.s.min.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/base.s.min.css" rel="stylesheet" />
<header class="my_header" id="myHeader">
            <div class="face" style="border-radius:100% !important;">
            	<a href="#" style="border-radius:100% !important">
            		<img id="headPic" src="${user.headUrl }" style="border-radius:100% !important;">
            	</a>
            </div>
            <div class="tip">
            <p>昵称：<span id="nicheng">${user.userName }</span></p>            
            <p>会员ID：${user.userId }</p>
            <p>您的头衔：
				<c:if test="${user.level == 0|| user.level == null}">星火种子</c:if>
				<c:if test="${user.level == 1}"><img style="width: 15px" src="../images/s1.png"/></c:if>
				<c:if test="${user.level == 2}"><img style="width: 15px" src="../images/s2.png"/></c:if>
				<c:if test="${user.level == 3}"><img style="width: 25px" src="../images/shenqing/4.png"/></c:if>
			</p>
            <p>店铺等级：<c:if test="${user.cardId== null||user.cardId == 0}">未开店</c:if>
            	<c:if test="${user.cardId== 1}">黄金店</c:if>
				<c:if test="${user.cardId== 2}">黑钻店</c:if>
				<c:if test="${user.cardId== 3}">蓝钻店</c:if>
				<c:if test="${user.cardId== 4}">黄钻店</c:if>
				<c:if test="${user.cardId== 5}">绿钻店</c:if>
				<c:if test="${user.cardId== 6}">红钻店</c:if>
				<c:if test="${user.cardId== 7}">皇冠店</c:if>
				<c:if test="${user.cardId== 8}">旗舰店</c:if>
				<c:if test="${user.cardId== 9}">星火总店</c:if>
            </p>
			<p>关注时间：${user.shappDate }</p>
            </div>
           <!-- <div class="edit_btn"><a href="#">修改收货地址</a></div>-->
            
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
