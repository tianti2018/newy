var llyddiscount = 1;
var llltdiscount = 1;
var lldxdiscount = 1;
var hfyddiscount = 1;
var hfltdiscount = 1;
var hfdxdiscount = 1;
//陕西移动省网折扣
var llydsxdiscount = 0.95;
var partnerId = 1;
// 根据partnerId不同选择对应的折扣值
$(function() {
	
	partnerId = getPartnerId();
	llyddiscount = llydObj[partnerId] ? llydObj[partnerId] : llyddiscount;
	llltdiscount = llltObj[partnerId] ? llltObj[partnerId] : llltdiscount;
	lldxdiscount = lldxObj[partnerId] ? lldxObj[partnerId] : lldxdiscount;
	hfyddiscount = hfydObj[partnerId] ? hfydObj[partnerId] : hfyddiscount;
	hfltdiscount = hfltObj[partnerId] ? hfltObj[partnerId] : hfltdiscount;
	hfdxdiscount = hfdxObj[partnerId] ? hfdxObj[partnerId] : hfdxdiscount;

	$(".divtitle a").click(function() {
		var thisEle = $(this);
		thisEle.addClass("selectDivHuaAndLiuCss").siblings("a").removeClass("selectDivHuaAndLiuCss");
		$(".table_c").eq(thisEle.index()).css("display", "block").siblings(".table_c").css("display", "none");
	})
	$(".divtitle a").eq(0).click();
	$(".table_c:first").css({
		"display" : "block"
	});
	$(".delete").click(function() {
		$(this).siblings(".input-code").val("");
		var thisinput = $(this).siblings(".input-code").attr("type").length;
		if (thisinput == "3") {
			$(this).parent().parent().siblings(".cm-btn-group").find("a").css("background", "#d0d0d0");
			$(this).parent().parent().siblings(".top-tips").css("visibility", "hidden");
			$(this).parent().parent().siblings(".chong-tips").css("visibility", "hidden");
		}
		$(this).hide();
		$(this).siblings(".input-code").focus();
		$("#liuLiangSubmit").attr("href", "javascript:void(0);");
	})

	if (isweixin()) {// 是微信内置浏览器
		$("#payTypeAliPay").prop("hidden", "true");// 隐藏支付宝
		$("#payType2AliPay").prop("hidden", "true");
	} else {
		$("#payTypeWeiChat").prop("hidden", "true");// 隐藏微信
		$("#payType2WeiChat").prop("hidden", "true");
	}
	$("input[type='radio'][name='payType']").change(function() {
		if ($(this).is(":checked")) {
			var radioIndex = $(this).index("input[type='radio'][name='payType']");
			handleDisabledSelect(radioIndex, "selLiuliang", 0);
			clearInput();// 清空卡号和密码的值
			$(this).siblings("span.span_ture").css("visibility", "visible");
			$(this).siblings(".payafterdiv").css("visibility", "hidden");
			$(this).parent().siblings().find("span.span_ture").css("visibility", "hidden");
			$(this).parent().siblings().find(".payafterdiv").css("visibility", "visible");
		} else {
			$(this).siblings("span.span_ture").css("visibility", "hidden");
			$("index-form.card_box").hide();
		}
	});

	$("input[type='radio'][name='payType']").click(function() {
		var _tempVal = $(this).val();
		if (_tempVal == '0' || _tempVal == '2' || _tempVal == '1') {
			liuLiangSubmitHandler();
		}
	});

	$("input[type='radio'][name='payType2']").change(function() {
		if ($(this).is(":checked")) {
			var radioIndex2 = $(this).index("input[type='radio'][name='payType2']");
			handleDisabledSelect(radioIndex2, "selHuaFei", 1);
			clearInput();// 清空卡号和密码的值
			$(this).siblings("span.span_ture").css("visibility", "visible");
			$(this).siblings(".payafterdiv").css("visibility", "hidden");
			$(this).parent().siblings().find("span.span_ture").css("visibility", "hidden");
			$(this).parent().siblings().find(".payafterdiv").css("visibility", "visible");
		} else {
			$(this).siblings("span.span_ture").css("visibility", "hidden");
			$("index-form.card_box").hide();
		}
	});

	$("input[type='radio'][name='payType2']").click(function() {
		var _tempVal = $(this).val();
		if (_tempVal == '0' || _tempVal == '2' || _tempVal == '1') {
			submitHuaFeiHandler();
		}
	});
})
// 流量partnerId对应的移动折扣
var llydObj = {
	"1" : "0.95"
}
// 流量partnerId对应的联通折扣
var llltObj = {
	"1" : "1"
}
// 流量partnerId对应的电信折扣
var lldxObj = {
	"1" : "0.95"
}
// 话费partnerId对应的移动折扣
var hfydObj = {
	"1" : "0.999"
}
// 话费partnerId对应的联通折扣
var hfltObj = {
	"1" : "0.999"
}
// 话费partnerId对应的电信折扣
var hfdxObj = {
	"1" : "0.999"
}
var proObj = {
	"移动" : {
		plist : [ {
			id : '000000004d23c6ef014d2847799b0023',
			name : '10M',
			price : '300',
			yongjin : 0.4
		}, {
			id : '000000004d23c6ef014d28481c4a0025',
			name : '30M',
			price : '500',
			yongjin : 0.6
		}, {
			id : '000000004d23c6ef014d2848b58c0027',
			name : '70M',
			price : '1000',
			yongjin : 1.2
		}, {
			id : '000000004d23c6ef014d284a17660029',
			name : '150M',
			price : '2000',
			yongjin : 2.5
		}, {
			id : '000000004d23c6ef014d284a913a002b',
			name : '500M',
			price : '3000',
			yongjin : 3.5
		}, {
			id : '000000004d56e353014d6ae8c7470021',
			name : '1G',
			price : '5000',
			yongjin : 5.5
		}, {
			id : '000000004e3cba65014e43997fb3006e',
			name : '2G',
			price : '7000',
			yongjin : 7.5
		}, {
			id : '000000004e3cba65014e439a2fe1008e',
			name : '3G',
			price : '10000',
			yongjin : 10.5
		}, {
			id : '000000004e3cba65014e439ae26900ae',
			name : '4G',
			price : '13000',
			yongjin : 13.5
		}, {
			id : '000000004e3cba65014e439b77ea00ce',
			name : '6G',
			price : '18000',
			yongjin : 20
		}, {
			id : '000000004e3cba65014e439c01aa00ee',
			name : '11G',
			price : '28000',
			yongjin : 35
		} ]
	},
	"联通" : {
		plist : [ {
			id : '000000004d9f2a6d014db6e97a2300cf',
			name : '20M',
			price : '300',
			yongjin : 0.1
		}, {
			id : '000000004cdff715014ce05ff5d70000',
			name : '50M',
			price : '600',
			yongjin : 0.2
		}, {
			id : '000000004d9f2a6d014db6ea579400ef',
			name : '100M',
			price : '1000',
			yongjin : 0.35
		}, {
			id : '000000004d375387014d41da22a8002f',
			name : '200M',
			price : '1500',
			yongjin : 0.5
		}, {
			id : '000000004d9f2a6d014db6ec9e29010f',
			name : '500M',
			price : '3000',
			yongjin : 1
		}/*, {
			id : '000000004d9f2a6d014db6ee95db012f',
			name : '1G(半年期)',
			price : '10000'
		} */]
	},
	"电信" : {
		plist : [ {
			id : '000000004cdff715014ce062806c0020',
			name : '5M',
			price : '100',
			yongjin : 0.05
		}, {
			id : '000000004cdff715014ce0ab9f50005f',
			name : '10M',
			price : '200',
			yongjin : 0.1
			
		}, {
			id : '000000004d09ba28014d09f143d60000',
			name : '30M',
			price : '500',
			yongjin : 0.25
		}, {
			id : '000000004ce97bdb014cede579c80047',
			name : '50M',
			price : '700',
			yongjin : 0.4
		}, {
			id : '000000004d375387014d41dc5144008d',
			name : '100M',
			price : '1000',
			yongjin : 0.6
		}, {
			id : '000000004d375387014d41dd174b00ad',
			name : '200M',
			price : '1500',
			yongjin : 0.8
		}, {
			id : '000000004d375387014d41dd94ac00cd',
			name : '500M',
			price : '3000',
			yongjin : 1.5,
			liunum : 300007
		}, {
			id : '000000004d375387014d41ddf64200ed',
			name : '1G',
			price : '5000',
			yongjin : 2.5,
			liunum : 300008
		} ]
	}
};

