package com.zklc.weishangcheng.member.service;

import java.util.List;

import com.zklc.framework.service.IBaseService;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.UserVo;

public interface UseryService extends IBaseService<Usery, Integer> {

	Usery findbyUserId(Integer userId);
	
	Usery findbyJifenUser(Users user);
	
	Users findJifenUserByUsery(Usery usery);
	
	Usery findbyWxOpenId(String wxOpenId);
	
	Usery findbyUnionId(String unionId);

	List<Usery> findChildsPagerByLevelAndSort(String viewLevel, Integer id, Integer pageNum, Integer pageSize);

	Long findChildNum(Integer useryId);
	
	Long findChildNumByLevel(Integer useryId,Integer level);

	Usery findByDianpuId(Integer dianpuId);
	
	Usery jianceUserLevel(Integer useryId,Integer userId);
	
	Usery jianceUserLevel(Usery usery,Users user);
}
