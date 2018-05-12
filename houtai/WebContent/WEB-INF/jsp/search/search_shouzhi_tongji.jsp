<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>高速公路逃费预警系统</title>

<link href="css/displaytag1.css" rel="stylesheet" type="text/css"/>

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

<script src="js/My97DatePicker/WdatePicker.js"  type="text/javascript"></script>

<script type="text/javascript">

	var frmSearch = null;
	function body_onload(){
		frmSearch = document.frmSearch;
		var message = "${message}";
		if (message!="") {
			alert(message);
		}
	}
		

	function changeSelect() {
		var zdmId = arguments[0];
		var flag = arguments[1];
		
		var zdmValue = document.getElementById(zdmId).value;
		var dis = "rukou";
		
		if (flag == '1') {
			dis = "j_rk_zdmc";
		}
		else if (flag == '2') {
			dis = "j_ck_zdmc";
		}
		
		
		if (zdmValue == "" || zdmValue == '000000') {
			document.getElementById(dis).style.display = "none";
		}
		else {
			document.getElementById(dis).style.display = "inline";
		}

		frmSearch.action="search!listAll.action?methodFlag=init";
		frmSearch.submit();				
	}
	
	function openSearchCount() {
		window.open("serchCount.html","riaghtIFrame");	
	}

	function searchClic() {

		var fromDate = document.getElementById("j_fromDate").value;
		var j_endDate = document.getElementById("j_endDate").value;
		if (fromDate != "" && j_endDate!="") {
			if (fromDate > j_endDate) {
				alert("请确认起日期不能大于止日期");

				return false;
			}
		}
		frmSearch.action="user!shouzhi_tongji.action";
		frmSearch.submit();
		
		/* var fromDate = document.getElementById("j_fromDate").value;
		var j_endDate = document.getElementById("j_endDate").value;
	
		var j_orderOne =  document.getElementById("j_orderOne").value;
		var j_orderTwo = document.getElementById("j_orderTwo").value;
		var j_orderThree = document.getElementById("j_orderThree").value;

		var j_sfydm = document.getElementById("j_sfydm").value;
		
		var parten = /^[A-Za-z0-9]+$/;
		
		if (j_sfydm != "" && !parten.test(j_sfydm)) {
			alert("收费员只能输入英文和数字");

			return false;
		}	
		
		if (((j_orderOne == j_orderTwo) || (j_orderOne == j_orderThree) || (j_orderTwo == j_orderThree)) && (j_orderOne != "" && j_orderTwo != "" && j_orderThree != "")) {
			alert("请确认排序的值不能一致");

			return false;
		}

		if (fromDate != "" && j_endDate!="") {
			if (fromDate > j_endDate) {
				alert("请确认起日期不能大于止日期");

				return false;
			}
		}
				
		frmSearch.action="search!listAll.action?methodFlag=all";
		frmSearch.submit(); */
	
	}
	
	function openExcel() {
	
		window.open("双方对开2009-05-16.xls");
	}
	

		
</script>

</head>

