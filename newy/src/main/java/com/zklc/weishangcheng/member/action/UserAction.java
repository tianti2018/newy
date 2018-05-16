package com.zklc.weishangcheng.member.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.utils.GetWxOrderno;
import com.utils.RequestHandler;
import com.utils.Sha1Util;
import com.utils.TenpayUtil;
import com.zklc.framework.action.BaseAction;
import com.zklc.weishangcheng.member.dao.FhRecordDao;
import com.zklc.weishangcheng.member.dao.JifenUserDao;
import com.zklc.weishangcheng.member.hibernate.persistent.AccessToken;
import com.zklc.weishangcheng.member.hibernate.persistent.Card;
import com.zklc.weishangcheng.member.hibernate.persistent.FhRecord;
import com.zklc.weishangcheng.member.hibernate.persistent.JiFenRecord;
import com.zklc.weishangcheng.member.hibernate.persistent.JifenUser;
import com.zklc.weishangcheng.member.hibernate.persistent.LiuCode;
import com.zklc.weishangcheng.member.hibernate.persistent.Order;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderLiu;
import com.zklc.weishangcheng.member.hibernate.persistent.TixianLiu;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.OrderAddressVO;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.UserVo;
import com.zklc.weishangcheng.member.service.CardService;
import com.zklc.weishangcheng.member.service.CityService;
import com.zklc.weishangcheng.member.service.FhRecordDianService;
import com.zklc.weishangcheng.member.service.FhrecordService;
import com.zklc.weishangcheng.member.service.GoodYongJinService;
import com.zklc.weishangcheng.member.service.JiFenRecordService;
import com.zklc.weishangcheng.member.service.JifenUserService;
import com.zklc.weishangcheng.member.service.LiuCodeService;
import com.zklc.weishangcheng.member.service.OrderAddressService;
import com.zklc.weishangcheng.member.service.OrderLiuService;
import com.zklc.weishangcheng.member.service.OrderService;
import com.zklc.weishangcheng.member.service.TixianLiuService;
import com.zklc.weishangcheng.member.service.UseryService;
import com.zklc.weishangcheng.member.service.WeixinAutosendmsgService;
import com.zklc.weishangcheng.member.service.XingHuoQuanRecordService;
import com.zklc.weishangcheng.member.service.YongjinService;
import com.zklc.weishangcheng.member.util.HongBaoUtil;
import com.zklc.weixin.util.SystemMessage;
import com.zklc.weixin.util.UserInfoUtil;
import com.zklc.weixin.util.WeixinUtil;
import com.zklc.weixin.util.sign;

@ParentPackage("json")
@Namespace("/user")
@Action(value = "userAction")
@Results({
		@Result(name = "qrcodePage", location = "/WEB-INF/jsp/user_qrcode.jsp"),
		@Result(name = "buy", location = "/buy.jsp"),
		@Result(name = "miaosha", location = "/miaosha.jsp"),
		@Result(name = "initOpenUser", location = "/open.jsp"),
		@Result(name = "initMessage", location = "/initMessage.jsp"),
		@Result(name = "gotoJifenShop", location = "/WEB-INF/jsp/jifen/jifenIndex.jsp"),
		@Result(name = "gotoHuiyuanShop", location = "/WEB-INF/jsp/huiyuan/huiyuanIndex.jsp"),
		@Result(name = "yiyou", location = "/WEB-INF/jsp/yiyou.jsp"),
		@Result(name = "gotoUpdateRef", location = "/WEB-INF/jsp/updateRef.jsp"),
		@Result(name = "gotoPersonalCenter", location = "/WEB-INF/jsp/personalCenter.jsp"),
		
		@Result(name = "fahongbao", type = "redirect", location = "/user/userAction!phoneFamily.action"),		
		@Result(name = "ajaxResult", type = "json", params = { "message",
				"message" }),
		@Result(name = "phoneFamily", location = "/WEB-INF/jsp/phone_family.jsp"),
		@Result(name = "viewHongbao", location = "/WEB-INF/jsp/phone_family_hongbao.jsp"),
		@Result(name = "viewPeoPage", location = "/WEB-INF/jsp/phone_family_vip.jsp"),
		@Result(name = "viewPeoPage2", location = "/WEB-INF/jsp/phone_family_vip.jsp"),
		@Result(name = "jamp", location = "/jamp.jsp"),
		@Result(name = "jampshopIndex", location = "/WEB-INF/jsp/market.jsp"),
		@Result(name = "loadRefs", location = "/WEB-INF/jsp/phone_family_ref.jsp"),
		
		@Result(name = "queryLiuBuy", location = "/liuliangbuy.jsp")
})
/**
 * bai
 */
public class UserAction extends BaseAction {
	@Autowired
	private JiFenRecordService jfrecordService;
	@Autowired
	private JifenUserService userService;
	@Autowired
	private FhrecordService fhrecordService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderAddressService orderAddressService;
	@Autowired
	private JifenUserDao jifenUserDao;
	@Autowired
	private FhRecordDao fhRecordDao;
	@Autowired
	private UseryService useryService;
	@Autowired
	private OrderLiuService orderLiuService;
	@Autowired
	private CardService cardService;
	@Autowired
	private YongjinService yongjinService;
	@Autowired
	private TixianLiuService tixianLiuService;
	@Autowired
	private WeixinAutosendmsgService autosendmsgService;
	@Autowired
	private LiuCodeService liuCodeService;
	@Autowired
	private WeixinAutosendmsgService weixinAutosendmsgService;
	@Autowired
	private FhRecordDianService fhDianService;
	@Autowired
	private GoodYongJinService yongJinService;
	@Autowired
	private XingHuoQuanRecordService xingHuoQuanRecordService;
	@Autowired
	private CityService cityService;
	
	private JifenUser user;
	private Order order;
	private String id;
	private List tSubscibeList;
	private String hyid;
	private String favid;
	private String goodId;// 商铺id
	private String subRemark;// 订单说明
	private String ddzt;// 订单状态
	private String imgPath;// 图片路径
	private String rcode;// 微信code
	private String code;// 微信code
	private String returnUrl;// 跳转地址
	private String wxOpenid;// 用户微信标识符
	private String imgUrl;

	private Double allScore;// 总积分
	private Double currScore;// 可用积分
	public String message;// 回显信息
	private String centerLoginName;// 服务中心
	private String referrerLoginName;// 推荐人
	public String tjrLoginName;
	public String appId2;
	public String timeStamp;
	public String nonceStr2;
	public String packages;
	public String signType2;
	public String paySign2;
	public String total_fee;// 支付金额
	public String orderNo;
	public String ckBindWx;// 是否绑定微信号
	public String tempName;
	public String viewLevel;//用于统计三级用户
	public String searchValue;
	
	private String orderType;
	public Integer userId;//传值用的    
	private Integer pageNum;
	private Integer userId2;
	
	private String sex;
	private String mobile;
	private String email;
	private String zjlx;
	private String zjhm;
	private String address;
	private String bank;
	private String zipcode;
	private String fxts;
	private String xy1;
	private String xy2;
	private String xy3;
	private String xy4;
	private String xy5;
	private String sfzzm;
	private String account;
	private String sfzfm;
	private String scsfz;
	private String yhkzm;
	private String userName;
	
	private Integer level;
	private Integer amount;
	
	private Integer flagCount;
	
	private Integer userLevel;
	
	private String textMessage;
	
	private String toWxOpenId;
	
	private String state;
	
	public String gotoJifenShop(){
		user = getSessionUser();
		String hql = "from JiFenRecord j where j.userId ="+ user.getUserId() +
				"and j.status = 0 and j.jifen = 60 and j.userId > 14000000";
		List<JiFenRecord> records = jfrecordService.findByHql(hql, null);
		if(records.size()>0){
			for(JiFenRecord record:records){
				record.setStatus(1);
				jfrecordService.update(record);
			}
		}
		if(user==null){
			return "timeOut";
		}
		return "gotoJifenShop";
	}
	
	public String gotoHuiyuanShop(){
		user = getSessionUser();
		if(user==null){
			return "timeOut";
		}
		return "gotoHuiyuanShop";
	}
	
	
	public String jampshopIndex(){
//		user = getSessionUser();
		user = userService.findById(1820);
		request.getSession().setAttribute("loginUser",user);

		if(user == null){
			return "timeOut";
		}
		
//		return "jamp";
		return "jampshopIndex";
	}
	
