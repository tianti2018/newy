package com.tw.web.dao;


public interface TixianDAO extends ICRUDBaseDAO {
	
	/**
	 * 支出总和,所有提现审核通过的金额总和
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public Double zong_zhi_chu(String startDate,String endDate);
	
	/**
	 * 支出总和,所有提现审核通过的金额总和
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public Double zong_yi_ti(String startDate,String endDate);

	/**
	 * 计算已提现金额总和
	 * @param userId
	 * @param string
	 * @return
	 */
	public Double countTiXianMeth(Integer userId, String string);
}
