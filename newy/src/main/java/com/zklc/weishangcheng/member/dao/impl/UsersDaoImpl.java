package com.zklc.weishangcheng.member.dao.impl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zklc.framework.dao.impl.EntityDaoImpl;
import com.zklc.framework.util.DateUtil;
import com.zklc.weishangcheng.member.dao.UsersDao;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
@Repository
public class UsersDaoImpl extends EntityDaoImpl <Users, Integer> implements UsersDao {

	@Override
	public List<Users> findAllUserByNowDate() throws ParseException {
		Calendar   cal   =   Calendar.getInstance();
		cal.add(Calendar.DATE,   -1);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String todayS = sf.format(cal.getTime());
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		String todayE = sf1.format(cal.getTime());
		
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.ge("createDate", DateUtil.parse(todayS)));
		dc.add(Restrictions.le("createDate", DateUtil.parse(todayE)));
		List<Users> list = getHibernateTemplate().findByCriteria(dc);
		return list;
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
				count =Double.parseDouble(((Integer)list.get(0)).toString())/600;
			}
		}
		return count;
	}

	@Override
	public List findAllUserIdByrefferIds(List<Integer> refferIds,int flag,int level,int pageNum) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		if (null!=refferIds) {
			dc.add(Restrictions.in("referrerId", refferIds));
			dc.addOrder(Order.desc("level"));
			switch (level) {
				case 1:dc.addOrder(Order.asc("oneFlag"));break;
				case 2:dc.addOrder(Order.asc("twoFlag1"));break;
				case 3:dc.addOrder(Order.asc("threeFlag2"));dc.addOrder(Order.asc("threeFlag1"));break;
			}
			
			if (flag==1) {
				dc.setProjection(Projections.property("userId")); 
			}
			List list = null;
			if(pageNum != 0){
				list = getHibernateTemplate().findByCriteria(dc,(pageNum-1)*5,5);
			}else {
				list = getHibernateTemplate().findByCriteria(dc);
			}
			
			if (list.size()!=0) {
				return list;
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
		
	}

	@Override
	public List findhongbaoAllUserIdByrefferIds(List<Integer> refferIds,
			int flag, int level) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		if (null!=refferIds) {
			dc.add(Restrictions.in("referrerId", refferIds));
			dc.addOrder(Order.desc("level"));
			switch (level) {
				case 1:
					dc.add(Restrictions.ge("level", 1));
					dc.add(Restrictions.isNull("oneFlag"));
					dc.add(Restrictions.isNull("activiti1"));
					dc.addOrder(Order.asc("oneFlag"));break;
				case 2:
					dc.add(Restrictions.ge("level", 2));
					dc.add(Restrictions.isNull("twoFlag1"));
					dc.add(Restrictions.isNull("activiti2"));
					dc.addOrder(Order.asc("twoFlag1"));break;
				case 3:
					
					dc.add(Restrictions.ge("level", 3));
					dc.add(Restrictions.or(Restrictions.isNull("threeFlag1"), Restrictions.or(Restrictions.isNull("threeFlag2"), Restrictions.isNull("activiti3"))));
					dc.addOrder(Order.asc("threeFlag2"));
					dc.addOrder(Order.asc("threeFlag1"));break;
			}
			
			if (flag==1) {
				dc.setProjection(Projections.property("userId")); 
			}
			
			List list = getHibernateTemplate().findByCriteria(dc);
			if (list.size()!=0) {
				return list;
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
		
	}
}
