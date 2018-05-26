package com.zklc.weishangcheng.member.hibernate.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TArea entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_area")
public class TArea implements Serializable {

	// Fields

	private Integer areaId;
	private String areaCode;
	private String areaName;
	private String areaType;
	private String spell;
	private String parentCode;
	private String name;
	private String memo;

	// Constructors

	/** default constructor */
	public TArea() {
	}

	/** minimal constructor */
	public TArea(Integer id) {
		this.areaId = id;
	}

	/** full constructor */
	public TArea(Integer id, String areaCode, String areaName, String areaType,
			String spell, String parentCode, String name, String memo) {
		this.areaId = id;
		this.areaCode = areaCode;
		this.areaName = areaName;
		this.areaType = areaType;
		this.spell = spell;
		this.parentCode = parentCode;
		this.name = name;
		this.memo = memo;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	@Column(name = "area_code", length = 20)
	public String getAreaCode() {
		return this.areaCode;
	}
	
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Column(name = "area_name", length = 50)
	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Column(name = "area_type", length = 5)
	public String getAreaType() {
		return this.areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	@Column(name = "spell", length = 100)
	public String getSpell() {
		return this.spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	@Column(name = "parent_code", length = 20)
	public String getParentCode() {
		return this.parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "memo", length = 300)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}