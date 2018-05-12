<%-- 
/*
 * ----------------------------------------------------------
 * FILE         : categoryList
 * CREATEUSER   : zhy
 * CREATEDATE   : 2009/10/12
 * FILENAME     : categoryList.jsp
 * DESCRIPTION  : 单位清单列表
 * MODIFIES     : 
 * MODIFIER     : 
 * MODIFIEDDATE :
 * COMMENT      : 
 * ----------------------------------------------------------
 */
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>list</title>
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
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jqModal.js"></script>
    <script type="text/javascript" src="js/jqDnR.js"></script>
    <script type="text/javascript" src="js/jqModal.litejva8.js"></script>
    <script src="js/qq.js" type="text/javascript"></script>

<script language="javascript" type="text/javascript">

    var frmUp = null;

	function body_onload () {
		
		var message = "<c:out value="${message}"/>";
		
		if (message != "" && message!=null) {
			alert(message);					
		}
	}

	
</script>


</head>
<body onload="body_onload();">
  <br>
  <br>
  <br>
    <form id="frmUp" name="frmUp" action="kefu!kefuonline.action" method="post" enctype="multipart/form-data" />
		<SPAN class="spanclass" id="span_ad_01">
			<c:forEach items="${litPagerOne}" var="item" varStatus="status">
			      <tr >
					<td valign="middle"  background="images/kefu_middle.gif" align="center">
					 <br><img src="http://www1.xise.cn/qq1/images/QQonline.gif" border=0 align=middle>
						<a class='qqb' target="blank" href="http://wpa.qq.com/msgrd?V=1&Uin=${item.qqNum}" >${item.qqNum}</a>
					</td>			
				  </tr>
				</c:forEach>   
	    <!--可更改内容结束-->
			</SPAN>
			<SCRIPT>ad_01.innerHTML=span_ad_01.innerHTML;span_ad_01.innerHTML="";</SCRIPT>
	</form>		
		<!-- 导入分页组件  -->
	  <%--<c:if test="${not empty litPagerOne}">
	    <c:import url="/WEB-INF/jsp/page/page.jsp">
	    	<c:param name="pageActionUrl" value="kefu!kefuonline.action"/>
	    </c:import>
	   </c:if>
	   <c:if test="${empty litPagerOne}">
	   	 <font color="red">查无资料 !!!</font>
	   </c:if>--%>
	   
	
</body>
</html>
