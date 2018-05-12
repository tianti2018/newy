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
@Table(name="users")
public class JifenUser implements Serializable {
	
	private Integer userId;					//主键
	private String loginName;
//	private String passWord;
//	private String passWord2;
	private String userName;				//昵称
//	private String phone;
	private String headUrl;					//头像连接
//	private BigInteger orderOne;
	
//	private Integer parentId;
	private Integer child1;//用来表示超级粉丝人数
	private Integer child2;//用来表示铁杆粉丝人数  下面的activity15  用来表示忠实粉丝人数
	private Integer child3;//用来表示忠实粉丝人数
	private Integer referrerId; //推荐人
	private Integer city;
	
	private Integer submitMoney;		//用来表示累计消费 
	private Integer realSubmitMoney;	//用来表示星火券 
	private Double totalMoney;			//获取利益
	private Date createDate;			//每天更新一次,用来校验用户数据
	private Integer level;				//为1和2表示准vip会员  为3表示vip会员
//	private String activitiFlag; //激活标志 默认为0
	
//	private String account; //账户
	
//	private String isAppCenter; //是否是报单中心
	private Date appDate;		//以前赢在世界关注时间,现在无用
	private Date shappDate;		//用做显示关注星火时间
	
	private Integer appCenterId;//2:封号
//	private String isHT; //是否回填单
//	private String isHTApp;//0 回填  1 不回填单
	
	private String block;//锁定用户
	
	private Integer cardId;//用作店铺等级显示
	
//	private String flagorg;
	private Integer buyCount;//用户积分
	private String qrCode;//名片二维码
	private String wxOpenid;//微信唯一标识
	private String unionid;				//多个公众号联合以后的唯一身份凭证用于多个公众号互通数据
	private String ticket;				//查询推荐人的关键字
//	private String tmallUserId;
	
	private Integer cardCount;			//
	
	private Integer oneFlag;			//红包校验位
	private Integer twoFlag1;
//	private Integer twoFlag2;
	private Integer threeFlag1;
	private Integer threeFlag2;
	
//	private Integer four1;
//	private Integer four2;
//	
//	private Integer five1;
//	private Integer five2;
//	private Integer five3;
//	
//	private Integer six1;
//	private Integer six2;
//	private Integer six3;
//	
//	private Integer seven1;
//	private Integer seven2;
//	private Integer seven3;
//	private Integer seven4;
//	
//	private Integer eight1;
//	private Integer eight2;
//	private Integer eight3;
//	private Integer eight4;
//	
//	private Integer  nine1;
//	private Integer  nine2;
//	private Integer  nine3;
//	private Integer  nine4;
//	private Integer  nine5;
//	
//	private Integer ten1;
//	private Integer ten2;
//	private Integer ten3;
//	private Integer ten4;
//	private Integer ten5;
//	
//	private Integer eleven1;
//	private Integer eleven2;
//	private Integer eleven3;
//	private Integer eleven4;
//	private Integer eleven5;
//	
//	private Integer twelve1;
//	private Integer twelve2;
//	private Integer twelve3;
//	private Integer twelve4;
//	private Integer twelve5;
//	private Integer twelve6;
//	
//	private Integer thirteen1;
//	private Integer thirteen2;
//	private Integer thirteen3;
//	private Integer thirteen4;
//	private Integer thirteen5;
//	private Integer thirteen6;
//	
//	private Integer fourteen1;
//	private Integer fourteen2;
//	private Integer fourteen3;
//	private Integer fourteen4;
//	private Integer fourteen5;
//	private Integer fourteen6;
//	private Integer fourteen7;
//	
//	private Integer fifteen1;
//	private Integer fifteen2;
//	private Integer fifteen3;
//	private Integer fifteen4;
//	private Integer fifteen5;
//	private Integer fifteen6;
//	private Integer fifteen7;
	
	private String ewmPictureUrl;
	//private Integer totalJifen;//个人积分
	
	
	/*****
	 * 
	 * 100 90 1
	 * 200 180 1
	 * 300 240 2
	 * 400 360 2
	 * 500 450 3
	 * 600 540 3
	 * 700 630 4 
	 * 800 720  4
	 * 900 810 5
	 * 1000 900 5
	 * 1100 990 5
	 * 1200 1080 6
	 * 1300 1170 6
	 * 1400 1260 7
	 * 1500 1350  7
	 */
	
