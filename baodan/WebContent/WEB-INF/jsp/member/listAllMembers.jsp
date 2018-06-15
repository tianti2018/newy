<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<title>list</title>


<link href="images/core_res/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="images/core_res/css/theme.css" rel="stylesheet" type="text/css"/>
 
<script src="images/common_res/js/jquery.js" type="text/javascript"></script>

<script src="images/common_res/js/pony.js" type="text/javascript"></script>

 
<script language="javascript" type="text/javascript">
$(function(){
	// 判断是否有sub-menu
	 if($("ul.sub-menu")){
		
			$(".menu-list>li>a").click(function(){
				var $sub_menu = $(this).next(".sub-menu"),
					sub_h = $sub_menu.find("li").length * 46;	//27px表示每个sub-menu 下面li的高度
				if($sub_menu.height() == 0){					//判断是否在可视区域
					$sub_menu.animate({"height":sub_h});		//添加动画
					$(this).find(".arrows").addClass("rotated");
				}else{
					$sub_menu.animate({"height":0});
					$(this).find(".arrows").removeClass("rotated");
				}
			})
		}else{
			return false;
		} 
	
})
	function body_onload () {
		
		var message = "<c:out value="${message}"/>";
		
		if (message != "" && message!=null) {
			alert(message);					
		}
	}

	function activityUser() {
		self.location.href="user!activityUser.action?userId="+arguments[0];			
	}
	
	function recharge() {
		var textId = arguments[0];
		var textValue = document.getElementById(textId).value;
		var userId = arguments[1];
	  /* 	if (""==textValue) {
			alert("存值金额不能为空");
			document.getElementById(textId).focus();
			return false;
		}
		else {
			//var matchOne = /^\+?[1-9][0-9]*$/;
			var matchOne = /^(-|\+)?\d+$/;
			if (!matchOne.test(textValue)) {
				alert("对不起，格式不正确，请输入整数！");
				document.getElementById(textId).focus();
				return false;
			}
			self.location.href("user!recharge.action?userId="+userId+"&submitMoney="+textValue);
		} 
		 */
		self.location.href="user!recharge.action?userId="+userId+"&submitMoney="+textValue;
		
	}
	
	function deleteUser() {
		if(confirm('您确定删除该用户吗？')) {
			self.location.href="user!deleteUser.action?userId="+arguments[0];
			/* window.open("user!deleteUser.action?userId="+arguments[0], "rightFrame"); */
			return true;	
		}
		return false;
	}
	function blockUser() {
		if(confirm('您确定锁定该用户吗？')) {
			self.location.href="user!blockUser.action?userId="+arguments[0];
			/* window.open("user!blockUser.action?userId="+arguments[0], "rightFrame"); */
			return true;	
		}
		return false;
	}
	function releaseUser() {
		if(confirm('您确定解锁该用户吗？')) {
			self.location.href="user!releaseUser.action?userId="+arguments[0];
			/* window.open("user!releaseUser.action?userId="+arguments[0], "rightFrame"); */
			return true;	
		}
		return false;
	}
	function isHTApp() {
		if(confirm('您确定该用户不回填吗？')) {
			window.open("user!isHTApp.action?userId="+arguments[0], "rightFrame");
			return true;	
		}
		return false;
	}
	
	function cleanSearch(){
		$("#input_qUserName").val("");
		$("#input_qflag").val("");
		$("#input_parentId").val("");
		$("#input_parentUserName").val("");
		$("#input_qUserId").val()
	}
	loadSearch =function () {
		$("#hUserName").val($("#input_qUserName").val());
		$("#hinput_block").val($("#input_qflag").val());
		$("#hinput_qphone").val($("#input_phone").val());
		$("#userId").val($("#input_qUserId").val());
		$("#currentPage").val(1);
		gotoPage();
// 		self.location.href="user!listAllUser.action?loginName="+loginName;	
	}
	
	changeLevel = function () {
		if(confirm('您确定该用户改变享受待遇吗？')) {
			var submitMoney = document.getElementById(arguments[0]).value;
			self.location.href="user!changeLevel.action?userId="+arguments[1]+"&&submitMoney="+submitMoney;	
			return true;
		}
		return false;
	}
	
	function kaiTongDianpu(useryId){
		$.ajax({
			url : "<%=request.getContextPath()%>/member!kaitongDianpu.action",
	        type: "post", 
	        data : {"useryId":useryId},
	        success: function(result) {
	     	   if(result.success){
	     		  alert("开通成功");
	     		  window.location.reload(true);
	     	   }
	        },
	        error: function() {
	     	   alert("系统错误，请联系管理员！",0);
	        },
	    	beforeSend : function() {
	        }
	     });
	}
	function getRefer(referId){
		$.ajax({
			url : "<%=request.getContextPath()%>/orders!getRefer.action",
	        type: "post", 
	        data : {"ordersId":referId},
	        success: function(result) {
	     	   if(result!=null||result!=""){
	     		  alert("收货姓名:"+result.toUserName+"\n收货人电话:"+result.phone+"\n推荐人编号:"+result.refuserId+"\n推荐人昵称:"
	     				  +result.refuserName);
	     	   }
	        },
	        error: function() {
	     	   alert("系统错误，请联系管理员！",0);
	        },
	    	beforeSend : function() {
	        }
	     });
	}
	function lingdao(userId){
		$("#input_qUserId").val(userId);
		$("#parentId").val('');
		loadSearch();
	}
	
	function shouxia(parentId){
		$("#input_qUserId").val('');
		$("#parentId").val(parentId);
		loadSearch();
	}
	function orderList(userId){
		var $form = $("<form method='post' action='orders!tuanduiCaiwu.action'></form>");
		 try{
		    	$form.append("<input type='text' name='userId' value='" + userId + "' />");
		        $("body").append($form);
		        $form.submit();
		    } finally{
		        if ($form)
		            $form.remove();
		    }
	}
	
	function bangding(useryId){
		self.location.href="member!quBangDing.action?useryId="+useryId;	
	}
