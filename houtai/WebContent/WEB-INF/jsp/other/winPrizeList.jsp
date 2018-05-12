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

</head>
<body onload="body_onload();">
	<div class="body-box">
	<div class="rhead">
		<div class="rpos">您的当前位置:财务管理-->中奖管理</div>
		<div class="clear"></div>
	</div>
	<div class="rhead">
		用户登录名：<input type="text" id="cmd_search" name="cmd_search" value='${loginName }'/>
		&nbsp;&nbsp;奖品名：<input type="text" id="cmd_search" name="cmd_search" value='${prizeName }' />
			&nbsp;&nbsp;兑奖状态：<select id="st_status" name="cmd_search" >
			<option value="">全部</option>
			<option value="0">未兑奖</option>
			<option value="1">已兑奖</option>
			</select>
			&nbsp;&nbsp;<input type="button" id="cmdBtn" style="cursor:pointer" name="cmdBtn" value="搜索" onclick="loadSearch();" />
			
		<form class="ropt" method="post">
			&nbsp; <input type="button"  id="btn_changePrize" style="cursor:pointer" value=" 同步积分到用户账户 " onclick="changePrize();"/>
		</form>
		<div class="clear"></div>
	</div>
	<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
		<thead class="pn-lthead">
		<tr>
			<th width="32">序号</th>
			<th width="100">用户登录名</th>		
			<th width="400">奖品名称</th>
			<th width="100">中奖时间</th>
			<th width="100">兑奖状态</th>
		</tr>
		</thead>
		<tbody class="pn-ltbody">
			<c:forEach items="${litPager}" var="item" varStatus="status">
			
			<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);">
				<td>${status.index+1}</td>
				<td align="center">${item.userName}</td>
				<td align="center">${item.prizeName}</td>
				<td align="center"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>	
				<td align="center">
				<c:choose>
				<c:when test="${item.status=='1'}">
				<span style="color:red">已兑奖</span>
				</c:when>
				<c:otherwise>
				未兑奖
				</c:otherwise>
				</c:choose>
				</td>
			</tr>
		</c:forEach>   
		</tbody>
	</table>

	 <!-- 导入分页组件  -->
    <c:if test="${not empty litPager}">
     <c:import url="/WEB-INF/jsp/page/page.jsp">
     	<c:param name="pageActionUrl" value="egg!winPrizeList.action"/>
     </c:import>
    </c:if>
    <c:if test="${empty litPager}">
    		<font color="red">查无资料 !!!</font>
    </c:if>

</div>

</body>

<script language="javascript" type="text/javascript">
var status='${prizeStatus}';
	$("#st_status").val(status);
function loadSearch() {
	var loginName = document.getElementsByName("cmd_search")[0].value;
	var prizeName = document.getElementsByName("cmd_search")[1].value;
	var prizeStatus = document.getElementsByName("cmd_search")[2].value;
	
	
	
	prizeStatus=encodeURI(encodeURI(prizeStatus));
	var url="http://127.0.0.1:8080/jilianji/egg!winPrizeList.action?1=1";
	if(loginName!=null&&loginName!=""){
		url+="&loginName="+loginName;
		loginName=encodeURI(encodeURI(loginName));
	}
	if(prizeName!=null&&prizeName!=""){
		url+="&prizeName="+prizeName;
		prizeName=encodeURI(encodeURI(prizeName));
	}
	if(prizeStatus!=null&&prizeStatus!="")
		url+="&prizeStatus="+prizeStatus;
	$("#cmdBtn").val("搜索中");
	$("#cmdBtn").attr("disabled","disabled"); 
	location.href=url;	
}

function changePrize(){
	 $.ajax({
		   url : "<%=request.getContextPath()%>/egg!ajaxChangeUserScore.action?"+new Date(),
         type: "post", 
         success: function(result) {
      	   if(result!=null){
      		   var dataObj=eval("("+result+")");
      		   alert(dataObj.tipMessage);
      		 self.location.href="egg!winPrizeList.action?loginName='${loginName }'&prizeName='${prizeName }'";	
      	   }
         },
		   error: function() {
      	  alert("系统错误，请稍后再试或联系管理员！");
         },
      		beforeSend : function() {
      			$("#btn_changePrize").val("同步中..");
				$("#btn_changePrize").attr('disabled',"true");

          }
	 });
	
}
</script>
</html>
