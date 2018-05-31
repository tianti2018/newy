package com.zklc.weishangcheng.member.hibernate.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="user_level_conf")
public class UserLevelConf implements Serializable {

	private Integer id;					//1:小二条件,2:掌柜条件,3:大掌柜条件
	private Long orderNum;				
	private Long xiaoErNum;
	private Long zhangGuiNum;
	private Long daZhangGuiNum;
	private Long qitaNum;
	private String beiyong;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Long orderNum) {
		this.orderNum = orderNum;
	}
	public Long getXiaoErNum() {
		return xiaoErNum;
	}
	public void setXiaoErNum(Long xiaoErNum) {
		this.xiaoErNum = xiaoErNum;
	}
	public Long getZhangGuiNum() {
		return zhangGuiNum;
	}
	public void setZhangGuiNum(Long zhangGuiNum) {
		this.zhangGuiNum = zhangGuiNum;
	}
	public Long getDaZhangGuiNum() {
		return daZhangGuiNum;
	}
	public void setDaZhangGuiNum(Long daZhangGuiNum) {
		this.daZhangGuiNum = daZhangGuiNum;
	}
	public Long getQitaNum() {
		return qitaNum;
	}
	public void setQitaNum(Long qitaNum) {
		this.qitaNum = qitaNum;
	}
	public String getBeiyong() {
		return beiyong;
	}
	public void setBeiyong(String beiyong) {
		this.beiyong = beiyong;
	}
	
	
	
}
