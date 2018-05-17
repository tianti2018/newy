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
@Table(name="xinghuoquan_record")
public class XingHuoQuanRecord implements Serializable {

	private Integer id;					//主键
	private Integer userId;				//给谁的星火券
	private Integer fromUserId;			//提供星火券的userId
	private Integer orderId;			//订单主键
	private Integer xinghuoquan;		//数量
	private Date createDate;			//创建时间
	private Integer status;				//状态 0未支付  1已获得  2 支出  3 等级不足流失 4 
	private Integer type;				//类型 0:无类型 1:自己订单返利 2:他人订单返利 3抽奖获得  4:购买兑换 11粉丝退款扣款
	private String memo;				//备注
	private Date updateDate;			//
	private String ordersBH;			//订单编号
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Integer getXinghuoquan() {
		return xinghuoquan;
	}
	public void setXinghuoquan(Integer xinghuoquan) {
		this.xinghuoquan = xinghuoquan;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getOrdersBH() {
		return ordersBH;
	}
	public void setOrdersBH(String ordersBH) {
		this.ordersBH = ordersBH;
	}
	
	
}
