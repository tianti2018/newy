package com.zklc.weishangcheng.member.service;

import java.util.List;
import java.util.Map;

import com.zklc.framework.service.IBaseService;
import com.zklc.weishangcheng.member.hibernate.persistent.FhRecord;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;

public interface FhrecordService extends IBaseService<FhRecord, Integer> {
	// 查询会员的总积分
	public Double findTotalJiFenByUserId(Integer userId);

	// 用户得到的所有积分
	public Double findTotalJiFenOneByUserId(Integer userId, String fhType,String flag);

	//排序序号产生的积分
	public Double findTotalJiFenByOrders(Integer uoId);
	
	//
	public List<Integer> getLeftOrRightUser(Integer key, Map<Integer,Integer[]> map,List<Integer> list,String flag);

}