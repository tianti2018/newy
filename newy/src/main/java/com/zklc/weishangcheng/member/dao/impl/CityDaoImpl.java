package com.zklc.weishangcheng.member.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zklc.framework.dao.impl.EntityDaoImpl;
import com.zklc.weishangcheng.member.dao.CityDao;
import com.zklc.weishangcheng.member.hibernate.persistent.City;
import com.zklc.weishangcheng.member.hibernate.persistent.Order;
@Repository
public class CityDaoImpl extends EntityDaoImpl <City, Integer> implements CityDao{

	@Override
	public List<City> getCityByLevel(Integer level) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("ordersBH", level));
		
		List<Order> list = getHibernateTemplate().findByCriteria(dc,0,1);
		
		return null;
	}

}
