package com.tw.web.dao;

import java.util.List;

import com.tw.web.hibernate.persistent.AdminUser;
import com.tw.web.hibernate.persistent.Card;

public interface CardDAO extends ICRUDBaseDAO {
  public Card findCardByCardTypeAndCardStatus(String cardType);	  // 1:10元包月  2:120元包年  3:150元包15个月
  
  public Card findCardByCardNo(String cardNo,String cardPassword,String cardType);	 //找到没有失效的卡
  
  public void deleteChongfuCardNo();
  
  public List<Integer> findCardTopNoUse(Integer num);
  
  public void updateCardInCardNo(Integer userId,Integer num,String type,Integer loginUserId);
  
  public Card findCardByUserId(String cardType,Integer userId);
}