	public String shopIndex(){
		user = getSessionUser2();
		if(user == null){
			return "timeOut";
		}
		AccessToken accessToken = autosendmsgService.processAccessToken();
		String nonce_str = sign.create_nonce_str();
        String timestamp = sign.create_timestamp();
        String url = SystemMessage.getString("YUMING")+"/user/userAction!shopIndex.action";
        request.setAttribute("appId", SystemMessage.getString("APPID"));
        request.setAttribute("nonce_str", nonce_str);
        request.setAttribute("timestamp", timestamp);
        request.setAttribute("signature", sign.getSignature(accessToken.getTicket(), url, nonce_str, timestamp));
        request.setAttribute("url", 
        		"https://open.weixin.qq.com/connect/oauth2/authorize?appid="+SystemMessage.getString("APPID")+"&redirect_uri="+
        				url+"&response_type=code&scope=snsapi_userinfo&state="+user.getUnionid()+"#wechat_redirect"
        		
//        		WeixinUtil.getShorUrl(accessToken.getToken(), 
//        				"https://open.weixin.qq.com/connect/oauth2/authorize?appid="+SystemMessage.getString("APPID")+"&redirect_uri="+
//        				url+"&response_type=code&scope=snsapi_userinfo&state="+user.getUnionid()+"#wechat_redirect"
//        				)
        			);
		return "jampshopIndex";
	}
	
	
	public String loadRefs(){
		user = getSessionUser();
		if(user==null){
			return "timeOut";
		}
		JifenUser p1=null;
		JifenUser p2=null;
		JifenUser p3=null;
		System.out.println(">>>>>>>>>>>>> user.getReferrerId() "+user.getReferrerId());
		if (null!=user.getReferrerId()) {
			//sendAddress(1,user.getReferrerId());
			p1 = userService.findById(user.getReferrerId()); //3级代理商
			if (null!=p1) {
				request.setAttribute("orderAddress1", orderAddressService.findOrderAddressByUserId(p1.getUserId()));
				if (null!=p1.getReferrerId()) {
					p2 = (JifenUser)userService.findById(p1.getReferrerId()); //2级代理商
					if (null!=p2) {
						request.setAttribute("orderAddress2", orderAddressService.findOrderAddressByUserId(p2.getUserId()));
						if (null!=p2.getReferrerId()) {
							p3 = (JifenUser)userService.findById(p2.getReferrerId()); //1级代理商
							if (null!=p3) {
								request.setAttribute("orderAddress3", orderAddressService.findOrderAddressByUserId(p3.getUserId()));
							}
						}
					}
					
				}
			}
			
		}
		request.setAttribute("p1", p1);
		request.setAttribute("p2", p2);
		request.setAttribute("p3", p3);
		
		
		return "loadRefs";
	}
	
