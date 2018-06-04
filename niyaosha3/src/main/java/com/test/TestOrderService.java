package com.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zklc.weishangcheng.member.hibernate.persistent.Orders;
import com.zklc.weishangcheng.member.service.OrdersService;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestOrderService {

	@Autowired
	private OrdersService ordersService;
	
	@Test
	public void testOrderService(){
		Orders orders = ordersService.findById(539);
		ordersService.moneyPay(orders);
	}
}
