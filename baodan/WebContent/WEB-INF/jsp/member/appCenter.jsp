<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<link href="images/common_res/css/jquery_validate.css" rel="stylesheet" type="text/css"/>
<link href="images/common_res/css/jquery.alerts.css" rel="stylesheet" type="text/css"/>
<link href="images/core_res/css/front.css" rel="stylesheet" type="text/css"/>
<link href="images/core_res/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="images/core_res/css/theme.css" rel="stylesheet" type="text/css"/>
 
<script src="images/common_res/js/jquery.js" type="text/javascript"></script>
<script src="images/common_res/js/jquery_validate.js" type="text/javascript"></script>
<script src="images/common_res/js/pony.js" type="text/javascript"></script>
<script src="images/common_res/js/jquery.ui.draggable.js" type="text/javascript"></script>
<script src="images/common_res/js/jquery.alerts.js" type="text/javascript"></script>
 
<script src="images/core_res/js/front.js" type="text/javascript"></script>
<script src="images/core_res/js/admin.js" type="text/javascript"></script>

<script src="js/common.js" type="text/javascript"></script>

<script type="text/javascript">

var frmAppe = null;
function body_onload () {

	frmAppe = document.frmAppe;
	var message = "<c:out value="${message}"/>";
	if (message != "" && message!=null) {
		alert(message);					
	}
}

function app()
{
	var areaId = document.getElementById("areaId1").value;
	var cardNo = document.getElementById("cardNo").value;
	var cardPassword = document.getElementById("cardPassword").value;
	
	if (areaId==-1) {
		alert("服务中心不能为空");
		return false;
	}
	if (cardNo=="") {
		alert("卡号不能为空");
		return false;
	}
	if (cardPassword=="") {
		alert("卡密不能为空");
		return false;
	}
	
	//self.location.href("member!appcenter.action");
	frmAppe.action = "member!appcenter.action";		
	frmAppe.target="rightFrame";
	frmAppe.submit();	
}

function saveOrUpdate() {
	var WIDtotal_fee = frmAppe.WIDtotal_fee.value;
	
	if (WIDtotal_fee=='') {
			alert("付款金额不能为空");
			frmAppe.WIDtotal_fee.focus();
			return false;
	}
		else {
			frmAppe.action = "alipayapi.jsp";			
			frmAppe.submit();
			frmAppe.cmdButton.disabled = true;
	}
}

function getCardByUserId() {
	$.ajax ({
		url : "joson!getCardByUserId.action",
		type : "post",		
		dataType : "json",			
		data : "cardType=5",
		success : 	function(jsonString) {
		if (jsonString.success) {
			document.getElementById("cardNo").value=jsonString.cardNo;
			document.getElementById("cardPassword").value=jsonString.cardPassword;
		}
		else {
			alert('请购买存值卡号和存值密码');
		}
		}				
	});
	
}

function getchange() {
	var selectId = arguments[0]+1;
	if ($("#areaId"+arguments[0]).val()==-1) {
		if (arguments[0]==1) {
// 			$("#areaId2").empty();
// 			$("#areaId3").empty();
// 			var opt1 = $("<option/>").text("请选择").attr("value", -1);
// 			var opt2 = $("<option/>").text("请选择").attr("value", -1);
// 			$("#areaId2").append(opt1);  
// 			$("#areaId3").append(opt2); 
		}
		else {
// 			$("#areaId3").empty();
// 			var opt = $("<option/>").text("请选择").attr("value", -1);
// 			$("#areaId3").append(opt);  
		}
	}
// 	$.ajax ({
// 		url : "joson!getAreaListByAreaCode.action",
// 		type : "post",		
// 		dataType : "json",			
// 		data : "areaId="+document.getElementById("areaId"+arguments[0]).value,
// 		success : 	function(jsonString) {
// 			if (jsonString.success) {
// 				var object = jsonString.list;
// 				$("#areaId"+selectId).empty();
// 				var opt = $("<option/>").text("请选择").attr("value", -1);
// 				 $("#areaId"+selectId).append(opt);  
// 				for (var i=0;i<object.length;i++) {
// 					var tarea = object[i];
// 					var areaId = tarea.areaId;
// 					var areaName = tarea.areaName;
// 					var opt = $("<option/>").text(areaName).attr("value", areaId);
// 					 $("#areaId"+selectId).append(opt);  
// 				}
// 			}
// 			else {
// 				alert('请购买存值卡号和存值密码');
// 			}
// 		}				
// 	});
	
	
	
}

