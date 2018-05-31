package com.zklc.weishangcheng.member.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderJinHuo;
import com.zklc.weishangcheng.member.hibernate.persistent.Orders;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.OrdersVo;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.UserVo;
import com.zklc.weishangcheng.member.service.OrderJinHuoService;
import com.zklc.weishangcheng.member.service.OrdersService;
import com.zklc.weishangcheng.member.service.WeixinAutosendmsgService;
@Service
public class OrderJinHuoServiceImpl extends BaseServiceImp<OrderJinHuo, Integer> implements OrderJinHuoService {
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private WeixinAutosendmsgService autosendmsgService;
	
	@Override
	public OrderJinHuo findbyOrdersId(Integer ordersId) {
		return findUniqueByProperty("ordersId", ordersId);
	}
	

	@Override
	public List findMyOrderList(UserVo userVo, String type,String date1,String date2,Integer pageNum) {
		if(pageNum==null || pageNum.equals("")){
			pageNum=0;
		}else{
			pageNum=pageNum*10;
		}
		String sql="select * from orders where 1=1 ";
		if(type!=null&&!type.equals("")){
			if(type.equals("0"))
				sql+=" and order_status ="+ type;
			else if(type.equals("1")){
				sql+=" and order_status in (1,3)";
			}else if(type.equals("2")){
				sql+=" and (pingjia in (0,1) or pingjia is null) and order_status = 6";
			}else if(type.equals("3")){
				sql+=" and order_status = 2";
			}
		}
		if(userVo.getUser()!=null&&userVo.getUsery()==null){
			sql+=" and userId = "+userVo.getUser().getUserId();
		}else if(userVo.getUser()==null&&userVo.getUsery()!=null){
			sql+=" and useryId = "+userVo.getUsery().getId();
		}else if(userVo.getUser()!=null&&userVo.getUsery()!=null){
			sql+=" and ((userId = "+userVo.getUser().getUserId()+" and useryId is null) or ( userId is null and useryId ="
					+userVo.getUsery().getId() + ") or (userId ="+userVo.getUser().getUserId() +" and useryId = "+userVo.getUsery().getId()+"))";
		}
		
		if((date1!=null&&!date1.equals(""))&&(date2!=null&&!date2.equals(""))){
          sql+=" and createDate between '"+date1+"' and '"+date2+"'";
        } else if((date1==null||date1.equals(""))&&(date2!=null&&!date2.equals(""))){
        	sql+=" and createDate<='"+date2+"'";
        } else if((date1!=null&&!date1.equals(""))&&(date2==null||date2.equals(""))){
        	sql+=" and createDate>='"+date1+"'";
        }
                    
        sql+=" order by createDate desc";
        sql+=" limit "+pageNum+" ,"+10;
        System.out.println(sql);
        List<Orders> orders= findBySql(Orders.class, sql, null);
		return orders;
	}

