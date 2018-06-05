package com.tw.web.dao.impl;

import org.springframework.stereotype.Repository;

import com.tw.web.dao.ProductsForDianpuDao;
import com.tw.web.hibernate.persistent.ProductsForDianpu;

@Repository
public class ProductsForDianpuDaoImpl extends CRUDBaseHibernateDAOImpl implements ProductsForDianpuDao {

	@Override
	protected Class getPojoClass() {
		// TODO Auto-generated method stub
		return ProductsForDianpu.class;
	}

}
