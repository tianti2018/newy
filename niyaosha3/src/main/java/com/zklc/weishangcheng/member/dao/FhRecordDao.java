package com.zklc.weishangcheng.member.dao;

import java.util.List;

import com.zklc.weishangcheng.member.hibernate.persistent.FhRecord;
import com.zklc.framework.dao.EntityDao;
public interface FhRecordDao extends EntityDao<FhRecord, Integer> {
	public double findleveData(Integer userId, String flag);
	
	public FhRecord findFhRecordBytoUserId(Integer userId, Integer fromUserId,Double amount,String flag,Integer ordersId);
	
	public int findCountFhRecordByMoney(Integer userId,Double fhmoney);
	
	//查找所有的我的没有发放的红包
	public List<Integer> findNoSendHongbaoFromUserIds(Integer userId);

	public List<FhRecord> findFHbyUserIdAndFlag(Integer userId, int i);
}