var proObjHuaFei = [ {
	id : "64bb54e71bda11e588edb083fe8bfc1f",
	name : "30元",
	price : "3000"
}, {
	id : "6662e1671bda11e588edb083fe8bfc1f",
	name : "50元",
	price : "5000"
}, {
	id : "67dc581f1bda11e588edb083fe8bfc1f",
	name : "100元",
	price : "10000"
}, {
	id : "692e7fe81bda11e588edb083fe8bfc1f",
	name : "200元",
	price : "20000"
}, {
	id : "c2bc0fcf1e2211e588edb083fe8bfc1f",
	name : "300元",
	price : "30000"
}, {
	id : "6b5adf1c1bda11e588edb083fe8bfc1f",
	name : "500元",
	price : "50000"
} ];

var llPrice = {

	'000000004d23c6ef014d2847799b0023' : '300',
	'000000004d23c6ef014d28481c4a0025' : '500',
	'000000004d23c6ef014d2848b58c0027' : '1000',
	'000000004d23c6ef014d284a17660029' : '2000',
	'000000004d23c6ef014d284a913a002b' : '3000',
	'000000004d56e353014d6ae8c7470021' : '5000',
	'000000004cdff715014ce05ff5d70000' : '600',
	'000000004d375387014d41da22a8002f' : '1500',
	'000000004d9f2a6d014db6e97a2300cf' : '300',
	'000000004d9f2a6d014db6ea579400ef' : '1000',
	'000000004d9f2a6d014db6ec9e29010f' : '3000',
	'000000004d9f2a6d014db6ee95db012f' : '10000',
	'000000004cdff715014ce062806c0020' : '100',
	'000000004cdff715014ce0ab9f50005f' : '200',
	'000000004ce97bdb014cede579c80047' : '700',
	'000000004d09ba28014d09f143d60000' : '500',
	'000000004d375387014d41dc5144008d' : '1000',
	'000000004d375387014d41dd174b00ad' : '1500',
	'000000004d375387014d41dd94ac00cd' : '3000',
	'000000004d375387014d41ddf64200ed' : '5000',
	'000000004d70806a014d75c1b25b0056' : '1000',
	'000000004d70806a014d75d39f640066' : '2000',
	'000000004d7b4de1014d7ed5a4340001' : '3000',
	'000000004e3cba65014e43997fb3006e' : '7000',
	'000000004e3cba65014e439a2fe1008e' : '10000',
	'000000004e3cba65014e439ae26900ae' : '13000',
	'000000004e3cba65014e439b77ea00ce' : '18000',
	'000000004e3cba65014e439c01aa00ee' : '28000'

};
var hfPrice = {
	'64bb54e71bda11e588edb083fe8bfc1f' : '3000',
	'6662e1671bda11e588edb083fe8bfc1f' : '5000',
	'67dc581f1bda11e588edb083fe8bfc1f' : '10000',
	'692e7fe81bda11e588edb083fe8bfc1f' : '20000',
	'c2bc0fcf1e2211e588edb083fe8bfc1f' : '30000',
	'6b5adf1c1bda11e588edb083fe8bfc1f' : '50000'
};

var isnews = {
	'000000004e3cba65014e43997fb3006e' : 'yes',
	'000000004e3cba65014e439a2fe1008e' : 'yes',
	'000000004e3cba65014e439ae26900ae' : 'yes',
	'000000004e3cba65014e439b77ea00ce' : 'yes',
	'000000004e3cba65014e439c01aa00ee' : 'yes'
}

var llPresentSize = {
	'000000004d23c6ef014d2847799b0023' : '',
	'000000004d23c6ef014d28481c4a0025' : '',
	'000000004d23c6ef014d2848b58c0027' : '',
	'000000004d23c6ef014d284a17660029' : '10MB',
	'000000004d23c6ef014d284a913a002b' : '30MB',
	'000000004d56e353014d6ae8c7470021' : '70MB',
	'000000004cdff715014ce05ff5d70000' : '',
	'000000004d375387014d41da22a8002f' : '20MB',
	'000000004d9f2a6d014db6e97a2300cf' : '',
	'000000004d9f2a6d014db6ea579400ef' : '',
	'000000004d9f2a6d014db6ec9e29010f' : '50MB',
	'000000004d9f2a6d014db6ee95db012f' : '100MB',
	'000000004cdff715014ce062806c0020' : '',
	'000000004cdff715014ce0ab9f50005f' : '',
	'000000004ce97bdb014cede579c80047' : '',
	'000000004d09ba28014d09f143d60000' : '',
	'000000004d375387014d41dc5144008d' : '5MB',
	'000000004d375387014d41dd174b00ad' : '10MB',
	'000000004d375387014d41dd94ac00cd' : '30MB',
	'000000004d375387014d41ddf64200ed' : '50MB',
	'000000004e3cba65014e43997fb3006e' : '70MB',
	'000000004e3cba65014e439a2fe1008e' : '150MB',
	'000000004e3cba65014e439ae26900ae' : '500MB',
	'000000004e3cba65014e439b77ea00ce' : '500MB',
	'000000004e3cba65014e439c01aa00ee' : '1G'

};

function getProvinceName(psVal) {
	var _txtPS = psVal.substring(0, 3);
	var _txtPro = parseInt(psVal.substring(0, 7)) - parseInt(_txtPS) * 10000;
	var _oProvinceName = "";
	if (PHONE_SEGMENT_MAP[_txtPS]) {
		var _provinceId = PHONE_SEGMENT_MAP[_txtPS].substring(_txtPro, _txtPro + 1);
		_oProvinceName = PROVINCENAME_MAP[_provinceId];
	}
	return _oProvinceName;
}

function getYysName(psVal) {
	var _txtPS = psVal.substring(0, 3);
	var _oyysName = "";
	if (PHONE_YYS_NAME_MAP[_txtPS]) {
		_oyysName = PHONE_YYS_NAME_MAP[_txtPS];
	}
	return _oyysName;
}

/**
 * 返回省份提示信息
 * 
 * @param pName
 *            省份简称
 * @param yysName
 *            运营商名称
 * @returns {___anonymous1322_1383}
 */
