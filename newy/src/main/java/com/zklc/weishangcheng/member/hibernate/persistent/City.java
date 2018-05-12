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
@Table(name="city")
public class City implements Serializable{
	
  private Integer id;
	
  private String name;
	
  private Integer parentId;

 
private String shortname;
	
  private Integer leveltype;
	
  private String citycode;
	
  private String zipcode;
	
  private String lng;//经度
	
  private String lat;//纬度
	
  private String pinyin;
	
  private Integer status;//0 作废，1可用，2 热门城市
	
  private String aleph;//首字母
  
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "parentid")
	 public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	@Column(name = "shortname")
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	@Column(name = "leveltype")
	public Integer getLeveltype() {
		return leveltype;
	}
	public void setLeveltype(Integer leveltype) {
		this.leveltype = leveltype;
	}
	@Column(name = "citycode")
	public String getCitycode() {
		return citycode;
	}
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	@Column(name = "zipcode")
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	@Column(name = "lng")
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	@Column(name = "lat")
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	@Column(name = "pinyin")
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name = "aleph")
	public String getAleph() {
		return aleph;
	}
	public void setAleph(String aleph) {
		this.aleph = aleph;
	}
	
 
  
}
