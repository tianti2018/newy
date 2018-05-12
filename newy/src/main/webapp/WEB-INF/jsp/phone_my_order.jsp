<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>

<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">
	<title>我的订单</title>
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
var pageNum=0;
function insertcode(){
	pageNum++;
   	var date1 = $("#date1").val();
   	var date2 = $("#date2").val();
   	var fahuoTime="";
   	var createTime="";

    /*加载内容*/
	$.ajax({
		type: "POST",
		url:"<%=request.getContextPath()%>/order/orderAction!ajaxOrderPerList.action",
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
	    	//var aa = eval(data);
	    	if (data.success==true) {
	    		var children = data.children;
	    		var child = '';
	    		for(var i=0;i<children.length;i++){
	    			if(children[i][4]!=null){
	    			  var createTime= new Date(children[i][4].time).Format("yyyy-MM-dd hh:mm:ss");
		    		}
	    			if(children[i][22]!=null){
	    			  var fahuoTime= new Date(children[i][22].time).Format("yyyy-MM-dd hh:mm:ss");
		    		}
	    			child+=(' <div class="my_order">');
	    			child+=('<div class="item" onclick="zhankai('+children[i][0]+')">');    
	    			child+=('<div class="item_t">');
	    			if(children[i][13].length<10){
	    			child+=('<em>'+children[i][13]+'</em></div>');
	    			}else{
	    			child+=('<em>'+children[i][13].substring(0,10)+'...</em></div>');	
	    			}
	    			child+=('<div class="item_m" id="item_m'+children[i][0]+'"><ul>');
	    			child+=('<li>订单类型：<em>');
	    			if(children[i][11]==1){
                       child+=('秒杀订单');
		    		}else if(children[i][11]==2){
                       child+=('积分订单');
			    	}else if(children[i][11]==3){
                       child+=('会员专区订单 ');
			    	}else if(children[i][11]==4){
                       child+=('店主专区订单 ');
			    	}else if(children[i][11]==5){
                       child+=('星火券订单 ');
			    	}
	    			child+=('</em></li>');
	    			child+=('<li>订单编号：<em>'+children[i][12]+'</em>');
	    			if(children[i][10]==0&&children[i][26]==1){
	    				child+=('<em style="float: right;">');
	    				child+=('<input type="button" onclick="cancelOrder(this)"');
	    				child+=(' orderId="children[i][0]" value="取消订单"/></em>');
	    			}
	    			child+=('</li>');
	    			child+=('<li>订单时间：<em>'+createTime+'</em></li>');
	    			child+=('<li>下单人ID：<em>'+children[i][20]+'</em></li>');
	    			child+=('<li>收货人：<em>'+children[i][18]+'</em></li>');
	    			child+=('<li>收货地址：<em>'+children[i][1]+'</em></li>');
	    			child+=('<li>收货电话：<em>'+children[i][8]+'</em></li>');
	    			child+=('<li>订单金额：<em>'+(children[i][9]==null?0:children[i][9])+'</em></li>');
	    			child+=('<li>支付状态：<em>');
	    			if(children[i][25]==1){
	    				child+=('已支付');
	    			}else if(children[i][25]==0){
	    				child+=('未支付');
	    			}
	    			else if(children[i][25]==1){
	    				child+=('已退款');
	    			}
	    			child+=('</em></li>');
	    			
	    			
	    			child+=('<li>处理状态：<em>');
	    			var fahuo = false;
	    			if(children[i][10]==0){
	    				child+=('备货中');
	    			}
	    			if(children[i][10]==11){
	    				child+=('已取消');
	    			}
	    			else if(children[i][10]==1){
	    				child+=('发货中');
	    			}else if(children[i][10]==2){
	    				child+=('已发货');
	    				fahuo = true;
	    			}else if(children[i][10]==3){
	    				child+=('已退货');
	    			}else if(children[i][10]==4){
	    				child+=('已收货');
	    				fahuo = true;
	    			}
	    			child+=('</em></li>');
	    			
	    			
	    			if(fahuo){
	    				
		    			child+=('<li>快递公司：<em>');
	                    if(children[i][5]==0){
	                       child+=('圆通');
	                    }else if(children[i][5]==1){
	                       child+=('顺丰');
	                    }else if(children[i][5]==2){
	                       child+=('申通');
	                    }else if(children[i][5]==3){
	                       child+=('中通');
	                    }else if(children[i][5]==4){
	                       child+=('韵达');
	                    }else if(children[i][5]==5){
	                       child+=('EMS');
	                    }else if(children[i][5]==6){
	                       child+=('宅急送');
	                    }else if(children[i][5]==7){
	                       child+=('全峰');
	                    }else if(children[i][5]==8){
	                       child+=('天天快递');
	                    }else if(children[i][5]==9){
	                       child+=('自提');
	                    }else if(children[i][5]==10){
	                       child+=('国通快递');
	                    }else if(children[i][5]==11){
	                       child+=('邮政');
	                    }else if(children[i][5]==12){
	                       child+=('优速快递');
	                    }else if(children[i][5]==13){
	                       child+=('德邦快递');
	                    }else if(children[i][5]==14){
	                       child+=('百世汇通');
	                    }
		    			child+=('</em></li>');
		    			child+=('<li>快递单号：<em>'+children[i][6]+'</em><input type="button" value="  查看物流 " onclick="serch('+children[i][6]+')"/></li>');
		    			child+=('<li>发货日期：<em>'+(fahuoTime==null?null:fahuoTime)+'</em></li>');
	    			}
	    			child+=('<li>支付积分：<em>'+(children[i][15]==null?0:children[i][15])+'</em></li>');
	    			child+=('<li>购买数量：<em>'+(children[i][16]==null?1:children[i][16])+'</em></li>');
	    			child+=('<li>星火券：<em>'+(children[i][24]==null?0:children[i][24])+'</em></li></ul></div></div></div>');
                    
	    		}
	    		var ul = $("#appendUl");
	    		ul.append(child);
	    		
	    	}else{
	    		alert("已经加载完毕!");
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
			insertcode();
		}             
    });
});
 //取消订单
