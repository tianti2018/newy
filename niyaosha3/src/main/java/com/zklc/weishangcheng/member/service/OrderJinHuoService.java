package com.zklc.weishangcheng.member.service;

import java.util.List;

import com.zklc.framework.service.IBaseService;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderJinHuo;
import com.zklc.weishangcheng.member.hibernate.persistent.Orders;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.OrdersVo;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.UserVo;

public interface OrderJinHuoService extends IBaseService<OrderJinHuo, Integer> {


/**
 * 获取我的订单信息
 * @param userVo
 * @param type 订单类型
 * @return
 */
public List findMyOrderList(UserVo userVo,String type,String date1,String date2,Integer pageNum); 


public void timerUpdateOrderStatus();


public int moneyPay(OrderJinHuo order,String openid,Users user);


public void saveAndCFh(OrderJinHuo order, Users user);

/**
 * 查询这一期秒杀卖出的产品数量
 * @param qi
 * @return
 */
public Integer findSellerNumByQi(Integer qi);

OrderJinHuo findbyOrdersId(Integer ordersId);


public OrderJinHuo findOrderByOrderBH(String out_trade_no);


public List<OrdersVo> findMyDianpuOrderList(UserVo userVo, String orderType, String date1, String date2, Integer pageNum);


public List<OrdersVo> findMyDianpuOrderList_jinhuo(UserVo userVo, String date2, String date1, String date22,
		Integer pageNum);


public Long findOrderNum(Usery usery, String lingDianDateStringByDays, String lingDianDateStringByDays2);


public void deleteByOrders(List<Orders> deleteList);
}