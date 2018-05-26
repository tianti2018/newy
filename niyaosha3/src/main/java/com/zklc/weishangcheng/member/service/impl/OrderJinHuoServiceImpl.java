package com.zklc.weishangcheng.member.service.impl;
import java.text.DecimalFormat;
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
import com.zklc.weishangcheng.member.hibernate.persistent.vo.OrderVo;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.OrdersVo;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.UserVo;
import com.zklc.weishangcheng.member.service.FhrecordService;
import com.zklc.weishangcheng.member.service.OrderJinHuoService;
import com.zklc.weishangcheng.member.service.OrdersService;
import com.zklc.weishangcheng.member.service.WeixinAutosendmsgService;
@Service
public class OrderJinHuoServiceImpl extends BaseServiceImp<OrderJinHuo, Integer> implements OrderJinHuoService {
	
	@Autowired
	private FhrecordService fhrecordService;
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private WeixinAutosendmsgService autosendmsgService;
	
	@Override
	public OrderJinHuo findbyOrdersId(Integer ordersId) {
		return findUniqueByProperty("ordersId", ordersId);
	}
	
	@Override
	public OrderJinHuo findByUserId(Integer userId) {
		OrderJinHuo returnOrder=null;
		StringBuffer hql = new StringBuffer("from Order t where 1=1 ");
		if(userId!=null&&!"".equals(userId)){
			hql.append(" and t.userId="+userId);
			hql.append(" order by t.createDate desc");
			List list=super.findByHql(hql.toString(), null);
			if(list!=null&&list.size()>0)
				returnOrder=(OrderJinHuo) list.get(0);
		}else{
			return null;
		}
		return returnOrder;
	}


	@Override
	public OrderJinHuo findByOrderBH(String orderBH) {
		OrderJinHuo returnOrder=null;
		StringBuffer hql = new StringBuffer("from Order t where 1=1 ");
		if(orderBH!=null&&!"".equals(orderBH)){
			hql.append(" and t.ordersBH='"+orderBH+"'");
			List list=super.findByHql(hql.toString(), null);
			if(list!=null&&list.size()>0)
				returnOrder=(OrderJinHuo) list.get(0);
		}else{
			return null;
		}
		return returnOrder;
	}


	@Override
	public OrderVo findByOrderVoBH(String orderBH) {
		OrderVo returnOrder=null;
		StringBuffer hql = new StringBuffer("select * from ordery t where 1=1 ");
		if(orderBH!=null&&!"".equals(orderBH)){
			hql.append(" and t.ordersBH='"+orderBH+"'");
			System.out.println("查询订单sql是:"+hql);
			List list=super.findBySql(OrderVo.class,hql.toString(), null);
			if(list!=null&&list.size()>0)
				returnOrder=(OrderVo) list.get(0);
		}else{
			return null;
		}
		return returnOrder;
	}

	@Override
	public List findPerOrder(Integer userId, String orderNo) {
		String sql = "SELECT o.ordersId,o.toUserName,o.mobile,o.address,date_format(o.createDate,'%Y-%m-%d %H:%i:%s') createDate,if(o.order_status=1,'下单已购买','下单未购买'),o.pname FROM ordery o where o.userId = "+userId+" and o.order_status = "+orderNo;
		List list=super.findBySql(sql, null);
		return list;
	}

	

