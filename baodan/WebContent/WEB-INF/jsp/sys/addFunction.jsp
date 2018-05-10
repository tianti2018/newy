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

	var frmFunction = null;
	function body_onload () {
	
		frmFunction = document.frmFunction;
		
		var message = "<c:out value="${message}"/>";
		
		if (message != "" && message!=null) {
			alert(message);					
		}
			
	}
	
	function goBack() {
	
		self.location.href="function!listAll.action";
	}
	
	function saveOrUpdate()
	{
	
		var functionId = frmFunction.functionId.value;
	
		var functionName = frmFunction.functionName.value;
		var url = frmFunction.url.value;
	
		if(checkspace(functionName))  {		
			alert("对不起，功能名称不能为空，请重新输入！");
			frmFunction.functionName.focus();
			return false;
		}
		
		
		
		if(checkspace(url))  {		
			alert("对不起，功能路径不能为空，请重新输入！");
			frmFunction.url.focus();
			return false;
		}
		
	
		if (functionId != "" && functionId != null) {
			frmFunction.action = "function!update.action";
		}
		else {
			frmFunction.action = "function!create.action";
		}
		
		frmFunction.submit();
	}

</script>
 
</head>


<body onload="body_onload();">
<div class="body-box">
<div class="rhead">
		<div class="rpos">您的当前位置: 功能管理-->新增/修改功能</div>
		<div class="clear"></div>
	</div>
<div class="rhead">	
	
	<form class="ropt" method="post">
		<input type="button" value="返回列表" onclick="goBack();"/>
	</form>
	<div class="clear"></div>
</div>
<form id="frmFunction" name="frmFunction" method="post" action="function!create.action" >

	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">所属模块：</td>
			<td width="80%" class="pn-fcontent">
			<select id="module" name="module">
				<option value="">请选择</option>
				<option value="1" <c:if test="${requestScope.function.module == '1'}">selected</c:if>>后台管理</option>
				<option value="2" <c:if test="${requestScope.function.module == '2'}">selected</c:if>>信息管理</option>
				<option value="3" <c:if test="${requestScope.function.module == '3'}">selected</c:if>>系统管理</option>
				<option value="4" <c:if test="${requestScope.function.module == '4'}">selected</c:if>>财务管理</option>
				<%-- <option value="4" <c:if test="${requestScope.function.module == '4'}">selected</c:if>>退出系统</option> --%>
			</select>
			</td>
		</tr>
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">功能名称：</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" id="functionName" name="functionName" value="${requestScope.function.functionName}" size="20"  maxlength="100"/><font color="red">*</font>
				<input type="hidden" id="functionId" name="functionId" value="${requestScope.function.functionId}"/>
			</td>
		</tr>				
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">功能路径：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="text"  id="url" name="url" size="20" value="${function.url}" maxlength="100"/>	<font color="red">*</font>	  	
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
