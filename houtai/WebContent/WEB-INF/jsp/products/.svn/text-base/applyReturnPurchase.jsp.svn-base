<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>list</title>
<!-- <link href="images/common_res/css/jquery_validate.css" rel="stylesheet" type="text/css"/>
<link href="images/common_res/css/jquery.alerts.css" rel="stylesheet" type="text/css"/> -->
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
	
	 function winSd(orderId,createDate) {
		 createDate = createDate.replace(new RegExp("-","gm"),"/");
		 createDate = createDate.substring(0,(createDate.length-2));
		 createDate = new Date(createDate).getTime();
		 var applyTime = new Date().getTime();
		 if((applyTime-createDate)>604800000){
			 alert("已超过7天无法退货!");
		 }else
			 if(confirm("确定退货物吗?")){
				 var applyBtn = $("#applyBtn");
					applyBtn.disabled = true;
					window.location.href="<%=request.getContextPath()%>/orders!applyReturn.action?ordersId="+orderId;
			 }
	} 
	
</script>
</head>
<body>
	<div class="body-box">
	<div class="rhead">
		<div class="rpos">您的当前位置: 后台管理-->申请退货</div>
		<div class="clear"></div>
	</div>
		<div class="rhead">
		<div class="clear" id="div_search">
			会员编号：<input type="text" id="input_userId" name="userId" value="${userId }"/>&nbsp;&nbsp;
			会员昵称：<input type="text" id="input_qUserName" name="userName" value="${userName }"/>&nbsp;&nbsp;
			订单编号：<input type="text" id="input_qOrderBH" name="ordersBH" value="${ordersBH }"/>&nbsp;&nbsp;
			快递单号：<input type="text" id="input_qKuaidiNo" name="kuaidiNo" value="${kuaidiNo }"/>&nbsp;&nbsp;
			收货人姓名：<input type="text" id="input_qtoUserName" name="toUserName" value="${toUserName }"/>&nbsp;&nbsp;
			收货人电话：<input type="text" id="input_qMobile" name="qMobile" value="${mobile }"/>&nbsp;&nbsp;
			<input type="button" id="cmdBtn" name="cmdBtn" value="搜索" style="cursor:pointer" onclick="loadSearch();" />&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" id="cmdBtn1" name="cmdBtn1" value="清空条件" style="cursor:pointer" onclick="cleanSearch();" />
		</div>
		</div>
	<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
		<thead class="pn-lthead">
		<tr>
			<th width="32">序号</th>
			<th width="100">订单编号</th>
			<th width="60">下单人编号</th>
			<th width="100">下单人昵称</th>
			<th width="100">收货人姓名</th>
			<th width="100">收货人电话</th>
			<th width="100">送货信息</th>
			<th width="100">订单状态</th>
			<th width="100">产品名称</th>
			<th width="100">产品首图</th>
			<th width="50">购买数量</th>
			<th width="50">订单金额</th>
			<!-- <th width="50">运费</th> -->
			<!-- <th width="50">男款颜色</th>
			<th width="50">男款尺码</th>
			<th width="50">女款颜色</th>
			<th width="50">女款尺码</th> -->
			<!-- <th width="120">订单类型</th> -->
			<th width="120">下单日期</th>
			<th width="100">快递名称</th>
			<th width="100">快递编号</th>
			<th width="100">退货</th>
			
		</tr>
		</thead>
		<tbody class="pn-ltbody">
			<c:forEach items="${litPager}" var="item" varStatus="status">
			<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);">
				<td>${status.index+1}</td>
				<td align="center">${item.ordersBH}</td>
				<td align="center">${item.user.userId}</td>
				<td align="center">${item.user.userName}</td>
				<td align="center">${item.toUserName}</td>
				<td align="center">${item.mobile}</td>
				<td align="center" title="点击查看收货信息">
					<a href="javascript:void(0)" onclick="showShouhuo('${status.index}')">点击查看</a>
				</td>
				<td align="center">
					<c:if test="${item.order_status=='0'}"><font size="2">未支付</font></c:if>
					<c:if test="${item.order_status=='1'}"><font size="2">已支付</font></c:if>
					<c:if test="${item.order_status=='3'}"><font size="2">已发货</font></c:if>
					<c:if test="${item.order_status=='4'}"><font size="2">已完成</font></c:if>
					<c:if test="${item.order_status=='5'}"><font size="2">已退货</font></c:if>
					<c:if test="${item.order_status=='6'}"><font size="2">已收货</font></c:if>
				</td>
				<td align="center">${item.pname}</td>
				<td align="center">
					<img src="${item.productImg}" style="width: 50px"/>
				</td>
				<td align="center">${item.size}</td>
				<td align="center">${item.money}</td>
				<%-- <td align="center">${item.color}</td>
				<td align="center">${item.s_man_s}</td>
				<td align="center">${item.s_wman_c}</td>
				<td align="center">${item.s_wman_s}</td> --%>
				<td align="center"><fmt:formatNumber type="number" value="${item.score}"  maxFractionDigits="2"/></td>
				<td align="center"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td align="center">
				<c:if test="${item.kuaidiName=='0'}">圆通</c:if>
		  		<c:if test="${item.kuaidiName=='1'}">顺丰</c:if>
		  		<c:if test="${item.kuaidiName=='2'}">申通</c:if>
		  		<c:if test="${item.kuaidiName=='3'}">中通</c:if>
		  		<c:if test="${item.kuaidiName=='4'}">韵达</c:if>
		  		<c:if test="${item.kuaidiName=='5'}">EMS</c:if>
		  		<c:if test="${item.kuaidiName=='6'}">宅急送</c:if>
		  		<c:if test="${item.kuaidiName=='7'}">全峰</c:if>
		  		<c:if test="${item.kuaidiName=='8'}">天天快递</c:if>
		  		<c:if test="${item.kuaidiName=='9'}">自提</c:if>
				</td>
				<td align="center">${item.kuaidiNo}</td>
				<td class="pn-lopt"><c:choose> <c:when test="${item.tel=='1'}"><font size="2">已申请退货</font></c:when><c:when test="${item.order_status=='2'}"><font size="2">已退货</font></c:when><c:when test="${item.order_status=='0'}"><font size="2">未支付</font></c:when><c:when test="${item.order_status=='4'}"><font size="2">已过期</font></c:when><c:otherwise><input id="applyBtn" type="button" onclick="winSd('${item.ordersId}','${item.createDate}');" value="申请退货"/></c:otherwise></c:choose> </td>
			</tr>
			<tr style="display:none" id="tr_${status.index }">
			<td colspan="13" align="left"  ><span style="color:red">收货信息：</span>地址: ${item.address }，&nbsp;邮编: ${item.zipcode }，&nbsp;收货人: ${item.toUserName }，&nbsp;收货人电话: ${item.mobile }</td>
			</tr>
		</c:forEach>   
		</tbody>
	</table>

	 <!-- 导入分页组件  -->
     <c:import url="/WEB-INF/jsp/page/page.jsp">
     	<c:param name="pageActionUrl" value="orders!applyReturnPurchase.action"/>
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
