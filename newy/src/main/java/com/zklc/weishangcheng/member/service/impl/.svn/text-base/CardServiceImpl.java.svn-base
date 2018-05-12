package com.zklc.weishangcheng.member.service.impl;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.hibernate.persistent.Card;
import com.zklc.weishangcheng.member.hibernate.persistent.JifenUser;
import com.zklc.weishangcheng.member.service.CardService;
@Service
public class CardServiceImpl extends BaseServiceImp<Card, Integer> implements CardService {
	
	@Override
	public List findByUserId(Integer userId) {
		List list=null;
		StringBuffer hql = new StringBuffer("from Card t where 1=1 ");
		if(userId!=null&&!"".equals(userId)){
			hql.append(" and t.userid="+userId);
			hql.append(" and t.cardstatus=1");
			hql.append(" order by t.createDate desc");
			 list=super.findByHql(hql.toString(), null);
		}
		return list;
	}


	@Override
	public List findNoUseCard(Integer num) {
		String hql="select t.* from cardy t where t.cardStatus=0 AND t.userId is null limit "+num;
		return super.findBySql(Card.class,hql, null);
	}

	@Override
	public Card findCardByCardStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer findUsedCardNumByUser(Integer userId) {
		String sql="select count(cardId) from cardy t where t.cardStatus='1' AND t.userId = "+userId;
		List list = findBySql(sql, null);
		return (null!=list.get(0))?Integer.parseInt(list.get(0).toString()):0;
	}


	@Override
	public List<Card> listCards(JifenUser user) {
		List<Card> cardList = findByUserId(user.getUserId());
		Integer num = cardList.size();
		Integer needNum = 0;
		if(user.getLevel()!=null){
			if(user.getLevel().equals(1)){
				needNum = 1;
			}else if(user.getLevel().equals(2)){
				needNum=3;
			}else if(user.getLevel().equals(3)){
				needNum = 6;
			}
			if(needNum-num>0){
				List<Card> cards = findNoUseCard(needNum-num);
				if(cards.size()>0){
					for(Card c:cards){
						c.setUserid(user.getUserId());
						c.setCardstatus("1");
					}
				}
				saveOrUpdateAll(cards);
				cardList.addAll(cards);
			}
			
			return cardList;
		}
		return null;
	}


}

