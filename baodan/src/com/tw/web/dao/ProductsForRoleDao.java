package com.tw.web.dao;

import java.util.List;

public interface ProductsForRoleDao extends ICRUDBaseDAO {

	void savePFR(int roleId, List<Integer> productsIds);

}
