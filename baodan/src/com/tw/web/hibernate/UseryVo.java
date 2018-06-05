package com.tw.web.hibernate;

public class UseryVo {

	private String useryname;				//昵称
	private String phone;
	private String loginName;
	private Integer id;						//主键
	private String userName;				//姓名
	private String headUrl;					//头像连接
	private Integer parentId;				//推荐人useryId
	private Integer level;					
	private Integer dianPuId;				//店铺id
	
	/**
	 * 0 未取消关注 1 取消关注
	 */
	private Integer subscribe;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
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


	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getDianPuId() {
		return dianPuId;
	}

	public void setDianPuId(Integer dianPuId) {
		this.dianPuId = dianPuId;
	}
	public String getUseryname() {
		return useryname;
	}

	public void setUseryname(String useryname) {
		this.useryname = useryname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
}
