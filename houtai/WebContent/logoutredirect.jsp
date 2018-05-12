<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="refresh" >
<title>CenfexLine</title>
</head>

<script>
	var flag = '${param.flag}';
	if (flag=='b') {
		window.open("login2.jsp?flag=flush","_top");
	}
	else if (flag=='a') {
		window.open("login.jsp?flag=flush","_top");
	}
	

	/*function body_onload () {
		var message = "${MESSAGE_CODE}";
		alert(message);
		var serverName = "http://"+"${serverName}?message="+encodeURI(message);
		
		top.location.replace(serverName);	

		 		
	}*/
	
</script>
<body>


</body>
</html>
