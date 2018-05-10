package com.tw.web.dao.impl;

import org.springframework.stereotype.Repository;

import com.tw.web.dao.OrdersProductsDAO;
import com.tw.web.hibernate.persistent.OrdersProducts;

@Repository
public class OrdersProductsDAOImpl extends CRUDBaseHibernateDAOImpl implements OrdersProductsDAO {

	@Override
	protected Class getPojoClass() { 
		return OrdersProducts.class;
	}

}
