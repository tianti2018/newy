package com.tw.web.hibernate.persistent;

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
@Table(name="shouyi_user")
public class ShouYiForUser implements Serializable {

	private Integer id;
	private Integer ordersId;				//订单id
	private Integer dianpuId;				//收益店铺
	private String ordersBh;				//订单编号
	private Double shouyi;					//收益
	private Integer status;					//状态0:未生效,1:待生效,2:生效,
	private Date createDate;				//创建时间
	private Integer orderType;				//什么类型的订单产生的收益0:顾客订单,1:其他店主进货订单
	
	private Date passDate;					//通过时间
	private Integer beiyong;				//备用
	private Integer tixian;					//提现  1:申请提现中,2:审核通过 直接status=2
	private String beizhu;					//备注信息
	private String dakuanDan;				//请输入打款单
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(Integer ordersId) {
		this.ordersId = ordersId;
	}
	public String getOrdersBh() {
		return ordersBh;
	}
	public void setOrdersBh(String ordersBh) {
		this.ordersBh = ordersBh;
	}
	public Double getShouyi() {
		return shouyi;
	}
	public void setShouyi(Double shouyi) {
		this.shouyi = shouyi;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getPassDate() {
		return passDate;
	}
	public void setPassDate(Date passDate) {
		this.passDate = passDate;
	}
	public Integer getBeiyong() {
		return beiyong;
	}
	public void setBeiyong(Integer beiyong) {
		this.beiyong = beiyong;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public Integer getDianpuId() {
		return dianpuId;
	}
	public void setDianpuId(Integer dianpuId) {
		this.dianpuId = dianpuId;
	}
	public Integer getTixian() {
		return tixian;
	}
	public void setTixian(Integer tixian) {
		this.tixian = tixian;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	public String getDakuanDan() {
		return dakuanDan;
	}
	public void setDakuanDan(String dakuanDan) {
		this.dakuanDan = dakuanDan;
	}
	
}
