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
 
<base target="_self" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/base.css" />
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/family.css" />
	<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script><title></title>
<link href="images/common_res/css/jquery_validate.css" rel="stylesheet" type="text/css"/>
<link href="images/common_res/css/jquery.alerts.css" rel="stylesheet" type="text/css"/>
<link href="images/core_res/css/front.css" rel="stylesheet" type="text/css"/>
<link href="images/core_res/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="images/core_res/css/theme.css" rel="stylesheet" type="text/css"/>
 
<script src="images/common_res/js/jquery_validate.js" type="text/javascript"></script>
<script src="images/common_res/js/pony.js" type="text/javascript"></script>
<script src="images/common_res/js/jquery.ui.draggable.js" type="text/javascript"></script>
<script src="images/common_res/js/jquery.alerts.js" type="text/javascript"></script>
 
<script src="images/core_res/js/front.js" type="text/javascript"></script>
<script src="images/core_res/js/admin.js" type="text/javascript"></script>

<script src="js/common.js" type="text/javascript"></script>


<script type="text/javascript">


	var frmUser = null;
	function body_onload () {
		frmUser = document.frmUser;
		
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
		/* var pw = document.getElementById(arguments[0]).value; */
		var pw = $("#password").val();
		var matchPw = /^[\w!@#$%\^&\*\(\)_]{6,12}$/ ;
	                       
		if (pw != "") {
			if (!matchPw.test(pw)) {
				/* alert("对不起，密码长度为6到12位，请重新输入！"); */
				/* document.getElementById(arguments[0]).focus();  */
				$("#passwordspan").text("密码长度为6到12位");
				$("#password").focus();
				frmUser.cmdButton.disabled = true;
				return false;
			}else{
				$("#passwordspan").text("");
				frmUser.cmdButton.disabled = false;
				return true;
			}			
		}		
	}
	
	<%--检查手机电话--%>
	function checkTel() {
		/* var tel = document.getElementById(arguments[0]).value; */
		var tel = $("#phone").val();
		//var matchTel = /^(\d{3,4})\-{0,1}(\d{7,8})$/ ;
		var matchTel = /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/ ;
		
	                       
		if (tel != "") {
			if (!matchTel.test(tel)) {
				/* alert("对不起，格式不正确，请重新输入！"); */
				/* document.getElementById(arguments[0]).focus(); */
				$("#phonespan").text("格式不正确");
				$("#phone").focus();
				frmUser.cmdButton.disabled = true;
				return false;
			}else{
				$("#phonespan").text("");
				frmUser.cmdButton.disabled = false;
				return true;
			}			
			
		}		
	}
	
	function saveOrUpdate() {
		var userId = document.getElementById("userId").value;	
		
		
		var loginName = frmUser.loginName.value;
		var password = frmUser.password.value;
		var confirmPWD = frmUser.confirmPWD.value;
		var userName = frmUser.userName.value;
		var phone = frmUser.phone.value;
		var sheng = frmUser.sheng.value; 
		var shi = frmUser.shi.value; 
		var qu = frmUser.qu.value;
		var address = frmUser.address.value;
		if(checkspace(loginName))  {		
			alert("对不起，登录名不能为空，请重新输入！");
			frmUser.loginName.focus();
			return false;
		}
			
		if (userId == "" || userId == null) {
			if(checkspace(password))  {		
				alert("对不起，密码不能为空，请重新输入！");
				frmUser.password.focus();
				return false;
			}
			
			if(checkspace(confirmPWD))  {		
				alert("对不起，确认密码不能为空，请重新输入！");
				frmUser.confirmPWD.focus();
				return false;
			}
			if (password != confirmPWD) {
				alert("对不起，密码与确认密码不一致，请重新输入！");		
				return false;
			}
			
		}

		if(checkspace(userName))  {		
			alert("对不起，用户姓名不能为空，请重新输入！");
			frmUser.userName.focus();
			return false;
		}
		
		if(checkspace(phone))  {		
			alert("对不起，用户电话不能为空，请重新输入！");
			frmUser.phone.focus();
			return false;
		}
		if(checkspace(sheng))  {		
			alert("对不起，居住省不能为空，请重新输入！");
			frmUser.sheng.focus();
			return false;
		}
		if(checkspace(shi))  {		
				alert("对不起，居住市不能为空，请重新输入！");
				frmUser.shi.focus();
				return false;
		}
		 
		if(checkspace(qu))  {		
				alert("对不起，居住区不能为空，请重新输入！");
				frmUser.qu.focus();
				return false;
		}
		 
		if(checkspace(address))  {		
			alert("对不起，详细地址不能为空，请重新输入！");
			frmUser.address.focus();
			return false;
		}
		if(userId==null||userId=="")
			frmUser.action = "user!createUser.action";		
		else
			frmUser.action = "user!modifyUser.action";
		frmUser.submit();
		frmUser.cmdButton.disabled = true;
	}
	
	
	
	 //限制字符串长度
	function checkTextArea() {
       var area = $("#address");
       var text = area.value; 
       var len = text.length;
       if(len > 250) {
           $("#addressspan").text("输入的字符过长！");
           area.focus();
           frmUser.cmdButton.disabled = true;
           return false;
       }else{
    	   $("#addressspan").text("");
    	   frmUser.cmdButton.disabled = false;
    	   return true;
       }
       
    }

	function getTarea(type){
		var value = null;
		var ws = null;
		if(type==1){
			ws = $("#shi");
			value = $("#sheng").val();
		}else if(type==2){
			ws = $("#qu");
			value = $("#shi").val();
		}
		if(value!=null){
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
	
	function selectQu(){
		$("#quCode").val($("#qu").val());
	}
 
	function yanzheng(){
		var loginName=$("#loginName").val();
		//s = s.replaceAll("[^0-9a-zA-Z]","");
		var re = /[^a-zA-Z]/; 
		if(re.test(loginName)){
			$("#loginName").val("");
		}
	}
</script>
 
</head>



<body onload="body_onload();">
<div class="body-box">
<div class="rhead">
		<div class="rpos">您的当前位置: 用户中心-->个人资料</div>
		<div class="clear"></div>
	</div>

<form id="frmUser" name="frmUser" method="post" action="user!createUser.action" >

	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		
		<tr>
			<td width="30%" class="pn-flabel pn-flabel-h">登录名：</td>
			<td width="70%" class="pn-fcontent">
				<input type="text" id="loginName" name="loginName" value="${user.loginName }" size="20"  maxlength="12" onkeyup="yanzheng();">
				<input type="hidden" id="userId" name="userId" value="${user.userId }"/>
				<font color="red">*登录名必须为英文字母</font>				
			</td>
		</tr>
	
		<tr>
		<td width="20%" class="pn-flabel pn-flabel-h">密码：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="password"  id="password" name="password" value="${user.passWord }"  onblur="checkPw('password');" size="20" maxlength="20"/>
		  	<font color="red">*<span id="passwordspan"></span></font>
		  </td>
		</tr>

		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">确认密码：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="password" id="confirmPWD" name="confirmPWD" value="${user.passWord }"  size="20" maxlength="20" /><font color="red">*</font>	
		  </td>
		</tr>
		
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">姓名：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="text" id="userName" value="${user.userName }" name="userName" size="20" maxlength="20"/><font color="red">*</font>
		  </td>
		</tr>
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">电话：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="text" id="phone" value="${user.phone }" name="phone" size="20" maxlength="20" onblur="checkTel('phone');"/>
		  	<font color="red">*<span id="phonespan"></span></font>
		  </td>
		</tr>
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">居住省：</td>
		  <td width="80%" class="pn-fcontent">
		  	<select  id="sheng" value="" name="sheng" onchange="getTarea(1);">
		  		<option value="" selected="selected">请选择</option>
		  		<c:forEach items="${litTArea_sheng}" var="item" varStatus="status">
		  			<option value="${item.areaCode }">${item.areaName }</option>
		  		</c:forEach>
		  	</select>
		  	<font color="red">*</font>
		  </td>
		</tr>
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">居住市：</td>
		  <td width="80%" class="pn-fcontent">
		  	<select  id="shi" value="请选择" name="shi" onchange="getTarea(2);">
		  	<option value='' selected="selected">请选择</option>
		  	</select>
		  	<font color="red">*</font>
		  </td>
		</tr>
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">居住区域：</td>
			<input type="hidden" id="quCode" name="quCode" value=""/>
		  <td width="80%" class="pn-fcontent">
		  	<select  id="qu" name="qu" onchange="selectQu()">
		  	<option value='' selected="selected">请选择</option>
		  	</select>
		  	<font color="red">*</font>
		  </td>
		</tr>
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">详细地址：</td>
		  <td width="80%" class="pn-fcontent">
		  	<input type="text" id="address" value="${user.address }" name="address" size="200" style="width: 90%" maxlength="200" onblur="checkTextArea()"/>
		  	<font color="red">*<span id="addressspan"></span></font>
		  </td>
		</tr>
			
		<tr>
			<td colspan="2" class="pn-fbutton">
			<input type="button" id="cmdButton" onclick="return saveOrUpdate();" value="保存" style="cursor:pointer;width: 100px;"/></td>
		</tr>
	</table>
</form>
</div>
</body>
</html>
