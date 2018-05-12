package com.tw.web.hibernate.persistent;

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
@Table(name="repeatOne")
public class Repeat extends AbstractSymBasePO {
	
	private int repeatId;
	private int userId;	
	private Double repeatMoney;

	private Date createDate;
	
	private User user;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "repeatId", unique = true, nullable = false)
	public int getRepeatId() {
		return repeatId;
	}
	public void setRepeatId(int repeatId) {
		this.repeatId = repeatId;
	}
	
	
	@Column(name="userId", insertable = false, updatable = false)
	public int getUserId() {
		return userId;
	}
	public Double getRepeatMoney() {
		return repeatMoney;
	}
	public void setRepeatMoney(Double repeatMoney) {
		this.repeatMoney = repeatMoney;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
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
	
	
}
