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
@Table(name="hongbao_lun")
public class HongBaoLun implements Serializable {

	private Long hongBaoId;    // 红包表主键  
	private String billNo;     // 红包订单号  
	private String openid;     // 领取红包的用户ID  
	private Integer amount;    // 领取红包金额 单位分  
	private Date addTime;      // 添加时间  
	private Integer result;    // 领取红包结果 0失败 1成功 2锁定  
	private String remark;	   // 备注  用于保存微信返回的json 
	private String beizhu;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "hongBaoId")
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
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	
	
}
