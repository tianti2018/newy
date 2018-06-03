package com.tw.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.Namespace;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionChainResult;
import com.tw.web.dao.ApplyReturnPurchaseDao;
import com.tw.web.dao.OrdersDAO;
import com.tw.web.dao.OrdersProductsDAO;
import com.tw.web.dao.ProductsDAO;
import com.tw.web.dao.TAreaDao;
import com.tw.web.dao.UserDAO;
import com.tw.web.dao.UserOrderDAO;
import com.tw.web.hibernate.persistent.AdminUser;
import com.tw.web.hibernate.persistent.ApplyReturnPurchase;
import com.tw.web.hibernate.persistent.Orders;
import com.tw.web.hibernate.persistent.Products;
import com.tw.web.hibernate.persistent.TArea;
import com.tw.web.hibernate.persistent.User;
import com.tw.web.hibernate.persistent.UserOrder;
import com.tw.web.service.LoginService;
import com.tw.web.util.CommUtils;

import sun.misc.BASE64Encoder;

@SuppressWarnings("serial")
@ParentPackage("app-default")
@Namespace("")
@Results(
		{
			
			@Result(name="quchongzhi", 			value="/WEB-INF/jsp/products/chongzhiOrder.jsp"),
			@Result(name="initAdd", 			value="/WEB-INF/jsp/products/editorder.jsp"),
			@Result(name="block", 			value="/block.html"),
			@Result(name="yichang", 			value="/WEB-INF/jsp/products/addyichang.jsp"),
			@Result(name="importExcel", 			value="/WEB-INF/jsp/upload/importExcel.jsp"),
			@Result(name="listYichang", 			value="/WEB-INF/jsp/products/yichangordersList.jsp"),
			@Result(name="initModify", 		value="/WEB-INF/jsp/products/addProducts.jsp"),
			@Result(name="create", 				value="/WEB-INF/jsp/products/productsList.jsp"),
			@Result(name="listAll", 			value="/WEB-INF/jsp/products/productsList.jsp"),
			@Result(name="listAllApplys", 			value="/WEB-INF/jsp/products/listAllApplys.jsp"),
			@Result(name="ordersList", 			value="/WEB-INF/jsp/products/ordersList.jsp"),
			@Result(name="tuanduiCaiwu", 			value="/WEB-INF/jsp/products/tuanduiCaiwuList.jsp"),
			
			@Result(name="caiwuList", 			value="/WEB-INF/jsp/products/caiwuList.jsp"),
			@Result(name="listAll", 			value="/WEB-INF/jsp/products/allOrders.jsp"),
			@Result(name="initkuaidi", 			value="/WEB-INF/jsp/products/addKuaidi.jsp"),
			@Result(name="beizhu", 			value="/WEB-INF/jsp/products/addbeizhu.jsp"),
			@Result(name="applyReturnPurchase", 			value="/WEB-INF/jsp/products/applyReturnPurchase.jsp"),
			@Result(name="goBackList", type=ActionChainResult.class, 	value="role", params = {"method", "listAll"}),
			@Result(name="goBackList1", type=ActionChainResult.class, 	value="orders", params = {"method", "ordersList"}),
			@Result(name="dealApply", type=ActionChainResult.class, 	value="orders", params = {"method", "listAllApplys"}),
			@Result(name="error", type=ActionChainResult.class, 	value="login", params = {"method", "doLogin"}),
			@Result(name="adminerror", type=ActionChainResult.class, 	value="login", params = {"method", "doAdminLogin"})
		}
)
public class OrdersAction extends ExtJSONActionSuport {
	
	private LoginService loginService;
	private ProductsDAO productsDAO;
	private OrdersDAO ordersDAO;
	private OrdersProductsDAO ordersProductsDAO;
	private UserOrderDAO userOrderDAO;
	private UserDAO userDAO;
	private TAreaDao tAreaDao;
	private ApplyReturnPurchaseDao applyReturnPurchaseDao;
	
	private String toUserName;//收货人电话
	private String money;//金额
	private String mobile;//收货人电话
	private String fromUserName;//发货人姓名
	private String tel;//发货人电话
	private String address;//收货地址
	private String zipcode;//邮编
	private String pname;//产品名称
	private Integer ordersId;//产品id
	private String kuaidiName;//快递名称
	private String kuaidiNo;//快递编号
	private String ordersBH;//订单编号
	private String loginName;//登录名称
	private String userName;//用户名称
	private String orderType;//订单类型
	private String applyNum;//退货数量
	private Integer userId;//订单人id
	private File importFile;//导入文件
	private String totalMoney;
	private String fromDate;//起始日期
	private String endDate;//截止日期
	private String size;//货物单位
	private String shuliang;//货物数量
	private String sheng;//省
	private String chengshi;//市
	private String diqu;//地区
	private String shengCode;//省代码
	private String chengshiCode;//城市代码
	private String diquCode;//地区代码
	private String oPhone;//报单人电话
	private String oUserName;//报单人名称
	private String dateType;//查询时间的类型
	private Integer order_status;//订单状态
	private String pictureUrl;//图片url
	private String comments;//信息
	private Integer[] orderIds;
	private Integer paixu;//0正序1倒序
	
	public String chongzhiOrder(){
		Orders orders = (Orders)ordersDAO.findById(ordersId);
//		User user = null;
		AdminUser adminUser = (AdminUser) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(adminUser == null){
			return "adminerror";
		}
		if(orders!=null){
			if(order_status!=null){
				orders.setOrder_status(order_status);
				ordersDAO.update(orders);
			}
		}
		return "goBackList1";
	}
	
	public String quchongzhi(){
		Orders orders = (Orders)ordersDAO.findById(ordersId);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("orders", orders);
		return "quchongzhi";
	}
	
	public String zhifuOrder(){
		Orders orders = (Orders) ordersDAO.findById(ordersId);
		orders.setOrder_status(1);
		ordersDAO.update(orders);
		return "goBackList1";
	}
	public String shouhuo(){
		Orders orders = (Orders) ordersDAO.findById(ordersId);
		orders.setOrder_status(6);
		orders.setShouhuoDate(new Date());
		ordersDAO.update(orders);
		return "goBackList1";
	}
	
	public String initImport(){
		return "importExcel";
	}
	
	public String gotoYichang(){
		
		Orders orders = (Orders)ordersDAO.findById(ordersId);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("orders", orders);
		return "yichang";
	}
	
	public String yichangOrder(){
		if(ordersId!=null){
			Orders orders = (Orders) ordersDAO.findById(ordersId);
			orders.setPictureUrl(pictureUrl);
			orders.setComments(comments);
			orders.setOrder_status(2);
			ordersDAO.update(orders);
		}
		return "goBackList1";
	}
	
	public String dealYichang(){
		if(ordersId!=null){
			Orders orders = (Orders) ordersDAO.findById(ordersId);
			orders.setDealNum(orders.getDealNum()==null?1:(orders.getDealNum()+1));
			orders.setDealDate(new Date());
			orders.setOrder_status(3);
			ordersDAO.update(orders);
		}
		return "goBackList1";
	}
	
	public String delete(){
		if(ordersId!=null){
			Orders orders = (Orders) ordersDAO.findById(ordersId);
			if(orders!=null){
				orders.setOrder_status(9);
			}
			ordersDAO.update(orders);
		}
		return "goBackList1";
	}
	
	public String listYichang() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Object obj = request.getSession().getAttribute("user");
		if(obj == null){
			return "error";
		}
		User user = null;
		if (obj instanceof User) {
			user = (User) obj;
		}
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		Map<String, Integer> compare = new HashMap<String, Integer>();
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("createDate", false);
		String sql = "select sum(money) from orders where 1=1 ";
		
		orderType = "2";
		sql += " and order_status =" +orderType;
		conditionProperties.put("order_status", Integer.valueOf(orderType));
		compare.put("order_status", 0);
		request.setAttribute("userFlag", Integer.parseInt(orderType));
		if (null!=toUserName &&! "".equals(toUserName.trim())) {
			conditionProperties.put("toUserName", toUserName.trim());
			compare.put("toUserName", 2);
			sql += " and toUserName like '%"+toUserName.trim()+"%'";
		}
		if (null!=oUserName &&! "".equals(oUserName.trim())) {
			conditionProperties.put("oUserName", oUserName.trim());
			compare.put("oUserName", 2);
			sql += " and oUserName like '%"+oUserName.trim()+"%'";
		}
		if (null!=fromUserName &&! "".equals(fromUserName.trim())) {
			conditionProperties.put("fromUserName", fromUserName.trim());
			compare.put("fromUserName", 2);
			sql += " and fromUserName like '%"+fromUserName.trim()+"%'";
		}
		if (null!=tel &&! "".equals(tel.trim())) {
			conditionProperties.put("tel", tel.trim());
			compare.put("tel", 2);
			sql += " and tel like '%"+tel.trim()+"%'";
		}
		if (null!=oPhone &&! "".equals(oPhone.trim())) {
			conditionProperties.put("oPhone", oPhone.trim());
			compare.put("oPhone", 2);
			sql += " and oPhone like '%"+oPhone.trim()+"%'";
		}
		if (null!=ordersBH &&! "".equals(ordersBH.trim())) {
			conditionProperties.put("ordersBH", ordersBH.trim());
			compare.put("ordersBH", 2);
			sql += " and ordersBH like '%"+ordersBH.trim()+"%'";
		}
		if(null!=mobile &&! "".equals(mobile.trim())){
			conditionProperties.put("mobile", mobile.trim());
			compare.put("mobile", 2);
			sql += " and mobile like '%"+mobile.trim()+"%'";
		}
		int count_size =ordersDAO.cout_size_Commen(conditionProperties, compare);
		List sumList=ordersDAO.findBySql(sql);
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
		
