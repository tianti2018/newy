<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<style type="text/css">
.div-xinxi li input{
	float: left;
	margin: 0;
	padding: 0;
	width: 65%;
	padding-left: 10px;
}
.dizhi span{
	float: left;
	width: 80px;
	line-height: 40px;
}
.ui-loader{
	display: none;
}
	</style>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>购买页面</title>
    <script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
    <%-- <script src="<%=request.getContextPath()%>/js/jquery.mobile-1.4.5.min.js"></script> --%>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bbt.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/mui.min.css"/>
   
</head>
<body>
<div class="banner_box">
	<div class="banner">
		<div class="banner">
				<a href="#" onclick="dianji(1)" ontouchmove="shows(1)">
					<img src="<%=request.getContextPath()%>/images/zengli1/螺旋藻胶囊图片.jpg" style="width: 100%">
				</a>
				<a href="#" onclick="dianji(2)" ontouchmove="shows(2)">
					<img src="<%=request.getContextPath()%>/images/zengli2/螺旋藻蛋白粉图片.jpg" style="width: 100%">
				</a>
				<a href="#" onclick="dianji(3)" ontouchmove="shows(3)">
					<img src="<%=request.getContextPath()%>/images/zengli3/黄金组合图片.jpg" style="width: 100%">
				</a>
			</div>
	</div>
