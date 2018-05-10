<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>list</title>


<link href="images/core_res/css/admin.css" rel="stylesheet"
	type="text/css" />
<link href="images/core_res/css/theme.css" rel="stylesheet"
	type="text/css" />

<script src="images/common_res/js/jquery.js" type="text/javascript"></script>

<script src="images/common_res/js/pony.js" type="text/javascript"></script>


<script language="javascript" type="text/javascript">
	function body_onload() {

		var message = "<c:out value="${message}"/>";

		if (message != "" && message != null) {
			alert(message);
		}
	}
</script>


</head>
<body onload="body_onload();">
	<div class="body-box">
		<div class="rhead">
			<div class="rpos">您的当前位置: 我的分红纪录</div>
			<div class="clear"></div>
		</div>
		<div class="rhead">
			<div class="clear" align="right">
				<font size="4" color="Red"> 我的总积分为:【${totalMoney}】</font>
			</div>
		</div>
		<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0"
			border="0">
			<thead class="pn-lthead">
				<tr>
					<th width="32">序号</th>
					<th width="50">会员登录名称</th>
					<th width="50">会员姓名</th>
					<th width="50">分红类型</th>
					<th width="150">描述</th>
					<th width="50">分红积分</th>

					<th width="80">创建日期</th>
				</tr>
			</thead>
			<tbody class="pn-ltbody">

				<c:forEach items="${list}" var="item" varStatus="status">
					<tr onmouseover="Pn.LTable.lineOver(this);"
						onmouseout="Pn.LTable.lineOut(this);"
						onclick="Pn.LTable.lineSelect(this);">
						<td>${status.index+1}</td>
						<td align="center">${item[0]}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<!-- 导入分页组件  -->
		<c:if test="${not empty litPager}">
			<c:import url="/WEB-INF/jsp/page/page.jsp">
				<c:param name="pageActionUrl"
					value="member!listAllFhRecordCount.action" />
				<c:param name="userId" value="${userId}" />
			</c:import>
		</c:if>
		<c:if test="${empty litPager}">
			<font color="red">查无资料 !!!</font>
		</c:if>

	</div>
</body>
</html>
