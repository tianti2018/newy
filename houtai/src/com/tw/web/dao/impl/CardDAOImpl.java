package com.tw.web.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.tw.web.dao.CardDAO;
import com.tw.web.hibernate.persistent.AdminUser;
import com.tw.web.hibernate.persistent.Card;

@Repository(value="cardDAO")
public class CardDAOImpl extends CRUDBaseHibernateDAOImpl implements CardDAO {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getPojoClass() {
		return Card.class;
	}

	@Override
	public Card findCardByCardTypeAndCardStatus(String cardType) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("cardType", cardType));
		dc.add(Restrictions.eq("cardStatus", "0"));
		dc.add(Restrictions.isNull("userId"));
		
		List<Card> lit = getHibernateTemplate().findByCriteria(dc,0,1);
		if (lit.size()!=0) {
			return (Card)lit.get(0);
		}
		return null;
	}

	@Override
	public Card findCardByCardNo(String cardNo,String cardPassword,String cardType) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		if(cardType!=null&&!"".equals(cardType))
			dc.add(Restrictions.eq("cardType", cardType));
		dc.add(Restrictions.eq("cardNo", cardNo));
		dc.add(Restrictions.eq("cardPassword", cardPassword));
		dc.add(Restrictions.eq("cardStatus", "0"));
//		dc.addOrder(Order.d)
		List<Card> lit = getHibernateTemplate().findByCriteria(dc,0,1);
		if (lit.size()!=0) {
			return (Card)lit.get(0);
		}
		return null;
	}

	@Override
	public void deleteChongfuCardNo() {
		Query query = this.getSession().createSQLQuery("create table tmp as SELECT  cardNo from card where userId is null GROUP BY cardNo HAVING count(cardNo)>=2");
		query.executeUpdate();
		
		Query query1 = this.getSession().createSQLQuery("DELETE  from card where cardNo in (SELECT cardNo from tmp)");
		query1.executeUpdate();
		

		Query query2 = this.getSession().createSQLQuery("drop table tmp");
		query2.executeUpdate();
	}

	@Override
	public List<Integer> findCardTopNoUse(Integer num) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("cardStatus", "0"));
		dc.add(Restrictions.isNull("userId"));
		dc.setProjection(Projections.property("cardId"));
		List<Integer> lit = getHibernateTemplate().findByCriteria(dc,0,num);
		return lit;
	}

	@Override
	public void updateCardInCardNo(Integer userId,Integer num,String type,Integer loginUserId) {
		Query query = this.getSession().createSQLQuery("create table tmp as SELECT cardId from card where cardType='1' and cardStatus = '0' and userId is null LIMIT :num");
		query.setParameter("num", num);
		query.executeUpdate();
		
		Query query1 = this.getSession().createSQLQuery("update card set userId =:userId,createDate=:createDate,cardType=:cardType,memo=:memo where cardId in (select cardId from tmp)");
		query1.setParameter("userId", userId);
		query1.setParameter("createDate", new Date());
		query1.setParameter("cardType", "2");
		String memo = "1".equals(type)?"支付宝充值":"后台充值";
		query1.setParameter("memo", memo+"  登录会员编号【"+loginUserId+"】共充入：【"+num+"】张");
		query1.executeUpdate();
		
		Query query3 = this.getSession().createSQLQuery("drop table tmp");
		query3.executeUpdate();
	}

	@Override
	public Card findCardByUserId(String cardType,Integer userId) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		if(cardType!=null&&!"".equals(cardType))
			dc.add(Restrictions.eq("cardType", cardType));
		dc.add(Restrictions.eq("cardStatus", "0"));
		dc.add(Restrictions.eq("userId", userId));
		List<Card> lit = getHibernateTemplate().findByCriteria(dc,0,1);		
		if (lit.size()!=0) {
			return  lit.get(0);
		}
		return null;
	}
	
}