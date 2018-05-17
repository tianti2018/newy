<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>编辑收货地址</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.mobile-1.4.3.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/css.css">
<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script> 


	<%-- <script src="<%=request.getContextPath()%>/js/index.js"></script> --%>
  <script src="<%=request.getContextPath()%>/js/jquery.mobile-1.4.3.js"></script>
<%--   <script src="<%=request.getContextPath()%>/js/provincesdata.js"></script>
  <script src="<%=request.getContextPath()%>/js/jquery.provincesCity.js"></script> --%>
   
</head>
    <body onload="body_onload()">
<div data-role="page" class="jqm-demos" data-quicklinks="true">
  
  <div class="ui-content main" role="main">
	<form action="<%= request.getContextPath()%>/orderAddress/orderAddressAction!saveEntity.action" id="frm_wxReg" 
	 method="post">
      <div id="ui-body-test" class="ui-body ui-body-a ui-corner-all">
        <div class="ui-field-contain">
          <label for="name2">收货人：</label>
          <input type="text" name="orderAddress.userName" value='${orderAddress.userName }'  id="input_shr" placeholder="收货人"/>
        </div>
        <div class="ui-field-contain">
          <label for="name2">手机号码：</label>
          <input type="text" name="orderAddress.mobile" value='${orderAddress.mobile }'  id="input_shsj" placeholder="手机号码"/>
        </div>
          <div class="ui-field-contain">
          <label for="name2">省份：</label>
          <select id="province" name="orderAddress.provinceId" onchange="showArea(this,'city')">
          <option value="">==请选择省份</option>
            <c:forEach var="p" items="${provincelist}">
            <c:choose>
            <c:when test="${p.id==orderAddress.provinceId}">
             <option selected="selected" value="<c:out value="${p.id}"/>"><c:out value="${p.name }"/></option>
            </c:when>
            <c:otherwise> 
                <option value="<c:out value="${p.id}"/>"><c:out value="${p.name }"/></option>
            </c:otherwise>
            </c:choose>
            </c:forEach>
          </select>
           <label for="name2">市：</label>
            <select id="city" name="orderAddress.cityId" onchange="showArea(this,'region')">
           <option value="">==请选择城市</option>
            <c:forEach var="c" items="${citylist}">
             <c:choose>
            <c:when test="${c.id==orderAddress.cityId}">
             <option selected="selected" value="<c:out value="${c.id}"/>"><c:out value="${c.name }"/></option>
            </c:when>
            <c:otherwise> 
                <option value="<c:out value="${c.id}"/>"><c:out value="${c.name }"/></option>
            </c:otherwise>
            </c:choose>
            </c:forEach>
          </select>
            <label for="name2">区县：</label>
            <select id="region" name="orderAddress.areaId">
            	<option value="">==请选择地区</option>
            	<c:forEach var="a" items="${arealist}">
               		<c:choose>
            			<c:when test="${a.id==orderAddress.areaId}">
             				<option selected="selected" value="<c:out value="${a.id}"/>"><c:out value="${a.name }"/></option>
            			</c:when>
            			<c:otherwise> 
                			<option value="<c:out value="${a.id}"/>"><c:out value="${a.name }"/></option>
            			</c:otherwise>
            		</c:choose>
            	</c:forEach>
            </select>
        </div>
        <div class="ui-field-contain">
         <label for="name2">详细地址：</label>
         <textarea  name="orderAddress.address" id="input_dz"  placeholder="收货地址">
          ${orderAddress.address }
          </textarea>
        </div>
     	<div class="ui-field-contain">
          <label for="name2">微信号：</label>
          <input type="text" name="orderAddress.zipcode" value='${orderAddress.zipcode}'  id="input_yb" maxlength="18" placeholder="微信号"/>
        </div>
        
        <div class="ui-field-contain">
          <label for="name2">身份证号：</label>
          <input type="text" name="orderAddress.idCard" value='${orderAddress.idCard}'  id="input_idc" maxlength="18" placeholder="身份证号"/>
        </div>
       
        <div class="ui-field-contain">
          <label for="name2">默认收货地址：</label>
         <input type="checkbox" id="ck_bindWx" name="ckDefaultAddr" style="margin-left:-10px;margin-top:-2px;"/>
         &nbsp;&nbsp;&nbsp;&nbsp;(勾选本项，此地址将作为默认收货地址)
        </div>
         <s:hidden name="orderAddress.id"></s:hidden>
         <s:hidden name="orderAddress.userId"></s:hidden>
           <s:hidden name="orderAddress.isFirst"></s:hidden>
      </div>
 	 </form>
      <div class="ui-field-contain">
        <button type="button" id="btn_submit" onclick="checkFrm()" class="ui-btn ui-shadow ui-corner-all ui-btn-active" >确定</button>
      </div>
  </div>
 
</div>
<script type="text/javascript">
	
	//省市选择级联
	function showArea(obj,targetId)
	{
		var parentId=$(obj).val();
		var optionhtml="<option value=''>==请选择</option>";
		$("#"+targetId).html("");
		$.post("<%=request.getContextPath()%>/city/cityAction!findCityByParentId.action",{parentId:parentId},function(data){
			        $(data.result).each(function(i){
			        	optionhtml+="<option value='"+this.id+"'>"+this.name+"</option>";
			        	 $("#"+targetId).html(optionhtml);
			        });
			});
	}

	
	function body_onload () {
		var message = '${message}';
		if (message != "" && message!=null) {
			alert(message);
		}
	}
		
	// 手机号码校验
	function checkMobile(val){
		var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
	    if(!myreg.test(val)) 
	    {
	        return false; 
	    }else{
		    return true;
	    }
	}
	
	function checkFrm() {
		
		var dz = $("#input_dz");
		var yb =$("#input_yb");
		var shr = $("#input_shr");
		var shsj = $("#input_shsj"); // 手机号码
		var province=$("#province").val();
		var city=$("#city").val();
		var region=$("#region").val();
		var idcard = $("#input_idc").val();
		if($.trim(shr.val())==null||$.trim(shr.val())=="")  {		
			alert("对不起，"+shr.attr("placeholder")+"不能为空，请重新输入！");
			shr.focus();
			return false;
		}
		if($.trim(shsj.val())==null||$.trim(shsj.val())=="" || !checkMobile(shsj.val()))  {	
			alert("对不起，"+shsj.attr("placeholder")+"为空或格式有误，请重新输入！");
			shsj.focus();
			return false;
		}
		if(province==""||city==""||region==""||city=="==请选择"||region=="==请选择")  {		
			alert("对不起，省市区不能为空！");
			return false;
		}
		if($.trim(dz.val())==null||$.trim(dz.val())=="")  {		
			alert("对不起，"+dz.attr("placeholder")+"不能为空，请重新输入！");
			dz.focus();
			return false;
		}
		if($.trim(yb.val())==null||$.trim(yb.val())=="")  {		
			alert("对不起，"+yb.attr("placeholder")+"不能为空，请重新输入！");
			yb.focus();
			return false;
		}
		if($.trim(idcard) != null&&$.trim(idcard) !="" ){
			var isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
			if(!isIDCard1.test(idcard)){
				alert("身份证号格式不正确");
				return;
			}
		}
		
		$("#btn_submit").val("提交中");
		$("#btn_submit").attr("disabled","disabled"); 
		
		
		document.getElementById("frm_wxReg").submit();
	}
	$(document).ready(function(){
		var dz=$.trim($("#input_dz").val());
		$("#input_dz").val(dz);
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
</body>
</html>
