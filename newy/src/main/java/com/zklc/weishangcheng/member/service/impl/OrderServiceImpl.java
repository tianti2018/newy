package com.zklc.weishangcheng.member.service.impl;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.dao.FhRecordDao;
import com.zklc.weishangcheng.member.hibernate.persistent.FhRecord;
import com.zklc.weishangcheng.member.hibernate.persistent.JifenUser;
import com.zklc.weishangcheng.member.hibernate.persistent.MiaoShaOrder;
import com.zklc.weishangcheng.member.hibernate.persistent.Order;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderLiu;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.OrderVo;
import com.zklc.weishangcheng.member.service.FhrecordService;
import com.zklc.weishangcheng.member.service.JifenUserService;
import com.zklc.weishangcheng.member.service.OrderLiuService;
import com.zklc.weishangcheng.member.service.OrderService;
import com.zklc.weishangcheng.member.service.UseryService;
import com.zklc.weishangcheng.member.service.WeixinAutosendmsgService;
import com.zklc.weixin.util.UserInfoUtil;
@Service
public class OrderServiceImpl extends BaseServiceImp<Order, Integer> implements OrderService {
	
	@Autowired
	private JifenUserService userService;
	@Autowired
	private FhRecordDao fhRecordDao;
	@Autowired
	private UseryService useryService;
	@Autowired
	private FhrecordService fhrecordService;
	@Autowired
	private WeixinAutosendmsgService autosendmsgService;
	@Autowired
	private OrderLiuService orderLiuService;
	
	
	
	
	
	
	@Override
	public Order findByUserId(Integer userId) {
		Order returnOrder=null;
		StringBuffer hql = new StringBuffer("from Order t where 1=1 ");
		if(userId!=null&&!"".equals(userId)){
			hql.append(" and t.userId="+userId);
			hql.append(" order by t.createDate desc");
			List list=super.findByHql(hql.toString(), null);
			if(list!=null&&list.size()>0)
				returnOrder=(Order) list.get(0);
		}else{
			return null;
		}
		return returnOrder;
	}


