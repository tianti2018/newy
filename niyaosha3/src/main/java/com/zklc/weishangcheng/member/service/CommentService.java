package com.zklc.weishangcheng.member.service;

import java.math.BigInteger;
import java.util.List;

import com.zklc.framework.service.IBaseService;
import com.zklc.weishangcheng.member.hibernate.persistent.ProductComment;

public interface CommentService extends IBaseService<ProductComment, Integer> {
	/*
	 * 插入评论的方法
	 */
	public void savecomment(ProductComment procom);
	/*
	 * 查询评论的方法
	 */
	public List<ProductComment> commentList(Integer prodId,Integer pageNum);
	/*
	 * 查询评论总条数的方法
	 */
	public BigInteger commentCount(Integer prodId);
	/*
	 * 查询订单状态
	 */
	public List<Integer> getstatus(Integer userId,Integer prodId);
}
