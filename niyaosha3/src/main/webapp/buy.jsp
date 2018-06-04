<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>购买页面</title>
    <script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
    <%-- <script src="<%=request.getContextPath()%>/js/jquery.mobile-1.4.5.min.js"></script> --%>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bbt.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/mui.min.css"/>
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/payfor.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/novipbuy.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/family.css" />
	<link href="<%=request.getContextPath()%>/css/style-metro.css" rel="stylesheet" type="text/css"/>
   
</head>
<body>
<div class="banner_box">
	<div class="banner">
		<a href="#" ontouchmove="shows(1)">
			<img src="<%=request.getContextPath()%>/images/shoutu.jpg" >
		</a>
		<a href="#" onclick="dianji(2)" ontouchmove="shows(2)">
			<img src="<%=request.getContextPath()%>/images/shoutu.jpg" >
		</a>
		<%-- <a href="#" ontouchmove="shows(3)">
			<img src="<%=request.getContextPath()%>/images/shoutu.jpg" >
		</a> --%>
	</div>
</div>
	<div class="div-xinxi">
		<ul>
			<li>产品：VIP</li>
			<div style="border-bottom: 1px solid #DADADA;" class="">
				<li style="border: none;">
					<p class="dengji" style="padding: 6px 0;">权限：</p>
					<!-- <button class="mui-btn mui-btn-primary mui-icon mui-icon-checkmarkempty but-1">市级(银卡)</button>
					<button class="mui-btn mui-btn-primary but-2">省级(金卡)</button> -->
					<button class="mui-btn mui-btn-primary but-3">VIP</button>
				</li>
				<%-- <div class="div-tishi">
					<a href="http://www.playtold.com/model_state.html"><img src="<%=request.getContextPath()%>/resource/img/kuang-1.png"></a>
					<a href="http://www.playtold.com/model_state.html"><img src="<%=request.getContextPath()%>/resource/img/kuang-2.png"></a>
					<a href="http://www.playtold.com/model_state.html"><img src="<%=request.getContextPath()%>/resource/img/kuang-3.png"></a>
				</div> --%>
			</div>
			<input type="hidden" id="xx-shu" class="xx-shu" value="0">
			<%-- <li>数量：<span id="xx-shu" class="xx-shu">0</span></li> --%>
			<div class="message">
			<div style="text-align: center;font-size: 20px"><input type="checkbox" id="tongyixieyi" onclick="tongyi()" style="width:50px;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/xieyi.html" style="text-decoration: underline;">同意《山人物语协议》</a></div>
			<c:if test="${null!=orderAddress}">
				
				 <ul class="menu-list">
				<li>
				   <a href="javascript:;"><i class="arrows rotated"></i><em>收货信息</em></a> 
					<ul class="sub-menu" style="height:180px">
					    <li><a href="<%=request.getContextPath()%>/orderAddress/orderAddressAction!orderAddress.action" style="background:#00aa3a; display:inline-block; color:#fff;">选择默认收货地址</a></li>
						<li>收货人：<em>${orderAddress.userName}</em></li>
						<li>联系方式：<em> ${orderAddress.mobile} </em></li>
						<li>收货地址：<em>${orderAddress.address}</em></li>
						<li></li>
					</ul>
					
				</li>
					
			</ul> 

			</c:if>
			<c:if test="${null==orderAddress}">
				<div align="center" style="margin-top: 10px;">
					<a href="<%=request.getContextPath()%>/orderAddress/orderAddressAction!orderAddress.action"  class="btn green" onclick="go();">选择一条收货地址</a>
				</div>
			</c:if>
	
		</div>
		</ul>
	</div>
	<script type="text/javascript">
	function tongyi(){
		if(document.getElementById('tongyixieyi').checked==true){
			$("#btn_submit").removeAttr('disabled');
		}else{
			$("#btn_submit").attr('disabled',"disabled");
		}
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
	function dianji(a){
		window.location.href="<%=request.getContextPath()%>/user/userAction!queryLiuBuy.action";
	}
	
		$(".div-tishi a").hide();
		$(".div-tishi a").eq(0).show();
		$(document).ready(function(){
			if("${user.level}"!=""&&parseInt("${user.level}")>0){
				$("#tongyixieyi").attr('checked',"checked");
			}
			$(".but-1").click(function(){
				$(".but-1").css({backgroundColor:"#f43853",border:"1px solid #f43853"});
				$(".but-2,.but-3").css({backgroundColor:"#007aff",border:"1px solid #007aff"});
				$(".but-2,.but-3").removeClass("mui-icon mui-icon-closeempty");
			});
			$(".but-2").click(function(){
				$(".but-1,.but-2").css({backgroundColor:"#f43853",border:"1px solid #f43853"});
				$(".but-3").css({backgroundColor:"#007aff",border:"1px solid #007aff"});
				$(".but-1,.but-2").addClass("mui-icon mui-icon-checkmarkempty");
				$(".but-3").removeClass("mui-icon mui-icon-closeempty");
			});
			$(".but-3").click(function(){
				$(".but-1,.but-2,.but-3").css({backgroundColor:"#f43853",border:"1px solid #f43853"});
				$(".but-1,.but-2,.but-3").addClass("mui-icon mui-icon-checkmarkempty");
			});
			
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
		jQuery(document).ready(function(){
			var level = '${user.level}';
			var newshu = 0;
			var newmoney=0;
			if(level=='1'){
				newshu = 1;
				newmoney=100;
			}else if(level=='2'){
				newshu=3;
				newmoney=300;
			}else if(level=='3'){
				newshu=6;
				newmoney=600;
			}
			  jQuery(".but-1").click(function(){
			    jQuery(".xx-shu").val((1-newshu)<=0?0:(1-newshu));
			    jQuery(".xx-jia").text((100-newmoney)<=0?0:(100-newmoney));
			    $("#level").val(1);
			    /* $("#hyhzhb").html((100-newmoney)*0.9); */
			    $("#kafei").html(((100-newmoney)<=0?0:(100-newmoney))*0.1);
			    /* if((100-newmoney)==100){
			    	$("#kaming").html("");
			    }else{
			    	$("#kaming").html('"银"');
			    } */
			    
			  });
			  jQuery(".but-2").click(function(){
			    jQuery(".xx-shu").val((3-newshu)<=0?0:(3-newshu));
			    jQuery(".xx-jia").text((300-newmoney)<=0?0:(300-newmoney));
			    $("#level").val(2);
			   
			    $("#kafei").html(((300-newmoney)<=0?0:(300-newmoney))*0.1);
			    /* $("#hyhzhb").html((300-newmoney)*0.9);
			    if((300-newmoney)==300){
			    	$("#kaming").html('"金,银"');
			    }else if((300-newmoney)==200){
			    	$("#kaming").html('"金"');
			    }else{
			    	$("#kaming").html("");
			    } */
			  });
			  jQuery(".but-3").click(function(){
			    jQuery(".xx-shu").val((6-newshu)<=0?0:(6-newshu));
			    jQuery(".xx-jia").text((600-newmoney)<=0?0:(600-newmoney));
			    $("#level").val(3);
			   
			    $("#kafei").html(((600-newmoney)<=0?0:(600-newmoney))*0.1);
			    /* $("#hyhzhb").html((600-newmoney)*0.9);
			    if((600-newmoney)==300){
			    	$("#kaming").html('"钻,金,银"');
			    }else if((600-newmoney)==500){
			    	$("#kaming").html('"钻,金"');
			    }else if((600-newmoney)==300){
			    	$("#kaming").html('"钻"');
			    }else{
			    	$("#kaming").html("");
			    } */
			  });
			});
		function goumai() {
			if(document.getElementById('tongyixieyi').checked==false){
				alert("请阅读并勾选<<山人物语购买协议>>");
				return false;
			}
			/* alert("系统维护中！"); */
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
			var xingming = "${orderAddress.userName}";
			var oldlevel = '${user.level}';
			var shuliang = parseInt($("#xx-shu").val());
			var jiage=$("#jiage").text();
			var addressId = "${orderAddress.id}";
			var dizhi ="${orderAddress.address}";
			var phone ="${orderAddress.mobile}";
			var wxhao ="${orderAddress.zipcode}";
			var level = null;
			if(shuliang<=0){
				alert("请选择购买类型或您已购买过该项!");
				return;
			}else if(shuliang==1){
				level="1";
			}else if(shuliang==2){
				level="2";
			}else if(shuliang==3){
				if(oldlevel==2)
					level= "3";
				else
					level="1,2";
			}else if(shuliang==5){
				level="2,3";
			}else if(shuliang==6){
				level="1,2,3";
			}
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
			if(dizhi.indexOf("null")>0){
				alert("本地址已无法用，请添加新的收货地址!");
				return;
			}
			
			if(jiage!=""&&jiage!=0){
			$.ajax({
		        type:"POST",
		        url:"<%=request.getContextPath()%>/order/orderAction!saveOrder.action",
		        data : {
		        	"total_price1":jiage,
		        	"qty_item_1":shuliang,
		        	"levelValue":level,
		        	"orderAddRessId":addressId,
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
			alert("参数出错!请关闭页面重新进入!"); 
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
   	 		        url:"<%=request.getContextPath()%>/user/userAction!ajaxWxPay.action?m="+new Date(),
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
		<p class="xx-J">价格：￥<span id="jiage" class="xx-jia">0</span>元(<span id="kafei">0</span>元为卡费,其余为粉丝感恩赠送红包)</p>
		<button id="btn_submit" class="mui-btn mui-btn-danger mui-btn-block" onclick="goumai()">立即购买</button>
	</div>
	
	
	
	
	
	
	
	
	
	
	
	</br>
	</br>
	</br>
	</br>
	</br>
</body>
</html>





























