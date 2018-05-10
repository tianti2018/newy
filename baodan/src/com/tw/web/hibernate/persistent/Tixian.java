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
//@Table(name="tixian")
public class Tixian extends AbstractSymBasePO {
	
	private int txId;
	private int userId;
	
	private Double tixianMoney;

	private Date appDate;
	private Date tixianDate;
	private User user;
	private String phone;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	private String type;
	
	private String bankName;
	private String openBankAddr;
	private String account;
	private String accountHolder;
	private String weinxinNo;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "txId", unique = true, nullable = false)
	public int getTxId() {
		return txId;
	}
	public void setTxId(int txId) {
		this.txId = txId;
	}
	@Column(name="userId", insertable = false, updatable = false)
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Double getTixianMoney() {
		return tixianMoney;
	}
	public void setTixianMoney(Double tixianMoney) {
		this.tixianMoney = tixianMoney;
	}
	public Date getAppDate() {
		return appDate;
	}
	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@ManyToOne
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getTixianDate() {
		return tixianDate;
	}
	public void setTixianDate(Date tixianDate) {
		this.tixianDate = tixianDate;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getOpenBankAddr() {
		return openBankAddr;
	}
	public void setOpenBankAddr(String openBankAddr) {
		this.openBankAddr = openBankAddr;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	public String getWeinxinNo() {
		return weinxinNo;
	}
	public void setWeinxinNo(String weinxinNo) {
		this.weinxinNo = weinxinNo;
	}
	
}
