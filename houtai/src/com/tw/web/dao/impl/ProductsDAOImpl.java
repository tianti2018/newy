package com.tw.web.dao.impl;

import org.springframework.stereotype.Repository;

import com.tw.web.dao.ProductsDAO;
import com.tw.web.hibernate.persistent.Products;

@Repository
public class ProductsDAOImpl extends CRUDBaseHibernateDAOImpl implements ProductsDAO {

	@Override
	protected Class getPojoClass() { 
		return Products.class;
	}

}
