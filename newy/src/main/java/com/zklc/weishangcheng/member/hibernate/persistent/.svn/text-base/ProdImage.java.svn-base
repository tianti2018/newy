package com.zklc.weishangcheng.member.hibernate.persistent;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "product_image")
public class ProdImage implements Serializable{
   private Integer  id;
   private Long  prodId;
   private String prodUrl;
   private String imageNote;
   private Integer urlType;//1轮播图，2详情页图
   private Integer imageState;
   private Timestamp createDate = new Timestamp(System.currentTimeMillis()); 
   
   
   @Column(name="imageNote")
   public String getImageNote() {
	return imageNote;
}
public void setImageNote(String imageNote) {
	this.imageNote = imageNote;
}
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name = "id", unique = true, nullable = false)
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
@Column(name="prodId")
public Long getProdId() {
	return prodId;
}
public void setProdId(Long prodId) {
	this.prodId = prodId;
}
@Column(name="prodUrl")
public String getProdUrl() {
	return prodUrl;
}
public void setProdUrl(String prodUrl) {
	this.prodUrl = prodUrl;
}
@Column(name="urlType")
public Integer getUrlType() {
	return urlType;
}
public void setUrlType(Integer urlType) {
	this.urlType = urlType;
}
@Column(name="imageState")
public Integer getImageState() {
	return imageState;
}
public void setImageState(Integer imageState) {
	this.imageState = imageState;
}
@Column(name="createDate")
public Timestamp getCreateDate() {
	return createDate;
}
public void setCreateDate(Timestamp createDate) {
	this.createDate = createDate;
}

}
