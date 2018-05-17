<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%@ taglib prefix="s"  uri="/struts-tags"%>    
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<title>error</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/base.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/popup.css"/>
</head>
<body style="border:0" bgcolor="#FFFFFF" leftmargin="0" topmargin="0" class="bgBodyRight">

    <div class='popup' style='height:100%;width:100%;overflow-y:auto;'>
		
		<div class='popupCon' style=' height:100%;margin:0 10px'>
			
			<div class='checkimageBox'>
					<img src="<%=path%>/images/error.png" alt='round_check' class='round_check'>
					<span>
						    对不起，你无权访问该页面！
					</span>			
    </div>
    </div>
    </div>
</body>
</html>