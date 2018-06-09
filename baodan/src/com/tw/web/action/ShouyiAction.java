package com.tw.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.ObjectUtils.Null;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.Namespace;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionChainResult;
import com.tw.web.dao.HongbaoyDao;
import com.tw.web.dao.ShouYiForUserDao;
import com.tw.web.hibernate.persistent.AdminUser;
import com.tw.web.hibernate.persistent.Hongbaoy;
import com.tw.web.hibernate.persistent.Orders;
import com.tw.web.hibernate.persistent.ShouYiForUser;
import com.tw.web.hibernate.persistent.User;
import com.tw.web.hibernate.persistent.Usery;

import net.sf.json.JSONObject;

@SuppressWarnings("serial")
@ParentPackage("app-default")
@Namespace("")
@Results(
		{
			
			@Result(name="error", 		value="/login.jsp"),	
			@Result(name="listAllHongbao", value="/WEB-INF/jsp/shouyi/listAllHongbao.jsp"),
			@Result(name="listAllShouyi", value="/WEB-INF/jsp/shouyi/listAllShouyi.jsp"),	
			@Result(name="listAllTixian", value="/WEB-INF/jsp/shouyi/listAllTixian.jsp"),	
			@Result(name="gobackListAllappCenter", type=ActionChainResult.class, 	value="member", params = {"method", "listAllappCenter"}),
			
		}
)
public class ShouyiAction extends ExtJSONActionSuport {

	@Autowired
	private HongbaoyDao hongbaoyDao;
	@Autowired
	private ShouYiForUserDao shouYiForUserDao;
	
	private String daKuanDan;		//打款单
	private Integer hongBaoId;		//红包记录主键
	private Integer shouyiId;		//收益记录主键
	private Integer dianpuId;		//店铺主键
	private Integer shouyiType;		//收益记录的类型
	private String fromDate;		//开始时间
	private String endDate;			//结束时间
	private Integer status;			//状态
	private Integer result;			//结果
	private Integer billNo;			//本地红包单号
	private Integer wxBillNo;		//微信返回红包单号
	private String ordersBH;		//订单编号
	private Integer orderType;		//订单类型
	private Integer ordersId;		//订单主键
	private String totalMoney;		//所有钱数
	private String statusz;			//红包状态
	
	public void tongguo(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Object obj = request.getSession().getAttribute("user");
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", false);
		AdminUser user=null;
		if (obj instanceof AdminUser) {
			user = (AdminUser)obj;
		}
		if(user==null){
			jsonObject.put("timeOut", true);
		}
		if(user!=null){
			ShouYiForUser shouYiForUser = (ShouYiForUser) shouYiForUserDao.findById(shouyiId);
			if(shouYiForUser!=null){
				Hongbaoy hongbaoy = shouYiForUserDao.tongguo(shouYiForUser);
				jsonObject.put("success", true);
				/*String message = shouYiForUserDao.fahongbao(hongbaoy);
				if(message!=""){
					jsonObject.put("success", true);
					jsonObject.put("message", message);
				}*/
			}
		}
		
		try {
			response.getWriter().println(jsonObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String listAllShouyi(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Object obj = request.getSession().getAttribute("user");
		if(obj == null){
			return "error";
		}
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		Map<String, Integer> compare = new HashMap<String, Integer>();
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("createDate", false);
		String sql = "select sum(shouyi) from shouyi_user where 1=1 ";
		
		sql += " and status =2 ";
		conditionProperties.put("status", 2);
		compare.put("status", 0);
		
		if (null!=dianpuId &&! "".equals(dianpuId)) {
			conditionProperties.put("dianpuId", dianpuId);
			compare.put("dianpuId", 0);
			sql += " and dianpuId ="+dianpuId;
		}
		if (null!=ordersBH &&! "".equals(ordersBH.trim())) {
			conditionProperties.put("ordersBH", ordersBH.trim());
			compare.put("ordersBH", 2);
			sql += " and ordersBH like '%"+ordersBH.trim()+"%'";
		}
		int count_size =shouYiForUserDao.cout_size_Commen(conditionProperties, compare);
		List sumList=shouYiForUserDao.findBySql(sql);
		totalMoney = "0";
		if(sumList!=null&&sumList.size()>0){
			java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
			// 不使用千分位，即展示为11672283.234，而不是11,672,283.234
			nf.setGroupingUsed(false);
			// 设置数的小数部分所允许的最小位数
			nf.setMinimumFractionDigits(0);
			// 设置数的小数部分所允许的最大位数
			nf.setMaximumFractionDigits(5);
			if(sumList.get(0)!=null){
				totalMoney = nf.format(sumList.get(0));
			}
		}
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count_size));
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
		
		List<ShouYiForUser> litPager = shouYiForUserDao.findAllPagerList(conditionProperties, compare, sort, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		request.setAttribute("litPager", litPager);
		request.setAttribute("totalMoney", totalMoney);
	
		return "listAllShouyi";
	}
	
	public String listAllTixian(){

		HttpServletRequest request = ServletActionContext.getRequest();
		
		Object obj = request.getSession().getAttribute("user");
		if(obj == null){
			return "error";
		}
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		Map<String, Integer> compare = new HashMap<String, Integer>();
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("createDate", false);
		String sql = "select sum(shouyi) from shouyi_user where 1=1 ";
		
		sql += " and status =2 and tixian = 1 ";
		conditionProperties.put("status", 2);
		compare.put("status", 0);
		conditionProperties.put("tixian", 1);
		compare.put("tixian", 0);
		
		if (null!=dianpuId &&! "".equals(dianpuId)) {
			conditionProperties.put("dianpuId", dianpuId);
			compare.put("dianpuId", 0);
			sql += " and dianpuId ="+dianpuId;
		}
		if (null!=ordersBH &&! "".equals(ordersBH.trim())) {
			conditionProperties.put("ordersBH", ordersBH.trim());
			compare.put("ordersBH", 2);
			sql += " and ordersBH like '%"+ordersBH.trim()+"%'";
		}
		int count_size =shouYiForUserDao.cout_size_Commen(conditionProperties, compare);
//		List sumList=shouYiForUserDao.findBySql(sql);
//		totalMoney = "0";
//		if(sumList!=null&&sumList.size()>0){
//			java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
//			// 不使用千分位，即展示为11672283.234，而不是11,672,283.234
//			nf.setGroupingUsed(false);
//			// 设置数的小数部分所允许的最小位数
//			nf.setMinimumFractionDigits(0);
//			// 设置数的小数部分所允许的最大位数
//			nf.setMaximumFractionDigits(5);
//			if(sumList.get(0)!=null){
//				totalMoney = nf.format(sumList.get(0));
//			}
//		}
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count_size));
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
		
		List<ShouYiForUser> litPager = shouYiForUserDao.findAllPagerList(conditionProperties, compare, sort, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		request.setAttribute("litPager", litPager);
//		request.setAttribute("totalMoney", totalMoney);
	
		return "listAllTixian";
	}
	
	public String listAllHongbao(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Object obj = request.getSession().getAttribute("user");
		if(obj == null){
			return "error";
		}
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		Map<String, Integer> compare = new HashMap<String, Integer>();
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("addTime", false);
		String sql = "select sum(amount) from hongbaoy where 1=1 ";
		
		if(statusz!=null&&!statusz.equals("")){
			conditionProperties.put("statusz", statusz);
			compare.put("statusz", 0);
			sql += " and statusz = '"+statusz+"'";
		}
		
		if (null!=dianpuId &&! "".equals(dianpuId)) {
			conditionProperties.put("dianpuId", dianpuId);
			compare.put("dianpuId", 0);
			sql += " and dianpuId ="+dianpuId;
		}
		int count_size =hongbaoyDao.cout_size_Commen(conditionProperties, compare);
		List sumList=hongbaoyDao.findBySql(sql);
		totalMoney = "0";
		if(sumList!=null&&sumList.size()>0){
			java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
			// 不使用千分位，即展示为11672283.234，而不是11,672,283.234
			nf.setGroupingUsed(false);
			// 设置数的小数部分所允许的最小位数
			nf.setMinimumFractionDigits(0);
			// 设置数的小数部分所允许的最大位数
			nf.setMaximumFractionDigits(5);
			if(sumList.get(0)!=null){
				totalMoney = nf.format(sumList.get(0));
			}
		}
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count_size));
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
		