		List<Orders> litPager = ordersDAO.findAllPagerList(conditionProperties, compare, sort, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		request.setAttribute("litPager", litPager);
		request.setAttribute("order_status", orderType);
		request.setAttribute("dateType", dateType);
		request.setAttribute("totalMoney", totalMoney);
		return "listYichang";
	}
	
	public String ordersList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Object obj = request.getSession().getAttribute("user");
		if(obj == null){
			return "error";
		}
		User user = null;
		if (obj instanceof User) {
			user = (User) obj;
			if(user.getBlock().equals("1")){
				return "block";
			}
		}
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		Map<String, Integer> compare = new HashMap<String, Integer>();
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		
		if(user !=null){
			userId = user.getUserId();
		}
		if (null!=userId &&! "".equals(userId)) {
			conditionProperties.put("userId", userId);
			compare.put("userId", 0);
		}
		if (null==orderType) {
			orderType = (String) request.getSession().getAttribute("order_status");
			
		}
		if(orderType==null){
			orderType = "-1";
		}
		if(orderType!=null){
			request.getSession().setAttribute("order_status", orderType);
		}
		if(!"-1".equals(orderType)){
			conditionProperties.put("order_status", Integer.valueOf(orderType));
			compare.put("order_status", 0);
		}else{
			Integer[] os = {0,1,2,3,4,5,6};
			conditionProperties.put("order_status", os);
			compare.put("order_status", 4);
		}
		if(paixu==null){
			paixu = (Integer) request.getSession().getAttribute("paixu");
			
		}
		if(paixu==null){
			paixu = 0;
		}
		boolean desc = true;
		if(paixu!=null){
			request.getSession().setAttribute("paixu", paixu);
			if(paixu == 1){
				desc = false;
			}
		}
		
		if (null!=toUserName &&! "".equals(toUserName.trim())) {
			conditionProperties.put("toUserName", toUserName.trim());
			compare.put("toUserName", 2);
		}
		if (null!=pname &&! "".equals(pname.trim())) {
			conditionProperties.put("pname", pname.trim());
			compare.put("pname", 2);
		}
		if (null!=oUserName &&! "".equals(oUserName.trim())) {
			conditionProperties.put("oUserName", oUserName.trim());
			compare.put("oUserName", 2);
		}
		if (null!=fromUserName &&! "".equals(fromUserName.trim())) {
			conditionProperties.put("fromUserName", fromUserName.trim());
			compare.put("fromUserName", 2);
		}
		if (null!=tel &&! "".equals(tel.trim())) {
			conditionProperties.put("tel", tel.trim());
			compare.put("tel", 2);
		}
		if (null!=oPhone &&! "".equals(oPhone.trim())) {
			conditionProperties.put("oPhone", oPhone.trim());
			compare.put("oPhone", 2);
		}
		if (null!=ordersBH &&! "".equals(ordersBH.trim())) {
			conditionProperties.put("ordersBH", ordersBH.trim());
			compare.put("ordersBH", 2);
		}
		if(null!=mobile &&! "".equals(mobile.trim())){
			conditionProperties.put("mobile", mobile.trim());
			compare.put("mobile", 2);
		}
		String selectDate = "";
		if(dateType==null){
			dateType = (String) request.getSession().getAttribute("dateType");
		}
		if(dateType==null){
			dateType = "1";
		}
		if(dateType!=null&&!"".equals(dateType)){
			if(dateType.equals("1")){
				selectDate = "createDate";
			}else if(dateType.equals("3")){
				selectDate = "fahuoDate";
			}else if(dateType.equals("5")){
				selectDate = "tuihuoDate";
			}else if(dateType.equals("6")){
				selectDate = "shouhuoDate";
			}
		}
		sort.put(selectDate, desc);
		if(null!=fromDate&&!"".equals(fromDate)/* &&endDate==null&&"".equals(endDate)*/){
			System.out.println("---------------------------------------"+fromDate);
			Date date=null;
			SimpleDateFormat   formatter   = 
					new   SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");
			try
			{
				date=formatter.parse(fromDate);
			} catch (ParseException e)
			{
				
				e.printStackTrace();
			}
			conditionProperties.put(selectDate, date);
			compare.put(selectDate, 8);
		}
		if(null!=endDate &&!"".equals(endDate)/*&&fromDate==null&&"".equals(fromDate)*/){
			System.out.println("+++++++++++++++++++++++++"+endDate);
			Date date=null;
			SimpleDateFormat   formatter   = 
					new   SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");
			try
			{
				date=formatter.parse(endDate);
			} catch (ParseException e)
			{
				
				e.printStackTrace();
			}
			conditionProperties.put(selectDate, date);
			compare.put(selectDate, 9);
		}
		if(fromDate!=null &&! "".equals(fromDate)&&null!=endDate &&! "".equals(endDate)){
			System.out.println("=========================");
			Date date=null;
			SimpleDateFormat   formatter   = 
					new   SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");
			try
			{
				date=formatter.parse(fromDate);
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
			Date date2=null;
			try
			{
				date2=formatter.parse(endDate);
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
			Date[] dates = {date,date2};
			conditionProperties.put(selectDate, dates);
			compare.put(selectDate, 10);
		}
		int count_size =ordersDAO.cout_size_Commen(conditionProperties, compare);
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count_size));
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
		
		List<Orders> litPager = ordersDAO.findAllPagerList(conditionProperties, compare, sort, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		request.setAttribute("litPager", litPager);
		request.getSession().setAttribute("orderType", orderType);
		request.getSession().setAttribute("dateType", dateType);
		request.setAttribute("totalMoney", totalMoney);
		return "ordersList";
	}
	
	public String caiwuList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Object obj = request.getSession().getAttribute("user");
		if(obj == null){
			return "error";
		}
		User user = null;
		if (obj instanceof User) {
			user = (User) obj;
			if(user.getBlock().equals("1")){
				return "block";
			}
		}
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		Map<String, Integer> compare = new HashMap<String, Integer>();
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		if(paixu==null){
			paixu = (Integer) request.getSession().getAttribute("paixu");
			
		}
		if(paixu==null){
			paixu = 0;
		}
		boolean desc = true;
		if(paixu!=null){
			request.getSession().setAttribute("paixu", paixu);
			if(paixu == 1){
				desc = false;
			}
		}
		
		String sql = "select sum(money) from orders where order_status in (1,2,3,4,6) ";
		Integer[] os = {1,2,3,4,5,6};
		conditionProperties.put("order_status", os);
		compare.put("order_status", 4);
		if(user !=null){
			userId = user.getUserId();
			sql+=" and userId ="+userId;
		}
		if (null!=userId &&! "".equals(userId)) {
			conditionProperties.put("userId", userId);
			compare.put("userId", 0);
		}
		
