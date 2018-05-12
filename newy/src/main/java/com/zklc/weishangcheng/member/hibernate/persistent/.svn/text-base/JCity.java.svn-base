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
@Table(name="j_position_city")
public class JCity implements Serializable{
	
	private Integer id;
	
	private Integer provinceId;
	
	private Long cityId;
	  
	private String cityName;
	
	private String pinyin;
	
	private Integer status;//0 作废，1可用，2 热门城市
	
	private String aleph;//首字母
	
	private String shortname;
	
	private Integer oldCityId;
  
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "province_id")
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	@Column(name = "city_id")
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	@Column(name = "city_name")
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAleph() {
		return aleph;
	}
	public void setAleph(String aleph) {
		this.aleph = aleph;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	@Column(name = "old_city_id")
	public Integer getOldCityId() {
		return oldCityId;
	}
	public void setOldCityId(Integer oldCityId) {
		this.oldCityId = oldCityId;
	}

}