function getProvinceDesc(pName, yysName) {
	var _oDesc = {
		isSupport : true,
		supportDesc : null,
		tips : null
	};
	switch (yysName) {
	case "移动":// 移动提示信息
		switch (pName) {
		case "北京":
		case "新疆":
		case "重庆":
		case "浙江":
		case "天津":
		case "广西":
		case "内蒙古":
		case "江西":
		case "安徽":
		case "贵州":
		case "辽宁":
		case "山西":
		case "青海":
		case "四川":
		case "江苏":
		case "西藏":
		case "吉林":
		case "云南":
		case "甘肃":
		case "山东":
		case "河南":
		case "上海":
		case "湖北":
		case "黑龙江":
		case "湖南":
		case "福建":
		case "宁夏":
		case "海南":
		case "河北":
			_oDesc = {
				isSupport : true,
				supportDesc : "支持2G/3G/4G&nbsp;&nbsp;&nbsp;&nbsp;"
						+ (llyddiscount == 1 ? "" : "<font color='#db2330'><b>" + (llyddiscount * 10) + "折优惠，发送短信指令'cxyl'至10086或拨打电话，即可查询流量到账情况！</b></font>"),
				tips : "<li><b>到账时间</b>：5分钟内</li>"

				+ "<li><b>生效时间</b>：充值成功即时生效</li>"

				+ "<li><b>使用范围</b>：支持全国漫游</li>"

				+ "<li><b>使用有效期</b>：充值当月有效</li>"

				+ "<li><b>充值次数限制</b>：不限制次数</li>"
			};
			break;
		case "陕西":
			_oDesc = {
				isSupport : true,
				supportDesc : "支持2G/3G/4G&nbsp;&nbsp;&nbsp;&nbsp;"
						+ "<font color='#db2330'><b>" + (0.95 * 10) + "折优惠，发送短信指令'cxyl'至10086或拨打电话，即可查询流量到账情况！</b></font>",
				tips : "<li><b>到账时间</b>：5分钟内</li>"

				+ "<li><b>生效时间</b>：充值成功即时生效</li>"

				+ "<li><b>使用范围</b>：支持全国漫游</li>"

				+ "<li><b>使用有效期</b>：充值当月有效</li>"

				+ "<li><b>充值次数限制</b>：不限制次数</li>"
			};
			break;
		case "广东":
			_oDesc = {
				isSupport : true,
				supportDesc : "支持2G/3G/4G&nbsp;&nbsp;&nbsp;&nbsp;"
						+ (llyddiscount == 1 ? "" : "<font color='#db2330'><b>" + (llyddiscount * 10) + "折优惠，发送短信指令'cxyl'至10086或拨打电话，即可查询流量到账情况！</b></font>"),
				tips : "<li><b>到账时间</b>：1分钟内</li>"

				+ "<li><b>生效时间</b>：充值成功即时生效</li>"

				+ "<li><b>使用范围</b>：支持全国漫游</li>"

				+ "<li><b>使用有效期</b>：充值当月有效</li>"

				+ "<li><b>充值次数限制</b>：不限制次数</li>"
			};
			break;
		default:
			break;
		}
		break;
	case "联通":// 联通提示信息
		switch (pName) {
		case "新疆":
		case "重庆":
		case "广东":
		case "浙江":
		case "天津":
		case "内蒙古":
		case "贵州":
		case "山西":
		case "青海":
		case "江苏":
		case "西藏":
		case "福建":
		case "上海":
		case "湖北":
		case "甘肃":
		case "山东":
		case "河南":
		case "湖南":
		case "宁夏":
		case "海南":
		case "北京":
		case "天津":
		case "辽宁":
		case "吉林":
		case "黑龙江":
		case "安徽":
		case "江西":
		case "广西":
		case "四川":
		case "云南":
		case "河北":
		case "陕西":
			_oDesc = {
				isSupport : true,
				supportDesc : "支持2G/3G/4G&nbsp;&nbsp;&nbsp;&nbsp;发送短信指令'412'至10001或拨打电话，即可查询流量到账情况！",// &nbsp;&nbsp;&nbsp;&nbsp;<font
				// color='#db2330'><b>" + (llltdiscount
				// * 10) + "折优惠</b></font>
				tips :

				"<li><b>到账时间</b>：5分钟内</li>"

				+ "<li><b>生效时间</b>：充值成功即时生效</li>"

				+ "<li><b>使用范围</b>：支持全国漫游</li>"

				+ "<li><b>使用有效期</b>：充值当月有效</li>"

				+ "<li><b>充值次数限制</b>：每月限制5次</li>"
			};
			break;
		default:
			break;
		}
		break;

	case "电信":// 电信提示信息
		switch (pName) {
		case "北京":
		case "新疆":
		case "重庆":
		case "广东":
		case "浙江":
		case "天津":
		case "广西":
		case "内蒙古":
		case "宁夏":
		case "江西":
		case "安徽":
		case "贵州":
		case "陕西":
		case "辽宁":
		case "山西":
		case "青海":
		case "四川":
		case "江苏":
		case "河北":
		case "西藏":
		case "福建":
		case "吉林":
		case "云南":
		case "上海":
		case "湖北":
		case "海南":
		case "甘肃":
		case "湖南":
		case "山东":
		case "河南":
		case "黑龙江":
			_oDesc = {
				isSupport : true,
				supportDesc : "支持2G/3G/4G&nbsp;&nbsp;&nbsp;&nbsp;"
						+ (llyddiscount == 1 ? "" : "<font color='#db2330'><b>" + (llyddiscount * 10) + "折优惠，发送短信指令'108'至10001或拨打电话，即可查询流量到账情况！</b></font>"),
				tips :

				"<li><b>到账时间</b>：5分钟内</li>"

				+ "<li><b>生效时间</b>：充值成功即时生效</li>"

				+ "<li><b>使用范围</b>：支持全国漫游</li>"

				+ "<li><b>使用有效期</b>：充值当月有效</li>"

				+ "<li><b>充值次数限制</b>：不限制次数</li>"
			};
			break;
		default:
			break;
		}
		break;
	default:
		break;
	}
	return _oDesc;
}
//var footHtml = "<div class=\"ui-nav\"><ul><li id=\"shouyelLi\" hidden=\"true\"><a style=\"color: #fc3d38;\"><img data-original=\"images/home_red.png\" style=\"width: 34px;\" src=\"images/home_red.png\" /><p>首页</p></a></li><li id=\"shouyelLi2\"><a><img data-original=\"images/home.png\" style=\"width: 34px;\" src=\"images/home.png\" /><p>首页</p></a></li><li class=\"li_select\" id=\"czLi\"><a> <img data-original=\"images/ico_71.png\" src=\"images/ico_71_1.png\" /><p class=\"czmenus\">手机充值</p></a></li><li id=\"hisOrderLi\"><a><img data-original=\"images/ico_9.png\" src=\"images/ico_9.png\" /><p>充值记录</p></a></li></ul></div>";
var inputOnFocus = false;

function liuLiangSubmitHandler() {
	var mobile = $("#mainStageTel").val().replace(/\s+/g, "");
	var payType = $("input[name='payType']:checked").val().replace(/\s+/g, "");
	var rechargeCardNumber = $("#CZLLCardNo").val().replace(/\s+/g, "");
	var rechargeCardPwd = $("#CZLLCardPwd").val().replace(/\s+/g, "");
	var partnerId = getPartnerId();
	var mianZhi = $("#selLiuliang").val();
	var body = $("#selLiuliang").attr("dataname");
	var total_fee = $("#rechargPrice").html();
	if (null != total_fee && undefined != total_fee && total_fee != "") {
		total_fee = total_fee.substring(0, total_fee.indexOf("元"));
	}
	if (null == partnerId || undefined == partnerId || partnerId == "") {
		alert("partnerId不能为空！");
		return;
	}
	if (mobile == "") {
		alertDia("手机号码不能为空！", "alert");
		return;
	}
	if (mobile.length != 11) {
		alertDia("手机号码错误！", "alert");
		return;
	}
	if (payType != 3) {
		if (null == mianZhi || undefined == mianZhi || mianZhi == "") {
			alertDia("请选择流量面值！", "alert");
			return;
		}
		if (getYysName(mobile) == "移动") {
			var now = new Date();
			if (now.getTime() >= getCurrentMonthLastTwo(2).getTime() && now.getTime() < getCurrentMonthLastTwo(0).getTime()) {
				alertDia("移动流量月末两天暂停充值，谢谢！", "alert");
				return;
			}
		}
	} else {
		if (rechargeCardNumber == "") {
			alertDia("卡号不能为空！", "alert");
			return;
		}
		if (rechargeCardPwd == "") {
			alertDia("密码不能为空！", "alert");
			return;
		}
	}
	if (payType != 3) {
		var _yysName = getYysName(mobile);
		var _pList = proObj[_yysName].plist;
		var _isRight = false;
		for (var i = 0; i < _pList.length; i++) {
			if (_pList[i].id == mianZhi) {
				_isRight = true;
			}
		}
		if (!_isRight) {
			alertDia("运营商不匹配！", "alert");
			return;
		}
	}
	if (payType == 0) {
		liuliangHBGong(payType, body, total_fee, mianZhi, mobile, partnerId, rechargeCardNumber, rechargeCardPwd);
	} else {
		var myDialog = $.dialog({
			content : '充值处理中……'
		});
		// setTimeout(function() {
		$.post("AlipayServlet", {
			"businessType" : 0,
			"WIDtotal_fee" : total_fee,
			"body" : body,
			"productId" : mianZhi,
			"mobile" : mobile,
			"partnerId" : partnerId,
			"payType" : payType,
			"rechargeCardNumber" : rechargeCardNumber,
			"rechargeCardPwd" : rechargeCardPwd
		}, function(data) {
			var tit = 'ok';
			var resultCode = data.resultCode;
			var returnDatas = data.resultMsg;
			if (resultCode == 926) {// 支付宝的返回值
				$("#alipay").html(data.resultMsg);
				return;
			}
			liuliangGong(resultCode, returnDatas, myDialog);

		}, "json");
	}

	// }, 1000);
}

