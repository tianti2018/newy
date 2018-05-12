package com.tw.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.Namespace;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import sun.misc.BASE64Encoder;

import com.opensymphony.xwork2.ActionChainResult;
import com.tw.web.dao.ApplyReturnPurchaseDao;
import com.tw.web.dao.CardDAO;
import com.tw.web.dao.EggActivityUserDAO;
import com.tw.web.dao.FhRecordDAO;
import com.tw.web.dao.OrdersDAO;
import com.tw.web.dao.OrdersProductsDAO;
import com.tw.web.dao.ProductsDAO;
import com.tw.web.dao.TAreaDao;
import com.tw.web.dao.UserDAO;
import com.tw.web.dao.UserOrderDAO;
import com.tw.web.hibernate.persistent.ApplyReturnPurchase;
import com.tw.web.hibernate.persistent.Card;
import com.tw.web.hibernate.persistent.EggActivityUser;
import com.tw.web.hibernate.persistent.FhRecord;
import com.tw.web.hibernate.persistent.Orders;
import com.tw.web.hibernate.persistent.Products;
import com.tw.web.hibernate.persistent.TArea;
import com.tw.web.hibernate.persistent.User;
import com.tw.web.hibernate.persistent.UserOrder;
import com.tw.web.service.LoginService;
import com.tw.web.util.CommUtils;
import com.tw.web.util.HttpConnectionUtil;

@SuppressWarnings("serial")
@ParentPackage("app-default")
@Namespace("")
@Results(
		{
			@Result(name="initAdd", 			value="/WEB-INF/jsp/products/addProducts.jsp"),
			@Result(name="initModify", 		value="/WEB-INF/jsp/products/addProducts.jsp"),
			@Result(name="create", 				value="/WEB-INF/jsp/products/productsList.jsp"),
			@Result(name="listAll", 			value="/WEB-INF/jsp/products/productsList.jsp"),
			@Result(name="listAllApplys", 			value="/WEB-INF/jsp/products/listAllApplys.jsp"),
			@Result(name="listAllOrderList", 			value="/WEB-INF/jsp/products/ordersList.jsp"),
			@Result(name="initkuaidi", 			value="/WEB-INF/jsp/products/addKuaidi.jsp"),
			@Result(name="beizhu", 			value="/WEB-INF/jsp/products/addbeizhu.jsp"),
			@Result(name="applyReturnPurchase", 			value="/WEB-INF/jsp/products/applyReturnPurchase.jsp"),
			@Result(name="goBackList", type=ActionChainResult.class, 	value="role", params = {"method", "listAll"}),
			@Result(name="goBackList1", type=ActionChainResult.class, 	value="orders", params = {"method", "listAllOrderList"}),
			@Result(name="dealApply", type=ActionChainResult.class, 	value="orders", params = {"method", "listAllApplys"}),
			@Result(name="initCard", type=ActionChainResult.class, 	value="card", params = {"method", "initCard"})
		}
)
public class OrdersAction extends ExtJSONActionSuport {
	
	private LoginService loginService;
	private ProductsDAO productsDAO;
	private OrdersDAO ordersDAO;
	private OrdersProductsDAO ordersProductsDAO;
	private CardDAO cardDAO;
	private FhRecordDAO fhRecordDAO;
	private UserOrderDAO userOrderDAO;
	private UserDAO userDAO;
	private EggActivityUserDAO eggActivityUserDAO;
	private TAreaDao tAreaDao;
	private ApplyReturnPurchaseDao applyReturnPurchaseDao;
	
	private String toUserName;
	private String mobile;
	private String address;
	private String zipcode;
	private String pname;
	private String cardNo;
	private String cardPassword;
	private Integer ordersId;
	private String kuaidiName;
	private String kuaidiNo;
	private String ordersBH;
	private String loginName;
	private String userName;
	private String orderType;
	private String applyNum;
	private Integer cardId;
	private Integer userId;
	private Integer parentId;
	private String parentUserName;
	private File importFile;
	private String totalMoney;
	private String fromDate;
	private String endDate;
	
	public String getFromDate()
	{
		return fromDate;
	}

	public void setFromDate(String fromDate)
	{
		this.fromDate = fromDate;
	}