		if (null!=toUserName &&! "".equals(toUserName.trim())) {
			conditionProperties.put("toUserName", toUserName.trim());
			compare.put("toUserName", 2);
			sql += " and toUserName like '%"+toUserName.trim()+"%'";
		}
		if (null!=pname &&! "".equals(pname.trim())) {
			conditionProperties.put("pname", pname.trim());
			compare.put("pname", 2);
			sql += " and pname like '%"+pname.trim()+"%'";
		}
		if (null!=oUserName &&! "".equals(oUserName.trim())) {
			conditionProperties.put("oUserName", oUserName.trim());
			compare.put("oUserName", 2);
			sql += " and oUserName like '%"+oUserName.trim()+"%'";
		}
		if (null!=fromUserName &&! "".equals(fromUserName.trim())) {
			conditionProperties.put("fromUserName", fromUserName.trim());
			compare.put("fromUserName", 2);
			sql += " and fromUserName like '%"+fromUserName.trim()+"%'";
		}
		if (null!=tel &&! "".equals(tel.trim())) {
			conditionProperties.put("tel", tel.trim());
			compare.put("tel", 2);
			sql += " and tel like '%"+tel.trim()+"%'";
		}
		if (null!=oPhone &&! "".equals(oPhone.trim())) {
			conditionProperties.put("oPhone", oPhone.trim());
			compare.put("oPhone", 2);
			sql += " and oPhone like '%"+oPhone.trim()+"%'";
		}
		if (null!=ordersBH &&! "".equals(ordersBH.trim())) {
			conditionProperties.put("ordersBH", ordersBH.trim());
			compare.put("ordersBH", 2);
			sql += " and ordersBH like '%"+ordersBH.trim()+"%'";
		}
		if(null!=mobile &&! "".equals(mobile.trim())){
			conditionProperties.put("mobile", mobile.trim());
			compare.put("mobile", 2);
			sql += " and mobile like '%"+mobile.trim()+"%'";
		}
		String selectDate = "";
		if(dateType==null){
			dateType = (String) request.getSession().getAttribute("dateType");
		}
		if(dateType!=null&&!"".equals(dateType)){
			if(dateType.equals("1")){
				selectDate = "createDate";
			}else if(dateType.equals("3")){
				selectDate = "fahuoDate";
			}else if(dateType.equals("5")){
				selectDate = "tuihuoDate";
			}else if(dateType.equals("6")){
				selectDate = "shouhuoDate";
			}
		}
		if(selectDate.equals("")){
			selectDate = "createDate";
		}
		sort.put(selectDate, desc);
		String dateSql = "";
		if(null!=fromDate&&!"".equals(fromDate)/* &&endDate==null&&"".equals(endDate)*/){
			System.out.println("---------------------------------------"+fromDate);
			Date date=null;
			SimpleDateFormat   formatter   = 
					new   SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");
			try
			{
				date=formatter.parse(fromDate);
			} catch (ParseException e)
			{
				
				e.printStackTrace();
			}
			conditionProperties.put(selectDate, date);
			compare.put(selectDate, 8);
			dateSql = " and "+selectDate+" >= '"+fromDate+"'";
		}
		if(null!=endDate &&!"".equals(endDate)/*&&fromDate==null&&"".equals(fromDate)*/){
			System.out.println("+++++++++++++++++++++++++"+endDate);
			Date date=null;
			SimpleDateFormat   formatter   = 
					new   SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");
			try
			{
				date=formatter.parse(endDate);
			} catch (ParseException e)
			{
				
				e.printStackTrace();
			}
			conditionProperties.put(selectDate, date);
			compare.put(selectDate, 9);
			dateSql = " and "+selectDate+" < '"+endDate+"'";
		}
		if(fromDate!=null &&! "".equals(fromDate)&&null!=endDate &&! "".equals(endDate)){
			System.out.println("=========================");
			Date date=null;
			SimpleDateFormat   formatter   = 
					new   SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");
			try
			{
				date=formatter.parse(fromDate);
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
			Date date2=null;
			try
			{
				date2=formatter.parse(endDate);
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
			Date[] dates = {date,date2};
			conditionProperties.put(selectDate, dates);
			compare.put(selectDate, 10);
			dateSql = " and "+selectDate+" between '"+fromDate+"' and '"+endDate+"'";
		}

		if((fromDate==null|| "".equals(fromDate))&&(null==endDate || "".equals(endDate))){
			SimpleDateFormat   formatter   = 
					new   SimpleDateFormat( "yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			Date date=calendar.getTime();
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			Date date2=calendar.getTime();
			Date[] dates = {date,date2};
			conditionProperties.put(selectDate, dates);
			compare.put(selectDate, 10);
			
			dateSql = " and "+selectDate+" between '"+formatter.format(date)+"  00:00:00' and '"+formatter.format(date2)+" 00:00:00'";
		}
		sql = sql+dateSql;
		int count_size =ordersDAO.cout_size_Commen(conditionProperties, compare);
		List sumList=ordersDAO.findBySql(sql);
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
		
		List<Orders> litPager = ordersDAO.findAllPagerList(conditionProperties, compare, sort, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		request.setAttribute("litPager", litPager);
		request.getSession().setAttribute("dateType", dateType);
		request.setAttribute("totalMoney", totalMoney);
		return "caiwuList";
	}
	
	public String tuanduiCaiwu() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Object obj = request.getSession().getAttribute("user");
		if(obj == null){
			return "error";
		}
		User user = null;
		if (obj instanceof User) {
			user = (User) obj;
			if(user.getBlock().equals("1")){
				return "block";
			}
		}
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		Map<String, Integer> compare = new HashMap<String, Integer>();
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		if(paixu==null){
			paixu = (Integer) request.getSession().getAttribute("paixu");
			
		}
		if(paixu==null){
			paixu = 0;
		}
		boolean desc = true;
		if(paixu!=null){
			request.getSession().setAttribute("paixu", paixu);
			if(paixu == 1){
				desc = false;
			}
		}
		
		String sql = "select sum(money) from orders where order_status in (1,2,3,4,6) ";
		Integer[] os = {1,2,3,4,5,6};
		conditionProperties.put("order_status", os);
		compare.put("order_status", 4);
		if (null!=userId &&! "".equals(userId)) {
			conditionProperties.put("userId", userId);
			compare.put("userId", 0);
			sql+=" and userId ="+userId;
		}else{
			return "block";
		}
		
		if (null!=toUserName &&! "".equals(toUserName.trim())) {
			conditionProperties.put("toUserName", toUserName.trim());
			compare.put("toUserName", 2);
			sql += " and toUserName like '%"+toUserName.trim()+"%'";
		}
		if (null!=pname &&! "".equals(pname.trim())) {
			conditionProperties.put("pname", pname.trim());
			compare.put("pname", 2);
			sql += " and pname like '%"+pname.trim()+"%'";
		}
		if (null!=oUserName &&! "".equals(oUserName.trim())) {
			conditionProperties.put("oUserName", oUserName.trim());
			compare.put("oUserName", 2);
			sql += " and oUserName like '%"+oUserName.trim()+"%'";
		}
		if (null!=fromUserName &&! "".equals(fromUserName.trim())) {
			conditionProperties.put("fromUserName", fromUserName.trim());
			compare.put("fromUserName", 2);
			sql += " and fromUserName like '%"+fromUserName.trim()+"%'";
		}
		if (null!=tel &&! "".equals(tel.trim())) {
			conditionProperties.put("tel", tel.trim());
			compare.put("tel", 2);
			sql += " and tel like '%"+tel.trim()+"%'";
		}
		if (null!=oPhone &&! "".equals(oPhone.trim())) {
			conditionProperties.put("oPhone", oPhone.trim());
			compare.put("oPhone", 2);
			sql += " and oPhone like '%"+oPhone.trim()+"%'";
		}
		if (null!=ordersBH &&! "".equals(ordersBH.trim())) {
			conditionProperties.put("ordersBH", ordersBH.trim());
			compare.put("ordersBH", 2);
			sql += " and ordersBH like '%"+ordersBH.trim()+"%'";
		}
		if(null!=mobile &&! "".equals(mobile.trim())){
			conditionProperties.put("mobile", mobile.trim());
			compare.put("mobile", 2);
			sql += " and mobile like '%"+mobile.trim()+"%'";
		}
		String selectDate = "";
		if(dateType==null){
			dateType = (String) request.getSession().getAttribute("dateType");
		}
		if(dateType!=null&&!"".equals(dateType)){
			if(dateType.equals("1")){
				selectDate = "createDate";
			}else if(dateType.equals("3")){
				selectDate = "fahuoDate";
			}else if(dateType.equals("5")){
				selectDate = "tuihuoDate";
			}else if(dateType.equals("6")){
				selectDate = "shouhuoDate";
			}
		}
		if(selectDate.equals("")){
			selectDate = "createDate";
		}
		sort.put(selectDate, desc);
		String dateSql = "";
		if(null!=fromDate&&!"".equals(fromDate)/* &&endDate==null&&"".equals(endDate)*/){
			System.out.println("---------------------------------------"+fromDate);
			Date date=null;
			SimpleDateFormat   formatter   = 
					new   SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");
			try
			{
				date=formatter.parse(fromDate);
			} catch (ParseException e)
			{
				
				e.printStackTrace();
			}
			conditionProperties.put(selectDate, date);
			compare.put(selectDate, 8);
			dateSql = " and "+selectDate+" >= '"+fromDate+"'";
		}
		if(null!=endDate &&!"".equals(endDate)/*&&fromDate==null&&"".equals(fromDate)*/){
			System.out.println("+++++++++++++++++++++++++"+endDate);
			Date date=null;
			SimpleDateFormat   formatter   = 
					new   SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");
			try
			{
				date=formatter.parse(endDate);
			} catch (ParseException e)
			{
				
				e.printStackTrace();
			}
			conditionProperties.put(selectDate, date);
			compare.put(selectDate, 9);
			dateSql = " and "+selectDate+" < '"+endDate+"'";
		}
		if(fromDate!=null &&! "".equals(fromDate)&&null!=endDate &&! "".equals(endDate)){
			System.out.println("=========================");
			Date date=null;
			SimpleDateFormat   formatter   = 
					new   SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");
			try
			{
				date=formatter.parse(fromDate);
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
			Date date2=null;
			try
			{
				date2=formatter.parse(endDate);
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
			Date[] dates = {date,date2};
			conditionProperties.put(selectDate, dates);
			compare.put(selectDate, 10);
			dateSql = " and "+selectDate+" between '"+fromDate+"' and '"+endDate+"'";
		}

		if((fromDate==null|| "".equals(fromDate))&&(null==endDate || "".equals(endDate))){
			SimpleDateFormat   formatter   = 
					new   SimpleDateFormat( "yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			Date date=calendar.getTime();
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			Date date2=calendar.getTime();
			Date[] dates = {date,date2};
			conditionProperties.put(selectDate, dates);
			compare.put(selectDate, 10);
			
			dateSql = " and "+selectDate+" between '"+formatter.format(date)+"  00:00:00' and '"+formatter.format(date2)+" 00:00:00'";
		}
		sql = sql+dateSql;
		int count_size =ordersDAO.cout_size_Commen(conditionProperties, compare);
		List sumList=ordersDAO.findBySql(sql);
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
		
		List<Orders> litPager = ordersDAO.findAllPagerList(conditionProperties, compare, sort, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		request.setAttribute("litPager", litPager);
		request.getSession().setAttribute("dateType", dateType);
		request.setAttribute("totalMoney", totalMoney);
		return "tuanduiCaiwu";
	}
	
	
	public String create() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Object obj = request.getSession().getAttribute("user");
		if(obj == null){
			return "error";
		}
		User user = null;
		if (obj instanceof User) {
			user = (User) obj;
		}
		Orders orders = null;
		if(ordersId ==null){
			orders = new Orders();
			//订单编号规则 88+日期+随机六位数
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
			Date date = new Date();
			String fd = sdf.format(date);
			orders.setOrdersBH(fd);
			orders.setCreateDate(date);
			order_status =0;
		}else{
			orders = (Orders) ordersDAO.findById(ordersId);
		}
		orders.setToUserName(toUserName);
		orders.setOrder_status(order_status);
		orders.setAddress(address);
		orders.setMobile(mobile);
		orders.setPname(pname);
		orders.setZipcode(zipcode);
		if(user!=null){
			orders.setoPhone(user.getPhone());
			orders.setoUserName(user.getUserName());
			orders.setUserId(user.getUserId());
		}
		
		orders.setMoney(Double.valueOf(money));
		orders.setSheng(sheng);
		orders.setChengshi(chengshi);
		orders.setShengCode(shengCode);
		orders.setChengshiCode(chengshiCode);
		orders.setDiquCode(diquCode);
		orders.setDiqu(diqu);
		orders.setSize(size);
		orders.setShuliang(Double.valueOf(shuliang));
		ordersDAO.saveOrUpdate(orders);
		ServletActionContext.getRequest().setAttribute("message", "报单成功");
		return "goBackList1";
	}
	
	
	public String getFromDate()
	{
		return fromDate;
	}

	public void setFromDate(String fromDate)
	{
		this.fromDate = fromDate;
	}

	public String getEndDate()
	{
		return endDate;
	}

	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}

	public String getTotalMoney()
	{
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney)
	{
		this.totalMoney = totalMoney;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
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

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public UserOrderDAO getUserOrderDAO() {
		return userOrderDAO;
	}
	
	public TAreaDao gettAreaDao() {
		return tAreaDao;
	}
	@Autowired
	public void settAreaDao(TAreaDao tAreaDao) {
		this.tAreaDao = tAreaDao;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}
	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Autowired
	public void setUserOrderDAO(UserOrderDAO userOrderDAO) {
		this.userOrderDAO = userOrderDAO;
	}
	public LoginService getLoginService() {
		return loginService;
	}
	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	public ProductsDAO getProductsDAO() {
		return productsDAO;
	}
	@Autowired
	public void setProductsDAO(ProductsDAO productsDAO) {
		this.productsDAO = productsDAO;
	}
	public ApplyReturnPurchaseDao getApplyReturnPurchaseDao() {
		return applyReturnPurchaseDao;
	}
	@Autowired
	public void setApplyReturnPurchaseDao(
			ApplyReturnPurchaseDao applyReturnPurchaseDao) {
		this.applyReturnPurchaseDao = applyReturnPurchaseDao;
	}

	public Integer getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(Integer ordersId) {
		this.ordersId = ordersId;
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
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public OrdersDAO getOrdersDAO() {
		return ordersDAO;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrdersBH() {
		return ordersBH;
	}

	public void setOrdersBH(String ordersBH) {
		this.ordersBH = ordersBH;
	}

	@Autowired
	public void setOrdersDAO(OrdersDAO ordersDAO) {
		this.ordersDAO = ordersDAO;
	}
	public OrdersProductsDAO getOrdersProductsDAO() {
		return ordersProductsDAO;
	}
	@Autowired
	public void setOrdersProductsDAO(OrdersProductsDAO ordersProductsDAO) {
		this.ordersProductsDAO = ordersProductsDAO;
	}
	public String getApplyNum() {
		return applyNum;
	}

	public void setApplyNum(String applyNum) {
		this.applyNum = applyNum;
	}

	public String initkuaidi () {
		Orders orders = (Orders)ordersDAO.findById(ordersId);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("orders", orders);
		return "initkuaidi";
	}
	public String beizhu () {
		Orders orders = (Orders)ordersDAO.findById(ordersId);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("orders", orders);
		return "beizhu";
	}
	
	public String listAll () throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer count = productsDAO.cout_size(null, null);
				
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count));
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
				
		List<Products> litPager = productsDAO.findAllPagerList_new(null, null, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		request.setAttribute("litPager", litPager);
		
		User loginUser = (User)request.getSession().getAttribute("user");
		//查看当前用户是否进行过第一次购买
		UserOrder userOrder = userOrderDAO.findUserOrderByUserId(loginUser.getUserId());
		if (null!=userOrder) {
			request.setAttribute("buyFlag", "1");
		}
		return "listAll";
	}
	
	public String initAdd () throws Exception {
		if(ordersId!=null){
			Orders orders = (Orders) ordersDAO.findById(ordersId);
			ServletActionContext.getRequest().setAttribute("order", orders);
		}
		List<TArea> litTArea = tAreaDao.findEntityByPropertiName("parentCode", "0");
		ServletActionContext.getRequest().setAttribute("litTArea_sheng", litTArea);
		return "initAdd";
	}
	
	public String updateCard () throws Exception { //跟新卡的状态
		
		return "initAdd";
	}
	
	
	public void getRefer(){
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		Map<String, Integer> compare = new HashMap<String, Integer>();
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("createDate", false);
		
		if (null != ordersId && !"".equals(ordersId)) {
			conditionProperties.put("userId", ordersId);
			compare.put("userId", 0);
		}
		List<Orders> litPager = ordersDAO.findAllPagerList_new1(conditionProperties, compare, sort, 0, 1, "page");
		Orders orders = null;
		if(litPager.size()>0){
			orders = litPager.get(0);
		}
		JSONObject json = new JSONObject();
		if(orders !=null){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			try {
				json.put("phone", orders.getMobile());
				json.put("toUserName", orders.getToUserName());
				response.getWriter().write(json.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void exportKudiExcel(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Object obj = request.getSession().getAttribute("user");
		AdminUser adminUser = null;
		User user = null;
		if (obj instanceof User) {
			user = (User) obj;
		}
		if(obj instanceof AdminUser){
			adminUser = (AdminUser) obj;
		}
		//excel模板路径
		String path = ServletActionContext.getServletContext().getRealPath("")+"/resource/kuaidi.xls";
		File fi=new File(path);
		POIFSFileSystem fs = null;
		HSSFWorkbook wb = null;
		try {
			fs = new POIFSFileSystem(new FileInputStream(fi));
			//读取excel模板
			wb = new HSSFWorkbook(fs);
		} catch (Exception e) {
			e.printStackTrace();
			
		} 
		if(wb!=null){
			
		
		//读取了模板内所有sheet内容
		HSSFSheet sheet = wb.getSheetAt(0);
		
		//如果这行没有了，整个公式都不会有自动计算的效果的
		sheet.setForceFormulaRecalculation(true);
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		Map<String, Integer> compare = new HashMap<String, Integer>();
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		conditionProperties.put("order_status", 1);
		compare.put("order_status", 0);
		sort.put("createDate", false);
		if(null!=orderIds&&orderIds.length>0){
			conditionProperties.put("ordersId", orderIds);
			compare.put("ordersId", 4);
		}
		
		if (null!=toUserName &&! "".equals(toUserName.trim())) {
			conditionProperties.put("toUserName", toUserName.trim());
			compare.put("toUserName", 2);
		}
		if (null!=pname &&! "".equals(pname.trim())) {
			conditionProperties.put("pname", pname.trim());
			compare.put("pname", 2);
		}
		if (null!=oUserName &&! "".equals(oUserName.trim())) {
			conditionProperties.put("oUserName", oUserName.trim());
			compare.put("oUserName", 2);
		}
		if (null!=fromUserName &&! "".equals(fromUserName.trim())) {
			conditionProperties.put("fromUserName", fromUserName.trim());
			compare.put("fromUserName", 2);
		}
		if (null!=tel &&! "".equals(tel.trim())) {
			conditionProperties.put("tel", tel.trim());
			compare.put("tel", 2);
		}
		if (null!=oPhone &&! "".equals(oPhone.trim())) {
			conditionProperties.put("oPhone", oPhone.trim());
			compare.put("oPhone", 2);
		}
		if (null!=ordersBH &&! "".equals(ordersBH.trim())) {
			conditionProperties.put("ordersBH", ordersBH.trim());
			compare.put("ordersBH", 2);
		}
		if(null!=mobile &&! "".equals(mobile.trim())){
			conditionProperties.put("mobile", mobile.trim());
			compare.put("mobile", 2);
		}
		String selectDate = "";
		
		if(dateType!=null&&!"".equals(dateType)){
			if(dateType.equals("1")){
				selectDate = "createDate";
			}else if(dateType.equals("3")){
				selectDate = "fahuoDate";
			}else if(dateType.equals("5")){
				selectDate = "tuihuoDate";
			}else if(dateType.equals("6")){
				selectDate = "shouhuoDate";
			}
		}
		if(null!=fromDate&&!"".equals(fromDate)/* &&endDate==null&&"".equals(endDate)*/){
			System.out.println("---------------------------------------"+fromDate);
			Date date=null;
			SimpleDateFormat   formatter   = 
					new   SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");
			try
			{
				date=formatter.parse(fromDate);
			} catch (ParseException e)
			{
				
				e.printStackTrace();
			}
			conditionProperties.put(selectDate, date);
			compare.put(selectDate, 8);
		}
		if(null!=endDate &&!"".equals(endDate)/*&&fromDate==null&&"".equals(fromDate)*/){
			System.out.println("+++++++++++++++++++++++++"+endDate);
			Date date=null;
			SimpleDateFormat   formatter   = 
					new   SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");
			try
			{
				date=formatter.parse(endDate);
			} catch (ParseException e)
			{
				
				e.printStackTrace();
			}
			conditionProperties.put(selectDate, date);
			compare.put(selectDate, 9);
		}
		if(fromDate!=null &&! "".equals(fromDate)&&null!=endDate &&! "".equals(endDate)){
			System.out.println("=========================");
			Date date=null;
			SimpleDateFormat   formatter   = 
					new   SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");
			try
			{
				date=formatter.parse(fromDate);
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
			Date date2=null;
			try
			{
				date2=formatter.parse(endDate);
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
			Date[] dates = {date,date2};
			conditionProperties.put(selectDate, dates);
			compare.put(selectDate, 10);
		}
		
		List<Orders> list = ordersDAO.findAllPagerList(conditionProperties, compare, sort, 0, 0, "all");
		if(list!=null){
			HSSFRow row = null;
			for (int i = 0; i < list.size(); i++) {  
				row=sheet.createRow(i+2);
				row.createCell(0).setCellValue("山人物语");
				row.createCell(5).setCellValue(adminUser.getPhone());//发货人联系电话
				row.createCell(6).setCellValue(list.get(i).getChengshi().substring(0, list.get(i).getChengshi().length()-1));//到达城市  注意:不带"市"字
				row.createCell(7).setCellValue("现结");
				row.createCell(8).setCellValue(list.get(i).getToUserName());
				row.createCell(10).setCellValue(list.get(i).getSheng()+list.get(i).getChengshi()+list.get(i).getDiqu()+list.get(i).getAddress());
				row.createCell(12).setCellValue(list.get(i).getMobile());
				row.createCell(14).setCellValue(list.get(i).getPname());
				row.createCell(15).setCellValue("1");
				row.createCell(25).setCellValue("全运村");
				row.createCell(28).setCellValue(list.get(i).getFromUserName());
				row.createCell(29).setCellValue(list.get(i).getOrdersBH());
	        }
		}
		 try  
	        {  
	        	HttpServletResponse response = ServletActionContext.getResponse();
	        	String filename = "已支付订单列表";
	        	filename += new String((new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date())).getBytes());
	        	filename += ".xls";
	        	filename = encodeFilename(request.getHeader("user-agent"),filename);
	        	response.setHeader("Content-disposition","attachment; filename=" +filename);
	        	OutputStream outputStream = response.getOutputStream();
	            wb.write(outputStream);
	            outputStream.flush();
	            outputStream.close();  
	        }  
	        catch (Exception e)  
	        {  
	            e.printStackTrace();  
	        } 
		}
		
	}
	
	public String importExcelToOrders(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Object obj = request.getSession().getAttribute("user");
		if(obj == null){
			return "error";
		}
		User user = null;
		AdminUser adminUser = null;
		if (obj instanceof User) {
			user = (User) obj;
			request.setAttribute("adminUser", false);
		}
		if (obj instanceof AdminUser) {
			request.setAttribute("adminUser", true);
			adminUser = (AdminUser) obj;
		}
		InputStream is=null;
		int jiShu=0;
		if(importFile!=null){
		try {
			is = new FileInputStream(importFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        HSSFWorkbook hssfWorkbook=null;
		try {
			hssfWorkbook = new HSSFWorkbook(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String hql = "from Orders s where s.kuaidiNo is null and order_status=1";
		//List<Orders> orders = ordersDAO.findAll();
		List<Orders> orders = ordersDAO.findbyHql(hql);
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 2; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
            	
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }
                	//	获取第一列
                	HSSFCell bh=hssfRow.getCell(29);
                	if(bh==null){
                		continue;
                	}
                	String ddbh=getValue(bh);       //订单编号
                    //获取第二列
                	HSSFCell sj=hssfRow.getCell(30);
                	if(sj==null){
                		continue;
                	}
                	String kdgs=getValue(sj);			//快递名称
                    //获取第三列
                	HSSFCell pn=hssfRow.getCell(31);
					if(pn==null){
						continue;
					}
					String kddh=getValue(pn);		//快递单号
					if(StringUtils.isBlank(kddh)){
						continue;
					}
					int i=3;
					
					if(orders.size()>0){
						 Iterator<Orders> iter = orders.iterator();
						 while(iter.hasNext()){
					            Orders b = iter.next();
					            if(b.getOrdersBH().trim().equals(ddbh.trim())){
					            	b.setKuaidiName(kdgs);
									b.setKuaidiNo(kddh);
									b.setOrder_status(i);
									if(adminUser!=null){
										b.setFromUserName(adminUser.getUserName());
										b.setTel(adminUser.getPhone());
									}
									b.setFahuoDate(new Date());
									ordersDAO.update(b);
					                iter.remove();
					                jiShu++;
					            }
					        }
					}
					
            }
        }
        System.out.println("jishu----------------"+jiShu);
        request.setAttribute("jiShu", jiShu);
		return "goBackList1";
		}else{
			return "importNull";
		}
}
	
	public void exportToExcel(){

		HttpServletRequest request = ServletActionContext.getRequest();
		Object obj = request.getSession().getAttribute("user");
		AdminUser adminUser = null;
		User user = null;
		if (obj instanceof User) {
			user = (User) obj;
		}
		if(obj instanceof AdminUser){
			adminUser = (AdminUser) obj;
		}
		//excel模板路径
		String path = ServletActionContext.getServletContext().getRealPath("")+"/resource/kuaidi.xls";
		File fi=new File(path);
		POIFSFileSystem fs = null;
		HSSFWorkbook wb = null;
		try {
			fs = new POIFSFileSystem(new FileInputStream(fi));
			//读取excel模板
			wb = new HSSFWorkbook(fs);
		} catch (Exception e) {
			e.printStackTrace();
			
		} 
		if(wb!=null){
			
		
		//读取了模板内所有sheet内容
		HSSFSheet sheet = wb.getSheetAt(0);
		
		//如果这行没有了，整个公式都不会有自动计算的效果的
		sheet.setForceFormulaRecalculation(true);
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		Map<String, Integer> compare = new HashMap<String, Integer>();
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		if(orderType!=null&&!"".equals(orderType.trim())){
			conditionProperties.put("order_status", Integer.valueOf(orderType));
			compare.put("order_status", 0);
		}else{
			Integer[] os = {0,1,2,3,4,5,6};
			conditionProperties.put("order_status", os);
			compare.put("order_status", 4);
		}
		sort.put("createDate", false);
		
		
		if (null!=toUserName &&! "".equals(toUserName.trim())) {
			conditionProperties.put("toUserName", toUserName.trim());
			compare.put("toUserName", 2);
		}
		if (null!=pname &&! "".equals(pname.trim())) {
			conditionProperties.put("pname", pname.trim());
			compare.put("pname", 2);
		}
		if (null!=oUserName &&! "".equals(oUserName.trim())) {
			conditionProperties.put("oUserName", oUserName.trim());
			compare.put("oUserName", 2);
		}
		if (null!=fromUserName &&! "".equals(fromUserName.trim())) {
			conditionProperties.put("fromUserName", fromUserName.trim());
			compare.put("fromUserName", 2);
		}
		if (null!=tel &&! "".equals(tel.trim())) {
			conditionProperties.put("tel", tel.trim());
			compare.put("tel", 2);
		}
		if (null!=oPhone &&! "".equals(oPhone.trim())) {
			conditionProperties.put("oPhone", oPhone.trim());
			compare.put("oPhone", 2);
		}
		if (null!=ordersBH &&! "".equals(ordersBH.trim())) {
			conditionProperties.put("ordersBH", ordersBH.trim());
			compare.put("ordersBH", 2);
		}
		if(null!=mobile &&! "".equals(mobile.trim())){
			conditionProperties.put("mobile", mobile.trim());
			compare.put("mobile", 2);
		}
		String selectDate = "";
		
		if(dateType!=null&&!"".equals(dateType)){
			if(dateType.equals("1")){
				selectDate = "createDate";
			}else if(dateType.equals("3")){
				selectDate = "fahuoDate";
			}else if(dateType.equals("5")){
				selectDate = "tuihuoDate";
			}else if(dateType.equals("6")){
				selectDate = "shouhuoDate";
			}
		}
		if(null!=fromDate&&!"".equals(fromDate)/* &&endDate==null&&"".equals(endDate)*/){
			System.out.println("---------------------------------------"+fromDate);
			Date date=null;
			SimpleDateFormat   formatter   = 
					new   SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");
			try
			{
				date=formatter.parse(fromDate);
			} catch (ParseException e)
			{
				
				e.printStackTrace();
			}
			conditionProperties.put(selectDate, date);
			compare.put(selectDate, 8);
		}
		if(null!=endDate &&!"".equals(endDate)/*&&fromDate==null&&"".equals(fromDate)*/){
			System.out.println("+++++++++++++++++++++++++"+endDate);
			Date date=null;
			SimpleDateFormat   formatter   = 
					new   SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");
			try
			{
				date=formatter.parse(endDate);
			} catch (ParseException e)
			{
				
				e.printStackTrace();
			}
			conditionProperties.put(selectDate, date);
			compare.put(selectDate, 9);
		}
		if(fromDate!=null &&! "".equals(fromDate)&&null!=endDate &&! "".equals(endDate)){
			System.out.println("=========================");
			Date date=null;
			SimpleDateFormat   formatter   = 
					new   SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");
			try
			{
				date=formatter.parse(fromDate);
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
			Date date2=null;
			try
			{
				date2=formatter.parse(endDate);
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
			Date[] dates = {date,date2};
			conditionProperties.put(selectDate, dates);
			compare.put(selectDate, 10);
		}
		
		List<Orders> list = ordersDAO.findAllPagerList(conditionProperties, compare, sort, 0, 0, "all");
		if(list!=null){
			HSSFRow row = null;
			for (int i = 0; i < list.size(); i++) {  
				row=sheet.createRow(i+2);
				row.createCell(0).setCellValue("山人物语");
				row.createCell(5).setCellValue(adminUser.getPhone());//发货人联系电话
				row.createCell(6).setCellValue(list.get(i).getChengshi().substring(0, list.get(i).getChengshi().length()-1));//到达城市  注意:不带"市"字
				row.createCell(7).setCellValue("现结");
				row.createCell(8).setCellValue(list.get(i).getToUserName());
				row.createCell(10).setCellValue(list.get(i).getSheng()+list.get(i).getChengshi()+list.get(i).getDiqu()+list.get(i).getAddress());
				row.createCell(12).setCellValue(list.get(i).getMobile());
				row.createCell(14).setCellValue(list.get(i).getPname());
				row.createCell(15).setCellValue("1");
				row.createCell(25).setCellValue("全运村");
				row.createCell(28).setCellValue(list.get(i).getFromUserName());
				row.createCell(29).setCellValue(list.get(i).getOrdersBH());
	        }
		}
		 try  
	        {  
	        	HttpServletResponse response = ServletActionContext.getResponse();
	        	String filename = "已支付订单列表";
	        	filename += new String((new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date())).getBytes());
	        	filename += ".xls";
	        	filename = encodeFilename(request.getHeader("user-agent"),filename);
	        	response.setHeader("Content-disposition","attachment; filename=" +filename);
	        	OutputStream outputStream = response.getOutputStream();
	            wb.write(outputStream);
	            outputStream.flush();
	            outputStream.close();  
	        }  
	        catch (Exception e)  
	        {  
	            e.printStackTrace();  
	        } 
		}
		
	
	}
	
	/*public String exportToExcel(){
		// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("sheet");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        HSSFFont font = wb.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 16);//设置字体大小
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(style);
        cell.setCellValue("订单编号");
        
        cell = row.createCell(1);
        cell.setCellStyle(style);
        cell.setCellValue("下单时间");
        
        cell = row.createCell(2);
        cell.setCellStyle(style);
        cell.setCellValue("下单人姓名");
        
        cell = row.createCell(3);
        cell.setCellStyle(style);
        cell.setCellValue("下单人电话");
        
        cell = row.createCell(4);
        cell.setCellStyle(style);
        cell.setCellValue("下单人编号");
        
        cell = row.createCell(5);
        cell.setCellStyle(style);
        cell.setCellValue("收货人姓名");
        
        cell = row.createCell(6);
        cell.setCellStyle(style);
        cell.setCellValue("收货人电话");
        
        cell = row.createCell(7);
        cell.setCellStyle(style);
        cell.setCellValue("所在省");
        
        cell = row.createCell(8);
        cell.setCellStyle(style);
        cell.setCellValue("所在市");
        
        cell = row.createCell(9);
        cell.setCellStyle(style);
        cell.setCellValue("所在区");
        
        cell = row.createCell(10);
        cell.setCellStyle(style);
        cell.setCellValue("详细地址");
        
        cell = row.createCell(11);
        cell.setCellStyle(style);
        cell.setCellValue("订单状态");
        
        cell = row.createCell(12);
        cell.setCellStyle(style);
        cell.setCellValue("购买数量");
        
        cell = row.createCell(13);
        cell.setCellStyle(style);
        cell.setCellValue("单位");
        
        cell = row.createCell(14);
        cell.setCellStyle(style);
        cell.setCellValue("快递名称");
        
        cell = row.createCell(15);
        cell.setCellStyle(style);
        cell.setCellValue("快递编号");
        
        cell = row.createCell(16);
        cell.setCellStyle(style);
        cell.setCellValue("水果名称");
        
        Map<String, Object> conditionProperties = new HashMap<String, Object>();
		Map<String, Integer> compare = new HashMap<String, Integer>();
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		if(orderType!=null&&!"".equals(orderType.trim())){
			conditionProperties.put("order_status", Integer.valueOf(orderType));
			compare.put("order_status", 0);
		}else{
			Integer[] os = {0,1,2,3,4,5,6};
			conditionProperties.put("order_status", os);
			compare.put("order_status", 4);
			orderType = "-1";
		}
		
		if(orderType.equals("0")){
			sort.put("createDate", false);
		}else if(orderType.equals("1")){
			sort.put("createDate", false);
		}else if(orderType.equals("2")){
			sort.put("createDate", false);
		}else if(orderType.equals("3")){
			sort.put("fahuoDate", false);
		}else if(orderType.equals("4")){
			sort.put("createDate", false);
		}else if(orderType.equals("5")){
			sort.put("tuihuoDate", false);
		}else if(orderType.equals("6")){
			sort.put("shouhuoDate", false);
		}
		
		if (null!=toUserName &&! "".equals(toUserName.trim())) {
			conditionProperties.put("toUserName", toUserName.trim());
			compare.put("toUserName", 2);
		}
		if (null!=pname &&! "".equals(pname.trim())) {
			conditionProperties.put("pname", pname.trim());
			compare.put("pname", 2);
		}
		if (null!=oUserName &&! "".equals(oUserName.trim())) {
			conditionProperties.put("oUserName", oUserName.trim());
			compare.put("oUserName", 2);
		}
		if (null!=fromUserName &&! "".equals(fromUserName.trim())) {
			conditionProperties.put("fromUserName", fromUserName.trim());
			compare.put("fromUserName", 2);
		}
		if (null!=tel &&! "".equals(tel.trim())) {
			conditionProperties.put("tel", tel.trim());
			compare.put("tel", 2);
		}
		if (null!=oPhone &&! "".equals(oPhone.trim())) {
			conditionProperties.put("oPhone", oPhone.trim());
			compare.put("oPhone", 2);
		}
		if (null!=ordersBH &&! "".equals(ordersBH.trim())) {
			conditionProperties.put("ordersBH", ordersBH.trim());
			compare.put("ordersBH", 2);
		}
		if(null!=mobile &&! "".equals(mobile.trim())){
			conditionProperties.put("mobile", mobile.trim());
			compare.put("mobile", 2);
		}
		String selectDate = "";
		
		if(dateType!=null&&!"".equals(dateType)){
			if(dateType.equals("1")){
				selectDate = "createDate";
			}else if(dateType.equals("3")){
				selectDate = "fahuoDate";
			}else if(dateType.equals("5")){
				selectDate = "tuihuoDate";
			}else if(dateType.equals("6")){
				selectDate = "shouhuoDate";
			}
		}
		if(null!=fromDate&&!"".equals(fromDate) &&endDate==null&&"".equals(endDate)){
			System.out.println("---------------------------------------"+fromDate);
			Date date=null;
			SimpleDateFormat   formatter   = 
					new   SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");
			try
			{
				date=formatter.parse(fromDate);
			} catch (ParseException e)
			{
				
				e.printStackTrace();
			}
			conditionProperties.put(selectDate, date);
			compare.put(selectDate, 8);
		}
		if(null!=endDate &&!"".equals(endDate)&&fromDate==null&&"".equals(fromDate)){
			System.out.println("+++++++++++++++++++++++++"+endDate);
			Date date=null;
			SimpleDateFormat   formatter   = 
					new   SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");
			try
			{
				date=formatter.parse(endDate);
			} catch (ParseException e)
			{
				
				e.printStackTrace();
			}
			conditionProperties.put(selectDate, date);
			compare.put(selectDate, 9);
		}
		if(fromDate!=null &&! "".equals(fromDate)&&null!=endDate &&! "".equals(endDate)){
			System.out.println("=========================");
			Date date=null;
			SimpleDateFormat   formatter   = 
					new   SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");
			try
			{
				date=formatter.parse(fromDate);
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
			Date date2=null;
			try
			{
				date2=formatter.parse(endDate);
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
			Date[] dates = {date,date2};
			conditionProperties.put(selectDate, dates);
			compare.put(selectDate, 10);
		}
		
		List<Orders> list = ordersDAO.findAllPagerList_new1(conditionProperties, compare, sort, 0, 0, "all");
  
        for (int i = 0; i < list.size(); i++)  
        {  
            row = sheet.createRow(i + 1);
            Orders order = list.get(i);
            // 第四步，创建单元格，并设置值  
            row.createCell(0).setCellValue(order.getOrdersBH());//订单编号
            row.createCell(1).setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getCreateDate())); //下单时间
			row.createCell(2).setCellValue(order.getoUserName());
			row.createCell(3).setCellValue(order.getoPhone());
			row.createCell(4).setCellValue(order.getUserId());
				
			row.createCell(5).setCellValue(order.getToUserName()==null?"":order.getToUserName());//收货人姓名
            row.createCell(6).setCellValue(order.getMobile()==null?"":order.getMobile());//收货人电话
			row.createCell(7).setCellValue(order.getSheng());//所在省
			row.createCell(8).setCellValue(order.getChengshi());//所在市
			row.createCell(9).setCellValue(order.getDiqu());//所在区
            row.createCell(10).setCellValue(order.getAddress()==null?"":order.getAddress());//详细地址
            if(order.getOrder_status()==0){//订单状态
            	row.createCell(11).setCellValue("未支付");
            }else if(order.getOrder_status()==1){
            	row.createCell(11).setCellValue("已支付");
            }else if(order.getOrder_status()==2){
            	row.createCell(11).setCellValue("申请退货");
            }else if(order.getOrder_status()==3){
            	row.createCell(11).setCellValue("已发货");
            }else if(order.getOrder_status()==4){
            	row.createCell(11).setCellValue("已完成");
            }else if(order.getOrder_status()==5){
            	row.createCell(11).setCellValue("已退货");
            }else if(order.getOrder_status()==6){
            	row.createCell(11).setCellValue("已收货");
            }else {
            	row.createCell(11).setCellValue("");
			}
            row.createCell(12).setCellValue(order.getShuliang()==null?"":order.getShuliang().toString());//购买数量
            row.createCell(13).setCellValue(order.getSize()==null?"":order.getSize());//单位
	        row.createCell(14).setCellValue(order.getKuaidiName()==null?"":order.getKuaidiName());//快递名称
            row.createCell(15).setCellValue(order.getKuaidiNo()==null?"":order.getKuaidiNo());//快递编号
            row.createCell(16).setCellValue(order.getPname()==null?"":order.getPname());//水果种类
        }
        // 第六步，将文件存到指定位置  
        try  
        {  
        	HttpServletResponse response = ServletActionContext.getResponse();
        	HttpServletRequest request = ServletActionContext.getRequest();
        	String filename = null;
        	if (null!=orderType &&! "".equals(orderType)) {
    			if(orderType.equals("0")){
    				filename = "未支付订单列表";
    			}else if(orderType.equals("1")){
    				filename = "已支付订单列表";
    			}else if(orderType.equals("2")){
    				filename = "申请退货订单列表";
    			}else if(orderType.equals("3")){
    				filename = "已发货订单列表";
    			}else if(orderType.equals("4")){
    				filename = "已完成订单列表";
    			}else if(orderType.equals("5")){
    				filename = "已退货订单列表";
    			}else if(orderType.equals("6")){
    				filename = "已收货订单列表";
    			}
    		}else {
    			filename = "已支付订单列表";
    		}
        	filename += new String((new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date())).getBytes());
        	filename += ".xls";
        	filename = encodeFilename(request.getHeader("user-agent"),filename);
        	response.setHeader("Content-disposition","attachment; filename=" +filename);
        	OutputStream outputStream = response.getOutputStream();
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        } 
		return null;
	}*/
	
	
	@SuppressWarnings({ "static-access"})
    private String getValue(XSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            // 返回字符串类型的值
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
	
	 /**
     * 得到Excel表中的值
     * 
     * @param hssfCell
     *            Excel中的每一个格子
     * @return Excel中每一个格子中的值
     */
    @SuppressWarnings({ "static-access" })
    private String getValue(HSSFCell hssfCell) {
    	NumberFormat nf = NumberFormat.getInstance();
    	nf.setGroupingUsed(false);
		// 设置数的小数部分所允许的最小位数
		nf.setMinimumFractionDigits(0);
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            return String.valueOf(nf.format(hssfCell.getNumericCellValue()));
        } else {
            // 返回字符串类型的值
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
	
	public static String encodeFilename(String userAgent, String filename) {
		try {
			if (userAgent.contains("Firefox")) { //ff
				filename = "=?UTF-8?B?"
						+ new BASE64Encoder().encode(filename.getBytes("utf-8"))
						+ "?=";
			} else { // IE
				filename = URLEncoder.encode(filename, "utf-8");
			}
		} catch (IOException e){
			e.printStackTrace();
		} 
		return filename;
	}
	
	public String applyReturnPurchase() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		Map<String, Integer> compare = new HashMap<String, Integer>();
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("createDate", false);
		boolean chaxun = false;
		if (null!=userId &&! "".equals(userId)) {
			conditionProperties.put("t.userId", userId);
			compare.put("t.userId", 13);
			chaxun = true;
		}
		if(null != userName && !userName.isEmpty()){
			conditionProperties.put("t.userName", userName);
			compare.put("t.userName", 14);
			chaxun = true;
		}
		if (null!=ordersBH &&! "".equals(ordersBH)) {
			conditionProperties.put("ordersBH", ordersBH);
			compare.put("ordersBH", 2);
			chaxun = true;
		}
		if (null!=toUserName &&! "".equals(toUserName)) {
			conditionProperties.put("toUserName", toUserName);
			compare.put("toUserName", 2);
			chaxun = true;
		}
		if (null!=kuaidiNo &&! "".equals(kuaidiNo)) {
			conditionProperties.put("kuaidiNo", kuaidiNo);
			compare.put("kuaidiNo", 2);
			chaxun = true;
		}
		if(null!=mobile &&! "".equals(mobile)){
			conditionProperties.put("mobile", mobile);
			compare.put("mobile", 2);
			chaxun = true;
		}
		if(chaxun){
			int count_size =ordersDAO.cout_size1(conditionProperties, compare);
			
			// 修改的时候保存当前页
			if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
				this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
				
				this.setPagerMethod("next");
			}
			this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count_size));
			this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
			
			List<Orders> litPager = ordersDAO.findAllPagerList_new1(conditionProperties, compare, sort, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
			request.setAttribute("litPager", litPager);
		}
		return "applyReturnPurchase";
	}
	
	public String tuihuo(){
		if(ordersId!=null&&!"".equals(ordersId)){
			Orders order = (Orders) ordersDAO.findById(ordersId);
			if(order != null){
				order.setOrder_status(2);
				ordersDAO.update(order);//置状态order_status为2
				//查询本用户是否具有族长资格
				String hql = "from Orders o where o.userId="+order.getUserId()+"and o.order_status in (1,3,4,6)";
				List<Orders> list = (List<Orders>)ordersDAO.findbyHql(hql);
				//当不具有资格时查询所有一级用户
				if(list.size()==0){
					String hql1 = "from User u where u.referrerId="+order.getUserId();
					List<User> users = userDAO.findbyHql(hql1);
					//本用户下的所有下级用户全部清空
					if(users != null && users.size()>0){
						for(User u:users){
							userDAO.update(u);
						}
					}
				}
			}
		}
		return "applyReturnPurchase";
	}
	public String applyReturn(){
		if(ordersId!= null && !"".equals(ordersId)){
			Orders order = (Orders) ordersDAO.findById(ordersId);
			order.setTel("1");//order表里tel用来表示退货消息,1表示退货申请2表示退货成功,这里退货申请置1
			
			ApplyReturnPurchase arp = new ApplyReturnPurchase();
			arp.setApplyTime(new Date());
			arp.setKuaiDiNum(order.getKuaidiNo());
			arp.setOrderBH(order.getOrdersBH());
			arp.setPhone(order.getMobile());
			arp.setStatus(0);
			arp.setUserName(order.getToUserName());
			arp.setOrderId(ordersId);
			try {
				arp.setReturnNum(Integer.parseInt(order.getSize()));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			
			User user = (User) userDAO.findById(order.getUserId());
			ordersDAO.update(order);
			applyReturnPurchaseDao.saveOrUpdate(arp);
		}
		return "applyReturnPurchase";
	}
	
	public void getAllReturnNum(){
		if(ordersId != null){
			String hql = "from ApplyReturnPurchase arp where arp.orderId =" +ordersId;
			List<ApplyReturnPurchase> list = applyReturnPurchaseDao.findByHql(hql);
			int alreadyApplyNum = 0;
			if(list != null && list.size() !=0){
				for(ApplyReturnPurchase arp:list){
					alreadyApplyNum += arp.getReturnNum();
				}
			}
			JSONObject json = new JSONObject();
			try {
				HttpServletResponse response = ServletActionContext.getResponse();
				json.put("alreadyApplyNum", alreadyApplyNum);
				response.getWriter().write(json.toString());
			} catch (JSONException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String listAllApplys(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		Map<String, Integer> compare = new HashMap<String, Integer>();
		Map<String, Boolean> sort = new HashMap<String, Boolean>();
		sort.put("applyTime", false);
		
		if (null!=orderType &&! "".equals(orderType)) {
			conditionProperties.put("status", Integer.parseInt(orderType));
			compare.put("status", 0);
		}else {
			orderType = "0";
			conditionProperties.put("status", 0);
			compare.put("status", 0);
		}
		if (null!=ordersBH &&! "".equals(ordersBH)) {
			conditionProperties.put("orderBH", ordersBH);
			compare.put("orderBH", 2);
		}
		if (null!=toUserName &&! "".equals(toUserName)) {
			conditionProperties.put("userName", toUserName);
			compare.put("userName", 2);
		}
		if (null!=kuaidiNo &&! "".equals(kuaidiNo)) {
			conditionProperties.put("kuaiDiNum", kuaidiNo);
			compare.put("kuaiDiNum", 2);
		}
		if(null!=mobile &&! "".equals(mobile)){
			conditionProperties.put("phone", mobile);
			compare.put("phone", 2);
		}
		int count_size =applyReturnPurchaseDao.cout_size_Commen(conditionProperties, compare);
		
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count_size));
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
		
		List<Orders> litPager = applyReturnPurchaseDao.findAllPagerList(conditionProperties, compare, sort, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		request.setAttribute("litPager", litPager);
		request.setAttribute("status", orderType);
		return "listAllApplys";
	}
	public String dealApply(){
		ApplyReturnPurchase arp = (ApplyReturnPurchase) applyReturnPurchaseDao.findById(ordersId);
		arp.setDealTime(new Date());
		arp.setStatus(2);
		Orders order = (Orders) ordersDAO.findById(arp.getOrderId());
		if(order != null){
			order.setOrder_status(5);
			order.setTel("2");//order表里tel用来表示退货消息,1表示退货申请2表示退货成功,这里退货处理置2
			ordersDAO.update(order);//置状态order_status为2
			//查询本用户是否具有族长资格
			String hql = "from Orders o where o.userId="+order.getUserId()+"and o.order_status in (1,3,4,6)";
			List<Orders> list = (List<Orders>)ordersDAO.findbyHql(hql);
			//当不具有资格时查询所有一级用户
			if(list.size()==0){
				String hql1 = "from User u where u.referrerId="+order.getUserId();
				List<User> users = userDAO.findbyHql(hql1);
				//本用户下的所有下级用户全部清空
			}
		}
		
			User user = (User) userDAO.findById(order.getUserId());
			if(user != null){
				userDAO.update(user);
			}
		
//		if(refferUser != null)
//			try {
//				HttpConnectionUtil.sendHttpMsg("3", user, null, null,refferUser.getWxOpenid());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		applyReturnPurchaseDao.update(arp);
		
		return "dealApply";
	}
	public String deleteReturn(){
		ApplyReturnPurchase arp = (ApplyReturnPurchase) applyReturnPurchaseDao.findById(ordersId);
		Orders order = (Orders) ordersDAO.findById(arp.getOrderId());
		order.setTel("");
		
		User refferUser ;
		User user = (User) userDAO.findById(order.getUserId());
		ordersDAO.update(order);
		applyReturnPurchaseDao.deleteById(ordersId);
		return "dealApply";
	}
	
	public String appOrder() throws Exception {
		Orders orders = (Orders)ordersDAO.findById(ordersId);
//		User user = null;
		AdminUser adminUser = (AdminUser) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(adminUser == null){
			return "adminerror";
		}
		if (orders != null) {
			orders.setOrder_status(3);
			orders.setKuaidiName(kuaidiName);
			orders.setKuaidiNo(kuaidiNo);
			orders.setFahuoDate(new Date());
			if(adminUser!=null){
				orders.setFromUserName(adminUser.getUserName());
				orders.setTel(adminUser.getPhone());
			}
			orders.setFahuoDate(new Date());
			ordersDAO.update(orders);
			ServletActionContext.getRequest().setAttribute("message", "发货成功");
//			user = orders.getUser();
//			if(user == null)
//				user = (User) userDAO.findById(orders.getUserId());
		}
		else {
			ServletActionContext.getRequest().setAttribute("message", "此订单不存在");
		}
//		if(user == null){
//			ServletActionContext.getRequest().setAttribute("message", "此订单不存在");
//		}else {
//			try {
//				HttpConnectionUtil.sendHttpMsg("1", user, null, orders,null);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		return "goBackList1";
	}
	public String appOrderbeizhu() throws Exception {
		Orders orders = (Orders)ordersDAO.findById(ordersId);
		User user = null;
		if (orders != null) {
			orders.setComments(kuaidiNo);
			ordersDAO.update(orders);
			ServletActionContext.getRequest().setAttribute("message", "操作成功");
			user = (User) userDAO.findById(orders.getUserId());
		}
		else {
			ServletActionContext.getRequest().setAttribute("message", "此订单不存在");
		}
		if(user == null){
			ServletActionContext.getRequest().setAttribute("message", "此订单不存在");
		}
		return "beizhu";
	}
	
	public String update() throws Exception  {
		/*HttpServletRequest request = ServletActionContext.getRequest();
		AdminUser loginUser = (AdminUser)request.getSession().getAttribute("user");
		News news = (News)newsDAO.findById(newsId);
		if (news != null) {
			news.setTitle(title);
			news.setContent(content);
			news.setUser(loginUser);
			newsDAO.update(news);
			
			ServletActionContext.getRequest().setAttribute("message", " 修改成功 ");
		}
		else {
			ServletActionContext.getRequest().setAttribute("message", "此公告不存在");
		}*/
		
		return "goBackList";
	}
	
	public static void main(String[] args) {
		int a = 19/5;
	//	System.out.println(a);
		
	/*	String pname = "";
		for (int i=1;i<=5;i++) {
			switch (i) {
			case 1:
				pname = "1";
				System.out.println(i);
				break;
			case 2:
				pname = "2";
				System.out.println(i);
				break;
			case 3:
				pname = "3";
				System.out.println(i);
				break;
			case 4:
				System.out.println(i);
				break;
			case 5:
				System.out.println(i);
				break;
			}
		}*/
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
		String fd = sdf.format(new Date());
		for (int i=0;i<54;i++) {
			System.out.println("ZD"+fd+CommUtils.getSixRandom());
		}
	}

	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
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
	public String getoPhone() {
		return oPhone;
	}
	public void setoPhone(String oPhone) {
		this.oPhone = oPhone;
	}
	public String getoUserName() {
		return oUserName;
	}
	public void setoUserName(String oUserName) {
		this.oUserName = oUserName;
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

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public Integer getOrder_status() {
		return order_status;
	}

	public void setOrder_status(Integer order_status) {
		this.order_status = order_status;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getShuliang() {
		return shuliang;
	}

	public void setShuliang(String shuliang) {
		this.shuliang = shuliang;
	}

	public Integer[] getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(Integer[] orderIds) {
		this.orderIds = orderIds;
	}

	public Integer getPaixu() {
		return paixu;
	}

	public void setPaixu(Integer paixu) {
		this.paixu = paixu;
	}

	public File getImportFile() {
		return importFile;
	}

	public void setImportFile(File importFile) {
		this.importFile = importFile;
	}
	
	
}
