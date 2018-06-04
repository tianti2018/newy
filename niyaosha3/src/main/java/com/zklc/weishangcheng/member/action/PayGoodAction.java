package com.zklc.weishangcheng.member.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletInputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.utils.GetWxOrderno;
import com.utils.MyUtils;
import com.utils.RequestHandler;
import com.utils.Sha1Util;
import com.utils.TenpayUtil;
import com.zklc.framework.action.BaseAction;
import com.zklc.weishangcheng.member.hibernate.persistent.DianpuForUser;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderAddress;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderJinHuo;
import com.zklc.weishangcheng.member.hibernate.persistent.Orders;
import com.zklc.weishangcheng.member.hibernate.persistent.Products;
import com.zklc.weishangcheng.member.hibernate.persistent.ProductsForDianpu;
import com.zklc.weishangcheng.member.hibernate.persistent.ShouYiForUser;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.service.DianpuForUserService;
import com.zklc.weishangcheng.member.service.OrderAddressService;
import com.zklc.weishangcheng.member.service.OrdersService;
import com.zklc.weishangcheng.member.service.ProductsForDianpuService;
import com.zklc.weishangcheng.member.service.ProductsService;
import com.zklc.weishangcheng.member.service.ShouYiForUserService;
import com.zklc.weishangcheng.member.service.UserService;
import com.zklc.weishangcheng.member.service.UseryService;
import com.zklc.weishangcheng.member.service.WeixinAutosendmsgService;
import com.zklc.weishangcheng.member.util.PublicUtil;
import com.zklc.weixin.util.SystemMessage;

import net.sf.json.JSONObject;

@SuppressWarnings("serial")
@ParentPackage("json")
@Namespace("/pay")
@Action(value = "payGoodAction")
@Results({
	@Result(name = "listHongbao", location = "/WEB-INF/jsp/phone_dianzhu_hongbao.jsp"),
	@Result(name = "listDianHongBaos", location = "/WEB-INF/jsp/list_dian_hongbao.jsp"),
	@Result(name = "toGoodBuy", location = "/WEB-INF/jsp/goodBuy.jsp"),
	@Result(name = "toJifenBuy", location = "/WEB-INF/jsp/jifen/jifenBuy.jsp"),
	@Result(name = "toMianMoBuy", location = "/WEB-INF/jsp/mianMoBuy2.jsp"),
	@Result(name = "toMiaoShaHou", location = "/WEB-INF/jsp/miaosha/miaosha_houpiao.jsp"),
	@Result(name = "product", location = "/WEB-INF/jsp/product.jsp"),
	@Result(name = "ajaxResult", type = "json", params = { "message",
	"message" }),
	@Result(name = "products", location = "/WEB-INF/jsp/products.jsp"),
	
})
public class PayGoodAction extends BaseAction {

	@Autowired
	private UseryService useryService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrdersService orderService;
	@Autowired
	private OrderAddressService orderAddressService;
	@Autowired
	private WeixinAutosendmsgService autosendmsgService;
	@Autowired
	private ProductsService productsService;
	@Autowired
	private ProductsForDianpuService productsForDianpuService;
	@Autowired
	private ShouYiForUserService shouyiService;
	
	@Autowired
	private DianpuForUserService dianpuForUserService;
	
	private OrderAddress orderAddress;
	private Orders order;
	private Users user;
	
	private String ordersBH;
	public String orderNo;
	private String qty_item_1;//购买的商品数量
	private String total_price1;
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
	public Integer type;//上平类型
	private Integer orderAddRessId;
	
