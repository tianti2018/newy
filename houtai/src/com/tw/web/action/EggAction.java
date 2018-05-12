package com.tw.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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

import com.google.util.ERCodeUtil;
import com.tw.web.dao.EggActivityDAO;
import com.tw.web.dao.EggActivityUserDAO;
import com.tw.web.dao.EggPrizeDAO;
import com.tw.web.dao.EggPrizeUserDAO;
import com.tw.web.dao.FhRecordDAO;
import com.tw.web.dao.UserDAO;
import com.tw.web.dao.UserOrderDAO;
import com.tw.web.hibernate.persistent.EggActivity;
import com.tw.web.hibernate.persistent.EggActivityUser;
import com.tw.web.hibernate.persistent.EggPrize;
import com.tw.web.hibernate.persistent.EggPrizeUser;
import com.tw.web.hibernate.persistent.EggPrizeUserVo;
import com.tw.web.hibernate.persistent.FhRecord;
import com.tw.web.hibernate.persistent.News;
import com.tw.web.hibernate.persistent.User;

@SuppressWarnings("serial")
@ParentPackage("app-default")
@Namespace("")
@Results({ @Result(name = "initPage", value = "/eggs.jsp"),
	    @Result(name="winPrizeList",value="/WEB-INF/jsp/other/winPrizeList.jsp"),
	    @Result(name="updateShiTouNum",value="/WEB-INF/jsp/products/updateShiTouNum.jsp"),
	    @Result(name="indexWinPrizeList",value="/WEB-INF/jsp/other/indexWinPrizeList.jsp"),	
	    @Result(name="qrcodePage",value="/WEB-INF/jsp/other/phoneQrCode.jsp"),
		@Result(name = "joson", value = "/resource/json_struts2.jsp") })
public class EggAction extends ExtJSONActionSuport {

	private EggActivityDAO eggActivityDAO;
	private EggActivityUserDAO eggActivityUserDAO;
	private EggPrizeDAO eggPrizeDAO;
	private EggPrizeUserDAO eggPrizeUserDAO;
	private UserOrderDAO userOrderDAO;
	private UserDAO userDAO;
	private FhRecordDAO fhRecordDAO;
	private Integer activityId;// 活动id
	public EggActivity eggActivity;// 砸蛋活动实体
	public EggPrize eggPrize;// 砸蛋奖品实体
	public User user;
	public List myPrizeList;
	public List<EggPrizeUser> prizeList;
	public String tipMessage ;
	public String status ;
	public String tipName ;
	public String tipLevel ;
	public String message ;
	public String loginName;//用户登录名
	public String prizeName;//奖品名
	public String prizeStatus;//奖品兑奖状态
	public Integer currNum;
	
