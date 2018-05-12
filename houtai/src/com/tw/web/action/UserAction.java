package com.tw.web.action;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.Namespace;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.util.ERCodeUtil;
import com.opensymphony.xwork2.ActionChainResult;
import com.tw.web.dao.AdminUserDAO;
import com.tw.web.dao.CardDAO;
import com.tw.web.dao.FhRecordDAO;
import com.tw.web.dao.OrdersDAO;
import com.tw.web.dao.TAreaDao;
import com.tw.web.dao.TixianDAO;
import com.tw.web.dao.UserDAO;
import com.tw.web.hibernate.UserVO;
import com.tw.web.hibernate.persistent.AdminUser;
import com.tw.web.hibernate.persistent.Card;
import com.tw.web.hibernate.persistent.FhRecord;
import com.tw.web.hibernate.persistent.Orders;
import com.tw.web.hibernate.persistent.TArea;
import com.tw.web.hibernate.persistent.Tixian;
import com.tw.web.hibernate.persistent.User;
import com.tw.web.service.LoginService;
import com.tw.web.util.HttpConnectionUtil;


@SuppressWarnings("serial")
@ParentPackage("app-default")
@Namespace("")
@Results(
		{
			@Result(name="listAllUser_new", 		value="/WEB-INF/jsp/sys/listAllUser_new.jsp"),
			@Result(name="listAllOrders_new", 		value="/WEB-INF/jsp/sys/listAllOrders_new.jsp"),
			@Result(name="listAllTixian", 			value="/WEB-INF/jsp/member/listAllTixian.jsp"),
			@Result(name="listAllUser", 			value="/WEB-INF/jsp/sys/userList.jsp"),	
			@Result(name="listAllAdminUser", 			value="/WEB-INF/jsp/sys/userList1.jsp"),
			
			@Result(name="shouzhi_tongji", 			value="/WEB-INF/jsp/search/search_shouzhi_tongji.jsp"),	
			@Result(name="jiesuan", type=ActionChainResult.class, 	value="user", params = {"method", "listAllUser_new"}),
			@Result(name="goBackList", type=ActionChainResult.class, 	value="user", params = {"method", "listAllUser"}),
			@Result(name="gobackList4", type=ActionChainResult.class, 	value="user", params = {"method", "listAllTixian"})
		}
)
public class UserAction extends ExtJSONActionSuport {
	
	private final int dataT = 5;
	private LoginService loginService;
	private FhRecordDAO fhRecordDAO;
	private UserDAO userDAO;
	private TixianDAO tixianDAO;
	private OrdersDAO ordersDAO;
	private AdminUserDAO adminUserDAO;
	private CardDAO cardDAO;
	private TAreaDao tAreaDao;
	private Tixian tixian;	
	private Integer userId;
	private Integer flag;
	private String centerLoginName;
	private String loginName;
	private String password;
	private String newPassword1;
	private String newPassword2;
	private String password2Flag;
	private String password2;
	private String userName;
	private String phone;
	private Integer parentId;//推荐人id这里没有用referrerId
	private String parentUserName;//推荐人昵称
	private String referrerId;
	private String account;
	private Integer submitMoney;
	private Integer realSubmitMoney;
	private Integer fhMoney;
	private Double tixianMoney;
	private String isAppCenter;
	private Integer txId;
	private String isHTApp;
	
	private String bankName;
	private String openBankAddr;
	private String accountHolder;
	
	private String parentLoginName;
	private String centerId;
	private String tjrLoginName;
	private String tjrCenterName;
	private String orderProperty;
	private String type;
	private String j_fromDate;
	private String j_endDate;
	public TAreaDao gettAreaDao() {
		return tAreaDao;
	}
	@Autowired
	public void settAreaDao(TAreaDao tAreaDao) {
		this.tAreaDao = tAreaDao;
	}
	public OrdersDAO getOrdersDAO() {
		return ordersDAO;
	}
	@Autowired
	public void setOrdersDAO(OrdersDAO ordersDAO) {
		this.ordersDAO = ordersDAO;
	}
	public String getCenterLoginName() {
		return centerLoginName;
	}

	public void setCenterLoginName(String centerLoginName) {
		this.centerLoginName = centerLoginName;
	}

	public String getParentLoginName() {
		return parentLoginName;
	}

	public String getTjrCenterName() {
		return tjrCenterName;
	}
	
	public String getOrderProperty() {
		return orderProperty;
	}
	public void setOrderProperty(String orderProperty) {
		this.orderProperty = orderProperty;
	}
	public void setTjrCenterName(String tjrCenterName) {
		this.tjrCenterName = tjrCenterName;
	}
	public void setParentLoginName(String parentLoginName) {
		this.parentLoginName = parentLoginName;
	}

	public LoginService getLoginService() {
		return loginService;
	}
	
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getOpenBankAddr() {
		return openBankAddr;
	}