	private Integer miaoShaNum;
	private Integer qi;
	private Integer prodId;//购买产品id
	private Integer xhq_count;//下单时使用的星火券的格式
	private String state;
	
	
	/**
	 * 订单支付成功通知
	 */
	public String execute() {
		String openid = null;
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
		System.out.println("总共支付:"+total_fee);
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
					String content="您好:\n您的订单已支付成功！\n"+"订单编号："+order.getOrdersBH()+"\n"+"支付金额："+order.getMoney()+
							",购买"+order.getPname()+order.getShuliang();
					orderService.moneyPay(order);
					autosendmsgService.sendMsg(openid, content);
				}
				
			}
		}
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
		Usery usery = null;
		if (orderNo != null) {
			order=orderService.findOrderByOrderBH(orderNo);
			if(order.getUserId()!=null){
				user = userService.findById(order.getUserId());
			}
			if(order.getUseryId()==null){
				if(user!=null&&user.getUseryId()!=null){
					usery = useryService.findById(user.getUseryId());
				}
			}else{
				usery = useryService.findById(order.getUseryId());
			}
			
			System.out.println("进来了"+orderNo);
		} else {
			message = "error";
			return "ajaxResult";
		}
		total_fee = (int)(order.getMoney()*100)+"";
		String useryIds = SystemMessage.getString("useryIds");
		String[] ids = useryIds.split(",");
		if(usery!=null)
			for(String id:ids){
				if(id.equals(usery.getId()+"")){
					total_fee="1";
				}
			}
			
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
		String notify_url = SystemMessage.getString("ZHIFU_YUMING")+"/pay/payGoodAction!execute.action";

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
	
	//利用收益从店铺下单
	public void savePDOSY(){
		json.put("success", false);
		userVo = getSessionUser();
		if(userVo == null){
			json.put("timeOut", true);
		}else {
			user = userVo.getUser();
			Usery usery = userVo.getUsery();
	//		user = userService.findById(1820);
			ProductsForDianpu productsForDianpu=productsForDianpuService.findById(prodId);
			Products prod = null;
			if(productsForDianpu!=null){
				prod = productsService.findById(productsForDianpu.getProductId());
			}
			Integer size =MyUtils.isNumber(qty_item_1)?Integer.parseInt(qty_item_1):1;
			if(prod!=null){
				//购买数量不能大于限购数量
				if(prod.getLimitNum()!=null&&prod.getLimitNum()!=0){
					if(size>prod.getLimitNum()){
						json.put("message", "本产品1次限购"+prod.getLimitNum());
						try {
							jsonOut(json);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return;
					}
				}
				if(size>prod.getStock()||productsForDianpu.getKaiguan()==1){
					
					json.put("message", "库存不足");
					try {
						jsonOut(json);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return;
				}
				Double yve = 0.0;
				if(usery!=null){
					yve = shouyiService.findYvE(usery.getDianPuId());
				}
				
					OrderJinHuo orderJinHuo = null;
					ShouYiForUser dianzhuShouyiRecord = null;
					ShouYiForUser jinhuodianShouyiRecord = null;
					Usery dianzhu = null;//店主
					Usery parent = null;//进货点主人
					Integer dianzhuLevel = 0;//店主级别
					Integer parentLevel =0;//进货点主人级别
					DianpuForUser dianzhuDianpu=null;//店主店铺
					DianpuForUser parentDianpu = null;//进货点
					Double gukePrice = productsForDianpu.getPrice();//顾客价格 
					
					Double dianzhuPrice = prod.getPrice();//店主进货价格
					Double parentPice = prod.getPrice();//进货店进货价格
					Double dianzhuShouyi = 0.0;//店主收益
					Double parentShouyi = 0.0;//进货点收益
					if(usery!=null){
						if(usery.getDianPuId()!=null&&usery.getDianPuId().equals(productsForDianpu.getDianpuId())){
							gukePrice = getPrice(usery.getLevel(), prod, null);
						}
					}
					if(yve<gukePrice+prod.getTransFee()){
						json.put("message","余额不足!");
						try {
							jsonOut(json);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return;
					}
					dianzhuDianpu = dianpuForUserService.findById(productsForDianpu.getDianpuId());
					dianzhu = useryService.findById(dianzhuDianpu.getUseryId());
					if(dianzhu!=null){
						if(dianzhu.getParentId()!=null){//进货店主
							parent = useryService.findById(dianzhu.getParentId());
							if(parent.getDianPuId()!=null){
								parentDianpu = dianpuForUserService.findById(parent.getDianPuId());
							}
						}
					}
					//上面确定三个角色顾客,店主,进货店主和两个店铺,店主店铺,进货店铺
					//下面是三个角色的等级
					if(dianzhu!=null){//店主等级
						dianzhuLevel = dianzhu.getLevel()==null?0:dianzhu.getLevel();
					}
					if(parent!=null){//进货点店主等级
						parentLevel = parent.getLevel()==null?0:parent.getLevel();
					}
					//下面是三个价格和两个收益
					dianzhuPrice = getPrice(dianzhuLevel, prod,null);
					parentPice = getPrice(parentLevel, prod, null);
					dianzhuShouyi = getShouyi(gukePrice-dianzhuPrice);
					parentShouyi = getShouyi(dianzhuPrice-parentPice);
					
					//状态 0未获得  1已获得  2 支出  
					OrderAddress orderAddress = orderAddressService.findDefaultAddressByUserVo(userVo);
					if(orderAddress!=null){
						// 订单编号
						ordersBH = "dp" + PublicUtil.getOrderNo();
						// 创建订单
						order = new Orders();
						String pname = prod.getName();
						if(StringUtils.isNotEmpty(prod.getGuige())){
							pname+="("+prod.getGuige()+")";
						}
						order.setPname(pname);
						order.setPictureUrl(prod.getHeadUrl());
						order.setProductId(prod.getProductsId());
						order.setPdId(productsForDianpu.getId());						
						order.setDianpuId(productsForDianpu.getDianpuId());
						
						order.setYunfei(prod.getTransFee());
						order.setToUserName(orderAddress.getUserName());
						order.setMobile(orderAddress.getMobile());
						order.setZipcode(orderAddress.getZipcode());
						order.setSheng(orderAddress.getSheng());						
						order.setChengshi(orderAddress.getChengshi());
						order.setDiqu(orderAddress.getDiqu());
						order.setShengCode(orderAddress.getShengCode());
						order.setChengshiCode(orderAddress.getChengshiCode());
						order.setDiquCode(orderAddress.getDiquCode());
						order.setAddress(orderAddress.getAddress());
						order.setShuliang(size);
						order.setOrderStatus(1);
						order.setCreateDate(new Date());
						order.setOrdersBH(ordersBH);
						if(user!=null)
							order.setUserId(user.getUserId());
						if(usery!=null){
							order.setUseryId(usery.getId());
						}
						order.setShouyi(dianzhuShouyi*size);
						order.setMoney(0.0);
						order.setXiaohaoShouyi(gukePrice*size+prod.getTransFee());
						order.setType(0);
						//这里记录收益的消耗记录
						ShouYiForUser xiaohaoRecord = new ShouYiForUser();
						xiaohaoRecord.setCreateDate(new Date());
						xiaohaoRecord.setOrdersBh(ordersBH);
						xiaohaoRecord.setDianpuId(usery.getDianPuId());
						xiaohaoRecord.setShouyi(-(gukePrice*size+prod.getTransFee()));
						xiaohaoRecord.setOrderType(0);
						xiaohaoRecord.setStatus(2);
						xiaohaoRecord.setBeizhu("购物消费");
						if(dianzhuDianpu!=null){
							//没有收益也能看到记录
							dianzhuShouyiRecord = new ShouYiForUser();
							dianzhuShouyiRecord.setCreateDate(new Date());
							dianzhuShouyiRecord.setOrdersBh(ordersBH);
							dianzhuShouyiRecord.setDianpuId(dianzhuDianpu.getId());
							dianzhuShouyiRecord.setOrderType(0);
							dianzhuShouyiRecord.setShouyi(dianzhuShouyi*size);
							dianzhuShouyiRecord.setStatus(1);
							dianzhuShouyiRecord.setBeizhu("收益");
						}
						
						if(parentDianpu!=null&&dianzhuLevel==1){
							orderJinHuo = new OrderJinHuo();
							orderJinHuo.setOrdersBH("jh"+ordersBH.substring(2));
							orderJinHuo.setDianpuId(parent.getDianPuId());
							orderJinHuo.setFromDianpuId(dianzhu.getDianPuId());
							orderJinHuo.setMoney(dianzhuPrice*size);
							orderJinHuo.setCreateDate(new Date());
							orderJinHuo.setShouyi(parentShouyi*size);
							//为0是否能看到记录
							jinhuodianShouyiRecord = new ShouYiForUser();
							jinhuodianShouyiRecord.setCreateDate(new Date());
							jinhuodianShouyiRecord.setDianpuId(parentDianpu.getId());
							jinhuodianShouyiRecord.setOrdersBh(orderJinHuo.getOrdersBH());
							jinhuodianShouyiRecord.setOrderType(1);
							jinhuodianShouyiRecord.setStatus(1);
							jinhuodianShouyiRecord.setShouyi(parentShouyi*size);
							jinhuodianShouyiRecord.setBeizhu("收益");
						}
						orderService.createOrder(order,orderJinHuo,dianzhuShouyiRecord,jinhuodianShouyiRecord,xiaohaoRecord);
						
						json.put("success", true);
						json.put("ordersBh", ordersBH);
				}
			}else{
				json.put("message", "产品已经下架!");
			}
		}
		try {
			jsonOut(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//利用收益从商城下单
	public void savePDSY(){
		json.put("success", false);
		userVo = getSessionUser();
		if(userVo == null){
			json.put("timeOut", true);
		}else {
			user = userVo.getUser();
			Usery usery = userVo.getUsery();
	//		user = userService.findById(1820);
			Products prod =  productsService.findById(prodId);
			Integer size =MyUtils.isNumber(qty_item_1)?Integer.parseInt(qty_item_1):1;
			if(prod!=null){
				//购买数量不能大于限购数量
				if(prod.getLimitNum()!=null&&prod.getLimitNum()!=0){
					if(size>prod.getLimitNum()){
						json.put("message", "本产品1次限购"+prod.getLimitNum());
						try {
							jsonOut(json);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return;
					}
				}
				if(size>prod.getStock()){
					
					json.put("message", "库存不足");
					try {
						jsonOut(json);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return;
				}
				Double yve = 0.0;
				if(usery!=null){
					yve = shouyiService.findYvE(usery.getDianPuId());
				}
				
					Integer gukeLevel = 0;  //顾客级别
					Double gukePrice = prod.getPrice();//顾客价格 
					if(usery!=null){//顾客等级
						gukeLevel = usery.getLevel()==null?0:usery.getLevel();
					}
					gukePrice = getPrice(gukeLevel, prod,null);
					if(yve<gukePrice+prod.getTransFee()){
						json.put("message","余额不足!");
						try {
							jsonOut(json);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return;
					}
					OrderAddress orderAddress = orderAddressService.findDefaultAddressByUserVo(userVo);
					if(orderAddress!=null){
						// 订单编号
						ordersBH = "dp" + PublicUtil.getOrderNo();
						// 创建订单
						order = new Orders();
						String pname = prod.getName();
						if(StringUtils.isNotEmpty(prod.getGuige())){
							pname+="("+prod.getGuige()+")";
						}
						order.setPname(pname);
						order.setPictureUrl(prod.getHeadUrl());
						order.setProductId(prod.getProductsId());
						order.setYunfei(prod.getTransFee());
						order.setToUserName(orderAddress.getUserName());
						order.setMobile(orderAddress.getMobile());
						order.setZipcode(orderAddress.getZipcode());
						order.setSheng(orderAddress.getSheng());						
						order.setChengshi(orderAddress.getChengshi());
						order.setDiqu(orderAddress.getDiqu());
						order.setShengCode(orderAddress.getShengCode());
						order.setChengshiCode(orderAddress.getChengshiCode());
						order.setDiquCode(orderAddress.getDiquCode());
						order.setAddress(orderAddress.getAddress());
						order.setShuliang(size);
						order.setOrderStatus(0);
						order.setCreateDate(new Date());
						order.setOrdersBH(ordersBH);
						if(user!=null)
							order.setUserId(user.getUserId());
						if(usery!=null){
							order.setUseryId(usery.getId());
						}
						order.setShouyi(0.0);
						order.setMoney(0.0);
						order.setXiaohaoShouyi(gukePrice*size+prod.getTransFee());
						order.setType(0);
						//生成消耗收益记录
						ShouYiForUser xiaohaoRecord = new ShouYiForUser();
						xiaohaoRecord.setCreateDate(new Date());
						xiaohaoRecord.setOrdersBh(ordersBH);
						xiaohaoRecord.setDianpuId(usery.getDianPuId());
						xiaohaoRecord.setShouyi(-(gukePrice*size+prod.getPrice()));
						xiaohaoRecord.setOrderType(0);
						xiaohaoRecord.setStatus(2);
						xiaohaoRecord.setBeizhu("购物消费");
						orderService.createOrder(order,null,null,null,xiaohaoRecord);
						
						json.put("success", true);
						json.put("ordersBh", ordersBH);
				}
			}else{
				json.put("message", "产品已经下架!");
			}
		}
		try {
			jsonOut(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveProductDianpuOrder(){
		json.put("success", false);
		userVo = getSessionUser();
		if(userVo == null){
			json.put("timeOut", true);
		}else {
			user = userVo.getUser();
			Usery usery = userVo.getUsery();
	//		user = userService.findById(1820);
			ProductsForDianpu productsForDianpu=productsForDianpuService.findById(prodId);
			Products prod = null;
			if(productsForDianpu!=null){
				prod = productsService.findById(productsForDianpu.getProductId());
			}
			Integer size =MyUtils.isNumber(qty_item_1)?Integer.parseInt(qty_item_1):1;
			if(prod!=null){
				//购买数量不能大于限购数量
				if(prod.getLimitNum()!=null&&prod.getLimitNum()!=0){
					if(size>prod.getLimitNum()){
						json.put("message", "本产品1次限购"+prod.getLimitNum());
						try {
							jsonOut(json);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return;
					}
				}
				if(size>prod.getStock()||productsForDianpu.getKaiguan()==1){
					
					json.put("message", "库存不足");
					try {
						jsonOut(json);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return;
				}
					
					OrderJinHuo orderJinHuo = null;
					ShouYiForUser dianzhuShouyiRecord = null;
					ShouYiForUser jinhuodianShouyiRecord = null;
					Usery dianzhu = null;//店主
					Usery parent = null;//进货点主人
					Integer dianzhuLevel = 0;//店主级别
					Integer parentLevel =0;//进货点主人级别
					DianpuForUser dianzhuDianpu=null;//店主店铺
					DianpuForUser parentDianpu = null;//进货点
					Double gukePrice = productsForDianpu.getPrice();//顾客价格 
					Double dianzhuPrice = prod.getPrice();//店主进货价格
					Double parentPice = prod.getPrice();//进货店进货价格
					Double dianzhuShouyi = 0.0;//店主收益
					Double parentShouyi = 0.0;//进货点收益
					if(usery!=null){
						if(usery.getDianPuId()!=null&&usery.getDianPuId().equals(productsForDianpu.getDianpuId())){
							gukePrice = getPrice(usery.getLevel(), prod, null);
						}
					}
					dianzhuDianpu = dianpuForUserService.findById(productsForDianpu.getDianpuId());
					dianzhu = useryService.findById(dianzhuDianpu.getUseryId());
					if(dianzhu!=null){
						if(dianzhu.getParentId()!=null){//进货店主
							parent = useryService.findById(dianzhu.getParentId());
							if(parent.getDianPuId()!=null){
								parentDianpu = dianpuForUserService.findById(parent.getDianPuId());
							}
						}
					}
					//上面确定三个角色顾客,店主,进货店主和两个店铺,店主店铺,进货店铺
					//下面是三个角色的等级
					if(dianzhu!=null){//店主等级
						dianzhuLevel = dianzhu.getLevel()==null?0:dianzhu.getLevel();
					}
					if(parent!=null){//进货点店主等级
						parentLevel = parent.getLevel()==null?0:parent.getLevel();
					}
					//下面是三个价格和两个收益
					dianzhuPrice = getPrice(dianzhuLevel, prod,null);
					parentPice = getPrice(parentLevel, prod, null);
					dianzhuShouyi = getShouyi(gukePrice-dianzhuPrice);
					parentShouyi = getShouyi(dianzhuPrice-parentPice);
					
					//状态 0未获得  1已获得  2 支出  
					OrderAddress orderAddress = orderAddressService.findDefaultAddressByUserVo(userVo);
					if(orderAddress!=null){
						// 订单编号
						ordersBH = "dp" + PublicUtil.getOrderNo();
						// 创建订单
						order = new Orders();
						String pname = prod.getName();
						if(StringUtils.isNotEmpty(prod.getGuige())){
							pname+="("+prod.getGuige()+")";
						}
						order.setPname(pname);
						order.setPictureUrl(prod.getHeadUrl());
						order.setProductId(prod.getProductsId());
						order.setPdId(productsForDianpu.getId());						
						order.setDianpuId(productsForDianpu.getDianpuId());
						
						order.setYunfei(prod.getTransFee());
						order.setToUserName(orderAddress.getUserName());
						order.setMobile(orderAddress.getMobile());
						order.setZipcode(orderAddress.getZipcode());
						order.setSheng(orderAddress.getSheng());						
						order.setChengshi(orderAddress.getChengshi());
						order.setDiqu(orderAddress.getDiqu());
						order.setShengCode(orderAddress.getShengCode());
						order.setChengshiCode(orderAddress.getChengshiCode());
						order.setDiquCode(orderAddress.getDiquCode());
						order.setAddress(orderAddress.getAddress());
						order.setShuliang(size);
						order.setOrderStatus(0);
						order.setCreateDate(new Date());
						order.setOrdersBH(ordersBH);
						if(user!=null)
							order.setUserId(user.getUserId());
						if(usery!=null){
							order.setUseryId(usery.getId());
						}
						order.setShouyi(dianzhuShouyi);
						order.setMoney(gukePrice*size+prod.getTransFee());
						order.setXiaohaoShouyi(0.0);
						order.setType(0);
						
						if(dianzhuDianpu!=null){
							//没有收益也能看到记录
							dianzhuShouyiRecord = new ShouYiForUser();
							dianzhuShouyiRecord.setCreateDate(new Date());
							dianzhuShouyiRecord.setOrdersBh(ordersBH);
							dianzhuShouyiRecord.setDianpuId(dianzhuDianpu.getId());
							dianzhuShouyiRecord.setOrderType(0);
							dianzhuShouyiRecord.setShouyi(dianzhuShouyi*size);
							dianzhuShouyiRecord.setStatus(0);
							dianzhuShouyiRecord.setBeizhu("收益");
						}
						
						if(parentDianpu!=null&&dianzhuLevel==1){
							orderJinHuo = new OrderJinHuo();
							orderJinHuo.setOrdersBH("jh"+ordersBH.substring(2));
							orderJinHuo.setDianpuId(parent.getDianPuId());
							orderJinHuo.setFromDianpuId(dianzhu.getDianPuId());
							orderJinHuo.setMoney(dianzhuPrice*size);
							orderJinHuo.setCreateDate(new Date());
							orderJinHuo.setShouyi(parentShouyi*size);
							//为0是否能看到记录
							jinhuodianShouyiRecord = new ShouYiForUser();
							jinhuodianShouyiRecord.setCreateDate(new Date());
							jinhuodianShouyiRecord.setDianpuId(parentDianpu.getId());
							jinhuodianShouyiRecord.setOrdersBh(orderJinHuo.getOrdersBH());
							jinhuodianShouyiRecord.setOrderType(1);
							jinhuodianShouyiRecord.setStatus(0);
							jinhuodianShouyiRecord.setShouyi(parentShouyi*size);
							jinhuodianShouyiRecord.setBeizhu("收益");
						}
						
						orderService.createOrder(order,orderJinHuo,dianzhuShouyiRecord,jinhuodianShouyiRecord,null);
						
						json.put("success", true);
						json.put("ordersBh", ordersBH);
						json.put("need_pay", true);
				}
			}else{
				json.put("message", "产品已经下架!");
			}
		}
		try {
			jsonOut(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Double getShouyi(Double shouyi) {
		return shouyi>0.0?shouyi:0.0;
	}

	public void saveProductOrder(){
		json.put("success", false);
		userVo = getSessionUser();
		if(userVo == null){
			json.put("timeOut", true);
		}else {
			user = userVo.getUser();
			Usery usery = userVo.getUsery();
	//		user = userService.findById(1820);
			Products prod =  productsService.findById(prodId);
			Integer size =MyUtils.isNumber(qty_item_1)?Integer.parseInt(qty_item_1):1;
			if(prod!=null){
				//购买数量不能大于限购数量
				if(prod.getLimitNum()!=null&&prod.getLimitNum()!=0){
					if(size>prod.getLimitNum()){
						json.put("message", "本产品1次限购"+prod.getLimitNum());
						try {
							jsonOut(json);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return;
					}
				}
				if(size>prod.getStock()){
					
					json.put("message", "库存不足");
					try {
						jsonOut(json);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return;
				}
					
					Integer gukeLevel = 0;  //顾客级别
					Double gukePrice = prod.getPrice();//顾客价格 
					if(usery!=null){//顾客等级
						gukeLevel = usery.getLevel()==null?0:usery.getLevel();
					}
					gukePrice = getPrice(gukeLevel, prod,null);
					
					OrderAddress orderAddress = orderAddressService.findDefaultAddressByUserVo(userVo);
					if(orderAddress!=null){
						// 订单编号
						ordersBH = "dp" + PublicUtil.getOrderNo();
						// 创建订单
						order = new Orders();
						String pname = prod.getName();
						if(StringUtils.isNotEmpty(prod.getGuige())){
							pname+="("+prod.getGuige()+")";
						}
						order.setPname(pname);
						order.setPictureUrl(prod.getHeadUrl());
						order.setProductId(prod.getProductsId());
						order.setYunfei(prod.getTransFee());
						order.setToUserName(orderAddress.getUserName());
						order.setMobile(orderAddress.getMobile());
						order.setZipcode(orderAddress.getZipcode());
						order.setSheng(orderAddress.getSheng());						
						order.setChengshi(orderAddress.getChengshi());
						order.setDiqu(orderAddress.getDiqu());
						order.setShengCode(orderAddress.getShengCode());
						order.setChengshiCode(orderAddress.getChengshiCode());
						order.setDiquCode(orderAddress.getDiquCode());
						order.setAddress(orderAddress.getAddress());
						order.setShuliang(size);
						order.setOrderStatus(0);
						order.setCreateDate(new Date());
						order.setOrdersBH(ordersBH);
						if(user!=null)
							order.setUserId(user.getUserId());
						if(usery!=null){
							order.setUseryId(usery.getId());
						}
						order.setShouyi(0.0);
						order.setMoney(gukePrice*size+prod.getTransFee());
						order.setXiaohaoShouyi(0.0);
						order.setType(0);
 						orderService.createOrder(order,null,null,null,null);
						
						json.put("success", true);
						json.put("need_pay", true);
						json.put("ordersBh", ordersBH);
				}
			}else{
				json.put("message", "产品已经下架!");
			}
		}
		try {
			jsonOut(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public Double getPrice(Integer level,Products products, Double gukePrice){
		switch (level) {
		case 1:
			return products.getLevelone();
		case 2:
			return products.getLeveltwo();
		case 3:
			return products.getLevelthr();
		case 4:
			return products.getLevelfor();
		default:
			if(gukePrice!=null){
				return gukePrice;
			}
			return products.getPrice();
		}
	}

	

	public String toMiaoShaHou(){
		userVo = getSessionUser();
		user = userVo.getUser();
//		user = userService.findById(2104408);
//		request.getSession().setAttribute("loginUser",user);
		if(user == null){
			return "timeOut";
		}
		request.setAttribute("orderAddress", orderAddressService.findOrderAddressByUserId(user.getUserId()));
		return "toMiaoShaHou";
	}
	
	public String toGoodBuy(){
		userVo = getSessionUser();
		user = userVo.getUser();
     	//user = userService.findById(3480);
		if(user==null){
			return "timeOut";
		}

		user = userService.findById(user.getUserId());
		request.getSession().setAttribute("loginUser",user);
		if(user == null){
			return "timeOut";
		}
		request.setAttribute("orderAddress", orderAddressService.findOrderAddressByUserId(user.getUserId()));
		return "toGoodBuy";
	}
	//面膜
	
	public void saveHouMS(){

		
		JSONObject json = new JSONObject();
		response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");	
		userVo = getSessionUser();
		user = userVo.getUser();
//		user = userService.findById(1820);
		if(user == null){
			json.put("success", false);
			json.put("timeOut", true);
		}else {
			Integer num = orderService.findSellerNumByQi(qi);
			if(miaoShaNum!=null&&num<=miaoShaNum){
				ordersBH = "msh" + PublicUtil.getOrderNo();
				order = new Orders();
				if(orderAddress!= null){
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
				}
					order.setMoney(165.0);
					order.setOrdersBH(ordersBH);
					order.setPname("朝鲜2013年猴年整版80枚全新 雕刻版 大版票 外国邮票");
					order.setToUserName(orderAddress.getUserName());
					order.setMobile(orderAddress.getMobile());
					order.setZipcode(orderAddress.getZipcode());
					order.setCreateDate(new Date());
					order.setUserId(user.getUserId());
					order.setAddress(orderAddress.getAddress());
					order.setType(type);
					orderService.save(order);
					json.put("success", true);
					json.put("ordersBh", ordersBH);
				}else{
					json.put("success", false);
					json.put("error", true);
				}
			}
		try {
			response.getWriter().print(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public void saveOrderGood() {


		JSONObject json = new JSONObject();
		response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");	
		userVo = getSessionUser();
		user = userVo.getUser();
//		user = userService.findById(1820);
		if(user == null){
			json.put("success", false);
			json.put("timeOut", true);
		}else {
			user = userService.findById(user.getUserId());
			// 订单编号
				ordersBH = "go" + PublicUtil.getOrderNo();
				// 创建订单
				if (order == null)
					order = new Orders();
				
				OrderAddress orderAddress = orderAddressService.findById(orderAddRessId);
				Double priceDouble = 2036.0;
//				if(user.getCardId()!=null&&user.getCardId()!=0){
//					Double zhekou = 0.75-0.05*user.getCardId();
//					if(zhekou<0.3){
//						zhekou = 0.3;
//					}
//					priceDouble *=zhekou;
//				}
				DecimalFormat fmt = new DecimalFormat("#.##");
				Double price = Double.valueOf(fmt.format(priceDouble));
				Integer size = 1;
				if(qty_item_1!=null&&!"".equals(qty_item_1)){
					size = Integer.parseInt(qty_item_1);
				}
				order.setMoney((price+30)*size);
				order.setOrdersBH(ordersBH);
				order.setPname("神六组合套装");
				order.setToUserName(orderAddress.getUserName());
				order.setMobile(orderAddress.getMobile());
				order.setZipcode(orderAddress.getZipcode());
				order.setCreateDate(new Date());
				order.setUserId(user.getUserId());
				order.setType(type);
				order.setProductId(1);
				order.setAddress(orderAddress.getAddress());
//					order.setLevelValue(levelValue);
				orderService.saveAndCFh(order,user);
				json.put("success", true);
				json.put("ordersBh", ordersBH);
		
		}
		try {
			response.getWriter().print(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public Integer getMiaoShaNum() {
		return miaoShaNum;
	}

	public void setMiaoShaNum(Integer miaoShaNum) {
		this.miaoShaNum = miaoShaNum;
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

	public Integer getXhq_count() {
		return xhq_count;
	}

	public void setXhq_count(Integer xhq_count) {
		this.xhq_count = xhq_count;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
