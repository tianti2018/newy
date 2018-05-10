<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style type="text/css">
        *{margin: 0;padding: 0;font-size:1.1rem;}
    </style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>

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
			<tr>
				<td align="center">
				 注意:
				</td>
				<td align="center">
				文件要求为excel文件!
				</td>
			</tr>
			<tr>
				<td align="center">
				 文件格式:
				</td>
				<td align="center">
				第一列为订单编号,第二列为快递名称,第三列为订单编号!这三列必须固定,其他列没用
				</td>
			</tr>
			<tr>
				
				<td align="center">
					上传文件： <input type="file" id="importFile" name="importFile"  />
				</td>
				<td align="center" >
					<input type="submit" id="cmdConfirm" name="cmdConfirm" value="上传"  />
				</td>
			</tr>
			
		</table>
		
	</form>
	</div>
</body>
</html>
