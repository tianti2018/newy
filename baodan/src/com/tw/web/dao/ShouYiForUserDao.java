package com.tw.web.dao;

import com.tw.web.hibernate.persistent.Hongbaoy;
import com.tw.web.hibernate.persistent.ShouYiForUser;

public interface ShouYiForUserDao extends ICRUDBaseDAO {

	Hongbaoy tongguo(ShouYiForUser shouYiForUser);

	String fahongbao(Hongbaoy hongbaoy);
	
	String weiXinFuKuan(Hongbaoy hongbaoy);
}
