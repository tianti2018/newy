package com.zklc.weishangcheng.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.hibernate.persistent.ProductsForDianpu;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.ProductVo;
import com.zklc.weishangcheng.member.service.ProductsForDianpuService;

@Service
public class ProductsForDianpuServiceImpl extends BaseServiceImp<ProductsForDianpu, Integer>
		implements ProductsForDianpuService {

	@Override
	public List<ProductVo> findPagerByPropertyAndSort(Integer type, Integer pageNum, Integer pageSize,Integer dianpuId) {
		String sql = "select pd.id,pd.dianpuId,pd.productId,pd.price,pd.type,pd.paixu,pd.kaiguan,pd.createDate,"+
				"pd.jianyiPrice,pd.name,pd.headUrl,pd.status,pd.prodType,p.levelone,p.leveltwo,p.levelthr,p.levelfor,p.guige"+
				" from products_dianpu pd join products p on pd.productId = p.productsId  where pd.dianpuId= "+dianpuId;
		if(type!=null){
			sql+=" and pd.type= "+type;
		}
		sql+=" order by pd.createDate desc, pd.paixu desc ";
		if(pageSize==null){
			pageSize = 10;
		}
		if(pageNum!=null&&pageNum!=0){
			sql+=" LIMIT "+(pageNum-1)*pageSize +","+pageSize;
		}
		List<ProductVo> list = findBySql(ProductVo.class,sql, null);
		
		return list;
	}

	@Override
	public List<ProductsForDianpu> findbyDianPuIdAndProductId(Integer dianpuId, Integer productId) {
		String hql = "from ProductsForDianpu where dianpuId ="+dianpuId+" and productId = "+productId;
		return findByHql(hql, null);
	}

}
