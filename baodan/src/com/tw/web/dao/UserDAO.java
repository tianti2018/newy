package com.tw.web.dao;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.tw.web.hibernate.persistent.TArea;
import com.tw.web.hibernate.persistent.User;

public interface UserDAO extends ICRUDBaseDAO {

	/**
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 */
	public int checkUserExistOrNo (String loginName, String password);
	
	public User findUserByLoginName (String loginName);
	
	public double findAllUserByIds(List<Integer> ids);
	
	public List<User> findAllUserByNowDate() throws ParseException;
	public double findAllUserByNowDate(List<Integer> userIds) throws ParseException;
	
	public Integer findTotalMoneyByNowDate() throws ParseException;
	public Integer findTotalMoney() throws ParseException;
	
	public List<User> findAllUserByLevel4();
	
	public Integer findAllUserByrefferIds(List<Integer> ids);
	
	public List<User> findAllUsersByIds(List<Integer> ids);
	
	public Map<Integer,Integer[]> findAllUserVOS(); 
	
	public Map<Integer,Integer> findRefferIdByUserId(Integer userId);
	
	public BigInteger findFinalOrder(String orgflag);//找到最后一个序号
	
	public BigInteger findCountReferrerId(Integer userId); //查询当前推荐人共推荐了多少用户
	
	public Map<Integer,String[]> findAllReffer(String loginName,final int firstResult,final int maxResults, final String flag);
	
	public List<User> listPageBySql(String sql,final int firstResult,final int maxReuslt,String orderProperty,boolean isAce)throws ParseException;
	
	public int countRows(String sql) ;
	
	/**
	 * 获取下级会员人数
	 * @param userId
	 * @param viewLevel
	 * @param name
	 * @return
	 */
	 
	public List<TArea> countFriFamily(Integer userId, String viewLevel,
			String name);
	
	/**
	 * 获取下级购买会员人数
	 * @param userId
	 * @param viewLevel
	 * @param name
	 * @return
	 */
	 
	public List<TArea> countGoumaiFamily(Integer userId, String viewLevel,
			String name);
}
