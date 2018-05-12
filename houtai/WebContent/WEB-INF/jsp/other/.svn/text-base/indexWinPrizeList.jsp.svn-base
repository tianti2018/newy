<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 

<ul style="margin-top:2px;">
 <s:iterator value="prizeList"  var="prize" status="st">
			<li >
<%-- 			<a href="<%=request.getContextPath()%>/egg!initPage.action?activityId=8" target="_blank"> --%>
			<s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/>&nbsp;&nbsp;&nbsp;&nbsp;${userName }在砸金蛋中获得
			<span style="color:red">${prizeName}</span>
<!-- 			</a> -->
			</li>
</s:iterator>
		</ul>