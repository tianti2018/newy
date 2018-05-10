package com.tw.web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.SimpleFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tw.web.dao.CardDAO;
import com.tw.web.dao.FhRecordDAO;
import com.tw.web.dao.FunctionDAO;
import com.tw.web.dao.RecordDAO;
import com.tw.web.dao.RelationDAO;
import com.tw.web.dao.RepeatDAO;
import com.tw.web.dao.RoleDAO;
import com.tw.web.dao.RoleFunctionDAO;
import com.tw.web.dao.UserDAO;

import com.tw.web.hibernate.persistent.Card;
import com.tw.web.hibernate.persistent.FhRecord;
import com.tw.web.hibernate.persistent.Function;
import com.tw.web.hibernate.persistent.Record;
import com.tw.web.hibernate.persistent.Relation;
import com.tw.web.hibernate.persistent.Repeat;
import com.tw.web.hibernate.persistent.Role;
import com.tw.web.hibernate.persistent.RoleFunction;
import com.tw.web.hibernate.persistent.User;

import com.tw.web.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	private UserDAO userDAO;
	private RelationDAO relationDAO;
	private RecordDAO recordDAO;
	private RepeatDAO repeatDAO;
	private FhRecordDAO fhRecordDAO;
	private RoleFunctionDAO roleFunctionDAO;
	private RoleDAO roleDAO;
	private FunctionDAO functionDAO;
	private CardDAO cardDAO;
	
	
	public CardDAO getCardDAO() {
		return cardDAO;
	}
	@Autowired
	public void setCardDAO(CardDAO cardDAO) {
		this.cardDAO = cardDAO;
	}
	public RoleDAO getRoleDAO() {
		return roleDAO;
	}
	@Autowired
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
	public FunctionDAO getFunctionDAO() {
		return functionDAO;
	}
	@Autowired
	public void setFunctionDAO(FunctionDAO functionDAO) {
		this.functionDAO = functionDAO;
	}
	public RoleFunctionDAO getRoleFunctionDAO() {
		return roleFunctionDAO;
	}
	@Autowired
	public void setRoleFunctionDAO(RoleFunctionDAO roleFunctionDAO) {
		this.roleFunctionDAO = roleFunctionDAO;
	}

	public RelationDAO getRelationDAO() {
		return relationDAO;
	}		
	
	public RecordDAO getRecordDAO() {
		return recordDAO;
	}
	
	@Autowired
	public void setRecordDAO(RecordDAO recordDAO) {
		this.recordDAO = recordDAO;
	}

	@Autowired
	public void setRelationDAO(RelationDAO relationDAO) {
		this.relationDAO = relationDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}		
	
	public RepeatDAO getRepeatDAO() {
		return repeatDAO;
	}
	
	@Autowired
	public void setRepeatDAO(RepeatDAO repeatDAO) {
		this.repeatDAO = repeatDAO;
	}
	
	
	public FhRecordDAO getFhRecordDAO() {
		return fhRecordDAO;
	}
	
	@Autowired
	public void setFhRecordDAO(FhRecordDAO fhRecordDAO) {
		this.fhRecordDAO = fhRecordDAO;
	}

	@Override
	public int checkUserExistOrNo(String loginName, String password) {
		try {
			return userDAO.checkUserExistOrNo(loginName, password);
		} 
		catch (Exception e) {
			System.err.println(" checkUserExistOrNo is Exception "+e.getMessage());
		}
		
		return 0;		
	}

	@Override
	public User findUserByLoginName(String loginName) {
		try {
			return userDAO.findUserByLoginName(loginName);
		} 
		catch (Exception e) {
			System.err.println(" findUserByLoginName is Exception "+e.getMessage());
		}
		return null;
	}

	@Override
	public void createUser(User user) {
		try {
			userDAO.create(user);
		} 
		catch (Exception e) {
			System.err.println(" createUser is Exception "+e.getMessage());
		}
		
	}

	@Override
	public void deleteUser(User user) {
		try {
			userDAO.delete(user);
		}
		catch (Exception e) {
			System.err.println(" deleteUser is Exception "+e.getMessage());
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUser(String propertyName, boolean isAsc, int firstResult, int maxResults, String flag) {
		try {
			return userDAO.findAllPagerList(propertyName, isAsc, firstResult, maxResults, flag);
		}
		catch (Exception e) {
			System.err.println(" findAllUser is Exception "+e.getMessage());
		}
		
		return null;
	}

	@Override
	public void updateUser(User user) {
		try {
			userDAO.update(user);
		} 
		catch (Exception e) {
			System.err.println(" updateUser is Exception "+e.getMessage());
		}
		
	}

	@Override
	public User findUserByUserId(Integer userId) {
		try {
			if (userId ==null) {
				return null;
			}
			return (User)userDAO.findById(userId);
		}
		catch (Exception e) {
			System.err.println(" findUserByUserId is Exception "+e.getMessage());
		}
		return null;
	}

	@Override
	public Relation findRelationByUserId(Integer userId) throws Exception {
		
		try {
			return relationDAO.findRelationByUserId(userId);
		}
		catch (Exception e) {
			System.err.println(" findRelationByUserId is Exception "+e.getMessage());
		}
		return null;
	}

	@Override
	public void createRelation(Relation relation) throws Exception {
		try {
			relationDAO.create(relation);
		} 
		catch (Exception e) {
			System.err.println(" createRelation is Exception "+e.getMessage());
		}
	}

	@Override
	public Relation findRelationById(Integer relationId) throws Exception {
		
		try {
			return (Relation)relationDAO.findById(relationId);
		} 
		catch (Exception e) {
			System.err.println(" findRelationById is Exception "+e.getMessage());
		}
		return null;
	}

	@Override
	public void modifyRelation(Relation relation) throws Exception {
		try {
			relationDAO.update(relation);
		} 
		catch (Exception e) {
			System.err.println(" modifyRelation is Exception "+e.getMessage());
		}
		
	}

	@Override
	public void createRecord(Record record) throws Exception {
		try {
			recordDAO.create(record);
		} 
		catch (Exception e) {
			System.err.println(" createRecord is Exception "+e.getMessage());
		}
		
	}

	@Override
	public void deleteRecord(Integer recordId) throws Exception {
		try {
			recordDAO.delete(recordDAO.findById(recordId));
		} 
		catch (Exception e) {
			System.err.println(" deleteRecord is Exception "+e.getMessage());
		}
		
	}

	@Override
	public void deleteRelation(Integer relationId) throws Exception {
		try {
			relationDAO.delete(relationDAO.findById(relationId));
		} 
		catch (Exception e) {
			System.err.println(" deleteRelation is Exception "+e.getMessage());
		}
		
	}

	@Override
	public Record findRecordById(Integer recordId) throws Exception {
		try {
			return (Record)recordDAO.findById(recordId);
		} 
		catch (Exception e) {
			System.err.println(" findRecordById is Exception "+e.getMessage());
		}
		return null;
	}

	@Override
	public void modifyRecord(Record record) throws Exception {
		try {
			recordDAO.update(record);
		} 
		catch (Exception e) {
			System.err.println(" modifyRecord is Exception "+e.getMessage());
		}
		
	}

	@Override
	public void createRepeat(Repeat repeat) throws Exception {
		try {
			repeatDAO.create(repeat);
		} 
		catch (Exception e) {
			System.err.println(" createRepeat is Exception "+e.getMessage());
		}
		
	}

	@Override
	public void deleteRepeat(Integer repeatId) throws Exception {
		try {
			repeatDAO.delete(repeatDAO.findById(repeatId));
		} 
		catch (Exception e) {
			System.err.println(" deleteRepeat is Exception "+e.getMessage());
		}
		
	}

	@Override
	public boolean findRepeatByCrrentYM(Integer userId, String yyyyMM) throws Exception {
		
		try {
			return repeatDAO.findRepeatByCrrentYM(userId,yyyyMM);
		} 
		catch (Exception e) {
			System.err.println(" findRepeatByCrrentYM is Exception "+e.getMessage());
		}
		
		return false;
	}

	@Override
	public Repeat findRepeatById(Integer repeatId) throws Exception {
		try {
			return (Repeat)repeatDAO.findById(repeatId);
		}
		catch (Exception e) {
			System.err.println(" findRepeatById is Exception "+e.getMessage());
		}
		return null;
	}

	@Override
	public void modifyRepeat(Repeat repeat) throws Exception {
		try {
			repeatDAO.update(repeat);
		} 
		catch (Exception e) {
			System.err.println(" modifyRepeat is Exception "+e.getMessage());
		}		
	}

	@Override
	public List<User> findAllUser() {
		return userDAO.findAll();
	}

	@Override
	public double findAllUser(List<Integer> ids) {
		
		return userDAO.findAllUserByIds(ids);
	}

	@Override
	public void createFhRecord(FhRecord fhRecord) {
		try {
			fhRecordDAO.create(fhRecord);
		} 
		catch (Exception e) {
			System.err.println(" createFhRecord is Exception "+e.getMessage());
		}
	}

	@Override
	public void updateFhRecord(FhRecord fhRecord) {
		try {
			fhRecordDAO.update(fhRecord);
		} 
		catch (Exception e) {
			System.err.println(" updateFhRecord is Exception "+e.getMessage());
		}
	}

	@Override
	public void deleteFhRecord(Integer fhId) {
		try {
			fhRecordDAO.delete(fhRecordDAO.findById(fhId));
		} 
		catch (Exception e) {
			System.err.println(" deleteFhRecord is Exception "+e.getMessage());
		}
	}

	@Override
	public double findleveData(Integer userId, String flag) {
		return fhRecordDAO.findleveData(userId, flag);
	}

	@Override
	public List<User> findAllUserByNowDate() throws ParseException {
		return userDAO.findAllUserByNowDate();
	}

	@Override
	public List<User> findAllUserByLevel4() {
		return userDAO.findAllUserByLevel4();
	}

	@Override
	public Integer findAllUserByrefferIds(List<Integer> ids) {
		return userDAO.findAllUserByrefferIds(ids);
	}

	@Override
	public List<FhRecord> findAllFhRecordByUserId(Integer userId) {
		return fhRecordDAO.findAllFhRecordByUserId(userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FhRecord> findAllFhRecord(String propertyName, boolean isAsc,
			int firstResult, int maxResults, String flag) {
		return fhRecordDAO.findAllPagerList(propertyName, isAsc, firstResult, maxResults, flag);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FhRecord> findAllFhRecordByUserId(
			Map<String, Object> conditionProperties,
			Map<String, Integer> compare, Map<String, Boolean> sort,
			int firstResult, int maxResults, String flag) {
		return fhRecordDAO.findAllPagerList_new(conditionProperties, compare, sort, firstResult, maxResults, flag);
	}
	
	
	
	/***************************************************************************************************************************/
	public void updateMemberbonus() {
		List<User> listAll = this.findAllUser();
		Map<Integer,Integer[]> map = userDAO.findAllUserVOS();
			for (int i=0;i<listAll.size();i++) {
				User user =  listAll.get(i);
				List childList1 = null;
				List childList2 = null;
				//List<Integer> listLeft = getLeftOrRightUser(user,childList1,"L");
				//List<Integer> listRight = getLeftOrRightUser(user,childList2,"R");
				
				List<Integer> listLeft = getLeftOrRightUser(user.getUserId(),map,childList1,"L");
				List<Integer> listRight = getLeftOrRightUser(user.getUserId(),map,childList2,"R");
				
				if (listLeft.size()!=0&&listRight.size()!=0) {
					double leftCount = this.findAllUser(listLeft);
					double leftAlreadNode = this.findleveData(user.getUserId(), "L");
					leftCount-=leftAlreadNode;
					
					double rightCount = this.findAllUser(listRight);
					double rightAlreadNode = this.findleveData(user.getUserId(), "R");
					rightCount-=rightAlreadNode;
					
				}
		}
		
	}
	
	
	public void fenhongall() {
		Map<Integer,Integer[]> map = userDAO.findAllUserVOS();
		for(Map.Entry<Integer, Integer[]> entry: map.entrySet()) { 
			 Integer userId = entry.getKey();
			 User user = (User)userDAO.findById(userId);
			 Integer submitMoneyOld = entry.getValue()[2];
			 Integer realSubmitMoneyOld = entry.getValue()[3];
				
				List childList1 = null;
				List childList2 = null;
				List<Integer> listLeft = getLeftOrRightUser(userId,map,childList1,"L");
				List<Integer> listRight = getLeftOrRightUser(userId,map,childList2,"R");
				
				if (listLeft.size()!=0&&listRight.size()!=0) {
					double leftCount = this.findAllUser(listLeft);
					double leftCount1 =leftCount; 
					double leftAlreadNode = this.findleveData(userId, "L");
					leftCount-=leftAlreadNode;
					
					double rightCount = this.findAllUser(listRight);
					double rightCount1= rightCount;
					double rightAlreadNode = this.findleveData(userId, "R");
					rightCount-=rightAlreadNode;
					
					if (leftCount!=0&&rightCount!=0) {
						Integer submitMoney = submitMoneyOld;
						double money = 0;
						double persentOne = submitMoney==1000?0.12:0.1;
						money=(leftCount>=rightCount)?(rightCount*200)*persentOne:(leftCount*200)*persentOne;
						
						//左区历史记录
						FhRecord fhRecord = new FhRecord();
						fhRecord.setUser(user);
						fhRecord.setCreateDate(new Date());
						fhRecord.setFhType("1"); //分红类型1：碰对奖 2：培育奖金 3：互动奖金 4：见点奖 5：全球分红奖
						
						switch (submitMoney) { //日封顶 1600:1600 3200:3200 6400:6400 12800:15000
							case 200:money=(money>=300)?300:money;break;
							case 500:money=(money>=1000)?1000:money;break;
							case 1000:money=(money>=3000)?3000:money;break;
							//case 12800:money=(money>=12800)?15000:money;break;
						}
						
						
						fhRecord.setFhmoney(money*1.00);
						fhRecord.setFlag("L");
						fhRecord.setLeaveNode(leftCount>=rightCount?(leftCount-rightCount):(rightCount-leftCount));
						fhRecord.setAlreadyNode(leftCount>=rightCount?rightCount:leftCount);
						fhRecord.setMemo("得到小区提成"+money);
						
						this.createFhRecord(fhRecord);
						
						//右区历史记录
						FhRecord fhRecordr = new FhRecord();
						fhRecordr.setUser(user);
						fhRecordr.setCreateDate(new Date());
						fhRecordr.setFhType("1");
						fhRecordr.setFhmoney(money*1.00);
						fhRecordr.setFlag("R");
						fhRecordr.setLeaveNode(rightCount>=leftCount?(rightCount-leftCount):(leftCount-rightCount));
						fhRecordr.setAlreadyNode(rightCount>=leftCount?leftCount:rightCount);
						fhRecordr.setMemo("得到小区提成"+money);
						this.createFhRecord(fhRecordr);
						
						//把此人得到的小区业绩奖项 平均分配给自己推荐的人
						avgAllByReferrerId(user,money);
						
					}
				}
		}
		
	}
	
	public void avgAllByReferrerId(User newUser,Double money) {
		List<User> userPeiyu = userDAO.findEntityByPropertiName("referrerId", newUser.getUserId());
		if (null!=userPeiyu&&userPeiyu.size()!=0)	{
			Double money1 = money*0.05/userPeiyu.size();
			
			for (int i=0;i<userPeiyu.size();i++) {
				User user1 = userPeiyu.get(i);
				FhRecord fhRecordr1 = new FhRecord();
				fhRecordr1.setUser(user1);
				fhRecordr1.setCreateDate(new Date());
				fhRecordr1.setFhType("14"); // 公司把推荐人的5% 分配给被推荐的人
				fhRecordr1.setFhmoney(money1);
				fhRecordr1.setMemo("拿到推荐人的5%碰对奖项"+money1);
				this.createFhRecord(fhRecordr1);
			}
			
		}
	}
	
	
	public void chongxiao() {
		List<Integer> userIds = fhRecordDAO.findAllUserAndNewData();
		
		List<User> users = userDAO.findAllUsersByIds(userIds);
		for (int i=0;i<users.size();i++) {
			FhRecord fhRecordr = new FhRecord();
			fhRecordr.setUser(users.get(i));
			fhRecordr.setFhType("11"); //扣除10%做为重复消费
			fhRecordr.setCreateDate(new Date());
			Double money = fhRecordDAO.findTotalMoneyByUserIdAndData(users.get(i).getUserId());
			fhRecordr.setFhmoney(money*-0.1);
			fhRecordr.setMemo("扣除当日奖金的10%做为重消电子商城购物，当日奖金为:["+money+"]");
			this.createFhRecord(fhRecordr);
			
			Repeat repeat = new Repeat();
			repeat.setCreateDate(new Date());
			repeat.setUser(users.get(i));
			repeat.setRepeatMoney(money*0.1);
			repeatDAO.create(repeat);
		}
		
	}
	
	
	public void peiyuMoney(User user,double money) {
		if (null!=user) {
			FhRecord fhRecordr = new FhRecord();
			fhRecordr.setUser(user);
			fhRecordr.setCreateDate(new Date());
			fhRecordr.setFhType("2");
			fhRecordr.setFhmoney(money*0.1);
			this.createFhRecord(fhRecordr);
		}
	}
	
	
	
	//查询我直接推荐了多少人
	
	
	public void quanqiuFH() { //TODO 全球分红
		List<User> listAll = this.findAllUserByLevel4();
		List<Integer> ids = new ArrayList<Integer>();
		for (int i=0;i<listAll.size();i++) {
			User user = listAll.get(0);
			ids.add(user.getUserId());
		}
		Integer count = this.findAllUserByrefferIds(ids);
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
	
	

	@Override
	public int checkAdminUserExistOrNo(String loginName, String password) {
		return 0;
	}
	
	/******************************************************************************************************************************************/
	
	public void openerRF(int roleId, List<Integer> functionId) {
		
		try {
			Role role = (Role)roleDAO.findById(roleId);
		
			for (RoleFunction litF : roleFunctionDAO.findFunctionByRoleId(roleId)) {
				roleFunctionDAO.delete(litF);
			}
				
			for (Integer fid : functionId) {
				RoleFunction roleFunction = new RoleFunction();
				Function funtion = (Function)functionDAO.findById(fid);
				
				roleFunction.setRole(role);
				roleFunction.setFunction(funtion);
				
				roleFunctionDAO.create(roleFunction);
			}
		}
		catch (Exception e) {
			System.err.println(" openerRF is Exception "+e.getMessage());
		}
		
	}
	
	public static int a(int flag,int count) {
		flag++;
		count++;
		if(flag<4){
			return a(flag,count);
		}else{
			return count;
		}
		
	}
	
	public static void main(String[] args) {
		System.out.print(a(1,0));
	}
	/*@Override
	public void memberbonus1() {
		// TODO Auto-generated method stub
		
		System.out.println("开始了开始了 "+"1111");
	}*/
	@Override
	public void generationCardNoAndPassword() {
		
		List list = new ArrayList();
		for (int ii=0; ii<10000; ii++) {
			int[] seed = { 1, 2, 3, 4, 5, 6, 7, 8, 9,0};  
			int[] ranArr = new int[9]; 
			String radCardNo = "";
			String radCardPassword = "";
			Random ran = new Random(); 
			// 数量你可以自己定义。 
			for (int i = 0; i < seed.length-1; i++) { 
				// 得到一个位置 
				int j = ran.nextInt(seed.length - i); 
				// 得到那个位置的数值 
				radCardPassword += seed[j]; 
				radCardNo+=seed[j]+(i+j);
				
				// 将最后一个未用的数字放到这里 
				seed[j] = seed[seed.length - 1 - i]; 
			
			}
			Card card = new Card();
			card.setCardNo(radCardNo);
			card.setCardPassword(radCardPassword);
			card.setCreateDate(new Date());
			card.setCardStatus("0");
			card.setCardType("1");
			card.setCardMoney(660.0);
			
			cardDAO.create(card);
		}
		cardDAO.deleteChongfuCardNo();
		
	}
}
