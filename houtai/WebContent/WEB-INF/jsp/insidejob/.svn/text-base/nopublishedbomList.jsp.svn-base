<%-- 
/*
 * ----------------------------------------------------------
 * FILE         : nopublishdbomList
 * CREATEUSER   : Anston
 * CREATEDATE   : 2009/9/20
 * FILENAME     : nopublishdbomList_view.jsp
 * DESCRIPTION  : 内勤管理页面
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
<script src="js/jquery.maxchar.js" type="text/javascript"></script>

<script src="images/core_res/js/front.js" type="text/javascript"></script>
<script src="images/core_res/js/admin.js" type="text/javascript"></script>

<script src="js/common.js" type="text/javascript"></script>
<script type="text/javascript">
	function body_onload () {
		
		var message = "<c:out value="${message}"/>";
		
		if (message != "" && message!=null) {
			alert(message);					
		}

		var bomdivId = '${param.bomdivId}';
		if (bomdivId!='') {
			changeDiv(bomdivId);	
		}
		
	}

	 function searchClic(){
		 document.getElementById("frmbom").action="insideJob!noPublishedBomList.action";
		 document.getElementById("frmbom").submit();
		 return true;
	 }
   
   changeDiv = function() {
	 	var bomId = arguments[0];
	 	 	
		var url = "insideJob!bomListView.action";
		$("#loadpage").load(url,{"bomId":bomId});					
	}	

	
	
</script>
</head>
<body onload="body_onload()">
<div class="body-box">
<script src="js/My97DatePicker/WdatePicker.js"  type="text/javascript"></script> 
	<div class="rhead">
    <div class="rpos">内勤管理 >>内勤管理</div>
  	<div class="clear"></div>

	</div>

<form name="frmbom" id="frmbom" method="post">
<div class="rhead">

	Bom清单Id:<input type="text" id="bomId" name="bomId" value="${param.bomId}"/>
	&nbsp;&nbsp;&nbsp;采购员:<input type="text" id="userName" name="userName" value="${param.userName}"/>
	
	&nbsp;&nbsp;&nbsp;分配状态:<select id="isallocate" name="isallocate">
		<option value="">请选择</option>
		<option value="0" <c:if test="${param.isallocate == '0'}">selected</c:if> >未分配</option>
		<option value="1" <c:if test="${param.isallocate == '1'}">selected</c:if>>部分分配</option>
		<option value="2" <c:if test="${param.isallocate == '2'}">selected</c:if>>全部分配</option>
	</select>
	&nbsp;&nbsp;&nbsp;发布状态:<select id="ispublish" name="ispublish">
		<option value="">请选择</option>
		<option value="0" <c:if test="${param.ispublish == '0'}">selected</c:if>>未发布</option>
		<option value="1" <c:if test="${param.ispublish == '1'}">selected</c:if>>部分发布</option>
		<option value="2" <c:if test="${param.ispublish == '2'}">selected</c:if>>全部发布</option>
	</select>
	&nbsp;&nbsp;&nbsp;<input type="button" id="cmdSearch" name="cmdSearch" value=" 查询 " onclick="return searchClic();" />
</div>


<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
<thead class="pn-lthead">
<tr>	
						
	          <th class="tab_biaoti" width="20%">BOM清单ID</th>
            <th class="tab_biaoti" width="10%">发布人</th>
            <th class="tab_biaoti" width="10%">申报人</th>
            <th class="tab_biaoti" width="10%">审批人</th>            
            <th class="tab_biaoti" width="20%">日期</th>
            <th class="tab_biaoti" width="10%">任务分配状态</th>
            <th class="tab_biaoti" width="10%">公告发布状态</th>
            <th class="tab_biaoti" width="8%">查看</th>

</tr>
</thead>

<tbody class="pn-ltbody">
	<c:forEach items="${litPager}" var="item" varStatus="status">
			<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);">		
			
				<td >${item.bomId}</td>
				<td >${item.insidePerson}</td>
				<td >${item.declarePerson}</td>
				<td >${item.approvalPerson}</td>
				
				<td><fmt:formatDate value="${item.issueDate}" pattern="yyyy-MM-dd"/></td>
				<td>
					<c:if test="${item.isallocate=='0'}"><font color="red">未分配</font></c:if>
					<c:if test="${item.isallocate=='1'}">部分分配</c:if>
					<c:if test="${item.isallocate=='2'}"><font color="green">全部分配</font></c:if>
				</td>
				<td>
					<c:if test="${item.ispublish=='0'}"><font color="red">未发布</font></c:if>
					<c:if test="${item.ispublish=='1'}">部分发布</c:if>
					<c:if test="${item.ispublish=='2'}"><font color="green">全部发布</font></c:if>
				</td>
				<td>
					<a href="javascript:void(0);" onclick="changeDiv('${item.bomId}');">明细</a>			
				</td>
			</tr>
	</c:forEach>	
</tbody>
</table>

</form>


    <c:if test="${not empty litPager}">
     <c:import url="/WEB-INF/jsp/page/page.jsp">
     	<c:param name="pageActionUrl" value="insideJob!noPublishedBomList.action"/>
     	<c:param name="bomId" value="${param.bomId}"/>
     	<c:param name="userName" value="${param.userName}"/>
     	<c:param name="isallocate" value="${param.isallocate}"/>
     	<c:param name="ispublish" value="${param.ispublish}"/>
     </c:import>
    </c:if>
    <c:if test="${empty litPager}">
    		<font color="red">查无资料 !!!</font>
    </c:if>
    
    <div id="loadpage"></div>
 </div>

</body>
</html>
