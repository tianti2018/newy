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

	function shenghe() {
		var userId = arguments[0];
		self.location.href="member!appCenterSH.action?userId="+userId;
	}
	loadSearch =function () {
		var loginName = document.getElementsByName("cmd_search")[0].value;
		self.location.href="member!listAllappCenter.action?loginName="+loginName;	
	}
</script>

</head>
<body onload="body_onload();">
	<div class="body-box">
	<div class="rhead">
		<div class="rpos">您的当前位置:后台管理-->报单中心管理</div>
		<div class="clear"></div>
	</div>
	<div class="rhead">
		<div class="clear">
			<input type="text" id="cmd_search" name="cmd_search" />
			<input type="button" id="cmdBtn" name="cmdBtn" value="搜索" onclick="loadSearch();" />
		</div>
	</div>
	<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
		<thead class="pn-lthead">
		<tr>
			<th width="32">序号</th>
			<th width="100">用户登录名称</th>
			<th width="100">用户姓名</th>
			
			<th width="100">推荐人</th>
			<th width="100">创建日期</th>
			<th width="64">申请日期</th>
			<th width="60">申请状态</th>
			<th width="100">审核完成日期</th>
		</tr>
		</thead>
		<tbody class="pn-ltbody">
		
			<c:forEach items="${litPager}" var="item" varStatus="status">
			<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
				<td>${status.index+1}</td>
				<td align="center">${item.loginName}</td>
				<td align="center">${item.userName}</td>
				<td align="center">${item.referrer.loginName}</td>
				<td align="center"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td align="center"><fmt:formatDate value="${item.appDate}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td  align="center">
					<c:if test="${item.isAppCenter=='1'}">
						<input id="btn${status.index+1}" type="button" value="审核成为报单中心" onclick="return shenghe('${item.userId}');" /></c:if>
					<c:if test="${item.isAppCenter=='2'}"><font color="green">已成为报单中心</font></c:if>
				</td>
				<td align="center"><fmt:formatDate value="${item.shappDate}" pattern="yyyy-MM-dd"/></td>
			</tr>
		</c:forEach>   
		</tbody>
	</table>

  <!-- 导入分页组件  -->
   <c:if test="${not empty litPager}">
     <c:import url="/WEB-INF/jsp/page/page.jsp">
    	  <c:param name="pageActionUrl" value="member!listAllappCenter.action"/>
     </c:import>
   </c:if>
   <c:if test="${empty litPager}">
   	<font color="red">查无资料 !!!</font>
   </c:if>

</div>
</body>
</html>
