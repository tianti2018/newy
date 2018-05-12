package com.tw.web.dao;

import java.text.ParseException;
import java.util.List;

public interface OrdersDAO extends ICRUDBaseDAO {
	/**
	 * 总收入,已支付订单的钱数总和
	 * @return
	 */
	public Double zong_shou_ru(String j_fromDate,String j_endDate);

	/**
	 * 获取所有可提现金额
	 * @param userId
	 * @param string
	 * @param string2
	 * @return
	 */
	public List findLevelUserOrderMoney(Integer userId, String string,
			String string2);

	/**
	 * 获取本用户下所有订单金额
	 * @param userId
	 * @return
	 */
	public List findCurrUserAllOrder(Integer userId);

	/**
	 * 根据时间获取总利润
	 * @param j_fromDate
	 * @param j_endDate
	 * @return
	 * @throws ParseException 
	 */
	public Double zong_li_run(String j_fromDate, String j_endDate) throws ParseException;

	/**
	 * 根据时间获取总的应该支出金额
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ParseException 
	 */
	public Double zong_zhi_chu(String startDate, String endDate) throws ParseException ;
}