function liuliangHBGong(payType, body, total_fee, mianZhi, mobile, partnerId, rechargeCardNumber, rechargeCardPwd) {
	var status;
	// 汉币支付，须加提示框
	layer.confirm(addTiShi(payType, body), {
		btn : [ '确定', '取消' ],
		title : "提示"
	// shade: false //不显示遮罩
	}, function(index) {
		layer.close(index);
		var myDialog = $.dialog({
			content : '充值处理中……'
		});
		// setTimeout(function() {
		$.post("AlipayServlet", {
			"businessType" : 0,
			"WIDtotal_fee" : total_fee,
			"body" : body,
			"productId" : mianZhi,
			"mobile" : mobile,
			"partnerId" : partnerId,
			"payType" : payType,
			"rechargeCardNumber" : rechargeCardNumber,
			"rechargeCardPwd" : rechargeCardPwd
		}, function(data) {
			var tit = 'ok';

			var resultCode = data.resultCode;
			var returnDatas = data.resultMsg;
			if (resultCode == 122) {
				tiaozhuan("phonecz.html", "bankLogin.html");
				return;
			}
			if (resultCode != 0) {
				tit = 'alert';
			}
			var msg = "";
			if (resultCode == 1199) {// 余额不足
				layer.confirm("余额不足，还需" + returnDatas.fee + "汉币<br/>&nbsp;&nbsp;&nbsp;是否充值汉币？", {
					btn : [ '是', '否' ],
					area : [ '30px', '' ],
					title : "提示"
				// shade: false //不显示遮罩
				}, function(index) {
					layer.prompt({
						formType : 0,
						value : returnDatas.fee,
						title : '输入充值金额(1￥=1汉币)',
						maxlength : 10,
						direction : 1,
						color : '#20bff9',
						tipsTime : 5000
					}, function(value, index, elem) {
						rechargedHanBi(value, index, "czhanBiInput", payType, body, total_fee, mianZhi, mobile, partnerId, rechargeCardNumber, rechargeCardPwd,
								0, 1); // 得到value
						layer.close(index);
					});
					return;
				}, function(index) {
					return;
				});
				myDialog.close();
				return;
			}
			if (returnDatas.payType == '0') {// 汉币支付
				if (resultCode == 0) {
					msg = "充值成功,消费汉币" + returnDatas.money + "个！"
				} else {
					msg = returnDatas.resultMsg;
				}
			} else {
				if (returnDatas.payType) {
					msg = returnDatas.resultMsg;
				} else {
					msg = returnDatas;
				}

			}
			myDialog.close();
			$.dialog({
				content : msg,
				title : tit,
				ok : function() {
					/** 弹出红包提示* */
					if (returnDatas.status == 0) {
						/** 重写微信方法* */
						shareRedEnvelope(returnDatas.redEnvelopeId);
						$(".redEnvelopeClass").click();
					}
					return true;
				},
				lock : true
			});

		}, "json");
		return;
	}, function() {
		return;
	});

}

function liuliangGong(resultCode, returnDatas, myDialog) {
	var tit = 'ok';
	if (resultCode != 0) {
		tit = 'alert';
	} else {
		$("#CZLLCardNo").val("");
		$("#CZLLCardPwd").val("");
	}
	if (returnDatas.payType == "2") {
		if (resultCode == 0) {
			var userId = returnDatas.userId;
			var fee = returnDatas.fee;
			// 当后台正确返回值时，调用微信的支付
			function onBridgeReady() {
				WeixinJSBridge.invoke('getBrandWCPayRequest', {
					"appId" : returnDatas.appId, // 公众号名称
					"timeStamp" : returnDatas.timeStamp, // 时间戳
					"nonceStr" : returnDatas.nonceStr, // 随机串
					"package" : returnDatas.packAge,
					"signType" : returnDatas.signType, // 微信签名方式:
					"paySign" : returnDatas.paySign,
					"redEnvelopeId" : returnDatas.redEnvelopeId,// 红包Id
					"status" : returnDatas.status
				// 判断是否在活动
				// 微信签名
				}, function(res) {
					var msg = "";
					if (res.err_msg == "get_brand_wcpay_request:ok") {
						msg = "微信支付成功！";
					} else if (res.err_msg == "get_brand_wcpay_request:cancel") {
						msg = "用户取消支付";
						backHB(userId, fee);
					} else if (res.err_msg == "get_brand_wcpay_request:fail") {
						msg = "微信支付失败";
						backHB(userId, fee);
					}
					myDialog.close();
					$.dialog({
						content : msg,
						title : tit,
						ok : function() {
							/** 添加红包 * */
							if (returnDatas.status == 0 && res.err_msg == "get_brand_wcpay_request:ok") {
								/** 重写微信方法* */
								shareRedEnvelope(returnDatas.redEnvelopeId);
								$(".redEnvelopeClass").click();
							}
							return true;
						},
						lock : true
					});
				});
			}
			if (typeof WeixinJSBridge == "undefined") {
				if (document.addEventListener) {
					document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
				} else if (document.attachEvent) {
					document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
					document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
				}
			} else {
				onBridgeReady();
			}
		} else {// 加获取用户信息失败，请重试
			myDialog.close();
			var _content = "";
			if (resultCode == 55) {
				_content = "获取用户信息失败，请重试!";
				if (returnDatas.userId && returnDatas.fee) {
					backHB(returnDatas.userId, returnDatas.fee);
				}
			}
			// 月末最后一天，不允许充值
			if (resultCode == 20) {
				_content = returnDatas;
			}
			$.dialog({
				content : _content,
				title : tit,
				ok : function() {
					if (resultCode == 11 || resultCode == 14) {
						$("#CZLLCardPwd").focus();
					} else if (resultCode == 10 || resultCode == 15) {
						$("#CZLLCardNo").focus();
					}
					return true;
				},
				lock : true
			});

		}
	} else {
		myDialog.close();
		if (returnDatas.resultMsg) {
			// if (returnDatas.payType == "0") {
			$.dialog({
				content : returnDatas.resultMsg,
				title : tit,
				ok : function() {
					if (resultCode == 11 || resultCode == 14) {
						$("#CZLLCardPwd").focus();
					} else if (resultCode == 10 || resultCode == 15) {
						$("#CZLLCardNo").focus();
					}
					return true;
				},
				lock : true
			});
			// }
		} else {
			$.dialog({
				content : returnDatas,
				title : tit,
				ok : function() {
					if (resultCode == 11 || resultCode == 14) {
						$("#CZLLCardPwd").focus();
					} else if (resultCode == 10 || resultCode == 15) {
						$("#CZLLCardNo").focus();
					}
					return true;
				},
				lock : true
			});
		}
	}
}

function submitHuaFeiHandler() {
	var mobile = $("#mainStage1Tel").val().replace(/\s+/g, "");
	var payType = $("input[name='payType2']:checked").val().replace(/\s+/g, "");
	var rechargeCardNumber = $("#rechargeCardNo").val().replace(/\s+/g, "");
	var rechargeCardPwd = $("#rechargeCardPwd").val().replace(/\s+/g, "");
	var partnerId = getPartnerId();
	var mianZhi = $("#selHuaFei").val();
	var body = $("#selHuaFei").attr("dataname");
	var total_fee = $("#huaFeiRechargPrice").html();
	if (null != total_fee && undefined != total_fee && total_fee != "") {
		total_fee = total_fee.substring(0, total_fee.indexOf("元"));
	}
	if (null == partnerId || undefined == partnerId || partnerId == "") {
		alert("partnerId不能为空！");
		return;
	}
	if (mobile == "") {
		alertDia("手机号码不能为空！", "alert");
		return;
	}
	if (mobile.length != 11) {
		alertDia("手机号码错误！", "alert");
		return;
	}
	if (payType != 3) {
		if (null == mianZhi || undefined == mianZhi || mianZhi == "") {
			alertDia("请选择话费面值！", "alert");
			return;
		}
	} else {
		if (rechargeCardNumber == "") {
			alertDia("卡号不能为空！", "alert");
			return;
		}
		if (rechargeCardPwd == "") {
			alertDia("密码不能为空！", "alert");
			return;
		}
	}

	if (payType == 0) {
		huafeiHBGong(payType, body, total_fee, mianZhi, mobile, partnerId, rechargeCardNumber, rechargeCardPwd);
	} else {
		var myDialog = $.dialog({
			content : '充值处理中……'
		});
		$.post("AlipayServlet", {
			"businessType" : 1,
			"WIDtotal_fee" : total_fee,
			"body" : body,
			"productId" : mianZhi,
			"mobile" : mobile,
			"partnerId" : partnerId,
			"payType" : payType,
			"rechargeCardNumber" : rechargeCardNumber,
			"rechargeCardPwd" : rechargeCardPwd
		}, function(data) {
			myDialog.close();
			var tit = 'ok';
			var resultCode = data.resultCode;
			var returnDatas = data.resultMsg;

			if (resultCode == 229) {// 微信和汉币混合支付
				layer.confirm('您尚有(' + returnDatas + ")汉币可用，是否使用?", {
					btn : [ '是', '否' ], // 按钮
					shade : 0.8,
					title : "提示"
				// 不显示遮罩
				}, function(index) {
					layer.close(index);
					$.post("AlipayServlet", {
						"weichatAndHB" : "yes",
						"businessType" : 1,
						"WIDtotal_fee" : total_fee,
						"body" : body,
						"productId" : mianZhi,
						"mobile" : mobile,
						"partnerId" : partnerId,
						"payType" : payType,
						"rechargeCardNumber" : rechargeCardNumber,
						"rechargeCardPwd" : rechargeCardPwd
					}, function(data) {
						var resCode = data.resultCode;
						var reDatas = data.resultMsg;
						huafeiGong(resCode, reDatas, myDialog);
					}, 'json');
				}, function(index) {
					layer.close(index);
					$.post("AlipayServlet", {
						"weichatAndHB" : "no",
						"businessType" : 1,
						"WIDtotal_fee" : total_fee,
						"body" : body,
						"productId" : mianZhi,
						"mobile" : mobile,
						"partnerId" : partnerId,
						"payType" : payType,
						"rechargeCardNumber" : rechargeCardNumber,
						"rechargeCardPwd" : rechargeCardPwd
					}, function(data) {
						var resCode = data.resultCode;
						var reDatas = data.resultMsg;
						huafeiGong(resCode, reDatas, myDialog);
					}, 'json');
				});
				return true;
			}

			if (resultCode == 926) {// 支付宝的返回值
				$("#alipay").html(data.resultMsg);
				return;
			}
			huafeiGong(resultCode, returnDatas, myDialog);
		}, "json");
	}

}

