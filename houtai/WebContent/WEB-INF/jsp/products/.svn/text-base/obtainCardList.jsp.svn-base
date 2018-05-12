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

changeCard = function () {
		if(confirm('您确定要转给该会员存值卡吗？')) {
			var otherLoginName = document.getElementById(arguments[1]).value;
			if (""==otherLoginName) {
				alert('会员编号不能为空');
				return false;
			}
			self.location.href="card!changeCard.action?cardId="+arguments[0]+"&&otherLoginName="+otherLoginName;	
			return true;
		}
		return false;
	}
</script>
</head>
<body onload="body_onload();">
	<div class="body-box">
	<div class="rhead">
		<div class="rpos">您的当前位置: 财务中心-->充值卡号和密码</div>
		<div class="clear"></div>
	</div>
	<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
		<thead class="pn-lthead">
		<tr>
			<th width="100">充值卡号</th>
			<th width="100">充值密码</th>
			<th width="50">状态</th>
			
			<th width="80">充值日期</th>
			<th width="50">转账</th>
		</tr>
		</thead>
		<tbody class="pn-ltbody">
		
			<c:forEach items="${litPager}" var="item" varStatus="status">
			<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
				<td align="center">${item.cardNo}</td>
				<td align="center">${item.cardPassword}</td>
				<td>
					<c:if test="${item.cardStatus=='0'}">未使用</c:if>
					<c:if test="${item.cardStatus=='1'}">已使用</c:if>
				</td>
				
				<td align="center"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></td>
				<td>
					会员编号：<input type="text" value="" id="cmd${status.index}" />
					<input type="button" value="转送" onclick="changeCard('${item.cardId}','cmd${status.index}');" />
				</td>
			</tr>
		</c:forEach>   
		</tbody>
	</table>

  <!-- 导入分页组件  -->
   <c:if test="${not empty litPager}">
     <c:import url="/WEB-INF/jsp/page/page.jsp">
    	  <c:param name="pageActionUrl" value="card!obtainCard.action"/>
     </c:import>
   </c:if>
   <c:if test="${empty litPager}">
   	<font color="red">查无资料 !!!</font>
   </c:if>

</div>
</body>
</html>
