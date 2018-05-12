var _link = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=";
var _linkEnd = "&redirect_uri=";
var _linkEnd2 = getWeichatUrl(1) + "/GrapRedEnvelopeServlet?method=grapRedRedEnvelope&partnerId=" + getPartnerId();
var _linkEnd3 = "&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
//var _linkEnd3 = "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";

var _imageUrl = getWeichatUrl(1) + "/images/weixin_ico.png";
var _title = "大汉流量银行！";
var _debug = false;
var _fenxianglink = "";
var _dialog = null;
function shareRedEnvelope(redEnvelopeId) {
	if (isweixin()) {// 是内置浏览器才去重写朋友圈分享等
		/*
		 * _dialog = $.dialog({ content : "加载中……" })
		 */
		getToken(redEnvelopeId);
	}

}
function getToken(redEnvelopeId) {
	var _url = encodeURIComponent(document.location);
	$.ajax({
		url : "../GetConfigParamsServlet?method=getParams",
		async : false,
		data : "pageUrl=" + _url,
		dataType : 'json',
		success : function(data) {
			var msg = data.resultMsg;
			if (data.resultCode == 0) {
				jsConfigAndReady(msg,redEnvelopeId);
				// 此处，当config失败时，将被动刷新ticket，且只尝试一次
				wx.error(function(res) {
					refreshTicket(_url);// 被动刷新一次ticket，并重新调用js接口

				});

			} else {
				window.location = '../sessionOut.html?page=personal.html&partnerId=' + getPartnerId();
			}
		},
		fail : function(msg) {
			window.location = '../sessionOut.html?page=personal.html&partnerId=' + getPartnerId();
		}
	});
}

function refreshTicket(_url) {
	$.ajax({
		url : "../GetConfigParamsServlet?method=refreshTicket",
		async : false,
		data : "pageUrl=" + _url,
		dataType : 'json',
		success : function(data) {
			var msg = data.resultMsg;
			if (data.resultCode == 0) {
				jsConfigAndReady(msg);
				// 此处，当config失败时，不再被动刷新ticket
				wx.error(function(res) {

				});
			} else {
				window.location = '../sessionOut.html?page=personal.html&partnerId=' + getPartnerId();
			}
		},
		fail : function(msg) {
			window.location = '../sessionOut.html?page=personal.html&partnerId=' + getPartnerId();
		}
	})
}

function startSaoyiSao() {
	wx.scanQRCode({
		needResult : 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
		scanType : [ "qrCode", "barCode" ], // 可以指定扫二维码还是一维码，默认二者都有
		success : function(res) {
			var result = res.resultStr;
			if (result) {

			}
		}
	});
}

function jsConfigAndReady(msg,redEnvelopeId) {
	wx.config({
		debug : _debug, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		appId : msg.appId,
		timestamp : msg.timestamp,
		nonceStr : msg.nonceStr,
		signature : msg.signature,
		jsApiList : [ 'onMenuShareTimeline', 'onMenuShareAppMessage', 'scanQRCode' ]
	});
	wx.ready(function() {
		var _End2 = _linkEnd2 + "&redEnvelopeId="+redEnvelopeId+"&parentOpenId="+msg.openId;
		var _link2 = encodeURIComponent(_End2)
		_link = _link + msg.appId + _linkEnd + _link2 + _linkEnd3;
		
		// 分享到朋友圈
		var mainUrl = '';
		var mainTitle = '';
		var mainImageUrl = '';
		if (getPartnerId() == 1) {
			mainUrl = _link;
			mainTitle = _title;
			mainImageUrl = _imageUrl;
		}
		wx.onMenuShareTimeline({
			title : mainTitle, // 分享标题
			link : mainUrl, // 分享链接
			imgUrl : mainImageUrl, // 分享图标
			success : function() {
				var url_hrl = window.location.href;
				if(url_hrl.indexOf("sendRedEnvelope.html") != -1){
					window.location.href ="../redenvelope/redEnvelopeInfo.html";
				}
				if(url_hrl.indexOf("phonecz.html")!= -1){
					window.location.href ="../phonecz.html";
				}
			},
			cancel : function() {

			}
		});
		// 发送给好友
		wx.onMenuShareAppMessage({
			title : mainTitle, // 分享标题
			desc : mainTitle, // 分享描述
			link : mainUrl, // 分享链接
			imgUrl : mainImageUrl, // 分享图标
			type : 'link', // 分享类型,music、video或link，不填默认为link
			dataUrl : '', // 如果type是music或video，则要提供数据链接，默认为空
			success : function() {
				var url_hrl = window.location.href;
				if(url_hrl.indexOf("sendRedEnvelope.html") != -1){
					window.location.href ="../redenvelope/redEnvelopeInfo.html";
				}
				if(url_hrl.indexOf("phonecz.html")!= -1){
					window.location.href ="../phonecz.html";
				}
			},
			cancel : function() {
				// 用户取消分享后执行的回调函数
			}
		});
		/*
		 * if (null != _dialog) { _dialog.close(); }
		 */
	});
}