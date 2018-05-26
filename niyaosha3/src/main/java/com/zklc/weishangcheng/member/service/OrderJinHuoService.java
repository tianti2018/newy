package com.zklc.weishangcheng.member.service;

import java.util.List;

import com.zklc.framework.service.IBaseService;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderJinHuo;
import com.zklc.weishangcheng.member.hibernate.persistent.Orders;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.OrderVo;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.OrdersVo;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.UserVo;

public interface OrderJinHuoService extends IBaseService<OrderJinHuo, Integer> {
/**
 * 查询最后一次订单
 * @param userId 用户id
 * @return
 */
public OrderJinHuo findByUserId(Integer userId);


public OrderJinHuo findByOrderBH (String orderBH);
public OrderVo findByOrderVoBH (String orderBH);

/**
 * 获取个人订单信息
 * @param userId
 * @param orderNo ，0 下单未购买，1下单已购买
 * @return
 */
public List findPerOrder(Integer userId,String orderNo);



/**
 * 查询制定级别人员订单列表
 * @param userId 用户id
 * @param orderType 订单状态 （0:未支付 1:已支付 2：未发货 3:已发货 4：已完成 5：未完成）
 * @param orderLevel 订单级别 (all:查询1-2-3级人员订单，1：1级人员订单，2：2级，3级)
 * @return
 */
public List findLevelPeoOrder(Integer userId,String orderType,String orderLevel);

/**
 * 获取我的订单信息
 * @param userVo
 * @param type 订单类型
 * @return
 */
public List findMyOrderList(UserVo userVo,String type,String date1,String date2,Integer pageNum); 

/**
 * 更新订单信息
 * @param userId
 * @return
 */
public boolean updateOrdersMsg(Integer orderId);

public void timerUpdateOrderStatus();


/**
 * 查询人员销售额
 * @param userId 用户id

 * @return
 */
public List findCurrUserAllOrder(Integer userId);

/**
 * 
* 查询指定级别人员订单金额
 * @param userId 用户id
 * @param orderType 订单状态 （0:未支付 1:已支付 2：未发货 3:已发货 4：已完成 5：未完成）
 * @param orderLevel 订单级别 (all:查询1-2-3级人员订单，1：1级人员订单，2：2级，3级)
 * @return
 */
public List findLevelUserOrderMoney(Integer userId,String orderType,String orderLevel);

public int moneyPay(OrderJinHuo order,String openid,Users user);



/**
 * 查看是否有订单
 * @param userId
 */
public Integer findOrderCountByUserId(Integer userId);

public void saveAndCFh(OrderJinHuo order, Users user);

/**
 * 查询这一期秒杀卖出的产品数量
 * @param qi
 * @return
 */
public Integer findSellerNumByQi(Integer qi);

OrderJinHuo findbyOrdersId(Integer ordersId);

/**
 * 查询用户消费的总金额
 */
public Integer findTotalMoneyByUserId(Integer userId);


public OrderJinHuo findOrderByOrderBH(String out_trade_no);


public List<OrdersVo> findMyDianpuOrderList(UserVo userVo, String orderType, String date1, String date2, Integer pageNum);


public List<OrdersVo> findMyDianpuOrderList_jinhuo(UserVo userVo, String date2, String date1, String date22,
		Integer pageNum);


public Long findOrderNum(Usery usery, String lingDianDateStringByDays, String lingDianDateStringByDays2);


public void deleteByOrders(List<Orders> deleteList);
}