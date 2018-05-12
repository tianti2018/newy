package com.zklc.weishangcheng.member.dao.impl;
import org.springframework.stereotype.Repository;

import com.zklc.framework.dao.impl.EntityDaoImpl;
import com.zklc.weishangcheng.member.dao.OrderAddressDao;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderAddress;
@Repository
public class OrderAddressDaoImpl extends EntityDaoImpl <OrderAddress, Integer> implements OrderAddressDao {

}
