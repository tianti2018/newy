<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>我爱家乡</title>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="x-dns-prefetch-control" content="on">   
    <meta http-equiv="x-dns-prefetch-control" content="on">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ivmc/footer.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ivmc/lovehouse.css"/>
	<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script> 
	<style>
		.hidehide{
			display:none;
		}
	
	</style>
	  <script type="text/javascript">
		function playOrPaused(obj){
			var aus = $("audio");
			var audio =document.getElementById(obj);
			var istrue = false;
			for(var x=0;x<aus.length;x++){
				if(!aus[x].paused){
					if(aus[x].id == audio.id){
						istrue = true;
					}
					aus[x].pause();	
				}
			 }
		if(audio.paused && istrue ==false){
			audio.play();
		}else{
			audio.pause();
		}
		}
	  </script>
</head>
<body>
	<div class="wrap">
		<div class="city-hea-con"></div>
		<div class="love-header">
			<a href="<%=request.getContextPath()%>/city/city5dAction!cities.action" class="city_btn">
	         <c:choose>
	          <c:when test="${not empty city.shortname}">
	          	<span id="cityNames"></span>
	          </c:when>
	          <c:otherwise> 
	          	<span id="cityNames"></span>
	          </c:otherwise>
	         </c:choose>
	         <!--切换城市  -->
	        </a>
			<input type="text" name="search" id="" placeholder="搜索城市乡村" />
		</div>
		<form id="searchForm" method="post" action="ivmcAction!ivmCountryList.action">
	        <input type="hidden" name="ivmCountry.cityId" value="${ivmCountry.cityId }"/>
			<div class="city">
				<h2>地理位置</h2>
				
				<div class="cities-list">
					<select id="county" name="ivmCountry.countyId" onchange="showArea(this,'town')">
		            	<option value="">*所在县/区</option>
		            	<c:forEach var="a" items="${countylist}">
		               		<c:choose>
		            			<c:when test="${a.countyId==ivmCountry.countyId}">
		             				<option selected="selected" value="<c:out value="${a.countyId}"/>"><c:out value="${a.countyName }"/></option>
		            			</c:when>
		            			<c:otherwise> 
		                			<option value="<c:out value="${a.countyId}"/>"><c:out value="${a.countyName }"/></option>
		            			</c:otherwise>
		            		</c:choose>
		            	</c:forEach>
		             </select>
				</div>
				<div class="cities-list">
					<select id="town" name="ivmCountry.townId" onchange="showArea(this,'village')">
		            	<option value="">*所在乡/镇</option>
		            	<c:forEach var="a" items="${townlist}">
		               		<c:choose>
		            			<c:when test="${a.townId==ivmCountry.townId}">
		             				<option selected="selected" value="<c:out value="${a.townId}"/>"><c:out value="${a.townName }"/></option>
		            			</c:when>
		            			<c:otherwise> 
		                			<option value="<c:out value="${a.townId}"/>"><c:out value="${a.townName }"/></option>
		            			</c:otherwise>
		            		</c:choose>
		            	</c:forEach>
		            </select>
				</div>
				<div class="cities-list">
					<select id="village" name="ivmCountry.villageId">
		            	<option value="">*所在村庄</option>
		            	<c:forEach var="a" items="${villagelist}">
		               		<c:choose>
		            			<c:when test="${a.villageId==ivmCountry.villageId}">
		             				<option selected="selected" value="<c:out value="${a.villageId}"/>"><c:out value="${a.villageName }"/></option>
		            			</c:when>
		            			<c:otherwise> 
		                			<option value="<c:out value="${a.villageId}"/>"><c:out value="${a.villageName }"/></option>
		            			</c:otherwise>
		            		</c:choose>
		            	</c:forEach>
		             </select>
				</div>
			</div>
			<div class="city-search-btn">
					<!-- <a href="#" onclick="javascript:addIvmCountry();"><span>上传视频</span></a> -->
					<a href="#" onclick="javascript:searchForm();"><span>搜索</span></a>
			</div>
		</form>
		<div class="my-media-title">
			<h2>我的视频</h2>
		</div>
		<div class="my-media">
			<c:forEach items="${ivmcList}" var="item" varStatus="status">
				<!--显示上传图片，不显示视频  onclick="javacscript:IvmCountryCon(1,${item.id})"-->
				<div class="my-media-img"><a href="javascript:;" ><img src="<%=request.getContextPath()%>${item.picUrl}"/></a></div> 
				<div class="my-media-det">
						<h2>${item.title }</h2>
						<!-- <a href="javascript:;" onclick="playOrPaused('voiceId_${status.index + 1 }');"><span>我的方言</span></a>
						<audio class="hidehide" id="voiceId_${status.index + 1 }" src="<%=request.getContextPath()%>${item.voiceUrl}" controls="controls"></audio>
						 -->
				</div>
				<audio id="voiceId_${status.index + 1 }" src="<%=request.getContextPath()%>${item.voiceUrl}" controls="controls"></audio>
				
				<div class="media-people">
					<a href="javascript:;"><span>上传日期<span>(${item.createDate})</span></span></a>
					<a href="javascript:;" onclick="alert('开发中...')"><span>0</span></a>
					<a href="javascript:;"><span>0</span></a>
					<a href="javascript:;"><span>0</span></a>
				</div>
			</c:forEach> 
			
		</div>
		<div class="other-media-title">
			<h2>其他视频</h2>
		</div>
		<div class="media">
			<c:forEach items="${memberCountryList}" var="item" varStatus="status">
				<ul>
					<li>
						<!-- onclick="javacscript:IvmCountryCon(2,${item.id})" -->
						<div class="vedio"><a href="javascript:;"><img src="<%=request.getContextPath()%>${item.picUrl}"/></a></div>
						<div class="media-msg">
							<div class="audio">
								<!-- 
								<a href="javascript:;" onclick="playOrPaused('voiceId_${status.index + 1 }');"><span>听听本村的方言</span></a>
								<audio class="hidehide" id="voiceId_${status.index + 1 }" src="<%=request.getContextPath()%>${item.voiceUrl}" controls="controls"></audio>
								 -->
								<p>${item.title }</p>
							</div>
						</div>
						<div style="height: 5px; clear: both;"></div>
						<audio id="voiceId_${status.index + 1 }" src="<%=request.getContextPath()%>${item.voiceUrl}" controls="controls"></audio>
						<div class="media-people">
							<span>上传日期<span>(${item.createDate})</span></span>
							<span>1666</span>
							<span>155</span>
							<span>1.5万</span>
						</div>
					</li>
				</ul>
			</c:forEach>
		</div>
	</div>
