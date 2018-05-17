var diag = null;
$(function() {
	/*
	 * if (!browserRedirect()) { layer.alert('请使用移动端登陆，敬请期待大汉流量银行PC版！', { shade :
	 * true }, function(index) { window.location.href =
	 * "http://www.dahantc.com"; }); }
	 */

	// 根据partnerId不同更改成对应的logo
	var partnerId = getPartnerId();
	var log = $("#gundongDiv");

	if (partnerId == 1) {
		log
				.html("<div class=\"swiper-container\" id=\"swiper-wrapper\"><div class=\"swiper-wrapper\" ><div class=\"swiper-slide\"><a><img src=\"images/dhllyh.png\" alt=\"\"></a></div><div class=\"swiper-slide\"><a><img src=\"images/banner_2.png\" alt=\"\"></a></div></div><div class=\"pagination\"></div></div>");
	} else if (partnerId == 'zdwy') {
		log
				.html("<div class=\"swiper-container\" id=\"swiper-wrapper\"><div class=\"swiper-wrapper\" ><div class=\"swiper-slide\"><a><img src=\"images/dhllyh.png\" alt=\"\"></a></div><div class=\"swiper-slide\"><a><img src=\"images/banner_2.png\" alt=\"\"></a></div></div><div class=\"pagination\"></div></div>");
	} else if (partnerId == 'ljwy') {
		log
				.html("<div class=\"swiper-container\" id=\"swiper-wrapper\"><div class=\"swiper-wrapper\" ><div class=\"swiper-slide swiper-no-swiping\"><a><img src=\"images/ljwy.png\" alt=\"\"></a></div></div><div class=\"pagination\"></div></div>");
	} else if (partnerId == 'qdmskj') {
		log
				.html("<div class=\"swiper-container\" id=\"swiper-wrapper\"><div class=\"swiper-wrapper\" ><div class=\"swiper-slide swiper-no-swiping\"><a><img src=\"images/qdmskj.png\" alt=\"\"></a></div></div><div class=\"pagination\"></div></div>");
	} else if (partnerId == 'sdyzsdwl') {
		log
				.html("<div class=\"swiper-container\" id=\"swiper-wrapper\"><div class=\"swiper-wrapper\" ><div class=\"swiper-slide swiper-no-swiping\"><a><img src=\"images/sdyzsdwl.png\" alt=\"\"></a></div></div><div class=\"pagination\"></div></div>");
	} else if (partnerId == 'qdstwl') {
		log
				.html("<div class=\"swiper-container\" id=\"swiper-wrapper\"><div class=\"swiper-wrapper\" ><div class=\"swiper-slide swiper-no-swiping\"><a><img src=\"images/qdstwl.png\" alt=\"\"></a></div></div><div class=\"pagination\"></div></div>");
	} else if (partnerId == 'llm') {
		log
				.html("<div class=\"swiper-container\" id=\"swiper-wrapper\"><div class=\"swiper-wrapper\" ><div class=\"swiper-slide swiper-no-swiping\"><a><img src=\"images/llm.png\" alt=\"\"></a></div></div><div class=\"pagination\"></div></div>");
	} else if (partnerId == 'clshb') {
		log
				.html("<div class=\"swiper-container\" id=\"swiper-wrapper\"><div class=\"swiper-wrapper\" ><div class=\"swiper-slide swiper-no-swiping\"><a><img src=\"images/clshb.png\" alt=\"\"></a></div></div><div class=\"pagination\"></div></div>");
	} else if (partnerId == '2345') {
		log
				.html("<div class=\"swiper-container\" id=\"swiper-wrapper\"><div class=\"swiper-wrapper\" ><div class=\"swiper-slide swiper-no-swiping\"><a><img src=\"images/2345.png\" alt=\"\"></a></div></div><div class=\"pagination\"></div></div>");
	} else if (partnerId == 'zdwy') {
		log
				.html("<div class=\"swiper-container\" id=\"swiper-wrapper\"><div class=\"swiper-wrapper\" ><div class=\"swiper-slide swiper-no-swiping\"><a><img src=\"images/zdwy.png\" alt=\"\"></a></div></div><div class=\"pagination\"></div></div>");
	} else if (partnerId == 'ludazhongcheng') {
		log
				.html("<div class=\"swiper-container\" id=\"swiper-wrapper\"><div class=\"swiper-wrapper\" ><div class=\"swiper-slide swiper-no-swiping\"><a><img src=\"images/ludazhongcheng.jpg\" alt=\"\"></a></div></div><div class=\"pagination\"></div></div>");
	} else if (partnerId == 'klsq') {
		log
				.html("<div class=\"swiper-container\" id=\"swiper-wrapper\"><div class=\"swiper-wrapper\" ><div class=\"swiper-slide swiper-no-swiping\"><a><img src=\"images/kaolashequ.png\" alt=\"\"></a></div></div><div class=\"pagination\"></div></div>");
	} else if (partnerId == 'laichuang') {
		log
				.html("<div class=\"swiper-container\" id=\"swiper-wrapper\"><div class=\"swiper-wrapper\" ><div class=\"swiper-slide swiper-no-swiping\"><a><img src=\"images/laichuang.png\" alt=\"\"></a></div></div><div class=\"pagination\"></div></div>");
	} else if (partnerId == '59store') {
		log
		.html("<div class=\"swiper-container\" id=\"swiper-wrapper\"><div class=\"swiper-wrapper\" ><div class=\"swiper-slide swiper-no-swiping\"><a><img src=\"images/59store.jpg\" alt=\"\"></a></div></div><div class=\"pagination\"></div></div>");
		$("#hanbiDiv").remove();
		$("#chongzhiCardDiv").remove();
		$("#hanbiHFDiv").remove();
		$("#chongzhiCardHFDiv").remove();
	} else {
		log.css("display", "none");
	}

})
// 获取微信地址
function getWeichatUrl(params) {
	var appid = "wxf66d176d29e9f5ef";
	var ul = "m.dahanbank.cn";
	if (params == 0) {
		return "appid=" + appid + "&redirect_uri=http%3A%2F%2F" + ul;
	} else if (params == 1) {
		return "http://" + ul;
	} else if (params == 2) {
		return appid;
	}
}
var $_REQUEST = new Object();
var aParams = document.location.search.substr(1).split('&');
for (i = 0; i < aParams.length; i++) {
	var aParam = aParams[i].split('=');
	$_REQUEST[aParam[0]] = aParam[1];
}
/**
 * 合作伙伴ID
 * 
 * @returns 合作伙伴ID，若为空，返回1(表示大汉三通)
 */
