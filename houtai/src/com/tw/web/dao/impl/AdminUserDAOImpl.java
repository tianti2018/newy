package com.tw.web.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.tw.web.dao.AdminUserDAO;
import com.tw.web.hibernate.persistent.AdminUser;

@Repository
public class AdminUserDAOImpl extends CRUDBaseHibernateDAOImpl implements AdminUserDAO {

	@SuppressWarnings("unchecked")
	@Override
	protected Class getPojoClass() {
		
		return AdminUser.class;
	}
	
	/**
	 * 
	 * @param loginName 登录输入的用户名
	 * @parame password 登录输入的密码
	 * @return int 11: 登录成功 12: 密码不正确 -1:用户不存在
	 * 
	 */	
	@Override
	public int checkUserExistOrNo(String loginName, String password) {
		
		int intflag = 0;
		
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("loginName", loginName));
		
		if (getHibernateTemplate().findByCriteria(dc).size() != 0 ) {
			
			dc.add(Restrictions.eq("passWord", password));
						
			if (getHibernateTemplate().findByCriteria(dc).size() != 0) {
				
				// 登录成功
				intflag = 11;
			}
			else {
				
				// 密码不正确
				intflag = 12;
			}
		}
		else {
			
			// 用户不存在
			intflag = -1;
		}
				
		return intflag;			
	}
	
	/**
	 * 检查改用户是否存在
	 * @param loginName 登录用户名
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AdminUser findUserByLoginName(String loginName) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("loginName", loginName));
		
		List<AdminUser> list = getHibernateTemplate().findByCriteria(dc);
		
		if (list.size() != 0) {
			return (AdminUser)list.get(0);
		}
		
		return null;
	}

	
	public static void main(String[] args) {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		System.out.println(sf.format(date));
	}

}