</div>
	<div class="div-xinxi">
		<ul>
			<li>欢迎：
				<c:if test="${user.cardId==null||user.cardId==0 }">您好,您还未开店!</c:if>
				<c:if test="${user.cardId==1 }">黄金店主</c:if>
				<c:if test="${user.cardId==2 }">黑钻店主</c:if>
				<c:if test="${user.cardId==3 }">蓝钻店主</c:if>
				<c:if test="${user.cardId==4 }">黄钻店主</c:if>
				<c:if test="${user.cardId==5 }">绿钻店主</c:if>
				<c:if test="${user.cardId==6 }">红钻店主</c:if>
				<c:if test="${user.cardId==7 }">皇冠店主</c:if>
				<c:if test="${user.cardId==8 }">旗舰店主</c:if>
				<c:if test="${user.cardId==9 }">星火总店主</c:if>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="<%=request.getContextPath()%>/pay/payGoodAction!toGoodBuy.action" class="mui-btn mui-btn-primary" >去购买产品</a>
			</li>
			<div style="border-bottom: 1px solid #DADADA;" class="">
				<li style="border: none;">
					<p class="dengji" style="padding: 6px 0;">权限：</p>
					<button onclick="clickDian(1)" class="mui-btn mui-btn-primary but-1">黄金店</button>
					<button onclick="clickDian(2)" class="mui-btn mui-btn-primary but-2">黑钻店</button>
					<button onclick="clickDian(3)" class="mui-btn mui-btn-primary but-3">蓝钻店</button>
					<p class="dengji" style="padding: 6px 0;clear: left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
					<button onclick="clickDian(4)" class="mui-btn mui-btn-primary but-4">黄钻店</button>
					<button onclick="clickDian(5)" class="mui-btn mui-btn-primary but-5">绿钻店</button>
					<button onclick="clickDian(6)" class="mui-btn mui-btn-primary but-6">红钻店</button>
					<p class="dengji" style="padding: 6px 0;clear: left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
					<button onclick="clickDian(7)" class="mui-btn mui-btn-primary but-7">皇冠店</button>
					<button onclick="clickDian(8)" class="mui-btn mui-btn-primary but-8">旗舰店</button>
					<button onclick="clickDian(9)" class="mui-btn mui-btn-primary but-9">星火总店</button>
				</li>
			</div>
			<input type="hidden" id="xx-shu" name="levelValue" class="xx-shu" value="0">
			<%-- <li>数量：<span id="xx-shu" class="xx-shu">0</span></li> --%>
			<li class="dizhi"><span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</span><input id="xingming" type="text" placeholder="姓名" value="${orderAddress.userName }"></li>
			<li class="dizhi"><span>微&nbsp;信&nbsp;号&nbsp;：</span><input id="wxhao" type="text" placeholder="微信号" value="${orderAddress.zipcode }"></li>
			<li class="dizhi"><span>手&nbsp;机&nbsp;号&nbsp;：</span><input id="phone" type="text" placeholder="手机号" value="${orderAddress.mobile }"></li>
			<li class="dizhi"><span>详细地址：</span><input id="dizhi" type="text" placeholder="详细地址" value="${orderAddress.address }"></li>
			<li><input type="checkbox" id="tongyixieyi" onclick="tongyi()" style="width:50px;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/xieyi.html" style="text-decoration: underline;">同意《山人物语协议》</a></li>
			<input id="orderAddRessId" type="hidden" value="${orderAddress.id }"/>
		</ul>
	</div>
	<script type="text/javascript">
	jQuery(document).ready(function(){
		$("#tongyixieyi").attr('checked',"checked");
	});
	function tongyi(){
		if(document.getElementById('tongyixieyi').checked==true){
			$("#btn_submit").removeAttr('disabled');
		}else{
			$("#btn_submit").attr('disabled',"disabled");
		}
	}
	
	function dianji(a){///newy/src/main/webappproduct1.jsp
		window.location.href="<%=request.getContextPath()%>/product"+a+".jsp";
	}
	$(".banner img").hide();
	$(".banner img:first").show();
	var n = 0;
	var xx = 0;

	function showImg() {
		if (n < $(".banner img").length - 1) {
			n = n + 1;
		} else {
			n = 0;
		}
		//alert(n);
		$(".banner img").hide();
		$(".banner img").eq(n).fadeIn(1000);
		//$(".banner img").eq(n).show();			
	}
	var interval = setInterval(showImg, 4000);
	function shows(num) {
		if (xx != num) {
			showImg();
			xx = num;
			clearInterval(interval);
			interval = setInterval(showImg, 4000);
		}
	}
	var jiages = [100,200,300,300,200,100,100,200,300];
	function clickDian(j){
		var level = '${user.cardId}';
		if(level==""||level==null){
			level = 0;
		}
		var jiage = 0;
		for(var i=level;i<j;i++){
			jiage+=jiages[i];
		}
		$("#xx-shu").val(j)
		$("#jiage").text(jiage);
		for(var i=1;i<=9;i++){
			if(i==j){
				$(".but-"+i).css({backgroundColor:"#f43853",border:"1px solid #f43853"});
				$(".but-"+i).addClass("mui-icon mui-icon-checkmarkempty");
			}else if(i<j){
				$(".but-"+i).css({backgroundColor:"#f43853",border:"1px solid #f43853"});
				$(".but-"+i).addClass("mui-icon mui-icon-checkmarkempty");
			}else{
				$(".but-"+i).css({backgroundColor:"#007aff",border:"1px solid #007aff"});
				$(".but-"+i).removeClass("mui-icon mui-icon-closeempty");
			}
		}
	}
		function goumai() {
			var str ='2016-03-11 02:00:00';
			str = str.replace(/-/g,"/");
			var end = '2016-03-11 04:30:00';
			end = end.replace(/-/g,"/");
			var date = new Date(str);
			var endDate = new Date(end);
			if(date < new Date() && new Date() < endDate){
				alert("微信系统升级维护中,请"+end+"以后再次操作!");
				return false;
			}
			if('${user.level}'!=3){
				alert("你还不是全国(钻卡),无法成为店主!");
				return false;
			}
			/* alert("系统维护中！"); */
			var xingming = $("#xingming").val();
			var jiage=$("#jiage").text();
			var addressId = $("#orderAddRessId").val();
			var dizhi =$("#dizhi").val();
			var phone =$("#phone").val();
			var wxhao =$("#wxhao").val();
			var level = parseInt($("#xx-shu").val());;
			if(xingming==null||xingming==""){
				alert("请填写姓名!");
				return;
			}
			if(wxhao==null||wxhao==""){
				alert("请填写微信号!");
				return;
			}
			if(phone==null||phone==""){
				alert("请填写联系电话!");
				return;
			}
			if(dizhi==null||dizhi==""){
				alert("请填写地址!");
				return;
			}
			
			if(jiage!=""&&jiage!=0){
			$.ajax({
		        type:"POST",
		        url:"<%=request.getContextPath()%>/pay/payDianAction!saveOrderDian.action",
		        data : {
		        	"total_price1":jiage,
		        	"levelValue":level,
		        	"orderAddress.id":addressId,
		        	"orderAddress.userName":xingming,
		        	"orderAddress.address":dizhi,
		        	"orderAddress.mobile":phone,
		        	"orderAddress.zipcode":wxhao,
		        	},
		        dataType:"json",
		        success:function(data) {
		       	 	if(data.success){
		       	 		moneyPay(data.ordersBh);
		       	 	//alert(data.ordersBh);
		       	 	}else{
		       	 		if(data.timeOut){
		       	 			alert("用户已超时,请关闭网页重新进入!");
		       	 		}
		       	 		if(data.error){
		       	 			alert("参数错误!请重试");
		       	 		}
			       	 	if(data.dengji){
		       	 			alert("你还不是全国(钻卡)无法开店!");
		       	 		}
			       	 	$("#btn_submit").html("立即购买");
	       	 			$("#btn_submit").removeAttr('disabled');
		       	 		
		       	 	}
			 	},
			 	beforeSend : function() {
				 	$("#btn_submit").html("订单生成!");
				 	$("#btn_submit").attr('disabled',"disabled");
				},
				error:function(){
					$("#btn_submit").html("立即购买");
       	 			$("#btn_submit").removeAttr('disabled');
					alert("错误!");
				}
		 	});
			
		}else
			alert("请选择购买项或者您已经是该店主!"); 
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
//   	 	        alert("您取消了支付。");  
   	 	   }
   	 		else{  
   	 		   $("#btn_submit").html("支付订单");
   	 			$("#btn_submit").removeAttr('disabled');
//   	 	       alert("支付失败!");  
   	 	   }  
   	 	// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg 将在用户支付成功后返回 ok，但并不保证它绝对可靠。
   	 	});
   	 	}
   	 	function moneyPay(bh){
   	 		 $.ajax({
   	 		        type:"POST",
   	 		        url:"<%=request.getContextPath()%>/pay/payDianAction!ajaxWxPay.action?m="+new Date(),
   	 		        data : {"orderNo":bh},
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
	<div class="ft-but">
		<p class="xx-J">价格：￥<span id="jiage" class="xx-jia">0</span>元</p>
		<button id="btn_submit" class="mui-btn mui-btn-danger mui-btn-block" onclick="goumai()">立即购买</button>
	</div>
	
	
	
	
	
	
	
	
	
	
	
	</br>
	</br>
	</br>
	</br>
	</br>
</body>
</html>





























