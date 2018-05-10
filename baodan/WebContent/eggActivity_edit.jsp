<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>添加秒杀商品</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resource/css/style1.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resource/js/Validform_v5.3.2/css/style.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resource/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resource/js/Validform_v5.3.2/js/Validform_v5.3.2.js"></script>

<script language="javascript"
	src="<%=request.getContextPath()%>/resource/datepicker/WdatePicker.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resource/js/kindeditor-4.1.10/themes/default/default.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resource/js/kindeditor-4.1.10/plugins/code/prettify.css" />
<script charset="utf-8"
	src="<%=request.getContextPath()%>/resource/js/kindeditor-4.1.10/kindeditor.js"></script>
<script charset="utf-8"
	src="<%=request.getContextPath()%>/resource/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script charset="utf-8"
	src="<%=request.getContextPath()%>/resource/js/kindeditor-4.1.10/plugins/code/prettify.js"></script>
<script>
	
	$(document).ready(function() {
		$(".click").click(function() {
			$(".tip").fadeIn(200);
		});

		$(".tiptop a").click(function() {
			$(".tip").fadeOut(200);
		});

		$(".sure").click(function() {
			$(".tip").fadeOut(100);
		});

		$(".cancel").click(function() {
			$(".tip").fadeOut(100);
		});
		$("#place").css({
			'width' : $(window).width()
		});
	});
	function alertMes(){
		if('<s:property value="message"/>'=='success'){
			//更新数据列表
			parent.setFromWin(null,null,"reloadwin()");
			parent.showTip("保存成功！",1);
			//如需保存成功后关闭弹窗，请取消下一行的注释
			//parent.closeDiv();
		}else if('<s:property value="message"/>'=='error'){
			parent.showTip("保存失败！",0);
		}
	}
	
	
