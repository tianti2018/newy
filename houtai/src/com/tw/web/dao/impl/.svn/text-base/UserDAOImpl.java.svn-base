package com.tw.web.dao.impl;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.tw.web.dao.UserDAO;
import com.tw.web.hibernate.persistent.TArea;
import com.tw.web.hibernate.persistent.User;
import com.tw.web.util.DateUtil;

@Repository
public class UserDAOImpl extends CRUDBaseHibernateDAOImpl implements UserDAO {

	@SuppressWarnings("unchecked")
	@Override
	protected Class getPojoClass() {
		
		return User.class;
	}
	
	/**
	 * 
	 * @param loginName 登录输入的用户名
	 * @parame password 登录输入的密码
	 * @return int 11: 登录成功 12: 密码不正确 -1:用户不存在
	 * 
	 */	
	@Override
	public int checkUserExistOrNo(String loginName, String password) {
		
		int intflag = 0;
		
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("loginName", loginName));
		
		if (getHibernateTemplate().findByCriteria(dc).size() != 0 ) {
			
			dc.add(Restrictions.eq("passWord", password));
						
			if (getHibernateTemplate().findByCriteria(dc).size() != 0) {
				
				// 登录成功
				intflag = 11;
			}
			else {
				
				// 密码不正确
				intflag = 12;
			}
		}
		else {
			
			// 用户不存在
			intflag = -1;
		}
				
