package com.tw.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.Namespace;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionChainResult;
import com.tw.web.dao.AdminUserDAO;
import com.tw.web.dao.CardDAO;
import com.tw.web.dao.FhRecordDAO;
import com.tw.web.dao.TixianDAO;
import com.tw.web.dao.UserDAO;
import com.tw.web.dao.UserOrderDAO;
import com.tw.web.hibernate.persistent.AdminUser;
import com.tw.web.hibernate.persistent.Card;
import com.tw.web.hibernate.persistent.FhRecord;
import com.tw.web.hibernate.persistent.Tixian;
import com.tw.web.hibernate.persistent.User;
import com.tw.web.service.LoginService;


@SuppressWarnings("serial")
@ParentPackage("app-default")
@Namespace("")
@Results(
		{
			@Result(name="initAddUser", 			value="/WEB-INF/jsp/member/addmember.jsp"),
			//@Result(name="initModifyUser", 		value="/WEB-INF/jsp/sys/addUser.jsp"),
			@Result(name="initModifyPassword", 		value="/WEB-INF/jsp/sys/modifyPwd.jsp"),
			@Result(name="confimPassword2", 		value="/WEB-INF/jsp/member/confimPassword2.jsp"),
			
			@Result(name="initModifyUser", 			value="/WEB-INF/jsp/member/updateMember.jsp"),
			@Result(name="initTixian", 			value="/WEB-INF/jsp/member/tixianList.jsp"),
			@Result(name="startTixian", 			value="/WEB-INF/jsp/member/addFinance.jsp"),
			
			@Result(name="listAllTixian", 			value="/WEB-INF/jsp/member/listAllTixian.jsp"),
			@Result(name="listAllFinance", 			value="/WEB-INF/jsp/member/listAllFinance.jsp"),
			
			@Result(name="refferAllList", 			value="/WEB-INF/jsp/member/refferAllList.jsp"),
			@Result(name="listbonus", 			value="/WEB-INF/jsp/member/listbonus.jsp"),
			@Result(name="listAllzhuanruzhuanchu", 		value="/WEB-INF/jsp/member/listAllzhuanruzhuanchu.jsp"),
			@Result(name="listAllchunzhi", 		value="/WEB-INF/jsp/member/listAllchunzhi.jsp"),
			
			@Result(name="initCard", 			value="/WEB-INF/jsp/products/addcards.jsp"),
			@Result(name="obtainCard", 			value="/WEB-INF/jsp/products/obtainCardList.jsp"),
			
			
			@Result(name="initZhuanzhang", 		value="/WEB-INF/jsp/member/zhuanzhanglist.jsp"),
			@Result(name="initjiangjinzhuanbaodanbi", 		value="/WEB-INF/jsp/member/zhuanzhanglist1.jsp"),
			
			@Result(name="createUser", 							value="/WEB-INF/jsp/sys/addUser.jsp"),
			@Result(name="listAllUser", 			value="/WEB-INF/jsp/sys/userList.jsp"),	
			@Result(name="listAllAdminUser", 			value="/WEB-INF/jsp/sys/userList1.jsp"),
			
			@Result(name="initModifyPwd", 			value="/WEB-INF/jsp/sys/modifyPwd.jsp"),				
			@Result(name="modifyPwd", 			value="/WEB-INF/jsp/sys/modifyPwd.jsp"),			
			@Result(name="intcanyuhuodong", 			value="/WEB-INF/jsp/products/canyuhuodong.jsp"),
			
			
			
			@Result(name="goBackList10", type=ActionChainResult.class, 	value="user", params = {"method", "initModifyUser"}),
			@Result(name="goBackList20", type=ActionChainResult.class, 	value="user", params = {"method", "initModifyPassword"}),
			@Result(name="goBackList21", type=ActionChainResult.class, 	value="user", params = {"method", "initjiangjinzhuanbaodanbi"}),
			@Result(name="goBackList31", type=ActionChainResult.class, 	value="user", params = {"method", "initCard"}),
			@Result(name="goBackList", type=ActionChainResult.class, 	value="user", params = {"method", "listAllUser"}),
			@Result(name="goBackList2", type=ActionChainResult.class, 	value="user", params = {"method", "initZhuanzhang"}),
			@Result(name="gobackList3", type=ActionChainResult.class, 	value="user", params = {"method", "initTixian"}),
			@Result(name="gobackList4", type=ActionChainResult.class, 	value="user", params = {"method", "listAllTixian"})
		}
)
public class TAreaAction extends ExtJSONActionSuport {
	
	private final int dataT = 5;
	private LoginService loginService;
	private FhRecordDAO fhRecordDAO;
	private UserDAO userDAO;
	private TixianDAO tixianDAO;
	private AdminUserDAO adminUserDAO;
	private CardDAO cardDAO;
	private UserOrderDAO userOrderDAO;
		
	private Integer userId;
	private String flag;
	private String centerLoginName;
	private String loginName;
	private String password;
	private String newPassword1;
	private String newPassword2;
	private String password2Flag;
	private String password2;
	private String userName;
	private String phone;
	private String parentId;
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
	
