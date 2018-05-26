package com.zklc.weishangcheng.member.service;

import com.zklc.framework.service.IBaseService;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderJinHuo;
import com.zklc.weishangcheng.member.hibernate.persistent.Orders;
import com.zklc.weishangcheng.member.hibernate.persistent.Product;
import com.zklc.weishangcheng.member.hibernate.persistent.ShouYiForUser;

public interface OrdersService extends IBaseService<Orders, Integer> {

	Orders findOrderByOrderBH(String orderNo);

	void moneyPay(Orders order);

	void saveAndCFh(Orders order, Users user);
  
	void saveMianMoOrder(Orders order, Users user,Product buyProd);
	
	Integer findNumByTypeAndUserId(Integer type,Integer userId);

	
	Integer findSellerNumByQi(Integer qi);
	
	Double getRebateAmt(Product buyProd,Integer size,Integer level);

	/**
	 * 保存积分购买订单
	 * @param order 订单数据
	 * @param user 下单用户
	 * @param prod 购买产品信息
	 * @param xiaohaoJifen  消耗积分类型 0：其他积分  1：推荐用户积分
	 * @return
	 */
	Boolean saveJifenOrder(Orders order, Users user, Product prod,Integer xiaohaoJifen);

	Boolean jifenPay(Orders order, Users user);

	Boolean saveHuiYuanOrder(Orders order, Users user, Product prod);

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

	void timerUpdateOrderStatus();

}
