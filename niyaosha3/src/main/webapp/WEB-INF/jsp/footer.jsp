<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="<%=request.getContextPath()%>/css/my_v2.s.min.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/base.s.min.css" rel="stylesheet" />
<div id="footer">
  <div class="foot_nav">
    <div class="foot_nav_inner">
	    <a href="<%=request.getContextPath()%>/user/userAction!jampshopIndex.action"><span class="line"><i class="nav_f2"></i></span><span class="text">首页</span></a>
	    <a href="javascript:zanwu();"><span class="line"><i class="nav_f4"></i></span><span class="text">购物车</span></a>
	    <a href="<%=request.getContextPath()%>/user/userAction!gotoPersonalCenter.action"><span class="line"><i class="nav_f5"></i></span><span class="text">个人中心</span></a>
	</div>
  </div>
</div>