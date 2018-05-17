<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.ResourceBundle"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>视频详情</title>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="x-dns-prefetch-control" content="on">   
    <meta http-equiv="x-dns-prefetch-control" content="on">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ivmc/media-det.css"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/ivmc/FaceBox.js" charset="utf-8"></script>
</head>
<body>
	<div class="wrap">
		<div class="media-det-header">
			<p>${ivmCountry.province } > ${ivmCountry.city } > ${ivmCountry.area } > ${ivmCountry.country }</p>
		</div>
		<div class="vedio">
			<div class="vedio-title">
				<h2>视频</h2>
			</div>
			<video width="100%"  preload="metadata"  controls="controls">
				<source src="<%=request.getContextPath()%>${ivmCountry.videoUrl}" type="video/ogg"/>
				<source src="<%=request.getContextPath()%>${ivmCountry.videoUrl}" type="video/mp4"/>
				<source src="<%=request.getContextPath()%>${ivmCountry.videoUrl}" type="video/webm"/>
			</video>
			<div class="vedio-det">
				<h2>♥【视频介绍】</h2>
				<p>我是视频介绍，我是视频介绍我是视频介绍。我是视频介绍，我是视频介绍我是视频介绍。我是视频介绍，我是视频介绍我是视频介绍。我是视频介绍，我是视频介绍我是视频介绍。</p>
			</div>
		</div>
		<div class="audio">
			<div class="audio-title">
				<h2>音频</h2>
			</div>
			<img src="<%=request.getContextPath()%>/images/ivmc/audio-img.jpg"/> <!--音频控件占位，请替换-->
			<audio width="100%" controls="controls">
				<!--<source src="http://www.iiwnet.com/music/music.ogg" />-->
				<source src="<%=request.getContextPath()%>${ivmCountry.voiceUrl}" type="audio/ogg"/>
   				<source src="<%=request.getContextPath()%>${ivmCountry.voiceUrl}" type="audio/mpeg"/>
   				<!--<source src="http://css.starsgrassland.com/123.mp3" />-->
			</audio>
			<div class="audio-det">
				<h2>♥【音频介绍】</h2>
				<p>我是音频介绍，我是音频介绍我是音频介绍。我是音频介绍，我是音频介绍我是音频介绍。我是音频介绍，我是音频介绍我是音频介绍。我是音频介绍，我是音频介绍我是音频介绍。</p>
			</div>
		</div>
		<div class="photo">
			<div class="photo-title">
				<h2>图片</h2>
			</div>
			<img src="<%=request.getContextPath()%>${ivmCountry.picUrl}"/> <!--图片占位，请替换-->
			<div class="vedio-det">
				<h2>♥【图片介绍】</h2>
				<p>${ivmCountry.detail}</p>
			</div>
		</div>
		<div class="media-det-min-btn">
			<ul>
				<li>
					<a class="min-btn-1" href="javascript:;"></a>
					<span>10000+</span>
				</li>
				<li>
					<a class="min-btn-2" href="javascript:;" onclick="alert('开发中...')"></a>	
					<span>1666</span>
				</li>
				<li>
					<a class="min-btn-3" href="javascript:;"></a>	
					<span>777</span>
				</li>
				<li>
					<a class="min-btn-4" href="javascript:;" onclick="alert('开发中...')"></a>
					<span>7445</span>
				</li>
				<li>
					<a class="min-btn-5" href="javascript:;" onclick="alert('开发中...')"></a>
					<span>123</span>
				</li>
			</ul>
		</div>
		<div class="media-det-reviews">
			<h2>最新评论</h2>
			<ul>
				<li>
					<div class="media-det-rev-info"><img src="<%=request.getContextPath()%>/images/ivmc/logo.png"/><span>昵**称</span></div>
					<div class="media-det-rev-con"><span>我是评论我是评论我是评论我是评论，啦啦啦</span></div>
				</li>
				<li>
					<div class="media-det-rev-info"><img src="<%=request.getContextPath()%>/images/ivmc/logo.png"/><span>昵**称</span></div>
					<div class="media-det-rev-con"><span>我是评论我是评论我是评论我是评论，啦啦啦</span></div>
				</li>
				<li>
					<div class="media-det-rev-info"><img src="<%=request.getContextPath()%>/images/ivmc/logo.png"/><span>昵**称</span></div>
					<div class="media-det-rev-con"><span>我是评论我是评论我是评论我是评论，啦啦啦</span></div>
				</li>
				<li>
					<div class="media-det-rev-info"><img src="<%=request.getContextPath()%>/images/ivmc/logo.png"/><span>昵**称</span></div>
					<div class="media-det-rev-con"><span>我是评论我是评论我是评论我是评论，啦啦啦</span></div>
				</li>
				<li>
					<div class="media-det-rev-info"><img src="<%=request.getContextPath()%>/images/ivmc/logo.png"/><span>昵**称</span></div>
					<div class="media-det-rev-con"><span>我是评论我是评论我是评论我是评论，啦啦啦</span></div>
				</li>
				<li>
					<div class="media-det-rev-info"><img src="<%=request.getContextPath()%>/images/ivmc/logo.png"/><span>昵**称</span></div>
					<div class="media-det-rev-con"><span>我是评论我是评论我是评论我是评论，啦啦啦</span></div>
				</li>
			</ul>
		</div>
	</div>
	<div class="footer" id="foo-con">
		<div class="foo-inp" id="Smohan_FaceBox">
			<p><a href="javascript:void(0)" class="face" title="表情"></a></p>
			<input name="text" id="Smohan_text" class="smohan_text" />
		</div>
		<div class="foo-btn">
			<span>发表</span>
		</div>
	</div>
	<script type="text/javascript">
		$(function (){
			$("a.face").smohanfacebox({
				Event : "click",	//触发事件	
				divid : "Smohan_FaceBox", //外层DIV ID
				textid : "Smohan_text", //文本框 ID
				parentdiv : "foo-con"
			});
			//解析表情  $('#Zones').replaceface($('#Zones').html());
		});
		//Demo测试
		// $('#Smohan_Showface').click(function() {
	 	//   	$('#Zones').fadeIn(360);
	 	//   	$('#Zones').html($('#Smohan_text').val());
	 	//   	$('#Zones').replaceface($('#Zones').html());//替换表情
		// });
	</script>
		
	
</body>
</html>