	public void setOpenBankAddr(String openBankAddr) {
		this.openBankAddr = openBankAddr;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	public AdminUserDAO getAdminUserDAO() {
		return adminUserDAO;
	}
	@Autowired
	public void setAdminUserDAO(AdminUserDAO adminUserDAO) {
		this.adminUserDAO = adminUserDAO;
	}

	public FhRecordDAO getFhRecordDAO() {
		return fhRecordDAO;
	}
	@Autowired
	public void setFhRecordDAO(FhRecordDAO fhRecordDAO) {
		this.fhRecordDAO = fhRecordDAO;
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public CardDAO getCardDAO() {
		return cardDAO;
	}
	@Autowired
	public void setCardDAO(CardDAO cardDAO) {
		this.cardDAO = cardDAO;
	}

	public TixianDAO getTixianDAO() {
		return tixianDAO;
	}
	@Autowired
	public void setTixianDAO(TixianDAO tixianDAO) {
		this.tixianDAO = tixianDAO;
	}

	public String getNewPassword1() {
		return newPassword1;
	}

	public void setNewPassword1(String newPassword1) {
		this.newPassword1 = newPassword1;
	}

	public String getNewPassword2() {
		return newPassword2;
	}

	public Integer getTxId() {
		return txId;
	}

	public void setTxId(Integer txId) {
		this.txId = txId;
	}

	public Integer getFhMoney() {
		return fhMoney;
	}

	public void setFhMoney(Integer fhMoney) {
		this.fhMoney = fhMoney;
	}

	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Tixian getTixian() {
		return tixian;
	}
	public void setTixian(Tixian tixian) {
		this.tixian = tixian;
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
	public String getReferrerId() {
		return referrerId;
	}

	public void setReferrerId(String referrerId) {
		this.referrerId = referrerId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public Integer getSubmitMoney() {
		return submitMoney;
	}

	public void setSubmitMoney(Integer submitMoney) {
		this.submitMoney = submitMoney;
	}
	
	public String getPassword2Flag() {
		return password2Flag;
	}

	public Double getTixianMoney() {
		return tixianMoney;
	}

	public void setTixianMoney(Double tixianMoney) {
		this.tixianMoney = tixianMoney;
	}

	public void setPassword2Flag(String password2Flag) {
		this.password2Flag = password2Flag;
	}

	public Integer getRealSubmitMoney() {
		return realSubmitMoney;
	}

	public void setRealSubmitMoney(Integer realSubmitMoney) {
		this.realSubmitMoney = realSubmitMoney;
	}
	
	public String getIsAppCenter() {
		return isAppCenter;
	}

	public void setIsAppCenter(String isAppCenter) {
		this.isAppCenter = isAppCenter;
	}
	
	public String getIsHTApp() {
		return isHTApp;
	}

	public void setIsHTApp(String isHTApp) {
		this.isHTApp = isHTApp;
	}

	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getCenterId() {
		return centerId;
	}
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	
	public String initAddUser1(){
		return "initAddUser1";
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getJ_fromDate() {
		return j_fromDate;
	}
	public void setJ_fromDate(String j_fromDate) {
		this.j_fromDate = j_fromDate;
	}
	public String getJ_endDate() {
		return j_endDate;
	}
	public void setJ_endDate(String j_endDate) {
		this.j_endDate = j_endDate;
	}
	//奖金拨比
	public String listbonus() throws Exception {
		Integer realSubmitMoney = userDAO.findTotalMoneyByNowDate();
		Double fhMoney = fhRecordDAO.findTotalMoneyByDate();
		
		//当日上的业绩
		ServletActionContext.getRequest().setAttribute("realSubmitMoney", realSubmitMoney);
		//当拨出奖金
		ServletActionContext.getRequest().setAttribute("fhMoney", fhMoney);
		//拨比率
		ServletActionContext.getRequest().setAttribute("percentage", (fhMoney/realSubmitMoney)*100);
		
		Integer realSubmitMoneyt = userDAO.findTotalMoney();
		Double fhMoneyt = fhRecordDAO.findTotalMoney();
		//总业绩
		ServletActionContext.getRequest().setAttribute("realSubmitMoneyt", realSubmitMoneyt);
		//当拨出奖金
		ServletActionContext.getRequest().setAttribute("fhMoneyt", fhMoneyt);
		//拨比率
		ServletActionContext.getRequest().setAttribute("percentaget", (fhMoneyt/realSubmitMoneyt)*100);
		
		return "listbonus";
	}
	
	public String initAddUser () throws Exception {
		Object user = ServletActionContext.getRequest().getSession().getAttribute("user");
		if (user instanceof User) {
			user = (User)user;
			ServletActionContext.getRequest().setAttribute("flag", "1");
			tjrLoginName=((User) user).getLoginName();
			if(((User) user).getIsAppCenter()!=null&&((User) user).getIsAppCenter().equals("2")){
			 tjrCenterName=((User) user).getLoginName();
			}
		}
		else if (user instanceof AdminUser) {
			user = (AdminUser)user;
			ServletActionContext.getRequest().setAttribute("flag", "2");
		}
		// 找到所有的地级市
		List<TArea> litTArea = tAreaDao.findEntityByPropertiName("parentCode", "0");
		ServletActionContext.getRequest().setAttribute("parentLoginName", parentLoginName);
		
		return "initAddUser";
	}
	
	public String activityUser () throws Exception {
		User user = loginService.findUserByUserId(userId);
		user.setActivitiFlag("1");
		
		loginService.updateUser(user);
		return "goBackList";
	}
	
	public String recharge () throws Exception { //充值
		//User user = loginService.findUserByUserId(userId);
		AdminUser adminUser = (AdminUser)ServletActionContext.getRequest().getSession().getAttribute("user");
		
		//存值多少張卡
		cardDAO.updateCardInCardNo(userId, submitMoney,"2",adminUser.getUserId());
		
		/*FhRecord fhRecord = new FhRecord();
		fhRecord.setUser(user);
		fhRecord.setCreateDate(new Date());
		fhRecord.setFhType("6"); //充值
		fhRecord.setFhmoney(submitMoney*1.0);
		fhRecord.setMemo("管理员给会员"+user.getUserId()+"存值电子货币"+submitMoney);
		loginService.createFhRecord(fhRecord);*/
		
		ServletActionContext.getRequest().setAttribute("message", "存值成功");
		
		//歷史記錄
		return "goBackList";
	}
	
	public String listAllAdminUser () throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		List<User> litAll = loginService.findAllUser("", true, 0, 0, "all");
				
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), litAll.size()));
		
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
						
		List<User> litPager = loginService.findAllUser("createDate", false, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		
		request.setAttribute("litPager", litPager);
		
		return "listAllAdminUser";
	}
	
	@SuppressWarnings("unchecked")
	public String listAllUser () throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		Map<String, Integer> compare = new HashMap<String, Integer>();
		if (null!=userId &&! "".equals(userId)) {
			conditionProperties.put("userId", userId);
			compare.put("userId", 0);
		}
		if(null != userName && !userName.isEmpty()){
			conditionProperties.put("userName", userName);
			compare.put("userName", 2);
		}
		if(null != parentId){
			conditionProperties.put("r.userId", parentId);
			compare.put("r.userId", 13);
		}
		if(null != parentUserName && !parentUserName.isEmpty()){
			conditionProperties.put("r.userName", parentUserName);
			compare.put("r.userName", 14);
		}
		if(null != flag && flag != 2 ){
			conditionProperties.put("subscribe", flag);
			compare.put("subscribe", 0);
		}
		//parentUserName     parentId  userName
			
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("appDate", false);
		
		int count_size =userDAO.cout_size(conditionProperties, compare);
		
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count_size));
		
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
		List<User> litPager = null;
		litPager = userDAO.findAllPagerList_new(conditionProperties, compare, sort,this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		
		List<UserVO> list = new ArrayList<UserVO>();
		if(litPager.size()>0){
			for(User u:litPager){
				UserVO uVo = new UserVO();
				uVo.setUserId(u.getUserId());
				uVo.setAppDate(u.getAppDate());
				uVo.setChildInteger1(u.getSubscribe());
				uVo.setUserName(u.getUserName());
				uVo.setChildString1(u.getHeadUrl());
				User referrer = u.getReferrer();
				if(referrer == null ){
					if(u.getReferrerId()!=null){
						referrer = (User) userDAO.findById(u.getReferrerId());
						uVo.setReferrerId(u.getReferrerId());
					}
				}
				if(referrer != null){
					uVo.setReferrer(u.getReferrer());
					uVo.setReferrerId(referrer.getUserId());
					uVo.setReferrerName(referrer.getUserName());
				}
				uVo.setWxOpenId(u.getWxOpenid());
				DecimalFormat   fnum   =   new   DecimalFormat("##0.00"); //四舍五入，保留两位小数 
				//获取个人销售额
				List sse = ordersDAO.findCurrUserAllOrder(u.getUserId());
				if(sse.size() == 3){
					uVo.setXiaoShowE(fnum.format(((Double)sse.get(0)+(Double)sse.get(1)+(Double)sse.get(2) ))); //总销售额
				}else if(sse.size() == 2){
					uVo.setXiaoShowE( fnum.format(((Double)sse.get(0)+(Double)sse.get(1)))); //总销售额
				}else if(sse.size() == 1){
					uVo.setXiaoShowE(fnum.format(((Double)sse.get(0)))); //总销售额
				}else{
					uVo.setXiaoShowE( "0.0"); //总销售额
				}
				//获取下级成员个数
				Integer fri = userDAO.countFriFamily(u.getUserId(),"1",null).size();
				Integer firGou = userDAO.countGoumaiFamily(u.getUserId(), "1", null).size();
				Integer sen = userDAO.countFriFamily(u.getUserId(),"2",null).size();
				Integer senGou = userDAO.countGoumaiFamily(u.getUserId(), "2", null).size();
				Integer thr = userDAO.countFriFamily(u.getUserId(),"3",null).size();
				Integer thrGou = userDAO.countGoumaiFamily(u.getUserId(), "3", null).size();
				//获取购买会员个数
				uVo.setChild1(firGou+"/"+fri);
				uVo.setChild2(senGou+"/"+sen);
				uVo.setChild3(thrGou+"/"+thr);
				//获取总佣金
				List yfk  = ordersDAO.findLevelUserOrderMoney(u.getUserId(), "1,3,4,6", "all");
				Double yfkOrder1  = (Double) yfk.get(0);
				Double yfkOrder2  = (Double) yfk.get(1);
				Double yfkOrder3  = (Double) yfk.get(2);
				Double tolyj = yfkOrder1+yfkOrder2+yfkOrder3;
				uVo.setYongjin(fnum.format(tolyj));
				list.add(uVo);
				}
			}
		request.setAttribute("litPager", list);
		
		return "listAllUser";
	}
	
