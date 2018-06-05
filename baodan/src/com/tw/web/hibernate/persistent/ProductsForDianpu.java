package com.tw.web.hibernate.persistent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="products_dianpu")
public class ProductsForDianpu implements Serializable{

	private Integer id;
	private Integer dianpuId;
	private Integer productId;
	private Double price;					//店主自制价格
	private Integer type;					//产品显示样式,0:轮播图,1:单爆款,2:普通散列
	private Integer paixu;					//排序值,倒序
	private Integer kaiguan;				//开关,0:正常贩卖,1:产品下架后自动变1,客户显示无货
	private Date createDate;				//上架时间
	private Double jianyiPrice;				//商品建议价格
	private String name;					//商品名称
	private String headUrl;					//商品缩略图
	private Integer status;					//1:上架
	private Integer prodType;				//特征码
	private Products products;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDianpuId() {
		return dianpuId;
	}
	public void setDianpuId(Integer dianpuId) {
		this.dianpuId = dianpuId;
	}
	@Column(name="productId", insertable = false, updatable = false)
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getPaixu() {
		return paixu;
	}
	public void setPaixu(Integer paixu) {
		this.paixu = paixu;
	}
	public Integer getKaiguan() {
		return kaiguan;
	}
	public void setKaiguan(Integer kaiguan) {
		this.kaiguan = kaiguan;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="productId")
	public Products getProducts() {
		return products;
	}
	public void setProducts(Products products) {
		this.products = products;
	}
	public Double getJianyiPrice() {
		return jianyiPrice;
	}
	public void setJianyiPrice(Double jianyiPrice) {
		this.jianyiPrice = jianyiPrice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHeadUrl() {
		return headUrl;
	}
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getProdType() {
		return prodType;
	}
	public void setProdType(Integer prodType) {
		this.prodType = prodType;
	}
	
}
