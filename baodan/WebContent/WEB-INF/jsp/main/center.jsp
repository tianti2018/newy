<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>无标题文档</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<style> 
	.navPoint { 
		COLOR: white; 
		CURSOR: hand; 
		FONT-FAMILY: Webdings; 
		FONT-SIZE: 9pt 
	} 
</style> 
<script>
	function switchSysBar(){ 
		
		var locate = location.href.replace('main!goToCenter.action?loginPage=${loginPage}', '');
		var ssrc=document.all("img1").src.replace(locate,'');
		if (ssrc=="images/main_55.gif") { 
			document.all("img1").src="images/main_55_1.gif";
			document.all("frmTitle").style.display="none" 
		} 
		else { 
			document.all("img1").src="images/main_55.gif";
			document.all("frmTitle").style.display=""; 
		} 
	} 
</script>

</head>

<%--
<body>
	<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" style="table-layout: fixed;">
		<tr>
			<td width="173" id="frmTitle" noWrap name="fmTitle" align="center" valign="top">
				<iframe name="leftIFrame" scrolling="auto" height="100%" width="180" src="main!goToLeft.action" border="0" frameborder="0" scrolling="no">
					浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架。
				</iframe>
			</td>
			<td width="8" valign="middle" background="images/main_12.gif" onclick="switchSysBar()" >
				<span class="navPoint">
					<img src="images/main_18.gif" name="img1" width="8" height="52" id="img1">
				</span>
			</td>
			<td align="center" valign="top" >
				<iframe name="rightIFrame" height="100%" width="100%" scrolling="auto" border="0" frameborder="0" src="main!goToRight.action">
					浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架。
				</iframe>
			</td>
			<td width="4" align="center" valign="top" background="images/main_20.gif"></td>
		</tr>
	</table>
</body>
 --%>
 
<body >
	<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
	  <tr>
	    <td width="171" id="frmTitle" noWrap name="fmTitle" align="center" valign="top">
	    	<table width="171" height="100%" border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
		      <tr>
		        <td  bgcolor="#353C44" style="width:6px;">&nbsp;</td>
		        <td width="165">
		        	<iframe name="leftFrame" id="leftFrame"  height="710" width="165" src="main!goToLeft.action?loginPage=${loginPage}" border="0" frameborder="0" scrolling="no"> 
		        		浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架。
		        	</iframe>
		        </td>
		      </tr>
	    </table>		
	    </td>
	    <td width="6"  style="width:6px;"valign="middle" bgcolor="#353C44" onclick="switchSysBar();">
	    	<SPAN class="navPoint" id="switchPoint" title="关闭/打开左栏">
	    		<img src="images/main_55.gif" name="img1" width="6" height="40" id="img1">
	    	</SPAN>
	    </td>
	    <td width="100%" align="center" valign="top">
	    	<iframe name="rightFrame" id="rightFrame" height="100%" width="100%" border="0" frameborder="0" src=""> 
	    		浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架。
	    	</iframe>
	    </td>
	  </tr>
	</table>
	
</body>
</html>