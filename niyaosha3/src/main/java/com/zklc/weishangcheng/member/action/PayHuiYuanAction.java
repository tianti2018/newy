package com.zklc.weishangcheng.member.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletInputStream;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.utils.GetWxOrderno;
import com.utils.RequestHandler;
import com.utils.Sha1Util;
import com.utils.TenpayUtil;
import com.zklc.framework.action.BaseAction;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderAddress;
import com.zklc.weishangcheng.member.hibernate.persistent.Orders;
import com.zklc.weishangcheng.member.hibernate.persistent.Product;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.service.GoodYongJinService;
import com.zklc.weishangcheng.member.service.OrdersService;
import com.zklc.weishangcheng.member.service.OrderAddressService;
import com.zklc.weishangcheng.member.service.ProductService;
import com.zklc.weishangcheng.member.service.UserService;
import com.zklc.weishangcheng.member.service.UseryService;
import com.zklc.weishangcheng.member.service.WeixinAutosendmsgService;
import com.zklc.weishangcheng.member.util.PublicUtil;
import com.zklc.weixin.util.SystemMessage;

import net.sf.json.JSONObject;

@SuppressWarnings("serial")
@ParentPackage("json")
@Namespace("/pay")
@Action(value = "payHuiYuanAction")
@Results({
	@Result(name = "listHongbao", location = "/WEB-INF/jsp/phone_dianzhu_hongbao.jsp"),
	@Result(name = "listDianHongBaos", location = "/WEB-INF/jsp/list_dian_hongbao.jsp"),
	@Result(name = "toGoodBuy", location = "/WEB-INF/jsp/goodBuy.jsp"),
	@Result(name = "toHuiYuanBuy", location = "/WEB-INF/jsp/huiyuan/huiyuanBuy.jsp"),
	@Result(name = "toMianMoBuy", location = "/WEB-INF/jsp/mianMoBuy.jsp"),
	@Result(name = "toMiaoShaHou", location = "/WEB-INF/jsp/miaosha/miaosha_houpiao.jsp"),
	@Result(name = "product", location = "/WEB-INF/jsp/product.jsp"),
	@Result(name = "ajaxResult", type = "json", params = { "message",
	"message" }),
	@Result(name = "products", location = "/WEB-INF/jsp/products.jsp"),
	
})
public class PayHuiYuanAction extends BaseAction {

	@Autowired
	private UseryService useryService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrdersService orderService;
	@Autowired
	private GoodYongJinService yongJinService;
	@Autowired
	private OrderAddressService orderAddressService;
	@Autowired
	private WeixinAutosendmsgService autosendmsgService;
	@Autowired
	private ProductService productService;
	
	private OrderAddress orderAddress;
	private Orders order;
	private Users user;
	
	private String ordersBH;
	public String code;
	public String wxOpenid;
	public String orderNo;
	private String qty_item_1;//购买的商品数量
	private String total_price1;
	public String message;// 回显信息
	
	private Integer userId2;
	private Integer level;
	private Integer amount;
	private Integer flagCount;
	private Integer xinghuoquan;
	public String appId2;
	public String timeStamp;
	public String nonceStr2;
	public String packages;
	public String signType2;
	public String paySign2;
	public String total_fee;// 支付金额
	public Integer type;//上平类型
	private Integer orderAddRessId;
	
	private Integer qi;
	private Integer prodId;//购买产品id
	
	public String toHuiYuanBuy(){
		userVo = getSessionUser();
		user = userVo.getUser();
		if(user==null){
			return "timeOut";
		}
		user = userService.findById(user.getUserId());
//		user = userService.findById(1883);
//		request.getSession().setAttribute("loginUser",user);
		
		Product prod=productService.findById(prodId);
		List<Product> prodList=productService.findByProperty("prodType", prod.getProdType());
		request.setAttribute("prod", prod);
		request.setAttribute("typelist", prodList);
		request.setAttribute("typeqty", prodList.size());
		request.setAttribute("orderAddress", orderAddressService.findOrderAddressByUserId(user.getUserId()));
		
		return "toHuiYuanBuy";
	}
	
