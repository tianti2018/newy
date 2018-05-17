<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 

<style type="text/css" media="screen">
table {border-collapse:collapse;border:solid #999;border-width:1px 0 0 1px;}
table td {border:solid #999;border-width:0 1px 1px 0;}
.t1 {background-color:#fff;}
.t2 {background-color:#eee;}
.t3 {background-color:#ccc;}
</style>
<script type="text/javascript">
var Ptr=document.getElementsByTagName("tr");
function recolor() {
for (i=1;i<Ptr.length+1;i++) {
Ptr[i-1].className = (i%2>0)?"t1":"t2";
}
}
window.onload=recolor;
for(var i=0;i<Ptr.length;i++) {
Ptr[i].onmouseover=function(){
this.tmpClass=this.className;
this.className = "t3";
};
Ptr[i].onmouseout=function(){
this.className=this.tmpClass;
};
}
</script>
<script language="javascript" type="text/javascript">
	
	gotoPage = function () {
		var url = '${param.pageActionUrl}';
// 		var prizeName='${prizeName}';
// 		if(prizeName!=null&&prizeName!=""){
// 			prizeName=encodeURI(encodeURI(prizeName));
// 			$("#hPrizeName").val(prizeName);
// 		}
// 		var loginName='${loginName}';
// 		if(loginName!=null&&loginName!=""){
// 			loginName=encodeURI(encodeURI(loginName));
// 			$("#hLoginName").val(loginName);
// 		}
		var methodFlag = '${param.methodFlag}';
		var currentPage = '<s:property value="pager.currentPage"/>';

		document.getElementById("pagerMethod").value= arguments[0];
		document.getElementById("frmPage").submit();
		//self.location.href(url+"?currentPage="+currentPage+"&pagerMethod="+arguments[0]+"&methodFlag="+methodFlag+getParam());		
	}

	changePage = function () {
	
		var toPage = document.getElementById("_goPs").value;

		var parten = /^[0-9]*[1-9][0-9]*$/;

		if (!parten.exec(toPage)) {
			return false;
		}
		
		if (toPage == "" || toPage < 1) {
			return false;
		}
		
		var url = '${param.pageActionUrl}';
		var methodFlag = '${param.methodFlag}';
// 		var prizeName='${prizeName}';
// 		if(prizeName!=null&&prizeName!=""){
// 			prizeName=encodeURI(encodeURI(prizeName));
// 			$("#hPrizeName").val(prizeName);
// 		}
// 		var loginName='${loginName}';
// 		if(loginName!=null&&loginName!=""){
// 			loginName=encodeURI(encodeURI(loginName));
// 			$("#hLoginName").val(loginName);
// 		}
		var currentPage = '<s:property value="pager.currentPage"/>';
				
		var totalPages = '<s:property value="pager.totalPages"/>';

		var pagerMethod;
		
		if (toPage == 1 ) {
			pagerMethod = 'first';
		}
		else if (toPage == totalPages ) {
			pagerMethod = 'last';
		}
		else if (currentPage < toPage) {
			toPage = Number(toPage)-1;
			pagerMethod = 'next';
		}
		else if (currentPage > toPage) {			
			toPage = Number(toPage)+1;
			pagerMethod = 'previous';			
		}
		else if (currentPage == toPage) {
			return false;
		}

		document.getElementById("pagerMethod").value= pagerMethod;
		document.getElementById("currentPage").value= toPage;

		document.getElementById("frmPage").submit();
	
		//self.location.href(url+"?currentPage="+toPage+"&pagerMethod="+pagerMethod+"&methodFlag="+methodFlag+getParam());		
	}

</script>
<form action="${param.pageActionUrl}" id="frmPage" name="frmPage" method="post">
<input type="hidden" id="hFromDate" name="fromDate" value='${fromDate}'>
<input type="hidden" id="hEndDate" name="endDate" value='${endDate}'>
<input type="hidden" id="hPrizeName" name="prizeName" value='${prizeName}'>
<input type="hidden" id="hLoginName" name="loginName" value='${loginName}'>
<input type="hidden" id="hMobile" name="mobile" value='${mobile}'>
<input type="hidden" id="hUserName" name="userName" value='${userName}'>
<input type="hidden" id="hOrderType" name="orderType" value='${orderType}'>
<input type="hidden" id="hOrdersBH" name="ordersBH" value='${ordersBH}'>
<input type="hidden" id="hKuaidiNo" name="kuaidiNo" value='${kuaidiNo}'>
<input type="hidden" id="htixianType" name="type" value='${type}'>
<input type="hidden" id="hToUserName" name="toUserName" value='${toUserName}'>
<input type="hidden" id="hTjr" name="tjrLoginName" value='${tjrLoginName}'>
<input type="hidden" id="hPrizeStatus" name="prizeStatus" value='${prizeStatus}'>
<input type="hidden" id="bomId" name="bomId" value="${param.bomId}">
<input type="hidden" id="isallocate" name="isallocate" value="${param.isallocate}">
<input type="hidden" id="ispublish" name="ispublish" value="${param.ispublish}">
<input type="hidden" id="currentPage" name="currentPage" value="<s:property value="pager.currentPage"/>">
<input type="hidden" id="methodFlag" name="methodFlag" value="${param.methodFlag}">
<input type="hidden" id="checkStatus" name="checkStatus" value="${checkStatus}">
<input type="hidden" id="pagerMethod" name="pagerMethod">
<input type="hidden" id="userId" name="userId" value="${param.userId}">
<input type="hidden" id="parentId" name="parentId" value="${param.parentId}">
<input type="hidden" id="parentUserName" name="parentUserName" value="${param.parentUserName}">
<input type="hidden" id="flag" name="flag" value="${param.flag}">
<input type="hidden" id="hinput_qoUserName" name="oUserName" value='${oUserName}'>
<input type="hidden" id="hinput_qoPhone" name="oPhone" value='${oPhone}'>
<input type="hidden" id="hinput_qphone" name="phone" value='${phone}'>
<input type="hidden" id="hinput_DateType" name="dateType" value='${dateType}'>
<input type="hidden" id="hinput_block" name="block" value='${block}'>
<input type="hidden" id="hinput_pname" name="pname" value='${pname}'>
<!-- 上传合同beagin -->
<input type="hidden" name="key" value="${param.key}"/>
<input type="hidden" name="categoryid" value="${param.categoryid}"/>
<input type="hidden" name="materialname" value="${param.materialname}"/>
<input type="hidden" name="upstatus" value="${param.upstatus}"/>

<!-- 财务管理begin -->
<input type="hidden"  name="condition" value="${param.condition}" /> 
<input type="hidden"  name="supplierId" value="${parma.supplierId}" /> 
<input type="hidden"  name="categoryId" value="${param.categoryId}" /> 
<input type="hidden"  name="materialId" value="${param.materialId}" /> 
<input type="hidden"  name="payedStatus" value="${param.payedStatus}" /> 
<!-- 财务管理end-->

<div class="pn-sp">
	<div class="pn-sp-left">共 <s:property value="pager.totalRows"/>条 &nbsp;每页<s:property value="pager.pageSize"/>条</div>
	
	<div class="pn-sp-right">
	
		<c:if test="${pager.previous}">
   		
  		<input type="button" value="首 页" onclick="gotoPage('first');" />&nbsp;
  		<input type="button" value="上一页" onclick="gotoPage('previous');" />&nbsp;
 		</c:if>
 		
 		
 		<c:if test="${!pager.previous}">
  		<input type="button" value="首 页"   disabled="disabled"/>&nbsp;
  		<input type="button" value="上一页"  disabled="disabled"/>&nbsp;
 		</c:if>
 		
 		<c:if test="${pager.next}">   		
  		<input type="button" value="下一页" onclick="gotoPage('next');" />&nbsp;
  		<input type="button" value="尾 页" onclick="gotoPage('last');" />&nbsp;
 		</c:if> 		
 	
 		<c:if test="${!pager.next}">
  			<input type="button" value="下一页"   disabled="disabled"/>&nbsp;
			<input type="button" value="尾 页"  		disabled="disabled"/> &nbsp;   			
 		</c:if>
				
		当前 <s:property value="pager.currentPage"/>/<s:property value="pager.totalPages"/> 页 &nbsp;
		
		
		转到第 <input type="text" id="_goPs" size="2" onfocus="this.select();" onkeypress="if(event.keyCode==13){$('#_goPage').click();return false;}"/> 页
		<input id="_goPage" type="button" value=" 转 " onclick="return changePage();"/>
	</div>
	<div class="clear"></div>
</div>

</form>
<%-- 
<table class="all_di">
               <tr align="right">
					    	<td colspan="3" class="bule">					    	
					    	第<s:property value="pager.currentPage"/>页/共<s:property value="pager.totalPages"/>页				    		
					    		<!-- 首页 -->
					    		<c:if test="${pager.previous}">
						    		<a href="<s:url includeParams="pageActionUrl">
						    			<s:param name="currentPage" value="currentPage"/>
						    			<s:param name="pagerMethod" value="'first'"/></s:url>">首页
						    		</a>
					    		</c:if>
					    		<c:if test="${!pager.previous}">
						    		<a href="javascript:void(0);">首页</a>
					    		</c:if>
					    							    		
					    		<!-- 上一页 -->
					    		<c:if test="${pager.previous}">
						    		<a href="<s:url includeParams="pageActionUrl">
						    			<s:param name="currentPage" value="currentPage"/>
						    			<s:param name="pagerMethod" value="'previous'"/>
						    		</s:url>">上一页</a>
					    		</c:if>
					    		<c:if test="${!pager.previous}">
						    		<a href="javascript:void(0);">上一页</a>
					    		</c:if>
					    		
					    		<c:if test="${pager.next}">
						    		<a href="<s:url includeParams="pageActionUrl">
						    			<s:param name="currentPage" value="currentPage"/>
						    			<s:param name="pagerMethod" value="'next'"/>
						    		</s:url>">下一页</a>
					    		</c:if>
					    		<c:if test="${!pager.next}">
					    			<a href="javascript:void(0);">下一页</a>
					    		</c:if>
					    		
					    		<c:if test="${pager.next}">
						    		<a href="<s:url includeParams="pageActionUrl">
						    			<s:param name="currentPage" value="currentPage"/>
						    			<s:param name="pagerMethod" value="'last'"/>
						    		</s:url>">最后一页</a>
					    		</c:if>
					    		<c:if test="${!pager.next}">
					    			<a href="javascript:void(0);">最后一页</a>
					    		</c:if>
					    	</td>
					    </tr>	                
            </table>
--%>