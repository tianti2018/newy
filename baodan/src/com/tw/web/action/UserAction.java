package com.tw.web.action;

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

import com.opensymphony.xwork2.ActionChainResult;
import com.tw.web.dao.AdminUserDAO;
import com.tw.web.dao.OrdersDAO;
import com.tw.web.dao.TAreaDao;
import com.tw.web.dao.UserDAO;
import com.tw.web.hibernate.UserVO;
import com.tw.web.hibernate.persistent.AdminUser;
import com.tw.web.hibernate.persistent.Orders;
import com.tw.web.hibernate.persistent.TArea;
import com.tw.web.hibernate.persistent.User;
import com.tw.web.service.LoginService;


@SuppressWarnings("serial")
@ParentPackage("app-default")
@Namespace("")
@Results(
		{
			@Result(name="listAllUser_new", 		value="/WEB-INF/jsp/sys/listAllUser_new.jsp"),
			@Result(name="error", 		value="/login2.jsp"),
			@Result(name="listAllOrders_new", 		value="/WEB-INF/jsp/sys/listAllOrders_new.jsp"),
			@Result(name="listAllTixian", 			value="/WEB-INF/jsp/member/listAllTixian.jsp"),
			@Result(name="listAllUser", 			value="/WEB-INF/jsp/sys/userList.jsp"),	
			@Result(name="listAllAdminUser", 			value="/WEB-INF/jsp/sys/userList1.jsp"),
			@Result(name="initAddUser", 			value="/WEB-INF/jsp/member/addmember.jsp"),
			@Result(name="initModifyUser", 			value="/WEB-INF/jsp/member/addmember.jsp"),
			@Result(name="shouzhi_tongji", 			value="/WEB-INF/jsp/search/search_shouzhi_tongji.jsp"),	
			@Result(name="jiesuan", type=ActionChainResult.class, 	value="user", params = {"method", "listAllUser_new"}),
			@Result(name="goBackList", type=ActionChainResult.class, 	value="user", params = {"method", "listAllUser"}),
			@Result(name="gobackList4", type=ActionChainResult.class, 	value="user", params = {"method", "listAllTixian"})
		}
)
public class UserAction extends ExtJSONActionSuport {
	
	private LoginService loginService;
	private UserDAO userDAO;
	private OrdersDAO ordersDAO;
	private AdminUserDAO adminUserDAO;
	private TAreaDao tAreaDao;
	private Integer userId;
	private Integer flag;
	private String loginName;
	private String password;
	private String userName;
	private String address;
	private String phone;
	private String type;
	private String quCode;
	private String j_fromDate;
	private String j_endDate;
	private String block;
	private Integer parentId;
	
	//奖金拨比
	
	public String initAddUser () throws Exception {
		Object user = ServletActionContext.getRequest().getSession().getAttribute("user");
		if (user instanceof User) {
			user = (User)user;
			ServletActionContext.getRequest().setAttribute("flag", "1");
		}
		else if (user instanceof AdminUser) {
			user = (AdminUser)user;
			ServletActionContext.getRequest().setAttribute("flag", "2");
		}
		// 找到所有的地级市
		List<TArea> litTArea = tAreaDao.findEntityByPropertiName("parentCode", "0");
		ServletActionContext.getRequest().setAttribute("litTArea_sheng", litTArea);
		ServletActionContext.getRequest().setAttribute("user", user);
		
		return "initAddUser";
	}
	
	public String activityUser () throws Exception {
		User user = loginService.findUserByUserId(userId);
		user.setBlock("1");
		
		loginService.updateUser(user);
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
		Object obj = request.getSession().getAttribute("user");
		if(obj == null){
			return "error";
		}
		User user = null;
		if (obj instanceof User) {
			user = (User) obj;
			if(user !=null){
				parentId = user.getUserId();
			}
		}
		
		if (null!=userId &&! "".equals(userId)) {
			conditionProperties.put("userId", userId);
			compare.put("userId", 0);
		}
		if (null!=parentId &&! "".equals(parentId)) {
			conditionProperties.put("parentId", parentId);
			compare.put("parentId", 0);
		}
		
		if(null != userName && !userName.isEmpty()){
			conditionProperties.put("userName", userName);
			compare.put("userName", 2);
		}
		
		if(null != phone && !phone.isEmpty()){
			conditionProperties.put("phone", phone);
			compare.put("phone", 2);
		}
		
		if(null != block&&!"".equals(block)){
			conditionProperties.put("block", block);
			compare.put("block", 0);
		}
			
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
		List<User> litPager = null;
		litPager = userDAO.findAllPagerList_new(conditionProperties, compare, sort,this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		
		request.setAttribute("litPager", litPager);
		
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
				DecimalFormat   fnum   =   new   DecimalFormat("##0.00"); //四舍五入，保留两位小数 
				List ordersList = userDAO.findBySql(sql);
				if(ordersList.size()>0){
					for(int i=0;i<ordersList.size();i++){
						Orders o = (Orders) ordersDAO.findById(Integer.parseInt(ordersList.get(i)==null?"0":ordersList.get(i).toString()));
							zongxiao+=o.getMoney();
					}
				}
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
		if(loginName!=null){
			List<User> users = userDAO.findEntityByPropertiName("loginName", loginName);
			if(users!=null&&users.size()>0){
				ServletActionContext.getRequest().setAttribute("message", "这个登录账户已经存在,请重新填写!");
				return "initAddUser";
			}
		}
		User user = new User();
		if(loginName==null){
			return "initAddUser";
		}
		user.setLoginName(loginName);
		user.setCreateDate(new Date());
		user.setPassWord(password);
		user.setBlock("1");
//		user.setPassWord2(password2);
		user.setUserName(userName);
		user.setPhone(phone);
		List<TArea> areas = tAreaDao.findEntityByPropertiName("areaCode", quCode);
		if(areas!=null&&areas.size()>0){
			user.settArea(areas.get(0));
		}
		user.setAddress(address);
		//创建用户二维码信息
		loginService.createUser(user);
		
		ServletActionContext.getRequest().setAttribute("message", "新增成功");
		return "error";
	}
	
	
	
	public String initModifyUser() {
		List<TArea> litTArea = tAreaDao.findEntityByPropertiName("parentCode", "0");
		ServletActionContext.getRequest().setAttribute("litTArea_sheng", litTArea);
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
		String password1 = user.getPassWord();
		
		if (!password1.equals(password)) {
			ServletActionContext.getRequest().setAttribute("message", "原密码不正确");
			return "initModifyPassword";
		}
		
		
		loginService.updateUser(user);
		ServletActionContext.getRequest().setAttribute("message", "密码修改成功");
		return "initModifyPassword";
	}
	
	
	
	
	
	public String deleteUser() throws Exception {
		User user = loginService.findUserByUserId(userId);
		
		if (user != null) {
			
			loginService.deleteUser(user);
			ServletActionContext.getRequest().setAttribute("message", "删除成功");
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
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getQuCode() {
		return quCode;
	}
	public void setQuCode(String quCode) {
		this.quCode = quCode;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
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

	public LoginService getLoginService() {
		return loginService;
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

	public UserDAO getUserDAO() {
		return userDAO;
	}
	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
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



	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
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

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
