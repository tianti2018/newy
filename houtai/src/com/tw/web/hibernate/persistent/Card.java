package com.tw.web.hibernate.persistent;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="card")
public class Card extends AbstractSymBasePO {
	
	private Integer cardId;
	private String cardName;	
	
	private String cardNo;
	private String cardPassword;
	private String cardStatus; // 使用状态 0 未使用   1 已使用
	private Double cardMoney;  //卡面值
	private Date createDate; //创建日期
	private String cardType; // 1: 支付宝购买  2：管理员充值  3: 首次购买 4： 复购   5:10000面值的卡 (服务中心卡)
	
	private Integer userId;
	
	private Integer formUserId;
	
	private String memo;
	
	private User user;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "cardId", unique = true, nullable = false)
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/*@ManyToOne
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}*/
	
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCardPassword() {
		return cardPassword;
	}
	public void setCardPassword(String cardPassword) {
		this.cardPassword = cardPassword;
	}
	public String getCardStatus() {
		return cardStatus;
	}
	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}
	public Double getCardMoney() {
		return cardMoney;
	}
	public void setCardMoney(Double cardMoney) {
		this.cardMoney = cardMoney;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name="userId", insertable = false, updatable = false)
	public Integer getUserId() {
		return userId;
	}
	public Integer getFormUserId() {
		return formUserId;
	}
	public void setFormUserId(Integer formUserId) {
		this.formUserId = formUserId;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
}
