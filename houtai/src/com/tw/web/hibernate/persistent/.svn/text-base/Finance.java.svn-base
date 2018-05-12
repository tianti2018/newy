package com.tw.web.hibernate.persistent;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="finance")
public class Finance extends AbstractSymBasePO {
	
	private int accountId;
	private String bankName;
	private String openBankAddr;
	private String account;
	private String accountHolder;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "accountId", unique = true, nullable = false)
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
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
	public String getOpenBankAddr() {
		return openBankAddr;
	}
	public void setOpenBankAddr(String openBankAddr) {
		this.openBankAddr = openBankAddr;
	}
	
}
