package com.zklc.weishangcheng.member.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
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
import com.zklc.weishangcheng.member.hibernate.persistent.DianForUser;
import com.zklc.weishangcheng.member.hibernate.persistent.FhRecordDian;
import com.zklc.weishangcheng.member.hibernate.persistent.JifenUser;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderAddress;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderDian;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.PriceForDianZhuLevel;
import com.zklc.weishangcheng.member.service.DianForUserService;
import com.zklc.weishangcheng.member.service.FhRecordDianService;
import com.zklc.weishangcheng.member.service.FhrecordService;
import com.zklc.weishangcheng.member.service.JifenUserService;
import com.zklc.weishangcheng.member.service.OrderAddressService;
import com.zklc.weishangcheng.member.service.OrderDianService;
import com.zklc.weishangcheng.member.service.UseryService;
import com.zklc.weishangcheng.member.service.WeixinAutosendmsgService;
import com.zklc.weishangcheng.member.util.PublicUtil;
import com.zklc.weixin.util.SystemMessage;
import com.zklc.weixin.util.WeixinUtil;

@SuppressWarnings("serial")
@ParentPackage("json")
@Namespace("/pay")
@Action(value = "payDianAction")
@Results({
	@Result(name = "listHongbao", location = "/WEB-INF/jsp/phone_dianzhu_hongbao.jsp"),
	@Result(name = "listDianHongBaos", location = "/WEB-INF/jsp/list_dian_hongbao.jsp"),
	@Result(name = "listDianbufaHongBaos", location = "/WEB-INF/jsp/list_dian_bufahongbao.jsp"),
	@Result(name = "dianzhuBuy", location = "/WEB-INF/jsp/Dianbuynew.jsp"),
	@Result(name = "product", location = "/WEB-INF/jsp/product.jsp"),
	@Result(name = "meiyouzige", location = "/WEB-INF/jsp/meiyouzige.jsp"),
	
	@Result(name = "ajaxResult", type = "json", params = { "message",
	"message" }),
	@Result(name = "products", location = "/WEB-INF/jsp/products.jsp"),
	
})
public class PayDianAction extends BaseAction {
	@Autowired
	private JifenUserService userService;
	@Autowired
	private WeixinAutosendmsgService autosendmsgService;
	@Autowired
	private UseryService useryService;
	@Autowired
	private OrderDianService orderService;
	@Autowired
	private OrderAddressService orderAddressService;
	@Autowired
	private FhRecordDianService fhDianService;
	@Autowired
	private FhrecordService fhrecordService;
	@Autowired
	private DianForUserService dianForUserService;
	
	private OrderDian order;
	private OrderAddress orderAddress;
	private JifenUser user;
	
	private String ordersBH;
	public String code;
	public String wxOpenid;
	public String orderNo;
	private String qty_item_1;
	private String total_price1;
	private Integer orderAddRessId;
	private Integer levelValue;
	private Integer levelOne;
	public String message;// 回显信息
	
	private Integer userId2;
	private Integer level;
	private Integer amount;
	private Integer flagCount;
	
	public String appId2;
	public String timeStamp;
	public String nonceStr2;
	public String packages;
	public String signType2;
	public String paySign2;
	public String total_fee;// 支付金额
	
