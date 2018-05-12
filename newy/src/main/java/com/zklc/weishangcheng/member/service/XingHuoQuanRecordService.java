package com.zklc.weishangcheng.member.service;

import java.util.List;

import com.zklc.framework.service.IBaseService;
import com.zklc.weishangcheng.member.hibernate.persistent.XingHuoQuanRecord;

public interface XingHuoQuanRecordService extends IBaseService<XingHuoQuanRecord, Integer> {

	Integer findAllMoneyByUserIdAndStatus(Integer userId, Integer status);
	/*
	 * 查询用户积分
	 */
		public List findXingHuoQuanList(Integer userId,String date1,String date2,Integer pageNum);

}
