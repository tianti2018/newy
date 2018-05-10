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

var frmNews = null;
function body_onload () {

	frmNews = document.frmNews;
	
	var message = "<c:out value="${message}"/>";
	
	if (message != "" && message!=null) {
		alert(message);					
	}
}

function goBack() {

	self.location.href="news!listAll.action";
}

function saveOrUpdate() {
	var newsId = frmNews.newsId.value;
	var title = frmNews.title.value;
	var content = frmNews.content.value;
	if(checkspace(title))  {		
		alert("对不起，公告标题不能为空，请重新输入！");
		frmNews.title.focus();
		return false;
	}
	if(checkspace(content))  {		
		alert("对不起，公告内容不能为空，请重新输入！");
		frmNews.content.focus();
		return false;
	}
			
	if (newsId != "" && newsId != null) {
		frmNews.action = "news!update.action";
	}
	else {
		frmNews.action = "news!create.action";
	}
	
	frmNews.submit();
}

</script>
 
</head>


<body onload="body_onload();">
<div class="body-box">
<div class="rhead">
		<div class="rpos">您的当前位置: 信息管理-->公告管理-->新增/修改公告</div>
		<div class="clear"></div>
	</div>
<div class="rhead">	
	
	<form class="ropt" method="post">
		<input type="button" value="返回列表" onclick="goBack();"/>
	</form>
	<div class="clear"></div>
</div>
<form id="frmNews" name="frmNews" method="post" action="news!create.action" >

	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">公告标题：</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" id="title" name="title" maxlength="60" value="${requestScope.news.title}" size="20"/><font color="red">*</font>
				<input type="hidden" id="newsId" name="newsId" value="${requestScope.news.newsId}"/>
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">公告内容：</td>
			<td width="80%" class="pn-fcontent">
				<textarea id="content" name="content" rows="20" cols="80">${requestScope.news.content}</textarea><font color="red">*</font>
			</td>
		</tr>				

		<tr>
			<td colspan="2" class="pn-fbutton">
			
			<input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}"/>
			<input type="button" onclick="return saveOrUpdate();" value="保存" style="cursor:pointer;width: 100px;"/>&nbsp;&nbsp;&nbsp;<input type="reset" value="重置"/></td>
		</tr>
	</table>
</form>
</div>
</body>
</html>
