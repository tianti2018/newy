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
@Table(name="liucode")
public class LiuCode implements Serializable {
	
	private Integer ordersId;
	private String busPerson;	
	private String liuMoney;
	private String liuNum;
	private Double yongjin;  //卡面值
	private Date lastUpdateTime;
	private Integer liuType;//流量充值模式 0：乐免  1：华时  2，云通讯
	
	private String huaCode;//华时流量编码
	private String yunCode;//云通讯流量编码
	private String liustatus;//记录移动联通电信 走那个运营商，优先乐免

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ordersId", unique = true, nullable = false)
	public Integer getOrdersId() {
		return ordersId;
	}

	/**
	 * @param ordersId the ordersId to set
	 */
	public void setOrdersId(Integer ordersId) {
		this.ordersId = ordersId;
	}

	/**
	 * @return the busPerson
	 */
	public String getBusPerson() {
		return busPerson;
	}

	/**
	 * @param busPerson the busPerson to set
	 */
	public void setBusPerson(String busPerson) {
		this.busPerson = busPerson;
	}

	/**
	 * @return the liuMoney
	 */
	public String getLiuMoney() {
		return liuMoney;
	}

	/**
	 * @param liuMoney the liuMoney to set
	 */
	public void setLiuMoney(String liuMoney) {
		this.liuMoney = liuMoney;
	}

	/**
	 * @return the liuNum
	 */
	public String getLiuNum() {
		return liuNum;
	}

	/**
	 * @param liuNum the liuNum to set
	 */
	public void setLiuNum(String liuNum) {
		this.liuNum = liuNum;
	}

	/**
	 * @return the yongjin
	 */
	public Double getYongjin() {
		return yongjin;
	}

	/**
	 * @param yongjin the yongjin to set
	 */
	public void setYongjin(Double yongjin) {
		this.yongjin = yongjin;
	}

	/**
	 * @return the lastUpdateTime
	 */
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	/**
	 * @param lastUpdateTime the lastUpdateTime to set
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getLiuType() {
		return liuType;
	}

	public void setLiuType(Integer liuType) {
		this.liuType = liuType;
	}

	public String getHuaCode() {
		return huaCode;
	}

	public void setHuaCode(String huaCode) {
		this.huaCode = huaCode;
	}

	/**
	 * @return the yunCode
	 */
	public String getYunCode() {
		return yunCode;
	}

	/**
	 * @param yunCode the yunCode to set
	 */
	public void setYunCode(String yunCode) {
		this.yunCode = yunCode;
	}

	public String getLiustatus() {
		return liustatus;
	}

	public void setLiustatus(String liustatus) {
		this.liustatus = liustatus;
	}
	
	//private User user;
	
	
	
}