		return intflag;			
	}
	
	/**
	 * 检查改用户是否存在
	 * @param loginName 登录用户名
	 */
	@SuppressWarnings("unchecked")
	@Override
	public User findUserByLoginName(String loginName) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("loginName", loginName));
		
		List<User> list = getHibernateTemplate().findByCriteria(dc);
		
		if (list.size() != 0) {
			return (User)list.get(0);
		}
		
		return null;
	}

	@Override
	public double findAllUserByIds(List<Integer> ids) {
		if (null==ids || ids.size()==0) {
			return 0;
		}
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.in("userId", ids));
		dc.setProjection(Projections.sum("realSubmitMoney")); //待遇真实的缴纳多少费用
		List list = getHibernateTemplate().findByCriteria(dc,0,1);
		double count = 0.0;
		if (null!=list&&list.size()!=0) {
			if (null!=list.get(0)) {
				count =Double.parseDouble(((Integer)list.get(0)).toString())/200;
			}
		}
		
		return count;
	}

	@Override
	public List<User> findAllUserByNowDate() throws ParseException {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String todayS = sf.format(date);
		
		
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String todayE = sf1.format(date);
		
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.ge("createDate", DateUtil.parse(todayS)));
		dc.add(Restrictions.le("createDate", DateUtil.parse(todayE)));
		List<User> list = getHibernateTemplate().findByCriteria(dc);
		
		return list;
	}
	
	public static void main(String[] args) {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		System.out.println(sf.format(date));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUserByLevel4() {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("level", "4"));
		dc.add(Restrictions.eq("realSubmitMoney", 12800));
		List<User> list = getHibernateTemplate().findByCriteria(dc);
		return list;
	}

	@Override
	public Integer findAllUserByrefferIds(List<Integer> ids) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.in("referrerId", ids));
		dc.add(Restrictions.eq("level", "4"));
		dc.add(Restrictions.eq("realSubmitMoney", 12800));
		dc.setProjection(Projections.rowCount());
		List<Integer> list = getHibernateTemplate().findByCriteria(dc,0,1);
		if (list!=null) {
			return list.get(0);
		}
		return 0;
	}

	@Override
	public List<User> findAllUsersByIds(List<Integer> ids) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.in("userId", ids));
		
		List<User> list = getHibernateTemplate().findByCriteria(dc);
		return list;
	}

	@Override
	public double findAllUserByNowDate(List<Integer> userIds)
			throws ParseException {
		if (null==userIds||userIds.size()==0) {
			return 0;
		}
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String todayS = sf.format(date);
		
		
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String todayE = sf1.format(date);
		
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.ge("createDate", DateUtil.parse(todayS)));
		dc.add(Restrictions.le("createDate", DateUtil.parse(todayE)));
		dc.add(Restrictions.in("userId", userIds));
		dc.setProjection(Projections.sum("realSubmitMoney")); //待遇真实的缴纳多少费用
		List list = getHibernateTemplate().findByCriteria(dc,0,1);
		double count = 0;
		if (null!=list&&list.size()!=0) {
			if (null!=list.get(0)) {
				count = Double.parseDouble(((Integer)list.get(0)).toString())/200;
			}
		}
		
		return count;
	}

	@Override
	public Integer findTotalMoneyByNowDate() throws ParseException {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String todayS = sf.format(date);
		
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String todayE = sf1.format(date);
		
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.ge("createDate", DateUtil.parse(todayS)));
		dc.add(Restrictions.le("createDate", DateUtil.parse(todayE)));
		dc.setProjection(Projections.sum("realSubmitMoney")); //待遇真实的缴纳多少费用
		List list = getHibernateTemplate().findByCriteria(dc,0,1);
		Integer count = 0;
		if (null!=list&&list.size()!=0) {
			if (null!=list.get(0)) {
				count = (Integer)list.get(0);
			}
		}
		
		return count;
	}

	@Override
	public Integer findTotalMoney() throws ParseException {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.setProjection(Projections.sum("realSubmitMoney")); //待遇真实的缴纳多少费用
		List list = getHibernateTemplate().findByCriteria(dc,0,1);
		Integer count = 0;
		if (null!=list&&list.size()!=0) {
			if (null!=list.get(0)) {
				count = (Integer)list.get(0);
			}
		}
		
		return count;
	}

	@Override
	public Map<Integer,Integer[]> findAllUserVOS() {
		List list = new ArrayList();
		Query query = this.getSession().createSQLQuery("select u.userId,u.child1,u.child2,u.submitMoney,u.realSubmitMoney from users u");
		list = query.list();
		
		Map<Integer,Integer[]> map = new HashMap<Integer,Integer[]>();
		for (int i=0;i<list.size();i++) {
			Object[] object = (Object[])list.get(i);
			Integer key = null==object[0]?null:(Integer)object[0];
			if (null==key) {
				continue;
			}
			Integer value1 = null==object[1]?null:(Integer)object[1];
			Integer value2 = null==object[2]?null:(Integer)object[2];
			Integer value3 = null==object[3]?null:(Integer)object[3];
			Integer value4 = null==object[4]?null:(Integer)object[4];
			map.put(key, new Integer[]{value1,value2,value3,value4});
		}
		
		return map;
	}

	@Override
	public Map<Integer, Integer> findRefferIdByUserId(Integer userId) {
		List list = new ArrayList();
		Query query = this.getSession().createSQLQuery("SELECT userId,realSubmitMoney from users where userId = (SELECT referrerId from users where userId=:userId)");
		query.setParameter("userId", userId);
		list = query.list();
		
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		for (int i=0;i<list.size();i++) {
			Object[] object = (Object[])list.get(i);
			Integer key = null==object[0]?null:(Integer)object[0];
			if (null==key) {
				continue;
			}
			Integer value1 = null==object[1]?null:(Integer)object[1];
			map.put(key, value1);
		}
		
		return map;
	}

	@Override
	public BigInteger findFinalOrder(String flagorg) {
		List list = new ArrayList();
		BigInteger orderOne= new BigInteger("1");
		Query query = this.getSession().createSQLQuery("SELECT max(orderOne)+1 from user_order where flagorg is null or  flagorg='"+flagorg+"'"); //查找到最大的序号
		list = query.list();
		for (int i=0;i<list.size();i++) {
			orderOne = (BigInteger)list.get(i);
			break;
		}
		return orderOne;
	}

	@Override
	public BigInteger findCountReferrerId(Integer userId) {
		Query query = this.getSession().createSQLQuery("SELECT count(referrerId) from users u JOIN user_order o ON u.userId=o.userId and u.referrerId=:referrerId"); //查找到最大的序号
		query.setParameter("referrerId", userId);
		BigInteger count =null;
		List list = query.list();
		for (int i=0;i<list.size();i++) {
			count = (BigInteger)list.get(i);
			break;
		}
		return count;
	}

	@Override
	public Map<Integer,String[]> findAllReffer(String loginName, int firstResult, int maxResults,String flag) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT u.referrerId,u1.loginName,u1.userName,count(u.referrerId) from users u JOIN user_order o ON u.userId = o.userId  and u.referrerId is not null ");
		sb.append("  JOIN users u1 ON u1.userId=u.referrerId  ");
		if (null!=loginName && !"".equals(loginName)) {
			sb.append("and u1.loginName LIKE '%"+loginName+"%'");
		}
		sb.append(" GROUP BY u.referrerId HAVING count(u.referrerId)>=5 ");
		Query query = this.getSession().createSQLQuery(sb.toString()); //查找到最大的序号
		Map<Integer,String[]> map = new HashMap<Integer,String[]>();
		if ("all".equals(flag)) {
			int count = query.list()==null?0:query.list().size();
			 map.put(1, new String[]{count+""});
			 return map;
		}
		else {
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResults);
			List list = query.list();
			for (int i=0;i<list.size();i++) {
				Object[] object = (Object[])list.get(i);
				Integer userId = (Integer)object[0];
				BigInteger count = (BigInteger)object[3];
				String value1 = null==object[0]?null:String.valueOf(userId.intValue());
				String value2 = null==object[1]?null:(String)object[1];
				String value3 = null==object[2]?null:(String)object[2];
				String value4 = null==object[3]?null:String.valueOf(count.intValue());
				map.put(i, new String[]{value1,value2,value3,value4});
			}
			return map;
		}
	}

	@Override
	public List<User> listPageBySql(String sql, int firstResult,
			int maxReuslt,String orderProperty,boolean isAce) throws ParseException {
		if(isAce){
			sql+=" asc";
		}else {
			sql+=" desc";
		}
		SQLQuery query = this.getSession().createSQLQuery(sql)
				.addScalar("userId",Hibernate.INTEGER)
				.addScalar("userName",Hibernate.STRING)
				.addScalar("child11",Hibernate.INTEGER)
				.addScalar("appDate",Hibernate.DATE)
				.addScalar("referrerId",Hibernate.INTEGER)
				.addScalar("wxOpenid",Hibernate.STRING);
		
		query.setFirstResult(firstResult);
		query.setMaxResults(maxReuslt);
		List qlist = query.list();
		List<User> list = new ArrayList<User>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for(int i = 0;i<qlist.size();i++){
			Object[] os = (Object[]) qlist.get(i);
			User user = new User();
			if(os[0] != null && !os[0].toString().isEmpty())
				user.setUserId(Integer.parseInt(os[0].toString()));
			if(os[1] != null)
			user.setUserName(os[1].toString());
			if(os[2] != null && !os[2].toString().isEmpty())
				user.setChild1(Integer.parseInt(os[2].toString()));
			if(os[3] !=null && !os[3].toString().isEmpty())
				user.setAppDate(sdf.parse(os[3].toString()));
			if(os[4] !=null && !os[4].toString().isEmpty())
				user.setReferrerId(Integer.parseInt(os[4].toString()));
			if(os[5] !=null && !os[5].toString().isEmpty())
				user.setWxOpenid(os[5].toString());
			list.add(user);
		}
		return list;
	}

	@Override
	public int countRows(String sql) {
		Query query = this.getSession().createSQLQuery(sql);
		Object o = query.uniqueResult();
		return Integer.parseInt(o.toString());
	}

	@Override
	public List<TArea> countFriFamily(Integer userId, String viewLevel,
			String name) {
		List list = null;
		String sql=null;
		if(userId != null && !"".equals(userId)){
				if(viewLevel.equals("all")){
					sql="select count('1')level from users t1, users t2 where t1.userId=t2.referrerId and (t1.userId= "+userId+"  )  and t2.wxOpenid is NOT NULL"+
						" union all "+
						"select count('2')level from users t1, users t2 where t1.userId=t2.referrerId and (t1.referrerId= "+userId+"  )  and t2.wxOpenid is NOT NULL" +
						" union all "+
						"select  COUNT('3')level from users t3 where t3.referrerId in(select t2.userId from users t1, users t2 where t1.userId=t2.referrerId and (t1.referrerId= "+userId+")) and t3.wxOpenid is NOT NULL";
				}else if(viewLevel.equals("1")){
					 sql = "select  t2.userName,date_format(t2.appDate,'%Y-%m-%d %H:%i:%s') createDate,t2.phone,t2.wxOpenid,'' photourl from users t1, users t2 where t1.userId=t2.referrerId and (t1.userId= "+userId+"  )  and t2.wxOpenid is NOT NULL";
				}else if(viewLevel.equals("2")){
					 sql = "select   t2.userName,date_format(t2.appDate,'%Y-%m-%d %H:%i:%s') createDate,t2.phone,t2.wxOpenid,'' photourl from users t1, users t2 where t1.userId=t2.referrerId and (t1.referrerId= "+userId+"  )  and t2.wxOpenid is NOT NULL";
				}else{
					 sql = "select  t3.userName,date_format(t3.appDate,'%Y-%m-%d %H:%i:%s') createDate,t3.phone,t3.wxOpenid,'' photourl from users t3 where t3.referrerId in(select t2.userId from users t1, users t2 where t1.userId=t2.referrerId and (t1.referrerId= "+userId+"  )) and t3.wxOpenid is NOT NULL";
				}
			
			if(null != name && !"".equals(name)){
				if(viewLevel.equals("1") || viewLevel.equals("2")){
					sql += " and t2.userName like '%"+name+"%'";
				}else{
					sql += " and t3.userName like '%"+name+"%'";
				}
				 
			}
			
			 list=super.findBySql(sql);
		}else{
			list = new ArrayList();
		}
		return list;
	}

	@Override
	public List<TArea> countGoumaiFamily(Integer userId, String viewLevel,
			String name) {
		List list = null;
		String sql=null;
		if(userId != null && !"".equals(userId)){
				if(viewLevel.equals("all")){
					sql="select count('1')level from users t1, users t2 where t1.userId=t2.referrerId and (t1.userId= "+userId+"  )  and t2.wxOpenid is NOT NULL and t2.ticket is NOT NULL and t2.ticket <>''"+
						" union all "+
						"select count('2')level from users t1, users t2 where t1.userId=t2.referrerId and (t1.referrerId= "+userId+"  )  and t2.wxOpenid is NOT NULL and t2.ticket is NOT NULL and t2.ticket <>''" +
						" union all "+
						"select  COUNT('3')level from users t3 where t3.referrerId in(select t2.userId from users t1, users t2 where t1.userId=t2.referrerId and (t1.referrerId= "+userId+")) and t3.wxOpenid is NOT NULL and t3.ticket is NOT NULL and t3.ticket <>''";
				}else if(viewLevel.equals("1")){
					 sql = "SELECT DISTINCT t.userId from orders o, (select  t2.userId from users t1, users t2 where t1.userId=t2.referrerId and (t1.userId= "+userId+"  )  and t2.wxOpenid is NOT NULL) t WHERE o.userId = t.userId AND o.order_status NOT IN (0,5)";
				}else if(viewLevel.equals("2")){
					 sql = "SELECT DISTINCT t.userId from orders o, (select   t2.userId from users t1, users t2 where t1.userId=t2.referrerId and (t1.referrerId= "+userId+"  )  and t2.wxOpenid is NOT NULL) t WHERE o.userId = t.userId AND o.order_status NOT IN (0,5)";
				}else{
					 sql = "SELECT DISTINCT t.userId from orders o, (select  t3.userId from users t3 where t3.referrerId in(select t2.userId from users t1, users t2 where t1.userId=t2.referrerId and (t1.referrerId= "+userId+"  )) and t3.wxOpenid is NOT NULL) t WHERE o.userId = t.userId AND o.order_status NOT IN (0,5)";
				}
			
			if(null != name && !"".equals(name)){
				if(viewLevel.equals("1") || viewLevel.equals("2")){
					sql += " and t2.userName like '%"+name+"%'";
				}else{
					sql += " and t3.userName like '%"+name+"%'";
				}
				 
			}
			
			 list=super.findBySql(sql);
		}else{
			list = new ArrayList();
		}
		return list;
	}
}
