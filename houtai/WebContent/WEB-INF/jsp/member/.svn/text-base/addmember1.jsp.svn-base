<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>会员注册</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<style type="text/css">
.rowDiv{ margin:10px 10px}
</style>
<script type="text/javascript">

	function body_onload () {
	var message = "<c:out value="${message}"/>";
		if (message != "" && message!=null) {
			alert(message);
			if ("新增成功"==message && window.dialogArguments!='') {
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
	
	function checkFrm() {
		
// 		var yqrbm = $("#input_yqrbm");
		var hydlm =$("#input_hydlm");
		var yjmm = $("#input_yjmm");
		var ejmm = $("#input_ejmm");
		var hyxm = $("#input_hyxm");
		var hydh = $("#input_hydh");
// 		var fwzxbm = $("#input_fwzxbm");
		
// 		if($.trim(yqrbm.val())==null||$.trim(yqrbm.val())=="")  {		
// 			alert("对不起，"+yqrbm.attr("placeholder")+"不能为空，请重新输入！");
// 			yqrbm.focus();
// 			return false;
// 		}
		if($.trim(hydlm.val())==null||$.trim(hydlm.val())=="")  {		
			alert("对不起，"+hydlm.attr("placeholder")+"不能为空，请重新输入！");
			hydlm.focus();
			return false;
		}
		if($.trim(yjmm.val())==null||$.trim(yjmm.val())=="")  {		
			alert("对不起，"+yjmm.attr("placeholder")+"不能为空，请重新输入！");
			yjmm.focus();
			return false;
		}
		
        
		if($.trim(ejmm.val())==null||$.trim(ejmm.val())=="")  {		
			alert("对不起，"+ejmm.attr("placeholder")+"不能为空，请重新输入！");
			ejmm.focus();
			return false;
		}
		var matchPw = /^[\w!@#$%\^&\*\(\)_]{6,12}$/ ;
		if (!matchPw.test(yjmm.val())) {
			alert("对不起，"+yjmm.attr("placeholder")+"长度6~12位，请重新输入！");
			yjmm.focus();
			return false;
		}	
		if (!matchPw.test(ejmm.val())) {
			alert("对不起，"+ejmm.attr("placeholder")+"长度6~12位，请重新输入！");
			ejmm.focus();
			return false;
		}	
		if($.trim(hyxm.val())==null||$.trim(hyxm.val())=="")  {		
			alert("对不起，"+hyxm.attr("placeholder")+"不能为空，请重新输入！");
			hyxm.focus();
			return false;
		}
		if($.trim(hydh.val())==null||$.trim(hydh.val())=="")  {		
			alert("对不起，"+hydh.attr("placeholder")+"不能为空，请重新输入！");
			hydh.focus();
			return false;
		}
// 		if($.trim(fwzxbm.val())==null||$.trim(fwzxbm.val())=="")  {		
// 			alert("对不起，"+fwzxbm.attr("placeholder")+"不能为空，请重新输入！");
// 			fwzxbm.focus();
// 			return false;
// 		}
		 
		$("#btn_submit").val("提交中");
		$("#btn_submit").attr("disabled","disabled"); 
		$("#frm_wxReg").submit();
	}
 
</script>
</head>
<body onload="body_onload()">
	<form action="<%= request.getContextPath()%>/user!createUser1.action" id="frm_wxReg"  method="post">
		<div class="container" style="width: 350px;">
			<div align="center"><h2>推荐新会员</h2></div>
		
			<div class="rowDiv">
				<label style="font-size: 12px; float: left; padding: 5px;">邀请人编码:&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<input type="text" class="form-control" readonly="readonly" style="width: 150px;" value='${tjrLoginName }'/>
			</div>
			<div class="rowDiv">
				<label style="font-size: 12px; float: left; padding: 5px;">会员登录名:&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<input type="text" class="form-control" style="width: 150px;"
					name="loginName" id="input_hydlm" placeholder="会员登录名" >
			</div>
			<div class="rowDiv">
				<label style="font-size: 12px; float: left; padding: 5px;">一级密码:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<input type="text" class="form-control" style="width: 150px;"
					name="password" id="input_yjmm" placeholder="一级密码" >
			</div>
			<div class="rowDiv">
				<label style="font-size: 12px; float: left; padding: 5px;">二级密码:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<input type="text" class="form-control" style="width: 150px;"
					name="password2" id="input_ejmm" placeholder="二级密码" >
			</div>
			<div class="rowDiv">
				<label style="font-size: 12px; float: left; padding: 5px;">会员姓名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<input type="text" class="form-control" name="userName" id="input_hyxm"
					style="width: 150px;" placeholder="会员姓名">
			</div>
			<div class="rowDiv">
				<label style="font-size: 12px; float: left; padding: 5px;">会员电话:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<input type="text" class="form-control" name="phone" id="input_hydh"
					style="width: 150px;" placeholder="会员电话">
			</div>

			<div class="rowDiv">
				<label style="font-size: 12px; float: left; padding: 5px;">服务中心编码:</label>
				<input type="text" class="form-control" readonly="readonly" style="width: 150px;" value='${tjrLoginName }'/>
			</div>
			<div class="rowDiv" style="margin-left:98px">
				<button type="button" id="btn_submit" onclick="checkFrm()" class="btn btn-success">&nbsp;&nbsp;确认注册&nbsp;&nbsp;</button>
			</div>
		</div>
		<input type="hidden"  name="referrerId" value='${tjrLoginName}'/>
		<input type="hidden"  name="centerId" value='${tjrLoginName}'/>
	</form>
</body>
</html>