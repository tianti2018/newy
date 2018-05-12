package com.tw.web.dao;

import java.util.List;

import com.tw.web.hibernate.persistent.Card;

public interface EggPrizeDAO extends ICRUDBaseDAO {
	public List findHavLists(Integer aid);
}
