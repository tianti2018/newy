package com.zklc.weishangcheng.member.hibernate.persistent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 48小时内互动用户表
 * 
 * @author jazz
 * 
 */
@Entity
@Table(name = "weixin_autosendmsg")
public class WeixinAutosendmsg implements Serializable {

	private Integer id;
	private String openid;
	private Date htime;
	private String username;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "openid")
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Column(name = "htime")
	public Date getHtime() {
		return htime;
	}

	public void setHtime(Date htime) {
		this.htime = htime;
	}

	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 重写equals方法，如果open相同则视为相同
	 */
	public boolean equals(Object other) {
		if (!(other instanceof WeixinAutosendmsg))
			return false;
		WeixinAutosendmsg oob = (WeixinAutosendmsg) other;
		if (!getOpenid().equals(oob.getOpenid()))
			return false;
		return true;
	}

}
