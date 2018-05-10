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

	var roleId = frmRole.roleId.value;

	var roleName = frmRole.roleName.value;

	if(checkspace(roleName))  {		
		alert("对不起，角色名称不能为空，请重新输入！");
		frmRole.roleName.focus();
		return false;
	}
			
	if (roleId != "" && roleId != null) {
		frmRole.action = "role!update.action";
	}
	else {
		frmRole.action = "role!create.action";
	}
	
	frmRole.submit();
}

</script>
 
</head>


<body onload="body_onload();">
<div class="body-box">
<div class="rhead">
		<div class="rpos">您的当前位置: 角色管理-->新增/修改角色</div>
		<div class="clear"></div>
	</div>
<div class="rhead">	
	
	<form class="ropt" method="post">
		<input type="button" value="返回列表" onclick="goBack();"/>
	</form>
	<div class="clear"></div>
</div>
<form id="frmRole" name="frmRole" method="post" action="role!create.action" >

	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">角色名称：</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" id="roleName" name="roleName" maxlength="60" value="${requestScope.role.roleName}" size="20"/><font color="red">*</font>
				<input type="hidden" id="roleId" name="roleId" value="${requestScope.role.roleId}"/>
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
