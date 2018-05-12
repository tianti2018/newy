package com.zklc.weishangcheng.member.service;

import java.util.List;

import com.zklc.framework.service.IBaseService;
import com.zklc.weishangcheng.member.hibernate.persistent.FhRecordDian;

public interface FhRecordDianService extends IBaseService<FhRecordDian, Integer> {

	FhRecordDian findFhRecordBytoUserId(Integer puserId, Integer formUserId,
			double d, Integer flag, Integer ordersId, int level);

	/**
	 * 查询等级红包个数
	 * @param userId
	 * @param level
	 * @return
	 */
	int findCountFhRecordByLevel(Integer userId, int level);

	/**
	 * 店主红包
	 * @param wxOpenid
	 * @param userId
	 * @param userId2
	 * @param level
	 * @param amount
	 * @param flagCount
	 * @return
	 */
	String fahongbao(String wxOpenid, Integer userId, Integer userId2,
			Integer level, Integer amount, Integer flagCount);
	
	/**
	 * 店主红包
	 * @param wxOpenid
	 * @param userId
	 * @param userId2
	 * @param level
	 * @param amount
	 * @param flagCount
	 * @return
	 */
	String bufahongbao(String wxOpenid, Integer userId, Integer userId2,
			Integer level, Integer amount, Integer flagCount);

	/**
	 * 查询店主红包金额
	 * @param userId
	 * @param string
	 * @param string2
	 * @return
	 */
	Double findTotalJiFenOneByUserId(Integer userId, String string,
			String string2);
}
