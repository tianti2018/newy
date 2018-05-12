<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<style type="text/css">
.top-tipsGig {
	padding-left: 17px;
	font-size: 14px;
	color: #808080;
	margin-top: 10px;
}

.cm-form .noPadding {
	padding: 0px;
	min-height: 24px;
}

.llProDiv {
	margin: 5px auto;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 5px;
	text-align: center;
	cursor: pointer;
	line-height: 20px;
	border: 0.062em solid #c0bfbf;
	background-color: #fff;
	color: #494949;
	height: 50px;
	padding-top: 5px;
	width: 92%;
	padding-bottom: 4px;
}

.newsLiu {
	background-color: #fee0d4;
	border-radius: 5px;
	cursor: pointer;
	height: 40px;
	line-height: 20px;
	margin: 5px auto;
	padding-bottom: 4px;
	padding-top: 5px;
	text-align: center;
	width: 92%;
	border: 0.062em solid #fc3d38;
	color: #fc3d38;
}

.leftDiv, .rightDiv {
	width: 33.3%;
	float: left;
}

.middleDiv {
	width: 33.4%;
	float: left;
}

.llProDiv_select {
	font-weight: normal;
	background-color: #fc3d38;
	color: #fff;
	border: 0.062em solid #fc3d38;
}

.productSelInner {
	width: 100%;
	margin: 0px auto;
}

.table_c {
	margin-top: 55px;
}

.layui-layer-dialog, .layui-layer-prompt .layui-layer-input,
	.layui-layer-title {
	border-radius: 3px;
}

.layui-layer-btn a {
	border-radius: 2px;
}

.cm-form .control-group .input-code {
	font-weight: normal;
}

.cm-form .control-group .cm-input {
	height: 35px;
}

.cm-form .phoneInput {
	line-height: 35px;
	min-height: 35px;
	margin: 0px 15px 2px 15px;
}

#selLiuliang, #selHuaFei {
	border: 0px;
	background: #f5f5f5;
	margin: 0px 15px;
}

.layui-layer-setwin .layui-layer-close1 {
	display: none;
}

.layui-layer-btn {
	padding-top: -20px;
}

.layui-layer-dialog .layui-layer-content {
	text-align: center;
}

.divtitle {
	overflow: hidden;
	height: 40px;
	position: fixed;
	top: 0;
	right: 0;
	width: 100%;
	opacity: 0.97;
}

.titleliu {
	display: block;
	float: left;
	width: 49.83%;
	text-align: center;
	height: 98%;
	line-height: 40px;
	border-bottom: 0.071em solid #d5d5d9;
	color: #888a8b;
	border-right: 0.071em solid #d5d5d9;
	background-color: #fff;
}

.titlehua {
	display: block;
	float: left;
	width: 49.83%;
	text-align: center;
	height: 98%;
	line-height: 40px;
	border-bottom: 0.071em solid #d5d5d9;
	color: #888a8b;
	background-color: #fff;
}

.selectDivHuaAndLiuCss {
	border-bottom: 1px solid #fc3d38;
	color: #fc3d38;
}

html, body {
	background-color: #f5f5f5;
}

.liupay {
	margin-top: 5px;
	border: none;
	border-top: 0.062em solid #dedee0;
	width: 100%;
}

.weixin, .pay_b {
	border-bottom: 0.062em solid #dedee0;
	font-size: 16px;
	line-height: 48px;
	background-color: #fff;
	height: 48px;
}

.pay_b span, .weixin span {
	margin-left: 45px;
}

.weixin img, .pay_b img {
	margin-top: 15px;
	margin-left: 6px;
	width: 26px;
}

.payradio {
	height: 100%;
	opacity: 0;
	position: absolute;
	right: 10px;
	width: 100%;
	z-index: 106;
}

.span_ture img, .span_ture2 img {
	width: 27px;
	position: absolute;
	bottom: 3px;
	z-index: 107;
}

