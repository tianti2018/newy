<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.ResourceBundle"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%ResourceBundle res = ResourceBundle.getBundle("system"); %> 
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="x-dns-prefetch-control" content="on">   
<meta http-equiv="x-dns-prefetch-control" content="on">
<title>介绍</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.mobile-1.4.3.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/css.css">
<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script> 
<script src="<%=request.getContextPath()%>/js/jquery.mobile-1.4.3.js"></script>
<!--S 线上样式-->
<link href="<%=request.getContextPath()%>/css/base.s.min.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/reg.css" rel="stylesheet" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.form.js"></script>
<!--E 线上样式-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ckplayer/ckplayer.js"></script>
</head>
<body onload="body_onload()">
<div data-role="page" class="jqm-demos" data-quicklinks="true">
  
  <div class="ui-content main" role="main">
      <div id="ui-body-test" class="ui-body ui-body-a ui-corner-all">
      	<form action="<%=request.getContextPath()%>/user/ivmcAction!saveOrUpdate.action" id="frm_ivmc" method="post">
	      <div class="ui-field-contain">
	          <label for="name2">标题：</label>
	          <input id="title"  type="text" name="ivmCountry.title" value='${ivmCountry.title }'  placeholder="标题"/>
	      </div>
	      <div class="ui-field-contain">
	          <label for="name2">描述：</label>
	          <textarea  id="detail"  name="ivmCountry.detail"  placeholder="描述"> ${ivmCountry.detail }</textarea>
	      </div>
          <div class="ui-field-contain">
          <label for="name2">省份：</label>
          <select id="province" name="ivmCountry.provinceId" onchange="showArea(this,'city')">
          	<option value="">==请选择==</option>
	            <c:forEach var="p" items="${provincelist}">
		            <c:choose>
		            <c:when test="${p.id==ivmCountry.provinceId}">
		             <option selected="selected" value="<c:out value="${p.id}"/>"><c:out value="${p.name }"/></option>
		            </c:when>
		            <c:otherwise> 
		                <option value="<c:out value="${p.id}"/>"><c:out value="${p.name }"/></option>
		            </c:otherwise>
		            </c:choose>
	            </c:forEach>
          </select>
           <label for="name2">市：</label>
          <select id="city" name="ivmCountry.cityId" onchange="showArea(this,'region')">
	           <option value="">==请选择==</option>
	            <c:forEach var="c" items="${citylist}">
	             <c:choose>
	            <c:when test="${c.id==ivmCountry.cityId}">
	             <option selected="selected" value="<c:out value="${c.id}"/>"><c:out value="${c.name }"/></option>
	            </c:when>
	            <c:otherwise> 
	                <option value="<c:out value="${c.id}"/>"><c:out value="${c.name }"/></option>
	            </c:otherwise>
	            </c:choose>
	            </c:forEach>
          </select>
            <label for="name2">区/县：</label>
            <select id="region" name="ivmCountry.areaId" onchange="showArea(this,'country')">
            	<option value="">==请选择==</option>
            	<c:forEach var="a" items="${arealist}">
               		<c:choose>
            			<c:when test="${a.id==ivmCountry.areaId}">
             				<option selected="selected" value="<c:out value="${a.id}"/>"><c:out value="${a.name }"/></option>
            			</c:when>
            			<c:otherwise> 
                			<option value="<c:out value="${a.id}"/>"><c:out value="${a.name }"/></option>
            			</c:otherwise>
            		</c:choose>
            	</c:forEach>
            </select>
	         <label for="name2">乡/镇/街道：</label>
	         <select id="country" name="ivmCountry.countryId">
            	<option value="">==请选择==</option>
            	<c:forEach var="a" items="${countrylist}">
               		<c:choose>
            			<c:when test="${a.id==ivmCountry.countryId}">
             				<option selected="selected" value="<c:out value="${a.id}"/>"><c:out value="${a.name }"/></option>
            			</c:when>
            			<c:otherwise> 
                			<option value="<c:out value="${a.id}"/>"><c:out value="${a.name }"/></option>
            			</c:otherwise>
            		</c:choose>
            	</c:forEach>
             </select>
	       
        </div>
        <div class="ui-field-contain">
         <label for="name2">详细地址：</label>
         <textarea  name="ivmCountry.address" id="address"  placeholder="详细地址">
          ${ivmCountry.address }
          </textarea>
        </div>       
         <input id="bankFront_input11"  type="hidden" name="ivmCountry.picUrl" value="${ivmCountry.picUrl }"/>
         <input id="bankFront_input21"  type="hidden" name="ivmCountry.videoUrl" value="${ivmCountry.videoUrl }"/>
		 <input id="bankFront_input31"  type="hidden" name="ivmCountry.voiceUrl" value="${ivmCountry.voiceUrl }"/>
         <input type="hidden" name="ivmCountry.id" value="${ivmCountry.id }"/>
	     <input type="hidden" name="ivmCountry.userId" value="${ivmCountry.userId }"/>
	     <input type="hidden" name="ivmCountry.status" value="${ivmCountry.status }"/>
	     <input type="hidden" name="ivmCountry.createDate"  value="${ivmCountry.createDate }"/>
	     </form>
	     <form name="demoForm1" id="demoForm1" class="file" method="post" enctype="multipart/form-data">
		 	<div class="ui-field-contain">
	            <label for="name1">图片介绍：</label>
	       		<input type="file" id="headFile1"  name="filedata" onchange="uploadFileAjax('demoForm1','headFile1','bankFront_input1')" class="reg_add_btn">	        
	        </div>
	        <div class="ui-field-contain">
	        	<label for="name1"></label>
	            <img id="headFile11" width="180px" height="120px" src="<%=res.getString("imgUrl") %>${ivmCountry.picUrl}" />
	        </div>
         </form>
         
         <form name="demoForm2" id="demoForm2" class="file" method="post" enctype="multipart/form-data">
		 	<div class="ui-field-contain">
	            <label for="name2">视频介绍：</label>
	       		<input type="file" id="headFile2"  name="filedata" onchange="uploadVideoAjax('demoForm2','headFile2','bankFront_input2')" class="reg_add_btn">	        
	        </div>
	        <div class="ui-field-contain">
	        	<label for="name2"></label>
	            <div id="a1"></div>
	        </div>
         </form>
         <!-- 
         <form name="demoForm3" id="demoForm3" class="file" method="post" enctype="multipart/form-data">
		 	<div class="ui-field-contain">
	            <label for="name3">语音介绍：</label>
	       		<input type="file" id="headFile3"  name="filedata" onchange="uploadVideoAjax('demoForm3','headFile3','bankFront_input3')" class="reg_add_btn">	        
	        </div>
	        <div class="ui-field-contain">
	        	<label for="name3"></label>
	            <div id="a2"></div>
	        </div>
         </form>
          -->         
      </div>
 	
      <div class="ui-field-contain">
        <button type="button" id="btn_submit" onclick="checkFrm()" class="ui-btn ui-shadow ui-corner-all ui-btn-active" >确定</button>
      </div>
  </div>
 