<!-- 底部s -->
<div id="footer">
  <div class="foot_nav">
    <div class="foot_nav_inner">
    <a href="#"><span class="line"><i class="nav_f1"></i></span><span class="text">我的红包</span></a>
    <a href="#"><span class="line"><i class="nav_f2"></i></span><span class="text">商城首页</span></a>
    <a href="#"><span class="line"><i class="nav_f3"></i></span><span class="text">二维码</span></a>
    <a href="#"><span class="line"><i class="nav_f4"></i></span><span class="text">购物车</span></a>
    <a href="#"><span class="line"><i class="nav_f5"></i></span><span class="text">个人中心</span></a>
    </div>
  </div>
</div>
<!-- 底部e -->
<script type="text/javascript">
	//省市选择级联
	function showArea(obj,targetId) {
		var parentId=$(obj).val();
		var optionhtml="<option value=''>==请选择</option>";
		$("#"+targetId).html("");
		if(targetId == 'town'){
			$.post("<%=request.getContextPath()%>/city/city5dAction!findTownByParentId.action",{parentId:parentId},function(data){
			        $(data.result).each(function(i){
			        	optionhtml+="<option value='"+this.townId+"'>"+this.townName+"</option>";
			        	 $("#"+targetId).html(optionhtml);
			        });
			});	
		}else if(targetId == 'village'){
			$.post("<%=request.getContextPath()%>/city/city5dAction!findVillageByParentId.action",{parentId:parentId},function(data){
			        $(data.result).each(function(i){
			        	optionhtml+="<option value='"+this.villageId+"'>"+this.villageName+"</option>";
			        	 $("#"+targetId).html(optionhtml);
			        });
			});
		}
		
	}
	function addIvmCountry(){
		window.location.href="<%=request.getContextPath()%>/user/ivmcAction!goIvmCountryPage.action";
		}
	
	function IvmCountryCon(type,pid){
		if(type == 1){
			window.location.href="<%=request.getContextPath()%>/user/ivmcAction!goIvmCountryPage.action?ivmcId="+pid;	
		}else if (type == 2){
			window.location.href="<%=request.getContextPath()%>/user/ivmcAction!viewIvmCountryPage.action?ivmcId="+pid;	
		}else{
			window.location.href="<%=request.getContextPath()%>/user/ivmcAction!delIvmCountry.action?ivmcId="+pid;
		}				
	}
	function searchForm(){
		$("#searchForm").submit();
	}
	$(function(){
		showCity();
	});
	function showCity(){
		var cityName = $("#cityNames");
		if(cityName.val()==""){
				$.ajax({
			        type:"POST",
			        url:"<%=request.getContextPath()%>/city/city5dAction!showCity.action",
			        dataType:"json",
			        success:function(data) {
			        	if(data.success){
			        		cityName.html(data.city);
			        	}else{
			        		// 城市定位,把id放到users表中city字段
			        		var map = new BMap.Map("bdMapBox");
			        		var nowCity = new BMap.LocalCity();
			        		nowCity.get(bdGetPosition);
			        		function bdGetPosition(result){
			        			var cityNamee = result.name; //当前的城市名
			        			cityNames = cityNamee.substr(0, 2);
			        			//自定义代码
			        			cityName.html(cityNames);
			        			atCity.innerHTML = cityNames;
			        			$.ajax({
			        				type: "POST",
			        				url:"<%=request.getContextPath()%>/city/city5dAction!putCity.action",
			        				data:{
			        					"userId":'${user.userId }',
			        					"cityNames":cityNames
			        				},
			        				dataType:"json"
			        			});
			        		}
			        	}
				 	}
			 	});
		}
	} 
</script>
</body>
</html>
