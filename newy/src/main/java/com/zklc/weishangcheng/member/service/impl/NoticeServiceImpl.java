package com.zklc.weishangcheng.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.hibernate.persistent.Notice;
import com.zklc.weishangcheng.member.service.NoticeService;

@Service
public class NoticeServiceImpl extends BaseServiceImp<Notice, Integer>
		implements NoticeService {

    
	/**
	 * 查询所有公告
	 */
	@Override
	public List<Notice> getAll(Integer pageNum) {
		if(pageNum==null||pageNum.equals("")){
			pageNum=0;
		}else{
			pageNum=pageNum*5;
		}
		String sql="select * from notice where status=1 order by create_date desc";
		sql+=" limit "+pageNum+" ,"+5;
		System.out.println(sql);
		List<Notice> notices=super.findBySql(sql, null);
		return notices;
	}
    
	
	/**
	 * 查询最近的一条公告
	 */
	@Override
	public String findLastNotice() {
		String sql="SELECT title FROM notice ORDER BY create_date DESC LIMIT 1";
		List notices = findBySql(sql, null);
		if(notices.size()>0){
			return notices.get(0).toString();
		}
		return null;
	}

}
