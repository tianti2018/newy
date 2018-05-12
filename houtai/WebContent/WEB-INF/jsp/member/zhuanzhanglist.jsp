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
		var fhMoney = frmzz.fhMoney.value;
		var loginName = frmzz.loginName.value;
		if (fhMoney=='') {
			alert("转账积分不能为空");
			frmzz.fhMoney.focus();
			return false;
		}
		else {
			
			var matchOne = /^\+?[1-9][0-9]*$/;
			if (!matchOne.test(fhMoney)) {
				alert("对不起，格式不正确，请输入正整数！");
				frmzz.fhMoney.focus();
				return false;
			}
		}
		if (loginName=='') {
			alert("转账用户Id不能为空");
			frmzz.loginName.focus();
			return false;
		}
		frmzz.submit();
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
		<div class="clear" align="left">
			<font size="4"  color="Red">我的总积分为:${totalMoney}</font>
		</div>
	</div>
	<div class="rhead" style="float:left;width:97%">	
	
	<form id ="frmzz" name="frmzz" action="user!zhuanzhang.action" class="ropt" method="post">
		转入积分:<input type="text"   id="fhMoney" name="fhMoney"/>
		转入会员编号:<input type="text"   id="loginName" name="loginName"/>
		<input type="button" value=" 转账  " onclick="check();" />&nbsp;<span style="color:red">注：100积分以上即可转让</span>
	</form>
	<div class="clear"></div>
</div>
	<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
		<thead class="pn-lthead">
		<tr>
			<th width="32">序号</th>
			<th width="35">会员编号</th>
			<th width="50">会员登录名称</th>
			<th width="50">会员姓名</th>
			<th width="150">描述</th>
			<th width="50">转账积分</th>
			
			<th width="80">创建日期</th>
		</tr>
		</thead>
		<tbody class="pn-ltbody">
		
			<c:forEach items="${litPager}" var="item" varStatus="status">
			<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
				<td>${status.index+1}</td>
				<td align="center">${item.user.userId}</td>
				<td align="center">${item.user.loginName}</td>
				<td align="center">${item.user.userName}</td>
				<td align="center">${item.memo}</td>
				<td align="center">${item.fhmoney}</td>
				
				<td align="right"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<%-- <td class="pn-lopt"><a href="user!initModifyUser.action?userId=${item.userId}&&currentPage=<s:property value="pager.currentPage"/>" class="pn-loperator">修改</a></td>
				<td class="pn-lopt"><a href="javascript:void(0);" onclick="deleteUser('${item.userId}');" class="pn-loperator">删除</a></td> --%>
			</tr>
		</c:forEach>   
		</tbody>
	</table>

  <!-- 导入分页组件  -->
   <c:if test="${not empty litPager}">
     <c:import url="/WEB-INF/jsp/page/page.jsp">
    	  <c:param name="pageActionUrl" value="user!initZhuanzhang.action"/>
     </c:import>
   </c:if>
   <c:if test="${empty litPager}">
   	<font color="red">查无资料 !!!</font>
   </c:if>

</div>
</body>
</html>
