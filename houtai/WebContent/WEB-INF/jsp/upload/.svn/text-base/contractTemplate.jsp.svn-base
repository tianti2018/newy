<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>无标题文档</title>
</head>

<body>

	<form action="uploadCT!uploadFile.action" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>
				 标&nbsp;&nbsp;&nbsp;&nbsp;题：
				  <input type="text" id="buyerUpFilename" name="buyerUpFilename"  />
				  <input type="hidden" id="supplierBidderId" name="supplierBidderId"  />
				  
				</td>
			</tr>
			<tr>
				
				<td>
					上传文件： <input type="file" id="buyerUpContract" name="buyerUpContract"  />
				</td>
				
			</tr>
			<tr>
				
				<td>
					<input type="submit" id="cmdConfirm" name="cmdConfirm" value="上传"  />
				</td>
			</tr>
			
			
		</table>
		
		<table>
			<tr>
				<td>
				
					<img src="<c:url value="${filePath}"/>" />
				</td>
			</tr>
		
		</table>
		
	</form>
</body>
</html>