	public void savehuiyuanOrder(){
		JSONObject json = new JSONObject();
		response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");	
		userVo = getSessionUser();
		user = userVo.getUser();
		Product prod=productService.findById(prodId);
		Integer size =1;
		
		if(user == null){
			json.put("success", false);
			json.put("timeOut", true);
		}else {
			user = userService.findById(user.getUserId());
//			if(user.getLevel()!=null&&user.getLevel()==3){
//				System.out.println("等级为vip已经无购买资格");
//				json.put("success", false);
//			}else{
			// 订单编号
				orderNo = "hy" + PublicUtil.getOrderNo();
				// 创建订单
				if (order == null)
				{
					order = new Orders();
				}
				
				OrderAddress orderAddress = orderAddressService.findById(orderAddRessId);
				order.setMoney(prod.getPrice()*size+prod.getTransFee());
				order.setOrdersBH(orderNo);
				order.setPname(prod.getProdName());
				order.setToUserName(orderAddress.getUserName());
				order.setMobile(orderAddress.getMobile());
				order.setZipcode(orderAddress.getZipcode());
				order.setCreateDate(new Date());
				order.setUserId(user.getUserId());
				order.setType(prod.getManufacturer());
				order.setAddress(orderAddress.getAddress());
				order.setProductId(prodId);
//					order.setLevelValue(levelValue);
				Boolean boolean1=orderService.saveHuiYuanOrder(order,user,prod);
				json.put("success", boolean1);
				if(boolean1)
					json.put("ordersBh", orderNo);
			}
//		}
		try {
			response.getWriter().print(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 订单支付成功通知
	 */
	public String execute() {
		String openid = null;
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));
	        String line = null;
	        StringBuilder sb = new StringBuilder();
	        while((line = br.readLine())!=null){
	            sb.append(line);
	        }
        Map map = null;
		try {
			map = GetWxOrderno.doXMLParse(sb.toString());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		String return_code = (String) map.get("return_code");
	    openid = (String) map.get("openid");
		String out_trade_no = (String) map.get("out_trade_no");
		String total_fee = (String) map.get("total_fee");
		
	  	PrintWriter out = response.getWriter();
	  	String returnStr="FAIL";
	  	
		out.println(return_code);
	  	out.flush();
			//支付成功
			if(null!=return_code&&return_code.equals("SUCCESS")){
				order = orderService.findOrderByOrderBH(out_trade_no);
				
				if(null!=order){
					if(order.getOrderStatus()==0){
						System.out.println("order.getOrderStatus() "+order.getOrderStatus());
						returnStr="SUCCESS";
							String content="您好:\n您的订单已支付成功！\n"+"订单编号："+order.getOrdersBH()+"\n"+"支付金额："+order.getMoney()+"元,购买产品!";
							orderService.huiyuanPay(order,user);
							autosendmsgService.sendMsg(openid, content);
					}
					
				}
			}
		//}
		System.out.println(new Date().toLocaleString()+",订单支付返回结果是："+returnStr);
		out.close();
	   } 
		catch (IOException e1) {
			e1.printStackTrace();
		}
		
    	return null;
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
			order=orderService.findOrderByOrderBH(orderNo);
			user = userService.findById(order.getUserId());
			usery = useryService.findbyUserId(order.getUserId());
			System.out.println("进来了"+orderNo);
		} else {
			message = "error";
			return "ajaxResult";
		}
		System.out.println(order);
		total_fee = (int)(order.getMoney()*100)+"";
		
//		if (null!=user.getLoginName()&&!"".equals(user.getLoginName())) {
//			if (user.getLoginName().equals("t01")) total_fee="1";
//		}
			
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
		String notify_url = SystemMessage.getString("ZHIFU_YUMING")+"/pay/payHuiYuanAction!execute.action";

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

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getOrdersBH() {
		return ordersBH;
	}

	public void setOrdersBH(String ordersBH) {
		this.ordersBH = ordersBH;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getWxOpenid() {
		return wxOpenid;
	}

	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getQty_item_1() {
		return qty_item_1;
	}

	public void setQty_item_1(String qty_item_1) {
		this.qty_item_1 = qty_item_1;
	}

	public String getTotal_price1() {
		return total_price1;
	}

	public void setTotal_price1(String total_price1) {
		this.total_price1 = total_price1;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getUserId2() {
		return userId2;
	}

	public void setUserId2(Integer userId2) {
		this.userId2 = userId2;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getFlagCount() {
		return flagCount;
	}

	public void setFlagCount(Integer flagCount) {
		this.flagCount = flagCount;
	}

	public String getAppId2() {
		return appId2;
	}

	public void setAppId2(String appId2) {
		this.appId2 = appId2;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
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

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public OrderAddress getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(OrderAddress orderAddress) {
		this.orderAddress = orderAddress;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getOrderAddRessId() {
		return orderAddRessId;
	}

	public void setOrderAddRessId(Integer orderAddRessId) {
		this.orderAddRessId = orderAddRessId;
	}

	public Integer getQi() {
		return qi;
	}

	public void setQi(Integer qi) {
		this.qi = qi;
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public Integer getXinghuoquan() {
		return xinghuoquan;
	}

	public void setXinghuoquan(Integer xinghuoquan) {
		this.xinghuoquan = xinghuoquan;
	}

}
