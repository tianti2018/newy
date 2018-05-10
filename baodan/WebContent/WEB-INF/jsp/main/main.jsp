<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style type="text/css">
        *{margin: 0;padding: 0;font-size:1.1rem;}
    </style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>

<title>会员管理后台</title>
</head>

<frameset rows="90,*,8" frameborder="no" border="0" framespacing="0">
  <frame src="main!goToTop.action?loginPage=${loginPage}" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" />
  <frame src="main!goToMiddel.action?loginPage=${loginPage}" name="centerFrame" id="centerFrame" scrolling="auto"  title="mainFrame" />  
  <frame src="main!goToBottom.action?loginPage=${loginPage}" name="bottomFrame" scrolling="No" noresize="noresize" id="bottomFrame" />
</frameset>
<noframes><body>
</body>
</noframes></html>