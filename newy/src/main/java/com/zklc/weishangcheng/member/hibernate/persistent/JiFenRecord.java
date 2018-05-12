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
@Table(name="jifen_record")
public class JiFenRecord implements Serializable {

	private Integer id;						//主键
	private Integer userId;					//给谁的积分
	private Integer fromUserId;				//来自谁	非必须
	private Integer orderId;				//订单主键 非必须
	private Integer jifen;					//积分数量 必须
	private Date createDate;				//创建时间
	private Integer status;					//状态 0未获得  1已获得  2 支出   3:不可使用
	private Integer type;					//类型 0:无类型 1:自己订单返利 2:他人订单返利 3抽奖获得  4:购买兑换5:新用户关注(status=2,表示花费新用户积分)
	private Integer isEnable;				//积分是否可用 0 可用   1(or 无数据) 不可用
	private String memo;					//备注
	private Date updateDate;				//更新时间
	private String ordersBH;				//订单编号  非必须
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
	public Integer getJifen() {
		return jifen;
	}
	public void setJifen(Integer jifen) {
		this.jifen = jifen;
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
	
	public Integer getIsEnable() {
		return isEnable;
	}
	
	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}
	
	
}
