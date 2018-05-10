package com.tw.web.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.tw.web.hibernate.persistent.FhRecord;
import com.tw.web.hibernate.persistent.Record;
import com.tw.web.hibernate.persistent.Relation;
import com.tw.web.hibernate.persistent.Repeat;
import com.tw.web.hibernate.persistent.User;

public interface LoginService {
	
	/*********************************** 对User 所有操作 start  *****************************************************/
	public int checkUserExistOrNo (String loginName, String password);
	
	public int checkAdminUserExistOrNo (String loginName, String password);
	
	public User findUserByUserId(Integer userId);
	
	public User findUserByLoginName(String loginName);
	
	public void createUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUser(User user);
	
	public List<User> findAllUser(String propertyName, boolean isAsc, int firstResult, int maxResults, String flag); 
	
	public List<FhRecord> findAllFhRecord(String propertyName, boolean isAsc, int firstResult, int maxResults, String flag); 
	
	public List<FhRecord> findAllFhRecordByUserId(Map<String,Object> conditionProperties,Map<String,Integer> compare,Map<String,Boolean> sort, final int firstResult,final int maxResults, final String flag);
	
	public List<User> findAllUser();
	public double findAllUser(List<Integer> ids);
	
	public List<User> findAllUserByNowDate() throws ParseException;
	
	public List<User> findAllUserByLevel4();
	
	public Integer findAllUserByrefferIds(List<Integer> ids);
	/*********************************** 对User 所有操作  end ********************************************************/
	
	
	public Relation findRelationByUserId(Integer userId) throws Exception;
	public Relation findRelationById(Integer relationId) throws Exception;
	
	public void createRelation(Relation relation) throws Exception;
	public void modifyRelation(Relation relation) throws Exception;
	public void deleteRelation(Integer relationId) throws Exception;
	
	
	public Record findRecordById(Integer recordId) throws Exception;
	
	public void createRecord(Record record) throws Exception;
	public void modifyRecord(Record record) throws Exception;
	public void deleteRecord(Integer recordId) throws Exception;
	
	public void createRepeat(Repeat repeat) throws Exception;
	public void modifyRepeat(Repeat repeat) throws Exception;
	public void deleteRepeat(Integer repeatId) throws Exception;
	public Repeat findRepeatById(Integer repeatId) throws Exception;
	
	public boolean findRepeatByCrrentYM(Integer userId, String yyyyMM) throws Exception; 
	
	public List<FhRecord> findAllFhRecordByUserId(Integer userId);
	
	/***************分红操作记录start *************/
	public void createFhRecord(FhRecord fhRecord);
	
	public void updateFhRecord(FhRecord fhRecord);
	
	public void deleteFhRecord(Integer fhId);
	
	public double findleveData(Integer userId,String flag); //根据用户Id找到左区或是右区还没有碰完的个数 flag = L 左区、R 右区
	
	/***************分红操作记录end *************/
	
	public void updateMemberbonus();
	
	public void fenhongall();
	
	
	//public void memberbonus1();

	
	public void openerRF(int roleId, List<Integer> functionId);
	
	public void generationCardNoAndPassword();


}
