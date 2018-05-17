package com.zklc.weishangcheng.member.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.utils.MyUtils;
import com.zklc.framework.action.BaseAction;
import com.zklc.weishangcheng.member.hibernate.persistent.City;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderAddress;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.OrderAddressVO;
import com.zklc.weishangcheng.member.service.CityService;
import com.zklc.weishangcheng.member.service.OrderAddressService;
import com.zklc.weishangcheng.member.service.UserService;
import com.zklc.weishangcheng.member.service.WeixinAutosendmsgService;

@ParentPackage("json")
@Namespace("/orderAddress")
@Action(value = "orderAddressAction")
@Results({
		@Result(name = "rRechargePage",type = "redirect", location = "/order/orderAction!goRecharge.action"),
		@Result(name = "orderAddressPage", location = "/WEB-INF/jsp/orderAddress.jsp"),
		@Result(name = "editPage", location = "/WEB-INF/jsp/orderAddress_add.jsp"),
		@Result(name = "ajaxResult", type = "json", params = {"message","message"}),
		@Result(name = "rEditPage", type = "redirect", location = "/user/userAction!jampshopIndex.action")
		

})
/**
 * 订单收货地址action
 * @author jazz
 *
 */
public class OrderAddressAction extends BaseAction {
	//@Result(name = "rEditPage", type = "redirect", params = {"id", "${id}" },location = "/orderAddress/orderAddressAction!editPage.action")
	@Autowired
	private OrderAddressService orderAddressService;
	@Autowired
	private UserService userService; 
	
	@Autowired
	private WeixinAutosendmsgService autosendmsgService;
	@Autowired
	private CityService cityService;
	
	public OrderAddress orderAddress;
	private Users user;
	private Integer id;
	private String message;//返回状态
	public String ckDefaultAddr;//默认地址
	
	public String code;
	public String wxOpenid;
	private Integer userId;
	/**
	 * 我的收货地址页
	 * @return
	 */
	public String orderAddress(){
		userVo = getSessionUser();
		user = userVo.getUser();
		
 		if(user == null){
			return "timeOut";
		}
		//user = userService.findById(3480);
		List<OrderAddress> addresslist=orderAddressService.findByUserId(user.getUserId());
		List<City> cities=new ArrayList<City>();
		List<Integer> idlist=new ArrayList<Integer>();
		if(MyUtils.isNotEmpty(addresslist))
		{
			for(OrderAddress od :addresslist)
			{
				if(MyUtils.isNotEmpty(od.getProvinceId()) && od.getProvinceId()>0)
				{
					idlist.add(od.getProvinceId());
				}
				if(MyUtils.isNotEmpty(od.getCityId()) && od.getCityId()>0)
				{
					idlist.add(od.getCityId());
				}
				if(MyUtils.isNotEmpty(od.getAreaId()) && od.getAreaId()>0)
				{
					idlist.add(od.getAreaId());
				}
			}
		}
		String ids=StringUtils.join(idlist, ",");
		cities=cityService.getByIds(ids);
		List<OrderAddressVO> volist=new ArrayList<OrderAddressVO>();
		for(OrderAddress oa :addresslist)
		{
			OrderAddressVO vo=new OrderAddressVO();
			vo.setId(oa.getId());
			vo.setAddress(oa.getAddress());
			vo.setCreateDate(oa.getCreateDate());
			vo.setIsFirst(oa.getIsFirst());
			vo.setMobile(oa.getMobile());
			vo.setUserId(oa.getUserId());
			vo.setUserName(oa.getUserName());
			vo.setZipcode(oa.getZipcode());
			for(City c :cities)
			{
				if(c.getId().equals(oa.getProvinceId()))
				{
					vo.setProvince(c.getName());
				}
				if(c.getId().equals(oa.getCityId()))
				{
					vo.setCity(c.getName());
				}
				if(c.getId().equals(oa.getAreaId()))
				{
					vo.setRegion(c.getName());
				}
			}
			volist.add(vo);
		}
		request.setAttribute("addressList", volist);
		return "orderAddressPage";
	}

