<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">
	<title>我的积分</title>
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/base.css" />
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/family.css" />
	<link href="<%=request.getContextPath()%>/css/user.css" rel="stylesheet" />
	<link href="<%=request.getContextPath()%>/css/base.s.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/css/my_v2.s.min.css" rel="stylesheet" />	
	<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
	<script src="<%=request.getContextPath()%>/js/user.js"></script>
    <link href="<%=request.getContextPath()%>/css/footer.css" rel="stylesheet" />
</head>


<body style="background-color: #EFEFEF;">
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
var pageNum=0;
var length=${length};
function insertcode(){
	
	pageNum++;
   	var date1 = $("#date1").val();
   	var date2 = $("#date2").val();
    /*加载内容*/
	$.ajax({
		type: "POST",
		url:"<%=request.getContextPath()%>/xinghuoquan/xinghuoquanAction!ajaxmyXingHuoQuanList.action",
		data:{
			"userId":'${user.userId }',
			"pageNum":pageNum,
			"date1":date1, 
			"date2":date2	
		},
		async: false,
	    error: function(request) {
	        alert("网络出现问题稍等再试!!!");
	    },
	    dataType:"json",
	    success: function(data) {
	    	if (data.success==true) {
	    		var children = data.children;
	    		var child = '';
	    		for(var i=0;i<children.length;i++){
	    			var time= new Date(children[i][1].time).Format("yyyy-MM-dd hh:mm:ss");
	    			child+=('<div class="my_order" >');
	    			child+=('<div class="item" onclick="zhankai('+children[i][0]+')">');
	    			child+=('<div class="item_t">');
	    			child+=('<em>'+time+'</em></div>');	    			
	    			child+=('<div class="item_m" id="item_m'+children[i][0]+'"><ul>');
	    			child+=('<li>星火券：<em>'+children[i][10]+'</em></li>');
	    			/* child+=('<li>来自ID：<em>'+children[i][2]+'</em></li>');
	    			child+=('<li>接收ID：<em>'+children[i][9]+'</em></li>'); */
	    			if(children[i][6]==1){
	    			child+=('<li>状态：<em>获得</em></li>');
	    		    }else if(children[i][6]==2){
	    		    	child+=('<li>状态：<em>支出</em></li>');
	    		    }else{
	    		    	child+=('<li>状态：<em>无</em></li>');
	    		    }
	    			/* child+=('<li>获得方式：<em>');
	    			if(children[i][7]==1){
	    				child+=('自己订单返利');
	    			}else if(children[i].type==2){
	    				child+=('他人订单返利');
	    			}else if(children[i].type==3){
	    				child+=('抽奖获得');
	    			}else if(children[i].type==4){
	    				child+=('购买兑换');
	    			}else if(children[i].type==5){
	    				child+=('新用户关注');
	    			}
	    			child+=('</em></li>'); */
	    			child+=('<li>来自订单：<em>'+children[i][5]+'</em></li>');
	    			/* child+=('<li>备注：<em>'+children[i][3]+'</em></li></ul></div></div></div>'); */
                    
	    		}
	    		var ul = $("#appendUl");
	    		ul.append(child);
	    		
	    	}else{
	    		length=data.length;
	    		alert("已经加载完毕!");
	    		
	    	}
	    }
	});
}
function zhankai(id){
	var attr = $("#item_m"+id);
	if(attr.css("display")==null||attr.css("display")=="none"){
		attr.css({display:"block"}); 
	} else{
		attr.css({display:"none"}); 
	}
}

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
		//documentElement 对应的是 html 标签，而 body 对应的是 body 标签。
		//document是整个文档对象，documentElement相当于该文档对象的root结点，即html标签。这样获取到的就是整个网页的高度
		//取得bodyHeight 高度（可见区域高度） document.documentElement.clientHeight
		//获取当前页面的滚动条纵坐标位置:document.documentElement.scrollTop
		//网页可见区域高： document.body.clientHeight
		var a = document.documentElement.clientHeight ;
		var b = document.documentElement.scrollTop==0? document.body.scrollTop : document.documentElement.scrollTop;
		var c = document.documentElement.scrollTop==0? document.body.scrollHeight : document.documentElement.scrollHeight;
		/*判断是否滑动到达底部**/
		if(a+b==c&&b!=0){
			//alert(a+" "+b+" "+c);
			if(length>0){
			insertcode();
		}             }
    });
});
</script>
	<!-- wrapper start -->
	
	<div class="wrapper" id="appendUl">
		<!-- 头部个人信息 start -->
		<%@ include file="/WEB-INF/jsp/order_head.jsp"%>
		<!-- 主要内容区域 -->
		<%-- <div class="con">
			<div class="money">
				<span class="sale"></span>
				<span class="mine"></span>
				
			</div>--%>
			<form method="post" action="href="xinghuoquanAction!myXingHuoQuanList.action?orderNo=0&requestType=mo & date1=document.getElementById('date1').value & date2=document.getElementById('date2').value">
            <div class="my_order_sear">
            <input type="date" class="input" id="date1" name="date1" value="${date1 }"><i>-</i><input type="date" class="input" id="date2" name="date2" value="${date2 }">
            <input type="submit" class="btn" value="搜索"></div>
			</form><br>
			<!-- 推荐 -->
			
			<!-- 等待收货 -->

			  <c:if test="${fn:length(myxinghuoquan) > 0}">
			
			<%-- <p class="tj"> 已付款订单  </p>--%>
			  </c:if>
			
			<c:forEach items="${myxinghuoquan}" var="myxinghuoquan" varStatus="status">
			<%-- <p class="tj">  ${ status.index + 1} 级会员  </p> --%>
        <div class="my_order" >
          <div class="item">
            <div class="item_t"><em>${myxinghuoquan[1] }</em></div>
            <div class="item_m">
                    <ul>
                    	<li>星火券：<em>${myxinghuoquan[10]}</em></li>
						<%-- <li>来自ID：<em>${myxinghuoquan[2]}</em></li>
						<li>接收ID：<em>${myxinghuoquan[9]}</em></li> --%>
						<li>星火券状态：
						 <em>
						  <c:if test="${myxinghuoquan[6]==1 }">获得</c:if>
						  <c:if test="${myxinghuoquan[6]==2 }">支出</c:if>
						  <c:if test="${myxinghuoquan[6]==3 }">流失</c:if>
						  <c:if test="${myxinghuoquan[6]==0 }">待支付</c:if>
						 </em>
						</li>
						<%--  <li>获得方式：
						 <em>
						  <c:if test="${myxinghuoquan[7]==1 }">自己订单返利</c:if>
						  <c:if test="${myxinghuoquan[7]==2 }">他人订单返利</c:if>
						  <c:if test="${myxinghuoquan[7]==3 }">抽奖获得</c:if>
						  <c:if test="${myxinghuoquan[7]==4 }">购买兑换</c:if>
						  <c:if test="${myxinghuoquan[7]==5 }">新用户关注</c:if>
						 </em>
					    </li> --%>
						<li>来自订单：<em>${myxinghuoquan[5] }</em></li>
						<%-- <li>备注：<em>${myxinghuoquan[3]}</em></li> --%>
						 	
											
					</ul>
				</div>
			</div>
			</div>
			</c:forEach>
			
		<%-- 	 <c:if test="${fn:length(myshrder) > 0}">
			
			<p class="tj"> 待确认收货订单  </p>
			</c:if>
			<!-- 确认收货 -->
			<c:forEach items="${myshrder}" var="item" varStatus="status">
			<form id='frmList' namespace="order"  action="orderAction!updateOrderStatus.action?orderId=${item[0] }"  method="post">
			<p class="tj">  ${ status.index + 1} 级会员  </p>
			<ul class="menu-list">
				<li><a href="javascript:;"><i class="arrows rotated"></i><em>${item[3]}</em></a>
					<ul class="sub-menu" style="height:300px">
						<li>订单编号：<em>${item[5]}</em></li>
						<li>订单时间：<em>${item[1]}</em></li>
						<li>支付状态：<em>已支付</em></li>
						<li>订单状态：<em><input type="button" onclick="javascript:qrshsubmit()" value="确认收货"></em></a></li>
						<li>订单金额：<em>${item[4]}元</em></li>
						<li>快递公司：<em><c:if test="${item[6] eq 0}">圆通</c:if><c:if test="${item[6] eq 1}">顺丰</c:if><c:if test="${item[6] eq 2}">申通</c:if><c:if test="${item[6] eq 3}">中通</c:if><c:if test="${item[6] eq 4}">韵达</c:if>
						<c:if test="${item[6] eq 5}">EMS</c:if><c:if test="${item[6] eq 6}">宅急送</c:if><c:if test="${item[6] eq 7}">全峰</c:if><c:if test="${item[6] eq 8}">天天快递</c:if><c:if test="${item[6] eq 9}">自提</c:if>
						</em></li>		
						<li>快递单号：<em>${item[2]}</em></li>				
					</ul>
				</li>
			</ul>
			</form>
			</c:forEach>
			
			<c:if test="${fn:length(myfcrder) > 0}">
			
			<p class="tj"> 完成订单  </p>
			</c:if>
			
			<!-- 完成订单 -->
			<c:forEach items="${myfcrder}" var="item" varStatus="status">
			<p class="tj">  ${ status.index + 1} 级会员  </p>
			<ul class="menu-list" >
				<li><a href="javascript:;"><i class="arrows rotated"></i><em>${item[3]}</em></a>
					<ul class="sub-menu" style="height:320px">
						<li>订单编号：<em>${item[5]}</em></li>
						<li>订单时间：<em>${item[1]}</em></li>
						<li>支付状态：<em>已付款</em></li>
						<li>订单状态：<em>完成</em></li>
						<li>订单金额：<em>${item[4]}元</em></li>
						<li>快递公司：<em><c:if test="${item[6] eq 0}">圆通</c:if><c:if test="${item[6] eq 1}">顺丰</c:if><c:if test="${item[6] eq 2}">申通</c:if><c:if test="${item[6] eq 3}">中通</c:if><c:if test="${item[6] eq 4}">韵达</c:if>
						<c:if test="${item[6] eq 5}">EMS</c:if><c:if test="${item[6] eq 6}">宅急送</c:if><c:if test="${item[6] eq 7}">全峰</c:if><c:if test="${item[6] eq 8}">天天快递</c:if><c:if test="${item[6] eq 9}">自提</c:if>
						</em></li>		
						<li>快递单号：<em>${item[2]}</em></li>					
					</ul>
				</li>
			</ul>
			
			</c:forEach>--%>
		</div>
	</div>
	<!-- footer start -->
	<%-- <footer class="footer">
		<div class="foot-nav">
			<a href="<%=request.getContextPath()%>/user/userAction!buy.action"><i class="foot-icon"><img src="../images/i_buy.png" alt=""></i><span>立即购买</span></a>
			<a href="<%=request.getContextPath()%>/order/orderAction!orderPerList.action?orderNo=0&requestType=mo" class="nowpage"><i class="foot-icon"><img src="../images/i_orders.png" alt=""></i><span>我的订单</span></a>
			<a href="<%=request.getContextPath()%>/user/userAction!phoneFamily.action" ><i class="foot-icon"><img src="../images/i_family.png" alt=""></i><span>家族成就</span></a>
			<a href="<%=request.getContextPath()%>/user/userAction!qrcodePage.action"><i class="foot-icon"><img src="../images/i_erweima.png" alt=""></i><span>我的二维码</span></a>
		</div><!-- /yingjun/src/main/webapp/images/i_buy.png -->
	</footer> --%>
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
	<script type="text/javascript">
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
		
	});
	
	
	
	/*  var $sub_menu = $(this).next(".sub-menu");
			 if($sub_menu.height() == 0){
				 var sub_h = $sub_menu.find("li").length * 46;
				 $sub_menu.animate({"height":sub_h});		//添加动画
					$(this).find(".arrows").addClass("rotated");
			 } */
		
		function qrshsubmit(){
			$("#frmList").submit();
			<%-- $.ajax({
				   url : "<%=request.getContextPath()%>/order/orderAction!updateOrderStatus.action",
		           type: "post", 
		           data : {"orderId":orderId},
		           success: function(result) {
		        	  /*  if(result!=null||result!=""){
						if(result.message=="success"){
							parent.showTip("删除成功！",1);
							reloadwin();
						}else{
							parent.showTip("删除失败！",0);
						}
		        	   }else{
		        		   parent.showTip("删除失败！",0);
		        	   } */
		           },
		           error: function() {
		        	   parent.showTip("系统错误，请联系管理员！",0);
		           },
		       		beforeSend : function() {
		           }
		        }); --%>
		}
		
		
		
		//执行支付
		function pay(appId, timeStamp, nonceStr, package1, signType, paySign) {
			WeixinJSBridge.invoke('getBrandWCPayRequest', {
			'appId' : appId, //公众号名称，由商户传入
			'timeStamp' : timeStamp, //时间戳，自 1970 年以来的秒数
			'nonceStr' : nonceStr, //随机串
			'package' : package1,
			'signType' : signType, //微信签名方式
			'paySign' : paySign
		//微信签名

		}, function(res) {
		$("#btn_submit").html("支付订单");
		$("#btn_submit").removeAttr('disabled');
		if(res.err_msg == "get_brand_wcpay_request:ok"){  
			 WeixinJSBridge.call('closeWindow'); 
		   }
			else if(res.err_msg == "get_brand_wcpay_request:cancel"){  
			   $("#btn_submit").html("支付订单");
				$("#btn_submit").removeAttr('disabled');
//		        alert("您取消了支付。");  
		   }
			else{  
			   $("#btn_submit").html("支付订单");
				$("#btn_submit").removeAttr('disabled');
//		       alert("支付失败!");  
		   }  
		// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg 将在用户支付成功后返回 ok，但并不保证它绝对可靠。
		});
		}
		function moneyPay(orderNo){
			///var bh=$("#span_bh").html();
			 $.ajax({
			        type:"POST",
			        url:"<%=request.getContextPath()%>/user/userAction!ajaxWxPay.action?m="+new Date(),
			        data : {"orderNo":orderNo},
			        success:function(data) {
			       	 $("#btn_submit").html("支付中");
			  			if(data!=null){
			  				if(data.message=="success") {
										pay(data.appId2,
												data.timeStamp,
												data.nonceStr2,
												data.packages,
												data.signType2,
												data.paySign2);
			  				}
			        }
				 },
				 beforeSend : function() {
					 $("#btn_submit").html("请求支付中");
					 $("#btn_submit").attr('disabled',"disabled");
					}
			 });

		}

		<%-- //关闭网页
		function closeWeb(){
		 WeixinJSBridge.call('closeWindow'); 
		}
		//返回业务中心
		function toServiceCenter(){
			window.location.href="<%=request.getContextPath()%>/order/orderAction!serviceCenter.action";
		} --%>
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