	public String webCreateQrcode(){
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		HttpServletRequest request = null;
		String returnStr="";
		try {
			out = ServletActionContext.getResponse().getWriter();
			request=ServletActionContext.getRequest();
		} catch (IOException e) {
			e.printStackTrace();
		}
		loginName = request.getParameter("loginName");
		System.out.println("参数是：" + loginName);
		if(loginName!=null&&!"".equals(loginName)){
			 String userUrl="http://218.241.153.74/user/userAction!userRegPage1.action?tjrLoginName="+loginName;
			 String ercodePath=ERCodeUtil.createQRcode(userUrl, null, null, false);
			 returnStr=ercodePath;
		}
		System.out.println("返回结果：" + returnStr);
		out.print(returnStr);
		out.flush();
		out.close();
		return null; 
	}
	public String indexWinPrizeList(){
		prizeList=eggPrizeUserDAO.topNumList(100);
		System.out.println(prizeList.size());
		String tempStr="",tempStr1="";
		for (int i = 0; i < prizeList.size(); i++) {
			tempStr=prizeList.get(i).getUserName();
			if(tempStr.length()>2){
				tempStr1=tempStr.substring(0,2);
				for (int j = 0; j < tempStr.length()-2; j++) {
					tempStr1+="*";
				}
				prizeList.get(i).setUserName(tempStr1);
			}
		}
		return "indexWinPrizeList";
	}
	public String phoneQrcodePage(){
		return "qrcodePage";
	}
	/**
	 * 查询中奖用户列表
	 * @return
	 */
	public String winPrizeList(){
		System.out.println(1);
		try {
			if(loginName!=null&&!"".equals(loginName))
				loginName = java.net.URLDecoder.decode(loginName, "UTF-8");
			if(prizeName!=null&&!"".equals(prizeName))
				prizeName = java.net.URLDecoder.decode(prizeName, "UTF-8");
			if(prizeStatus!=null&&!"".equals(prizeStatus))
				prizeStatus = java.net.URLDecoder.decode(prizeStatus, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Map<String, Integer> compare = new HashMap<String, Integer>();
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		
		
		if (null!=loginName &&! "".equals(loginName)) {
			conditionProperties.put("userName", loginName);
			compare.put("userName", 3);
		}
		if (null!=prizeName &&! "".equals(prizeName)) {
			conditionProperties.put("prizeName", prizeName);
			compare.put("prizeName", 3);
		}
		if (null!=prizeStatus &&! "".equals(prizeStatus)) {
			conditionProperties.put("status", prizeStatus);
			compare.put("status", 3);
		}
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("createTime", false);
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Integer count = eggPrizeUserDAO.cout_size(conditionProperties, compare);
				
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count));
		
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
				
		List<News> litPager = eggPrizeUserDAO.findAllPagerList_new(conditionProperties, compare, sort,this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		request.setAttribute("litPager", litPager);
		
		return "winPrizeList";
	}
	public String initPrizeUserData(){
//		List<EggPrizeUser> prizeList=eggPrizeUserDAO.findAll();
//		
//		for (int i = 0; i < prizeList.size(); i++) {
//			eggPrize=(EggPrize) eggPrizeDAO.findById(prizeList.get(i).getPrizeId());
//			if(eggPrize!=null)
//				prizeList.get(i).setPrizeName(eggPrize.getPrizeName());
//			user=(User) userDAO.findById(prizeList.get(i).getUserId());
//			if(user!=null)
//				prizeList.get(i).setUserName(user.getLoginName());
//			eggPrizeUserDAO.update(prizeList.get(i));
//		}
		List<User> userList=userDAO.findAll();
		String userUrl="";
		String ercodePath="";
		for (int i = 0; i < userList.size(); i++) {
			 userUrl="http://218.241.153.74/user/userAction!userRegPage1.action?tjrLoginName="+userList.get(i).getLoginName();
			 System.out.println(userUrl);
			 ercodePath=ERCodeUtil.createQRcode(userUrl, null, null, false);
			 userList.get(i).setQrCode(ercodePath);
			 userDAO.update(userList.get(i));
		}
		
		return "";
	}
	
	public String showShiTouNum(){
		EggActivityUser eau = (EggActivityUser) eggActivityUserDAO.findById(1);
		if(eau != null){
			ServletActionContext.getRequest().setAttribute("eau", eau);
		}else {
			return "error";
		}
		return "updateShiTouNum";
	}
	
	public String updateShiTouNum(){
		if(currNum != null){
			EggActivityUser eau = (EggActivityUser) eggActivityUserDAO.findById(1);
			if(eau != null){
				eau.setCurrNum(currNum);
				eggActivityUserDAO.update(eau);
				ServletActionContext.getRequest().setAttribute("eau", eau);
			}
		}
		return "updateShiTouNum";
	}
	
	/**
	 * 
	 * @return
	 */
	public String initPage() {
		//初始化用户数据
//		List<UserOrder> userList=userDAO.findAll();
//		for (int i = 0; i < userList.size(); i++) {
//		 //插入一条抽奖记录数据
//			EggActivityUser eggActivityUser=new EggActivityUser();
//			eggActivityUser.setActivityId(8);
//			eggActivityUser.setCurrNum(0);
//			eggActivityUser.setMaxNum(1);
//			eggActivityUser.setUserId(userList.get(i).getUserId());
//			eggActivityUserDAO.saveOrUpdate(eggActivityUser);
//		}
		
		User loginUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		if (activityId != null) {
			eggActivity = eggActivityDAO.findEggActivityById(activityId);
			
			List prizeList=eggPrizeUserDAO.findListByAidAndUid(activityId,loginUser.getUserId());
			myPrizeList=new ArrayList();
			EggPrizeUserVo eggPrizeUserVo;
			EggPrizeUser eggPrizeUser;
			for (int i = 0; i < prizeList.size(); i++) {
				eggPrizeUser=(EggPrizeUser) prizeList.get(i);
				eggPrizeUserVo=new EggPrizeUserVo();
				eggPrizeUserVo.setActivityId(eggPrizeUser.getActivityId());
				eggPrizeUserVo.setCreateTime(eggPrizeUser.getCreateTime());
				eggPrizeUserVo.setUserId(eggPrizeUser.getUserId());
				eggPrizeUserVo.setPrizeName(eggPrizeUser.getPrizeName());
				myPrizeList.add(eggPrizeUserVo);
			}
		}
		return "initPage";
	}

	/**
	 * 中奖积分同步到用户
	 * @return
	 */
	public String ajaxChangeUserScore() {
		List<EggPrizeUser> prizeList=eggPrizeUserDAO.findAll();
		String tempStr="";
		Double tempNum=0.0;
		FhRecord fhRecord;
		
		for (int i = 0; i < prizeList.size(); i++) {
			if(prizeList.get(i).getStatus()==null||"0".equals(prizeList.get(i).getStatus())){
				if(prizeList.get(i).getPrizeName()!=null){
					if(prizeList.get(i).getPrizeName().indexOf("积分")>-1){
						tempNum=Double.parseDouble(prizeList.get(i).getPrizeName().replace("积分", ""));
						//插入一条记录到积分中
						fhRecord=new FhRecord();
						fhRecord.setCreateDate(new Date());
						fhRecord.setFhType("10");
						fhRecord.setFhmoney(tempNum);
						user=new User();
						user.setUserId(prizeList.get(i).getUserId());
						fhRecord.setUserId(prizeList.get(i).getUserId());
						fhRecord.setUser(user);
						fhRecord.setMemo("砸金蛋奖品兑换");
						fhRecordDAO.create(fhRecord);
						
						//更新奖品已兑奖状态
						prizeList.get(i).setStatus("1");
						eggPrizeUserDAO.update(prizeList.get(i));
					}
				}
			}
		}
		tipMessage="同步完成！";
		this.setJsonString("{success:true,tipMessage:'" + tipMessage
				+ "',status:'" + status + "',tipName:'" + tipName + "',tipLevel:'"
				+ tipLevel + "',message:'" + message + "'}");
		return "joson";
	}
	/**
	 * ajax 砸蛋
	 * 
	 * @return
	 */
	public String ajaxHitEgg() {
		User loginUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		if (activityId != null) {
			eggActivity = eggActivityDAO.findEggActivityById(activityId);
			if(eggActivity!=null){
			//管理员才能砸蛋
//				if(!loginUser.getUserId().equals(1)){
//					status="0";
//					message="success";
//					tipMessage="目前此活动不对会员开放，只有管理员才可以参与！";
//					this.setJsonString("{success:true,tipMessage:'" + tipMessage
//							+ "',status:'" + status + "',tipName:'" + tipName + "',tipLevel:'"
//							+ tipLevel + "',message:'" + message + "'}");
//					return "joson";
//				}
				
				Date newDate=new Date();
				if(eggActivity.getBeginTime().after(newDate)||eggActivity.getEndTime().before(newDate)){
					status="0";
					message="success";
					tipMessage="目前没有砸蛋活动，请耐心等待。";
					this.setJsonString("{success:true,tipMessage:'" + tipMessage
							+ "',status:'" + status + "',tipName:'" + tipName + "',tipLevel:'"
							+ tipLevel + "',message:'" + message + "'}");
					return "joson";
				}
			}
			
		
		
			// 查询是否还有抽奖次数
			EggActivityUser eggActivityUser = eggActivityUserDAO.findNewData(
					activityId, loginUser.getUserId());
			if (eggActivityUser != null) {
				if (eggActivityUser.getMaxNum() > eggActivityUser.getCurrNum()) {
					// 按中奖率高低查询此活动奖品信息
					List<EggPrize> prizeList = eggPrizeDAO.findHavLists(activityId);
					if (prizeList != null && prizeList.size() > 0) {
						// 获得用户随机数
						double tempRnum = new Random().nextInt(10000) + 1;
						double tempRate = 0;
						// 减去一次抽奖次数
						eggActivityUser.setCurrNum(eggActivityUser.getCurrNum() + 1);
						eggActivityUserDAO.update(eggActivityUser);
						tipMessage = "剩余抽奖次数"
								+ (eggActivityUser.getMaxNum() - eggActivityUser.getCurrNum())
								+ "次，已抽了" + eggActivityUser.getCurrNum() + "次";
						// 判断用户是否中奖
						EggPrizeUser eggPrizeUser;
						FhRecord fhRecord;
						Double tempNum=0.0;
						
						for (int i = 0; i < prizeList.size(); i++) {
							tempRate = (prizeList.get(i).getWinRate() * 100);
							System.out.println(tempRate + "-" + tempRnum);
							if (tempRate >= tempRnum) {
								if(prizeList.get(i).getNums()>0){
									status = "1";
									tipName = prizeList.get(i).getPrizeName();
									tipLevel = prizeList.get(i).getPrizeLevel();
									message = "success";
									eggPrizeUser = new EggPrizeUser();
									if(prizeList.get(i).getPrizeName().indexOf("积分")>-1){
										tempNum=Double.parseDouble(prizeList.get(i).getPrizeName().replace("积分", ""));
										//插入一条记录到积分中
										fhRecord=new FhRecord();
										fhRecord.setCreateDate(new Date());
										fhRecord.setFhType("10");
										fhRecord.setFhmoney(tempNum);
										user=new User();
										user.setUserId(loginUser.getUserId());
										fhRecord.setUserId(loginUser.getUserId());
										fhRecord.setUser(user);
										fhRecord.setMemo("砸金蛋奖品兑换");
										fhRecordDAO.create(fhRecord);
										//已兑奖
										eggPrizeUser.setStatus("1");
									}else{
										eggPrizeUser.setStatus("0");
									}
									// 插入中奖纪录
									
									eggPrizeUser.setActivityId(activityId);
									eggPrizeUser.setCreateTime(new Date());
									eggPrizeUser.setPrizeId(prizeList.get(i).getId());
									eggPrizeUser.setUserId(loginUser.getUserId());
									eggPrizeUser.setPrizeName(prizeList.get(i).getPrizeName());
									eggPrizeUser.setUserName(loginUser.getLoginName());
									eggPrizeUserDAO.create(eggPrizeUser);
									//减少奖品数量
									prizeList.get(i).setNums(prizeList.get(i).getNums()-1);
									eggPrizeDAO.update(prizeList.get(i));
									break;
								}else
									continue;
							}
						}
					}
				} else {
					// 次数用光
					status = "2";
					tipMessage = "您的抽奖次数已用完！";
					message = "success";
				}
			} else {
				// 无权参与
				status = "3";
				tipMessage = "您无权参与抽奖！";
				message = "success";
			}
			this.setJsonString("{success:true,tipMessage:'" + tipMessage
					+ "',status:'" + status + "',tipName:'" + tipName + "',tipLevel:'"
					+ tipLevel + "',message:'" + message + "'}");
			return "joson";
		}
		return null;

	}

	/**
	 * ajax检查是否可以继续砸蛋
	 * 
	 * @return
	 */
	public String ajaxCheckEgg() {
		User loginUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		if (activityId != null) {
			eggActivity = eggActivityDAO.findEggActivityById(activityId);
			if(eggActivity!=null){
				//管理员才能砸蛋
//				if(!loginUser.getUserId().equals(1)){
//					status="0";
//					message="success";
//					tipMessage="目前此活动不对会员开放，只有管理员才可以参与！";
//					this.setJsonString("{success:true,tipMessage:'" + tipMessage
//							+ "',status:'" + status + "',tipName:'" + tipName + "',tipLevel:'"
//							+ tipLevel + "',message:'" + message + "'}");
//					return "joson";
//				}
				
				Date newDate=new Date();
				if(eggActivity.getBeginTime().after(newDate)||eggActivity.getEndTime().before(newDate)){
					status="0";
					message="success";
					tipMessage="目前没有砸蛋活动，请耐心等待。";
					this.setJsonString("{success:true,tipMessage:'" + tipMessage
							+ "',status:'" + status + "',tipName:'" + tipName + "',tipLevel:'"
							+ tipLevel + "',message:'" + message + "'}");
					return "joson";
				}
			}
			
			// 查询是否还有抽奖次数
			EggActivityUser eggActivityUser = eggActivityUserDAO.findNewData(
					activityId, loginUser.getUserId());
			if (eggActivityUser != null) {
				if (eggActivityUser.getMaxNum() > eggActivityUser.getCurrNum()) {
					status = "1";
					tipMessage = "剩余抽奖次数"
							+ (eggActivityUser.getMaxNum() - eggActivityUser.getCurrNum())
							+ "次，已抽了" + eggActivityUser.getCurrNum() + "次";
					message = "success";
				} else {
					// 次数用光
					status = "2";
					message = "success";
				}

			} else {
				// 无权参与
				status = "3";
				message = "success";
			}
		}
		this.setJsonString("{success:true,tipMessage:'" + tipMessage
				+ "',status:'" + status + "',tipName:'" + tipName + "',tipLevel:'"
				+ tipLevel + "',message:'" + message + "'}");
		return "joson";
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public EggActivity getEggActivity() {
		return eggActivity;
	}

	public void setEggActivity(EggActivity eggActivity) {
		this.eggActivity = eggActivity;
	}

	public EggPrize getEggPrize() {
		return eggPrize;
	}

	public void setEggPrize(EggPrize eggPrize) {
		this.eggPrize = eggPrize;
	}

	public EggActivityDAO getEggActivityDAO() {
		return eggActivityDAO;
	}

	@Autowired
	public void setEggActivityDAO(EggActivityDAO eggActivityDAO) {
		this.eggActivityDAO = eggActivityDAO;
	}

	public EggActivityUserDAO getEggActivityUserDAO() {
		return eggActivityUserDAO;
	}

	@Autowired
	public void setEggActivityUserDAO(EggActivityUserDAO eggActivityUserDAO) {
		this.eggActivityUserDAO = eggActivityUserDAO;
	}

	public EggPrizeDAO getEggPrizeDAO() {
		return eggPrizeDAO;
	}

	@Autowired
	public void setEggPrizeDAO(EggPrizeDAO eggPrizeDAO) {
		this.eggPrizeDAO = eggPrizeDAO;
	}

	public EggPrizeUserDAO getEggPrizeUserDAO() {
		return eggPrizeUserDAO;
	}

	@Autowired
	public void setEggPrizeUserDAO(EggPrizeUserDAO eggPrizeUserDAO) {
		this.eggPrizeUserDAO = eggPrizeUserDAO;
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
	public String getTipMessage() {
		return tipMessage;
	}

	public void setTipMessage(String tipMessage) {
		this.tipMessage = tipMessage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTipName() {
		return tipName;
	}

	public void setTipName(String tipName) {
		this.tipName = tipName;
	}

	public String getTipLevel() {
		return tipLevel;
	}

	public void setTipLevel(String tipLevel) {
		this.tipLevel = tipLevel;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List getMyPrizeList() {
		return myPrizeList;
	}

	public void setMyPrizeList(List myPrizeList) {
		this.myPrizeList = myPrizeList;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPrizeName() {
		return prizeName;
	}
	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}
	public FhRecordDAO getFhRecordDAO() {
		return fhRecordDAO;
	}
	@Autowired
	public void setFhRecordDAO(FhRecordDAO fhRecordDAO) {
		this.fhRecordDAO = fhRecordDAO;
	}

	public List<EggPrizeUser> getPrizeList() {
		return prizeList;
	}

	public void setPrizeList(List<EggPrizeUser> prizeList) {
		this.prizeList = prizeList;
	}

	public String getPrizeStatus() {
		return prizeStatus;
	}

	public void setPrizeStatus(String prizeStatus) {
		this.prizeStatus = prizeStatus;
	}
	public Integer getCurrNum() {
		return currNum;
	}
	public void setCurrNum(Integer currNum) {
		this.currNum = currNum;
	}

}
