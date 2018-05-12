<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="zh-CN"><head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>星火草原商城</title>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="x-dns-prefetch-control" content="on">   
    <meta http-equiv="x-dns-prefetch-control" content="on">
    <!--S 线上样式-->
    <link href="<%=request.getContextPath()%>/css/base.s.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/css/mod_nav_foot.s.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/css/wx_bar_v2.s.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/css/wx_bar_nian.s.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/css/wallet_v2.s.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/css/wallet_v2_guide.s.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/css/footer.css" rel="stylesheet" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/liuliang/jquery.1.9.1.min.js"></script>
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
    <!--E 线上样式-->


    <style type="text/css">
		.wet_app_panel{min-height: 40px;}
        .wet_mat_panel{min-height: 100px;}
        .WX_backtop_active {display: block;bottom:60px;}
    </style>
</head>
<body>

<!-- 头部s -->

<div id="topsearchbar" class="wx_search wx_search_chanel_mode wx_search_nian wx_search_promote_ex">
	  <input type="hidden" id="atCity">
      <div class="wx_search_inner">
        <div class="wx_bar_cate"><a href="<%=request.getContextPath()%>/city/cityAction!cities.action" class="city_btn">
         <c:choose>
          <c:when test="${not empty city.shortname}">
          	<span id="cityNames"></span>
          </c:when>
          <c:otherwise> 
          	<span id="cityNames"></span>
          </c:otherwise>
         </c:choose>
       
        </a></div>
        <form action="" method="get" class="wx_search_form" onsubmit="return false;">
            <input name="key" class="wx_search_txt" placeholder="" type="search" id="topSearchTxt" autocomplete="off">
        </form>
    </div>

</div>

<!-- 头部e -->


<!-- 幻灯片s -->
  