	/**
	 * ajax设置默认地址
	 * @return
	 */
	public String ajaxDefaultAddr(){
		userVo = getSessionUser();
		user = userVo.getUser();
		message="error";
		if(id!=null){
			orderAddressService.defaultAddr(id,user.getUserId());
			message="success";
			return "ajaxResult";
		}else{
			return null;
		}
	}
	/**
	 * ajax删除地址
	 * @return
	 */
	public String ajaxDelAddr(){
		message="error";
		if(id!=null){
			orderAddressService.delete(orderAddressService.findById(id));
			message="success";
			return "ajaxResult";
		}else{
			return null;
		}
	}
	
	
	
	/**
	 * 前往新增收货地址页
	 * @return
	 */
	public String editPage(){
		userVo = getSessionUser();
		user = userVo.getUser();
//			user = userService.findById(3480);
			//如果已有五个地址，则不准继续增加
			List<OrderAddress> tempList=orderAddressService.findByUserId(user.getUserId());//("userId", user.getUserId());
			System.out.println("已有收货地址"+tempList.size()+"个");
			if(tempList!=null&&tempList.size()>=15){
				message="只能添加十五个收货地址！";
				return "editPage";
			}
		 List<City> citylist=new ArrayList<City>();
		 List<City> arealist=new ArrayList<City>();
		if(orderAddress!=null &&MyUtils.isGtZero(orderAddress.getProvinceId()))
		{
			citylist=cityService.findByParentId(orderAddress.getProvinceId());
		}
		if(orderAddress!=null &&MyUtils.isGtZero(orderAddress.getCityId()))
		{
			arealist=cityService.findByParentId(orderAddress.getCityId());
		}
		request.setAttribute("provincelist", cityService.findByLevel(1));
		request.setAttribute("citylist", citylist);
		request.setAttribute("arealist", arealist);
		request.setAttribute("orderAddress", orderAddress);
		return "editPage";
	}
	
	
	
	/**
	 * 修改收货地址
	 */
	public String updatePage(){
		userVo = getSessionUser();
		user = userVo.getUser();
//		user = userService.findById(3480);
	if(id!=null){
		orderAddress=orderAddressService.findById(id);
		if(!user.getUserId().equals(orderAddress.getUserId())) {
			orderAddress=null;
		}
	}
	 List<City> citylist=new ArrayList<City>();
	 List<City> arealist=new ArrayList<City>();
	if(orderAddress!=null &&MyUtils.isGtZero(orderAddress.getProvinceId()))
	{
		citylist=cityService.findByParentId(orderAddress.getProvinceId());
	}
	if(orderAddress!=null &&MyUtils.isGtZero(orderAddress.getCityId()))
	{
		arealist=cityService.findByParentId(orderAddress.getCityId());
	}
	request.setAttribute("provincelist", cityService.findByLevel(1));
	request.setAttribute("citylist", citylist);
	request.setAttribute("arealist", arealist);
	request.setAttribute("orderAddress", orderAddress);
	return "editPage";
}
	
	
	/**
	 * 新增或保存收货地址
	 * @return
	 */
	public String saveEntity(){
		userVo = getSessionUser();
		user = userVo.getUser();
		//user = userService.findById(3480);
		if(orderAddress!=null){
			//更新对象
			if(orderAddress.getId()!=null){
				orderAddress.setCreateDate(new Date());
				orderAddressService.update(orderAddress);
			}else
			//新增对象
			{
				//如果已有五个地址，则不准继续增加
				List<OrderAddress> tempList=orderAddressService.findByProperty("userId", user.getUserId());
				if(tempList!=null&&tempList.size()>=5){
					message="只能添加五个收货地址！";
					return "editPage";
				}
				orderAddress.setCreateDate(new Date());
				orderAddress.setUserId(user.getUserId());
				orderAddress.setIsFirst("1");
				orderAddressService.save(orderAddress);
			}
			id=orderAddress.getId();
			//设置为默认地址
			if (null != ckDefaultAddr && ckDefaultAddr.equals("on")) {
				orderAddressService.defaultAddr(id,user.getUserId());
			}

			//返回到商城首页
			return "rEditPage";
		}
		return null;
	}
	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public OrderAddress getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(OrderAddress orderAddress) {
		this.orderAddress = orderAddress;
	}

	public String getCkDefaultAddr() {
		return ckDefaultAddr;
	}

	public void setCkDefaultAddr(String ckDefaultAddr) {
		this.ckDefaultAddr = ckDefaultAddr;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getWxOpenid() {
		return wxOpenid;
	}

	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
