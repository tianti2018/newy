package com.zklc.weishangcheng.member.hibernate.persistent.vo;

import com.zklc.weishangcheng.member.hibernate.persistent.OrderAddress;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;

public class UserVo {

	private Users user;
	private Usery usery;
	private OrderAddress orderAddress;
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Usery getUsery() {
		return usery;
	}
	public void setUsery(Usery usery) {
		this.usery = usery;
	}
	public OrderAddress getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(OrderAddress orderAddress) {
		this.orderAddress = orderAddress;
	}
	
	
}