.payafterdiv {
	width: 25px;
	height: 25px;
	border: 0.062em solid #dedde0;
	position: absolute;
	bottom: 10px;
	right: 18px;
	z-index: 105;
	border-radius: 100px;
}

.phoneInputDivcss {
	width: 100%;
	border-top: 1px solid #dedee0;
	border-bottom: 1px solid #dedee0;
	background-color: #fff;
	height: 47px;
	padding-top: 4px;
}

.cm-input {
	border: none;
	margin-left: 20px;
	height: 45px;
	padding: 0;
	width: 100%;
	font-size: 18px;
}

.tipsinfo {
	width: 100%;
}

.cm-form .fieldset {
	margin-top: 5px;
}

.firstFont {
	font-size: 16px;
}

.secondFont {
	font-size: 13px;
}

.index-form {
	margin-top: -5px;
}

.telDeleteCss {
	margin-top: 9px;
}

.layui-layer {
	box-shadow: none;
}

.cm-input {
	margin-left: 0;
	padding-left: 20px;
}
</style>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>购买页面</title>
  
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bbt.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/mui.min.css"/>
   
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/liuliang/jquery.1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/liuliang/zepto.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/liuliang/ps.1.0.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/liuliang/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/liuliang/phonecz.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/liuliang/layer_singleman.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/liuliang/layer.ext.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/liuliang/zepto.alert.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/liuliang/sweetalert.min.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/liuliang/singleManActivityWeiChat.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/liuliang/jweixin-1.0.0.js"></script>

	<script type="text/javascript" src="<%=request.getContextPath()%>/js/liuliang/swiper-2.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/liuliang/index.js"></script>


	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/js/liuliangcss/zepto.alert.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/js/liuliangcss/sweetalert.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/js/liuliangcss/index.css">
