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

var frmMessage = null;
function body_onload () {

	frmMessage = document.frmMessage;
	
	var messages = "<c:out value="${messages}"/>";
	
	if (messages != "" && messages!=null) {
		alert(messages);					
	}
}

function goBack() {

	self.location.href="message!listAll.action";
}

function saveOrUpdate() {
	var messageId = frmMessage.messageId.value;
	var title = frmMessage.title.value;
	var contents = frmMessage.contents.value;
	if(checkspace(title))  {		
		alert("对不起，留言标题不能为空，请重新输入！");
		frmMessage.title.focus();
		return false;
	}
	if(checkspace(contents))  {		
		alert("对不起，留言内容不能为空，请重新输入！");
		frmMessage.contents.focus();
		return false;
	}
			
	if (messageId != "" && messageId != null) {
		frmMessage.action = "message!update.action";
	}
	else {
		frmMessage.action = "message!create.action";
	}
	
	frmMessage.submit();
}

</script>
 
</head>


<body onload="body_onload();">
<div class="body-box">
<div class="rhead">
		<div class="rpos">您的当前位置: 新增留言</div>
		<div class="clear"></div>
	</div>
<div class="rhead">	
	
	<form class="ropt" method="post">
		<input type="button" value="返回列表" onclick="goBack();"/>
	</form>
	<div class="clear"></div>
</div>
<form id="frmMessage" name="frmMessage" method="post" action="message!create.action" >

	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">留言标题：</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" id="title" name="title" maxlength="100" value="${requestScope.message.title}" size="20"/><font color="red">*</font>
				<input type="hidden" id="messageId" name="messageId" value="${requestScope.message.messageId}"/>
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">留言内容：</td>
			<td width="80%" class="pn-fcontent">
				<textarea id="contents" name="contents" rows="20" cols="80">${requestScope.message.contents}</textarea><font color="red">*</font>
			</td>
		</tr>				

		<tr>
			<td colspan="2" class="pn-fbutton">
			
			<input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}"/>
			<input type="button" onclick="return saveOrUpdate();" value="保存"/>&nbsp;&nbsp;&nbsp;<input type="reset" value="重置"/></td>
		</tr>
	</table>
</form>
</div>
</body>
</html>
