package com.tw.web.dao.impl;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.tw.web.dao.FhRecordDAO;
import com.tw.web.hibernate.persistent.FhRecord;
import com.tw.web.util.DateUtil;

@Repository
public class FhRecordDAOImpl extends CRUDBaseHibernateDAOImpl implements FhRecordDAO {

	@SuppressWarnings("unchecked")
	@Override
	protected Class getPojoClass() {
		return FhRecord.class;
	}

	@Override
	public double findleveData(Integer userId, String flag) { //找到当前最新一笔最后一次碰对左区还是右区还剩下多少没有碰
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("userId", userId));
		dc.add(Restrictions.eq("flag", flag));
		dc.setProjection(Projections.sum("alreadyNode"));
		
		List list = getHibernateTemplate().findByCriteria(dc,0,1);
		if (null!=list) {
			
			return (null!=list.get(0))?(Double)list.get(0):0;
		}
		return 0;
	}

	@Override
	public List<FhRecord> findAllFhRecordByUserId(Integer userId) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("userId", userId));
		dc.add(Restrictions.or(Restrictions.ne("flag", "R"), Restrictions.isNull("flag")));
		
		List<FhRecord> list = getHibernateTemplate().findByCriteria(dc);
		return list;
	}

	@Override
	public Double findTotalMoneyByUserId(Integer userId) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("userId", userId));
		dc.add(Restrictions.in("fhType", new String[]{"3","4","5","6","7","8","9","10","11","12","13","14"})); 
		dc.setProjection(Projections.sum("fhmoney"));
		
		List<Double> money = getHibernateTemplate().findByCriteria(dc,0,1);
		if (null!=money) {
			return null==money.get(0)?0:money.get(0);
		}
		return 0.0;
	}

	@Override
	public Double findTotalMoneyByUserIdAndData(Integer userId) {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String todayS = sf.format(date);
		
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String todayE = sf1.format(date);
		
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("userId", userId));
		dc.add(Restrictions.ne("fhType", "8"));
		dc.add(Restrictions.or(Restrictions.ne("flag", "R"), Restrictions.isNull("flag")));
		try {
			dc.add(Restrictions.ge("createDate", DateUtil.parse(todayS)));
			dc.add(Restrictions.le("createDate", DateUtil.parse(todayE)));
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		dc.setProjection(Projections.sum("fhmoney"));
		
		List<Double> money = getHibernateTemplate().findByCriteria(dc,0,1);
		if (null!=money) {
			return null==money.get(0)?0:money.get(0);
		}
		return 0.0;
	}

	@Override
	public List<Integer> findAllUserAndNewData() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String todayS = sf.format(date);
		
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String todayE = sf1.format(date);
		
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.or(Restrictions.ne("flag", "R"), Restrictions.isNull("flag")));
		try {
			dc.add(Restrictions.ge("createDate", DateUtil.parse(todayS)));
			dc.add(Restrictions.le("createDate", DateUtil.parse(todayE)));
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		dc.setProjection(Projections.distinct(Projections.property("userId")));
		List<Integer> userIds = getHibernateTemplate().findByCriteria(dc);
		
		return userIds;
	}

	@Override
	public Double findTotalMoneyByDate() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String todayS = sf.format(date);
		
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String todayE = sf1.format(date);
		
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.or(Restrictions.ne("flag", "R"), Restrictions.isNull("flag")));
		try {
			dc.add(Restrictions.ge("createDate", DateUtil.parse(todayS)));
			dc.add(Restrictions.le("createDate", DateUtil.parse(todayE)));
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		dc.setProjection(Projections.sum("fhmoney"));
		
		List<Double> money = getHibernateTemplate().findByCriteria(dc,0,1);
		if (null!=money) {
			return null==money.get(0)?0:money.get(0);
		}
		return 0.0;
	}

	@Override
	public Double findTotalMoney() {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.or(Restrictions.ne("flag", "R"), Restrictions.isNull("flag")));
		dc.add(Restrictions.in("fhType", new String[]{"1","2","3","4","9","10","13","14","16"}));
		
		dc.setProjection(Projections.sum("fhmoney"));
		List<Double> money = getHibernateTemplate().findByCriteria(dc,0,1);
		if (null!=money) {
			return null==money.get(0)?0:money.get(0);
		}
		return 0.0;
	}	
	
	@Override
	public int findTotalCountSmall(BigInteger uoId) { //总共拿到多少积分
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("uoId", uoId));
		dc.add(Restrictions.eq("fhType", "3"));
		dc.setProjection(Projections.rowCount());
		
		List list = (List)getHibernateTemplate().findByCriteria(dc,0,1);
		if (null != list) {
			return (Integer)list.get(0);
		}
		
		return 0;
	}

	@Override
	public int findNetUser(Integer userId) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("userId", userId));
		dc.add(Restrictions.eq("fhType", "12")); //网络使用费用
		
		List list = (List)getHibernateTemplate().findByCriteria(dc,0,1);
		if (null != list) {
			return (Integer)list.size();
		}
		
		return 0;
	}

	@Override
	public Double findAllBaodanBi(Integer userId) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("userId", userId));
		dc.add(Restrictions.or(Restrictions.ne("flag", "R"), Restrictions.isNull("flag")));
		dc.add(Restrictions.in("fhType", new String[]{"5","6","7","8","12","15","17"})); // 存值 电子转账--转入 奖金转换电子币-转入
		dc.setProjection(Projections.sum("fhmoney"));
		
		List<Double> money = getHibernateTemplate().findByCriteria(dc,0,1);
		if (null!=money) {
			return null==money.get(0)?0:money.get(0);
		}
		return 0.0;
	}

	@Override
	public Double findAllJiangjing(Integer userId) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("userId", userId));
		dc.add(Restrictions.in("fhType", new String[]{"3","4","5","6","7","8","9","10","11","12","13","14","15"}));
		dc.setProjection(Projections.sum("fhmoney"));
		
		List<Double> money = getHibernateTemplate().findByCriteria(dc,0,1);
		if (null!=money) {
			return null==money.get(0)?0:money.get(0);
		}
		return 0.0;
	}

	@Override
	public Double findTotalJiFenByGoumai(Integer userId) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("userId", userId));
		dc.add(Restrictions.eq("fhType", "1")); 
		dc.setProjection(Projections.sum("fhmoney"));
		
		List<Double> money = getHibernateTemplate().findByCriteria(dc,0,1);
		if (null!=money) {
			return null==money.get(0)?0:money.get(0);
		}
		return 0.0;
	}

	@Override
	public Double findTotalJiFenByOrders(Integer uoId) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("uoId", uoId));
		dc.add(Restrictions.eq("fhType", "3")); 
		dc.setProjection(Projections.sum("fhmoney"));
		
		List<Double> money = getHibernateTemplate().findByCriteria(dc,0,1);
		if (null!=money) {
			return null==money.get(0)?0:money.get(0);
		}
		return 0.0;
	}

	@Override
	public Double findTotalJiFenByUserId(Integer userId) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("userId", userId));
		dc.setProjection(Projections.sum("fhmoney"));
		
		List<Double> money = getHibernateTemplate().findByCriteria(dc,0,1);
		if (null!=money) {
			return null==money.get(0)?0:money.get(0);
		}
		return 0.0;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findTotalMoneyByFhType(Integer userId) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT  userId as '用户Id', SUM(IF(fhType='3',fhmoney,0)) as '品牌建设分红', ");
		//sb.append(" SELECT date_format(createDate,'%Y-%m-%d') as '日期', userId as '用户Id', SUM(IF(fhType='3',fhmoney,0)) as '品牌建设分红', ");
		
		sb.append(" SUM(IF(fhType='4',fhmoney,0)) as '服务分红', ");
		sb.append(" SUM(IF(fhType='5',fhmoney,0)) as '分享分红', ");
		sb.append(" SUM(IF(fhType='6',fhmoney,0)) as '提现扣除', ");
		sb.append(" SUM(IF(fhType='7',fhmoney,0)) as '购买卡密', ");
		sb.append(" SUM(IF(fhType='3',fhmoney,0))+SUM(IF(fhType='4',fhmoney,0))+SUM(IF(fhType='5',fhmoney,0))+SUM(IF(fhType='6',fhmoney,0))+SUM(IF(fhType='7',fhmoney,0)) as '总积分'  ");
		//sb.append(" from fhrecord where userId =:userId GROUP BY  date_format(createDate,'%Y-%m-%d')");
		sb.append(" from fhrecord where userId =:userId GROUP BY  createDate ");
		Query query1 = this.getSession().createSQLQuery(sb.toString());
		query1.setParameter("userId", userId);
		query1.executeUpdate();
		List list = query1.list();
		for (int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
		return query1.list();
	}

	@Override
	public Double findTotalJiFenOneByUserId(Integer userId,String[] fhType) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.in("fhType", fhType));
		dc.add(Restrictions.eq("userId", userId));
		dc.setProjection(Projections.sum("fhmoney"));
		
		List<Double> money = getHibernateTemplate().findByCriteria(dc,0,1);
		if (null!=money) {
			return null==money.get(0)?0:money.get(0);
		}
		return 0.0;
	}

	@Override
	public FhRecord findFhRecordByUidAndFid(Integer userId, String dingqiFlag) {
		FhRecord returnObj=null;
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("userId", userId));
		dc.add(Restrictions.eq("dingqiFlag", dingqiFlag));
		
		List list = getHibernateTemplate().findByCriteria(dc,0,1);
		if (null!=list&&list.size()>0) {
			returnObj=(FhRecord) list.get(0);
		}
		return returnObj;
	}
}
