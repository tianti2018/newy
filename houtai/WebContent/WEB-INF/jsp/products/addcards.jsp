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

	var frmUser = null;
	function body_onload () {
		frmUser = document.frmUser;
		
		var message = "<c:out value="${message}"/>";
		
		if (message != "" && message!=null) {
			alert(message);
			if ("购买成功"==message && window.dialogArguments!='') {
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
		var cardNo = frmUser.cardNo.value;
		var cardPassword = frmUser.cardPassword.value;
		var toUserName = frmUser.toUserName.value;
		
		var mobile = frmUser.mobile.value;
		var zipcode = frmUser.zipcode.value;
		
		var address = frmUser.address.value;
		var pname = frmUser.pname.value;
		
		if(checkspace(cardNo))  {		
			alert("对不起，存值卡号不能为空，请重新输入！");
			frmUser.cardNo.focus();
			return false;
		}
			
		if(checkspace(cardPassword))  {		
			alert("对不起，存值密码不能为空，请重新输入！");
			frmUser.cardPassword.focus();
			return false;
		}
		
		if(checkspace(toUserName))  {		
			alert("对不起，用户真实姓名不能为空，请重新输入！");
			frmUser.toUserName.focus();
			return false;
		}
		if(checkspace(mobile))  {		
			alert("对不起，用户电话不能为空，请重新输入！");
			frmUser.mobile.focus();
			return false;
		}
		if(checkspace(zipcode))  {		
			alert("对不起，邮政编码不能为空，请重新输入！");
			frmUser.zipcode.focus();
			return false;
		}
		if(checkspace(address))  {		
			alert("对不起，寄货地址不能为空，请重新输入！");
			frmUser.address.focus();
			return false;
		}
		
		frmUser.action = "orders!create.action";			
		frmUser.submit();
		frmUser.cmdButton.disabled = true;
	}
 
	function getCardByUserId() {
		$.ajax ({
			url : "joson!getCardByUserId.action",
			type : "post",		
			dataType : "json",			
// 			data : "cardType=1",
			success : 	function(jsonString) {
			if (jsonString.success) {
				document.getElementById("cardNo").value=jsonString.cardNo;
				document.getElementById("cardPassword").value=jsonString.cardPassword;
			}
			else {
				alert('请购买存值卡号和存值密码');
			}
			}				
		});
		
	}
		
</script>
 
</head>



<body onload="body_onload();">
<div class="body-box">
<div class="rhead">
		<div class="rpos">您的当前位置: 会员中心-->购买产品</div>
		<div class="clear"></div>
	</div>

<form id="frmUser" name="frmUser" method="post" action="orders!create.action" >

	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		<tr>
			<td width="10%" colspan="2" class="pn-flabel pn-flabel-h"><a href="#" onclick="return getCardByUserId();">使用已有充值卡号和密码</a></td>
			
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">充值卡号：</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" id="cardNo" name="cardNo" value="" size="30"  maxlength="100">
				<input type="hidden" id="pname" name="pname" value="${param.pname}" >
				<font color="red">*</font>				
			</td>
		</tr>
	
		<tr>
		<td width="20%" class="pn-flabel pn-flabel-h">充值密码：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="password"  id="cardPassword" name="cardPassword"   size="30" maxlength="100"/>
		  	<font color="red">*</font>
		  </td>
		</tr>

		<tr>
		  <td width="20%" class="pn-flabel pn-flabel-h">收货人姓名：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="text" id="toUserName" name="toUserName" value="${order.toUserName }"  size="20" maxlength="12" /><font color="red">*</font>	
		  </td>
		</tr>
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">收货人电话：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="text" id="mobile" value="${order.mobile }" name="mobile" size="20" maxlength="11"/><font color="red">*</font>
		  </td>
		</tr>
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">邮政编码:</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="text" id="zipcode"  name="zipcode" value="${order.zipcode }" size="10" maxlength="6"/><font color="red">*</font>
		  </td>
		</tr>
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">收货人地址：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="text" id="address"  name="address" value="${order.address }" size="50" maxlength="100"/><font color="red">*</font>
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
