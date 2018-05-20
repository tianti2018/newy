package com.tw.web.dao.impl;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.tw.web.dao.OrdersDAO;
import com.tw.web.hibernate.persistent.Orders;
import com.tw.web.hibernate.persistent.User;
import com.tw.web.util.SystemConfigUtil;

@Repository
public class OrdersDAOImpl extends CRUDBaseHibernateDAOImpl implements OrdersDAO {

	@Override
	protected Class getPojoClass() { 
		return Orders.class;
	}

	@Override
	public Double zong_shou_ru(String j_fromDate, String j_endDate) {
		String sql = "select SUM(money) from orders where order_status = 4";
		
		if(j_fromDate != null && !j_fromDate.isEmpty())
			sql += " and createDate >='" + j_fromDate+"'";
		if(j_endDate != null && !j_endDate.isEmpty())
			sql += " and createDate <='" + j_endDate+"'";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		List list = query.list();
		if(list != null && list.size()>0){
			Object o = list.get(0);
			if(o!= null)
				return (Double) o;
			else
				return 0.0;
		}
		else {
			return 0.0;
		}
	}

	@Override
	public List findLevelUserOrderMoney(Integer userId, String orderType,
			String orderLevel) {
		List list = null;
		StringBuffer sql = new StringBuffer();
		if(orderLevel.equals("1")){
			sql.append("select if(sum(money)>0,sum(money),0) total from orders o where o.userId in (");
			sql.append("select t2.userId from users t1, users t2 where t1.userId=t2.referrerId and (t1.userId= "+userId+" )  and t2.wxOpenid is NOT NULL) and o.order_status in("+orderType+")");
			
			list=super.findBySql(sql.toString());
			
		}else if(orderLevel.equals("2")){
			sql.append("select if(sum(money)>0,sum(money),0) total from orders o1 where o1.userId in (");
			sql.append("select t2.userId from users t1, users t2 where t1.userId=t2.referrerId and (t1.referrerId= "+userId+" )  and t2.wxOpenid is NOT NULL) and o1.order_status in("+orderType+")");
			
			list=super.findBySql(sql.toString());
			
		}else if(orderLevel.equals("3")){
			sql.append("select if(sum(money)>0,sum(money),0) total from orders o2 where o2.userId in (");
		    sql.append("select t3.userId from users t3 where t3.referrerId in(select t2.userId from users t1, users t2 where t1.userId=t2.referrerId ");
		    sql.append("and (t1.referrerId= "+userId+" )) and t3.wxOpenid is NOT NULL ) and o2.order_status in("+orderType+")");
		    
		    list=super.findBySql(sql.toString());
		}else if(orderLevel.equals("all")){
			
			sql.append("select if(sum(money)>0,sum(money*(SELECT p.levelone from products p where p.productsId=o.productsId)),0) total from orders o where o.userId in (");
			sql.append("select t2.userId from users t1, users t2 where t1.userId=t2.referrerId and (t1.userId= "+userId+" )  and t2.wxOpenid is NOT NULL) and o.order_status in("+orderType+")");

			sql.append(" union all ");
			sql.append("select if(sum(money)>0,sum(money*(SELECT p.leveltwo from products p where p.productsId=o1.productsId)),0) total from orders o1 where o1.userId in (");
			sql.append("select t2.userId from users t1, users t2 where t1.userId=t2.referrerId and (t1.referrerId= "+userId+" )  and t2.wxOpenid is NOT NULL) and o1.order_status in("+orderType+")");

			sql.append(" union all ");
			sql.append("select if(sum(money)>0,sum(money*(SELECT p.levelthr from products p where p.productsId=o2.productsId)),0) total from orders o2 where o2.userId in (");
		    sql.append("select t3.userId from users t3 where t3.referrerId in(select t2.userId from users t1, users t2 where t1.userId=t2.referrerId ");
		    sql.append("and (t1.referrerId= "+userId+" )) and t3.wxOpenid is NOT NULL ) and o2.order_status in("+orderType+")");
		    list=super.findBySql(sql.toString());    
		}
		
		return  list;
	}

