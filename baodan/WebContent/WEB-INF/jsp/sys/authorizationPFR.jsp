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
<script src="js/common.js" type="text/javascript"></script>

<script language="javascript" type="text/javascript">

	function body_onload () {
		
		var message = "<c:out value="${message}"/>";
		
		if (message != "" && message!=null) {
			alert(message);					
		}
	}

	function addRoleFunction() {

		var roleId = document.getElementById("rid").value;
		
		if (checkspace(roleId)) {
			alert("对不起，角色不能为空，请重新输入！");
			document.getElementById("roleId").focus();
			return false;
		}

		 document.getElementById("roleId").value = roleId;
		document.getElementById("frmAuth").submit();
		//self.location.href("roleFunction!create.action?roleId="+roleId);			
	}

	function changeFunction() {
		var roleId = document.getElementById("rid").value;
		
		if (checkspace(roleId)) {
			alert("对不起，角色不能为空，请重新输入！");
			document.getElementById("rid").focus();

			return false;
		}
		
		self.location.href="roleFunction!listAllProducts.action?roleId="+roleId;			
	} 		
</script>


</head>

<script src="js/My97DatePicker/WdatePicker.js"  type="text/javascript"></script>

<script src="images/common_res/js/jquery.js" type="text/javascript"></script>
<script src="images/common_res/js/pony.js" type="text/javascript"></script>

<body>
<div class="body-box">
<div class="rhead">
	<div class="rpos">您的当前位置-->角色授权看到的产品</div>
	<div class="clear"></div>
</div>
<div class="rhead">
	<form  method="post">
		
		
		角色:<select name="rid" id="rid" onchange="return changeFunction();">			
				<option value="">请选择</option>
				<c:forEach items="${listAllRole}" var="item">
					<option  value="${item.roleId}" <c:if test="${item.roleId == param.roleId}">selected</c:if>>${item.roleName}</option>
				</c:forEach>			  																								
		</select>	
		
	</form>
	<div class="clear"></div>
</div>
<div class="rhead">
	<input type="button" id="cmdComfirm" name="cmdComfirm" value=" 确定 " onclick="return addRoleFunction();" />

</div>

<form id="frmAuth"  name="frmAuth" method="post" action="roleFunction!savePFR.action" >

<input type="hidden" id="roleId" name="roleId"  />

<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
<thead class="pn-lthead">
<tr>	
	<th width="5%">序号</th>
	<th width="70%">产品名称</th>
	<th width="12%">授权</th>

</tr>
</thead>
<tbody class="pn-ltbody">

 <%--<c:forEach items="${litPager}" var="item" varStatus="status">--%>
 <s:iterator value="litProducts" var="item"  status="status">
 
<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><s:property value="#status.index+1"/></td>
	<td><s:property value="name"/></td>
		
	<td>
		<s:if test="productsId in #request.had ">
			<s:checkbox theme="simple" name="productsIds" fieldValue="%{productsId}" value="true">
			
			</s:checkbox>
			<%-- <input type="checkbox" id="functionId${status.index+1}" name="functionId" value="${item.functionId}"/> --%>
		</s:if>	
		<s:else>
			<s:checkbox theme="simple" name="productsIds" fieldValue="%{productsId}" value="false">			
			</s:checkbox>
		</s:else>							
	</td>
	
</tr>
 </s:iterator>
 <%--</c:forEach>--%>

</tbody>
</table>

<div  style="text-align:center"></div>
</form>

</div>
</body>

</html>