	public void loadUserMoneyWL(){//未领取佣金
		JSONObject json = new JSONObject();
		user = getSessionUser();
		json.put("success", false);
		if(user !=null){
			DecimalFormat   fnum   =   new   DecimalFormat("##0.00"); //四舍五入，保留两位小数 
			Double kdailiYJ = fhrecordService.findTotalJiFenOneByUserId(user.getUserId(), "2","1");//代理佣金
			Double kdianzhuYJ = fhDianService.findTotalJiFenOneByUserId(user.getUserId(), null,"1");//店铺佣金
			Double kgoodYJ = yongJinService.findAllMoneyBuyUserIdAndType(user.getUserId(),1);//产品佣金
			List ktList = userService.findYongin(user.getUserId());
			Object[] obj = (Object[]) ktList.get(0);
			Double tolMoney = Double.parseDouble(obj[0].toString());
			Double yiMoney = Double.parseDouble(obj[1].toString());
			Double kliuliangYJ=tolMoney-yiMoney;
			json.put("success", true);
			json.put("kdailiYJ", fnum.format(kdailiYJ));
			json.put("kdianzhuYJ", fnum.format(kdianzhuYJ));
			json.put("kgoodYJ", fnum.format(kgoodYJ));
			json.put("kliuliangYJ", fnum.format(kliuliangYJ));
		}
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadUserMoneyAll(){
		JSONObject json = new JSONObject();
		user = getSessionUser();
		json.put("success", false);
		if(user !=null){
			DecimalFormat   fnum   =   new   DecimalFormat("##0.00"); //四舍五入，保留两位小数 
			Double totalMoneyDai = fhrecordService.findTotalJiFenOneByUserId(user.getUserId(), "2","1,2");//代理总佣金
			Double totalMoneyDian = fhDianService.findTotalJiFenOneByUserId(user.getUserId(), null,"1,2");//店铺总佣金
			Double ytxGoodYJ = yongJinService.findAllMoneyBuyUserIdAndType(user.getUserId(),2);
			Double ktxGoodYJ = yongJinService.findAllMoneyBuyUserIdAndType(user.getUserId(),1);
			Double allGoodYJ = ytxGoodYJ+ktxGoodYJ;
			List ktList = userService.findYongin(user.getUserId());
			Object[] obj = (Object[]) ktList.get(0);
			Double tolLiuLiangMoney = Double.parseDouble(obj[0].toString());
			Double totalMoney = tolLiuLiangMoney+totalMoneyDai+totalMoneyDian+allGoodYJ;
			user = userService.updateMoney(user,totalMoney);
			request.getSession().setAttribute("loginUser",user);
			json.put("totalMoney", fnum.format(totalMoney));
			json.put("success", true);
		}
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadUserInfo(){
		JSONObject json = new JSONObject();
		json.put("success", false);
		user = getSessionUser();
		if(user !=null){
			json = userService.updateUserInfo(user,json,request);
		}
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String gotoPersonalCenter(){
		user = getSessionUser();
		//user = userService.findById(3480);
		if(user==null){
			return "timeOut";
		}

		List zongJifen = jfrecordService.countUserJifen(user.getUserId(),1);
		List zongnewUser = jfrecordService.countNewUserJifen(user.getUserId(),1);
		user = userService.updateUserMsg(user,zongJifen);
		 request.getSession().setAttribute("loginUser",user);
		//Integer xiaofeiJifen = jfrecordService.findbyUserIdAndStatus(user.getUserId(),2);
		//jfrecordService.countUserJifen(user.getUserId(),1);
		request.setAttribute("zongJifen", zongJifen.get(0));//总的可用积分    总的消耗积分   总其他积分  总的推荐用户积分---未写
		request.setAttribute("xiaofeiJifen",  Integer.parseInt(zongJifen.get(1).toString())+ Integer.parseInt(zongnewUser.get(1).toString()));
		request.setAttribute("zongnewuser", zongnewUser.get(0));
		Integer newuserUse = Integer.parseInt(zongnewUser.get(0).toString())-Integer.parseInt(zongnewUser.get(1).toString());
		request.setAttribute("usejifen", Integer.parseInt(zongJifen.get(0).toString())-Integer.parseInt(zongJifen.get(1).toString())+newuserUse);
		/*request.setAttribute("notUse", zongJifen.get(2));*/
		
		return "gotoPersonalCenter";
	}
	
	public void updateRef(){
		user = getSessionUser();
		JSONObject json = new JSONObject();
		json.put("success", true);
		if(user!=null){
			user = userService.findById(user.getUserId());
			if(user.getReferrerId()!=null){
				json.put("success", "yiyou");
			}else {
				if(userId!=null&&userId<user.getUserId()){
					user.setReferrerId(userId);
					userService.update(user);
				}else{
					json.put("success", false);
				}
			}
		}else{
			json.put("success", false);
		}
		response = ServletActionContext.getResponse();
		try {
			response.getWriter().print(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void searcheRef(){
		user =getSessionUser();
		JSONObject json = new JSONObject();
		json.put("success", true);
		if(user!=null){
			user = userService.findById(user.getUserId());
			if(user.getReferrerId()!=null){
				json.put("success", "yiyou");
			}else {
				if(userId!=null){
					JifenUser ref=userService.findById(userId);
					if(ref!=null){
						json.put("userId", ref.getUserId());
						json.put("userName", ref.getUserName());
					}
				}else{
					json.put("success", false);
				}
			}
		}else{
			json.put("success", false);
		}
		response = ServletActionContext.getResponse();
		try {
			response.getWriter().print(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String gotoUpdateRef(){
		user = getSessionUser();
//		user = userService.findById(7314);
//		 request.getSession().setAttribute("loginUser",user);
		if(user==null){
			return "timeOut";
		}
		request.setAttribute("userNiKi", user.getUserName());
		request.setAttribute("userPhoto", user.getHeadUrl());
		request.setAttribute("userAppDate", new SimpleDateFormat("yyyy-MM-dd H:m:s").format(user.getAppDate()));
		request.setAttribute("userNiId", user.getUserId());
		request.setAttribute("level", user.getLevel());
		if(user.getReferrerId()==null){
			return "gotoUpdateRef";
		}else{
			return "yiyou";
		}
		
	}
	
	public void testq(){
		 weixinAutosendmsgService.processTicketByToken();
		//orderLiuService.liuliangPay(liu, null, null);
	}
	
	public String queryLiuBuy(){
		user = getSessionUser();
		if(user == null){
			return "error";
		}
		//判断平台是否欠费，
		//busperson 平台是否欠费
		//liumoney 移动是否可充值  0：可使用  1不可使用
		//liunum 联通是否可充值 
		//yongjin 电信是否可充值 
		LiuCode code = liuCodeService.findById(25);
		if(code != null){
			request.setAttribute("isbuy", code.getBusPerson());
			request.setAttribute("isyi", code.getLiuMoney());
			request.setAttribute("islian", code.getLiuNum());
			request.setAttribute("isdian", code.getYongjin());
		}
		return "queryLiuBuy";
	}
	
	public String initMessage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("toWxOpenId", toWxOpenId);
		
		return "initMessage";
	}
	
	public String miaosha(){
		user = getSessionUser();
//		user = userService.findById(1820);
		if(user == null){
			return "timeOut";
		}
		request.setAttribute("orderAddress", orderAddressService.findOrderAddressByUserId(user.getUserId()));
		return "miaosha";
	}
	
	/**
	 * 统计流量佣金
	 * @return 提现页面
	 */
	public String countLiu(){
		user = getSessionUser();
		if(user == null){
			return "timeOut";
		}
		List list = orderLiuService.findBySql("SELECT if(sum(y.money)>0,sum(y.money),0) FROM yongjin y where y.toUserId="+user.getUserId(), null);
		request.setAttribute("totalyong", list.get(0));
		
		return "countLiu";
	}
	
	
	
	//手机验证码
	public void sendMobileCode() {
		Random ran=new Random();
		String ranNum="";
		for (int i=0;i<4;i++) {
			ranNum+=ran.nextInt(10);
		}
		HashMap<String, Object> result = null;
		
		//初始化SDK
		CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
		//******************************注释*********************************************
		//*初始化服务器地址和端口                                                       *
		//*沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
		//*生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883");       *
		//*******************************************************************************
		restAPI.init("sandboxapp.cloopen.com", "8883");
		
		//******************************注释*********************************************
		//*初始化主帐号和主帐号令牌,对应官网开发者主账号下的ACCOUNT SID和AUTH TOKEN     *
		//*ACOUNT SID和AUTH TOKEN在登陆官网后，在“应用-管理控制台”中查看开发者主账号获取*
		//*参数顺序：第一个参数是ACOUNT SID，第二个参数是AUTH TOKEN。                   *
		//*******************************************************************************
		restAPI.setAccount("aaf98f894e7826aa014e858929e109ab", "54c020e2de514204b92a3bfa2702454b");
		
		
		//******************************注释*********************************************
		//*初始化应用ID                                                                 *
		//*测试开发可使用“测试Demo”的APP ID，正式上线需要使用自己创建的应用的App ID     *
		//*应用ID的获取：登陆官网，在“应用-应用列表”，点击应用名称，看应用详情获取APP ID*
		//*******************************************************************************
		restAPI.setAppId("8a48b5514e7c2193014e8598ab1906ea");
		
		
		//******************************注释****************************************************************
		//*调用发送模板短信的接口发送短信                                                                  *
		//*参数顺序说明：                                                                                  *
		//*第一个参数:是要发送的手机号码，可以用逗号分隔，一次最多支持100个手机号                          *
		//*第二个参数:是模板ID，在平台上创建的短信模板的ID值；测试的时候可以使用系统的默认模板，id为1。    *
		//*系统默认模板的内容为“【云通讯】您使用的是云通讯短信模板，您的验证码是{1}，请于{2}分钟内正确输入”*
		//*第三个参数是要替换的内容数组。																														       *
		//**************************************************************************************************
		
		//**************************************举例说明***********************************************************************
		//*假设您用测试Demo的APP ID，则需使用默认模板ID 1，发送手机号是13800000000，传入参数为6532和5，则调用方式为           *
		//*result = restAPI.sendTemplateSMS("13800000000","1" ,new String[]{"6532","5"});																		  *
		//*则13800000000手机号收到的短信内容是：【云通讯】您使用的是云通讯短信模板，您的验证码是6532，请于5分钟内正确输入     *
		//*********************************************************************************************************************
		result = restAPI.sendTemplateSMS(mobile,"1" ,new String[]{ranNum,"10"});
		
		System.out.println("SDKTestGetSubAccounts result=" + result);
		if("000000".equals(result.get("statusCode"))){
			//正常返回输出data包体信息（map）
			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for(String key:keySet){
				Object object = data.get(key);
				System.out.println(key +" = "+object);
			}
		}else{
			//异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
		}
		
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			HttpServletRequest request = ServletActionContext.getRequest();
			response.setContentType("text/json; charset=utf-8");
			PrintWriter out;
			out = response.getWriter();
			JSONObject obj = new JSONObject();  
	    obj.put("success", true);
	    obj.put("ranNum", ranNum);
	    out.println(obj.toString()); 
	    out.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	
	public void sendMessage () {
		//String mess = "您好:\n您的"+levelName+"【"+user.getUserName()+"】"+"已成功下单！\n"+"支付金额："+order.getMoney()+"元";
		user = getSessionUser();
		if(user!=null){
			StringBuilder sb = new StringBuilder();
			sb.append("您的合伙人编号【"+user.getUserId()+"】姓名【"+user.getUserName()+"】给您留言:");
			sb.append(textMessage);
			
			sb.append("<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid="
					+ SystemMessage.getString("APPID")
					+ "&redirect_uri="
					+ SystemMessage.getString("ZHIFU_YUMING")
					+ "/user/userAction!initMessage.action?toWxOpenId="+user.getUnionid()+"&response_type=code&scope=snsapi_base&state=123&connect_redirect=1#wechat_redirect\">"
					+ "   回复留言" + "</a>");
			if(wxOpenid!=null){
				Usery toUsery = useryService.findbyUnionId(wxOpenid);
				if(toUsery!=null){
					autosendmsgService.sendMsg(toUsery.getWxOpenid(),sb.toString());
					try {
						HttpServletResponse response = ServletActionContext.getResponse();
						HttpServletRequest request = ServletActionContext.getRequest();
						response.setContentType("text/json; charset=utf-8");
						PrintWriter out;
						out = response.getWriter();
						JSONObject obj = new JSONObject();  
					    obj.put("success", true);
					    out.println(obj.toString()); 
					    out.close();
					} 
					catch (IOException e) {
						e.printStackTrace();
					}
				}else{
					Usery usery = useryService.findbyUserId(user.getUserId());
					autosendmsgService.sendMsg(usery.getWxOpenid(),"这位会员还没有关注星火草原!!");
				}
			}
		}
		
		
	} 
	
	public List findUserLevel(int flag,List<Integer> userIds,int level,int pageNum) {
		List list1 = jifenUserDao.findAllUserIdByrefferIds(userIds,1,level,0);
		if (flag==level) {
			return jifenUserDao.findAllUserIdByrefferIds(userIds,2,level,pageNum);
		}
		else if (flag<level) {
			flag++;
			return findUserLevel(flag,list1,level,pageNum);
		}
		return null;
	}
	
	public List findUserIdsLevel(int flag, List<Integer> userIds, int level,int pageNum) {
		List list1 = jifenUserDao.findAllUserIdByrefferIds(userIds,1,level,0);
		if (flag==level) {
			return jifenUserDao.findAllUserIdByrefferIds(userIds,1,level,0);
		}
		else if (flag<level) {
			flag++;
			return findUserIdsLevel(flag,list1,level,pageNum);
		}
		return null;
	}
	
	
	public void findUserLevelCount(int flag,List<Integer> userIds,int level) {
		List list1 = jifenUserDao.findAllUserIdByrefferIds(userIds,1,level,0);
		if (flag==level) {
			if (list1!=null&&list1.size()!=0) {
				int count = Integer.valueOf(list1.size()+"");
				HttpServletRequest request = ServletActionContext.getRequest();
				request.setAttribute("count"+level, count);
			}
		}
		else {
			if (flag<level) {
				flag++;
				findUserLevelCount(flag,list1,level);
			}
		}
	}
	
	public String listCards(){
		user = getSessionUser();
//		user = userService.findById(1821);
		if(user!=null){
			List<Card> list = cardService.listCards(user);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", true);
			map.put("list", list);
			JSONObject json = JSONObject.fromObject(map);
			try {
				ServletActionContext.getResponse().getWriter().print(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 获取用户信息并跳转到family页面
	 * @return
	 */
	public String phoneFamily(){
		//获取用户信息
//		userId = 1820;
		user = getSessionUser();
//		user = userService.findById(3480);
		if(user == null){
			return "timeOut";
		}
		user = userService.findById(user.getUserId());
		request.getSession().setAttribute("loginUser",user);
		DecimalFormat   fnum   =   new   DecimalFormat("##0.00"); //四舍五入，保留两位小数 
		Double totalMoney = fhrecordService.findTotalJiFenOneByUserId(user.getUserId(), "2","1,2");//总佣金
		Double ytxMoney = fhrecordService.findTotalJiFenOneByUserId(user.getUserId(), "2","2");//已领取佣金
		Double ktxMoney = totalMoney-ytxMoney;//未领取佣金,
		
		request.setAttribute("totalMoney", fnum.format(totalMoney)); //总佣金
		request.setAttribute("ytxMoney", fnum.format(ytxMoney));
		request.setAttribute("ktxMoney", fnum.format(ktxMoney));
		
//		List<Card> list = cardService.listCards(user);
//		
//	List list = cardService.findByUserId(user.getUserId());
//		request.setAttribute("list", list);
//		List<Integer> listOne = new ArrayList<Integer>();
//		listOne.add(user.getUserId());
//		
//		//1级会员总数
//		findUserLevelCount(1, listOne, 1);
//		findUserLevelCount(1, listOne, 2);
//		findUserLevelCount(1, listOne, 3);
		
//		JifenUser p1=null;
//		JifenUser p2=null;
//		JifenUser p3=null;
//		System.out.println(">>>>>>>>>>>>> user.getReferrerId() "+user.getReferrerId());
//		if (null!=user.getReferrerId()) {
//			//sendAddress(1,user.getReferrerId());
//			p1 = userService.findById(user.getReferrerId()); //3级代理商
//			if (null!=p1) {
//				request.setAttribute("orderAddress1", orderAddressService.findOrderAddressByUserId(p1.getUserId()));
//				if (null!=p1.getReferrerId()) {
//					p2 = (JifenUser)userService.findById(p1.getReferrerId()); //2级代理商
//					if (null!=p2) {
//						request.setAttribute("orderAddress2", orderAddressService.findOrderAddressByUserId(p2.getUserId()));
//						if (null!=p2.getReferrerId()) {
//							p3 = (JifenUser)userService.findById(p2.getReferrerId()); //1级代理商
//							if (null!=p3) {
//								request.setAttribute("orderAddress3", orderAddressService.findOrderAddressByUserId(p3.getUserId()));
//							}
//						}
//					}
//					
//				}
//			}
//			
//		}
//		request.setAttribute("p1", p1);
//		request.setAttribute("p2", p2);
//		request.setAttribute("p3", p3);
		
		//获取推荐人
//		if (null!=p1) {
//			request.setAttribute("referee",p1.getUserName());
//		}
//		else{
//			request.setAttribute("referee", SystemMessage.getString("company"));
//		}
		//获取流量佣金
		Date d=new Date();   
		   SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
		   //System.out.println("今天的日期："+df.format(d));   
		   // System.out.println("五天后的日期：" + df.format(new Date(d.getTime() - 5 * 24 * 60 * 60 * 1000)));
		
		//List ylist = yongjinService.findByHql("from Yongjin y where y.toUserId="+user.getUserId()+" and y.createDate > '"+df.format(new Date(d.getTime() - 5 * 24 * 60 * 60 * 1000))+"' and y.status=1", null);
		List ktList = userService.findYongin(user.getUserId());
		
		//
		Object[] obj = (Object[]) ktList.get(0);
		//Double tolMoney = Double.parseDouble(ktList.get(0).toString());
		Double tolMoney = Double.parseDouble(obj[0].toString());
		Double yiMoney = Double.parseDouble(obj[1].toString());
	//	request.setAttribute("yongList", ylist);
		request.setAttribute("tolMoney", fnum.format(tolMoney));
		request.setAttribute("yiMoney", fnum.format(yiMoney));
		request.setAttribute("ktMoney", fnum.format(tolMoney-yiMoney));
		
		return "phoneFamily";
	}
	
	public String saveTixianLiu(){
		user = getSessionUser();
		Usery usery = useryService.findbyUserId(user.getUserId());
		if(usery == null){
			message = "在多试几次吧";
			return "ajaxResult";
		}
		//在次判断可提现佣金
		String tixian = request.getParameter("tixianMoney");
		if(StringUtils.isNotBlank(tixian)){
			Integer ktmoney = Integer.parseInt(tixian);
			List ktList =  userService.findYongin(user.getUserId());
			Object[] obj = (Object[]) ktList.get(0);
			
			Double tolMoney = Double.parseDouble(obj[0].toString());
			Double yiMoney = Double.parseDouble(obj[1].toString());
			if(ktmoney>(tolMoney-yiMoney)){
				message = "可提现余额不足";
			}else{
				TixianLiu tx = new TixianLiu();
				tx.setAmount(ktmoney);
				tx.setAddTime(new Date());
				tx.setUserId(usery.getUserId());
				
				message = userService.fahongbaoLiu(usery.getWxOpenid(), user.getUserId(), ktmoney*100,tx);
				//message = "成功";
			}
			
		}else{
			message = "提现金额不能为空";
		}
		return "ajaxResult";
	}
	
	public void fahongbao() {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String message = "";
		user = getSessionUser();
		Usery usery = useryService.findbyUserId(user.getUserId());
		if(user == null){
			message="当前用户不存在";
		}
		if (null!=user) {
			int levelFlag = level.intValue();
			Integer activity = null;
			switch (levelFlag) {
				case 1:activity = user.getActiviti1();break;
				case 2:activity = user.getActiviti2();break;
				case 3:activity = user.getActiviti3();break;
				
				//TODO 15
			}
			/*if (null==activity||0==activity) {
				message = "您还没有被您的第【 "+levelFlag+"级供应商激活】，请联系他为您激活";
			}*/
			if (null!=user.getLevel()&&user.getLevel()!=0&&user.getLevel()<level) {
				message = "您还不能发货，您的级别不够，请尽快成为卡主 ";
			}
			else {
				//看他的级别已经审核了几个了 审核三个就必须升级 目前最高级别为三级
				if (user.getLevel()==1) {
					int shCount = fhRecordDao.findCountFhRecordByMoney(user.getUserId(), 90.0);
					if (shCount>=3) {
						message = "您得审核次数已经用完,为了审核更多的红包,请尽快成为金卡 ";
					}
				}
				else if (user.getLevel()==2) {
					int shCount = fhRecordDao.findCountFhRecordByMoney(user.getUserId(), 180.0);
					if (shCount>=3) {
						message = "您得审核次数已经用完,为了审核更多的红包,请尽快成为钻卡 ";
					}
				}
				
				if (message=="") {
					message = userService.fahongbao(usery.getWxOpenid(),user.getUserId(),userId2,level,amount,flagCount);
				}
				//TODO 
				
			}
		}
		try {
			response.setContentType("text/json; charset=utf-8");
			PrintWriter out;
			out = response.getWriter();
			JSONObject obj = new JSONObject();  
		    obj.put("success", true);  
		    obj.put("message", message);  
		    out.println(obj.toString()); 
		    out.close();  
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	private JifenUser getSessionUser2(){
		
		
		UserInfoUtil userInfo = null;
		 user = (JifenUser) request.getSession().getAttribute("loginUser");
		 if(user==null){
			 if(!StringUtils.isNotEmpty(code)){
					try {
						code = request.getParameter("code");
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
				if(!StringUtils.isNotEmpty(wxOpenid)){
					 wxOpenid=(String) request.getSession().getAttribute("wxOpenid");
				}
			  if(code!=null){
				  if(wxOpenid==null){
					  System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> code333------ "+code);
					  userInfo = WeixinUtil.getUserInfoByCode(code);
					  if(userInfo!=null){
						  wxOpenid = userInfo.getOpenid();
						  request.getSession().setAttribute("wxOpenid",wxOpenid);
					  }
				  }
			  }
			 if(wxOpenid!=null){
				 boolean songjifen = false;
				 Usery usery = useryService.findbyWxOpenId(wxOpenid);
				 if(usery!=null){
					 user = useryService.findJifenUserByUsery(usery);
					 if (user!=null) {
						 request.getSession().setAttribute("loginUser",user);
					 }
				 }else{
					System.out.println("查询不到插入");
					usery = new Usery();
					usery.setSubscribe(1);
					usery.setUnionid(userInfo.getUnionid());
					usery.setWxOpenid(wxOpenid);
					usery.setAppDate(new Date());
					user = userService.findbyUnionId(userInfo.getUnionid());
					Usery parentUsery = null;
					if(user==null){
						songjifen = true;
						System.out.println("旧表中不存在");
						user = new JifenUser();
						user.setAppDate(new Date());
						user.setSubscribe(1);
						user.setLevel(0);
						user.setBlock("1");
				    	
				    	if(userInfo!= null) {
				    		user.setUserName(userInfo.getNickname().trim());
				    		user.setHeadUrl(userInfo.getHeadimgurl());
				    		user.setUnionid(userInfo.getUnionid());
				    	}
				    	state = request.getParameter("state");
				    	String ticket = state;
				    	System.out.println("uninoid-------------->"+ticket);
				    	if (null!=ticket&&!"".equals(ticket)) {
				    	  	List<Usery> listOne = useryService.findByProperty("unionid", ticket);
				    	  	if (listOne.size()!=0) {
				    	  		parentUsery = listOne.get(0);
				    	  		user.setReferrerId(parentUsery.getUserId());
				    	  	}
				    	}
				    	userService.save(user);
					}
					usery.setUserId(user.getUserId());
					useryService.save(usery);
			    	if(songjifen){
			    		JiFenRecord record = new JiFenRecord();
						record.setCreateDate(new Date());
						record.setJifen(60);
						record.setMemo("新用户关注");
						record.setStatus(0);
						record.setUserId(user.getUserId());
						record.setType(5);
						jfrecordService.save(record);
						JifenUser refferUser = null;
						if(parentUsery!=null){
							autosendmsgService.sendMsg(parentUsery.getWxOpenid(), "["+user.getUserName()+":"+
									user.getUserId()+"]点击了您分享的链接,积分将待其关注公众号后获得!");
							refferUser = userService.findById(parentUsery.getUserId());
							if(refferUser !=null){
								JiFenRecord record1 = new JiFenRecord();
								record1.setCreateDate(new Date());
								record1.setJifen(5);
								record1.setMemo("新超级粉丝["+user.getUserId()+"]关注,赠送["+refferUser.getUserId()+"]"+record1.getJifen()+"积分");
								record1.setStatus(0);
								record1.setUserId(refferUser.getUserId());
								record1.setFromUserId(user.getUserId());
								record1.setType(5);
								jfrecordService.save(record1);
								if(refferUser.getReferrerId()!=null){
									JifenUser r2 = userService.findById(refferUser.getReferrerId());
									if(r2!=null){
										JiFenRecord record2 = new JiFenRecord();
										record2.setCreateDate(new Date());
										record2.setJifen(2);
										record2.setMemo("新铁杆粉丝["+user.getUserId()+"]关注,赠送["+r2.getUserId()+"]"+record2.getJifen()+"积分");
										record2.setStatus(0);
										record2.setUserId(r2.getUserId());
										record2.setFromUserId(user.getUserId());
										record2.setType(5);
										jfrecordService.save(record2);
										if(r2.getReferrerId()!=null){
											JiFenRecord record3 = new JiFenRecord();
											record3.setCreateDate(new Date());
											record3.setJifen(1);
											record3.setMemo("新忠实粉丝["+user.getUserId()+"]关注,赠送["+r2.getReferrerId()+"]"+record3.getJifen()+"积分");
											record3.setStatus(0);
											record3.setUserId(r2.getReferrerId());
											record3.setFromUserId(user.getUserId());
											record3.setType(5);
											jfrecordService.save(record3);
										}
									}
								}
							}
						}
			    	}
				 }
			 }
		 }
		 if(user!=null&&!"".equals(user.getAppCenterId())&& null != user.getAppCenterId() && user.getAppCenterId().equals(2)){
			 return null;
		 }
		 //user =userService.findById(2341);
		return  user;
	} 
	public String viewPeoPage() {
		List list = null;
//		user = userService.findById(1820);
		user = getSessionUser();
		if(user == null){
			return "timeOut";
		}
		//获取当前登录用户id
		if((viewLevel!=null && !"".equals(viewLevel))){
			List<Integer> listOne = new ArrayList<Integer>();
			listOne.add(user.getUserId());
			//发送不同级别的用户
			List userList = new ArrayList();//userList  
			
			if(userId!=null){
					request.setAttribute("userIdS", userId);
					for (int i = 1; i <= 3; i++) {//这是用来判断等级的 可以在哪里搜索用户的 OK了
						List<Integer> listUserids = findUserIdsLevel(1, listOne, i, 0);//存的所有的id 
						if(listUserids!=null&&listUserids.size()>0){
							if(listUserids.contains(userId)){
								JifenUser uu = userService.findById(userId);
								userList.add(uu);//看到这了 相当于把这些都添加到了userlist里面  用来在页面显示的
							}
						}
					}
			}else{
				userList=findUserLevel(1,listOne,Integer.valueOf(viewLevel),1);
			}
			List<UserVo> userVos = new ArrayList<UserVo>();//就是相当于把users(JiFenUser)存到了UserVos  把其他的都遍历出来
			if(userList!=null&&userList.size()>0){
				for(int i=0;i<userList.size();i++){
					JifenUser u = (JifenUser) userList.get(i);
					OrderAddressVO address = orderAddressService.findOrderAddressByUserId(u.getUserId());
					UserVo userVo = new UserVo();
					userVo.setAddress(address);
					userVo.setUser(u);
					userVos.add(userVo);
				}
			}//看到这了  然后添加 这些  在页面上显示出来
			request.setAttribute("userList", userVos);
			request.setAttribute("viewLevel", viewLevel);
			
		}//
		
		return "viewPeoPage";
	}
	
	
	
	
	/*public String viewPeoPage2(){ //这是新的一个查询所有粉丝的方法
		user = getSessionUser();//获得在session里面的user  就是当前登录的用户
		if(user == null){
			return "timeOut";
		}//--------到这结束
		List<JifenUser> listUsers = null;
		if(userId!=null){
			listUsers = jifenUserDao.getRefferid(user.getUserId(), 1);;//存的所有的id 
			if(listUsers!=null&&listUsers.size()>0){
				for (int i = 0; i < listUsers.size(); i++) {
					
					if(listUsers.get(i).getUserId().equals(userId)){
						JifenUser uu = userService.findById(userId);
						listUsers.add(uu);//看到这了 相当于把这些都添加到了userlist里面
					}
				}
			}
		}else {
			listUsers=jifenUserDao.getRefferid(user.getUserId(), 1);//存了所有的粉丝对象
		}
		//request.setAttribute("listUsersLV", listUsers);
		System.out.println("dfjalskfjaklfjlkajflkjadslkfklajkfl");
		System.out.println(listUsers.get(2).getUserId());
		
		System.out.println(listUsers.size());
		System.out.println("dfjalskfjaklfjlkajflkjadslkfklajkfl");
		
		List<UserVo> userVos = new ArrayList<UserVo>();//就是相当于把users(JiFenUser)存到了UserVos  把其他的都遍历出来
		
		if(listUsers != null && listUsers.size() > 0){
			for(int i = 0;i < listUsers.size();i++){
				JifenUser u = listUsers.get(i);//获取粉丝对象
				OrderAddressVO address = orderAddressService.findOrderAddressByUserId(u.getUserId());
				UserVo userVo = new UserVo();
				userVo.setAddress(address);
				userVo.setUser(u);
				userVos.add(userVo);
			}
			
		}//看到这了  然后添加 这些  在页面上显示出来
		System.out.println("方法执行完了");
		request.setAttribute("userList", userVos);
		request.setAttribute("viewLevel", viewLevel);
		//System.out.println(listUserIds);
		return "viewPeoPage2";
		//return null;
	}  本来想着这个方法是用来修改的结果没用到  这个方法没有实现   前万别打开这个方法了 没用   */
	
	
	
	public void ajaxListUserLevel(){
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		List<Integer> listOne = new ArrayList<Integer>();
		listOne.add(userId);
		List<JifenUser> listLevelUsers = findUserLevel(1,listOne,Integer.valueOf(viewLevel),pageNum);
		response = ServletActionContext.getResponse();
		/***********组装json*************/
		
		
		if(listLevelUsers != null&&listLevelUsers.size()>0){
			List<UserVo> userVos = new ArrayList<UserVo>();
			for(int i=0;i<listLevelUsers.size();i++){
				JifenUser u = (JifenUser) listLevelUsers.get(i);
				OrderAddressVO address = orderAddressService.findOrderAddressByUserId(u.getUserId());
				UserVo userVo = new UserVo();
				userVo.setAddress(address);
				userVo.setUser(u);
				userVos.add(userVo);
			}
			jsonMap.put("success", true);
			jsonMap.put("children", userVos);
		}else {
			jsonMap.put("success", false);
		}
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		/***********组装json*************/
		try {
			response.getWriter().print(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String viewHongbao() {
		user = getSessionUser();
//		user = userService.findById(1820);
		if(user == null){
			return "timeOut";
		}
		//获取当前登录用户id
		if((viewLevel!=null && !"".equals(viewLevel))){
			List<Integer> listOne = new ArrayList<Integer>();
			listOne.add(user.getUserId());
			
			//发送不同级别的用户
			List<JifenUser> hongbaoUsers = new ArrayList<JifenUser>();
			List<JifenUser> userlist = findUserLevelhongbao(1,listOne,Integer.valueOf(viewLevel));
			List<FhRecord> fhRecords = fhRecordDao.findFHbyUserIdAndFlag(user.getUserId(),1);
			if(fhRecords.size()>0&&userlist!=null&&userlist.size()>0){
				for(FhRecord fh:fhRecords){
					for(JifenUser u:userlist){
						if(fh.getFromUserId().equals(u.getUserId())&&fh.getUserId()==user.getUserId().intValue()){
							if(!hongbaoUsers.contains(u))
								hongbaoUsers.add(u);
						}
					}
				}
			}
			request.setAttribute("userList", hongbaoUsers);
			Integer orderyi = orderService.findLevelPeoOrder(user.getUserId(), "1","all").size();
			request.setAttribute("viewLevel", viewLevel);
			request.setAttribute("orderyi", orderyi);
			
			request.setAttribute("userLevel", user.getLevel());
			
		}
		
		return "viewHongbao";
	}
	
	public List<JifenUser> findUserLevelhongbao(int flag,List<Integer> userIds,int level) {
		
		
		if (flag<level) {
			List list1 = jifenUserDao.findAllUserIdByrefferIds(userIds,1,level,0);
			flag++;
			return findUserLevelhongbao(flag,list1,level);
		}
		else {
			List listUsers = jifenUserDao.findhongbaoAllUserIdByrefferIds(userIds,2,level);
			return listUsers;
			
		}
	}
	
	
	public String buy() {
		user = getSessionUser();
//		user = userService.findById(1883);
//		request.getSession().setAttribute("loginUser",user);
		if(user == null){
			return "timeOut";
		}
		
		request.setAttribute("orderAddress", orderAddressService.findOrderAddressByUserId(user.getUserId()));
		
		/*JifenUser users = userService.findById(user.getUserId());
		JifenUser p1=null;
		JifenUser p2=null;
		JifenUser p3=null;
		System.out.println(">>>>>>>>>>>>> user.getReferrerId() "+user.getReferrerId());
		if (null!=users.getReferrerId()) {
			//sendAddress(1,user.getReferrerId());
			p1 = userService.findById(users.getReferrerId()); //3级代理商
			if (null!=p1) {
				request.setAttribute("orderAddress1", orderAddressService.findOrderAddressByUserId(p1.getUserId()));
				if (null!=p1.getReferrerId()) {
					p2 = (JifenUser)userService.findById(p1.getReferrerId()); //2级代理商
					if (null!=p2) {
						request.setAttribute("orderAddress2", orderAddressService.findOrderAddressByUserId(p2.getUserId()));
						if (null!=p2.getReferrerId()) {
							p3 = (JifenUser)userService.findById(p2.getReferrerId()); //1级代理商
							if (null!=p3) {
								request.setAttribute("orderAddress3", orderAddressService.findOrderAddressByUserId(p3.getUserId()));
							}
						}
					}
					
				}
			}
			
		}
		request.setAttribute("p1", p1);
		request.setAttribute("p2", p2);
		request.setAttribute("p3", p3);*/
		
		//request.setAttribute("user", user);
		return "buy";
	}
	
	public void sendAddress(int flag,Integer referrerId) {
		if (flag <16) {
			if (null!=referrerId) {
				JifenUser p = userService.findById(referrerId);
				if (null!=p) {
					request.setAttribute("orderAddress"+flag, orderAddressService.findOrderAddressByUserId(p.getUserId()));
					sendAddress(flag++,p.getReferrerId());
				}
				request.setAttribute("p"+flag, p);
			}
		}
	}
	
	public String ajaxMSpay() {
		Usery usery = null;
		if (orderNo != null) {
			order=orderService.findByOrderBH(orderNo);
			System.out.println(orderNo+" -------------------------------");
			System.out.println(order);
			user = userService.findById(order.getUserId());
			usery = useryService.findbyUserId(order.getUserId());
			System.out.println("进来了"+orderNo);
		} else {
			message = "error";
			return "ajaxResult";
		}
		System.out.println(order);
		total_fee = (int)(order.getMoney()*100)+"";
		
		/*if (null!=user.getLoginName()&&!"".equals(user.getLoginName())) {
			if (user.getLoginName().equals("t01")) total_fee="1";
		}*/
			
		// 商户相关资料
		String appid = SystemMessage.getString("APPID");
		String appsecret = SystemMessage.getString("APPSECRET");
		String mch_id = SystemMessage.getString("MCH_ID");// 邮件里的MCHID
		String partnerkey = SystemMessage.getString("PARTNERKEY");// 在微信商户平台pay.weixin.com里自己生成的那个key

		// String openId = "oG7zVjqbwr8mBSJ9UcRAYzU_CWAc";//用oath授权得到的openid
		String openId = usery.getWxOpenid();
		// 获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
		String currTime = TenpayUtil.getCurrTime();
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		String strReq = strTime + strRandom;

		// 子商户号 非必输
		// String sub_mch_id="";
		// 设备号 非必输
		String device_info = "";
		// 随机数
		String nonce_str = strReq;
		// 商品描述
		// String body = describe;

		// 商品描述根据情况修改
		String body = order.getPname();
		// 商户订单号
		String out_trade_no = order.getOrdersBH();
		// int intMoney = Integer.parseInt(finalmoney);

		// 总金额以分为单位，不带小数点
		// int total_fee = intMoney;
		// 订单生成的机器 IP
		String spbill_create_ip = "127.0.0.1";
		// 订 单 生 成 时 间 非必输
		// String time_start ="";
		// 订单失效时间 非必输
		// String time_expire = "";
		// 商品标记 非必输
		// String goods_tag = "";

		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		String notify_url = SystemMessage.getString("ZHIFU_YUMING")+"/pay/miaoshaAction.action";

		String trade_type = "JSAPI";
		// 非必输
		// String product_id = "";
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		// packageParams.put("attach", attach);
		packageParams.put("out_trade_no", out_trade_no);

		// 这里写的金额为1 分到时修改
		packageParams.put("total_fee", total_fee);
		// packageParams.put("total_fee", "finalmoney");
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);

		packageParams.put("trade_type", trade_type);
		packageParams.put("openid", openId);

		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(appid, appsecret, partnerkey);

		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
				+ "</nonce_str>" + "<sign><![CDATA[" + sign + "]]></sign>"
				+ "<body><![CDATA[" + body + "]]></body>"
				+ "<out_trade_no>"
				+ out_trade_no
				+ "</out_trade_no>"
				+
				// 金额，这里写的1 分到时修改
				"<total_fee>"
				+ total_fee
				+ "</total_fee>"
				+
				// "<attach>"+attach+"</attach>"+
				"<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
				+ "<notify_url>" + notify_url + "</notify_url>"
				+ "<trade_type>" + trade_type + "</trade_type>" + "<openid>"
				+ openId + "</openid>" + "</xml>";
		String allParameters = "";
		try {
			allParameters = reqHandler.genPackage(packageParams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		Map<String, Object> dataMap2 = new HashMap<String, Object>();
		String prepay_id = "";
		try {
			prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);
			if (prepay_id.equals("")) {
				System.out.println("统一支付接口获取预支付订单出错");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SortedMap<String, String> finalpackage = new TreeMap<String, String>();

		timeStamp = Sha1Util.getTimeStamp();
		appId2 = appid;
		nonceStr2 = nonce_str;
		String prepay_id2 = "prepay_id=" + prepay_id;
		packages = prepay_id2;
		signType2 = "MD5";
		finalpackage.put("appId", appId2);
		finalpackage.put("timeStamp", timeStamp);
		finalpackage.put("nonceStr", nonceStr2);
		finalpackage.put("package", packages);
		finalpackage.put("signType", "MD5");
		String finalsign = reqHandler.createSign(finalpackage);
		paySign2 = finalsign;
		// return "\"appId\":\"" + appid2 + "\",\"timeStamp\":\"" + timeStamp
		// + "\",\"nonceStr\":\"" + nonceStr2 + "\",\"package\":\""
		// + packages + "\",\"signType\" : \"MD5" + "\",\"paySign\":\""
		// + finalsign + "\"";

		message = "success";
		return "ajaxResult";
	}
	
	/**
	 * 获取支付凭证
	 * @return
	 */
	public String ajaxWxPay() {
//		System.out.println("订单编号是1：" + orderNo);
//		orderVo=orderService.findByOrderVoBH(orderNo);
//		System.out.println(orderVo.getPname());
		Usery usery = null;
		if (orderNo != null) {
			order=orderService.findByOrderBH(orderNo);
			user = userService.findById(order.getUserId());
			usery = useryService.findbyUserId(order.getUserId());
			System.out.println("进来了"+orderNo);
		} else {
			message = "error";
			return "ajaxResult";
		}
		System.out.println(order);
		total_fee = (int)(order.getMoney()*100)+"";
		
		/*if (null!=user.getLoginName()&&!"".equals(user.getLoginName())) {
			if (user.getLoginName().equals("t01")) total_fee="1";
		}*/
			
		// 商户相关资料
		String appid = SystemMessage.getString("APPID");
		String appsecret = SystemMessage.getString("APPSECRET");
		String mch_id = SystemMessage.getString("MCH_ID");// 邮件里的MCHID
		String partnerkey = SystemMessage.getString("PARTNERKEY");// 在微信商户平台pay.weixin.com里自己生成的那个key

		// String openId = "oG7zVjqbwr8mBSJ9UcRAYzU_CWAc";//用oath授权得到的openid
		String openId = usery.getWxOpenid();
		// 获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
		String currTime = TenpayUtil.getCurrTime();
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		String strReq = strTime + strRandom;

		// 子商户号 非必输
		// String sub_mch_id="";
		// 设备号 非必输
		String device_info = "";
		// 随机数
		String nonce_str = strReq;
		// 商品描述
		// String body = describe;

		// 商品描述根据情况修改
		String body = order.getPname();
		// 商户订单号
		String out_trade_no = order.getOrdersBH();
		// int intMoney = Integer.parseInt(finalmoney);

		// 总金额以分为单位，不带小数点
		// int total_fee = intMoney;
		// 订单生成的机器 IP
		String spbill_create_ip = "127.0.0.1";
		// 订 单 生 成 时 间 非必输
		// String time_start ="";
		// 订单失效时间 非必输
		// String time_expire = "";
		// 商品标记 非必输
		// String goods_tag = "";

		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		String notify_url = SystemMessage.getString("ZHIFU_YUMING")+"/pay/payAction.action";

		String trade_type = "JSAPI";
		// 非必输
		// String product_id = "";
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		// packageParams.put("attach", attach);
		packageParams.put("out_trade_no", out_trade_no);

		// 这里写的金额为1 分到时修改
		packageParams.put("total_fee", total_fee);
		// packageParams.put("total_fee", "finalmoney");
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);

		packageParams.put("trade_type", trade_type);
		packageParams.put("openid", openId);

		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(appid, appsecret, partnerkey);

		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
				+ "</nonce_str>" + "<sign><![CDATA[" + sign + "]]></sign>"
				+ "<body><![CDATA[" + body + "]]></body>"
				+ "<out_trade_no>"
				+ out_trade_no
				+ "</out_trade_no>"
				+
				// 金额，这里写的1 分到时修改
				"<total_fee>"
				+ total_fee
				+ "</total_fee>"
				+
				// "<attach>"+attach+"</attach>"+
				"<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
				+ "<notify_url>" + notify_url + "</notify_url>"
				+ "<trade_type>" + trade_type + "</trade_type>" + "<openid>"
				+ openId + "</openid>" + "</xml>";
		String allParameters = "";
		try {
			allParameters = reqHandler.genPackage(packageParams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		Map<String, Object> dataMap2 = new HashMap<String, Object>();
		String prepay_id = "";
		try {
			prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);
			if (prepay_id.equals("")) {
				System.out.println("统一支付接口获取预支付订单出错");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SortedMap<String, String> finalpackage = new TreeMap<String, String>();

		timeStamp = Sha1Util.getTimeStamp();
		appId2 = appid;
		nonceStr2 = nonce_str;
		String prepay_id2 = "prepay_id=" + prepay_id;
		packages = prepay_id2;
		signType2 = "MD5";
		finalpackage.put("appId", appId2);
		finalpackage.put("timeStamp", timeStamp);
		finalpackage.put("nonceStr", nonceStr2);
		finalpackage.put("package", packages);
		finalpackage.put("signType", "MD5");
		String finalsign = reqHandler.createSign(finalpackage);
		paySign2 = finalsign;
		// return "\"appId\":\"" + appid2 + "\",\"timeStamp\":\"" + timeStamp
		// + "\",\"nonceStr\":\"" + nonceStr2 + "\",\"package\":\""
		// + packages + "\",\"signType\" : \"MD5" + "\",\"paySign\":\""
		// + finalsign + "\"";

		message = "success";
		return "ajaxResult";
	}
	
	public String liuAjaxPay(){
		OrderLiu orderVo= null;
		JifenUser user = null;
		Usery usery = null;
		if (orderNo != null) {
			System.out.println("订单编号是1：" + orderNo);
			orderVo = orderLiuService.findOrderByOrderBH(orderNo);
			if(orderVo != null){
			
				user = userService.findById(orderVo.getUserId());
				usery = useryService.findbyUserId(orderVo.getUserId());
			}else{
				message = "error";
				return "ajaxResult";
			}
		} else { 
			message = "error";
			return "ajaxResult";
		}
		System.out.println("查询结果："+orderVo);

			total_fee = (int)(orderVo.getMoney()*100)+"";
			Double bmoney = Double.parseDouble(total_fee);
			
			/*if (null!=user.getLoginName()&&!"".equals(user.getLoginName())) {
				if (user.getLoginName().equals("t01")) total_fee="1";
			}*/
			//验证价格与购买流量是否正确
			String busType = null;//运营商
			Integer num = Integer.parseInt(orderVo.getPname().substring(6,orderVo.getPname().length()-1));//购买流量数
			
			if(orderVo.getPname().contains("电信")){
				busType = "3";
			}else if(orderVo.getPname().contains("移动")){
				busType = "1";
			}else if(orderVo.getPname().contains("联通")){
				busType = "2";
			}else{
				return "error";
			}
			String isG = orderVo.getPname().substring(orderVo.getPname().length()-1, orderVo.getPname().length());
			if(isG.equals("G")){
				num = num*1024;
			}
			//根据流量运营商，购买流量数据，流量价值，判断数据库中是否存在，0 不存在，返回 ，大于1，数据异常返回，等于1 继续执行
			List list = orderLiuService.checkLiuDateIsTure(busType, num.toString(), orderVo.getMoney().toString());
			if(list.size() == 0 ){
				return "error";
			}else if(list.size() > 1){
				return "error";
			}   
			
			
	
		// 商户相关资料
		String appid = SystemMessage.getString("APPID");
		String appsecret = SystemMessage.getString("APPSECRET");
		String mch_id = SystemMessage.getString("MCH_ID");// 邮件里的MCHID
		String partnerkey = SystemMessage.getString("PARTNERKEY");// 在微信商户平台pay.weixin.com里自己生成的那个key

		
		String openId = usery.getWxOpenid();
		// 获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
		String currTime = TenpayUtil.getCurrTime();
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		String strReq = strTime + strRandom;

		
		// 设备号 非必输
		String device_info = "";
		// 随机数
		String nonce_str = strReq;
		// 商品描述
		// String body = describe;

		// 商品描述根据情况修改
		String body = orderVo.getPname();
		// 商户订单号
		String out_trade_no = orderVo.getOrdersBH();
	

		// 总金额以分为单位，不带小数点
		// int total_fee = intMoney;
		// 订单生成的机器 IP
		String spbill_create_ip = "127.0.0.1";
		

		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		String notify_url = SystemMessage.getString("ZHIFU_YUMING")+"/pay/payAction.action";
	
		String trade_type = "JSAPI";
		// 非必输
		// String product_id = "";
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		// packageParams.put("attach", attach);
		packageParams.put("out_trade_no", out_trade_no);

		// 这里写的金额为1 分到时修改
		packageParams.put("total_fee", total_fee);
		// packageParams.put("total_fee", "finalmoney");
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);

		packageParams.put("trade_type", trade_type);
		packageParams.put("openid", openId);

		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(appid, appsecret, partnerkey);

		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
				+ "</nonce_str>" + "<sign><![CDATA[" + sign + "]]></sign>"
				+ "<body><![CDATA[" + body + "]]></body>"
				+ "<out_trade_no>"
				+ out_trade_no
				+ "</out_trade_no>"
				+
				// 金额，这里写的1 分到时修改
				"<total_fee>"
				+ total_fee
				+ "</total_fee>"
				+
			
				"<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
				+ "<notify_url>" + notify_url + "</notify_url>"
				+ "<trade_type>" + trade_type + "</trade_type>" + "<openid>"
				+ openId + "</openid>" + "</xml>";
		String allParameters = "";
		try {
			allParameters = reqHandler.genPackage(packageParams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		Map<String, Object> dataMap2 = new HashMap<String, Object>();
		String prepay_id = "";
		try {
			prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);
			if (prepay_id.equals("")) {
				System.out.println("统一支付接口获取预支付订单出错");
			}
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		SortedMap<String, String> finalpackage = new TreeMap<String, String>();

		timeStamp = Sha1Util.getTimeStamp();
		appId2 = appid;
		nonceStr2 = nonce_str;
		String prepay_id2 = "prepay_id=" + prepay_id;
		packages = prepay_id2;
		signType2 = "MD5";
		finalpackage.put("appId", appId2);
		finalpackage.put("timeStamp", timeStamp);
		finalpackage.put("nonceStr", nonceStr2);
		finalpackage.put("package", packages);
		finalpackage.put("signType", "MD5");
		String finalsign = reqHandler.createSign(finalpackage);
		paySign2 = finalsign;
		

		message = "success";
		return "ajaxResult";
	
	}
	
	/**
	 * 
	 * 微信二维码
	 * 
	 * @return
	 */
	public String qrcodePage() {
		user = getSessionUser();
//		user = userService.findById(7291);
		if(user == null){
			return "timeOut";
		}
		String nginxUrl = SystemMessage.getString("imgUrl");
		Usery usery = useryService.findbyUserId(user.getUserId());
//		if(user.getLevel()!=null&& user.getLevel()>0){
			Date date = new Date();
			if(null == usery.getQrCode() || "".equals(usery.getQrCode()) || null == usery.getTicket() || "".equals(usery.getTicket())||(usery.getQrCodeDate()!=null&&date.after(usery.getQrCodeDate()))){
				try {
					request.setAttribute("qrCode", nginxUrl+userService.createQrCode(user));
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				request.setAttribute("qrCode", nginxUrl+usery.getQrCode());
			}
//		}
		return "qrcodePage";

	}
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String testT(){
		JSONArray ja=new JSONArray();
		JSONObject jb=new JSONObject();
		
		return null;
	} 

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHyid() {

		return hyid;
	}

	public void setHyid(String hyid) {

		this.hyid = hyid;
	}

	public String getGoodId() {

		return goodId;
	}

	public void setGoodId(String goodId) {

		this.goodId = goodId;
	}

	public String getSubRemark() {

		return subRemark;
	}

	public void setSubRemark(String subRemark) {

		this.subRemark = subRemark;
	}

	public List gettSubscibeList() {
						    	    	
		return tSubscibeList;
	}

	public void settSubscibeList(List tSubscibeList) {

		this.tSubscibeList = tSubscibeList;
	}

	public String getDdzt() {

		return ddzt;
	}

	public void setDdzt(String ddzt) {

		this.ddzt = ddzt;
	}

	public String getImgPath() {

		return imgPath;
	}

	public void setImgPath(String imgPath) {

		this.imgPath = imgPath;
	}

	public String getFavid() {

		return favid;
	}

	public void setFavid(String favid) {

		this.favid = favid;
	}

	public JifenUser getUser() {
		return user;
	}

	public void setUser(JifenUser user) {
		this.user = user;
	}

	public String getRcode() {
		return rcode;
	}

	public void setRcode(String rcode) {
		this.rcode = rcode;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getWxOpenid() {
		return wxOpenid;
	}

	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Double getAllScore() {
		return allScore;
	}

	public void setAllScore(Double allScore) {
		this.allScore = allScore;
	}

	public Double getCurrScore() {
		return currScore;
	}

	public void setCurrScore(Double currScore) {
		this.currScore = currScore;
	}

	public String getCenterLoginName() {
		return centerLoginName;
	}

	public void setCenterLoginName(String centerLoginName) {
		this.centerLoginName = centerLoginName;
	}

	public String getReferrerLoginName() {
		return referrerLoginName;
	}

	public void setReferrerLoginName(String referrerLoginName) {
		this.referrerLoginName = referrerLoginName;
	}

	public String getTjrLoginName() {
		return tjrLoginName;
	}

	public void setTjrLoginName(String tjrLoginName) {
		this.tjrLoginName = tjrLoginName;
	}

	public String getAppId2() {
		return appId2;
	}

	public void setAppId2(String appId2) {
		this.appId2 = appId2;
	}

	public String getNonceStr2() {
		return nonceStr2;
	}

	public void setNonceStr2(String nonceStr2) {
		this.nonceStr2 = nonceStr2;
	}

	public String getPackages() {
		return packages;
	}

	public void setPackages(String packages) {
		this.packages = packages;
	}

	public String getSignType2() {
		return signType2;
	}

	public void setSignType2(String signType2) {
		this.signType2 = signType2;
	}

	public String getPaySign2() {
		return paySign2;
	}

	public void setPaySign2(String paySign2) {
		this.paySign2 = paySign2;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	
	public String getViewLevel() {
		return viewLevel;
	}


	public void setViewLevel(String viewLevel) {
		this.viewLevel = viewLevel;
	}


	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCkBindWx() {
		return ckBindWx;
	}

	public void setCkBindWx(String ckBindWx) {
		this.ckBindWx = ckBindWx;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public String getTempName() {
		return tempName;
	}
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}


	public String getSearchValue() {
		return searchValue;
	}


	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}


	public JifenUserService getUserService() {
		return userService;
	}

	public void setUserService(JifenUserService userService) {
		this.userService = userService;
	}

	public FhrecordService getFhrecordService() {
		return fhrecordService;
	}

	public void setFhrecordService(FhrecordService fhrecordService) {
		this.fhrecordService = fhrecordService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public OrderAddressService getOrderAddressService() {
		return orderAddressService;
	}

	public void setOrderAddressService(OrderAddressService orderAddressService) {
		this.orderAddressService = orderAddressService;
	}

	public JiFenRecordService getJfrecordService() {
		return jfrecordService;
	}

	public void setJfrecordService(JiFenRecordService jfrecordService) {
		this.jfrecordService = jfrecordService;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZjlx() {
		return zjlx;
	}

	public void setZjlx(String zjlx) {
		this.zjlx = zjlx;
	}

	public String getZjhm() {
		return zjhm;
	}

	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getFxts() {
		return fxts;
	}

	public void setFxts(String fxts) {
		this.fxts = fxts;
	}

	public String getXy1() {
		return xy1;
	}

	public void setXy1(String xy1) {
		this.xy1 = xy1;
	}

	public String getXy2() {
		return xy2;
	}

	public void setXy2(String xy2) {
		this.xy2 = xy2;
	}

	public String getXy3() {
		return xy3;
	}

	public void setXy3(String xy3) {
		this.xy3 = xy3;
	}

	public String getXy4() {
		return xy4;
	}

	public void setXy4(String xy4) {
		this.xy4 = xy4;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getXy5() {
		return xy5;
	}

	public void setXy5(String xy5) {
		this.xy5 = xy5;
	}

	public String getSfzzm() {
		return sfzzm;
	}

	public void setSfzzm(String sfzzm) {
		this.sfzzm = sfzzm;
	}

	public String getSfzfm() {
		return sfzfm;
	}

	public void setSfzfm(String sfzfm) {
		this.sfzfm = sfzfm;
	}

	public String getScsfz() {
		return scsfz;
	}

	public void setScsfz(String scsfz) {
		this.scsfz = scsfz;
	}

	public String getYhkzm() {
		return yhkzm;
	}

	public void setYhkzm(String yhkzm) {
		this.yhkzm = yhkzm;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Integer getFlagCount() {
		return flagCount;
	}

	public void setFlagCount(Integer flagCount) {
		this.flagCount = flagCount;
	}

	public Integer getUserId2() {
		return userId2;
	}

	public void setUserId2(Integer userId2) {
		this.userId2 = userId2;
	}

	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public String getTextMessage() {
		return textMessage;
	}

	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}

	public String getToWxOpenId() {
		return toWxOpenId;
	}

	public void setToWxOpenId(String toWxOpenId) {
		this.toWxOpenId = toWxOpenId;
	}

	public  static void main(String[] args) throws InterruptedException {
		/*for (int i=0;i<5;i++) {
			Thread thread = Thread.currentThread();
			fahb();
		}*/
		
//		 Calendar cal = Calendar. getInstance ();
//         cal.set(Calendar.HOUR , Calendar.HOUR -1 ) ;
//         String oneHoursAgoTime =  new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ).format(cal.getTime());//获取到完整的时间
//         System.out.println(oneHoursAgoTime);
		
		
	}
	
	public static void fahb() {
		String userId = "1483";
		String billNo = HongBaoUtil.createBillNo(userId);
		
		//String openid = "ozLHAvk3hxJF74QxyYMlQNnH-218"; //1482
		
		String openid = "ozLHAvvNJqjIBUzI2kQhHQmUP2qg";
		
		int amount = 100;
		SortedMap<String, String> map = HongBaoUtil.createMap(billNo, openid, userId,amount);  
		HongBaoUtil.sign(map);  
		String requestXML = HongBaoUtil.getRequestXml(map);  
		   
		try {
			
			 FileInputStream instream = new FileInputStream(new File("C:/yifei.p12"));
			 //HttpServletRequest request = ServletActionContext.getRequest();
			//与微信交互并接收返回参数  
			String responseXML = HongBaoUtil.post(requestXML,instream);
			
			//String xml = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[发放成功]]></return_msg><result_code><![CDATA[SUCCESS]]></result_code><mch_billno><![CDATA[2244622915332845190221234455]]></mch_billno><mch_id>1254095301</mch_id><wxappid><![CDATA[wx323a326f5840035b]]></wxappid><re_openid><![CDATA[ozLHAvk3hxJF74QxyYMlQNnH-218]]></re_openid><total_amount>100</total_amount><send_listid><![CDATA[10000000002015080637311171896]]></send_listid><send_time><![CDATA[20150806172532]]></send_time></xml>";
			//try {
				Document document = DocumentHelper.parseText(responseXML);
				Element root = document.getRootElement();
				 List<Element> elements = root.elements();
				 Element element1 = elements.get(0);
				 Element element2 = elements.get(1);
				System.out.println(element1.getTextTrim());
				System.out.println(element2.getTextTrim());
				
				Thread thread = Thread.currentThread();
				try {
					thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 /* for(Iterator<Element> it = elements.iterator();it.hasNext();){
				   Element element = it.next();
				   System.out.println(element.getName()+" : "+element.getTextTrim());
				  }
*/
				
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (KeyManagementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnrecoverableKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CertificateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (KeyStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	private JifenUser getSessionUser(){
		
		 user = (JifenUser) request.getSession().getAttribute("loginUser");
		 if(user==null){
			 if(!StringUtils.isNotEmpty(code)){
					try {
						code = request.getParameter("code");
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
				if(!StringUtils.isNotEmpty(wxOpenid)){
					 wxOpenid=(String) request.getSession().getAttribute("wxOpenid");
				}
			  if(code!=null){
				  if(wxOpenid==null){
					  System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> code333------ "+code);
					  wxOpenid = WeixinUtil.code2openid(code);
					  request.getSession().setAttribute("wxOpenid",wxOpenid);
				  }
			  }
			 if(wxOpenid!=null){
				 Usery usery = useryService.findbyWxOpenId(wxOpenid);
				 if(usery!=null){
					 user = useryService.findJifenUserByUsery(usery);
					 if (user!=null) {
						 request.getSession().setAttribute("loginUser",user);
					 }
				}
			 }
		 }
		 if(user!=null&&!"".equals(user.getAppCenterId())&& null != user.getAppCenterId() && user.getAppCenterId().equals(2)){
			 return null;
		 }
		 
			 //user = userService.findById(7291);
		
		// user =userService.findById(2341);
		return  user;
	} 
	
}
