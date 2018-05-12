package com.zklc.weishangcheng.member.service;

import java.util.List;

import com.zklc.framework.service.IBaseService;
import com.zklc.weishangcheng.member.hibernate.persistent.JifenUser;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderLiu;

public interface OrderLiuService extends IBaseService<OrderLiu, Integer> {
	public OrderLiu findOrderByOrderBH(String ordersBH);
	
	/**
	 * 购买流量
	 * @param order
	 * @param openid
	 * @param user
	 * @return
	 */
	public int liuliangPay(OrderLiu order,String openid,JifenUser user);
	
	
	public String saveOrder(JifenUser user,OrderLiu order);
	
	/**
	 * 查询流量数据是否正确
	 * @param busType 运营商编码
	 * @param num 流量数额
	 * @param money
	 * @return
	 */
	public List checkLiuDateIsTure(String busType,String num,String money);
	
}