	public String listAllUser_new(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		Map<String, Integer> compare = new HashMap<String, Integer>();
		if (null!=userId &&! "".equals(userId)) {
			conditionProperties.put("userId", userId);
			compare.put("userId", 0);
		}
		if (null!=referrerId &&! "".equals(referrerId)) {
			conditionProperties.put("r.referrerId", referrerId);
			compare.put("r.referrerId", 13);
		}
		conditionProperties.put("level", "1");
		compare.put("level", 0);
			
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("appDate", false);
		
		int count_size =userDAO.cout_size(conditionProperties, compare);
		
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count_size));
		
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
		List<User> litPager = userDAO.findAllPagerList_new(conditionProperties, compare, sort,this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		List<UserVO> list = new ArrayList<UserVO>();
		if(litPager.size()>0){
			for(User u:litPager){
				UserVO uvo = new UserVO();
				Double zongxiao = 0.0;
				Double yijie = 0.0;
				Double weijie = 0.0;
				String sql = "select o.ordersId from orders o where o.order_status = 4 and o.userId in (select userId from users where FIND_IN_SET(referrerId, getChildLst("+u.getUserId()+")))";
				uvo.setUserId(u.getUserId());
				User referrer = u.getReferrer();
				if(referrer == null ){
					if(u.getReferrerId()!=null){
						referrer = (User) userDAO.findById(u.getReferrerId());
						uvo.setReferrerId(u.getReferrerId());
					}
				}
				if(referrer != null){
					uvo.setReferrer(u.getReferrer());
					uvo.setReferrerId(referrer.getUserId());
				}
				uvo.setUserName(u.getUserName());
				uvo.setWxOpenId(u.getWxOpenid());
				DecimalFormat   fnum   =   new   DecimalFormat("##0.00"); //四舍五入，保留两位小数 
				List ordersList = userDAO.findBySql(sql);
				if(ordersList.size()>0){
					for(int i=0;i<ordersList.size();i++){
						Orders o = (Orders) ordersDAO.findById(Integer.parseInt(ordersList.get(i)==null?"0":ordersList.get(i).toString()));
							if(o.getCardId()!=null && o.getCardId()==1){
								yijie+=o.getMoney();
							}else {
								weijie+=o.getMoney();
							}
							zongxiao+=o.getMoney();
					}
				}
				uvo.setChild1(fnum.format(zongxiao));
				uvo.setChild2(fnum.format(yijie));
				uvo.setChild3(fnum.format(weijie));
				list.add(uvo);
			}
		}
		request.setAttribute("litPager", list);
		
		return "listAllUser_new";
	}
	
	public String listAllOrders_new(){
		HttpServletRequest request = ServletActionContext.getRequest();
		if(userId !=null&&!"".equals(userId)){
			String sql = "select o.* from orders o where (o.cardId IS NULL OR o.cardId=0) and o.order_status = 4 and o.userId in (select userId from users where FIND_IN_SET(referrerId, getChildLst("+userId+")))";
			List<Orders> ordersList = userDAO.findBySql(sql);
			// 修改的时候保存当前页
			if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
				this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
				
				this.setPagerMethod("next");
			}
			this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), ordersList.size()));
			
			this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
			if(ordersList.size()<=25){
				request.setAttribute("litPaper", ordersList);
			}else {
				List<Orders> ordersVo = new ArrayList<Orders>();
				for(int i=0;i<ordersList.size();i++){
					if(i>=((Integer.parseInt(this.getCurrentPage())-1)*25)&&i<=Integer.parseInt(this.getCurrentPage())*25){
						ordersVo.add(ordersList.get(i));
					}
				}
				request.setAttribute("litPaper", ordersVo);
			}
		}
		if(loginName !=null&&!"".equals(loginName)){
			request.setAttribute("weijie", loginName);
		}
		return "listAllOrders_new";
	}
	
	public String jiesuan(){
		if(userId !=null&&!"".equals(userId)){
			String sql = "select o.ordersId from orders o where (o.cardId IS NULL OR o.cardId=0) and o.order_status = 4 and o.userId in (select userId from users where FIND_IN_SET(referrerId, getChildLst("+userId+")))";
			List ordersList = userDAO.findBySql(sql);
			if(ordersList.size()>0){
				for(int i=0;i<ordersList.size();i++){
					Orders o = (Orders) ordersDAO.findById(Integer.parseInt(ordersList.get(i)==null?"0":ordersList.get(i).toString()));
					o.setCardId(1);
					ordersDAO.update(o);
				}
			}
		}
		return "jiesuan";
	}
	
	public String refferAllListCount()throws Exception{
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		conditionProperties.put("referrerId", userId);
		
		Map<String, Integer> compare = new HashMap<String, Integer>();
		compare.put("referrerId", 0);
		
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("createDate", false);
		
		int count_size =userDAO.cout_size(conditionProperties, compare);
				
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count_size));
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
		
		
		List<User> litPager = userDAO.findAllPagerList_new(conditionProperties, compare, sort,this.getPager().getStartRow(), this.getPager().getPageSize(), "all");
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listCount", litPager.size());
		return null;
	}
	
	public String refferAllList() throws Exception { //直荐会员统计
		HttpServletRequest request = ServletActionContext.getRequest();
		User loginUser = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		conditionProperties.put("referrerId", loginUser.getUserId());
		
		Map<String, Integer> compare = new HashMap<String, Integer>();
		compare.put("referrerId", 0);
		
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("createDate", false);
		
		int count_size =userDAO.cout_size(conditionProperties, compare);
				
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count_size));
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
		
		
		List<User> litPager = userDAO.findAllPagerList_new(conditionProperties, compare, sort,this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		request.setAttribute("litPager", litPager);
		return "refferAllList";
		
	}
	
	public String createUser() throws Exception {
		User loginNameUser = loginService.findUserByLoginName(loginName);
		User appCenter=null;
		if (null!=centerId&&!"".equals(centerId)) {
			appCenter = loginService.findUserByLoginName(centerId);
			if (null == appCenter) {
				ServletActionContext.getRequest().setAttribute("message", "此服务中心不存在");
				return "initAddUser";
			}
			else if(!"2".equals(appCenter.getIsAppCenter())) {
				ServletActionContext.getRequest().setAttribute("message", "此账号不是服务中心");
				return "initAddUser";
			}
		}
		
		if (null!=loginNameUser) {
			ServletActionContext.getRequest().setAttribute("message", "此用户名已经注册请更换用户名谢谢");
			return "initAddUser";
		}
		
		User referrer = loginService.findUserByLoginName(referrerId);
		if (null == referrer) {
			ServletActionContext.getRequest().setAttribute("message", "邀请人不存在");
			return "initAddUser";
		}
		User user = new User();
		user.setAppCenter(appCenter); //报单中心
		user.setLoginName(loginName);
		user.setCreateDate(new Date());
		user.setPassWord(password);
		user.setPassWord2(password2);
		user.setUserName(userName);
		user.setPhone(phone);
		user.setReferrer(referrer);
		user.setFlagorg(referrer.getFlagorg());
		
		user.setActivitiFlag("0");//注册成功不激活
		String userUrl="http://218.241.153.74/user/userAction!userRegPage1.action?tjrLoginName="+centerId;
		String ercodePath=ERCodeUtil.createQRcode(userUrl, null, null, false);
		user.setQrCode(ercodePath);
		//创建用户二维码信息
		loginService.createUser(user);
		
		ServletActionContext.getRequest().setAttribute("message", "新增成功");
		return "initAddUser";
	}
	public String createUser1() throws Exception {
		User loginNameUser = loginService.findUserByLoginName(loginName);
		User appCenter=null;
		if (null!=centerId&&!"".equals(centerId)) {
			appCenter = loginService.findUserByLoginName(centerId);
			if (null == appCenter) {
				ServletActionContext.getRequest().setAttribute("message", "此服务中心不存在");
				return "rInitAddUser1";
			}
			else if(!"2".equals(appCenter.getIsAppCenter())) {
				ServletActionContext.getRequest().setAttribute("message", "此账号不是服务中心");
				return "rInitAddUser1";
			}
		}
		
		if (null!=loginNameUser) {
			ServletActionContext.getRequest().setAttribute("message", "此用户名已经注册请更换用户名谢谢");
			return "rInitAddUser1";
		}
		
		User referrer = loginService.findUserByLoginName(referrerId);
		if (null == referrer) {
			ServletActionContext.getRequest().setAttribute("message", "邀请人不存在");
			return "rInitAddUser1";
		}
		User user = new User();
		user.setAppCenter(appCenter); //报单中心
		user.setLoginName(loginName);
		user.setCreateDate(new Date());
		user.setPassWord(password);
		user.setPassWord2(password2);
		user.setUserName(userName);
		user.setPhone(phone);
		user.setReferrer(referrer);
		user.setFlagorg(referrer.getFlagorg());
		
		user.setActivitiFlag("0");//注册成功不激活
		String userUrl="http://218.241.153.74/user/userAction!userRegPage1.action?tjrLoginName="+centerId;
		String ercodePath=ERCodeUtil.createQRcode(userUrl, null, null, false);
		user.setQrCode(ercodePath);
		//创建用户二维码信息
		loginService.createUser(user);
		
		ServletActionContext.getRequest().setAttribute("message", "新增成功");
		return "rInitAddUser1";
	}
	
	//点击参与公司促销活动
	public String canyuhuodong() {
		User loginUser = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		
		
		return "";
		
	}
	
	
	public String initCard() {
		User loginUser = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		conditionProperties.put("userId", loginUser.getUserId());
		conditionProperties.put("fhType", "17");
		
		Map<String, Integer> compare = new HashMap<String, Integer>();
		compare.put("userId", 0);
		compare.put("fhType", 0);
		
		List<FhRecord> list = loginService.findAllFhRecordByUserId(conditionProperties, compare, null, 0, 1, "page");
		String flag = "0";
		if (null!=list&&list.size()!=0) {
			flag = "1";
			FhRecord fhRecord = list.get(0);
			ServletActionContext.getRequest().setAttribute("fhRecord", fhRecord);
		}
		if (null==loginUser.getRealSubmitMoney() || 0==loginUser.getRealSubmitMoney()) {
			flag = "1";
		}
		ServletActionContext.getRequest().setAttribute("flag", flag);
		ServletActionContext.getRequest().setAttribute("user", loginUser);
		return "initCard";
	}
	
	public String goumaiCard() {
		User loginUser = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		
		if (null==loginUser.getRealSubmitMoney() || 0==loginUser.getRealSubmitMoney()) {
			ServletActionContext.getRequest().setAttribute("message", "空单不能购买");
			return "goBackList31";
		}
		
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		conditionProperties.put("userId", loginUser.getUserId());
		conditionProperties.put("fhType", "17");
		
		Map<String, Integer> compare = new HashMap<String, Integer>();
		compare.put("userId", 0);
		compare.put("fhType", 0);
		
		List<FhRecord> list = loginService.findAllFhRecordByUserId(conditionProperties, compare, null, 0, 1, "page");
		if (null!=list&&list.size()!=0) {
			ServletActionContext.getRequest().setAttribute("message", "您已经购买过,不能再次购买");
			return "goBackList31";
		}
		
		
		Card card = cardDAO.findCardByCardTypeAndCardStatus("3");
		if (null != card) {
			//card.setUser(user);
			
			Double totalMoney = fhRecordDAO.findAllBaodanBi(loginUser.getUserId());
			if (totalMoney<100) {
				ServletActionContext.getRequest().setAttribute("message", "对不起您的报单币不足100,请联系管理员存值!!!");
				return "goBackList31";
			}
			
			FhRecord fhRecord = new FhRecord();
			fhRecord.setUser(loginUser);
			fhRecord.setCreateDate(new Date());
			fhRecord.setFhmoney(-100.0);
			fhRecord.setFhType("17"); //100积分购买一个150元包15个月的存值卡
			fhRecord.setMemo("套餐名称:150元包15个月的存值卡 卡号:"+card.getCardNo()+" 卡密:"+card.getCardPassword());
			loginService.createFhRecord(fhRecord);
			
			card.setCardStatus("1"); //已分配
			cardDAO.update(card);
		}
		
		return "goBackList31";
	}
	
	
	public String initModifyUser() {
		Object user = ServletActionContext.getRequest().getSession().getAttribute("user");
		String flag = "1";
		if (user instanceof AdminUser) {
			user = (User)userDAO.findById(userId);
			flag = "2";
		}
		ServletActionContext.getRequest().setAttribute("flag", flag);
		ServletActionContext.getRequest().setAttribute("users", user);
		
		return "initModifyUser";
	}
	
	public String modifyUser () {
		User user = loginService.findUserByUserId(userId);
		String lName = user.getLoginName();
		if (!lName.equals(loginName)) {
			List<User> userL = userDAO.findEntityByPropertiName("loginName", loginName);
			if (null != userL && userL.size()!=0) {
				User user2 = userL.get(0); 
				String loginName3 = user2.getLoginName();
				if (loginName.equals(loginName3)) {
					ServletActionContext.getRequest().setAttribute("message", "系统已经存在此用户名");
					User user1 = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
					ServletActionContext.getRequest().setAttribute("users", user1);
					
					return "initModifyUser";
				}
			}
		}
		
		user.setLoginName(loginName);
		user.setPhone(phone);
		user.setUserName(userName);
		//user.setAccount(account);
		user.setPassWord(password);
		user.setPassWord2(password2);
		
		loginService.updateUser(user);
		ServletActionContext.getRequest().setAttribute("users", user);
		ServletActionContext.getRequest().setAttribute("message","修改成功");
		return "initModifyUser";
	}
	
	public String initModifyPassword() {
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		ServletActionContext.getRequest().setAttribute("user", user);
		
		return "initModifyPassword";
	}
	
	public String modifyPassword() {
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		/*String password1 = user.getPassWord();
		String password22 = user.getPassWord2();
		
		if (!password1.equals(password)) {
			ServletActionContext.getRequest().setAttribute("message", "原一级密码不正确");
			return "initModifyPassword";
		}
		
		if (!password22.equals(password2)) {
			ServletActionContext.getRequest().setAttribute("message", "原二级密码不正确");
			return "initModifyPassword";
		}*/
		
		user.setPassWord(newPassword1);
		user.setPassWord2(newPassword2);
		loginService.updateUser(user);
		ServletActionContext.getRequest().setAttribute("message", "密码修改成功");
		return "initModifyPassword";
	}
	
	public String confirmpwd2() {
		ServletActionContext.getRequest().setAttribute("password2Flag", password2Flag);
		return "confimPassword2";
	}
	
	public String password2Confirm() {
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		ServletActionContext.getRequest().setAttribute("password2Flag", password2Flag);
		String password22 = user.getPassWord2();
		
		if (!password22.equals(password2)) {
			ServletActionContext.getRequest().setAttribute("message", "对不起您输入的二级密码不正确");
			return "confimPassword2";
		}
		
		if ("1".equals(password2Flag)) { //奖金体现
			return "gobackList32";
		}
		else if ("2".equals(password2Flag)) {//我得充值卡号和密码
			return "goBackList2";
		}
		else if ("3".equals(password2Flag)) {//个人资料维护
			return "goBackList10";
		}
		else if ("4".equals(password2Flag)) {//密码修改
			return "goBackList20";
		}
		else if ("5".equals(password2Flag)) {//奖金转换电子币-转入
			return "goBackList21";
		}
		else if ("6".equals(password2Flag)) {//购买卡页面
			return "goBackList31";
		}
		else if ("7".equals(password2Flag)) {//奖金转换电子币-转入
			return "goBackList41";
		}
		else if ("8".equals(password2Flag)) {//奖金转换电子币-转入
			return "goBackList51";
		}
		else if ("9".equals(password2Flag)) {//奖金转换电子币-转入
			return "goBackList61";
		}
		
		return "confimPassword2";
	}
	
	//电子币转账
	public String initZhuanzhang() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		User loginUser = (User)request.getSession().getAttribute("user");
		
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		conditionProperties.put("userId", loginUser.getUserId());
		conditionProperties.put("fhType", new String[]{"8","9"});
		
		Map<String, Integer> compare = new HashMap<String, Integer>();
		compare.put("userId", 0);
		compare.put("fhType", 4);
		
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("createDate", false);
		
		int count_size =fhRecordDAO.cout_size(conditionProperties, compare);
				
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count_size));
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
		
		
		List<FhRecord> litPager = loginService.findAllFhRecordByUserId(conditionProperties, compare, sort, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		request.setAttribute("litPager", litPager);
		Double totalMoney = fhRecordDAO.findTotalMoneyByUserId(loginUser.getUserId());
		ServletActionContext.getRequest().setAttribute("totalMoney", totalMoney);
		return "initZhuanzhang";
		
	}
	
	
	//电子币转账
	public String initjiangjinzhuanbaodanbi() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		User loginUser = (User)request.getSession().getAttribute("user");
		
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		conditionProperties.put("userId", loginUser.getUserId());
		conditionProperties.put("fhType", "15");
		
		Map<String, Integer> compare = new HashMap<String, Integer>();
		compare.put("userId", 0);
		compare.put("fhType", 0);
		
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("createDate", false);
		
		int count_size =fhRecordDAO.cout_size(conditionProperties, compare);
				
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count_size));
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
		
		
		List<FhRecord> litPager = loginService.findAllFhRecordByUserId(conditionProperties, compare, sort, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		request.setAttribute("litPager", litPager);
		Double totalMoney = fhRecordDAO.findTotalMoneyByUserId(loginUser.getUserId());
		ServletActionContext.getRequest().setAttribute("totalMoney", totalMoney);
		return "initjiangjinzhuanbaodanbi";
		
	}
	
	public String zhuanzhang () {
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		
		User user2 = loginService.findUserByLoginName(loginName);
		if (null==user2) {
			ServletActionContext.getRequest().setAttribute("message", "对不起没有此用户");
			return "goBackList61";
		}
		
		if (user.getUserId()==user2.getUserId()) {
			ServletActionContext.getRequest().setAttribute("message", "对不起自己不能和自己转账");
			return "goBackList61";
		}
		
		Double totalMoney = fhRecordDAO.findAllJiangjing(user.getUserId());
		if (fhMoney>totalMoney) {
			ServletActionContext.getRequest().setAttribute("message", "对不起您的剩余报单币少于转账额度请管理人员存值");
			return "goBackList61";
		}
		if (fhMoney<100) {
			ServletActionContext.getRequest().setAttribute("message", "对不起每次转币不能少于100");
			return "goBackList61";
		}
		
		FhRecord fhRecord = new FhRecord();
		fhRecord.setUser(user);
		fhRecord.setCreateDate(new Date());
		fhRecord.setFhmoney(-fhMoney*1.0);
		fhRecord.setFhType("8"); //电子转账8 转出
		fhRecord.setMemo("会员"+user.getLoginName()+"给会员"+user2.getLoginName()+"转出"+fhMoney);
		loginService.createFhRecord(fhRecord);
		
		FhRecord fhRecord2 = new FhRecord();
		fhRecord2.setUser(user2);
		fhRecord2.setCreateDate(new Date());
		fhRecord2.setFhmoney(fhMoney*1.0);
		fhRecord2.setFhType("9");//电子转账9 转入
		fhRecord2.setMemo("会员"+user.getLoginName()+"给会员"+user2.getLoginName()+"转入"+fhMoney);
		loginService.createFhRecord(fhRecord2);
		
		ServletActionContext.getRequest().setAttribute("message", "转账成功");
		return "goBackList61";
	}
	
	public String jiangjinzhuandianzibi() {
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		
		Double totalMoney = fhRecordDAO.findAllJiangjing(user.getUserId());
		if (fhMoney>totalMoney) {
			ServletActionContext.getRequest().setAttribute("message", "对不起您的奖金不能转换这么多报单币");
			return "goBackList21";
		}
		
		FhRecord fhRecord = new FhRecord();
		fhRecord.setUser(user);
		fhRecord.setCreateDate(new Date());
		fhRecord.setFhmoney(fhMoney*1.0);
		fhRecord.setFhType("15"); //奖金转换电子币-转入
		fhRecord.setMemo("奖金转换报单币 转入"+fhMoney);
		loginService.createFhRecord(fhRecord);
		
		FhRecord fhRecord2 = new FhRecord();
		fhRecord2.setUser(user);
		fhRecord2.setCreateDate(new Date());
		fhRecord2.setFhmoney(-fhMoney*1.0);
		fhRecord2.setFhType("16");//奖金转换电子币-转出
		fhRecord2.setMemo("奖金转换电子币-转出");
		loginService.createFhRecord(fhRecord2);
		
		ServletActionContext.getRequest().setAttribute("message", "奖金转换电子币成功");
		return "goBackList21";
		
	}
	
	//体现TODO
	@SuppressWarnings("unchecked")
	public String initTixian() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User loginUser = (User)request.getSession().getAttribute("user");
		
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		conditionProperties.put("userId", loginUser.getUserId());
		
		Map<String, Integer> compare = new HashMap<String, Integer>();
		compare.put("userId", 0);
		
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("appDate", false);
		
		int count_size =tixianDAO.cout_size(conditionProperties, compare);
				
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count_size));
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
		
		
		List<Tixian> litPager = tixianDAO.findAllPagerList_new(conditionProperties, compare, sort, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		
		//Double totalMoney = fhRecordDAO.findTotalMoneyByUserId(loginUser.getUserId());
		Double totalMoney = fhRecordDAO.findAllJiangjing(loginUser.getUserId());
		request.setAttribute("litPager", litPager);
		request.setAttribute("totalMoney", totalMoney);
		
		return "initTixian";
	}
	
	public String startTixian() {
		//查询是否有提现记录，有的话读出来
		HttpServletRequest request = ServletActionContext.getRequest();
		User loginUser = (User)request.getSession().getAttribute("user");
		List tempList=tixianDAO.findEntityByPropertiName("userId", loginUser.getUserId());
		if(tempList!=null&&tempList.size()>0){
			tixian=(Tixian) tempList.get(0);
		}
		return "startTixian";
	}
	
	//todo
	public String apptixian () {
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		Double totalMoney = fhRecordDAO.findAllJiangjing(user.getUserId());
		
		if ((tixianMoney%100)!=0.0) {
			ServletActionContext.getRequest().setAttribute("message", "您的体现金额必须是100的整数才能体现");
			return "gobackList3";
		}
		
		if (tixianMoney<200) {
			ServletActionContext.getRequest().setAttribute("message", "您的体现金额必须为200或大于200的整数才能体现");
			return "gobackList3";
		}
		
		if (totalMoney<200) {
			ServletActionContext.getRequest().setAttribute("message", "您的余额必须大于200才能提现");
			return "gobackList3";
		}
		
		if (totalMoney<tixianMoney) {
			ServletActionContext.getRequest().setAttribute("message", "提现金额大于您的账户余额请联系公司为您存值");
			return "gobackList3";
		}
		
		tixianMoney =  (tixianMoney/100)*100;
		
		Tixian tixian = new Tixian();
		tixian.setUser(user);
		tixian.setAccount(account);
		tixian.setBankName(bankName);
		if(accountHolder!=null)
			tixian.setAccountHolder(accountHolder);
		tixian.setOpenBankAddr(openBankAddr);
		tixian.setAppDate(new Date());
		tixian.setTixianMoney(tixianMoney);
		tixian.setType("0");
		
		tixianDAO.create(tixian);
		
		FhRecord fhRecord = new FhRecord();
		fhRecord.setUser(tixian.getUser());
		fhRecord.setCreateDate(new Date());
		fhRecord.setFhmoney(-tixian.getTixianMoney());
		fhRecord.setFhType("6"); //提现6
		fhRecord.setMemo("会员"+tixian.getUser().getUserName()+"提现"+tixian.getTixianMoney());
		loginService.createFhRecord(fhRecord);
		
		ServletActionContext.getRequest().setAttribute("message", "提现申请成功");
		return "startTixian";
	}
	
	public String listAllTixian () {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		Map<String, Integer> compare = new HashMap<String, Integer>();
		
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("appDate", false);
		if (null!=type && type.equals("1")) {
			conditionProperties.put("type", type);
			compare.put("type", 0);
		}else {
			conditionProperties.put("type", "1");
			compare.put("type", 1);
		}
		int count_size =tixianDAO.cout_size_Commen(conditionProperties, compare);
				
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count_size));
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
		DecimalFormat   fnum   =   new   DecimalFormat("##0.00"); //四舍五入，保留两位小数 
		List<Tixian> litPager = tixianDAO.findAllPagerList(conditionProperties, compare, sort, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		if(litPager.size()>0){
			for(Tixian t:litPager){
				User u = t.getUser();
				Double ktx = ketixian_money(u.getUserId());
				if(ktx==null)
					ktx = 0.0;
				u.setTotalMoney(new BigDecimal(ktx).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
				t.setUser(u);
			}
		}
		request.setAttribute("litPager", litPager);
		request.setAttribute("type", type);
		return "listAllTixian";
	}
	
	public Double ketixian_money(Integer userId){
		
		List yfk  = ordersDAO.findLevelUserOrderMoney(userId, "4", "all");
		Double yfkOrder1  = (Double) yfk.get(0);
		Double yfkOrder2  = (Double) yfk.get(1);
		Double yfkOrder3  = (Double) yfk.get(2);
		Double tolyj = yfkOrder1+yfkOrder2+yfkOrder3;
		Double ytxMoney = tixianDAO.countTiXianMeth(userId, "1");//已提现金额
		Double ktxMoney = tolyj-ytxMoney;
		return ktxMoney;
	}
	
	public String listAllFinance () {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("appDate", false);
		
		int count_size =tixianDAO.cout_size(null, null);
				
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count_size));
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
		
		List<Tixian> litPager = tixianDAO.findAllPagerList_new(null, null, sort, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		request.setAttribute("litPager", litPager);
		
		return "listAllFinance";
	}
	
	public String tixiansh () {
		Tixian tixian = (Tixian)tixianDAO.findById(txId);
		
		//Double totalMoney = fhRecordDAO.findTotalMoneyByUserId(tixian.getUserId());
		//Double totalMoney = fhRecordDAO.findAllJiangjing(tixian.getUserId());
		
		/*if (totalMoney<tixian.getTixianMoney()) {
			ServletActionContext.getRequest().setAttribute("message", "提现金额大于会员的账户余额");
			return "gobackList4";
		}*/
		
		tixian.setType("1");//提现成功
		tixian.setTixianDate(new Date());
		tixianDAO.update(tixian);
		
		ServletActionContext.getRequest().setAttribute("message", "提现申请成功");
		User user = tixian.getUser();
		if(user == null)
			user = (User)userDAO.findById(tixian.getUserId());
		if(user != null)
			HttpConnectionUtil.sendHttpMsg("2", user, tixian, null,null);
		return "gobackList4";
	}
	
	
	public String deleteUser() throws Exception {
		User user = loginService.findUserByUserId(userId);
		
		if (user != null) {
			User leftUser = user.getChildOne();
			User rightUser = user.getChildTwo();
			if (null != leftUser || null != rightUser) {
				ServletActionContext.getRequest().setAttribute("message", "该用户节点下面还有其他子节点无法删除");
				return "goBackList";
			}
			Double totalMoney = fhRecordDAO.findTotalMoneyByUserId(user.getUserId());
			if (totalMoney!=0.0) {
				ServletActionContext.getRequest().setAttribute("message", "该用户已参与奖金结算无法删除");
				return "goBackList";
			}
			
			User parent = user.getParent();
			if (null!=parent) {
				User one = parent.getChildOne();
				if (null!=one) {
					if (one.getUserId().equals(user.getUserId())) {
						parent.setChildOne(null);
					}
					else {
						parent.setChildTwo(null);
					}
					loginService.updateUser(parent);
				}
			}
			
			loginService.deleteUser(user);
			ServletActionContext.getRequest().setAttribute("message", "删除成功");
		}
		else {
			ServletActionContext.getRequest().setAttribute("message", "此用户不存在");
		}
		return "goBackList";
	}
	
	public String changeLevel () {
		User user = loginService.findUserByUserId(userId);
		if (user != null) { 
			user.setSubmitMoney(submitMoney);
			String level = "1";
			switch (submitMoney) {
				case 1600:level="1";break;
				case 3200:level="2";break;
				case 6400:level="3";break;
				case 12800:level="4";break;
				default:
					break;
			}
			user.setLevel(level);
			userDAO.update(user);
			ServletActionContext.getRequest().setAttribute("message", "操作成功");
		}
		else {
			ServletActionContext.getRequest().setAttribute("message", "此用户不存在");
		}
		return "goBackList";
	}
	
	public String blockUser() {
		User user = loginService.findUserByUserId(userId);
		if (user != null) { 
			user.setBlock("1");//锁定用户
			userDAO.update(user);
			ServletActionContext.getRequest().setAttribute("message", "锁定成功");
		}
		else {
			ServletActionContext.getRequest().setAttribute("message", "此用户不存在");
		}
		return "goBackList";
	}
	
	public String releaseUser() {
		User user = loginService.findUserByUserId(userId);
		if (user != null) { 
			user.setBlock("0");//解除用户
			userDAO.update(user);
			ServletActionContext.getRequest().setAttribute("message", "解除成功");
		}
		else {
			ServletActionContext.getRequest().setAttribute("message", "此用户不存在");
		}
		return "goBackList";
	}
	
	//回填单是否回填
	public String isHTApp() {
		User user = loginService.findUserByUserId(userId);
		if (user != null) { 
			user.setIsHTApp("1");
			userDAO.update(user);
			ServletActionContext.getRequest().setAttribute("message", "操作成功");
		}
		else {
			ServletActionContext.getRequest().setAttribute("message", "此用户不存在");
		}
		return "goBackList";
	}
	
	public String updateUser() throws Exception  {
		return "goBackList";
	}
	
	public String initModifyPwd() throws Exception {			
		return "initModifyPwd";
	}
	
	//存值列表
	public String listAllchunzhi () {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		conditionProperties.put("userId", -1);
		conditionProperties.put("cardStatus", "0");
		
		Map<String, Integer> compare = new HashMap<String, Integer>();
		compare.put("userId", 12);
		compare.put("cardStatus", 0);
		
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("createDate", false);
		
		int count_size =cardDAO.cout_size(conditionProperties, compare);
				
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count_size));
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
		
		List<Card> litPager = cardDAO.findAllPagerList_new(conditionProperties, compare, sort, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		request.setAttribute("litPager", litPager);
		
		return "listAllchunzhi";
	}
	
	//转入转出报单币
	public String listAllzhuanruzhuanchu () {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		conditionProperties.put("fhType",new String[]{"7","8"});
		
		Map<String, Integer> compare = new HashMap<String, Integer>();
		compare.put("fhType", 4);
		
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("createDate", false);
		
		if (null!=loginName&&!"".equals(loginName)) {
			User user2 = loginService.findUserByLoginName(loginName);
			if (null!=user2) {
				conditionProperties.put("userId", user2.getUserId());
				compare.put("userId", 0);
			}
		}
		
		int count_size =fhRecordDAO.cout_size(conditionProperties, compare);
				
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count_size));
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
		
		List<FhRecord> litPager = fhRecordDAO.findAllPagerList_new(conditionProperties, compare, sort, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		request.setAttribute("litPager", litPager);
		
		return "listAllzhuanruzhuanchu";
	}
	
	public String listAllReffer () {
		System.out.println(" loginName>>>> "+loginName);
		Map<Integer,String[]> map = userDAO.findAllReffer(loginName,0,0,"all");
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			this.setPagerMethod("next");
		}
		System.out.println("map.get(1) "+map.get(1)[0]);
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), Integer.valueOf(map.get(1)[0]+"")));
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
		Map litPager = userDAO.findAllReffer(loginName, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("litPager", litPager);
		
		return "listAllReffer";
	}
	
	public static void main(String[] args) {
		Double abc = 345.89;
		System.out.println(abc.intValue());
		Integer a = 10;
		Integer b= 10;
		System.out.println((a==b));
		
		System.out.println((int)(0.03/0.01));
	}
	public String getTjrLoginName() {
		return tjrLoginName;
	}
	public void setTjrLoginName(String tjrLoginName) {
		this.tjrLoginName = tjrLoginName;
	}
	
	public String shouzhi_tongji(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Double zshr = ordersDAO.zong_shou_ru(null, null);
		Double zzhch= tixianDAO.zong_zhi_chu(null, null);
		Double jdshr = ordersDAO.zong_shou_ru(j_fromDate, j_endDate);
		Double jdzhch= tixianDAO.zong_zhi_chu(j_fromDate, j_endDate);
		request.setAttribute("zshr", zshr);
		request.setAttribute("zzhch", zzhch);
		request.setAttribute("zlr", zshr-zzhch);
		request.setAttribute("jdshr", jdshr);
		request.setAttribute("jdzhch", jdzhch);
		request.setAttribute("jdlr", jdshr-jdzhch);
		return "shouzhi_tongji";
	}
		
}
