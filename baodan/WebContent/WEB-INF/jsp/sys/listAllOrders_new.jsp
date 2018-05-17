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
 


<script language="javascript" type="text/javascript">

	function body_onload () {
		
		var message = "<c:out value="${message}"/>";
		
		if (message != "" && message!=null) {
			alert(message);					
		}
	}

	function addNews() {
		self.location.href="news!initAdd.action";			
	}
			
	function confirmOrder() {
		if(confirm('您确定已经送货？')) {
			self.location.href="orders!appOrder.action?ordersId="+arguments[0];	
			return true;	
		}
		return false;
	}
	function cleanSearch(){
		$("#input_qLoginName").val("");
		$("#input_qUserName").val("");
		$("#hMobile").val("");
		$("#st_qOrderType").val("1");
		$("#hLoginName").val("");
		$("#hUserName").val("");
		$("#hOrderType").val("1");
		$("#input_qtoUserName").val("");
	}
	loadSearch =function () {
		$("#hLoginName").val($("#input_qLoginName").val());
		$("#hMobile").val($("#input_qMobile").val());
		$("#hToUserName").val($("#input_qtoUserName").val());
		$("#hUserName").val($("#input_qUserName").val());
		$("#hOrderType").val($("#st_qOrderType").val());
		$("#currentPage").val(1);
		gotoPage();
	}
	
	function winSd() {
		var datetime = new Date();
		var winSettings = "dialogHeight:500px;dialogWidth:700px;status:no;help:no";
		var param = "?time=" + datetime+"&ordersId="+arguments[0];
		bid = window.showModalDialog("orders!initkuaidi.action" + param, datetime,winSettings);
		window.open("orders!ordersList.action?currentPage="+<s:property value="pager.currentPage"/>,"rightFrame");
	}
	function winSdy() {
		var datetime = new Date();
		var winSettings = "dialogHeight:500px;dialogWidth:700px;status:no;help:no";
		var param = "?time=" + datetime+"&ordersId="+arguments[0];
		bid = window.showModalDialog("orders!beizhu.action" + param, datetime,winSettings);
		window.open("orders!ordersList.action?currentPage="+<s:property value="pager.currentPage"/>,"rightFrame");
	}
	function exportToExcel(){
		var $form = $("<form method='post' action='orders!exportToExcel.action'></form>");
	     
	    try{
	        var data = {
	        		orderType:$("#st_qOrderType").val(),
	        		toUserName:$("#input_qtoUserName").val(),
	        		mobile:$("#input_qMobile").val()
	        		};
	        for (var attr in data){
	         
	                $form.append("<input type='text' name='" + attr + "' value='" + data[attr] + "' />");
	        }
	        $("body").append($form);
	        $form.submit();
	    } finally{
	        if ($form)
	            $form.remove();
	    }
		//orders!exportToExcel.action;
	}
	function jiesuan(userId){
		window.location.href = "user!jiesuan.action?userId="+userId;
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
			<span>未结算额:${loginName}元</span>
			<input type="button" id="cmdBtn" name="cmdBtn" value="结算" style="cursor:pointer" onclick="jiesuan(${userId});" />&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		</div>
	<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
		<thead class="pn-lthead">
		<tr>
			<th width="32">序号</th>
			<th width="100">订单编号</th>
			<th width="60">下单人编号</th>
			<th width="100">收货人姓名</th>
			<th width="100">收货人电话</th>
			<th width="100">送货信息</th>
			<th width="100">订单状态</th>
			<th width="50">购买数量</th>
			<th width="120">下单日期</th>
			<th width="100">快递名称</th>
			<th width="100">快递编号</th>
		</tr>
		</thead>
		<tbody class="pn-ltbody">
			<c:forEach items="${litPaper}" var="item" varStatus="status">
			<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);">
				<td align="center">${status.index+1}</td>
				<td align="center">${item[13]}</td>
				<td align="center">${item[6]}</td>
				<td align="center">${item[5]}</td>
				<td align="center">${item[2]}</td>
				<td align="center" title="点击查看收货信息">
					<a href="javascript:void(0)" onclick="showShouhuo('${status.index}')">点击查看</a>
				</td>
				<td align="center">
					<c:if test="${item[3]=='0'}"><font size="2">未支付</font></c:if>
					<c:if test="${item[3]=='1'}"><font size="2">已支付</font></c:if>
					<c:if test="${item[3]=='3'}"><font size="2">已发货</font></c:if>
					<c:if test="${item[3]=='4'}"><font size="2">已完成</font></c:if>
					<c:if test="${item[3]=='5'}"><font size="2">已退货</font></c:if>
					<c:if test="${item[3]=='6'}"><font size="2">已收货</font></c:if>
				</td>
				<td align="center">${item[18]}</td>
				<td align="center"><fmt:formatDate value="${item[9]}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td align="center">
					<c:if test="${item[10]=='0'}">圆通</c:if>
		  			<c:if test="${item[10]=='1'}">顺丰</c:if>
			  		<c:if test="${item[10]=='2'}">申通</c:if>
			  		<c:if test="${item[10]=='3'}">中通</c:if>
			  		<c:if test="${item[10]=='4'}">韵达</c:if>
			  		<c:if test="${item[10]=='5'}">EMS</c:if>
			  		<c:if test="${item[10]=='6'}">宅急送</c:if>
			  		<c:if test="${item[10]=='7'}">全峰</c:if>
			  		<c:if test="${item[10]=='8'}">天天快递</c:if>
			  		<c:if test="${item[10]=='9'}">自提</c:if>
				</td>
				<td align="center">${item[11]}</td>
			</tr>
			<tr style="display:none" id="tr_${status.index }">
				<td colspan="13" align="left"  ><span style="color:red">收货信息：</span>地址: ${item[1] }，&nbsp;邮编: ${item[7] }，&nbsp;收货人: ${item[5] }，&nbsp;收货人电话: ${item[2] }</td>
			</tr>
		</c:forEach>   
		</tbody>
	</table>

	 <!-- 导入分页组件  -->
     <c:import url="/WEB-INF/jsp/page/page.jsp">
     	<c:param name="pageActionUrl" value="user!listAllOrders_new.action"/>
     </c:import>

</div>
<script src="images/common_res/js/jquery.js" type="text/javascript"></script>
<script src="images/common_res/js/jquery_validate.js" type="text/javascript"></script>
<script src="images/common_res/js/pony.js" type="text/javascript"></script>
<script src="images/common_res/js/jquery.ui.draggable.js" type="text/javascript"></script>
<script src="images/common_res/js/jquery.alerts.js" type="text/javascript"></script>
 
<script src="images/core_res/js/front.js" type="text/javascript"></script>
<script src="images/core_res/js/admin.js" type="text/javascript"></script>
<script>
$("#st_qOrderType").val('${orderType}');
function showShouhuo(i){
	$("#tr_"+i).toggle();
	if($("#trr_"+i).css("display")=="table-row")
		$("#trr_"+i).hide();
}
function showBeizhu(i){
	$("#trr_"+i).toggle();
	if($("#tr_"+i).css("display")=="table-row")
		$("#tr_"+i).hide();
}

//监听搜索区域的回车事件
$('#div_search').keydown(function(e){ 
	if(e.keyCode==13){ 
		loadSearch();//处理事件 
	} 
}); 
</script>
</body>
</html>
