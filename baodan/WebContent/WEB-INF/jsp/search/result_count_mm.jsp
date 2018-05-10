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
			
	
	function searchClic() {

		var syear = $("#syear").val();
		var smonth = $("#smonth").val();
		var eyear = $("#eyear").val();
		var emonth = $("#emonth").val();

		if (smonth != "") {
			if (syear=="") {
				alert("请选择起始年");
				return false;
			}
		}

		if (emonth != "") {
			if (eyear=="") {
				alert("请选截止年");
				return false;
			}
		}

		if (eyear != "" && syear != "") {
			if (syear != "") {

				if (eyear<syear) {
					alert("起始年不能小于截止年");
					return false;
				}
				if (eyear == syear) {
					if (smonth!=''&&emonth!='') {
						if (Number(smonth)>Number(emonth)) {
							alert("起始年与截止年相等时 起始月不能大于截止月");
							return false;
						}
					}
				}							
			}
		}
				
		frmSearch.action="resultCountMm!listAllCount.action?methodFlag=all";
		frmSearch.submit();
	
	}

	getParamOne = function () {
		var j_c_ffTypeId='${param.j_c_ffTypeId}';
		var year = '${param.year}';
   	var month = '${param.month}';
   	var j_cph = '${param.j_cph}';
   	var j_batchparaid = '${param.j_batchparaid}';
   	
		var c_cph = j_cph.replace(/\#/g,"@");
							
		var param = "?j_c_ffTypeId="+j_c_ffTypeId+"&j_cph="+j_cph+"&year="+year
							+"&month="+month+"&j_batchparaid="+j_batchparaid;

		return param;
	}
	
	function openExcel() {
						
		window.open("downLoadExcelCount!init.action"+getParamOne());
	}

	openView = function() {

		var tflx_id = arguments[0];
				
		var syear = '${param.syear}'==''?'${newYear}':'${param.syear}';
		var smonth = '${param.smonth}'==''?'${newMonth}':'${param.smonth}';
		var eyear = '${param.eyear}'==''?'${newYear}':'${param.eyear}';
		var emonth = '${param.emonth}'==''?'${newMonth}':'${param.emonth}';
		var fzx = '${param.fzx}';
		var c_count = '${param.c_count}';

		var param = "?syear="+syear+"&smonth="+smonth+"&eyear="+eyear+"&emonth="+emonth+"&tflx_id="+tflx_id+"&c_count="+c_count+"&fzx="+fzx;
	
		frmSearch.action = "resultCountMm!view.action"+param;	

		window.open("resultCountMm!view.action"+param, "rightIFrame");	
	
	}
			
</script>

</head>

<body onload="body_onload();">
	
	<div class="body-box">

	<div class="rhead">
		<div class="rpos">您的当前位置: 逃费车辆管理-->逃费车辆统计报表</div>
		<div class="clear"></div>
	</div>

	
	 	
	<div class="rhead"  >
	<table style="background-color:white;"><tr>
	<td>
		<form  id="frmSearch" name="frmSearch" method="post"  action="resultCountMm!listAllCount.action">
		<table style="background-color:white;">
			<tr>
				<td>
									
			起始年<select id="syear" name="syear" >
		    <option value=""> 请选择</option>
		    		   
		    <c:forEach  var="item" begin="2000" step="1" end="2022">
		  	  <option value="${item}" <c:if test="${param.syear == item}">selected</c:if><c:if test="${empty param.syear && newYear == item}">selected</c:if> >${item}</option>	   	
		    </c:forEach>				    	
		  </select>				    
 			
		          月:
		    <select name="smonth" id="smonth">
				 <option value="">请选择</option>						 
				  <c:forEach  var="item" begin="01" step="1" end="12">
			  	  <option value="${item}" <c:if test="${param.smonth == item}">selected</c:if> <c:if test="${empty param.smonth && newMonth == item}">selected</c:if>>${item}</option>	   	
			    </c:forEach>	
			 </select>
						
			 &nbsp;&nbsp; &nbsp;&nbsp;
		  
		截止年<select id="eyear" name="eyear" >
		    <option value=""> 请选择</option>
		    		   
		    <c:forEach  var="item" begin="2000" step="1" end="2022">
		  	  <option value="${item}" <c:if test="${param.eyear == item}">selected</c:if> <c:if test="${empty param.eyear && newYear == item}">selected</c:if>>${item}</option>	   	
		    </c:forEach>				    	
		  </select>				     		
		          月:
		    <select name="emonth" id="emonth">
				 <option value="">请选择</option>						 
				  <c:forEach  var="item" begin="01" step="1" end="12">
			  	  <option value="${item}" <c:if test="${param.emonth == item}">selected</c:if> <c:if test="${empty param.emonth && newMonth == item}">selected</c:if>>${item}</option>	   	
			    </c:forEach>	
			 </select>			
			<br />
		  <br />
			
			
						
			<c:if test="${sessionScope.user.zhan.zdm == '000000'}">	  
		           所属机构:
	  		<select name="fzx" id="fzx" >
				  <option value="">请选择</option>
				  <option value="1" <c:if test="${param.fzx == '1'}">selected</c:if>>全路网</option>
				  <c:forEach items="${listRCKPZhan}"  var="item">
			   	  <c:if test="${item.zdm != '000000'}"><option value="${item.zdm}" <c:if test="${param.fzx == item.zdm}">selected</c:if>>${item.zmc}</option></c:if>	   	
			    </c:forEach>		
			  </select>
			</c:if>	
			
  		  		
  		<br />
		  <br />
		  	        
	          次数范围:
	    <select name="c_count" id="c_count" >
	      <option value="">请选择</option>	
	      <option value="1-10" <c:if test="${param.c_count == '1-10'}">selected</c:if>>1-10</option>
	      <option value="11-20" <c:if test="${param.c_count == '11-20'}">selected</c:if>>11-20</option>	
	      <option value="21-40" <c:if test="${param.c_count == '21-40'}">selected</c:if>>21-40</option>	
	      <option value="41" <c:if test="${param.c_count == '41'}">selected</c:if>>40以上</option>		   
	   	    		
	    </select>	  
	    
	     &nbsp;&nbsp;&nbsp;&nbsp;<input name="button" type="button" onclick="return searchClic();" value="  查询  "/> 
	     	     
				</td>
				<td>
				<div id="param_desc"  style="float: right;margin-right: 2%;display: none;">
					<table >
						<tr>
							<td id="td_desc"></td>
							<td id="td_type"></td>
						</tr>							
					</table>			
				</div>
				</td>
			</tr>
		
		</table>	
		</form>  										
   </td>
	
	<td>
 <b>功能简介：</b><br>
 &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;本模块对逃费车辆从时间、逃费类型、所属机构、逃费次数范围等角度分析统计逃费车辆数和逃费次数，<br>
点击查询结果还可查询相对应的具体逃费车辆信息。
	 
	</td>
	</tr></table> 	
	</div>	
</div>


<div style="overflow: auto;">
<form id="frmSearchOne" name="frmSearchOne" method="post">
	<display:table name="litPager"  requestURI="resultCountMm!listAllCount.action"  cellspacing="1" cellpadding="0" id="someLists" class="pn-ltable" >
		
		<display:column title="逃费方式" property="tflx_name"  sortable="true" style="width33%;align:center;"/> 	  
	  <display:column title="车辆数"  sortable="true" style="width:33%;align:center;">
	  	<a href="#" onclick="openView('<c:out value="${someLists.tflx_id}"/>');"><c:out value="${someLists.cphNum}"/></a>		  
	  </display:column>
	      	  	
  	<display:column title="次数"  sortable="true"  > 
  		<a href="#" onclick="openView('<c:out value="${someLists.tflx_id}"/>');"><c:out value="${someLists.tcount}"/></a>
  	</display:column> 		 	          			
  </display:table>

</form>

		     
 	<!-- 导入分页组件  -->
   <c:if test="${not empty litPager}">
		 <c:import url="/WEB-INF/jsp/page/page.jsp">
		 <c:param name="pageActionUrl" value="resultCountMm!listAllCount.action"/>
		 <c:param name="methodFlag" value="all"/>
		
		 <c:param name="syear" value="${param.syear}" >
		 	<c:if test="${empty param.syear}">${newYear}</c:if>
		 	<c:if test="${not empty param.syear}">${param.syear}</c:if>
		 </c:param>
		 
		 <c:param name="eyear" value="${param.eyear}" >
		 	<c:if test="${empty param.eyear}">${newYear}</c:if>
		 	<c:if test="${not empty param.eyear}">${param.eyear}</c:if>
		 </c:param>
		 
		  <c:param name="smonth" value="${param.smonth}" >
		 	<c:if test="${empty param.smonth}">${newMonth}</c:if>
		 	<c:if test="${not empty param.smonth}">${param.smonth}</c:if>
		 </c:param>
		 
		 <c:param name="emonth" value="${param.emonth}" >
		 	<c:if test="${empty param.emonth}">${newMonth}</c:if>
		 	<c:if test="${not empty param.emonth}">${param.emonth}</c:if>
		 </c:param>
		 
		 <c:param name="year" value="${param.year}"/>
		 <c:param name="month" value="${param.month}"/>
		
		 <c:param name="fzx" value="${param.fzx}"/>		  
		
		 <c:param name="c_count" value="${param.c_count}"/>					 	
		</c:import>
  </c:if>
		    
			
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
			
	</script>  	
</div>
	

</body>

</html>
