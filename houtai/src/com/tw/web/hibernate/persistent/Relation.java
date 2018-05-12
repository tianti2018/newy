package com.tw.web.hibernate.persistent;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="relation")
public class Relation extends AbstractSymBasePO {
	
	private int relationId;
	private int userId;
	private int recommend;
	
	private BigDecimal userMoney;
	private int orderOne;
	private Date createDate;
	
	private User user;
	private User recommendUser;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "relationId", unique = true, nullable = false)
	public int getRelationId() {
		return relationId;
	}
	public void setRelationId(int relationId) {
		this.relationId = relationId;
	}
	
	@Column(name="userId", insertable = false, updatable = false)
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public BigDecimal getUserMoney() {
		return userMoney;
	}
	public void setUserMoney(BigDecimal userMoney) {
		this.userMoney = userMoney;
	}
	public int getOrderOne() {
		return orderOne;
	}
	public void setOrderOne(int orderOne) {
		this.orderOne = orderOne;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@ManyToOne
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name="recommend", insertable = false, updatable = false)
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	
	@ManyToOne
	@JoinColumn(name="recommend")
	public User getRecommendUser() {
		return recommendUser;
	}
	public void setRecommendUser(User recommendUser) {
		this.recommendUser = recommendUser;
	}
	
	
	
}
