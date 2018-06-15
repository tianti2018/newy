<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta name="apple-mobile-web-app-capable" content="yes"/>
	<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
	<meta name="format-detection" content="telephone=no"/>

<title>无标题文档</title>
<link href="images/common_res/css/jquery_validate.css" rel="stylesheet" type="text/css"/>
<link href="images/common_res/css/jquery.alerts.css" rel="stylesheet" type="text/css"/>
<link href="images/core_res/css/front.css" rel="stylesheet" type="text/css"/>
<link href="images/core_res/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="images/core_res/css/theme.css" rel="stylesheet" type="text/css"/>
<script src="js/My97DatePicker/WdatePicker.js"  type="text/javascript"></script>
</head>

<body>
<div class="rhead">
	<form action="orders!importExcelToOrders.action" method="post" enctype="multipart/form-data">
		<table class="pn-ltable" width="100%" cellspacing="0" cellpadding="0" border="1">
			<thead class="pn-lthead">
				<tr>
					<th width="50%">左边</th>
					<th width="50%">右边</th>
				</tr>
			</thead>
			<tbody class="pn-ltbody">
			<tr>
				<td  align="center">
				 文件格式:
				</td>
				<td  align="center">
				文件要求为excel文件!
				</td>
			</tr>
			<tr>
				<td  align="center">
				 注意:
				</td>
				<td  align="center">
				1.倒数第一列为快递单号;</br>
				2.倒数第二列为快递名称;</br>
				3.倒数第三列为订单编号!</br>
				4.这三列必须固定在这个位置不要做删减
				</td>
			</tr>
			<tr>
				
				<td  align="center">
					上传文件： <input type="file" id="importFile" name="importFile"  />
				</td>
				<td  align="center" >
					<input type="submit" id="cmdConfirm" name="cmdConfirm" value="上      传"  />
				</td>
			</tr>
			</tbody>
		</table>
		
	</form>
	</div>
</body>
</html>
