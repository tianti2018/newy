<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>用户登录</TITLE>
<LINK href="images2/Default.css" type=text/css rel=stylesheet>
<LINK href="images2/xtree.css" type=text/css rel=stylesheet>
<LINK href="images2/User_Login.css" type=text/css rel=stylesheet>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.6000.16674" name=GENERATOR>
</HEAD>
<script src="js/common.js" type="text/javascript"></script>
<script type="text/javascript">
function body_onload () {
	
	var message = "<c:out value="${message}"/>";
	
	if (message != "" && message!=null) {
		if ("-1" == message) {
			message = "该登录用户不存在！";
			alert(message);
		}
		else if ("12"== message) {
			message = "密码错误！";
			alert(message);
		}		
		else {
			alert(message);
		}
					
	}

	if ('${param.flag}'=='flush'){
		alert("session超时 或 还没有登录 请重新登录!!!");
		
		self.location.href="login.jsp?flag=1"; 	
		
	}	
	
	// 退出
	if ('${param.flag}'=='flushTwo'){		
		self.location.href="login2.jsp?flag=1"; 		
	}

	document.loginform.loginName.focus();
}
 
function check()
{
	if(checkspace(document.loginform.loginName.value))  {		
		alert("对不起，用户名不能为空，请重新输入！");
		document.loginform.loginName.focus();
		return false;
	}
	
	if(checkspace(document.loginform.password.value))  {		
		alert("对不起，密码不能为空，请重新输入！");
		document.loginform.password.focus();
		return false;
	}
	
	if(checkspace(document.loginform.random.value))  {		
		alert("对不起，验证码不能为空，请重新输入！");
		document.loginform.random.focus();
		return false;
	}
	document.loginform.submit();
}

//回车事件
function enterKey(event) {
	if(event.keyCode==13){     
	  return check();
	}
}

function changeValidateCode() {    

	//获取当前的时间作为参数，无具体意义    
	var timenow = new Date().getTime();    

	//每次请求需要一个不同的参数，否则可能会返回同样的验证码    
	//这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。    
	//obj.src="rand.action?d="+timenow;
	document.getElementById("imgRandm").src="rand.action?d="+timenow;  
} 

function winSd() {
	var datetime = new Date();
	var winSettings = "dialogHeight:500px;dialogWidth:700px;status:no;help:no";
	var param = "?time=" + datetime;
	bid = window.showModalDialog("user!initAddUser.action" + param, datetime,winSettings);
	window.location.reload();//
}
</script>
<%

		// 每次进入页面时都把session销毁
		request.getSession().invalidate();		
%>

<BODY id=userlogin_body onLoad="body_onload();">
 <form id="loginform"  name="loginform" action="login!doLogin.action" method="post" onSubmit="return check();">
	<DIV></DIV>

	<DIV id=user_login>
		<DL>
			<DD id=user_top>
				<UL>
					<LI class=user_top_l></LI>
					<LI class=user_top_c></LI>
					<LI class=user_top_r></LI>
				</UL>
			<DD id=user_main>
				<UL>
					<LI class=user_main_l></LI>
					<LI class=user_main_c>
						<DIV class=user_main_box>
							<UL>
								<LI class=user_main_text>用户名：</LI>
								<LI class=user_main_input>
									<INPUT class=TxtUserNameCssClass type="text" onkeypress="enterKey(event);" id=loginName maxLength=20 name=loginName>
								</LI>
							</UL>
							<UL>
								<LI class=user_main_text>密 码：</LI>
								<LI class=user_main_input><INPUT class=TxtPasswordCssClass
									id=password type=password name=password maxlength="20"></LI>
							</UL>
							<UL>
								<LI class=user_main_text>验证码：</LI>
								<LI class=user_main_input><input  id="random" name="random" type="text"   onkeypress="enterKey(event);"    maxlength="10" /></LI>
								<LI class=user_main_input>
                    	 			<img   id="imgRandm" src="rand.action" title="点击图片刷新验证码" onclick="changeValidateCode('imgRandm');"/>  
								</LI>
							</UL>
							<ul>
								<li style="text-align: center;"><a href="#" onclick="winSd();">注册</a></li>
							</ul>
									<ul>
								<li style="text-align: center;"><a href="images/jifenjihua.doc">积分计划操作手册</a></li>
							</ul>
							
						</DIV>
					</LI>
					<LI class=user_main_r>
						<INPUT class=IbtnEnterCssClass onkeypress="enterKey(event);" id=IbtnEnter style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px" 
							type=image src="images2/user_botton.gif" name=IbtnEnter onclick="return check();"/>
					</LI>
				</UL>
			<DD id=user_bottom>
				<UL>
					<LI class=user_bottom_l></LI>
					<LI class=user_bottom_c><SPAN style="MARGIN-TOP: 40px">
					</SPAN></LI>
					<LI class=user_bottom_r></LI>
				</UL>
			</DD>
		</DL>
	</DIV>
	<SPAN id=ValrUserName style="DISPLAY: none; COLOR: red"></SPAN>
	<SPAN id=ValrPassword style="DISPLAY: none; COLOR: red"></SPAN>
	<SPAN id=ValrValidateCode style="DISPLAY: none; COLOR: red"></SPAN>
	<DIV id=ValidationSummary1 style="DISPLAY: none; COLOR: red"></DIV>

	<DIV></DIV>

	</FORM>
</BODY>
</HTML>
