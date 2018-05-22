<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 

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
	<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script><title></title>
<base target="_self" />

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


	var frmOrder = null;
	function body_onload () {
		frmOrder = document.frmOrder;
		
		var message = "<c:out value="${message}"/>";
		
		if (message != "" && message!=null) {
			alert(message);
			if ("新增成功"==message && window.dialogArguments!='') {
				window.close();
				window.returnValue = "true";
			}
			
		}
		
	}
	
	function checkJinE() {
		var pw = $("#money").val();
		var matchPw = /^\d+(\.\d+)?$/;
		if (pw != "") {
			if (!matchPw.test(pw)) {
				$("#moneyspan").text("请填写正确数字");
				$("#money").focus();
				frmOrder.cmdButton.disabled = true;
				return false;
			}else{
				$("#moneyspan").text("");
				frmOrder.cmdButton.disabled = false;
				return true;
			}			
		}		
	}
	
	function checkShuLiang() {
		var pw = $("#shuliang").val();
		var matchPw = /^\d+(\.\d+)?$/;
		if (pw != "") {
			if (!matchPw.test(pw)) {
				$("#shuliangspan").text("请填写正确数字");
				$("#shuliang").focus();
				frmOrder.cmdButton.disabled = true;
				return false;
			}else{
				$("#shuliangspan").text("");
				frmOrder.cmdButton.disabled = false;
				return true;
			}			
		}		
	}
	
	<%--检查手机电话--%>
	function checkTel() {
		/* var tel = document.getElementById(arguments[0]).value; */
		var tel = $("#mobile").val();
		//var matchTel = /^(\d{3,4})\-{0,1}(\d{7,8})$/ ;
		var matchTel = /^[1][3,4,5,7,8][0-9]{9}$/ ;
		
	                       
		if (tel != "") {
			if (!matchTel.test(tel)) {
				/* alert("对不起，格式不正确，请重新输入！"); */
				/* document.getElementById(arguments[0]).focus(); */
				$("#phonespan").text("格式不正确");
				$("#mobile").focus();
				frmOrder.cmdButton.disabled = true;
				return false;
			}else{
				$("#phonespan").text("");
				frmOrder.cmdButton.disabled = false;
				return true;
			}			
			
		}		
	}
	
	function saveOrUpdate() {
		
		var toUserName = frmOrder.toUserName.value;
		var mobile = frmOrder.mobile.value;
		var shengCode = frmOrder.shengCode.value; 
		var chengshiCode = frmOrder.chengshiCode.value; 
		var diquCode = frmOrder.diquCode.value;
		var address = frmOrder.address.value;
		var money = frmOrder.money.value;
		var shuliang = frmOrder.shuliang.value;
		var size = frmOrder.size.value;
			
		if(checkspace(toUserName))  {		
			alert("对不起，收货人姓名不能为空，请重新输入！");
			frmOrder.toUserName.focus();
			return false;
		}
		if(checkspace(money))  {		
			alert("对不起，金额不能为空，请重新输入！");
			frmOrder.toUserName.focus();
			return false;
		}
		if(checkspace(shuliang))  {		
			alert("对不起，数量不能为空，请重新输入！");
			frmOrder.toUserName.focus();
			return false;
		}
		if(checkspace(size))  {		
			alert("对不起，计量单位不能为空，请重新输入！");
			frmOrder.toUserName.focus();
			return false;
		}
		
		if(checkspace(mobile))  {		
			alert("对不起，电话不能为空，请重新输入！");
			frmOrder.mobile.focus();
			return false;
		}
		if(checkspace(shengCode))  {		
			alert("对不起，省份不能为空，请重新输入！");
			frmOrder.shengCode.focus();
			return false;
		}
		if(checkspace(chengshiCode))  {		
				alert("对不起，城市不能为空，请重新输入！");
				frmOrder.chengshiCode.focus();
				return false;
		}
		 
		if(checkspace(diquCode))  {		
				alert("对不起，区域不能为空，请重新输入！");
				frmOrder.diquCode.focus();
				return false;
		}
		 
		if(checkspace(address))  {		
			alert("对不起，详细地址不能为空，请重新输入！");
			frmOrder.address.focus();
			return false;
		}
		 
		frmOrder.submit();
		frmOrder.cmdButton.disabled = true;
	}
	
	
	
	 //限制字符串长度
	function checkTextArea() {
       var area = $("#address");
       var text = area.value; 
       var len = text.length;
       if(len > 250) {
           $("#addressspan").text("输入的字符过长！");
           area.focus();
           frmOrder.cmdButton.disabled = true;
           return false;
       }else{
    	   $("#addressspan").text("");
    	   frmOrder.cmdButton.disabled = false;
    	   return true;
       }
       
    }

	function getTarea(type){
		var value = null;
		var ws = null;
		var obj = null;
		if(type==1){
			ws = $("#chengshiCode");
			obj = $("#shengCode");
			$("#sheng").val(obj[0].options[obj[0].selectedIndex].text);
		}else if(type==2){
			ws = $("#diquCode");
			obj = $("#chengshiCode");
			$("#chengshi").val(obj[0].options[obj[0].selectedIndex].text);
		}else if(type==3){
			obj = $("#diquCode");
			$("#diqu").val(obj[0].options[obj[0].selectedIndex].text);
		}
		value = obj.val();
		if(value!=null&&type!=3){
			$.ajax({
				url : "<%=request.getContextPath()%>/area!getTareaByCode.action",
		        type: "post", 
		        data : {"areaCode":value},
		        success: function(result) {
		     	   if(result!=null||result!=""){
		     		  ws.empty();
		     		  ws.append("<option value=''>请选择</option>");
		     		   for(var i=0;i<result.length;i++){
		     			  ws.append("<option value='"+result[i].areaCode+"'>"+result[i].areaName+"</option>");
		     		   }
		     	   }
		        },
		        error: function() {
		     	   alert("系统错误，请联系管理员！",0);
		        },
		    	beforeSend : function() {
		        }
		     });
		}
		
	}
	
