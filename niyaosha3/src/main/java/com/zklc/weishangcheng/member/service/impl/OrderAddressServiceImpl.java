package com.zklc.weishangcheng.member.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.utils.MyUtils;
import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderAddress;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.UserVo;
import com.zklc.weishangcheng.member.service.OrderAddressService;
@Service
public class OrderAddressServiceImpl extends BaseServiceImp<OrderAddress, Integer> implements OrderAddressService {
	@Override
	public void defaultAddr(Integer aid,Integer userId) {
		String sql ="update OrderAddress t set t.isFirst =0 where t.userId=? ";
		update(sql,userId);
		//当前秒杀商品的状态改为1
		sql ="update OrderAddress t set t.isFirst =1 where t.id=? ";
		update(sql,aid);
		
	}

	@Override
	public OrderAddress findOrderAddressByUserId(Integer userId) {
		List<OrderAddress> list=super.findByHql("from OrderAddress t  where t.isFirst=1 and t.userId="+userId+"", null);
		if(MyUtils.isNotEmpty(list))
		{
			return list.get(0);
			 
		}
		return null;
	}

	
	@Override
	public List<OrderAddress> findByUserId(Integer userId)
	{
		return findByHql("from OrderAddress t  where t.userId="+userId+"", null);
	}

	@Override
	public List<OrderAddress> findByUseryId(Integer useryId) {
		
		return findByHql("from OrderAddress t  where t.useryId="+useryId+"", null);
	}

	@Override
	public List<OrderAddress> listAllAddress(UserVo userVo) {
		Users user = userVo.getUser();
		Usery usery = userVo.getUsery();
 		
 		List<OrderAddress> tempList = new ArrayList<>();
		if(user!=null){
			List<OrderAddress> userAddressList = findByUserId(user.getUserId());
			if(userAddressList!=null)
				tempList.addAll(userAddressList);
		}
		if(usery!=null){
			List<OrderAddress> useryAddressList = findByUseryId(usery.getId());
			if(useryAddressList!=null&&useryAddressList.size()!=0){
				for(OrderAddress address:useryAddressList){
					if(!tempList.contains(address)){
						tempList.add(address);
					}
				}
			}
		}
		return tempList;
	}

	@Override
	public UserVo defaultAddr(Integer id, UserVo userVo) {
		String hql ="";
		if(userVo.getUser()!=null){
			hql ="update OrderAddress t set t.isFirst =0 where  t.userId=? ";
			update(hql,userVo.getUser().getUserId());
		}
		if(userVo.getUsery()!=null){
			hql ="update OrderAddress t set t.isFirst =0 where  t.useryId=? ";
			update(hql,userVo.getUsery().getId());
		}
		OrderAddress orderAddress = findById(id);
		orderAddress.setIsFirst("1");
		userVo.setOrderAddress(orderAddress);
		return userVo;
	}

	@Override
	public OrderAddress findDefaultAddressByUserVo(UserVo userVo) {
		String hql = ""; 
		List<OrderAddress> list = null;
		if(userVo.getUser()!=null){
			hql = "from OrderAddress where isFirst ='1'  and userId = " +userVo.getUser().getUserId();
			list = findByHql(hql, null);
			if(list!=null&&list.size()>0){
				return list.get(0);
			}
		}
		if(userVo.getUsery()!=null){
			hql="from OrderAddress where isFirst ='1'  and useryId =" + userVo.getUsery().getId();
			list = findByHql(hql, null);
			if(list!=null&&list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}

}
 
