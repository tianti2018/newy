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
@Table(name="usery")
public class Usery implements Serializable {

	private Integer id;						//主键
	private Integer userId;					//
	private String userName;				//昵称
	private String headUrl;					//头像连接
	private Integer parentId;				//推荐人useryId
	private Integer childNum;				//团队数量
	private Integer referrerId; 			//推荐人userId
	private Integer city;					//城市id
	
	private Double totalMoney;				//获取总利益利益
	
	private Double dayMoney;				//今日收益
	private Double account;					//账户余额
	private Integer totalOrderNum;			//总成交单数
	private Integer dayOrderNum;			//日成交量
	
	private Date createDate;				//每天更新一次,用来校验用户数据
	private Integer level;					
	
	private Date appDate;					//关注时间
	
	private String block;					//锁定用户
	
	private String qrCode;					//名片二维码
	private String wxOpenid;				//微信唯一标识
	private String unionid;					//多个公众号联合以后的唯一身份凭证用于多个公众号互通数据
	private String ticket;					//查询推荐人的关键字
	
	private Integer dianPuId;				//店铺id
	
	/**
	 * 0 未取消关注 1 取消关注
	 */
	private Integer subscribe;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getWxOpenid() {
		return wxOpenid;
	}

	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public Integer getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}

	public Date getAppDate() {
		return appDate;
	}

	public void setAppDate(Date appDate) {
		this.appDate = appDate;
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

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getChildNum() {
		return childNum;
	}

	public void setChildNum(Integer childNum) {
		this.childNum = childNum;
	}

	public Integer getReferrerId() {
		return referrerId;
	}

	public void setReferrerId(Integer referrerId) {
		this.referrerId = referrerId;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Double getDayMoney() {
		return dayMoney;
	}

	public void setDayMoney(Double dayMoney) {
		this.dayMoney = dayMoney;
	}

	public Double getAccount() {
		return account;
	}

	public void setAccount(Double account) {
		this.account = account;
	}

	public Integer getTotalOrderNum() {
		return totalOrderNum;
	}

	public void setTotalOrderNum(Integer totalOrderNum) {
		this.totalOrderNum = totalOrderNum;
	}

	public Integer getDayOrderNum() {
		return dayOrderNum;
	}

	public void setDayOrderNum(Integer dayOrderNum) {
		this.dayOrderNum = dayOrderNum;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public Integer getDianPuId() {
		return dianPuId;
	}

	public void setDianPuId(Integer dianPuId) {
		this.dianPuId = dianPuId;
	}

}
