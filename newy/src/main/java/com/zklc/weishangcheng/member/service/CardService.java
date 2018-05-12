package com.zklc.weishangcheng.member.service;

import java.util.List;

import com.zklc.framework.hibernate.persistent.Pager;
import com.zklc.framework.service.IBaseService;
import com.zklc.weishangcheng.member.hibernate.persistent.Card;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;

public interface CardService extends IBaseService<Card, Integer> {
/**
 * 查询可用卡密
 * @param userId 用户id
 * @return
 */
public List findByUserId(Integer userId);

public List findNoUseCard(Integer num);

public Card findCardByCardStatus();

/**
 * 查询该用户有几张卡
 * @param userId
 * @return
 */
public Integer findUsedCardNumByUser(Integer userId);

/**
 * 查询用户的邀请码  不够的补发
 * @param user
 * @return
 */
public List<Card> listCards(Users user);
}