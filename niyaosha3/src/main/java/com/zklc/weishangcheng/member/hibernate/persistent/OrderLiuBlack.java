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
@Table(name="liublack")
public class OrderLiuBlack implements Serializable {
	
	private Integer bid;
	private String phone;//手机号
	private String describe;//禁止原因
	private Date createDate;
	private Integer isStart;//是否启用 0 不启用  1启用

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "bid", unique = true, nullable = false)
	public Integer getBid() {
		return bid;
	}
	/**
	 * @param bid the bid to set
	 */
	public void setBid(Integer bid) {
		this.bid = bid;
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
	/**
	 * @return the describe
	 */
	public String getDescribe() {
		return describe;
	}
	/**
	 * @param describe the describe to set
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
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
	 * @return the isStart
	 */
	public Integer getIsStart() {
		return isStart;
	}
	/**
	 * @param isStart the isStart to set
	 */
	public void setIsStart(Integer isStart) {
		this.isStart = isStart;
	}

	
}
