package com.zklc.weishangcheng.member.service;

import java.util.List;

import com.zklc.framework.service.IBaseService;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderAddress;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.OrderAddressVO;

public interface OrderAddressService extends IBaseService<OrderAddress, Integer> {
/**
 * 设置默认收货地址
 * @param aid
 *  @param userId
 */
public void defaultAddr(Integer aid,Integer userId);

public OrderAddressVO findOrderAddressByUserId(Integer userId);

public List findOrderAddressListByid(Integer userId);
public List<OrderAddress> findByUserId(Integer userId);
public OrderAddressVO  getAddressVoById(Integer id);
}  