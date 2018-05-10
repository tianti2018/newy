<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>list</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/guanwang/js/jquery.1.8.2.min.js" ></script>
<link href="images/common_res/css/jquery_validate.css" rel="stylesheet" type="text/css"/>
<link href="images/common_res/css/jquery.alerts.css" rel="stylesheet" type="text/css"/>
<link href="images/core_res/css/front.css" rel="stylesheet" type="text/css"/>
<link href="images/core_res/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="images/core_res/css/theme.css" rel="stylesheet" type="text/css"/>
 
<style type="text/css">
ul,li{list-style-type:none;}
#scrollDiv{width:750px;height:375px;font-size:12px;color:#000;min-height:25px;line-height:25px;border:#ccc 1px solid;overflow:hidden;background:#fff; 
/* filter:alpha(opacity:40);opacity:0.4;  */
}
#scrollDiv li{padding-left:10px;height:25px;}
</style>
<script language="javascript" type="text/javascript">
//滚动插件
(function($){
	$.fn.extend({
		Scroll:function(opt,callback){
				//参数初始化
				if(!opt) var opt={};
				var _this=this.eq(0).find("ul:first");
				var lineH=_this.find("li:first").height(), //获取行高
					line=opt.line?parseInt(opt.line,10):parseInt(this.height()/lineH,10), //每次滚动的行数，默认为一屏，即父容器高度
					speed=opt.speed?parseInt(opt.speed,10):1000, //卷动速度，数值越大，速度越慢（毫秒）
					timer=opt.timer?parseInt(opt.timer,10):5000; //滚动的时间间隔（毫秒）
				if(line==0) line=1;
				var upHeight=0-line*lineH;
				//滚动函数
				scrollUp=function(){
						_this.animate({
								marginTop:upHeight
						},speed,function(){
								for(i=1;i<=line;i++){
										_this.find("li:first").appendTo(_this);
								}
								_this.css({marginTop:0});
						});
				}
				//鼠标事件绑定
				_this.hover(function(){
						clearInterval(timerID);
				},function(){
						timerID=setInterval("scrollUp()",timer);
				}).mouseout();
		}       
	});
})(jQuery);

$(document).ready(function(){
	$("a[id^='a_']").click(function(){
		$("#div_"+this.rel).toggle();
	});
});
</script>
</head>
<body>
	<div class="body-box">
	<div class="rhead">
		<div class="rpos">您的当前位置: 市场管理-->公告管理</div>
		<div class="clear"></div>
	</div>
	<div class="rhead">
		<div class="clear"></div>
	</div>
	<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
		<thead class="pn-lthead">
		<tr>
			<th width="32">序号</th>
			<th width="100px">题目</th>		
			<th width="100px">发布部门</th>
			<th width="100px">发布日期</th>
		</tr>
		</thead>
		<tbody class="pn-ltbody">
			<c:forEach items="${litPager}" var="item" varStatus="status">
			<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);">
				<td>${status.index+1}</td>
				<td align="center">
				<a href="javascript:void(0)" rel=${status.index} id="a_${status.index}" title="点击名称查看内容">${item.title}</a>
				<p id="div_${status.index}" style="padding:5px;color:red;display:none">${item.content}</p>
				</td>
				<td align="center">${item.roleName}</td>
				<td align="center"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>	
			</tr>
		</c:forEach>   
		</tbody>
	</table>

	 <!-- 导入分页组件  -->
    <c:if test="${not empty litPager}">
     <c:import url="/WEB-INF/jsp/page/page.jsp">
     	<c:param name="pageActionUrl" value="news!listAll1.action"/>
     </c:import>
    </c:if>
    <c:if test="${empty litPager}">
    		<font color="red">查无资料 !!!</font>
    </c:if>

</div>
<div style="margin-left:100px;"><h3>最新砸金蛋中奖信息</h3></div>
<div id="scrollDiv" style="margin-left:100px;margin-top:0px;position:absolute;z-index:9999;display:none">
		
</div>
<script type="text/javascript">
$.ajax({
    url : "<%=request.getContextPath()%>/egg!indexWinPrizeList.action?"+ new Date(),
				type : "post",
				success : function(result) {
					$("#scrollDiv").show();
					$("#scrollDiv").html(result);
					$("#scrollDiv").Scroll({line:15,speed:1000,timer:3000});
				},
				error: function() {
		        	  alert("错误，请联系管理员！");
		           },
				beforeSend : function() {
					
				}
			});
</script>
</body>
</html>
