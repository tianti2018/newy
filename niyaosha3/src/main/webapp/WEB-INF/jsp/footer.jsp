<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ResourceBundle"%>
<div id="footer">
  <div class="foot_nav">
    <div class="foot_nav_inner">
    	<a href="<%=request.getContextPath() %>/notice/noticeAction!noticeList.action"><span class="line"><i class="nav_f4"></i></span><span class="text">公告</span></a>
	    <a href="<%=request.getContextPath()%>/order/orderAction!myOrderList.action"><span class="line"><i class="nav_f2"></i></span><span class="text">我的订单</span></a>
	    <a id="orderAdress" href="<%=request.getContextPath()%>/user/userAction!gotoPersonalCenter.action" ><span class="line"><i class="nav_f5"></i></span><span class="text">个人中心</span></a>
	</div>
  </div>
</div>

