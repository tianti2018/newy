package com.tw.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.opensymphony.xwork2.ActionChainResult;
import com.tw.web.dao.CardDAO;
import com.tw.web.dao.FhRecordDAO;
import com.tw.web.dao.OrdersDAO;
import com.tw.web.dao.RepeatDAO;
import com.tw.web.dao.TAreaDao;
import com.tw.web.dao.UserDAO;
import com.tw.web.dao.UserOrderDAO;
import com.tw.web.hibernate.persistent.AdminUser;
import com.tw.web.hibernate.persistent.Card;
import com.tw.web.hibernate.persistent.FhRecord;
import com.tw.web.hibernate.persistent.Orders;
import com.tw.web.hibernate.persistent.Repeat;
import com.tw.web.hibernate.persistent.TArea;
import com.tw.web.hibernate.persistent.User;
import com.tw.web.hibernate.persistent.UserOrder;
import com.tw.web.service.LoginService;
import com.tw.web.util.CommUtils;

@SuppressWarnings("serial")
@ParentPackage("app-default")
@Namespace("")
@Results(
		{
			@Result(name="loadMember", 		value="/WEB-INF/jsp/member/membertree.jsp"),
			@Result(name="listAllFhRecord", 		value="/WEB-INF/jsp/member/fhRecordList.jsp"),
			@Result(name="listAlljifenByOrder", 		value="/WEB-INF/jsp/member/memberFhRecordList.jsp"),
			@Result(name="initAppCenter", 		value="/WEB-INF/jsp/member/appCenter.jsp"),
			@Result(name="listAllappCenter", 		value="/WEB-INF/jsp/member/appCenterList.jsp"),
			@Result(name="listAllRepeat", 		value="/WEB-INF/jsp/member/repeatList.jsp"),
			@Result(name="addmember", 		value="/WEB-INF/jsp/member/addmember.jsp"),
			@Result(name="error", 		value="/login.jsp"),	
			@Result(name="listAlljifen", 		value="/WEB-INF/jsp/member/listAlljifen.jsp"),
			@Result(name="listAllFensi", 		value="/WEB-INF/jsp/member/listAllFensi.jsp"),
			@Result(name="listAllFhRecordCount", 		value="/WEB-INF/jsp/member/listAllFhRecordCount.jsp"),
			
			
			@Result(name="listAllUserOrder", value="/WEB-INF/jsp/member/userOrderList.jsp"),	
			
			@Result(name="goinitAppCenter", type=ActionChainResult.class, 	value="member", params = {"method", "initAppCenter"}),
			@Result(name="gobackListAllappCenter", type=ActionChainResult.class, 	value="member", params = {"method", "listAllappCenter"}),
			
		}
)
public class MemberAction extends ExtJSONActionSuport {
	
	private LoginService loginService;
	private FhRecordDAO fhRecordDAO;
	private UserDAO userDAO;
	private RepeatDAO repeatDAO;
	private TAreaDao tAreaDao;
	private String loginName;
	private String password;
	private String random;
	private CardDAO cardDAO;
	private OrdersDAO ordersDAO;
	
	private Integer uoId;
	
	private UserOrderDAO userOrderDAO;
	
	private Integer userId;
	private String areaType;
	private Integer areaId;
	private String cardNo;
	private String cardPassword;
	
	private Integer areaId1;
	private Integer areaId2;
	private Integer areaId3;
	
