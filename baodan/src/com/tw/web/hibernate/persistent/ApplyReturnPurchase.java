package com.tw.web.hibernate.persistent;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@SuppressWarnings("serial")
//@Entity
//@Table(name="apply_return_purchase")
public class ApplyReturnPurchase extends AbstractSymBasePO {

	private Integer arpId;
	private String kuaiDiNum;
	private String orderBH;
	private Integer returnNum;
	private Integer orderId;
	/**
	 * 退货人姓名
	 */
	private String userName;
	/**
	 * 退货人联系电话
	 */
	private String phone;
	/**
	 * 申请退货时间
	 */
	private Date applyTime;
	/**
	 * 处理时间
	 */
	private Date dealTime;
	/**
	 * 处理结果
	 * 0未成功退货,1成功退货
	 */
	private Integer status;
	
	/**
	 * 商品类型
	 */
	private String orderType;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "arpId", unique = true, nullable = false)
	public Integer getArpId() {
		return arpId;
	}
	public void setArpId(Integer arpId) {
		this.arpId = arpId;
	}
	
	public String getKuaiDiNum() {
		return kuaiDiNum;
	}
	public void setKuaiDiNum(String kuaiDiNum) {
		this.kuaiDiNum = kuaiDiNum;
	}
	public String getOrderBH() {
		return orderBH;
	}
	public void setOrderBH(String orderBH) {
		this.orderBH = orderBH;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getReturnNum() {
		return returnNum;
	}
	public void setReturnNum(Integer returnNum) {
		this.returnNum = returnNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public Date getDealTime() {
		return dealTime;
	}
	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
}
