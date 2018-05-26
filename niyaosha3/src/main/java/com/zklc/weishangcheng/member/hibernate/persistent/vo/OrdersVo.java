package com.zklc.weishangcheng.member.hibernate.persistent.vo;

import java.util.Date;

public class OrdersVo {

	private Integer id;					//主键
	private Integer ordersId;			//订单id
	
	private Integer fromDianpuId;		//来自店铺id
	
	private Integer orderStatus;	//0:未支付,1:已支付,2:异常,3发货,4完成,5退货,6收获,7待用,8待用,9删除
	
	private String pname;			//水果品种
	private Integer productId;		//产品主键
	private Integer pdId;			//店铺订单主键
	private Integer dianpuId;		//店铺id
	private String pictureUrl;		//图片链接
	
	private String toUserName;		//收货人姓名
	private String mobile; 			//收货人电话
	private String shengCode;
	private String chengshiCode;
	private String diquCode;
	private String sheng;
	private String chengshi;
	private String diqu;
	private String address;			//详细地址
	private String zipcode;
	private Double yunfei;			//运费
	
	private String fromUserName;	//发货人姓名
	private String tel;				//发货人电话
	private Integer userId;			//订单提交人id
	private Integer useryId;		//用户微信id
	
	private Date createDate;		//订单创建日期
	private Date payTime;			//用户实际的支付时间
	private Date fahuoDate;			//发货日期
	private Date tuihuoDate;		//退货日期
	private Date shouhuoDate;		//收货日期
	
	
	private String kuaidiName;		//快递公司名称
	private String kuaidiNo;		//快递单号
	
	private String ordersBH;		//订单号
	private Double money;			//支付金额
	private String oUserName;		//报单人姓名
	private String oPhone;			//报单人电话
	private Integer shuliang;		//发货数量
	
	private String comments;		//备注
	
	private Integer dealNum;		//处理次数
	private Date dealDate;			//处理时间
	
	private Double shouyi;			//用户的收益
	private Double xiaohaoShouyi;	//消耗的收益
	
	private Date cancelTime;		//用户取消订单的时间
	private Date refundTime;		//退款时间
	private Integer type;			//订单类型0:金钱订单,1:消耗收益订单
	private Integer pingjia;		//null:未收货,0:待评价,1待追评,2已经评价过了

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

	public Integer getDianpuId() {
		return dianpuId;
	}

	public void setDianpuId(Integer dianpuId) {
		this.dianpuId = dianpuId;
	}

	public Integer getFromDianpuId() {
		return fromDianpuId;
	}

	public void setFromDianpuId(Integer fromDianpuId) {
		this.fromDianpuId = fromDianpuId;
	}

	public String getOrdersBH() {
		return ordersBH;
	}

	public void setOrdersBH(String ordersBH) {
		this.ordersBH = ordersBH;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getShouyi() {
		return shouyi;
	}

	public void setShouyi(Double shouyi) {
		this.shouyi = shouyi;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getPdId() {
		return pdId;
	}

	public void setPdId(Integer pdId) {
		this.pdId = pdId;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getShengCode() {
		return shengCode;
	}

	public void setShengCode(String shengCode) {
		this.shengCode = shengCode;
	}

	public String getChengshiCode() {
		return chengshiCode;
	}

	public void setChengshiCode(String chengshiCode) {
		this.chengshiCode = chengshiCode;
	}

	public String getDiquCode() {
		return diquCode;
	}

	public void setDiquCode(String diquCode) {
		this.diquCode = diquCode;
	}

	public String getSheng() {
		return sheng;
	}

	public void setSheng(String sheng) {
		this.sheng = sheng;
	}

	public String getChengshi() {
		return chengshi;
	}

	public void setChengshi(String chengshi) {
		this.chengshi = chengshi;
	}

	public String getDiqu() {
		return diqu;
	}

	public void setDiqu(String diqu) {
		this.diqu = diqu;
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

	public Double getYunfei() {
		return yunfei;
	}

	public void setYunfei(Double yunfei) {
		this.yunfei = yunfei;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUseryId() {
		return useryId;
	}

	public void setUseryId(Integer useryId) {
		this.useryId = useryId;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getFahuoDate() {
		return fahuoDate;
	}

	public void setFahuoDate(Date fahuoDate) {
		this.fahuoDate = fahuoDate;
	}

	public Date getTuihuoDate() {
		return tuihuoDate;
	}

	public void setTuihuoDate(Date tuihuoDate) {
		this.tuihuoDate = tuihuoDate;
	}

	public Date getShouhuoDate() {
		return shouhuoDate;
	}

	public void setShouhuoDate(Date shouhuoDate) {
		this.shouhuoDate = shouhuoDate;
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

	public String getoUserName() {
		return oUserName;
	}

	public void setoUserName(String oUserName) {
		this.oUserName = oUserName;
	}

	public String getoPhone() {
		return oPhone;
	}

	public void setoPhone(String oPhone) {
		this.oPhone = oPhone;
	}

	public Integer getShuliang() {
		return shuliang;
	}

	public void setShuliang(Integer shuliang) {
		this.shuliang = shuliang;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getDealNum() {
		return dealNum;
	}

	public void setDealNum(Integer dealNum) {
		this.dealNum = dealNum;
	}

	public Date getDealDate() {
		return dealDate;
	}

	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}

	public Double getXiaohaoShouyi() {
		return xiaohaoShouyi;
	}

	public void setXiaohaoShouyi(Double xiaohaoShouyi) {
		this.xiaohaoShouyi = xiaohaoShouyi;
	}

	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	public Date getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPingjia() {
		return pingjia;
	}

	public void setPingjia(Integer pingjia) {
		this.pingjia = pingjia;
	}
	
	
}
