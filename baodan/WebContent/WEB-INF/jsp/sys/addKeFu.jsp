<%-- 
/*
 * ----------------------------------------------------------
 * FILE         : addCategory
 * CREATEUSER   : zhy
 * CREATEDATE   : 2009/10/12
 * FILENAME     : addCategory.jsp
 * DESCRIPTION  : 单位清单列表
 * MODIFIES     : 
 * MODIFIER     : 
 * MODIFIEDDATE :
 * COMMENT      : 
 * ----------------------------------------------------------
 */
--%>

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

	var frmKeFu = null;
	function body_onload () {
	
		frmKeFu = document.frmKeFu;
		
		var message = "<c:out value="${message}"/>";
		
		if (message != "" && message!=null) {
			alert(message);					
		}
	}

	// 返回按钮事件
	function goBack() {
		self.location.href="kefu!listAll.action";
	}

	// 确定按钮事件
	function saveOrUpdate() {
	
		var qqId = frmKeFu.qqId.value;
	
		var qqNum = frmKeFu.qqNum.value;
	
		if(checkspace(qqNum))  {		
			alert("对不起，QQ号码不能为空，请重新输入！");
			frmKeFu.qqNum.focus();
			return false;
		}
				
		if (qqId != "" && qqId != null) {
			frmKeFu.action = "kefu!update.action";
		}
		else {
			frmKeFu.action = "kefu!create.action";
		}
		
		frmKeFu.submit();
	}

</script>
 
</head>

<body onload="body_onload();">
	<div class="body-box">
		<div class="rhead">
			<div class="rpos">您的当前位置: 系统管理-->新增/修改号码</div>
			<div class="clear"></div>
		</div>
		<div class="rhead">			
			<form class="ropt" method="post">
				<input type="button" value="返回列表" onclick="goBack();"/>
			</form>
		<div class="clear"></div>
	</div>
	<form id="frmKeFu" name="frmKeFu" method="post" action="kefu!create.action" >
	
		<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
			<tr>
				<td width="20%" class="pn-flabel pn-flabel-h">QQ号码：</td>
				<td width="80%" class="pn-fcontent">
					<input type="text" id="qqNum" name="qqNum" maxlength="60" value="${requestScope.kefu.qqNum}" size="20"/><font color="red">*</font>
					<input type="hidden" id="qqId" name="qqId" value="${requestScope.kefu.qqId}"/>
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
