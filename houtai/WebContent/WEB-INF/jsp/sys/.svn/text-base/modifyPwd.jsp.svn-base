<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>list</title>
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
<script language="javascript" type="text/javascript">

	var pwdform = null;

	function body_onload () {
		pwdform = document.pwdform;
		
		var message = "<c:out value="${message}"/>";
		
		if (message != "" && message!=null) {
			alert(message);					
		}
	}

	function check()
	{
		if(checkspace(pwdform.loginName.value))  {		
			alert("对不起，登录名不能为空，请重新输入！");
			pwdform.loginName.focus();
			return false;
		}
		
		/* if(checkspace(pwdform.password.value))  {		
			alert("对不起，原密码不能为空，请重新输入！");
			pwdform.password.focus();
			return false;
		}
		if(checkspace(pwdform.password2.value))  {		
			alert("对不起，原二级密码不能为空，请重新输入！");
			pwdform.password2.focus();
			return false;
		} */

		if(checkspace(pwdform.newPassword1.value))  {		
			alert("对不起，新一级密码不能为空，请重新输入！");
			pwdform.newPassword1.focus();
			return false;
		}
		if(checkspace(pwdform.confirmPwd1.value))  {		
			alert("对不起，确认新一级密码不能为空，请重新输入！");
			pwdform.confirmPwd1.focus();
			return false;
		}

		if(pwdform.newPassword1.value != pwdform.confirmPwd1.value)  {		
			alert("对不起，新一级密码和确认新一级密码不一致，请重新输入！");			
			return false;
		}
		if(checkspace(pwdform.newPassword2.value))  {		
			alert("对不起，新二级密码不能为空，请重新输入！");
			pwdform.newPassword2.focus();
			return false;
		}
		if(checkspace(pwdform.confirmPwd2.value))  {		
			alert("对不起，确认新二级密码不能为空，请重新输入！");
			pwdform.confirmPwd2.focus();
			return false;
		}

		if(pwdform.newPassword2.value != pwdform.confirmPwd2.value)  {		
			alert("对不起，新二级密码和确认新二级密码不一致，请重新输入！");			
			return false;
		}
		
		pwdform.submit();
		
		//window.open("main.html");
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
	
</script>
</head>
<body onload="body_onload();">
	<div class="body-box">
		<div class="rhead">
			<div class="rpos">您的当前位置: 修改密码</div>
			<div class="clear"></div>
		</div>
		<form method="post" action="user!modifyPassword.action" id="pwdform" name="pwdform" >
			
			<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">登录名：</td>
					<td width="80%" class="pn-fcontent">
						<input type="text" value="${user.loginName}" name="loginName" id="loginName" size="20" disabled="disabled"/>
					</td>
				</tr>
				<%-- 
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">原密码：</td>
						<td width="80%" class="pn-fcontent">
							<input type="password" name="password" id="password"  size="20" maxlength="12"/>
						</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">原二级密码：</td>
						<td width="80%" class="pn-fcontent">
							<input type="password" name="password2" id="password2"  size="20" maxlength="12"/>
						</td>
					</tr>
				--%>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">新一级密码：</td>
					<td width="80%" class="pn-fcontent">
						<input type="password" id="newPassword1" name="newPassword1" onblur="checkPw('newPassword1');"  size="20" maxlength="12"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">确认新一级密码：</td>
					<td width="80%" class="pn-fcontent">
						<input type="password" size="20" id="confirmPwd1" name="confirmPwd1"  onblur="checkPw('confirmPwd1');" maxlength="12"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">新二级密码：</td>
					<td width="80%" class="pn-fcontent">
						<input type="password" id="newPassword2" name="newPassword2" onblur="checkPw('newPassword2');"  size="20" maxlength="12"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">确认新二级密码：</td>
					<td width="80%" class="pn-fcontent">
						<input type="password" size="20" id="confirmPwd2" name="confirmPwd2"  onblur="checkPw('confirmPwd2');" maxlength="12"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="pn-fbutton">
						<input type="button" value="提交" onclick="return check();"/> &nbsp; 
						<input type="reset" value="重置"/>
					</td>
				</tr>
			</table>
		</form>
		
	</div>
</body>
</html>