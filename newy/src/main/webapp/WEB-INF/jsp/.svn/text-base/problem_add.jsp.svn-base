<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<style type="text/css">
.div-xinxi li input{
	float: left;
	margin: 0;
	padding: 0;
	width: 65%;
	padding-left: 10px;
}
.dizhi span{
	float: left;
	width: 80px;
	line-height: 40px;
}
.ui-loader{
	display: none;
}
.div-xinxi .dizhi{
border-bottom: none;
}
	</style>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>问题反馈</title>
    <script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
    <%-- <script src="<%=request.getContextPath()%>/js/jquery.mobile-1.4.5.min.js"></script> --%>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bbt.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/mui.min.css"/>
   
</head>
<body>

	<div class="div-xinxi">
		<ul>
			<form id="frmRole" namespace="user" method="post" action="problemAction!saveOrUpdate.action" >
			<input type="hidden" id="createDate" name="problem.createDate"  value="${problem.createDate }">
			<input type="hidden" id="endDate" name="problem.endDate"  value="${problem.endDate }">
			 <input type="hidden" id="userId" name="problem.createUserId"  value="${problem.createUserId }">
			<input type="hidden" id="proId" name="problem.id"  value="${problem.id }">
			<input type="hidden" name="problem.status"  value="${problem.status }">
			
			<%-- <li>数量：<span id="xx-shu" class="xx-shu">0</span></li> --%>
			<li class="dizhi" ><span>问题类型：</span><%-- <input id="type" type="text" placeholder="姓名" value="${problem.type }"> --%>
			<select id="protype" name="problem.type" style="width:65%">
								<option value="红包问题">红包问题</option>
								<option value="pos机问题">pos机问题</option>
								<option value="支付问题">支付问题</option>
								<option value="流量问题">流量问题</option>
								<option value="二维码问题">二维码问题</option>
								<option value="快递商品未到">快递商品未到</option>
								<option value="其他问题">其他问题</option>
							</select>
			
			
			</li>
			<li class="dizhi" ><span>问题关系：</span><%-- <input id="type" type="text" placeholder="姓名" value="${problem.type }"> --%>
			<select id="protype" name="problem.relation" style="width:65%">
								<option value="请选择">此问题如与上下级有关系请选择</option>
								<option value="上一级">上一级</option>
								<option value="上二级">上二级</option>
								<option value="上三级">上三级</option>
								<option value="忠实粉丝">忠实粉丝</option>
								<option value="铁杆粉丝">铁杆粉丝</option>
								<option value="超级粉丝">超级粉丝</option>
							</select>
			
			
			</li>
			<li class="dizhi"><span>我的id：</span>
				<input id="stauts" type="text" name="problem.userId" placeholder="如本人提交问题，不需填写" value="${problem.userId }"></li>
			</li>
			<li class="dizhi"><span>与谁发生问题：</span>
			<input id="stauts" type="text" name="problem.peoId" placeholder="请填写与您发生问题用户的id" value="${problem.peoId }"></li>
				
			</li>
			<li class="dizhi"><span>联系方式：</span>
				<input  type="text" name="problem.phone" id="myphoneid" placeholder="请务必填写您的手机号" value="${problem.userId }"></li>
			</li>
			
			<li class="dizhi"><span><font color="red" ><strong>*</strong></font>问题描述：</span>
				<textarea  style="width: 65%;" name="problem.describez" id="describe"  placeholder="请在这里详细描述您遇到的问题，如问题与其他会员有关请填写他的id">${problem.describez }</textarea>
			</li>
			<c:if test="${problem != null }">
			<li class="dizhi"><span>问题状态：</span>
			<c:if test="${problem.status == 0}">
				<input id="stauts" type="text" disabled="disabled"  value="未解决"></li>
			</c:if>
			<c:if test="${problem.status == 1}">
				<input id="stauts" type="text" disabled="disabled"  value="已解决"></li>
			</c:if>
			<c:if test="${problem.status == 2}">
				<input id="stauts" type="text" disabled="disabled"  value="问题描述不清晰"></li>
			</c:if> 
			<c:if test="${problem.status == 3}">
				<input id="stauts" type="text" disabled="disabled"  value="驳回"></li>
			</c:if> 
			<li class="dizhi" id="methodId"><span>解决方式：</span><textarea  style="width: 65%;" disabled="disabled"  name="problem.method" id="method"  >${problem.method }</textarea></li>
          </c:if>
          </form>
		</ul>
	</div>
	
	<div class="ft-but">
		<button id="btn_submit" class="mui-btn mui-btn-danger mui-btn-block" onclick=" return goumai()">提交</button>
	</div>
	<script type="text/javascript">
	var problem= '${problem}';
	if(problem != null && problem != ""){
	    $("select option[value='${problem.type}']").attr("selected", "selected");  
	    $("select option[value='${problem.relation}']").attr("selected", "selected");  
		var sta = '${problem.status}';
		if(sta != '0'){
			$("#btn_submit").hide();
			}
		}


		function goumai() {
			if($.trim($("#describe").val()).length ==0){
				alert("问题描述不能为空");
				return false;
			}
			if($.trim($("#myphoneid").val()).length ==0){
				alert($("#myphoneid").val());
				alert("联系方式不能为空");
				return false;
			}
			
			
		$("#frmRole").submit();
			
			<%-- $.ajax({
		        type:"POST",
		        url:"<%=request.getContextPath()%>/pay/payDianAction!saveOrderDian.action",
		        data : {
		        	"total_price1":jiage,
		        	"levelValue":level,
		        	"orderAddress.id":addressId,
		        	"orderAddress.userName":xingming,
		        	"orderAddress.address":dizhi,
		        	"orderAddress.mobile":phone,
		        	"orderAddress.zipcode":wxhao,
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
		       	 			alert("你还不是全国总代无法开店!");
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
		 	}); --%>
			
		
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
	

	</br>
	</br>
	</br>
	</br>
	</br>
</body>
</html>
