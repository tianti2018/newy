package com.tw.web.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tw.web.dao.ProductsDAO;
import com.tw.web.dao.ProductsForDianpuDao;
import com.tw.web.hibernate.persistent.Products;

@Repository
public class ProductsDAOImpl extends CRUDBaseHibernateDAOImpl implements ProductsDAO {

	@Autowired
	private ProductsForDianpuDao productsForDianpuDao;
	
	@Override
	protected Class getPojoClass() { 
		return Products.class;
	}

	@Override
	public void xiajia(Products product) {
		product.setStatus(0);
		saveOrUpdate(product);
		productsForDianpuDao.update("update ProductsForDianpu pfd set pfd.kaiguan = 1 where pfd.productId ="+product.getProductsId(), null);
		
	}

}
