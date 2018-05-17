package com.zklc.weishangcheng.member.dao.impl;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.zklc.framework.dao.impl.EntityDaoImpl;
import com.zklc.weishangcheng.member.dao.FhRecordDao;
import com.zklc.weishangcheng.member.hibernate.persistent.FhRecord;
@Repository
public class FhRecordDaoImpl extends EntityDaoImpl <FhRecord, Integer> implements FhRecordDao {

	@Override
	public double findleveData(Integer userId, String flag) {
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
	public FhRecord findFhRecordBytoUserId(Integer userId, Integer fromUserId,Double amount,String flag,Integer ordersId) {
		DetachedCriteria dc = DetachedCriteria.forClass(FhRecord.class);
		dc.add(Restrictions.eq("userId", userId));
		dc.add(Restrictions.eq("fromUserId", fromUserId));
		dc.add(Restrictions.eq("fhmoney", amount));
		dc.add(Restrictions.eq("flag", flag));
		if (null!=ordersId) {
			dc.add(Restrictions.eq("ordersId", ordersId));
		}
		
		List<FhRecord> list = getHibernateTemplate().findByCriteria(dc);
		if (list.size()!=0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int findCountFhRecordByMoney(Integer userId, Double fhmoney) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("userId", userId));
		dc.add(Restrictions.eq("flag", "2"));
		dc.add(Restrictions.eq("fhmoney", fhmoney));
		dc.add(Restrictions.eq("fhType", "2"));
		dc.setProjection(Projections.count("userId"));
		
		List list = getHibernateTemplate().findByCriteria(dc,0,1);
		if (null!=list&&list.size()!=0) {
			return (null!=list.get(0))?Integer.parseInt(list.get(0).toString()):0;
		}
		return 0;
	}

	@Override
	public List<Integer> findNoSendHongbaoFromUserIds(Integer userId) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("userId", userId));
		dc.add(Restrictions.eq("flag", "1"));
		dc.setProjection(Projections.property("fromUserId"));
		
		List list = getHibernateTemplate().findByCriteria(dc);
		if (null!=list&&list.size()!=0) {
			return list;
		}
		return null;
	}

	@Override
	public List<FhRecord> findFHbyUserIdAndFlag(Integer userId, int i) {
		String hql = "from FhRecord fh where fh.userId = "+ userId+" and flag = '"+i+"'";
		return findByHql(hql, null);
	}

}
