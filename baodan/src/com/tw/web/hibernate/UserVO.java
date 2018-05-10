package com.tw.web.hibernate;

import java.util.Date;

import com.tw.web.hibernate.persistent.User;

public class UserVO {

	private Integer referrerId;
	private String referrerName;
	private String wxOpenId;
	private User referrer;
	private Integer userId;
	private String userName;
	private String xiaoShowE;
	private String yongjin;
	private Date appDate;
	private String child1;
	private String child2;
	private String child3;
	
	
	private String childString1;
	private String childString2;
	private Double childDouble1;
	private Double childDouble2;
	private Integer childInteger1;
	private Integer childInteger2;
	private Date chilDate1;
	private Date childDate2;
	public Integer getReferrerId() {
		return referrerId;
	}
	public void setReferrerId(Integer referrerId) {
		this.referrerId = referrerId;
	}
	public String getReferrerName() {
		return referrerName;
	}
	public void setReferrerName(String referrerName) {
		this.referrerName = referrerName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getXiaoShowE() {
		return xiaoShowE;
	}
	public void setXiaoShowE(String xiaoShowE) {
		this.xiaoShowE = xiaoShowE;
	}
	public String getYongjin() {
		return yongjin;
	}
	public void setYongjin(String yongjin) {
		this.yongjin = yongjin;
	}
	public Date getAppDate() {
		return appDate;
	}
	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}
	public String getChild1() {
		return child1;
	}
	public void setChild1(String child1) {
		this.child1 = child1;
	}
	public String getChild2() {
		return child2;
	}
	public void setChild2(String child2) {
		this.child2 = child2;
	}
	public String getChild3() {
		return child3;
	}
	public void setChild3(String child3) {
		this.child3 = child3;
	}
	public String getChildString1() {
		return childString1;
	}
	public void setChildString1(String childString1) {
		this.childString1 = childString1;
	}
	public String getChildString2() {
		return childString2;
	}
	public void setChildString2(String childString2) {
		this.childString2 = childString2;
	}
	public Double getChildDouble1() {
		return childDouble1;
	}
	public void setChildDouble1(Double childDouble1) {
		this.childDouble1 = childDouble1;
	}
	public Double getChildDouble2() {
		return childDouble2;
	}
	public void setChildDouble2(Double childDouble2) {
		this.childDouble2 = childDouble2;
	}
	public Integer getChildInteger1() {
		return childInteger1;
	}
	public void setChildInteger1(Integer childInteger1) {
		this.childInteger1 = childInteger1;
	}
	public Integer getChildInteger2() {
		return childInteger2;
	}
	public void setChildInteger2(Integer childInteger2) {
		this.childInteger2 = childInteger2;
	}
	public Date getChilDate1() {
		return chilDate1;
	}
	public void setChilDate1(Date chilDate1) {
		this.chilDate1 = chilDate1;
	}
	public Date getChildDate2() {
		return childDate2;
	}
	public void setChildDate2(Date childDate2) {
		this.childDate2 = childDate2;
	}
	public String getWxOpenId() {
		return wxOpenId;
	}
	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
	}
	public User getReferrer() {
		return referrer;
	}
	public void setReferrer(User referrer) {
		this.referrer = referrer;
	}
	
	
}
