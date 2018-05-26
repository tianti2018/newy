package com.zklc.weishangcheng.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.dao.UserDao;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.service.UseryService;

@Service
public class UseryServiceImpl extends BaseServiceImp<Usery, Integer> implements
		UseryService {

	@Autowired
	private UserDao jifenUserDao;
	
	@Override
	public Usery findbyUserId(Integer userId) {
		List<Usery> userys = findByProperty("userId", userId);
		if(userys.size()>0){
			return userys.get(0);
		}
		return null;
	}

	@Override
	public Usery findbyJifenUser(Users user) {
		return findbyUserId(user.getUserId());
	}

	@Override
	public Users findJifenUserByUsery(Usery usery) {
		return jifenUserDao.findById(usery.getUserId());
	}

	@Override
	public Usery findbyWxOpenId(String wxOpenId) {
		List<Usery> userys = findByProperty("wxOpenid", wxOpenId);
		if(userys.size()>0){
			return userys.get(0);
		}
		return null;
	}

	@Override
	public Usery findbyUnionId(String unionId) {
		List<Usery> userys = findByProperty("unionid", unionId);
		if(userys.size()>0){
			return userys.get(0);
		}
		return null;
	}

	@Override
	public List<Usery> findChildsPagerByLevelAndSort(String viewLevel, Integer id,Integer pageNum,Integer pageSize) {
		String sql ="select * from usery where parentId = "+id;
		if(viewLevel!=null&&!viewLevel.equals("all")){
			sql+=" and level = "+viewLevel;
		}else{
			sql+=" and level in (0,1)";
		}
		if(pageNum==null||pageNum==0){
			pageNum = 1;
		}
		if(pageSize==null||pageSize==0){
			pageSize = 10;
		}
		sql+="  order by level desc,appDate desc limit "+(pageNum-1)*pageSize +","+pageSize;
		return findBySql(Usery.class,sql, null);
	}

	@Override
	public Long findChildNum(Integer useryId) {
		String sql = "select count(1) from usery where parentId = "+useryId;
		List list = findBySql(sql, null);
		if(list!=null&&list.size()>0){
			return Long.valueOf(list.get(0).toString());
		}
		return 0l;
	}

	@Override
	public Usery findByDianpuId(Integer dianpuId) {
		return findUniqueByProperty("dianPuId", dianpuId);
	}


}
