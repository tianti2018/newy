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
			if ("新增成功"==message && window.dialogArguments!='') {
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
		var userId = document.getElementById("userId").value;	
		var referrerId = document.getElementById("referrerId").value;	
		var centerId = document.getElementById("centerId").value;	
		
		
		var loginName = frmUser.loginName.value;
		var password = frmUser.password.value;
		var confirmPWD = frmUser.confirmPWD.value;
		
		var password2 = frmUser.password2.value;
		var confirmPWD2 = frmUser.confirmPWD2.value;
		var userName = frmUser.userName.value;
		
		var phone = frmUser.phone.value; 
		
		if(checkspace(referrerId))  {		
			alert("对不起，推荐人编号不能为空，请重新输入！");
			frmUser.referrerId.focus();
			return false;
		}
		if(checkspace(loginName))  {		
			alert("对不起，会员名不能为空，请重新输入！");
			frmUser.loginName.focus();
			return false;
		}
			
		if (userId == "" || userId == null) {
			if(checkspace(password))  {		
				alert("对不起，密码不能为空，请重新输入！");
				frmUser.password.focus();
				return false;
			}
			
			if(checkspace(confirmPWD))  {		
				alert("对不起，确认密码不能为空，请重新输入！");
				frmUser.confirmPWD.focus();
				return false;
			}
			if (password != confirmPWD) {
				alert("对不起，密码与确认密码不一致，请重新输入！");		
				return false;
			}
			
			if(checkspace(password2))  {		
				alert("对不起，二级密码不能为空，请重新输入！");
				frmUser.password2.focus();
				return false;
			}
			
			if(checkspace(confirmPWD2))  {		
				alert("对不起，二级确认密码不能为空，请重新输入！");
				frmUser.confirmPWD2.focus();
				return false;
			}
			if (password2 != confirmPWD2) {
				alert("对不起，二级密码与二级确认密码不一致，请重新输入！");		
				return false;
			}
		}

		if(checkspace(userName))  {		
			alert("对不起，会员名称不能为空，请重新输入！");
			frmUser.userName.focus();
			return false;
		}
		
		 if(checkspace(phone))  {		
			alert("对不起，用户电话不能为空，请重新输入！");
			frmUser.phone.focus();
			return false;
		}

		if(checkspace(centerId))  {		
			alert("对不起，服务中心编码不能为空，请重新输入！");
			frmUser.centerId.focus();
			return false;
		}
		 
		frmUser.action = "user!createUser.action";			
		frmUser.submit();
		frmUser.cmdButton.disabled = true;
	}
 
</script>
 
</head>



<body onload="body_onload();">
<div class="body-box">
<div class="rhead">
		<div class="rpos">您的当前位置: 会员中心-->注册新会员</div>
		<div class="clear"></div>
	</div>

<form id="frmUser" name="frmUser" method="post" action="user!createUser.action" >

	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">邀请人编码：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="text" id="referrerId" name="referrerId"  value="${tjrLoginName}"  size="20"  maxlength="30"/><font color="red">*</font>	
		  </td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">会员登录名：</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" id="loginName" name="loginName" value="" size="20"  maxlength="12" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')">
				<input type="hidden" id="userId" name="userId" value=""/>
				<input type="hidden" id="flag" name="flag" value="${flag}"/>
				<font color="red">*</font>				
			</td>
		</tr>
	
		<tr>
		<td width="20%" class="pn-flabel pn-flabel-h">一级密码：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="password"  id="password" name="password" value=""  onblur="checkPw('password');" size="20" maxlength="20"/>
		  	<font color="red">*</font>
		  </td>
		</tr>

		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">一级确认密码：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="password" id="confirmPWD" name="confirmPWD" value=""  onblur="checkPw('confirmPWD');" size="20" maxlength="20" /><font color="red">*</font>	
		  </td>
		</tr>
		
		<tr>
		<td width="20%" class="pn-flabel pn-flabel-h">二级密码：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="password"  id="password2" name="password2" value=""   onblur="checkPw('password2');" size="20" maxlength="20"/>
		  	<font color="red">*</font>
		  </td>
		</tr>

		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">二级确认密码：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="password" id="confirmPWD2" name="confirmPWD2" value=""   onblur="checkPw('confirmPWD2');" size="20" maxlength="20" /><font color="red">*</font>	
		  </td>
		</tr>
	
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">会员姓名：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="text" id="userName" value="" name="userName" size="20" maxlength="20"/><font color="red">*(会员姓名为提现姓名不能修改)</font>
		  </td>
		</tr>
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">会员电话：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="text" id="phone" value="" name="phone" size="20" maxlength="20"/><font color="red">*</font>
		  </td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">服务中心编码：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="text" id="centerId" name="centerId"  value="${tjrCenterName}"  size="20"  maxlength="30"/>	<font color="red">*</font>
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