</script>
</head>
<body onload="body_onload();">
	<div class="body-box">
	<div class="rhead">
		<div class="rpos">您的当前位置: 后台管理-->用户管理</div>
		<div class="clear"></div>
	</div>
	<div class="rhead">
		<div class="clear" id="div_search">
			<table class="pn-ltable" width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr><td align="center">用户ID：</td><td align="center"><input type="text" id="input_qUserId" name="userId" value="${userId }"/></td></tr>
				<tr><td align="center">用户昵称：</td><td align="center"><input type="text" id="input_qUserName" name="userName" value="${userName }"/></td></tr>
				<tr><td align="center"><input type="button" id="cmdBtn" name="cmdBtn" value="搜索" style="cursor:pointer;width: 100px;" onclick="loadSearch();" /></td>
				<td align="center"><input type="button" id="cmdBtn1" name="cmdBtn1" value="清空条件" style="cursor:pointer" onclick="cleanSearch();" /></td></tr>
			</table>
		</div>
	</div>
	<c:if test="${session.shebei=='mobile' }">
		<c:forEach items="${litPager}" var="item" varStatus="status">
			<ul class="menu-list">
				<li><a href="javascript:;"><i class="arrows"></i><em>${item.userName}</em></a>
					<ul class="sub-menu" >
						<li>用户电话：<em>${item.phone}</em></li>
						<li>注册日期：<em>${item.createDate}</em></li>
						<li>登录名称：<em>${item.loginName}</em></li>
						<li>状态：<em>
							<c:if test="${item.block=='0'}"><font size="2">未锁定</font></c:if>
							<c:if test="${item.block=='1'}"><font size="2">已锁定</font></c:if>
						</em></li>
					</ul>
				</li>
			</ul>
			</c:forEach>
			<c:import url="/WEB-INF/jsp/page/page.jsp">
     			<c:param name="pageActionUrl" value="user!listAllUser.action"/>
     		</c:import>
     		</br>
     		</br>
     		</br>
     		</br>
     		</br>
     		</br>
			<footer class="footer">
				<div class="foot-nav">
				<c:if test="${adminUser}">
					<a href="<%=request.getContextPath()%>/orders!listYichang.action"><i class="foot-icon"><img src="<%=request.getContextPath()%>/images/i_buy.png" alt=""></i><span>异常订单</span></a>
				</c:if>
				<a href="<%=request.getContextPath()%>/orders!ordersList.action" ><i class="foot-icon"><img src="<%=request.getContextPath()%>/images/i_orders.png" alt=""></i><span>我的订单</span></a>
				
				<a href="<%=request.getContextPath()%>/user!listAllUser.action" class="nowpage"><i class="foot-icon"><img src="<%=request.getContextPath()%>/images/i_family.png" alt=""></i><span>团队列表</span></a>
				
				<c:if test="${adminUser}">
					<a href="#"><i class="foot-icon"><img src="<%=request.getContextPath()%>/images/i_erweima.png" alt=""/></i><span>待开发</span></a>
				</c:if>
				</div>
			</footer>
			
		</c:if>
	<c:if test="${session.shebei=='pc' }">	
	<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
		<thead class="pn-lthead">
		<tr>
			<th width="50">用户编号</th>
			<th width="80">用户姓名</th>
			<th width="80">登录名称</th>
			<th width="80">电话</th>
			<th width="80">微信昵称</th>
			<th width="80">微信头像</th>
			<th width="80">团队数量</th>
			<th width="80">总收益</th>
			<th width="80">余额</th>
			<th width="80">总成交量</th>
			<th width="80">头衔</th>
			<th width="80">店铺ID</th>
			<th width="80">关注日期</th>
			<th width="80">状态</th>
			<th width="120">操作</th>
		</tr>
		</thead>
		<tbody class="pn-ltbody">
			<c:forEach items="${litPager}" var="item" varStatus="status">
			<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
				<td align="center">${item.usery.id}</td>
				<td align="center">${item.user.userName }</td>
				<td align="center">${item.user.loginName }</td>
				<td align="center">${item.user.phone }</td>
				<td align="center">${item.usery.userName }</td>
				<td align="center">
					<img src="${item.usery.headUrl }" style="width: 50px"/>
				</td>
				<td align="center">${item.usery.childNum+0 }</td>
				<td align="center">${item.usery.totalMoney+0 }</td>
				<td align="center">${item.usery.account+0 }</td>
				<td align="center">${item.usery.totalOrderNum+0 }</td>
				
				<td align="center">
					<c:if test="${item.usery.level==0}">准店小二</c:if>
					<c:if test="${item.usery.level==1}">店小二</c:if>
					<c:if test="${item.usery.level==2}">掌柜</c:if>
					<c:if test="${item.usery.level==3}">大掌柜</c:if>
				</td>
				<td align="center">${item.usery.dianPuId }</td>
				<td align="center"><fmt:formatDate value="${item.usery.appDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td align="center">
					<c:if test="${item.usery.subscribe==0}">
						已关注
					</c:if>
					<c:if test="${item.usery.subscribe==1}">
						未关注
					</c:if>
				</td>
				<td align="center">
					<c:if test="${adminUser}">
						<c:if test="${item.usery.dianPuId==null}">
								<input type="button" value="开通店铺" onclick="kaiTongDianpu('${item.usery.id}');" /> |
						</c:if>
						<c:if test="${item.usery.parentId==null}">
							<input type="button" value="关系绑定" onclick="bangding('${item.usery.id}');" /> |
						</c:if>
						<c:if test="${item.usery.parentId!=null}">
							<input type="button" value="领导" onclick="lingdao('${item.usery.parentId}');" />|
						</c:if>
						<input type="button" value="手下" onclick="shouxia('${item.usery.id}');" />
					</c:if>
				</td>
				<%-- <td class="pn-lopt"><a href="user!initModifyUser.action?userId=${item.userId}&&currentPage=<s:property value="pager.currentPage"/>" class="pn-loperator">修改</a></td>
				<td class="pn-lopt"><a href="javascript:void(0);" onclick="deleteUser('${item.userId}');" class="pn-loperator">删除</a></td> --%>
			</tr>
		</c:forEach>   
		</tbody>
	</table>

  <!-- 导入分页组件  -->
     <c:import url="/WEB-INF/jsp/page/page.jsp">
    	  <c:param name="pageActionUrl" value="member!listAllMembers.action"/>
     </c:import>
	</c:if>
</div>
<script>
//监听搜索区域的回车事件
$('#div_search').keydown(function(e){ 
	if(e.keyCode==13){ 
		loadSearch();//处理事件 
	} 
}); 

var block = '<%=request.getAttribute("block")%>';
if(block != "null")
	$("#input_qflag  option[value='"+block+"'] ").attr("selected",true);
</script>
</body>
</html>
