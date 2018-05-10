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
		if( "${showDiv}" ){
			document.getElementById("illuminateDiv").style.display="none";
		}
		frmSearch = document.frmSearch;
		var message = "${message}";
		if (message!="") {
			alert(message);
		}
		
		getParamDesc();	
	}
			
	function openSearchCount() {
		window.open("serchCount.html","riaghtIFrame");	
	}
  
   function getCookieInf(name){
	   alert(document.cookie);
		var pairs=document.cookie.split(";");
		 
		for(var i=0;i<pairs.length;i++){
			var pairsSplit=pairs[i].split("=");
			if(pairsSplit[0]==name){
				return pairsSplit[1];
				break;
			}
		}
		return 0;
		
	}
	function searchClic() {
		 
		var year = document.getElementById("year").value;
		var month = document.getElementById("month").value;					
				
		if (month != "" && year=="0000") {
			alert("请选择年");

			return false;
		}
				
		frmSearch.action="search!listCountOne.action?methodFlag=all";
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
						
		window.open("downLoadExcelCount!init.action"+getParamOne()+"&t_flag=-1");
	}

	function changeTflx() {

		document.getElementById("param_desc").style.display = "none";
		
		var tflx = document.getElementById("j_c_ffTypeId").value;
		var dis = "j_batchparaid";
		if (tflx != "") {
					
			$.ajax ({		
				url : "joson!createOption.action",
				type : "post",		
				dataType : "json",			
				data : "tflx_id="+tflx,

				success : 	function(jsonString) {					
					if (jsonString.success) {
	
						document.getElementById(dis).options.length=0; 
						document.getElementById(dis).options.add(new Option("请选择","")); 
						
						for(var i = 0; i < jsonString.list.length; i++){
							var batchpara =   jsonString.list[i];
							var batchpara_id = batchpara.batchpara_id;
							var batchpara_name = batchpara.batchpara_name;
							
							document.getElementById(dis).options.add(new Option(batchpara_name,batchpara_id)); 								
						}
					}
				}				
			});
			
		}
		else {
			return false;
		}
	}

	openView = function() {
			
		var rksj = arguments[0];
		var cph = arguments[1];
		var tflx_id = arguments[2];
		var batchpara_id = arguments[3];
		var currentPage = arguments[4];
	
		//var c_cph = cph.replace(/\#/g,"@");

		document.getElementById("cph").value=cph;
		document.getElementById("rksj").value=rksj;
		document.getElementById("tflx_id").value=tflx_id;
		document.getElementById("batchpara_id").value=batchpara_id;		
		var param = "?rksj="+rksj+"&tflx_id="+tflx_id+"&batchpara_id="+batchpara_id+"&cph="+cph+"&cPage="+currentPage;
	
		frmSearch.action = "search!countView.action";		
		frmSearch.submit();
		
	}

	function getParamDesc() {
		var j_c_ffTypeId = document.getElementById("j_c_ffTypeId").value;
		
		if (j_c_ffTypeId != "") {
						
			$.ajax({
				url : "joson!findParamByBatchparaId.action",
				type : "post",		
				dataType : "json",			
				data : "tflx_id="+j_c_ffTypeId+"&j_c_flag=1",
				success : 	function(jsonString) {
					
					if (jsonString.success) {

						var paramDesc = "";		
						var selectTwo = "---------------------------【必选】";
						var selectOne = "---------------------------【可选】";
						var type = "";
						for(var i = 0; i < jsonString.list.length; i++){
							var newDesc = "";
							var newType = "";
							var param =   jsonString.list[i];
							var para_desc = param.para_desc;
							var tj_type = param.tj_type;

							newDesc = para_desc+"<br>";								
							if (tj_type == '2') {									
								newType = selectTwo+"<br>";								
							}
							else if (tj_type == '1' ) {									
								newType = selectOne+"<br>";	
							}
							paramDesc += newDesc;
							type += newType;								
						}
						
						document.getElementById("td_desc").innerHTML =  paramDesc;	
						document.getElementById("td_type").innerHTML =  type;
						document.getElementById("param_desc").style.display = "inline";
					}
				}

				});

			
		}
		else {
			document.getElementById("td_desc").innerHTML =  "";	
			document.getElementById("td_type").innerHTML =  "";
			return false;
		}
	}
	
	
</script>

</head>

