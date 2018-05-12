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
	function check() {
		
		if(parseFloat(arguments[1])>parseFloat(arguments[2])){
			alert("可提现金额已不足!");
		}else {
			if(confirm("确定审核通过吗?"))
				self.location.href="user!tixiansh.action?txId="+arguments[0];
		}
	}
	loadSearch =function () {
		$("#htixianType").val($("#st_qtixian").val());
		$("#currentPage").val(1);
		gotoPage();
	}
</script>


</head>
<body onload="body_onload();">
	<div class="body-box">
	<div class="rhead">
		<div class="rpos">您的当前位置: 财务管理-->提现审核</div>
		<div class="clear"></div>
	</div>
	<div class="rhead">	
		<div class="clear">
			提现状态：
			<select id="st_qtixian"> 
				<option value="0">未通过</option>
				<option value="1">已通过</option>
			</select>
			<input type="button" id="cmdBtn" name="cmdBtn" value="搜索" style="cursor:pointer" onclick="loadSearch();" />&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
	</div>
	<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
		<thead class="pn-lthead">
		<tr>
			<th width="32">序号</th>
			<th width="35">用户编号</th>
			<th width="80">用户昵称</th>
			<!-- <th width="35">提现编号</th>
			<th width="60">登录名称</th>
			<th width="50">姓名</th> -->
			<th width="40">手机</th>
			
			<!-- <th width="100">银行名称</th>
			<th width="100">开户行地址</th>
			<th width="100">银行账户</th>
			<th width="100">账户持有人姓名</th> -->
			
			<th width="40">提现申请日期</th>
			<th width="60">提现金额</th>
			<th width="60">可提现金额</th>
			<th width="60">支付宝账号</th>
			<th width="60">账户名</th>
			<th width="30">提现状态</th>
			<th width="50">提现日期</th>
			
			<th width="50">审核确认</th>
			
		</tr>
		</thead>
		<tbody class="pn-ltbody">
		
			<c:forEach items="${litPager}" var="item" varStatus="status">
			<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
				<td align="center">${status.index+1}</td>
				<input type="hidden" value="${item.txId}"/>
				<td align="center">${item.user.userId}</td>
				<td align="center">${item.user.userName}</td>
				<%-- <td align="center">${item.txId}</td>
				<td align="center">${item.user.loginName}</td>
				<td align="center">${item.user.userName}</td> --%>
				<td align="center">${item.phone}</td>
				
				<%-- <td align="center">${item.bankName}</td>
				<td align="center">${item.openBankAddr}</td>
				<td align="center">${item.account}</td>
				<td align="center">${item.accountHolder}</td> --%>
				
				<td align="center"><fmt:formatDate value="${item.appDate}" pattern="yyyy-MM-dd"/></td>
				<td align="center">${item.tixianMoney}</td>
				<td align="center">${item.user.totalMoney}</td>
				<td align="center">${item.account}</td>
				<td align="center">${item.accountHolder}</td>
				<td align="center">
					<c:if test="${item.type=='0'||item.type==''||item.type==null}"><font color="red" size="2">未通过</font></c:if>
					<c:if test="${item.type=='1'}"><font color="green" size="2">已通过</font></c:if>
				</td>
				<td align="center"><fmt:formatDate value="${item.tixianDate}" pattern="yyyy-MM-dd"/></td>
				
				<td align="center">
					<c:if test="${item.type=='0'||item.type==''||item.type==null}">
						<input type="button" value="审核" id="sh${status.index+1}" name="sh${status.index+1}" onclick="check('${item.txId}','${item.tixianMoney}','${item.user.totalMoney}');" />
					</c:if>
					<c:if test="${item.type=='1'}">
						已审核
					</c:if>
					
				</td>
			</tr>
		</c:forEach>   
		</tbody>
	</table>

  <!-- 导入分页组件  -->
   <c:if test="${not empty litPager}">
     <c:import url="/WEB-INF/jsp/page/page.jsp">
    	  <c:param name="pageActionUrl" value="user!listAllTixian.action"/>
     </c:import>
   </c:if>
   <c:if test="${empty litPager}">
   	<c:import url="/WEB-INF/jsp/page/page.jsp">
    	  <c:param name="pageActionUrl" value="user!listAllTixian.action"/>
     </c:import>
   </c:if>

</div>
<script language="javascript" type="text/javascript">
	var type = '<%=request.getAttribute("type")%>' ;
	if(type=="null"){
		$("#st_qtixian  option[value='0'] ").attr("selected",true);
	}else
		$("#st_qtixian  option[value='"+type+"'] ").attr("selected",true);
</script>
</body>
</html>
