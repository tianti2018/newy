package com.tw.web.dao;

import java.util.List;

import com.tw.web.hibernate.persistent.RoleFunction;

public interface RoleFunctionDAO extends ICRUDBaseDAO {
	
	public List<RoleFunction> findFunctionByRoleId(int roleId);
	
}
