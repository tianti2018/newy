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

//@SuppressWarnings("serial")
//@Entity
//@Table(name="record")
public class Record extends AbstractSymBasePO {
	
	private int recordId;
	private int loginUserId;
	
	private Double todyMoney;

	private Date createDate;
	
	private User loginUser;
	
	private String memo;

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "recordId", unique = true, nullable = false)
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	
	@Column(name="loginUserId", insertable = false, updatable = false)
	public int getLoginUserId() {
		return loginUserId;
	}
	public void setLoginUserId(int loginUserId) {
		this.loginUserId = loginUserId;
	}
	public Double getTodyMoney() {
		return todyMoney;
	}
	public void setTodyMoney(Double todyMoney) {
		this.todyMoney = todyMoney;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@ManyToOne
	@JoinColumn(name="loginUserId")
	public User getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(User loginUser) {
		this.loginUser = loginUser;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
			
}
