<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>会员管理系统</title>

<link href="images/core_res/css/front.css" rel="stylesheet" type="text/css"/>
<link href="images/core_res/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="images/core_res/css/theme.css" rel="stylesheet" type="text/css"/>

<script src="js/common.js" type="text/javascript"></script>
<style type="text/css"> 
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow:hidden;
}
.input{
	BORDER: #cccccc 1px solid; 
	height:16px;
	width:150px;
}
.version {
	color: #000000;
	font-weight: bold;
	font-size: 25px;
}
.STYLE1 {
	color: #FFFFFF;
	font-size: 16px;
	font-weight: bold;
}
</style>
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
		self.location.href="login.jsp?flag=1"; 		
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

function changeValidateCode() {    

	//获取当前的时间作为参数，无具体意义    
	var timenow = new Date().getTime();    

	//每次请求需要一个不同的参数，否则可能会返回同样的验证码    
	//这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。    
	//obj.src="rand.action?d="+timenow;
	document.getElementById("imgRandm").src="rand.action?d="+timenow;  
}   


	function MM_preloadImages() { //v3.0
	  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
	    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
	    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
	}

	function MM_swapImgRestore() { //v3.0
	  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
	}

	function MM_findObj(n, d) { //v4.01
	  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
	    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
	  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
	  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
	  if(!x && d.getElementById) x=d.getElementById(n); return x;
	}

	function MM_swapImage() { //v3.0
	  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
	   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
	}

	// 回车事件
	function enterKey(event) {
    if(event.keyCode==13){     
      return check();
    }
  }
</script>

<%

		// 每次进入页面时都把session销毁
		request.getSession().invalidate();		
%>

</head>
<body onLoad="MM_preloadImages('images/login/login_09_1.gif','images/login/login_10_1.gif');body_onload();">

 <form id="loginform" name="loginform" action="login!doAdminLogin.action" method="post" onSubmit="return check();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td bgcolor="#02395f">&nbsp;</td>
  </tr>
  <tr>
    <td height="607" align="center" background="images/login/login_02.gif"><table width="974" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="331" background="images/login/login_01.jpg">&nbsp;</td>
      </tr>
      <tr>
        <td height="116"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="393" height="116" background="images/login/login_05.gif">&nbsp;</td>
            <td width="174"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="81" background="images/login/login_06.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="24%">
                    	<div align="center">
                    		<font style="height:1;font-size:9pt; color:#bfdbeb;filter:glow(color=#1070a3,strength=1)">用户</font>
                    	</div>
                    </td>
                    <td width="100%" height="25">
                    	<input type="text" name="loginName" id="loginName"  onkeypress="enterKey(event);"   style="width:125px; height:20px; background:#32a2e3; font-size:12px; border:solid 1px #0468a7; color:#14649f;" onfocus="this.select();">
                    </td>
                  </tr>
                  <tr>
                    <td>
                    	<div align="center">
                    		<font style="height:1;font-size:9pt; color:#bfdbeb;filter:glow(color=#1070a3,strength=1)">密码</font>
                    	</div>
                    </td>
                    <td height="25">
                    	<input type="password" name="password" id="password"  onkeypress="enterKey(event);"   style="width:125px; height:20px; background:#32a2e3; font-size:12px; border:solid 1px #0468a7; color:#14649f;">
                    </td>
                  </tr>
                  <tr >
                    <td >
                    	<div align="center">
                    		<font style="height:1;font-size:9pt; color:#bfdbeb;filter:glow(color=#1070a3,strength=1)">验证码</font>
                    	</div>
                    </td>
                    <td height="25"  width="100%">
                    	 <input name="random" type="text"   onkeypress="enterKey(event);"  id="random" size="1" maxlength="2" />
                    	 <img width="62%" height="25"  id="imgRandm" src="rand.action" title="点击图片刷新验证码" onclick="changeValidateCode('imgRandm');"/>  
                    </td>
                  </tr>
                </table></td>
              </tr>
              <tr>
                <td height="35"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="50" height="35"><img src="images/login/login_08.gif" width="50" height="35"></td>
                    <td width="46"><a href="#"><img src="images/login/login_09.gif" name="Image1" width="46" height="35" border="0" id="Image1" onMouseOver="MM_swapImage('Image1','','images/login/login_09_1.gif',1)" onMouseOut="MM_swapImgRestore()" onclick="return check();" ></a></td>
                    <td width="45"><a href="#"><img src="images/login/login_10.gif" name="Image2" width="45" height="35" border="0" id="Image2" onMouseOver="MM_swapImage('Image2','','images/login/login_10_1.gif',1)" onMouseOut="MM_swapImgRestore()"></a></td>
                    <td width="33"><img src="images/login/login_11.gif" width="33" height="35"></td>
                  </tr>
                </table></td>
              </tr>
            </table></td>
            <td width="407" background="images/login/login_07.gif">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="160" background="images/login/login_12.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td bgcolor="#02609c">&nbsp;</td>
  </tr>
</table>
</form>

</body>
</html>