	public void zigeChaXun(){
		JSONObject json = new JSONObject();
		json.put("success", false);
		user = getSessionUser();
		if(user==null){
			json.put("timeOut", true);
		}else{
			Double sumHongbao = fhrecordService.findTotalJiFenOneByUserId(user.getUserId(), "2","1,2");
			if(sumHongbao>=2700.0){
				json.put("success", true);
			}
		}
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		String notify_url = SystemMessage.getString("ZHIFU_YUMING")+"/pay/payDianAction!execute.action";

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
	
	@SuppressWarnings("rawtypes")
	public String listBuDianHongBaos(){
		
		user = getSessionUser();
		if(user==null){
			return "timeOut";
		}
		String sql = "SELECT s.headUrl,s.userName,f.userId,s.appDate,s.level userLevel,s.cardId,f.fhmoney,f.flag,f.fromUserId,f.dianzhuLevel,f.level starLevel,f.memo FROM users s JOIN fhrecord_dian f ON s.userId = f.fromUserId AND f.ordersId IN(SELECT o.ordersId FROM order_dian o WHERE o.orderStatus >0 and o.createDate <'2016-01-28 00:01:00') AND f.flag = 4 and f.userId="+user.getUserId();
		List fhRecordDians = fhDianService.findBySql(sql, null);
		List fhList = new ArrayList();
		if(fhRecordDians.size()>0){
			for(Object o:fhRecordDians){
				Object[] os = (Object[])o; 
				Integer fromUserId = (Integer) os[8];
				DianForUser du = dianForUserService.findByUserId(fromUserId);
				if(du==null){
					du = new DianForUser();
					du.setUserId(fromUserId);
					dianForUserService.save(du);
				}
				if(os[9].equals(1)&&du.getDian1()==null){
					fhList.add(o);
				}
				if(os[9].equals(2)&&du.getDian2()==null){
					fhList.add(o);
				}
				if(os[9].equals(3)){
					if(du.getDian31()==null&&os[6].equals(200)){
						fhList.add(o);
					}
					if(du.getDian32()==null&&!os[6].equals(200)){
						fhList.add(o);
					}
				}
				if(os[9].equals(4)){
					if(du.getDian41()==null&&os[6].equals(200)){
						fhList.add(o);
					}
					if(du.getDian42()==null&&!os[6].equals(200)){
						fhList.add(o);
					}
				}
				if(os[9].equals(5)&&du.getDian5()==null){
					fhList.add(o);
				}
				if(os[9].equals(6)&&du.getDian6()==null){
					fhList.add(o);
				}
				if(os[9].equals(7)&&du.getDian7()==null){
					fhList.add(o);
				}
				if(os[9].equals(8)&&du.getDian8()==null){
					fhList.add(o);
				}
				if(os[9].equals(9)){
					if(du.getDian91()==null&&os[6].equals(200)){
						fhList.add(o);
					}
					if(du.getDian92()==null&&!os[6].equals(200)){
						fhList.add(o);
					}
				}
			}
		}
		request.setAttribute("fhRecordDians", fhList);
		return "listDianbufaHongBaos";
	}
	
	public void bufahongbao(){
		HttpServletResponse response = ServletActionContext.getResponse();
		String message = "";
		user = getSessionUser();
		if(user == null){
			message="当前用户不存在";
		}
		user = userService.findById(user.getUserId());
		Usery usery = useryService.findbyUserId(user.getUserId());
		if (null!=user) {
			if(user.getCardId()!=null){
				//看他的级别已经审核了几个了 审核三个就必须升级 目前最高级别为三级
				if (user.getCardId()!=9) {
					int shCount = fhDianService.findCountFhRecordByLevel(user.getUserId(), user.getCardId());
					if (shCount>=3) {
						message = "您得审核次数已经用完,请申请更高店主!";
					}
				}
				
				if (message=="") {
					if(user.getCardId()>=level)
						message = fhDianService.bufahongbao(usery.getWxOpenid(),user.getUserId(),userId2,level,amount,flagCount);
					else {
						message = "您的数据出现错误,请联系管理员进行更改!";
					}
				}
			}else {
				message = "您未成为店主或者已退款无法领取店主红包!";
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
	
	@SuppressWarnings("rawtypes")
	public String listDianHongBaos(){
		
		user = getSessionUser();
		if(user==null){
			return "timeOut";
		}
		String sql = "SELECT s.headUrl,s.userName,f.userId,s.appDate,s.level userLevel,s.cardId,f.fhmoney,f.flag,f.fromUserId,f.dianzhuLevel,f.level starLevel,f.memo FROM users s JOIN fhrecord_dian f ON s.userId = f.fromUserId AND f.flag = 1 and f.userId="+user.getUserId();
		List fhRecordDians = fhDianService.findBySql(sql, null);
		request.setAttribute("fhRecordDians", fhRecordDians);
		return "listDianHongBaos";
	}
	
	public void listDianHBs(){
		user = getSessionUser();
//		user = userService.findById(1821);
		if(user!=null){
			DecimalFormat   fnum   =   new   DecimalFormat("##0.00"); //四舍五入，保留两位小数 
			Double totalMoney = fhDianService.findTotalJiFenOneByUserId(user.getUserId(), null,"1,2");//总佣金
			Double ytxMoney = fhDianService.findTotalJiFenOneByUserId(user.getUserId(), null,"2");//已领取佣金
			Double ktxMoney = totalMoney-ytxMoney;//未领取佣金,
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("totalMoney", fnum.format(totalMoney));
			map.put("ytxMoney", fnum.format(ytxMoney));
			map.put("ktxMoney", fnum.format(ktxMoney));
			JSONObject json = JSONObject.fromObject(map);
			try {
				ServletActionContext.getResponse().getWriter().print(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String dianzhuBuy(){
		
		user = getSessionUser();
		if(user==null){
			return "timeOut";
		}
		user = userService.findById(user.getUserId());
//		user = userService.findById(424090);
		request.getSession().setAttribute("loginUser",user);
		Double sumHongbao = fhrecordService.findTotalJiFenOneByUserId(user.getUserId(), "2","1,2");
		if(sumHongbao<2700.0){
			return "meiyouzige";
		}
		request.setAttribute("orderAddress", orderAddressService.findOrderAddressByUserId(user.getUserId()));
		return "dianzhuBuy";
	}
	
	public void saveOrderDian() {

		JSONObject json = new JSONObject();
		response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");	
		boolean cunchu = false;
		user = getSessionUser();
//		user = userService.findById(1820);
		if(user == null){
			json.put("success", false);
			json.put("timeOut", true);
		}else {
			user = userService.findById(user.getUserId());
			if(user.getLevel().equals(3)){
			// 订单编号
				orderNo = "66" + PublicUtil.getOrderNo();
				// 创建订单
				if (order == null)
					order = new OrderDian();
				
				/*if(orderAddress!= null){
					if(orderAddress.getId()==null){
						orderAddress.setIsFirst("1");
						orderAddress.setUserId(user.getUserId());
						orderAddressService.save(orderAddress);
					}else {
						OrderAddress oldAddress = orderAddressService.findById(orderAddress.getId());
						oldAddress.setAddress(orderAddress.getAddress());
						oldAddress.setUserName(orderAddress.getUserName());
						oldAddress.setMobile(orderAddress.getMobile());
						oldAddress.setZipcode(orderAddress.getZipcode());
						orderAddressService.update(oldAddress);
					}
				}*/
				 orderAddress = orderAddressService.findById(orderAddress.getId());
				
				System.out.println(">>>>>>>>>>>>>>> total_price "+total_price1);
				
				if(null!=total_price1 && !"".equals(total_price1)){
					StringUtils.replace(total_price1, "￥", "");
					if(!"".equals(StringUtils.replace(total_price1, "￥", ""))){
						Double priceDouble = 0.0;
						Double price1 = Double.parseDouble(StringUtils.replace(total_price1, "￥", ""));
						if(levelValue!=null&&(user.getCardId()==null?0:user.getCardId())<levelValue){
							for(int i=(user.getCardId()==null?0:user.getCardId());i<levelValue;i++){
								priceDouble+=PriceForDianZhuLevel.price[i];
							}
							order.setMoney(priceDouble);
							order.setLevel(levelValue);
						}else{
							json.put("success", false);
							json.put("message", "您也是该店主!无法重复购买!");
							cunchu = true;
						}
						if(!price1.equals(priceDouble)){
							json.put("success", false);
							json.put("message", "参数错误,请关闭网页重新进入!");
							cunchu = true;
						}
						if(!cunchu){
							order.setOrdersBH(orderNo);
							order.setPname(SystemMessage.getString("company"));
			//				order.setSize(qty_item_1);//这里记录运费
							order.setToUserName(orderAddress.getUserName());
							order.setMobile(orderAddress.getMobile());
							order.setZipcode(orderAddress.getZipcode());
							order.setOrderStatus(0);// 待支付
							order.setCreateDate(new Date());
							order.setUserId(user.getUserId());
							order.setAddress(orderAddress.getAddress());
		//					order.setLevelValue(levelValue);
							orderService.saveAndCFh(order,user);
							json.put("success", true);
							json.put("ordersBh", orderNo);
						}
				
					}else{
						json.put("success", false);
						json.put("error", true);
					}
				}
			}else{
				json.put("success", false);
				json.put("dengji", true);
			}
		}
		try {
			response.getWriter().print(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	
	}
	
	@SuppressWarnings("unchecked")
	public String listHongbao(){
		user = getSessionUser();
		if(levelOne!=null){
			String hql = "from FhRecordDian fh where flag = 1 and userId = "+user.getUserId();
			List<FhRecordDian> fhDians = fhDianService.findByHql(hql, null);
			request.setAttribute("fhDians", fhDians);
		}
		
		return "listHongbao";
	}
	
	public void fahongbao(){
		HttpServletResponse response = ServletActionContext.getResponse();
		String message = "";
		user = getSessionUser();
		if(user == null){
			message="当前用户不存在";
		}
		user = userService.findById(user.getUserId());
		Usery usery = useryService.findbyUserId(user.getUserId());
		if (null!=user) {
			if(user.getCardId()!=null){
				//看他的级别已经审核了几个了 审核三个就必须升级 目前最高级别为三级
				if (user.getCardId()!=9) {
					int shCount = fhDianService.findCountFhRecordByLevel(user.getUserId(), user.getCardId());
					if (shCount>=3) {
						message = "您得审核次数已经用完,请申请更高店主!";
					}
				}
				
				if (message=="") {
					if(user.getCardId()>=level)
						message = fhDianService.fahongbao(usery.getWxOpenid(),user.getUserId(),userId2,level,amount,flagCount);
					else {
						message = "您的数据出现错误,请联系管理员进行更改!";
					}
				}
			}else {
				message = "您已退款无法领取店主红包!";
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
						//信息通知用户
						String content="您好:\n您的订单已支付成功！\n"+"订单编号："+order.getOrdersBH()+"\n"+"支付金额："+order.getMoney()+"元";
						autosendmsgService.sendMsg(openid, content);
						user = userService.findById(order.getUserId());

						orderService.moneyPay(order,user);
						
						if(!out_trade_no.startsWith("ms")){
							JifenUser refferUser = null;
							Usery parentUsery = null;
							if(user.getReferrerId()!=null){
								refferUser = userService.findById(user.getReferrerId());
								parentUsery = useryService.findbyUserId(user.getReferrerId());
							}
							String mess = "";
							if(refferUser !=null){
								mess = "您好:\n您的超级粉丝:";
								mess += "【"+user.getUserId()+" ： "+user.getUserName()+"】已成功下单！\n"+"支付金额："+order.getMoney()+"元,升级店铺!";
								if(parentUsery !=null&&parentUsery.getWxOpenid()!=null)
								autosendmsgService.sendMsg(parentUsery.getWxOpenid(),mess);
								if(refferUser.getReferrerId()!=null){
									JifenUser r2 = userService.findById(refferUser.getReferrerId());
									if(r2!=null){
										Usery usery2 = useryService.findbyUserId(r2.getUserId());
										if(usery2!=null&&usery2.getWxOpenid()!=null){
											mess = "您好:\n您的铁杆粉丝:";
											mess += "【"+user.getUserId()+" ： "+user.getUserName()+"】已成功下单！\n"+"支付金额："+order.getMoney()+"元,升级店铺!";
											autosendmsgService.sendMsg(usery2.getWxOpenid(),mess);
										}
										if(r2.getReferrerId()!=null){
											Usery u3 = useryService.findbyUserId(r2.getReferrerId());
											if(u3!=null&&u3.getWxOpenid()!=null){
												mess = "您好:\n您的忠实粉丝:";
												mess += "【"+user.getUserId()+" ： "+user.getUserName()+"】已成功下单！\n"+"支付金额："+order.getMoney()+"元,升级店铺!";
												autosendmsgService.sendMsg(u3.getWxOpenid(),mess);
											}
										}
									}
								}
							}
						}
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
		 //user =userService.findById(2341);
		return  user;
	} 

	public String getOrdersBH() {
		return ordersBH;
	}

	public void setOrdersBH(String ordersBH) {
		this.ordersBH = ordersBH;
	}

	public JifenUser getUser() {
		return user;
	}


	public void setUser(JifenUser user) {
		this.user = user;
	}


	public OrderDian getOrder() {
		return order;
	}


	public void setOrder(OrderDian order) {
		this.order = order;
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


	public OrderAddress getOrderAddress() {
		return orderAddress;
	}


	public void setOrderAddress(OrderAddress orderAddress) {
		this.orderAddress = orderAddress;
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


	public Integer getOrderAddRessId() {
		return orderAddRessId;
	}


	public void setOrderAddRessId(Integer orderAddRessId) {
		this.orderAddRessId = orderAddRessId;
	}


	public Integer getLevelValue() {
		return levelValue;
	}


	public void setLevelValue(Integer levelValue) {
		this.levelValue = levelValue;
	}

	public Integer getLevelOne() {
		return levelOne;
	}

	public void setLevelOne(Integer levelOne) {
		this.levelOne = levelOne;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
