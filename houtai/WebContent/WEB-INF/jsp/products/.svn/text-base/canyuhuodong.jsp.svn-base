<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<!--按钮生成器— http://www.knowsky.com-->
<style>

.thisclass{background-color:#990000}

</style>
<script src="images/common_res/js/jquery.js" type="text/javascript"></script>
<script language="javascript">
	var frmUser = null;
	function body_onload () {
		frmUser = document.frmUser;
		
		var message = "<c:out value="${message}"/>";
		
		if (message != "" && message!=null) {
			alert(message);
		}
		
		var maxOrder = "<c:out value="${maxOrder}"/>";
		if (maxOrder != "" && maxOrder!=null) {
			var innerHTML = '<h3><font color="red">恭喜您您这次获得的序号为:</font></h3><h1><font color="green">'+maxOrder+'</font></h1>';
			 document.getElementById("one").innerHTML = innerHTML;
		}
		
	}

	function change(color){
		var el=event.srcElement
		if (el.tagName=="INPUT"&&el.type=="button")
		event.srcElement.style.backgroundColor=color
	}
		
	function jumpto2(){
		frmUser.action= "card!canyuhuodong.action";
		frmUser.submit();
		frmUser.cmdButton.disabled = true;
	}

</script>

</head>
<body onload="body_onload();">
<form id="frmUser" name="frmUser" method="post" action="card!canyuhuodong.action" onMouseOver="change('#000099')" onMouseOut="change('#990000')">
<div align="center" style="margin-top: 200px;">
	<input type="button" id = "cmdButton" name="Button" class="thisclass" value="参与活动" onMouseOver="this.style.color='#99FF99'" onMouseOut="this.style.color='#FFFFFF'" onMouseDown="this.style.color='#FFFF00'" style="color:#FFFFFF; font-family:宋体; font-weight:bold; font-size:40px;" onClick="jumpto2()">
	<span id="one"></span>
</di>
</form>

</body> 
</html>