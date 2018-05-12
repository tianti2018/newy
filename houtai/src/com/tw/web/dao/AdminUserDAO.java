package com.tw.web.dao;

import com.tw.web.hibernate.persistent.AdminUser;

public interface AdminUserDAO extends ICRUDBaseDAO {

	/**
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 */
	public int checkUserExistOrNo (String loginName, String password);
	
	public AdminUser findUserByLoginName (String loginName);
}