	@Override
	public List findLevelPeoOrder(Integer userId, String orderType,
			String orderLevel) {
		List list = null;
		StringBuffer sql = new StringBuffer();
		if(orderLevel.equals("all")){
			sql.append("select ordersId,userId,toUserName,mobile,address,order_status, date_format(createDate,'%Y-%m-%d %H:%i:%s') datatime,pname,(select u.wxOpenid from users u where u.userId = o.userId) wxopenid,o.money,o.kuaidiName,o.kuaidiNo from ordery o where o.userId in (");
			sql.append("select t2.userId from users t1, users t2 where t1.userId=t2.referrerId and (t1.userId= "+userId+" )  and t2.wxOpenid is NOT NULL) and o.order_status in("+orderType+")");
		    sql.append("  UNION ALL  ");
		    sql.append("select ordersId,userId,toUserName,mobile,address,order_status, date_format(createDate,'%Y-%m-%d %H:%i:%s') datatime,pname,(select u.wxOpenid from users u where u.userId = o1.userId) wxopenid,o1.money,o1.kuaidiName,o1.kuaidiNo from ordery o1 where o1.userId in (");
		    sql.append("select t2.userId from users t1, users t2 where t1.userId=t2.referrerId and (t1.referrerId= "+userId+" )  and t2.wxOpenid is NOT NULL) and o1.order_status in("+orderType+")");
		    sql.append("  UNION ALL  ");
		    sql.append("select ordersId,userId,toUserName,mobile,address,order_status, date_format(createDate,'%Y-%m-%d %H:%i:%s') datatime,pname,(select u.wxOpenid from users u where u.userId = o2.userId) wxopenid,o2.money,o2.kuaidiName,o2.kuaidiNo from ordery o2 where o2.userId in (");
		    sql.append("select t3.userId from users t3 where t3.referrerId in(select t2.userId from users t1, users t2 where t1.userId=t2.referrerId ");
		    sql.append("and (t1.referrerId= "+userId+" )) and t3.wxOpenid is NOT NULL ) and o2.order_status in("+orderType+")");
		    
		    list=super.findBySql(sql.toString(), null);
		    
		}else if(orderLevel.equals("1")){
			sql.append("select ordersId,userId,toUserName,mobile,address,order_status, date_format(createDate,'%Y-%m-%d %H:%i:%s') datatime,pname,(select u.wxOpenid from users u where u.userId = o.userId) wxopenid,o.money,o.kuaidiName,o.kuaidiNo from ordery o where o.userId in (");
			sql.append("select t2.userId from users t1, users t2 where t1.userId=t2.referrerId and (t1.userId= "+userId+" )  and t2.wxOpenid is NOT NULL) and o.order_status in("+orderType+")");
			
			list=super.findBySql(sql.toString(), null);
			
		}else if(orderLevel.equals("2")){
			sql.append("select ordersId,userId,toUserName,mobile,address,order_status, date_format(createDate,'%Y-%m-%d %H:%i:%s') datatime,pname,(select u.wxOpenid from users u where u.userId = o1.userId) wxopenid,o1.money,o1.kuaidiName,o1.kuaidiNo from ordery o1 where o1.userId in (");
			sql.append("select t2.userId from users t1, users t2 where t1.userId=t2.referrerId and (t1.referrerId= "+userId+" )  and t2.wxOpenid is NOT NULL) and o1.order_status in("+orderType+")");
			
			list=super.findBySql(sql.toString(), null);
			
		}else if(orderLevel.equals("3")){
			sql.append("select ordersId,userId,toUserName,mobile,address,order_status, date_format(createDate,'%Y-%m-%d %H:%i:%s') datatime,pname,(select u.wxOpenid from users u where u.userId = o2.userId) wxopenid,o2.money,o2.kuaidiName,o2.kuaidiNo from ordery o2 where o2.userId in (");
		    sql.append("select t3.userId from users t3 where t3.referrerId in(select t2.userId from users t1, users t2 where t1.userId=t2.referrerId ");
		    sql.append("and (t1.referrerId= "+userId+" )) and t3.wxOpenid is NOT NULL ) and o2.order_status in("+orderType+")");
		    
		    list=super.findBySql(sql.toString(), null);
		}
		return list;
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
	public boolean updateOrdersMsg(Integer orderId) { 
		boolean result = false;
		OrderJinHuo order = super.findById(orderId);
		if(null != order && !"".equals(order)){
//			order.setOrderStatus(6);
			super.update(order);
			result = true;
		}
		
		return result;
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

	@Override
	public List findCurrUserAllOrder(Integer userId) {
		List list = null;
		    return list;
	}

	@Override
	public List findLevelUserOrderMoney(Integer userId, String orderType,
			String orderLevel) {
		List list = null;
		
		return  list;
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
	public Integer findOrderCountByUserId(Integer userId) {
		Number returnNum=0;
		StringBuffer hql = new StringBuffer("select count(ordersId) from Order t where 1=1 ");
		if(userId!=null){
				hql.append(" and t.orderStatus=1");
				hql.append(" and t.userId="+userId+"");
				List lit =super.findByHql(hql.toString(), null);
				if (null!=lit) {
					returnNum = (null==lit.get(0))?0:(Number)lit.get(0);
				}
		}
		return returnNum.intValue();
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


	/**
	 * 查询用户消费的总金额
	 */
	@Override
	public Integer findTotalMoneyByUserId(Integer userId) {
		double money11=0.0;
		double money12=0.0;
		double money13=0.0;
		double money14=0.0;
		String sql1="select sum(money) from miaosha_order where order_status in(1,2,4)";
		String sql2="select sum(money) from orders where order_status in(1,2,4)";
		String sql3="select sum(money) from ordersyliu where order_status in(1,2,4)";
		String sql4="select sum(money) from ordery where order_status in(1,2,4)";
		if(userId!=null){
			sql1+=" and userId="+userId+"";
			sql2+=" and userId="+userId+"";
			sql3+=" and userId="+userId+"";
			sql4+=" and userId="+userId+"";
		}
		System.out.println(sql1);
		System.out.println(sql2);
		System.out.println(sql3);
		System.out.println(sql4);
		List<Double> money1=(List<Double>) super.findBySql(sql1, null);
		List<Double> money2=(List<Double>) super.findBySql(sql2, null);
		List<Double> money3=(List<Double>) super.findBySql(sql3, null);
		List<Double> money4=(List<Double>) super.findBySql(sql4, null);
		if (null!=money1) {
			 money11=(null==money1.get(0)?0:money1.get(0));
		}
		if (null!=money2) {
			 money12=(null==money2.get(0)?0:money2.get(0));
		}
		if (null!=money3) {
			 money13=(null==money3.get(0)?0:money3.get(0));
		}
		if (null!=money4) {
			 money14=(null==money4.get(0)?0:money4.get(0));
		}
		DecimalFormat df=new DecimalFormat("0");
		Integer totalMoney=Integer.parseInt(df.format(money11+money12+money13+money14));
		System.out.println(totalMoney);
		return totalMoney;
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

