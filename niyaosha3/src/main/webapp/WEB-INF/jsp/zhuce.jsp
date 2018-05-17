<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ResourceBundle"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="zh-CN"><head>
	<%ResourceBundle res = ResourceBundle.getBundle("system"); %> 
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>厂家入驻</title>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="x-dns-prefetch-control" content="on">   
    <meta http-equiv="x-dns-prefetch-control" content="on">
    <!--S 线上样式-->
    <link href="<%=request.getContextPath()%>/css/base.s.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/css/reg.css" rel="stylesheet" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.form.js"></script>
    <!--E 线上样式-->

</head>
<body>

<div class="reg_wrap">
	
   <div class="reg_form">
    <form id="registerForm" method="post" action="<%=request.getContextPath()%>/companyInfo/companyInfoAction!saveOrupdate.action">
     <div class="item">
       <div class="t">所在地区</div>
       <div class="m"><input type="text"  id="location" name="companyInfo.location" class="reg_input" placeholder="地区信息" value="${companyInfo.location }"></div>
     </div>
     <div class="item">
       <div class="t">厂家地址</div>
       <div class="m"><textarea id="companyAddress"  name="companyInfo.companyAddress" class="reg_textarea" placeholder="街道门牌信息">${companyInfo.location }</textarea></div>
     </div>
     <div class="item">
       <div class="t">厂家名称</div>
       <div class="m"><input id="name" type="text"  name="companyInfo.name" class="reg_input" placeholder="店家的名字" value="${companyInfo.name }"></div>
     </div>
     <div class="item">
       <div class="t">联系人</div>
       <div class="m"><input id="cantactMan" name="companyInfo.cantactMan"  type="text" value="${companyInfo.cantactMan }" class="reg_input" placeholder="地区信息"></div>
     </div>
     <div class="item">
       <div class="t">联系电话</div>
       <div class="m"><input id="cantactPhone" name="companyInfo.cantactPhone"  value="${companyInfo.cantactPhone }" type="text" class="reg_input" placeholder="手机或者电话号码"></div>
     </div>
     <div class="item">
       <div class="t">产品介绍连接地址</div>
       <div class="m"><input id="goodsUrl" name="companyInfo.goodsUrl"  value="${companyInfo.goodsUrl }" type="text" class="reg_input" placeholder="关于产品的链接地址或官网"></div>
     </div>
     <div class="item">
       <div class="t">经营种类</div>
       <div class="m"><select id="leibie"  name="companyInfo.businessId" class="reg_input" >
       		<option value="1">数码</option>
       		<option value="2">服装</option>
       		<option value="3">玩具</option>
       		<option value="4">家电</option>
       		<option value="5">电脑</option>
       		<option value="6">家纺</option>
       		<option value="7">礼品</option>
       		<option value="8">首饰</option>
       		<option value="9">乐器</option>
       		<option value="10">钟表</option>
       		<option value="11">其他</option>
       </select></div>
     </div>
     <div class="item">
       <div class="t">经营范围</div>
       <div class="m"><textarea id="businessInfo"  name="companyInfo.businessInfo" class="reg_textarea" placeholder="简述下经营范围">${companyInfo.businessInfo }</textarea></div>
     </div>
     <input id="bankFront_input11"  type="hidden" name="companyInfo.logoUrl" value="${companyInfo.logoUrl }"/>
   	 <input type="hidden" name="companyInfo.id" value="${companyInfo.id }"/>
     <input type="hidden" name="companyInfo.userId" value="${companyInfo.userId }"/>
     <input id="bankFront_input21"  type="hidden" name="companyInfo.licenceUrl" value="${companyInfo.licenceUrl }"/>
     <input  type="hidden" name="companyInfo.createDate"  value="${companyInfo.createDate }"/>
     
     <%-- <div class="item item2">
       
       <div class="t">营业执照</div>
       <div class="m"><a href="#" class="reg_add_img"><img id="headFile21" src="<%=res.getString("imgUrl") %>${companyInfo.licenceUrl}" /></a></div>
     </div>
     <div class="item item2">
		
       <div class="t">其他资质图片</div>
       <div class="m"><a href="#" class="reg_add_img"><img id="headFile11" src="<%=res.getString("imgUrl") %>${companyInfo.logoUrl}" /></a></div>
     </div> --%>
     </form>
     <div class="item item2">
       <div class="t">上传营业执照</div>
       <div class="m">
       	<form name="demoForm1" id="demoForm2" class="file" method="post" enctype="multipart/form-data"> 
       		<input type="file" id="headFile2"  name="file1" onchange="uploadFileAjax('demoForm2','headFile2','bankFront_input2')" class="reg_add_btn"/>
       	</form>
       <div class="reg_add_img"><img id="headFile21" src="<%=res.getString("imgUrl") %>${companyInfo.licenceUrl}" /></div>
     </div>
     </div>
     <div class="item item2">
       <div class="t">上传其他资质</div>
       <div class="m">
       		<form name="demoForm1" id="demoForm1" class="file" method="post" enctype="multipart/form-data">
       			<input type="file" id="headFile1"  name="file1" onchange="uploadFileAjax('demoForm1','headFile1','bankFront_input1')" class="reg_add_btn">
       		</form>
       		<div class="reg_add_img"><img id="headFile11" src="<%=res.getString("imgUrl") %>${companyInfo.logoUrl}" /></div>
       </div>
     </div>
     
   </div>
