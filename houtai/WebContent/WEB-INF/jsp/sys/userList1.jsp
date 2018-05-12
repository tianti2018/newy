<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>list</title>


<link href="images/core_res/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="images/core_res/css/theme.css" rel="stylesheet" type="text/css"/>
 
<script src="images/common_res/js/jquery.js" type="text/javascript"></script>

<script src="images/common_res/js/pony.js" type="text/javascript"></script>

 
<script language="javascript" type="text/javascript">

	function body_onload () {
		
		var message = "<c:out value="${message}"/>";
		
		if (message != "" && message!=null) {
			alert(message);					
		}
	}

	function adduser() {
		self.location.href="adminUser!initAddUser.action";			
	}
			
	function deleteUser() {
		if(confirm('您确定删除吗？')) {
			window.open("adminUser!deleteUser.action?userId="+arguments[0], "rightFrame");
			return true;	
		}
		return false;
	
	}
	
</script>


</head>
<body onload="body_onload();">
	<div class="body-box">
	<div class="rhead">
		<div class="rpos">您的当前位置: 系统管理-->用户管理</div>
		<div class="clear"></div>
	</div>
	<div class="rhead">
		<form class="ropt" method="post">
			
	&nbsp; <input type="button" value="新增用户" onclick="adduser();"/>
			
		</form>
		<div class="clear"></div>
	</div>
	<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
		<thead class="pn-lthead">
		<tr>
			<th width="32">序号</th>
			<th width="100">用户编号</th>
			<th width="100">用户登录名称</th>
			<th width="100">用户姓名</th>			
			<th width="100">用户角色</th>
			<th width="196">Email</th>
			<th width="300">备注</th>
			<th width="64">修改</th>
			<th width="64">删除</th>
		</tr>
		</thead>
		<tbody class="pn-ltbody">
		
			<c:forEach items="${litPager}" var="item" varStatus="status">
			<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
				<td>${status.index+1}</td>
				<td align="center">${item.userId}</td>
				<td align="center">${item.loginName}</td>
				<td align="center">${item.userName}</td>		
				<td align="center">${item.role.roleName}</td>
				<td align="center">${item.email}</td>
				<td align="center">
				    <c:if test="${fn:length(item.memo) >= 90}">${fn:substring(item.memo,0,90)}...</c:if>
		  	        <c:if test="${fn:length(item.memo) < 90}"> ${item.memo}</c:if>
				</td>
				<td class="pn-lopt"><a href="adminUser!initModifyUser.action?userId=${item.userId}&&currentPage=<s:property value="pager.currentPage"/>" class="pn-loperator">修改</a></td>
				<td class="pn-lopt"><a href="javascript:void(0);" onclick="deleteUser('${item.userId}');" class="pn-loperator">删除</a></td>
			</tr>
		</c:forEach>   
		</tbody>
	</table>

  <!-- 导入分页组件  -->
   <c:if test="${not empty litPager}">
     <c:import url="/WEB-INF/jsp/page/page.jsp">
    	  <c:param name="pageActionUrl" value="adminUser!listAllUser.action"/>
     </c:import>
   </c:if>
   <c:if test="${empty litPager}">
   	<font color="red">查无资料 !!!</font>
   </c:if>

</div>
</body>
</html>
