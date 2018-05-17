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

<html lang="en">
<head>
<style type="text/css">
.mumber_list h5{
overflow: hidden;
}
</style>
<%ResourceBundle res = ResourceBundle.getBundle("system"); %> 
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- <meta content="width=device-width, initial-scale=1.0" name="viewport"/> -->
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta content="" name="description"/>
<meta content="" name="author"/>
	<title>推荐人信息</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/family.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttons.css">
	<link href="<%=request.getContextPath()%>/css/footer.css" rel="stylesheet" />
	<link href="http://cdn.bootcss.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/css/style-metro.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/source/jquery.fancybox.css?v=2.1.5" media="screen" />
	<link href="<%=request.getContextPath()%>/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet">
	<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js" type="text/javascript"></script>
</head>
<body>
<script type="text/javascript">
</script>
	<!-- wrapper start -->
	<div class="wrapper">
		<!-- 头部个人信息 start -->
		<%@ include file="/WEB-INF/jsp/order_head.jsp"%>
		<!-- 主要内容区域 -->
		<div class="con">
			<%-- <div class="money">
				<form action="<%=request.getContextPath()%>/user/userAction!viewPeoPage.action">
				<input type="hidden"  name="viewLevel" value="${viewLevel }">
				<span class="sale">ID:<input id="userId" style="width: 60px;color: blue;" type="text" name="userId" onchange="shuzi()"></span>
				<span class="mine"><input type="submit" class="button-primary"  value="搜寻会员"></span>
				</form>
			</div>
			<div class="mumber">
				<p>
					<c:if test="${viewLevel==1}">超级粉丝团会员</c:if>
					<c:if test="${viewLevel==2}">铁杆粉丝团会员</c:if>
					<c:if test="${viewLevel==3}">忠实粉丝团会员</c:if>
			</div> --%>
			
			<ul class="mumber_list" id="appendUl">
					<li>
					<span><img src="${p1.headUrl}" alt=""></span>
					<h2>银卡推荐人</h2>
					<h2>${p1.userName}</h2>
					<h5>会员编号：${p1.userId} </h5>
					<h5><span>头衔：</span><i><c:if test="${p1.level == 0|| p1.level == null}">星火种子</c:if>
						<c:if test="${p1.level == 1}"><img style="width: 15px" src="../images/s1.png"/></c:if>
						<c:if test="${p1.level == 2}"><img style="width: 15px" src="../images/s2.png"/></c:if>
						<c:if test="${p1.level == 3}"><img style="width: 25px" src="../images/shenqing/4.png"/></c:if></i></h5>
					<h5>店铺等级：<i><c:if test="${p1.cardId== null||p1.cardId == 0}">未开店</c:if>
		            	<c:if test="${p1.cardId== 1}">黄金店</c:if>
						<c:if test="${p1.cardId== 2}">黑钻店</c:if>
						<c:if test="${p1.cardId== 3}">蓝钻店</c:if>
						<c:if test="${p1.cardId== 4}">黄钻店</c:if>
						<c:if test="${p1.cardId== 5}">绿钻店</c:if>
						<c:if test="${p1.cardId== 6}">红钻店</c:if>
						<c:if test="${p1.cardId== 7}">皇冠店</c:if>
						<c:if test="${p1.cardId== 8}">旗舰店</c:if>
						<c:if test="${p1.cardId== 9}">星火总店</c:if></i>
					</h5>	
					<h5>关注时间：<i>${p1.shappDate}</i></h5>
					<%-- <h5>手机号码：<i>${orderAddress1.mobile}</i></h5> --%>
					<h5>微信号码：<i>${orderAddress1.zipcode}</i></h5>
     				
     				<c:if test="${p1.level==null || p1.level==0}">
     					 <font color="red">尚未购买</font>
     				</c:if>
     				<c:if test="${p1.level!=null && p1.level!=0}">
     					 <a class="button button-pill button-primary"  href="#" onclick="searchOrder(${p1.userId},${viewLevel})">查看订单</a>
     				</c:if>
     				
     				<span style="float:right">
     						<a id="liuyan" class="button button-pill button-primary"  href="#inline" onclick="viewDialog('${p1.unionid}');">留言</a>
     				</span>
     				</li>
     				<c:if test="${p1.userId!=2391191}">
     				<li>
					<span><img src="${p2.headUrl}" alt=""></span>
					<h2>金卡推荐人</h2>
					<h2>${p2.userName}</h2>
					<h5>会员编号：${p2.userId} </h5>
					<h5><span>头衔：</span><i><c:if test="${p2.level == 0|| p2.level == null}">星火种子</c:if>
						<c:if test="${p2.level == 1}"><img style="width: 15px" src="../images/s1.png"/></c:if>
						<c:if test="${p2.level == 2}"><img style="width: 15px" src="../images/s2.png"/></c:if>
						<c:if test="${p2.level == 3}"><img style="width: 25px" src="../images/shenqing/4.png"/></c:if></i></h5>
					<h5>店铺等级：<i><c:if test="${p2.cardId== null||p2.cardId == 0}">未开店</c:if>
		            	<c:if test="${p2.cardId== 1}">黄金店</c:if>
						<c:if test="${p2.cardId== 2}">黑钻店</c:if>
						<c:if test="${p2.cardId== 3}">蓝钻店</c:if>
						<c:if test="${p2.cardId== 4}">黄钻店</c:if>
						<c:if test="${p2.cardId== 5}">绿钻店</c:if>
						<c:if test="${p2.cardId== 6}">红钻店</c:if>
						<c:if test="${p2.cardId== 7}">皇冠店</c:if>
						<c:if test="${p2.cardId== 8}">旗舰店</c:if>
						<c:if test="${p2.cardId== 9}">星火总店</c:if></i>
					</h5>	
					<h5>关注时间：<i>${p2.shappDate}</i></h5>
     				<%-- <h5>手机号码：<i>${orderAddress2.mobile}</i></h5> --%>
					<h5>微信号码：<i>${orderAddress2.zipcode}</i></h5>
     				<c:if test="${p2.level==null || p2.level==0}">
     					 <font color="red">尚未购买</font>
     				</c:if>
     				<c:if test="${p2.level!=null && p2.level!=0}">
     					 <a class="button button-pill button-primary"  href="#" onclick="searchOrder(${p2.userId},${viewLevel})">查看订单</a>
     				</c:if>
     				
     				<span style="float:right">
     						<a id="liuyan" class="button button-pill button-primary"  href="#inline" onclick="viewDialog('${p2.unionid}');">留言</a>
     				</span>
     				</li>
     				</c:if>
     				<c:if test="${p1.userId!=2391191&&p2.userId!=2391191}">
     				<li>
					<span><img src="${p3.headUrl}" alt=""></span>
					<h2>钻石卡推荐人</h2>
					<h2>${p3.userName}</h2>
					<h5>会员编号：${p3.userId} </h5>
					<h5><span>头衔：</span><i><c:if test="${p3.level == 0|| p3.level == null}">星火种子</c:if>
						<c:if test="${p3.level == 1}"><img style="width: 15px" src="../images/s1.png"/></c:if>
						<c:if test="${p3.level == 2}"><img style="width: 15px" src="../images/s2.png"/></c:if>
						<c:if test="${p3.level == 3}"><img style="width: 25px" src="../images/shenqing/4.png"/></c:if></i></h5>
					<h5>店铺等级：<i><c:if test="${p3.cardId== null||p3.cardId == 0}">未开店</c:if>
		            	<c:if test="${p3.cardId== 1}">黄金店</c:if>
						<c:if test="${p3.cardId== 2}">黑钻店</c:if>
						<c:if test="${p3.cardId== 3}">蓝钻店</c:if>
						<c:if test="${p3.cardId== 4}">黄钻店</c:if>
						<c:if test="${p3.cardId== 5}">绿钻店</c:if>
						<c:if test="${p3.cardId== 6}">红钻店</c:if>
						<c:if test="${p3.cardId== 7}">皇冠店</c:if>
						<c:if test="${p3.cardId== 8}">旗舰店</c:if>
						<c:if test="${p3.cardId== 9}">星火总店</c:if></i>
					</h5>	
					<h5>关注时间：<i>${p3.shappDate}</i></h5>
     				<%-- <h5>手机号码：<i>${orderAddress3.mobile}</i></h5> --%>
					<h5>微信号码：<i>${orderAddress3.zipcode}</i></h5>
     				<c:if test="${p3.level==null || p3.level==0}">
     					 <font color="red">尚未购买</font>
     				</c:if>
     				<c:if test="${p3.level!=null && p3.level!=0}">
     					 <a class="button button-pill button-primary"  href="#" onclick="searchOrder(${p3.userId},${viewLevel})">查看订单</a>
     				</c:if>
     				
     				<span style="float:right">
     						<a id="liuyan" class="button button-pill button-primary"  href="#inline" onclick="viewDialog('${p3.unionid}');">留言</a>
     				</span>
     				</li>
				</c:if>
					
			</ul>
			
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
	function insertcode() {
		pageNum++;
        /*加载内容*/
		$.ajax({
			type: "POST",
			url:"<%=request.getContextPath()%>/user/userAction!ajaxListUserLevel.action",
			data:{
				"userId":'${userNiId }',
				"pageNum":pageNum,
				"viewLevel":'${viewLevel}'
				
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
		    			var time= new Date(children[i].shappDate.time).Format("yyyy-MM-dd hh:mm:ss");
		    			
		    			
		    			child+=('<li>');
		    			child+=('<span><img src="'+children[i].headUrl+'" alt=""></span>');
		    			child+=('<h2>'+children[i].userName+'</h2>');
		    			child+=('<h5>会员编号：'+children[i].userId+' </h5>');
		    			child+=('<h5>关注时间：<i>'+time+'.0</i></h5>');
		    			child+=('<h5>头衔：<i>'+children[i].level+'</i></h5>');
		    			if(children[i].level!=null&&children[i].level!=0&&children[i].level!="null"){
		    				child+=('<a class="button button-pill button-primary"  href="#" onclick="searchOrder('+children[i].userId+','+children[i].level+')">查看订单</a>');
		    			}else{
		    				child+=('<font color="red">尚未购买</font>');
		    			}
		    			child+=('<span style="float:right">');
		    			child+=('<a id="liuyan" class="button button-pill button-primary"  href="#inline" onclick="viewDialog(\''+children[i].unionid+'\');">留言</a>');
		    			child+=('</span>');
		    		}
		    		var ul = $("#appendUl");
		    		ul.append(child);
		    		
		    	}else{
		    		alert("已经加载完毕!");
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