<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/buttons.css">
<div class="panel panel-primary" id="loginline" style="display: none">
	<div class="panel-heading">
		<h3 class="panel-title">用户登录</h3>
	</div>
	<div class="panel-body">
		<div>
			<div class="form-group">
				手机号:<input style="width: 90%" class="form-control" id="loginPhone" name="loginName" 
					value=""/>
				密码:<input type="password" style="width: 90%" class="form-control"  id="password" name="passWord"/>
			</div>
		</div>
		<div style="margin:0px auto;text-align:center;">
			<a class="button button-pill button-primary" href="#" role="button"
				onclick="login();">登陆</a> 
			<a id="zhucea"	class="button button-pill button-primary" href="#zhuceinline" role="button"
				onclick="quzhuce();">去注册</a>
		</div>

	</div>
</div>
<div class="panel panel-primary" id="zhuceinline" style="display: none">
	<div class="panel-heading">
		<h3 class="panel-title">用户注册</h3>
	</div>
	<div class="panel-body">
		<div>
			<div class="form-group">
				姓名:<input style="width: 90%" class="form-control" id="userName" name="userName" 
					value=""/>
				手机号:<input style="width: 90%" class="form-control" id="loginPhone2" name="loginName" 
					value=""/>
				密码:<input type="password" style="width: 90%" class="form-control"  id="password2" name="passWord"/>
				确认密码:<input type="password" style="width: 90%" class="form-control"  id="password3" name="passWord2"/>
			</div>
		</div>
		<div style="margin:0px auto;text-align:center;">
			<a  class="button button-pill button-primary" href="#" role="button"
				onclick="zhuce();">注册</a> 
		</div>

	</div>
</div>
<script type="text/javascript">
function addressList() {
		if("${userVo==null}"=="true"){
			viewLogin();
		}else{
			self.location.href="<%=request.getContextPath()%>/orderAddress/orderAddressAction!orderAddress.action";
		}
	
}

function zhuce(){
	var userName = $("#userName").val().trim();
	var loginName = $("#loginPhone2").val().trim();
	var passWord = $("#password2").val().trim();
	var password3 = $("#password3").val().trim();
	var matchTel = /^[1][3,4,5,7,8][0-9]{9}$/ ;
	if(loginName ==null||loginName==""){
		alert("请填写登陆手机!");
		return;
	}
	if(userName ==null||userName==""){
		alert("请填写姓名!");
		return;
	}
	if(!matchTel.test(loginName)){
		alert("手机号码格式不对!");
		return;
	}
	if(loginName.length!=11){
		alert("手机号码格式不对!");
		return;
	}
	if(passWord==null||passWord==""){
		alert("请填写密码!");
		return;
	}
	if(password3==null||password3==""){
		alert("请填写确认密码!");
		return;
	}
	if(passWord!=password3){
		alert("密码与确认密码不一致!");
		return;
	}
	$.ajax({
        type:"POST",
        url:"<%=request.getContextPath()%>/user/userAction!zhuce.action",
        data : {
        	"loginName":loginName,
        	"userName":userName,
        	"passWord":passWord,
        	},
        dataType:"json",
        success:function(data) {
        	if(data.success){
        		window.location.reload(true);
        	}else{
        		alert(data.message);
        	}
        	
        }
	});
}
function viewZhuCe() {
	 $("#zhucea").fancybox({
	        'hideOnContentClick': true
	 });
}
function closefancybox() {
	jQuery.fancybox.close();
}

function viewLogin() {
	 $("#orderAdress").fancybox({
	        'hideOnContentClick': true
	 });
}
function viewZhuCe() {
	 $("#zhucea").fancybox({
	        'hideOnContentClick': true
	 });
}
function login(){
	var loginName = $("#loginPhone").val().trim();
	var passWord = $("#password").val().trim();
	var matchTel = /^[1][3,4,5,7,8][0-9]{9}$/ ;
	if(loginName ==null||loginName==""){
		alert("请填写登陆账号!");
		return;
	}
	if(!matchTel.test(loginName)){
		alert("手机号码格式不对!");
		return;
	}
	if(loginName.length!=11){
		alert("手机号码格式不对!");
		return;
	}
	if(passWord==null||passWord==""){
		alert("请填写密码!");
		return;
	}
	$.ajax({
        type:"POST",
        url:"<%=request.getContextPath()%>/user/userAction!login.action",
        data : {
        	"loginName":loginName,
        	"passWord":passWord,
        	},
        dataType:"json",
        success:function(data) {
        	if(data.success){
        		window.location.reload(true);
        	}else{
        		alert("用户名与密码不匹配!");
        	}
        	
        }
	});
}
function quzhuce(){
	closefancybox();
	viewZhuCe();
}

function jianceDenglu(){
	if("<%=session.getAttribute("loginUser")%>"=="null"){
		viewLogin();
	}else{
		self.location.href="<%=request.getContextPath()%>/user/userAction!gotoPersonalCenter.action";
	}
}
</script>