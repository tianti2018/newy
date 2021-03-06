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
@Table(name="yongjin")
public class Yongjin implements Serializable {
	
	private int id;//主键id
	private Integer orderId;
	private Integer fromUserId;
	private Integer toUserId;
	private Double money;
	private Integer status;//0 未支付 1 已支付
	private Integer level;//toUserId是当前人的关系
	private Date createDate;
	private Date updateTime;//更新时间
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the orderId
	 */
	public Integer getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the fromUserId
	 */
	public Integer getFromUserId() {
		return fromUserId;
	}
	/**
	 * @param fromUserId the fromUserId to set
	 */
	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}
	/**
	 * @return the toUserId
	 */
	public Integer getToUserId() {
		return toUserId;
	}
	/**
	 * @param toUserId the toUserId to set
	 */
	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}
	/**
	 * @return the money
	 */
	public Double getMoney() {
		return money;
	}
	/**
	 * @param money the money to set
	 */
	public void setMoney(Double money) {
		this.money = money;
	}
	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
