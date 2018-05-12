<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>管理页面</title>

<script src="js/prototype.lite.js" type="text/javascript"></script>
<script src="js/moo.fx.js" type="text/javascript"></script>
<script src="js/moo.fx.pack.js" type="text/javascript"></script>
<style>
	body {
		font:12px Arial, Helvetica, sans-serif;
		color: #000;
		background-color: #EEF2FB;
		margin: 0px;
	}
	#container {
		width: 182px;
	}
	H1 {
		font-size: 12px;
		margin: 0px;
		width: 182px;
		cursor: pointer;
		height: 30px;
		line-height: 20px;	
	}
	H1 a {
		display: block;
		width: 182px;
		color: #000;
		height: 30px;
		text-decoration: none;
		moz-outline-style: none;
		background-image: url(images/menu_bgs.gif);
		background-repeat: no-repeat;
		line-height: 30px;
		text-align: center;
		margin: 0px;
		padding: 0px;
	}
	.content{
		width: 182px;
		height: 26px;
		
	}
	.MM ul {
		list-style-type: none;
		margin: 0px;
		padding: 0px;
		display: block;
	}
	.MM li {
		font-family: Arial, Helvetica, sans-serif;
		font-size: 12px;
		line-height: 26px;
		color: #333333;
		list-style-type: none;
		display: block;
		text-decoration: none;
		height: 26px;
		width: 182px;
		padding-left: 0px;
	}
	.MM {
		width: 182px;
		margin: 0px;
		padding: 0px;
		left: 0px;
		top: 0px;
		right: 0px;
		bottom: 0px;
		clip: rect(0px,0px,0px,0px);
	}
	.MM a:link {
		font-family: Arial, Helvetica, sans-serif;
		font-size: 12px;
		line-height: 26px;
		color: #333333;
		background-image: url(images/menu_bg1.gif);
		background-repeat: no-repeat;
		height: 26px;
		width: 182px;
		display: block;
		text-align: center;
		margin: 0px;
		padding: 0px;
		overflow: hidden;
		text-decoration: none;
	}
	.MM a:visited {
		font-family: Arial, Helvetica, sans-serif;
		font-size: 12px;
		line-height: 26px;
		color: #333333;
		background-image: url(images/menu_bg1.gif);
		background-repeat: no-repeat;
		display: block;
		text-align: center;
		margin: 0px;
		padding: 0px;
		height: 26px;
		width: 182px;
		text-decoration: none;
	}
	.MM a:active {
		font-family: Arial, Helvetica, sans-serif;
		font-size: 12px;
		line-height: 26px;
		color: #333333;
		background-image: url(images/menu_bg1.gif);
		background-repeat: no-repeat;
		height: 26px;
		width: 182px;
		display: block;
		text-align: center;
		margin: 0px;
		padding: 0px;
		overflow: hidden;
		text-decoration: none;
	}
	.MM a:hover {
		font-family: Arial, Helvetica, sans-serif;
		font-size: 12px;
		line-height: 26px;
		font-weight: bold;
		color: #006600;
		background-image: url(images/menu_bg2.gif);
		background-repeat: no-repeat;
		text-align: center;
		display: block;
		margin: 0px;
		padding: 0px;
		height: 26px;
		width: 182px;
		text-decoration: none;
	}
</style>


</head>

