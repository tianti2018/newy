package com.tw.web.hibernate.persistent;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="users")
public class User extends AbstractSymBasePO {
	
	private Integer userId;
	private String loginName;
	private String passWord;
	private String passWord2;
	private String userName;
	private String phone;
	private BigInteger orderOne;
	
	private Integer parentId;
	private Integer child1;
	private Integer child2;
	private Integer referrerId; //推荐人
	
	private User parent;
	private User childOne;
	private User childTwo;
	private User referrer;
	
	private Integer submitMoney;
	private Integer realSubmitMoney; 
	private Double totalMoney;
	private Date createDate;
	private String level;
	private String activitiFlag; //激活标志 默认为0
	
	private String account; //账户
	
	private String isAppCenter; //是否是报单中心
	private Date appDate;
	private Date shappDate;
	
	private Integer appCenterId;
	private User appCenter;
	private String isHT; //是否回填单
	private String isHTApp;//0 回填  1 不回填单
	
	private String block;//锁定用户
	
	private Card card;
	private Integer cardId;
	
	private String flagorg;
	private Integer buyCount;
	private String qrCode;//名片二维码
	private String wxOpenid;//微信唯一标识
	
	private Integer areaId;
	private TArea tArea;
	private String ticket;
	/**
	 * 头像路径
	 */
	private String headUrl;
	/**
	 * 0 未取消关注 1 取消关注
	 */
	private Integer subscribe;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "userId", unique = true, nullable = false)
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name="parentId", insertable = false, updatable = false)
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	@Column(name="child1", insertable = false, updatable = false)
	public Integer getChild1() {
		return child1;
	}
	public void setChild1(Integer child1) {
		this.child1 = child1;
	}
	
	@Column(name="child2", insertable = false, updatable = false)
	public Integer getChild2() {
		return child2;
	}
	public void setChild2(Integer child2) {
		this.child2 = child2;
	}
	
	public Integer getSubmitMoney() {
		return submitMoney;
	}
	public void setSubmitMoney(Integer submitMoney) {
		this.submitMoney = submitMoney;
	}
	public Double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="parentId")
	public User getParent() {
		return parent;
	}
	public void setParent(User parent) {
		this.parent = parent;
	}
	
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="child1")
	public User getChildOne() {
		return childOne;
	}
	public void setChildOne(User childOne) {
		this.childOne = childOne;
	}
				
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="child2")
	public User getChildTwo() {
		return childTwo;
	}
	
	@Column(name="referrerId", insertable = false, updatable = false)
	public Integer getReferrerId() {
		return referrerId;
	}
	public void setReferrerId(Integer referrerId) {
		this.referrerId = referrerId;
	}
		
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="referrerId")
	public User getReferrer() {
		return referrer;
	}
	public void setReferrer(User referrer) {
		this.referrer = referrer;
	}
	public void setChildTwo(User childTwo) {
		this.childTwo = childTwo;
	}		
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassWord2() {
		return passWord2;
	}
	public void setPassWord2(String passWord2) {
		this.passWord2 = passWord2;
	}
	public String getActivitiFlag() {
		return activitiFlag;
	}
	public void setActivitiFlag(String activitiFlag) {
		this.activitiFlag = activitiFlag;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIsAppCenter() {
		return isAppCenter;
	}
	public void setIsAppCenter(String isAppCenter) {
		this.isAppCenter = isAppCenter;
	}
	public Date getAppDate() {
		return appDate;
	}
	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}
	public Date getShappDate() {
		return shappDate;
	}
	public void setShappDate(Date shappDate) {
		this.shappDate = shappDate;
	}
	@Column(name="appCenterId", insertable = false, updatable = false)
	public Integer getAppCenterId() {
		return appCenterId;
	}
	public void setAppCenterId(Integer appCenterId) {
		this.appCenterId = appCenterId;
	}
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="appCenterId")
	public User getAppCenter() {
		return appCenter;
	}
	public void setAppCenter(User appCenter) {
		this.appCenter = appCenter;
	}
	public Integer getRealSubmitMoney() {
		return realSubmitMoney;
	}
	public void setRealSubmitMoney(Integer realSubmitMoney) {
		this.realSubmitMoney = realSubmitMoney;
	}
	public String getIsHT() {
		return isHT;
	}
	public void setIsHT(String isHT) {
		this.isHT = isHT;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public String getIsHTApp() {
		return isHTApp;
	}
	public void setIsHTApp(String isHTApp) {
		this.isHTApp = isHTApp;
	}
	@ManyToOne 
	@JoinColumn(name="cardId")
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	@Column(name="cardId", insertable = false, updatable = false)
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	public BigInteger getOrderOne() {
		return orderOne;
	}
	public void setOrderOne(BigInteger orderOne) {
		this.orderOne = orderOne;
	}
	public String getFlagorg() {
		return flagorg;
	}
	public void setFlagorg(String flagorg) {
		this.flagorg = flagorg;
	}
	public Integer getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public String getWxOpenid() {
		return wxOpenid;
	}
	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}
	@Column(name="areaId", insertable = false, updatable = false)
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="areaId")
	public TArea gettArea() {
		return tArea;
	}
	public void settArea(TArea tArea) {
		this.tArea = tArea;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getHeadUrl() {
		return headUrl;
	}
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
	public Integer getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}
	
	
}
