package com.zklc.weishangcheng.member.service.impl;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.dao.FhRecordDao;
import com.zklc.weishangcheng.member.dao.JifenUserDao;
import com.zklc.weishangcheng.member.hibernate.persistent.FhRecord;
import com.zklc.weishangcheng.member.hibernate.persistent.JifenUser;
import com.zklc.weishangcheng.member.service.FhrecordService;
import com.zklc.weishangcheng.member.service.JifenUserService;
@Service
public class FhrecordServiceImpl extends BaseServiceImp<FhRecord, Integer> implements FhrecordService {
	
	@Autowired
	private JifenUserService jifenUserService;
	@Autowired
	private JifenUserDao jifenUserDao;
	@Autowired
	private FhRecordDao fhRecordDao;
	
	@Override
	public Double findTotalJiFenByUserId(Integer userId) {
		StringBuffer hql = new StringBuffer("select sum(t.fhmoney)from fhrecordy t where 1=1 ");
		if(userId!=null){
			hql.append(" and t.userId="+userId);
			List<Double> money =super.findByHql(hql.toString(), null);
			if (null!=money) {
				return null==money.get(0)?0:money.get(0);
			}
		}
		return 0.0;
	}

	@Override
	public Double findTotalJiFenOneByUserId(Integer userId, String fhType,String flag) {
		Double allMoney = 0.0;
		StringBuffer hql = new StringBuffer("select sum(t.fhmoney) from fhrecordy t where 1=1 ");
		if(userId!=null){
			hql.append(" and t.userId="+userId);
//			if(fhType!=null){
//				hql.append(" and t.fhType in("+fhType+")");	
//			}
			if (StringUtils.isNotEmpty(flag)) {
				hql.append(" and t.flag in("+flag+")");	
			}
			List<Double> money =super.findBySql(hql.toString(), null);
			if (null!=money) {
				allMoney+=( null==money.get(0)?0:money.get(0));
			}
			StringBuffer hql1 = new StringBuffer("select sum(t.fhmoney) from fhrecord t where 1=1 ");
			hql1.append(" and t.userId="+userId);
//			if(fhType!=null){
//				hql1.append(" and t.fhType in("+fhType+")");	
//			}
			if (StringUtils.isNotEmpty(flag)) {
				hql1.append(" and t.flag in("+flag+")");	
			}
			List<Double> money1 =super.findBySql(hql1.toString(), null);
			if (null!=money1) {
				allMoney+=( null==money1.get(0)?0:money1.get(0));
			}
		}
		return allMoney;
	}

	@Override
	public Double findTotalJiFenByOrders(Integer uoId) {
		StringBuffer hql = new StringBuffer("select sum(t.fhmoney)from fhrecordy t where 1=1 ");
		if(uoId!=null){
			hql.append(" and t.uoId="+uoId);
			hql.append(" and t.fhType=3");
			List<Double> money =super.findByHql(hql.toString(), null);
			if (null!=money) {
				return null==money.get(0)?0:money.get(0);
			}
		}
		return 0.0;
	}

	
//	//查找今天新增的会员
//	public Map<Integer,Integer[]> jishuanTodayAllUsers() throws ParseException {
//		List<JifenUser> users = jifenUserDao.findAllUserByNowDate();
//		Map<Integer,Integer[]> map = new HashMap<Integer, Integer[]>();
//		for (int i=0;i<users.size();i++) {
//			Integer count = users.get(i).getRealSubmitMoney()==null?0:users.get(i).getRealSubmitMoney()/660;
//			updateRecord(map,users.get(i),count);
//		}
//		map.size();
//		
//		for(Map.Entry<Integer, Integer[]> entry: map.entrySet()) { 
//			 Integer userId = entry.getKey();
//			 Integer submitMoneyOld = entry.getValue()[0];
//			 Integer realSubmitMoneyOld = entry.getValue()[1];
//		}
//		
//		return map;
//	}
	
//	public void updateRecord(Map<Integer,Integer[]> map,JifenUser user,Integer count) {
//		if (null==user.getParentId()) {
//			return;
//		}
//		JifenUser parent = jifenUserDao.findById(user.getParentId());
//		System.out.println(">>>>>>>> "+parent.getLoginName());
//		System.out.println(">>>>>>>> "+parent.getChild1());
//		if (user.getUserId().equals(parent.getChild1())) { //父亲的左区
//			Integer leftCount = count;
//			Integer rightCount = 0;
//			if (map.containsKey(parent.getUserId())) {
//				leftCount = (Integer)map.get(parent.getUserId())[0]+count;
//				rightCount = (Integer)map.get(parent.getUserId())[1];
//			}
//			map.put(parent.getUserId(), new Integer[]{leftCount,rightCount});
//			updateRecord(map,parent,count);
//		}
//		else if (user.getUserId().equals(parent.getChild2())){ //父亲的右区
//			Integer leftCount = 0;
//			Integer rightCount = count;
//			if (map.containsKey(parent.getUserId())) {
//				leftCount = (Integer)map.get(parent.getUserId())[0];
//				rightCount = (Integer)map.get(parent.getUserId())[1]+count;
//			}
//			map.put(parent.getUserId(), new Integer[]{leftCount,rightCount});
//			updateRecord(map,parent,count);
//		}
//	}

	@Override
	public List<Integer> getLeftOrRightUser(Integer key,
			Map<Integer, Integer[]> map, List<Integer> list, String flag) {
		// TODO Auto-generated method stub
		return null;
	}

}

