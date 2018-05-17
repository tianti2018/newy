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
@Table(name="ordersyliu")
public class OrderLiu implements Serializable {
	
	private int ordersId;
	
	private String mobile;//手机号
	private Integer orderStatus;//0未支付 1已支付 不能退货
	private String chongzhiStatus;//充值状态  充值接口返回的状态   http://www.le-mian.com/Interface#pError api 说明 1 成功
	//充值状态  充值接口返回的状态 云通讯 0000 成功
	private String msgId;//充值时返回的信息id
	private String description;//记录充值的中文结果
	private String toUserName;
	private Integer userId;//usery中的id
	
	private String pname;//充值 流量数值
	private String orderType;//移动 联通 电信 
	private Double retMoney;//可返佣金总额
	private Date createDate;
	private String ordersBH;
	private Double money;//支付金额 
	private String liunum;//云通讯 流量编码
	private String customId;//云通讯 第三方交易id
	
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ordersId", unique = true, nullable = false)
	public int getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(int ordersId) {
		this.ordersId = ordersId;
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

	/**
	 * @return the chongzhiStatus
	 */
	public String getChongzhiStatus() {
		return chongzhiStatus;
	}

	/**
	 * @param chongzhiStatus the chongzhiStatus to set
	 */
	public void setChongzhiStatus(String chongzhiStatus) {
		this.chongzhiStatus = chongzhiStatus;
	}

	/**
	 * @return the retMoney
	 */
	public Double getRetMoney() {
		return retMoney;
	}

	/**
	 * @param retMoney the retMoney to set
	 */
	public void setRetMoney(Double retMoney) {
		this.retMoney = retMoney;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the liunum
	 */
	public String getLiunum() {
		return liunum;
	}

	/**
	 * @param liunum the liunum to set
	 */
	public void setLiunum(String liunum) {
		this.liunum = liunum;
	}

	
	public String getCustomId() {
		return customId;
	}

	
	public void setCustomId(String customId) {
		this.customId = customId;
	}
	

	
}