	private String cardNo;
	private String cardPassword;
	
	
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

	public String getCenterLoginName() {
		return centerLoginName;
	}

	public void setCenterLoginName(String centerLoginName) {
		this.centerLoginName = centerLoginName;
	}

	public String getParentLoginName() {
		return parentLoginName;
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
	
	public UserOrderDAO getUserOrderDAO() {
		return userOrderDAO;
	}
	@Autowired
	public void setUserOrderDAO(UserOrderDAO userOrderDAO) {
		this.userOrderDAO = userOrderDAO;
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String activityUser () throws Exception {
		User user = loginService.findUserByUserId(userId);
		
		loginService.updateUser(user);
		return "goBackList";
	}
	
	public String intcanyuhuodong() {
		return "intcanyuhuodong";
	}
	

	public String initArea() {
		return "initCard";
	}
	
	public String updateCard () throws Exception { //跟新卡的状态
		Card card = cardDAO.findCardByCardNo(cardNo,cardPassword,"1");
		if (null !=card) {
			card.setCardStatus("1"); //已经使用过了
			cardDAO.update(card);
		}
		
		return "initAdd";
	}
	
	
	public String goumaiCard() {
		User loginUser = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		
		
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
//		if (!lName.equals(loginName)) {
//			List<User> userL = userDAO.findEntityByPropertiName("loginName", loginName);
//			if (null != userL && userL.size()!=0) {
//				User user2 = userL.get(0); 
//				String loginName3 = user2.getLoginName();
//				if (loginName.equals(loginName3)) {
//					ServletActionContext.getRequest().setAttribute("message", "系统已经存在此用户名");
//					User user1 = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
//					ServletActionContext.getRequest().setAttribute("users", user1);
//					
//					return "initModifyUser";
//				}
//			}
//		}
		
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
			return "gobackList3";
		}
		else if ("2".equals(password2Flag)) {//电子币转账
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
		
		return "confimPassword2";
	}
	
	//电子币转账
	public String initZhuanzhang() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		User loginUser = (User)request.getSession().getAttribute("user");
		
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		conditionProperties.put("userId", loginUser.getUserId());
		conditionProperties.put("fhType", new String[]{"7","8"});
		
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
			return "goBackList2";
		}
		
		if (user.getUserId()==user2.getUserId()) {
			ServletActionContext.getRequest().setAttribute("message", "对不起自己不能和自己转账");
			return "goBackList2";
		}
		
		Double totalMoney = fhRecordDAO.findAllBaodanBi(user.getUserId());
		if (fhMoney>totalMoney) {
			ServletActionContext.getRequest().setAttribute("message", "对不起您的剩余报单币少于转账额度请管理人员存值");
			return "goBackList2";
		}
		if (fhMoney<100) {
			ServletActionContext.getRequest().setAttribute("message", "对不起每次转币不能少于100");
			return "goBackList2";
		}
		
		FhRecord fhRecord = new FhRecord();
		fhRecord.setUser(user);
		fhRecord.setCreateDate(new Date());
		fhRecord.setFhmoney(-fhMoney*1.0);
		fhRecord.setFhType("7"); //电子转账7 转出
		loginService.createFhRecord(fhRecord);
		
		FhRecord fhRecord2 = new FhRecord();
		fhRecord2.setUser(user2);
		fhRecord2.setCreateDate(new Date());
		fhRecord2.setFhmoney(fhMoney*1.0);
		fhRecord2.setFhType("8");//电子转账8 转入
		loginService.createFhRecord(fhRecord2);
		
		ServletActionContext.getRequest().setAttribute("message", "转账成功");
		return "goBackList2";
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
		return "startTixian";
	}
	
