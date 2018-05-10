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
	
		self.location.href="adminUser!listAllUser.action";
	}


	<%--检查email--%>
	function checkEmail() {
		var email = document.getElementById(arguments[0]).value;
		
		var matchEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	                       
		if (email != "") {
			if (!matchEmail.test(email)) {
				alert("对不起，email格式不正确，请重新输入！");
				document.getElementById(arguments[0]).focus();
				return false;
			}			
		}
	}

	<%--检查手机电话--%>
	function checkTel() {
		/* var tel = document.getElementById(arguments[0]).value; */
		var tel = $("#phone").val();
		//var matchTel = /^(\d{3,4})\-{0,1}(\d{7,8})$/ ;
		var matchTel = /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/ ;
		
	                       
		if (tel != "") {
			if (!matchTel.test(tel)) {
				/* alert("对不起，格式不正确，请重新输入！"); */
				/* document.getElementById(arguments[0]).focus(); */
				$("#phonespan").text("格式不正确");
				$("#phone").focus();
				frmUser.cmdButton.disabled = true;
				return false;
			}else{
				$("#phonespan").text("");
				frmUser.cmdButton.disabled = false;
				return true;
			}			
			
		}			
	}

	<%--检查password--%>
	function checkPw() {
		var pw = $("#password").val();
		
		var matchPw = /^[\w!@#$%\^&\*\(\)_]{6,12}$/ ;
	                       
		if (pw != "") {
			if (!matchPw.test(pw)) {
				$("#passwordspan").text("密码长度为6到12位");
				$("#password").focus();
				frmUser.cmdButton.disabled = true;
				return false;
			}else{
				$("#passwordspan").text("");
				frmUser.cmdButton.disabled = false;
				return true;
			}
		}		
	}
	
	function saveOrUpdate() {
	
		var userId = frmUser.userId.value;
	
		var loginName = frmUser.loginName.value;
		var password = frmUser.password.value;
		var confirmPWD = frmUser.confirmPWD.value;
		var roleId = frmUser.roleId.value;
		var userName = frmUser.userName.value;
		
		if(checkspace(loginName))  {		
			alert("对不起，用户名不能为空，请重新输入！");
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
		}

		if(checkspace(userName))  {		
			alert("对不起，用户名称不能为空，请重新输入！");
			frmUser.userName.focus();
			return false;
		}
		if(checkspace(roleId))  {		
			alert("对不起，角色不能为空，请重新输入！");
			frmUser.roleId.focus();
			return false;
		}
		
	
		if (userId != "" && userId != null) {

			if (!checkspace(password)) {
				
				if (password != confirmPWD) {
					alert("对不起，密码与确认密码不一致，请重新输入！");		
					return false;
				}
			}
			
			frmUser.action = "adminUser!updateUser.action";
		}
		else {
			frmUser.action = "adminUser!createUser.action";
		}
		
		frmUser.submit();
	}

    //限制字符串长度
	function checkTextArea() {
       var area = arguments[0];
       var text = area.value; 
       var len = text.length;
       if(len > 250) {
           alert("输入的字符过长！");
           area.focus();
       }
    }
</script>
 
</head>



<body onload="body_onload();">
<div class="body-box">
<div class="rhead">
		<div class="rpos">您的当前位置: 用户管理-->新增/修改用户</div>
		<div class="clear"></div>
	</div>
<div class="rhead">	
	
	<form class="ropt" method="post">
		<input type="button" value="返回列表" onclick="goBack();"/>
	</form>
	<div class="clear"></div>
</div>
<form id="frmUser" name="frmUser" method="post" action="adminUser!createUser.action" >

	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">用户登录名：</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" id="loginName" name="loginName" value="${requestScope.user.loginName}" size="20"  maxlength="12"/><font color="red">*</font>
				<input type="hidden" id="userId" name="userId" value="${requestScope.user.userId}"/>
			</td>
		</tr>
	
		<tr>
		<td width="20%" class="pn-flabel pn-flabel-h">用户密码：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="password"  id="password" name="password"  onblur="checkPw('password');" size="20" maxlength="12"/>
		  	<font color="red"><span id="passwordspan"></span>(注意: 修改的时候不输入默认为原密码)</font>
		  </td>
		</tr>

		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">确认密码：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="password" id="confirmPWD" name="confirmPWD"   size="20" maxlength="12" />
		  </td>
		</tr>
	
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">用户姓名：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="text" id="userName" value="${requestScope.user.userName}" name="userName" size="20" maxlength="20"/><font color="red">*</font>
		  </td>
		</tr>
	
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">电子邮件：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="text" id="email" name="email" onblur="checkEmail('email');"  value="${requestScope.user.email}"  size="20"  maxlength="30"/>
		  </td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">手机：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="text" id="phone" name="phone"   value="${requestScope.user.phone}"  size="20"  maxlength="30" onblur="checkTel('phone');"/>
		  	<font color="red">*<span id="phonespan"></span></font>
		  </td>
		</tr>
			
		<tr>
		  <td width="20%" class="pn-flabel pn-flabel-h">用户角色：</td>
		  <td width="80%" class="pn-fcontent">
		  	<select name="roleId" id="roleId">
					<option value="">请选择</option>
					<c:forEach items="${listAllRole}" var="item">
						<option  value="${item.roleId}" <c:if test="${item.roleId == requestScope.user.roleId}">selected</c:if>>${item.roleName}</option>
					</c:forEach>			  																		
				</select> <font color="red">*</font>
			</td>
		</tr>

		<tr>
			<td width="20%" height="62" class="pn-flabel pn-flabel-h">备注：</td>
		  <td width="80%" class="pn-fcontent">
		  	<p>
		  	  <textarea name="memo" cols="50" rows="5" id="memo" onblur = "checkTextArea(this)" >${requestScope.user.memo}</textarea>
		  	</p>  	
		  </td>
		</tr>

		<tr>
			<td colspan="2" class="pn-fbutton">
			
			<input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}"/>
			<input type="button" onclick="return saveOrUpdate();" value="保存" style="cursor:pointer;width: 100px;"/>&nbsp;&nbsp;&nbsp;<input type="reset" value="重置"/></td>
		</tr>
	</table>
</form>
</div>
</body>
</html>
