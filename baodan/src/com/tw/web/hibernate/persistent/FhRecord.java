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
//@Table(name="fhrecord")
public class FhRecord extends AbstractSymBasePO {
	
	private int fhId;
	private int userId;
	
	private Double fhmoney;

	private Date createDate;
	
	private User user;
	
	private String flag;
	private String memo;
	private Double leaveNode;
	private Double alreadyNode;
	private String fhType;
	private Integer uoId;
	
	private String dingqiFlag; //注入15的标志
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "fhId", unique = true, nullable = false)
	public int getFhId() {
		return fhId;
	}
	public void setFhId(int fhId) {
		this.fhId = fhId;
	}
	@Column(name="userId", insertable = false, updatable = false)
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Double getFhmoney() {
		return fhmoney;
	}
	public void setFhmoney(Double fhmoney) {
		this.fhmoney = fhmoney;
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
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Double getLeaveNode() {
		return leaveNode;
	}
	public void setLeaveNode(Double leaveNode) {
		this.leaveNode = leaveNode;
	}
	public Double getAlreadyNode() {
		return alreadyNode;
	}
	public void setAlreadyNode(Double alreadyNode) {
		this.alreadyNode = alreadyNode;
	}
	public String getFhType() {
		return fhType;
	}
	public void setFhType(String fhType) {
		this.fhType = fhType;
	}
	public Integer getUoId() {
		return uoId;
	}
	public void setUoId(Integer uoId) {
		this.uoId = uoId;
	}
	public String getDingqiFlag() {
		return dingqiFlag;
	}
	public void setDingqiFlag(String dingqiFlag) {
		this.dingqiFlag = dingqiFlag;
	}
	
}
