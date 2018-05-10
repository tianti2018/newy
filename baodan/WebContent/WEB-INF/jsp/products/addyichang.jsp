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
	<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<title></title>
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
			if ('操作成功'==message && window.dialogArguments!='') {
				window.close();
				window.returnValue = "true";
			}
			
		}
		
	}
	
	<%--检查password--%>
	function checkPw() {
		var pw = document.getElementById(arguments[0]).value;
		
		var matchPw = /^[\w!@#$%\^&\*\(\)_]{6,12}$/ ;
	                       
		if (pw != "") {
			if (!matchPw.test(pw)) {
				alert("对不起，密码长度为6到12位，请重新输入！");
				document.getElementById(arguments[0]).focus();
				return false;
			}			
		}		
	}
	
	function saveOrUpdate() {
		var comments = document.getElementById("comments").value;	
		
		
		if(checkspace(comments))  {		
			alert("对不起，信息不能为空，请重新输入！");
			frmOrder.kuaidiNo.focus();
			return false;
		} 
			
		frmOrder.submit();
		frmOrder.cmdButton.disabled = true;
	}
 
</script>
 
</head>



<body onload="body_onload();">
<div class="body-box">
<div class="rhead">
		<div class="rpos">您的当前位置: 添加异常</div>
		<div class="clear"></div>
	</div>

<form id="frmOrder" name="frmOrder" method="post" action="orders!yichangOrder.action" >

	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		<tr>
			<input type="hidden" id="pictureUrl" name="pictureUrl"  value="${orders.pictureUrl}" />
			<td width="20%" class="pn-flabel pn-flabel-h">添加图片(目前还不能用)：</td>
			<td width="80%" class="pn-fcontent">
				<form action="orders!addpic.action" method="post" enctype="multipart/form-data">
					文件：<input type="file" id="picname" name="picname" /></br>
     				<input type="submit" value="提交"/>
				</form>			
			</td>
		</tr>
		<tr>
			<input type="hidden" id="ordersId" name="ordersId"  value="${orders.ordersId}" />
			<td width="20%" class="pn-flabel pn-flabel-h">异常信息：</td>
			<td width="80%" class="pn-fcontent">
				<textarea id="comments" name="comments" >${orders.comments}</textarea>
				<font color="red">*</font>				
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
