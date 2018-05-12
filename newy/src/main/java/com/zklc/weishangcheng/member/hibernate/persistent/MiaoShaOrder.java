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
@Table(name="miaosha_order")
public class MiaoShaOrder implements Serializable {
	
	private int ordersId;			//主键
	private String address;			//收货地址
	private String mobile;			//收货电话
	private Integer orderStatus;//11 表示已取消，0表示待审核这个时候用户可以取消订单，1发货中（订单已支付6小时可能订单已经发出），2已发货
	private String tel;				//身份证号
	private String toUserName;		//收货人
	private Integer userId;			//下单人id
	private String zipcode;			//微信号
	private String pname;			//产品名称
	private Date fahuoDate;			//发货时间
	private Date createDate;		//下单时间
	private String kuaidiName;		//快递公司
	private String kuaidiNo;		//快递单号
	private Integer cardId;			//
	private String ordersBH;		//订单编号
	private String orderType;		//1秒杀订单  2 积分订单  3会员专区订单  4店主专区订单 5星火券订单
	private Double money;			//支付金额
	private Double score;			//支付积分
	private Integer qi;				//秒杀第几期
	private String color;			//产品颜色
	private Integer size;			//产品数量
	private String levelValue;		//
	private Integer type;			//用来区分厂家
	private Integer productId;		//产品主键
	private Integer xinghuoquan;	//用户消耗的星火券
	private Integer payStatus;		//用户实际的支付状态 0 未支付，1已支付，2已退款
	private Date    payTime;		//用户实际的支付时间
	private Date cancelTime;		//用户取消订单的时间
	private Date refundTime;		//退款时间
	
	public Date getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}

	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ordersId", unique = true, nullable = false)
	public int getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(int ordersId) {
		this.ordersId = ordersId;
	}
	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name = "order_status")
	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	@Column(name = "tel")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	@Column(name = "toUserName")
	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	@Column(name = "userId")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name = "zipcode")
	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	@Column(name = "pname")
	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}
	@Column(name = "createDate")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name = "kuaidiName")
	public String getKuaidiName() {
		return kuaidiName;
	}

	public void setKuaidiName(String kuaidiName) {
		this.kuaidiName = kuaidiName;
	}
	@Column(name = "kuaidiNo")
	public String getKuaidiNo() {
		return kuaidiNo;
	}

	public void setKuaidiNo(String kuaidiNo) {
		this.kuaidiNo = kuaidiNo;
	}
	@Column(name = "cardId")
	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	@Column(name = "ordersBH")
	public String getOrdersBH() {
		return ordersBH;
	}

	public void setOrdersBH(String ordersBH) {
		this.ordersBH = ordersBH;
	}
	@Column(name = "orderType")
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	@Column(name = "money")
	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}
	@Column(name = "score")
	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getLevelValue() {
		return levelValue;
	}

	public void setLevelValue(String levelValue) {
		this.levelValue = levelValue;
	}

	public Integer getQi() {
		return qi;
	}

	public void setQi(Integer qi) {
		this.qi = qi;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getXinghuoquan() {
		return xinghuoquan;
	}

	public void setXinghuoquan(Integer xinghuoquan) {
		this.xinghuoquan = xinghuoquan;
	}

	public Date getFahuoDate() {
		return fahuoDate;
	}

	public void setFahuoDate(Date fahuoDate) {
		this.fahuoDate = fahuoDate;
	}

}
