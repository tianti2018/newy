package com.zklc.weishangcheng.member.service;

import java.util.Date;
import java.util.List;

import com.zklc.framework.service.IBaseService;
import com.zklc.weishangcheng.member.hibernate.persistent.JifenUser;
import com.zklc.weishangcheng.member.hibernate.persistent.Order;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderLiu;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.OrderVo;

public interface OrderService extends IBaseService<Order, Integer> {
/**
 * 查询最后一次订单
 * @param userId 用户id
 * @return
 */
public Order findByUserId(Integer userId);


public Order findByOrderBH (String orderBH);
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
 * @param userId
 * @param type 订单类型
 * @return
 */
public List findMyOrderList(Integer userId,String type,String date1,String date2,Integer pageNum); 

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

public int moneyPay(Order order,String openid,JifenUser user);



/**
 * 查看是否有订单
 * @param userId
 */
public Integer findOrderCountByUserId(Integer userId);

public void saveAndCFh(Order order, JifenUser user);

public OrderLiu findOrderLiuOrderBH(String orderBh);

/**
 * 查询这一期秒杀卖出的产品数量
 * @param qi
 * @return
 */
public Integer findSellerNumByQi(Integer qi);


/**
 * 查询用户消费的总金额
 */
public Integer findTotalMoneyByUserId(Integer userId);
}