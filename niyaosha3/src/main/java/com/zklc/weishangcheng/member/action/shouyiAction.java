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
import com.zklc.weishangcheng.member.hibernate.persistent.ShouYiForUser;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.UserVo;
import com.zklc.weishangcheng.member.service.ShouYiForUserService;
import com.zklc.weishangcheng.member.service.UserService;
import com.zklc.weishangcheng.member.service.UseryService;
import com.zklc.weishangcheng.member.util.PublicUtil;

import net.sf.json.JSONObject;

@SuppressWarnings("serial")
@ParentPackage("json")
@Namespace("/shouyi")
@Action(value = "shouyiAction")
@Results({
		@Result(name = "myshouyiList", location = "/WEB-INF/jsp/shouyi/phone_my_shouyi.jsp"),//我的订单
		@Result(name = "product", location = "/WEB-INF/jsp/product.jsp"),
		@Result(name = "products", location = "/WEB-INF/jsp/products.jsp"),
})
public class shouyiAction extends BaseAction {
	@Autowired
	private UserService userService;
	@Autowired
	private UseryService useryService;
	@Autowired
	private ShouYiForUserService shouyiService;

	private Integer pageNum;
	public String message;
	public Integer orderId;
	private String total_price1;
	private String qty_item_1;
	
	private String orderType;
	private String date1;
	private String date2;
	private Double money;
	
	public void tixian(){
		userVo = getSessionUser();
		json=yanzheng(userVo);
		if(json.get("code").equals("000000")){
			Integer dianpuId = userVo.getUsery().getDianPuId(); 
			Double yve = shouyiService.findYvE(dianpuId);
			if(money%10==0.0&&money.compareTo(0.0)>0){
				if(money.compareTo(yve)<=0){
					ShouYiForUser shouYiForUser = new ShouYiForUser();
					shouYiForUser.setBeizhu("提现");
					shouYiForUser.setCreateDate(new Date());
					shouYiForUser.setDianpuId(dianpuId);
					shouYiForUser.setShouyi(-money);
					shouYiForUser.setStatus(2);
					shouYiForUser.setTixian(1);
					shouyiService.save(shouYiForUser);
				}else{
					json.put("code", "001001");//余额不足
				}
			}else{
				json.put("code", "001010");//提现金额格式不对
			}
			
		}			
		try {
			jsonOut(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadUserMoneyAll(){
		Double yve = 0.0;
		Double shouru =0.0;
		Double zhichu = 0.0;
		Double daishengxiao = 0.0;
		userVo = getSessionUser();
		json = yanzheng(userVo);
		if(json.get("code").equals("000000")){
			//COALESCE(SUM(name),0) 
			Integer dianpuId = userVo.getUsery().getDianPuId(); 
			yve = shouyiService.findYvE(dianpuId);
			shouru = shouyiService.findShouRu(dianpuId);
			zhichu = shouyiService.findZhiChu(dianpuId);
			daishengxiao = shouyiService.findDaiShengXiao(dianpuId);
		}
		json.put("yve", yve);
		json.put("shouru", shouru);
		json.put("zhichu", zhichu);
		json.put("daishengxiao", daishengxiao);
		try {
			jsonOut(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showMoney(){
		json.put("success", false);
		Double yve = 0.0;
		Double shenqingzhong = 0.0;
		Double yitongguo = 0.0;
		userVo = getSessionUser();
		json = yanzheng(userVo);
		if(json.get("code").equals("000000")){
			//COALESCE(SUM(name),0) 
			Integer dianpuId = userVo.getUsery().getDianPuId(); 
			yve = shouyiService.findYvE(dianpuId);
			shenqingzhong = shouyiService.findShenQing(dianpuId);
			yitongguo = shouyiService.findYiTongGuo(dianpuId);
		}
		json.put("yve", yve);
		json.put("shenqingzhong", shenqingzhong);
		json.put("yitongguo", yitongguo);
		try {
			jsonOut(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String myshouyiList(){
		//获取用户信息
		userVo = getSessionUser();
		if(userVo == null){
			return "timeOut";
		}
			
		return "myshouyiList";
			
	}

	public void ajaxmyShouyiList(){
		userVo = getSessionUser();
		if(userVo!=null){
			json.put("success", false);
			List<ShouYiForUser> shouYiForUsers = shouyiService.findShouyiList(userVo,orderType,pageNum,date1,date2);
	        if(shouYiForUsers!=null && shouYiForUsers.size()>0){
	        	json.put("success", true);
	        	json.put("children", shouYiForUsers);
	        }
		}
		
		try {
			jsonOut(json);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}
	
	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public String getDate1() {
		return date1;
	}
	public void setDate1(String date1) {
		this.date1 = date1;
	}
	public String getDate2() {
		return date2;
	}
	public void setDate2(String date2) {
		this.date2 = date2;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getTotal_price1() {
		return total_price1;
	}

	public void setTotal_price1(String total_price1) {
		this.total_price1 = total_price1;
	}

	public String getQty_item_1() {
		return qty_item_1;
	}

	public void setQty_item_1(String qty_item_1) {
		this.qty_item_1 = qty_item_1;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	} 
	
}
