<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %> 

<div class="rhead">
	配合车辆显示
</div>

<div style="overflow: auto;height: 200px;">

	<display:table name="litPager"  requestURI="search!countViewinc.action"  cellspacing="1" cellpadding="0" id="someListsOne" class="pn-ltable" style="width:3000px;">
	       	 
  	
    <display:column title="卡号" property="ckls.kh"  sortable="true" style="width:100px;text-align:right;"/>
    <display:column title="入口车牌号" property="ckls.rkcph"  sortable="true" style="width:100px;"/>
	       	
		<%-- 
		   		车型(类型)
			1-一型货，2-二型货，3-三型货，4-四型货，5-五行货，9-一型客，7-二型客，8-三型客，a-四型客 
		--%>
		<display:column title="入口车辆类型"   sortable="true" style="width:100px;">
			
			<c:if test="${someListsOne.ckls.rkcllx == 'a'}">四型客</c:if>
			<c:if test="${someListsOne.ckls.rkcllx == '8'}">三型客 </c:if>
			<c:if test="${someListsOne.ckls.rkcllx == '7'}">二型客</c:if>
			<c:if test="${someListsOne.ckls.rkcllx == '9'}">一型客</c:if>
			<c:if test="${someListsOne.ckls.rkcllx == '5'}">五型货</c:if>
			<c:if test="${someListsOne.ckls.rkcllx == '4'}">四型货</c:if>
			<c:if test="${someListsOne.ckls.rkcllx == '3'}">三型货</c:if>
			<c:if test="${someListsOne.ckls.rkcllx == '2'}">二型货</c:if>
			<c:if test="${someListsOne.ckls.rkcllx == '1'}">一型货</c:if>	       		
		</display:column>
	       	
		<%--       
		  		车类(种类)
		1-普通车，2-公务车，3-军车，4-车队，9-违章      
		--%>
		<display:column title="入口车辆种类"   sortable="true" style="width:100px;">
			<c:if test="${someListsOne.ckls.rkclzl == '1'}">普通车</c:if>
			<c:if test="${someListsOne.ckls.rkclzl == '2'}">公务车 </c:if>
			<c:if test="${someListsOne.ckls.rkclzl == '3'}">军车</c:if>
			<c:if test="${someListsOne.ckls.rkclzl == '4'}">车队</c:if>
			<c:if test="${someListsOne.ckls.rkclzl == '9'}">违章</c:if>	       		
		</display:column>
	       	
   	<display:column title="入口收费站" property="ckls.rkZhan.zmc"  sortable="true" style="width:200px;"/>
   	<display:column title="入口时间" property="ckls.rksj1" format="{0,date,yyyy-MM-dd HH:mm:ss}"  sortable="true" style="width:150px;"/>
   	<display:column title="入口收费员代码" property="ckls.rksfydm"  sortable="true" style="width:100px;"/>	       		       	
   	<display:column title="入口车道代码" property="ckls.rkcddm"  sortable="true" style="width:100px;"/>
   		
   	<display:column title="出口车牌号" property="ckls.cph"  sortable="true" style="width:100px;"/>	
   	<display:column title="出口收费站" property="ckls.zhan.zmc"  sortable="true" style="width:200px;"/>	
   	<display:column title="出口时间" 			property="ckls.cssj1"  format="{0,date,yyyy-MM-dd HH:mm:ss}"  sortable="true" style="width:150px;"/>
   	<display:column title="出口收费员代码" property="ckls.sfydm"  sortable="true" style="width:100px;"/>
   	<display:column title="出口车道代码" 	property="ckls.cddm"  sortable="true" style="width:100px;"/>
	       	
   	<display:column title="出口车种类" 	 sortable="true" style="width:100px;">
   		<c:if test="${someListsOne.ckls.clzl == '1'}">普通车</c:if>
   		<c:if test="${someListsOne.ckls.clzl == '2'}">公务车 </c:if>
   		<c:if test="${someListsOne.ckls.clzl == '3'}">军车</c:if>
   		<c:if test="${someListsOne.ckls.clzl == '4'}">车队</c:if>
   		<c:if test="${someListsOne.ckls.clzl == '9'}">违章</c:if>	    	       		       	
   	</display:column>	       
   	<display:column title="出口车类型" 	  sortable="true" style="width:100px;">
   		<c:if test="${someListsOne.ckls.cllx == 'a'}">四型客</c:if>
   		<c:if test="${someListsOne.ckls.cllx == '8'}">三型客 </c:if>
   		<c:if test="${someListsOne.ckls.cllx == '7'}">二型客</c:if>
   		<c:if test="${someListsOne.ckls.cllx == '9'}">一型客</c:if>
   		<c:if test="${someListsOne.ckls.cllx == '5'}">五型货</c:if>
   		<c:if test="${someListsOne.ckls.cllx == '4'}">四型货</c:if>
   		<c:if test="${someListsOne.ckls.cllx == '3'}">三型货</c:if>
   		<c:if test="${someListsOne.ckls.cllx == '2'}">二型货</c:if>
   		<c:if test="${someListsOne.ckls.cllx == '1'}">一型货</c:if>	  	       		     
   	</display:column>
   	
   	<display:column title="通行费(元)" property="ckls.ystxf"  sortable="true" style="width:100px;text-align:right;"/>	       	  
   	
   	<display:column title="轴数" 	property="ckls.zs"  sortable="true" style="width:100px;text-align:right;"/>
   	<%-- <display:column title="轴组信息" 	property="zzxx"  sortable="true" /> --%>
   	<display:column title="总重量(吨)"  sortable="true" style="width:100px;text-align:right;">
   		<c:out value="${someListsOne.ckls.zz/1000}"/>
   	</display:column>
   	
   	<display:column title="轴组数量" property="ckls.zzsl"  sortable="true" style="width:100px;"/>	 
   	
   	<%--
   	<display:column title="限制总重(吨)" 	  sortable="true" style="width:100px;text-align:right;">
   		<c:out value="${someListsOne.ckls.jsxzzl/1000}"/>
   	</display:column>
   	
   	<display:column title="修改总重(吨)" 	 sortable="true" style="width:100px;text-align:right;">
   		<c:out value="${someListsOne.ckls.xgzz/1000}"/>
   	</display:column>
   	   
     	<display:column title="删除" >
    		 <a href="javascript:void(0);" onclick="deleQrLog('<c:out value="${someLists.lsh}"/>')">删除</a>
    	
    </display:column>		--%>				    
  </display:table>

</div>	
	      		
	<script type="text/javascript">  
		
		//senfe("表格名称","奇数行背景","偶数行背景","鼠标经过背景","点击后背景");
		senfe("someListsOne","#FFF","#FFF","#F2F2F2","#d9e8fb");
		
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

