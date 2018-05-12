<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.ResourceBundle"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!-- 
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.2
Version: 3.7.0
Author: KeenThemes
Website: http://www.keenthemes.com/
Contact: support@keenthemes.com
Follow: www.twitter.com/keenthemes
Like: www.facebook.com/keenthemes
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
License: You must have a valid license purchased only from themeforest(the above link) in order to legally use the theme for your project.
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->

<html lang="zh-CN">
<head>
<%ResourceBundle res = ResourceBundle.getBundle("system"); %> 
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- <meta content="width=device-width, initial-scale=1.0" name="viewport"/> -->
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta content="" name="description"/>
<meta content="" name="author"/>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="x-dns-prefetch-control" content="on">   
    <meta http-equiv="x-dns-prefetch-control" content="on">
	<title>星团成员</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/user.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/family.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttons.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.s.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/my_v2.s.min.css">	
	<link href="<%=request.getContextPath()%>/css/footer.css" rel="stylesheet" />
	<link href="http://cdn.bootcss.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/css/style-metro.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/source/jquery.fancybox.css?v=2.1.5" media="screen" />
	<link href="<%=request.getContextPath()%>/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet">
	<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js" type="text/javascript"></script>
</head>

<body style="background-color: #efefef">

<script type="text/javascript">
$(document).ready(function () {
	/* alert("document.documentElement.scrollHeight:"+document.documentElement.scrollTop+
	"\ndocument.body.clientHeight:"+document.body.clientHeight+
	"\ndocument.documentElement.clientHeight:"+document.documentElement.clientHeight+
	"\ndocument.body.scrollTop:"+document.body.scrollTop+
	"\ndocument.documentElement.scrollTop:"+document.documentElement.scrollTop+
	"\ndocument.body.scrollHeight:"+document.body.scrollHeight+
	"\ndocument.documentElement.scrollHeight:"+document.documentElement.scrollHeight); */
    $(window).scroll(function () {
    	
		/*获取当前窗口的一些内容**/
		var a = document.documentElement.clientHeight ;
		var b = document.documentElement.scrollTop==0? document.body.scrollTop : document.documentElement.scrollTop;
		var c = document.documentElement.scrollTop==0? document.body.scrollHeight : document.documentElement.scrollHeight;
		var s = document.getElementById("userids").value;
		/*判断是否滑动到达底部**/
		if(a+b==c&&b!=0&&s==0){
			//alert(s);
			//alert(a+" "+b+" "+c);
			insertcode();
		}             
    });
});
</script>
	<!-- wrapper start -->
	<div>
		<!-- 头部个人信息 start -->
		<%@ include file="/WEB-INF/jsp/order_head.jsp"%>
		<!-- 主要内容区域 -->
		<div >
			<div class="my_member_sear">
				<form action="<%=request.getContextPath()%>/user/userAction!viewPeoPage.action">
				<input type="hidden"  name="viewLevel" value="${viewLevel }">
				<input id="userids" type="hidden"  value="${userIdS}">
				<input id="userId" class="input" placeholder="ID" type="text" name="userId" onchange="shuzi()">
				<input type="submit" class="btn"  value="搜寻会员">
				
				</form>
			</div>	
			<div class="my_member" id="appendUl">
			<c:forEach items="${userList}" var="item" varStatus="status">
				<div class="item" >
					
					<div class="item_t">
					<img src="${item.user.headUrl}" class="img">
					<P>${item.user.userName}</P>
					<P>会员编号：${item.user.userId}</P>
					<P>头衔：<c:if test="${item.user.level == 0|| item.user.level == null}">星火种子</c:if>
						<c:if test="${item.user.level == 1}"><img style="width: 15px" src="../images/s1.png"/></c:if>
						<c:if test="${item.user.level == 2}"><img style="width: 15px" src="../images/s2.png"/></c:if>
						<c:if test="${item.user.level == 3}"><img style="width: 25px" src="../images/shenqing/4.png"/></c:if></P>
					</div>
					<div class="item_m">
					<P>关注时间：${item.user.appDate}</P>
					<P>星级：<c:if test="${viewLevel==1}">超级粉丝</c:if>
					<c:if test="${viewLevel==2}">铁杆粉丝</c:if>
					<c:if test="${viewLevel==3}">忠实粉丝</c:if></P>
					<P>店铺等级：<c:if test="${p2.cardId== null||p2.cardId == 0}">未开店</c:if>
		            	<c:if test="${p2.cardId== 1}">黄金店</c:if>
						<c:if test="${p2.cardId== 2}">黑钻店</c:if>
						<c:if test="${p2.cardId== 3}">蓝钻店</c:if>
						<c:if test="${p2.cardId== 4}">黄钻店</c:if>
						<c:if test="${p2.cardId== 5}">绿钻店</c:if>
						<c:if test="${p2.cardId== 6}">红钻店</c:if>
						<c:if test="${p2.cardId== 7}">皇冠店</c:if>
						<c:if test="${p2.cardId== 8}">旗舰店</c:if>
						<c:if test="${p2.cardId== 9}">星火总店</c:if>     
						</P>
     				<%-- <h5>手机号：<i>${item.address.mobile}</i></h5> --%>
     				<p>微信号：${item.address.zipcode}</p>
     				</div>
     				<div class="item_f">
     				<ul>
     				<li>
     				<c:if test="${item.user.level==null || item.user.level==0}">
     					 <p><font color="red">尚未购买</font></p>
     				</c:if>
     				</li>
     				<li>
     				<c:if test="${item.user.level!=null && item.user.level!=0}">
     					 <a   href="#" class="btn btn1" onclick="searchOrder(${item.user.userId},${viewLevel})">查看订单</a>
     				</c:if>
     				</li>     				
     				<li><a href="#" class="btn btn3" style="display: none">查看</a></li>
     				<li>
     						<a id="liuyan" href="#inline" class="btn btn3" onclick="viewDialog('${item.user.unionid}');">留言</a>
     				</li>
     				</ul>
     				</div>
    			
				</div>
				</c:forEach>	
			</div>
			
		</div>
	</div>
	
	 <div class="panel panel-primary" id="inline" style="display: none;">
      <div class="panel-heading">
        <h3 class="panel-title">留言</h3>
      </div>
      <div class="panel-body">
      	<div>
      		<div class="form-group">
      			留言内容:<textarea class="form-control" rows="8" id="textMessage"></textarea>
        	</div>
      	</div>
      	<div>
      		<input type="hidden" value="" id="wxOpenid" />
      		<a class="button button-pill button-primary" href="#" role="button" onclick="sendMessage();">发送</a>
        	<a class="button button-pill button-primary" href="#" role="button" onclick="closefancybox();">关闭</a>
      	</div>
        
      </div>
    </div>
	<br>
	<br>
	<br>
	<!-- footer start -->
	<%-- <footer class="footer">
		<div class="foot-nav">
			<a href="<%=request.getContextPath()%>/user/userAction!buy.action"><i class="foot-icon"><img src="../images/i_buy.png" alt=""></i><span>立即购买</span></a>
			<a href="<%=request.getContextPath()%>/order/orderAction!orderPerList.action?orderNo=0&requestType=mo"><i class="foot-icon"><img src="../images/i_orders.png" alt=""></i><span>我的订单</span></a>
			<a href="<%=request.getContextPath()%>/user/userAction!phoneFamily.action" class="nowpage"><i class="foot-icon"><img src="../images/i_family.png" alt=""></i><span>家族成就</span></a>
			<a href="<%=request.getContextPath()%>/user/userAction!qrcodePage.action"><i class="foot-icon"><img src="../images/i_erweima.png" alt=""></i><span>我的二维码</span></a>
		</div><!-- /yingjun/src/main/webapp/images/i_buy.png -->
		
	</footer>  --%>
