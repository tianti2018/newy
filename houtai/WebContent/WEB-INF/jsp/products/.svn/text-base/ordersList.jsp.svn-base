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
<script src="js/My97DatePicker/WdatePicker.js"  type="text/javascript"></script>
 


<script language="javascript" type="text/javascript">
	function searchClic() {

		var fromDate = document.getElementById("j_fromDate").value;
		var j_endDate = document.getElementById("j_endDate").value;
		if (fromDate != "" && j_endDate!="") {
		if (fromDate > j_endDate) {
			alert("请确认起日期不能大于止日期");

			return false;
		}
		}
		loadSearch();
	
	}

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
		$("#input_qOrdersBH").val("");
		$("#input_qMobile").val("");
		$("#st_qOrderType").val("1");
		$("#input_qtoUserName").val("");
		$("#input_parentId").val("");
		$("#input_parentUserName").val("");
	
	
	}
	loadSearch =function () {
		$("#userId").val($("#input_userId").val());
		$("#parentId").val($("#input_parentId").val());
		$("#parentUserName").val($("#input_parentUserName").val());
		$("#hOrdersBH").val($("#input_qOrdersBH").val());
		$("#hMobile").val($("#input_qMobile").val());
		$("#hToUserName").val($("#input_qtoUserName").val());
		$("#hUserName").val($("#input_qUserName").val());
		$("#hOrderType").val($("#st_qOrderType").val());
		$("#hFromDate").val($("#j_fromDate").val());
		$("#hEndDate").val($("#j_endDate").val());
		$("#currentPage").val(1);
		gotoPage();
	}
	
	function winSd() {
		var datetime = new Date();
		var winSettings = "dialogHeight:500px;dialogWidth:700px;status:no;help:no";
		var param = "?time=" + datetime+"&ordersId="+arguments[0];
		bid = window.showModalDialog("orders!initkuaidi.action" + param, datetime,winSettings);
		window.open("orders!listAllOrderList.action?currentPage="+<s:property value="pager.currentPage"/>,"rightFrame");
	}
	function winSdy() {
		var datetime = new Date();
		var winSettings = "dialogHeight:500px;dialogWidth:700px;status:no;help:no";
		var param = "?time=" + datetime+"&ordersId="+arguments[0];
		bid = window.showModalDialog("orders!beizhu.action" + param, datetime,winSettings);
		window.open("orders!listAllOrderList.action?currentPage="+<s:property value="pager.currentPage"/>,"rightFrame");
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
	
	
</script>

</head>
<body onload="body_onload();">
	<div class="body-box">
	<div class="rhead">
		<div class="rpos">您的当前位置: 后台管理-->订单查询</div>
		<div class="clear"></div>
	</div>
		<div class="rhead">
		<div class="clear" id="div_search">
			推荐人编号：<input type="text" id="input_parentId" name="parentId" value="${parentId }"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			会员&nbsp;&nbsp;编号：<input type="text" id="input_userId" name="userId" value="${userId }"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			会员&nbsp;&nbsp;昵称：<input type="text" id="input_qUserName" name="userName" value="${userName }"/>
			<br />
			<br />
			订单&nbsp;&nbsp;编号：<input type="text" id="input_qOrdersBH" name="qOrdersBH" value="${ordersBH }"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			收货人电话：<input type="text" id="input_qMobile" name="qMobile" value="${mobile }"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			收货人姓名：<input type="text" id="input_qtoUserName" name="toUserName" value="${toUserName }"/>
			<br />
			<br />
	   	 起&nbsp;&nbsp;日&nbsp;&nbsp;期:    
	    <input type="text" id="j_fromDate" name="j_fromDate" value="${fromDate}" readonly="readonly"/>
	    <img  onclick="WdatePicker({el:'j_fromDate',startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" src="js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"/> 
	    &nbsp;&nbsp;&nbsp;止&nbsp;&nbsp;日&nbsp;&nbsp;期:
	    <input type="text" id="j_endDate" name="j_endDate" value="${endDate}" readonly="readonly"/> 
	    <img onclick="WdatePicker({el:'j_endDate',startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" src="js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"/>
	    &nbsp;&nbsp;&nbsp;&nbsp;<input name="button" type="button" onclick="return searchClic();" value="  查询  "/>
			订单状态：
			<select id="st_qOrderType"> 
				<option value="0">未支付</option>
				<option value="1">已支付</option>
				<option value="3">已发货</option>
				<option value="4">已完成</option>
				<option value="5">已退货</option>
				<option value="6">已收货</option>
			</select>
			<input type="button" id="cmdBtn" name="cmdBtn" value="搜索" style="cursor:pointer" onclick="loadSearch();" />&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" id="cmdBtn1" name="cmdBtn1" value="清空条件" style="cursor:pointer" onclick="cleanSearch();" />&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" id="cmdBtn2" name="cmdBtn2" onclick="exportToExcel();" value="导出到EXCEL" style="cursor:pointer" /> 
		</div>
		</div>
	<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
		<thead class="pn-lthead">
		<tr>
			<th width="32">序号</th>
			<th width="100">订单编号</th>
			<th width="100">推荐人编号</th>
			<th width="100">推荐人昵称</th>
			<th width="60">下单人编号</th>
			<th width="60">下单人昵称</th>
			<th width="100">收货人姓名</th>
			<th width="100">收货人电话</th>
			<th width="100">产品名称</th>
			<th width="100">产品首图</th>
			<th width="100">送货信息</th>
			<th width="100">订单状态</th>
			<th width="50">购买数量</th>
			<th width="50">订单金额</th>
			<!-- <th width="50">男款颜色</th>
			<th width="50">男款尺码</th>
			<th width="50">女款颜色</th>
			<th width="50">女款尺码</th> -->
			<!-- <th width="120">订单类型</th> -->
			<th width="120">下单日期</th>
			<th width="80">备注</th>
			<th width="80">查看备注</th>
			<c:if test="${userFlag>1}">
				<!-- <th width="80">是否送了石头</th> -->
				<th width="100">快递名称</th>
				<th width="100">快递编号</th>
			</c:if>
			<c:if test="${userFlag==1}">
				<th width="100">确认送货</th>
			</c:if>
			
			
		</tr>
		</thead>
		<tbody class="pn-ltbody">
			<c:forEach items="${litPager}" var="item" varStatus="status">
			<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);">
				<td>${status.index+1}</td>
				<td align="center">${item.ordersBH}</td>
				<td align="center"><a href="javascript:void(0);" onclick="getRefer('${item.user.referrer.userId}')">${item.user.referrer.userId}</a>    </td>
				<td align="center"><a href="javascript:void(0);" onclick="getRefer('${item.user.referrer.userId}')">${item.user.referrer.userName}</a></td>
				<td align="center">${item.user.userId}</td>
				<td align="center">${item.user.userName}</td>
				<td align="center">${item.toUserName}</td>
				<td align="center">${item.mobile}</td>
				<td align="center">${item.pname}</td>
				<td align="center"><img src="${item.productImg}" style="width: 50px"/></td>
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
				<td align="center">${item.size}</td>
				<td align="center">${item.money}</td>
				<%-- <td align="center">${item.color}</td>
				<td align="center">${item.s_man_s}</td>
				<td align="center">${item.s_wman_c}</td>
				<td align="center">${item.s_wman_s}</td> --%>
				<td align="center"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td class="pn-lopt"><a href="javascript:void(0);" onclick="winSdy('${item.ordersId}');" class="pn-loperator"><c:choose><c:when test="${item.comments==null}">添加备注</c:when><c:otherwise>修改备注</c:otherwise></c:choose></a></td>
				<td align="center"><a href="javascript:void(0)" onclick="showBeizhu('${status.index}')">查看备注</a></td>
				<c:if test="${userFlag>1}">
					<%-- <td align="center">
						<c:if test="${item.cardId==0||item.cardId==null}">否</c:if>
			  			<c:if test="${item.cardId==1}">是</c:if>
					</td> --%>
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
				</c:if>	
				<c:if test="${userFlag=='1'}">
					<td class="pn-lopt"><a href="javascript:void(0);" onclick="winSd('${item.ordersId}');" class="pn-loperator">确认送货</a></td>
				</c:if>	
			</tr>
			<tr style="display:none" id="tr_${status.index }">
				<td colspan="13" align="left"  ><span style="color:red">收货信息：</span>地址: ${item.address }，&nbsp;邮编: ${item.zipcode }，&nbsp;收货人: ${item.toUserName }，&nbsp;收货人电话: ${item.mobile }</td>
			</tr>
			<tr style="display:none" id="trr_${status.index }">
				<td colspan="13" align="left"  ><span style="color:red">备注信息：</span>${item.comments }</td>
			</tr>
		</c:forEach>   
		</tbody>
	</table>

	 <!-- 导入分页组件  -->
     <c:import url="/WEB-INF/jsp/page/page.jsp">
     	<c:param name="pageActionUrl" value="orders!listAllOrderList.action"/>
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

function getRefer(referId){
	$.ajax({
		url : "<%=request.getContextPath()%>/orders!getRefer.action",
        type: "post", 
        data : {"ordersId":referId},
        success: function(result) {
     	   if(result!=null||result!=""){
     		  alert("收货姓名:"+result.toUserName+"\n收货人电话:"+result.phone+"\n推荐人编号:"+result.refuserId+"\n推荐人昵称:"
     				  +result.refuserName);
     	   }
        },
        error: function() {
     	   alert("系统错误，请联系管理员！",0);
        },
    	beforeSend : function() {
        }
     });
}


//监听搜索区域的回车事件
$('#div_search').keydown(function(e){ 
	if(e.keyCode==13){ 
		loadSearch();//处理事件 
	} 
}); 
var order_status = '<%=request.getAttribute("order_status")%>';
if(order_status == "null")
	$("#st_qOrderType  option[value='1'] ").attr("selected",true);
else
	$("#st_qOrderType  option[value='"+order_status+"'] ").attr("selected",true);
</script>
</body>
</html>
