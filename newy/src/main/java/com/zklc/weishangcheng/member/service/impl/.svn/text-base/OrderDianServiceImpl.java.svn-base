package com.zklc.weishangcheng.member.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.hibernate.persistent.FhRecordDian;
import com.zklc.weishangcheng.member.hibernate.persistent.JifenUser;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderDian;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.PriceForDianZhuLevel;
import com.zklc.weishangcheng.member.service.FhRecordDianService;
import com.zklc.weishangcheng.member.service.JifenUserService;
import com.zklc.weishangcheng.member.service.OrderDianService;
import com.zklc.weishangcheng.member.service.UseryService;

@Service
public class OrderDianServiceImpl extends BaseServiceImp<OrderDian, Integer> implements
		OrderDianService {

	@Autowired
	private JifenUserService userService;
	@Autowired
	private UseryService useryService;
	@Autowired
	private FhRecordDianService fhrecordService;
	
	@Override
	public OrderDian findOrderByOrderBH(String out_trade_no) {
		List<OrderDian> orders = findByProperty("ordersBH", out_trade_no);
		if(orders.size()>0){
			return orders.get(0);
		}
		return null;
	}

	@Override
	public void moneyPay(OrderDian order, JifenUser user) {

		order.setOrderStatus(1); //已支付
		order.setCreateDate(new Date());
		update(order);
		user.setCardId(order.getLevel());
		userService.update(user);
		List<FhRecordDian> updateDians = new ArrayList<FhRecordDian>();
		List<FhRecordDian> fhRecordDians = fhrecordService.findByProperty("ordersId", order.getOrdersId());
		if(fhRecordDians.size()>0){
			for(FhRecordDian fhdian:fhRecordDians){
				if(fhdian.getFlag().equals(0)){
					fhdian.setFlag(1);
					updateDians.add(fhdian);
				}
			}
		}
		if(updateDians.size()>0){
			fhrecordService.saveOrUpdateAll(updateDians);
		}
	}
	@SuppressWarnings("unused")
	public List<JifenUser> findAllParent(JifenUser user,List<JifenUser> list) {
		if (null==user) {
			return list;
		}
		if (list.size()==3) { //15层的时候此时变为15
			return list;
		}
		if (null==list) {
			list = new ArrayList<JifenUser>();
		}
		
		Integer referrerId = user.getReferrerId();
		System.out.println(">>>>>>>>> referrerId" + referrerId);
		if (null!=referrerId) {
			JifenUser ref = userService.findById(referrerId);
			list.add(ref);
			findAllParent(ref,list);
		}
		
		return list;
	}
	
	public void creatFhrecord(Integer puserId,Integer formUserId,int level, Double fhmoney,Integer ordersId,Integer flag) {
		if (fhmoney>200) {
			int cishu = (int) Math.floor(fhmoney/200); //提现200的次数
			int yushu = (int)(fhmoney%200);
			for (int i=0;i<cishu;i++) {
				FhRecordDian fhRecord = fhrecordService.findFhRecordBytoUserId(puserId, formUserId, 200.0, 0,ordersId,level);
				if (null!=fhRecord) {
					fhRecord.setFlag(flag);
					fhRecord.setUpdaDate(new Date());
					fhrecordService.update(fhRecord);
				}
			}
			if (yushu!=0) {
				FhRecordDian fhRecord = fhrecordService.findFhRecordBytoUserId(puserId, formUserId, yushu*1.0, 0,ordersId,level);
				if (null!=fhRecord) {
					fhRecord.setFlag(flag);
					fhRecord.setUpdaDate(new Date());
					fhrecordService.update(fhRecord);
				}
			}
		}
		else {
			FhRecordDian fhRecord = fhrecordService.findFhRecordBytoUserId(puserId, formUserId, fhmoney, 0,ordersId,level);
			if (null!=fhRecord) {
				fhRecord.setFlag(flag);
				fhRecord.setUpdaDate(new Date());
				fhrecordService.update(fhRecord);
			}
		}
	}

	@Override
	public void saveAndCFh(OrderDian order, JifenUser user) {
		save(order);
		Integer userLevel = user.getCardId()==null?0:user.getCardId();
		Integer levelValue = order.getLevel();
		Integer referrerId = user.getReferrerId();
		if (null!=referrerId) {
			List<JifenUser> list2 = new ArrayList<JifenUser>();
			list2 = findAllParent(user,list2);
			for (int i=userLevel;i<levelValue;i++) {
				Integer levelOne = i/3;
				if (levelOne<=(list2.size()-1)) {
					JifenUser ref = list2.get(levelOne);
					System.out.println(ref.getUserName());
					if(ref!=null){
						Double lv = 0.0;
						Integer refLevelOne =0; 
						if(ref.getCardId()!=null&&ref.getCardId()!=0){
							refLevelOne = (ref.getCardId()-1)/3;
						}
						switch (refLevelOne) {
						case 0:
							lv = 0.7;
							break;
						case 1:
							lv = 0.8;
							break;
						case 2:
							lv = 0.9;
							break;
						}
						if(ref.getCardId()==null||ref.getCardId()<(i+1)){
							creatFhrecord(ref,user.getUserId(),levelOne,PriceForDianZhuLevel.price[i]*lv,order.getOrdersId(),false,i+1);
						}else{
							creatFhrecord(ref,user.getUserId(),levelOne,PriceForDianZhuLevel.price[i]*lv,order.getOrdersId(),true,i+1);
						}
					}
				}
			}
		}
	}

	private void creatFhrecord(JifenUser ref, Integer formUserId, Integer levelOne,
			double fhmoney, int ordersId, boolean b,Integer level) {

		if (fhmoney>200) {
			int cishu = (int) Math.floor(fhmoney/200); //提现200的次数
			int yushu = (int)(fhmoney%200);
			for (int i=0;i<cishu;i++) {
				FhRecordDian fhRecord = new FhRecordDian();
				fhRecord.setOrdersId(ordersId);
				fhRecord.setUserId(ref.getUserId());
				fhRecord.setFromUserId(formUserId);
				fhRecord.setLevel(levelOne);
				fhRecord.setFhType("2");
				fhRecord.setFhmoney(200.0);
				fhRecord.setCreateDate(new Date());
				fhRecord.setDianzhuLevel(level);
				fhRecord.setMemo("用户【"+formUserId+"】 升【"+level+"】");
				if(b){
					fhRecord.setFlag(0);
				}else{
					fhRecord.setFlag(4);
					fhRecord.setUpdaDate(new Date());
				}
				fhrecordService.save(fhRecord);
			}
			if (yushu!=0) {
				FhRecordDian fhRecord = new FhRecordDian();
				fhRecord.setOrdersId(ordersId);
				fhRecord.setUserId(ref.getUserId());
				fhRecord.setFromUserId(formUserId);
				fhRecord.setLevel(levelOne);
				fhRecord.setFhType("2");
				fhRecord.setDianzhuLevel(level);
				fhRecord.setFhmoney(yushu*1.0);
				fhRecord.setCreateDate(new Date());
				fhRecord.setMemo("用户【"+formUserId+"】 升【"+level+"】");
				if(b){
					fhRecord.setFlag(0);
				}else{
					fhRecord.setFlag(4);
					fhRecord.setUpdaDate(new Date());
				}

				fhrecordService.save(fhRecord);
			}
		}
		else {
			FhRecordDian fhRecord = new FhRecordDian();
			fhRecord.setOrdersId(ordersId);
			fhRecord.setLevel(levelOne);
			fhRecord.setUserId(ref.getUserId());
			fhRecord.setFromUserId(formUserId);
			fhRecord.setFhType("2");
			fhRecord.setDianzhuLevel(level);
			fhRecord.setFhmoney(fhmoney);
			fhRecord.setCreateDate(new Date());
			fhRecord.setMemo("用户【"+formUserId+"】 升【"+level+"】");
			if(b){
				fhRecord.setFlag(0);
			}else{
				fhRecord.setFlag(4);
				fhRecord.setUpdaDate(new Date());
			}

			fhrecordService.save(fhRecord);
		}
	
	
	}

	public static void main(String[]args){
		System.out.println(-1/3);
	}
}
