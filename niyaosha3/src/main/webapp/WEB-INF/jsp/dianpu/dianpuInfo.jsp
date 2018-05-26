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
	<title>我的店铺</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/user.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/family.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttons.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.s.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/my_v2.s.min.css">	
	<link href="<%=request.getContextPath()%>/css/wallet_v2.s.min.css" rel="stylesheet" />
	<link href="<%=request.getContextPath()%>/css/footer.css" rel="stylesheet" />
	<link href="http://cdn.bootcss.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/css/style-metro.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/source/jquery.fancybox.css?v=2.1.5" media="screen" />
	<link href="<%=request.getContextPath()%>/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet">
	<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js" type="text/javascript"></script>
	<style type="text/css">
		.wet_app_panel{min-height: 40px;}
        .wet_mat_panel{min-height: 100px;}
        .WX_backtop_active {display: block;bottom:60px;}
    </style>
</head>

<body>

<script type="text/javascript">
</script>
	<header class="my_header" id="myHeader">
         <div class="face"><a href="#">
         		<img id="headUrl" id="headPic" src="${userVo.usery.headUrl }" >
         </a></div>
    </header>
    <div class="my_header_links">
    	<c:if test="${dianpu!=null }">
	        <a href="javascript:showProducts(1);"><span >&nbsp;</span><span >已上架</span></a>
	        <a href="javascript:showProducts(0);"><span >&nbsp;</span><span >未上架</span></a>
        </c:if>
        <a href="#inline" onclick="viewDialog();" id="dianpuxinxi"><span >&nbsp;</span><span >店铺信息</span></a>
    </div>
	<!-- wrapper start -->
	<div style="width: 100%">
		<div class="panel panel-primary" id="inline" style="display: none">
			<div class="panel-heading">
				<h3 class="panel-title">店铺信息</h3>
			</div>
			<div class="panel-body">
				<div>
					<div class="form-group">
						<input type="hidden" id="dianpuId" name="dianpuId" value =""/>
						<input type="hidden" id="productId" name="productId" value =""/>
						<input type="hidden" id="ketiao" name="ketiao" value =""/>
						店铺名称:<input style="width: 90%" class="form-control" id="dianpuName" name="dianpuName" 
							value="<c:if test="${dianpu.name==null}">${userVo.usery.userName }的店铺</c:if>
								<c:if test="${dianpu.name!=null}">${dianpu.name }</c:if> "/>
						店铺简介:<textarea style="width: 90%" class="form-control" rows="4"  id="dianPuJianJie" name="dianPuJianJie">${dianpu.jianjie }</textarea>
					</div>
				</div>
				<div style="margin:0px auto;text-align:center;">
					<a class="button button-pill button-primary" href="#" role="button"
						onclick="saveOrUpdate();">保存</a> 
					<a	class="button button-pill button-primary" href="#" role="button"
						onclick="closefancybox();">关闭</a>
				</div>
	
			</div>
		</div>
		<div class="panel panel-primary" id="inline_shangjia" style="display: none">
			<div class="panel-heading">
				<h3 class="panel-title">产品上架</h3>
			</div>
			<div class="panel-body">
				<div>
					<div class="form-group">
						<c:if test="${userVo.usery.level>=1}">
						产品售价:<input style="width: 90%" class="form-control" id="dianpuPrice" name="dianpuPrice" onchange="yanzheng()"/>
						</c:if>
						
						展示方式:<select style="width: 90%" class="form-control" rows="4"  id="type" name="type">
									<option value="2">普通展示</option>
									<option value="1">横幅展示</option>
									<option value="0">轮播展示</option>
								</select>
						排序:<input style="width: 90%" class="form-control" id="paixu" name="paixu" onchange="shuzi()"/>
					</div>
				</div>
				<div style="margin:0px auto;text-align:center;">
					<a class="button button-pill button-primary" href="#" role="button"
						onclick="querenShangjia();">确定</a> 
				</div>
	
			</div>
		</div>
	</div>
	<c:if test="${dianpu!=null }">
	<div class="wet_space"></div>
	<div id="channelFloor">
	
		<div class="wet_section" id="cnxhShow">
			<div class="wet_pro_panel wet_pro_v2_panel">
				<div class="wet_pro_list" id="product_item">
				</div>
			</div>
		</div>
	</div>
	</c:if>
	
	<%@ include file="/WEB-INF/jsp/footer.jsp"%>
	<script src="<%=request.getContextPath()%>/bootstrap-3.3.5/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/css/source/jquery.fancybox.pack.js?v=2.1.5"></script>
	<script type="text/javascript">
	var ketiaoJia = 0;
	function shuzi() {
		var reg = new RegExp("^[0-9]*$");  
	    if(!reg.test($("#paixu").val())){  
	        alert("请输入正整数!");  
	        $("#paixu").val("");
	    }  
	    /* if(!/^[0-9]*$/.test(obj.value)){  
	        alert("请输入数字!");  
	    }   */
	}
	function yanzheng() {
		var dianpuPrice=$("#dianpuPrice").val()
		var reg = /^\d+(\.\d+)?$/;  
	    if(!reg.test(dianpuPrice)){  
	        alert("请输入数字!");
	        $("#dianpuPrice").val("");
	    }  
	    /* if(!/^[0-9]*$/.test(obj.value)){  
	        alert("请输入数字!");  
	    }   */
	}
	
	function shangjia(dianpuId,productId,ketiao){
		 $("#shangjia").fancybox({
		        'hideOnContentClick': true
		 });
		 $("#productId").val(productId);
		 $("#dianpuId").val(dianpuId);
		 $("#ketiao").val(ketiao);
		 ketiaoJia = ketiao;
	}
	function querenShangjia(){
		var dianpuPrice = $("#dianpuPrice").val();
		$.ajax({
			type: "POST",
			url:"<%=request.getContextPath()%>/dianpu/dianpuAction!shangjia.action",
			data:{
				"dianpuId":$("#dianpuId").val(),
				"productId":$("#productId").val(),
				"type":$("#type").val(),
				"paixu":$("#paixu").val(),
				"dianpuPrice":dianpuPrice
			},
		    error: function(request) {
		        alert("网络出现问题稍等再试!!!");
		    },
		    dataType:"json",
		    success: function(data) {
		    	if(data.success){
		    		closefancybox();
		    		if(data.message!=null&&data.message!=""){
		    			alert(data.message);
		    		}
		    		$("#weishang_"+$("#productId").val()).remove();
		    	}else{
		    		alert(data.message);
		    	}
		    }
		});
	}
	function xiajia(id){
		$.ajax({
			type: "POST",
			url:"<%=request.getContextPath()%>/dianpu/dianpuAction!xiajia.action",
			data:{
				"pdId":id,
			},
		    error: function(request) {
		        alert("网络出现问题稍等再试!!!");
		    },
		    dataType:"json",
		    success: function(data) {
		    	if(data.success){
		    		alert("下架成功!");
		    		$("#yishang_"+id).remove();
		    	}else{
		    		alert("该商品已经下架!");
		    	}
		    }
		});
	}
	var pageNum = 1;
	var shangjiaType = 1;
	function showProducts(type){
		$("#product_item").html("");
		pageNum = 1;
		shangjiaType=type;
		showProductsAjax(type);
	}
	function showProductsAjax(type) {
		
		var url = "";
		if(type==1){
			url = "<%=request.getContextPath()%>/dianpu/dianpuAction!yishangAjax.action";
		}
		if(type==0){
			url = "<%=request.getContextPath()%>/dianpu/dianpuAction!weishangAjax.action";
		}
		var ul = $("#product_item");
	    /*加载内容*/
		$.ajax({
			type: "POST",
			url:url,
			data:{
				"dianpuId":'${userVo.usery.dianPuId }',
				"pageNum":pageNum,
			},
		    error: function(request) {
		        alert("网络出现问题稍等再试!!!");
		    },
		    dataType:"json",
		    success: function(data) {
		    	if (data!=null) {
		    		pageNum++;
		    		var child = '';
		    		if(type==1){
			    		for(var i=0;i<data.length;i++){
			    			child+='<div id=yishang_'+data[i].id+' class="item">';
			    			child+='<a class="url">';
			    			child+='<div class="img">';
			    			child+='<img alt="" src="'+data[i].headUrl +'" loaded="117">';
			    			child+='</div>';
			    			child+='</a>';
			    			child+='<div class="info">';
			    			child+='<div class="name" style="color: red;">'+data[i].name+'<span style="color: blue;float:right">';
			    			if(data[i].type==0){
			    				child+='轮播展示';
			    			}
			    			if(data[i].type==1){
			    				child+='横幅展示';
			    			}
							if(data[i].type==2){
								child+='普通展示';
			    			}
			    			child+='</span></div>';
			    			child+='<div class="cost">';
			    			child+='<span class="price">¥'+data[i].price +'</span>';
			    			child+='<del>¥'+data[i].jianyiPrice +'</del>';
			    			child+='<div onclick="javascript:xiajia('+data[i].id +');" class="btn_relate" >下架</div> ';
			    			child+='</div></div></div>';
			    		}
		    		}
		    		if(type==0){
		    			for(var i=0;i<data.length;i++){
			    			child+='<div id=weishang_'+data[i].productsId+' class="item">';
			    			child+='<a href="<%=request.getContextPath()%>/products/productsAction!product.action?prodId='+data[i].productsId +'" class="url">';
			    			child+='<div class="img">';
			    			child+='<img alt="" src="'+data[i].headUrl +'" loaded="117">';
			    			child+='</div>';
			    			child+='</a>';
			    			child+='<div class="info">';
			    			child+='<div class="name" style="color: red;">'+data[i].name+'</div>';
			    			child+='<div class="cost">';
			    			child+='<span class="price">¥';
			    			if('${userVo.usery.level>=2}'=='true'){
			    				child+=data[i].leveltwo +'</span>';
			    				child+='<del>¥'+data[i].price +'</del>';
			    			}else if('${userVo.usery.level==1}'=='true'){
			    				child+=data[i].levelone +'</span>';
			    				child+='<del>¥'+data[i].price +'</del>';
			    			}else{
			    				child+=data[i].price +'</span>';
			    			}
			    			child+='<div href="#inline_shangjia" id="shangjia" onclick="javascript:shangjia(${userVo.usery.dianPuId },'+data[i].productsId +','+data[i].ketiao+');" class="btn_relate" >上架</div> ';
			    			child+='</div></div></div>';
			    		}
		    		}
		    		ul.append(child);
		    	}else{
		    		alert("已经加载完毕!");
		    	}
		    }
		});
		
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
			var a = document.documentElement.clientHeight ;
			var b = document.documentElement.scrollTop==0? document.body.scrollTop : document.documentElement.scrollTop;
			var c = document.documentElement.scrollTop==0? document.body.scrollHeight : document.documentElement.scrollHeight;
			/*判断是否滑动到达底部**/
			if(a+b==c&&b!=0){
				//alert(s);
				//alert(a+" "+b+" "+c);
				showProductsAjax(shangjiaType);
			}             
	    });
		showProductsAjax(shangjiaType);
	});
	function closefancybox() {
		jQuery.fancybox.close();
	}
	
	function viewDialog() {
		 $("#dianpuxinxi").fancybox({
		        'hideOnContentClick': true
		 });
	}
	
	function saveOrUpdate() {
		var dianpuName = $('#dianpuName').val();
		var dianPuJianJie = $("#dianPuJianJie").val();
		if (dianpuName=="") {
			alert("店铺名称不能为空");
			return false;
		}
		var url ='<%=request.getContextPath()%>/dianpu/dianpuAction!saveDianPu.action';
		$.ajax({
			cache: true,
			type: "POST",
			url:url,
			data:"dianpuName="+dianpuName+"&dianPuJianJie="+dianPuJianJie,// 你的formid
			async: false,
		    error: function(request) {
		        alert("网络出现问题稍等再试!!!");
		    },
		    success: function(data) {
		    	if (data.success) {
		    		alert("保存成功");
		    	}else{
		    		alert("保存失败,请返回!")
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