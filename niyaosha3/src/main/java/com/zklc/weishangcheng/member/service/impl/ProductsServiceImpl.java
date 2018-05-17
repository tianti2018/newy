package com.zklc.weishangcheng.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.hibernate.persistent.Products;
import com.zklc.weishangcheng.member.service.ProductsService;

@Service
public class ProductsServiceImpl extends BaseServiceImp<Products, Integer> implements ProductsService {

	@Override
	public List<Products> findPagerByPropertyAndSort(Integer type, Integer pageNum, Integer pageSize) {
		String sql = "select * from products  where status=1 ";
		if(type!=null){
			sql+=" and type= "+type;
		}
		sql+=" order by createDate desc, paixu desc ";
		if(pageNum!=null&&pageNum!=0){
			sql+=" LIMIT "+(pageNum-1)*pageSize +","+pageSize;
		}
		List<Products> list = findBySql(Products.class, sql, null);
		
		return list;
	}

	@Override
	public List<Products> findWeiShangByDianId(Integer dianpuId,Integer pageNum, Integer pageSize) {
		String sql =" select * from products where productsId not in (select productId from products_dianpu where dianpuId = "+dianpuId+") and status = 1 ";
		if(pageNum==null){
			pageNum = 1;
		}
		if(pageSize==null){
			pageSize = 10;
		}
		sql += " limit "+(pageNum-1)*pageSize+","+pageNum*pageSize;
		return findBySql(Products.class, sql, null);
	}

}
