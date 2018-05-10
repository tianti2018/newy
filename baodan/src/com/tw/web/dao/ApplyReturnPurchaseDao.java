package com.tw.web.dao;

import java.util.List;

import com.tw.web.hibernate.persistent.ApplyReturnPurchase;

public interface ApplyReturnPurchaseDao extends ICRUDBaseDAO {

	List<ApplyReturnPurchase> findByHql(String hql);
}