<!-- <div class="t">上传营业执照</div>
		<form name="demoForm1" id="demoForm2" class="file" method="post" enctype="multipart/form-data"> 
		 	<input type="file" id="headFile2"  name="file1" onchange="uploadFileAjax('demoForm2','headFile2','bankFront_input2')" class="xuanqu kuang">
		</form>
<div class="t">上传其他资质</div>
<form name="demoForm1" id="demoForm1" class="file" method="post" enctype="multipart/form-data"> 
		 	<input type="file" id="headFile1"  name="file1" onchange="uploadFileAjax('demoForm1','headFile1','bankFront_input1')" class="xuanqu kuang">
		</form> -->
		
     <div class="reg_btn_main"><a onclick="tijiao()" class="reg_btn">提交</a></div>
     
</div>
</body>
<script type="text/javascript">
var ktijiao=false;
$("#leibie  option[value='${companyInfo.businessId}'] ").attr("selected",true);
function uploadFileAjax(fromId,inputName,bankFrontdirUrl){
	//克隆上传元素   赋值headFile1  获取 bankFront_input  bankCon_input bankImage_input

	/* $("#subfrom").append($("#"+inputName).clone());*/
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
		  	url:"<%=request.getContextPath()%>/file/fileUploadAction!uploadFile1.action",
		    dataType:"json",
		    success:function(data){
		        if(data.success){
		        	/* $("[name='bankFront']").attr("value",data.dirUrl); */
		        	$("#"+bankFrontdirUrl+"1").val(data.path);
		        	$("#"+fromId+"1").val(data.path);
		        	$("#"+inputName+"1").attr("src",("<%=res.getString("imgUrl") %>"+data.path));
		        	alert("上传成功!");
		        }else{    
		        	alert("文件上传失败了(>_<)");
		        }  
		     }, 
		      error: function () {
            	alert("异常");
             } 
		}); 
 }
 
 function tijiao(){
	 if($("#location").val()==null||$("#location").val()==""){
		 alert("所在地区不能为空!");
		 return false;
	 }
	 if($("#companyAddress").val()==null||$("#companyAddress").val()==""){
		 alert("厂家地址不能为空!");
		 return false;
	 }
	 if($("#name").val()==null||$("#name").val()==""){
		 alert("厂家名称不能为空!");
		 return false;
	 }
	 if($("#cantactMan").val()==null||$("#cantactMan").val()==""){
		 alert("联系人不能为空!");
		 return false;
	 }
	 if($("#cantactPhone").val()==null||$("#cantactPhone").val().length!=11){
		 alert("请填写正确联系方式!");
		 return false;
	 }
	 if($("#goodsUrl").val()==null||$("#goodsUrl").val()==""){
		 alert("产品链接不能为空!");
		 return false;
	 }
	 if($("#businessInfo").val()==null||$("#businessInfo").val()==""){
		 alert("请填写经营范围!");
		 return false;
	 }
	 if($("#bankFront_input11").val()==null||$("#bankFront_input11").val()==""){
		 alert("请上传其他资质!");
		 return false;
	 }
	 if($("#bankFront_input21").val()==null||$("#bankFront_input21").val()==""){
		 alert("请上传营业执照!");
		 return false;
	 }
	 if($("#location").val()=="${companyInfo.location }"&&
			 $("#companyAddress").val()=="${companyInfo.companyAddress }"&&
			 $("#name").val()=="${companyInfo.name }"&&
			 $("#cantactMan").val()=="${companyInfo.cantactMan }"&&
			 $("#cantactPhone").val()=="${companyInfo.cantactPhone }"&&
			 $("#goodsUrl").val()=="${companyInfo.goodsUrl }"&&
			 $("#businessInfo").val()=="${companyInfo.businessInfo }"&&
			 $("#bankFront_input11").val()=="${companyInfo.logoUrl }"&&
			 $("#bankFront_input21").val()=="${companyInfo.licenceUrl }"&&
			 $("#leibie").val()=="${companyInfo.businessId }"
	 	){
		 alert("没有改变,不能重复提交!");
		 return false;
	 }
	 
	   document.getElementById("registerForm").submit(); 
	 /* $("#registerForm").submit(); */
 }
 
</script>


</html>