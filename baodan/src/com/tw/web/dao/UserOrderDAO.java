package com.tw.web.dao;

import java.util.List;

import com.tw.web.hibernate.persistent.UserOrder;

public interface UserOrderDAO extends ICRUDBaseDAO {
	public UserOrder findUserOrderByAppcenterIdAndorderId(String flagorg, Integer orderOne);
	public UserOrder findUserOrderByUserId(Integer userId);
	public List<UserOrder> findByHql(String hql);
}
