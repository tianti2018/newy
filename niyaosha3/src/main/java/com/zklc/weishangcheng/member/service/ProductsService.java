package com.zklc.weishangcheng.member.service;

import java.util.List;

import com.zklc.framework.service.IBaseService;
import com.zklc.weishangcheng.member.hibernate.persistent.Products;;

public interface ProductsService extends IBaseService<Products,Integer> {
	
	List<Products> findPagerByPropertyAndSort(Integer type,Integer pageNum,Integer pageSize);

	List<Products> findWeiShangByDianId(Integer dianpuId, Integer pageNum, Integer pageSize);
	
}
