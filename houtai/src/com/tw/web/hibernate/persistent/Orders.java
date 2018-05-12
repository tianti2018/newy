package com.tw.web.hibernate.persistent;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@SuppressWarnings("serial")
@Entity
@Table(name="orders") 
public class Orders extends AbstractSymBasePO {
	
	private Integer ordersId;
	private byte order_status;
	private String toUserName;//收货人姓名
	private String address;
	private String zipcode;
	private String mobile;
	private String tel;
	private Integer userId;
	private String pname;
	private Date createDate;
	
	private String kuaidiName;
	private String kuaidiNo;
	
	private Integer cardId;
	private String ordersBH;
	private Integer productsId;
	private String productImg;
	
	private String orderType; //订单类型 1：首次购买 2：重复购买  3:邀请人购买 4：中奖 5:服务中心赠送
	private Double money;//支付金额
	private Double score;//支付积分
	private User user;
	private String size;
	private String color;
	
	private String comments;
	private Set<OrdersProducts> orderProducts = new HashSet<OrdersProducts>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ordersId", unique = true, nullable = false)
	public Integer getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(Integer ordersId) {
		this.ordersId = ordersId;
	}
	public byte getOrder_status() {
		return order_status;
	}
	public void setOrder_status(byte order_status) {
		this.order_status = order_status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Column(name="userId", insertable = false, updatable = false)
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	@ManyToOne
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.EAGER, mappedBy="orders")
	@LazyCollection(LazyCollectionOption.FALSE) 
	@Fetch(FetchMode.SELECT) 
	@JoinColumn(name="ordersId")
	public Set<OrdersProducts> getOrderProducts() {
		return orderProducts;
	}
	public void setOrderProducts(Set<OrdersProducts> orderProducts) {
		this.orderProducts = orderProducts;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getKuaidiName() {
		return kuaidiName;
	}
	public void setKuaidiName(String kuaidiName) {
		this.kuaidiName = kuaidiName;
	}
	public String getKuaidiNo() {
		return kuaidiNo;
	}
	public void setKuaidiNo(String kuaidiNo) {
		this.kuaidiNo = kuaidiNo;
	}
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	public String getOrdersBH() {
		return ordersBH;
	}
	public void setOrdersBH(String ordersBH) {
		this.ordersBH = ordersBH;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Integer getProductsId() {
		return productsId;
	}
	public void setProductsId(Integer productsId) {
		this.productsId = productsId;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	
}
