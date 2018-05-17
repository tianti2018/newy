package com.zklc.weishangcheng.member.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.zklc.framework.dao.impl.EntityDaoImpl;
import com.zklc.weishangcheng.member.dao.OrderDao;
import com.zklc.weishangcheng.member.hibernate.persistent.Order;
@Repository
public class OrderDaoImpl extends EntityDaoImpl <Order, Integer> implements OrderDao {

	@Override
	public Order findOrderByOrderBH(String ordersBH) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("ordersBH", ordersBH));
		
		List<Order> list = getHibernateTemplate().findByCriteria(dc,0,1);
		if (list.size()!=0) {
			return list.get(0);
		}
		return null;
	}
}
