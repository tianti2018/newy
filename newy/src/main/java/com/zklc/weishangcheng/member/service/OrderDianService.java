package com.zklc.weishangcheng.member.service;

import com.zklc.framework.service.IBaseService;
import com.zklc.weishangcheng.member.hibernate.persistent.JifenUser;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderDian;

public interface OrderDianService extends IBaseService<OrderDian, Integer> {

	OrderDian findOrderByOrderBH(String out_trade_no);

	void moneyPay(OrderDian order, JifenUser user);

	/**
	 * 创建订单并且创建红包条件
	 * @param order
	 * @param user
	 */
	void saveAndCFh(OrderDian order, JifenUser user);

	
}
