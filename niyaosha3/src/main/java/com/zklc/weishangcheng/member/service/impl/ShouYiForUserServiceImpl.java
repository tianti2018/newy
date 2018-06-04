package com.zklc.weishangcheng.member.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderJinHuo;
import com.zklc.weishangcheng.member.hibernate.persistent.Orders;
import com.zklc.weishangcheng.member.hibernate.persistent.ShouYiForUser;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.UserVo;
import com.zklc.weishangcheng.member.service.OrderJinHuoService;
import com.zklc.weishangcheng.member.service.ShouYiForUserService;
@Service
public class ShouYiForUserServiceImpl extends BaseServiceImp<ShouYiForUser, Integer> implements ShouYiForUserService {

	@Autowired
	private OrderJinHuoService orderJinHuoService;
	
	@Override
	public List<ShouYiForUser> findShouyiList(UserVo userVo, String orderType, Integer pageNum, String date1,
			String date2) {
		String sql = "";
		if(userVo.getUsery()!=null){
			if(userVo.getUsery().getDianPuId()!=null){
				sql += " select * from shouyi_user where dianpuId ="+userVo.getUsery().getDianPuId();
				if(orderType!=null&&!orderType.equals("")){
					if(orderType.equals("2"))
						sql+=" and status =2 and shouyi>=0";
					else if(orderType.equals("1"))
						sql +=" and status =2 and shouyi<0";
					else if(orderType.equals("3"))
						sql+=" and status =1 ";
				}
				sql+=" order by createDate desc";
		        sql+=" limit "+pageNum+" ,"+10;
		        List<ShouYiForUser> shouYiForUsers = findBySql(ShouYiForUser.class, sql, null);
		        return shouYiForUsers;
			}
		}
		return null;
	}
	
	@Override
	public Double findYvE(Integer dianPuId) {
		return findMoneyByStatusAndTixian(dianPuId, 2, null, null);
	}

	@Override
	public Double findShenQing(Integer dianpuId) {
		return findMoneyByStatusAndTixian(dianpuId, 2, 1, null);
	}

	@Override
	public Double findYiTongGuo(Integer dianpuId) {
		return findMoneyByStatusAndTixian(dianpuId, 2, 2, null);
	}

	@Override
	public Double findShouRu(Integer dianpuId) {
		return findMoneyByStatusAndTixian(dianpuId, 2, null, 0);
	}

	@Override
	public Double findZhiChu(Integer dianpuId) {
		return findMoneyByStatusAndTixian(dianpuId, 2, null, 1);
	}

	@Override
	public Double findDaiShengXiao(Integer dianpuId) {
		return findMoneyByStatusAndTixian(dianpuId, 1, null, null);
	}

	@Override
	public Double findMoneyByStatusAndTixian(Integer dianpuId, Integer status, Integer tixianStatus, Integer shrzhch) {
		String sql = "select COALESCE(SUM(shouyi),0) from shouyi_user where dianpuId ="+dianpuId;
		if(shrzhch!=null){
			if(shrzhch==0){
				sql+=" and shouyi >0";
			}else if(shrzhch==1){
				sql+=" and shouyi <0";
			}
		}
		if(tixianStatus!=null){
			sql+=" and tixian ="+tixianStatus;
		}
		if(status!=null){
			sql += " and status =" +status;
		}
		List list = findBySql(sql, null);
		if(list!=null&&list.size()>0){
			java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
			// 不使用千分位，即展示为11672283.234，而不是11,672,283.234
			nf.setGroupingUsed(false);
			// 设置数的小数部分所允许的最小位数
			nf.setMinimumFractionDigits(0);
			return Double.parseDouble(nf.format(list.get(0)));
		}
		return 0.0;
	}

	@Override
	public Double findShouRu(Usery usery, String date1, String date2) {
		Double shouru = 0.0;
		if(usery.getDianPuId()!=null){
			String sql = "select COALESCE(SUM(shouyi),0) from shouyi_user where status = 2 and shouyi>0 and dianpuId ="+usery.getDianPuId();
			if((date1!=null&&!date1.equals(""))&&(date2!=null&&!date2.equals(""))){
		        sql+=" and createDate between '"+date1+"' and '"+date2+"'";
	        } else if((date1==null||date1.equals(""))&&(date2!=null&&!date2.equals(""))){
	        	sql+=" and createDate<='"+date2+"'";
	        } else if((date1!=null&&!date1.equals(""))&&(date2==null||date2.equals(""))){
	        	sql+=" and createDate>='"+date1+"'";
	        }
			List list = findBySql(sql, null);
			if(list!=null&&list.size()>0){
				shouru = Double.parseDouble(list.get(0).toString());
			}
		}
		return shouru;
	}

	@Override
	public void deleteShouyiByOrders(List<Orders> deleteList) {
		List<OrderJinHuo> orderJinHuos = new ArrayList<>();
		List<ShouYiForUser> shouYiForUsers = new ArrayList<>();
		if(deleteList.size()>0){
			for(Orders order:deleteList){
				ShouYiForUser shouyi1 = findbyOrdersId(order.getOrdersId());
				if(shouyi1!=null){
					shouYiForUsers.add(shouyi1);
				}
				OrderJinHuo jinHuo = orderJinHuoService.findbyOrdersId(order.getOrdersId());
				if(jinHuo!=null){
					orderJinHuos.add(jinHuo);
					ShouYiForUser shouyi2 = findbyOrdersId(jinHuo.getId());
					if(shouyi2!=null){
						shouYiForUsers.add(shouyi2);
					}
				}
			}
		}
		deleteAll(shouYiForUsers);
		orderJinHuoService.deleteAll(orderJinHuos);
	}

	@Override
	public void updateShouyiStatusByOrders(List<Orders> updateOrders) {
		List<OrderJinHuo> orderJinHuos = new ArrayList<>();
		List<ShouYiForUser> shouYiForUsers = new ArrayList<>();
		if(updateOrders.size()>0){
			for(Orders order:updateOrders){
				ShouYiForUser shouyi1 = findbyOrdersId(order.getOrdersId());
				if(shouyi1!=null){
					shouyi1.setStatus(2);
					shouYiForUsers.add(shouyi1);
				}
				OrderJinHuo jinHuo = orderJinHuoService.findbyOrdersId(order.getOrdersId());
				if(jinHuo!=null){
					orderJinHuos.add(jinHuo);
					ShouYiForUser shouyi2 = findbyOrdersId(jinHuo.getId());
					if(shouyi2!=null){
						shouyi2.setStatus(2);
						shouYiForUsers.add(shouyi2);
					}
				}
			}
		}
		saveOrUpdateAll(shouYiForUsers);
	}

	@Override
	public ShouYiForUser findbyOrdersId( Integer ordersId) {
		String hql = "from ShouYiForUser where ordersId =" + ordersId;
		List<ShouYiForUser> shouyis = findByHql(hql, null);
		if(shouyis!=null&&shouyis.size()>0){
			return shouyis.get(0);
		}
		return null;
	}

}
