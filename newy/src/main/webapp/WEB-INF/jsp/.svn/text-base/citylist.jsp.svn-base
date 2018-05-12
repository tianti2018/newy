<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type"/>
<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<meta content="telephone=no" name="format-detection" />
<TITLE>城市列表</TITLE>

<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/city.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/city.css" />


<meta name="keywords" content="关键词">
<meta name="description" content="描述">
<meta name="applicable-device" content="mobile">





</head>
<body>

<div id="wrap">

  <div id="header">
    <div class="topbox">
      <div class="topl"><a href="javascript:history.go(-1);"><img src="<%=request.getContextPath()%>/images/top/back.png" /></a></div>
      <div class="topsear"><form name="form" action="<%=request.getContextPath()%>/city/cityAction!cities.action" method="post"><input type="text" name="cityWord" id="cityWord" class="input" placeholder="快速搜索所在城市"><input type="button" class="btn" ></form></div>
      <div class="topr"><a href="javascript:this.form.submit()"><img src="<%=request.getContextPath()%>/images/top/config.png" /></a></div>
    </div>
  </div>
  
  <div id="mainer">
      <div class="list-table">
    <div class="list-address-title">定位城市</div>
    <div class="list-address-line">北京</div>
    <div class="list-address-title">热门城市</div>
    <div class="hot-address">
    
   
    <c:forEach var="hot" items="${hotcities}">
    <c:if test="${not empty hot.shortname}"> 
    <a onclick="javascript:check('${hot.id}')" class="hot-city"> <c:out value="${hot.shortname }"/></a>
    </c:if>
    </c:forEach>
   
    </div>
   </div>

			<c:forEach var="m" items="${citymap}">
				<div class="list-table">
					<div class="list-address-title" id="group-<c:out value="${fn:toLowerCase(m.key)}"/>"><c:out value="${m.key}" /></div>
					<c:forEach var="city" items="${m.value}">
					<c:if test="${not empty city.shortname}"> 
						<div class="list-line list-address-line">
						<a onclick="javascript:check('${city.id}')">
						<c:out value="${city.shortname}" />
						</a>
						</div>
						</c:if>
					</c:forEach>
				</div>
			</c:forEach>
	<div class="city-nav">
    <a href="#group-a"><div>A</div></a>
    <a href="#group-a"><div>B</div></a>
    <a href="#group-c"><div>C</div></a>
    <a href="#group-d"><div>D</div></a>
    <a href="#group-e"><div>E</div></a>
    <a href="#group-f"><div>F</div></a>
    <a href="#group-g"><div>G</div></a>
    <a href="#group-h"><div>H</div></a>
    <a href="#group-j"><div>J</div></a>
    <a href="#group-k"><div>K</div></a>
    <a href="#group-l"><div>L</div></a>
    <a href="#group-m"><div>M</div></a>
    <a href="#group-n"><div>N</div></a>
    <a href="#group-p"><div>P</div></a>
    <a href="#group-q"><div>Q</div></a>
    <a href="#group-r"><div>R</div></a>
    <a href="#group-s"><div>S</div></a>
    <a href="#group-t"><div>T</div></a>
    <a href="#group-w"><div>W</div></a>
    <a href="#group-x"><div>X</div></a>
    <a href="#group-y"><div>Y</div></a>
    </div>

  </div>
</div>

<!-- zhang_wanqiang 城市定位-->
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=cVZOceoa2o3Vl3LOxAYKsKNPU1avOE4w"></script>
<div id="bdMapBox" style="display:none;"></div>
<script type="text/javascript">
     $(function(){
    	 
     });
     
     function choiceCity()
     {
    	 $(".list-address-line").each(function(i){
    		  $(this).bind("click",function(){
    			  
    		  });
    	 });
     }
     
     function check(id){
 		var map = new BMap.Map("bdMapBox");
 		var nowCity = new BMap.LocalCity();
 		nowCity.get(bdGetPosition);
 		function bdGetPosition(result){
 			var cityNamee = result.name; //当前的城市名
 			cityNames = cityNamee.substr(0, 2);
 			$.ajax({
 				type: "POST",
 				url:"<%=request.getContextPath()%>/city/cityAction!choiceCity.action",
 				data:{
 					"cityId":id,
 					"cityNames":cityNames
 				},
 				dataType:"json",
 				success:function(data) {
 					if(data.success){
 						//var shortname = data.city;
 						window.location.href = '<%=request.getContextPath()%>/user/userAction!jampshopIndex.action';
 					}else{
 						alert(data.tipMessage);
 						//var tipMessage = data.tipMessage;
 						window.location.href = '<%=request.getContextPath()%>/user/userAction!jampshopIndex.action';
 					}
 				}
 			});
 		}	
 	}

</script>
</body>
</html>