	//todo
	public String apptixian () {
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		
		//Double totalMoney = fhRecordDAO.findTotalMoneyByUserId(user.getUserId());
		Double totalMoney = fhRecordDAO.findAllJiangjing(user.getUserId());
		
		/*if (totalMoney<100) {
			ServletActionContext.getRequest().setAttribute("message", "您的余额必须大于500才能提现");
			return "gobackList3";
		}
		
		if (totalMoney<tixianMoney) {
			ServletActionContext.getRequest().setAttribute("message", "提现金额大于您的账户余额请联系公司为您存值");
			return "gobackList3";
		}*/
		
		if (tixianMoney<100) {
			ServletActionContext.getRequest().setAttribute("message", "您的体现金额必须大于100才能提现");
			return "gobackList3";
		}
		
		if ((tixianMoney%100)!=0.0) {
			ServletActionContext.getRequest().setAttribute("message", "您的体现金额必须是100的整数才能体现");
			return "gobackList3";
		}
		
		if (totalMoney<500) {
			ServletActionContext.getRequest().setAttribute("message", "您的余额必须大于500才能提现");
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
		tixian.setAccountHolder(accountHolder);
		tixian.setOpenBankAddr(openBankAddr);
		tixian.setAppDate(new Date());
		tixian.setTixianMoney(tixianMoney);
		tixian.setType("0");
		
		tixianDAO.create(tixian);
		
		FhRecord fhRecord = new FhRecord();
		fhRecord.setUser(tixian.getUser());
		fhRecord.setCreateDate(new Date());
		fhRecord.setFhmoney(-tixian.getTixianMoney()); //扣除5%做为手续费
		fhRecord.setFhType("13"); //提现13
		fhRecord.setMemo("会员"+tixian.getUser().getUserName()+"提现"+tixian.getTixianMoney());
		loginService.createFhRecord(fhRecord);
		
		ServletActionContext.getRequest().setAttribute("message", "提现申请成功");
		return "startTixian";
	}
	
	public String listAllTixian () {
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
		
		return "listAllTixian";
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
		Double totalMoney = fhRecordDAO.findAllJiangjing(tixian.getUserId());
		
		/*if (totalMoney<tixian.getTixianMoney()) {
			ServletActionContext.getRequest().setAttribute("message", "提现金额大于会员的账户余额");
			return "gobackList4";
		}*/
		
		tixian.setType("1");//提现成功
		tixian.setTixianDate(new Date());
		tixianDAO.update(tixian);
		
		ServletActionContext.getRequest().setAttribute("message", "提现申请成功");
		return "gobackList4";
	}
	
	
	public String deleteUser() throws Exception {
		User user = loginService.findUserByUserId(userId);
		
		if (user != null) {
			Double totalMoney = fhRecordDAO.findTotalMoneyByUserId(user.getUserId());
			if (totalMoney!=0.0) {
				ServletActionContext.getRequest().setAttribute("message", "该用户已参与奖金结算无法删除");
				return "goBackList";
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
			String level = "1";
			switch (submitMoney) {
				case 1600:level="1";break;
				case 3200:level="2";break;
				case 6400:level="3";break;
				case 12800:level="4";break;
				default:
					break;
			}
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
		conditionProperties.put("fhType","6");
		
		
		Map<String, Integer> compare = new HashMap<String, Integer>();
		compare.put("fhType", 0);
		
		
		if (null!=loginName&&!"".equals(loginName)) {
			User user2 = loginService.findUserByLoginName(loginName);
			if (null!=user2) {
				conditionProperties.put("userId", user2.getUserId());
				compare.put("userId", 0);
			}
		}
		
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
		
		List<FhRecord> litPager = fhRecordDAO.findAllPagerList_new(conditionProperties, compare, sort, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
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
	
	public String fenpeiCard() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		
		Card card = cardDAO.findCardByCardTypeAndCardStatus("1");
		if (null != card) {
			card.setUserId(user.getUserId());
			cardDAO.update(card);
		}
		
		request.setAttribute("card", card);
		return "fenpeiCard";
	}
	
	
	public String obtainCard() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		conditionProperties.put("userId",user.getUserId());
		
		Map<String, Integer> compare = new HashMap<String, Integer>();
		compare.put("userId", 0);
		
		int count_size =cardDAO.cout_size(conditionProperties, compare);
		
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count_size));
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
		
		List<Card> litPager = cardDAO.findAllPagerList_new(conditionProperties, compare, null, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		request.setAttribute("litPager", litPager);
		
		return "obtainCard";
	}
	
	
	public void generationRandCard() {
		loginService.generationCardNoAndPassword();
	}
	
	public static void main(String[] args) {
		/*Double abc = 345.89;
		System.out.println(abc.intValue());
		Integer a = 10;
		Integer b= 10;
		System.out.println((a==b));*/
		
	/*	Map map = new HashMap();
		for (int i=7;i<32;i++) {
			map.put(i, i);
		}
		if (map.containsKey(BigInteger.valueOf(9).intValue())) {
			System.out.println("4444");
		}
		Object[] abc = {"1","2111111111111"};
		BigInteger ddd = new BigInteger(abc[1].toString());
		System.out.println(ddd);
		
		 int  radom = (int)(Math.random()*10);

         System.out.println(radom); 
*/
		// 种子你可以随意生成，但不能重复 
		for (int ii=0; ii<10; ii++) {
			String[] seed = { "1", "2", "3", "4", "5", "6", "7", "8", "9","0","a","b","c","e","F","R","Z","Q"};  
			int[] ranArr = new int[10]; 
			String radCardNo = "";
			String radCardPassword = "";
			Random ran = new Random(); 
			// 数量你可以自己定义。 
			for (int i = 0; i < seed.length-9; i++) { 
				// 得到一个位置 
				int j = ran.nextInt(seed.length - i); 
				// 得到那个位置的数值 
				radCardNo += seed[j]; 
				radCardPassword+=seed[j]+j;
				
				// 将最后一个未用的数字放到这里 
				seed[j] = seed[seed.length - 1 - i]; 
			
			} 
			System.out.println("radCardNo:" +radCardNo+"       radCardPassword:" +radCardPassword); 
		}
		
	}
		
}
