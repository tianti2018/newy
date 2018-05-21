package com.zklc.weishangcheng.member.hibernate.persistent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="order_jinhuo")
public class OrderJinHuo implements Serializable {
	
	private Integer id;					//主键
	private Integer ordersId;			//订单id
	
	private Integer dianpuId;			//店主店铺id
	private Integer fromDianpuId;		//来自店铺id
	private String ordersBH;			//订单编号
	private Double money;				//支付金额
	private Double shouyi;				//店主收益
	private Date createDate;			//记录生成时间
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getOrdersId() {
		return ordersId;
	}

	public Double getShouyi() {
		return shouyi;
	}

	public void setShouyi(Double shouyi) {
		this.shouyi = shouyi;
	}

	public void setOrdersId(Integer ordersId) {
		this.ordersId = ordersId;
	}
	public String getOrdersBH() {
		return ordersBH;
	}

	public void setOrdersBH(String ordersBH) {
		this.ordersBH = ordersBH;
	}
	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getDianpuId() {
		return dianpuId;
	}

	public void setDianpuId(Integer dianpuId) {
		this.dianpuId = dianpuId;
	}

	public Integer getFromDianpuId() {
		return fromDianpuId;
	}

	public void setFromDianpuId(Integer fromDianpuId) {
		this.fromDianpuId = fromDianpuId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
