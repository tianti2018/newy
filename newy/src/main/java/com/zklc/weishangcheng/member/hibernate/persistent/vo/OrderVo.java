package com.zklc.weishangcheng.member.hibernate.persistent.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class OrderVo {
	
	private Integer ordersid;
	private String address;
	private String mobile;
	private Integer orderStatus;
	private String tel;
	private String tousername;
	private Integer userid;
	private String zipcode;
	private String pname;
	private Date createdate;
	private String kuaidiname;
	private String kuaidino;
	private Integer cardid;
	private String ordersbh;
	private String ordertype;
	private Double money;//支付金额
	private Double score;//支付积分
	private Integer orderid;
	
	public Integer getOrdersid() {
		return ordersid;
	}
	public void setOrdersid(Integer ordersid) {
		this.ordersid = ordersid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public Integer getOrderStatus() {
		return orderStatus;
	}
	
	public String getTel() {
		return tel;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTousername() {
		return tousername;
	}
	public void setTousername(String tousername) {
		this.tousername = tousername;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getKuaidiname() {
		return kuaidiname;
	}
	public void setKuaidiname(String kuaidiname) {
		this.kuaidiname = kuaidiname;
	}
	public String getKuaidino() {
		return kuaidino;
	}
	public void setKuaidino(String kuaidino) {
		this.kuaidino = kuaidino;
	}
	public Integer getCardid() {
		return cardid;
	}
	public void setCardid(Integer cardid) {
		this.cardid = cardid;
	}
	public String getOrdersbh() {
		return ordersbh;
	}
	public void setOrdersbh(String ordersbh) {
		this.ordersbh = ordersbh;
	}
	public String getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	
	
	
}
