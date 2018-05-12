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

	var frmOrder = null;
	function body_onload () {
		frmOrder = document.frmOrder;
		
		var message = "<c:out value="${message}"/>";
		
		if (message != "" && message!=null) {
			alert(message);
			if ('操作成功'==message && window.dialogArguments!='') {
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
		var ordersId = document.getElementById("ordersId").value;	
		//var kuaidiName = document.getElementById("kuaidiName").value;	
		var kuaidiNo = document.getElementById("kuaidiNo").value;	
		
		
		/* if(checkspace(kuaidiName))  {		
			alert("对不起，快递名称不能为空，请重新输入！");
			frmUser.kuaidiName.focus();
			return false;
		}*/
		if(checkspace(kuaidiNo))  {		
			alert("对不起，备注信息不能为空，请重新输入！");
			frmOrder.kuaidiNo.focus();
			return false;
		} 
			
		frmOrder.action = "orders!appOrderbeizhu.action";			
		frmOrder.submit();
		frmOrder.cmdButton.disabled = true;
	}
 
</script>
 
</head>



<body onload="body_onload();">
<div class="body-box">
<div class="rhead">
		<div class="rpos">您的当前位置: 财务管理-->订单查询-->添加/修改备注信息</div>
		<div class="clear"></div>
	</div>

<form id="frmOrder" name="frmOrder" method="post" action="order!appOrderbeizhu.action" >

	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		
		<%-- <tr>
			<td width="20%" class="pn-flabel pn-flabel-h">快递名称：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="hidden" id="ordersId" name="ordersId"  value="${orders.ordersId}" />
		  	<select id="kuaidiName" name="kuaidiName">
		  		<option value="">请选择</option>
		  		<option value="0" <c:if test="${orders.kuaidiName=='0'}">selected</c:if>>圆通</option>
		  		<option value="1" <c:if test="${orders.kuaidiName=='1'}">selected</c:if>>顺丰</option>
		  		<option value="2" <c:if test="${orders.kuaidiName=='2'}">selected</c:if>>申通</option>
		  		<option value="3" <c:if test="${orders.kuaidiName=='3'}">selected</c:if>>中通</option>
		  		<option value="4" <c:if test="${orders.kuaidiName=='4'}">selected</c:if>>韵达</option>
		  		<option value="5" <c:if test="${orders.kuaidiName=='5'}">selected</c:if>>EMS</option>
		  		<option value="6" <c:if test="${orders.kuaidiName=='6'}">selected</c:if>>宅急送</option>
		  		<option value="7" <c:if test="${orders.kuaidiName=='7'}">selected</c:if>>全峰</option>
		  			<option value="8" <c:if test="${orders.kuaidiName=='8'}">selected</c:if>>天天快递</option>
		  				<option value="9" <c:if test="${orders.kuaidiName=='9'}">selected</c:if>>自提</option>
		  	</select>
		  	<font color="red">*</font>	
		  </td>
		</tr> --%>
		<tr>
			<input type="hidden" id="ordersId" name="ordersId"  value="${orders.ordersId}" />
			<td width="20%" class="pn-flabel pn-flabel-h">备注信息：</td>
			<td width="80%" class="pn-fcontent">
				<textarea id="kuaidiNo" name="kuaidiNo" >${orders.comments}</textarea>
				<font color="red">*</font>				
			</td>
		</tr>
	
		<tr>
			<td colspan="2" class="pn-fbutton">
			<input type="button" id="cmdButton" onclick="return saveOrUpdate();" value="保存"/>&nbsp;&nbsp;&nbsp;<input type="reset" value="重置"/></td>
		</tr>
	</table>
</form>
</div>
</body>
</html>