function huafeiHBGong(payType, body, total_fee, mianZhi, mobile, partnerId, rechargeCardNumber, rechargeCardPwd) {
	// 充值卡支付，须加提示框
	layer.confirm(addTiShi(payType, body), {
		btn : [ '确定', '取消' ],
		title : "提示"
	// shade: false //不显示遮罩
	}, function(index) {
		layer.close(index);
		var myDialog = $.dialog({
			content : '充值处理中……'
		});

		$.post("AlipayServlet", {
			"businessType" : 1,
			"WIDtotal_fee" : total_fee,
			"body" : body,
			"productId" : mianZhi,
			"mobile" : mobile,
			"partnerId" : partnerId,
			"payType" : payType,
			"rechargeCardNumber" : rechargeCardNumber,
			"rechargeCardPwd" : rechargeCardPwd
		}, function(data) {
			myDialog.close();
			var tit = 'ok';
			var resultCode = data.resultCode;
			var returnDatas = data.resultMsg;
			if (resultCode != 0) {
				tit = 'alert';
			}
			var msg = "";
			if (resultCode == 1199) {// 余额不足
				layer.confirm("余额不足，还需" + returnDatas.fee + "汉币<br/>&nbsp;&nbsp;&nbsp;是否充值汉币？", {
					btn : [ '是', '否' ],
					area : [ '30px', '' ],
					title : "提示"
				// shade: false //不显示遮罩
				}, function(index) {
					layer.prompt({
						formType : 0,
						value : returnDatas.fee,
						title : '输入充值金额(1￥=1汉币)',
						maxlength : 10,
						direction : 1,
						color : '#20bff9',
						tipsTime : 5000
					}, function(value, index, elem) {
						rechargedHanBi(value, index, "czhanBiInput", payType, body, total_fee, mianZhi, mobile, partnerId, rechargeCardNumber, rechargeCardPwd,
								1, 1); // 得到value
						layer.close(index);
					});
					return;
				}, function(index) {
					return;
				});
				myDialog.close();
				return;
			}
			if (returnDatas.payType == '0') {// 汉币支付
				if (resultCode == 0) {
					msg = "充值成功,消费汉币" + returnDatas.money + "个！"
				} else {
					msg = returnDatas.resultMsg;
				}

			} else {
				if (returnDatas.payType) {
					msg = returnDatas.resultMsg;
				} else {
					msg = returnDatas;
				}

			}
			myDialog.close();
			$.dialog({
				content : msg,
				title : tit,
				ok : function() {
					return true;
				},
				lock : true
			});
		}, "json");

	});

}

function huafeiGong(resultCode, returnDatas, myDialog) {
	if (resultCode != 0) {
		tit = 'alert';
	} else {
		$("#CZLLCardNo").val("");
		$("#CZLLCardPwd").val("");
	}
	if (returnDatas.payType == "2") {
		if (resultCode == 0) {
			var userId = returnDatas.userId;
			var fee = returnDatas.fee;
			// 当后台正确返回值时，调用微信的支付
			function onBridgeReady() {
				WeixinJSBridge.invoke('getBrandWCPayRequest', {
					"appId" : returnDatas.appId, // 公众号名称
					"timeStamp" : returnDatas.timeStamp, // 时间戳
					"nonceStr" : returnDatas.nonceStr, // 随机串
					"package" : returnDatas.packAge,
					"signType" : returnDatas.signType, // 微信签名方式:
					"paySign" : returnDatas.paySign
				// 微信签名
				}, function(res) {
					var msg = "";
					if (res.err_msg == "get_brand_wcpay_request:ok") {
						msg = "微信支付成功！";
					} else if (res.err_msg == "get_brand_wcpay_request:cancel") {
						msg = "用户取消支付";
						backHB(userId, fee);
					} else if (res.err_msg == "get_brand_wcpay_request:fail") {
						msg = "微信支付失败";
						backHB(userId, fee);
					}
					myDialog.close();
					$.dialog({
						content : msg,
						title : tit,
						ok : function() {
							return true;
						},
						lock : true
					});
				});
			}
			if (typeof WeixinJSBridge == "undefined") {
				if (document.addEventListener) {
					document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
				} else if (document.attachEvent) {
					document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
					document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
				}
			} else {
				onBridgeReady();
			}
		} else {
			myDialog.close();
			var _content = "发起微信支付失败！";
			if (resultCode == 55) {
				_content = "获取用户信息失败，请重试!";
				if (returnDatas.userId && returnDatas.fee) {
					backHB(returnDatas.userId, returnDatas.fee);
				}
			}
			$.dialog({
				content : _content,
				title : tit,
				ok : function() {
					if (resultCode == 11 || resultCode == 14) {
						$("#CZLLCardPwd").focus();
					} else if (resultCode == 10 || resultCode == 15) {
						$("#CZLLCardNo").focus();
					}
					return true;
				},
				lock : true
			});

		}
	} else {
		myDialog.close();
		if (returnDatas.resultMsg) {
			// if (returnDatas.payType == "0") {
			$.dialog({
				content : returnDatas.resultMsg,
				title : tit,
				ok : function() {
					if (resultCode == 11 || resultCode == 14) {
						$("#CZLLCardPwd").focus();
					} else if (resultCode == 10 || resultCode == 15) {
						$("#CZLLCardNo").focus();
					}
					return true;
				},
				lock : true
			});
			// }
		} else {
			$.dialog({
				content : returnDatas,
				title : tit,
				ok : function() {
					if (resultCode == 11 || resultCode == 14) {
						$("#CZLLCardPwd").focus();
					} else if (resultCode == 10 || resultCode == 15) {
						$("#CZLLCardNo").focus();
					}
					return true;
				},
				lock : true
			});
		}

	}

}

function changeInput(obj) {
	var thisvalue = $(obj).val().length;
	var thistype = $(obj).attr("type").length;
	if (thistype == "3") {
		if (thisvalue == "13") {
			$(obj).parent().parent().siblings(".top-tips").css("visibility", "visible");
			$(obj).parent().parent().siblings(".chong-tips").css("visibility", "visible");
			$(obj).parent().parent().siblings(".cm-btn-group").find("a").css("background", "#db2330");
		} else {
			$(obj).parent().parent().siblings(".top-tips").css("visibility", "hidden");
			$(obj).parent().parent().siblings(".chong-tips").css("visibility", "hidden");
		}
	}

}
function showdelete(obj) {
	inputOnFocus = true;
	$(".ui-nav").remove();
	$(obj).siblings(".delete").show();
}
function hidedelete(obj) {
	var thisval1 = $(obj).val();
	if (thisval1 == "") {
		$(obj).siblings(".delete").hide();
	}
	setTimeout(function() {
		if (!inputOnFocus) {
			if ($(".ui-nav").size() <= 0) {
				//$("#mainDiv").append(footHtml);
				phoneczFooters(partnerId);
			}
		}
	}, 580);
	inputOnFocus = false;
}