<link href="<%=request.getContextPath()%>/css/flexslider.css" rel="stylesheet" />
    <div style="height:45px;"></div>
    
    <div class="banner">
      <div class="flexslider"> 
        <ul class="slides"> 
         
         <li onclick="javascript:chooseUrl(5,92);"><img src="<%=request.getContextPath()%>/images/miaosha/meixiongyi/banna.jpg" /></li>
          <li onclick="javascript:chooseUrl(5,93);"><img src="<%=request.getContextPath()%>/images/miaosha/qinangzhen/banna.jpg" /></li>
          <li onclick="javascript:chooseUrl(5,94);"><img src="<%=request.getContextPath()%>/images/miaosha/yangshengyi/banna.jpg" /></li>
          <li onclick="javascript:chooseUrl(5,95);"><img src="<%=request.getContextPath()%>/images/miaosha/jianjingzhuililiaoyi/banna.jpg" /></li>
          <li onclick="javascript:chooseUrl(5,97);"><img src="<%=request.getContextPath()%>/images/miaosha/lengfengzuodian/banna.jpg" /></li> 
          
         <li onclick="javascript:chooseUrl(5,96);"><img src="<%=request.getContextPath()%>/images/miaosha/yaobao/banna.jpg" /></li> 
          <%-- <li onclick="javascript:chooseUrl(5,91);"><img src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/banna.jpg" /></li>   --%>        
        </ul> 
      </div>
    </div>
  

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.flexslider-min.js"></script>
<!-- zhang_wanqiang 城市定位-->
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=cVZOceoa2o3Vl3LOxAYKsKNPU1avOE4w"></script>
<div id="bdMapBox" style="display:none;"></div>
<script>
wx.config({
    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: "${appId}", // 必填，公众号的唯一标识
    timestamp: '${timestamp}', // 必填，生成签名的时间戳
    nonceStr: '${nonce_str}', // 必填，生成签名的随机串
    signature: '${signature}',// 必填，签名，见附录1
    jsApiList: ['menuItem:share:appMessage','onMenuShareAppMessage',
                'menuItem:share:timeline','onMenuShareTimeline',
                'menuItem:share:qq','onMenuShareQQ',
                'menuItem:share:weiboApp','onMenuShareWeibo',
                'menuItem:share:QZone','onMenuShareQZone'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
});

wx.ready(function(){
    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
 /* wx.checkJsApi({
	    jsApiList: ['menuItem:share:appMessage','onMenuShareAppMessage',
	                'menuItem:share:timeline','onMenuShareTimeline',
	                'menuItem:share:qq','onMenuShareQQ',
	                'menuItem:share:weiboApp','onMenuShareWeibo',
	                'menuItem:share:QZone','onMenuShareQZone'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
	    success: function(res) {
	        // 以键值对的形式返回，可用的api值true，不可用为false
	        // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
	    }
	});  */	
  wx.onMenuShareTimeline({
	    title: '星火草原首页商城', // 分享标题
	    link: '${url}', // 分享链接
	    imgUrl: 'https://mmbiz.qlogo.cn/mmbiz/pKWIGyfSjUm56IyFxsNI5shwgexlbGhiaEOwrTI87P4ic8DE6xq2jVJYMmfO0ViaAla2PyveBsTxeNOvBCaueZibyg/0?wx_fmt=jpeg', // 分享图标
	    success: function () { 
	        // 用户确认分享后执行的回调函数
	    	alert("分享成功");
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	    	alert("您取消了分享");
	    }
	});
 wx.onMenuShareAppMessage({
	    title: '星火草原首页商城', // 分享标题
	    desc: '大众创业,万众创新,一切颠覆尽在其中', // 分享描述
	    link: '${url}', // 分享链接
	    imgUrl: 'https://mmbiz.qlogo.cn/mmbiz/pKWIGyfSjUm56IyFxsNI5shwgexlbGhiaEOwrTI87P4ic8DE6xq2jVJYMmfO0ViaAla2PyveBsTxeNOvBCaueZibyg/0?wx_fmt=jpeg', // 分享图标
	    //type: '', // 分享类型,music、video或link，不填默认为link
	    //dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
	    success: function () { 
	        // 用户确认分享后执行的回调函数
	        alert("分享成功");
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	    	alert("您取消了分享");
	    }
	});
 wx.onMenuShareQQ({
	    title: '星火草原首页商城', // 分享标题
	    desc: '大众创业,万众创新,一切颠覆尽在其中', // 分享描述
	    link: '${url}', // 分享链接
	    imgUrl: 'https://mmbiz.qlogo.cn/mmbiz/pKWIGyfSjUm56IyFxsNI5shwgexlbGhiaEOwrTI87P4ic8DE6xq2jVJYMmfO0ViaAla2PyveBsTxeNOvBCaueZibyg/0?wx_fmt=jpeg', // 分享图标
	    success: function () { 
	       // 用户确认分享后执行的回调函数
	    	alert("分享成功");
	    },
	    cancel: function () { 
	       // 用户取消分享后执行的回调函数
	    	alert("您取消了分享");
	    }
	});
 wx.onMenuShareWeibo({
	    title: '星火草原首页商城', // 分享标题
	    desc: '大众创业,万众创新,一切颠覆尽在其中', // 分享描述
	    link: '${url}', // 分享链接
	    imgUrl: 'https://mmbiz.qlogo.cn/mmbiz/pKWIGyfSjUm56IyFxsNI5shwgexlbGhiaEOwrTI87P4ic8DE6xq2jVJYMmfO0ViaAla2PyveBsTxeNOvBCaueZibyg/0?wx_fmt=jpeg', // 分享图标
	    success: function () { 
	       // 用户确认分享后执行的回调函数
	    	alert("分享成功");
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	    	alert("您取消了分享");
	    }
	});
 wx.onMenuShareQZone({
	    title: '星火草原首页商城', // 分享标题
	    desc: '大众创业,万众创新,一切颠覆尽在其中', // 分享描述
	    link: '${url}', // 分享链接
	    imgUrl: 'https://mmbiz.qlogo.cn/mmbiz/pKWIGyfSjUm56IyFxsNI5shwgexlbGhiaEOwrTI87P4ic8DE6xq2jVJYMmfO0ViaAla2PyveBsTxeNOvBCaueZibyg/0?wx_fmt=jpeg', // 分享图标
	    success: function () { 
	       // 用户确认分享后执行的回调函数
	    	alert("分享成功");
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	    	alert("您取消了分享");
	    }
	});
});

wx.error(function(res){
    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
	
    wx.hideOptionMenu();//被分享的页面无法再次分享
});



var msg="${requestScope.tipMessage}";
if(msg!=""){
	alert(msg);
}

/**
 * 发送给朋友: "menuItem:share:appMessage"
	 分享到朋友圈: "menuItem:share:timeline"
 */
$(function(){
	//shwoCity();
	/* $(".quick-entry-link").each(function(){
		$(this).bind("click",function(){
			zanwu();
		})
	}); */
  $(".flexslider").flexslider({
	  animation: "slide",
	  directionNav: false,
	  touch: true
  });
});

function chooseUrl(type,prodId){
	 /*  var user = '${loginUser}';
	  if(user == null || user == "" || user == '' || user == " "){
		  alert("请刷新页面后再试哦！");
		  return false;
	  } */
	  if(type==1){
		  window.location.href="<%=request.getContextPath()%>/user/userAction!queryLiuBuy.action";
	  }else if(type==2){
		  window.location.href="<%=request.getContextPath()%>/companyInfo/companyInfoAction!editPage.action";
	  }else if(type==3){
		  window.location.href="<%=request.getContextPath()%>/user/userAction!queryLiuBuy.action";
	  }else if(type==4){
		  window.location.href="<%=request.getContextPath()%>/pay/payGoodAction!toGoodBuy.action";
	  }
	  else if(type==5){
		  //https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1967ca0ef7d225c0&redirect_uri=http://css.starsgrassland.com/user/userAction!jampshopIndex.action&response_type=code&scope=snsapi_base&state=asd#wechat_redirect
		  window.location.href="<%=request.getContextPath()%>/pay/payGoodAction!toMianMoBuy.action?prodId="+prodId;
		  //window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1967ca0ef7d225c0&redirect_uri=http://css.starsgrassland.com/pay/payGoodAction!toMianMoBuy.action?prodId="+prodId+"&response_type=code&scope=snsapi_userinfo&state="+"${user.userId}"+"#wechat_redirect";
	  }else if(type==6){
		  window.location.href="http://mp.weixin.qq.com/s?__biz=MzAwMzc2MDA1MA==&mid=554343834&idx=1&sn=1246afe1c8443c0d855bc44fa3a680e3#rd";
	  }
	  
 }
 

/**
 * 显示城市名称  zhangwanqiang 
 */
function shwoCity(){
	var cityName = $("#cityNames");
	if(cityName.val()==""){
			$.ajax({
		        type:"POST",
		        url:"<%=request.getContextPath()%>/city/cityAction!showCity.action",
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
		        				url:"<%=request.getContextPath()%>/city/cityAction!putCity.action",
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
<!-- 幻灯片e -->


<!-- 领券s -->
        
        <div class="wet_space size_s top"></div>
        
        <nav class="quick-entry-nav">
        	<a class="quick-entry-link" href="<%=request.getContextPath()%>/user/userAction!gotoHuiyuanShop.action"><img width="45" height="45" src="<%=request.getContextPath()%>/images/nav/4.png"><span>会员专区</span></a>
            <a class="quick-entry-link" href="<%=request.getContextPath()%>/user/userAction!gotoJifenShop.action"><img width="45" height="45" src="<%=request.getContextPath()%>/images/nav/7.png"><span>微积分商城</span></a>
            <a class="quick-entry-link" href="javascript:chooseUrl(1,0);"><img width="45" height="45" src="<%=request.getContextPath()%>/images/nav/1.png"><span>流量商城</span></a>
            <a class="quick-entry-link" href="<%=request.getContextPath()%>/companyInfo/companyInfoAction!editPage.action"><img width="45" height="45" src="<%=request.getContextPath()%>/images/nav/2.png"><span>厂家入驻</span></a>
            <%-- <a class="quick-entry-link" href="javascript:zanwu();"><img width="45" height="45" src="<%=request.getContextPath()%>/images/nav/5.png"><span>电话卡主</span></a>
            <a class="quick-entry-link" href="<%=request.getContextPath()%>/user/userAction!buy.action"><img width="45" height="45" src="<%=request.getContextPath()%>/images/nav/1.png"><span>VIP专区</span></a>
            <a class="quick-entry-link" href="javascript:zanwu();"><img width="45" height="45" src="<%=request.getContextPath()%>/images/nav/3.png"><span>店铺专区</span></a>
            <a class="quick-entry-link" href="javascript:zanwu();"><img width="45" height="45" src="<%=request.getContextPath()%>/images/nav/6.png"><span>秒杀专区</span></a> --%>
            <%-- <a class="quick-entry-link" href="<%=request.getContextPath()%>/user/ivmcAction!ivmCountryList.action"><img width="45" height="45" src="<%=request.getContextPath()%>/images/nav/6.png"><span>我爱家乡</span></a> --%>
        </nav>
        
        
        <div class="wet_space size_s top"></div>
        
        <div class="ind-not">
			<h2>☆ 星火公告 ☆</h2>
			<a href="<%=request.getContextPath()%>/notice/noticeAction!noticeList.action" ><span id="noticeUl">最新公告最新公告最新公告</span></a>
		</div>

		<div class="wet_space size_s top"></div>
<!-- 领券e -->
<!-- 特价s -->

<!-- 特价s -->
<div class="wet_bnr " id="topTonglan">
  <div class="item"><a href="javascript:chooseUrl(5,2);" class="url"><img style="width: 100%" src="<%=request.getContextPath()%>/images/miaosha/mianmo/mm32.jpg" alt="" onload="firstImgLoaded();"></a></div>
  <div class="wet_space size_s btm"></div>
    <div class="item"><a href="javascript:chooseUrl(5,49);" class="url"><img style="width: 100%" src="<%=request.getContextPath()%>/images/miaosha/shuiguangzhen/shuiguangzhenhengfu.jpg" alt="" onload="firstImgLoaded();"></a></div>
  <div class="wet_space size_s btm"></div>
</div>
         <div class="wet_spe_panel">
            <div class="spe_span span_l" id="divTejia" onclick="chooseUrl(5,95);">
              <div class="hd"><h3>新品推荐</h3></div>
              <div class="info"><!-- 倒计时 -->
                <div class="tp"><%-- <span class="food">家电场</span> <span class="mod_time_counter"><b class="h">00</b><span class="p">:</span><b class="m">42</b><span class="p">:</span> <b class="s">17</b></span><span>后结束</span> --%></div>
                <div class="ct"><a href="#"><span class="ct_name">壹优肩颈椎理疗仪299元</span><div class="img"><img src="<%=request.getContextPath()%>/images/miaosha/jianjingzhuililiaoyi/a2.jpg"></div></a></div>
                <div class="ft"><%-- <a href="#" class="line"><span class="wet_c_red">[08:00] 家庭场</span> 双层煮蛋器29.9元</a> --%></div>
              </div>
            </div>
            
            <div class="spe_span span_r">
     
                <a href="#" class="s_col brand_col" onclick="chooseUrl(5,94)">
                    <div class="r_img" > 
                        <div class="img_info">                  
                        <h3>壹优养生衣599元</h3>
                        <div class="img_logo">
                        <%-- <img src="<%=request.getContextPath()%>/images/c/6.jpg"> --%>
                        </div>
                        <div class="img_des">针对肩周炎，颈椎疼痛</div> 
                    </div>
                    <div class="img"> 
                        <img src="<%=request.getContextPath()%>/images/miaosha/yangshengyi/a1.jpg" onload="firstImgLoaded();">
                    </div>
                    </div>
                </a>
                <a href="#" class="s_col" onclick="chooseUrl(5,93)">
                    <div class="r_img" > 
                        <div class="img_info">                  
                        <h3>壹优气囊式颈椎理疗枕头299元</h3>
                        <div class="img_logo">
                               改善睡眠，呵护颈椎                                                     </div>
                        <!-- <div class="img_des">10:08爆款抢购</div>  -->
                    </div>
                    <div class="img">
                        <img src="<%=request.getContextPath()%>/images/miaosha/qinangzhen/a1.jpg" onload="firstImgLoaded();">
                    </div>
                    </div>
                </a>
            </div>
        </div> 
        <div class="wet_space"></div>
         <div class="wet_spe_panel">
            <div class="spe_span span_l" id="divTejia" onclick="chooseUrl(5,92);">
              <div class="hd"><h3>新品推荐</h3></div>
              <div class="info"><!-- 倒计时 -->
                <div class="tp"><%-- <span class="food">家电场</span> <span class="mod_time_counter"><b class="h">00</b><span class="p">:</span><b class="m">42</b><span class="p">:</span> <b class="s">17</b></span><span>后结束</span> --%></div>
                <div class="ct"><a href="#"><span class="ct_name">壹优丰韵美胸仪399元</span><div class="img"><img src="<%=request.getContextPath()%>/images/miaosha/meixiongyi/a2.jpg"></div></a></div>
                <div class="ft"><%-- <a href="#" class="line"><span class="wet_c_red">[08:00] 家庭场</span> 双层煮蛋器29.9元</a> --%></div>
              </div>
            </div>
            
            <div class="spe_span span_r">
     
                <a href="#" class="s_col brand_col" onclick="chooseUrl(5,96)">
                    <div class="r_img" > 
                        <div class="img_info">                  
                        <h3>壹优纯草本精华热敷调理包(三袋装)99元</h3>
                        <div class="img_logo">
                        <%-- <img src="<%=request.getContextPath()%>/images/c/6.jpg"> --%>
                        </div>
                        <div class="img_des">疏通经络 刺激穴位</div> 
                    </div>
                    <div class="img"> 
                        <img src="<%=request.getContextPath()%>/images/miaosha/yaobao/a1.jpg" onload="firstImgLoaded();">
                    </div>
                    </div>
                </a>
                <a href="#" class="s_col" onclick="chooseUrl(5,97)">
                    <div class="r_img" > 
                        <div class="img_info">                  
                        <h3>壹优车尚品冷风坐垫130元</h3>
                        <div class="img_logo">
                               清凉一夏                                                        </div>
                        <!-- <div class="img_des">10:08爆款抢购</div>  -->
                    </div>
                    <div class="img">
                        <img src="<%=request.getContextPath()%>/images/miaosha/lengfengzuodian/a1.jpg" onload="firstImgLoaded();">
                    </div>
                    </div>
                </a>
            </div>
        </div> 
        <div class="wet_space"></div>
         <div class="wet_spe_panel">
            <div class="spe_span span_l" id="divTejia" onclick="chooseUrl(5,50);">
              <div class="hd"><h3>新品推荐</h3></div>
              <div class="info"><!-- 倒计时 -->
                <div class="tp"><%-- <span class="food">家电场</span> <span class="mod_time_counter"><b class="h">00</b><span class="p">:</span><b class="m">42</b><span class="p">:</span> <b class="s">17</b></span><span>后结束</span> --%></div>
                <div class="ct"><a href="#"><span class="ct_name">浩沙水钻款活力裤66元</span><div class="img"><img src="<%=request.getContextPath()%>/images/miaosha/huoliku/shuizuan/shuizuana2.jpg"></div></a></div>
                <div class="ft"><%-- <a href="#" class="line"><span class="wet_c_red">[08:00] 家庭场</span> 双层煮蛋器29.9元</a> --%></div>
              </div>
            </div>
            
            <div class="spe_span span_r">
     
                <a href="#" class="s_col brand_col" onclick="chooseUrl(5,82)">
                    <div class="r_img" > 
                        <div class="img_info">                  
                        <h3>净懿空气净化器799元</h3>
                        <div class="img_logo">
                        <%-- <img src="<%=request.getContextPath()%>/images/c/6.jpg"> --%>
                        </div>
                        <div class="img_des">好水 好空气 好生活</div> 
                    </div>
                    <div class="img"> 
                        <img src="<%=request.getContextPath()%>/images/miaosha/jiayongjinghuaqi/a2.jpg" onload="firstImgLoaded();">
                    </div>
                    </div>
                </a>
                <a href="#" class="s_col" onclick="chooseUrl(5,52)">
                    <div class="r_img" > 
                        <div class="img_info">                  
                        <h3>浩沙腿部花纹款活力裤66元</h3>
                        <div class="img_logo">
                               给你最舒适的感觉                                                        </div>
                        <!-- <div class="img_des">10:08爆款抢购</div>  -->
                    </div>
                    <div class="img">
                        <img src="<%=request.getContextPath()%>/images/miaosha/huoliku/tuibuhuawen/tuibuhuawena2.jpg" onload="firstImgLoaded();">
                    </div>
                    </div>
                </a>
            </div>
        </div> 
        <div class="wet_space"></div>
       
<!-- 特价e -->
        <div class="wet_pdt_panel wet_phone_panel" id="channelFloor2">
            <div class="hd"><a href="#"><h3><img src="<%=request.getContextPath()%>/images/c1.png" class="floor_img">抢购专区<%-- <span class="tit_des">稀缺手机提前抢 快人一步</span><span class="sign_ps">京东配送</span> --%></h3><span class="more"><span class="arrow"></span></span></a></div>
            <div class="info">
              <div class="l_item"><a href="javascript:chooseUrl(5,3);" class="item"><i class="hot"></i><h4>集联集  焕肤驻颜深层滋养精华面膜4片装</h4><p>焕发您肌肤活力光彩</p><div class="img"><img src="<%=request.getContextPath()%>/images/miaosha/mianmo/mma3.jpg" loaded="12"></div></a><a href="javascript:chooseUrl(5,59);" class="enter_shop">立即进入</a></div>
              <div class="r_item">
                <ul>
                  <li><a href="javascript:chooseUrl(5,2);" class="item span_item"><i></i><div class="des_info"><div class="posit"><i class="hot"></i><h4>集联集  焕肤驻颜深层滋养精华面膜2片装</h4><p>焕发您肌肤活力光彩</p></div></div><div class="img"><img src="<%=request.getContextPath()%>/images/miaosha/mianmo/mma4.jpg" loaded="11"></div></a></li>
                  <%-- <li><a href="<%=request.getContextPath()%>/user/userAction!miaosha.action" class="item"><i></i><h4>汇付天下蓝牙pos机</h4><p>秒刷秒到神器,轻便易携带</p><div class="img"><img src="<%=request.getContextPath()%>/images/miaosha/posimg/pos4.jpg" loaded="10"></div></a></li>
                  <li><a href="javascript:zanwu();" class="item"><i></i><h4>汇通天下蓝牙pos机 </h4><p>秒刷秒到神器,轻便易携带</p><div class="img"><img src="<%=request.getContextPath()%>/images/miaosha/posimg/pos4.jpg" loaded="9"></div></a></li> --%>
                  <li><a href="javascript:chooseUrl(5,53);" class="item"><i class="hot"></i><h4>可贝尔眼纹消</h4><p>祛除眼纹，滋润肌肤</p><div class="img"><img src="<%=request.getContextPath()%>/images/miaosha/kebeieryanwenxiao/a2.jpg" loaded="14"></div></a></li>
                  <li><a href="javascript:chooseUrl(5,49);" class="item"><i class="hot"></i><h4>集联集水光针</h4><p>肌肤弹嫩滑</p><div class="img"><img src="<%=request.getContextPath()%>/images/miaosha/shuiguangzhen/shuiguangzhena3.jpg" loaded="13"></div></a></li>
               
                </ul>
              </div>
            </div>
        </div>

<div class="wet_space"></div>


<div id="channelFloor">

<!-- 手机s -->
        <div class="wet_pdt_panel wet_phone_panel" id="channelFloor2">
            <div class="hd"><a href="#"><h3><img src="<%=request.getContextPath()%>/images/c1.png" class="floor_img">爆款专区<%-- <span class="tit_des">稀缺手机提前抢 快人一步</span><span class="sign_ps">京东配送</span> --%></h3><span class="more"><span class="arrow"></span></span></a></div>
            <div class="info">
              <div class="l_item"><a href="javascript:chooseUrl(5,59);" class="item"><i class="hot"></i><h4>智能调理文胸</h4><p>适合大胸，完美贴身</p><div class="img"><img src="<%=request.getContextPath()%>/images/miaosha/neiyi/heiseduankuan/a2.jpg" loaded="12"></div></a><a href="javascript:chooseUrl(5,59);" class="enter_shop">立即进入</a></div>
              <div class="r_item">
                <ul>
                  <li><a href="javascript:chooseUrl(5,6);" class="item span_item"><i></i><div class="des_info"><div class="posit"><i class="hot"></i><h4>丛菲凡生态智能调理内衣</h4><p>"知己难求"，爱不释手</p></div></div><div class="img"><img src="<%=request.getContextPath()%>/images/miaosha/neiyi/hongse/a2.jpg" loaded="11"></div></a></li>
                  <%-- <li><a href="<%=request.getContextPath()%>/user/userAction!miaosha.action" class="item"><i></i><h4>汇付天下蓝牙pos机</h4><p>秒刷秒到神器,轻便易携带</p><div class="img"><img src="<%=request.getContextPath()%>/images/miaosha/posimg/pos4.jpg" loaded="10"></div></a></li>
                  <li><a href="javascript:zanwu();" class="item"><i></i><h4>汇通天下蓝牙pos机 </h4><p>秒刷秒到神器,轻便易携带</p><div class="img"><img src="<%=request.getContextPath()%>/images/miaosha/posimg/pos4.jpg" loaded="9"></div></a></li> --%>
                  <li><a href="javascript:chooseUrl(5,14);" class="item"><i class="hot"></i><h4>丛菲凡连体塑身内衣</h4><p>收腰 提臀 丰胸</p><div class="img"><img src="<%=request.getContextPath()%>/images/miaosha/neiyi/heise/a2.jpg" loaded="14"></div></a></li>
                  <li><a href="javascript:chooseUrl(5,19);" class="item"><i class="hot"></i><h4>丛菲凡分体塑身内衣</h4><p>有了它，穿什么衣服都漂亮</p><div class="img"><img src="<%=request.getContextPath()%>/images/miaosha/neiyi/fense/a2.jpg" loaded="13"></div></a></li>
               
                </ul>
              </div>
            </div>
        </div>
<!-- 手机e -->

<div class="wet_space"></div>

<!-- 超市s -->
        <div class="wet_pdt_panel wet_shop_panel" id="channelFloor1">
            <div class="hd"><a href="#"><h3><img src="<%=request.getContextPath()%>/images/c2.png" class="floor_img">秒杀专区<span class="tit_des">真的很好 还省</span><%-- <span class="sign_ps">星火专供</span></h3><span class="more"><span class="arrow"></span></span> --%></a></div>
            <div class="info">
              <div class="l_item"><a href="javascript:chooseUrl(5,39);" class="item"><h4>爱加倍卫生巾</h4><p>混合套装</p><div class="img"><img src="<%=request.getContextPath()%>/images/miaosha/wsj/wsja3.jpg" loaded="17"></div></a><a href="javascript:chooseUrl(5,39);" class="enter_shop">立即购买</a></div>
              <div class="r_item">
                <ul>
                  <li><a href="javascript:chooseUrl(5,40);" class="item"><h4>大树红茶</h4><p>丝滑浓厚 口感鲜爽</p><div class="img"><img src="<%=request.getContextPath()%>/images/miaosha/cha/gushuhongcha/hongchaa3.jpg" loaded="16"></div></a></li>
                  <li><a href="javascript:chooseUrl(5,29);" class="item"><h4>壹优眼罩</h4><p>祛黑眼圈美容护眼罩</p><div class="img"><img src="<%=request.getContextPath()%>/images/miaosha/yanzhao/a2.jpg" loaded="15"></div></a></li>
                 <li><a href="javascript:chooseUrl(5,4);" class="item"><i></i><h4>净懿车载净化器</h4><p>对污染说NO</p><div class="img"><img src="<%=request.getContextPath()%>/images/miaosha/jinghuaqi/jinghuaqia3.jpg" loaded="10"></div></a></li>
                  <li><a href="javascript:chooseUrl(5,5);" class="item"><i></i><h4>净亚超滤净水器 </h4><p>健康好水 源自超滤</p><div class="img"><img src="<%=request.getContextPath()%>/images/miaosha/jingshuiji/jingshuijia3.jpg" loaded="9"></div></a></li>
                </ul>
              </div>
            </div>
        </div>
<!-- 超时e -->

<div class="wet_space"></div>
<%-- 
<!-- 电器s -->
        <div class="wet_pdt_panel wet_house_panel" id="channelFloor4">
            <div class="hd"><a href="#"><h3><img src="<%=request.getContextPath()%>/images/c3.png" class="floor_img">家用电器<span class="tit_des">7天无理由退换 30天价保</span><span class="sign_ps">京东配送</span></h3><span class="more"><span class="arrow"></span></span></a></div>
            <div class="info">
              <div class="l_item"><a href="#" class="item"><h4>京东年货家电馆</h4><p>滚筒洗衣机999元起</p><div class="img"><img src="<%=request.getContextPath()%>/images/c/c1.jpg" loaded="4"></div></a><a href="#" class="enter_shop">进入馆区</a></div>
              <div class="r_item">
                <ul>
                  <li><a href="#" class="item span_item"><i class="hot"></i><div class="des_info"><div class="posit"><h4>家电年货专场</h4><p>60英寸智能电视3999</p></div></div><div class="img"><img src="<%=request.getContextPath()%>/images/c/c2.jpg" loaded="3"></div></a></li>
                  <li><a href="#" class="item"><i class="hot"></i><h4>电视年货免费抢</h4><p>白条12期免息</p><div class="img"><img src="<%=request.getContextPath()%>/images/c/c3.jpg" loaded="2"></div></a></li>
                  <li><a href="#" class="item"><h4>韩电品牌日</h4><p>洗衣机低至399元</p><div class="img"><img src="<%=request.getContextPath()%>/images/c/c4.jpg" loaded="1"></div></a></li>
                </ul>
              </div>
            </div>
        </div>
<!-- 电器e -->

<div class="wet_space"></div>

<!-- 电脑s -->
        <div class="wet_pdt_panel wet_computer_panel" id="channelFloor3">
            <div class="hd"><a href="#"><h3><img src="<%=request.getContextPath()%>/images/c4.png" class="floor_img">电脑数码<span class="tit_des">购物0首付 畅享30天免息</span><span class="sign_ps">京东配送</span></h3><span class="more"><span class="arrow"></span></span></a></div>
            <div class="info">
              <div class="l_item"><a href="#" class="item"><h4>京东电脑办公馆</h4><p>小新i7本每满千减百</p><div class="img"><img src="<%=request.getContextPath()%>/images/c/d1.jpg" loaded="8"></div></a><a href="#" class="enter_shop">进入馆区</a></div>
              <div class="r_item">
                <ul>
                  <li><a href="#" class="item span_item"><i class="hot"></i><div class="des_info"><div class="posit"><h4>数码主题馆</h4><p>2W毫安充电宝79元</p></div></div><div class="img"><img src="<%=request.getContextPath()%>/images/c/d2.jpg" loaded="7"></div></a></li>
                  <li><a href="#" class="item"><h4>笔记本旗舰专场</h4><p>小新直降200起</p><div class="img"><img src="<%=request.getContextPath()%>/images/c/d3.jpg" loaded="6"></div></a></li>
                  <li><a href="#" class="item"><h4>苹果数码节</h4><p>不仅仅是促销</p><div class="img"><img src="<%=request.getContextPath()%>/images/c/d4.jpg" loaded="5"></div></a></li>
                </ul>
              </div>
            </div>
        </div>
<!-- 电脑e -->

</div>
<div class="wet_space"></div>

<!-- 分类区s -->
    <div class="wet_section wet_v2_panel">
        <div class="wet_mat_panel">
            <div class="wet_mat_list">
            
            <div class="item">
            <a href="#" class="url">
            <div class="sign hot">hot</div>
            <div class="info"><div class="name">女装内衣</div><div class="desc">腊八节<span>低至￥29.9</span></div></div>
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/p1.png"></div>
            </a>
            </div>
            
            <div class="item">
            <a href="#" class="url">
            <div class="sign hot">hot</div>
            <div class="info"><div class="name">大牌男装</div><div class="desc">年末清仓<span>低至69元</span></div></div>
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/p2.png"></div>
            </a>
            </div>
            
            <div class="item">
            <a href="#" class="url">
            <div class="info"><div class="name">男鞋女鞋</div><div class="desc">全场低至<span>49元</span></div></div>
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/p3.png"></div>
            </a>
            </div>
            
            <div class="item">
            <a href="#" class="url">
            <div class="info"><div class="name">运动大牌</div><div class="desc">领券<span>满299减60</span></div></div>
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/p4.png"></div>
            </a>
            </div>
            
            <div class="item">
            <a href="#" class="url">
            <div class="sign hot">hot</div>
            <div class="info"><div class="name">潮流箱包</div><div class="desc">199旅箱送背包</div></div>
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/p5.png"></div>
            </a>
            </div>
            
            <div class="item">
            <a href="#" class="url">
            <div class="info"><div class="name">钟表美妆</div><div class="desc">最高<span>满199减100</span></div></div>
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/p6.png"></div>
            </a>
            </div>
            
            <div class="item">
            <a href="#" class="url">
            <div class="info"><div class="name">年货家居</div><div class="desc">沙发699元</div></div>
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/p7.png"></div>
            </a>
            </div>
            
            <div class="item">
            <a href="#" class="url">
            <div class="info"><div class="name">母婴玩具</div><div class="desc">尿裤最高<span>满399减80</span></div></div>
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/p8.png"></div>
            </a>
            </div>
            
            <div class="item">
            <a href="#" class="url">
            <div class="sign hot">hot</div>
            <div class="info"><div class="name">图书音像</div><div class="desc">满减叠<span>199减100</span>券</div></div>
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/p9.png"></div>
            </a>
            </div>
            
            </div>
        </div>
        
    </div>
<!-- 分类区e -->

<div class="wet_space"></div>

<div class="wet_bnr" id="midBanner">
  <div class="item"><a href="#" class="url"><img src="<%=request.getContextPath()%>/images/c/7.jpg" alt=""></a></div><div class="wet_space size_s btm"></div>
</div>


<!-- 闪购s -->
<div class="wet_global_panel" id="specialChannel">
  <div class="global_span"><h3>闪购自营</h3><a href="#" class="url"><div class="img"><img src="<%=request.getContextPath()%>/images/s1.jpg"></div></a><div class="ft"><div class="line">京东自营好货特卖 正品极速达</div></div></div>
  <div class="global_span"><h3>全球购进口尖货</h3><a href="#" class="url"><div class="img"><img src="<%=request.getContextPath()%>/images/s2.jpg" loaded="10"></div></a><div class="ft"><div class="line">进口精选大牌  正品保证</div></div></div>
</div>
<!-- 闪购e --> --%>

<div class="wet_space"></div>

<!-- 猜你喜欢s -->

<div class="wet_section" id="cnxhShow">
        <div class="section_hd">
            <h3>商品列表</h3>
            <div class="desc"><span class="time">23:58已更新</span></div>
        </div>
        <div class="wet_pro_panel wet_pro_v2_panel">
        
        <div class="wet_pro_list">
        <%-- <div class="item">
          	<a href="javascript:chooseUrl(5,91);" class="url" >
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/weimeiqi/a1.jpg" loaded="117"></div>
            </a>
            <div class="info">
              <div class="name" style="color: red;">维美七氨基酸经典洗护三件套</div>
              <div class="cost"><span class="price">¥199.00</span><del>¥399.00</del><!-- <div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div> --></div>
            </div>
          </div> --%>
          <div class="item">
          	<a href="javascript:chooseUrl(5,96);" class="url" >
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/yaobao/a1.jpg" loaded="117"></div>
            </a>
            <div class="info">
              <div class="name" style="color: red;">壹优纯草本精华热敷调理包(三袋装)</div>
              <div class="cost"><span class="price">¥99.00</span><del>¥124.00</del><!-- <div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div> --></div>
            </div>
          </div>
          <div class="item">
          	<a href="javascript:chooseUrl(5,97);" class="url" >
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/lengfengzuodian/a1.jpg" loaded="117"></div>
            </a>
            <div class="info">
              <div class="name" style="color: red;">壹优车尚品冷风坐垫</div>
              <div class="cost"><span class="price">¥130.00</span><del>¥148.00</del><!-- <div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div> --></div>
            </div>
          </div>
          
          <div class="item">
          	<a href="javascript:chooseUrl(5,95);" class="url" >
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/jianjingzhuililiaoyi/a1.jpg" loaded="117"></div>
            </a>
            <div class="info">
              <div class="name" style="color: red;">壹优远红外肩颈椎理疗仪</div>
              <div class="cost"><span class="price">¥299.00</span><del>¥368.00</del><!-- <div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div> --></div>
            </div>
          </div>
          <div class="item">
          	<a href="javascript:chooseUrl(5,94);" class="url" >
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/yangshengyi/a1.jpg" loaded="117"></div>
            </a>
            <div class="info">
              <div class="name" style="color: red;">壹优养生衣</div>
              <div class="cost"><span class="price">¥599.00</span><del>¥688.00</del><!-- <div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div> --></div>
            </div>
          </div>
          <div class="item">
          	<a href="javascript:chooseUrl(5,93);" class="url" >
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/qinangzhen/a1.jpg" loaded="117"></div>
            </a>
            <div class="info">
              <div class="name" style="color: red;">壹优气囊式颈椎理疗枕头</div>
              <div class="cost"><span class="price">¥299.00</span><del>¥369.00</del><!-- <div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div> --></div>
            </div>
          </div>
          <div class="item">
          	<a href="javascript:chooseUrl(5,92);" class="url" >
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/meixiongyi/a1.jpg" loaded="117"></div>
            </a>
            <div class="info">
              <div class="name" style="color: red;">壹优美胸仪</div>
              <div class="cost"><span class="price">¥399.00</span><del>¥490.00</del><!-- <div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div> --></div>
            </div>
          </div>
        <div class="item">
          	<a href="javascript:chooseUrl(5,83);" class="url" >
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/jiemianmosi/a1.jpg" loaded="117"></div>
            </a>
            <div class="info">
              <div class="name" style="color: red;">集联集洁面摩斯</div>
              <div class="cost"><span class="price">¥69.00</span><del>¥120.00</del><!-- <div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div> --></div>
            </div>
          </div>
        <div class="item">
          	<a href="javascript:chooseUrl(5,82);" class="url" >
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/jiayongjinghuaqi/a1.jpg" loaded="117"></div>
            </a>
            <div class="info">
              <div class="name" style="color: red;">净懿空气净化器</div>
              <div class="cost"><span class="price">¥799.00</span><del>¥4980.00</del><!-- <div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div> --></div>
            </div>
          </div>
         <div class="item">
          	<a href="javascript:chooseUrl(5,81);" class="url" >
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/nameibojinduotai/a1.jpg" loaded="117"></div>
            </a>
            <div class="info">
              <div class="name" style="color: red;">丽雅兰姬纳米铂金多肽</div>
              <div class="cost"><span class="price">¥380.00</span><del>¥3800.00</del><!-- <div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div> --></div>
            </div>
          </div>
        <div class="item">
          	<a href="javascript:chooseUrl(5,53);" class="url" >
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/kebeieryanwenxiao/a_1.jpg" loaded="117"></div>
            </a>
            <div class="info">
              <div class="name" style="color: red;">可贝尔眼纹消</div>
              <div class="cost"><span class="price">¥298.00</span><del>¥2612.00</del><!-- <div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div> --></div>
            </div>
          </div>
          <div class="item">
          	<a href="javascript:chooseUrl(5,54);" class="url" >
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/guanjieling/a1.jpg" loaded="117"></div>
            </a>
            <div class="info">
              <div class="name" style="color: red;">关节灵</div>
              <div class="cost"><span class="price">¥199.00</span><del>¥269.00</del><!-- <div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div> --></div>
            </div>
          </div>
           <div class="item">
          	<a href="javascript:chooseUrl(5,55);" class="url" >
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/jingzhuibao/a1.jpg" loaded="117"></div>
            </a>
            <div class="info">
              <div class="name" style="color: red;">颈椎宝</div>
              <div class="cost"><span class="price">¥99.00</span><del>¥148.00</del><!-- <div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div> --></div>
            </div>
          </div>
          <div class="item">
          	<a href="javascript:chooseUrl(5,56);" class="url" >
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/yaoshudai/a1.jpg" loaded="117"></div>
            </a>
            <div class="info">
              <div class="name" style="color: red;">腰舒带</div>
              <div class="cost"><span class="price">¥139.00</span><del>¥178.00</del><!-- <div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div> --></div>
            </div>
          </div>
            <div class="item">
          	<a href="javascript:chooseUrl(5,49);" class="url" >
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/shuiguangzhen/shuiguangzhen_a2.jpg" loaded="117"></div>
            </a>
            <div class="info">
              <div class="name" style="color: red;">集联集水光针</div>
              <div class="cost"><span class="price">¥99.00</span><del>¥360.00</del><!-- <div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div> --></div>
            </div>
          </div>
          <div class="item">
          	<a href="javascript:chooseUrl(5,59);" class="url" >
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heiseduankuan/a1.jpg" loaded="117"></div>
            </a>
            <div class="info">
              <div class="name" style="color: red;">丛菲凡智能调理文胸</div>
              <div class="cost"><span class="price">¥199.00</span><del>¥598.00</del><!-- <div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div> --></div>
            </div>
          </div>
          <div class="item">
          	<a href="javascript:chooseUrl(5,50);" class="url" >
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/shuizuan/a1.jpg" loaded="117"></div>
            </a>
            <div class="info">
              <div class="name" style="color: red;">水钻款活力裤</div>
              <div class="cost"><span class="price">¥66.00</span><del>¥199.00</del><!-- <div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div> --></div>
            </div>
          </div>
          <div class="item">
          	<a href="javascript:chooseUrl(5,51);" class="url" >
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/tu an/a1.jpg" loaded="117"></div>
            </a>
            <div class="info">
              <div class="name" style="color: red;">图案款活力裤</div>
              <div class="cost"><span class="price">¥49.00</span><del>¥159.00</del><!-- <div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div> --></div>
            </div>
          </div>
          <div class="item">
          	<a href="javascript:chooseUrl(5,52);" class="url" >
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/huoliku/tuibuhuawen/a1.jpg" loaded="117"></div>
            </a>
            <div class="info">
              <div class="name" style="color: red;">腿部花纹款活力裤</div>
              <div class="cost"><span class="price">¥66.00</span><del>¥199.00</del><!-- <div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div> --></div>
            </div>
          </div>
          <div class="item">
          	<a href="javascript:chooseUrl(5,40);" class="url" >
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/cha/gushuhongcha/hongchaa1.jpg" loaded="117"></div>
            </a>
            <div class="info">
              <div class="name" style="color: red;">大树红茶</div>
              <div class="cost"><span class="price">¥569.00</span><del>¥1980.00</del><!-- <div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div> --></div>
            </div>
          </div>
          <div class="item">
          	<a href="javascript:chooseUrl(5,2);" class="url" >
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/mianmo/mm1a1.jpg" loaded="117"></div>
            </a>
            <div class="info">
              <div class="name" style="color: red;">集联集  焕肤驻颜深层滋养精华面膜2片装</div>
              <div class="cost"><span class="price">¥59.00</span><del>¥159.00</del><div onclick="javascript:chooseUrl(5,3);" class="btn_relate" >看相似</div></div>
            </div>
          
          </div>
          
          <div class="item">
          	<a href="javascript:chooseUrl(5,3);" class="url" >
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/mianmo/mm2a1.jpg" loaded="117"></div>
            </a>
            <div class="info">
              <div class="name" style="color: red;">集联集  焕肤驻颜深层滋养精华面膜4片装</div>
              <div class="cost"><span class="price">¥99.00</span><del>¥360.00</del><div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div></div>
            </div>
          
          </div>
          
          <div class="item">
          	<a href="javascript:chooseUrl(5,4);" class="url" >
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/jinghuaqi/a1.jpg" loaded="117"></div>
            </a>
            <div class="info">
              <div class="name" style="color: red;">净懿车载净化器</div>
              <div class="cost"><span class="price">¥520.00</span><del>¥2390.00</del><!-- <div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div> --></div>
            </div>
          
          </div>
          
          <div class="item">
          	<a href="javascript:chooseUrl(5,5);" class="url" >
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/jingshuiji/a_1.jpg" loaded="117"></div>
            </a>
            <div class="info">
              <div class="name" style="color: red;">净亚超滤净水器</div>
              <div class="cost"><span class="price">¥430.00</span><del>¥2380.00</del><!-- <div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div> --></div>
            </div>
          
          </div>
          <div class="item">
          	<a href="javascript:chooseUrl(5,6);" class="url" >
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/hongse/hong_a1.jpg" loaded="117"></div>
            </a>
            <div class="info">
              <div class="name" style="color: red;">丛菲凡生态智能调理内衣</div>
              <div class="cost"><span class="price">¥199.00</span><del>¥498.00</del><!-- <div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div> --></div>
            </div>
          
          </div>
          <div class="item">
          	<a href="javascript:chooseUrl(5,14);" class="url" >
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/heise/hei_1.jpg" loaded="117"></div>
            </a>
            <div class="info">
              <div class="name" style="color: red;">丛菲凡连体塑身内衣</div>
              <div class="cost"><span class="price">¥299.00</span><del>¥2380.00</del><!-- <div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div> --></div>
            </div>
          
          </div>
          <div class="item">
          	<a href="javascript:chooseUrl(5,29);" class="url" >
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/yanzhao/a_1.jpg" loaded="117"></div>
            </a>
            <div class="info">
              <div class="name" style="color: red;">壹优眼罩</div>
              <div class="cost"><span class="price">¥99.00</span><del>¥168.00</del><!-- <div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div> --></div>
            </div>
          
          </div>
          
          <div class="item">
          	<a href="javascript:chooseUrl(5,19);" class="url" >
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/neiyi/fense/fen_a1.jpg" loaded="117"></div>
            </a>
            <div class="info">
              <div class="name" style="color: red;">丛菲凡分体塑身内衣</div>
              <div class="cost"><span class="price">¥519.00</span><del>¥3980.00</del><!-- <div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div> --></div>
            </div>
          </div>
          
          <div class="item">
          	<a href="javascript:chooseUrl(5,39);" class="url" >
            <div class="img"><img alt="" src="<%=request.getContextPath()%>/images/miaosha/wsj/wsja1.jpg" loaded="117"></div>
            </a>
            <div class="info">
              <div class="name" style="color: red;">爱加倍卫生巾</div>
              <div class="cost"><span class="price">¥99.00</span><del>¥148.00</del><!-- <div onclick="javascript:chooseUrl(5,2);" class="btn_relate" >看相似</div> --></div>
            </div>
          </div>

         
          
        </div>
        <!-- /list -->
        <c:if test="${code !=null}">
						<div><img style="width: 100%" alt="" src="<%=request.getContextPath()%>/images/lunbotu/xinghuogongzhonghao.jpg" /></div>
				</c:if>
        <div class="wet_pro_list_empty"><span>看完啦，待会儿再来~</span></div></div>
        <!-- /panel -->
    </div>
<!-- 猜你喜欢e -->

<a id="backToTop" href="#" class="WX_backtop WX_backtop_active" style="bottom: 60px;">返回顶部</a>



<!-- 底部s -->
<div id="footer">
  <div class="foot_nav">
    <div class="foot_nav_inner">
    <a href="<%=request.getContextPath()%>/user/userAction!phoneFamily.action"><span class="line"><i class="nav_f1"></i></span><span class="text">我的红包</span></a>
    <a href="<%=request.getContextPath()%>/user/userAction!jampshopIndex.action" class="hover"><span class="line"><i class="nav_f2"></i></span><span class="text">商城首页</span></a>
    <a href="<%=request.getContextPath()%>/user/userAction!qrcodePage.action"><span class="line"><i class="nav_f3"></i></span><span class="text">二维码</span></a>
    <a href="javascript:zanwu();"><span class="line"><i class="nav_f4"></i></span><span class="text">购物车</span></a>
    <a href="<%=request.getContextPath()%>/user/userAction!gotoPersonalCenter.action" ><span class="line"><i class="nav_f5"></i></span><span class="text">个人中心</span></a>
    </div>
  </div>
</div>
<!-- 底部e -->
<script type="text/javascript">
$(document).ready(function() { 
	$.ajax({
	    type:"POST",
	    url:"<%=request.getContextPath()%>/notice/noticeAction!findLastNotice.action",
	    dataType:"json",
	    success:function(data){
    		var ul = $("#noticeUl");
    		ul.html(data.noticeName);
	      }
	});
	}); 

    
  

</script>

</body>
</html>