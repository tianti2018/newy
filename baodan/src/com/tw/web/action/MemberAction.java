package com.tw.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.Namespace;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.alipay.util.httpClient.HttpResponse;
import com.opensymphony.xwork2.ActionChainResult;
import com.tw.web.dao.DianpuForUserDao;
import com.tw.web.dao.OrdersDAO;
import com.tw.web.dao.ProductsForDianpuDao;
import com.tw.web.dao.TAreaDao;
import com.tw.web.dao.UserDAO;
import com.tw.web.dao.UseryDao;
import com.tw.web.hibernate.UserVO;
import com.tw.web.hibernate.UseryVo;
import com.tw.web.hibernate.persistent.AdminUser;
import com.tw.web.hibernate.persistent.FhRecord;
import com.tw.web.hibernate.persistent.TArea;
import com.tw.web.hibernate.persistent.User;
import com.tw.web.hibernate.persistent.Usery;
import com.tw.web.service.LoginService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings("serial")
@ParentPackage("app-default")
@Namespace("")
@Results(
		{
			
			@Result(name="listAllMembers", 		value="/WEB-INF/jsp/member/listAllMembers.jsp"),
			@Result(name="quBangDing", 		value="/WEB-INF/jsp/member/quBangDing.jsp"),
			
			
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
	private UserDAO userDAO;
	private UseryDao useryDao;
	private TAreaDao tAreaDao;
	private OrdersDAO ordersDAO;
	private DianpuForUserDao dianpuForUserDao;
	private ProductsForDianpuDao productsForDianpuDao;
	
	private String loginName;
	private String password;
	private String random;
	private String userName;
	
	private Integer uoId;
	
	private Integer userId;
	private Integer useryId;
	private Integer parentId;
	private String areaType;
	private Integer areaId;
	private String cardNo;
	private String cardPassword;
	
	private Integer areaId1;
	private Integer areaId2;
	private Integer areaId3;
	
	public void bangding(){
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", false);
		Usery usery = (Usery) useryDao.findById(useryId);
		if(usery!=null&&usery.getParentId()==null){
			usery.setParentId(parentId);
			useryDao.saveOrUpdate(usery);
			jsonObject.put("success", true);
		}
		
		try {
			response.getWriter().println(jsonObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void searchMember(){
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		Map<String, Integer> compare = new HashMap<String, Integer>();
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("appDate", false);
		if (null!=useryId &&! "".equals(useryId)) {
			conditionProperties.put("id", useryId);
			compare.put("id", 0);
		}
		if (StringUtils.isNotBlank(userName)) {
			conditionProperties.put("userName", userName.trim());
			compare.put("userName", 2);
		}
		List<Usery> useries = useryDao.findAllPagerList(conditionProperties, compare, sort, 0, 0, "all");
		List<UseryVo> useryVos = new ArrayList<UseryVo>();
		if(useries!=null&&useries.size()>0){
			for(Usery usery:useries){
				UseryVo useryVo = new UseryVo();
				useryVo.setDianPuId(usery.getDianPuId());
				useryVo.setHeadUrl(usery.getHeadUrl());
				useryVo.setId(usery.getId());
				useryVo.setLevel(usery.getLevel());
				useryVo.setUseryname(usery.getUserName());
				useryVo.setParentId(usery.getParentId());
				useryVo.setSubscribe(usery.getSubscribe());
				if(usery.getUserId()!=null){
					User user = (User) userDAO.findById(usery.getUserId());
					useryVo.setLoginName(user.getLoginName());
					useryVo.setPhone(user.getPhone());
					useryVo.setUserName(user.getUserName());
				}
				useryVos.add(useryVo);
			}
		}
		JSONArray jsonObject = JSONArray.fromObject(useryVos);
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().println(jsonObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String quBangDing(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("useryId", useryId);
		return "quBangDing";
	}
	
	public void kaitongDianpu(){
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", dianpuForUserDao.kaitongDianpu(useryId));
		
		try {
			response.getWriter().println(jsonObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String listAllMembers(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Object loginUser = request.getSession().getAttribute("user");
		if(loginUser==null){
			return "timeOut";
		}
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		Map<String, Integer> compare = new HashMap<String, Integer>();
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("appDate", false);
		if (null!=userId &&! "".equals(userId)) {
			conditionProperties.put("id", userId);
			compare.put("id", 0);
		}
		if (StringUtils.isNotBlank(userName)) {
			conditionProperties.put("userName", userName.trim());
			compare.put("userName", 2);
		}
		int count_size =useryDao.cout_size(conditionProperties, compare);
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count_size));
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
		
		List<Usery> useries = useryDao.findAllPagerList(conditionProperties, compare, sort, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		List<UserVO> list = new ArrayList<UserVO>();
		if(useries!=null&&useries.size()>0){
			for(Usery usery:useries){
				UserVO userVO = new UserVO();
				userVO.setUsery(usery);
				if(usery.getUserId()!=null){
					User user = (User) userDAO.findById(usery.getUserId());
					userVO.setUser(user);
					
				}
				list.add(userVO);
			}
		}
		request.setAttribute("litPager", list);
		return "listAllMembers";
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
	
	//根据用户ID罗列出这个会员参与的序号
	
	//根据会员参与的序号罗列出所有得到的积分
	
	
	
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
		
		request.setAttribute("user", user);
		
		return "loadMember";
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
	
	
	public UseryDao getUseryDao() {
		return useryDao;
	}
	@Autowired
	public void setUseryDao(UseryDao useryDao) {
		this.useryDao = useryDao;
	}
	
	public TAreaDao gettAreaDao() {
		return tAreaDao;
	}
	@Autowired
	public void settAreaDao(TAreaDao tAreaDao) {
		this.tAreaDao = tAreaDao;
	}

	public Integer getUseryId() {
		return useryId;
	}

	public void setUseryId(Integer useryId) {
		this.useryId = useryId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public DianpuForUserDao getDianpuForUserDao() {
		return dianpuForUserDao;
	}
	@Autowired
	public void setDianpuForUserDao(DianpuForUserDao dianpuForUserDao) {
		this.dianpuForUserDao = dianpuForUserDao;
	}

	public ProductsForDianpuDao getProductsForDianpuDao() {
		return productsForDianpuDao;
	}
	@Autowired
	public void setProductsForDianpuDao(ProductsForDianpuDao productsForDianpuDao) {
		this.productsForDianpuDao = productsForDianpuDao;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