	@Override
	public List findCurrUserAllOrder(Integer userId) {
		List list = null;
		StringBuffer sql = new StringBuffer();
		
			sql.append("select if(SUM(money)>0,SUM(money),0) total from orders o where o.userId in (");
			sql.append("select t2.userId from users t1, users t2 where t1.userId=t2.referrerId and (t1.userId= "+userId+" )  and t2.wxOpenid is NOT NULL) and o.order_status not in(0,5)");
		    sql.append("  UNION ALL  ");
		    sql.append("select if(SUM(money)>0,SUM(money),0) total from orders o1 where o1.userId in (");
		    sql.append("select t2.userId from users t1, users t2 where t1.userId=t2.referrerId and (t1.referrerId= "+userId+" )  and t2.wxOpenid is NOT NULL) and o1.order_status not in(0,5)");
		    sql.append("  UNION ALL  ");
		    sql.append("select if(SUM(money)>0,SUM(money),0) total from orders o2 where o2.userId in (");
		    sql.append("select t3.userId from users t3 where t3.referrerId in(select t2.userId from users t1, users t2 where t1.userId=t2.referrerId ");
		    sql.append("and (t1.referrerId= "+userId+" )) and t3.wxOpenid is NOT NULL ) and o2.order_status not in(0,5)");
		    
		    list=super.findBySql(sql.toString());
		    return list;
	}

	@Override
	public Double zong_li_run(String j_fromDate, String j_endDate) throws ParseException  {
		
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		Map<String, Integer> compare = new HashMap<String, Integer>();
		
		conditionProperties.put("order_status", new Byte("4"));
		compare.put("order_status", 0);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
		Date from = null;
		Date end = null;
		if (null!=j_fromDate &&! "".equals(j_fromDate)) {
			from = sdf.parse(j_fromDate);
			conditionProperties.put("createDate", from);
			compare.put("createDate", 8);
		}
		if (null!=j_endDate &&! "".equals(j_endDate)) {
			end = sdf.parse(j_endDate);
			conditionProperties.put("createDate", end);
			compare.put("createDate", 9);
		}
		
		if(null!=j_fromDate &&! "".equals(j_fromDate)&&null!=j_endDate &&! "".equals(j_endDate)){
			Date[] date = {from,end};
			conditionProperties.put("createDate", date);
			compare.put("createDate", 10);
		}
		
		List<Orders> orders = super.findAllPagerList_new1(conditionProperties, compare, null, 0, 0, "all");
		Double lirun = 0.0;
		if(orders.size()>0){
			for(Orders o:orders){
				Double bilv = 0.0 ;
				Double chengben = Double.valueOf(SystemConfigUtil.getString("chengben"));
				Double danjia =Double.valueOf(SystemConfigUtil.getString("danjia"));
				lirun += o.getMoney()*(1-bilv)-chengben*o.getMoney()/danjia;
			}
		}
		DecimalFormat   fnum   =   new   DecimalFormat("##0.00"); //四舍五入，保留两位小数 
		return Double.valueOf(fnum.format(lirun));
		
	}

	@Override
	public Double zong_zhi_chu(String j_fromDate, String j_endDate) throws ParseException {
		Map<String, Object> conditionProperties = new HashMap<String, Object>();
		Map<String, Integer> compare = new HashMap<String, Integer>();
		
		conditionProperties.put("order_status", new Byte("4"));
		compare.put("order_status", 0);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
		Date from = null;
		Date end = null;
		if (null!=j_fromDate &&! "".equals(j_fromDate)) {
			from = sdf.parse(j_fromDate);
			conditionProperties.put("createDate", from);
			compare.put("createDate", 8);
		}
		if (null!=j_endDate &&! "".equals(j_endDate)) {
			end = sdf.parse(j_endDate);
			conditionProperties.put("createDate", end);
			compare.put("createDate", 9);
		}
		
		if(null!=j_fromDate &&! "".equals(j_fromDate)&&null!=j_endDate &&! "".equals(j_endDate)){
			Date[] date = {from,end};
			conditionProperties.put("createDate", date);
			compare.put("createDate", 10);
		}
		
		List<Orders> orders = super.findAllPagerList_new1(conditionProperties, compare, null, 0, 0, "all");
		Double zongzhichu = 0.0;
		if(orders.size()>0){
			for(Orders o:orders){
				Double bilv = 0.0 ;
				zongzhichu += o.getMoney()*bilv;
			}
		}
		DecimalFormat   fnum   =   new   DecimalFormat("##0.00"); //四舍五入，保留两位小数 
		return Double.valueOf(fnum.format(zongzhichu));
	}


}
