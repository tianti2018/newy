<%-- 
/*
 * ----------------------------------------------------------
 * FILE         : nopublishdbomList_view
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

	
	<script type="text/javascript">
		
		allocate = function () {
			var procurementId = document.getElementById("procurementId").value;

			if (procurementId == "") {
				alert("请选择采购员");

				return false;
			}

			else {

				var checks=document.getElementsByName('chk');
			 	var flagOne = 0;
			 	var bommateriallist = "";

			 	var flagChk = false;
			 	
	      for(var i=0;i<checks.length;i++) {            

	    		if (checks[i].checked) {

		    			
	    			flagOne += 1;		    			
	    			if (flagOne==1) {
	    				flagChk = true; // 有一个选中就可以分配任务
	    				
	    				bommateriallist = checks[i].value;
		    		}
	    			else {
	    				bommateriallist+=","+checks[i].value;
		    		}
	        } 
			  			
		  	}

		  	if (!flagChk) {
					alert("请至少勾选一笔明细数据");
					
					return false;
			  }
		  	else {		
		  				  	
		  		$.ajax({			  
					url : "insideJob!allocateBom.action",
					type : "post",		
					dataType : "json",			
					data : "bommateriallist="+bommateriallist+"&procurementId="+procurementId+"&bomId="+'${bomList.bomId}',
					success : 	function(jsonString) {									
								if (jsonString.success) {
									alert(jsonString.message);	
									self.location.href="insideJob!noPublishedBomList.action?bomdivId="+'${bomList.bomId}';
																																
								}
							}	
					});

			  }
			}
	
		}

	 addlastDate = function () {
		   var bomMiddleId = arguments[0];
		   var lastarrivedDate = document.getElementById('lastarrivedDate'+arguments[1]).value; 
		   var laststoreDate = document.getElementById('laststoreDate'+arguments[1]).value; 
		   var remarks = document.getElementById('remarks'+arguments[1]).value; 

				alert(remarks);
		   if (lastarrivedDate == "") {
				  alert("最后到货日期不能为空");
				  return false;
			 }
		   if (laststoreDate == "") {
				  alert("最迟入库时间不能为空");
				  return false;
			 }
			 if (lastarrivedDate > laststoreDate) {
				 alert("最后到货日期不能大于最迟入库时间");
				 return false;
			 }
		   
		   $.ajax({			  
				url : "insideJob!updateBommaterial.action",
				type : "post",		
				dataType : "json",			
				data : "bomMiddleId="+bomMiddleId+"&lastarrivedDate="+lastarrivedDate+"&laststoreDate="+laststoreDate+"&remarks="+remarks,
				success : 	function(jsonString) {									
							if (jsonString.success) {
								alert(jsonString.message);	
								self.location.href="insideJob!noPublishedBomList.action?bomdivId="+'${bomList.bomId}';																						
							}
						}	
				});

		 }

	 
	

	function gocheck() {
		
		$('#remarks'+arguments[0]).maxChar(100);
	}
	 
	</script>

	<div class="rhead">
		<div class="rposdown">下面为Bom清单 ${bomList.bomId} 的明细</div>
		<div class="clear"></div>  
	</div>

	<div class="rhead">
		<script src="js/My97DatePicker/WdatePicker.js"  type="text/javascript"></script> 
						
		采购员
		<select  id="procurementId" name="procurementId">			
			<option value="">请选择</option>
			<c:forEach items="${listUser}" var="item">
				<option  value="${item.userId}" <c:if test="${item.userId == param.procurementId}">selected</c:if> >${item.userName}</option>
			</c:forEach>			  																								
		</select>		
		&nbsp;&nbsp;&nbsp;<input type="button" id="cmdBut" name="cmdBut" value="分配任务" onclick="return allocate('1');"/>
		&nbsp;&nbsp;&nbsp;<input type="button" id="cmdBut" name="cmdBut" value="修改分配任务" onclick="return allocate('2');"/>
	</div>
	
	<div style="overflow: auto;height: 400px;">
	<table class="pn-ltable" width="2000px" cellspacing="1" cellpadding="0" border="0">
		<thead class="pn-lthead">
			<tr>	
				<th width="50">请选择</th>        
        <th width="100">材料类别</th>
       
        <th width="200">材料</th>
        <th width="100">缺货数量</th>
         <th width="100">单位</th>
        <th width="100">规格</th>
        <th width="100">型号</th>
        <th width="200">备注</th>
        <th width="100">分配状态</th>
        <th width="100">发布状态</th>
        <th width="200">采购人员编号</th>
        <th width="200">采购人员姓名</th>
        
        <th  width="100">最迟到货日期</th>
        <th  width="100">最迟入库时间</th>
        <th width="50">操作</th>           
			</tr>
		</thead>

		<tbody class="pn-ltbody">
		
			<c:forEach items="${bomList.bommaterial}" var="item" varStatus="status">
				<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);">		
					<td align="center"><input type="checkbox" id="chk${status.index+1}" value="${item.bomMiddleId}" <c:if test="${item.ispublish == '1'}">disabled</c:if>   name="chk" /></td>							
					<td >${item.material.categroy.categoryName}</td>
					
					<td >${item.material.materialsName}</td>
					<td align="right">${item.outNum}</td>
					<td >${item.material.materialunit.unitname}</td>
					<td align="center">${item.material.materialsType}</td>
					<td >${item.material.materialsSpec}</td>
					<td >
						<textarea id="remarks${status.index+1}" rows="4" cols="20" onkeyup="gocheck('${status.index+1}');">${item.remarks}</textarea>
					</td>	
					<td >
						<c:if test="${item.isallocate == '0'}"><font color="red">未分配</font></c:if>
						<c:if test="${item.isallocate == '1'}"><font color="green">已分配</font></c:if>
					</td>
					<td >
						<c:if test="${item.ispublish == '0'}"><font color="red">未发布</font></c:if>
						<c:if test="${item.ispublish == '1'}"><font color="green">已发布</font></c:if>
					</td>
					<td>${item.properson.userId}</td>	
					<td>${item.properson.userName}</td>
					
				<td>						
						<input class="Wdate" type="text" id="lastarrivedDate${status.index+1}" name="lastarrivedDate${status.index+1}" value="<fmt:formatDate value="${item.lastarrivedDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"  onclick="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,wdate:true})" readonly="readonly"  <c:if test="${item.ispublish == '1'}">disabled</c:if>/>				    				   						
				</td>
				<td>							
					<input type="text" id="laststoreDate${status.index+1}" name="laststoreDate${status.index+1}" value="<fmt:formatDate value="${item.laststoreDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,wdate:true})" readonly="readonly" class="Wdate" <c:if test="${item.ispublish == '1'}">disabled</c:if>/>
			    
				</td>
				
				<td>
					<input type="button"  id="cmd" name="cmd" value="确定" onclick="addlastDate('${item.bomMiddleId}','${status.index+1}');" <c:if test="${item.ispublish == '1'}">disabled</c:if> />
				</td>
										
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>