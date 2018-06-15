package com.tw.web.hibernate.persistent;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="orders") 
public class Orders extends AbstractSymBasePO {
	
	private Integer ordersId;
	private Integer order_status;//0:未支付,1:已支付,2:异常,3发货,4完成,5退货,6收货,7待用,8待用,9删除
	
	private String toUserName;//收货人姓名
	private String mobile; //收货人电话
	private String shengCode;
	private String chengshiCode;
	private String diquCode;
	private String sheng;
	private String chengshi;
	private String diqu;
	private String address;
	private String zipcode;
	
	private String fromUserName;//发货人姓名
	private String tel;//发货人电话
	private Integer userId;//订单提交人id
	private Integer useryId;//订单提交人id
	private String pname;//水果品种
	private Date createDate;//订单创建日期
	private Date payTime;			//用户实际的支付时间
	private Date fahuoDate;//发货日期
	private Date tuihuoDate;//退货日期
	private Date shouhuoDate;//收货日期
	
	private String kuaidiName;//快递公司名称
	private String kuaidiNo;//快递单号
	
	private String ordersBH;//订单号
	private Double money;//支付金额
	private String oUserName;//报单人姓名
	private String oPhone;//报单人电话
	private Double shuliang;//发货数量
	private String size;//发货数量单位
	
	private String comments;//备注
	private String pictureUrl;//异常图片链接
	private Integer productId;		//产品主键
	private Integer dealNum;//处理次数
	private Date dealDate;//处理时间
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ordersId", unique = true, nullable = false)
	public Integer getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(Integer ordersId) {
		this.ordersId = ordersId;
	}
	
	public Integer getOrder_status() {
		return order_status;
	}
	public void setOrder_status(Integer order_status) {
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
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
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
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
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
	public Double getShuliang() {
		return shuliang;
	}
	public void setShuliang(Double shuliang) {
		this.shuliang = shuliang;
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
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
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
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Integer getUseryId() {
		return useryId;
	}
	public void setUseryId(Integer useryId) {
		this.useryId = useryId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
}
