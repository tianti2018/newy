package com.tw.web.hibernate.persistent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 砸金蛋奖品表
 * 
 * @author fyrh
 * @currentTime
 */
@Entity
@Table(name = "egg_prize")
public class EggPrize implements Serializable {

	/*
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 活动id
	 */
	private Integer activityId;
	/**
	 * 奖品级别
	 */
	private String  prizeLevel;
	/**
	 * 奖品名称
	 */
	private String prizeName;
	/**
	 * 奖品数量
	 */
	private Integer nums;
	/**
	 * 状态(0关闭1开启，不开放的话用户永远抽不到)
	 */
	private String status;
	
	/**
	 * 中奖率(2位小数)
	 */
	private Double winRate;
	private Date createTime;
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
	@Column(name = "prize_level")
	public String getPrizeLevel() {
		return prizeLevel;
	}

	public void setPrizeLevel(String prizeLevel) {
		this.prizeLevel = prizeLevel;
	}
	@Column(name = "prize_name")
	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}
	@Column(name = "nums")
	public Integer getNums() {
		return nums;
	}

	public void setNums(Integer nums) {
		this.nums = nums;
	}
	@Column(name = "stauts")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "win_rate")
	public Double getWinRate() {
		return winRate;
	}

	public void setWinRate(Double winRate) {
		this.winRate = winRate;
	}
	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
