package com.zklc.weishangcheng.member.hibernate.persistent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 退款明细
 * @author yang
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="refund_record")
public class RefundRecord  implements Serializable{
	private Integer id;
	  private Integer userId;
	  private Integer orderId;
	  private String orderBH;
	  private Date createDate;
	  private Double refundAmount;
	  private String partnerTradeNo;
	  private String returnXML;
	  private Integer result;
	  private String wxOpenId;
	  
	  public String getPartnerTradeNo() {
		return partnerTradeNo;
	}
	public void setPartnerTradeNo(String partnerTradeNo) {
		this.partnerTradeNo = partnerTradeNo;
	}
	@Column(name="returnXML",length =1000)
	public String getReturnXML() {
		return returnXML;
	}
	public void setReturnXML(String returnXML) {
		this.returnXML = returnXML;
	}
	
	  
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
  public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "userId")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name = "orderId")
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	@Column(name = "orderBH")
	public String getOrderBH() {
		return orderBH;
	}
	public void setOrderBH(String orderBH) {
		this.orderBH = orderBH;
	}
	@Column(name = "createDate")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name = "refundAmount")
	public Double getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(Double refundAmount) {
		this.refundAmount = refundAmount;
	}
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public String getWxOpenId() {
		return wxOpenId;
	}
	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
	}

  
}
