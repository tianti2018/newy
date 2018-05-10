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
	var frmzz = null;
	function body_onload () {
		frmzz = document.frmzz;
		var message = "<c:out value="${message}"/>";
		
		if (message != "" && message!=null) {
			alert(message);					
		}
	}

		
	function deleteUser() {
		if(confirm('您确定删除吗？')) {
			window.open("user!deleteUser.action?userId="+arguments[0], "rightFrame");
			return true;	
		}
		return false;
	}
	
	function check() {
		/* var tixianMoney = frmzz.tixianMoney.value;
		if (tixianMoney=='') {
			alert("提现金额不能为空");
			frmzz.tixianMoney.focus();
			return false;
		}
		else {
			
			var matchOne = /^\+?[1-9][0-9]*$/;
			if (!matchOne.test(tixianMoney)) {
				alert("对不起，格式不正确，请输入正整数！");
				frmzz.tixianMoney.focus();
				return false;
			}
		}
		 */
		
		var datetime = new Date();
		var winSettings = "dialogHeight:500px;dialogWidth:700px;status:no;help:no";
		//var userId = arguments[0];
		var param = "?time=" + datetime;
		bid = window.showModalDialog("user!startTixian.action" + param, datetime,winSettings);
		window.location.reload();//
		
		//frmzz.submit();
	}
	
</script>


</head>
<body onload="body_onload();">
	<div class="body-box">
	<div class="rhead">
		<div class="rpos">您的当前位置: 提现记录</div>
		<div class="clear"></div>
	</div>
	<div class="rhead">
		<div class="clear" align="right">
			<font size="4"  color="Red">我的总积分为:${totalMoney}</font>
		</div>
	</div>
	<div class="rhead">	
	
	<form id ="frmzz" name="frmzz" action="user!apptixian.action" class="ropt" method="post">
		<input type="button" value=" 提现  " onclick="check();" />
	</form>
	<div class="clear"></div>
</div>
	<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
		<thead class="pn-lthead">
		<tr>
			<th width="32">序号</th>
			<th width="35">用户编号</th>
			<th width="50">用户登录名称</th>
			<th width="50">用户姓名</th>
			<th width="150">提现申请日期</th>
			<th width="100">提现金额</th>
			<th width="150">支付宝账号</th>
			<th width="100">提现状态</th>
			<th width="50">提现日期</th>
			
		</tr>
		</thead>
		<tbody class="pn-ltbody">
		
			<c:forEach items="${litPager}" var="item" varStatus="status">
			<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
				<td>${status.index+1}</td>
				<td align="center">${item.user.userId}</td>
				<td align="center">${item.user.loginName}</td>
				<td align="center">${item.user.userName}</td>
				<td align="center"><fmt:formatDate value="${item.appDate}" pattern="yyyy-MM-dd"/></td>
				<td align="center">${item.tixianMoney}</td>
				<td align="center">${item.account}</td>
				<td align="center">
					<c:if test="${item.type=='0'}">申请提现</c:if>
					<c:if test="${item.type=='1'}">提现成功</c:if>
				</td>
				<td align="center"><fmt:formatDate value="${item.tixianDate}" pattern="yyyy-MM-dd"/></td>
			</tr>
		</c:forEach>   
		</tbody>
	</table>

  <!-- 导入分页组件  -->
   <c:if test="${not empty litPager}">
     <c:import url="/WEB-INF/jsp/page/page.jsp">
    	  <c:param name="pageActionUrl" value="user!initTixian.action"/>
     </c:import>
   </c:if>
   <c:if test="${empty litPager}">
   	<font color="red">查无资料 !!!</font>
   </c:if>

</div>
</body>
</html>
