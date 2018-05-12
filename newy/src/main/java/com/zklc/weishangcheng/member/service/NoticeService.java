package com.zklc.weishangcheng.member.service;

import java.util.List;

import com.zklc.framework.service.IBaseService;
import com.zklc.weishangcheng.member.hibernate.persistent.Notice;

public interface NoticeService extends IBaseService<Notice, Integer> {
	
	/**
	 * 查询所有公告
	 */
	public List<Notice> getAll(Integer pageNum);
	
	
	/**
	 * 查询最近的一条公告
	 */
	public String findLastNotice();

}
