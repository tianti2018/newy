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
@Table(name="sys_problem")
public class Problem implements Serializable {
	
	private Integer id;
	private String userId;
	private String peoId;//与谁发生的问题
	private String relation;//跟你的上几级或下几级发生的问题
	private String describez;//问题描述
	private String type;//问题类型
	private Integer status;//0:未解决 1：以解决 2：问题描述不清楚
	private String method;//解决方式
	private Date createDate;
	private Date endDate;//解决时间
	private Integer createUserId;
	private String phone;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	
	
	
	public void setId(Integer id) {
		this.id = id;
	}

	
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}



	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}



	/**
	 * @return the peoId
	 */
	public String getPeoId() {
		return peoId;
	}



	/**
	 * @param peoId the peoId to set
	 */
	public void setPeoId(String peoId) {
		this.peoId = peoId;
	}



	/**
	 * @return the relation
	 */
	public String getRelation() {
		return relation;
	}



	/**
	 * @param relation the relation to set
	 */
	public void setRelation(String relation) {
		this.relation = relation;
	}



	/**
	 * @return the describez
	 */
	public String getDescribez() {
		return describez;
	}

	/**
	 * @param describez the describez to set
	 */
	public void setDescribez(String describez) {
		this.describez = describez;
	}

	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getMethod() {
		return method;
	}
	
	public void setMethod(String method) {
		this.method = method;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



	/**
	 * @return the createUserId
	 */
	public Integer getCreateUserId() {
		return createUserId;
	}



	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}



	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}



	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
