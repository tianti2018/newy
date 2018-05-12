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

	function activityUser() {
		self.location.href="user!activityUser.action?userId="+arguments[0];			
	}
	
	function recharge() {
		var textId = arguments[0];
		var textValue = document.getElementById(textId).value;
		var userId = arguments[1];
	  /* 	if (""==textValue) {
			alert("存值金额不能为空");
			document.getElementById(textId).focus();
			return false;
		}
		else {
			//var matchOne = /^\+?[1-9][0-9]*$/;
			var matchOne = /^(-|\+)?\d+$/;
			if (!matchOne.test(textValue)) {
				alert("对不起，格式不正确，请输入整数！");
				document.getElementById(textId).focus();
				return false;
			}
			self.location.href("user!recharge.action?userId="+userId+"&submitMoney="+textValue);
		} 
		 */
		self.location.href="user!recharge.action?userId="+userId+"&submitMoney="+textValue;
		
	}
	
	function deleteUser() {
		if(confirm('您确定删除该用户吗？')) {
			window.open("user!deleteUser.action?userId="+arguments[0], "rightFrame");
			return true;	
		}
		return false;
	}
	function blockUser() {
		if(confirm('您确定锁定该用户吗？')) {
			window.open("user!blockUser.action?userId="+arguments[0], "rightFrame");
			return true;	
		}
		return false;
	}
	function releaseUser() {
		if(confirm('您确定解锁该用户吗？')) {
			window.open("user!releaseUser.action?userId="+arguments[0], "rightFrame");
			return true;	
		}
		return false;
	}
	function isHTApp() {
		if(confirm('您确定该用户不回填吗？')) {
			window.open("user!isHTApp.action?userId="+arguments[0], "rightFrame");
			return true;	
		}
		return false;
	}
	
	function cleanSearch(){
		$("#input_userId").val("");
		$("#input_qUserName").val("");
		$("#input_qTjr").val("");
		$("#hLoginName").val("");
		$("#hUserName").val("");
		$("#hTjr").val("");
	}
	loadSearch =function () {
		$("#userId").val($("#input_userId").val());
		$("#hUserName").val($("#input_qUserName").val());
		$("#currentPage").val(1);
		gotoPage();
// 		self.location.href="user!listAllUser.action?loginName="+loginName;	
	}
	
	changeLevel = function () {
		if(confirm('您确定该用户改变享受待遇吗？')) {
			var submitMoney = document.getElementById(arguments[0]).value;
			self.location.href="user!changeLevel.action?userId="+arguments[1]+"&&submitMoney="+submitMoney;	
			return true;
		}
		return false;
	}
	
</script>
</head>
<body onload="body_onload();">
	<div class="body-box">
	<div class="rhead">
		<div class="rpos">您的当前位置: 财务管理-->销售结算</div>
		<div class="clear"></div>
	</div>
	<div class="rhead">
		<div class="clear" id="div_search">
			会员编号：<input type="text" id="input_userId" name="userId" value="${userId }"/>&nbsp;&nbsp;
			<%-- 推荐人昵称：<input type="text" id="input_qUserName" name="qUserName" value="${userName }"/>&nbsp;&nbsp; --%>
			<input type="button" id="cmdBtn" name="cmdBtn" value="搜索" style="cursor:pointer" onclick="loadSearch();" />&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" id="cmdBtn1" name="cmdBtn1" value="清空条件" style="cursor:pointer" onclick="cleanSearch();" />
		</div>
	</div>
	<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
		<thead class="pn-lthead">
		<tr>
			<th width="32">序号</th>
			<th width="100">推荐人编号</th>
			<th width="100">会员编号</th>
			<th width="80">会员昵称</th>
			<th width="80">总销售额</th>
			<th width="80">已结算额</th>
			<th width="80">未结算额</th>
			<th width="120">操作</th>
		</tr>
		</thead>
		<tbody class="pn-ltbody">
			<c:forEach items="${litPager}" var="item" varStatus="status">
			<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
				<td align="center">${status.index+1}</td>
				<td align="center">${item.referrerId}</td>
				<td align="center">${item.userId}</td>
				<td align="center"><c:choose><c:when test="${item.userName == '已取消关注'}"><span style="color:red;">已取消关注</span></c:when><c:otherwise>${item.userName}</c:otherwise></c:choose>    </td>
				<td align="center">${item.child1}</td>
				<td align="center">${item.child2}</td>
				<td align="center">${item.child3}</td>
				<td align="center"><input type="button" value="去结算" onclick="jiesuan('${item.userId}','${item.child3}');" /></td>
			</tr>
		</c:forEach>   
		</tbody>
	</table>

  <!-- 导入分页组件  -->
     <c:import url="/WEB-INF/jsp/page/page.jsp">
    	  <c:param name="pageActionUrl" value="user!listAllUser_new.action"/>
     </c:import>

</div>
<script>
//监听搜索区域的回车事件
$('#div_search').keydown(function(e){ 
	if(e.keyCode==13){ 
		loadSearch();//处理事件 
	} 
}); 
function jiesuan(userId,wjse){
	window.location.href="user!listAllOrders_new.action?userId="+userId+"&loginName="+wjse;
}

</script>
</body>
</html>