<body onload="body_onload();">

	<div class="body-box">

	<div class="rhead">
		<div class="rpos">您的当前位置: 财务管理-->收支统计</div>
		<div class="clear"></div>
	</div>

	<form  id="frmSearch" name="frmSearch" method="post"  action="search!listAll.action" >
	<div class="rhead">
								
			<%-- 逃费方式:
			<select name="j_c_ffTypeId" id="j_c_ffTypeId">
				<option value="">请选择</option>
				
				<c:forEach items="${fleeFeeType}" var="item">
					
					<option value="${item.tflx_id}" <c:if test="${param.j_c_ffTypeId == item.tflx_id}">selected</c:if> >${item.tflx_name}</option>	
									
				</c:forEach>
			</select>						
    	<br /> --%>	
	    <br />
	   	 起日期:    
	    <input type="text" id="j_fromDate" name="j_fromDate" value="${param.j_fromDate}" readonly="readonly"/>
	    <img  onclick="WdatePicker({el:'j_fromDate',startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" src="js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"/> 
	    &nbsp;&nbsp;&nbsp;止日期:
	    <input type="text" id="j_endDate" name="j_endDate" value="${param.j_endDate}" readonly="readonly"/> 
	    <img onclick="WdatePicker({el:'j_endDate',startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" src="js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"/>
	    &nbsp;&nbsp;&nbsp;&nbsp;<input name="button" type="button" onclick="return searchClic();" value="  查询  "/>
	    <br />
	    <br />
		阶段收入:<span> ${requestScope.jdshr}</span>元
		<br />
	    <br />
		阶段支出:<span> ${requestScope.jdzhch}</span>元
		<br />
	    <br />
		阶段利润:<span> ${requestScope.jdlr}</span>元
		 <br />
	    <br />
	    
		<strong>总收入:<span style=" color:red"> ${requestScope.zshr}</span>元</strong>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<strong>总支出:<span style=" color:red"> ${requestScope.zzhch}</span>元</strong>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<strong>总利润:<span style=" color:red"> ${requestScope.zlr}</span>元</strong>
	</div>
 </form> 
		    <%--   入口:
		  <select name="j_rk_zdmp" id="j_rk_zdmp" onchange="changeSelect('j_rk_zdmp','1');">
			  <option value="">请选择</option>
			  <c:forEach items="${listRCKPZhan}"  var="item">
		   	  <option value="${item.zdm}" <c:if test="${param.j_rk_zdmp == item.zdm}">selected</c:if> >${item.zmc}</option>	   	
		    </c:forEach>		
		  </select>
		  <select id="j_rk_zdmc" name="j_rk_zdmc" >
		    <option value=""> 请选择</option>
		    <c:forEach items="${listRKCZhan}"  var="item">
		  	  <option value="${item.zdm}" <c:if test="${param.j_rk_zdmc == item.zdm}">selected</c:if> >${item.zmc}</option>	   	
		    </c:forEach>	
		  </select>
	          出口:
	    <select name="j_ck_zdmp" id="j_ck_zdmp" onchange="changeSelect('j_ck_zdmp','2');">
			 <option value="">请选择</option>
			 <c:forEach items="${listRCKPZhan}"  var="item">
		   	 <option value="${item.zdm}" <c:if test="${param.j_ck_zdmp == item.zdm}">selected</c:if> >${item.zmc}</option>	   	
		   </c:forEach>		
		 </select>
			
	   <select id="j_ck_zdmc" name="j_ck_zdmc" >
	     <option value=""> 请选择</option>
	     <c:forEach items="${listCKCZhan}"  var="item">
		   	 <option value="${item.zdm}" <c:if test="${param.j_ck_zdmc == item.zdm}">selected</c:if> >${item.zmc}</option>	   	
		   </c:forEach>	
	   </select>
		 <br />
	   <br /> 
	        
	        收费员:<input type="text" name="j_sfydm" id="j_sfydm" value="<c:out value="${param.j_sfydm}"/>" maxlength="50"/>
	        车牌号:<input type="text" name="j_cph" id="j_cph" value="<c:out value="${requestScope.j_cph}"/>" maxlength="14"/><s:property value="j_cph" escape="false"/>
	   &nbsp;&nbsp;&nbsp;<br /> <br />
	        
	          参数结果名称:
	    <select name="j_batchparaid" id="j_batchparaid">
	      <option value="">请选择</option>	   
	   	  <c:forEach items="${litBatchpara}"  var="item">
	   		  <option value="${item.batchpara_id}" <c:if test="${param.j_batchparaid == item.batchpara_id}">selected</c:if> >${item.batchpara_name}</option>	   	
	   	  </c:forEach>	   		
	    </select>
	   
	 
  </div>
	
	
	<div class="rhead">
		
	  <div class="clear" align="left">
			排序: 
			  <select name="j_orderOne" id="j_orderOne">
					<option value="">请选择</option>
          <option value="tflx_id" <c:if test="${param.j_orderOne == 'tflx_id'}">selected</c:if> >逃费方式</option>
          <option value="xyd" <c:if test="${param.j_orderOne == 'xyd'}">selected</c:if> >嫌疑程度</option>
          <option value="cph" <c:if test="${param.j_orderOne == 'cph'}">selected</c:if> >车牌号</option>           
          <option value="ckzbm" <c:if test="${param.j_orderOne == 'ckzbm'}">selected</c:if> >收费站</option>
          <option value="sfydm" <c:if test="${param.j_orderOne == 'sfydm'}">selected</c:if> >收费员</option>                  					
			  </select>
			 
	      <select name="j_orderTwo" id="j_orderTwo">
            
          <option value="">请选择</option>
          <option value="tflx_id" <c:if test="${param.j_orderTwo == 'tflx_id'}">selected</c:if> >逃费方式</option>
          <option value="xyd" <c:if test="${param.j_orderTwo == 'xyd'}">selected</c:if> >嫌疑程度</option>
          <option value="cph" <c:if test="${param.j_orderTwo == 'cph'}">selected</c:if> >车牌号</option>           
          <option value="ckzbm" <c:if test="${param.j_orderTwo == 'ckzbm'}">selected</c:if> >收费站</option>
          <option value="sfydm" <c:if test="${param.j_orderTwo == 'sfydm'}">selected</c:if> >收费员</option>       
           
        </select>
        <select name="j_orderThree" id="j_orderThree">
          <option value="">请选择</option>
          <option value="tflx_id" <c:if test="${param.j_orderThree == 'tflx_id'}">selected</c:if> >逃费方式</option>
          <option value="xyd" <c:if test="${param.j_orderThree == 'xyd'}">selected</c:if> >嫌疑程度</option>
          <option value="cph" <c:if test="${param.j_orderThree == 'cph'}">selected</c:if> >车牌号</option>           
          <option value="ckzbm" <c:if test="${param.j_orderThree == 'ckzbm'}">selected</c:if> >收费站</option>
          <option value="sfydm" <c:if test="${param.j_orderThree == 'sfydm'}">selected</c:if> >收费员</option>                      
        </select>
        &nbsp;&nbsp;&nbsp;&nbsp;<input name="button" type="button" onclick="return searchClic();" value="  查询  "/>
		  </div>
		</div>
	
		<div class="rhead" style="display: none">
			
			<div class="clear" align="left">
			  <div align="center">汇总: 
			    &nbsp;&nbsp;&nbsp;&nbsp;逃费方式<input type="checkbox" id="j_tffs" name="j_tffs" value="tflx_id"  />
				  &nbsp;&nbsp;&nbsp;&nbsp;车牌号<input type="checkbox" id="fft" name="fft" value="cph"  />
				  &nbsp;&nbsp;&nbsp;&nbsp;出口收费站<input type="checkbox" id="fft2" name="fft2" value="ckzbm" />
				  &nbsp;&nbsp;&nbsp;&nbsp;入口收费站<input type="checkbox" id="fft" name="fft" value="1"  />
				  &nbsp;&nbsp;&nbsp;&nbsp;收费员<input type="checkbox" id="fft" name="fft" value="sfydm"  />
				  &nbsp;&nbsp;&nbsp;&nbsp;嫌疑程<input type="checkbox" id="fft" name="fft" value="1"  />
				  &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="cmdCount" name="cmdCount" value=" 汇总 " onclick="openSearchCount();"/>
				  
		   	 </div>
			 </div>
		 </div>
	
		<div class="rhead">
			
			<div class="clear" align="right">
				<input type="button" value="  录入黑名单  "    id="enter"    name="enter" onclick="return checkAllRadio();" />
				<input type="button" value="  Excel报表列印  " id="printExl" name="printExl" onclick="openExcel();" />
			</div>
		</div>--%>
		
		<script type="text/javascript">  

			var checkValue = "";
			function checkBoxOne() {
				
				if (document.getElementById(arguments[0]).checked) {
					checkValue+=document.getElementById(arguments[0]).value;				
				}
				else {
					document.getElementById(arguments[0]).value="";					
				}
				
			}

			// 检查所有的radio是有有选择
			 function checkAllRadio () {
				
				 var oForm=document.all("frmSearch");
				 var eles = oForm.elements;
				 
				 var flag = false;
				 
				 //遍历所有表元素
				 for (var i=0; i<eles.length;i++) {

					 if (eles[i].type == "checkbox") {				
						 if (document.getElementById(eles[i].id).checked) {
							 flag = true;				
								break;
							}
					 }
					 else 
						 continue;			
				 }	 

				 if (flag == false) {
					 alert(" 请您至少选择一项!!! ");
				 }
				 
				 if (flag) {
					
					 document.getElementById("frmSearch").action="search!enter.action";
					 document.getElementById("frmSearch").submit();					 
				 }
				 
				 return flag;
			 }
			
		</script>
	
		<%-- <display:table name="litPager"     requestURI="search!listAll.action"  cellspacing="1" cellpadding="0" id="someLists" class="pn-ltable">
	       	
	       	<display:column title="请选择">
	       		<input type="checkbox" id="chk_${someLists_rowNum}" name="chk_" value="${lsh}" onclick="checkBoxOne('chk_${someLists_rowNum}')"/>
	       	</display:column>
	       	<display:column title="逃费方式" property="fleeFeeType.tflx_name"  sortable="true" />
	       	<display:column title="嫌疑程度" property="xyd" sortable="true"  />
	       	<display:column title="车牌号" property="cph"  sortable="true" />		
	       	<display:column title="车型" property="cllx"  sortable="true" />	
	       	<display:column title="入口收费站" property="rkzhan.zmc"  sortable="true" />
	       	<display:column title="出口收费站" property="ckzhan.zmc"  sortable="true" />				
	       	TODO <display:column title="车道号" property="loginName"  sortable="true" />		
	       	<display:column title="收费员" property="sfydm"  sortable="true" />		
	       	
	       	
	       	<display:column title="应收" property="userId" sortable="true"  />
	       	<display:column title="实收" property="loginName"  sortable="true" />		
	       	<display:column title="估计漏征金额" property="userId" sortable="true"  />
	       	
	       	<display:column title="删除" >
	       		 <a href="#" >删除</a>
	       	
	       	</display:column>						    
	      </display:table>
	     </form>  	 
			 	<!-- 导入分页组件  -->
		    <c:if test="${not empty litPager}">
		     <c:import url="/WEB-INF/jsp/page/page.jsp">
		     	<c:param name="pageActionUrl" value="search!listAll.action"/>
		     	<c:param name="methodFlag" value="all"/>
		     	
		     	<c:param name="ffTypeId" value="${param.j_c_ffTypeId}"/>
		     	<c:param name="fromDate" value="${param.j_fromDate}"/>
		     	<c:param name="endDate" value="${param.j_endDate}"/>
		     	<c:param name="rk_zdmp" value="${param.j_rk_zdmp}"/>
		     	<c:param name="rk_zdmc" value="${param.j_rk_zdmc}"/>
		     	
		     	<c:param name="ck_zdmp" value="${param.j_ck_zdmp}"/>
		     	<c:param name="ck_zdmc" value="${param.j_ck_zdmc}"/>
		     	
		     	<c:param name="sfydm" value="${param.j_sfydm}"/>
		     	<c:param name="cph" value="${param.j_cph}"/>		  
		     	
		     	<c:param name="batchparaid" value="${param.j_batchparaid}"/>	
		     	
		     	<c:param name="orderOne" value="${param.j_orderOne}"/>
		     	<c:param name="orderTwo" value="${param.j_orderTwo}"/>	
		     	<c:param name="orderThree" value="${param.j_orderThree}"/>
		     	
		     </c:import>
		    </c:if>
		     --%>
			
			<script type="text/javascript">  
		
			//senfe("表格名称","奇数行背景","偶数行背景","鼠标经过背景","点击后背景");
			senfe("someLists","#FFF","#FFF","#F2F2F2","#d9e8fb");
			
			function senfe(o,a,b,c,d){
	
				var table = document.getElementById(o);    
	
				if (table != null) {
					 var tbody = table.getElementsByTagName("tbody")[0];   
					  if (tbody == null) {   
					    var t = table.getElementsByTagName("tr");   
					  } 
					  else {   
					    var t = tbody.getElementsByTagName("tr");   
					  }   
						
						 var t=document.getElementById(o).getElementsByTagName("tr");
						 for(var i=0;i<t.length;i++){
						  t[i].style.backgroundColor=(t[i].sectionRowIndex%2==0)?a:b;
						  t[i].onclick=function(){
				
									
							   if(this.x!="1"){
							    this.x="1";//本来打算直接用背景色判断，FF获取到的背景是RGB值，不好判断
							    this.style.backgroundColor=d;			    
						    
						   }
						   else{
						    this.x="0";
						    this.style.backgroundColor=(this.sectionRowIndex%2==0)?a:b;
						   }
						  }
						  t[i].onmouseover=function(){
						   if(this.x!="1")this.style.backgroundColor=c;
						  }
						  t[i].onmouseout=function(){
						   if(this.x!="1")this.style.backgroundColor=(this.sectionRowIndex%2==0)?a:b;
						  }
						 }
	
			  	}
				
		   
				}


			var selectedRow = null;
			var singleSelection = true;
			var selectedRowClassName = "";	
			var oldRowName = "";

			function trDiscolor() {
				var row = window.event.srcElement;

			  while (row.tagName != "TR"){
			    row = row.parentElement;
			  }
				
			  if (selectedRow != null && singleSelection == true && row != selectedRow){
			    selectedRow.className = oldRowName;
			  }
			  
			  if (row != selectedRow){
			  
			    if (singleSelection == true){
			    
						selectedRow = row;
						oldRowName = selectedRow.className;
						row.className = "trselect1";
						
			    }
			    
			  }
				
			}
			
			
	</script>  
	
	
	
</div>
	

</body>

</html>
