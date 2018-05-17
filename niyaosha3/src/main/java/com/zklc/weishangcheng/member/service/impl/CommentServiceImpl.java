package com.zklc.weishangcheng.member.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.hibernate.persistent.HongbaoDian;
import com.zklc.weishangcheng.member.hibernate.persistent.ProductComment;
import com.zklc.weishangcheng.member.hibernate.persistent.XingHuoQuanRecord;
import com.zklc.weishangcheng.member.service.CommentService;
import com.zklc.weishangcheng.member.service.UserService;

@Service
public class CommentServiceImpl extends BaseServiceImp<ProductComment, Integer> implements CommentService{

	@Override
	public void savecomment(ProductComment procom) {
		super.save(procom);
		
	}

	@Override
	public List commentList(Integer prodId, Integer pageNum) {
		if(pageNum==null||pageNum.equals("")){
			pageNum=0;
		}else{
			pageNum=pageNum*5;
		}
		String sql="select * from product_comment where prodId = "+prodId+"";
		sql+=" order by createtime desc";
		sql+=" limit "+pageNum+" ,"+5;
		System.out.println(sql);
		List<ProductComment> list=super.findBySql(sql, null);
		return list;
	}

	@Override
	public BigInteger commentCount(Integer prodId) {
		String sql="select count(1) from product_comment where prodId="+prodId+"";
		List list=super.findBySql(sql, null);
		System.out.println(list.get(0));
		BigInteger count =(BigInteger) list.get(0);
		System.out.println(count);
		return count;
	}

	@Override
	public List<Integer> getstatus(Integer userId, Integer prodId) {
		String sql="select order_status from miaosha_order where qi=(select qi from product where prodId="+prodId+") and userId="+userId+"";
		List<Integer> list=super.findBySql(sql, null);
		System.out.println(sql);
		System.out.println(list.size());
		return list;
	}


}
