package com.zklc.weishangcheng.member.service;

import java.util.List;

import com.zklc.framework.service.IBaseService;
import com.zklc.weishangcheng.member.hibernate.persistent.Orders;
import com.zklc.weishangcheng.member.hibernate.persistent.ShouYiForUser;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.UserVo;

public interface ShouYiForUserService extends IBaseService<ShouYiForUser, Integer> {

	Double findYvE(Integer dianPuId);

	List<ShouYiForUser> findShouyiList(UserVo userVo, String orderType, Integer pageNum, String date1, String date2);

	Double findShenQing(Integer dianpuId);

	Double findYiTongGuo(Integer dianpuId);

	Double findShouRu(Integer dianpuId);

	Double findZhiChu(Integer dianpuId);

	Double findDaiShengXiao(Integer dianpuId);
	/**
	 * 查找收益和支出
	 * @param dianpuId 个人店铺id
	 * @param status	1待生效2已生效
	 * @param tixianStatus 1提现申请2通过申请
	 * @param shrzhch 0 收入,1支出
	 * @return
	 */
	Double findMoneyByStatusAndTixian(Integer dianpuId,Integer status,Integer tixianStatus,Integer shrzhch);

	Double findShouRu(Usery usery, String lingDianDateStringByDays, String lingDianDateStringByDays2);

	void deleteShouyiByOrders(List<Orders> deleteList);

	void updateShouyiStatusByOrders(List<Orders> shouyiOrders);

}
