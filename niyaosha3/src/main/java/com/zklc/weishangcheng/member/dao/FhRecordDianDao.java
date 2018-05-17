package com.zklc.weishangcheng.member.dao;

import com.zklc.framework.dao.EntityDao;
import com.zklc.weishangcheng.member.hibernate.persistent.FhRecordDian;

public interface FhRecordDianDao extends EntityDao<FhRecordDian, Integer> {

	/**
	 * 查询等级的红包数量
	 * @param userId
	 * @param level
	 * @return
	 */
	int findCountFhRecordByLevel(Integer userId, int level);

	FhRecordDian findFhRecordBytoUserId(Integer puserId, Integer fromUserId,
			double amount, Integer flag, Integer ordersId, int level);

}
