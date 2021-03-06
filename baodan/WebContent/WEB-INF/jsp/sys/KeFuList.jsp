<%-- 
/*
 * ----------------------------------------------------------
 * FILE         : categoryList
 * CREATEUSER   : zhy
 * CREATEDATE   : 2009/10/12
 * FILENAME     : categoryList.jsp
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
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jqModal.js"></script>
    <script type="text/javascript" src="js/jqDnR.js"></script>
    <script type="text/javascript" src="js/jqModal.litejva8.js"></script>

<script language="javascript" type="text/javascript">

	function body_onload () {
		
		var message = "<c:out value="${message}"/>";
		
		if (message != "" && message!=null) {
			alert(message);					
		}
	}

	function addKeFu() {
		self.location.href="kefu!initAdd.action";			
	}
			
	function deletekefu() {
		if(confirm('您确定删除吗？')) {
            alert("enter");
			self.location.href="kefu!delete.action?qqId="+arguments[0];				
			return true;	
		}
		return false;
	
	}

	
</script>


</head>
<body onload="body_onload();">
	<div class="body-box">
		<div class="rhead">
			<div class="rpos">您的当前位置: 客服管理</div>
			<div class="clear"></div>
		</div>
		<div class="rhead">
			<form class="ropt" method="post">			
				&nbsp; <input type="button" value="新增客服号码" onclick="addKeFu();"/>	
			</form>
			<div class="clear"></div>
		</div>
		<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
			<thead class="pn-lthead">
				<tr>
					<th align="center">序号</th>
					<th align="center">Id</th>		
					<th align="center">QQ号码</th>			
					<th align="center">修改</th>
					<th align="center">删除</th>
				</tr>
			</thead>
		    <tbody class="pn-ltbody">
			<c:forEach items="${litPager}" var="item" varStatus="status">
                   <tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);">
						<td>${status.index+1}</td>
						<td align="center">${item.qqId}</td>
						<td align="center">${item.qqNum}</td>			
						<td class="pn-lopt"><a href="kefu!initModify.action?qqId=${item.qqId}&&currentPage=<s:property value="pager.currentPage"/>" class="pn-loperator">修改</a></td>
					    <td class="pn-lopt"><a href="javascript:void(0);" onclick="deletekefu('${item.qqId}');" class="pn-loperator">删除</a></td>
					</tr>
				</c:forEach>   
			</tbody>
		</table>
	
		<!-- 导入分页组件  -->
	  <c:if test="${not empty litPager}">
	    <c:import url="/WEB-INF/jsp/page/page.jsp">
	    	<c:param name="pageActionUrl" value="kefu!listAll.action"/>
	    </c:import>
	   </c:if>
	   <c:if test="${empty litPager}">
	   	 <font color="red">查无资料 !!!</font>
	   </c:if>
	   
	</div>
	
</body>
</html>
