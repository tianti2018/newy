package com.zklc.weishangcheng.member.service;

import java.util.List;

import com.zklc.framework.service.IBaseService;
import com.zklc.weishangcheng.member.hibernate.persistent.City;

public interface CityService extends IBaseService<City, Integer>{
    public List<City> getCities();
    public List<City> findByParentId(Integer parentId);
    public List<City> findByLevel(Integer level);
    public List<City> getByIds(String ids);
}