function liuLiangPasteHandleProcess(mainStageTel) {
	$("#" + mainStageTel + "").bind({
		paste : function(e) {
			setTimeout(function() {
				var data = $("#" + mainStageTel + "").val();
				var result = ""
				var handlSecond = data.replace(/[\u4e00-\u9fa5]/g, "").replace(/\-+/g, "").replace(/\s+/g, "");
				var index = handlSecond.indexOf("1");
				handlSecond = handlSecond.substring(index,index+11);
				if (handlSecond.length == 11) {
					result += handlSecond.substring(0, 3) + " ";
					result += handlSecond.substring(3, 7) + " ";
					result += handlSecond.substring(7, 11);
				}
				$("#" + mainStageTel + "").val(result);
				$("#" + mainStageTel + "").keyup();
				$("#" + mainStageTel + "").blur();
			}, 1);
		},
		input : function(e) {
			setTimeout(function() {
				var data = $("#" + mainStageTel + "").val();
				var result = ""
				var handlSecond = data.replace(/[\u4e00-\u9fa5]/g, "").replace(/\-+/g, "").replace(/\s+/g, "");
				var index = handlSecond.indexOf("1");
				handlSecond = handlSecond.substring(index,index+11);
				if (handlSecond.length == 11) {
					result += handlSecond.substring(0, 3) + " ";
					result += handlSecond.substring(3, 7) + " ";
					result += handlSecond.substring(7, 11);
					$("#" + mainStageTel + "").val(result);
					$("#" + mainStageTel + "").keyup();
					$("#" + mainStageTel + "").blur();
				}
			}, 1);

		}
	});

}

function inputLiuLiangTelHandle(mainStageTel, liuLiangInfoFont, rechargPrice, selLiuliang, liuLiangTipContent, topTips, mainStageTelFrom, liuLiangSubmit) {
	var prevProvince, prevYYSName, isSupport = false;
	$("#" + mainStageTel + "").keyup(
			function(event) {
				var _txtVal = $.trim($(this).val());
				if (_txtVal.charAt(_txtVal.length - 1) < '0' || _txtVal.charAt(_txtVal.length - 1) > '9') {
					$(this).val(_txtVal.substring(0, _txtVal.length - 1));
					return;
				}

				// $(this).val(handleInputTel($(this).val(),0));
				if (event.which == 8) {
					$(this).val($(this).val());
					// $(this).val(handleInputTel($(this).val(),1));
					// alert(handleInputTel($(this).val()))

				} else {
					if ($(this).val().length == 3) {
						$(this).val($(this).val() + " ");
					}
					if ($(this).val().length == 4) {
						var newTel = $(this).val().replace(/\s+/g, "");
						if (newTel.length == $(this).val().length) {
							$(this).val($(this).val().substring(0, 3) + " " + $(this).val().substring(3, 4));
						}
					}
					if ($(this).val().length == 8) {
						$(this).val($(this).val() + " ");
					}
					if ($(this).val().length == 9) {
						var newTel = $(this).val().replace(/\s+/g, "");
						if (newTel.length == $(this).val().length - 1) {
							$(this).val($(this).val().substring(0, 8) + " " + $(this).val().substring(8, 9));
						}
					}
				}

				if (_txtVal.length == 13) {
					$(".delete").hide();
					if ($("input[type='radio'][value='3']").prop("checked") == true) {
						$(".chong-tips").css("visibility", "hidden");
					}
					var _pName = getProvinceName(_txtVal.replace(/\s+/g, ""));
					var _yysName = getYysName(_txtVal.replace(/\s+/g, ""));
					if (_pName && _yysName) {
						$("#" + liuLiangInfoFont + "").html(_pName + _yysName + "流量充值提示");
						if (true) {//prevYYSName != _yysName || prevYYSName == "联通"
							// $("#" + rechargPrice + "").html("0.0元");
							$("#" + selLiuliang + "").html("");
							var _pList = proObj[_yysName].plist;
							var discount;
							if (_yysName == "移动") {
								discount = llyddiscount;
								if(_pName == "陕西"){
									discount = llydsxdiscount;
								}
							} else if (_yysName == "联通") {
								discount = llltdiscount;
							} else {
								discount = lldxdiscount;
							}

							// $("#" + selLiuliang + "").append("<div
							// class='productSelInner' >");
							var appendData = "<div class='productSelInner' >";
							for (var i = 0; i < _pList.length; i++) {
								var price = (parseInt(llPrice[_pList[i].id] * discount) / 100);
								var dataName = _yysName + "全国流量" + _pList[i].name;
								var isnew = isnews[_pList[i].id];
								var yongyong = _pList[i].yongjin;
								var isnewcss = false;
								if (isnew && isnew == "yes") {
									isnewcss = true;
								}
								if ((i + 1) % 3 == 1) {
									if (_pList[i].supportProvince == undefined || _pList[i].supportProvince == _pName) {
										appendData += "<div class='leftDiv' ><div  class='llProDiv' dataname='" + dataName + "' value='" + _pList[i].id
												+ "' pricedata='" + price + "' yongyong='"+_pList[i].yongjin+"'><p class='firstFont'>" + _pList[i].name + "</p><p class='secondFont'>售价" + price
												+ "元</p></div></div>";
									}
								} else if ((i + 1) % 3 == 2) {
									if (_pList[i].supportProvince == undefined || _pList[i].supportProvince == _pName) {
										appendData += "<div class='middleDiv'><div class='llProDiv' dataname='" + dataName + "' value='" + _pList[i].id
												+ "' pricedata='" + price + "' yongyong='"+_pList[i].yongjin+"'><p class='firstFont'>" + _pList[i].name + "</p><p class='secondFont'>售价" + price
												+ "元</p></div></div>";
									}

								} else {
									if (_pList[i].supportProvince == undefined || _pList[i].supportProvince == _pName) {
										appendData += "<div class='rightDiv'><div class='llProDiv' dataname='" + dataName + "' value='" + _pList[i].id
												+ "' pricedata='" + price + "' yongyong='"+_pList[i].yongjin+"'><p class='firstFont'>" + _pList[i].name + "</p><p class='secondFont'>售价" + price
												+ "元</p></div></div>";
									}
								}

							}
							appendData += "</div>";
							$("#" + selLiuliang + "").append(appendData);
							$("#" + selLiuliang + " .llProDiv").click(function() {
								$("#" + selLiuliang + " .llProDiv").removeClass("llProDiv_select");
								$("#" + selLiuliang + " .newsLiu").removeClass("llProDiv_select");
								$(this).addClass("llProDiv_select");
								// $("#" + rechargPrice +
								// "").html(parseFloat($(this).attr("pricedata"))
								// + "元");
								// $("#liuliangzengsongInfo").html(llPresentSize[$(this).attr("value")]
								// ? ("赠送流量" +
								// llPresentSize[$(this).attr("value")]) : "")
								$("#" + selLiuliang + "").val($(this).attr("value"));
								$("#" + selLiuliang + "").attr("dataname", $(this).attr("dataname"));
							});
							$("#" + selLiuliang + " .newsLiu").click(function() {
								$("#" + selLiuliang + " .newsLiu").removeClass("llProDiv_select");
								$("#" + selLiuliang + " .llProDiv").removeClass("llProDiv_select");
								$(this).addClass("llProDiv_select");
								// $("#" + rechargPrice +
								// "").html(parseFloat($(this).attr("pricedata"))
								// + "元");
								$("#" + selLiuliang + "").val($(this).attr("value"));
								$("#" + selLiuliang + "").attr("dataname", $(this).attr("dataname"));
							});
						}

						if (prevProvince != _pName || prevYYSName != _yysName) {
							var _tsInfo = getProvinceDesc(_pName, _yysName);
							$("#" + liuLiangTipContent + "").html("");
							isSupport = _tsInfo.isSupport;
							if (_tsInfo.isSupport) {
								$("#" + liuLiangTipContent + "").append(_tsInfo.tips);
							} else {
								$("#" + liuLiangTipContent + "").html("");
							}
							var supportDes = "";
							if (isSupport == true) {
								var _warnInfo = "";
								supportDes = _tsInfo.supportDesc;
								var _cxUrl = "http://wap.10086.cn";
								if (_yysName == "移动") {
									_cxUrl = "http://wap.10086.cn";
									_warnInfo = "<a style='text-decoration: underline;' href='" + _cxUrl + "'>点击查询充值到账情况</a>";
								} else if (_yysName == "联通") {
									_cxUrl = "http://wap.10010.com";
									_warnInfo = "<a style='text-decoration: underline;' href='" + _cxUrl + "'>点击查询充值到账情况</a>";
								} else if (_yysName == "电信") {
									_warnInfo = "<a style='text-decoration: none;' href='#'>发送短信指令'108'至10001，即可查询流量到账情况！</a>";
								}

								$("#liuliangInfoToCX").html(_warnInfo);
							} else {
								supportDes = "<font color=red>" + _tsInfo.supportDesc + "</font>";
							}
							$("#" + topTips + "").html(_pName + _yysName + "&nbsp;&nbsp;&nbsp;&nbsp;" + supportDes);
							$("#" + mainStageTelFrom + "").html(
									_pName + "&nbsp;&nbsp;" + _yysName + "&nbsp;&nbsp;<font color='red'>" + _tsInfo.supportDesc + "</font>");
						}
						if (isSupport) {
							// $("#liuLiangSubmit").removeClass("cm-btn
							// cm-btn-large cm-btn-disable");
							$("#" + liuLiangSubmit + "").removeAttr("disabled");
							$("#" + liuLiangSubmit + "").attr("href", "javascript:" + liuLiangSubmit + "Handler()");
						} else {
							$(this).parent().parent().siblings(".cm-btn-group").find("a").css("background", "#d0d0d0");
							$("#" + liuLiangSubmit + "").attr("href", "javascript:void(0);");
						}
						prevYYSName = _yysName;
						prevYYS = _yysName
					} else {
						_pName = _pName ? _pName : "未知省份";
						_yysName = _yysName ? _yysName : "未知运营商";
						$("#" + topTips + "").html("<font>" + _pName + "&nbsp;&nbsp;" + _yysName + "</font>");
						$("#" + mainStageTelFrom + "").html(_pName + "&nbsp;&nbsp;" + _yysName);
						$(this).parent().parent().siblings(".cm-btn-group").find("a").css("background", "#d0d0d0");
						$("#" + liuLiangSubmit + "").attr("href", "javascript:void(0);");
					}
					$(this).blur();
				} else {
					$(this).parent().parent().siblings(".cm-btn-group").find("a").css("background", "#d0d0d0");
					$("#" + liuLiangSubmit + "").attr("href", "javascript:void(0);");
				}
			});
	// 粘贴事件
	liuLiangPasteHandleProcess(mainStageTel);
}

