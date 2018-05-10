<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>砸金蛋</title>

<script language="JavaScript"
	src="<%=request.getContextPath()%>/resource/js/jquery/jquery-1.8.2.min.js"></script>
<link
	href="<%=request.getContextPath()%>/resource/findpwd/css/findpwd.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath()%>/resource/eggs/style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resource/js/Validform_v5.3.2/js/Validform_v5.3.2.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resource/js/Validform_v5.3.2/css/style.css"
	type="text/css" />
</head>
<body>

	<div class="wrapper" style="padding:10px" align="center">
		<div class="mod-area" style="height:400px">
		<div><h1>${eggActivity.activityName }</h1></div>
		
			<!-- 内容开始 [[ -->
				<div class="egg" style="margin-top:-80px">
		<ul class="eggList">
			<p class="hammer" id="hammer">锤子</p>
			<p class="resultTip" id="resultTip"><b id="result" style="display:none"></b></p>
			<li><span>1</span><sup></sup></li>
			<li><span>2</span><sup></sup></li>
			<li><span>3</span><sup></sup></li>

		</ul>
	</div>
 
				<!-- 内容结束 ]] -->
		</div>
		<div style="height: 20px"></div>
	</div>
	<!--footer-->
	<div  id="div_tip" align="center" style="color:red"> </div>
		<input id="hHavEgg" type="hidden"/>
	<div >
	
	<table width="600px"  align="center">
	<tr><td colspan="3"><h3>获奖纪录：</h3></td></tr>
	<tr>
<!-- 	<td>获奖等级</td> -->
	<td>获奖名称</td>
	<td>获奖时间</td>
	</tr>
	 <s:iterator value="myPrizeList" status="i" var="prize">
	 
	 <tr>
<%-- 	<td>${prizeLevel }</td> --%>
	<td>${prizeName }</td>
	<td><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss" /></td>
	</tr>
				</s:iterator>
	</table>
	</div>
	<!-- footer end -->
	<script type="text/javascript">

		
function eggClick(obj) {
	$("#result").html("");
	if($("#hHavEgg").val()=="0"){
		alert( $("#div_tip").html());
		return false;
	}
	var _this = obj;
	
// 		if(_this.hasClass("curr")){
// 			alert("蛋都碎了，别砸了！刷新再来.");
// 			return false;
// 		}
		_this.unbind('click');
		$(".hammer").css({"top":_this.position().top-55,"left":_this.position().left+185});
		$(".hammer").animate({
			"top":_this.position().top-25,
			"left":_this.position().left+125
			},30,function(){
				
				$('.resultTip').css({display:'block',top:'100px',left:_this.position().left+45,opacity:0}).animate({top: '50px',opacity:1},300,function(){
					 $.ajax({
						   url : "<%=request.getContextPath()%>/egg!ajaxHitEgg.action?"+new Date(),
				           type: "post", 
				           data : {"activityId":'${activityId}'},
				           success: function(result) {
				        	   if(result!=null){
				        		   var dataObj=eval("("+result+")");
				        		   
				        		   if(dataObj.status=="0"){
				 	       			 $("#hHavEgg").val("0");
				 	      		  }
				        		   else if(dataObj.status=="2"){
				        			   $("#hHavEgg").val("0");
				        		   }
				        		   else  if(dataObj.status=="3"){
				        			   $("#hHavEgg").val("0");
				        		   }
				        		   else if(dataObj.status=="1"){
										
										_this.addClass("curr"); //蛋碎效果
										_this.find("sup").show(); //金花四溅
										$(".hammer").hide();
										$("#result").html("恭喜，您中得"+dataObj.tipLevel+":"+dataObj.tipName+"!");
										$("#result").fadeIn(3000);
// 										location.reload(true);
									}else{
										_this.addClass("curr"); //蛋碎效果
										_this.find("sup").show(); //金花四溅
										$(".hammer").hide();
										$("#result").html("很遗憾,您没能中奖!");
										$("#result").fadeIn(3000);
// 										location.reload(true);
									}
				        		   $("#div_tip").html(dataObj.tipMessage);
				        	   }
				           }
					 });
					
				});	
			}
		);

}


$(".eggList li").click(function() {
	$(this).children("span").hide();
	eggClick($(this));
});

$(".eggList li").hover(function() {
	var posL = $(this).position().left + $(this).width();
	$("#hammer").show().css('left', posL);
})
//ajax检查抽奖资格和剩余次数
ajaxCheckEgg();
function ajaxCheckEgg(){
	 $.ajax({
		   url : "<%=request.getContextPath()%>/egg!ajaxCheckEgg.action?"+new Date(),
         type: "post", 
         data : {"activityId":'${activityId}'},
         success: function(result) {
      	   if(result!=null){
      		   var dataObj=eval("("+result+")");
	      		 if(dataObj.status=="0"){
	      			  $("#div_tip").html(dataObj.tipMessage);
	       			 $("#hHavEgg").val("0");
	      		 }
      		   else if(dataObj.status=="2"){
      			   $("#div_tip").html("抽奖次数用光了！");
      			 $("#hHavEgg").val("0");
      			   return false;
      		   }
      		   else if(dataObj.status=="3"){
						 $("#div_tip").html("恁无权参与此次抽奖！");
						 $("#hHavEgg").val("0");
						return false;
      			   }
      		   else if(dataObj.status=="1"){
						 $("#div_tip").html(dataObj.tipMessage);
					}
	 		  }
		}
});

}
	</script>
</body>
</html>