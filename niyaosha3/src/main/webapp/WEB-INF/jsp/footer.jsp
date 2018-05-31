<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.flexslider-min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/flexslider.css" />
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/css/source/jquery.fancybox.css?v=2.1.5" media="screen" />
<script type="text/javascript" 	src="<%=request.getContextPath()%>/css/source/jquery.fancybox.pack.js?v=2.1.5"></script>
<div id="footer">
  <div class="foot_nav">
    <div class="foot_nav_inner">
	    <a href="<%=request.getContextPath()%>/user/userAction!jampshopIndex.action"><span class="line"><i class="nav_f2"></i></span><span class="text">首页</span></a>
	    <a href="javascript:zanwu();"><span class="line"><i class="nav_f4"></i></span><span class="text">购物车</span></a>
	    <a id="orderAdress" class="clear" href="#loginline" onclick="jianceDenglu();"><span class="line"><i class="nav_f5"></i></span><span class="text">个人中心</span></a>
	</div>
  </div>
</div>

