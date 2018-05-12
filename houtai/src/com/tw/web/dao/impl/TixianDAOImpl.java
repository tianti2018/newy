package com.tw.web.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.tw.web.dao.TixianDAO;
import com.tw.web.hibernate.persistent.Tixian;

@Repository
public class TixianDAOImpl extends CRUDBaseHibernateDAOImpl implements TixianDAO {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getPojoClass() {
		return Tixian.class;
	}

	@Override
	public Double zong_zhi_chu(String startDate, String endDate) {
		String sql = "SELECT SUM(tixianMoney) from tixian where type =1";
		if(startDate != null && !startDate.isEmpty())
			sql += " and tixianDate >='" + startDate+"'";
		if(endDate != null && !endDate.isEmpty())
			sql += " and tixianDate <='" + endDate+"'";
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
	public Double countTiXianMeth(Integer userId, String type) {
		Double sum = 0d;
		if(null != userId && !"".equals(userId)){
			String sql ="SELECT SUM(t.tixianMoney) FROM tixian t where userId ="+userId+" and t.type = "+type;
			List list = super.findBySql(sql);
			if(list.size() > 0){
				if(!"".equals(list.get(0)) && null != list.get(0)){
					sum =  (Double) list.get(0);
				}
				
			}
		}
		return sum;
	}
	
	public Double zong_yi_ti(String startDate, String endDate){
		String sql = "SELECT SUM(tixianMoney) from tixian where type =1";
		if(startDate != null && !startDate.isEmpty())
			sql += " and tixianDate >='" + startDate+"'";
		if(endDate != null && !endDate.isEmpty())
			sql += " and tixianDate <='" + endDate+"'";
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
	
}
