<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ResourceBundle"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">
<title>公告通知</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/notice.css" />
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/footer.css"  />
  <script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
</head>
<body>
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
   	
    /*加载内容*/
	$.ajax({
		type: "POST",
		url:"<%=request.getContextPath()%>/notice/noticeAction!ajaxNoticeList.action",
		data:{
			"pageNum":pageNum
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
	    			var time= new Date(children[i][2].time).Format("yyyy/M/dd");
	    			child+=('<div class="not-con">');
	    			child+=('<ul><li><div class="not-con-title">');
	    			child+=('<span>'+children[i][4]+'</span>');
	    			child+=('<span>'+time+'</span></div>');
	    			child+=('<div class="not-con-con">');
	    			child+=('<p>'+children[i][1].substring(0,100)+'</p>');
	    			child+=('<div class="notBtn">');
	    			child+=('<a id="not-btn-'+(i+6*pageNum)+'"');
	    			child+=(' href="javascript:showAndHide(\'notConAll-'+(i+6*pageNum)+'\',\'not-btn-'+(i+6*pageNum)+'\')"');
	    			child+=('>阅读全文</a></div></div>');		
	    			child+=('<div class="not-con-all" id="notConAll-'+(i+6*pageNum)+'"');
	    			child+=('<p><p>'+children[i][1].substring(100,10000)+'</p>');
	    			child+=('<div class="notBtn">');
	    			child+=('<a id="not-btn-'+(i+6*pageNum)+'"');
	    			child+=(' href="javascript:showAndHide(\'notConAll-'+(i+6*pageNum)+'\',\'not-btn-'+(i+6*pageNum)+'\')"');
	    			child+=('>收起</a>');		
	    			child+=('</div></div></li></ul></div>');
                    
	    		}
	    		var ul = $("#appendUl");
	    		ul.append(child);
	    		
	    	}else{
	    		length = data.length;
	    		/* alert("已经加载完毕!"); */
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
	
});
</script>
<div class="wrap">
  <div class="notHeader">
    <div class="not-hea-log"><img src="http://wx.qlogo.cn/mmopen/ooynj70RTEA2ict4nxwt4yMaoghPWbJNkQotrXJSEns2OEGjtUGTfuSdCO0ArOQCAxq22IEtyasgkLZRN0ic04ARiclJkfZuHicN/64"></div>
    <div class="not-hea-cn"><span>山人物语</span></div>
	<div class="not-hea-en"><span>SHANRENWUYU</span></div>
  </div>
  <div class="not-list" id="appendUl">
	<div class="not-h"><span></span><span>公 告</span><span></span></div>

 <c:forEach var="notice" items="${notices }" varStatus="status">
 <div class="not-con"> 
 <ul>
  <li>
   <div class="not-con-title">
	<span>${notice[4] }</span>
	<span><fmt:formatDate value="${notice[2] }" pattern="yyyy/M/dd"/></span>
   </div>
   <div class="not-con-con">
	<p>${fn:substring(notice[1],0,100) }</p>
   <div class="notBtn"><a id="not-btn-${status.index+1 }" href="javascript:showAndHide('notConAll-${status.index+1 }','not-btn-${status.index+1 }')">阅读全文</a></div>
   </div>
   <div class="not-con-all" id="notConAll-${status.index+1 }">
    <p>${fn:substring(notice[1],100,10000) }</p>
	<div class="notBtn"><a id="not-btn-${status.index+1 }" href="javascript:showAndHide('notConAll-${status.index+1 }','not-btn-${status.index+1 }')">收起</a></div>
	</div>
	</li>
 </ul> 
 </div>
 </c:forEach> 
 </div>
 		<script type="text/javascript">
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
			function showAndHide(tid,yuedu,shouqi){
				var istrue = $("#"+tid).is(":hidden");
				if(istrue == true){
					$("#"+tid).show();
					$("#"+yuedu).hide();
				}else{
					$("#"+tid).hide();
					$("#"+yuedu).show();
				}
			}
		</script>
	</div>
<!-- 底部s -->
<%@ include file="/WEB-INF/jsp/footer.jsp"%>
<!-- 底部e -->
</body>
</html>