package com.zklc.weishangcheng.member.hibernate.persistent.vo;

import java.util.Date;

public class FhRecordDianVO {

	private Integer userId;
	
	private String headUrl;
	
	private String userName;
	
	private Date appDate;
	
	private Integer userLevel;
	
	private Integer cardId;
	
	private Integer flag;
	
	private Double fhmoney;
	
	private Integer fromUserId;
	
	private Integer dianzhuLevel;
	
	private Integer starLevel;
	
	private String memo;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getAppDate() {
		return appDate;
	}

	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}

	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Double getFhmoney() {
		return fhmoney;
	}

	public void setFhmoney(Double fhmoney) {
		this.fhmoney = fhmoney;
	}

	public Integer getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}

	public Integer getDianzhuLevel() {
		return dianzhuLevel;
	}

	public void setDianzhuLevel(Integer dianzhuLevel) {
		this.dianzhuLevel = dianzhuLevel;
	}

	public Integer getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(Integer starLevel) {
		this.starLevel = starLevel;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