</script>
</head>
<body onload="alertMes();">

	<s:form namespace="/admin" action="eggActivity!updateEntity.action"
		method="post" id="frmAdd" theme="simple">
		<s:hidden name="eggActivity.id"></s:hidden>
		<div class="formbody">
			<div class="formtitle">
				<span>添加活动</span>
			</div>
			<ul class="forminfo">

				<li><label><b style="color: #f00;">*</b>&nbsp;活动名称：</label><input
					name="eggActivity.activityName" value='${eggActivity.activityName }' datatype="uum2-128" nullMsg="请填写活动名称" errorMsg="请输入6~128位汉字/数字/字母/"
					type="text" class="inputxt"  /><font
					class="Validform_checktip">请输入2~32位字符串</font></li>

				<li><label><b style="color: #f00;">*</b>&nbsp;活动开始时间：</label> <input
					name="beginTime" datatype="*" nullMsg="请填写秒杀开始时间" 
					type="text"  id="beginTime" value='${eggActivity.beginTime }'
					onfocus="WdatePicker({dateFmt:'yyyy-M-d H:mm:ss',minDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'endTime\')}'})"
						 class="Wdate" 	 style="width:200px"
					 /><font class="Validform_checktip">请输入2~32位字符串</font></li>

				<li><label><b style="color: #f00;">*</b>&nbsp;活动结束时间：</label> <input
					name="endTime" datatype="*" nullMsg="请填写秒杀开始时间" style="width:200px"
					type="text" class="Wdate" id="endTime"  value='${eggActivity.endTime }'
					onFocus="WdatePicker({dateFmt:'yyyy-M-d H:mm:ss',minDate:'#F{$dp.$D(\'beignTime\',{m:10})||\'%y-%M-%d {%H+1}:%m:%s\'}'})"
					 /><font class="Validform_checktip">请输入2~32位字符串</font></li>

				<li><label><b style="color: #f00;">*</b>&nbsp;是否开启: </label>
				<s:if test="eggActivity.status==1">
				<input type="radio" name="eggActivity.status" value="1" checked="checked"
							id="rad1" />是&nbsp;&nbsp; <input type="radio"
							name="eggActivity.status" value="0" 
							id="rad0" />否
				</s:if>
				<s:else>
				<input type="radio" name="eggActivity.status" value="1" 
							 />是&nbsp;&nbsp; <input type="radio"
							name="eggActivity.status" value="0"  checked="checked"
							/>否
				</s:else> 
				<font
					class="Validform_checktip">请输入2~32位字符串</font></li>


				<li><label><b style="color: #f00;">*</b>&nbsp;活动内容:</label>
				<textarea name="eggActivity.activityContent" id="content" rows="15" cols="155">${eggActivity.activityContent }</textarea></li>


			</ul>

		</div>

		<div class="formtitle" style="margin-left: 18px;z-index:-1">
			<span>添加活动奖品</span>
		</div>
		<div style="margin-left: 118px;z-index:999">
			<input type="button" style="margin-top:-58px" class="loginbtn1" value="增加一个奖品" onclick="addPrizeDiv();"/></div>
		<div id="div_prizes">
		<s:iterator value="prizeList" status="i" var="prize">
		<table style="margin-left: 30px;margin-top:20px"  class="tablelist" id="tableListFirst">
			<tr>
				<td width="120px"><label><b style="color: #f00;">*</b>&nbsp;奖品名称:</label></td>
				<td><input
					name="eggPrize.prizeName" value='${prizeName }'  datatype="uum2-128" nullMsg="请填写奖品名称" errorMsg="请输入2~128位汉字/数字/字母/"
					type="text" class="inputxt"/>
					<font
					class="Validform_checktip">请输入奖品名称</font>
					</td>
			
			<td width="120px">
			<s:if test="#i.index!=0">
			<input type="button" class="loginbtn1" value="删除此奖品" onclick="delPrizeDiv(this);"/>
			</s:if>
			</td>
			</tr>
			<tr>
				<td ><label><b style="color: #f00;">*</b>&nbsp;奖品等级:</label></td>
				<td><input
					name="eggPrize.prizeLevel" value='${prizeLevel }' datatype="uum2-12" nullMsg="请填写奖品等级" errorMsg="请输入2~12位汉字/数字/字母/"
					type="text" class="inputxt"/>
					<font
					class="Validform_checktip">请输入奖品等级</font>
					</td>
			<td ></td>
			</tr>
			
			<tr>
				<td><label><b style="color: #f00;">*</b>&nbsp;奖品数量:</label></td>
				<td><input
					name="nums"  datatype="n"  value='${nums }' nullMsg="请填写活动名称"
					type="text" class="inputxt"/>
					<font
					class="Validform_checktip">请输入奖品数量！</font>
					</td>
						<td></td>
			</tr>
			<tr>
				<td><label><b style="color: #f00;">*</b>&nbsp;奖品是否开放:</label></td>
				<td>
				<s:if test="status==1">a
				<input type="radio" name="statuss" value="1" checked="checked"
							 />是&nbsp;&nbsp; <input type="radio"
							name="statuss" value="0" 
							 />否
				</s:if>
				<s:else>b
				<input type="radio" name="statuss" value="1" 
							 />是&nbsp;&nbsp; <input type="radio"
							name="statuss" value="0"  checked="checked"
							/>否
				</s:else> 
				
				
					<font
					class="Validform_checktip">选"否"的话永久不会被抽中</font>
					</td>
						<td></td>
			</tr>
			<tr>
				<td><label><b style="color: #f00;">*</b>&nbsp;抽奖几率:</label></td>
				<td><input
					name="winRates" datatype="rate"  value='${winRate }' nullMsg="请填写活动名称" errorMsg="请填入中奖率，比如0.05(0~100之间，最多两位小数)"
					type="text" class="inputxt"/>
					<font
					class="Validform_checktip">请输入中奖几率，比如0.05(0~100之间，最多两位小数)</font>
					</td>
						<td></td>
			</tr>
		</table>
		</s:iterator>
		</div>
		<div style="margin-left: 150px;margin-top:10px">
		<label>&nbsp;</label>
		<input id="btn_submit" type="button" 
			class="btn" value=" 保 存 " />
		</div>
	</s:form>
	<div style="display:none"  id="hide_div_prize">
	<table style="margin-left: 30px;margin-top:20px"  class="tablelist" id="tableList">
	<tr>
				<td width="120px"><label><b style="color: #f00;">*</b>&nbsp;奖品名称:</label></td>
				<td><input
					name="eggPrize.prizeName"  datatype="uum2-128" nullMsg="请填写奖品名称" errorMsg="请输入2~128位汉字/数字/字母/"
					type="text" class="inputxt"/>
					<font
					class="Validform_checktip">请输入奖品名称</font>
					</td>
			
			<td width="120px"><input type="button" class="loginbtn1" value="删除此奖品" onclick="delPrizeDiv(this);"/></td>
			</tr>
			<tr>
				<td ><label><b style="color: #f00;">*</b>&nbsp;奖品等级:</label></td>
				<td><input
					name="eggPrize.prizeLevel" datatype="uum2-12" nullMsg="请填写奖品等级" errorMsg="请输入2~12位汉字/数字/字母/"
					type="text" class="inputxt"/>
					<font
					class="Validform_checktip">请输入奖品等级</font>
					</td>
			<td ></td>
			</tr>
			
			<tr>
				<td><label><b style="color: #f00;">*</b>&nbsp;奖品数量:</label></td>
				<td><input
					name="nums"  datatype="n" nullMsg="请填写活动名称"
					type="text" class="inputxt"/>
					<font
					class="Validform_checktip">请输入奖品数量！</font>
					</td>
						<td></td>
			</tr>
			<tr>
				<td><label><b style="color: #f00;">*</b>&nbsp;奖品是否开放:</label></td>
				<td><input type="radio" name="statuss" value="1"  checked="checked"
							id="rad1" />是&nbsp;&nbsp; <input type="radio"
							name="statuss" value="0"
							id="rad0" />否 
					<font
					class="Validform_checktip">选"否"的话永久不会被抽中</font>
					</td>
						<td></td>
			</tr>
			<tr>
				<td><label><b style="color: #f00;">*</b>&nbsp;抽奖几率:</label></td>
				<td><input
					name="winRates" datatype="rate" nullMsg="请填写活动名称" errorMsg="请填入中奖率，比如0.05(0~100之间，最多两位小数)"
					type="text" class="inputxt"/>
					<font
					class="Validform_checktip">请输入中奖几率，比如0.05(0~100之间，最多两位小数)</font>
					</td>
						<td></td>
			</tr>
	
	
	
	
	
	
		</table>
		</div>
	<%!private String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}%>
	<script>
	function addPrizeDiv(){
		
		$("#div_prizes").append($("#hide_div_prize").html());
		//添加验证规则
	}
	function delPrizeDiv(obj){
		$(obj).parent().parent().parent().remove();
	
		
	}
	var editor1;
	KindEditor.ready(function(K) {
		 editor1 = K.create('textarea[name="eggActivity.activityContent"]', {
			width : '560px',
			cssPath : '<%=request.getContextPath()%>/js/kindeditor-4.1.10/plugins/code/prettify.css',
			uploadJson : '<%=request.getContextPath()%>/FileServlet',
			fileManagerJson : '<%=request.getContextPath()%>/js/kindeditor-4.1.10/jsp/file_manager_json.jsp',
										allowFileManager : true,
										afterCreate : function() {
											var self = this;
											K.ctrl(document, 13, function() {
												self.sync();
											});
											K.ctrl(self.edit.doc, 13,
													function() {
														self.sync();
													});
										}
									});
					prettyPrint();
				});

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
			},
			btnSubmit:"#btn_submit", 
			beforeSubmit:function(curform){
				$('#content').val(editor1.html());
				$("#btn_submit").val("提交中..");
				$("#btn_submit").attr('disabled',"true");
			}
		});

		//提交表单
		function doSubmit() {
			/* document.getElementById("content").value=editor1.html(); */
			$('#content').val(editor1.html());
			$("#frmAdd").submit();
		}
	</script>
</body>
</html>