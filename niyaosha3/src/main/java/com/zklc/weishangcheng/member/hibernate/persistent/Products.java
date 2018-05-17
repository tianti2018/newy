package com.zklc.weishangcheng.member.hibernate.persistent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="products") 
public class Products implements Serializable {
	
	private Integer productsId;
	private Integer userId;//上传人的id
	private String userName;//上传人姓名
	private String name;//产品名称
	private String headUrl;//缩略图
	private Double price;//价格
	private Double oldPrice;//原价
	private Date createDate;//创建时间
	private String productInfo;//界面详情  用来存储界面图文信息
	private Double levelone;//小二价格
	private Double leveltwo;//掌柜价格
	private Double levelthr;//大掌柜价格
	private Double levelfor;//进货价
	private Integer type;//首页图显示类型 0轮播图 1一行一款2一行两款
	private Integer status;//0取消上架1上架
	private String prodDescription;//商品描述卖点
	private Integer transFee;//运费
	private Integer limitNum;//限购// 限购数量，默认值0 表示无限制，大于0，表示限制数量
	private Integer stock;// 库存量
	private String prodColor;//颜色
	private String prodSize;//尺寸
	private String guige;//规格
	private Integer paixu;//值越大,越靠前
	private Integer prodType;//特征码这个值相同则为同一款产品的属性
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "productsId", unique = true, nullable = false)
	public Integer getProductsId() {
		return productsId;
	}
	public void setProductsId(Integer productsId) {
		this.productsId = productsId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name="userId")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHeadUrl() {
		return headUrl;
	}
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getOldPrice() {
		return oldPrice;
	}
	public void setOldPrice(Double oldPrice) {
		this.oldPrice = oldPrice;
	}
	@Column(length=5000)
	public String getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}
	public Double getLevelone() {
		return levelone;
	}
	public void setLevelone(Double levelone) {
		this.levelone = levelone;
	}
	public Double getLeveltwo() {
		return leveltwo;
	}
	public void setLeveltwo(Double leveltwo) {
		this.leveltwo = leveltwo;
	}
	public Double getLevelthr() {
		return levelthr;
	}
	public void setLevelthr(Double levelthr) {
		this.levelthr = levelthr;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getProdDescription() {
		return prodDescription;
	}
	public void setProdDescription(String prodDescription) {
		this.prodDescription = prodDescription;
	}
	public Integer getTransFee() {
		return transFee;
	}
	public void setTransFee(Integer transFee) {
		this.transFee = transFee;
	}
	public Integer getLimitNum() {
		return limitNum;
	}
	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getProdColor() {
		return prodColor;
	}
	public void setProdColor(String prodColor) {
		this.prodColor = prodColor;
	}
	public String getProdSize() {
		return prodSize;
	}
	public void setProdSize(String prodSize) {
		this.prodSize = prodSize;
	}
	public Double getLevelfor() {
		return levelfor;
	}
	public void setLevelfor(Double levelfor) {
		this.levelfor = levelfor;
	}
	public String getGuige() {
		return guige;
	}
	public void setGuige(String guige) {
		this.guige = guige;
	}
	public Integer getPaixu() {
		return paixu;
	}
	public void setPaixu(Integer paixu) {
		this.paixu = paixu;
	}
	public Integer getProdType() {
		return prodType;
	}
	public void setProdType(Integer prodType) {
		this.prodType = prodType;
	}
	
}