	private String str_to_date(String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void timerUpdateOrderStatus() {
		String hql = " from Order o where o.orderStatus = 0";
		List<Orders> updateList = new ArrayList<>();
		List<Orders> deleteList = new ArrayList<>();
		List<Orders> weizhifulist = super.findByHql(hql, null);
		List<Orders> shouyiOrders = new ArrayList<>();
		Date now = new Date();
		if(weizhifulist!=null&&weizhifulist.size() > 0){
			for(Orders order:weizhifulist){
				if(now.getTime()-order.getCreateDate().getTime()>1000*60*60*24*3){
					deleteList.add(order);
				}
			}
		}
		hql = " from Order o where o.orderStatus = 3";
		List<Orders> yifahuoList = findByHql(hql, null);
		if(yifahuoList!=null&&yifahuoList.size()>0){
			for(Orders order:yifahuoList){
				if(now.getTime()-order.getFahuoDate().getTime()>1000*60*60*24*7){
					order.setOrderStatus(6);
					updateList.add(order);
				}
			}
		}
		hql = " from Order o where o.orderStatus = 6";
		List<Orders> yishouhuoList = findByHql(hql, null);
		if(yishouhuoList!=null&&yishouhuoList.size()>0){
			for(Orders order:yishouhuoList){
				if(now.getTime()-order.getShouhuoDate().getTime()>1000*3600*24*7){
					order.setOrderStatus(4);
					updateList.add(order);
				}
			}
		}
		if(deleteList.size()>0){
			deleteAll(deleteList);
		}
		if(updateList.size()>0){
			saveOrUpdateAll(updateList);
		}
		//最后还需要更新收益列表里面的状态
		//这里还没有确定是哪一个状态才修改,如果定了就去添加list更新状态吧
		
//		updateUserMsg();
	}
	
	public void updateUserMsg(){
//		String hql = "from Users u where u.unionid is null";//259992
//		List<Users> users = userService.findByHql(hql, null);
////		List<Users> users = userService.findAll();
//		
//		if(users.size()>0){
//			for(Users user:users){
//				String wxOpenId = user.getWxOpenid();
//				if(wxOpenId != null&&user.getUnionid()==null){
//					UserInfoUtil userInfo = autosendmsgService.processUserInfoObject(wxOpenId);
//					if(userInfo!=null ){
//						boolean update = false;
//						if(userInfo.getUnionid()!=null){
//							user.setUnionid(userInfo.getUnionid());
//							update = true;
//							System.out.println(user.getUserId()+"\t"+user.getUnionid());
//						}
//						if(update){
//							userService.update(user);
//						}
//					}
//				}
//			}
//		}
//		System.out.println("over");
	}

	
	public int moneyPay(OrderJinHuo order, String openid,Users user) {	return 0;
	}
	
	public void creatFhrecord(Integer puserId,Integer formUserId,int level, Double fhmoney,Integer ordersId) {}
	
	public List<Integer> findAllParent(Integer userId,List<Integer> list) {
		if (null==userId) {
			return list;
		}
		if (list.size()==3) { //15层的时候此时变为15
			return list;
		}
		if (null==list) {
			list = new ArrayList<Integer>();
		}
		
		return list;
	}

	@Override
	public void saveAndCFh(OrderJinHuo order, Users user) {

		save(order);
	}
	
	
	@Override
	public Integer findSellerNumByQi(Integer qi) {
		
		Number returnNum=0;
		StringBuffer hql = new StringBuffer("select count(ordersId) from Order t where 1=1 ");
		if(qi!=null){
				hql.append(" and t.orderStatus=1");
				hql.append(" and t.qi="+qi);
				List lit =super.findByHql(hql.toString(), null);
				if (null!=lit) {
					returnNum = (null==lit.get(0))?0:(Number)lit.get(0);
				}
		}
		return returnNum.intValue();
	}


	@Override
	public OrderJinHuo findOrderByOrderBH(String out_trade_no) {
		return findOrderByOrderBH(out_trade_no);
	}


	@Override
	public List<OrdersVo> findMyDianpuOrderList(UserVo userVo, String orderType, String date1, String date2,
			Integer pageNum) {

		if(pageNum==null || pageNum.equals("")){
			pageNum=0;
		}else{
			pageNum=pageNum*10;
		}
		String sql="select * from orders where dianpuId= "+userVo.getUsery().getDianPuId();
		
		
		if((date1!=null&&!date1.equals(""))&&(date2!=null&&!date2.equals(""))){
          sql+=" and createDate between '"+date1+"' and '"+date2+"'";
        } else if((date1==null||date1.equals(""))&&(date2!=null&&!date2.equals(""))){
        	sql+=" and createDate<='"+date2+"'";
        } else if((date1!=null&&!date1.equals(""))&&(date2==null||date2.equals(""))){
        	sql+=" and createDate>='"+date1+"'";
        }
                    
        sql+=" order by createDate desc";
        sql+=" limit "+pageNum+" ,"+10;
        System.out.println(sql);
        List<OrdersVo> orders= findBySql(OrdersVo.class, sql, null);
		return orders;
	
	}


	@Override
	public List<OrdersVo> findMyDianpuOrderList_jinhuo(UserVo userVo, String date2, String date1, String date22,
			Integer pageNum) {
		if(pageNum==null || pageNum.equals("")){
			pageNum=0;
		}else{
			pageNum=pageNum*10;
		}
		String sql="SELECT j.ordersId,j.dianpuId,j.createDate,j.fromDianpuId,j.money,j.ordersBH,j.shouyi,o.address,"+
		"o.chengshi,o.chengshiCode,o.dealDate,o.diqu,o.diquCode,o.fahuoDate,o.kuaidiName,o.kuaidiNo,o.mobile,o.order_status,"+
		"o.pdId,o.pictureUrl,o.pname,o.productId,o.refundTime,o.sheng,o.shengCode,o.shouhuoDate,o.shuliang,o.fahuoDate,"+
		"o.tel,o.toUserName,o.tuihuoDate,o.xiaohaoShouyi,o.yunfei,o.type,o.userId,o.useryId FROM order_jinhuo j JOIN orders o "+
		"ON j.ordersId = o.ordersId WHERE j.dianpuId = "+userVo.getUsery().getDianPuId();
		
		
		if((date1!=null&&!date1.equals(""))&&(date2!=null&&!date2.equals(""))){
          sql+=" and createDate between '"+date1+"' and '"+date2+"'";
        } else if((date1==null||date1.equals(""))&&(date2!=null&&!date2.equals(""))){
        	sql+=" and createDate<='"+date2+"'";
        } else if((date1!=null&&!date1.equals(""))&&(date2==null||date2.equals(""))){
        	sql+=" and createDate>='"+date1+"'";
        }
                    
        sql+=" order by createDate desc";
        sql+=" limit "+pageNum+" ,"+10;
        System.out.println(sql);
        List<OrdersVo> orders= findBySql(OrdersVo.class, sql, null);
        return orders;
	}


	@Override
	public Long findOrderNum(Usery usery, String date1, String date2) {
		Integer dianpuId = usery.getDianPuId();
		Long num = 0l;
		if(dianpuId!=null){
			String sql = "select count(1) from orders where order_status in (1,2,3,4,6)";
			sql+=" and dianpuId ="+dianpuId;
			if((date1!=null&&!date1.equals(""))&&(date2!=null&&!date2.equals(""))){
		        sql+=" and createDate between '"+date1+"' and '"+date2+"'";
	        } else if((date1==null||date1.equals(""))&&(date2!=null&&!date2.equals(""))){
	        	sql+=" and createDate<='"+date2+"'";
	        } else if((date1!=null&&!date1.equals(""))&&(date2==null||date2.equals(""))){
	        	sql+=" and createDate>='"+date1+"'";
	        }
			sql+= " UNION ALL select count(1) from order_jinhuo j join orders o on j.ordersId = o.ordersId where o.order_status in (1,2,3,4,6) and j.dianpuId ="
					+dianpuId;
			if((date1!=null&&!date1.equals(""))&&(date2!=null&&!date2.equals(""))){
		          sql+=" and j.createDate between '"+date1+"' and '"+date2+"'";
	        } else if((date1==null||date1.equals(""))&&(date2!=null&&!date2.equals(""))){
	        	sql+=" and j.createDate<='"+date2+"'";
	        } else if((date1!=null&&!date1.equals(""))&&(date2==null||date2.equals(""))){
	        	sql+=" and j.createDate>='"+date1+"'";
	        }
			List list = findBySql(sql, null);
			if(list!=null&&list.size()>0){
				if(list.get(0)!=null){
					num+=Long.valueOf(list.get(0).toString());
				}
				if(list.get(1)!=null){
					num+=Long.valueOf(list.get(1).toString());
				}
			}
		}
		
		return num;
	}

	@Override
	public void deleteByOrders(List<Orders> deleteList) {
		List<OrderJinHuo> deleteOrders =new ArrayList<>();
		if(deleteList.size()>0){
			for(Orders order:deleteList){
				OrderJinHuo jinHuo = findUniqueByProperty("ordersId", order.getOrdersId());
				if(jinHuo!=null){
					deleteOrders.add(jinHuo);
				}
			}
		}
		if(deleteOrders.size()>0){
			deleteAll(deleteOrders);
		}
	}


}