	@Override
	public Order findByOrderBH(String orderBH) {
		Order returnOrder=null;
		StringBuffer hql = new StringBuffer("from Order t where 1=1 ");
		if(orderBH!=null&&!"".equals(orderBH)){
			hql.append(" and t.ordersBH='"+orderBH+"'");
			List list=super.findByHql(hql.toString(), null);
			if(list!=null&&list.size()>0)
				returnOrder=(Order) list.get(0);
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
	public List findMyOrderList(Integer userId, String type,String date1,String date2,Integer pageNum) {
		if(pageNum==null || pageNum.equals("")){
			pageNum=0;
		}else{
			pageNum=pageNum*5;
		}
		String sql="select * from miaosha_order where userId = "+userId+" and (order_status in("+type+")";
		sql+=" or (order_status=0 and payStatus<>0 ))";
		if((date1!=null&&!date1.equals(""))&&(date2!=null&&!date2.equals(""))){
          sql+=" and createDate between '"+date1+"' and '"+date2+"'";
        } else if((date1==null||date1.equals(""))&&(date2!=null&&!date2.equals(""))){
        	sql+=" and createDate<='"+date2+"'";
        } else if((date1!=null&&!date1.equals(""))&&(date2==null||date2.equals(""))){
        	sql+=" and createDate>='"+date1+"'";
        }
                    
        sql+=" order by createDate desc";
        sql+=" limit "+pageNum+" ,"+5;
        System.out.println(sql);
        List<MiaoShaOrder> orders=super.findBySql(sql, null);
		return orders;
	}

	private String str_to_date(String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean updateOrdersMsg(Integer orderId) { 
		boolean result = false;
		Order order = super.findById(orderId);
		if(null != order && !"".equals(order)){
			order.setOrderStatus(6);
			super.update(order);
			result = true;
		}
		
		return result;
	}

	@Override
	public void timerUpdateOrderStatus() {
//		String sql = " from Order o where o.orderStatus in(0,3,6)";
//		
//		List<Order> list = super.findByHql(sql, null);
//		SimpleDateFormat cdate = new SimpleDateFormat("yyyyMMddhhmm");
//		if(list.size() > 0){
//			
//			Order order = null;
//			long millionSeconds = 0;
//			long time =0;
//			long udate=0;
//			List deleteList = new ArrayList();
//				
//			for(int i=0;i<list.size();i++){
//				try {
//					order = list.get(i);
//				 millionSeconds = cdate.parse(new SimpleDateFormat("yyyyMMddhhmm").format(order.getCreateDate()).toString()).getTime();
//				 time = Calendar.getInstance().getTimeInMillis()-millionSeconds;
//				 udate= time / (1000 * 60 * 60 * 24);
//				
//					
//					if(order.getOrderStatus().equals(3) || order.getOrderStatus().equals(6)){
//						if(udate >= 14){
//							
//							order.setOrderStatus(4);
//							super.update(order);
//						}else if((7<= udate && udate < 14) && !order.getOrderStatus().equals(6)){
//							order.setOrderStatus(6);
//							super.update(order);
//						}
//					}else{
//						if(udate >= 3){
//							deleteList.add(order.getOrdersId());
//						}
//					}
//					
//			}catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			
//			if(deleteList.size()>0){
//				super.deleteAll("ordersId", deleteList);
//			}
//		}
//		updateUserMsg();
	}
	
	public void updateUserMsg(){
		String hql = "from JifenUser u where u.unionid is null";//259992
		List<JifenUser> users = userService.findByHql(hql, null);
//		List<JifenUser> users = userService.findAll();
		
		if(users.size()>0){
			for(JifenUser user:users){
				String wxOpenId = user.getWxOpenid();
				if(wxOpenId != null&&user.getUnionid()==null){
					UserInfoUtil userInfo = autosendmsgService.processUserInfoObject(wxOpenId);
					if(userInfo!=null ){
						boolean update = false;
						if(userInfo.getUnionid()!=null){
							user.setUnionid(userInfo.getUnionid());
							update = true;
							System.out.println(user.getUserId()+"\t"+user.getUnionid());
						}
						if(update){
							userService.update(user);
						}
					}
				}
			}
		}
		System.out.println("over");
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

	
	
	
	
	public int moneyPay(Order order, String openid,JifenUser user) {
		order.setUserId(user.getUserId());
		order.setOrderStatus(1); //已支付
		order.setCreateDate(new Date());
		update(order);
		
	  	//激活该用户
	    //user.setActivitiFlag("1");
		String levelValue = order.getLevelValue();
		String[] lvs = StringUtils.split(levelValue, ",");
		user.setLevel(Integer.valueOf(lvs[lvs.length-1]).intValue());
//		user.setBlock("1");
		userService.update(user);
		Integer referrerId = user.getReferrerId();
		if (null!=referrerId) {
			List<Integer> list2 = new ArrayList<Integer>();
			list2 = findAllParent(user.getUserId(),list2);
			for (int i=0;i<lvs.length;i++) {
				int levelOne = Integer.valueOf(lvs[i]).intValue();
				Double money = 0.0;
				if (levelOne<=list2.size()) {
					Integer puserId = list2.get(levelOne-1);
					double fv1 = 1;
					double feilv = 0.9;
					switch (levelOne) {
						case 1:money=fv1*100.0*feilv;break;
						case 2:money=fv1*200*feilv;break;
						case 3:money=fv1*300*feilv;break;
						
						case 4:money=fv1*400*feilv;break;
						case 5:money=fv1*500*feilv;break;
						case 6:money=fv1*600*feilv;break;
						case 7:money=fv1*700*feilv;break;
						case 8:money=fv1*800*feilv;break;
						case 9:money=fv1*900*feilv;break;
						case 10:money=fv1*1000*feilv;break;
						case 11:money=fv1*1100*feilv;break;
						case 12:money=fv1*1200*feilv;break;
						case 13:money=fv1*1300*feilv;break;
						case 14:money=fv1*1400*feilv;break;
						case 15:money=fv1*1500*feilv;break;
						
					}
					JifenUser pjfUser = userService.findById(puserId);
					Usery usery = useryService.findbyUserId(puserId);
					if (null!=pjfUser.getLevel()&&pjfUser.getLevel()>=levelOne&&usery!=null) {
						switch (money.intValue()) {
							case 90:
								creatFhrecord(puserId,user.getUserId(),levelOne,90.0,order.getOrdersId(),"1");
								break;
							case 180:
								creatFhrecord(puserId,user.getUserId(),levelOne,180.0,order.getOrdersId(),"1");
								break;
							case 270:
								creatFhrecord(puserId,user.getUserId(),levelOne,270.0,order.getOrdersId(),"1");
								break;
							case 4*90:
								creatFhrecord(puserId,user.getUserId(),levelOne,4*90.0,order.getOrdersId(),"1");
								break;
							case 5*90:
								creatFhrecord(puserId,user.getUserId(),levelOne,5*90.0,order.getOrdersId(),"1");
								break;
							case 6*90:
								creatFhrecord(puserId,user.getUserId(),levelOne,6*90.0,order.getOrdersId(),"1");
								break;
							case 7*90:
								creatFhrecord(puserId,user.getUserId(),levelOne,7*90.0,order.getOrdersId(),"1");
								break;
							case 8*90:
								creatFhrecord(puserId,user.getUserId(),levelOne,8*90.0,order.getOrdersId(),"1");
								break;
							case 9*90:
								creatFhrecord(puserId,user.getUserId(),levelOne,9*90.0,order.getOrdersId(),"1");
								break;
							case 10*90:
								creatFhrecord(puserId,user.getUserId(),levelOne,10*90.0,order.getOrdersId(),"1");
								break;
							case 11*90:
								creatFhrecord(puserId,user.getUserId(),levelOne,11*90.0,order.getOrdersId(),"1");
								break;
							case 12*90:
								creatFhrecord(puserId,user.getUserId(),levelOne,12*90.0,order.getOrdersId(),"1");
								break;
							case 13*90:
								creatFhrecord(puserId,user.getUserId(),levelOne,13*90.0,order.getOrdersId(),"1");
								break;
							case 14*90:
								creatFhrecord(puserId,user.getUserId(),levelOne,14*90.0,order.getOrdersId(),"1");
								break;
							case 15*90:
								creatFhrecord(puserId,user.getUserId(),levelOne,15*90.0,order.getOrdersId(),"1");
								break;
						}
					}
					else { //您的上级用户级别为空 或者小于当前用户 红包丢失
						//更新当前用户的状态 激活审核人为公司
						//发送卡密
						switch (money.intValue()) {
							case 90:
								creatFhrecord(puserId,user.getUserId(),levelOne,90.0,order.getOrdersId(),"4");
								if (null!=user) {
									user.setOneFlag(1);
									user.setActiviti1(1);
									userService.update(user);
									
								}
								break;
							case 180:
								creatFhrecord(puserId,user.getUserId(),levelOne,180.0,order.getOrdersId(),"4");
								if (null!=user) {
									user.setTwoFlag1(1);
									user.setActiviti2(1);
									userService.update(user);
									
								}
								
								break;
							case 270:
								creatFhrecord(puserId,user.getUserId(),levelOne,270.0,order.getOrdersId(),"4");
								if (null!=user) {
									user.setThreeFlag1(1);
									user.setThreeFlag2(1);
									user.setActiviti3(1);
									userService.update(user);
									
								}break;
						}
					}
				}else{//缺少上级的补充
					switch (levelOne) {
					case 1:{
						user.setOneFlag(1);
						user.setActiviti1(1);
						userService.update(user);
						break;
					}
					case 2:{
						user.setTwoFlag1(1);
						user.setActiviti2(1);
						userService.update(user);
						break;
					}
					case 3:{
						user.setThreeFlag1(1);
						user.setThreeFlag2(1);
						user.setActiviti3(1);
						userService.update(user);
						break;
					}
				}
			}
			}
		}else{//没有上级

			for (int i=0;i<lvs.length;i++) {
				int levelOne = Integer.valueOf(lvs[i]).intValue();
				switch (levelOne) {
					case 1:{
						user.setOneFlag(1);
						user.setActiviti1(1);
						userService.update(user);
						break;
					}
					case 2:{
						user.setTwoFlag1(1);
						user.setActiviti2(1);
						userService.update(user);
						break;
					}
					case 3:{
						user.setThreeFlag1(1);
						user.setThreeFlag2(1);
						user.setActiviti3(1);
						userService.update(user);
						break;
					}
				}
			}
		
		}
		return 0;
	}
	
	public void creatFhrecord(Integer puserId,Integer formUserId,int level, Double fhmoney,Integer ordersId) {
		if (fhmoney>200) {
			int cishu = (int) Math.floor(fhmoney/200); //提现200的次数
			int yushu = (int)(fhmoney%200);
			for (int i=0;i<cishu;i++) {
				FhRecord fhRecord = new FhRecord();
				fhRecord.setOrdersId(ordersId);
				fhRecord.setUserId(puserId);
				fhRecord.setFromUserId(formUserId);
				fhRecord.setFlag("0");
				fhRecord.setFhType("2");
				fhRecord.setFhmoney(200.0);
				fhRecord.setCreateDate(new Date());
				fhRecord.setMemo("用户【"+formUserId+"】 升【"+level+"】");
				fhrecordService.save(fhRecord);
			}
			if (yushu!=0) {
				FhRecord fhRecord = new FhRecord();
				fhRecord.setOrdersId(ordersId);
				fhRecord.setUserId(puserId);
				fhRecord.setFromUserId(formUserId);
				fhRecord.setFlag("0");
				fhRecord.setFhType("2");
				fhRecord.setFhmoney(yushu*1.0);
				fhRecord.setCreateDate(new Date());
				fhRecord.setMemo("用户【"+formUserId+"】 升【"+level+"】");
				fhrecordService.save(fhRecord);
			}
		}
		else {
			FhRecord fhRecord = new FhRecord();
			fhRecord.setOrdersId(ordersId);
			fhRecord.setUserId(puserId);
			fhRecord.setFromUserId(formUserId);
			fhRecord.setFlag("0");
			fhRecord.setFhType("2");
			fhRecord.setFhmoney(fhmoney);
			fhRecord.setCreateDate(new Date());
			fhRecord.setMemo("用户【"+formUserId+"】 升【"+level+"】");
			fhrecordService.save(fhRecord);
		}
	
	}
	
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
		
		JifenUser jifenUser = userService.findById(userId);
		Integer referrerId = jifenUser.getReferrerId();
		System.out.println(">>>>>>>>> referrerId" + referrerId);
		if (null!=referrerId) {
			list.add(referrerId);
			findAllParent(referrerId,list);
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
	public void saveAndCFh(Order order, JifenUser user) {

		save(order);
		String levelValue = order.getLevelValue();
		String[] lvs = StringUtils.split(levelValue, ",");
		Integer referrerId = user.getReferrerId();
		if (null!=referrerId) {
			List<Integer> list2 = new ArrayList<Integer>();
			list2 = findAllParent(user.getUserId(),list2);
			for (int i=0;i<lvs.length;i++) {
				int levelOne = Integer.valueOf(lvs[i]).intValue();
				Double money = 0.0;
				if (levelOne<=list2.size()) {
					Integer puserId = list2.get(levelOne-1);
					double fv1 = 1;
					double feilv   = 0.9;
					switch (levelOne) {
						case 1:money=fv1*100.0*feilv;break;
						case 2:money=fv1*200*feilv;break;
						case 3:money=fv1*300*feilv;break;
						case 4:money=fv1*400*feilv;break;
						case 5:money=fv1*500*feilv;break;
						case 6:money=fv1*600*feilv;break;
						case 7:money=fv1*700*feilv;break;
						case 8:money=fv1*800*feilv;break;
						case 9:money=fv1*900*feilv;break;
						case 10:money=fv1*1000*feilv;break;
						case 11:money=fv1*1100*feilv;break;
						case 12:money=fv1*1200*feilv;break;
						case 13:money=fv1*1300*feilv;break;
						case 14:money=fv1*1400*feilv;break;
						case 15:money=fv1*1500*feilv;break;
					}
					JifenUser pjfUser = userService.findById(puserId);
					if (null!=pjfUser.getLevel()) {
						switch (money.intValue()) {
							case 90:
								creatFhrecord(puserId,user.getUserId(),levelOne,90.0,order.getOrdersId());
								break;
							case 180:
								creatFhrecord(puserId,user.getUserId(),levelOne,180.0,order.getOrdersId());
								break;
							case 270:
								creatFhrecord(puserId,user.getUserId(),levelOne,270.0,order.getOrdersId());
								break;
							case 360:
								creatFhrecord(puserId,user.getUserId(),levelOne,360.0,order.getOrdersId());
								break;
							case 450:
								creatFhrecord(puserId,user.getUserId(),levelOne,450.0,order.getOrdersId());
								break;
							case 540:
								creatFhrecord(puserId,user.getUserId(),levelOne,540.0,order.getOrdersId());
								break;
							case 630:
								creatFhrecord(puserId,user.getUserId(),levelOne,630.0,order.getOrdersId());
								break;
							case 720:
								creatFhrecord(puserId,user.getUserId(),levelOne,720.0,order.getOrdersId());
								break;
							case 810:
								creatFhrecord(puserId,user.getUserId(),levelOne,810.0,order.getOrdersId());
								break;
							case 900:
								creatFhrecord(puserId,user.getUserId(),levelOne,900.0,order.getOrdersId());
								break;
							case 990:
								creatFhrecord(puserId,user.getUserId(),levelOne,990.0,order.getOrdersId());
								break;
							case 1080:
								creatFhrecord(puserId,user.getUserId(),levelOne,1080.0,order.getOrdersId());
								break;
							case 1170:
								creatFhrecord(puserId,user.getUserId(),levelOne,1170.0,order.getOrdersId());
								break;
							case 1260:
								creatFhrecord(puserId,user.getUserId(),levelOne,1260.0,order.getOrdersId());
								break;
							case 1350:
								creatFhrecord(puserId,user.getUserId(),levelOne,1350.0,order.getOrdersId());
								break;
						}
					}
				}
			}
		}
	
		
	}
	
	
	public void creatFhrecord(Integer puserId,Integer formUserId,int level, Double fhmoney,Integer ordersId,String flag) {
		if (fhmoney>200) {
			int cishu = (int) Math.floor(fhmoney/200); //提现200的次数
			int yushu = (int)(fhmoney%200);
			for (int i=0;i<cishu;i++) {
				FhRecord fhRecord = fhRecordDao.findFhRecordBytoUserId(puserId, formUserId, 200.0, "0",ordersId);
				if (null!=fhRecord) {
					fhRecord.setFlag(flag);
					fhRecord.setUpdaDate(new Date());
					fhrecordService.update(fhRecord);
				}
			}
			if (yushu!=0) {
				FhRecord fhRecord = fhRecordDao.findFhRecordBytoUserId(puserId, formUserId, yushu*1.0, "0",ordersId);
				if (null!=fhRecord) {
					fhRecord.setFlag(flag);
					fhRecord.setUpdaDate(new Date());
					fhrecordService.update(fhRecord);
				}
			}
		}
		else {
			FhRecord fhRecord = fhRecordDao.findFhRecordBytoUserId(puserId, formUserId, fhmoney, "0",ordersId);
			if (null!=fhRecord) {
				fhRecord.setFlag(flag);
				fhRecord.setUpdaDate(new Date());
				fhrecordService.update(fhRecord);
			}
		}
	}


	@Override
	public OrderLiu findOrderLiuOrderBH(String orderBh) {
		OrderLiu ordre = null;
		String hql = "from OrderLiu o where o.ordersBH='"+orderBh+"'";
		List list = orderLiuService.findByHql(hql, null);
		if(list.size() > 0){
			ordre = (OrderLiu) list.get(0);
		}
		return ordre;
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


}

