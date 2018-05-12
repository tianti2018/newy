<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.ResourceBundle"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <!--  <meta name="viewport" content="width=device-width, initial-scale=1"> -->
   <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/source/jquery.fancybox.css?v=2.1.5" media="screen" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttons.css">
 	<link href="<%=request.getContextPath()%>/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <div class="panel panel-primary" id="inline">
      <div class="panel-heading">
        <h3 class="panel-title">留言</h3>
      </div>
      <div class="panel-body">
      	<div class="form-group">
                             留言内容:<textarea class="form-control" rows="8" id="textMessage"></textarea>
        </div>
      	<div>
      		<input type="hidden" value="" id="wxOpenid" value="${toWxOpenId}"/>
      		<a class="button button-pill button-primary" href="#" role="button" onclick="sendMessage();">发送</a>
      	</div>
        
      </div>
    </div>
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=request.getContextPath()%>/bootstrap-3.3.5/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/css/source/jquery.fancybox.pack.js?v=2.1.5"></script>
    
    <script type="text/javascript">
	function sendMessage() {
		var textMessage = $('#textMessage').val();
		var wxOpenid = $("#wxOpenid").val();
		if (textMessage=="") {
			alert("留言内容不能为空");
			return false;
		}
		var url ='<%=request.getContextPath()%>/user/userAction!sendMessage.action';
		$.ajax({
			cache: true,
			type: "POST",
			url:url,
			data:"wxOpenid=${toWxOpenId}&textMessage="+textMessage,// 你的formid
			async: false,
		    error: function(request) {
		        alert("Connection error111");
		    },
		    success: function(data) {
		    	if (data.success) {
		    		alert("回复成功!");
		    	}
		    }
		});
	}
    </script>
  </body>
</html>