function inputHuaFeiTelHandle(mainStageTel, rechargPrice, selLiuliang, topTips, mainStageTelFrom, liuLiangSubmit) {
	var prevProvince, prevYYSName, isSupport = false;
	$("#" + mainStageTel + "").focus();
	$("#" + mainStageTel + "").keyup(
			function(event) {
				var _txtVal = $.trim($(this).val());
				if (_txtVal.charAt(_txtVal.length - 1) < '0' || _txtVal.charAt(_txtVal.length - 1) > '9') {
					$(this).val(_txtVal.substring(0, _txtVal.length - 1));
					return;
				}
				if (event.which == 8) {
					$(this).val($(this).val());
				} else {
					if ($(this).val().length == 3) {
						$(this).val($(this).val() + " ");
					}
					if ($(this).val().length == 4) {
						var newTel = $(this).val().replace(/\s+/g, "");
						if (newTel.length == $(this).val().length) {
							$(this).val($(this).val().substring(0, 3) + " " + $(this).val().substring(3, 4));
						}
					}
					if ($(this).val().length == 8) {
						$(this).val($(this).val() + " ");
					}
					if ($(this).val().length == 9) {
						var newTel = $(this).val().replace(/\s+/g, "");
						if (newTel.length == $(this).val().length - 1) {
							$(this).val($(this).val().substring(0, 8) + " " + $(this).val().substring(8, 9));
						}
					}
				}
				if (_txtVal.length == 13) {
					$(".delete").hide();
					var _pName = getProvinceName(_txtVal.replace(/\s+/g, ""));
					var _yysName = getYysName(_txtVal.replace(/\s+/g, ""));
					if (_pName && _yysName) {
						// $("#" + rechargPrice + "").html("0.0元");
						$("#" + selLiuliang + "").html("");
						var _pList = proObjHuaFei;
						var discount;
						if (_yysName == "移动") {
							discount = hfyddiscount;
						} else if (_yysName == "联通") {
							discount = hfltdiscount;
						} else {
							discount = hfdxdiscount;
						}
						var appendData = "<div class='productSelInner' >";
						for (var i = 0; i < _pList.length; i++) {
							var price = (parseInt(hfPrice[_pList[i].id] * discount) / 100);
							if ((i + 1) % 3 == 1) {

								appendData += "<div class='leftDiv' ><div class='llProDiv' dataname='" + "自由话费" + _pList[i].name + "' value='" + _pList[i].id
										+ "' pricedata='" + price + "'><p class='firstFont'>" + _pList[i].name + "</p><p class='secondFont'>售价" + price
										+ "元</p></div></div>";
							} else if ((i + 1) % 3 == 2) {

								appendData += "<div class='middleDiv'><div class='llProDiv' dataname='" + "自由话费" + _pList[i].name + "' value='" + _pList[i].id
										+ "' pricedata='" + price + "'><p class='firstFont'>" + _pList[i].name + "</p><p class='secondFont'>售价" + price
										+ "元</p></div></div>";

							} else {

								appendData += "<div class='rightDiv'><div class='llProDiv' dataname='" + "自由话费" + _pList[i].name + "' value='" + _pList[i].id
										+ "' pricedata='" + price + "'><p class='firstFont'>" + _pList[i].name + "</p><p class='secondFont'>售价" + price
										+ "元</p></div></div>";
							}

						}
						appendData += "</div>";
						$("#" + selLiuliang + "").append(appendData);
						$("#" + selLiuliang + " .llProDiv").click(function() {
							$("#" + selLiuliang + " .llProDiv").removeClass("llProDiv_select");
							$(this).addClass("llProDiv_select");
							// $("#" + rechargPrice +
							// "").html(parseFloat($(this).attr("pricedata")) +
							// "元");
							$("#" + selLiuliang + "").val($(this).attr("value"));
							$("#" + selLiuliang + "").attr("dataname", $(this).attr("dataname"));
						});
						if (prevProvince != _pName || prevYYSName != _yysName) {
							var _tsInfo = getProvinceDesc(_pName, _yysName);
							isSupport = true;// _tsInfo.isSupport;
							var _discount = _yysName == "移动" ? hfyddiscount : _yysName == "联通" ? hfltdiscount : hfdxdiscount;
							$("#" + topTips + "").html(
									_pName + _yysName + "&nbsp;&nbsp;&nbsp;&nbsp;"
											+ (_discount == 1 ? "" : "<font color='#db2330'><b>" + (_discount * 10) + "折优惠</b>"));
							$("#" + mainStageTelFrom + "").html(
									_pName + "&nbsp;&nbsp;" + _yysName + "&nbsp;&nbsp;<font color='red'>" + "支持2G/3G/4G&nbsp;&nbsp;&nbsp;&nbsp;" + "</font>"
											+ (_discount == 1 ? "" : "<font color='#db2330'><b>" + (_discount * 10) + "折优惠，=</b></font>"));
						}
						if (isSupport) {
							$("#" + liuLiangSubmit + "").removeAttr("disabled");
							$("#" + liuLiangSubmit + "").attr("href", "javascript:" + liuLiangSubmit + "Handler()");
						} else {
							$(this).parent().parent().siblings(".cm-btn-group").find("a").css("background", "#d0d0d0");
							$("#" + liuLiangSubmit + "").attr("href", "javascript:void(0);");
						}
						prevYYSName = _yysName;
						prevYYS = _yysName
					} else {
						_pName = _pName ? _pName : "未知省份";
						_yysName = _yysName ? _yysName : "未知运营商";
						$("#" + topTips + "").html("<font color=red>" + _pName + "&nbsp;&nbsp;" + _yysName + "</font>");
						$("#" + mainStageTelFrom + "").html(_pName + "&nbsp;&nbsp;" + _yysName);
						$(this).parent().parent().siblings(".cm-btn-group").find("a").css("background", "#d0d0d0");
						$("#" + liuLiangSubmit + "").attr("href", "javascript:void(0);");
					}
					$(this).blur();
				} else {
					$(this).parent().parent().siblings(".cm-btn-group").find("a").css("background", "#d0d0d0");
					$("#" + liuLiangSubmit + "").attr("href", "javascript:void(0);");
				}
			});
	// 粘贴事件
	liuLiangPasteHandleProcess(mainStageTel);
}

