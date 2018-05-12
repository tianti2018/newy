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
@Table(name="fhrecord_dian")
public class FhRecordDian implements Serializable{

	private int fhId;
	private int userId;
	
	private Double fhmoney;

	private Date createDate;
	
	
	private Integer flag;
	private String memo;
	private Integer level;
	private Double alreadyNode;
	private String fhType;
	private Integer uoId;
	private String dingqiFlag; //注入15的标志
	
	private Integer fromUserId;
	
	private Integer ordersId;
	private Date updaDate;
	private Integer dianzhuLevel;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "fhId", unique = true, nullable = false)
	public int getFhId() {
		return fhId;
	}
	public void setFhId(int fhId) {
		this.fhId = fhId;
	}
	@Column(name = "userId")
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Column(name = "fhmoney")
	public Double getFhmoney() {
		return fhmoney;
	}
	public void setFhmoney(Double fhmoney) {
		this.fhmoney = fhmoney;
	}
	@Column(name = "createDate")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name = "memo")
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Column(name = "alreadyNode")
	public Double getAlreadyNode() {
		return alreadyNode;
	}
	public void setAlreadyNode(Double alreadyNode) {
		this.alreadyNode = alreadyNode;
	}
	@Column(name = "fhType")
	public String getFhType() {
		return fhType;
	}
	public void setFhType(String fhType) {
		this.fhType = fhType;
	}
	@Column(name = "uoId")
	public Integer getUoId() {
		return uoId;
	}
	public void setUoId(Integer uoId) {
		this.uoId = uoId;
	}
	@Column(name = "dingqiFlag")
	public String getDingqiFlag() {
		return dingqiFlag;
	}
	public void setDingqiFlag(String dingqiFlag) {
		this.dingqiFlag = dingqiFlag;
	}
	public Integer getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}
	public Integer getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(Integer ordersId) {
		this.ordersId = ordersId;
	}
	public Date getUpdaDate() {
		return updaDate;
	}
	public void setUpdaDate(Date updaDate) {
		this.updaDate = updaDate;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Integer getDianzhuLevel() {
		return dianzhuLevel;
	}
	public void setDianzhuLevel(Integer dianzhuLevel) {
		this.dianzhuLevel = dianzhuLevel;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
}
