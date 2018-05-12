<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>list</title>


<link href="images/core_res/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="images/core_res/css/theme.css" rel="stylesheet" type="text/css"/>
 
<script src="images/common_res/js/jquery.js" type="text/javascript"></script>

<script src="images/common_res/js/pony.js" type="text/javascript"></script>

 
<script language="javascript" type="text/javascript">

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
			window.open("user!deleteUser.action?userId="+arguments[0], "rightFrame");
			return true;	
		}
		return false;
	}
	function blockUser() {
		if(confirm('您确定锁定该用户吗？')) {
			window.open("user!blockUser.action?userId="+arguments[0], "rightFrame");
			return true;	
		}
		return false;
	}
	function releaseUser() {
		if(confirm('您确定解锁该用户吗？')) {
			window.open("user!releaseUser.action?userId="+arguments[0], "rightFrame");
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
		$("#input_userId").val("");
		$("#input_qUserName").val("");
		$("#input_qflag").val("2");
		$("#input_parentId").val("");
		$("#input_parentUserName").val("");
	}
	loadSearch =function () {
		$("#userId").val($("#input_userId").val());
		$("#hUserName").val($("#input_qUserName").val());
		$("#flag").val($("#input_qflag").val());
		$("#parentId").val($("#input_parentId").val());
		$("#parentUserName").val($("#input_parentUserName").val());
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
</script>
</head>
<body onload="body_onload();">
	<div class="body-box">
	<div class="rhead">
		<div class="rpos">您的当前位置: 后台管理-->会员管理</div>
		<div class="clear"></div>
	</div>
	<div class="rhead">
		<div class="clear" id="div_search">
			推荐人编号：<input type="text" id="input_parentId" name="parentId" value="${parentId }"/>&nbsp;&nbsp;
			推荐人昵称:<input type="text" id="input_parentUserName" name="parentUserName" value="${parentUserName }"/>&nbsp;&nbsp;
			会员编号：<input type="text" id="input_userId" name="userId" value="${userId }"/>&nbsp;&nbsp;
			会员昵称：<input type="text" id="input_qUserName" name="userName" value="${userName }"/>&nbsp;&nbsp;
			取消关注过滤条件：<select id="input_qflag">
							<option value="2">请选择</option>
							<option value="0">未取消关注</option>
							<option value="1">已取消关注</option>
					 </select>&nbsp;&nbsp;
			<input type="button" id="cmdBtn" name="cmdBtn" value="搜索" style="cursor:pointer" onclick="loadSearch();" />&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" id="cmdBtn1" name="cmdBtn1" value="清空条件" style="cursor:pointer" onclick="cleanSearch();" />
		</div>
	</div>
	<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
		<thead class="pn-lthead">
		<tr>
			<th width="32">序号</th>
			<th width="100">推荐人编号</th>
			<th width="100">推荐人昵称</th>
			<th width="100">会员编号</th>
			<th width="80">会员昵称</th>
			<th width="100">头像</th>
			<th width="80">销售额</th>
			<th width="80">佣金</th>
			<!-- <th width="80">电话</th>
			
			<th width="80">一级密码</th>
			<th width="80">二级密码</th>
			
			<th width="80">服务中心</th>
			<th width="100">推荐人</th> -->
			<th width="80">关注日期</th>
			<th width="120">一级族长人数/一级总人数</th>
			<th width="120">二级族长人数/二级总人数</th>
			<th width="120">三级族长人数/三级总人数</th>
			<th width="80">是否取消关注</th>
			<!-- <th width="100">充值</th>
			<th width="120">操作</th> -->
		</tr>
		</thead>
		<tbody class="pn-ltbody">
			<c:forEach items="${litPager}" var="item" varStatus="status">
			<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
				<td align="center">${status.index+1}</td>
				<td align="center"><a href="javascript:void(0);" onclick="getRefer('${item.referrerId}')">${item.referrerId}</a></td>
				<td align="center"><a href="javascript:void(0);" onclick="getRefer('${item.referrerId}')">${item.referrerName}</a></td>
				<td align="center">${item.userId}</td>
				<td align="center">${item.userName }</td>
				<td align="center"><c:if test="${item.childString1!=null&&item.childString1 != '' }"><a href="${item.childString1 }" target="_blak">点击查看</a></c:if> </td>
				<td align="center">${item.xiaoShowE}</td>
				<td align="center">${item.yongjin}</td>
				<td align="center"><fmt:formatDate value="${item.appDate}" pattern="yyyy-MM-dd"/></td>
				<td align="center">${item.child1}</td>
				<td align="center">${item.child2}</td>
				<td align="center">${item.child3}</td>
				<td align="center"><c:if test="${item.childInteger1 ==0}">未取消</c:if><c:if test="${item.childInteger1 ==1}"><span style="color:red;">已取消</span></c:if>   </td>
				<%-- <td align="center">
					<a href="member!listAlljifen.action?userId=${item.userId}">${item.loginName}</a>
				</td> --%>
				<%--<td align="center">${item.loginName}</td>--%>
				<%-- <td align="center">
					<a href="user!initModifyUser.action?userId=${item.userId}">${item.userName}</a>
				</td> --%>
				
				
				<%-- <td align="center">${item.passWord}</td>
				<td align="center">${item.passWord2}</td>
				<td align="center">
					<c:if test="${item.isAppCenter=='1'}">是</c:if>
					<c:if test="${item.isAppCenter!='1'}">否</c:if>
				</td>
				<td align="center">${item.referrer.loginName}</td>
				<td align="center"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></td>
				
				<td align="center">
					<c:if test="${item.block=='1'}">
						已锁定
					</c:if>
				</td>
				<td >
					<input id="cz${status.index+1}" type="text" />
					<select id="cz${status.index+1}">
						<c:forEach begin="1" end="100" var="dateT">
							<option value="${dateT}">${dateT}个</option>
						</c:forEach>
					</select>
					<input id="btn${status.index+1}" type="button" value="充值" onclick="return recharge('cz${status.index+1}','${item.userId}');" />
				
				</td>
				<td>
					<input type="button" value="锁定" onclick="blockUser('${item.userId}');" /> | <input type="button" value="解锁" onclick="releaseUser('${item.userId}');" />| <input type="button" value="删除" onclick="deleteUser('${item.userId}');" />
				</td> --%>
				<%-- <td class="pn-lopt"><a href="user!initModifyUser.action?userId=${item.userId}&&currentPage=<s:property value="pager.currentPage"/>" class="pn-loperator">修改</a></td>
				<td class="pn-lopt"><a href="javascript:void(0);" onclick="deleteUser('${item.userId}');" class="pn-loperator">删除</a></td> --%>
			</tr>
		</c:forEach>   
		</tbody>
	</table>

  <!-- 导入分页组件  -->
     <c:import url="/WEB-INF/jsp/page/page.jsp">
    	  <c:param name="pageActionUrl" value="user!listAllUser.action"/>
     </c:import>

</div>
<script>
//监听搜索区域的回车事件
$('#div_search').keydown(function(e){ 
	if(e.keyCode==13){ 
		loadSearch();//处理事件 
	} 
}); 

var flag = '<%=request.getAttribute("flag")%>';
if(flag != "null")
	$("#input_qflag  option[value='"+flag+"'] ").attr("selected",true);
</script>
</body>
</html>
