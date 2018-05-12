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
		if(confirm("确定要处理吗?"))
			self.location.href="orders!dealApply.action?ordersId="+arguments[0];
	}
	function deleteReturn(){
		if(confirm("确定取消退货?"))
			self.location.href="orders!deleteReturn.action?ordersId="+arguments[0];
	}
	function cleanSearch(){
		$("#input_userId").val("");
		$("#input_qUserName").val("");
		$("#input_qOrderBH").val("");
		$("#input_qKuaidiNo").val("");
		$("#input_qMobile").val("");
		$("#input_qtoUserName").val("");
	}
	loadSearch =function () {
		$("#userId").val($("#input_userId").val());
		$("#hUserName").val($("#input_qUserName").val());
		$("#hOrdersBH").val($("#input_qOrderBH").val());
		$("#hKuaidiNo").val($("#input_qKuaidiNo").val());
		$("#hMobile").val($("#input_qMobile").val());
		$("#hToUserName").val($("#input_qtoUserName").val());
		$("#currentPage").val(1);
		gotoPage();
	}
	
</script>


</head>
<body onload="body_onload();">
	<div class="body-box">
	<div class="rhead">
		<div class="rpos">您的当前位置: 财务管理-->退货处理</div>
		<div class="clear"></div>
	</div>
	<div class="rhead">	
		<div class="clear" id="div_search1">
			<%-- 会员编号：<input type="text" id="input_userId" name="userId" value="${userId }"/>&nbsp;&nbsp;
			会员昵称：<input type="text" id="input_qUserName" name="userName" value="${userName }"/>&nbsp;&nbsp; --%>
			订单编号：<input type="text" id="input_qOrderBH" name="ordersBH" value="${ordersBH }"/>&nbsp;&nbsp;
			快递单号：<input type="text" id="input_qKuaidiNo" name="kuaidiNo" value="${kuaidiNo }"/>&nbsp;&nbsp;
			收货人姓名：<input type="text" id="input_qtoUserName" name="toUserName" value="${toUserName }"/>&nbsp;&nbsp;
			收货人电话：<input type="text" id="input_qMobile" name="qMobile" value="${mobile }"/>&nbsp;&nbsp;
			状态：
			<select id="st_qtuihuo"> 
				<option value="0">未处理</option>
				<option value="2">已处理</option>
			</select>
			<input type="button" id="cmdBtn" name="cmdBtn" value="搜索" style="cursor:pointer" onclick="loadSearch();" />&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" id="cmdBtn1" name="cmdBtn1" value="清空条件" style="cursor:pointer" onclick="cleanSearch();" />
		</div>
	</div>
	<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
		<thead class="pn-lthead">
		<tr>
			<th width="32">序号</th>
			<th width="35">订单编号</th>
			<th width="35">快递单号</th>
			<!-- <th width="35">提现编号</th>
			<th width="60">登录名称</th>
			<th width="50">姓名</th> -->
			<th width="40">收货人</th>
			<th width="40">收货人电话</th>
			
			<!-- <th width="100">银行名称</th>
			<th width="100">开户行地址</th>
			<th width="100">银行账户</th>
			<th width="100">账户持有人姓名</th> -->
			
			<th width="40">申请日期</th>
			<th width="60">退货数量</th>
			<th width="40">状态</th>
			<th width="60">处理日期</th>
			<th width="60">审核确认</th>
			
		</tr>
		</thead>
		<tbody class="pn-ltbody">
		
			<c:forEach items="${litPager}" var="item" varStatus="status">
			<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
				<td align="center">${status.index+1}</td>
				<input type="hidden" value="${item.arpId}"/>
				<td align="center">${item.orderBH}</td>
				<td align="center">${item.kuaiDiNum}</td>
				<%-- <td align="center">${item.txId}</td>
				<td align="center">${item.user.loginName}</td>
				<td align="center">${item.user.userName}</td> --%>
				<td align="center">${item.userName}</td>
				<td align="center">${item.phone}</td>
				
				<%-- <td align="center">${item.bankName}</td>
				<td align="center">${item.openBankAddr}</td>
				<td align="center">${item.account}</td>
				<td align="center">${item.accountHolder}</td> --%>
				
				<td align="center"><fmt:formatDate value="${item.applyTime}" pattern="yyyy-MM-dd"/></td>
				<td align="center">${item.returnNum}</td>
				<td align="center">
					<c:if test="${item.status=='0'||item.status==''||item.status==null}"><font color="red" size="2">未处理</font></c:if>
					<c:if test="${item.status=='1'}"><font color="green" size="2">已处理</font></c:if>
				</td>
				<td align="center"><fmt:formatDate value="${item.dealTime}" pattern="yyyy-MM-dd"/></td>
				<td align="center">
					<c:if test="${item.status=='0'||item.status==''||item.status==null}">
						<input type="button" value="处理" id="sh${status.index+1}" name="sh${status.index+1}" onclick="check('${item.arpId}');" />
						<input type="button" value="取消" id="qx${status.index+1}" name="qx${status.index+1}" onclick="deleteReturn('${item.arpId}');" />
					</c:if>
					<c:if test="${item.status=='2'}">
						已处理
					</c:if>
					
				</td>
			</tr>
		</c:forEach>   
		</tbody>
	</table>

  <!-- 导入分页组件  -->
     <c:import url="/WEB-INF/jsp/page/page.jsp">
    	  <c:param name="pageActionUrl" value="orders!listAllApplys.action"/>
     </c:import>
</div>
<script language="javascript" type="text/javascript">
$('#div_search1').keydown(function(e){ 
	if(e.keyCode==13){ 
		loadSearch();//处理事件 
	} 
}); 
	var type = '<%=request.getAttribute("status")%>' ;
	if(type=="null"){
		$("#st_qtuihuo  option[value='0'] ").attr("selected",true);
	}else
		$("#st_qtuihuo  option[value='"+type+"'] ").attr("selected",true);
</script>
</body>
</html>
