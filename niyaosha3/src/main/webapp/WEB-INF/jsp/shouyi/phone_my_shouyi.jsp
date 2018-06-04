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
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/commenObject/massageCode.js"></script>
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
var wanbi=0;
function insertcode(){
	if(wanbi!=0){
		return;
	}
	
   	var date1 = $("#date1").val();
   	var date2 = $("#date2").val();
    /*加载内容*/
	$.ajax({
		type: "POST",
		url:"<%=request.getContextPath()%>/shouyi/shouyiAction!ajaxmyShouyiList.action",
		data:{
			"orderType":'${orderType }',
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
	    			var time= new Date(children[i].createDate.time).Format("yyyy-MM-dd hh:mm:ss");
	    			var passTime = null;
	    			if(children[i].passDate!=null){
	    				passTime = new Date(children[i].passDate.time).Format("yyyy-MM-dd hh:mm:ss");
	    			}
	    			child+=('<div class="my_account" >');
	    			child+=('<div class="item" onclick="zhankai('+children[i].id+')">');
	    			child+=('<div class="item_t">');
	    			child+=('<h2>'+children[i].beizhu+'</h2><span>'+children[i].shouyi +'</span></div>');	    			
	    			child+=('<div class="item_m" id="item_m'+children[i].id+'"><ul>');
	    			child+=('<li><a ><span>时间：</span><i>'+time+'</i></a></li>');
	    			if(children[i].status==1){
		    			child+=('<li><a ><span>状态：</span><i>待生效</i></a></li>');
		    		    }else if(children[i].status==2){
		    		    	child+=('<li><a ><span>状态：</span><i>生效</i></a></li>');
		    		    }
	    			if(children[i].tixian==1){
	    			child+=('<li><a ><span>提现状态：</span><i>申请中</i></a></li>');
	    		    }else if(children[i].tixian==2){
	    		    	child+=('<li><a ><span>提现状态：</span><i>通过</i></a></li>');
	    		    	child+=('<li><a ><span>通过时间：</span><i>'+passTime+'</i></a></li>');
	    		    	child+=('<li><a ><span>打款单：</span><i>'+children[i].dakuanDan+'</i></a></li>');
	    		    	
	    		    }
	    			child+=('<li><a ><span>来自订单：</span><i>'+children[i].ordersBh+'</i></a></li>');
	    			child+=('</li></ul></div></div></div>'); 
                    
	    		}
	    		var ul = $("#appendUl");
	    		ul.append(child);
	    		
	    	}else{
	    		wanbi=data.length;
	    		/* alert("已经加载完毕!"); */
	    		
	    	}
	    }
	});
	pageNum++;
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
		}             
		}
    });
   insertcode();
   showMoney();
});
function showMoney(){
	$.ajax({
		type: "POST",
		url:"<%=request.getContextPath()%>/shouyi/shouyiAction!showMoney.action",
	    error: function(request) {
	        alert("网络出现问题稍等再试!!!");
	    },
	    dataType:"json",
	    success: function(data) {
	    	var code = data.code;
	    	if(code=="000000"){
	    		$("#yve").html(data.yve);
	    		$("#shenqingzhong").html(data.shenqingzhong);
	    		$("#yitongguo").html(data.yitongguo);
	    	}else{
	    		alert(getMessageByCode(code));
	    	}
	    }
	});
}
function tixian(){
	var money = $("#money").val();
	var zhengze = /^\+?[1-9][0-9]*$/  ;
	if(!zhengze.test(money)){
		alert("请填写正确数字!");
		return ;
	}
	if(money%10!=0){
		alert("必须为10的整数!");
		return ;
	}
	$.ajax({
		type: "POST",
		url:"<%=request.getContextPath()%>/shouyi/shouyiAction!tixian.action",
		data:{
			"money":money	
		},
	    error: function(request) {
	        alert("网络出现问题稍等再试!!!");
	    },
	    dataType:"json",
	    success: function(data) {
	    	code = data.code;
	    	if(code == "000000"){
	    		alert("申请提现成功!");
	    		window.location.reload(true	);
	    	}else{
	    		alert(getMessageByCode(code));
	    	}
	    }
	});
}
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
		<form method="post" action="orderAction!orderPerList.action?orderNo=0&requestType=mo & date1=document.getElementById('date1').value & date2=document.getElementById('date2').value">
			<div class="my_order_sear">
				<input type="date" class="input" id="date1" name="date1"
					value="${date1 }"><i>-</i><input type="date" class="input"
					id="date2" name="date2" value="${date2 }"> <input
					type="submit" class="btn" value="搜索">
			</div>
		</form>
		<br>
		<div class="my_account">
			<div class="item">
				<div class="item_t">
					<h2>余额</h2>
					<span id="yve">0</span>
				</div>
				<div class="item_m item_m2">
					<ul>
						<li><a href="javascript:shenqingzhong();"><span >申请中</span><i id="shenqingzhong">0</i></a></li>
						<li><a href="javascript:yitongguo();"><span >已通过</span><i id="yitongguo">0</i></a></li>
					</ul>
					<div class="f">
						<input type="text" id="money" name="money" class="input" placeholder="提现金额为10的整数倍">
						<input type="button" class="btn" value="提现" onclick="tixian();">
					</div>
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>
	<br>
	
	<!-- footer start -->
<%@ include file="/WEB-INF/jsp/footer.jsp"%>
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
	
	function shenqingzhong(){
		alert("开发中");
	}
	function yitongguo(){
		alert("开发中");
	}
	
	
		
		function qrshsubmit(){
			$("#frmList").submit();
		}
		
	</script>
</body>
</html>
