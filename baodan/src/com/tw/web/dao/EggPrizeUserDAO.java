package com.tw.web.dao;

import java.util.List;

import com.tw.web.hibernate.persistent.Card;

public interface EggPrizeUserDAO extends ICRUDBaseDAO {
  
  public List<Integer> findListByAidAndUid(Integer aid,Integer uid);
  
  public List topNumList(Integer maxNum);
  public Card findCardByUserId(Integer userId);
}
