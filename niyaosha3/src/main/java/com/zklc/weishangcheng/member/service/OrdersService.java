package com.zklc.weishangcheng.member.service;

import com.zklc.framework.service.IBaseService;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderJinHuo;
import com.zklc.weishangcheng.member.hibernate.persistent.Orders;
import com.zklc.weishangcheng.member.hibernate.persistent.ShouYiForUser;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;

public interface OrdersService extends IBaseService<Orders, Integer> {

	Orders findOrderByOrderBH(String orderNo);

	void moneyPay(Orders order);

	void saveAndCFh(Orders order, Users user);
	
	Integer findNumByTypeAndUserId(Integer type,Integer userId);

	
	Integer findSellerNumByQi(Integer qi);
	

	Boolean jifenPay(Orders order, Users user);

	void huiyuanPay(Orders order, Users user);
	Integer cancelOrder(Orders order);
	void updateOrdeToSend();
	Integer checkXingHuoQuanOrder(Users user);
	
	/**
	 * 统计用户需身份证指定产品是否购买过
	 * @param userId
	 * @param prodId
	 * @return
	 */
	public Integer countUserIdCardProduct(Integer userId, Integer prodId,String idCard);

	void createOrder(Orders order, OrderJinHuo orderJinHuo, ShouYiForUser dianzhuShouyiRecord,
			ShouYiForUser shangjiShouyiRecord, ShouYiForUser xiaohaoRecord);

	void timerUpdateOrderStatus0and3();
	void timerUpdateOrderStatus3and6();
	
}
