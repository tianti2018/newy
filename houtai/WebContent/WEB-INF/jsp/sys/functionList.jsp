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

<script language="javascript" type="text/javascript">

	function body_onload () {
		
		var message = "<c:out value="${message}"/>";
		
		if (message != "" && message!=null) {
			alert(message);					
		}
	}

	function addFunction() {
		self.location.href="function!initAdd.action";			
	}
			
	function deleteFunction() {
		if(confirm('您确定删除吗？')) {

			window.open("function!delete.action?functionId="+arguments[0], "rightIFrame");
			
			return true;	
		}
		return false;
	
	}
	
</script>


</head>
<body onload="body_onload();">
	<div class="body-box">
	<div class="rhead">
		<div class="rpos">您的当前位置: 功能管理</div>
		<div class="clear"></div>
	</div>
	<div class="rhead">
		<form class="ropt" method="post">
			
	&nbsp; <input type="button" value="新增功能" onclick="addFunction();"/>
			
		</form>
		<div class="clear"></div>
	</div>
	<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
		<thead class="pn-lthead">
		<tr>
			<th width="32">序号</th>
			<th width="100">功能编号</th>
			<th width="100">所属模块</th>
			<th width="100">功能名称</th>
			<th width="100">功能路径</th>			
			<th width="64">修改</th>
			<th width="64">删除</th>
		</tr>
		</thead>
		<tbody class="pn-ltbody">
		
			<c:forEach items="${litPager}" var="item" varStatus="status">
			<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);">
				<td>${status.index+1}</td>
				<td align="center">${item.functionId}</td>
				<td align="center">
					<c:if test="${item.module=='1'}">后台管理</c:if>
					<c:if test="${item.module=='2'}">信息管理</c:if>
					<c:if test="${item.module=='3'}">系统管理</c:if>
					<c:if test="${item.module=='4'}">财务管理</c:if>
				</td>
				<td align="center">${item.functionName}</td>		
				<td align="center">${item.url}</td>				
				<td class="pn-lopt"><a href="function!initModify.action?functionId=${item.functionId}&&currentPage=<s:property value="pager.currentPage"/>" class="pn-loperator">修改</a></td>
				<td class="pn-lopt"><a href="javascript:void(0);" onclick="deleteFunction('${item.functionId}');" class="pn-loperator">删除</a></td>
			</tr>
		</c:forEach>   
		</tbody>
	</table>

	 <!-- 导入分页组件  -->
    <c:if test="${not empty litPager}">
     <c:import url="/WEB-INF/jsp/page/page.jsp">
     	<c:param name="pageActionUrl" value="function!listAll.action"/>
     </c:import>
    </c:if>
    <c:if test="${empty litPager}">
    		<font color="red">查无资料 !!!</font>
    </c:if>

</div>
</body>
</html>
