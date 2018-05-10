package com.tw.web.dao;

import java.util.List;

import com.tw.web.hibernate.persistent.Card;
import com.tw.web.hibernate.persistent.EggActivity;

public interface EggActivityDAO extends ICRUDBaseDAO {
  public EggActivity findEggActivityById(Integer aid);	 
  
  public Card findCardByCardNo(String cardNo,String cardPassword);	 //找到没有失效的卡
  
  public void deleteChongfuCardNo();
  
  public List<Integer> findCardTopNoUse(Integer num);
  
  public void updateCardInCardNo(Integer userId,Integer num);
  
  public Card findCardByUserId(Integer userId);
}
