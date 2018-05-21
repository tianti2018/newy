package com.zklc.weishangcheng.member.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utils.CalendarUtils;
import com.utils.MyUtils;
import com.utils.WXRefund;
import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.hibernate.persistent.GoodYongJin;
import com.zklc.weishangcheng.member.hibernate.persistent.JiFenRecord;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.hibernate.persistent.LiuCode;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderJinHuo;
import com.zklc.weishangcheng.member.hibernate.persistent.Orders;
import com.zklc.weishangcheng.member.hibernate.persistent.Product;
import com.zklc.weishangcheng.member.hibernate.persistent.RefundRecord;
import com.zklc.weishangcheng.member.hibernate.persistent.ShouYiForUser;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.hibernate.persistent.XingHuoQuanRecord;
import com.zklc.weishangcheng.member.service.GoodYongJinService;
import com.zklc.weishangcheng.member.service.JiFenRecordService;
import com.zklc.weishangcheng.member.service.UserService;
import com.zklc.weishangcheng.member.service.LiuCodeService;
import com.zklc.weishangcheng.member.service.OrderJinHuoService;
import com.zklc.weishangcheng.member.service.OrdersService;
import com.zklc.weishangcheng.member.service.ProductService;
import com.zklc.weishangcheng.member.service.RefundRecordService;
import com.zklc.weishangcheng.member.service.ShouYiForUserService;
import com.zklc.weishangcheng.member.service.UseryService;
import com.zklc.weishangcheng.member.service.WeixinAutosendmsgService;
import com.zklc.weishangcheng.member.service.XingHuoQuanRecordService;
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
	private GoodYongJinService yongJinService;
	@Autowired
	private WeixinAutosendmsgService autosendmsgService;
	@Autowired
	private ProductService  productService;
	@Autowired
	private JiFenRecordService jiFenRecordService;
	@Autowired
	private XingHuoQuanRecordService xingHuoQuanRecordService;
	@Autowired
	private RefundRecordService refundRecordService;
	
	@Autowired
	private LiuCodeService liuCodeService;
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
	
	/**
	 * 更新已付款订单的状态为可以发货
	 */
	@Override
	public void updateOrdeToSend() {
		List<Orders> list = super.findByHql("from MiaoShaOrder t  where t.orderStatus=0 and t.payStatus=1", null);
		List<Orders> updateList = new ArrayList<Orders>();
		if (MyUtils.isNotEmpty(list) && list.size() > 0) {
			for (Orders order : list) {
				if (CalendarUtils.compareDate(CalendarUtils.mergeHour(order.getPayTime(), 6), new Date()).equals("<")) {
					 order.setOrderStatus(1);
					  update(order);
						//更新佣金的状态
						List<GoodYongJin> yongJins = yongJinService.findByProperty("orderId", order.getOrdersId());
						if(yongJins.size()>0){
							for(GoodYongJin yongJin:yongJins){
								if(yongJin.getStatus().equals(0)){
									yongJin.setStatus(1);
									yongJinService.update(yongJin);
								}
							}
						}
						Double zhifuMoney = order.getMoney();
						Users user=userService.findById(order.getUserId());
						Users refferUser = null;
						Usery parentUsery = null;
//						if(user.getReferrerId()!=null){
//							refferUser = userService.findById(user.getReferrerId());
//							parentUsery = useryService.findbyUserId(user.getReferrerId());
//						}
						String mess = "";
						if(refferUser !=null){
							mess = "您好:\n您的超级粉丝:";
							mess += "【"+user.getUserId()+" ： "+user.getUserName()+"】已成功下单！\n"+"支付金额："+zhifuMoney+"元,请留意佣金变化!";
							if(parentUsery !=null&&parentUsery.getWxOpenid()!=null){
								
								autosendmsgService.sendMsg(parentUsery.getWxOpenid(),mess);
							}
//							if(refferUser.getReferrerId()!=null){
//								Users r2 = userService.findById(refferUser.getReferrerId());
//								if(r2!=null){
//									Usery usery2 = useryService.findbyUserId(r2.getUserId());
//									if(usery2!=null&&usery2.getWxOpenid()!=null){
//										mess = "您好:\n您的铁杆粉丝:";
//										mess += "【"+user.getUserId()+" ： "+user.getUserName()+"】已成功下单！\n"+"支付金额："+zhifuMoney+"元,请留意佣金变化!";
//										autosendmsgService.sendMsg(usery2.getWxOpenid(),mess);
//									}
//									if(r2.getReferrerId()!=null){
//										Usery u3 = useryService.findbyUserId(r2.getReferrerId());
//										if(u3!=null&&u3.getWxOpenid()!=null){
//											mess = "您好:\n您的忠实粉丝:";
//											mess += "【"+user.getUserId()+" ： "+user.getUserName()+"】已成功下单！\n"+"支付金额："+zhifuMoney+"元,请留意佣金变化!";
//											autosendmsgService.sendMsg(u3.getWxOpenid(),mess);
//										}
//									}
//								}
//							}
						
					}
				}
			}
		}
		
		
		
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
	public void moneyPay(Orders order, Users user) {
		user = userService.findById(order.getUserId());
		//支付成功后更新用户的星火券数量
		if(order.getShouyi()>0)
		{
			
			userService.update(user);
			//添加星火券的消费记录
			XingHuoQuanRecord xhqRecord=new XingHuoQuanRecord();
			xhqRecord.setCreateDate(new Date());
			xhqRecord.setFromUserId(user.getUserId());
			xhqRecord.setMemo("订单"+order.getOrdersBH()+"兑换消费");
			xhqRecord.setOrderId(order.getOrdersId());
			xhqRecord.setOrdersBH(order.getOrdersBH());
			xhqRecord.setStatus(2);//表示支出
			xhqRecord.setType(4);//4:购买兑换
			xhqRecord.setUserId(user.getUserId());
//			xhqRecord.setXinghuoquan(order.getXinghuoquan());
//			xingHuoQuanRecordService.save(xhqRecord);
//			order.setOrderStatus(0);
		}
		else
		{
			//如果没有用星火券支付 则 直接给佣金
			order.setOrderStatus(1);
			//更新佣金的状态
			List<GoodYongJin> yongJins = yongJinService.findByProperty("orderId", order.getOrdersId());
			if(yongJins.size()>0){
				for(GoodYongJin yongJin:yongJins){
					if(yongJin.getStatus().equals(0)){
						yongJin.setStatus(1);
						yongJinService.update(yongJin);
					}
				}
			}
			Double zhifuMoney = order.getMoney();
			Users refferUser = null;
			Usery parentUsery = null;
//			if(user.getReferrerId()!=null){
//				refferUser = userService.findById(user.getReferrerId());
//				parentUsery = useryService.findbyUserId(user.getReferrerId());
//			}
			String mess = "";
			if(refferUser !=null){
				mess = "您好:\n您的超级粉丝:";
				mess += "【"+user.getUserId()+" ： "+user.getUserName()+"】已成功下单！\n"+"支付金额："+zhifuMoney+"元,请留意佣金变化!";
				if(parentUsery !=null&&parentUsery.getWxOpenid()!=null){
					
					autosendmsgService.sendMsg(parentUsery.getWxOpenid(),mess);
				}
//				if(refferUser.getReferrerId()!=null){
//					Users r2 = userService.findById(refferUser.getReferrerId());
//					if(r2!=null){
//						Usery usery2 = useryService.findbyUserId(r2.getUserId());
//						if(usery2!=null&&usery2.getWxOpenid()!=null){
//							mess = "您好:\n您的铁杆粉丝:";
//							mess += "【"+user.getUserId()+" ： "+user.getUserName()+"】已成功下单！\n"+"支付金额："+zhifuMoney+"元,请留意佣金变化!";
//							autosendmsgService.sendMsg(usery2.getWxOpenid(),mess);
//						}
//						if(r2.getReferrerId()!=null){
//							Usery u3 = useryService.findbyUserId(r2.getReferrerId());
//							if(u3!=null&&u3.getWxOpenid()!=null){
//								mess = "您好:\n您的忠实粉丝:";
//								mess += "【"+user.getUserId()+" ： "+user.getUserName()+"】已成功下单！\n"+"支付金额："+zhifuMoney+"元,请留意佣金变化!";
//								autosendmsgService.sendMsg(u3.getWxOpenid(),mess);
//							}
//						}
//					}
//				}
			}
		}
		//更新订单的支付信息
//		order.setPayStatus(1);
		order.setPayTime(new Date());
		update(order);
		
		Product product = productService.findById(order.getProductId());
		if(product.getStock()!=null&&product.getStock()!=999){
			product.setStock(product.getStock());
			productService.update(product);
		}
	}
	/*佣金的计算方法
	 * 
	 * 
	 * */
	@Override
	public Double getRebateAmt(Product buyProd,Integer size,Integer level)
	{
		Double rebate=0D;
		//比例
		if(buyProd.getRebateType().equals(1))
		{
			if(level.equals(1))
			{
			rebate=buyProd.getRebateBaseAmt()*buyProd.getRebateAmtFirst()*size;
			}
			else if(level.equals(2))
			{
				rebate=buyProd.getRebateBaseAmt()*buyProd.getRebateAmtSecond()*size;
			}
			else if(level.equals(3))
			{
				rebate=buyProd.getRebateBaseAmt()*buyProd.getRebateAmtThird()*size;
			}
		}
		else if(buyProd.getRebateType().equals(2))
		{
			//金额
			if(level.equals(1))
			{
			rebate=buyProd.getRebateAmtFirst()*size;
			}
			else if(level.equals(2))
			{
				rebate=buyProd.getRebateAmtSecond()*size;
			}
			else if(level.equals(3))
			{
				rebate=buyProd.getRebateAmtThird()*size;
			}
		}
		return rebate;
	}
	//面膜订单的保存方法
	@Override
	public void saveMianMoOrder(Orders order, Users user,Product buyProd)
	{
//		if((order.getXinghuoquan()*100/2)>=(order.getMoney()*100)){
//			order.setPayStatus(1);
//			order.setPayTime(new Date());
//			user = userService.findById(order.getUserId());
//			userService.update(user);
//			//添加星火券的消费记录
//			XingHuoQuanRecord xhqRecord=new XingHuoQuanRecord();
//			xhqRecord.setCreateDate(new Date());
//			xhqRecord.setFromUserId(user.getUserId());
//			xhqRecord.setMemo("订单"+order.getOrdersBH()+"兑换消费");
//			xhqRecord.setOrderId(order.getOrdersId());
//			xhqRecord.setOrdersBH(order.getOrdersBH());
//			xhqRecord.setStatus(2);//表示支出
//			xhqRecord.setType(4);//4:购买兑换
//			xhqRecord.setUserId(user.getUserId());
//			xhqRecord.setXinghuoquan(order.getXinghuoquan());
//			xingHuoQuanRecordService.save(xhqRecord);
//			String content="您好:\n您的订单已支付成功！\n"+"订单编号："+order.getOrdersBH()+"\n"+"支付金额："+order.getMoney()+"元,购买产品!";
//			Usery usery = useryService.findbyUserId(user.getUserId());
//			if(usery!=null)
//				autosendmsgService.sendMsg(usery.getWxOpenid(), content);
//		}
		//该方法应该 是一个 事物
		save(order);
//		if (user.getReferrerId() != null) {
//			Users ref1 = userService.findById(user.getReferrerId());
//			if (ref1 != null) {
//				GoodYongJin yongJin = new GoodYongJin();
//				yongJin.setCreateDate(new Date());
//				yongJin.setFromUserId(user.getUserId());
//				yongJin.setToUserId(user.getReferrerId());
//				yongJin.setLevel(1);
//				yongJin.setMoney(getRebateAmt(buyProd, order.getSize(), 1));
//				yongJin.setOrderId(order.getOrdersId());
//				if (ref1.getLevel() >= 1) {
//					Integer num = findNumByTypeAndUserId(buyProd.getManufacturer(), ref1.getUserId());
//					if (num > 0||(buyProd.getKaiguan()!=null&&buyProd.getKaiguan()==1)) {
//						yongJin.setStatus(0);
//						yongJin.setMemo(user.getUserId() + "购买,生成" + ref1.getUserId() + "的佣金!");
//					} else {
//						yongJin.setStatus(4);
//						yongJin.setMemo(ref1.getUserId() + "未购买过本产品," + user.getUserId() + "购买产品的佣金流失!");
//					}
//				} else {
//					yongJin.setStatus(3);
//					yongJin.setMemo(ref1.getUserId() + "等级不足," + user.getUserId() + "购买产品的佣金流失!");
//				}
//				yongJinService.save(yongJin);
//				if (ref1.getReferrerId() != null) {
//					Users ref2 = userService.findById(ref1.getReferrerId());
//					if (ref2 != null) {
//						GoodYongJin yongJin1 = new GoodYongJin();
//						yongJin1.setCreateDate(new Date());
//						yongJin1.setFromUserId(user.getUserId());
//						yongJin1.setToUserId(ref1.getReferrerId());
//						yongJin1.setLevel(2);
//						yongJin1.setMoney(getRebateAmt(buyProd, order.getSize(), 2));
//						yongJin1.setOrderId(order.getOrdersId());
//						if (ref2.getLevel() >= 2) {
//							Integer num = findNumByTypeAndUserId(buyProd.getManufacturer(), ref2.getUserId());
//							if (num > 0||(buyProd.getKaiguan()!=null&&buyProd.getKaiguan()==1)) {
//								yongJin1.setStatus(0);
//								yongJin1.setMemo(user.getUserId() + "购买,生成" + ref2.getUserId() + "的佣金!");
//							} else {
//								yongJin1.setStatus(4);
//								yongJin1.setMemo(ref2.getUserId() + "未购买过本产品," + user.getUserId() + "购买产品的佣金流失!");
//							}
//						} else {
//							yongJin1.setStatus(3);
//							yongJin1.setMemo(ref2.getUserId() + "等级不足," + user.getUserId() + "购买产品的佣金流失!");
//						}
//						yongJinService.save(yongJin1);
//						if (ref2.getReferrerId() != null) {
//							Users ref3 = userService.findById(ref2.getReferrerId());
//							if (ref3 != null) {
//								GoodYongJin yongJin2 = new GoodYongJin();
//								yongJin2.setCreateDate(new Date());
//								yongJin2.setFromUserId(user.getUserId());
//								yongJin2.setToUserId(ref2.getReferrerId());
//								yongJin2.setLevel(3);
//								yongJin2.setMoney(getRebateAmt(buyProd, order.getSize(), 3));
//								yongJin2.setOrderId(order.getOrdersId());
//								if (ref3.getLevel() >= 3||(buyProd.getKaiguan()!=null&&buyProd.getKaiguan()==1)) {
//									Integer num = findNumByTypeAndUserId(buyProd.getManufacturer(), ref3.getUserId());
//									if (num > 0||(buyProd.getKaiguan()!=null&&buyProd.getKaiguan()==1)) {
//										yongJin2.setStatus(0);
//										yongJin2.setMemo(user.getUserId() + "购买,生成" + ref3.getUserId() + "的佣金!");
//									} else {
//										yongJin2.setStatus(4);
//										yongJin2.setMemo(
//												ref3.getUserId() + "未购买过本产品," + user.getUserId() + "购买产品的佣金流失!");
//									}
//								} else {
//									yongJin2.setStatus(3);
//									yongJin2.setMemo(ref3.getUserId() + "等级不足," + user.getUserId() + "购买产品的佣金流失!");
//								}
//								yongJinService.save(yongJin2);
//							}
//						}
//					}
//				}
//			}
//		}
	}
	@Override
	public void saveAndCFh(Orders order, Users user) {

		save(order);
		if(order.getType().equals(1)){
//			if(user.getReferrerId()!=null){
//				Users ref1 = userService.findById(user.getReferrerId());
//				if(ref1!=null){
//					GoodYongJin yongJin = new GoodYongJin();
//					yongJin.setCreateDate(new Date());
//					yongJin.setFromUserId(user.getUserId());
//					yongJin.setToUserId(user.getReferrerId());
//					yongJin.setLevel(1);
//					yongJin.setMoney(10.0*order.getSize());
//					yongJin.setOrderId(order.getOrdersId());
//					if(ref1.getLevel()>=1){
//						//查询订单的存在
//						Integer num = findNumByTypeAndUserId(1, ref1.getUserId());
//						if(num>0){
//							yongJin.setStatus(0);
//							yongJin.setMemo(user.getUserId()+"购买,生成"+ref1.getUserId()+"的佣金!");
//						}else{
//							yongJin.setStatus(4);
//							yongJin.setMemo(ref1.getUserId()+"未购买过本产品,"+user.getUserId()+"购买产品的佣金流失!");
//						}
//						
//					}else{
//						yongJin.setStatus(3);
//						yongJin.setMemo(ref1.getUserId()+"等级不足,"+user.getUserId()+"购买产品的佣金流失!");
//					}
//					yongJinService.save(yongJin);
//					if(ref1.getReferrerId()!=null){
//						Users ref2 = userService.findById(ref1.getReferrerId());
//						if(ref2!=null){
//							GoodYongJin yongJin1 = new GoodYongJin();
//							yongJin1.setCreateDate(new Date());
//							yongJin1.setFromUserId(user.getUserId());
//							yongJin1.setToUserId(ref1.getReferrerId());
//							yongJin1.setLevel(2);
//							yongJin1.setMoney(20.0*order.getSize());
//							yongJin1.setOrderId(order.getOrdersId());
//							if(ref2.getLevel()>=2){
//								Integer num = findNumByTypeAndUserId(1, ref2.getUserId());
//								if(num>0){
//									yongJin1.setStatus(0);
//									yongJin1.setMemo(user.getUserId()+"购买,生成"+ref2.getUserId()+"的佣金!");
//								}else{
//									yongJin1.setStatus(4);
//									yongJin1.setMemo(ref2.getUserId()+"未购买过本产品,"+user.getUserId()+"购买产品的佣金流失!");
//								}
//							}else{
//								yongJin1.setStatus(3);
//								yongJin1.setMemo(ref2.getUserId()+"等级不足,"+user.getUserId()+"购买产品的佣金流失!");
//							}
//							yongJinService.save(yongJin1);
//							if(ref2.getReferrerId()!=null){
//								Users ref3 = userService.findById(ref2.getReferrerId());
//								if(ref3!=null){
//									GoodYongJin yongJin2 = new GoodYongJin();
//									yongJin2.setCreateDate(new Date());
//									yongJin2.setFromUserId(user.getUserId());
//									yongJin2.setToUserId(ref2.getReferrerId());
//									yongJin2.setLevel(3);
//									yongJin2.setMoney(30.0*order.getSize());
//									yongJin2.setOrderId(order.getOrdersId());
//									if(ref3.getLevel()>=3){
//										Integer num = findNumByTypeAndUserId(1, ref3.getUserId());
//										if(num>0){
//											yongJin2.setStatus(0);
//											yongJin2.setMemo(user.getUserId()+"购买,生成"+ref3.getUserId()+"的佣金!");
//										}else{
//											yongJin2.setStatus(4);
//											yongJin2.setMemo(ref3.getUserId()+"未购买过本产品,"+user.getUserId()+"购买产品的佣金流失!");
//										}
//									}else{
//										yongJin2.setStatus(3);
//										yongJin2.setMemo(ref3.getUserId()+"等级不足,"+user.getUserId()+"购买产品的佣金流失!");
//									}
//									yongJinService.save(yongJin2);
//								}
//							}
//						}
//					}
//				}
//			}
		}
	
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

	@Override
	public Boolean saveJifenOrder(Orders order, Users user, Product prod,Integer xiaohaoJifen) {
		Integer jifen = 0;
//		if(order.getScore()!=null){
//			jifen=order.getScore().intValue();
//		}
		/*if(user.getBuyCount()==null||user.getBuyCount()<jifen){
			return false;
		}*/
		save(order);
		
		JiFenRecord record = new JiFenRecord();
		record.setCreateDate(new Date());
		record.setJifen(jifen);
		record.setMemo("用户"+user.getUserId()+":"+user.getUserName()+" 兑换商品 id:"+prod.getProdId()+" 花费"+jifen+"积分");
		record.setOrderId(order.getOrdersId());
		record.setOrdersBH(order.getOrdersBH());
		record.setStatus(0);
		
		record.setType(4);
		//LiuCode code = liuCodeService.findById(26);
		if(xiaohaoJifen != null){
			if(xiaohaoJifen == 1){
				record.setType(5);
			}
		}
		
		record.setUserId(user.getUserId());
		jiFenRecordService.save(record);
		return true;
	}

	@Override
	public Boolean jifenPay(Orders order, Users user) {
		try {
			user = userService.findById(order.getUserId());
			List jilist =null;
			
			LiuCode code = liuCodeService.findById(26);
			
			Integer xiaohaoType = 0;//0：其他积分支付  1：推荐用户积分支付
			if(code != null && code.getBusPerson().contains(order.getProductId().toString())){
					//可使用积分是否足够
					jilist = jiFenRecordService.countNewUserJifen(user.getUserId(), 2);
					if(jilist.size() >0){
//						if(Integer.parseInt(jilist.get(0).toString()) >=  order.getScore().intValue()){
//							xiaohaoType=1;
//						}
					}
					//推荐用户积分不足
					if(xiaohaoType == 0){
						jilist = jiFenRecordService.countUserJifen(user.getUserId(), 2);
					}
			}else{
				//可使用积分是否足够
				jilist = jiFenRecordService.countUserJifen(user.getUserId(), 2);
			}
			
			
			Integer dataji =0;
			if(jilist.size() >0){
				dataji = Integer.parseInt(jilist.get(0).toString());
				
			}
			//Integer jifen = 0;
			order.setOrderStatus(1);
			//List<JiFenRecord> updateRecords = new ArrayList<JiFenRecord>();
//			if(order.getScore() != null && order.getScore()<=dataji){
//				update(order);
//				List<JiFenRecord> records = jiFenRecordService.findByProperty("orderId", order.getOrdersId());
//				if(records.size() > 0){
//					JiFenRecord jf = records.get(0);
//					jf.setStatus(2);
//					jiFenRecordService.update(jf);
//				}else{
////					order.setOrderStatus(8);//设置订单需退款的状态8 退款完成状态9;
//					update(order);
//					return false;
//				}
//				return true;
//			}else{
//				order.setOrderStatus(8);//设置订单需退款的状态8 退款完成状态9;
//				update(order);
//				return false;
//			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}

	@Override
	public Boolean saveHuiYuanOrder(Orders order, Users user,
			Product prod) {
		save(order);
		Integer allMoney = 0;
//		Users parent1 = findParent(user);
//		Users parent2 = findParent(parent1);
//		Users parent3 = findParent(parent2);
		Integer orderMoney = order.getMoney().intValue();
		System.out.println("下单金额"+orderMoney);
//		if(user.getLevel()!=null&&user.getLevel()!=0){
//			if(user.getLevel()==2){
//				if(user.getSubmitMoney()==null||user.getSubmitMoney()<330){
//					allMoney=330;
//				}else{
//					allMoney=user.getSubmitMoney();
//				}
//				if((allMoney+orderMoney)>=660){
//					System.out.println( "level2大于660直接升vip");
//					return CTFenRun("3", orderMoney, user, parent1, parent2, parent3, order);
//				}
//			}else if(user.getLevel()==1){
//				if(user.getSubmitMoney()==null||user.getSubmitMoney()<110){
//					allMoney=110;
//				}else{
//					allMoney=user.getSubmitMoney();
//				}
//				if((allMoney+orderMoney)>=660){
//					System.out.println( "level1大于660直接升vip");
//					return CTFenRun("2,3", orderMoney, user, parent1, parent2, parent3, order);
//				}else if((allMoney+orderMoney)>=330){
//					System.out.println( "level1大于330升准vip");
//					return CTFenRun("2", orderMoney, user, parent1, parent2, parent3, order);
//				}
//			}
//		}else{
//			if((allMoney+orderMoney)>=660){
//				System.out.println( "大于660直接升vip");
//				return CTFenRun("1,2,3", orderMoney, user, parent1, parent2, parent3, order);
//			}else if((allMoney+orderMoney)>=330){
//				System.out.println( "大于330升准vip");
//				return CTFenRun("1,2", orderMoney, user, parent1, parent2, parent3, order);
//			}else if((allMoney+orderMoney)>=110){
//				System.out.println( "大于110升准vip");
//				return CTFenRun("1", orderMoney, user, parent1, parent2, parent3, order);
//			}
//		}
		return true;
	}
	
	public Boolean CTFenRun(String type,Integer orderMoney,Users user,Users parent1,Users parent2,
				Users parent3,Orders order){
		Random random = new Random();
		try {
			if(type.contains("3")){
				if(parent3!=null){
					XingHuoQuanRecord record = new XingHuoQuanRecord();
					record.setCreateDate(new Date());
					record.setFromUserId(user.getUserId());
//					if(parent3.getLevel()==null||parent3.getLevel()==0){
//						record.setStatus(3);
//						record.setMemo("["+parent3.getUserName()+":"+parent3.getUserId()+"]不是准VIP,忠实粉丝["+
//								user.getUserName()+":"+user.getUserId()+"]购买"+
//								order.getPname()+"的星火券流失");
//					}else{
//						record.setStatus(0);
//						record.setMemo("忠实粉丝["+user.getUserName()+":"+user.getUserId()+"]购买"+
//								order.getPname()+",生成["+parent3.getUserName()+":"+parent3.getUserId()+"]的星火券");
//					}
//					record.setType(2);
//					record.setUserId(parent3.getUserId());
//					Integer xinghuoquan = 420-random.nextInt(5);
//					record.setXinghuoquan(xinghuoquan);
//					record.setOrderId(order.getOrdersId());
//					record.setOrdersBH(order.getOrdersBH());
					xingHuoQuanRecordService.save(record);
					GoodYongJin yongJin = new GoodYongJin();
					yongJin.setCreateDate(new Date());
					yongJin.setFromUserId(user.getUserId());
					yongJin.setLevel(3);
					
					yongJin.setOrderId(order.getOrdersId());
					Double money = 90.0-random.nextInt(5);
					yongJin.setMoney(money);
					yongJin.setToUserId(parent3.getUserId());
//					if(parent3.getLevel()!=null&&parent3.getLevel()==3){
//						yongJin.setStatus(0);
//						yongJin.setMemo("忠实粉丝["+user.getUserName()+":"+user.getUserId()+"]购买"+
//							order.getPname()+",生成["+parent3.getUserName()+":"+parent3.getUserId()+"]的红包");
//					}else{
//						yongJin.setStatus(3);
//						yongJin.setMemo("["+parent3.getUserName()+":"+parent3.getUserId()+
//										"]不是VIP,忠实粉丝["+user.getUserName()+":"+user.getUserId()+"]购买"+
//										order.getPname()+"的红包流失");
//					}
//					yongJin.setType(2);
//					yongJinService.save(yongJin);
				}
			}
			if(type.contains("2")){
				if(parent2!=null){
					XingHuoQuanRecord record = new XingHuoQuanRecord();
					record.setCreateDate(new Date());
					record.setFromUserId(user.getUserId());
//					if(parent2.getLevel()==null||parent2.getLevel()==0){
//						record.setStatus(3);
//						record.setMemo("["+parent2.getUserName()+":"+parent2.getUserId()+"]不是准VIP,铁杆粉丝["+
//								user.getUserName()+":"+user.getUserId()+"]购买"+
//								order.getPname()+"的星火券流失");
//					}else{
//						record.setStatus(0);
//						record.setMemo("铁杆粉丝["+user.getUserName()+":"+user.getUserId()+"]购买"+
//								order.getPname()+",生成["+parent2.getUserName()+":"+parent2.getUserId()+"]的星火券");
//					}
//					record.setType(2);
//					record.setUserId(parent2.getUserId());
//					Integer xinghuoquan = 280-random.nextInt(5);
//					record.setXinghuoquan(xinghuoquan);
//					record.setOrderId(order.getOrdersId());
//					record.setOrdersBH(order.getOrdersBH());
//					xingHuoQuanRecordService.save(record);
					GoodYongJin yongJin = new GoodYongJin();
					yongJin.setCreateDate(new Date());
					yongJin.setFromUserId(user.getUserId());
					yongJin.setLevel(2);
					
					yongJin.setOrderId(order.getOrdersId());
					Double money = 60.0-random.nextInt(5);
					yongJin.setMoney(money);
					yongJin.setToUserId(parent2.getUserId());
//					if(parent2.getLevel()!=null&&parent2.getLevel()==3){
//						yongJin.setStatus(0);
//						yongJin.setMemo("铁杆粉丝["+user.getUserName()+":"+user.getUserId()+"]购买"+
//							order.getPname()+",生成["+parent2.getUserName()+":"+parent2.getUserId()+"]的红包");
//					}else{
//						yongJin.setStatus(3);
//						yongJin.setMemo("["+parent2.getUserName()+":"+parent2.getUserId()+
//										"]不是VIP,铁杆粉丝["+user.getUserName()+":"+user.getUserId()+"]购买"+
//										order.getPname()+"的红包流失");
//					}
//					yongJin.setType(2);
//					yongJinService.save(yongJin);
				}
			}
			if(type.contains("1")){
				if(parent1!=null){
					XingHuoQuanRecord record = new XingHuoQuanRecord();
					record.setCreateDate(new Date());
					record.setFromUserId(user.getUserId());
//					if(parent1.getLevel()==null||parent1.getLevel()==0){
//						record.setStatus(3);
//						record.setMemo("["+parent1.getUserName()+":"+parent1.getUserId()+"]不是准VIP,超级粉丝["+
//								user.getUserName()+":"+user.getUserId()+"]购买"+
//								order.getPname()+"的星火券流失");
//					}else{
//						record.setStatus(0);
//						record.setMemo("超级粉丝["+user.getUserName()+":"+user.getUserId()+"]购买"+
//								order.getPname()+",生成["+parent1.getUserName()+":"+parent1.getUserId()+"]的星火券");
//					}
//					record.setType(2);
//					record.setUserId(parent1.getUserId());
//					Integer xinghuoquan = 140-random.nextInt(5);
//					record.setXinghuoquan(xinghuoquan);
//					record.setOrderId(order.getOrdersId());
//					record.setOrdersBH(order.getOrdersBH());
//					xingHuoQuanRecordService.save(record);
					GoodYongJin yongJin = new GoodYongJin();
					yongJin.setCreateDate(new Date());
					yongJin.setFromUserId(user.getUserId());
					yongJin.setLevel(1);
					
					yongJin.setOrderId(order.getOrdersId());
					Double money = 30.0-random.nextInt(5);
					yongJin.setMoney(money);
					yongJin.setToUserId(parent1.getUserId());
//					if(parent1.getLevel()!=null&&parent1.getLevel()==3){
//						yongJin.setStatus(0);
//						yongJin.setMemo("超级粉丝["+user.getUserName()+":"+user.getUserId()+"]购买"+
//							order.getPname()+",生成["+parent1.getUserName()+":"+parent1.getUserId()+"]的红包");
//					}else{
//						yongJin.setStatus(3);
//						yongJin.setMemo("["+parent1.getUserName()+":"+parent1.getUserId()+
//										"]不是VIP,超级粉丝["+user.getUserName()+":"+user.getUserId()+"]购买"+
//										order.getPname()+"的红包流失");
//					}
//					yongJin.setType(2);
//					yongJinService.save(yongJin);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
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
	public Integer countUserIdCardProduct(Integer userId, Integer prodId,String idCard) {
		Product product = productService.findById(prodId);
		if(product==null){
			return 1;
		}
		if(userId != null && prodId != null && idCard != null){
			String sql ="SELECT COUNT(m.ordersId) from miaosha_order m where  m.tel='"+idCard+"'  and  m.qi="+product.getQi()+" and m.order_status>0";
			List list = this.findBySql(sql, null);
			return Integer.parseInt(list.get(0).toString());
		}
		
		return 0;
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

	
}
