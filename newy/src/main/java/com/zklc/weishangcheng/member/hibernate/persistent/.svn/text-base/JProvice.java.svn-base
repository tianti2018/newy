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
@Table(name="j_position_provice")
public class JProvice implements Serializable{
	
	private Integer id;
	
	private Integer proviceId;
	  
	private String proviceName;
  
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "provice_id")
	public Integer getProviceId() {
		return proviceId;
	}
	public void setProviceId(Integer proviceId) {
		this.proviceId = proviceId;
	}
	@Column(name = "provice_name")
	public String getProviceName() {
		return proviceName;
	}
	public void setProviceName(String proviceName) {
		this.proviceName = proviceName;
	}
	
}
