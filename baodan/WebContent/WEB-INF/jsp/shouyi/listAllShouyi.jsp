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
	function guanbi(){
		var tabled= $("#trId");
		tabled.hide();
	}
	
	function mingxi(dianpuId){
		if(dianpuId!=null){
			window.open("shouyi!listAllShouyi.action?dianpuId="+dianpuId, null, null, null);
		}else{
			alert("没有店铺!");
		}
	}
	
	function chakanDianpu(dianpuId){
		if(dianpuId!=null&&dianpuId!=""){
			$.ajax({
				url : "<%=request.getContextPath()%>/member!searchMember.action",
		        type: "post", 
		        data : {"dianpuId":dianpuId,
		        		},
		        success: function(result) {
		     	   if(result!=null||result!=""){
		     		   var tabled= $("#trId");
		     		   tabled.html("");
		     		   var tr = '<table width="100%" border="1">'+
					     			'<tr>'+
							   			'<th width="50">用户ID</th>'+
							   			'<th width="80">用户姓名</th>'+
							   			'<th width="80">登录名称</th>'+
							   			'<th width="80">电话</th>'+
							   			'<th width="80">微信昵称</th>'+
							   			'<th width="80">微信头像</th>'+
							   			'<th width="80">头衔</th>'+
							   			'<th width="80">店铺ID</th>'+
							   			'<th width="80">状态</th>'+
							   			'<th width="80">关闭</th>'+
							   		'</tr>';
		     		   for(var i=0;i<result.length;i++){
		     			  var usery = result[i];
		     			  tr+='<tr>';
		     			  tr+='<td align="center">'+usery.id+'</td>'+
		     						'<td align="center">'+usery.userName +'</td>'+
		     						'<td align="center">'+usery.loginName +'</td>'+
		     						'<td align="center">'+usery.phone +'</td>'+
		     						'<td align="center">'+usery.useryname +'</td>'+
		     						'<td align="center">'+
		     						'	<img src="'+usery.headUrl +'" style="width: 50px"/>'+
		     						'</td>';
		     						
		     			tr+='<td align="center">';
		     			if(usery.level==0){
		     				tr+='准店小二';
		     			}else if(usery.level==1){
		     				tr+='店小二';
		     			}else if(usery.level==2){
		     				tr+='掌柜';
		     			}else if(usery.level==3){
		     				tr+='大掌柜';
		     			}
		     			
		     			tr+='</td>';
		     			tr+='<td align="center">'+usery.dianPuId +'</td>';
		     			tr+='<td align="center">';
		     			if(usery.subscribe==0){
		     				tr+='已关注</td>';
		     			}else 
		     				tr+='未关注</td>';
		     			
		     				tr+='<td align="center">'+
		 							'<input type="button" value="关闭" style="width:100px" onclick="guanbi();" /> '+
		 						'</td>';
		     			tr+='</tr>';
		     		   }
		     		  tr+='</table>';
		     		  tabled.append(tr);
		     		  tabled.show();
		     	   }
		     	   
		        },
		        error: function() {
		     	   alert("系统错误，请联系管理员！",0);
		        },
		    	beforeSend : function() {
		        }
		     });
		}else{
			alert("没有店铺!");
		}
	}
</script>


</head>
<body onload="body_onload();">
	<div class="body-box">
	<div class="rhead">
		<div class="rpos">您的当前位置: 财务管理-->用户收益明细</div>
		<div class="clear"></div>
	</div>
	<div class="rhead">
		<div class="rpos" >余额:<span id="zongJinE" style="color: red">${totalMoney}</span>元</div>
		<div class="clear"></div>
	</div>
	<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
		<thead class="pn-lthead">
		<tr>
			<th width="32">序号</th>
			<th width="80">订单编号</th>
			<th width="80">订单类型</th>
			<th width="80">查看店铺</th>
			<th width="80">金额</th>
			<th width="80">创建日期</th>
			<th width="80">提现状态</th>
			<th width="80">审核时间</th>
			<th width="80">备注</th>
			<th width="80">交易单号</th>
		</tr>
		</thead>
		<tbody class="pn-ltbody">
		
			<c:forEach items="${litPager}" var="item" varStatus="status">
			<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
				<td align="center">${status.index+1}</td>
				<td align="center" onclick="chakanDingdan(${item.ordersId},${item.orderType })">${item.ordersBh}</td>
				<td align="center">
					<c:if test="${item.orderType==0}">客官订单</c:if>
					<c:if test="${item.orderType==1}">小二订单</c:if>
				</td>
				<td align="center" onclick="chakanDianpu(${item.dianpuId})">${item.dianpuId}</td>
				<td align="center">${item.shouyi}</td>
				<td align="center"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></td>
				<td align="center">
					<c:if test="${item.tixian==1}">申请中</c:if>
					<c:if test="${item.tixian==2}">审核通过</c:if>
				</td>
				<td align="center"><fmt:formatDate value="${item.passDate}" pattern="yyyy-MM-dd"/></td>
				<td align="center">${item.beizhu}</td>
				<td align="center">${item.dakuanDan}</td>
			</tr>
		</c:forEach>   
		</tbody>
	</table>

  <!-- 导入分页组件  -->
   <c:if test="${not empty litPager}">
     <c:import url="/WEB-INF/jsp/page/page.jsp">
    	  <c:param name="pageActionUrl" value="shouyi!listAllShouyi.action"/>
     </c:import>
   </c:if>
   <c:if test="${empty litPager}">
   	暂无记录
   </c:if>
   <div style="display: none;position:fixed;right:0;top:20%;background-color:white;width:100%" id="trId" >
   
   
   </div>

</div>
</body>
</html>