<body>
	<table width="100%" height="800" border="0" cellpadding="0" cellspacing="0" bgcolor="#EEF2FB">
	  <tr>
	    <td width="182" valign="top">
	    	<div id="container">
	      	
	      	 <c:if test="${loginPage=='1'}">	
		      <h1 class="type"><a href="javascript:void(0)">会员中心</a></h1>
		      <div class="content">
		        <table width="100%" border="0" cellspacing="0" cellpadding="0">
		          <tr>
		            <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
		          </tr>
		        </table>
		        <ul class="MM">
					<%-- 是报单中心才可以报单 --%>
					<li><a href="news!listAll1.action" target="rightFrame">公告管理</a></li>
		         	<li><a href="message!listAll.action" target="rightFrame">我要留言</a></li>
		         	<li><a href="alipay!initAlipay.action" target="rightFrame">购买存值卡号和卡密</a></li>
					<li><a href="user!initAddUser.action" target="rightFrame">注册新会员</a></li>
					
					<li><a href="user!confirmpwd2.action?password2Flag=7" target="rightFrame">申请成为服务中心</a></li>
					<li><a href="user!confirmpwd2.action?password2Flag=8" target="rightFrame">我的铁粉</a></li> 
					
				<%-- -- 	<li><a href="card!intcanyuhuodong.action" target="rightFrame">参与活动</a></li> --%>
					<li><a href="products!listAll.action" target="rightFrame">购买产品</a></li>
					<%-- <li><a href="egg!initPage.action?activityId=8" target="rightFrame">砸金蛋</a></li>--%>
					
					
					 <li><a href="user!confirmpwd2.action?password2Flag=3" target="rightFrame">个人资料维护</a></li>
		         	 <li><a href="user!confirmpwd2.action?password2Flag=4" target="rightFrame">密码修改</a></li>
					 <li><a href="egg!phoneQrcodePage.action" target="rightFrame">电话卡二维码</a></li>
		        </ul>
		      </div>
		      
		      
		      <h1 class="type"><a href="javascript:void(0)">财务管理</a></h1>
		      <div class="content">
		        <table width="100%" border="0" cellspacing="0" cellpadding="0">
		          <tr>
		            <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
		          </tr>
		        </table>
		         <ul class="MM">
		         	<li><a href="user!confirmpwd2.action?password2Flag=2" target="rightFrame">我的充值卡号和密码</a></li>
					<%-- <li><a href="member!listAllUserOrder.action" target="rightFrame">我的积分明细</a></li>--%>
					<%--<li><a href="member!listAllFhRecordCount.action" target="rightFrame">我的积分汇总</a></li>--%>
					<li><a href="member!listAlljifen.action" target="rightFrame">我的积分明细</a></li>
					<li><a href="user!confirmpwd2.action?password2Flag=9" target="rightFrame">积分转让</a></li>
					<li><a href="user!confirmpwd2.action?password2Flag=1" target="rightFrame">申请提现</a></li>
					<li><a href="orders!listAllOrderList.action" target="rightFrame">我的订单</a></li>
						<%--<a href="member!listAllRepeat.action" target="rightFrame">重复消费</a></li> --%><li>
					
		        </ul>
		      </div>
		      </c:if>
		      <c:if test="${loginPage=='2'}">
			      <h1 class="type"><a href="javascript:void(0)">后台管理</a></h1>
			      <div class="content">
			        <table width="100%" border="0" cellspacing="0" cellpadding="0">
			          <tr>
			            <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
			          </tr>
			        </table>
			         <ul class="MM"> 
						<%-- <li>
							<a href="user!listAllUser.action" target="rightFrame">
								会员管理
							</a>
						</li>
						<li>
							<a href="user!listAllTixian.action" target="rightFrame">
								提现审核
							</a>
						</li>
						<li>
							<a href="member!listAllappCenter.action" target="rightFrame">
								报单中心管理
							</a>
						</li>
						<li>
							<a href="member!testjiesuan.action" target="rightFrame">
								测试奖金结算
							</a>
						</li> --%>
						  <c:forEach items="${users.role.roleFunction}" var="item">																		
							<c:if test="${item.function.module == '1'}">
								<li>
									<a href="${item.function.url}" target="rightFrame">
										${item.function.functionName}
									</a>
								</li>
							</c:if>																		
						</c:forEach>
			        </ul>
			      </div>
			      
			      <%-- <h1 class="type"><a href="javascript:void(0)">信息管理</a></h1>
			      <div class="content">
			        <table width="100%" border="0" cellspacing="0" cellpadding="0">
			          <tr>
			            <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
			          </tr>
			        </table>
			         <ul class="MM"> 
						<c:forEach items="${users.role.roleFunction}" var="item">																		
							<c:if test="${item.function.module == '2'}">
								<li>
									<a href="${item.function.url}" target="rightFrame">
										${item.function.functionName}
									</a>
								</li>
							</c:if>																		
						</c:forEach>
			        </ul>
			      </div>
			      
			      <h1 class="type"><a href="javascript:void(0)">系统管理</a></h1>
			      <div class="content">
			        <table width="100%" border="0" cellspacing="0" cellpadding="0">
			          <tr>
			            <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
			          </tr>
			        </table>
			         <ul class="MM"> 
			         	
			         	  <c:forEach items="${users.role.roleFunction}" var="item">																		
							<c:if test="${item.function.module == '3'}">
								<li>
									<a href="${item.function.url}" target="rightFrame">
										${item.function.functionName}
									</a>
								</li>
							</c:if>																		
						</c:forEach>
			         	<li><a href="role!listAll.action" target="rightFrame">角色管理</a></li>
			         	<li><a href="function!listAll.action" target="rightFrame">功能管理</a></li>
			         	<li><a href="roleFunction!listAll.action" target="rightFrame">授权管理</a></li>
						<li><a href="adminUser!listAllUser.action" target="rightFrame">管理员管理</a></li>
						
						<li><a href="#" target="rightFrame">财务账号列表</a></li>
						<li><a href="#" target="rightFrame">管理日志</a></li>
						<li><a href="#" target="rightFrame">系统参数</a></li>
			        </ul>
			      </div>--%>
			      
			      <h1 class="type"><a href="javascript:void(0)">财务管理</a></h1>
			      <div class="content">
			        <table width="100%" border="0" cellspacing="0" cellpadding="0">
			          <tr>
			            <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
			          </tr>
			        </table>
			         <ul class="MM"> 
		         	   <c:forEach items="${users.role.roleFunction}" var="item">																		
						 <c:if test="${item.function.module == '4'}">
							<li>
								<a href="${item.function.url}" target="rightFrame">
									${item.function.functionName}
								</a>
							</li>
						</c:if>																		
					  </c:forEach>
			        </ul>
			      </div> 
			      
		      </c:if>
		      	
	    		</div>
	     		  
	     		  <c:if test="${loginPage=='1'}">	
	     		  <div>
	     		  	 <ul class="MM"> 
						<li><font color="red">会员姓名：${user.userName}</font></li>
						<li><font color="red">会员总积分：${totalTJiFen}</font></li>
						<li><font color="red">剩余积分：${totalJiFen}</font></li>
			        </ul>
	     		  </div>
	     		 </c:if> 	     
	      	<script type="text/javascript">
				var contents = document.getElementsByClassName('content');
				var toggles = document.getElementsByClassName('type');
				
				var myAccordion = new fx.Accordion(
					toggles, contents, {opacity: true, duration: 400}
				);
				myAccordion.showThisHideOpen(contents[0]);
			</script>
			
			
	    	</td>
	  	</tr>
	  	
		</table>
	</body>
</html>
