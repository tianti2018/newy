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

var frmRole = null;
function body_onload () {

	frmRole = document.frmRole;
	
	var message = "<c:out value="${message}"/>";
	
	if (message != "" && message!=null) {
		alert(message);					
	}
}

function goBack() {

	self.location.href="role!listAll.action";
}

function saveOrUpdate()
{
	var password2 = frmRole.password2.value;

	if(checkspace(password2))  {		
		alert("对不起，二级密码不能为空，请重新输入！");
		frmRole.password2.focus();
		return false;
	}
	
	frmRole.submit();
}

</script>
 
</head>


<body onload="body_onload();">
<div class="body-box">
<div class="rhead">
		<div class="rpos">您的当前位置: 二级密码确认</div>
		<div class="clear"></div>
	</div>
<form id="frmRole" name="frmRole" method="post" action="user!password2Confirm.action" >

	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">请输入二级密码：</td>
			<td width="80%" class="pn-fcontent">
				<input type="password" id="password2" name="password2" maxlength="60"  size="20"/><font color="red">*</font>
				<input type="hidden" id="password2Flag" name="password2Flag" value="${password2Flag}" />
				<input type="button" onclick="return saveOrUpdate();" value="确定"/>
			</td>
		</tr>			

		<tr>
			<td colspan="2" class="pn-fbutton">
			</td>
		</tr>
	</table>
</form>
</div>
</body>
</html>
