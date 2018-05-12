package com.zklc.weishangcheng.member.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.hibernate.persistent.City;
import com.zklc.weishangcheng.member.service.CityService;

@Service
public class CityServiceImpl extends BaseServiceImp<City, Integer> implements CityService{
	@Override
	public List<City> getCities() {
		List<City> list=super.findByHql("from City t  where t.leveltype=2 and t.status>=1", null);//findByProperty("leveltype", level);
		 
		return list;
	}
	@Override
	public List<City> findByParentId(Integer parentId)
	{
		List<City> list=super.findByHql("from City t  where t.parentId="+parentId+"", null);
		 
		return list;
	}
	@Override
	 public List<City> findByLevel(Integer level)
	 {
		List<City> list=super.findByHql("from City t  where t.leveltype="+level+"", null);
		 
		return list;
	 }
	@Override
	 public List<City> getByIds(String ids)
	 {
		if(ids==null||ids.isEmpty()){
			return new ArrayList<City>();
		}
		List<City> list=super.findByHql("from City t  where t.id in("+ids+")", null);
		 
		return list;
	 }
}
