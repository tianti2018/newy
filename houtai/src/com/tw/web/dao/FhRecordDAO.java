package com.tw.web.dao;

import java.math.BigInteger;
import java.util.List;

import com.tw.web.hibernate.persistent.FhRecord;

public interface FhRecordDAO extends ICRUDBaseDAO {
	public double findleveData(Integer userId, String flag);
	
	public List<FhRecord> findAllFhRecordByUserId(Integer userId);
	
	public Double findTotalMoneyByUserId(Integer userId);
	
	public List<Integer> findAllUserAndNewData(); //查询当天所有分红的用户
	
	public Double findTotalMoneyByUserIdAndData(Integer userId);////查询会员当日收入
	
	public Double findTotalMoneyByDate();
	public Double findTotalMoney();
	
	public int findTotalCountSmall(BigInteger uoId);
	
	public int findNetUser(Integer userId);
	
	//查找所有的存值和转换的电子币
	public Double findAllBaodanBi(Integer userId);
	
	//查找所有的奖金所得
	public Double findAllJiangjing(Integer userId);
	
	//购买产品所得的积分
	public Double findTotalJiFenByGoumai(Integer userId); //每次购买产品赠送的300积分
	
	//排序序号产生的积分
	public Double findTotalJiFenByOrders(Integer uoId); //每次购买产品赠送的300积分
	
	//查询会员的总积分
	public Double findTotalJiFenByUserId(Integer userId);
	
	//汇总每个会员的当前总收入
	public List findTotalMoneyByFhType(Integer userId);
	
	//用户得到的所有积分
	public Double findTotalJiFenOneByUserId(Integer userId, String[] fhType);
	/**
	 * 查询积分记录
	 * @param userId 用户id
	 * @param dingqiFlag 注入15的标志
	 * @return
	 */
	public FhRecord findFhRecordByUidAndFid(Integer userId, String dingqiFlag);
}