function getPartnerId() {
	var _partnerId = $_REQUEST["partnerId"];
	return _partnerId ? _partnerId : "1";
}
/**
 * 微信openId
 * 
 * @returns
 */
function getOpenId() {
	return $_REQUEST["openId"];
}
function getPage() {
	var _page = $_REQUEST["page"];
	return _page ? _page : "bankHome.html";
}

function getMobile() {
	var _mobile = $_REQUEST["mobile"];
	return _mobile;
}

function getId() {
	return $_REQUEST["id"];
}

function getBalance() {
	return $_REQUEST["balance"];
}

function getParentOpenId() {
	return $_REQUEST["parentOpenId"];
}

function getCurrOpenId() {
	return $_REQUEST["currOpenId"];
}

function getUserType() {
	return $_REQUEST["userType"];
}

function getPayBody() {
	return $_REQUEST["payBody"];
}

function getProductId() {
	return $_REQUEST["productId"];
}

function getBusinessType() {
	return $_REQUEST["businessType"];
}

function getFlag() {
	return $_REQUEST["flag"];
}

function getActiFlag() {
	return $_REQUEST["actiFlag"];
}

function getToLogin() {
	return $_REQUEST["toLogin"];
}

function getBackBusinessType() {
	return $_REQUEST["backBusinessType"];
}

function getName() {
	return $_REQUEST["name"];
}

function getCardNo() {
	return $_REQUEST["cardNo"];
}

function getCardPwd() {
	return $_REQUEST["cardPwd"];
}

function getErrInfo() {
	return $_REQUEST["errInfo"];
}

function getRemainFlow() {
	return $_REQUEST["remainFlow"];
}

function getYYSType() {
	return $_REQUEST["yysType"];
}

function getSex() {
	return $_REQUEST["sex"];
}

function getBirthday() {
	return $_REQUEST["birthday"];
}

function getUploadFlag() {
	return $_REQUEST["uploadFlag"];
}

function getStatus() {
	return $_REQUEST["status"];
}

function getInfos() {
	return $_REQUEST["infos"];
}

// 支持的包大小
function getSupportPackageSize() {
	return $_REQUEST["supportPackageSize"];
}

function getExCode() {
	return $_REQUEST["exCode"];
}
function getRedEnvelopeId() {
	return $_REQUEST["redEnvelopeId"];
}
function getChildRedEnvelopeId() {
	return $_REQUEST["childRedEnvelopeId"];
}
function getChildRedEnvelopeMoney() {
	return $_REQUEST["childRedEnvelopeMoney"];
}
function getUserId() {
	return $_REQUEST["userId"];
}
function getIsGetRed() {
	return $_REQUEST["isGetRed"];
}