<body onload="body_onload();" >
	
	<div class="body-box">

	<div class="rhead">
		<div class="rpos">您的当前位置: 逃费车辆管理-->逃费车辆查询</div>
		<div class="clear"></div>
	</div>

	<form  id="frmSearch" name="frmSearch" method="post"  action="search!listCount.action">
	
	<input type="hidden" id="cph" name="cph"  />
	<input type="hidden" id="rksj" name="rksj"  />
	<input type="hidden" id="tflx_id" name="tflx_id"  />
	<input type="hidden" id="batchpara_id" name="batchpara_id"  />
	
	
	<div class="rhead" id="illuminateDiv">
		<b> 功能简介：</b><br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本模块可根据时间、逃费方式、车牌号等条件灵活组合查询由数据挖掘模型挖掘出来的逃费车辆信息。<br>
		选择”逃费方式”下拉框中的某一逃费方式，页面右侧可显示这一逃费方式的详细挖掘参数配置信息。<br>
		选择相应的查询条件，点击“查询”按钮，页面下方将显示出查询结果信息，如：入口时间、逃费方式、出口车牌号、逃费次数、明细信息等信息，<br>
		点击查询结果信息中的“明细”字段，还可显示这一车辆的详细信息；还可在查询条件“车牌号”内输入要查询的车牌号，点击“查询”按钮，查询该车牌号的结果信息。<br>
		点击“Excel报表打印”按钮，可将查询的结果信息全部导出。点击“进入逃费车辆详细查询界面“，<br>
		可灵活组合查询条件，如：逃费方式、起止日期、入口分中心、入口收费站、出口分中心、出口收费站、收费员、车牌号等查询逃费车辆信息，还可根据需要进行组合排序，便于查询相关数据信息；<br>
		对查询的结果可进行录入黑名单、排除嫌疑、Excel报表打印等操作。<br>
		
	
	</div>
	
	<div class="rhead">
		
		<table style="background-color:white;">
			<tr>
				<td>
					逃费方式:
			<select name="j_c_ffTypeId" id="j_c_ffTypeId" onchange="return getParamDesc();">
				<option value="">请选择</option>
				
				<c:forEach items="${fleeFeeType}" var="item">
					
					<c:if test="${item.tflx_id != 41}">
						<option value="${item.tflx_id}" <c:if test="${param.j_c_ffTypeId == item.tflx_id}">selected</c:if> >${item.tflx_name}</option>
					</c:if>
											
				</c:forEach>
			</select>							
    	<br />	  
			<br />
			
			年<select id="year" name="year" >
		    <option value="0000"> 请选择</option>
		    		   
		    <c:forEach  var="item" begin="2000" step="1" end="2022">
		  	  <option value="${item}" <c:if test="${param.year == item}">selected</c:if> >${item}</option>	   	
		    </c:forEach>				    	
		  </select>				    
 		
	
		          月:
		    <select name="month" id="month">
				 <option value="">请选择</option>						 
				  <c:forEach  var="item" begin="01" step="1" end="12">
			  	  <option value="${item}" <c:if test="${param.month == item}">selected</c:if> >${item}</option>	   	
			    </c:forEach>	
			 </select>
						
			<br />
		  <br />
			
	        车牌号:<input type="text" name="j_cph" id="j_cph" value="<c:out value="${requestScope.j_cph}"/>" maxlength="14"/>
	   &nbsp;&nbsp;&nbsp;<br /> <br />	        	       
	    
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
								
			
  	</div>
  	
  	<div class="rhead">
  		<a href="search!listAllTwo.action?methodFlag=init" target="rightIFrame"> >>进入逃费车辆详细查询页面</a>			
  	</div>
		
		<div class="rhead">			
			<div class="clear" align="right" >				
				<input type="button" value="  Excel报表列印  " id="printExl" name="printExl" onclick="openExcel();" />
			</div>
		</div>
	</form>  	 
	
	
</div>


<div style="overflow: auto;">
<form id="frmSearchOne" name="frmSearchOne" method="post">
	<display:table name="litPager"  requestURI="search!listCount.action"  cellspacing="1" cellpadding="0" id="someLists" class="pn-ltable" >
	  
	  <display:column title="入口时间" property="rksj" format="{0,date,yyyy-MM-dd HH:mm:ss}"  sortable="true" style="width:150px;"/> 
	      	  	
  	<display:column title="逃费方式" property="tflx_name"  sortable="true"  />
  	<display:column title="挖掘参数名称" property="batchpara_name"  sortable="true" />
  	<display:column title="出口车牌号" property="cph"  sortable="true" />	
  	<display:column title="次数" property="count"  sortable="true" />	    	 
	      
     <display:column title="明细信息" >
     		<a  href="#" onclick="return openView('<c:out value="${someLists.rksj}"/>','<c:out value="${someLists.cph}"/>','<c:out value="${someLists.tflx_id}"/>','<c:out value="${someLists.batchpara_id}"/>','<s:property value="pager.currentPage"/>');">明细</a>
     </display:column>   			 
  </display:table>

</form>
	
	     
 	<!-- 导入分页组件  -->
   <c:if test="${not empty litPager}">
		 <c:import url="/WEB-INF/jsp/page/page.jsp">
		 <c:param name="pageActionUrl" value="search!listCountOne.action"/>
		 <c:param name="methodFlag" value="all"/>
		
		 <c:param name="ffTypeId" value="${param.j_c_ffTypeId}"/>
		 <c:param name="year" value="${param.year}"/>
		 <c:param name="month" value="${param.month}"/>
		
		 <c:param name="cph" value="${param.j_cph}"/>		  
		
		 <c:param name="batchparaid" value="${param.j_batchparaid}"/>					 	
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
