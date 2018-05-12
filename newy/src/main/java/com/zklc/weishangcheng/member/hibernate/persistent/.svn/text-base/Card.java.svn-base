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
@Table(name="cardy")
public class Card implements Serializable {
	
	private Integer cardid;
	private String cardname;	
	
	private String cardno;
	private String cardpassword;
	private String cardstatus; // 使用状态 0 未使用   1 已使用
	private Double cardmoney;  //卡面值
	private String cardtype; // 1: 支付宝购买  2：管理员充值  3: 首次购买 4： 复购
	
	private Integer userid;
	
	private Integer formuserid;
	
	private String memo;
	
	private Date createDate;
	
	//private User user;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "cardId", unique = true, nullable = false)
	public Integer getCardid() {
		return cardid;
	}
	public void setCardid(Integer cardid) {
		this.cardid = cardid;
	}
	
	@Column(name = "cardName")
	public String getCardname() {
		return cardname;
	}
	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
	@Column(name = "cardNo")
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	@Column(name = "cardPassword")
	public String getCardpassword() {
		return cardpassword;
	}
	public void setCardpassword(String cardpassword) {
		this.cardpassword = cardpassword;
	}
	@Column(name = "cardStatus")
	public String getCardstatus() {
		return cardstatus;
	}
	public void setCardstatus(String cardstatus) {
		this.cardstatus = cardstatus;
	}
	@Column(name = "cardMoney")
	public Double getCardmoney() {
		return cardmoney;
	}
	public void setCardmoney(Double cardmoney) {
		this.cardmoney = cardmoney;
	}
	@Column(name = "cardType")
	public String getCardtype() {
		return cardtype;
	}
	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	@Column(name = "userId")
	public Integer getUserid() {
		return userid;
	}
	@Column(name = "formuserid")
	public Integer getFormuserid() {
		return formuserid;
	}
	public void setFormuserid(Integer formuserid) {
		this.formuserid = formuserid;
	}
	@Column(name = "memo")
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Column(name = "createDate")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