</head>
<body>

	<div class="cm-wrapper" id="mainDiv">

		


		<div id="mainStage" template="ctrl.qq.m.traffic" class="table_c">
			<form class="cm-form index-form">
				<fieldset class="fieldset">
					<div style="width: 100%" class="phoneInputDivcss">
						<input id="mainStageTel" class="cm-input input-code" type="tel"
							event-focusin="logic.qq.traffic-focus"
							event-focusout="logic.qq.traffic-blur"
							event-input="logic.qq.traffic-input" maxlength=""
							onkeyup="changeInput(this)" onfocus="showdelete(this);"
							onblur="hidedelete(this);" placeholder="请输入充值的手机号码" value=""
							list="historyTel"></input>
						<datalist id="historyTel">
						</datalist>
						<a id="mainStageHistoryTel" class="cm-btn" style="display: none;"
							href="javascript:void(0);"> 历史号码 </a> <select
							id="mainStageSelect" class="cm-select cm-select-right opacity0"
							event-change="logic.qq.traffic-selectNum" style="display: none;"></select>
						
						<div class="delete" id="liuliangTelDel">
							<img class="telDeleteCss" src="<%=request.getContextPath()%>/js/liuliang/delete.png" />
						</div>

					</div>
				</fieldset>
				<div class="top-tipsGig">
					<font id="mainStageTelFrom"></font> &nbsp;&nbsp;<font
						id="liuliangzengsongInfo" color="red"></font>
						<!-- 
						电信  发送短信指令'108'至10001，即可查询流量到账情况！
						http://wap.10010.com 联通  发送短信指令'412'至10001，即可查询流量到账情况！
						http://wap.10086.cn 移动  发送短信指令'cxyl'至10086，即可查询流量到账情况！
						 -->
				</div>
				<fieldset class="fieldset">
					<div class="control-group noPadding" id="selLiuliang" value="">
						
						<div class="productSelInner">
							 <div class="leftDiv">
								<div  value="000000004d23c6ef014d2847799b0023"
									dataname="移动全国流量10M" class="llProDiv">
									<p class="firstFont">10M</p>
									<!-- <p class="secondFont">售价2.85元</p> -->
								</div>
							</div>
							<div class="middleDiv">
								<div pricedata="4.75" value="000000004d23c6ef014d28481c4a0025"
									dataname="移动全国流量30M" class="llProDiv">
									<p class="firstFont">30M</p>
									<!-- <p class="secondFont">售价4.75元</p> -->
								</div>
							</div>
							<div class="rightDiv">
								<div pricedata="9.5" value="000000004d23c6ef014d2848b58c0027"
									dataname="移动全国流量70M" class="llProDiv">
									<p class="firstFont">70M</p>
									<!-- <p class="secondFont">售价9.5元</p> -->
								</div>
							</div>
							<div class="leftDiv">
								<div pricedata="19" value="000000004d23c6ef014d284a17660029"
									dataname="移动全国流量150M" class="llProDiv">
									<p class="firstFont">150M</p>
									<!-- <p class="secondFont">售价19元</p> -->
								</div>
							</div>
							<div class="middleDiv">
								<div pricedata="28.5" value="000000004d23c6ef014d284a913a002b"
									dataname="移动全国流量500M" class="llProDiv">
									<p class="firstFont">500M</p>
									<!-- <p class="secondFont">售价28.5元</p> -->
								</div>
							</div>
							<div class="rightDiv">
								<div pricedata="47.5" value="000000004d56e353014d6ae8c7470021"
									dataname="移动全国流量1G" class="llProDiv">
									<p class="firstFont">1G</p>
									<!-- <p class="secondFont">售价47.5元</p> -->
								</div>
							</div> 
						</div>
					</div>
					
					<font id="liuliangzengsongInfo" color="red">温馨提示：<br/>&nbsp;&nbsp;&nbsp;&nbsp;1.每月最后三天为运营商结算时间，该期间不能充值。
					<br/>&nbsp;&nbsp;&nbsp;&nbsp;2.根据运营方规定，流量叠加包只能当月使用，下月自动清零！！！。
					<br/>&nbsp;&nbsp;&nbsp;&nbsp;3.流量充值后请耐心等待，如1小时仍未到账，请在个人中心->问题反馈中提交流量未到账问题。
				<a href="../aikefu.jsp">aikefu</a> &nbsp;&nbsp;&nbsp;
				<a href="../huanxin.jsp">huanxin</a>
				
					</font>
					
					
				</fieldset>
				
				
			</form>
		</div>
	
	</div>
	<div id="alipay"></div>
	<div class="redEnvelopeClass" style="display: none;"></div>
	<script type="text/javascript">
	/* $("#btn_submit").attr('disabled',"disabled");
	$("#btn_submit").html("升级中"); */
