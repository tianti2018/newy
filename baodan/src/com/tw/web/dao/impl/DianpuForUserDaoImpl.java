package com.tw.web.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tw.web.dao.DianpuForUserDao;
import com.tw.web.dao.ProductsDAO;
import com.tw.web.dao.ProductsForDianpuDao;
import com.tw.web.dao.UseryDao;
import com.tw.web.hibernate.persistent.DianpuForUser;
import com.tw.web.hibernate.persistent.Products;
import com.tw.web.hibernate.persistent.ProductsForDianpu;
import com.tw.web.hibernate.persistent.Usery;

@Repository
public class DianpuForUserDaoImpl extends CRUDBaseHibernateDAOImpl implements DianpuForUserDao {

	private UseryDao useryDao;
	private ProductsDAO productsDAO;
	private ProductsForDianpuDao productsForDianpuDao;
	
	@Override
	protected Class getPojoClass() {
		return DianpuForUser.class;
	}

	@Override
	public Boolean kaitongDianpu(Integer useryId) {
		Usery usery = (Usery) useryDao.findById(useryId);
		if(usery!=null){
			DianpuForUser dianpu = new DianpuForUser();
			dianpu.setName(usery.getUserName());
			dianpu.setUseryId(useryId);
			saveOrUpdate(dianpu);
			usery.setDianPuId(dianpu.getId());
			useryDao.saveOrUpdate(usery);
			List<Products> products = productsDAO.findAll();
			if(products!=null&&products.size()>0){
				for(Products product:products){
					if(product.getStatus().equals(1)){
						ProductsForDianpu pfd = new ProductsForDianpu();
						pfd.setCreateDate(new Date());
						pfd.setDianpuId(dianpu.getId());
						pfd.setHeadUrl(product.getHeadUrl());
						pfd.setJianyiPrice(product.getPrice());
						pfd.setName(product.getName());
						pfd.setPrice(product.getPrice());
						pfd.setType(product.getType());
						pfd.setProdType(product.getProdType());
						pfd.setProducts(product);
						pfd.setProductId(product.getProductsId());
						pfd.setKaiguan(0);
						pfd.setStatus(1);
						productsForDianpuDao.saveOrUpdate(pfd);
					}
				}
			}
			return true;
		}
		return false;
	}

	public UseryDao getUseryDao() {
		return useryDao;
	}
	@Autowired
	public void setUseryDao(UseryDao useryDao) {
		this.useryDao = useryDao;
	}

	public ProductsForDianpuDao getProductsForDianpuDao() {
		return productsForDianpuDao;
	}

	@Autowired
	public void setProductsForDianpuDao(ProductsForDianpuDao productsForDianpuDao) {
		this.productsForDianpuDao = productsForDianpuDao;
	}

	public ProductsDAO getProductsDAO() {
		return productsDAO;
	}

	@Autowired
	public void setProductsDAO(ProductsDAO productsDAO) {
		this.productsDAO = productsDAO;
	}
	
	

}