function getParentUserId() {
	return $_REQUEST["parentUserId"];
}

function getSignInTime() {
	return $_REQUEST["signInTime"];
}

function getLoveCount() {
	return $_REQUEST["loveCount"];
}
function showDia(content) {
	diag = $.dialog({
		content : content,
		lock : true
	})
}
function closeDia() {
	if (diag) {
		diag.close();
	}
}
function showLayerTips(id, msg, direction, time) {
	direction = !direction ? 1 : direction;
	layer.tips(msg, '#' + id, {
		tips : [ direction, '#db2330' ],
		time : time ? time : 2000
	});
}

function footLinkHandler(partnerId) {
	$("#czLi").click(function() {
		var _random = Math.random();
		$.post("ToRechargeServlet", {
			partnerId : partnerId,
			random : _random
		}, function(data) {
			window.location = "chongzhi.html?mobile=" + data;

		});
	});
	$("#hisOrderLi").click(function() {
		personalCheckSession("history.html", partnerId, "login.html");
		// window.location = "history.html?partnerId=" + partnerId + "&balance="
		// + getBalance();

	});
	$("#questionLi").click(function() {
		window.location = "question.html?partnerId=" + partnerId + "&balance=" + getBalance();

	});
	$("#personalLi").click(function() {
		personalCheckSession("personal.html", partnerId, "login.html");
		// window.location = "personal.html?partnerId=" + partnerId +
		// "&balance=" + getBalance();
	});
}
// 用于纯粹大汉的手机充值页面
function phoneczFooters(partnerId) {
	$("#czLi").click(function() {
		var _random = Math.random();
		$.post("ToRechargeServlet", {
			partnerId : partnerId,
			random : _random
		}, function(data) {
			window.location = "phonecz.html?mobile=" + data;

		});
	});
	$("#hisOrderLi").click(function() {
		personalCheckSession("bankHistory.html", partnerId, "bankLogin.html");
	});

	$("#shouyelLi2").click(function() {
		$(this).css("display", "none");
		$("#shouyelLi").css("display", "block");
		window.location.href = "bankHome.html";
	});

}
/* 判断是不是微信浏览器 */
function isweixin() {
	var ua = navigator.userAgent.toLowerCase();
	if (ua.match(/MicroMessenger/i) == "micromessenger") {
		return true;
	} else {
		return false;
	}
}
/*
 * 判断是移动端还是pc端访问 移动端返回true
 */
function browserRedirect() {
	var sUserAgent = navigator.userAgent.toLowerCase();
	var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
	var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
	var bIsMidp = sUserAgent.match(/midp/i) == "midp";
	var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
	var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
	var bIsAndroid = sUserAgent.match(/android/i) == "android";
	var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
	var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
	if (bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM) {
		return true;
	} else {
		return false;
	}
}

function personalCheckSession(page, partnerId, logPage) {
	$.post("CheckSessionServlet", null, function(data) {
		if (data == '0') {
			window.location = page + "?partnerId=" + partnerId + "&balance=" + getBalance();
		} else {
			tiaozhuan(page, logPage);
		}
	});
}
function personalUpCheckSession(page, partnerId, logPage) {
	$.post("../CheckSessionServlet", null, function(data) {
		if (data == '0') {
			window.location = page + "?partnerId=" + partnerId + "&balance=" + getBalance();
		} else {
			tiaozhuan(page, logPage);
		}
	});
}
function tiaozhuan(page, logPage) {
	var _url = "";
	if (!page) {
		page = "bankHome.html"
	}
	if (!logPage) {
		logPage = "bankLogin.html";
	}
	if (isweixin()) {
		_url = "https://open.weixin.qq.com/connect/oauth2/authorize?"

		+ getWeichatUrl(0) + "%2FWeiChatRegisterServlet%3FpartnerId%3D" + getPartnerId() + "%26pageName%3D"

		+ page

		+ "&response_type=code"

		+ "&scope=snsapi_base"

		+ "&state=123#wechat_redirect";
	} else {
		var flag = "";
		if (page == "personal.html") {
			flag = "&toLogin=1";
		} else if (page == "history.html") {
			flag = "&toLogin=0";
		} else if (page == "bankHistory.html") {
			flag = "&toLogin=0";
		}
		_url = logPage + "?page=" + page + "&partnerId=" + getPartnerId() + flag;
	}
	window.location = _url;
}

function addPhoto(id, userid) {
	if (id && userid) {
		var random = Math.random();
		$("#" + id + "").prop("src", getWeichatUrl(1) + "/UpLoadPhotoServlet?method=getPhoto&id=" + userid + "&random=" + random);
	}
}

// 日期转换
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
	return format;
}