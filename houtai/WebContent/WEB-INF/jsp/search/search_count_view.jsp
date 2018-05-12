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

<script type="text/javascript">
	function goBack() {

		history.go(-1);
		//self.location.href("search!listCount.action");
	}

	getParamOne = function () {
		var rksj='${param.rksj}';
		var tflx_id = '${param.tflx_id}';
   	var batchpara_id = '${param.batchpara_id}';
   	var cph = '${param.cph}';   
   	   	
		var c_cph = cph.replace(/\#/g,"@");
							
		var param = "?rksj="+rksj+"&tflx_id="+tflx_id+"&batchpara_id="+batchpara_id+"&cph="+c_cph;

		return param;
	}
	
	function openExcel() {
						
		window.open("downLoadExcelCountView!init.action"+getParamOne());
	}


</script>

</head>

<body>

	<div class="body-box">
	
	<div class="rhead">
		<div class="rpos">您的当前位置: 逃费车辆管理-->逃费车辆查询-->明细页面</div>
		<div class="clear"></div>
	</div>
	<div class="rhead">	
	
		<form class="ropt" method="post">
			<input type="button" value="返回列表" onclick="goBack();"/>
		</form>
		<div class="clear"></div>
	</div>
	
	<div class="rhead">			
		<div class="clear" align="right" >				
			<input type="button" value="  Excel报表列印  " id="printExl" name="printExl" onclick="openExcel();" />
		</div>
	</div>
	
	<form  id="frmSearch" name="frmSearch" method="post"  action="search!listAllOne.action">
	
		<div class="rhead" style="display: none;">
			
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
	
	</form>  	 
	<script type="text/javascript">  

		function changeDiv() {
	 	 	var lsh_tf = arguments[0];
	 	 	var br_id = arguments[1]; 		
			var url = "search!countViewinc.action";
			$("#loadpage").load(url,{"lsh_tf":lsh_tf,"br_id":br_id});				
	 	}	
	</script>
	
</div>

<div style="overflow: auto;height: 400px;">
<form id="frmSearchOne" name="frmSearchOne" method="post">
	<display:table name="litPager"  requestURI="search!countView.action"  cellspacing="1" cellpadding="0" id="someLists" class="pn-ltable" style="width:3000px;">
	       	
  	<display:column title="请选择" style="width:50px;">
  		<input type="checkbox" id="chk_${someLists_rowNum}" name="chk" value="<c:out value="${someLists.lsh}"/>" />
  	</display:column>
  	<display:column title="逃费方式" property="fleeFeeType.tflx_name"  sortable="true"  style="width:380px;"/>
  	
  	<display:column title="配合车辆"  sortable="true"  style="width:60px;">	       		
  		<c:if test="${someLists.tflx_id == 8 || someLists.tflx_id == 13 || someLists.tflx_id == 14 || someLists.tflx_id == 15 || someLists.tflx_id == 18}">
  			<a href="#" onclick="changeDiv('<c:out value="${someLists.lsh}"/>','<c:out value="${someLists.br_id}"/>');">配合车辆</a>		
  		</c:if>
  		
  	</display:column>
  	
  	<%-- 修正嫌疑度 ：5 高 4 较高 3 一般 2 较低 1 低 0 无嫌疑 --%>
  	<display:column title="嫌疑度"  sortable="true"  style="width:60px;">	       		
  		<c:if test="${someLists.xzxyd == 5}">高</c:if>
  		<c:if test="${someLists.xzxyd == 4}">较高 </c:if>
  		<c:if test="${someLists.xzxyd == 3}">一般</c:if>
  		<c:if test="${someLists.xzxyd == 2}">较低</c:if>
  		<c:if test="${someLists.xzxyd == 1}">低</c:if>
  		<c:if test="${someLists.xzxyd == 0}">无嫌疑</c:if>  		
  	</display:column>
    <display:column title="卡号" property="kh"  sortable="true" style="width:100px;text-align:right;"/>
    <display:column title="入口车牌号" property="rkcph"  sortable="true" style="width:100px;"/>
	       	
		<%-- 
		   		车型(类型)
			1-一型货，2-二型货，3-三型货，4-四型货，5-五行货，9-一型客，7-二型客，8-三型客，a-四型客 
		--%>
		<display:column title="入口车辆类型"   sortable="true" style="width:100px;">
			<c:if test="${someLists.rkcllx == 'a'}">四型客</c:if>
			<c:if test="${someLists.rkcllx == '8'}">三型客 </c:if>
			<c:if test="${someLists.rkcllx == '7'}">二型客</c:if>
			<c:if test="${someLists.rkcllx == '9'}">一型客</c:if>
			<c:if test="${someLists.rkcllx == '5'}">五型货</c:if>
			<c:if test="${someLists.rkcllx == '4'}">四型货</c:if>
			<c:if test="${someLists.rkcllx == '3'}">三型货</c:if>
			<c:if test="${someLists.rkcllx == '2'}">二型货</c:if>
			<c:if test="${someLists.rkcllx == '1'}">一型货</c:if>	       		
		</display:column>
	       	
		<%--       
		  		车类(种类)
		1-普通车，2-公务车，3-军车，4-车队，9-违章      
		--%>
		<display:column title="入口车辆种类"   sortable="true" style="width:100px;">
			<c:if test="${someLists.rkclzl == '1'}">普通车</c:if>
			<c:if test="${someLists.rkclzl == '2'}">公务车 </c:if>
			<c:if test="${someLists.rkclzl == '3'}">军车</c:if>
			<c:if test="${someLists.rkclzl == '4'}">车队</c:if>
			<c:if test="${someLists.rkclzl == '9'}">违章</c:if>	       		
		</display:column>
	       	
   	<display:column title="入口收费站" property="rkzhan.zmc"  sortable="true" style="width:200px;"/>
   	<display:column title="入口时间" property="rksj" format="{0,date,yyyy-MM-dd HH:mm:ss}"  sortable="true" style="width:150px;"/>
   	<display:column title="入口收费员代码" property="rksfydm"  sortable="true" style="width:100px;"/>	       		       	
   	<display:column title="入口车道代码" property="rkcddm"  sortable="true" style="width:100px;"/>
   		
   	<display:column title="出口车牌号" property="cph"  sortable="true" style="width:100px;"/>	
   	<display:column title="出口收费站" property="ckzhan.zmc"  sortable="true" style="width:200px;"/>	
   	<display:column title="出口时间" 			property="cksj"  format="{0,date,yyyy-MM-dd HH:mm:ss}"  sortable="true" style="width:150px;"/>
   	<display:column title="出口收费员代码" property="sfydm"  sortable="true" style="width:100px;"/>
   	<display:column title="出口车道代码" 	property="ckcddm"  sortable="true" style="width:100px;"/>
	       	
   	<display:column title="出口车种类" 	 sortable="true" style="width:100px;">
   		<c:if test="${someLists.clzl == '1'}">普通车</c:if>
   		<c:if test="${someLists.clzl == '2'}">公务车 </c:if>
   		<c:if test="${someLists.clzl == '3'}">军车</c:if>
   		<c:if test="${someLists.clzl == '4'}">车队</c:if>
   		<c:if test="${someLists.clzl == '9'}">违章</c:if>	    	       		       	
   	</display:column>	       
   	<display:column title="出口车类型" 	  sortable="true" style="width:100px;">
   		<c:if test="${someLists.cllx == 'a'}">四型客</c:if>
   		<c:if test="${someLists.cllx == '8'}">三型客 </c:if>
   		<c:if test="${someLists.cllx == '7'}">二型客</c:if>
   		<c:if test="${someLists.cllx == '9'}">一型客</c:if>
   		<c:if test="${someLists.cllx == '5'}">五型货</c:if>
   		<c:if test="${someLists.cllx == '4'}">四型货</c:if>
   		<c:if test="${someLists.cllx == '3'}">三型货</c:if>
   		<c:if test="${someLists.cllx == '2'}">二型货</c:if>
   		<c:if test="${someLists.cllx == '1'}">一型货</c:if>	  	       		     
   	</display:column>
	       	
   	<display:column title="里程(公里)" 	  sortable="true" style="width:100px;text-align:right;">
   		<c:out value="${someLists.juli/1000}"/>
   	</display:column>
   	<display:column title="速度(公里/小时)" 	  sortable="true" style="width:100px;text-align:right;">
   		<c:out value="${someLists.rate/1000}"/>
   	</display:column>
   	
   	<display:column title="轴数" 	property="zs"  sortable="true" style="width:100px;text-align:right;"/>
   	<%-- <display:column title="轴组信息" 	property="zzxx"  sortable="true" /> --%>
   	<display:column title="总重量(吨)"  sortable="true" style="width:100px;text-align:right;">
   		<c:out value="${someLists.zz/1000}"/>
   	</display:column>
   	<display:column title="限制总重(吨)" 	  sortable="true" style="width:100px;text-align:right;">
   		<c:out value="${someLists.xzzl/1000}"/>
   	</display:column>
   	<display:column title="超限率(%)" 	  sortable="true" style="width:100px;text-align:right;">
   		<c:if test="${someLists.zz > someLists.xzzl && someLists.xzzl != 0}">   			
   			<fmt:formatNumber value="${((someLists.zz-someLists.xzzl)/1000)/(someLists.xzzl/1000)*100}" pattern="##.##"  /> 
   		</c:if>   	   		
   	</display:column>
   	<display:column title="修改总重(吨)" 	 sortable="true" style="width:100px;text-align:right;">
   		<c:out value="${someLists.xgzz/1000}"/>
   	</display:column>
   	
   	<display:column title="通行费(元)" 	 sortable="true" style="width:100px;text-align:right;">
   		<c:out value="${someLists.fee}"/>
   	</display:column>
	       	
    <%-- 	<display:column title="删除" >
    		 <a href="javascript:void(0);" onclick="deleQrLog('<c:out value="${someLists.lsh}"/>')">删除</a>
    	
    </display:column>		--%>				    
  </display:table>

</form>
</div>	
	     
 	<!-- 导入分页组件  -->
   <c:if test="${not empty litPager}">
		 <c:import url="/WEB-INF/jsp/page/page.jsp">
		 <c:param name="pageActionUrl" value="search!countView.action"/>
		 
		 <c:param name="rksj" value="${param.rksj}"/>
		 <c:param name="cph" value="${param.cph}"/>
		 <c:param name="tflx_id" value="${param.tflx_id}"/>
		 <c:param name="batchpara_id" value="${param.batchpara_id}"/>
		
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

	<div id="loadpage" style="height: 100%;"></div>

</body>

</html>
