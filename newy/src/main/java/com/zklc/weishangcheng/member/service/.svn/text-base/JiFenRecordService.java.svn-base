package com.zklc.weishangcheng.member.service;



import java.util.List;

import com.zklc.framework.service.IBaseService;
import com.zklc.weishangcheng.member.hibernate.persistent.JiFenRecord;

public interface JiFenRecordService extends IBaseService<JiFenRecord, Integer> {
/*
 * 查询用户积分
 */
	public List findJiFenList(Integer userId,Integer status,String date1,String date2,Integer pageNum);

	
	public Integer findbyUserIdAndStatus(Integer userId, int status);
	
	/**
	 * 查询用户积分
	 * @param userId
	 * @param type 1 查询总积分 可用积分 已用积分  2 可用积分
	 * @return
	 */
	public List countUserJifen(Integer userId,Integer type);
	
	/**
	 * 查询新用户推荐积分
	 * @param userId
	 * @param type 1 查询总积分 可用积分 已用积分  2 可用积分
	 * @return
	 */
	public List countNewUserJifen(Integer userId,Integer type);
	
}
