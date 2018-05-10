<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<base target="_self" />

<link href="images/common_res/css/jquery_validate.css" rel="stylesheet" type="text/css"/>
<link href="images/common_res/css/jquery.alerts.css" rel="stylesheet" type="text/css"/>
<link href="images/core_res/css/front.css" rel="stylesheet" type="text/css"/>
<link href="images/core_res/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="images/core_res/css/theme.css" rel="stylesheet" type="text/css"/>
 
<script src="images/common_res/js/jquery.js" type="text/javascript"></script>
<script src="images/common_res/js/jquery_validate.js" type="text/javascript"></script>
<script src="images/common_res/js/pony.js" type="text/javascript"></script>
<script src="images/common_res/js/jquery.ui.draggable.js" type="text/javascript"></script>
<script src="images/common_res/js/jquery.alerts.js" type="text/javascript"></script>
 
<script src="images/core_res/js/front.js" type="text/javascript"></script>
<script src="images/core_res/js/admin.js" type="text/javascript"></script>

<script src="js/common.js" type="text/javascript"></script>

<script type="text/javascript">

	var alipayment = null;
	function body_onload () {
		frmUser = document.alipayment;
		
		var message = "<c:out value="${message}"/>";
		
		if (message != "" && message!=null) {
			alert(message);
		}
		
	}
	
	function saveOrUpdate() {
		var WIDtotal_fee = frmUser.WIDtotal_fee.value;
		
		if (WIDtotal_fee=='') {
			alert("付款金额不能为空");
			frmUser.WIDtotal_fee.focus();
			return false;
		}
		else {
			
			/* var matchOne = /^\+?[1-9][0-9]*$/;
			if (!matchOne.test(WIDtotal_fee)) {
				alert("对不起，格式不正确，请输入正整数！");
				frmUser.WIDtotal_fee.focus();
				return false;
			} */
		}
		
		frmUser.action = "alipayapi.jsp";			
		frmUser.submit();
		frmUser.cmdButton.disabled = true;
	}
 
	function fill() {
		var num = arguments[0];
		document.getElementById("WIDtotal_fee").value=num*660;
	}
	
	 function recharge() {
		 var numData = document.getElementById("cmdcz").value;
		 if (numData=='') {
			 alert('存值卡数量不能为空');
			 return false;
		 }
		 var canBuyNum=$.trim($("#span_cbnum").html());
		 if(numData>canBuyNum){
			 alert('您的积分最多只能买'+canBuyNum+"张");
			 return false;
		 }
		 if (confirm("您确定购买 "+numData+" 张存值卡")) {
			 $("#cmdButtonOne").val(" 购 买 中 ");
			 $("#cmdButtonOne").attr("disabled","disabled"); 
			 self.location.href="alipay!jifengoumaicard.action?numData="+numData;
		 }
		 
	}
</script>
</head>

<body onload="body_onload();">
<div class="body-box">
<div class="rhead">
		<div class="rpos">您的当前位置: 会员中心-->购买存值卡号和卡密</div>
		<div class="clear"></div>
	</div>
	
	<c:if test="${card==null}">
		<form id="alipayment" name=alipayment action=alipayapi.jsp method=post target="_blank">
		<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		  <tr>
			<td width="20%" class="pn-flabel pn-flabel-h">【支付方式一】:支付宝购买：</td>
			<td width="80%" class="pn-fcontent">
				
				<input type="radio" name="radioOne" onclick="fill(1);" value="1"/>1&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="radioOne" onclick="fill(5);" value="5"/>5&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="radioOne" onclick="fill(10);" value="10"/>10&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input size="10" name="WIDtotal_fee" id="WIDtotal_fee" readonly="readonly" />元
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button id="cmdButton" class="new-btn-login" onclick="saveOrUpdate()" style="text-align:center;">确 认</button>	
				<font color="red">*存值卡(660元/张)</font>	
			</td>
		  </tr>	
		 
		  <tr>
			<td width="20%" class="pn-flabel pn-flabel-h">【支付方式二】:积分购买：</td>
			<td width="40%" class="pn-fcontent">
				存值卡数量：<select id="cmdcz">
						<option value="">请选择</option>
						<c:forEach begin="1" end="100" var="dateT">
							<option value="${dateT}">${dateT}个</option>
						</c:forEach>
					</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" id="cmdButtonOne" class="new-btn-login" onclick="recharge()" value=" 购 买 "/>	
					<font color="red">【您的总积分为： ${totalAllJiangjing}】</font>
					<font color="red">【您可以购买：<span id="span_cbnum"><fmt:formatNumber type="number" value="${(totalAllJiangjing-totalAllJiangjing%660)/660}" maxFractionDigits="0"/></span> 张卡密】</font>
			</td>
		  </tr>	
		 
		 <tr>
			<td width="20%" class="pn-flabel pn-flabel-h">【支付方式三】:</br>直接汇款到支付宝账号或者银行账户：</td>
			<td width="40%" class="pn-fcontent">
				（一）支付宝账号： 919694067@qq.com   </br>
						   账户名称： 青岛集联集信息技术有限公司	</br></br>
				 （二）账户名称：    戚金朋  </br>
				 		   开户行：工商银行北京分行昌平支行东小口支行    </br>
				 		   账号：6212 2602 0002 0159 113
				</br>			
			</td>
		  </tr>	
		</table>
	</form>
	</c:if>
	<c:if test="${null!=card}">
		<div class="rhead">
		<div class="rpos">恭喜您得到的存值卡号是 :${card.cardNo}  密码是:${card.cardPassword}</div>
		<div class="clear"></div>
	</div>
	</c:if>
	
</div>

<div class="rhead">
		<div class="rpos">购买卡密 10000元一张</div>
		<div class="clear"></div>
	</div>

</body>
</html>
