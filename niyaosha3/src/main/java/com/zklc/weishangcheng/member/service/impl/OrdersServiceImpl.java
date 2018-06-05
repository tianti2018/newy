package com.zklc.weishangcheng.member.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utils.CalendarUtils;
import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderJinHuo;
import com.zklc.weishangcheng.member.hibernate.persistent.Orders;
import com.zklc.weishangcheng.member.hibernate.persistent.Products;
import com.zklc.weishangcheng.member.hibernate.persistent.ShouYiForUser;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.service.DianpuForUserService;
import com.zklc.weishangcheng.member.service.OrderJinHuoService;
import com.zklc.weishangcheng.member.service.OrdersService;
import com.zklc.weishangcheng.member.service.ProductsService;
import com.zklc.weishangcheng.member.service.RefundRecordService;
import com.zklc.weishangcheng.member.service.ShouYiForUserService;
import com.zklc.weishangcheng.member.service.UserService;
import com.zklc.weishangcheng.member.service.UseryService;
import com.zklc.weishangcheng.member.service.WeixinAutosendmsgService;
/**
 * @author yang
 *
 */
/**
 * @author yang
 *
 */
@SuppressWarnings("rawtypes")
@Service
public class OrdersServiceImpl extends BaseServiceImp<Orders, Integer>
		implements OrdersService {
	@Autowired
	private UserService userService;
	@Autowired
	private ShouYiForUserService shouyiService;
	@Autowired
	private OrderJinHuoService jinhuoService;
	@Autowired
	private UseryService useryService;
	@Autowired
	private WeixinAutosendmsgService autosendmsgService;
	@Autowired
	private RefundRecordService refundRecordService;
	
	@Autowired
	private DianpuForUserService dianpuService;
	@Autowired
	private OrderJinHuoService orderJinHuoService;
	
	@Autowired
	private ProductsService productsService;
	
	@Override
	public void timerUpdateOrderStatus3and6() {
		List<Orders> updateList = new ArrayList<>();
		Date now = new Date();
		String hql = " from Orders o where o.orderStatus = 3";
		List<Orders> yifahuoList = findByHql(hql, null);
		if(yifahuoList!=null&&yifahuoList.size()>0){
			for(Orders order:yifahuoList){
				if(order.getFahuoDate()!=null){
					if(now.getTime()-order.getFahuoDate().getTime()>1000*60*60*24*7){
						order.setOrderStatus(6);
						updateList.add(order);
					}
				}
				
			}
		}
		hql = " from Orders o where o.orderStatus = 6";
		List<Orders> yishouhuoList = findByHql(hql, null);
		if(yishouhuoList!=null&&yishouhuoList.size()>0){
			for(Orders order:yishouhuoList){
				if(order.getShouhuoDate()==null){
					order.setOrderStatus(4);
					updateList.add(order);
				}else if(now.getTime()-order.getShouhuoDate().getTime()>1000*3600*24*7){
					order.setOrderStatus(4);
					updateList.add(order);
				}
			}
		}
		if(updateList.size()>0){
			saveOrUpdateAll(updateList);
		}
	}
	
	@Override
	public void timerUpdateOrderStatus0and3() {
		String hql = " from Orders o where o.orderStatus = 0";
		List<Orders> updateList = new ArrayList<>();
		List<Orders> deleteList = new ArrayList<>();
		List<Orders> weizhifulist = findByHql(hql, null);
		Date now = new Date();
		if(weizhifulist!=null&&weizhifulist.size() > 0){
			for(Orders order:weizhifulist){
				if(now.getTime()-order.getCreateDate().getTime()>1000*60*60){
					deleteList.add(order);
				}
			}
		}
		hql = " from Orders o where o.orderStatus = 3";
		List<Orders> yifahuoList = findByHql(hql, null);
		if(deleteList.size()>0){
			deleteAll(deleteList);
		}
		
		//最后还需要更新收益列表里面的状态
		//这里还没有确定是哪一个状态才修改,如果定了就去添加list更新状态吧
		shouyiService.updateShouyiStatusByOrders(yifahuoList);
		shouyiService.deleteShouyiByOrders(deleteList);
//		updateUserMsg();
	}
	
	/**
	 * 一个用户一天内只能使用星火券支付一次
	 */
	@Override
	public Integer checkXingHuoQuanOrder(Users user)
	{
		String startDate =CalendarUtils.getFormatDate(CalendarUtils.Y_M_D_LONG, new Date());
		String endDate=CalendarUtils.getFormatDate(CalendarUtils.Y_M_DHMS_LONG, new Date());
		String sql = "SELECT count(1) FROM miaosha_order  WHERE  xinghuoquan>0 and createDate>='"+startDate+"' and createDate<='"+endDate+"' and userId = "+user.getUserId()+" AND payStatus not in(0)";
		List list = findBySql(sql, null);
		if(list!=null && list.size()>0){
			return Integer.parseInt(list.get(0).toString());
		}
		return 0;
	}
	
	@Override
	public Orders findOrderByOrderBH(String orderNo) {
		
		List<Orders> orders = findByProperty("ordersBH", orderNo);
		if(orders.size()>0){
			return orders.get(0);
		}
		return null;
	
	}

	@Override
	public Integer cancelOrder(Orders order) {/*
		Integer cancelResult = 0;
		// 1.更新订单状态和取消时间
		// 2.还需要操作退款
		// 3.还需要作废上级用户的佣金
		if (order.getOrder_status() != null && order.getOrder_status()==0) {
			Usery user = useryService.findbyUserId(order.getUserId());
			order.setCancelTime(new Date());
			if (order.getOrder_status() != null && order.getOrder_status() == 1) {
				// 微信退款
				String xmlResult = WXRefund.refund(user.getWxOpenid(), order.getOrdersBH(), String.valueOf(order.getMoney()*97));
				Map refundMap=null;
				try {
					refundMap = WXRefund.doXMLParse(xmlResult);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RefundRecord refundRecord = new RefundRecord();
				refundRecord.setCreateDate(new Date());
				refundRecord.setOrderBH(order.getOrdersBH());
				refundRecord.setOrderId(order.getOrdersId());
				refundRecord.setRefundAmount(order.getMoney()*0.97);
				refundRecord.setUserId(order.getUserId());
				refundRecord.setReturnXML(xmlResult);
				refundRecord.setWxOpenId(user.getWxOpenid());
				
				
				if (refundMap.get("return_code").equals("SUCCESS")&&refundMap.get("result_code").equals("SUCCESS")) {
					refundRecord.setPartnerTradeNo(refundMap.get("payment_no")==null?"":refundMap.get("payment_no").toString());
					order.setRefundTime(new Date());
					refundRecord.setResult(1);
					// 更新订单的取消状态
					cancelResult = 1;
					// 如果订单已支付 则添加退款明细记录
					Product product = productService.findById(order.getProductId());
					if (product.getStock() != null && product.getStock() != 999) {
						product.setStock(product.getStock() );
						productService.update(product);
					}	
				} else {
					refundRecord.setResult(0);
				}
				refundRecordService.save(refundRecord);
			}
		   this.update(order);
		}
		return cancelResult;
	*/
		return null;
		}
	@Override
	public void moneyPay(Orders order) {
		Usery usery = null;
		Users user = null;
		if(order.getUserId()!=null){
			user = userService.findById(order.getUserId());
		}
		if(order.getUseryId()!=null){
			usery = useryService.findById(order.getUseryId());
		}
		Usery dianzhu =null;
		Usery jinhuoDianzhu = null;
		OrderJinHuo orderJinHuo = null;
		String mess = "【";
		if(order.getDianpuId()!=null){
			dianzhu = useryService.findByDianpuId(order.getDianpuId());
			orderJinHuo = orderJinHuoService.findbyOrdersId(order.getOrdersId());
			if(orderJinHuo!=null){
				jinhuoDianzhu = useryService.findByDianpuId(orderJinHuo.getDianpuId());
			}
		}
		
		if(usery!=null){
			mess += usery.getUserName();
		}else {
			mess +=user.getUserName();
		}
		mess+="】在您的店铺里购买了【"+order.getPname()+"】"+order.getShuliang();
		if(dianzhu !=null){
			String message =mess+ ",订单编号:"+order.getOrdersBH()+",请尽快处理!";
			autosendmsgService.sendMsg(dianzhu.getWxOpenid(), message);
		}
		if(jinhuoDianzhu!=null){
			String message =mess+ ",订单编号:"+orderJinHuo.getOrdersBH()+",请尽快处理!";
			autosendmsgService.sendMsg(jinhuoDianzhu.getWxOpenid(), message);
		}
		//更新订单的支付信息
		order.setOrderStatus(1);
		order.setPayTime(new Date());
		update(order);
		ShouYiForUser dianzhuShouyi = shouyiService.findbyOrdersId(order.getOrdersId());
		if(dianzhuShouyi!=null){
			dianzhuShouyi.setStatus(1);
			shouyiService.update(dianzhuShouyi);
		}
		if(orderJinHuo!=null){
			ShouYiForUser jinhuoDianzhuShouyi = shouyiService.findbyOrdersId(orderJinHuo.getId());
			if(jinhuoDianzhuShouyi!=null){
				jinhuoDianzhuShouyi.setStatus(1);
				shouyiService.update(jinhuoDianzhuShouyi);
			}
		}
		Products product = productsService.findById(order.getProductId());
		if(product.getStock()!=null&&product.getStock()!=999){
			product.setStock(product.getStock()-order.getShuliang());
			productsService.update(product);
		}
		useryService.jianceUserLevel(usery, user);
	}

	
	@Override
	public Integer findNumByTypeAndUserId(Integer type, Integer userId) {
		String sql = "select count(1) from miaosha_order where type = "+type+" and userId="+userId+" and order_status >0";
		List result = findBySql(sql, null);
		if(result.size()>0){
			return Integer.parseInt(result.get(0).toString());
		}
		return 0;
	}

	@Override
	public Integer findSellerNumByQi(Integer qi) {

		
		Number returnNum=0;
		StringBuffer hql = new StringBuffer("select count(ordersId) from MiaoShaOrder t where 1=1 ");
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


	
	
//	public Users findParent(Users user) {
//		Users parent = null;
//		if(user!=null&&user.getReferrerId()!=null){
//			parent = userService.findById(user.getReferrerId());
//			Usery usery = useryService.findbyUserId(user.getReferrerId());
//			if(usery!=null){
//				return parent;
//			}
//		}
//		return null;
//	}

	@Override
	public void huiyuanPay(Orders order, Users user) {
		user = userService.findById(order.getUserId());
//		if(user.getLevel()!=null&&user.getLevel()!=3){
//			try {
//				List<GoodYongJin> yongJins = yongJinService.findByProperty("orderId", order.getOrdersId());
//				if(yongJins.size()>0){
//					for(GoodYongJin yongJin:yongJins){
//						if(yongJin.getStatus().equals(0)){
//							yongJin.setStatus(1);
//							yongJinService.update(yongJin);
//							Usery usery = useryService.findbyUserId(yongJin.getToUserId());
//							if(usery!=null){
//								String meString = yongJin.getFromUserId()+"购买产品,奖励您"+yongJin.getMoney()+"红包";
//								autosendmsgService.sendMsg(usery.getWxOpenid(), meString);
//							}
//						}
//					}
//				}
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//			try {
//				List<XingHuoQuanRecord> records = xingHuoQuanRecordService.findByProperty("orderId", order.getOrdersId());
//				if(records.size()>0){
//					for(XingHuoQuanRecord record:records){
//						if(record.getStatus().equals(0)){
//							record.setStatus(1);
//							xingHuoQuanRecordService.update(record);
//							Users parent = userService.findById(record.getUserId());
//							userService.update(parent);
//							Usery usery = useryService.findbyUserId(record.getUserId());
//							if(usery!=null){
//								String meString = record.getFromUserId()+"购买产品,奖励您"+record.getXinghuoquan()+"星火券";
//								autosendmsgService.sendMsg(usery.getWxOpenid(), meString);
//							}
//						}
//					}
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		order.setOrderStatus(1);
		update(order);
		Integer allMoney = 0;
		Integer orderMoney= order.getMoney().intValue();
//		if(user.getLevel()!=null){
//			if(user.getLevel()==3){
//				if(user.getSubmitMoney()==null||user.getSubmitMoney()<660){
//					allMoney=660;
//				}else{
//					allMoney = user.getSubmitMoney();
//				}
//			}else if(user.getLevel()==2){
//				if(user.getSubmitMoney()==null||user.getSubmitMoney()<330){
//					allMoney=330;
//				}else{
//					allMoney = user.getSubmitMoney();
//				}
//			}else if(user.getLevel()==1){
//				if(user.getSubmitMoney()==null||user.getSubmitMoney()<110){
//					allMoney=110;
//				}else{
//					allMoney = user.getSubmitMoney();
//				}
//			}
//		}
//		if((allMoney+orderMoney)>=660){
//			if(user.getLevel()==null||user.getLevel()<3)
//				user.setLevel(3);
//		}else if((allMoney+orderMoney)>=330){
//			if(user.getLevel()==null||user.getLevel()<2)
//				user.setLevel(2);
//		}else if((allMoney+orderMoney)>=110){
//			if(user.getLevel()==null||user.getLevel()<1)
//				user.setLevel(1);
//		}
		userService.update(user);
	}

	@Override
	public void createOrder(Orders order, OrderJinHuo orderJinHuo, ShouYiForUser dianzhuShouyiRecord,
			ShouYiForUser shangjiShouyiRecord,ShouYiForUser xiaohaoRecord) {
		if(order!=null){
			save(order);
			if(orderJinHuo!=null){
				orderJinHuo.setOrdersId(order.getOrdersId());
				jinhuoService.save(orderJinHuo);
				if(shangjiShouyiRecord!=null){
					shangjiShouyiRecord.setOrdersId(orderJinHuo.getId());
					shouyiService.save(shangjiShouyiRecord);
				}
			}
			if(dianzhuShouyiRecord!=null){
				dianzhuShouyiRecord.setOrdersId(order.getOrdersId());
				shouyiService.save(dianzhuShouyiRecord);
			}
			if(xiaohaoRecord!=null){
				xiaohaoRecord.setOrdersId(order.getOrdersId());
				shouyiService.save(xiaohaoRecord);
			}
		}
		
	}

	@Override
	public void saveAndCFh(Orders order, Users user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean jifenPay(Orders order, Users user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateOrdeToSend() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer countUserIdCardProduct(Integer userId, Integer prodId, String idCard) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
