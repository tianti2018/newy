package com.tw.web.hibernate.persistent;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;

import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="user_order")
public class UserOrder implements Serializable {
	
	private Integer uoId;
	private Integer cardId;	
	private Integer userId;
	private BigInteger orderOne;
	private Integer parentUoId;
	private Date createDate; //创建日期
	private String status;
	
	private Integer appcenterId; //报单中心id
	private String flagorg;
	private Integer ordersid;
	/**
	 * 退货申请状态
	 * 0无退货申请,1退货申请中,2退货成功
	 * 默认0
	 */
	private Integer return_purchase;
	/**
	 * 是否提供抽奖资格状态
	 * 0未提供,1提供,2退货,3取消退货
	 * 默认0
	 */
	private Integer if_offer_chjzg;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "uoId", unique = true, nullable = false)
	public Integer getUoId() {
		return uoId;
	}
	@Column(name = "createDate")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public void setUoId(Integer uoId) {
		this.uoId = uoId;
	}
	@Column(name = "userId")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name = "orderOne")
	public BigInteger getOrderOne() {
		return orderOne;
	}
	public void setOrderOne(BigInteger orderOne) {
		this.orderOne = orderOne;
	}
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "cardId")
	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	@Column(name = "parentUoId")
	public Integer getParentUoId() {
		return parentUoId;
	}

	public void setParentUoId(Integer parentUoId) {
		this.parentUoId = parentUoId;
	}
	@Column(name = "appcenterId")
	public Integer getAppcenterId() {
		return appcenterId;
	}

	public void setAppcenterId(Integer appcenterId) {
		this.appcenterId = appcenterId;
	}
	@Column(name = "flagorg")
	public String getFlagorg() {
		return flagorg;
	}

	public void setFlagorg(String flagorg) {
		this.flagorg = flagorg;
	}
	public Integer getOrdersid() {
		return ordersid;
	}
	@Column(name = "ordersid")
	public void setOrdersid(Integer ordersid) {
		this.ordersid = ordersid;
	}
	@Column(nullable=true,columnDefinition="INT default 0")
	public Integer getReturn_purchase() {
		return return_purchase;
	}
	public void setReturn_purchase(Integer return_purchase) {
		this.return_purchase = return_purchase;
	}
	@Column(nullable=true,columnDefinition="INT default 0")
	public Integer getIf_offer_chjzg() {
		return if_offer_chjzg;
	}
	public void setIf_offer_chjzg(Integer if_offer_chjzg) {
		this.if_offer_chjzg = if_offer_chjzg;
	}
	
}