	private Integer activiti1;
	private Integer activiti2;
	private Integer activiti3;
//	private Integer activiti4;
//	private Integer activiti5;
//	private Integer activiti6;
//	private Integer activiti7;
//	private Integer activiti8;
//	private Integer activiti9;
//	private Integer activiti10;
//	private Integer activiti11;
//	private Integer activiti12;
//	private Integer activiti13;
	private Integer activiti14;  //用户消费的总金额

	
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
	
	@Column(name="loginName")
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
//	@Column(name="passWord")
//	public String getPassWord() {
//		return passWord;
//	}
//	public void setPassWord(String passWord) {
//		this.passWord = passWord;
//	}
	@Column(name="userName")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
//	@Column(name="parentId")
//	public Integer getParentId() {
//		return parentId;
//	}
//	public void setParentId(Integer parentId) {
//		this.parentId = parentId;
//	}
	
	@Column(name="child1")
	public Integer getChild1() {
		return child1;
	}
	public void setChild1(Integer child1) {
		this.child1 = child1;
	}
	
	@Column(name="child2")
	public Integer getChild2() {
		return child2;
	}
	public void setChild2(Integer child2) {
		this.child2 = child2;
	}
	@Column(name="submitMoney")
	public Integer getSubmitMoney() {
		return submitMoney;
	}
	
	public void setSubmitMoney(Integer submitMoney) {
		this.submitMoney = submitMoney;
	}
	@Column(name="totalMoney")
	public Double getTotalMoney() {
		return totalMoney;
	}
	
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	@Column(name="createDate")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name="level")
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	@Column(name="referrerId")
	public Integer getReferrerId() {
		return referrerId;
	}
	public void setReferrerId(Integer referrerId) {
		this.referrerId = referrerId;
	}
		
//	@Column(name="account")
//	public String getAccount() {
//		return account;
//	}
//	public void setAccount(String account) {
//		this.account = account;
//	}
//	@Column(name="passWord2")
//	public String getPassWord2() {
//		return passWord2;
//	}
//	public void setPassWord2(String passWord2) {
//		this.passWord2 = passWord2;
//	}
//	@Column(name="activitiFlag")
//	public String getActivitiFlag() {
//		return activitiFlag;
//	}
//	public void setActivitiFlag(String activitiFlag) {
//		this.activitiFlag = activitiFlag;
//	}
//	@Column(name="phone")
//	public String getPhone() {
//		return phone;
//	}
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}
//	@Column(name="isAppCenter")
//	public String getIsAppCenter() {
//		return isAppCenter;
//	}
//	public void setIsAppCenter(String isAppCenter) {
//		this.isAppCenter = isAppCenter;
//	}
	@Column(name="appDate")
	public Date getAppDate() {
		return appDate;
	}
	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}
	@Column(name="shappDate")
	public Date getShappDate() {
		return shappDate;
	}
	public void setShappDate(Date shappDate) {
		this.shappDate = shappDate;
	}
	@Column(name="appCenterId")
	public Integer getAppCenterId() {
		return appCenterId;
	}
	public void setAppCenterId(Integer appCenterId) {
		this.appCenterId = appCenterId;
	}
	@Column(name="realSubmitMoney")
	public Integer getRealSubmitMoney() {
		return realSubmitMoney;
	}
	public void setRealSubmitMoney(Integer realSubmitMoney) {
		this.realSubmitMoney = realSubmitMoney;
	}
//	@Column(name="isHT")
//	public String getIsHT() {
//		return isHT;
//	}
//	public void setIsHT(String isHT) {
//		this.isHT = isHT;
//	}
	@Column(name="block")
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
//	@Column(name="isHTApp")
//	public String getIsHTApp() {
//		return isHTApp;
//	}
//	public void setIsHTApp(String isHTApp) {
//		this.isHTApp = isHTApp;
//	}
	@Column(name="cardId")
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
//	@Column(name="orderOne")
//	public BigInteger getOrderOne() {
//		return orderOne;
//	}
//	public void setOrderOne(BigInteger orderOne) {
//		this.orderOne = orderOne;
//	}
//	@Column(name="flagorg")
//	public String getFlagorg() {
//		return flagorg;
//	}
//	public void setFlagorg(String flagorg) {
//		this.flagorg = flagorg;
//	}
	@Column(name="buyCount")
	public Integer getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}
	@Column(name="qrCode")
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	@Column(name="wxOpenid")
	public String getWxOpenid() {
		return wxOpenid;
	}
	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	@Column(name="headUrl")
	public String getHeadUrl() {
		return headUrl;
	}
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
//	public String getTmallUserId() {
//		return tmallUserId;
//	}
//	public void setTmallUserId(String tmallUserId) {
//		this.tmallUserId = tmallUserId;
//	}
	public Integer getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}
	public Integer getCardCount() {
		return cardCount;
	}
	public void setCardCount(Integer cardCount) {
		this.cardCount = cardCount;
	}
	public Integer getOneFlag() {
		return oneFlag;
	}
	public void setOneFlag(Integer oneFlag) {
		this.oneFlag = oneFlag;
	}
	public Integer getTwoFlag1() {
		return twoFlag1;
	}
	public void setTwoFlag1(Integer twoFlag1) {
		this.twoFlag1 = twoFlag1;
	}
