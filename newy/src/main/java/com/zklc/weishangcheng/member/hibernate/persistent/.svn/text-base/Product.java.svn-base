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
@Table(name = "product")
public class Product implements Serializable {
	
	private Integer prodId;// 商品主键
	private String prodName;// 商品名称
	private String prodCode;// 商品编码
	private String prodDescription;
	private Double price;// 商品销售价格
	private Double orginPrice;//商品原价 页面上显示用
	private Integer rebateType;// 返利类型：1比例，2金额
	private Integer prodState;// 商品状态 0下架，1正在使用，2缺货(商品并未下架只是库存不够)
	private Integer prodType;// 商品的品类，这个值相同 表示是同品类，比如内衣，皮鞋
	private Double transFee;// 商品的购买运费
	private Double rebateBaseAmt;// 拿出计算佣金的基数
	private Double rebateAmtFirst;// 当rebateType=1时
									// 表示佣金的占比，当rebateType=2时表示能得到的佣金金额
	private Double rebateAmtSecond;// 同上
	private Double rebateAmtThird;// 同上
	private Integer limitNum;// 限购数量，默认值0 表示无限制，大于0，表示限制数量
	private Integer manufacturer;// 厂商
	private Integer qi;// 为和现业务保持相近，表示什么（第几期）商品
	private Integer stock;// 库存量
	private String prodColor;//颜色
	private String prodSize;//尺寸
	private Integer kaiguan; //为1则不用购买直接拿返利为0或空都要判断推荐人是否购买过本类产品
	
	private Double score;//	积分
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "prodId", unique = true, nullable = false)
	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}
	@Column(name = "prodName")
	public String getProdName() {
		return prodName;
	}
	

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	@Column(name = "prodCode")
	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	@Column(name = "prodDescription")
	public String getProdDescription() {
		return prodDescription;
	}

	public void setProdDescription(String prodDescription) {
		this.prodDescription = prodDescription;
	}

	@Column(name = "price")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "rebateType")
	public Integer getRebateType() {
		return rebateType;
	}

	public void setRebateType(Integer rebateType) {
		this.rebateType = rebateType;
	}

	@Column(name = "prodState")
	public Integer getProdState() {
		return prodState;
	}

	public void setProdState(Integer prodState) {
		this.prodState = prodState;
	}

	@Column(name = "prodType")
	public Integer getProdType() {
		return prodType;
	}

	public void setProdType(Integer prodType) {
		this.prodType = prodType;
	}

	@Column(name = "createDate")
	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Column(name = "modDate")
	public Timestamp getModDate() {
		return modDate;
	}

	public void setModDate(Timestamp modDate) {
		this.modDate = modDate;
	}

	
	@Column(name="prodColor")
	public String getProdColor() {
		return prodColor;
	}

	public void setProdColor(String prodColor) {
		this.prodColor = prodColor;
	}
    @Column(name="prodSize")
	public String getProdSize() {
		return prodSize;
	}

	public void setProdSize(String prodSize) {
		this.prodSize = prodSize;
	}
	@Column(name="stock")
	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	// 创建时间
	private Timestamp createDate = new Timestamp(System.currentTimeMillis());
	// 修改时间
	private Timestamp modDate = new Timestamp(System.currentTimeMillis());
   @Column(name="rebateAmtFirst")
	public Double getRebateAmtFirst() {
		return rebateAmtFirst;
	}

	public void setRebateAmtFirst(Double rebateAmtFirst) {
		this.rebateAmtFirst = rebateAmtFirst;
	}
    @Column(name="rebateAmtSecond")
	public Double getRebateAmtSecond() {
		return rebateAmtSecond;
	}

	public void setRebateAmtSecond(Double rebateAmtSecond) {
		this.rebateAmtSecond = rebateAmtSecond;
	}
    @Column(name="rebateAmtThird")
	public Double getRebateAmtThird() {
		return rebateAmtThird;
	}

	public void setRebateAmtThird(Double rebateAmtThird) {
		this.rebateAmtThird = rebateAmtThird;
	}

	@Column(name = "limitNum")
	public Integer getLimitNum() {
		return limitNum;
	}

	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}

	@Column(name = "manufacturer")
	public Integer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Integer manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Column(name = "qi")
	public Integer getQi() {
		return qi;
	}

	public void setQi(Integer qi) {
		this.qi = qi;
	}

	@Column(name = "rebateBaseAmt")
	public Double getRebateBaseAmt() {
		return rebateBaseAmt;
	}

	public void setRebateBaseAmt(Double rebateBaseAmt) {
		this.rebateBaseAmt = rebateBaseAmt;
	}

	@Column(name = "transFee")
	public Double getTransFee() {
		return transFee;
	}

	public void setTransFee(Double transFee) {
		this.transFee = transFee;
	}
	@Column(name="orginPrice")
	public Double getOrginPrice() {
		return orginPrice;
	}

	public void setOrginPrice(Double orginPrice) {
		this.orginPrice = orginPrice;
	}

	public Integer getKaiguan() {
		return kaiguan;
	}

	public void setKaiguan(Integer kaiguan) {
		this.kaiguan = kaiguan;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
	
}
