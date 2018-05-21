package com.zklc.weishangcheng.member.dao;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderJinHuo;
import com.zklc.framework.dao.EntityDao;
public interface OrderDao extends EntityDao<OrderJinHuo, Integer> {
	public OrderJinHuo findOrderByOrderBH(String ordersBH);
}