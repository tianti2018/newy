<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ResourceBundle"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%
	ResourceBundle res = ResourceBundle.getBundle("system");
%>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="x-dns-prefetch-control" content="on">
<meta http-equiv="x-dns-prefetch-control" content="on">
<title>产品详情</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/buttons.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/novipbuy.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/footer.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/detail.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/flexslider.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/source/jquery.fancybox.css?v=2.1.5"
	media="screen" />
<link
	href="<%=request.getContextPath()%>/bootstrap-3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/payfor.js"></script>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.mobile-1.4.5.min.js"></script> --%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/family.css" />
<link href="<%=request.getContextPath()%>/css/style-metro.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/products/products.js"></script>
</head>
<body id="bodyy">
	<div class="wrap">
		<div class="banner">
			<div class="flexslider">
				<ul class="slides">

						<a href="#" ontouchmove="shows(1)">
							<li><img src="${prod.headUrl }"
								style="width: 100%"></li>
						</a>
				</ul>
			</div>
		</div>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery.flexslider-min.js"></script>
		<script>
				$(function() {
					$(".flexslider").flexslider({
						animation: "slide",
						directionNav: false,
						touch: true
					});
				});
				function orderBuy(){
					$("#lijibuy").removeAttr("onclick");
					var str ='2016-03-10 02:00:00';
					str = str.replace(/-/g,"/");
					var end = '2016-04-12 08:00:00';
					end = end.replace(/-/g,"/");
					var date = new Date(str);
					var endDate = new Date(end);
					 //if("${user.userId}"!="1821")
					if(date < new Date() && new Date() < endDate){
						alert("系统升级维护中,请"+end+"以后再次操作!");
						$("#lijibuy").attr("onclick","orderBuy()");
						return false;
					}
					
					var num = $("#qty_item_1").val();
					var stock=$("#productKC").val()==""?"0":$("#productKC").val();
					var prodId=$("#checkProdId").val();
					var xhq_count=$("#diyongcount").val();
					xhq_count=xhq_count==""?"0":xhq_count;
					var addressId = ${userVo.orderAddress.id};
					if(addressId==null){
						alert("请选择收货地址或点击刷新页面!");
						return;
					}
					
					var inputamt = parseInt(parseInt(xhq_count) / 2);
					var totalamt = parseFloat($("#total_amt").val());
					if(num <=0){
						alert("至少购买一套!");
						$("#lijibuy").attr("onclick","orderBuy()");
						return false;
					}
					else if(parseInt("${prod.limitNum}")>0 && num>parseInt("${prod.limitNum}"))
					{
						alert("由于本产品倾销过快,防止厂家出现货源问题,所以最多只能购买${prod.limitNum}件,敬请谅解!");
						$("#lijibuy").attr("onclick","orderBuy()");
						return false;
					}
					else if(parseInt(num)>parseInt(stock))
						{
						  alert("库存不够");
						  $("#lijibuy").attr("onclick","orderBuy()");
						  return false;
						}
					else if(prodId=="")
						{
						  alert("请选择购买商品");
						  $("#lijibuy").attr("onclick","orderBuy()");
						  return false;
						}
					
					
					if(confirm("确定购买"+num+"套吗?")){
								$.ajax({
							        type:"POST",
							        url:"<%=request.getContextPath()%>/pay/payGoodAction!saveMianMoOrder.action",
							        data : {
							        	"qty_item_1":num,
							        	"prodId":prodId,
							        	 "xhq_count":xhq_count
							        	},
							        dataType:"json",
							        success:function(data) {
							       	 	if(data.success){
											//如果订单用火星券全部支付则不用调用微信的支付接口
										    if(data.need_pay)
										    {
											   moneyPay(data.ordersBh);
											 }
										    else 
										    {
										    	alert("订单保存成功");
										    	WeixinJSBridge.call('closeWindow'); 
										    }
							       	 	//alert(data.ordersBh);
							       	 	}else{
							       	 		if(data.timeOut){
							       	 			alert("用户已超时,请关闭网页重新进入!");
							       	 		}
							       	 		if(data.error){
							       	 			alert("参数错误!请重试");
							       	 		}
							       	 		if(data.overbuy)
							       	 			{
							       	 		$("#lijibuy").attr("onclick","orderBuy()");
							       	 			  alert("订购数量不能大于${prod.limitNum}");
							       	 			}
							       	 		else if(data.stockless)
							       	 			{
							       	 		$("#lijibuy").attr("onclick","orderBuy()");
							       	 			  alert("库存不够");
							       	 			}
							       	 		else if(data.xhq_nok)
							       	 			{
							       	 			  alert("星火券数量异常，请关闭网页重新进入");
							       	 			}
							       	 		else if(data.xhq_overbuy)
							       	 			{
							       	 			   alert("现在处于测试阶段, 一天只能用星火券购买两次");
							       	 			}
								       	 	$("#btn_submit").html("立即购买");
						       	 			$("#btn_submit").removeAttr('disabled');
							       	 		
							       	 	}
								 	},
								 	beforeSend : function() {
									 	$("#btn_submit").html("订单生成中!");
									 	$("#btn_submit").attr('disabled',"disabled");
									},
									error:function(){
										$("#btn_submit").html("立即购买");
					       	 			$("#btn_submit").removeAttr('disabled');
										alert("错误!");
									}
							 	});
					}
					$("#lijibuy").attr("onclick","orderBuy()");
				}
			</script>
		<div style="height: 10px"></div>
		<div class="det-middle">
			<div class="det-pro-name">
				<span><c:out value="${prod.name}" /></span>
			</div>
			<div class="det-pro-num">
				<div class="det-pro-price">
					<span>￥<em><c:out value="${prod.price }" /></em></span>
					<div class="pro-size-m">
						已选：
						<span id="qty_item_3"><c:out value="${prod.name}" /></span>
						<span id="qty_item_3"></span> /<span id="qty_item_2">1</span>件
					</div>
				</div>

				<div class="" id="size-wrap">
					<div class="" id="proSize">
						<div class="pro-color">
							<h2>规格：</h2>
							<c:if test="${typeqty>0}">
								<c:forEach var="p" items="${typelist}" varStatus="vstatus">
									<a id="xuanze_${p.productsId}" class="xuanze button" name="product" guige="${p.guige}" stock="${p.stock}"
											transFee="${p.transFee}" prodId="${p.productsId}" price="${p.price}" onclick="checkProd(this)">
										<c:out	value="${p.guige}" />
										<c:if test="${p.stock<500 }">
											库存：<c:out value="${p.stock }" />件
										</c:if>
									</a>
								</c:forEach>
							</c:if>
							<c:if test="${typeqty==0}">
								<a id="xuanze_${prod.productsId}" class="xuanze button" name="product" guige="${prod.guige}" stock="${prod.stock}"
											transFee="${prod.transFee}" prodId="${prod.productsId}" price="${prod.price}" onclick="checkProd(this)">
										<c:out	value="${prod.guige}" />
										<c:if test="${prod.stock<500 }">
											库存：<c:out value="${prod.stock }" />件
										</c:if>
									</a>
							</c:if>
						</div>
					</div>
				</div>
				<div class="det-pro-buy">
					<div class="det-pro-sl">
						<span>数量</span> <a class="reduce" onclick="cut()"
							href="javascript:void(0)"><img
							src="<%=request.getContextPath()%>/images/novipbuy/reduce.png"
							alt=""></a> <input type="text" name="qty_item_1" value="1"
							id="qty_item_1" onblur="inputqty()" class="text" size="4" /> <a
							class="add" onclick="add()" href="javascript:void(0)"><img
							src="<%=request.getContextPath()%>/images/novipbuy/add.png"
							alt=""></a>
					</div>
					<div style="height: 2px;"></div>
				</div>
				<div class="det-pro-zj">
					<span>总计:</span> <span><em>￥<span id="total_prod_amt">${prod.price}</span></em>
						=</span> <span><em>￥<span id="total_prod_m">${prod.price}</span></em>
						+</span> <span><em>￥<span id="total_yunfei"><c:out
									value="${prod.transFee}" /></em>(运费)</span>
				</div>
				<c:if test="${typeqty>1}">
					<input type="hidden" id="checkProdId" value="" />
					<input type="hidden" id="prodprice" value="" />
					<input type="hidden" id="productKC" value="" />
				</c:if>
				<c:if test="${typeqty==1}">
					<input type="hidden" id="checkProdId" value="${prod.productsId}" />
					<input type="hidden" id="prodprice" value="${prod.price}" />
					<input type="hidden" id="productKC" value="${prod.stock}" />
				</c:if>
				<br> 
				<c:if test="${userVo.usery.level>=1 }">
				<input type="checkbox" name="diyong" id="diyong" onclick="checkDiYong(this)"> 
					<label for="diyong"	style="color: red;"> 
						当前余额：<span id="max_xhq_count">
							<c:out value="${userVo.usery.account}" />
						</span>，是否使用 ？
					</label> 
				</c:if>	
			</div>
			<div class="message">
				<c:if test="${null!=userVo.orderAddress}">

					<ul class="menu-list">
						<li><a href="javascript:;"><i class="arrows rotated"></i><em>收货信息</em></a>
							<ul class="sub-menu" style="height: 180px">
								<li><a
									href="<%=request.getContextPath()%>/orderAddress/orderAddressAction!orderAddress.action?productId=${prod.productsId}"
									style="background: #00aa3a; display: inline-block; color: #fff;">选择默认收货地址</a></li>
								<li>收货人：<em>${userVo.orderAddress.userName}</em></li>
								<li>联系方式：<em> ${userVo.orderAddress.mobile} </em></li>
								<li>收货地址：<em>${userVo.orderAddress.sheng}${userVo.orderAddress.chengshi}${userVo.orderAddress.diqu}${userVo.orderAddress.address}</em></li>


							</ul></li>

					</ul>

				</c:if>
				<c:if test="${null==userVo.orderAddress}">
					<div align="center">
						<a
							href="<%=request.getContextPath()%>/orderAddress/orderAddressAction!orderAddress.action?productId=${prod.productsId}"
							class="btn green" onclick="go();">选择一条收货地址</a>
					</div>
				</c:if>
				<br/>
				<a href="#" id="lijibuy" onclick="javascript:orderBuy()"
							class="btn green" style="width: 45%;margin-left: 5px">立即购买</a>
				<a href="#" id="lijibuy1" onclick="javascript:shuaxin()"
							class="btn green" style="width: 45%;margin-left: 2px">刷新页面</a>
			</div>
		</div>
	</div>

	<div class="tab-wrap">
		<div class="tab">
			<ul class="tuwen">
				<li id="miaoshuB">图文详情</li>
				<li id="shouhouB">售后服务</li>
				<li id="pinglunB">商品评论(<span>${count}</span>)
				</li>
			</ul>
		</div>
		<div id="miaoshu" class="shangpintu">
			<div id="product-detail-1">
				${prod.productInfo }
			</div>

		</div>
		<div id="shouhou" class="shangpintu">
			<ul>
				<li><h4 style="color: red">
						厂家售后电话:010-57732808，微信号：jlj968
						</h1></li>
				<li><h4 style="color: red">工作时间:9:00--17:30</h4></li>
			</ul>
		</div>
		<div id="pingjia" class="shangpintu">
			<div id="em_all" class="assess">
					<a id="liuyan" href="#inline" class="btnn" onclick="viewDialog();">发表评论</a>
				<ul id="appendUl">
					<c:forEach var="c" items="${commentlist}" varStatus="comstatus">
						<li>
							<div class="assess-name">
								<span><em>${c[8]}</em></span> <span><em>${c[3]}</em></span>
							</div>
							<div class="assess-con">
								<p>
									<em>${c[2]}</em>
								</p>
							</div>
						</li>
					</c:forEach>
				</ul>
				<button class="assess-btn" onclick="insertcode()">
					<span>点击加载更多</span><br />
					<img src="<%=request.getContextPath()%>/images/more.gif" />
				</button>
			</div>
		</div>
	</div>
	<div class="panel panel-primary" id="inline"
		style="display: none; width: 300px; height: 320px;">
		<div class="panel-heading">
			<h3 class="panel-title">评论</h3>
		</div>
		<div class="panel-body">
			<div>
				<div class="form-group">
					评论内容:
					<textarea class="form-control" rows="8" id="textMessage"
						style="height: 175px; width: 240px;"></textarea>
				</div>
			</div>
			<div>
				<input type="hidden" value="" id="wxOpenid" /> <a
					class="button button-pill button-primary" href="#" role="button"
					onclick="sendMessage();">发送</a> <a
					class="button button-pill button-primary" href="#" role="button"
					onclick="closefancybox();">关闭</a>
			</div>

		</div>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/css/source/jquery.fancybox.pack.js?v=2.1.5"></script>
	<script type="text/javascript">
			 wx.config({
				    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				    appId: "${appId}", // 必填，公众号的唯一标识
				    timestamp: '${timestamp}', // 必填，生成签名的时间戳
				    nonceStr: '${nonce_str}', // 必填，生成签名的随机串
				    signature: '${signature}',// 必填，签名，见附录1
				    jsApiList: ['menuItem:share:appMessage','onMenuShareAppMessage',
				                'menuItem:share:timeline','onMenuShareTimeline',
				                'menuItem:share:qq','onMenuShareQQ',
				                'menuItem:share:weiboApp','onMenuShareWeibo',
				                'menuItem:share:QZone','onMenuShareQZone'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
				});
			 
			  wx.ready(function(){
				    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
				 /* wx.checkJsApi({
					    jsApiList: ['menuItem:share:appMessage','onMenuShareAppMessage',
					                'menuItem:share:timeline','onMenuShareTimeline',
					                'menuItem:share:qq','onMenuShareQQ',
					                'menuItem:share:weiboApp','onMenuShareWeibo',
					                'menuItem:share:QZone','onMenuShareQZone'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
					    success: function(res) {
					        // 以键值对的形式返回，可用的api值true，不可用为false
					        // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
					    }
					});  */	
				  wx.onMenuShareTimeline({
					    title: '${prod.name}', // 分享标题
					    link: '${url}', // 分享链接
					    imgUrl: 'https://mmbiz.qlogo.cn/mmbiz/pKWIGyfSjUm56IyFxsNI5shwgexlbGhiaEOwrTI87P4ic8DE6xq2jVJYMmfO0ViaAla2PyveBsTxeNOvBCaueZibyg/0?wx_fmt=jpeg', // 分享图标
					    success: function () { 
					        // 用户确认分享后执行的回调函数
					    	alert("分享成功");
					    },
					    cancel: function () { 
					        // 用户取消分享后执行的回调函数
					    	alert("您取消了分享");
					    }
					});
				 wx.onMenuShareAppMessage({
					    title: '${prod.name}', // 分享标题
					    desc: '${prod.prodDescription}', // 分享描述
					    link: '${url}', // 分享链接
					    imgUrl: 'https://mmbiz.qlogo.cn/mmbiz/pKWIGyfSjUm56IyFxsNI5shwgexlbGhiaEOwrTI87P4ic8DE6xq2jVJYMmfO0ViaAla2PyveBsTxeNOvBCaueZibyg/0?wx_fmt=jpeg', // 分享图标
					    //type: '', // 分享类型,music、video或link，不填默认为link
					    //dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
					    success: function () { 
					        // 用户确认分享后执行的回调函数
					        alert("分享成功");
					    },
					    cancel: function () { 
					        // 用户取消分享后执行的回调函数
					    	alert("您取消了分享");
					    }
					});
				 wx.onMenuShareQQ({
					    title: '${prod.name}', // 分享标题
					    desc: '${prod.prodDescription}', // 分享描述
					    link: '${url}', // 分享链接
					    imgUrl: 'https://mmbiz.qlogo.cn/mmbiz/pKWIGyfSjUm56IyFxsNI5shwgexlbGhiaEOwrTI87P4ic8DE6xq2jVJYMmfO0ViaAla2PyveBsTxeNOvBCaueZibyg/0?wx_fmt=jpeg', // 分享图标
					    success: function () { 
					       // 用户确认分享后执行的回调函数
					    	alert("分享成功");
					    },
					    cancel: function () { 
					       // 用户取消分享后执行的回调函数
					    	alert("您取消了分享");
					    }
					});
				 wx.onMenuShareWeibo({
					    title: '${prod.name}', // 分享标题
					    desc: '${prod.prodDescription}', // 分享描述
					    link: '${url}', // 分享链接
					    imgUrl: 'https://mmbiz.qlogo.cn/mmbiz/pKWIGyfSjUm56IyFxsNI5shwgexlbGhiaEOwrTI87P4ic8DE6xq2jVJYMmfO0ViaAla2PyveBsTxeNOvBCaueZibyg/0?wx_fmt=jpeg', // 分享图标
					    success: function () { 
					       // 用户确认分享后执行的回调函数
					    	alert("分享成功");
					    },
					    cancel: function () { 
					        // 用户取消分享后执行的回调函数
					    	alert("您取消了分享");
					    }
					});
				 wx.onMenuShareQZone({
					    title: '${prod.name}', // 分享标题
					    desc: '${prod.prodDescription}', // 分享描述
					    link: '${url}', // 分享链接
					    imgUrl: 'https://mmbiz.qlogo.cn/mmbiz/pKWIGyfSjUm56IyFxsNI5shwgexlbGhiaEOwrTI87P4ic8DE6xq2jVJYMmfO0ViaAla2PyveBsTxeNOvBCaueZibyg/0?wx_fmt=jpeg', // 分享图标
					    success: function () { 
					       // 用户确认分享后执行的回调函数
					    	alert("分享成功");
					    },
					    cancel: function () { 
					        // 用户取消分享后执行的回调函数
					    	alert("您取消了分享");
					    }
					});
			 });
			  
			wx.error(function(res){
				    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
					
				    wx.hideOptionMenu();//被分享的页面无法再次分享
			});
			  
			
	
	function dianji(a){///newy/src/main/webappproduct1.jsp
		window.location.href="<%=request.getContextPath()%>/product"+a+".jsp";
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
   	 		$("#lijibuy").attr("onclick","orderBuy()");
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
   	 		        url:"<%=request.getContextPath()%>/pay/payGoodAction!ajaxWxPay.action?m="+new Date(),
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
	
		go = function () {
			self.location.href="<%=request.getContextPath()%>/orderAddress/orderAddressAction!orderAddress.action";
		}
		
		
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
		})
	/* function onBridgeReady() {
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
	} */
	
	
	$(document).ready(function(){
		$("#shouhou,#pingjia").hide();
		 var price=parseFloat($("#prodprice").val());
	     var transFee=parseInt($("#total_yunfei").html());
	     var qty=$("#qty_item_1").val().trim()==""?1:parseInt($("#qty_item_1").val().trim());
	     var total_item_amt=parseFloat(parseFloat(price)*qty+transFee);
		 showAmt(qty,total_item_amt,transFee);
		
	});
	
	function checkProd(obj)
	{
		$(".xuanze").each(function(){
			$(".xuanze").removeClass("pro-sel");		
		});
		$("#xuanze_"+$(obj).attr("prodId")).addClass("pro-sel");
		var yunfei=$(obj).attr("transFee")==""?0:parseInt($(obj).attr("transFee"));
		var guige=$(obj).attr("guige");
		var qty=$("#qty_item_1").val().trim()==""?1:parseInt($("#qty_item_1").val().trim());
		var total_item_amt=parseFloat(parseFloat($(obj).attr("price"))+yunfei);
		$("#total_prod_m").text($(obj).attr("price"));
		showAmt2(qty,total_item_amt,yunfei,guige);
		$("#checkProdId").val($(obj).attr("prodId"));
		$("#productKC").val($(obj).attr("stock"));
	}
	function showAmt2(qty,total_item_amt,transFee,guige)
	{
		     $("#qty_item_1").val(1);
		     $("#qty_item_2").html(1);
		     $("#qty_item_3").html(guige);
		     $("#total_prod_amt").html(total_item_amt);
			 $("#total_amt").val(total_item_amt+transFee);
			 $("#total_yunfei").html(transFee);
	}
	function showAmt(qty,total_item_amt,transFee,total_item_m)
	{
		     $("#qty_item_1").val(qty);
		     $("#qty_item_2").html(qty);
		     $("#total_prod_amt").html(total_item_amt);
		     $("#total_prod_m").html(total_item_m);
			 $("#total_amt").val(total_item_amt+transFee);
			 $("#total_yunfei").html(transFee);
	}
	//增加商品
	var typeqty=parseInt("${typeqty}");
	function add()
	{
		if(typeqty==1)
			{
			
			     var price=parseFloat($("#prodprice").val());
			     var transFee=parseInt($("#total_yunfei").html());
			     var qty=$("#qty_item_1").val().trim()==""?1:parseInt($("#qty_item_1").val().trim())+1;
			     var total_item_amt=parseFloat(parseFloat(price)*qty+transFee);
			     var total_prod_m=parseFloat(parseFloat(price)*qty);
				 showAmt(qty,total_item_amt,transFee,total_prod_m);
			}
		else 
			{
			  var prodId=$("#checkProdId").val();
			  if(prodId=="")
				  {
				    alert("请选择要购买的商品规格");
				  }
			  else
				  {
				       $(".xuanze").each(function(){
						if($(this).attr("prodId")==prodId)
							{
							 var price=parseFloat($(this).attr("price"));
							 var transFee=$(this).attr("transFee")==""?0:parseInt($(this).attr("transFee"));
							 var qty=$("#qty_item_1").val().trim()==""?1:parseInt($("#qty_item_1").val().trim())+1;
							 var total_item_amt=parseFloat(parseFloat(price)*qty+transFee);
							 var total_prod_m=parseFloat(parseFloat(price)*qty);
							 showAmt(qty,total_item_amt,transFee,total_prod_m);
							}
						
					});
				  }
			}
	}
	//减少商品
	function cut()
	{
		if(typeqty==1)
		{
		     var price=parseFloat($("#prodprice").val());
		     var transFee=parseInt($("#total_yunfei").html());
		     var qty=$("#qty_item_1").val().trim()==""?1:parseInt($("#qty_item_1").val().trim())-1;
		     if(qty<1){qty=1;}
		     var total_item_amt=parseFloat(parseFloat(price)*qty+transFee);
		     var total_prod_m=parseFloat(parseFloat(price)*qty);
		     showAmt(qty,total_item_amt,transFee,total_prod_m);
		}
		else 
			{
			 var prodId=$("#checkProdId").val();
			  if(prodId=="")
				  {
				    alert("请选择要购买的商品规格");
				  }
			  else
				  {
				       $(".xuanze").each(function(){
						if($(this).attr("prodId")==prodId)
							{
							 var price=parseFloat($(this).attr("price"));
							 var transFee=$(this).attr("transFee")==""?0:parseInt($(this).attr("transFee"));
							 var qty=$("#qty_item_1").val().trim()==""?1:parseInt($("#qty_item_1").val().trim())-1;
							 if(qty<1){qty=1;}
							 var total_item_amt=parseFloat(parseFloat(price)*qty+transFee);
							 var total_prod_m=parseFloat(parseFloat(price)*qty);
							 showAmt(qty,total_item_amt,transFee,total_prod_m);
							}
						
					});
				  }
			}
	}
	function inputqty()
	{
		var re = /^[0-9]+.?[0-9]*$/; 
		if (!re.test($("#qty_item_1").val().trim()))
			{
			$("#qty_item_1").val("1");
			}
		if(typeqty==1)
		{
		     var price=parseFloat($("#prodprice").val());
		     var transFee=parseInt($("#total_yunfei").html());
		     var qty=$("#qty_item_1").val().trim()==""?1:parseInt($("#qty_item_1").val().trim());
		     var total_item_amt=parseFloat(parseFloat(price)*qty+transFee);
			 showAmt(qty,total_item_amt,transFee);
		}
	else 
		{
		  var prodId=$("#checkProdId").val();
		  if(prodId=="")
			  {
			    alert("请选择要购买的商品规格");
			  }
		  else
			  {
			       $(".xuanze").each(function(){
					if($(this).attr("prodId")==prodId)
						{
						 var price=parseFloat($(this).attr("price"));
						 var transFee=$(this).attr("transFee")==""?0:parseInt($(this).attr("transFee"));
						 var qty=$("#qty_item_1").val().trim()==""?1:parseInt($("#qty_item_1").val().trim());
						 var total_item_amt=parseFloat(parseFloat(price)*qty+transFee);
						 showAmt(qty,total_item_amt,transFee);
						}
					
				});
			  }
		}
	}
	
		function checkDiYong(obj) {
			if ($(obj).prop("checked")) {
				$("#diyiong_info").show();
			} else {
				$("#diyiong_info").hide();
				$("#final_diyongamt").val("0");
				$("#diyongcount").val("0");
			}
		}

		
		
	
		function inputDiYongAmt(obj) {
			var dycount = $(obj).val().trim();
			var max_xhq_count =$("#max_xhq_count").html().trim();
			max_xhq_count = max_xhq_count == "" ? "0" : max_xhq_count;
			var re = /^[0-9]+.?[0-9]*$/;
			if (!re.test(dycount)) {
				$(obj).val("0");
			} else {
				if (parseInt(dycount) > parseInt(max_xhq_count)) {
					$(obj).val(max_xhq_count);
					dycount = max_xhq_count;
				}
				var transFee=parseInt($("#total_yunfei").html()==""?"0":$("#total_yunfei").html());
				var inputamt = parseFloat(parseInt(dycount) / 2);
				var prodamt = parseFloat($("#total_prod_amt").html());
				if (inputamt > prodamt+transFee) {
					$("#final_diyongamt").val(prodamt+transFee);
					$(obj).val(prodamt* 2);
				} else {
					$("#final_diyongamt").val(inputamt);
				}

			}
		}
		$("#shouhouB").click(function() {
			t=false;
			$("#shouhou").show();
			$("#pingjia,#miaoshu").hide();
			$(".tuwen li").removeClass("dangqian");
			$("#shouhouB").addClass("dangqian");
		});
		$("#miaoshuB").click(function() {
			t=false;
			$("#miaoshu").show();
			$("#pingjia,#shouhou").hide();
			$(".tuwen li").removeClass("dangqian");
			$("#miaoshuB").addClass("dangqian");
		});
		$("#pinglunB").click(function() {
			t=true;
			$("#pingjia").show();
			$("#miaoshu,#shouhou").hide();
			$(".tuwen li").removeClass("dangqian");
			$("#pinglunB").addClass("dangqian");
			if(pageNum==0)
				insertcode();
		});
		function viewDialog() {
			 $("#liuyan").fancybox({
			        'hideOnContentClick': true
			 });
		}
		function closefancybox() {
			jQuery.fancybox.close();
			$('#textMessage').val("");
		}
		function sendMessage() {
			var textMessage = $('#textMessage').val();
			var prodId = ${prod.productsId};
			if (textMessage=="") {
				alert("评论内容不能为空");
				closefancybox();
				return false;
			}
			if(textMessage.length<15){
				alert("评论长度不能少于15字");
				closefancybox();
				return false;
				
			}
			if(textMessage.length>200){
				alert("评论长度不能超过200字");
				closefancybox();
				return false;
			}
			var url ='<%=request.getContextPath()%>/comment/commentAction!saveCommet.action';
			$.ajax({
				cache: true,
				type: "POST",
				url:url,
				data:"prodId="+prodId+"&textMessage="+textMessage,// 你的formid
				async: false,
			    error: function(request) {
			        alert("网络出现问题稍等再试!!!");
			    },
			    success: function(data) {
			    	if(data){
			    		alert("评论成功");
			    		$('#textMessage').val("");
			    		jQuery.fancybox.close();
			    		window.location.href="<%=request.getContextPath()%>/pay/payGoodAction!toMianMoBuy.action?prodId=${prod.productsId}";
			    	}
			    }
			});
		}
		 
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
		 function insertcode(){
			    /*加载内容*/
				$.ajax({
					type: "POST",
					url:"<%=request.getContextPath()%>/comment/commentAction!ajaxcommentList.action",
						data : {
							"prodId" : '${prod.productsId}',
							"pageNum" : pageNum
						},
						async : false,
						error : function(request) {
							alert("网络出现问题稍等再试!!!");
						},
						dataType : "json",
						success : function(data) {
							if (data.success == true) {
								var children = data.children;
								var child = '';
								for (var i = 0; i < children.length; i++) {
									var time = new Date(
											children[i].createtime.time)
											.Format("yyyy-MM-dd hh:mm:ss");
									child += ('<li>');
									child += ('<div class="assess-name">');
									child += ('<span><em>'
											+ children[i].userName + '</em></span>');
									child += ('<span><em>' + time + '</em></span></div>');
									child += ('<div class="assess-con">');
									child += ('<p><em>' + children[i].comment + '</em></p></div>');
									child += ('</li>');
								}
								var ul = $("#appendUl");
								ul.append(child);
							} else {
								length = data.length;
								alert("已经加载完毕!");
							}
						}
					});
			pageNum++;
		}
	</script>
</body>
</html>