//	public Integer getTwoFlag2() {
//		return twoFlag2;
//	}
//	public void setTwoFlag2(Integer twoFlag2) {
//		this.twoFlag2 = twoFlag2;
//	}
	public Integer getThreeFlag1() {
		return threeFlag1;
	}
	public void setThreeFlag1(Integer threeFlag1) {
		this.threeFlag1 = threeFlag1;
	}
	public Integer getThreeFlag2() {
		return threeFlag2;
	}
	public void setThreeFlag2(Integer threeFlag2) {
		this.threeFlag2 = threeFlag2;
	}
	public Integer getActiviti1() {
		return activiti1;
	}
	public void setActiviti1(Integer activiti1) {
		this.activiti1 = activiti1;
	}
	public Integer getActiviti2() {
		return activiti2;
	}
	public void setActiviti2(Integer activiti2) {
		this.activiti2 = activiti2;
	}
	public Integer getActiviti3() {
		return activiti3;
	}
	public void setActiviti3(Integer activiti3) {
		this.activiti3 = activiti3;
	}
//	public Integer getFour1() {
//		return four1;
//	}
//	public void setFour1(Integer four1) {
//		this.four1 = four1;
//	}
//	public Integer getFour2() {
//		return four2;
//	}
//	public void setFour2(Integer four2) {
//		this.four2 = four2;
//	}
//	public Integer getFive1() {
//		return five1;
//	}
//	public void setFive1(Integer five1) {
//		this.five1 = five1;
//	}
//	public Integer getFive2() {
//		return five2;
//	}
//	public void setFive2(Integer five2) {
//		this.five2 = five2;
//	}
//	public Integer getFive3() {
//		return five3;
//	}
//	public void setFive3(Integer five3) {
//		this.five3 = five3;
//	}
//	public Integer getSix1() {
//		return six1;
//	}
//	public void setSix1(Integer six1) {
//		this.six1 = six1;
//	}
//	public Integer getSix2() {
//		return six2;
//	}
//	public void setSix2(Integer six2) {
//		this.six2 = six2;
//	}
//	public Integer getSix3() {
//		return six3;
//	}
//	public void setSix3(Integer six3) {
//		this.six3 = six3;
//	}
//	public Integer getSeven1() {
//		return seven1;
//	}
//	public void setSeven1(Integer seven1) {
//		this.seven1 = seven1;
//	}
//	public Integer getSeven2() {
//		return seven2;
//	}
//	public void setSeven2(Integer seven2) {
//		this.seven2 = seven2;
//	}
//	public Integer getSeven3() {
//		return seven3;
//	}
//	public void setSeven3(Integer seven3) {
//		this.seven3 = seven3;
//	}
//	public Integer getSeven4() {
//		return seven4;
//	}
//	public void setSeven4(Integer seven4) {
//		this.seven4 = seven4;
//	}
//	public Integer getEight1() {
//		return eight1;
//	}
//	public void setEight1(Integer eight1) {
//		this.eight1 = eight1;
//	}
//	public Integer getEight2() {
//		return eight2;
//	}
//	public void setEight2(Integer eight2) {
//		this.eight2 = eight2;
//	}
//	public Integer getEight3() {
//		return eight3;
//	}
//	public void setEight3(Integer eight3) {
//		this.eight3 = eight3;
//	}
//	public Integer getEight4() {
//		return eight4;
//	}
//	public void setEight4(Integer eight4) {
//		this.eight4 = eight4;
//	}
//	public Integer getNine1() {
//		return nine1;
//	}
//	public void setNine1(Integer nine1) {
//		this.nine1 = nine1;
//	}
//	public Integer getNine2() {
//		return nine2;
//	}
//	public void setNine2(Integer nine2) {
//		this.nine2 = nine2;
//	}
//	public Integer getNine3() {
//		return nine3;
//	}
//	public void setNine3(Integer nine3) {
//		this.nine3 = nine3;
//	}
//	public Integer getNine4() {
//		return nine4;
//	}
//	public void setNine4(Integer nine4) {
//		this.nine4 = nine4;
//	}
//	public Integer getNine5() {
//		return nine5;
//	}
//	public void setNine5(Integer nine5) {
//		this.nine5 = nine5;
//	}
//	public Integer getTen1() {
//		return ten1;
//	}
//	public void setTen1(Integer ten1) {
//		this.ten1 = ten1;
//	}
//	public Integer getTen2() {
//		return ten2;
//	}
//	public void setTen2(Integer ten2) {
//		this.ten2 = ten2;
//	}
//	public Integer getTen3() {
//		return ten3;
//	}
//	public void setTen3(Integer ten3) {
//		this.ten3 = ten3;
//	}
//	public Integer getTen4() {
//		return ten4;
//	}
//	public void setTen4(Integer ten4) {
//		this.ten4 = ten4;
//	}
//	public Integer getTen5() {
//		return ten5;
//	}
//	public void setTen5(Integer ten5) {
//		this.ten5 = ten5;
//	}
//	public Integer getEleven1() {
//		return eleven1;
//	}
//	public void setEleven1(Integer eleven1) {
//		this.eleven1 = eleven1;
//	}
//	public Integer getEleven2() {
//		return eleven2;
//	}
//	public void setEleven2(Integer eleven2) {
//		this.eleven2 = eleven2;
//	}
//	public Integer getEleven3() {
//		return eleven3;
//	}
//	public void setEleven3(Integer eleven3) {
//		this.eleven3 = eleven3;
//	}
//	public Integer getEleven4() {
//		return eleven4;
//	}
//	public void setEleven4(Integer eleven4) {
//		this.eleven4 = eleven4;
//	}
//	public Integer getEleven5() {
//		return eleven5;
//	}
//	public void setEleven5(Integer eleven5) {
//		this.eleven5 = eleven5;
//	}
//	public Integer getTwelve1() {
//		return twelve1;
//	}
//	public void setTwelve1(Integer twelve1) {
//		this.twelve1 = twelve1;
//	}
//	public Integer getTwelve2() {
//		return twelve2;
//	}
//	public void setTwelve2(Integer twelve2) {
//		this.twelve2 = twelve2;
//	}
//	public Integer getTwelve3() {
//		return twelve3;
//	}
//	public void setTwelve3(Integer twelve3) {
//		this.twelve3 = twelve3;
//	}
//	public Integer getTwelve4() {
//		return twelve4;
//	}
//	public void setTwelve4(Integer twelve4) {
//		this.twelve4 = twelve4;
//	}
//	public Integer getTwelve5() {
//		return twelve5;
//	}
//	public void setTwelve5(Integer twelve5) {
//		this.twelve5 = twelve5;
//	}
//	public Integer getTwelve6() {
//		return twelve6;
//	}
//	public void setTwelve6(Integer twelve6) {
//		this.twelve6 = twelve6;
//	}
//	public Integer getThirteen1() {
//		return thirteen1;
//	}
//	public void setThirteen1(Integer thirteen1) {
//		this.thirteen1 = thirteen1;
//	}
//	public Integer getThirteen2() {
//		return thirteen2;
//	}
//	public void setThirteen2(Integer thirteen2) {
//		this.thirteen2 = thirteen2;
//	}
//	public Integer getThirteen3() {
//		return thirteen3;
//	}
//	public void setThirteen3(Integer thirteen3) {
//		this.thirteen3 = thirteen3;
//	}
//	public Integer getThirteen4() {
//		return thirteen4;
//	}
//	public void setThirteen4(Integer thirteen4) {
//		this.thirteen4 = thirteen4;
//	}
//	public Integer getThirteen5() {
//		return thirteen5;
//	}
//	public void setThirteen5(Integer thirteen5) {
//		this.thirteen5 = thirteen5;
//	}
//	public Integer getThirteen6() {
//		return thirteen6;
//	}
//	public void setThirteen6(Integer thirteen6) {
//		this.thirteen6 = thirteen6;
//	}
//	public Integer getFourteen1() {
//		return fourteen1;
//	}
//	public void setFourteen1(Integer fourteen1) {
//		this.fourteen1 = fourteen1;
//	}
//	public Integer getFourteen2() {
//		return fourteen2;
//	}
//	public void setFourteen2(Integer fourteen2) {
//		this.fourteen2 = fourteen2;
//	}
//	public Integer getFourteen3() {
//		return fourteen3;
//	}
//	public void setFourteen3(Integer fourteen3) {
//		this.fourteen3 = fourteen3;
//	}
//	public Integer getFourteen4() {
//		return fourteen4;
//	}
//	public void setFourteen4(Integer fourteen4) {
//		this.fourteen4 = fourteen4;
//	}
//	public Integer getFourteen5() {
//		return fourteen5;
//	}
//	public void setFourteen5(Integer fourteen5) {
//		this.fourteen5 = fourteen5;
//	}
//	public Integer getFourteen6() {
//		return fourteen6;
//	}
//	public void setFourteen6(Integer fourteen6) {
//		this.fourteen6 = fourteen6;
//	}
//	public Integer getFourteen7() {
//		return fourteen7;
//	}
//	public void setFourteen7(Integer fourteen7) {
//		this.fourteen7 = fourteen7;
//	}
//	public Integer getFifteen1() {
//		return fifteen1;
//	}
//	public void setFifteen1(Integer fifteen1) {
//		this.fifteen1 = fifteen1;
//	}
//	public Integer getFifteen2() {
//		return fifteen2;
//	}
//	public void setFifteen2(Integer fifteen2) {
//		this.fifteen2 = fifteen2;
//	}
//	public Integer getFifteen3() {
//		return fifteen3;
//	}
//	public void setFifteen3(Integer fifteen3) {
//		this.fifteen3 = fifteen3;
//	}
//	public Integer getFifteen4() {
//		return fifteen4;
//	}
//	public void setFifteen4(Integer fifteen4) {
//		this.fifteen4 = fifteen4;
//	}
//	public Integer getFifteen5() {
//		return fifteen5;
//	}
//	public void setFifteen5(Integer fifteen5) {
//		this.fifteen5 = fifteen5;
//	}
//	public Integer getFifteen6() {
//		return fifteen6;
//	}
//	public void setFifteen6(Integer fifteen6) {
//		this.fifteen6 = fifteen6;
//	}
//	public Integer getFifteen7() {
//		return fifteen7;
//	}
//	public void setFifteen7(Integer fifteen7) {
//		this.fifteen7 = fifteen7;
//	}
//	public Integer getActiviti4() {
//		return activiti4;
//	}
//	public void setActiviti4(Integer activiti4) {
//		this.activiti4 = activiti4;
//	}
//	public Integer getActiviti5() {
//		return activiti5;
//	}
//	public void setActiviti5(Integer activiti5) {
//		this.activiti5 = activiti5;
//	}
//	public Integer getActiviti6() {
//		return activiti6;
//	}
//	public void setActiviti6(Integer activiti6) {
//		this.activiti6 = activiti6;
//	}
//	public Integer getActiviti7() {
//		return activiti7;
//	}
//	public void setActiviti7(Integer activiti7) {
//		this.activiti7 = activiti7;
//	}
//	public Integer getActiviti8() {
//		return activiti8;
//	}
//	public void setActiviti8(Integer activiti8) {
//		this.activiti8 = activiti8;
//	}
//	public Integer getActiviti9() {
//		return activiti9;
//	}
//	public void setActiviti9(Integer activiti9) {
//		this.activiti9 = activiti9;
//	}
//	public Integer getActiviti10() {
//		return activiti10;
//	}
//	public void setActiviti10(Integer activiti10) {
//		this.activiti10 = activiti10;
//	}
//	public Integer getActiviti11() {
//		return activiti11;
//	}
//	public void setActiviti11(Integer activiti11) {
//		this.activiti11 = activiti11;
//	}
//	public Integer getActiviti12() {
//		return activiti12;
//	}
//	public void setActiviti12(Integer activiti12) {
//		this.activiti12 = activiti12;
//	}
//	public Integer getActiviti13() {
//		return activiti13;
//	}
//	public void setActiviti13(Integer activiti13) {
//		this.activiti13 = activiti13;
//	}
	@Column(name="activiti14")
	public Integer getActiviti14() {
		return activiti14;
	}
	public void setActiviti14(Integer activiti14) {
		this.activiti14 = activiti14;
	}
//	public Integer getActiviti15() {
//		return activiti15;
//	}
//	public void setActiviti15(Integer activiti15) {
//		this.activiti15 = activiti15;
//	}
	public String getEwmPictureUrl() {
		return ewmPictureUrl;
	}
	public void setEwmPictureUrl(String ewmPictureUrl) {
		this.ewmPictureUrl = ewmPictureUrl;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public Integer getChild3() {
		return child3;
	}
	public void setChild3(Integer child3) {
		this.child3 = child3;
	}
	/*public Integer getTotalJifen() {
		return totalJifen;
	}
	public void setTotalJifen(Integer totalJifen) {
		this.totalJifen = totalJifen;
	}*/
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	
	
}
