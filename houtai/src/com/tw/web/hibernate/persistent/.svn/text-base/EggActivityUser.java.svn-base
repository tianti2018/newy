package com.tw.web.hibernate.persistent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 砸金蛋活动用户表 
 * 
 * @author fyrh
 * @currentTime
 */
@Entity
@Table(name = "egg_activity_user")
public class EggActivityUser implements Serializable {

	/*
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 活动id
	 */
	private Integer activityId;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 最大次数
	 */
	private Integer maxNum;
	/**
	 * 当前次数
	 */
	private Integer currNum;
	@Id
	@GeneratedValue
	@Column(name = "id")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "activity_id")
	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	@Column(name = "user_id")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name = "max_num")
	public Integer getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}
	@Column(name = "curr_num")
	public Integer getCurrNum() {
		return currNum;
	}

	public void setCurrNum(Integer currNum) {
		this.currNum = currNum;
	}


}