	public Integer getAreaId1() {
		return areaId1;
	}
	public void setAreaId1(Integer areaId1) {
		this.areaId1 = areaId1;
	}
	public Integer getAreaId2() {
		return areaId2;
	}
	public void setAreaId2(Integer areaId2) {
		this.areaId2 = areaId2;
	}
	public Integer getAreaId3() {
		return areaId3;
	}
	public void setAreaId3(Integer areaId3) {
		this.areaId3 = areaId3;
	}
	public OrdersDAO getOrdersDAO() {
		return ordersDAO;
	}
	@Autowired
	public void setOrdersDAO(OrdersDAO ordersDAO) {
		this.ordersDAO = ordersDAO;
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
	public CardDAO getCardDAO() {
		return cardDAO;
	}
	@Autowired
	public void setCardDAO(CardDAO cardDAO) {
		this.cardDAO = cardDAO;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public TAreaDao gettAreaDao() {
		return tAreaDao;
	}
	@Autowired
	public void settAreaDao(TAreaDao tAreaDao) {
		this.tAreaDao = tAreaDao;
	}
	public RepeatDAO getRepeatDAO() {
		return repeatDAO;
	}
	@Autowired
	public void setRepeatDAO(RepeatDAO repeatDAO) {
		this.repeatDAO = repeatDAO;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public Integer getUoId() {
		return uoId;
	}
	public void setUoId(Integer uoId) {
		this.uoId = uoId;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public LoginService getLoginService() {
		return loginService;
	}
	
	public UserOrderDAO getUserOrderDAO() {
		return userOrderDAO;
	}
	@Autowired
	public void setUserOrderDAO(UserOrderDAO userOrderDAO) {
		this.userOrderDAO = userOrderDAO;
	}
	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String intAddMember() throws Exception {
		
		return "addmember";
	}
	
	
	public FhRecordDAO getFhRecordDAO() {
		return fhRecordDAO;
	}
	@Autowired
	public void setFhRecordDAO(FhRecordDAO fhRecordDAO) {
		this.fhRecordDAO = fhRecordDAO;
	}
	
	//我得粉丝
	public String listAllFensi() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Object loginUser = null;
		loginUser = request.getSession().getAttribute("user");
		
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		conditionProperties.put("referrerId", (loginUser instanceof AdminUser)?userId:((User)loginUser).getUserId());
		Map<String, Integer> compare = new HashMap<String, Integer>();
		compare.put("referrerId", 0);
		
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("buyCount", false);
		
		int count_size =userDAO.cout_size(conditionProperties, compare);
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count_size));
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
		
		List<User> litPager = userDAO.findAllPagerList_new(conditionProperties, compare, sort, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		request.setAttribute("litPager", litPager);
		
		return "listAllFensi";
	}
	
	//根据会员参与的序号罗列出所有得到的积分
	public String listAlljifen() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Object loginUser = null;
		loginUser = request.getSession().getAttribute("user");
		
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		//conditionProperties.put("userId", (loginUser instanceof AdminUser)?userId:((User)loginUser).getUserId());
		conditionProperties.put("userId", (loginUser instanceof AdminUser)?userId:((User)loginUser).getUserId());
		Map<String, Integer> compare = new HashMap<String, Integer>();
		compare.put("userId", 0);
		
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
		
		//剩余积分
		Double totalMoney = fhRecordDAO.findAllJiangjing((loginUser instanceof AdminUser)?userId:((User)loginUser).getUserId());
		request.setAttribute("totalMoney", totalMoney);
		
		//总积分
		Double totalTJiFen = fhRecordDAO.findTotalJiFenOneByUserId((loginUser instanceof AdminUser)?userId:((User)loginUser).getUserId(),new String[]{"3","4","5","9","10","11","13","14"});
		ServletActionContext.getRequest().setAttribute("totalTJiFen", totalTJiFen);
		
		//品牌建设分红
		Double jiansheJiFen = fhRecordDAO.findTotalJiFenOneByUserId((loginUser instanceof AdminUser)?userId:((User)loginUser).getUserId(),new String[]{"3"});
		ServletActionContext.getRequest().setAttribute("jiansheJiFen", jiansheJiFen);
		
		//服务分红
		Double fuwuJiFen = fhRecordDAO.findTotalJiFenOneByUserId((loginUser instanceof AdminUser)?userId:((User)loginUser).getUserId(),new String[]{"4"});
		ServletActionContext.getRequest().setAttribute("fuwuJiFen", fuwuJiFen);
		
		//分享分红
		Double fenxiangJiFen = fhRecordDAO.findTotalJiFenOneByUserId((loginUser instanceof AdminUser)?userId:((User)loginUser).getUserId(),new String[]{"5"});
		ServletActionContext.getRequest().setAttribute("fenxiangJiFen", fenxiangJiFen);
		
		//购买卡密
		Double goumaiKMJiFen = fhRecordDAO.findTotalJiFenOneByUserId((loginUser instanceof AdminUser)?userId:((User)loginUser).getUserId(),new String[]{"7"});
		ServletActionContext.getRequest().setAttribute("goumaiKMJiFen", goumaiKMJiFen);
		
		//提现扣除
		Double tixianJiFen = fhRecordDAO.findTotalJiFenOneByUserId((loginUser instanceof AdminUser)?userId:((User)loginUser).getUserId(),new String[]{"6"});
		ServletActionContext.getRequest().setAttribute("tixianJiFen", tixianJiFen);
		
		//抽奖所得积分
		Double coujianJifen = fhRecordDAO.findTotalJiFenOneByUserId((loginUser instanceof AdminUser)?userId:((User)loginUser).getUserId(),new String[]{"10"});
		ServletActionContext.getRequest().setAttribute("coujianJifen", coujianJifen);
		
		//转出积分
		Double zcJifen = fhRecordDAO.findTotalJiFenOneByUserId((loginUser instanceof AdminUser)?userId:((User)loginUser).getUserId(),new String[]{"8"});
		ServletActionContext.getRequest().setAttribute("zcJifen", zcJifen);
		
		//转入积分
		Double zrJifen = fhRecordDAO.findTotalJiFenOneByUserId((loginUser instanceof AdminUser)?userId:((User)loginUser).getUserId(),new String[]{"9"});
		ServletActionContext.getRequest().setAttribute("zrJifen", zrJifen);
		//复购积分
		Double fugouJiFen = fhRecordDAO.findTotalJiFenOneByUserId((loginUser instanceof AdminUser)?userId:((User)loginUser).getUserId(),new String[]{"11"});
		ServletActionContext.getRequest().setAttribute("fugouJiFen", fugouJiFen);	
		
		//招商奖
		Double zhaoshangJiFen = fhRecordDAO.findTotalJiFenOneByUserId((loginUser instanceof AdminUser)?userId:((User)loginUser).getUserId(),new String[]{"13"});
		ServletActionContext.getRequest().setAttribute("zhaoshangJiFen", zhaoshangJiFen);	
		
		//收益奖
		Double shouyiJiFen = fhRecordDAO.findTotalJiFenOneByUserId((loginUser instanceof AdminUser)?userId:((User)loginUser).getUserId(),new String[]{"14"});
		ServletActionContext.getRequest().setAttribute("shouyiJiFen", shouyiJiFen);	
		
		//管理津贴
		Double guanliJiFen = fhRecordDAO.findTotalJiFenOneByUserId((loginUser instanceof AdminUser)?userId:((User)loginUser).getUserId(),new String[]{"15"});
		ServletActionContext.getRequest().setAttribute("guanliJiFen", guanliJiFen);	
		
		return "listAlljifen";
	}
	
	//根据用户ID罗列出这个会员参与的序号
	public String listAllUserOrder() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User loginUser = (User)request.getSession().getAttribute("user");
		
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		conditionProperties.put("userId", loginUser.getUserId());
		
		Map<String, Integer> compare = new HashMap<String, Integer>();
		compare.put("userId", 0);
		
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("createDate", true);
		
		int count_size =userOrderDAO.cout_size(conditionProperties, compare);
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count_size));
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
		
		List<UserOrder> litPager = userOrderDAO.findAllPagerList_new(conditionProperties, compare, sort, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		request.setAttribute("litPager", litPager);
		
		return "listAllUserOrder";
	}
	
	//根据会员参与的序号罗列出所有得到的积分
	public String listAlljifenByOrder() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User loginUser = (User)request.getSession().getAttribute("user");
		
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		conditionProperties.put("uoId", uoId);
		
		Map<String, Integer> compare = new HashMap<String, Integer>();
		compare.put("uoId", 0);
		
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
		
		fhRecordDAO.findTotalJiFenByOrders(uoId);
		
		Double totalMoney = fhRecordDAO.findAllJiangjing(loginUser.getUserId());
		request.setAttribute("totalMoney", totalMoney);
		
		return "listAlljifenByOrder";
	}
	
	
	public String listAllRepeat() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User loginUser = (User)request.getSession().getAttribute("user");
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		conditionProperties.put("userId", loginUser.getUserId());
		
		Map<String, Integer> compare = new HashMap<String, Integer>();
		compare.put("userId", 0);
		
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("createDate", false);
		
		int count_size =repeatDAO.cout_size(conditionProperties, compare);
				
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count_size));
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
		
		List<Repeat> litPager = repeatDAO.findAllPagerList_new(conditionProperties, compare, sort, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		Double repeatMoney = repeatDAO.findTotalMoney(loginUser.getUserId());
		request.setAttribute("repeatMoney", repeatMoney);
		request.setAttribute("litPager", litPager);
		
		return "listAllRepeat";
	}
	
	public String initAppCenter () { //报单中心申请
		HttpServletRequest request = ServletActionContext.getRequest();
		User loginUser = (User)request.getSession().getAttribute("user");
		User user = (User)userDAO.findById(loginUser.getUserId());
		/*
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		conditionProperties.put("referrerId", loginUser.getUserId());
		
		Map<String, Integer> compare = new HashMap<String, Integer>();
		compare.put("referrerId", 0);
		
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("createDate", false);
		
		int count_size =userDAO.cout_size(conditionProperties, compare);
		request.setAttribute("count_size", count_size);*/
		request.setAttribute("user", user);
		
		List<TArea> areas = (List<TArea>)tAreaDao.findEntityByPropertiName("areaType", "1");
		request.setAttribute("areas", areas);
		
		List<User> user1 = (List<User>)userDAO.findEntityByPropertiName("isAppCenter","1");
		request.setAttribute("user1", user1);
		
		List<User> user2 = (List<User>)userDAO.findEntityByPropertiName("isAppCenter","2");
		request.setAttribute("user2", user2);

		return "initAppCenter";
	}
	
	public String appcenter() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User loginUser = (User)request.getSession().getAttribute("user");
		
		if ("1".equals(loginUser.getIsAppCenter()) || "2".equals(loginUser.getIsAppCenter())) {
			request.setAttribute("message", "您已申请无需再次申请");
			return "goinitAppCenter";
		}
		
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		conditionProperties.put("areaId", areaId3!=-1?areaId3:(areaId2!=-1?areaId2:areaId1));
		
		Map<String, Integer> compare = new HashMap<String, Integer>();
		compare.put("areaId", 0);
		List<User> userOne = (List<User>)userDAO.findAllPagerList_new(conditionProperties, compare, null, 0, 1, "page");
		if (null!=userOne&&userOne.size()==1) {
			request.setAttribute("message", "此省市的服务中心已被申请,请重新选择其他省市");
			return "goinitAppCenter";
		}
		
		Card card = cardDAO.findCardByCardNo(cardNo, cardPassword,"5");
		if (null!=card) {
			card.setCardStatus("1");
			card.setCreateDate(new Date());
			card.setMemo("申请服务中心 :登录会员编号【"+loginUser.getUserId()+"】会员名称：【"+loginUser.getLoginName()+"】");
			cardDAO.update(card);
			
			loginUser.setIsAppCenter("1");  //提交申请
			TArea tArea = (TArea)tAreaDao.findById(areaId3!=-1?areaId3:(areaId2!=-1?areaId2:areaId1));
			loginUser.settArea(tArea);
			loginUser.setAppDate(new Date());
			userDAO.update(loginUser);
			request.setAttribute("message", "提交申请成功");
			return "goinitAppCenter";
		}
		else {
			request.setAttribute("message", "此卡不存在请核实谢谢");
			return "goinitAppCenter";
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public String listAllappCenter() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		//conditionProperties.put("isAppCenter", new String[]{"1","2"});
		
		Map<String, Integer> compare = new HashMap<String, Integer>();
		//compare.put("isAppCenter", 4);
		
		
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("appDate", false);
		
		if (null!=loginName &&!"".equals(loginName))  {
			conditionProperties.put("loginName", loginName);
			compare.put("loginName", 0);
		}
		
		int count_size =userDAO.cout_size(conditionProperties, compare);
				
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count_size));
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
		
		List<User> litPager = userDAO.findAllPagerList_new(conditionProperties, compare, sort, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		request.setAttribute("litPager", litPager);
		
		return "listAllappCenter";
	}
	
	public String appCenterSH() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User)userDAO.findById(userId);
		
		user.setIsAppCenter("2");
		user.setShappDate(new Date());
		userDAO.update(user);
		
		/***************8
		 *         平台服务费                                                              赠送内容
		 * 省级服务中心  10万元      	 预收1万元                                     35套集联集面膜（2片装140盒）
		 * 市级服务中心  10万元     	 预收2万元                                     75套集联集面膜（2片装300盒）
		 * 区县服务中心   10万元    	预收5万元                                       200套集联集面膜（2片装800盒）
		 * 
		 */
		
		// 查看服务中心的服务地址是哪里
		TArea tArea = (TArea)tAreaDao.findById(user.getAreaId());
		
		Double refferMoney = 0.0; //招募服务中心费用
		Double serviceMoney = 0.0; // 邀请人服务费用
		String pname = ""; //赠送内容
		//省级服务中心  新增粉丝*40元  市级服务中心  新增粉丝*30元  区县服务中心 新增粉丝*20元
		switch (Integer.valueOf(tArea.getAreaType())) { // 判断当前服务中心是省级
			case 1:
				refferMoney = 10000*0.2;
				pname = "35套集联集面膜【2片装140盒】";
				break;
			case 2: //市级服务中心 找到所属的省级服务中心
				refferMoney = 20000*0.2;
				serviceMoney = 20000*0.1;
				pname = "75套集联集面膜【2片装300盒】";
				List<TArea> tlist = (List<TArea>)tAreaDao.findEntityByPropertiName("areaCode", tArea.getParentCode());
				if(null!=tlist&&tlist.size()!=0){
					TArea tArea2 = tlist.get(0); 
					List<User> tuser = (List<User>)userDAO.findEntityByPropertiName("areaId", tArea2.getAreaId()); //找到对应的服务中心负责人
					if (null!=tuser&&tuser.size()!=0) {
						User user2 = tuser.get(0);
						if ("2".equals(user2.getIsAppCenter())) {
							FhRecord fhRecord = new FhRecord();	
							fhRecord.setUser(user2);
							fhRecord.setCreateDate(new Date());
							fhRecord.setFhmoney(serviceMoney);
							fhRecord.setFhType("14"); //收益奖项
							fhRecord.setMemo("您的下属市级服务中心 【"+user.getLoginName()+"】成为【"+tArea.getAreaName()+"】服务中心");
							fhRecordDAO.create(fhRecord);	
						}
					}
				}
				break;
			case 3:
				refferMoney = 50000*0.2;
				pname = " 200套集联集面膜【2片装800盒】";
				List<TArea> tlist2 = (List<TArea>)tAreaDao.findEntityByPropertiName("areaCode", tArea.getParentCode());
				if(null!=tlist2&&tlist2.size()!=0){
					TArea tArea3 = tlist2.get(0); 
					List<User> tuser = (List<User>)userDAO.findEntityByPropertiName("areaId", tArea3.getAreaId()); //市服务中心 
					if (null!=tuser&&tuser.size()!=0) {
						User user2 = tuser.get(0);
						if ("2".equals(user2.getIsAppCenter())) {
							FhRecord fhRecord = new FhRecord();	
							fhRecord.setUser(user2);
							fhRecord.setCreateDate(new Date());
							fhRecord.setFhmoney(50000*0.1);
							fhRecord.setFhType("14"); //收益奖项
							fhRecord.setMemo("您的下属区县服务中心 【"+user.getLoginName()+"】成为【"+tArea.getAreaName()+"】服务中心");
							fhRecordDAO.create(fhRecord);	
						}
						//省服务中心 
						List<TArea> tlist3 = (List<TArea>)tAreaDao.findEntityByPropertiName("areaCode", tArea3.getParentCode());
						if (null!=tlist3&&tlist3.size()!=0) {
							TArea tArea4 = tlist3.get(0);
							List<User> tuser4 = (List<User>)userDAO.findEntityByPropertiName("areaId", tArea4.getAreaId()); //省服务中心负责人
							if (null!=tuser4&&tuser4.size()!=0) {
								User user3 = tuser4.get(0);
								if ("2".equals(user3.getIsAppCenter())) {
									FhRecord fhRecord2 = new FhRecord();	
									fhRecord2.setUser(user3);
									fhRecord2.setCreateDate(new Date());
									fhRecord2.setFhmoney(50000*0.02);
									fhRecord2.setFhType("14"); //收益奖项
									fhRecord2.setMemo("您的下属区县服务中心 【"+user.getLoginName()+"】成为【"+tArea.getAreaName()+"】服务中心");
									fhRecordDAO.create(fhRecord2);	
								}
							}
						}
					}
				}
				break;
		}
		
		//赠送内容
		List listOrders = ordersDAO.findEntityByPropertiName("userId", user.getUserId());
		if (null!=listOrders&&listOrders.size()!=0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
			String fd = sdf.format(new Date());
			
			Orders orders = (Orders)listOrders.get(0);
			Orders orders2 = new Orders();
			orders2.setUser(user);
			orders2.setAddress(orders.getAddress());
			orders2.setPname(pname);
			
			orders2.setCreateDate(new Date());
			orders2.setToUserName(orders.getToUserName());
			orders2.setMobile(orders.getMobile());
			orders2.setZipcode(orders.getZipcode());
			orders2.setOrdersBH("ZS"+fd+CommUtils.getSixRandom());
			orders2.setOrderType("5");
			ordersDAO.create(orders2);
		}
		
		//给邀请人招商奖：招募服务中心*20%
		User refferUser = (User)userDAO.findById(user.getReferrerId());
		FhRecord fhRecord = new FhRecord();	
		fhRecord.setUser(refferUser);
		fhRecord.setCreateDate(new Date());
		fhRecord.setFhmoney(refferMoney);
		fhRecord.setFhType("13"); //招商奖
		fhRecord.setMemo("招商奖项 被您招商的会员 【"+user.getLoginName()+"】成为【"+tArea.getAreaName()+"】服务中心");
		fhRecordDAO.create(fhRecord);	
		
		request.setAttribute("message", "审核通过");
		return "gobackListAllappCenter";
	}
	
	public String ListAllFhRecordByUserId () throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Object loginUser = null;
		
		loginUser = request.getSession().getAttribute("user");
		
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		conditionProperties.put("userId", (loginUser instanceof AdminUser)?userId:((User)loginUser).getUserId());
		
		Map<String, Integer> compare = new HashMap<String, Integer>();
		compare.put("userId", 0);
		
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
		Double totalMoney = fhRecordDAO.findTotalMoneyByUserId((loginUser instanceof AdminUser)?userId:((User)loginUser).getUserId());
		//Double todayMoney = fhRecordDAO.findTotalMoneyByUserIdAndData((loginUser instanceof AdminUser)?userId:((User)loginUser).getUserId());
		request.setAttribute("litPager", litPager);
		
		request.setAttribute("totalMoney", totalMoney);
		//request.setAttribute("todayMoney", todayMoney);
		
		request.setAttribute("userId", (loginUser instanceof AdminUser)?userId:((User)loginUser).getUserId());
		return "ListAllFhRecordByUserId";
	}
	
	public String listAllFhRecord () throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		List<FhRecord> litAll = loginService.findAllFhRecord("", true, 0, 0, "all");
				
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), litAll.size()));
		
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
						
		List<FhRecord> litPager = loginService.findAllFhRecord("createDate", false, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		
		request.setAttribute("litPager", litPager);
		
		return "listAllFhRecord";
		
	}
	
	public String listAllFhRecordCount () {
		HttpServletRequest request = ServletActionContext.getRequest();
		Object loginUser = null;
		loginUser = request.getSession().getAttribute("user");
		Integer userIdOne = (loginUser instanceof AdminUser)?userId:((User)loginUser).getUserId();
		List list = fhRecordDAO.findTotalMoneyByFhType(userIdOne);
		request.setAttribute("list", list);
		
		return "listAllFhRecordCount";
	}
	
	public String loadMember() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Object loginUserold = request.getSession().getAttribute("user");
		
		User loginUser = loginService.findUserByUserId((loginUserold instanceof User)?((User)loginUserold).getUserId():1);
		
		String flag = (loginUserold instanceof AdminUser)?"1":"2"; //判断是前台还是后台登陆
		request.setAttribute("flag", flag);
		
		User currentUser = loginService.findUserByUserId(userId);
		
		if (null!=loginName) {
			List childList1 = null;
			List childList2 = null;
			Map<Integer,Integer[]> map = userDAO.findAllUserVOS();
			
			List<Integer> listLeft = getLeftOrRightUser(loginUser.getUserId(),map,childList1,"L");
			List<Integer> listRight = getLeftOrRightUser(loginUser.getUserId(),map,childList2,"R");
			listLeft.addAll(listRight);
			boolean flag1 = false;
			User serchUser = loginService.findUserByLoginName(loginName);
			if (null !=serchUser) {
				for (int i=0;i<listLeft.size();i++) {
					if (serchUser.getUserId().intValue()==listLeft.get(i).intValue()) {
						currentUser = loginService.findUserByLoginName(loginName);
						break;
					}
				}
			}
			
		}
		
		User user = currentUser == null?loginUser:currentUser;
		
		User user1 = user.getChildOne();
		User user2 = user.getChildTwo();
		
		User user11 = user1 == null ?null:user1.getChildOne();
		//User user11L = user11 ==null?null:user11.getChildOne();
		//User user11R = user11 ==null?null:user11.getChildTwo();
		
		User user12 = user1 == null ?null:user1.getChildTwo();
		//User user12L = user12 ==null?null:user12.getChildOne();
		//User user12R = user12 ==null?null:user12.getChildTwo();
		
		User user21 = user2 == null ?null:user2.getChildOne();
		//User user21L = user21 ==null?null:user21.getChildOne();
		//User user21R = user21 ==null?null:user21.getChildTwo();
		
		User user22 = user2 == null ?null:user2.getChildTwo();
		//User user22L = user22 ==null?null:user22.getChildOne();
		//User user22R = user22 ==null?null:user22.getChildTwo();
		
		request.setAttribute("user", user);
		/*List childList1 = null;
		List childList2 = null;
		List<Integer> listLeft = getLeftOrRightUser(user,childList1,"L");
		int leftCount = loginService.findAllUser(listLeft);
		List<Integer> listRight = getLeftOrRightUser(user,childList2,"R");
		int rightCount = loginService.findAllUser(listRight);
		for (int i=0;i<listRight.size();i++) {
			listLeft.add(listRight.get(i));
		}
		int todayCount = userDAO.findAllUserByNowDate(listLeft);
		int totalCount = leftCount+rightCount;
		int oldCount = totalCount-todayCount;
		request.setAttribute("leftCount", leftCount);
		request.setAttribute("rightCount", rightCount);
		request.setAttribute("todayCount", todayCount);
		request.setAttribute("oldCount", oldCount);
		request.setAttribute("totalCount", totalCount);*/
		countDanshu(user,"");
		
		request.setAttribute("user1", user1);
		countDanshu(user1,"1");
		
		
		request.setAttribute("user11", user11);
		countDanshu(user11,"11");
		//request.setAttribute("user11L", user11L);
		//request.setAttribute("user11R", user11R);
		
		request.setAttribute("user12", user12);
		countDanshu(user12,"12");
		//request.setAttribute("user12L", user12L);
		//request.setAttribute("user12R", user12R);
		
		request.setAttribute("user2", user2);
		countDanshu(user2,"2");
		request.setAttribute("user21", user21);
		countDanshu(user21,"21");
		//request.setAttribute("user21L", user21L);
		//request.setAttribute("user21R", user21R);
		
		request.setAttribute("user22", user22);
		countDanshu(user22,"22");
		//request.setAttribute("user22L", user22L);
		//request.setAttribute("user22R", user22R);
		
		return "loadMember";
	}
	
	public void countDanshu(User user,String flag) throws ParseException {
		if (null != user) {
			HttpServletRequest request = ServletActionContext.getRequest();
			List childList1 = null;
			List childList2 = null;
			Map<Integer,Integer[]> map = userDAO.findAllUserVOS();
			List<Integer> listLeft = getLeftOrRightUser(user.getUserId(),map,childList1,"L");
			double leftCount = loginService.findAllUser(listLeft);
			List<Integer> listRight = getLeftOrRightUser(user.getUserId(),map,childList2,"R");
			double rightCount = loginService.findAllUser(listRight);
			for (int i=0;i<listRight.size();i++) {
				listLeft.add(listRight.get(i));
			}
			double todayCount = userDAO.findAllUserByNowDate(listLeft);
			double totalCount = leftCount+rightCount;
			double oldCount = totalCount-todayCount;
			request.setAttribute("leftCount"+flag, leftCount);
			request.setAttribute("rightCount"+flag, rightCount);
			request.setAttribute("todayCount"+flag, todayCount);
			request.setAttribute("oldCount"+flag, oldCount);
			request.setAttribute("totalCount"+flag, totalCount);
			
			
			double leftAlreadNode = fhRecordDAO.findleveData(user.getUserId(), "L");
			double leftCountL = leftCount - leftAlreadNode;
			
			double rightAlreadNode = fhRecordDAO.findleveData(user.getUserId(), "R");
			double rightCountL = rightCount - rightAlreadNode;
			
			request.setAttribute("leftCountL"+flag, leftCountL);
			request.setAttribute("rightCountL"+flag, rightCountL);
			
		}
		
	}
	
	
	public void peiyu(User user,double money,int flag) { //培育奖  intType为给几层 2 3 4 5
		
		User referrer = user.getReferrer();
		if (null!=referrer) {
			int submitMoney = referrer.getSubmitMoney();
			switch (flag) {
				case 1:
					peiyuMoney(referrer,money);
					peiyu(referrer,money,2);break;
				case 2:
					if (submitMoney>=1600) {
						peiyuMoney(referrer,money);
						peiyu(referrer,money,3);
					};break;
				case 3:
					if (submitMoney>=3200) {
						peiyuMoney(referrer,money);
						peiyu(referrer,money,4);
					}else {
						peiyu(referrer,money,4);
					};break;
				case 4:
					if (submitMoney>=6400) {
						peiyuMoney(referrer,money);
						peiyu(referrer,money,5);
					}else {
						peiyu(referrer,money,5);
					};break;
				case 5:
					if (submitMoney==12800) {
						peiyuMoney(referrer,money);
					};break;
			}
		}
	}
	
	public void peiyuMoney(User user,double money) {
		if (null!=user) {
			FhRecord fhRecordr = new FhRecord();
			fhRecordr.setUser(user);
			fhRecordr.setCreateDate(new Date());
			fhRecordr.setFhType("2");
			fhRecordr.setFhmoney(money*0.1);
			loginService.createFhRecord(fhRecordr);
		}
	}
	
	public void hudong(User olduser,User newUser,double money,int flag) { //互动奖金
		User leftchild = newUser.getChildOne();
		User rightchild = newUser.getChildTwo();
		flag++;
		
		double bijiaoMoney = (flag<3)?6400:12800;
		
		if (flag<=3) {
			if (leftchild!=null) {
				double submitMoney = leftchild.getSubmitMoney();
				if (submitMoney>=bijiaoMoney) {
					hudongMoney(leftchild,money,olduser);
				}
				hudong(olduser,leftchild,money,flag);
			}
			if (rightchild!=null) {
				double submitMoney = rightchild.getSubmitMoney();
				if (submitMoney>=bijiaoMoney) {
					hudongMoney(rightchild,money,olduser);
				}
				hudong(olduser,rightchild,money,flag);
			}
		}
	
	}
	public void hudongMoney(User user,double money,User parent) {
		
		FhRecord fhRecordr = new FhRecord();
		fhRecordr.setUser(user);
		fhRecordr.setCreateDate(new Date());
		fhRecordr.setFhType("3");
		fhRecordr.setFhmoney(money*0.05);
		fhRecordr.setMemo("用户"+(parent.getUserId()!=null?parent.getUserId():0)+"碰对给下级 ");
		loginService.createFhRecord(fhRecordr);
	}
	
	public void quanqiuFH() { //TODO 全球分红
		List<User> listAll = loginService.findAllUserByLevel4();
		List<Integer> ids = new ArrayList<Integer>();
		for (int i=0;i<listAll.size();i++) {
			User user = listAll.get(0);
			ids.add(user.getUserId());
		}
		Integer count = loginService.findAllUserByrefferIds(ids);
		//Double oneMoney = listAll!=null?(listAll.size()+count)/:0;
		
	}
	
	public List<Integer> getLeftOrRightUser(User user,List<Integer> list,String flag) {
		if (null==list) {
			list = new ArrayList<Integer>();
		}
		
		User leftUser = user.getChildOne();
		User rightUser = user.getChildTwo();
		if ("L".equals(flag)&&null!=leftUser) {
			list.add(leftUser.getUserId());
			getLeftOrRightUser(leftUser,list,"A");
			
		}
		if ("R".equals(flag)&&null!=rightUser) {
			list.add(rightUser.getUserId());
			getLeftOrRightUser(rightUser,list,"A");
		}
		
		if ("A".equals(flag)) {
			if (null!=leftUser) {
				list.add(leftUser.getUserId());
				getLeftOrRightUser(leftUser,list,"A");
			}
			if (null!=rightUser) {
				list.add(rightUser.getUserId());
				getLeftOrRightUser(rightUser,list,"A");
			}
		}
		
		
		return list;
	}
	
	public List<Integer> getLeftOrRightUser(Integer key, Map<Integer,Integer[]> map,List<Integer> list,String flag) {
		if (null==list) {
			list = new ArrayList<Integer>();
		}
		
		Integer leftUserId = null;
		Integer rightUserId = null;
		
		if (map.containsKey(key)) {
			leftUserId = map.get(key)[0];
			rightUserId = map.get(key)[1];
		}
		
		if ("L".equals(flag)&&null!=leftUserId) {
			list.add(leftUserId);
			getLeftOrRightUser(leftUserId,map,list,"A");
			
		}
		if ("R".equals(flag)&&null!=rightUserId) {
			list.add(rightUserId);
			getLeftOrRightUser(rightUserId,map,list,"A");
		}
		
		if ("A".equals(flag)) {
			if (null!=leftUserId) {
				list.add(leftUserId);
				getLeftOrRightUser(leftUserId,map,list,"A");
			}
			if (null!=rightUserId) {
				list.add(rightUserId);
				getLeftOrRightUser(rightUserId,map,list,"A");
			}
		}
		return list;
	}
	
	public void getAllUserByNowDate() { //当天上的所有节点
		List<User> listAll;
		try {
			listAll = loginService.findAllUserByNowDate();
			for (int i=0;i<listAll.size();i++) {
				User user = listAll.get(i);
				rijiePoint(user,user,1,0);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	public void rijiePoint(User olduser,User newUser,int flag,int intFlag) { //奇数层奖
		intFlag++;
		double money = olduser.getSubmitMoney();
		switch (flag) {
		case 1: User parent = newUser.getParent();
			double money1 = newUser.getSubmitMoney();
			if (null!=parent) {
				if (intFlag<11||(intFlag>=11&&money1>=3200)||(intFlag>=17&&money1>=6400)||(intFlag>=21&&intFlag<=29&&money1>=12800)) {
					pointMoney(olduser,parent,money);
					rijiePoint(olduser,parent,2,intFlag);
				}
			};break;
		case 2:  User parent2 = newUser.getParent();
			if (null!=parent2) {
				rijiePoint(olduser,parent2,1,intFlag);
			};break;
		}
	}
	
	
	public void pointMoney(User oldUser,User user,double money) {
		FhRecord fhRecord = new FhRecord();
		fhRecord.setCreateDate(new Date());
		fhRecord.setFhmoney(money*0.01);
		fhRecord.setFhType("4");
		fhRecord.setMemo("新增会员"+oldUser.getUserId()+"拿到见点奖金");
		fhRecord.setUser(user);
		loginService.createFhRecord(fhRecord);
	}
	
	//测试奖金分配
	public void testjiesuan() {
		loginService.fenhongall();
	}
	
	public static void main(String[] args) {
		/*int a = 20;
		int b = 19;
		if (a>=b) {
			System.out.println(" aaaa ");
		}
		else if (b>a) {
			System.out.println(" bbbb ");
		}

		int leftCount = 17;
		int rightCount = 6;
		Double submitMoney = 3200.00;
		
		// 1:1 1:2 1:3/ 1:1 2:1 3:1
		int intr=(leftCount>=rightCount)?leftCount/rightCount:rightCount/leftCount;
		int inty=(leftCount>=rightCount)?leftCount%rightCount:rightCount%leftCount;
		int money = 0;
		System.out.println(">>>>>>>>> intr "+intr);
		if (submitMoney==1600) { //铜
			switch (intr) { 
				case 1:money=160;break;// 1600*10%
				case 2:money=224;break;// 1600*14%
				case 3:money=256;break;// 1600*16%
				default: money = 256;inty=(leftCount>rightCount)?(leftCount-(rightCount*3)):(rightCount-(leftCount*3));break; //>4 3:1
			}
		}
		if (submitMoney==3200) { //银
			switch (intr) { 
				case 1:money=192;break;// 1600*12%
				case 2:money=256;break;// 1600*16%
				case 3:money=304;break;// 1600*19%
				default: money = 304;inty=(leftCount>rightCount)?(leftCount-(rightCount*3)):(rightCount-(leftCount*3));break; //>4 3:1
			}
		}
		if (submitMoney==6400) { //金
			switch (intr) { 
				case 1:money=224;break;// 1600*14%
				case 2:money=288;break;// 1600*18%
				case 3:money=352;break;// 1600*22%
				default: money = 352;inty=(leftCount>rightCount)?(leftCount-(rightCount*3)):(rightCount-(leftCount*3));break; //>4 3:1
			}
		}
		if (submitMoney==12800) { //砖石
			switch (intr) { 
				case 1:money=270;break;// 1600*17%
				case 2:money=336;break;// 1600*21%
				case 3:money=416;break;// 1600*26%
				default: money = 416;inty=(leftCount>rightCount)?(leftCount-(rightCount*3)):(rightCount-(leftCount*3));break; //>4 3:1
			}
		}
			
		System.out.println("money >>>>> "+money);
		System.out.println("inty >>>>> "+inty);
		*/
		
		List list1 = new ArrayList();
		List list2 = new ArrayList();
		
		list1.add(1);
		list1.add(2);
		
		list2.add(3);
		list2.add(4);
		
		list1.addAll(list2);
		
		for (int i=0;i<list1.size();i++) {
			System.out.println(">>>>>>>>>>>> "+list1.get(i));
		}
	}
			
}