function alertDia(content, title) {
	$.dialog({
		content : content,
		title : title,
		ok : function() {
			return true;
		},
		lock : true
	});
}
// 处理输入号码时显示问题
/*
 * function handleInputTel(data, type) {
 * 
 * var d = data; var v = data.replace(/\s+/g, ""); var vLength = v.length; // if
 * (vLength != 3 && vLength != 7) { if (vLength = 3 && type == 1) { d =
 * v.substring(0, 3); } else if (vLength = 7 && type == 1) { d = v.substring(0,
 * 3) + " " + v.substring(3, 7); } else if (vLength > 7) { d = v.substring(0, 3) + " " +
 * v.substring(3, 7) + " " + v.substring(7, vLength); } else if (vLength > 3 &&
 * vLength < 7) { d = v.substring(0, 3) + " " + v.substring(3, vLength); } // }
 * return d; }
 */

function clearInput() {
	$("#CZLLCardNo").val("");
	$("#CZLLCardPwd").val("");
	$("#rechargeCardNo").val("");
	$("#rechargeCardPwd").val("");
}

function handleDisabledSelect(radioIndex, id, index) {
	/*
	 * if (index == 1) { if ($(".card_box").length == 1) { index = 0; } }
	 */
	if (radioIndex == 3) {
		$(".card_box").eq(index).show();
		$(".chong-tips").css("visibility", "hidden");
		// $("#" + id + "").siblings("label").css("color", "#d0d0d0");
		// $("#" + id + "").val("").css("color", "#d0d0d0").attr("disabled",
		// "disabled");
	} else {
		$(".card_box").eq(index).hide();
		$(".chong-tips").css("visibility", "visible");
		// $("#" + id + "").removeAttr("disabled").css("color", "#000000");
		// $("#" + id + "").siblings("label").css("color", "#000000");
	}
}
/**
 * 提示框提示信息
 */
function addTiShi(payType, body) {
	var title = "";
	if (payType == 0) {
		title = "汉币充值 ";
	} else if (payType == 3) {
		title = "充值卡充值 ";
	}
	return title + body;
}

// 退换汉币
function backHB(userId, fee) {
	if (userId && fee) {
		$.post("BackHBServlet", {
			"userId" : userId,
			"fee" : fee
		}, function(data) {

		}, 'json');
	}
}

function rechargedHanBi(value, index, id, payType, body, total_fee, mianZhi, mobile, partnerId, rechargeCardNumber, rechargeCardPwd, businessType, flag) {
	var mydia = $.dialog({
		content : '充值处理中……'
	});
	if (isNaN(value)) {
		/*
		 * layer.tips("金额必须是数字!", '#' + id, { tips : [ 1, '#db2330' ], time :
		 * 2000 });
		 */
		$("#" + id).focus();
		showLayerTips(id, "金额必须是数字！", 1);
		return;
	}

	if (value.indexOf(".") != -1) {
		var subRes = value.substring(value.indexOf(".") + 1, value.length);
		if (subRes.length > 2) {
			/*
			 * layer.tips("小数点后最多输入两位!", '#' + id, { tips : [ 1, '#20bff9' ],
			 * time : 2000 });
			 */
			$("#" + id).focus();
			showLayerTips(id, "小数点后最多输入两位！", 1);
			return;
		}
	}
	if (value < 0) {
		$("#" + id).focus();
		showLayerTips(id, "充值金额须大于0！", 1);
		return;
	}
	if (!value) {
		$("#" + id).focus();
		showLayerTips(id, "请输入金额 ！", 1);
		return;
	}
	var payType = 1;
	if (isweixin()) {
		payType = 2;
	} else {
		payType = 1;
	}
	$.post("servlet/RechargeHanBiServlet", {
		payType : payType,
		money : value,
		body : "汉币充值" + value + "元",
		totalFee : total_fee,
		productId : mianZhi,
		partnerId : partnerId,
		businessType : businessType,
		flag : flag,
		mobile : mobile,
		payBody : body
	}, function(data) {
		var resultCode = data.resultCode;
		var returnDatas = data.resultMsg;
		if (resultCode == 926) {// 支付宝的返回值
			$("#alipay").html(data.resultMsg);
			return;
		}
		if (returnDatas.payType == "2") {
			if (resultCode == 0) {
				// 当后台正确返回值时，调用微信的支付
				function onBridgeReady() {
					WeixinJSBridge.invoke('getBrandWCPayRequest', {
						"appId" : returnDatas.appId, // 公众号名称
						"timeStamp" : returnDatas.timeStamp, // 时间戳
						"nonceStr" : returnDatas.nonceStr, // 随机串
						"package" : returnDatas.packAge,
						"signType" : returnDatas.signType, // 微信签名方式:
						"paySign" : returnDatas.paySign
					// 微信签名
					}, function(res) {
						if (res.err_msg == "get_brand_wcpay_request:ok") {
							/*
							 * layer.confirm("是否为" + mobile + "<br/>充值" + body, {
							 * btn : [ '是', '否' ], area : [ '30px', '' ] //
							 * shade: false //不显示遮罩 }, function(index) {
							 */
							if (businessType == 0) {
								liuliangHBGong(0, body, total_fee, mianZhi, mobile, partnerId, rechargeCardNumber, rechargeCardPwd);
							} else if (businessType == 1) {
								huafeiHBGong(0, body, total_fee, mianZhi, mobile, partnerId, rechargeCardNumber, rechargeCardPwd);
							}
							layer.close(index);
							/*
							 * }, function(index) { });
							 */

						} else if (res.err_msg == "get_brand_wcpay_request:cancel") {

						} else if (res.err_msg == "get_brand_wcpay_request:fail") {

						}
						mydia.close();
						// layer.close(index);
						// getInfo();
					});
				}
				if (typeof WeixinJSBridge == "undefined") {
					if (document.addEventListener) {
						document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
					} else if (document.attachEvent) {
						document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
						document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
					}
				} else {
					onBridgeReady();
				}
			} else {
				layer.alert("发起微信支付失败", {
					icon : 3,
					skin : 'layer-ext-moon'
				})
			}
		}
		/*
		 * layer.alert(data.resultMsg, { icon : 3, skin : 'layer-ext-moon' })
		 */
	}, "json");

}
/* 判断该用户是否已经显示过活动页面 true 没有出现过 */
function isAppeared() {
	var isAppeared = false;
	$.ajax({
		url : "servlet/ActivityServlet?method=getActivityParams",
		async : false,
		dataType : 'json',
		success : function(data) {
			if (data.resultCode == 0) {// 没有出现过
				isAppeared = true;
			}
		}
	})
	return isAppeared;
}

function updateIsAppeared() {
	$.post("servlet/ActivityServlet?method=updateActivityParams", function(data) {

	}, "json")
}

function isActivity() {
	if (browserRedirect()) {// 是移动端
		if (isweixin()) {// 是微信内置浏览器
			var time = 1438358400000;// 2015-08-01 时间小于2015-8-1则出现
			if ($.now() < time) {
				if (isAppeared()) {
					var lay = layer
							.open({
								type : 1,
								shade : 0.8,
								title : false, // 不显示标题
								area : [ '100%', '500px' ],
								skin : 'layui-layer-nobg',
								offset : '30px',
								closeBtn : false,
								content : "<div  align='center'><img  width='260px' src='images/guidepage.png'/></div><div align='center' style='margin-top:40px;'><img id='iknowBtn'  width='160px'  src='images/btn.png'/></div>" // 捕获的元素
							})
					$("#iknowBtn").click(function() {
						updateIsAppeared();
						layer.close(lay);
					})
				}
			}
		}
	}
}
// 获取每月最后几天
function getCurrentMonthLastTwo(day) {
	var date = new Date();
	var currentMonth = date.getMonth();
	var nextMonth = ++currentMonth;
	var nextMonthFirstDay = new Date(date.getFullYear(), nextMonth, 1);
	var twoDay = 1000 * 60 * 60 * 24 * day;
	return new Date(nextMonthFirstDay - twoDay);
}