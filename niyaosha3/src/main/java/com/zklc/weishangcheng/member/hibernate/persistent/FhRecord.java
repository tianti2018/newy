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
@Table(name="fhrecordy")
public class FhRecord implements Serializable {
	
	private int fhId;					//主键
	private int userId;					//表示给谁的红包
	
	private Double fhmoney;				//红包金额

	private Date createDate;			//创建时间
	
	
	private String flag;				//0表示未支付,1表示已支付,红包待领取,2表示红包已领取 3表示已退款  4表示红包流失
	private String memo;				//注释
	private Double leaveNode;			//
	private Double alreadyNode;			//
	private String fhType;				//固定为"2"
	private Integer uoId;				//
	private String dingqiFlag; //注入15的标志
	
	private Integer fromUserId;			//下单人id
	
	private Integer ordersId;			//订单主键
	private Date updaDate;				//领取红包时间
	
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
	@Column(name = "flag")
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Column(name = "memo")
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Column(name = "leaveNode")
	public Double getLeaveNode() {
		return leaveNode;
	}
	public void setLeaveNode(Double leaveNode) {
		this.leaveNode = leaveNode;
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
	
}
