<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form >

	<table>
		<tr>
			<td>图片显示</td>
			<td>
			<c:forEach items="test" var="item">
				<a href="download.action?fileId=${item.testId}" >下载</a>
			</c:forEach>
			</td>
		</tr>
	</table>
</form>
</body>
</html>