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
@Table(name="order_address")
public class OrderAddress implements Serializable {
	
	private Integer id;
	private String shengCode;
	private String chengshiCode;
	private String diquCode;
	private String sheng;
	private String chengshi;
	private String diqu;
	private String address;			//详细地址
	private String mobile;			//收货人电话
	private String zipcode;			//邮编
	private String userName;		//收货人姓名
	private Integer userId;			
	private Integer useryId;
	private Date createDate;
	private String idCard;			//身份证
	/**
	 * 是否是默认收货地址，1是，0为空不是
	 */
	private String isFirst;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getIsFirst() {
		return isFirst;
	}
	public void setIsFirst(String isFirst) {
		this.isFirst = isFirst;
	}
	/**
	 * @return the idCard
	 */
	public String getIdCard() {
		return idCard;
	}
	/**
	 * @param idCard the idCard to set
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getShengCode() {
		return shengCode;
	}
	public void setShengCode(String shengCode) {
		this.shengCode = shengCode;
	}
	public String getChengshiCode() {
		return chengshiCode;
	}
	public void setChengshiCode(String chengshiCode) {
		this.chengshiCode = chengshiCode;
	}
	public String getDiquCode() {
		return diquCode;
	}
	public void setDiquCode(String diquCode) {
		this.diquCode = diquCode;
	}
	public String getSheng() {
		return sheng;
	}
	public void setSheng(String sheng) {
		this.sheng = sheng;
	}
	public String getChengshi() {
		return chengshi;
	}
	public void setChengshi(String chengshi) {
		this.chengshi = chengshi;
	}
	public String getDiqu() {
		return diqu;
	}
	public void setDiqu(String diqu) {
		this.diqu = diqu;
	}
	public Integer getUseryId() {
		return useryId;
	}
	public void setUseryId(Integer useryId) {
		this.useryId = useryId;
	}
	
}
