package com.zklc.weishangcheng.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.hibernate.persistent.ProductsForDianpu;
import com.zklc.weishangcheng.member.service.ProductsForDianpuService;

@Service
public class ProductsForDianpuServiceImpl extends BaseServiceImp<ProductsForDianpu, Integer>
		implements ProductsForDianpuService {

	@Override
	public List<ProductsForDianpu> findPagerByPropertyAndSort(Integer type, Integer pageNum, Integer pageSize,Integer dianpuId) {
		String sql = "select * from products_dianpu  where dianpuId= "+dianpuId;
		if(type!=null){
			sql+=" and type= "+type;
		}
		sql+=" order by createDate desc, paixu desc ";
		if(pageSize==null){
			pageSize = 10;
		}
		if(pageNum!=null&&pageNum!=0){
			sql+=" LIMIT "+(pageNum-1)*pageSize +","+pageSize;
		}
		List<ProductsForDianpu> list = findBySql(ProductsForDianpu.class,sql, null);
		
		return list;
	}

	@Override
	public List<ProductsForDianpu> findbyDianPuIdAndProductId(Integer dianpuId, Integer productId) {
		String hql = "from ProductsForDianpu where dianpuId ="+dianpuId+" and productId = "+productId;
		return findByHql(hql, null);
	}

}
