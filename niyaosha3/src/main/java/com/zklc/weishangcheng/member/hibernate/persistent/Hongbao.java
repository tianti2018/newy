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
@Table(name="hongbaoy")
public class Hongbao implements Serializable {
	
	private Long hongBaoId;    // 红包表主键  
	private String billNo;     // 红包订单号  
	private String openid;     // 领取红包的用户ID  
	private Integer amount;    // 领取红包金额 单位分  
	private Date addTime;      // 添加时间  
	private Integer result;    // 领取红包结果 0失败 1成功 2锁定  
	private String remark;	   // 备注  用于保存微信返回的json 
	private String statusz;		//红包状态(SENDING:发放中;SENT:已发放待领取;FAILED:发放失败;RECEIVED:已领取;REFUND:已退款)
	private Integer bufaNum;	//红包补发次数
	private Integer fhrecordId;		//分红表的id
	private String wxBillNo;	//红包订单的微信单号
	private Integer userId;		//领红包的人
	private Integer fromUserId;	//领取谁的红包
	private Date bufaTime;		//补发时间
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "hongBaoId", unique = true, nullable = false)
	public Long getHongBaoId() {
		return hongBaoId;
	}
	public void setHongBaoId(Long hongBaoId) {
		this.hongBaoId = hongBaoId;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getBufaNum() {
		return bufaNum;
	}
	public void setBufaNum(Integer bufaNum) {
		this.bufaNum = bufaNum;
	}
	public String getStatusz() {
		return statusz;
	}
	public void setStatusz(String statusz) {
		this.statusz = statusz;
	}
	public Integer getFhrecordId() {
		return fhrecordId;
	}
	public void setFhrecordId(Integer fhrecordId) {
		this.fhrecordId = fhrecordId;
	}
	
	public String getWxBillNo() {
		return wxBillNo;
	}
	public void setWxBillNo(String wxBillNo) {
		this.wxBillNo = wxBillNo;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}
	public Date getBufaTime() {
		return bufaTime;
	}
	public void setBufaTime(Date bufaTime) {
		this.bufaTime = bufaTime;
	}    
	
}
