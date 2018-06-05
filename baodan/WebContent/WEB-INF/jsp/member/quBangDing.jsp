<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta name="apple-mobile-web-app-capable" content="yes"/>
	<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
	<meta name="format-detection" content="telephone=no"/>
 <link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/base.css" />
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/family.css" />
	<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<title></title>
<base target="_self" />

<link href="images/common_res/css/jquery_validate.css" rel="stylesheet" type="text/css"/>
<link href="images/common_res/css/jquery.alerts.css" rel="stylesheet" type="text/css"/>
<link href="images/core_res/css/front.css" rel="stylesheet" type="text/css"/>
<link href="images/core_res/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="images/core_res/css/theme.css" rel="stylesheet" type="text/css"/>
 
<script src="images/common_res/js/jquery_validate.js" type="text/javascript"></script>
<script src="images/common_res/js/pony.js" type="text/javascript"></script>
<script src="images/common_res/js/jquery.ui.draggable.js" type="text/javascript"></script>
<script src="images/common_res/js/jquery.alerts.js" type="text/javascript"></script>
 
<script src="images/core_res/js/front.js" type="text/javascript"></script>
<script src="images/core_res/js/admin.js" type="text/javascript"></script>

<script src="js/common.js" type="text/javascript"></script>


<script type="text/javascript">

	var frmOrder = null;
	function body_onload () {
		frmOrder = document.frmOrder;
		
		var message = "<c:out value="${message}"/>";
		
		if (message != "" && message!=null) {
			alert(message);
			if ('操作成功'==message && window.dialogArguments!='') {
				window.close();
				window.returnValue = "true";
			}
			
		}
		
	}
	
	function bangding(parentId){
		var useryId = $("#useryId").val();
		if(parentId!=null&&parentId!=""&&useryId!=null&&useryId!=""){
			$.ajax({
				url : "<%=request.getContextPath()%>/member!bangding.action",
		        type: "post", 
		        data : {"useryId":useryId,
		        		"parentId":parentId},
		        success: function(result) {
		        	if(result.success){
		        		alert("绑定成功!");
		        		window.location.href="member!listAllMembers.action";
		        	}else{
		        		alert("绑定失败!");
		        	}
		        }
			});    		
		}
	}
	
	function search(){
		var useryId = $("#chaxunId").val();
		var userName = $("#chaxunNicheng").val();
		if((useryId!=null&&useryId.trim()!="")||(userName!=null&&userName.trim()!=""))
		$.ajax({
			url : "<%=request.getContextPath()%>/member!searchMember.action",
	        type: "post", 
	        data : {"useryId":useryId,
	        		"userName":userName},
	        success: function(result) {
	     	   if(result!=null||result!=""){
	     		   var trId = $("#trId");
	     		   var tr = '';
	     		   for(var i=0;i<result.length;i++){
	     			  var usery = result[i];
	     			  tr+='<tr  onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">';
	     			  tr+='<td align="center">'+usery.id+'</td>'+
	     						'<td align="center">'+usery.userName +'</td>'+
	     						'<td align="center">'+usery.loginName +'</td>'+
	     						'<td align="center">'+usery.phone +'</td>'+
	     						'<td align="center">'+usery.useryname +'</td>'+
	     						'<td align="center">'+
	     						'	<img src="'+usery.headUrl +'" style="width: 50px"/>'+
	     						'</td>';
	     						
	     			tr+='<td align="center">';
	     			if(usery.level==0){
	     				tr+='准店小二';
	     			}else if(usery.level==1){
	     				tr+='店小二';
	     			}else if(usery.level==2){
	     				tr+='掌柜';
	     			}else if(usery.level==3){
	     				tr+='大掌柜';
	     			}
	     			
	     			tr+='</td>';
	     			tr+='<td align="center">'+usery.dianPuId +'</td>';
	     			tr+='<td align="center">';
	     			if(usery.subscribe==0){
	     				tr+='已关注</td>';
	     			}else 
	     				tr+='未关注</td>';
	     			
	     			tr+='<td align="center">'+
	     							'<input type="button" value="关系绑定" onclick="bangding('+usery.id+');" /> '+
	     						'</td>';
	     			tr+='</tr>';
	     			 
	     			trId.append(tr);
	     			
	     		  
	     		   }
	     	   }
	        },
	        error: function() {
	     	   alert("系统错误，请联系管理员！",0);
	        },
	    	beforeSend : function() {
	        }
	     });
		else 
			alert("请填写查询条件!");
	}	
</script>
 
</head>



<body onload="body_onload();">
<div class="body-box">
<div class="rhead">
		<div class="rpos">您的当前位置: 后台管理-->用户查询-->绑定关系</div>
		<div class="clear"></div>
	</div>



	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">ID：</td>
			<td width="80%" class="pn-fcontent">
				<input id="chaxunId" />
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">昵称：</td>
			<td width="80%" class="pn-fcontent">
				<input id="chaxunNicheng" />
			</td>
		</tr>
	
		<tr>
			<td width="20%" class="pn-fbutton">
				<input type="button" id="cmdButton" onclick="search();" value="查询" style="cursor:pointer;width: 100px;"/>
			</td>
			<td width="80%" class="pn-fbutton">
				<input type="button" value="重置" onclick="chongzhi();" style="cursor:pointer;width: 100px;"/>
			</td>
			
		</tr>
	</table>
	<input type="hidden" id="useryId" name="useryId"  value="${useryId}" />
	<table id="trId" width="100%" border="1">
		<tr>
			<th width="50">用户ID</th>
			<th width="80">用户姓名</th>
			<th width="80">登录名称</th>
			<th width="80">电话</th>
			<th width="80">微信昵称</th>
			<th width="80">微信头像</th>
			<th width="80">头衔</th>
			<th width="80">店铺ID</th>
			<th width="80">状态</th>
			<th width="80">操作</th>
		</tr>
	</table>
	
</div>
</body>
</html>