</div>
<script type="text/javascript">
var flashvars={f:'http://movie.ks.js.cn/flv/other/1_0.flv',c:0,b:1};
var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always',wmode:'transparent'};

function uploadFileAjax(fromId,inputName,bankFrontdirUrl){
	//克隆上传元素   赋值headFile1  获取 bankFront_input  bankCon_input bankImage_input
	  var f=$("#"+inputName).val(); 
	  
	  if(f==""){ 
    	  alert("请上传1张图片");
    	  return false;
      }else{
        if(!RegExp(".(gif|jpg|jpeg|png|GIF|JPG|PNG)$").test(f)){
          alert("图片类型必须是.gif,jpeg,jpg,png中的一种");
          return false;
        }
      }  
	  $("#"+fromId).ajaxSubmit({
		  	url:"<%=request.getContextPath()%>/file/fileUpload!upload.action",
		    dataType:"json",
		    success:function(data){
		        if(data.success){
		        	 $("#"+bankFrontdirUrl+"1").val(data.path);
	                 $("#"+fromId+"1").val(data.path);
	                 $("#"+inputName+"1").attr("src",("<%=res.getString("imgUrl") %>"+data.path));
		        	alert("上传成功!");
		        }else{    
		        	alert("文件上传失败了(>_<)");
		        }  
		     }, 
		      error: function (data) {
            	alert("异常");
             } 
		}); 
 }
	//省市选择级联
	function showArea(obj,targetId)
	{
		var parentId=$(obj).val();
		var optionhtml="<option value=''>==请选择</option>";
		$("#"+targetId).html("");
		$.post("<%=request.getContextPath()%>/city/city5dAction!findCityByParentId.action",{parentId:parentId},function(data){
			        $(data.result).each(function(i){
			        	optionhtml+="<option value='"+this.id+"'>"+this.name+"</option>";
			        	 $("#"+targetId).html(optionhtml);
			        });
			});
	}

	
	function body_onload () {
		var message = '${message}';
		if (message != "" && message!=null) {
			alert(message);
		}
	}
	
	function checkFrm() {
		var title = $("#title");
		var detail = $("#detail");
		var address = $("#address");
		var province = $("#province").val();
		var city = $("#city").val();
		var region = $("#region").val();
		var country = $("#country").val();
		if($.trim(title.val())==null||$.trim(title.val())=="")  {		
			alert("对不起，"+title.attr("placeholder")+"不能为空，请重新输入！");
			title.focus();
			return false;
		}
		if($.trim(detail.val())==null||$.trim(detail.val())=="")  {		
			alert("对不起，"+detail.attr("placeholder")+"不能为空，请重新输入！");
			detail.focus();
			return false;
		}
		if($.trim(address.val())==null||$.trim(address.val())=="")  {		
			alert("对不起，"+address.attr("placeholder")+"不能为空，请重新输入！");
			address.focus();
			return false;
		}
		if(province==""||province=="==请选择")  {		
			alert("对不起，省/直辖市不能为空！");
			return false;
		}
		if(city==""||city=="==请选择")  {		
			alert("对不起，市不能为空！");
			return false;
		}
		if(region==""||region=="==请选择")  {		
			alert("对不起，区/县不能为空！");
			return false;
		}
		if(country==""||country=="==请选择")  {		
			alert("对不起，乡/镇/街道不能为空！");
			return false;
		}
		$("#btn_submit").val("提交中");
		$("#btn_submit").attr("disabled","disabled"); 
		
		
		document.getElementById("frm_ivmc").submit();
	}
	$(document).ready(function(){
		var address = $.trim($("#address").val());
		$("#address").val(address);
		var detail = $.trim($("#detail").val());
		$("#detail").val(detail);
		var videoUrl = $.trim($("#bankFront_input21").val());
		if(videoUrl == ""){
		   	CKobject.embedSWF('<%=request.getContextPath()%>/js/ckplayer/ckplayer.swf','a1','ckplayer_a1','400','250',flashvars,params);
		}else{
			 var url = '<%=request.getContextPath()%>'+videoUrl;
        	 CKobject.embedSWF(url,'a1','ckplayer_a1','400','250',flashvars,params);
		}
		/*
		CKobject.embedSWF(播放器路径,容器id,播放器id/name,播放器宽,播放器高,flashvars的值,其它定义也可省略);
		下面三行是调用html5播放器用到的
		
		var video=['http://movie.ks.js.cn/flv/other/1_0.mp4->video/mp4','http://www.ckplayer.com/webm/0.webm->video/webm','http://www.ckplayer.com/webm/0.ogv->video/ogg'];
		var support=['iPad','iPhone','ios','android+false','msie10+false'];
		CKobject.embedHTML5('a1','ckplayer_a1',600,400,video,flashvars,support);
		*/

	});
	
	function closelights(){//关灯
		alert(' 本演示不支持开关灯');
	}
	function openlights(){//开灯
		alert(' 本演示不支持开关灯');
	}
	
	function uploadVideoAjax(fromId,inputName,bankFrontdirUrl){
	  var f=$("#"+inputName).val(); 
	  if(f==""){ 
    	  alert("请上传视频文件");
    	  return false;
      }else{
        if(!RegExp(".(mp4|swf)$").test(f)){
          alert("视频类型必须是.mp4,swf中的一种");
          return false;
        }
      }  
	  $("#"+fromId).ajaxSubmit({
		  	url:"<%=request.getContextPath()%>/file/fileUpload!videoUpload.action",
		    dataType:"json",
		    success:function(data){
		        if(data.success){
		        	 $("#"+bankFrontdirUrl+"1").val(data.path);
	                 $("#"+fromId+"1").val(data.path);
	                 $("#"+inputName+"1").attr("src",("<%=request.getContextPath()%>"+data.path));
	                 var url = '<%=request.getContextPath()%>'+data.path;
	             	 CKobject.embedSWF(url,'a1','ckplayer_a1','400','250',flashvars,params);
		        	alert("上传成功!");
		        }else{    
		        	alert("文件上传失败了(>_<)");
		        }  
		     }, 
		      error: function (data) {
            	alert("异常");
             } 
		}); 
	 }
  </script>

</script>
</body>
</html>