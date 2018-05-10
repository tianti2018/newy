<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>

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
		}
	}
	
	function goBack() {
	
		self.location.href="user!listAllUser.action";
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
		
		var userId = frmUser.userId.value;	
		var loginName = frmUser.loginName.value;
		
		var userName = frmUser.userName.value;
		var phone = frmUser.phone.value;
		
		//var account = frmUser.account.value;
		if(checkspace(loginName))  {		
			alert("对不起，用户名不能为空，请重新输入！");
			frmUser.loginName.focus();
			return false;
		}
			
		
		if(checkspace(userName))  {		
			alert("对不起，用户名称不能为空，请重新输入！");
			frmUser.userName.focus();
			return false;
		}
		if(checkspace(phone))  {		
			alert("对不起，用户电话不能为空，请重新输入！");
			frmUser.phone.focus();
			return false;
		}
		else {
			var matchTel = /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/ ;
			if (!matchTel.test(phone)) {
				alert("对不起，格式不正确，请重新输入！");
				frmUser.phone.focus();
				return false;
			}			
		}
		/* if(checkspace(account))  {		
			alert("对不起，账户不能为空，请重新输入！");
			frmUser.account.focus();
			return false;
		}
		else {
			var matchOne = /^\+?[1-9][0-9]*$/;
			if (!matchOne.test(account)) {
				alert("对不起，格式不正确，请输入正整数！");
				document.getElementById(account).focus();
				return false;
			}
		} */

		frmUser.action = "user!modifyUser.action";			
		frmUser.submit(); 
	}
 
</script>
 
</head>



<body onload="body_onload();">
<div class="body-box">
<div class="rhead">
		<div class="rpos">您的当前位置: 市场管理-->个人资料维护</div>
		<div class="clear"></div>
	</div>

<form id="frmUser" name="frmUser" method="post" action="user!modifyUser.action" >

	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">用户登录名：</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" id="loginName" <c:if test="${flag == '1'}">readonly title='用户名不允许修改'</c:if> name="loginName" value="${users.loginName}" size="20"   maxlength="12" />
				<input type="hidden" id="userId" name="userId" value="${users.userId}"/>
				<font color="red">*</font>				
			</td>
		</tr>
	
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">用户姓名：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="text" id="userName"  name="userName" <c:if test="${flag == '1'}">readonly</c:if> value="${users.userName}" size="20" maxlength="20"/><font color="red">*</font>
		 <a href="" style="color:red">申请更改姓名</a>
		  </td>
		</tr>
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">用户电话：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="text" id="phone"  name="phone" size="20" value="${users.phone}" maxlength="20"/><font color="red">*</font>
		  </td>
		</tr>
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">一级密码：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="text" id="password"  name="password" size="20" value="${users.passWord}" maxlength="20"/><font color="red">*</font>
		  </td>
		</tr>
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">二级密码：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="text" id="password2"  name="password2" size="20" value="${users.passWord2}" maxlength="20"/><font color="red">*</font>
		  </td>
		</tr>
		
		<%--
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">账户：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="text" id="account" name="account"  value="${users.account}"  size="20"  maxlength="30" />
		  </td>
		</tr> --%>
					
		<tr>
			<td colspan="2" class="pn-fbutton">
						
			<input type="button" onclick="return saveOrUpdate();" value="保存" style="cursor:pointer;width: 100px;"/>&nbsp;&nbsp;&nbsp;<input type="reset" value="重置"/></td>
		</tr>
	</table>
</form>
</div>
</body>
</html>
