<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>list</title>


<link href="images/core_res/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="images/core_res/css/theme.css" rel="stylesheet" type="text/css"/>
 
<script src="images/common_res/js/jquery.js" type="text/javascript"></script>

<script src="images/common_res/js/pony.js" type="text/javascript"></script>

 
<script language="javascript" type="text/javascript">
	var frmzz = null;
	function body_onload () {
		frmzz = document.frmzz;
		var message = "<c:out value="${message}"/>";
		
		if (message != "" && message!=null) {
			alert(message);					
		}
	}

		
	function deleteUser() {
		if(confirm('您确定删除吗？')) {
			window.open("user!deleteUser.action?userId="+arguments[0], "rightFrame");
			return true;	
		}
		return false;
	}
	
	function check() {
		self.location.href="user!goumaiCard.action";
	}
	
</script>


</head>
<body onload="body_onload();">
	<div class="body-box">
	<div class="rhead">
		<div class="rpos">您的当前位置: 会员中心-->提取购买/卡密</div>
		<div class="clear"></div>
	</div>
	<div class="rhead">
		<div class="clear" align="left">
			<c:if test="${user.card.cardType=='1'}">10元包月存值卡:</c:if> 
			<c:if test="${user.card.cardType=='2'}">120元包年存值卡:</c:if> 
			<font size="4"  color="Red">&nbsp;&nbsp;&nbsp; 卡号:${user.card.cardNo} 密码:${user.card.cardPassword}</font></br>
			
			150元包15个月的存值卡: <font size="4"  color="Red">&nbsp;&nbsp;&nbsp; ${fhRecord.memo}</font></br>
		</div>
	</div>
	<div class="rhead" align="center">	
	<div class="clear" align="left">
		<form id ="frmzz" name="frmzz"  class="ropt" method="post" >
		  <input size="20" type="button" <c:if test="${flag=='1'}">disabled</c:if> value="     购买         " onclick="check();" /><font color="red">(注意:100积分购买一个150元包15个月的存值卡 ,15个月内只能购买一次)</font>
		</form>
	</div>
	<div class="clear"></div>
</div>
	

</div>
</body>
</html>