<div id="footer">
  <div class="foot_nav">
    <div class="foot_nav_inner">
    <a href="<%=request.getContextPath()%>/user/userAction!phoneFamily.action"><span class="line"><i class="nav_f1"></i></span><span class="text">我的红包</span></a>
    <a href="<%=request.getContextPath()%>/user/userAction!jampshopIndex.action"><span class="line"><i class="nav_f2"></i></span><span class="text">商城首页</span></a>
    <a href="<%=request.getContextPath()%>/user/userAction!qrcodePage.action"><span class="line"><i class="nav_f3"></i></span><span class="text">二维码</span></a>
    <a href="javascript:zanwu();"><span class="line"><i class="nav_f4"></i></span><span class="text">购物车</span></a>
    <a href="<%=request.getContextPath()%>/user/userAction!gotoPersonalCenter.action" class="hover"><span class="line"><i class="nav_f5"></i></span><span class="text">个人中心</span></a>
    </div>
  </div>
</div>

		
	<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.10.2.min.js"></script> --%>
	<script src="<%=request.getContextPath()%>/bootstrap-3.3.5/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/css/source/jquery.fancybox.pack.js?v=2.1.5"></script>
	<script type="text/javascript">
	Date.prototype.Format = function (fmt) { //author: meizz 
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
	}
	
	function shuzi() {
		var reg = new RegExp("^[0-9]*$");  
	    if(!reg.test($("#userId").val())){  
	        alert("请输入数字!");  
	        $("#userId").val("");
	    }  
	    /* if(!/^[0-9]*$/.test(obj.value)){  
	        alert("请输入数字!");  
	    }   */
	}
	
	
	var pageNum = 1;
	var viewLevel=1;
	function insertcode() {
		pageNum++;
		//var viewLevel='${viewLevel}';
        /*加载内容*/
		$.ajax({
			type: "POST",
			url:"<%=request.getContextPath()%>/user/userAction!ajaxListUserLevel.action",
			data:{
				"userId":'${user.userId }',
				"pageNum":pageNum,
				"viewLevel":viewLevel
				
			},
			async: false,
		    error: function(request) {
		        alert("网络出现问题稍等再试!!!");
		    },
		    dataType:"json",
		    success: function(data) {
		    	//var aa = eval(data);
		    	
		    	if (data.success==true) {
		    		var children = data.children;
		    		var child = '';
		    		for(var i=0;i<children.length;i++){
		    			var time= new Date(children[i].user.appDate.time).Format("yyyy-MM-dd hh:mm:ss");		    			
		    			child+=('<div class="item">');
		    			child+=('<div class="item_t">');
		    			child+=('<img src="'+children[i].user.headUrl+'" alt="" class="img">');
		    			child+=('<p>'+children[i].user.userName+'</p>');
		    			child+=('<p>会员编号：'+children[i].user.userId+' </p>');
		    			if(children[i].user.level==0||children[i].user.level==null||
		    					children[i].user.level==""||children[i].user.level=="null"){
		    				child+=('<p>头衔：星火种子</p>');
		    			}else if(children[i].user.level==1){
		    				child+=('<p>头衔：<img style="width: 15px" src="../images/s1.png"/></p>');
		    			}else if(children[i].user.level==2){
		    				child+=('<p>头衔：<img style="width: 15px" src="../images/s2.png"/></p>');
		    			}else if(children[i].user.level==3){
		    				child+=('<p>头衔：<img style="width: 25px" src="../images/shenqing/4.png"/></p>');
		    			}
		    			child+=('</div><div class="item_m">');
		    			child+=('<p>关注时间'+time+'</p>');
		    			child+=('<p>星级：');
		    			if(viewLevel==1){
		    				child+=('超级粉丝');
		    			}else if(viewLevel==2){
		    				child+=('铁杆粉丝');
		    			}else if(viewLevel==3){
		    				child+=('忠实粉丝');
		    			}
		    			child+=('</p>');
		    			child+=('<p>店铺等级：');
		    			if(children[i].user.cardId==0||children[i].user.cardId==null||
		    					children[i].user.cardId==""||children[i].user.cardId=="null"){
		    				child+=('未开店');
		    			}else if(children[i].user.cardId==1){
		    				child+=('黄金店');
		    			}else if(children[i].user.cardId==2){
		    				child+=('黑钻店');
		    			}else if(children[i].user.cardId==3){
		    				child+=('蓝钻店');
		    			}else if(children[i].user.cardId==4){
		    				child+=('黄钻店');
		    			}else if(children[i].user.cardId==5){
		    				child+=('绿钻店');
		    			}else if(children[i].user.cardId==6){
		    				child+=('红钻店');
		    			}else if(children[i].user.cardId==7){
		    				child+=('皇冠店');
		    			}else if(children[i].user.cardId==8){
		    				child+=('旗舰店');
		    			}else if(children[i].user.cardId==9){
		    				child+=('星火总店');
		    			}
		    			child+=('</p>');
		    					    			
		    			if(children[i].address!=null&&children[i].address!=""&&children[i].address!="null"){
		    				/* child+=('<h5>手机号：<i>'+children[i].address.mobile+'</i></h5>'); */
			    			child+=('<p>微信号：'+children[i].address.zipcode+'</p>');
		    			}
		    			child+=('</div><div class="item_f"><ul><li>');
		    			if(children[i].user.level!=null&&children[i].user.level!=0&&children[i].user.level!="null"){
		    				child+=('<a class="btn btn1"  href="#" onclick="searchOrder('+children[i].user.userId+',${viewLevel})">查看订单</a>');
		    				child+=('</li>');
		    			}else{
		    				child+=('<li>');
		    				child+=('<p><font color="red">尚未购买</font></p>');
		    				child+=('</li>');
		    			}
		    			
		    			child+=('<li><a id="liuyan" class="btn btn3"  href="#inline" onclick="viewDialog(\''+children[i].user.unionid+'\');">留言</a></li>');
		    			child+=('</ul></div></div>');
		    		}
		    		var ul = $("#appendUl");
		    		ul.append(child);
		    		
		    	}else{
		    		viewLevel++;
		    		pageNum=0;
		    		if(viewLevel == 4)alert("已经加载完毕!");
		    		insertcode();
		    	}
		    }
		});
		
    }
    
	
	
	function go1 () {
		var url = arguments[0];
		$.fancybox.open('http://101.201.172.238/'+url);
	}
	
	function closefancybox() {
		jQuery.fancybox.close();
		$('#textMessage').val("");
	}
	
	function viewDialog() {
		$("#wxOpenid").val(arguments[0]);
		 $("#liuyan").fancybox({
		        'hideOnContentClick': true
		 });
	}
	
	function sendMessage() {
		var textMessage = $('#textMessage').val();
		var wxOpenid = $("#wxOpenid").val();;
		if (textMessage=="") {
			alert("留言内容不能为空");
			return false;
		}
		var url ='<%=request.getContextPath()%>/user/userAction!sendMessage.action';
		$.ajax({
			cache: true,
			type: "POST",
			url:url,
			data:"wxOpenid="+wxOpenid+"&textMessage="+textMessage,// 你的formid
			async: false,
		    error: function(request) {
		        alert("网络出现问题稍等再试!!!");
		    },
		    success: function(data) {
		    	if (data.success) {
		    		alert("留言成功");
		    		$('#textMessage').val("");
		    		jQuery.fancybox.close();
		    		
		    	}
		    }
		});
	}
	
	function fahongbao() {
		var userId = arguments[0];
		var level = ${viewLevel};
		var amount = arguments[1];
		var flagCount = arguments[2];
		//var bId = arguments[3];
		document.getElementById(userId).disabled = true;
		
		/* var activity = 0;
		switch (level) {
			case 1:activity = '${user.activiti1}';break;
			case 2:activity = '${user.activiti2}';break;
			case 3:activity = '${user.activiti3}';break;
		}
		if (null==activity||""==activity || activity==0) {
			message = "您还没有被您的第【 "+level+"级供应商激活】，请联系他为您激活";
			return false;
		} */
		
		var url ='<%=request.getContextPath()%>/user/userAction!fahongbao.action';
		$.ajax({
			cache: true,
			type: "POST",
			url:url,
			data:"userId2="+userId+"&level="+level+"&amount="+amount+"&flagCount="+flagCount,// 你的formid
			async: false,
		    error: function(request) {
		        alert("Connection error111");
		    },
		    success: function(data) {
		    	if (data.success) {
		    		alert(data.message);
		    		if (data.message=="发放成功") {
		    			self.location.href="<%=request.getContextPath()%>/user/userAction!viewPeoPage.action?viewLevel="+level;
		    		}
		    	}
		    }
		});
	}
	
	function searchInfo() {
		var title=$.trim($("#search_text").val());
		
		$("#searchValue").val(title);
		$("#frmList").submit();
	}
	
	function searchOrder(userId,level){
		$.ajax({
			type: "POST",
			url:"<%=request.getContextPath()%>/order/orderAction!searchOrder.action",
			data:{
				"userId":userId,
				"levelValue":level
			},
			dataType:"json",
		    error: function(request) {
		        alert("Connection error111");
		    },
		    success: function(data) {
		    	if (data.success) {
					alert("订单编号:"+data.ordersBH+"\n下单时间:"+data.createDate);		    		
		    	}else{
		    		alert("无订单");
		    	}
		    }
		});
	}
	
	
	
	function onBridgeReady() {
		WeixinJSBridge.call('hideOptionMenu');
	}

	if (typeof WeixinJSBridge == "undefined") {
		if (document.addEventListener) {
			document.addEventListener('WeixinJSBridgeReady', onBridgeReady,
					false);
		} else if (document.attachEvent) {
			document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
			document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
		}
	} else {
		onBridgeReady();
	}
</script>
	
</body>
</html>