/* 	$("#ChinaMobileDiv").hide();
	$("#ChinaUnionDiv").hide();
	$("#ChinaTelcomDiv").hide(); */
	/* var yunyingshang;
	function showLiu(telphone){
		//alert(JSON.stringify(value));
		
	var isChinaMobile =/^134[0-8]\d{7}$|^(?:13[5-9]|147|15[0-27-9]|178|18[2-478])\d{8}$/; //移动方面最新答复
   	var isChinaUnion = /^(?:13[0-2]|145|15[56]|176|18[56])\d{8}$/; //向联通微博确认并未回复
   	var isChinaTelcom = /^(?:133|153|177|18[019])\d{8}$/; //1349号段 电信方面没给出答复，视作不存在
   	var isOtherTelphone = /^170([059])\d{7}$/;//其他运营商
   	

   
     var telphone = telphone.replace(/\s+/g,"");
     if(telphone.length != 11){
          alert('未检测到正确的手机号码');
          return false;
     }else{
    	 
         if(isChinaMobile.test(telphone) ){
        	 yunyingshang = "ChinaMobile";
        	 $("#ChinaMobileDiv").show();
        	 $("#ChinaUnionDiv").hide();
        	 $("#ChinaTelcomDiv").hide();
        	 $("#phonetype").html("中国移动");
             
         }
         else if(isChinaUnion.test(telphone)){
        	 yunyingshang = "ChinaUnion";
        	 $("#ChinaMobileDiv").hide();
        	 $("#ChinaUnionDiv").show();
        	 $("#ChinaTelcomDiv").hide();
         $("#phonetype").HTML("中国联通");
         }
         else if(isChinaTelcom.test(telphone)){
        	 $("#ChinaMobileDiv").hide();
        	 $("#ChinaUnionDiv").hide();
        	 $("#ChinaTelcomDiv").show();
        	 yunyingshang = "ChinaTelcom";
        	 $("#phonetype").HTML("中国电信");
         }
         else{
        	 alert("暂不支持此号段");
        	 $("#phone").val("");
        	 return false;
         }
     }
	} */
	/* function chooseMoney(){
		 $("#jiage").html(jQuery("#"+yunyingshang+"  option:selected").val());
	} */
	
	
	
	
		function goumai() {
		var str ='2016-03-11 02:00:00';
		str = str.replace(/-/g,"/");
		var end = '2016-03-11 04:30:00';
		end = end.replace(/-/g,"/");
		var date = new Date(str);
		var endDate = new Date(end);
		if(date < new Date() && new Date() < endDate){
			alert("微信平台维护请稍候提交，感谢您的支持！");
			return false;
		}
		
		    var phoneType = $("#mainStageTelFrom").html();
		    var isbuy = '${isbuy}';
		    var isyi = '${isyi}';
		    var islian = '${islian}';
		    var isdian = '${isdian}';
		    if(isbuy == '1'){
		    	alert("平台维护，暂时无法充值，请您稍后再试，感谢您的支持！");
		    	return false;
		    }
		    var istrue;
		    if(isyi == '1'){
		    	istrue = phoneType.indexOf("移动");
		    	if(istrue >= 0){
		    		alert("移动用户暂时不能充值，请您稍后再试，感谢您的支持！");
		    		return false;
		    	}
		    	 
		    }
		    if(islian == '1'){
		    	istrue = phoneType.indexOf("联通");
		    	if(istrue >= 0){
		    		alert("联通用户暂时不能充值，请您稍后再试，感谢您的支持！");
		    		return false;
		    	}
		    }
		    if(isdian == '1.0'){
		    	istrue = phoneType.indexOf("电信");
		    	if(istrue >= 0){
		    		alert("电信用户暂时不能充值，请您稍后再试，感谢您的支持！");
		    		return false;
		    	}
		    }
		   
			var phone =$("#mainStageTel").val();
			phone = phone.replace(/\s+/g,"");
		     if(phone.length != 11){
		          alert('未检测到正确的手机号码');
		          $("#btn_submit").removeAttr('disabled');
		          return false;
		     }
		    
		     if(phone.substr(0,3) == "177"){
		    	 alert("暂时不支持177号段充值，请谅解！");
		    	 return false;
		     }
			/*
	 		var yongyong = chooseDiv[0].nodeValue;
	 		var money = chooseDiv[0].nodeValue;
	 		var pname = chooseDiv[2].nodeValue */
			
			//var youfei = jQuery("#"+yunyingshang+"  option:selected");
	 		$("#btn_submit").attr('disabled',"disabled");
	 		if( $(".llProDiv_select").length ==0){
	 			  alert('请选择需要充值的流量');
		          $("#btn_submit").removeAttr('disabled');
		          return false;
	 		}
	 		  var chooseDiv = $(".llProDiv_select")[0].attributes;
	 		 /*  var lyongjin = chooseDiv[0].nodeValue;  //window- 佣金
			var lmoney = chooseDiv[1].nodeValue; // 钱数
			var lnumber=chooseDiv[3].nodeValue;  //购买流量   */
			
			var lyongjin = chooseDiv[4].nodeValue;  
			var lmoney = chooseDiv[3].nodeValue;  
			var lnumber=chooseDiv[1].nodeValue;
			
			//alert(chooseDiv[0].nodeValue+"---"+chooseDiv[1].nodeValue+"-----"+chooseDiv[2].nodeValue+"-----"+chooseDiv[3].nodeValue+"---"+chooseDiv[4].nodeValue);
			
			//alert(lmoney+"---"+lnumber+"-----"+lyongjin);
			//return false;
			if(lmoney <= 0 || lnumber == "" || lnumber==null || lyongjin == "" || lyongjin == null){
				alert("请选择正确的套餐");
				$("#btn_submit").removeAttr('disabled');
				return false;
			}
			//$("#jiage").html(lmoney);
			//var jiage=$("#jiage").text();
			var jiage =1;
			if(jiage!=""&&jiage!=0){
			$.ajax({
		        type:"POST",
		        url:"<%=request.getContextPath()%>/order/orderAction!saveOrderLiu.action",
		        data : {
		        	"phone":phone,
		        	"lmoney":lmoney,
		        	"lnumber":lnumber,
		        	"lyongjin":lyongjin
		        	},
		        dataType:"json",
		        success:function(data) {
		       	 	if(data.success){
		       	 		moneyPay(data.ordersBh);
		       	 	
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
   	 		        url:"<%=request.getContextPath()%>/user/userAction!liuAjaxPay.action?m="+new Date(),
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
   	 	


 	var partnerId = getPartnerId();
 	phoneczFooters(partnerId);

 	$(document).ready(
 			function() {
 				isActivity();

 				$("#selLiuliang .llProDiv").click(function() {
 					$("#selLiuliang .llProDiv").removeClass("llProDiv_select");
 					$(this).addClass("llProDiv_select");
 					$("#rechargPrice").html(parseFloat($(this).attr("pricedata")) + "元");
 					$("#selLiuliang").val($(this).attr("value"));
 					$("#selLiuliang").attr("dataname", $(this).attr("dataname"));
 				});
 				$("#selHuaFei .llProDiv").click(function() {
 					$("#selHuaFei .llProDiv").removeClass("llProDiv_select");
 					$(this).addClass("llProDiv_select");
 					$("#huaFeiRechargPrice").html(parseFloat($(this).attr("pricedata")) + "元");
 					$("#selHuaFei").val($(this).attr("value"));
 					$("#selHuaFei").attr("dataname", $(this).attr("dataname"));
 				});
 				//流量充值
 				inputLiuLiangTelHandle("mainStageTel", "liuLiangInfoFont", "rechargPrice", "selLiuliang", "liuLiangTipContent", "topTips", "mainStageTelFrom",
 						"liuLiangSubmit");
 				inputHuaFeiTelHandle("mainStage1Tel", "huaFeiRechargPrice", "selHuaFei", "topTipsHuaFei", "mainStage1TelForm", "submitHuaFei");
 				// 如果带有手机号则自动填充
 				var _mobile = getMobile();
 				if (_mobile) {
 					if (_mobile.length == 11) {
 						_mobile = _mobile.substring(0, 3) + " " + _mobile.substring(3, 7) + " " + _mobile.substring(7, 11);
 					} else {
 						_mobile = "";
 					}
 					$("#mainStageTel").val(_mobile);
 					$("#mainStage1Tel").val(_mobile);
 					$("#mainStageTel").keyup();
 					$("#mainStage1Tel").keyup();
 				}

 			});
 	
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
		<%-- <p class="xx-J">价格：￥<span id="jiage" class="xx-jia">0</span>元</p> --%>
		<button id="btn_submit" class="mui-btn mui-btn-danger mui-btn-block" onclick="goumai()">立即购买</button>
	</div>
	
	</br>
	</br>
	</br>
	</br>
	</br>
</body>
</html>
