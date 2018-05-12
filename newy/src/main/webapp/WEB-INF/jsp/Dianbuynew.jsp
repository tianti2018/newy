<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="zh-CN"><head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>申请店主</title>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="x-dns-prefetch-control" content="on">   
    <meta http-equiv="x-dns-prefetch-control" content="on">
    <!--S 线上样式-->
    <link href="<%=request.getContextPath()%>/css/base.s.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/css/shenqing.css" rel="stylesheet" />
    <!--E 线上样式-->
	  <script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
</head>
<body>
<div class="sq_wrap">

   <div class="sq_list">
     <ul>
       <li><div class="m" onclick="clickDian(1)"><input type="radio" id="zige-1" name="zige" class="regular-radio" checked /><label for="zige-1"><img src="<%=request.getContextPath()%>/images/shenqing/1.png" /><span>黄金店</span><i>¥100</i></label></div></li>
       <li><div class="m" onclick="clickDian(2)"><input type="radio" id="zige-2" name="zige" class="regular-radio" /><label for="zige-2"><img src="<%=request.getContextPath()%>/images/shenqing/2.png" /><span>黑钻店</span><i>¥200</i></label></div></li>
       <li><div class="m" onclick="clickDian(3)"><input type="radio" id="zige-3" name="zige" class="regular-radio" /><label for="zige-3"><img src="<%=request.getContextPath()%>/images/shenqing/3.png" /><span>蓝钻店</span><i>¥300</i></label></div></li>
       <li><div class="m" onclick="clickDian(4)"><input type="radio" id="zige-4" name="zige" class="regular-radio" /><label for="zige-4"><img src="<%=request.getContextPath()%>/images/shenqing/4.png" /><span>黄钻店</span><i>¥300</i></label></div></li>
       <li><div class="m" onclick="clickDian(5)"><input type="radio" id="zige-5" name="zige" class="regular-radio" /><label for="zige-5"><img src="<%=request.getContextPath()%>/images/shenqing/5.png" /><span>绿钻店</span><i>¥200</i></label></div></li>
       <li><div class="m" onclick="clickDian(6)"><input type="radio" id="zige-6" name="zige" class="regular-radio" /><label for="zige-6"><img src="<%=request.getContextPath()%>/images/shenqing/6.png" /><span>红钻店</span><i>¥100</i></label></div></li>
       <li><div class="m" onclick="clickDian(7)"><input type="radio" id="zige-7" name="zige" class="regular-radio" /><label for="zige-7"><img src="<%=request.getContextPath()%>/images/shenqing/7.png" /><span>皇冠店</span><i>¥100</i></label></div></li>
       <li><div class="m" onclick="clickDian(8)"><input type="radio" id="zige-8" name="zige" class="regular-radio" /><label for="zige-8"><img src="<%=request.getContextPath()%>/images/shenqing/8.png" /><span>旗舰店</span><i>¥200</i></label></div></li>
       <li><div class="m" onclick="clickDian(9)"><input type="radio" id="zige-9" name="zige" class="regular-radio" /><label for="zige-9"><img src="<%=request.getContextPath()%>/images/shenqing/9.png" /><span>星火总店</span><i>¥300</i></label></div></li>
       <div class="clear"></div>
     </ul>
     <input type="checkbox" id="tongyixieyi" onclick="tongyi()" style="width:50px;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/xieyi.html" style="text-decoration: underline;">同意《星火草原协议》</a>
   </div>
   <input type="hidden" id="xx-shu" name="levelValue" class="xx-shu" value="0">
   <input type="hidden" id="jiage" />
  <div class="sq_btn_main"><a href="#" class="sq_btn" id="btn_submit" onclick="goumai()">立即支付</a></div>

</div>

</body>
	<script type="text/javascript">
	
	function tongyi(){
		if(document.getElementById('tongyixieyi').checked==true){
			$("#btn_submit").removeAttr('disabled');
		}else{
			$("#btn_submit").attr('disabled',"disabled");
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
			$("#xx-shu").val(j);
			$("#jiage").val(jiage);
		/* 	for(var i=1;i<=9;i++){
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
			} */
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
			if(document.getElementById('tongyixieyi').checked!=true){
				alert("请您阅读购买条款");	
			return false;
				
			}
			if('${user.level}'!=3){
				alert("你还不是全国(钻卡),无法成为店主!");
				return false;
			}
			/* alert("系统维护中！"); */
			var jiage=$("#jiage").val();
			var level = parseInt($("#xx-shu").val());;
			var addressId = '${orderAddress.id }';
			if(jiage!=""&&jiage!=0){
				if(confirm("确认消耗"+jiage+"元吗?")){
					$.ajax({
				        type:"POST",
				        url:"<%=request.getContextPath()%>/pay/payDianAction!saveOrderDian.action",
				        data : {
				        	"total_price1":jiage,
				        	"levelValue":level,
				        	"orderAddress.id":addressId,
				        	/* "orderAddress.userName":xingming,
				        	"orderAddress.address":dizhi,
				        	"orderAddress.mobile":phone,
				        	"orderAddress.zipcode":wxhao, */
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
				}
			
			
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
</html>




























