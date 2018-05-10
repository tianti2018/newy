<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
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

</script>
 
</head>

<body onload="body_onload();">
	<div class="body-box">
	<div class="rhead">
		<div class="rpos">您的当前位置: 财务管理-->拨比统计</div>
		<div class="clear"></div>
	</div>
<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
	<thead class="pn-lthead">
	<tr>
		<th width="100">当日上的业绩</th>
		<th width="100">当日拨出奖金</th>
		<th width="100">当日拨比率</th>
		
		<th width="100">总业绩</th>
		<th width="100">总拨出奖金</th>
		<th width="100">总拨比率</th>
		
	</tr>
	</thead>
	
	<tbody class="pn-ltbody">
		<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
			<td align="center">${realSubmitMoney}</td>
			<td align="center">${fhMoney}</td>
			<td align="center">${percentage}</td>
			<td align="center">${realSubmitMoneyt}</td>
			<td align="center">${fhMoneyt}</td>
			<td align="center">${percentaget}</td>
		</tr>
	</tbody>
</table>
</div>
</body>
</html>
