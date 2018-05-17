package com.zklc.weishangcheng.member.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.hibernate.persistent.City5d;
import com.zklc.weishangcheng.member.service.City5dService;

@Service
public class City5dServiceImpl extends BaseServiceImp<City5d, Integer> implements City5dService{
	@Override
	public List<City5d> getCities() {
		List<City5d> list=super.findByHql("from City5d t  where t.level=3", null);//findByProperty("leveltype", level);
		 
		return list;
	}
	@Override
	public List<City5d> findByParentId(Integer parentId)
	{
		List<City5d> list=super.findByHql("from City5d t  where t.parentId="+parentId+"", null);
		 
		return list;
	}
	@Override
	 public List<City5d> findByLevel(Integer level)
	 {
		List<City5d> list=super.findByHql("from City5d t  where t.level="+level+"", null);
		 
		return list;
	 }
	@Override
	 public List<City5d> getByIds(String ids)
	 {
		if(ids==null||ids.isEmpty()){
			return new ArrayList<City5d>();
		}
		List<City5d> list=super.findByHql("from City5d t  where t.id in("+ids+")", null);
		 
		return list;
	 }
}
