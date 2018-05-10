<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta name="apple-mobile-web-app-capable" content="yes"/>
	<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
	<meta name="format-detection" content="telephone=no"/>
 <link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/base.css" />
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/family.css" />
	<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
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

	var frmOrder = null;
	function body_onload () {
		frmOrder = document.frmOrder;
		
		var message = "<c:out value="${message}"/>";
		
		if (message != "" && message!=null) {
			alert(message);
			if ('发货成功'==message && window.dialogArguments!='') {
				window.close();
				window.returnValue = "true";
			}
			
		}
		
	}
	
	<%--检查password--%>
	function checkPw() {
		var pw = document.getElementById(arguments[0]).value;
		
		var matchPw = /^[\w!@#$%\^&\*\(\)_]{6,12}$/ ;
	                       
		if (pw != "") {
			if (!matchPw.test(pw)) {
				alert("对不起，密码长度为6到12位，请重新输入！");
				document.getElementById(arguments[0]).focus();
				return false;
			}			
		}		
	}
	
	function saveOrUpdate() {
		var kuaidiName = document.getElementById("kuaidiName").value;	
		var kuaidiNo = document.getElementById("kuaidiNo").value;	
		/* alert(document.getElementById("cardId").value); */
		
		
		if(checkspace(kuaidiName))  {		
			alert("对不起，快递名称不能为空，请重新输入！");
			frmUser.kuaidiName.focus();
			return false;
		}
		if(checkspace(kuaidiNo))  {		
			alert("对不起，快递编号不能为空，请重新输入！");
			frmOrder.kuaidiNo.focus();
			return false;
		}
			
		frmOrder.action = "orders!appOrder.action";			
		frmOrder.submit();
		frmOrder.cmdButton.disabled = true;
		
	}
 
</script>
 
</head>



<body onload="body_onload();">
<div class="body-box">
<div class="rhead">
		<div class="rpos">您的当前位置: 财务管理-->订单查询-->添加/修改物流信息</div>
		<div class="clear"></div>
	</div>

<form id="frmOrder" name="frmOrder" method="post" action="order!appOrder.action" >

	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">快递名称：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="hidden" id="ordersId" name="ordersId"  value="${orders.ordersId}" />
		  	<select id="kuaidiName" name="kuaidiName">
		  		<option value="${orders.kuaidiName }">${orders.kuaidiName }</option>
		  		<option value="圆通" >圆通</option>
		  		<option value="顺丰" >顺丰</option>
		  		<option value="申通" >申通</option>
		  		<option value="中通" >中通</option>
		  		<option value="韵达" >韵达</option>
		  		<option value="EMS" >EMS</option>
		  		<option value="宅急送" >宅急送</option>
		  		<option value="全峰" >全峰</option>
		  		<option value="天天" >天天快递</option>
		  		<option value="国通" >国通</option>
		  		<option value="自提" >自提</option>
		  	</select>
		  	<font color="red">*</font>	
		  </td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">快递编号：</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" id="kuaidiNo" name="kuaidiNo" value="${orders.kuaidiNo}" size="20"  maxlength="20"/>
				<font color="red">*</font>				
			</td>
		</tr>
		<!-- <tr>
			<td width="20%" class="pn-flabel pn-flabel-h">是否送石头：</td>
			<td width="80%" class="pn-fcontent">
				<input type="radio" id="cardId" name="cardId" checked="checked" value="0">否</input>
				<input type="radio" id="cardId" name="cardId" >是</input>
			</td>
		</tr> -->
	
		<tr>
			<td colspan="2" class="pn-fbutton">
			<input type="button" id="cmdButton" onclick="return saveOrUpdate();" value="保存" style="cursor:pointer;width: 100px;"/>&nbsp;&nbsp;&nbsp;<input type="reset" value="重置"/></td>
		</tr>
	</table>
</form>
</div>
</body>
</html>