	public String getEndDate()
	{
		return endDate;
	}

	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}

	public String getTotalMoney()
	{
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney)
	{
		this.totalMoney = totalMoney;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getKuaidiName() {
		return kuaidiName;
	}

	public void setKuaidiName(String kuaidiName) {
		this.kuaidiName = kuaidiName;
	}

	public String getKuaidiNo() {
		return kuaidiNo;
	}

	public void setKuaidiNo(String kuaidiNo) {
		this.kuaidiNo = kuaidiNo;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public UserOrderDAO getUserOrderDAO() {
		return userOrderDAO;
	}
	
	public TAreaDao gettAreaDao() {
		return tAreaDao;
	}
	@Autowired
	public void settAreaDao(TAreaDao tAreaDao) {
		this.tAreaDao = tAreaDao;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}
	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Autowired
	public void setUserOrderDAO(UserOrderDAO userOrderDAO) {
		this.userOrderDAO = userOrderDAO;
	}
	public FhRecordDAO getFhRecordDAO() {
		return fhRecordDAO;
	}
	@Autowired
	public void setFhRecordDAO(FhRecordDAO fhRecordDAO) {
		this.fhRecordDAO = fhRecordDAO;
	}
	public LoginService getLoginService() {
		return loginService;
	}
	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	public ProductsDAO getProductsDAO() {
		return productsDAO;
	}
	@Autowired
	public void setProductsDAO(ProductsDAO productsDAO) {
		this.productsDAO = productsDAO;
	}
	public EggActivityUserDAO getEggActivityUserDAO() {
		return eggActivityUserDAO;
	}

	@Autowired
	public void setEggActivityUserDAO(EggActivityUserDAO eggActivityUserDAO) {
		this.eggActivityUserDAO = eggActivityUserDAO;
	}
	public ApplyReturnPurchaseDao getApplyReturnPurchaseDao() {
		return applyReturnPurchaseDao;
	}
	@Autowired
	public void setApplyReturnPurchaseDao(
			ApplyReturnPurchaseDao applyReturnPurchaseDao) {
		this.applyReturnPurchaseDao = applyReturnPurchaseDao;
	}

	public Integer getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(Integer ordersId) {
		this.ordersId = ordersId;
	}
	public CardDAO getCardDAO() {
		return cardDAO;
	}
	@Autowired
	public void setCardDAO(CardDAO cardDAO) {
		this.cardDAO = cardDAO;
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public OrdersDAO getOrdersDAO() {
		return ordersDAO;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCardPassword() {
		return cardPassword;
	}
	public void setCardPassword(String cardPassword) {
		this.cardPassword = cardPassword;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrdersBH() {
		return ordersBH;
	}

	public void setOrdersBH(String ordersBH) {
		this.ordersBH = ordersBH;
	}

	@Autowired
	public void setOrdersDAO(OrdersDAO ordersDAO) {
		this.ordersDAO = ordersDAO;
	}
	public OrdersProductsDAO getOrdersProductsDAO() {
		return ordersProductsDAO;
	}
	@Autowired
	public void setOrdersProductsDAO(OrdersProductsDAO ordersProductsDAO) {
		this.ordersProductsDAO = ordersProductsDAO;
	}
	public String getApplyNum() {
		return applyNum;
	}

	public void setApplyNum(String applyNum) {
		this.applyNum = applyNum;
	}

	public String initkuaidi () {
		Orders orders = (Orders)ordersDAO.findById(ordersId);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("orders", orders);
		return "initkuaidi";
	}
	public String beizhu () {
		Orders orders = (Orders)ordersDAO.findById(ordersId);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("orders", orders);
		return "beizhu";
	}
	
	public String listAll () throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer count = productsDAO.cout_size(null, null);
				
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count));
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
				
		List<Products> litPager = productsDAO.findAllPagerList_new(null, null, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		request.setAttribute("litPager", litPager);
		
		User loginUser = (User)request.getSession().getAttribute("user");
		//查看当前用户是否进行过第一次购买
		UserOrder userOrder = userOrderDAO.findUserOrderByUserId(loginUser.getUserId());
		if (null!=userOrder) {
			request.setAttribute("buyFlag", "1");
		}
		return "listAll";
	}
	
	public String initAdd () throws Exception {
		return "initAdd";
	}
	
	public String updateCard () throws Exception { //跟新卡的状态
		
		return "initAdd";
	}
	
	
	public String create() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		User loginUser = (User)request.getSession().getAttribute("user");
		
//		Card card = cardDAO.findCardByCardNo(cardNo, cardPassword,"1");
		Card card = cardDAO.findCardByCardNo(cardNo, cardPassword,null);
		if (null!=card) {
			if ("1".equals(pname)) {
				card.setCardStatus("1");
				card.setCardType("3");
				card.setCreateDate(new Date());
				card.setMemo("登录会员编号【"+loginUser.getUserId()+"】会员名称：【"+loginUser.getLoginName()+"】");
				cardDAO.update(card);
				
				Orders orders = new Orders();
				//订单编号规则 88+日期+随机六位数
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
				String fd = sdf.format(new Date());
				orders.setOrdersBH("88"+fd+CommUtils.getSixRandom());
				orders.setToUserName(toUserName);
				orders.setAddress(address);
				orders.setMobile(mobile);
				orders.setOrderType("1"); //首次购买
				pname = "1".equals(pname)?"1套面膜":"1套面膜";
				orders.setPname(pname);
				orders.setZipcode(zipcode);
				orders.setCreateDate(new Date());
				orders.setUser(loginUser);
				orders.setCardId(card.getCardId());
				ordersDAO.create(orders);
				
				//参与活动
				//给用户一个序号
				BigInteger maxOrder = userDAO.findFinalOrder(loginUser.getFlagorg());
				maxOrder = null==maxOrder?BigInteger.valueOf(1):maxOrder;
				UserOrder userOrder = new UserOrder();
				//userOrder.setUoId(maxOrder);
				userOrder.setFlagorg(loginUser.getFlagorg());
				//userOrder.setCardId(card.getCardId());
				userOrder.setUserId(loginUser.getUserId());
				userOrder.setCreateDate(new Date());
				userOrder.setOrderOne(maxOrder);
				userOrder.setCardId(card.getCardId());
				userOrder.setStatus("0");
				Integer parentUoId = CommUtils.parentId(maxOrder.intValue()); //找到他的父亲节点的序号
				UserOrder userOrder1 = (UserOrder)userOrderDAO.findUserOrderByAppcenterIdAndorderId(loginUser.getFlagorg(), parentUoId);
				if (null!=userOrder1) {
					userOrder.setParentUoId(userOrder1.getUoId());
				}
				userOrderDAO.create(userOrder);
				
				//记录当前用户已经购买
				if (null==loginUser.getBuyCount()||0==loginUser.getBuyCount()) {
					loginUser.setBuyCount(1);
				}
			
				else {
					loginUser.setBuyCount(loginUser.getBuyCount()+1);
				}
				userDAO.update(loginUser);
				
				//插入一条抽奖记录数据
				EggActivityUser eggActivityUser=new EggActivityUser();
				eggActivityUser.setActivityId(8);
				eggActivityUser.setCurrNum(0);
				eggActivityUser.setMaxNum(1);
				eggActivityUser.setUserId(loginUser.getUserId());
				eggActivityUserDAO.saveOrUpdate(eggActivityUser);
				
				User appCenter = (User)userDAO.findById(loginUser.getAppCenterId());
				if (appCenter.getAreaId()!=null) {
					// 查看服务中心的服务地址是哪里
					TArea tArea = (TArea)tAreaDao.findById(appCenter.getAreaId());
					//省级服务中心  新增粉丝*40元  市级服务中心  新增粉丝*30元  区县服务中心 新增粉丝*20元
					Double dailiMoney = 0.0;
					switch (Integer.valueOf(tArea.getAreaType())) { // 判断当前服务中心是省级
						case 1:
							dailiMoney = 40.00;
							break;
						case 2:
							dailiMoney = 30.00;
							break;
						case 3:dailiMoney = 20.00;break;
					}
					
					//给报单中心10元
					FhRecord fhRecord = new FhRecord();
					fhRecord.setUser(appCenter);
					fhRecord.setFhmoney(loginUser.getAppCenterId().intValue()==1?10.00:dailiMoney);
					fhRecord.setCreateDate(new Date());
					fhRecord.setFhType("4"); 
					//fhRecord.setUoId(userOrder.getUoId());
					fhRecord.setMemo("销售服务中心销售服务提成"); 
					fhRecordDAO.create(fhRecord);
				}
				
				/*if ("A".equals(appCenter.getFlagorg())&&(1!=appCenter.getUserId())) { //一级销售服务中心 给5元
					FhRecord fhRecord2 = new FhRecord();
					User userOne = (User)userDAO.findById(1);
					fhRecord2.setUser(userOne);
					fhRecord2.setFhmoney(5.00);
					fhRecord2.setCreateDate(new Date());
					fhRecord2.setFhType("4");  //给一级报单中心多5元
					//fhRecord.setUoId(userOrder.getUoId());
					fhRecord2.setMemo("二级销售服务中心"+appCenter.getLoginName()+"销售服务提成"); 
					fhRecordDAO.create(fhRecord2);
				}*/
				
				User referrer = (User)userDAO.findById(loginUser.getReferrerId());
				//给邀请人66积分
				FhRecord fhRecord1 = new FhRecord();
				fhRecord1.setUser(referrer);
				fhRecord1.setFhmoney(66.00);
				fhRecord1.setCreateDate(new Date());
				fhRecord1.setFhType("5"); 
				//fhRecord1.setUoId(userOrder.getUoId());
				fhRecordDAO.create(fhRecord1);
				
				// 推荐人 奖励规则
				//第一次推荐5人，奖励1套  第二推荐5人，奖励1.5套  第三次推荐5人，奖励2套  之后每推荐5人，奖励2套
				BigInteger referrerCount = userDAO.findCountReferrerId(referrer.getUserId());
				referrerCount = null==referrerCount?BigInteger.valueOf(0):referrerCount;
				Map<String, Object> conditionProperties = new HashMap<String, Object>();
				Map<String, Integer> compare = new HashMap<String, Integer>();
				conditionProperties.put("orderType", "3");
				conditionProperties.put("userId", referrer.getUserId());
				compare.put("orderType", 0);
				compare.put("userId", 0);
				int orderCount = ordersDAO.cout_size(conditionProperties, compare);
				List listOrders = ordersDAO.findEntityByPropertiName("userId", referrer.getUserId());
				Orders orders2 = null;
				if (orderCount==0) { //第一次没有订单
					if (listOrders.size()!=0) {
						orders2 = (Orders)listOrders.get(0);
						int size = referrerCount.intValue()/5;
						for (int i=1;i<=size;i++) {
							Orders orders3 = new Orders();
							orders3.setAddress(orders2.getAddress());
							orders3.setCreateDate(new Date());
							orders3.setToUserName(orders2.getToUserName());
							orders3.setMobile(orders2.getMobile());
							orders3.setUser(referrer);
							orders3.setZipcode(orders2.getZipcode());
							orders3.setOrdersBH("FX"+fd+CommUtils.getSixRandom());
							orders3.setOrderType("3");
							switch (i) {
							case 1:
								orders3.setPname("1套面膜");
								ordersDAO.create(orders3);
								break;
							case 2:
								orders3.setPname("1.5套面膜");
								ordersDAO.create(orders3);
								break;
							case 3:
								orders3.setPname("2套面膜");
								ordersDAO.create(orders3);
								break;
							default: 
								orders3.setPname("2套面膜");
								ordersDAO.create(orders3);
								break;
							}
						}
					}
				}
				else { //已经有订单了
					int referrerCount2 = (referrerCount.intValue()-(orderCount*5))/5;
					if (listOrders.size()!=0&&referrerCount2==1) {
						orders2 = (Orders)listOrders.get(0);
						String pname = "";
						switch (orderCount) { // orderCount==1 说明这是第二个邀请5个人  orderCount==2 说明这是第三个邀请5个人  大于2 说明是第4次甚至更多
						case 1:
							pname="1.5套面膜";
							break;
						case 2:
							pname="2套面膜";
							break;
						default: 
							pname="2套面膜";
							break;
						}
						Orders orders3 = new Orders();
						orders3.setPname(pname);
						orders3.setAddress(orders2.getAddress());
						orders3.setCreateDate(new Date());
						orders3.setToUserName(orders2.getToUserName());
						orders3.setMobile(orders2.getMobile());
						orders3.setUser(referrer);
						orders3.setZipcode(orders2.getZipcode());
						orders3.setOrdersBH("FX"+fd+CommUtils.getSixRandom());
						orders3.setOrderType("3");
						ordersDAO.create(orders3);
					}
				}
				
				Map map = new HashMap();
				for (int i=1;i<11;i++) {
					map.put(i, i);
				}
				//即时结算给积分
				fhuserOrder(loginUser.getFlagorg(),parentUoId,1,map);
				
				ServletActionContext.getRequest().setAttribute("message","参与活动成功");
				ServletActionContext.getRequest().setAttribute("maxOrder",maxOrder);
			}
			else if ("2".equals(pname)) {
				card.setCardStatus("1");
				card.setCreateDate(new Date());
				card.setCardType("4"); //复购
				card.setMemo("复购登录会员编号【"+loginUser.getUserId()+"】会员名称：【"+loginUser.getLoginName()+"】");
				cardDAO.update(card);
				
				Orders orders = new Orders();
				//订单编号规则 88+日期+随机六位数
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
				String fd = sdf.format(new Date());
				orders.setOrdersBH("FG"+fd+CommUtils.getSixRandom());
				orders.setToUserName(toUserName);
				orders.setAddress(address);
				orders.setMobile(mobile);
				pname = "1".equals(pname)?"1套面膜":"1套面膜";
				orders.setPname(pname);
				orders.setZipcode(zipcode);
				orders.setCreateDate(new Date());
				orders.setUser(loginUser);
				orders.setCardId(card.getCardId());
				orders.setOrderType("2"); //重复购买
				ordersDAO.create(orders);
				
				//赠送300积分
				FhRecord fhRecord = new FhRecord();
				fhRecord.setUser(loginUser);
				fhRecord.setFhmoney(300.00);
				fhRecord.setCreateDate(new Date());
				fhRecord.setFhType("11"); //复购赠送300积分
				//fhRecord1.setUoId(userOrder.getUoId());
				fhRecordDAO.create(fhRecord);
			}
		}
		else {
			ServletActionContext.getRequest().setAttribute("message", "您的充值卡号或者密码不正确请找管理人员核对 ");
			return "initCard";
		}
		
		ServletActionContext.getRequest().setAttribute("message", "购买成功");
		return "initCard";
	}
	
	public void fhuserOrder(String flagorg,Integer parentUoId,int flag,Map map) {
		if (flag<11) {
			UserOrder userOrder = (UserOrder)userOrderDAO.findUserOrderByAppcenterIdAndorderId(flagorg, parentUoId);
			//UserOrder userOrder = (UserOrder)userOrderDAO.findById(parentUoId);
			if (null==userOrder) {
				return;
			}
			Double totalJifen = fhRecordDAO.findTotalJiFenByOrders(userOrder.getUoId());
			if (map.containsKey(userOrder.getOrderOne().intValue())) { //公司
				if (totalJifen>=6000000) {
					return;
				}
			}
			
			if (null!=userOrder) {
				FhRecord fhRecord=fhRecordDAO.findFhRecordByUidAndFid(userOrder.getUserId(),"1");
				if(fhRecord==null){
					User user = (User)userDAO.findById(userOrder.getUserId());
					fhRecord=new FhRecord();
					fhRecord.setUser(user);
					fhRecord.setFhmoney(15.00);
					fhRecord.setCreateDate(new Date());
					fhRecord.setFhType("3"); // 公司给会员15积分
					fhRecord.setUoId(userOrder.getUoId());
					fhRecordDAO.create(fhRecord);
				}else{
					fhRecord.setDingqiFlag(null);
					fhRecordDAO.update(fhRecord);
				}
				Integer parentUoIdOne = CommUtils.parentId(parentUoId.intValue());
				flag++;
				fhuserOrder(flagorg,parentUoIdOne,flag,map);
			}
		}
	}
	
	public String listAllOrderList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
	/*	Object loginUser = null;
		loginUser = request.getSession().getAttribute("user");
		*/
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		Map<String, Integer> compare = new HashMap<String, Integer>();
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("createDate", false);
		if (null!=userId &&! "".equals(userId)) {
			conditionProperties.put("t.userId", userId);
			compare.put("t.userId", 13);
		}
		if(null != userName && !userName.isEmpty()){
			conditionProperties.put("t.userName", userName);
			compare.put("t.userName", 14);
		}
		if(null != parentId){
			conditionProperties.put("t.referrerId", parentId);
			compare.put("t.referrerId", 13);
		}
		
		if (null!=orderType &&! "".equals(orderType)) {
			conditionProperties.put("order_status", new Byte(orderType));
			compare.put("order_status", 0);
			
		}else {
			orderType = "1";
			conditionProperties.put("order_status", new Byte(orderType));
			compare.put("order_status", 0);
		}
		request.setAttribute("userFlag", Integer.parseInt(orderType));
		if (null!=toUserName &&! "".equals(toUserName)) {
			conditionProperties.put("toUserName", toUserName);
			compare.put("toUserName", 2);
		}
		if (null!=ordersBH &&! "".equals(ordersBH)) {
			conditionProperties.put("ordersBH", ordersBH);
			compare.put("ordersBH", 2);
		}
		if(null!=mobile &&! "".equals(mobile)){
			conditionProperties.put("mobile", mobile);
			compare.put("mobile", 2);
		}
		if(null!=fromDate&&!"".equals(fromDate)/* &&endDate==null&&"".equals(endDate)*/){
			System.out.println("---------------------------------------"+fromDate);
			Date date=null;
			SimpleDateFormat   formatter   = 
					new   SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");
			try
			{
				date=formatter.parse(fromDate);
			} catch (ParseException e)
			{
				
				e.printStackTrace();
			}
			conditionProperties.put("createDate", date);
			compare.put("createDate", 8);
		}
		if(null!=endDate &&!"".equals(endDate)/*&&fromDate==null&&"".equals(fromDate)*/){
			System.out.println("+++++++++++++++++++++++++"+endDate);
			Date date=null;
			SimpleDateFormat   formatter   = 
					new   SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");
			try
			{
				date=formatter.parse(endDate);
			} catch (ParseException e)
			{
				
				e.printStackTrace();
			}
			conditionProperties.put("createDate", date);
			compare.put("createDate", 9);
		}
		if(fromDate!=null &&! "".equals(fromDate)&&null!=endDate &&! "".equals(endDate)){
			System.out.println("=========================");
			Date date=null;
		if(null!=fromDate &&! "".equals(fromDate)){
			
			SimpleDateFormat   formatter   = 
					new   SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");
			try
			{
				date=formatter.parse(fromDate);
			} catch (ParseException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Date date2=null;
		if(null!=endDate &&! "".equals(endDate)){
			
			SimpleDateFormat   formatter   = 
					new   SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");
			try
			{
				date2=formatter.parse(endDate);
			} catch (ParseException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Date[] dates = {date,date2};
		conditionProperties.put("createDate", dates);
		compare.put("createDate", 10);
		}
//		conditionProperties.put("order_status", new Byte("2"));
//		compare.put("order_status", 1);
		int count_size =ordersDAO.cout_size1(conditionProperties, compare);
		
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count_size));
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
		
		List<Orders> litPager = ordersDAO.findAllPagerList_new1(conditionProperties, compare, sort, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		if(litPager.size()>0){
			for(Orders order:litPager){
				if(order.getProductsId() != null){
					Products products = (Products) productsDAO.findById(order.getProductsId());
					if(products!=null){
						order.setPname(products.getName());
						order.setProductImg(products.getHeadUrl());
					}
				}
			}
		}
		request.setAttribute("litPager", litPager);
		request.setAttribute("order_status", orderType);
		return "listAllOrderList";
	}
	
	public void getRefer(){
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		Map<String, Integer> compare = new HashMap<String, Integer>();
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("createDate", false);
		
		if (null != ordersId && !"".equals(ordersId)) {
			conditionProperties.put("userId", ordersId);
			compare.put("userId", 0);
		}
		List<Orders> litPager = ordersDAO.findAllPagerList_new1(conditionProperties, compare, sort, 0, 1, "page");
		Orders orders = null;
		if(litPager.size()>0){
			orders = litPager.get(0);
		}
		JSONObject json = new JSONObject();
		if(orders !=null){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			try {
				json.put("phone", orders.getMobile());
				json.put("toUserName", orders.getToUserName());
				User refuser = orders.getUser().getReferrer();
				if(refuser == null){
					json.put("refuserId", "无");
					json.put("refuserName", "无");
					json.put("refuserHeadUrl", "无");
				}else{
					json.put("refuserId", refuser.getUserId());
					if(refuser.getUserName()==null||refuser.getUserName().trim().equals(""))
						json.put("refuserName", "无");
					else
						json.put("refuserName", refuser.getUserName());
					if(refuser.getHeadUrl()==null||refuser.getHeadUrl().trim().equals(""))
						json.put("refuserHeadUrl", "无");
					else
						json.put("refuserHeadUrl", refuser.getHeadUrl());
				}
				response.getWriter().write(json.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public String exportToExcel(){
		// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("sheet");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        HSSFFont font = wb.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 16);//设置字体大小
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(style);
        cell.setCellValue("订单编号");
        
        cell = row.createCell(1);
        cell.setCellStyle(style);
        cell.setCellValue("下单时间");
        
        cell = row.createCell(2);
        cell.setCellStyle(style);
        cell.setCellValue("推荐人编号");
        
        cell = row.createCell(3);
        cell.setCellStyle(style);
        cell.setCellValue("推荐人昵称");
        
        cell = row.createCell(4);
        cell.setCellStyle(style);
        cell.setCellValue("下单人编号");
        
        cell = row.createCell(5);
        cell.setCellStyle(style);
        cell.setCellValue("下单人昵称");
        
        cell = row.createCell(6);
        cell.setCellStyle(style);
        cell.setCellValue("是否取消关注");
        
        cell = row.createCell(7);
        cell.setCellStyle(style);
        cell.setCellValue("收货人姓名");
        
        cell = row.createCell(8);
        cell.setCellStyle(style);
        cell.setCellValue("收货人电话");
        
        cell = row.createCell(9);
        cell.setCellStyle(style);
        cell.setCellValue("送货地址");
        
        cell = row.createCell(10);
        cell.setCellStyle(style);
        cell.setCellValue("订单状态");
        
        cell = row.createCell(11);
        cell.setCellStyle(style);
        cell.setCellValue("产品类型");
        
        cell = row.createCell(12);
        cell.setCellStyle(style);
        cell.setCellValue("购买数量");
        
        cell = row.createCell(13);
        cell.setCellStyle(style);
        cell.setCellValue("备注");
        
        cell = row.createCell(14);
        cell.setCellStyle(style);
        cell.setCellValue("快递名称");
        
        cell = row.createCell(15);
        cell.setCellStyle(style);
        cell.setCellValue("快递编号");
        
        cell = row.createCell(16);
        cell.setCellStyle(style);
        cell.setCellValue("头像链接");
        
        Map<String, Object> conditionProperties = new HashMap<String, Object>();
		Map<String, Integer> compare = new HashMap<String, Integer>();
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("createDate", false);
		
		if (null!=orderType &&! "".equals(orderType)) {
			conditionProperties.put("order_status", new Byte(orderType));
			compare.put("order_status", 0);
			
		}else {
			orderType = "1";
			conditionProperties.put("order_status", new Byte(orderType));
			compare.put("order_status", 0);
			
		}
		if (null!=userId &&! "".equals(userId)) {
			conditionProperties.put("t.userId", userId);
			compare.put("t.userId", 0);
		}
		if(null != userName && !userName.isEmpty()){
			conditionProperties.put("t.userName", userName);
			compare.put("t.userName", 2);
		}
		if(null != parentId){
			conditionProperties.put("t.referrerId", parentId);
			compare.put("t.referrerId", 0);
		}
		if (null!=toUserName &&! "".equals(toUserName)) {
			conditionProperties.put("toUserName", toUserName);
			compare.put("toUserName", 2);
		}
		if (null!=ordersBH &&! "".equals(ordersBH)) {
			conditionProperties.put("ordersBH", ordersBH);
			compare.put("ordersBH", 2);
		}
		if(null!=mobile &&! "".equals(mobile)){
			conditionProperties.put("mobile", mobile);
			compare.put("mobile", 2);
		}
		
		List<Orders> list = ordersDAO.findAllPagerList_new1(conditionProperties, compare, sort, 0, 0, "all");
  
        for (int i = 0; i < list.size(); i++)  
        {  
            row = sheet.createRow(i + 1);
            Orders order = list.get(i);
            // 第四步，创建单元格，并设置值  
            row.createCell(0).setCellValue(order.getOrdersBH());//订单编号
            row.createCell(1).setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getCreateDate())); //下单时间
            User refererUser = order.getUser().getReferrer();
			if(refererUser != null){//推荐人编号和昵称
				row.createCell(2).setCellValue(refererUser.getUserId());
				row.createCell(3).setCellValue(refererUser.getUserName());
			}else {
				row.createCell(2).setCellValue("");
				row.createCell(3).setCellValue("");
			}
			User u = order.getUser();
			if(u != null){//下单人编号和昵称
				row.createCell(4).setCellValue(u.getUserId());
				row.createCell(5).setCellValue(u.getUserName());
				row.createCell(16).setCellValue(u.getHeadUrl()==null?"":u.getHeadUrl());//头像链接
				if(u.getSubscribe()==0){//是否取消关注
					row.createCell(6).setCellValue("否");
				}else if(u.getSubscribe()==1){
					HSSFFont font1 = wb.createFont();
					font1.setColor(HSSFColor.RED.index);
					HSSFCellStyle style1 = wb.createCellStyle();  
					style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
					style1.setFont(font1);
					HSSFCell cell1= row.createCell(6);
					cell1.setCellStyle(style1);
					cell1.setCellValue("是");
				}
			}else {
				row.createCell(4).setCellValue("");
				row.createCell(5).setCellValue("");
				row.createCell(6).setCellValue("");
				row.createCell(16).setCellValue("");//头像链接
			}
            
            row.createCell(7).setCellValue(order.getToUserName()==null?"":order.getToUserName());//收货人姓名
            row.createCell(8).setCellValue(order.getMobile()==null?"":order.getMobile());//收货人电话
            row.createCell(9).setCellValue(order.getAddress()==null?"":order.getAddress());//送货地址
            if(order.getOrder_status()==0){//订单状态
            	row.createCell(10).setCellValue("未支付");
            }else if(order.getOrder_status()==1){
            	row.createCell(10).setCellValue("已支付");
            }else if(order.getOrder_status()==2){
            	row.createCell(10).setCellValue("已退货");
            }else if(order.getOrder_status()==3){
            	row.createCell(10).setCellValue("已发货");
            }else if(order.getOrder_status()==4){
            	row.createCell(10).setCellValue("已完成");
            }else if(order.getOrder_status()==5){
            	row.createCell(10).setCellValue("已退货");
            }else if(order.getOrder_status()==6){
            	row.createCell(10).setCellValue("已收货");
            }else {
            	row.createCell(10).setCellValue("");
			}
            row.createCell(11).setCellValue(order.getOrderType()==null?"":order.getOrderType());//产品类型
            row.createCell(12).setCellValue(order.getSize()==null?"":order.getSize());//购买数量
            row.createCell(13).setCellValue(order.getComments()==null?"":order.getComments());//备注信息
            if(order.getKuaidiName() != null){
	            if(order.getKuaidiName().equals("0")){//快递名称
	            	row.createCell(14).setCellValue("圆通");
	            }else if(order.getKuaidiName().equals("1")){
	            	row.createCell(14).setCellValue("顺丰");
	            }else if(order.getKuaidiName().equals("2")){
	            	row.createCell(14).setCellValue("申通");
	            }else if(order.getKuaidiName().equals("3")){
	            	row.createCell(14).setCellValue("中通");
	            }else if(order.getKuaidiName().equals("4")){
	            	row.createCell(14).setCellValue("韵达");
	            }else if(order.getKuaidiName().equals("5")){
	            	row.createCell(14).setCellValue("EMS");
	            }else if(order.getKuaidiName().equals("6")){
	            	row.createCell(14).setCellValue("宅急送");
	            }else if(order.getKuaidiName().equals("7")){
	            	row.createCell(14).setCellValue("全峰");
	            }else if(order.getKuaidiName().equals("8")){
	            	row.createCell(14).setCellValue("天天快递");
	            }else if(order.getKuaidiName().equals("9")){
	            	row.createCell(14).setCellValue("自提");
	            }else if(order.getKuaidiName().equals("10")){
	            	row.createCell(14).setCellValue("国通");
	            }
            }else {
            	row.createCell(14).setCellValue("");
			}
            row.createCell(15).setCellValue(order.getKuaidiNo()==null?"":order.getKuaidiNo());//快递编号
           
        }
        // 第六步，将文件存到指定位置  
        try  
        {  
//            FileOutputStream fout = new FileOutputStream("E:/students.xls"); 
        	HttpServletResponse response = ServletActionContext.getResponse();
        	HttpServletRequest request = ServletActionContext.getRequest();
        	String filename = null;
        	if (null!=orderType &&! "".equals(orderType)) {
    			if(orderType.equals("0")){
    				filename = "未支付订单列表";
    			}else if(orderType.equals("1")){
    				filename = "已支付订单列表";
    			}else if(orderType.equals("2")){
    				filename = "已退货订单列表";
    			}else if(orderType.equals("3")){
    				filename = "已发货订单列表";
    			}else if(orderType.equals("4")){
    				filename = "已完成订单列表";
    			}else if(orderType.equals("5")){
    				filename = "已退货订单列表";
    			}else if(orderType.equals("6")){
    				filename = "已收货订单列表";
    			}
    		}else {
    			filename = "已支付订单列表";
    		}
        	filename += new String((new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date())).getBytes());
        	filename += ".xls";
        	filename = encodeFilename(request.getHeader("user-agent"),filename);
        	response.setHeader("Content-disposition","attachment; filename=" +filename);
        	OutputStream outputStream = response.getOutputStream();
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        } 
		return null;
	}
	
	public String importExcelToOrders(){
		InputStream is=null;
		int jiShu=0;
		if(importFile!=null){
		try {
			is = new FileInputStream(importFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        XSSFWorkbook hssfWorkbook=null;
		try {
			hssfWorkbook = new XSSFWorkbook(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String hql = "from Orders s where s.kuaidiNo is null and order_status='1'";
		//List<Orders> orders = ordersDAO.findAll();
		List<Orders> orders = ordersDAO.findbyHql(hql);
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
            	
                XSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }
                	//	获取第一列
                	XSSFCell bh=hssfRow.getCell(0);
                	if(bh==null){
                		continue;
                	}
                	String ddbh=getValue(bh);       //订单编号
                    //获取第二列
                	XSSFCell sj=hssfRow.getCell(1);
                	if(sj==null){
                		continue;
                	}
                	String kdgs=getValue(sj);			//快递名称
                    //获取第三列
                	XSSFCell pn=hssfRow.getCell(2);
					if(pn==null){
						continue;
					}
					String kddh=getValue(pn);		//快递单号
					byte i=(byte)3;
					String kdname="";
					if("圆通".equals(kdgs)){
						kdname="0";
					}else if("顺丰".equals(kdgs)){
						kdname="1";
					}else if("申通".equals(kdgs)){
						kdname="2";
					}else if("中通".equals(kdgs)){
						kdname="3";
					}else if("韵达".equals(kdgs)){
						kdname="4";
					}else if("EMS".equals(kdgs)){
						kdname="5";
					}else if("宅急送".equals(kdgs)){
						kdname="6";
					}else if("全峰".equals(kdgs)){
						kdname="7";
					}else if("天天快递".equals(kdgs)){
						kdname="8";
					}else if("自提".equals(kdgs)){
						kdname="9";
					}else if("国通".equals(kdgs)){
						kdname="10";
					}
					
					if(orders.size()>0){
						 Iterator<Orders> iter = orders.iterator();
						 while(iter.hasNext()){
					            Orders b = iter.next();
					            if(b.getOrdersBH().trim().equals(ddbh.trim())){
					            	b.setKuaidiName(kdname);
									b.setKuaidiNo(kddh);
									b.setOrder_status(i);
									ordersDAO.update(b);
					                iter.remove();
					                jiShu++;
					            }
					        }
					}
					
            }
        }
        HttpServletRequest request = ServletActionContext.getRequest();
        System.out.println("jishu----------------"+jiShu);
        request.setAttribute("jiShu", jiShu);
		return "importExcelToOrders";
		}else{
			return "importNull";
		}
}
	@SuppressWarnings({ "static-access"})
    private String getValue(XSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            // 返回字符串类型的值
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
	
	 /**
     * 得到Excel表中的值
     * 
     * @param hssfCell
     *            Excel中的每一个格子
     * @return Excel中每一个格子中的值
     */
    @SuppressWarnings({ "static-access", "unused" })
    private String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            // 返回字符串类型的值
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
	
	public static String encodeFilename(String userAgent, String filename) {
		try {
			if (userAgent.contains("Firefox")) { //ff
				filename = "=?UTF-8?B?"
						+ new BASE64Encoder().encode(filename.getBytes("utf-8"))
						+ "?=";
			} else { // IE
				filename = URLEncoder.encode(filename, "utf-8");
			}
		} catch (IOException e){
			e.printStackTrace();
		} 
		return filename;
	}
	
	public String applyReturnPurchase() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		Map<String, Integer> compare = new HashMap<String, Integer>();
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("createDate", false);
		boolean chaxun = false;
		if (null!=userId &&! "".equals(userId)) {
			conditionProperties.put("t.userId", userId);
			compare.put("t.userId", 13);
			chaxun = true;
		}
		if(null != userName && !userName.isEmpty()){
			conditionProperties.put("t.userName", userName);
			compare.put("t.userName", 14);
			chaxun = true;
		}
		if (null!=ordersBH &&! "".equals(ordersBH)) {
			conditionProperties.put("ordersBH", ordersBH);
			compare.put("ordersBH", 2);
			chaxun = true;
		}
		if (null!=toUserName &&! "".equals(toUserName)) {
			conditionProperties.put("toUserName", toUserName);
			compare.put("toUserName", 2);
			chaxun = true;
		}
		if (null!=kuaidiNo &&! "".equals(kuaidiNo)) {
			conditionProperties.put("kuaidiNo", kuaidiNo);
			compare.put("kuaidiNo", 2);
			chaxun = true;
		}
		if(null!=mobile &&! "".equals(mobile)){
			conditionProperties.put("mobile", mobile);
			compare.put("mobile", 2);
			chaxun = true;
		}
		if(chaxun){
			int count_size =ordersDAO.cout_size1(conditionProperties, compare);
			
			// 修改的时候保存当前页
			if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
				this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
				
				this.setPagerMethod("next");
			}
			this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count_size));
			this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
			
			List<Orders> litPager = ordersDAO.findAllPagerList_new1(conditionProperties, compare, sort, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
			request.setAttribute("litPager", litPager);
		}
		return "applyReturnPurchase";
	}
	
	public String tuihuo(){
		if(ordersId!=null&&!"".equals(ordersId)){
			Orders order = (Orders) ordersDAO.findById(ordersId);
			if(order != null){
				order.setOrder_status(new Byte("2"));
				ordersDAO.update(order);//置状态order_status为2
				//查询本用户是否具有族长资格
				String hql = "from Orders o where o.userId="+order.getUserId()+"and o.order_status in (1,3,4,6)";
				List<Orders> list = (List<Orders>)ordersDAO.findbyHql(hql);
				//当不具有资格时查询所有一级用户
				if(list.size()==0){
					String hql1 = "from User u where u.referrerId="+order.getUserId();
					List<User> users = userDAO.findbyHql(hql1);
					//本用户下的所有下级用户全部清空
					if(users != null && users.size()>0){
						for(User u:users){
							u.setReferrer(null);
							userDAO.update(u);
						}
					}
				}
			}
		}
		return "applyReturnPurchase";
	}
	public String applyReturn(){
		if(ordersId!= null && !"".equals(ordersId)){
			Orders order = (Orders) ordersDAO.findById(ordersId);
			order.setTel("1");//order表里tel用来表示退货消息,1表示退货申请2表示退货成功,这里退货申请置1
			
			ApplyReturnPurchase arp = new ApplyReturnPurchase();
			arp.setApplyTime(new Date());
			arp.setKuaiDiNum(order.getKuaidiNo());
			arp.setOrderType(order.getOrderType());
			arp.setOrderBH(order.getOrdersBH());
			arp.setPhone(order.getMobile());
			arp.setStatus(0);
			arp.setUserName(order.getToUserName());
			arp.setOrderId(ordersId);
			try {
				arp.setReturnNum(Integer.parseInt(order.getSize()));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			
			User user = order.getUser();
			User refferUser ;
			if(user == null){
				user = (User) userDAO.findById(order.getUserId());
			}
			refferUser = user.getReferrer();
			if(refferUser == null){
				Integer refferId = user.getReferrerId();
				if(refferId != null)
					refferUser = (User) userDAO.findById(refferId);
			}
			if(refferUser != null)
				try {
					HttpConnectionUtil.sendHttpMsg("3", user, null, null,refferUser.getWxOpenid());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			ordersDAO.update(order);
			applyReturnPurchaseDao.saveOrUpdate(arp);
		}
		return "applyReturnPurchase";
	}
	
	public void getAllReturnNum(){
		if(ordersId != null){
			String hql = "from ApplyReturnPurchase arp where arp.orderId =" +ordersId;
			List<ApplyReturnPurchase> list = applyReturnPurchaseDao.findByHql(hql);
			int alreadyApplyNum = 0;
			if(list != null && list.size() !=0){
				for(ApplyReturnPurchase arp:list){
					alreadyApplyNum += arp.getReturnNum();
				}
			}
			JSONObject json = new JSONObject();
			try {
				HttpServletResponse response = ServletActionContext.getResponse();
				json.put("alreadyApplyNum", alreadyApplyNum);
				response.getWriter().write(json.toString());
			} catch (JSONException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String listAllApplys(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		Map<String, Integer> compare = new HashMap<String, Integer>();
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("applyTime", false);
		
		if (null!=orderType &&! "".equals(orderType)) {
			conditionProperties.put("status", Integer.parseInt(orderType));
			compare.put("status", 0);
		}else {
			orderType = "0";
			conditionProperties.put("status", 0);
			compare.put("status", 0);
		}
		if (null!=ordersBH &&! "".equals(ordersBH)) {
			conditionProperties.put("orderBH", ordersBH);
			compare.put("orderBH", 2);
		}
		if (null!=toUserName &&! "".equals(toUserName)) {
			conditionProperties.put("userName", toUserName);
			compare.put("userName", 2);
		}
		if (null!=kuaidiNo &&! "".equals(kuaidiNo)) {
			conditionProperties.put("kuaiDiNum", kuaidiNo);
			compare.put("kuaiDiNum", 2);
		}
		if(null!=mobile &&! "".equals(mobile)){
			conditionProperties.put("phone", mobile);
			compare.put("phone", 2);
		}
		int count_size =applyReturnPurchaseDao.cout_size_Commen(conditionProperties, compare);
		
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count_size));
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
		
		List<Orders> litPager = applyReturnPurchaseDao.findAllPagerList(conditionProperties, compare, sort, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		request.setAttribute("litPager", litPager);
		request.setAttribute("status", orderType);
		return "listAllApplys";
	}
	public String dealApply(){
		ApplyReturnPurchase arp = (ApplyReturnPurchase) applyReturnPurchaseDao.findById(ordersId);
		arp.setDealTime(new Date());
		arp.setStatus(2);
		Orders order = (Orders) ordersDAO.findById(arp.getOrderId());
		if(order != null){
			order.setOrder_status(new Byte("5"));
			order.setTel("2");//order表里tel用来表示退货消息,1表示退货申请2表示退货成功,这里退货处理置2
			ordersDAO.update(order);//置状态order_status为2
			//查询本用户是否具有族长资格
			String hql = "from Orders o where o.userId="+order.getUserId()+"and o.order_status in (1,3,4,6)";
			List<Orders> list = (List<Orders>)ordersDAO.findbyHql(hql);
			//当不具有资格时查询所有一级用户
			if(list.size()==0){
				String hql1 = "from User u where u.referrerId="+order.getUserId();
				List<User> users = userDAO.findbyHql(hql1);
				//本用户下的所有下级用户全部清空
				if(users != null && users.size()>0){
					for(User u:users){
						u.setReferrer(null);
						userDAO.update(u);
					}
				}
			}
		}
		
		User user = order.getUser();
		User refferUser =null;
		if(user == null){
			user = (User) userDAO.findById(order.getUserId());
			if(user != null){
				user.setQrCode("");
				user.setTicket("");
				refferUser = user.getReferrer();
				userDAO.update(user);
				if(refferUser == null){
					Integer refferId = user.getReferrerId();
					if(refferId != null)
						refferUser = (User) userDAO.findById(refferId);
				}
			}
		}
		
		if(refferUser != null)
			try {
				HttpConnectionUtil.sendHttpMsg("3", user, null, null,refferUser.getWxOpenid());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		applyReturnPurchaseDao.update(arp);
		
		return "dealApply";
	}
	public String deleteReturn(){
		ApplyReturnPurchase arp = (ApplyReturnPurchase) applyReturnPurchaseDao.findById(ordersId);
		Orders order = (Orders) ordersDAO.findById(arp.getOrderId());
		order.setTel("");
		
		User user = order.getUser();
		User refferUser ;
		if(user == null){
			user = (User) userDAO.findById(order.getUserId());
		}
		refferUser = user.getReferrer();
		if(refferUser == null){
			Integer refferId = user.getReferrerId();
			if(refferId != null)
				refferUser = (User) userDAO.findById(refferId);
		}
		if(refferUser != null)
			try {
				HttpConnectionUtil.sendHttpMsg("3", user, null, null,refferUser.getWxOpenid());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		ordersDAO.update(order);
		applyReturnPurchaseDao.deleteById(ordersId);
		return "dealApply";
	}
	
	public String appOrder() throws Exception {
		Orders orders = (Orders)ordersDAO.findById(ordersId);
		User user = null;
		if (orders != null) {
			orders.setOrder_status(new Byte("3"));
			orders.setKuaidiName(kuaidiName);
			orders.setKuaidiNo(kuaidiNo);
			/*EggActivityUser eau = (EggActivityUser) eggActivityUserDAO.findById(1);
			if(eau!=null){
				Integer num = eau.getCurrNum();
				if(num>0){
					eau.setCurrNum(num-1);
					eggActivityUserDAO.saveOrUpdate(eau);
					orders.setCardId(cardId);
				}else {
					ServletActionContext.getRequest().setAttribute("message", "免费极地独山石已经赠送完毕");
					return "initkuaidi";
				}
			}*/
			
			ordersDAO.update(orders);
			ServletActionContext.getRequest().setAttribute("message", "审核成功");
			user = orders.getUser();
			if(user == null)
				user = (User) userDAO.findById(orders.getUserId());
		}
		else {
			ServletActionContext.getRequest().setAttribute("message", "此订单不存在");
		}
		if(user == null){
			ServletActionContext.getRequest().setAttribute("message", "此订单不存在");
		}else {
			try {
				HttpConnectionUtil.sendHttpMsg("1", user, null, orders,null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "initkuaidi";
	}
	public String appOrderbeizhu() throws Exception {
		Orders orders = (Orders)ordersDAO.findById(ordersId);
		User user = null;
		if (orders != null) {
			orders.setComments(kuaidiNo);
			ordersDAO.update(orders);
			ServletActionContext.getRequest().setAttribute("message", "操作成功");
			user = orders.getUser();
			if(user == null)
				user = (User) userDAO.findById(orders.getUserId());
		}
		else {
			ServletActionContext.getRequest().setAttribute("message", "此订单不存在");
		}
		if(user == null){
			ServletActionContext.getRequest().setAttribute("message", "此订单不存在");
		}
		return "beizhu";
	}
	
	public String update() throws Exception  {
		/*HttpServletRequest request = ServletActionContext.getRequest();
		AdminUser loginUser = (AdminUser)request.getSession().getAttribute("user");
		News news = (News)newsDAO.findById(newsId);
		if (news != null) {
			news.setTitle(title);
			news.setContent(content);
			news.setUser(loginUser);
			newsDAO.update(news);
			
			ServletActionContext.getRequest().setAttribute("message", " 修改成功 ");
		}
		else {
			ServletActionContext.getRequest().setAttribute("message", "此公告不存在");
		}*/
		
		return "goBackList";
	}
	
	public static void main(String[] args) {
		int a = 19/5;
	//	System.out.println(a);
		
	/*	String pname = "";
		for (int i=1;i<=5;i++) {
			switch (i) {
			case 1:
				pname = "1";
				System.out.println(i);
				break;
			case 2:
				pname = "2";
				System.out.println(i);
				break;
			case 3:
				pname = "3";
				System.out.println(i);
				break;
			case 4:
				System.out.println(i);
				break;
			case 5:
				System.out.println(i);
				break;
			}
		}*/
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
		String fd = sdf.format(new Date());
		for (int i=0;i<54;i++) {
			System.out.println("ZD"+fd+CommUtils.getSixRandom());
		}
	}

	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentUserName() {
		return parentUserName;
	}

	public void setParentUserName(String parentUserName) {
		this.parentUserName = parentUserName;
	}
	
}
