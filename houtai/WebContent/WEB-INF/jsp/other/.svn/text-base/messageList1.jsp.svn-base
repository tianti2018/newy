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

	function addMessage() {
		self.location.href="message!initAdd.action";			
	}
			
	function deleteMessage() {
		if(confirm('您确定删除吗？')) {
			self.location.hre="message!delete.action?messageId="+arguments[0];	
			return true;	
		}
		return false;
	}
	
	loadSearch =function () {
		var loginName = document.getElementsByName("cmd_search")[0].value;
		self.location.href="message!listAll1.action?fromUserId="+loginName;	
	}
	
</script>

</head>
<body onload="body_onload();">
	<div class="body-box">
	<div class="rhead">
		<div class="rpos">您的当前位置: 信息管理-->留言管理</div>
		<div class="clear"></div>
	</div>
	<div class="rhead">
		<div class="clear">
			<input type="text" id="cmd_search" name="cmd_search" />
			<input type="button" id="cmdBtn" name="cmdBtn" value="搜索" onclick="loadSearch();" />
		</div>
		<div class="clear"></div>
	</div>
	<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
		<thead class="pn-lthead">
		<tr>
			<th width="32">序号</th>
			<th width="100">留言人</th>
			<th width="100">留言标题</th>		
			<th width="400">留言内容</th>
			<th width="100">创建日期</th>
			<th width="100">回复内容</th>
			<th width="100">回复日期</th>
			<th width="100">回复人员</th>
			<th width="64">回复</th>
			<th width="64">删除</th>
		</tr>
		</thead>
		<tbody class="pn-ltbody">
			<c:forEach items="${litPager}" var="item" varStatus="status">
			<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);">
				<td>${status.index+1}</td>
				<td align="center">${item.fromUser.loginName}</td>
				<td align="center">${item.title}</td>
				<td align="center">${item.contents}</td>
				<td align="center"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>	
				
				<td align="center">${item.replyContents}</td>
				<td align="center"><fmt:formatDate value="${item.replyDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td align="center">${item.toUser.loginName}</td>				
				<td class="pn-lopt"><a href="message!initReply.action?messageId=${item.messageId}&&currentPage=<s:property value="pager.currentPage"/>" class="pn-loperator">回复</a></td>
				<td class="pn-lopt"><a href="javascript:void(0);" onclick="deleteMessage('${item.messageId}');" class="pn-loperator">删除</a></td>
			</tr>
		</c:forEach>   
		</tbody>
	</table>

	 <!-- 导入分页组件  -->
    <c:if test="${not empty litPager}">
     <c:import url="/WEB-INF/jsp/page/page.jsp">
     	<c:param name="pageActionUrl" value="message!listAll1.action"/>
     </c:import>
    </c:if>
    <c:if test="${empty litPager}">
    		<font color="red">查无资料 !!!</font>
    </c:if>

</div>

</body>
</html>