function cancelOrder(obj){
	 if(confirm("取消订单将会收取一定的手续费,确定取消吗?")){
		 
		 $.ajax({
		        type:"POST",
		        url:"<%=request.getContextPath()%>/order/orderAction!cancelOrder.action?m="+new Date(),
		        data : {"orderId":$(obj).attr("orderId")},
		        success:function(data) {
		  		 if(data.success==true) {
	 					 $(obj).val("订单取消成功");
	  				}
		  		 else if(data.overtime==true)
		  		{
		  			  alert("订单已经确认,厂家已安排发货！");
		  	    }
		  		 else
		  			 {
		  			   alert("系统异常，请联系客服");
		  			 }
			 },
			 beforeSend : function() {
				 $(obj).val("正在取消订单");
				 $(obj).attr('disabled',"disabled");
				}
		 });
	 }
	 

}


</script>
	<!-- wrapper start -->
	
	<div class="wrapper" id="appendUl">
		<!-- 头部个人信息 start -->
		<%@ include file="/WEB-INF/jsp/order_head.jsp"%>
			<form method="post" action="orderAction!orderPerList.action?orderNo=0&requestType=mo & date1=document.getElementById('date1').value & date2=document.getElementById('date2').value">
            <div class="my_order_sear">
            <input type="date" class="input" id="date1" name="date1"><i>-</i><input type="date" class="input" id="date2" name="date2">
            <input type="submit" class="btn" value="搜索"></div>
			</form><br>
			<!-- 推荐 -->
			
			<!-- 等待收货 -->

			<c:forEach items="${myfhOrder}" var="order" varStatus="status">
					<div class="my_order">
						<div class="item">
							<div class="item_t">
								<c:if test="${fn:length(order[13]) >10}">
								<em>${fn:substring(order[13],0,10) }...</em>
								</c:if>
								<c:if test="${fn:length(order[13]) <10}">
								<em>${order[13]}</em>
								</c:if>
							</div>
							<div class="item_m">
								<ul>
									<li>订单类型： <em> <c:if test="${order[11]==1 }">秒杀订单</c:if>
											<c:if test="${order[11]==2 }">积分订单</c:if> <c:if
												test="${order[11]==3 }">会员专区订单 </c:if> <c:if
												test="${order[11]==4 }">店主专区订单 </c:if> <c:if
												test="${order[11]==5 }">星火券订单</c:if>
									</em>
									</li>
									<li>订单编号：<em>${order[12] }</em> <c:if
											test="${order[10]==0 && order[26]==1 }">
											<em style="float: right;">
												<input type="button" onclick="cancelOrder(this)"
													orderId="${order[0]}" value="取消订单"/>
											</em>
										</c:if>
									</li>
									<li>订单时间：<em>${order[4]}</em></li>
									<li>下单人ID：<em>${order[20]}</em></li>
									<li>收&nbsp;货&nbsp;人&nbsp;：<em>${order[18]}</em></li>
									<li>收货地址：<em>${order[1]}</em></li>
									<li>收货电话：<em>${order[8]}</em></li>
									<li>订单金额：<em>${order[9]+0}元</em></li>
									<li>支付状态： <em> <c:if test="${order[26]==1 }">已支付</c:if>
									</em>
									</li>

									<li>订单状态： <em> <c:if test="${order[10]==11 }">已取消</c:if>
											<c:if test="${order[10]==0}">支付确认中</c:if> <c:if
												test="${order[10]==1 }">发货中</c:if> <c:if
												test="${order[10]==2 }">已发货</c:if> <c:if
												test="${order[10]==3 }">已退货</c:if> <c:if
												test="${order[10]==4 }">已收货</c:if>
									</em>
									</li>


									<c:if test="${order[10]>1 }">
										<li>快递公司： <em> <c:if test="${order[5]==0 }">圆通</c:if>
												<c:if test="${order[5]==1 }">顺丰</c:if> <c:if
													test="${order[5]==2 }">申通</c:if> <c:if
													test="${order[5]==3 }">中通</c:if> <c:if
													test="${order[5]==4 }">韵达</c:if> <c:if
													test="${order[5]==5 }">EMS</c:if> <c:if
													test="${order[5]==6 }">宅急送</c:if> <c:if
													test="${order[5]==7 }">全峰</c:if> <c:if
													test="${order[5]==8 }">天天快递</c:if> <c:if
													test="${order[5]==9 }">自提</c:if> <c:if
													test="${order[5]==10 }">国通快递</c:if> <c:if
													test="${order[5]==11 }">邮政</c:if> <c:if
													test="${order[5]==12 }">优速快递</c:if> <c:if
													test="${order[5]==13 }">德邦快递</c:if> <c:if
													test="${order[5]==14 }">百世汇通</c:if>
										</em>
										</li>
										<li>快递单号：<em>${order[6]}</em> <input type="button"
											value="  查看物流 " onclick="serch('${order[6]}')" /></li>
										<li>发货日期：<em>${order[22]}</em></li>
									</c:if>
									<li>支付积分：<em>${order[15]+0}</em></li>
									<li>购买数量：<em>${order[16]+0}</em></li>
									<li>星&nbsp;火&nbsp;券&nbsp;：<em>${order[24]+0}</em></li>
								</ul>
							</div>
						</div>
					</div>
		</c:forEach>
	</div>
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
		
	})
	/* 
	var $sub_menu = $(this).next(".sub-menu");
 	if($sub_menu.height() == 0){
	 	var sub_h = $sub_menu.find("li").length * 46;
	 	$sub_menu.animate({"height":sub_h});		//添加动画
		$(this).find(".arrows").addClass("rotated");
	} 
	*/
		
		function qrshsubmit(){
			$("#frmList").submit();
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
		
		<%-- $(function(){
			$('#search_btn').click(function(){
				var selCom = "";
				var num = "";
				// var num = "550286779199";
				var callbackurl = "http://localhost:8080/newz/order/orderAction!orderPerList.action?orderNo=0&requestType=mo";
				window.location.href = 'http://m.kuaidi100.com/index_all.html?type='+selCom+'&postid='+num+'&callbackurl='+callbackurl;
			});
		}); --%>
		
		//删除地址
		function serch(orderid){
				var selCom = "";
				var num = "";
				//var num = orderid;
				// var num = "550286779199";
				var callbackurl = "http://localhost:8080/newz/order/orderAction!orderPerList.action?orderNo=0&requestType=mo";
				window.location.href = 'http://m.kuaidi100.com/index_all.html?type='+selCom+'&postid='+num;
		}
	</script>
</body>
</html>
