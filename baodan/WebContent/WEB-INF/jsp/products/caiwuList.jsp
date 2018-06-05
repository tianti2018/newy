<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta name="apple-mobile-web-app-capable" content="yes"/>
	<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
	<meta name="format-detection" content="telephone=no"/>
 <link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/base.css" />
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/family.css" />
	<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>

<title>list</title>
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
		loadSearch();
	}

	function addNews() {
		self.location.href="orders!initAdd.action";			
	}
			
	function confirmOrder() {
		if(confirm('您确定已经送货？')) {
			self.location.href="orders!appOrder.action?ordersId="+arguments[0];	
			return true;	
		}
		return false;
	}
	function cleanSearch(){
		$("#input_qOrdersBH").val("");
		$("#input_qMobile").val("");
		$("#st_qOrderType").val("1");
		$("#input_qtoUserName").val("");
		$("#input_qoUserName").val("");
		$("#input_qMobile").val("");
		$("#j_fromDate").val("");
		$("#st_qDateType").val("1")
		$("#j_endDate").val("");
		$("#input_qPname").val("");
	}
	loadSearch =function () {
		$("#hOrdersBH").val($("#input_qOrdersBH").val());
		$("#hMobile").val($("#input_qMobile").val());
		$("#hToUserName").val($("#input_qtoUserName").val());
		$("#hOrderType").val($("#st_qOrderType").val());
		$("#hFromDate").val($("#j_fromDate").val());
		$("#hEndDate").val($("#j_endDate").val());
		$("#hinput_qoUserName").val($("#input_qoUserName").val());
		$("#hinput_qoPhone").val($("#input_qoPhone").val());
		$("#hinput_DateType").val($("#st_qDateType").val());
		$("#hinput_pname").val($("#input_qPname").val());
		$("#currentPage").val(1);
		gotoPage();
	}
	
	function winSd() {
		self.location.href="orders!initkuaidi.action?ordersId="+arguments[0];	
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
	        		mobile:$("#input_qMobile").val(),
	        		oUserName:$("#input_qoUserName").val(),
	        		oPhone:$("#input_qoPhone").val(),
	        		ordersBH:$("#input_qOrdersBH").val(),
	        		
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
	//orders!importExcel.action;
	function importExcel(){
		self.location.href="orders!initImport.action";
	}
	function editOrder(ordersId){
		self.location.href="orders!initAdd.action?ordersId="+ordersId;
	}
	function deleteOrder(ordersId){
		self.location.href="orders!delete.action?ordersId="+ordersId;
	}
	function yichangOrder(ordersId){
		self.location.href="orders!gotoYichang.action?ordersId="+ordersId;
	}
	function zhifuOrder(ordersId){
		if(confirm('请再次确定单号及是否支付？')) {
			self.location.href="orders!zhifuOrder.action?ordersId="+ordersId;
			return true;	
		}
		return false;
	}
	function winCHZH(){
		self.location.href="orders!quchongzhi.action?ordersId="+arguments[0];
	}
	function shouhuo(ordersId){
		if(confirm('请再次确定是否收货？')) {
			self.location.href="orders!shouhuo.action?ordersId="+ordersId;
			return true;	
		}
		return false;
	}
	function paixu(paixu){
		self.location.href="orders!caiwuList.action?paixu="+paixu;
	}
	
</script>

</head>
<body onload="body_onload();">
	<div class="body-box">
	<div class="rhead">
		<div class="rpos">您的当前位置: 财务查询</div>
		<div class="clear"></div>
	</div>
	<div class="rhead">
		<div class="rpos" >总计金额:<span id="zongJinE" style="color: red">${totalMoney}</span>元</div>
		<div class="clear"></div>
	</div>
		<div class="rhead">
		<table class="pn-ltable" width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr><td align="center">订单编号：</td><td align="center"><input type="text" id="input_qOrdersBH" name="qOrdersBH" value="${ordersBH }"/></td></tr>
			<tr><td align="center">收货人电话：</td><td align="center"><input type="text" id="input_qMobile" name="qMobile" value="${mobile }"/></td></tr>
			<tr><td align="center">货物名称：</td><td align="center"><input type="text" id="input_qPname" name="pname" value="${pname }"/></td></tr>
			<tr><td align="center">收货人姓名：</td><td align="center"><input type="text" id="input_qtoUserName" name="toUserName" value="${toUserName }"/></td></tr>
	   		<c:if test="${adminUser}">
		   		<tr><td align="center">报单人姓名：</td><td align="center"><input type="text" id="input_qoUserName" name="oUserName" value="${oUserName }"/></td></tr>
				<tr><td align="center">报单人电话：</td><td align="center"><input type="text" id="input_qoPhone" name="oPhone" value="${oPhone }"/></td></tr>
	   		</c:if>
	   	<tr><td align="center"> 起日期: </td><td align="center">   
		    <input type="text" id="j_fromDate" name="j_fromDate" value="${fromDate}" readonly="readonly"/>
		    <img  onclick="WdatePicker({el:'j_fromDate',startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" src="js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"/> 
	   	</td></tr> 
	   	<tr><td align="center"> 止日期:</td><td align="center">
		    <input type="text" id="j_endDate" name="j_endDate" value="${endDate}" readonly="readonly"/> 
		    <img onclick="WdatePicker({el:'j_endDate',startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" src="js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"/>
		</td></tr> 
		<tr><td align="center">日期类型：</td><td align="center">
			<select id="st_qDateType" name ="dateType"> 
				<option value="0">下单日期</option>
				<option value="1">支付日期</option>
				<option value="3">发货日期</option>
				<option value="5">退货日期</option>
				<option value="6">收货日期</option>
			</select>
			<input type="button" id="cmdBtn6" name="cmdBtn6" value="正序" style="cursor:pointer" onclick="paixu(0);" />
			<input type="button" id="cmdBtn7" name="cmdBtn7" value="倒序" style="cursor:pointer" onclick="paixu(1);" />
		</td></tr> 
		<tr><td align="center">
			<c:if test="${adminUser ==false}">
				<input type="button" id="cmdBtn3" name="cmdBtn3" onclick="addNews();" value="报  单" style="cursor:pointer;width: 100px;" /> 
			</c:if>
		</td><td align="center">
			<input type="button" id="cmdBtn" name="cmdBtn" value="搜索" style="cursor:pointer;width: 100px;" onclick="searchClic();" />
			<input type="button" id="cmdBtn1" name="cmdBtn1" value="清空条件" style="cursor:pointer" onclick="cleanSearch();" />
		</td></tr>
		</table>
		</div>
		<c:if test="${session.shebei=='mobile' }">
		<c:forEach items="${litPager}" var="item" varStatus="status">
			<ul class="menu-list">
				<li><a href="javascript:;"><i class="arrows"></i><em>${item.ordersBH}</em></a>
					<ul class="sub-menu" >
						<li>收货人姓名：<em>${item.toUserName}</em></li>
						<li>收货人电话：<em>${item.mobile}</em></li>
						<li>产品名称：<em>${item.pname}</em></li>
						<c:if test="${item.order_status=='0'}"><li>订单状态：<em><font size="2">未支付</font></em></li></c:if>
						<c:if test="${item.order_status=='1'}"><li>订单状态：<em><font size="2">已支付</font></em></li></c:if>
						<c:if test="${item.order_status=='2'}"><li>订单状态：<em><font size="2">异常</font></em></li></c:if>
						<c:if test="${item.order_status=='3'}"><li>订单状态：<em><font size="2">已发货</font></em></li></c:if>
						<c:if test="${item.order_status=='4'}"><li>订单状态：<em><font size="2">已完成</font></em></li></c:if>
						<c:if test="${item.order_status=='5'}"><li>订单状态：<em><font size="2" style="color: red;">已退货</font></em></li></c:if>
						<c:if test="${item.order_status=='6'}"><li>订单状态：<em><font size="2">已收货</font></em></li></c:if>
						<c:if test="${item.order_status=='9'}"><li>订单状态：<em><font size="2">已删除</font></em></li></c:if>
						
						<li>购买数量：<em>${item.shuliang}${item.size}</em></li>
						<li>订单金额：<em>${item.money}</em></li>
						<li>收货地址：<em>${item.sheng }${item.chengshi }${item.diqu }${item.address }，邮编: ${item.zipcode }</em></li>
						<li>下单日期：<em><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></em></li>
						<li>支付日期：<em><fmt:formatDate value="${item.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/></em></li>
							<li>发货人：<em>${item.fromUserName}</em></li>
							<li>发货人电话：<em>${item.tel}</em></li>
							<li>发货时间：<em>${item.fahuoDate}</em></li>
							<li>快递名称：<em>${item.kuaidiName}</em></li>
							<li>快递单号：<em>${item.kuaidiNo}</em></li>
						<c:if test="${adminUser}">
							<li>报单人姓名：<em>${item.oUserName}</em></li>
							<li>报单人电话：<em>${item.oPhone}</em></li>
							
						</c:if>
						<li>备注信息：<em>${item.comments },处理时间:${item.dealDate },处理次数:${item.dealNum }</em></li>
					</ul>
				</li>
			</ul>
			</c:forEach>
			<c:import url="/WEB-INF/jsp/page/page.jsp">
     			<c:param name="pageActionUrl" value="orders!caiwuList.action"/>
     		</c:import>
     		</br>
     		</br>
     		</br>
     		</br>
     		</br>
     		</br>
			<footer class="footer">
				<div class="foot-nav">
				<c:if test="${adminUser}">
					<a href="<%=request.getContextPath()%>/orders!listYichang.action"><i class="foot-icon"><img src="<%=request.getContextPath()%>/images/i_buy.png" alt=""></i><span>异常订单</span></a>
				</c:if>
				<c:if test="${adminUser==false}">
					<a href="<%=request.getContextPath()%>/orders!initAdd.action"><i class="foot-icon"><img src="<%=request.getContextPath()%>/images/i_buy.png" alt=""></i><span>新增订单</span></a>
				</c:if>
				<a href="<%=request.getContextPath()%>/orders!ordersList.action" ><i class="foot-icon"><img src="<%=request.getContextPath()%>/images/i_orders.png" alt=""></i><span>我的订单</span></a>
				
				<a href="<%=request.getContextPath()%>/user!listAllUser.action" ><i class="foot-icon"><img src="<%=request.getContextPath()%>/images/i_family.png" alt=""></i><span>团队列表</span></a>
				
				<c:if test="${adminUser==false}">
					<a href="<%=request.getContextPath()%>/user!initAddUser.action" class="nowpage"><i class="foot-icon"><img src="<%=request.getContextPath()%>/images/i_family.png" alt=""></i><span>我的信息</span></a>
				</c:if>
				<c:if test="${adminUser}">
					<a href="<%=request.getContextPath()%>/orders!caiwuList.action" class="nowpage"><i class="foot-icon"><img src="<%=request.getContextPath()%>/images/i_erweima.png" alt=""/></i><span>对账查询</span></a>
				</c:if>
				</div>
			</footer>
			
		</c:if>
	<c:if test="${session.shebei=='pc' }">	
	<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
		<thead class="pn-lthead">
		<tr>
			<th width="5%">订单编号</th>
			<th width="5%">收货人姓名</th>
			<th width="5%">收货人电话</th>
			<th width="5%">产品名称</th>
			<th width="5%">订单状态</th>
			<th width="5%">购买数量</th>
			<th width="5%">订单金额</th>
			<th width="5%">发货人</th>
			<th width="5%">发货人电话</th>
			<th width="5%">快递名称</th>
			<th width="5%">快递编号</th>
			<th width="5%">报单人姓名</th>
			<th width="5%">报单人电话</th>
			<th width="5%">下单日期</th>
			<th width="5%">支付日期</th>
			<th width="5%">发货时间</th>	
			<th width="5%">收货日期</th>	
		</tr>
		</thead>
		<tbody class="pn-ltbody">
			<c:forEach items="${litPager}" var="item" varStatus="status">
			<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="showShouhuo('${status.index}')">
				<td align="center">${item.ordersBH}</td>
				<td align="center">${item.toUserName}</td>
				<td align="center">${item.mobile}</td>
				<td align="center">${item.pname}</td>
				<td align="center">
					<c:if test="${item.order_status=='0'}"><font size="2">未支付</font></c:if>
					<c:if test="${item.order_status=='1'}"><font size="2">已支付</font></c:if>
					<c:if test="${item.order_status=='2'}"><font size="2">异常</font></c:if>
					<c:if test="${item.order_status=='3'}"><font size="2">已发货</font></c:if>
					<c:if test="${item.order_status=='4'}"><font size="2">已完成</font></c:if>
					<c:if test="${item.order_status=='5'}"><font size="2" style="color: red;">已退货</font></c:if>
					<c:if test="${item.order_status=='6'}"><font size="2">已收货</font></c:if>
					<c:if test="${item.order_status=='9'}"><font size="2">已删除</font></c:if>
				</td>
				<td align="center">${item.shuliang}${item.size}</td>
				<td align="center">${item.money}</td>
				<td align="center">	${item.fromUserName}</td>
				<td align="center">	${item.tel}</td>
				
				<td align="center">	${item.kuaidiName}</td>
				<td align="center">${item.kuaidiNo}</td>
				<td align="center">	${item.oUserName}</td>
				<td align="center">	${item.oPhone}</td>
				<td align="center"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td align="center"><fmt:formatDate value="${item.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td align="center"><fmt:formatDate value="${item.fahuoDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td align="center"><fmt:formatDate value="${item.shouhuoDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			
			<tr style="display:none" id="tr_${status.index }">
				<td colspan="13" align="left"  ><span style="color:red">收货信息：</span>地址: 
				${item.sheng }${item.chengshi }${item.diqu }${item.address }，&nbsp;邮编: ${item.zipcode }，&nbsp;收货人: ${item.toUserName }，&nbsp;收货人电话: ${item.mobile }，
				&nbsp;&nbsp;备注信息：${item.comments }&nbsp;&nbsp;处理时间:${item.dealDate },处理次数:${item.dealNum }
				</td>
			</tr>
		</c:forEach>   
		</tbody>
	</table>
	
	 <!-- 导入分页组件  -->
     <c:import url="/WEB-INF/jsp/page/page.jsp">
     	<c:param name="pageActionUrl" value="orders!caiwuList.action"/>
     </c:import>
     </c:if>

</div>
<script src="images/common_res/js/jquery_validate.js" type="text/javascript"></script>
<script src="images/common_res/js/pony.js" type="text/javascript"></script>
<script src="images/common_res/js/jquery.ui.draggable.js" type="text/javascript"></script>
<script src="images/common_res/js/jquery.alerts.js" type="text/javascript"></script>
 
<script src="images/core_res/js/front.js" type="text/javascript"></script>
<script src="images/core_res/js/admin.js" type="text/javascript"></script>
<script>
$(function(){
	// 判断是否有sub-menu
	 if($("ul.sub-menu")){
		
			$(".menu-list>li>a").click(function(){
				var $sub_menu = $(this).next(".sub-menu"),
					sub_h = $sub_menu.find("li").length * 46;	//27px表示每个sub-menu 下面li的高度
				if($sub_menu.height() == 0){					//判断是否在可视区域
					$sub_menu.animate({"height":sub_h});		//添加动画
					$(this).find(".arrows").addClass("rotated");
				}else{
					$sub_menu.animate({"height":0});
					$(this).find(".arrows").removeClass("rotated");
				}
			})
		}else{
			return false;
		} 
	
})
$("#st_qOrderType").val('${order_status}');
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
var order_status = '<%=session.getAttribute("order_status")%>';
if(order_status != "null")
	$("#st_qOrderType  option[value='"+order_status+"'] ").attr("selected",true);
	
var st_qDateType = '<%=session.getAttribute("dateType")%>';
if(st_qDateType == "null")
	$("#st_qDateType  option[value='0'] ").attr("selected",true);
else
	$("#st_qDateType  option[value='"+st_qDateType+"'] ").attr("selected",true);
</script>
</body>
</html>
