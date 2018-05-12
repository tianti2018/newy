package com.zklc.weishangcheng.member.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.zklc.weishangcheng.member.hibernate.persistent.AccessToken;
import com.zklc.weishangcheng.member.hibernate.persistent.JiFenRecord;
import com.zklc.weishangcheng.member.hibernate.persistent.JifenUser;
import com.zklc.weishangcheng.member.hibernate.persistent.LiuCode;
import com.zklc.weishangcheng.member.hibernate.persistent.MiaoShaOrder;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderAddress;
import com.zklc.weishangcheng.member.hibernate.persistent.Product;
import com.zklc.weishangcheng.member.hibernate.persistent.ProductComment;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.OrderAddressVO;
import com.zklc.weishangcheng.member.service.CommentService;
import com.zklc.weishangcheng.member.service.GoodYongJinService;
import com.zklc.weishangcheng.member.service.JiFenRecordService;
import com.zklc.weishangcheng.member.service.JifenUserService;
import com.zklc.weishangcheng.member.service.LiuCodeService;
import com.zklc.weishangcheng.member.service.MiaoShaOrderService;
import com.zklc.weishangcheng.member.service.OrderAddressService;
import com.zklc.weishangcheng.member.service.ProdImageService;
import com.zklc.weishangcheng.member.service.ProductService;
import com.zklc.weishangcheng.member.service.UseryService;
import com.zklc.weishangcheng.member.service.WeixinAutosendmsgService;
import com.zklc.weishangcheng.member.service.XingHuoQuanRecordService;
import com.zklc.weishangcheng.member.util.PublicUtil;
import com.zklc.weixin.util.SystemMessage;
import com.zklc.weixin.util.UserInfoUtil;
import com.zklc.weixin.util.WeixinUtil;
import com.zklc.weixin.util.sign;

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
	private JifenUserService userService;
	@Autowired
	private MiaoShaOrderService orderService;
	@Autowired
	private GoodYongJinService yongJinService;
	@Autowired
	private OrderAddressService orderAddressService;
	@Autowired
	private WeixinAutosendmsgService autosendmsgService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProdImageService prodImageService;
	@Autowired
	private JiFenRecordService jiFenRecordService;
	@Autowired
	private XingHuoQuanRecordService xingHuoQuanRecordService;
	@Autowired
	private CommentService commentservice;
	
	@Autowired
	private LiuCodeService liuCodeService;
	
	private OrderAddress orderAddress;
	private MiaoShaOrder order;
	private JifenUser user;
	
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
	

	public String toJifenBuy(){
		user = getSessionUser();
		if(user==null){
			return "timeOut";
		}
		List othJifen = jiFenRecordService.countUserJifen(user.getUserId(), 2);
		List newuserJIfen = jiFenRecordService.countNewUserJifen(user.getUserId(), 2);
		OrderAddressVO address = orderAddressService.findOrderAddressByUserId(user.getUserId());
		LiuCode code = liuCodeService.findById(26);
		if(code != null){
			if(code.getBusPerson().contains(prodId.toString())){//是否需要推荐用户积分
				request.setAttribute("newUserPro", true);
			}else{
				request.setAttribute("newUserPro", false);
			}
			
			if(code.getLiuMoney().contains(prodId.toString())){//是否需要身份证
				if(address != null && StringUtils.isNotBlank(address.getIdCard())){
					//判断身份证号是否存在，存在是否使用兑换过积分产品
					Integer countid = orderService.countUserIdCardProduct(user.getUserId(),prodId,address.getIdCard());
					if(countid == 0){
						request.setAttribute("isCardProd", true);
					}else{
						request.setAttribute("isCardProd", false);
					}
				}else{
					request.setAttribute("isCardProd", false);
				}
			}
			
		}
		if(othJifen.size() >0){
			//查询用其他类型可用积分
			request.setAttribute("useJifen", Integer.parseInt(othJifen.get(0).toString()));
		}else{
			request.setAttribute("useJifen", 0);
		}

		//查询新增用户可用积分
		if(newuserJIfen.size()>0){
			request.setAttribute("newUserJIfen",Integer.parseInt(newuserJIfen.get(0).toString()));
		}else{
			request.setAttribute("newUserJIfen",0);
		}
		
		Product prod=productService.findById(prodId);
		List<Product> prodList=productService.findByProperty("prodType", prod.getProdType());
		request.setAttribute("prod", prod);
		request.setAttribute("typelist", prodList);
		request.setAttribute("typeqty", prodList.size());
		request.setAttribute("orderAddress", address);
		
		return "toJifenBuy";
	}
	
	public void saveJifenOrder(){
		JSONObject json = new JSONObject();
		response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");	
		user = getSessionUser();
		Product prod=productService.findById(prodId);
		Integer size =1;
		
		//购买数量不能大于限购数量
//		if(size>prod.getLimitNum())
//		{
//			json.put("success", false);
//			json.put("overbuy", true);
//		}
//		else if(size>prod.getStock())
//		{
//			json.put("success", false);
//			json.put("stockless", true);
//		}
		if(user == null){
			json.put("success", false);
			json.put("timeOut", true);
		}else {
			user = userService.findById(user.getUserId());
			
			}
		List jilist = null;
		LiuCode code = liuCodeService.findById(26);
		Integer xiaohaoType = 0;//0：其他积分支付  1：推荐用户积分支付
		if(code != null && code.getBusPerson().contains(prodId.toString())){
				//可使用积分是否足够
				jilist = jiFenRecordService.countNewUserJifen(user.getUserId(), 2);
				if(jilist.size() >0){
					if(Integer.parseInt(jilist.get(0).toString()) >=  prod.getScore().intValue()){
						xiaohaoType=1;
					}
				}
				//推荐用户积分不足
				if(xiaohaoType == 0){
					jilist = jiFenRecordService.countUserJifen(user.getUserId(), 2);
				}
				//jilist = jiFenRecordService.countUserJifen(user.getUserId(), 2);
		}else{
			//可使用积分是否足够
			jilist = jiFenRecordService.countUserJifen(user.getUserId(), 2);
		}
		
		
		if(jilist.size() >0){
			Integer dataji = Integer.parseInt(jilist.get(0).toString());
			if(dataji >= prod.getScore().intValue()){
				// 订单编号
				orderNo = "jf" + PublicUtil.getOrderNo();
				// 创建订单
				if (order == null)
				{
					order = new MiaoShaOrder();
				}
				
				OrderAddressVO orderAddress = orderAddressService.getAddressVoById(orderAddRessId);
				order.setSize(size);//这里记录运费
				order.setMoney(prod.getPrice()*size+prod.getTransFee());
				order.setOrdersBH(orderNo);
				order.setScore(prod.getScore());
				order.setPname(prod.getProdName()+"("+prod.getProdCode()+prod.getProdColor()+prod.getProdSize()+")");
				order.setToUserName(orderAddress.getUserName());
				order.setMobile(orderAddress.getMobile());
				order.setZipcode(orderAddress.getZipcode());
				order.setOrderStatus(0);// 待支付
				order.setCreateDate(new Date());
				order.setUserId(user.getUserId());
				order.setType(prod.getManufacturer());
				order.setOrderType("2");
				order.setQi(prod.getQi());
				order.setAddress(orderAddress.getAddress());
				order.setProductId(prodId); 
				order.setXinghuoquan(0);
//					order.setLevelValue(levelValue);
				
				Boolean boolean1 = false;
				if(code.getLiuMoney().contains(prodId.toString())){//是否需要身份证
					if(orderAddress != null && StringUtils.isNotBlank(orderAddress.getIdCard())){
						//判断身份证号是否存在，存在是否使用兑换过积分产品
						Integer countid = orderService.countUserIdCardProduct(user.getUserId(),prodId,orderAddress.getIdCard());
						if(countid == 0){
							order.setTel(orderAddress.getIdCard());
							boolean1=orderService.saveJifenOrder(order,user,prod,xiaohaoType);
						}
					}
				}else{
					boolean1=orderService.saveJifenOrder(order,user,prod,xiaohaoType);
				}
				/*if(boolean1 == true){
					JiFenRecord jf = new JiFenRecord();
					jf.setStatus(3);//待支付 兑换商品
					jf.setCreateDate(new Date());
					jf.setJifen(prod.getScore().intValue());
					jf.setMemo("用户"+user.getUserId()+":"+user.getUserName()+" 兑换商品 id"+prod.getProdId()+" 花费"+jf.getJifen()+"积分");
					jf.setOrderId(order.getOrdersId());
					jf.setUserId(user.getUserId());
					jiFenRecordService.save(jf);
				}*/
				
				json.put("success", boolean1);
				if(boolean1)
					json.put("ordersBh", orderNo);
		}else{
			json.put("success", false);
		}
		
			}else{
				json.put("success", false);
				json.put("message", "积分不足");
			}
			try {
				response.getWriter().print(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
	
	}

	public String toMiaoShaHou(){
		user = getSessionUser();
//		user = userService.findById(2104408);
//		request.getSession().setAttribute("loginUser",user);
		if(user == null){
			return "timeOut";
		}
		request.setAttribute("orderAddress", orderAddressService.findOrderAddressByUserId(user.getUserId()));
		return "toMiaoShaHou";
	}
	
	public void listDianpuYJ(){
		user = getSessionUser();
		JSONObject json = new JSONObject();
		json.put("success", false);
		if(user==null){
			json.put("timeOut", true);
		}else{
			DecimalFormat   fnum   =   new   DecimalFormat("##0.00"); //四舍五入，保留两位小数 
			Double ytxGoodYJ = yongJinService.findAllMoneyBuyUserIdAndType(user.getUserId(),2);
			Double ktxGoodYJ = yongJinService.findAllMoneyBuyUserIdAndType(user.getUserId(),1);
			Double totalGoodYJ = ytxGoodYJ+ktxGoodYJ;
			json.put("ytxGoodYJ", fnum.format(ytxGoodYJ));
			json.put("ktxGoodYJ", fnum.format(ktxGoodYJ));
			json.put("totalGoodYJ", fnum.format(totalGoodYJ));
			json.put("success", true);
		}
		try {
			ServletActionContext.getResponse().getWriter().print(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String toGoodBuy(){
		user = getSessionUser();
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
	public String toMianMoBuy(){
		user = getSessionUser();
     	//user = userService.findById(3480);
		if(user==null){
			return "timeOut";
		}
		user = userService.findById(user.getUserId());
		request.getSession().setAttribute("loginUser",user);
		if(user == null){
			return "timeOut";
		}
		Integer userId = user.getUserId();
		Product prod=productService.findById(prodId);
		List<Product> prodList=productService.findByProperty("prodType", prod.getProdType());
		BigInteger count=commentservice.commentCount(prodId);
		List<Integer> status=commentservice.getstatus(userId, prodId);
		int statu=0;
		for(int i=0;i<status.size();i++){
			int a = status.get(i);
			if(a==1||a==2){
				statu=1;
			}
		}
		request.setAttribute("prod", prod);
		request.setAttribute("typelist", prodList);
		request.setAttribute("typeqty", prodList.size());
		request.setAttribute("count", count);
		request.setAttribute("statu", statu);
		request.setAttribute("xhq",user.getRealSubmitMoney()==null?0:user.getRealSubmitMoney());
		request.setAttribute("xhq_money", user.getRealSubmitMoney()==null?0:user.getRealSubmitMoney()/2);
		request.setAttribute("orderAddress", orderAddressService.findOrderAddressByUserId(user.getUserId()));
		AccessToken accessToken = autosendmsgService.processAccessToken();
		String nonce_str = sign.create_nonce_str();
        String timestamp = sign.create_timestamp();
        String url = SystemMessage.getString("YUMING")+"/pay/payGoodAction!toMianMoBuy.action?prodId="+prodId;
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
		return "toMianMoBuy";
	}
	
	public void saveHouMS(){

		
		JSONObject json = new JSONObject();
		response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");	
		user = getSessionUser();
//		user = userService.findById(1820);
		if(user == null){
			json.put("success", false);
			json.put("timeOut", true);
		}else {
			Integer num = orderService.findSellerNumByQi(qi);
			if(miaoShaNum!=null&&num<=miaoShaNum){
				orderNo = "msh" + PublicUtil.getOrderNo();
				order = new MiaoShaOrder();
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
					order.setOrdersBH(orderNo);
					order.setPname("朝鲜2013年猴年整版80枚全新 雕刻版 大版票 外国邮票");
					order.setToUserName(orderAddress.getUserName());
					order.setMobile(orderAddress.getMobile());
					order.setZipcode(orderAddress.getZipcode());
					order.setOrderStatus(0);// 待支付
					order.setCreateDate(new Date());
					order.setUserId(user.getUserId());
					order.setAddress(orderAddress.getAddress());
					order.setQi(qi);
					order.setType(type);
					orderService.save(order);
					json.put("success", true);
					json.put("ordersBh", orderNo);
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
	//保存面膜订单
	public void saveMianMoOrder()
	{
		JSONObject json = new JSONObject();
		response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");	
		user = getSessionUser();
//		user = userService.findById(1820);
		Product prod=productService.findById(prodId);
		Integer size =MyUtils.isNumber(qty_item_1)?Integer.parseInt(qty_item_1):1;
		
		//购买数量不能大于限购数量
		if(size>prod.getLimitNum())
		{
			json.put("success", false);
			json.put("overbuy", true);
		}
		else if(size>prod.getStock())
		{
			json.put("success", false);
			json.put("stockless", true);
		}
		if(user == null){
			json.put("success", false);
			json.put("timeOut", true);
		}else {
			user = userService.findById(user.getUserId());
			// 订单编号
				orderNo = "go" + PublicUtil.getOrderNo();
				// 创建订单
				if (order == null)
				{
					order = new MiaoShaOrder();
				}
				//状态 0未获得  1已获得  2 支出  
			Integer inQty=xingHuoQuanRecordService.findAllMoneyByUserIdAndStatus(user.getUserId(), 1);
			Integer outQty=xingHuoQuanRecordService.findAllMoneyByUserIdAndStatus(user.getUserId(), 2); 
			Integer resultQty=inQty-outQty;//星火券余额
			//如果用户提交的星火券数量和计算的不一样则不能提交订单
			if((xhq_count!=0&&xhq_count>resultQty)||!resultQty.equals(user.getRealSubmitMoney()==null?0:user.getRealSubmitMoney()))
			{
				json.put("success", false);
				json.put("xhq_nok", true);
			}
			else if(xhq_count>0 && orderService.checkXingHuoQuanOrder(user)>1)
			{
				json.put("success", false);
				json.put("xhq_overbuy", true);
			}
			else
			{
				OrderAddressVO orderAddress = orderAddressService.getAddressVoById(orderAddRessId);
				order.setSize(size);//这里记录运费
				order.setMoney(prod.getPrice()*size+prod.getTransFee());
				order.setOrdersBH(orderNo);
				order.setPname(prod.getProdName()+"("+prod.getProdCode()+prod.getProdColor()+prod.getProdSize()+")");
				order.setToUserName(orderAddress.getUserName());
				order.setMobile(orderAddress.getMobile());
				order.setZipcode(orderAddress.getZipcode());
				order.setOrderStatus(0);// 待支付
				order.setCreateDate(new Date());
				order.setUserId(user.getUserId());
				order.setType(prod.getManufacturer());
				order.setQi(prod.getQi());
				order.setOrderType("1");
				order.setAddress(orderAddress.getAddress());
				order.setProductId(prodId);
				order.setXinghuoquan(xhq_count);
//					order.setLevelValue(levelValue);
				orderService.saveMianMoOrder(order,user,prod);
				json.put("success", true);
				json.put("need_pay", xhq_count/2<order.getMoney()?true:false);
				json.put("ordersBh", orderNo);
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
		user = getSessionUser();
//		user = userService.findById(1820);
		if(user == null){
			json.put("success", false);
			json.put("timeOut", true);
		}else {
			user = userService.findById(user.getUserId());
			// 订单编号
				orderNo = "go" + PublicUtil.getOrderNo();
				// 创建订单
				if (order == null)
					order = new MiaoShaOrder();
				
				OrderAddressVO orderAddress = orderAddressService.getAddressVoById(orderAddRessId);
				Double priceDouble = 2036.0;
				if(user.getCardId()!=null&&user.getCardId()!=0){
					Double zhekou = 0.75-0.05*user.getCardId();
					if(zhekou<0.3){
						zhekou = 0.3;
					}
					priceDouble *=zhekou;
				}
				DecimalFormat fmt = new DecimalFormat("#.##");
				Double price = Double.valueOf(fmt.format(priceDouble));
				Integer size = 1;
				if(qty_item_1!=null&&!"".equals(qty_item_1)){
					size = Integer.parseInt(qty_item_1);
				}
				order.setSize(size);//这里记录运费
				order.setMoney((price+30)*size);
				order.setOrdersBH(orderNo);
				order.setPname("神六组合套装");
				order.setToUserName(orderAddress.getUserName());
				order.setMobile(orderAddress.getMobile());
				order.setZipcode(orderAddress.getZipcode());
				order.setOrderStatus(0);// 待支付
				order.setCreateDate(new Date());
				order.setUserId(user.getUserId());
				order.setType(type);
				order.setProductId(1);
				order.setOrderType("1");
				order.setAddress(orderAddress.getAddress());
//					order.setLevelValue(levelValue);
				orderService.saveAndCFh(order,user);
				json.put("success", true);
				json.put("ordersBh", orderNo);
		
		}
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
						if(order.getOrdersBH().startsWith("jf")){
							String content="您好:\n您的订单已支付成功！\n"+"订单编号："+order.getOrdersBH()+"\n"+"支付运费："+order.getMoney()+"元,积分:"+order.getScore()+"兑换"+order.getPname()+"!";
							Boolean success=orderService.jifenPay(order,user);
							if(success){
								autosendmsgService.sendMsg(openid, content);
							}else{
								content = "您好:\n由于您的积分不足导致订单"+order.getOrdersBH() +"支付失败,我们将会在3个工作日之内给您退款!";
								autosendmsgService.sendMsg(openid, content);
							}
						}else{
							//信息通知用户
							String content="您好:\n您的订单已支付成功！\n"+"订单编号："+order.getOrdersBH()+"\n"+"支付金额："+order.getMoney()+"元,购买产品!";
							orderService.moneyPay(order,user);
							autosendmsgService.sendMsg(openid, content);
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
						jiFenRecordService.save(record);
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
								jiFenRecordService.save(record1);
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
										jiFenRecordService.save(record2);
										if(r2.getReferrerId()!=null){
											JiFenRecord record3 = new JiFenRecord();
											record3.setCreateDate(new Date());
											record3.setJifen(1);
											record3.setMemo("新忠实粉丝["+user.getUserId()+"]关注,赠送["+r2.getReferrerId()+"]"+record3.getJifen()+"积分");
											record3.setStatus(0);
											record3.setUserId(r2.getReferrerId());
											record3.setFromUserId(user.getUserId());
											record3.setType(5);
											jiFenRecordService.save(record3);
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
		//total_fee = (int)(order.getMoney()*100)+"";
		//如果有火星券支付应该减除
		Integer xinghoujuan = 0;
		if(order.getXinghuoquan() != null && order.getXinghuoquan() > 0){
			xinghoujuan = order.getXinghuoquan();
		}
		total_fee = (int)(order.getMoney()*100-xinghoujuan*100/2)+"";
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

	public MiaoShaOrder getOrder() {
		return order;
	}

	public void setOrder(MiaoShaOrder order) {
		this.order = order;
	}

	public JifenUser getUser() {
		return user;
	}

	public void setUser(JifenUser user) {
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
