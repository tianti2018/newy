<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ResourceBundle"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	ResourceBundle res = ResourceBundle.getBundle("system");
%>
<div id="footer">
  <div class="foot_nav">
    <div class="foot_nav_inner">
    	<a href="<%=request.getContextPath() %>/notice/noticeAction!noticeList.action"><span class="line"><i class="nav_f3"></i></span><span class="text">公告</span></a>
	    <c:if test="${null==userVo}">
		    <a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=<%=res.getString("APPID")%>&redirect_uri=<%=res.getString("yumingzhz")%>%2Forder%2ForderAction!myOrderList.action&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect"><span class="line"><i class="nav_f2"></i></span><span class="text">我的订单</span></a>
		    <a id="orderAdress" href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=<%=res.getString("APPID")%>&redirect_uri=<%=res.getString("yumingzhz")%>%2Fuser%2FuserAction!gotoPersonalCenter.action&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect" ><span class="line"><i class="nav_f5"></i></span><span class="text">个人中心</span></a>
	    	
	    </c:if>
	    
	    <c:if test="${null!=userVo}">
		    <a href="<%=request.getContextPath()%>/order/orderAction!myOrderList.action"><span class="line"><i class="nav_f2"></i></span><span class="text">我的订单</span></a>
		    <a id="orderAdress" href="<%=request.getContextPath()%>/user/userAction!gotoPersonalCenter.action" ><span class="line"><i class="nav_f5"></i></span><span class="text">个人中心</span></a>
		</c:if>
	</div>
  </div>
</div>

