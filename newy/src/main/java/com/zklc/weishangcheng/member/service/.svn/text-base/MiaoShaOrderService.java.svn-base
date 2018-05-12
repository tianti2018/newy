package com.zklc.weishangcheng.member.service;

import com.zklc.framework.service.IBaseService;
import com.zklc.weishangcheng.member.hibernate.persistent.JifenUser;
import com.zklc.weishangcheng.member.hibernate.persistent.MiaoShaOrder;
import com.zklc.weishangcheng.member.hibernate.persistent.Product;

public interface MiaoShaOrderService extends IBaseService<MiaoShaOrder, Integer> {

	MiaoShaOrder findOrderByOrderBH(String orderNo);

	void moneyPay(MiaoShaOrder order, JifenUser user);

	void saveAndCFh(MiaoShaOrder order, JifenUser user);
  
	void saveMianMoOrder(MiaoShaOrder order, JifenUser user,Product buyProd);
	
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
	Boolean saveJifenOrder(MiaoShaOrder order, JifenUser user, Product prod,Integer xiaohaoJifen);

	Boolean jifenPay(MiaoShaOrder order, JifenUser user);

	Boolean saveHuiYuanOrder(MiaoShaOrder order, JifenUser user, Product prod);

	void huiyuanPay(MiaoShaOrder order, JifenUser user);
	Integer cancelOrder(MiaoShaOrder order);
	void updateOrdeToSend();
	Integer checkXingHuoQuanOrder(JifenUser user);
	
	/**
	 * 统计用户需身份证指定产品是否购买过
	 * @param userId
	 * @param prodId
	 * @return
	 */
	public Integer countUserIdCardProduct(Integer userId, Integer prodId,String idCard);

}
