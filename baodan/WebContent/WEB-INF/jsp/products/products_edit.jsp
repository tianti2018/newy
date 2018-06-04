<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- 默认为IE8渲染模式，解决兼容问题-->
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>商品编辑</title>
<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
<script src="js/jquery.form.js"	type="text/javascript"></script>
<script type="text/javascript"
	src="js/Validform_v5.3.2/js/Validform_v5.3.2.js"></script>
<link rel="stylesheet"
	href="js/Validform_v5.3.2/css/style.css"
	type="text/css" />
<link rel="stylesheet"
	href="js/kindeditor-4.1.10/themes/simple/simple.css" />
<link rel="stylesheet"
	href="js/kindeditor-4.1.10/plugins/code/prettify.css" />
<script charset="utf-8"
	src="js/kindeditor-4.1.10/kindeditor.js"></script>
<script charset="utf-8"
	src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script charset="utf-8"
	src="js/kindeditor-4.1.10/plugins/code/prettify.js"></script>
</head>
<body onload="alertMes();">
	<div class="popup" id='J_popup'>
		<div id='J_popupMain'>
			<form id="headUrlForm" method="post" enctype="multipart/form-data" >
				<input type="file"	name="headFile" id="headFile" />
				<input type="button" onclick="tijiao()" value="提交"/> 
			</form>
			<form action="<%=request.getContextPath()%>/products!saveOrUpdate.action"
				method="post" id="frmAdd">
				<table>
					<tr>
						<td><label><b style="color: #f00;">*</b>商品图片：</label></td>
						<td> 
							<div id="div_good_style_pic"> 
								<div style="display:inline;margin-left:2px" id="div_style_pic">
									<img width="120px" id="img_style" height='70px' src="${product.headUrl}"  />
									<a href="javascript:void(0);" style="margin-left:0px" id="a_style_pic" onclick="delStylePic()">删除</a>
								</div>
							</div>
						<input type="hidden"
							id="hGoodStylePic" name="product.headUrl" value="${product.headUrl }" datatype="*"  nullMsg="请上传商品图片"/>
								<font class="Validform_checktip" id="span_v_1">请上传商品图片，建议符合首页样式(宽高不应小于220像素)</font>
							</td>
							
					</tr>
					
					<tr>
						<td><label><b style="color: #f00;">*</b>&nbsp;名称：</label></td>

						<td><input datatype="*2-128" name="product.name"  value='${product.name }'
							nullMsg="请填写商品名称" type="text" class="text" /><font
							class="Validform_checktip">请输入2~128位名称</font>
						</td>
					</tr>
					
					<tr>
						<td><label><b style="color: #f00;">*</b>&nbsp;商品卖点：</label></td>
						<td><textarea name="product.prodDescription"
								rows="3" cols="50">${product.prodDescription}
						</textarea></td>
					</tr>

					<tr>
						<td><label><b style="color: #f00;">*</b>&nbsp;商品详情：</label></td>
						<td><textarea name="product.productInfo" id="trea_spms"
								rows="25" cols="250">${product.productInfo}
						</textarea></td>
					</tr>
					<tr>
						<td><label><b style="color: #f00;">*</b>&nbsp;商品价格：</label></td>

						<td><input type="text"
							onchange="tyJg(this)" name="product.price" value="${product.price}"  datatype="/^\d+(\.\d+)?$/" nullMsg="请填写价格" errorMsg="请填入1~10位数字" class="normalinput" />(元)<font
							class="Validform_checktip">请输入商品价格</font>&nbsp;<span id="span_fanli" style="color:red;font-size:16px;display:none"></span></td>
					</tr>
					
					<tr>
						<td><label><b style="color: #f00;">*</b>&nbsp;首页显示样式：</label></td>
						<td>
						<select id="type" datatype="*" name="product.type" nullMsg="请选择首页显示样式">
								<option value="0">轮播图</option>
								<option value="1">单款显示</option>
								<option value="2">散列显示</option>
								
						</select>
						</td>
					</tr>
					
					<tr>
						<td><label><b style="color: #f00;">*</b>&nbsp;小二价格：</label></td>

						<td><input type="text" name="product.levelone"
							onchange="tyJg(this)" class="normalinput" value="${product.levelone}<%-- ${fn:replace(((product.levelone==0||product.levelone==null)?0.01:product.levelone)*100,'.0','')} --%>" 
							datatype="/^\d+(\.\d+)?$/" nullMsg="请填写数字" errorMsg="请填入数字"/>
							<font class="Validform_checktip">请输入小二拿货价格</font></td>
					</tr>
					
					<tr>
						<td><label><b style="color: #f00;">*</b>&nbsp;掌柜价格：</label></td>
						<td><input type="text" name="product.leveltwo"
							onchange="tyJg(this)" class="normalinput" value="${product.leveltwo}<%-- ${fn:replace(((product.leveltwo==0||product.leveltwo==null)?0.01:product.leveltwo)*100,'.0','')} --%>"
							datatype="/^\d+(\.\d+)?$/" nullMsg="请填写数字" errorMsg="请填入数字"/>
							<font class="Validform_checktip">请输入掌柜拿货价格</font></td>
					</tr>
					
					<tr>
						<td><label><b style="color: #f00;">*</b>&nbsp;大掌柜价格：</label></td>
						<td><input type="text" name="product.levelthr"
							onchange="tyJg(this)" class="normalinput" value="${product.levelthr}<%-- ${fn:replace(((product.levelthr==0||product.levelthr==null)?0.01:product.levelthr)*100,'.0','')} --%>"
							datatype="/^\d+(\.\d+)?$/" nullMsg="请填写数字" errorMsg="请填入数字" />
							<font class="Validform_checktip">请输入大掌柜拿货价</font></td>
					</tr>
					<tr>
						<td><label><b style="color: #f00;">*</b>&nbsp;进货价格：</label></td>
						<td><input type="text" name="product.levelfor"
							onchange="tyJg(this)" class="normalinput" value="${product.levelfor}"
							datatype="/^\d+(\.\d+)?$/" nullMsg="请填写数字" errorMsg="请填入数字" />
							<font class="Validform_checktip">请输入进货价格</font></td>
					</tr>
					<tr>
						<td><label><b style="color: #f00;">*</b>&nbsp;商品库存：</label></td>

						<td><input type="text"
							onchange="tyJs(this)"  name="product.stock" value="${product.stock}"  datatype="n1-10" nullMsg="请填写库存" errorMsg="请填入1~10位数字" class="normalinput" /><font
							class="Validform_checktip">请输入库存</font>&nbsp;<span style="color:red;font-size:16px;display:none"></span></td>
					</tr>
					<tr>
						<td><label><b style="color: #f00;">*</b>&nbsp;商品运费：</label></td>

						<td><input type="text"
							onchange="bujiance0(this)" name="transFee" value="${product.transFee}"  datatype="/^\d+(\.\d+)?$/" nullMsg="请填写运费" errorMsg="请填入数字" class="normalinput" /><font
							class="Validform_checktip">请输入运费</font>&nbsp;<span style="color:red;font-size:16px;display:none"></span></td>
					</tr>
					<%-- <tr>
						<td><label><b style="color: #f00;"></b>&nbsp;商品颜色：</label></td>

						<td><input type="text"
							name="product.prodColor" value="${product.prodColor}" type="text" class="text"/><font
							class="Validform_checktip">请输入颜色</font>&nbsp;<span style="color:red;font-size:16px;display:none"></span></td>
					</tr> --%>
					<tr>
						<td><label><b style="color: #f00;"></b>&nbsp;规格：</label></td>

						<td><input type="text"
							name="product.guige" value="${product.guige}" type="text" class="text"/><font
							class="Validform_checktip">请输入规格</font>&nbsp;<span style="color:red;font-size:16px;display:none"></span></td>
					</tr>
					<tr>
						<td><label><b style="color: #f00;"></b>&nbsp;商品限购：</label></td>

						<td><input type="text"
							name="product.limitNum" value="${product.limitNum==null?0:product.limitNum}"  datatype="n1-10" nullMsg="请填写限购数量" errorMsg="请填入1~10位数字" class="normalinput" /><font
							class="Validform_checktip">请输入限购数量</font>&nbsp;<span style="color:red;font-size:16px;display:none"></span></td>
					</tr>
					<tr>
						<td><label><b style="color: #f00;"></b>&nbsp;排序：</label></td>

						<td><input type="text"
							name="product.paixu" value="${product.paixu==null?0:product.paixu}"  datatype="n1-10" nullMsg="请填写排序值" errorMsg="请填入1~10位数字" class="normalinput" /><font
							class="Validform_checktip">值越大越靠前,值相同则按时间倒序</font>&nbsp;<span style="color:red;font-size:16px;display:none"></span></td>
					</tr>
					<tr>
						<td><label><b style="color: #f00;"></b>&nbsp;同款特征码：</label></td>

						<td><input type="text"
							name="product.prodType" value="${product.prodType}"  datatype="n0-10" errorMsg="请填入1~10位数字" class="normalinput" /><font
							class="Validform_checktip">同款产品特征码相同,相同特征码的产品会在属性里面显示</font>&nbsp;<span style="color:red;font-size:16px;display:none"></span></td>
					</tr>

				</table>
				<div style="height:30px"></div>
				<input type="hidden" name="product.productsId" id="hid"	value='${product.productsId }' /> 
				<input type="hidden" name="product.userId" id="huserid"	value='${product.userId }' /> 
				<input type="hidden" name="product.createDate" value='${product.createDate }' /> 
				<div align="center">
					<input id="btn_submit" type="button" class="btn" value=" 保 存 " />
				</div>
		</form>
		</div>
	</div>
	<script type="text/javascript">
	function delStylePic(){
		if(confirm("确定删除这张图吗?")){
			$("#img_style").attr("src","");
			$("#hGoodStylePic").val("");
			$("#div_style_pic").hide();
		}
	}
	
	function tijiao(){
		   var f=$("#headFile").val();
		  if(f==""){ 
	    	  alert("请上传1张图片");
	    	  return false;
	      }else{
	        if(!RegExp(".(gif|jpg|jpeg|png|GIF|JPG|PNG)$").test(f)){
	          alert("图片类型必须是.gif,jpeg,jpg,png中的一种");
	          return false;
	        }
	      } 
		  $("#headUrlForm").ajaxSubmit({  
			  	url:"products!upLoadHeadUrl.action",
			    dataType:"json",
			    success:function(data) {
			        if(data.message =="true" ){
	            		$("#span_v_1").removeClass("Validform_wrong");
	            		$("#span_v_1").html("");
	            		$("#img_style").attr("src",data.headUrl);
	            		$("#hGoodStylePic").val(data.headUrl);
	            		$("#div_style_pic").show();
			        }else{    
			        	alert("文件上传失败了(>_<)");
			        }  
			     }, 
			     error: function () {
                     alert("异常");
                 }
			}); 
		//$("#headUrlForm").submit();
	   }
		//页面加载时判断是否是更新或保存成功
 		function alertMes() {
			if ('<s:property value="message"/>' == 'success') {
				//更新数据列表
				alert("更新成功");
				//如需保存成功后关闭弹窗，请取消下一行的注释
				parent.closeDiv();
			} else if ('<s:property value="message"/>' == 'error') {
				alert("更新失败");
			}
		} 
		//绑定表单验证
		$("#frmAdd").Validform({
			tiptype : function(msg, o, cssctl) {
				//msg：提示信息;
				//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
				//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
				if (!o.obj.is("form")) {//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
					var objtip = o.obj.siblings(".Validform_checktip");
					cssctl(objtip, o.type);
					objtip.text(msg);
				} else {
					var objtip = o.obj.find("#msgdemo");
					cssctl(objtip, o.type);
					objtip.text(msg);
				}
			}	,
		btnSubmit:"#btn_submit", 
		beforeSubmit:function(curform){
			
			
			$("#btn_submit").val("提交中..");
			$("#btn_submit").attr('disabled',"true");
		}
		});
		
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[id="trea_spms"]', {
				width : '740px',
				afterBlur: function(){this.sync();},
				cssPath : 'js/kindeditor-4.1.10/plugins/code/prettify.css',
				uploadJson : 'products!upLoadProductInfo.action',
				fileManagerJson : 'js/kindeditor-4.1.10/jsp/file_manager_json.jsp',
									allowFileManager : true,
									afterCreate : function() {
										var self = this;
										K.ctrl(document, 13, function() {
											self.sync();
										});
										K.ctrl(self.edit.doc, 13, function() {
											self.sync();
										});
									}
								});
				prettyPrint();
			});

		//统一件数
		function tyJs(inputz) {
			var value = inputz.value;
			var reg = new RegExp("^[0-9]*$");
			if (value == "") {
	 			alert("请输入数字!");
	 			inputz.value=1;
				return;
			}
			if (value == 0) {
	 			alert("不能为0!");
	 			inputz.value=1;
				return;
			}
			if (!reg.test(value)) {
	 			alert("请输入数字!");
	 			inputz.value=0;
				return;
			}

		}
		//统一价格
		function tyJg(inputz) {
			var value = inputz.value;
			var reg = /^[+-]?(?!0\d)\d+(\.\d{1,2})?$/;
			if (value == "") {
				return;
			}
			if (value == 0) {
	 			alert("不能为0!");
	 			inputz.value=1;
				return;
			}
			if(!reg.test(value)){
				return;
			}
		}
		function bujiance0(inputz) {
			var value = inputz.value;
			var reg = /^[+-]?(?!0\d)\d+(\.\d{1,2})?$/;
			if (value == "") {
				return;
			}
			
			if(!reg.test(value)){
				return;
			}
		}
		
		
		
	   function changeModelType(type){
	   		if(type==0){
	   			$(".bfh").show();
	   		}else if(type==1){
	   			$(".bfh").hide();
	   		}
	   		
	   	}
	   
	   
	   $(document).ready(function(){
		   if("${product.headUrl }"==""||"${product.headUrl }"==null){
			   $("#div_style_pic").hide();
		   }
		   if("${product.type }"!=null&&"${product.type }"!=""){
	   			$("#type  option[value='${product.type }'] ").attr("selected",true);
	   		}
		}); 
</script>							
</body>

</html>