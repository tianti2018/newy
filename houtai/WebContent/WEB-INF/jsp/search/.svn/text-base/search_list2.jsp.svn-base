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

		getParamDesc();
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
			document.getElementById(dis).options.length=0; 
			document.getElementById(dis).options.add(new Option("请选择","")); 

			return false;
		}
		
		/*Ext.onReady(function () {
			Ext.Ajax.request( {
				url : 'joson!createOption.action',
				method : 'post',
				autoAbort : true,
				params : {
					pvalue: zdmValue				
				},
				success : 	function(response, options) {
					var responseArray = Ext.util.JSON.decode(response.responseText);
					if (responseArray.success) {
	
						document.getElementById(dis).options.length=0; 
						document.getElementById(dis).options.add(new Option("请选择","")); 
						
						for(var i = 0; i < responseArray.list.length; i++){
							var zhan =   responseArray.list[i];
							var zdm = zhan.zdm;
							var zmc = zhan.zmc;
	
							document.getElementById(dis).options.add(new Option(zmc,zdm)); 
							
						}
					}
				}											
			});
		
		});*/

		$.ajax ({		
			url : "joson!createOption.action",
			type : "post",		
			dataType : "json",			
			data : "pvalue="+zdmValue,

			// 回传函数
			success : function callBackOne(jsonString) {
				if (jsonString.success) {
				
					document.getElementById(dis).options.length=0; 
					document.getElementById(dis).options.add(new Option("请选择","")); 
				
					for(var i = 0; i < jsonString.list.length; i++){
						var zhan =   jsonString.list[i];
						var zdm = zhan.zdm;
						var zmc = zhan.zmc;

						document.getElementById(dis).options.add(new Option(zmc,zdm)); 					
					}
				}
	
			}

		});
		
	}
	
	function openSearchCount() {
		window.open("serchCount.html","riaghtIFrame");	
	}

	function searchClic() {

		var fromDate = document.getElementById("j_fromDate").value;
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

		if (j_orderOne != "") {
			if ((j_orderOne == j_orderTwo) || (j_orderOne == j_orderThree)) {
				alert("请确认排序的值不能一致");

				return false;
			}
		}
		if (j_orderTwo != "") {
			if ((j_orderOne == j_orderTwo) || (j_orderTwo == j_orderThree)) {
				alert("请确认排序的值不能一致");

				return false;
			}
		}
		if (j_orderThree != "") {
			if ((j_orderThree == j_orderTwo) || (j_orderOne == j_orderThree)) {
				alert("请确认排序的值不能一致");

				return false;
			}
		}
		

		if (fromDate != "" && j_endDate!="") {
			if (fromDate > j_endDate) {
				alert("请确认起日期不能大于止日期");

				return false;
			}
		}
				
		frmSearch.action="search!listAllOne.action?methodFlag=all";
		frmSearch.submit();
	
	}

	getParamOne = function () {
		var methodFlag='${param.methodFlag}';
		var ffTypeId = '${param.j_c_ffTypeId}';
   	var fromDate = '${param.j_fromDate}';
   	var endDate = '${param.j_endDate}';
   	var rk_zdmp = '${param.j_rk_zdmp}';
   	var rk_zdmc = '${param.j_rk_zdmc}';
   	
   	var ck_zdmp = '${param.j_ck_zdmp}';
   	var ck_zdmc = '${param.j_ck_zdmc}';
   	
   	var sfydm = '${param.j_sfydm}';
   	var cph = '${param.j_cph}';

   	var batchparaid = '${param.j_batchparaid}';
   	var orderOne = '${param.j_orderOne}';
   	var orderTwo = '${param.j_orderTwo}';
    var orderThree = '${param.j_orderThree}';
       	
		var c_cph = cph.replace(/\#/g,"@");
							
		var param = "?methodFlag="+methodFlag+"&a="+ffTypeId+"&b="+fromDate
							+"&c="+endDate+"&d="+rk_zdmp+"&e="+rk_zdmc
							+"&f="+ck_zdmp+"&g="+ck_zdmc+"&h="+sfydm+"&i="+c_cph
							+"&j="+batchparaid+"&k="+orderOne+"&l="+orderTwo+"&m="+orderThree;


		return param;
	}
	
	function openExcel() {
						
		window.open("downLoadExcel!init.action"+getParamOne());
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

	function goBack() {

		self.location.href="search!listCount.action";
	}

	function getParamDesc() {
		var j_batchparaid = document.getElementById("j_batchparaid").value;
		
		if (j_batchparaid != "") {
			
			$.ajax({
				url : "joson!findParamByBatchparaId.action",
				type : "post",		
				dataType : "json",			
				data : "j_batchparaid="+j_batchparaid,
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

<body onload="body_onload();">
	
	<div class="body-box">

	<div class="rhead">
		<div class="rpos">您的当前位置: 逃费车辆管理-->逃费车辆查询-->详细查询页面</div>
		<div class="clear"></div>
	</div>
	
	<c:if test="${param.back != '1' || requestScope.back !='1' }">
	<div class="rhead">	
			
		<form class="ropt" method="post">
			<input type="button" value="返回列表" onclick="goBack();"/>
		</form>
		
		<div class="clear"></div>
	</div>
	</c:if>
	<form  id="frmSearch" name="frmSearch" method="post"  action="search!listAllOne.action">
	
	
	<div class="rhead">
			
			<table style="background-color:white;">
				<tr>
					<td>
						逃费方式:
			<select name="j_c_ffTypeId" id="j_c_ffTypeId" onchange="return changeTflx();">
				<option value="xxxx">请选择</option>
				
				<c:forEach items="${fleeFeeType}" var="item">
					
					<c:if test="${item.tflx_id != 41}">
						<option value="${item.tflx_id}" <c:if test="${param.j_c_ffTypeId == item.tflx_id}">selected</c:if> >${item.tflx_name}</option>
					</c:if>
						
									
				</c:forEach>
			</select>							
    	<br />
	    <br />
	   	 起日期:    
			<input type="text" id="j_fromDate" name="j_fromDate" value="${param.j_fromDate}" readonly="readonly"/>
			<img  onclick="WdatePicker({el:'j_fromDate',startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" src="js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"/> 
			&nbsp;&nbsp;&nbsp;止日期:
			<input type="text" id="j_endDate" name="j_endDate" value="${param.j_endDate}" readonly="readonly"/> 
			<img onclick="WdatePicker({el:'j_endDate',startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" src="js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"/>
			<br />
			<br />
			
			<c:if test="${sessionScope.user.zhan.zdm == '000000'}">
			
		  	入口:
			  <select name="j_rk_zdmp" id="j_rk_zdmp" onchange="return changeSelect('j_rk_zdmp','1');">
				  <option value="">请选择</option>
				  <c:forEach items="${listRCKPZhan}"  var="item">
			   	  <option value="${item.zdm}" <c:if test="${param.j_rk_zdmp == item.zdm}">selected</c:if> >${item.zmc}</option>	   	
			    </c:forEach>		
			  </select>
			</c:if>
			<c:if test="${sessionScope.user.zhan.parentdm == '000000'}">							
				入口站<select id="j_rk_zdmc" name="j_rk_zdmc" >
			    <option value=""> 请选择</option>
			    		   
			    <c:forEach items="${listRKCZhan}"  var="item">
			  	  <option value="${item.zdm}" <c:if test="${param.j_rk_zdmc == item.zdm}">selected</c:if> >${item.zmc}</option>	   	
			    </c:forEach>				    	
			  </select>				    
  		</c:if>				  
			<c:if test="${sessionScope.user.zhan.zdm == '000000'}">	  
			          出口:
			    <select name="j_ck_zdmp" id="j_ck_zdmp" onchange="return changeSelect('j_ck_zdmp','2');">
					 <option value="">请选择</option>						 
					 <c:forEach items="${listRCKPZhan}"  var="item">
				   	 <option value="${item.zdm}" <c:if test="${param.j_ck_zdmp == item.zdm}">selected</c:if> >${item.zmc}</option>	   	
				   </c:forEach>		
				 </select>
			</c:if>	
			<c:if test="${sessionScope.user.zhan.parentdm == '000000'}">	
		   出口站<select id="j_ck_zdmc" name="j_ck_zdmc" >
		     <option value=""> 请选择</option>
		     <c:forEach items="${listCKCZhan}"  var="item">
			   	 <option value="${item.zdm}" <c:if test="${param.j_ck_zdmc == item.zdm}">selected</c:if> >${item.zmc}</option>	   	
			   </c:forEach>
		   </select>
  		</c:if>	  		
			<br />
		  <br />
			
	        收费员:<input type="text" name="j_sfydm" id="j_sfydm" value="<c:out value="${param.j_sfydm}"/>" maxlength="50"/>
	        车牌号:<input type="text" name="j_cph" id="j_cph" value="<c:out value="${requestScope.j_cph}"/>" maxlength="14"/>
	   &nbsp;&nbsp;&nbsp;<br /> <br />
	        
	          参数结果名称:
	    <select name="j_batchparaid" id="j_batchparaid" onchange="getParamDesc();">
	      <option value="">请选择</option>	   
	   	  <c:forEach items="${litBatchpara}"  var="item">
	   		  <option value="${item.batchpara_id}" <c:if test="${param.j_batchparaid == item.batchpara_id}">selected</c:if> >${item.batchpara_name}</option>	   	
	   	  </c:forEach>	   		
	    </select>	 
					</td>
					
					<td>
						<div id="param_desc"  style="float: right;margin-right: 5%;display: none;">
							<table>
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
		
	  	<div class="clear" align="left">排序: 
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
        <input type="hidden" id="back" name="back" value="${param.back}" />
        &nbsp;&nbsp;&nbsp;&nbsp;<input name="button" type="button" onclick="return searchClic();" value="  查询  "/>
		  </div>
		</div>
	
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
	
		<div class="rhead">
			
			<div class="clear" align="right" >
				<input type="button" value="  录入黑名单  "    id="enter"    name="enter" onclick="return checkAllRadio('1');" />
				<input type="button" value="  排除嫌疑  "    id="enterQrlog"    name="enterQrlog" onclick="return checkAllRadio('2');" />
				<input type="button" value="  Excel报表列印  " id="printExl" name="printExl" onclick="openExcel();" />
			</div>
		</div>
	</form>  	 
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

			var param = "";
				
			 var oForm=document.all("frmSearchOne");
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
			 
			 else {

				 var checks=document.getElementsByName('chk');
				 var flagOne = 0;
		      for(var i=0;i<checks.length;i++) {            

		    		if (checks[i].checked) {	
		    			flagOne += 1;		    			
		    			if (flagOne==1) {
			    			
		    				param = checks[i].value;
			    		}
		    			else {
		    				param+=","+checks[i].value;
			    		}
		        } 
				  			
			  	}

		      var chk_url = "";
		      if (arguments[0]=="1") {
		    	  chk_url = 'search!enter.action';
			    }
		      else if (arguments[0]=="2") {
		    	  chk_url = 'search!createQrLog.action';
			    }
						    
				$.ajax({
					url : chk_url,
					type : "post",		
					dataType : "json",			
					data : "param="+param,
					success : 	function(jsonString) {
								
								if (jsonString.success) {
									alert(jsonString.message);
			
									if (jsonString.message == "排除嫌疑成功") {
										document.getElementById("frmSearchOne").action="search!listAllOne.action?methodFlag=all";
										document.getElementById("frmSearchOne").submit();		
									}															
								}
							}	
					});
				
							  
			  return true;
		  }		
	  }
	
	</script>
	
</div>

<div style="overflow: auto;">
<form id="frmSearchOne" name="frmSearchOne" method="post">
	<display:table name="litPager"  requestURI="search!listAllOne.action"  cellspacing="1" cellpadding="0" id="someLists" class="pn-ltable" style="width:3000px;">
	       	
  	<display:column title="请选择" style="width:50px;">
  		<input type="checkbox" id="chk_${someLists_rowNum}" name="chk" value="<c:out value="${someLists.id}"/>" />
  	</display:column>
  	<display:column title="逃费方式" property="fleeFeeType.tflx_name"  sortable="true"  style="width:380px;"/>
  	
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
		 <c:param name="pageActionUrl" value="search!listAllOne.action"/>
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
		 <c:param name="back" value="${param.back}"/>		 		
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

	

</body>

</html>
