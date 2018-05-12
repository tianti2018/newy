package com.tw.web.dao;

public interface RepeatDAO extends ICRUDBaseDAO {
	
	public boolean findRepeatByCrrentYM(Integer userId, String yyyyMM) throws Exception;
	
	public Double findTotalMoney(Integer userId);
}