</script>
 
</head>



<body onload="body_onload();">
<div class="body-box">
<div class="rhead">
		<div class="rpos">您的当前位置: 用户中心-->报单</div>
		<div class="clear"></div>
	</div>

<form id="frmOrder" name="frmOrder" method="post" action="orders!create.action" >

	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">收货人姓名：</td>
			<input type="hidden" id="ordersId" name="ordersId" value="${order.ordersId }"/>
			<input type="hidden" id="order_status" name="order_status" value="${order.order_status }"/>
		  <td width="80%" class="pn-fcontent">
		  	<input type="text" id="toUserName" value="${order.toUserName }" name="toUserName" size="20" maxlength="20"/><font color="red">*</font>
		  </td>
		</tr>
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">电话：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="text" id="mobile" value="${order.mobile }" name="mobile" size="11" maxlength="11" onblur="checkTel('mobile');"/>
		  	<font color="red">*<span id="phonespan"></span></font>
		  </td>
		</tr>
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">省份：</td>
			<input type="hidden" id="sheng" name="sheng" value="${order.sheng }"/>
		  <td width="80%" class="pn-fcontent">
		  	<select  id="shengCode" value="" name="shengCode" onchange="getTarea(1);">
		  		<option value="${order.shengCode }" selected="selected">${order.sheng }</option>
		  		<c:forEach items="${litTArea_sheng}" var="item" varStatus="status">
		  			<option value="${item.areaCode }">${item.areaName }</option>
		  		</c:forEach>
		  	</select>
		  	<font color="red">*</font>
		  </td>
		</tr>
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">城市：</td>
			<input type="hidden" id="chengshi" name="chengshi" value="${order.chengshi }"/>
		  <td width="80%" class="pn-fcontent">
		  	<select  id="chengshiCode" value="请选择" name="chengshiCode" onchange="getTarea(2);">
		  	<option value='${order.chengshiCode }' selected="selected">${order.chengshi }</option>
		  	</select>
		  	<font color="red">*</font>
		  </td>
		</tr>
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">区域：</td>
			<input type="hidden" id="diqu" name="diqu" value="${order.diqu }"/>
		  <td width="80%" class="pn-fcontent">
		  	<select  id="diquCode" name="diquCode" onchange="getTarea(3)">
		  	<option value='${order.diquCode }' selected="selected">${order.diqu }</option>
		  	</select>
		  	<font color="red">*</font>
		  </td>
		</tr>
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">详细地址：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="text" id="address" value="${order.address }" name="address" size="200" maxlength="200" style="width: 90%" onblur="checkTextArea()"/>
		  	<font color="red">*<span id="addressspan"></span></font>
		  </td>
		</tr>
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">邮编：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="text" id="zipcode" value="${order.zipcode }" name="zipcode" size="20" maxlength="20"/>
		  </td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">水果名称：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="text" id="pname" value="${order.pname }" name="pname" size="20" maxlength="20" />
		  	<font color="red">*</font>
		  </td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">数量：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="text" id="shuliang" value="${order.shuliang }" name="shuliang" size="20" maxlength="20" onblur="checkShuLiang()"/>
		  	<font color="red">*<span id="shuliangspan"></span></font>
		  </td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">单位：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="text" id="size" value="${order.size }" name="size" size="20" maxlength="20" />
		  	<font color="red">*</font>
		  </td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">金额：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="text" id="money" value="${order.money }" name="money" size="20" maxlength="20" onblur="checkJinE()"/>
		  	<font color="red">*<span id="moneyspan"></span></font>
		  </td>
		</tr>
		
		<tr>
			<td colspan="2" class="pn-fbutton">
			<input type="button" id="cmdButton" onclick="return saveOrUpdate();" value="保存" style="cursor:pointer;width: 100px;"/>&nbsp;&nbsp;&nbsp;<input type="reset" value="重置"/></td>
		</tr>
	</table>
</form>
</div>
</body>
</html>
