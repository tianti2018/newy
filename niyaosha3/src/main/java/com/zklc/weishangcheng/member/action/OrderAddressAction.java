package com.zklc.weishangcheng.member.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.zklc.framework.action.BaseAction;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderAddress;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.service.OrderAddressService;
import com.zklc.weishangcheng.member.service.TareaService;

@SuppressWarnings("serial")
@ParentPackage("json")
@Namespace("/orderAddress")
@Action(value = "orderAddressAction")
@Results({
	@Result(name = "rRechargePage",type = "redirect", location = "/order/orderAction!goRecharge.action"),
	@Result(name = "orderAddressPage", location = "/WEB-INF/jsp/orderAddress.jsp"),
	@Result(name = "editPage", location = "/WEB-INF/jsp/orderAddress_add.jsp"),
	@Result(name = "timeOut", location = "/WEB-INF/jsp/denglu.jsp"),
	@Result(name = "ajaxResult", type = "json", params = {"message","message"}),
	@Result(name = "gobackList4", type = "redirect", location = "/orderAddress/orderAddressAction!orderAddress.action")

})
/**
 * 订单收货地址action
 * @author bai
 *
 */
public class OrderAddressAction extends BaseAction {
	//@Result(name = "rEditPage", type = "redirect", params = {"id", "${id}" },location = "/orderAddress/orderAddressAction!editPage.action")
	@Autowired
	private OrderAddressService orderAddressService;
	@Autowired
	private TareaService tareaService;
	
	public OrderAddress orderAddress;
	private Users user;
	private Integer id;
	private String message;//返回状态
	public String ckDefaultAddr;//默认地址
	private Integer pdId;
	private Integer productId;
	
	/**
	 * 我的收货地址页
	 * @return
	 */
	public String orderAddress(){
		userVo = getSessionUser();
		if(userVo == null){
			return "timeOut";
		}
		List<OrderAddress> tempList = orderAddressService.listAllAddress(userVo);
		request.setAttribute("addressList", tempList);
		request.setAttribute("productId", productId);
		request.setAttribute("pdId", pdId);
		return "orderAddressPage";
	}
	
	/**
	 * ajax设置默认地址
	 * @return
	 */
	public void ajaxDefaultAddr(){
		json.put("message", "error");
		userVo = getSessionUser();
		if(id!=null){
			userVo = orderAddressService.defaultAddr(id,userVo);
			session.setAttribute("loginUser", userVo);
			json.put("message", "success");
		}
		try {
			jsonOut(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ajax删除地址
	 * @return
	 */
	public void ajaxDelAddr(){
		userVo =getSessionUser();
		json.put("message", "error");
		if(id!=null){
			orderAddress = orderAddressService.findById(id);
			if(userVo!=null){
				if(userVo.getOrderAddress()!=null&&userVo.getOrderAddress().getId().equals(id)){
					userVo.setOrderAddress(null);
					session.setAttribute("loginUser", userVo);
				}
			}
			orderAddressService.delete(orderAddress);
			json.put("message", "success");
		}
		try {
			jsonOut(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 前往新增收货地址页
	 * @return
	 */
	public String editPage(){
		request.setAttribute("litTArea_sheng", tareaService.findByProperty("parentCode", "0"));
		return "editPage";
	}
	
	
	
	/**
	 * 修改收货地址
	 */
	public String updatePage(){
		if(id!=null){
			orderAddress=orderAddressService.findById(id);
		}
		request.setAttribute("litTArea_sheng", tareaService.findByProperty("parentCode", "0"));
		request.setAttribute("orderAddress", orderAddress);
		return "editPage";
	}
	
	
	/**
	 * 新增或保存收货地址
	 * @return
	 */
	public String saveEntity(){
		userVo = getSessionUser();
		//user = userService.findById(3480);
		if(userVo!=null){
			if(orderAddress!=null){
				//更新对象
				if(orderAddress.getId()!=null){
					orderAddressService.update(orderAddress);
				}else//新增对象
				{
					orderAddress.setCreateDate(new Date());
					if(userVo.getUser()!=null)
						orderAddress.setUserId(userVo.getUser().getUserId());
					if(userVo.getUsery()!=null)
						orderAddress.setUseryId(userVo.getUsery().getId());
					orderAddress.setIsFirst("0");
					orderAddressService.save(orderAddress);
				}
				id=orderAddress.getId();
				//设置为默认地址
				if (null != ckDefaultAddr && ckDefaultAddr.equals("on")) {
					userVo = orderAddressService.defaultAddr(id,userVo);
					userVo.setOrderAddress(orderAddress);
					session.setAttribute("loginUser", userVo);
				}

				//返回到商城首页
				return "gobackList4";
			}
		}
		
		return "error";
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

	public Integer getPdId() {
		return pdId;
	}

	public void setPdId(Integer pdId) {
		this.pdId = pdId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}


}
