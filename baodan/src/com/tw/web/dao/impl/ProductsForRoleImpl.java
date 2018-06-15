package com.tw.web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tw.web.dao.ProductsDAO;
import com.tw.web.dao.ProductsForRoleDao;
import com.tw.web.dao.RoleDAO;
import com.tw.web.hibernate.persistent.Products;
import com.tw.web.hibernate.persistent.ProductsForRole;
import com.tw.web.hibernate.persistent.Role;

@Repository
public class ProductsForRoleImpl extends CRUDBaseHibernateDAOImpl implements ProductsForRoleDao {

	@Override
	protected Class getPojoClass() {
		return ProductsForRole.class;
	}
	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private ProductsDAO productsDAO;

	@Override
	public void savePFR(int roleId, List<Integer> productsIds) {
		
		Role role = (Role)roleDAO.findById(roleId);
		List<ProductsForRole> pfrs = findEntityByPropertiName("roleId", roleId);
		
		for (ProductsForRole pfr : pfrs) {
			delete(pfr);
		}
			
		for (Integer pid : productsIds) {
			ProductsForRole pfr = new ProductsForRole();
			Products p = (Products) productsDAO.findById(pid);
			if(p!=null){
				pfr.setProducts(p);
				pfr.setRole(role);
				create(pfr);
			}
			
		}
	}

}
