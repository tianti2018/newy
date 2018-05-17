<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.ResourceBundle"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<%ResourceBundle res = ResourceBundle.getBundle("system"); %> 
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">
	<title><%=res.getString("company")%>商城</title>
	
<%-- 	 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/mui.min.css"/> --%>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/novipbuy.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
	
	<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.mobile-1.4.5.min.js"></script> --%>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/family.css" />
	<link href="<%=request.getContextPath()%>/css/style-metro.css" rel="stylesheet" type="text/css"/>
	 
</head>
<body>
	<!-- wrapper start -->
	<div class="wrapper">
		<!-- 头部产品图片 -->
 <link href="<%=request.getContextPath()%>/css/flexslider.css" rel="stylesheet" />
  <div id="banner">
    <div class="flexslider"> 
      <ul class="slides"> 
         <li onclick="javascript:chooseUrl(1,1);"><img src="<%=request.getContextPath()%>/images/jifenImg/ban11.jpg" /></li>
        <li onclick="javascript:chooseUrl(1,2);"><img src="<%=request.getContextPath()%>/images/jifenImg/ban12.jpg" /></li>
        <li onclick="javascript:chooseUrl(1,3);"><img src="<%=request.getContextPath()%>/images/jifenImg/ban13.jpg" /></li>
      </ul> 
    </div>
  </div>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.flexslider-min.js"></script>
  
 <script>
  $(function(){
    
    $(".flexslider").flexslider({
        animation: "slide",
        directionNav: false,
        touch: true
    });
    
  });
  function chooseUrl(type,prodId){
	  if(type==1){
		  window.location.href="<%=request.getContextPath()%>/product"+type+prodId+".jsp";
	  }else if(type==2){
		  window.location.href="<%=request.getContextPath()%>/companyInfo/companyInfoAction!editPage.action";
	  }else if(type==3){
		  window.location.href="<%=request.getContextPath()%>/user/userAction!queryLiuBuy.action";
	  }else if(type==4){
		  window.location.href="<%=request.getContextPath()%>/pay/payGoodAction!toGoodBuy.action";
	  }else if(type==5){
		  window.location.href="<%=request.getContextPath()%>/pay/payGoodAction!toJifenBuy.action?prodId="+prodId;
	  }
 }
  
  </script>
		<form >
		<div class="buy_cont">
			<div class="number">
				<p><c:out value="${prod.prodName}"/></p>
				<%-- <div class="reduce_add">
				  
					<a class="reduce" onclick="cut()" href="javascript:void(0)"><img src="<%=request.getContextPath()%>/images/novipbuy/reduce.png" alt=""></a>
				 
				    <input type="text" name="qty_item_1" value="1" id="qty_item_1" onblur="inputqty()" class="text" />
				    
				    <a class="add" onclick="add()" href="javascript:void(0)"><img src="<%=request.getContextPath()%>/images/novipbuy/add.png" alt=""></a>
			         
			    </div> --%>
			</div>
			<div class="price">
				<p>购物车总计</p>
					<div class="total">
					
					<br><span>商品：</span>
					<c:forEach var="p" items="${typelist}" varStatus="vstatus">
					
					<br><br>
					<label>
					 <input type="radio" name="product" stock="${p.stock}" transFee="${p.transFee}" prodId="${p.prodId}" price="${p.score}" onclick="checkProd(this)"/>
					<span id="price_item_1">价格：
						<c:out value="${p.price }"/>
					<del id="orgin_price"><font color="#333">￥
					<c:out value="${p.orginPrice }"/>
					</font></del>
					
					</span>
					<span style="padding-left:12px;">规格：<c:out value="${p.prodCode}${p.prodColor}${p.prodSize}"/></span>
					 <c:if test="${p.stock<500}">
				   <span> 库存：<c:out value="${p.stock }"/> 件</span>   
				   </c:if>
				   </label>
					</c:forEach>
					
					<br><span>商品总价：<c:out value="${prod.price }"/></span><span class="total-font" id="total_item_1"></span>
					<span class="total_txt">运费：</span><span class="total-font" id="total_yunfei"><c:out value="${prod.transFee}"/>元</span>
			        <input type="hidden" name="total_price" id="total_price" value="" class="total-font"/>
				<c:if test="${typeqty>1}">
				 <input type="hidden" id="checkProdId" value=""/>
				 </c:if>
				 <c:if test="${typeqty==1}">
				 <input type="hidden" id="checkProdId" value="${prod.prodId}"/>
				  <input type="hidden" id="prodprice" value="${prod.score}"/>
				   <input type="hidden" id="prodtransFee" value="${prod.transFee}"/>
				   <input type="hidden" id="prodstock" value="${prod.stock}"/>
				 </c:if>
				</div>
			</div>
		</div>
		</form>
		<div class="message">
		<div style="text-align: center;font-size: 20px"><input type="checkbox" id="tongyixieyi" onclick="tongyi()" style="width:50px;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/xieyi.html" style="text-decoration: underline;">同意《星火草原协议》</a></div>
			<c:if test="${null!=orderAddress}">
				
				 <ul class="menu-list">
				<li>
				   <a href="javascript:;"><i class="arrows rotated"></i><em>收货信息</em></a> 
					<ul class="sub-menu" style="height:180px">
					    <li><a href="<%=request.getContextPath()%>/orderAddress/orderAddressAction!orderAddress.action" style="background:#00aa3a; display:inline-block; color:#fff;">选择默认收货地址</a></li>
						<li>收货人：<em>${orderAddress.userName}</em></li>
						<li>联系方式：<em>${orderAddress.mobile} </em></li>
						<li>收货地址：<em>${orderAddress.address}</em></li>
					</ul>
				</li>
					
					<li><a href="#" onclick="javascript:orderBuy()" class="btn green">立即购买</a></li>	
			</ul> 

			</c:if>
			<c:if test="${null==orderAddress}">
				<div align="center">
					<a href="<%=request.getContextPath()%>/orderAddress/orderAddressAction!orderAddress.action"  class="btn green" onclick="go();">选择一条收货地址</a>
				</div>
			</c:if>
	
		</div>
	</div>
	<!-- footer start -->
	<%-- <footer class="footer">
		<div class="foot-nav">
			<a href="<%=request.getContextPath()%>/pay/payDianAction!dianzhuBuy.action"  class="nowpage"><i class="foot-icon"><img src="../images/i_buy.png" alt=""></i><span>成为店主</span></a>
			<a href="<%=request.getContextPath()%>/order/orderAction!orderPerList.action"><i class="foot-icon"><img src="../images/i_orders.png" alt=""></i><span>我的订单</span></a>
			<a href="<%=request.getContextPath()%>/user/userAction!phoneFamily.action"><i class="foot-icon"><img src="../images/i_family.png" alt=""></i><span>个人中心</span></a>
			<a href="<%=request.getContextPath()%>/user/userAction!qrcodePage.action"><i class="foot-icon"><img src="../images/i_erweima.png" alt=""></i><span>我的二维码</span></a>
		</div><!-- /yingjun/src/main/webapp/images/i_buy.png -->
	</footer> --%>
	<script type="text/javascript">
	
	$(document).ready(function(){
		if("${user.level}"!=""&&parseInt("${user.level}")>0){
			$("#tongyixieyi").attr('checked',"checked");
		}
	});
	
	
	function tongyi(){
		if(document.getElementById('tongyixieyi').checked==true){
			$("#btn_submit").removeAttr('disabled');
		}else{
			$("#btn_submit").attr('disabled',"disabled");
		}
	}
		function orderBuy(){
			var str ='2016-04-04 05:00:00';
			str = str.replace(/-/g,"/");
			var end = '2016-04-04 08:00:00';
			end = end.replace(/-/g,"/");
			var date = new Date(str);
			var endDate = new Date(end);
			if(date < new Date() && new Date() < endDate){
				alert("微信系统升级维护中,请"+end+"以后再次操作!");
				return false;
			}
			if(document.getElementById('tongyixieyi').checked==false){
				alert("请阅读并勾选<<星火草原购买协议>>");
				return false;
			}
			if("${user.level}"=="3"){
				alert("您已经是VIP会员,无需购买本产品!");
				return false;
			}
			
			var num = $("#qty_item_1").val();
			var stock=$("#prodstock").val()==""?"0":$("#prodstock").val();
			var prodId=$("#checkProdId").val();
			if(num <=0){
				alert("至少购买一套!");
				return false;
			}
			else if(parseInt("${prod.limitNum}")>0 && num>parseInt("${prod.limitNum}"))
			{
				alert("由于本产品倾销过快,防止厂家出现货源问题,所以最多只能购买${prod.limitNum}件,敬请谅解!");
				return false;
			}
			/* else if(parseInt(num)>parseInt(stock))
				{
				  alert("库存不够");
				  return false;
				} */
			else if(prodId=="")
				{
				  alert("请选择购买商品");
				  return false;
				}
			
			
				if(confirm("请确认您的地址够详细,联系方式能联系到您!")){
					if(confirm("请再次确认您的收货地址是:${orderAddress.address},联系电话是:${orderAddress.mobile}")){
						$.ajax({
					        type:"POST",
					        url:"<%=request.getContextPath()%>/pay/payHuiYuanAction!savehuiyuanOrder.action",
					        data : {
					        	"qty_item_1":num,
					        	"orderAddRessId":"${orderAddress.id}",
					        	"prodId":prodId
					        	},
					        dataType:"json",
					        success:function(data) {
					       	 	if(data.success){
					       	 		moneyPay(data.ordersBh);
					       	 	//alert(data.ordersBh);
					       	 	}else{
					       	 		alert("您已经是vip会员无法购买本产品!");
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
				}
				
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
   	 		        url:"<%=request.getContextPath()%>/pay/payHuiYuanAction!ajaxWxPay.action?m="+new Date(),
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
	
	
	
	
	function checkProd(obj)
	{
		var yunfei=$(obj).attr("transFee")==""?0:parseInt($(obj).attr("transFee"));
		var qty=$("#qty_item_1").val().trim()==""?1:parseInt($("#qty_item_1").val().trim());
		var total_item_amt=parseFloat(parseFloat($(obj).attr("price"))*qty);
		showAmt(qty,total_item_amt,yunfei);
		$("#checkProdId").val($(obj).attr("prodId"));
		$("#prodstock").val($(obj).attr("stock"));
	}
	function showAmt(qty,total_item_amt,transFee)
	{
		     $("#qty_item_1").val(qty);
		     $("#total_item_1").html(total_item_amt);
			 $("#total_price").html(total_item_amt);
			 $("#total_yunfei").html(transFee);
	}
	//增加商品
	var typeqty=parseInt("${typeqty}");
	function add()
	{
		if(typeqty==1)
			{
			     var price=parseFloat($("#prodprice").val());
			     var transFee=parseInt($("#prodtransFee").val());
			     var qty=$("#qty_item_1").val().trim()==""?1:parseInt($("#qty_item_1").val().trim())+1;
			     var total_item_amt=parseFloat(parseFloat(price)*qty);
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
				       $("input[type=radio]").each(function(){
						if($(this).attr("prodId")==prodId)
							{
							 var price=parseFloat($(this).attr("price"));
							 var transFee=$(this).attr("transFee")==""?0:parseInt($(this).attr("transFee"));
							 var qty=$("#qty_item_1").val().trim()==""?1:parseInt($("#qty_item_1").val().trim())+1;
							 var total_item_amt=parseFloat(parseFloat(price)*qty);
							 showAmt(qty,total_item_amt,transFee);
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
		     var transFee=parseInt($("#prodtransFee").val());
		     var qty=$("#qty_item_1").val().trim()==""?1:parseInt($("#qty_item_1").val().trim())-1;
		     if(qty<1){qty=1;}
		     var total_item_amt=parseFloat(parseFloat(price)*qty);
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
				       $("input[type=radio]").each(function(){
						if($(this).attr("prodId")==prodId)
							{
							 var price=parseFloat($(this).attr("price"));
							 var transFee=$(this).attr("transFee")==""?0:parseInt($(this).attr("transFee"));
							 var qty=$("#qty_item_1").val().trim()==""?1:parseInt($("#qty_item_1").val().trim())-1;
							 if(qty<1){qty=1;}
							 var total_item_amt=parseFloat(parseFloat(price)*qty);
							 showAmt(qty,total_item_amt,transFee);
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
		     var transFee=parseInt($("#prodtransFee").val());
		     var qty=$("#qty_item_1").val().trim()==""?1:parseInt($("#qty_item_1").val().trim());
		     var total_item_amt=parseFloat(parseFloat(price)*qty);
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
			       $("input[type=radio]").each(function(){
					if($(this).attr("prodId")==prodId)
						{
						 var price=parseFloat($(this).attr("price"));
						 var transFee=$(this).attr("transFee")==""?0:parseInt($(this).attr("transFee"));
						 var qty=$("#qty_item_1").val().trim()==""?1:parseInt($("#qty_item_1").val().trim());
						 var total_item_amt=parseFloat(parseFloat(price)*qty);
						 showAmt(qty,total_item_amt,transFee);
						}
					
				});
			  }
		}
	}
	</script>
</body>
</html>