</script>
 
</head>


<body onload="body_onload();">
<div class="body-box">
<div class="rhead">
		<div class="rpos">您的当前位置: 会员管理-->服务中心申请</div>
		<div class="clear"></div>
	</div>
<div class="rhead">	
	<div class="clear"></div>
</div>
<form id="frmAppe" name="frmAppe" method="post" target="_blank">

	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">用户登录名：</td>
			<td width="80%" class="pn-fcontent">
				${requestScope.user.loginName}
			</td>
		</tr>
	
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">用户姓名：</td>
		  <td width="80%" class="pn-fcontent">
		  	${requestScope.user.userName}
		  </td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">手机：</td>
		  <td width="80%" class="pn-fcontent">
		  	${requestScope.user.phone}
		  </td>
		</tr>

		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">是否是服务中心：</td>
			<td width="80%" class="pn-fcontent">
				<c:if test="${requestScope.user.isAppCenter==null || requestScope.user.isAppCenter==''}">否</c:if>
				<c:if test="${requestScope.user.isAppCenter=='1'}"><font color="red">【${requestScope.user.tArea.areaName}】服务中心申请中...</font></c:if>
				<c:if test="${requestScope.user.isAppCenter=='2'}"><font color="green">恭喜您【${requestScope.user.tArea.areaName}】服务中心审核通过</font></c:if>
			</td>
		</tr>			
			<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">服务中心卡密：</td>
			<td width="80%" class="pn-fcontent">
					<input size="10" name="WIDtotal_fee" id="WIDtotal_fee" readonly="readonly" value="10000" />元
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="cmdButton" size="20" onclick="return saveOrUpdate();" value="&nbsp;&nbsp;&nbsp;支付&nbsp;&nbsp;&nbsp;">
				<font color="red">*(10000元/张)</font>	
			</td>
				
			</td>
		</tr>		
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">选择服务中心:</td>
			<td width="80%" class="pn-fcontent">
					<select id="areaId1" name="areaId1" onchange="getchange(1);">
						<option value="-1">请选择</option>
						<c:forEach items="${areas}" var="item" varStatus="status">
							<option value="${item.areaId}">
								${item.areaName}
							</option>
						</c:forEach>
					</select>
<%-- 					<select  id="areaId2" name="areaId2" onchange="getchange(2);"> --%>
<!-- 						<option value="-1">请选择</option> -->
<%-- 					</select> --%>
<%-- 					<select  id="areaId3" name="areaId3"> --%>
<!-- 					<option value="-1">请选择</option> -->
<%-- 					</select> --%>
			</td>
		</tr>	
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">卡号:</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" id="cardNo" name="cardNo" /><a href="#" onclick="return getCardByUserId();">使用已有充值卡号和密码</a>
			</td>
		</tr>	
			<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">卡密:</td>
			<td width="80%" class="pn-fcontent">
				<input type="password" id="cardPassword" name="cardPassword"/>
			</td>
		</tr>	
		
		<tr>
			<td colspan="2" class="pn-fbutton">
			<input type="button" id="cmdButton2" <c:if test="${requestScope.user.isAppCenter=='1' || requestScope.user.isAppCenter=='2'}">disabled</c:if> onclick="app();" value="申请成为服务中心"/>&nbsp;&nbsp;&nbsp;</td>
		</tr>
	</table>
	
	<div class="clear">
		</br></br><font color="green" size="4">审核通过的省级服务中心有：</font>
	</div>
	<div class="clear">
		<font size="2" color="green">
		<c:forEach items="${user2}" var="item">
			<c:if test="${item.tArea!=nul}">
				【${item.tArea.areaName}】&nbsp;&nbsp;&nbsp;
			</c:if>
		</c:forEach>
		</font>
	</div>
	
	<div class="clear">
		</br></br><font color="red">正在审核中的省级服务中心有：</font>
	</div>
	<div class="clear">
		<font size="2" color="red">
		<c:forEach items="${user1}" var="item">
			<c:if test="${item.tArea!=null}">
				【${item.tArea.areaName}】&nbsp;&nbsp;&nbsp;
			</c:if>
		</c:forEach>
		</font>
	</div>
</form>
</div>
</body>
</html>
