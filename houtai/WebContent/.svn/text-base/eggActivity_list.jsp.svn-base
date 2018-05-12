<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- 默认为IE8渲染模式，解决兼容问题-->
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>秒杀活动列表</title>
<script
	src="<%=request.getContextPath()%>/resource/js/jquery/jquery-1.8.2.min.js"></script>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resource/order/css/base.css" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resource/order/css/user.base.2012.css" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resource/order/css/user.order.2013.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resource/css/style.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resource/js/layer/layer.min.js"></script>
	<script>
	$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});
 $("#place").css({'width':$(window).width()});  
});
</script>
</head>
<body>

	<s:form action="eggActivity!queryList.action" namespace="/admin"
		method="post" id="frmList">
		<c:import url="/WEB-INF/jsp/fyrh/common/navigation.jsp"></c:import>
		<div class="rightinfo">
			<div class="tools">
				<ul class="toolbar">
					<li class="click" onclick='addMiaoSha();'><span><img
							src="<%=request.getContextPath()%>/resource/images/t01.png" /></span>添加</li>
					<li class="click" onclick='editMiaoSha();'><span><img
							src="<%=request.getContextPath()%>/resource/images/t02.png" /></span>修改</li>
					<li onclick='delMaioSha();'><span><img
							src="<%=request.getContextPath()%>/resource/images/t03.png" /></span>删除</li>
				</ul>
			</div>
			<ul class="seachform" id="ul_search">
				<li><label>&nbsp;&nbsp;活动名称:</label><input id="queryName"
					value="<s:property value='queryEggActivity.name'/>" type="text"
					class="searchinput" /></li>
				<input type="hidden" name="queryEggActivity.name" value="${queryEggActivity.name }" id="hQueryName" />

				<li><label>&nbsp;</label><input id="btn_search"
					onclick="searchList()" type="button" class="searchbtn" value="查询" /></li>

			</ul>
			<table class="tablelist" id="tableList">
				<thead>
					<tr>
						<th width="10%">序号<i class="sort"><img
								src="<%=request.getContextPath()%>/resource/images/px.gif" /></i></th>

						<th width="30%">活动名称</th>
						<th width="20%">开始时间</th>
						<th width="20%">结束时间</th>
						<th width="8%">状态</th>
						<th>操作</th>


					</tr>
				</thead>
				<tbody>
					<s:iterator value="pager.entityList" status="i" var="miaosha">

						<tr id='<s:property value="id"/>'>
							<!-- 序列号 -->
							<td><input name="selectedids" type="radio"
								id="radio<s:property value='id'/>" /><s:property value="#i.count" /></td>
							<!-- 活动名称 -->
							<td><s:property value="activityName" /></td>
							<td><s:date name="beginTime" format="yyyy-MM-dd HH:mm:ss" /></td>
							<td><s:date name="endTime" format="yyyy-MM-dd HH:mm:ss" /></td>
							<!-- 状态 -->
							<td><s:if test="status==0">未开始 </s:if> <s:if test="status==1">
									<font color="#FF0000">已开始</font>
								</s:if></td>
							<!-- 操作 -->
							<td>
								<div>
									<%-- <a
									href="/admin/tMiaoSha!initAddMiaoSha.action?id=${tMiaoSha.id}">编辑</a>
								<a
									href="/admin/tMiaoSha!deleteMiaoSha.action?id=${tMiaoSha.id}">删除</a> --%>
									<s:if test="state==0">
									<a
										href="<%=request.getContextPath()%>/admin/tMiaoSha!startMiaoSha.action?id=<s:property value="id"/>">开始秒杀</a>
									</s:if>
									<s:elseif test="state==1">
									<a
										href="<%=request.getContextPath()%>/admin/tMiaoSha!stopMiaoSha.action?id=<s:property value="id"/>">停止秒杀</a>
								</s:elseif>
								</div>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
		<input type="hidden" id="hidecurrid" />
		<c:import url="/WEB-INF/jsp/fyrh/common/page.jsp"></c:import>
		<s:hidden name="navigationhtml.guid"></s:hidden>
	</s:form>
	<script type="text/javascript">
	//绑定行点击事件
	$(document).ready(function(){
		$("#tableList").on("click", "tbody tr", function () {
		    //获得当前行数据
		    var id = $(this).attr("id");
		    $("#hidecurrid").val(id);
		   $("#radio"+id).attr("checked",true);
		})
		});
	//添加数据
	function addMiaoSha(){
		parent.topFrameDivOpen("添加秒杀活动","<%=request.getContextPath()%>/admin/eggActivity!addPage.action","900px", "550px", this);}
	//编辑数据
	function editMiaoSha(){
		var id=  $("#hidecurrid").val();
		if(id==""){
			parent.showTip("请选择一条数据！",2);
			return;
		}
		top.topFrameDivOpen("编辑秒杀","<%=request.getContextPath()%>/admin/eggActivity!editPage.action?id="+id,"900px","550px",this);
	}
	function delMaioSha(){
		var id=  $("#hidecurrid").val();
		if(id==""){
			parent.showTip("请选择一条数据！",2);
			return;
		}
		if(confirm("确认删除信息吗？")){
		var loadi;
			$.ajax({
			   url : "<%=request.getContextPath()%>/admin/tMiaoSha!deleteMiaoSha.action?"+new Date(),
	           type: "post", 
	           data : {"id":id},
	           success: function(result) {
	           	layer.close(loadi);
	        	   if(result!=null||result!=""){
					if(result.message=="success"){
						//删除成功后弹出提示并刷新列表页
						parent.showTip("删除成功！",1);
						searchList();
					}else{
						parent.showTip("删除失败！",0);
					}
	        	   }else{
	        	   		layer.close(loadi);
	        		   parent.showTip("删除失败！",0);
	        	   }
	        	  
	           },
	           error: function() {
	        	   parent.showTip("系统错误，请联系管理员！",0);
	           },
	       		beforeSend : function() {
	       		 loadi = layer.load('删除中，请稍等…'); //需关闭加载层时，执行layer.close(loadi)即可

	           }
	        });
	}else{
		return;
	}
	}
	//刷新列表页(重置搜索条件)
	function reloadwin(){
			$("#hQueryName").val("");
			$("#queryName").val("");
			$("#frmList").submit();
		}

		//搜索信息(保留搜索条件)
		function searchList() {
			$("#hQueryName").val($("#queryName").val());
			$("#frmList").submit();
		}

		//监听搜索区域的回车事件
		$('#ul_search').keydown(function(e) {
			if (e.keyCode == 13) {
				searchList();//处理事件 
			}
		});
	</script>

</body>
</html>