		List<Hongbaoy> litPager = hongbaoyDao.findAllPagerList(conditionProperties, compare, sort, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		request.setAttribute("litPager", litPager);
		request.setAttribute("totalMoney", totalMoney);
		return "listAllHongbao";
	}

	public HongbaoyDao getHongbaoyDao() {
		return hongbaoyDao;
	}

	public void setHongbaoyDao(HongbaoyDao hongbaoyDao) {
		this.hongbaoyDao = hongbaoyDao;
	}

	public ShouYiForUserDao getShouYiForUserDao() {
		return shouYiForUserDao;
	}

	public void setShouYiForUserDao(ShouYiForUserDao shouYiForUserDao) {
		this.shouYiForUserDao = shouYiForUserDao;
	}

	public String getDaKuanDan() {
		return daKuanDan;
	}

	public void setDaKuanDan(String daKuanDan) {
		this.daKuanDan = daKuanDan;
	}


	public Integer getShouyiId() {
		return shouyiId;
	}

	public void setShouyiId(Integer shouyiId) {
		this.shouyiId = shouyiId;
	}

	public Integer getDianpuId() {
		return dianpuId;
	}

	public void setDianpuId(Integer dianpuId) {
		this.dianpuId = dianpuId;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getHongBaoId() {
		return hongBaoId;
	}

	public void setHongBaoId(Integer hongBaoId) {
		this.hongBaoId = hongBaoId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public Integer getBillNo() {
		return billNo;
	}

	public void setBillNo(Integer billNo) {
		this.billNo = billNo;
	}

	public Integer getWxBillNo() {
		return wxBillNo;
	}

	public void setWxBillNo(Integer wxBillNo) {
		this.wxBillNo = wxBillNo;
	}

	public String getOrdersBH() {
		return ordersBH;
	}

	public void setOrdersBH(String ordersBH) {
		this.ordersBH = ordersBH;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public void setShouyiType(Integer shouyiType) {
		this.shouyiType = shouyiType;
	}

	public Integer getShouyiType() {
		return shouyiType;
	}

	public Integer getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(Integer ordersId) {
		this.ordersId = ordersId;
	}

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getStatusz() {
		return statusz;
	}

	public void setStatusz(String statusz) {
		this.statusz = statusz;
	}
	
	
}
