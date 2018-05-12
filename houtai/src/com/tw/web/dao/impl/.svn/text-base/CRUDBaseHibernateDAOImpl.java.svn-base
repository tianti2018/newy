/*
 * ----------------------------------------------------------
 * FILE         : CRUDBaseHibernateDAOImpl
 * CREATEUSER   : Anston
 * CREATEDATE   : 2008/03/17
 * FILENAME     : CRUDBaseHibernateDAOImpl.java
 * DESCRIPTION  : 
 * MODIFIES     : 
 * MODIFIER     : 
 * MODIFIEDDATE : 
 * COMMENT      : 
 * ----------------------------------------------------------
 */

package com.tw.web.dao.impl;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.Type;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.tw.web.dao.ICRUDBaseDAO;
import com.tw.web.util.CommUtils;

@Repository
public abstract class CRUDBaseHibernateDAOImpl extends TwHibernateDaoSupport
		implements ICRUDBaseDAO {
	public final static String ORDER_BY_ASC = "ORDER_BY_ASC";
	public final static String ORDER_BY_DESC = "ORDER_BY_DESC";

	/**
	 * 
	 */
	public CRUDBaseHibernateDAOImpl() {

		super();
	}

	/**
	 * @param entity
	 * @return
	 */
	public Serializable create(Serializable entity) {
		try {
			getHibernateTemplate().save(entity);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return getHibernateTemplate().save(entity);
	}

	/**
	 * @param entity
	 */
	public int update(Serializable entity) {

		getHibernateTemplate().update(entity);
		return 0;
	}

	public int saveOrUpdate(Serializable entity) {
		getHibernateTemplate().saveOrUpdate(entity);
		return 0;
	}

	/**
	 * @param entity
	 */
	public int delete(Serializable entity) {

		getHibernateTemplate().delete(entity);
		return 0;
	}

	/**
	 * @param id
	 */
	public int deleteById(Serializable id) {
		int i = 0;
		getHibernateTemplate().delete(findById(id));
		i++;
		return i;
	}

	public int deleteByProperty(String propertyName, Serializable pValue) {
		int i = 0;
		List list = this.findEntityByPropertiName(propertyName, pValue);
		for (i = 0; i < list.size(); i++) {
			getHibernateTemplate().delete(list.get(i));
		}
		return i;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vs.data.access.CRUDBaseDAO#findById(java.io.Serializable)
	 */
	public Serializable findById(Serializable id) {

		return (Serializable) getHibernateTemplate().get(getPojoClass(), id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vs.data.access.CRUDBaseDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List findAll() {

		return getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(getPojoClass()));
	}

	protected abstract Class getPojoClass();

	/**
	 * @param flag
	 *            是 分页的 标志 栏位 "page"代表 要 分页 "all"表示 不分页 主要是 求 总数
	 */
	@SuppressWarnings("unchecked")
	public List findAllPagerList(final String propertyName,
			final boolean isAsc, final int firstResult, final int maxResults,
			final String flag) {

		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		if (StringUtils.isNotEmpty(propertyName)) {
			if (isAsc) {
				dc.addOrder(Order.asc(propertyName));

			} else {
				dc.addOrder(Order.desc(propertyName));
			}
		}

		if ("page".equals(flag)) {

			return getHibernateTemplate().findByCriteria(dc, firstResult,
					maxResults);
		} else if ("all".equals(flag)) {
			return getHibernateTemplate().findByCriteria(dc);
		}

		return null;

	}

	@SuppressWarnings("unchecked")
	public List findEntityByPropertiName(String propertyName,
			Serializable pValue) {

		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq(propertyName, pValue));

		return getHibernateTemplate().findByCriteria(dc);
	}

	@SuppressWarnings("unchecked")
	public List findEntityByPropertiName(DetachedCriteria dc,
			Map properties,
			Map<String, Integer> compare,
			Map<String, Boolean> isAscProperties, int firstResult,
			int maxResults, String flag) {

		if (dc == null) {
			dc = DetachedCriteria.forClass(getPojoClass());
		}

		//条件和值
		if(properties!=null&&properties.size()>0){
			 Set<Map.Entry> set=properties.entrySet();
			 
			 Set<Map.Entry<String,Integer>> set_compare=compare.entrySet();
			 			 
			 for(Map.Entry entry:set){
				 //System.out.println(">>>>>>>>>> 开始  ");
				 String propertyName = String.valueOf(entry.getKey());
				 //String proertyVale = String.valueOf(entry.getValue());
				 System.out.println(">>>>>>>>　>　entry.getValue() instanceof String "+(entry.getValue() instanceof String));
				 if (entry.getValue() instanceof String) {
					 System.out.println(">>>>>>>> entry.getValue().toString().length() "+entry.getValue().toString().length());
					 if (entry.getValue().toString().length() == 0) {
						 continue;
					 }
				 }
				 System.out.println(">>>>>>>>>> 下一步  entry.getValue() "+entry.getValue());
				 if (entry.getValue() != null && entry.getValue()!="") {
					 int opertor = 0;
					 for (Map.Entry<String, Integer> compareOne:set_compare) {
						 if (compareOne.getKey().equals(propertyName)) {				
							 opertor  = compareOne.getValue().intValue();
							 break;
						 }
					 }
					 System.out.println("opertor>>>>>>>>>>>>>>>  "+opertor);
					 switch (opertor) {
						 case 0: dc.add(Restrictions.eq(propertyName, entry.getValue())); //0 = eq
										break;
						 case 1: dc.add(Restrictions.ne(propertyName, entry.getValue())); // 1=noteq
										break;
						 case 2: dc.add(Restrictions.like(propertyName, "%"+entry.getValue()+"%", MatchMode.ANYWHERE)); // 2=like
										break;
						 case 3: dc.add(Restrictions.ilike(propertyName, "%"+entry.getValue()+"%", MatchMode.ANYWHERE)); //3=ilike
										break;
						 case 4: dc.add(Restrictions.in(String.valueOf(entry.getKey()), (Object[])entry.getValue())); //4=in	
										break;
						 case 5: dc.add(Restrictions.not(Restrictions.in(propertyName, (Object[])entry.getValue()))); //5=not in
										break;
						 case 6: dc.add(Restrictions.gt(propertyName, entry.getValue())); //6=gt
										break;
						 case 7: dc.add(Restrictions.lt(propertyName, entry.getValue())); //7=lt
										break;
						 case 8: dc.add(Restrictions.ge(propertyName, entry.getValue())); //8=ge
										break;
						 case 9: dc.add(Restrictions.le(propertyName, entry.getValue())); //9=le
										break;
						 case 10: dc.add(Restrictions.between(propertyName, ((Object[])entry.getValue())[0], ((Object[])entry.getValue())[1])); //10=between
										break;
					 }
				 }								
			 } 
		 }
		if (isAscProperties != null && isAscProperties.size() > 0) {
			Set<Map.Entry<String, Boolean>> set = isAscProperties.entrySet();
			for (Map.Entry<String, Boolean> entry : set) {
				if (entry.getValue()) {
					dc.addOrder(Order.asc(entry.getKey()));
				} else {
					dc.addOrder(Order.desc(entry.getKey()));
				}
			}
		}

		if (flag.equals("page"))
			return getHibernateTemplate().findByCriteria(dc, firstResult,
					maxResults);
		else if (flag.equals("all"))
			return getHibernateTemplate().findByCriteria(dc);
		return null;
	}
	
	public List findAllByProperty(Map conditionProperties,Map<String,Integer> compare) {
		DetachedCriteria  dc = DetachedCriteria.forClass(getPojoClass());
		//条件和值
		if(conditionProperties!=null&&conditionProperties.size()>0){
			 Set<Map.Entry> set=conditionProperties.entrySet();
			 
			 Set<Map.Entry<String,Integer>> set_compare=compare.entrySet();
			 			 
			 for(Map.Entry entry:set){
				 
				 String propertyName = String.valueOf(entry.getKey());
				 String proertyVale = String.valueOf(entry.getValue());
				 if (entry.getValue() instanceof String) {
					 if (entry.getValue().toString().length() == 0) {
						 continue;
					 }
				 }
				 if (entry.getValue() != null && entry.getValue()!="") {
					 int opertor = 0;
					 for (Map.Entry<String, Integer> compareOne:set_compare) {
						 if (compareOne.getKey().equals(propertyName)) {				
							 opertor  = compareOne.getValue().intValue();
							 break;
						 }
					 }				
					 switch (opertor) {
						 case 0: dc.add(Restrictions.eq(propertyName, entry.getValue())); //0 = eq
										break;
						 case 1: dc.add(Restrictions.ne(propertyName, entry.getValue())); // 1=noteq
										break;
						 case 2: dc.add(Restrictions.like(propertyName, "%"+entry.getValue()+"%", MatchMode.ANYWHERE)); // 2=like
										break;
						 case 3: dc.add(Restrictions.ilike(propertyName, "%"+entry.getValue()+"%", MatchMode.ANYWHERE)); //3=ilike
										break;
						 case 4: dc.add(Restrictions.in(String.valueOf(entry.getKey()), (Object[])entry.getValue())); //4=in	
										break;
						 case 5: dc.add(Restrictions.not(Restrictions.in(propertyName, (Object[])entry.getValue()))); //5=not in
										break;
						 case 6: dc.add(Restrictions.gt(propertyName, entry.getValue())); //6=gt
										break;
						 case 7: dc.add(Restrictions.lt(propertyName, entry.getValue())); //7=lt
										break;
						 case 8: dc.add(Restrictions.ge(propertyName, entry.getValue())); //8=ge
										break;
						 case 9: dc.add(Restrictions.le(propertyName, entry.getValue())); //9=le
										break;
						 case 10: dc.add(Restrictions.between(propertyName, ((Object[])entry.getValue())[0], ((Object[])entry.getValue())[1])); //10=between
										break;
					 }
				 }								
			 } 
		 }
				
		return getHibernateTemplate().findByCriteria(dc);
		
	}
	
	@SuppressWarnings("unchecked")
	public int cout_size(Map conditionProperties,Map<String,Integer> compare) {
		DetachedCriteria  dc = DetachedCriteria.forClass(getPojoClass());
		//条件和值
		if(conditionProperties!=null&&conditionProperties.size()>0){
			 Set<Map.Entry> set=conditionProperties.entrySet();
			 
			 Set<Map.Entry<String,Integer>> set_compare=compare.entrySet();
			 			 
			 for(Map.Entry entry:set){
				 
				 String propertyName = String.valueOf(entry.getKey());
				 String proertyVale = String.valueOf(entry.getValue());
				 if (entry.getValue() instanceof String) {
					 if (entry.getValue().toString().length() == 0) {
						 continue;
					 }
				 }
				 if (entry.getValue() != null && entry.getValue()!="") {
					 int opertor = 0;
					 for (Map.Entry<String, Integer> compareOne:set_compare) {
						 if (compareOne.getKey().equals(propertyName)) {				
							 opertor  = compareOne.getValue().intValue();
							 break;
						 }
					 }				
					 switch (opertor) {
						 case 0: dc.add(Restrictions.eq(propertyName, entry.getValue())); //0 = eq
										break;
						 case 1: dc.add(Restrictions.ne(propertyName, entry.getValue())); // 1=noteq
										break;
						 case 2: dc.add(Restrictions.like(propertyName, "%"+entry.getValue()+"%", MatchMode.ANYWHERE)); // 2=like
										break;
						 case 3: dc.add(Restrictions.ilike(propertyName, "%"+entry.getValue()+"%", MatchMode.ANYWHERE)); //3=ilike
										break;
						 case 4: dc.add(Restrictions.in(String.valueOf(entry.getKey()), (Object[])entry.getValue())); //4=in	
										break;
						 case 5: dc.add(Restrictions.not(Restrictions.in(propertyName, (Object[])entry.getValue()))); //5=not in
										break;
						 case 6: dc.add(Restrictions.gt(propertyName, entry.getValue())); //6=gt
										break;
						 case 7: dc.add(Restrictions.lt(propertyName, entry.getValue())); //7=lt
										break;
						 case 8: dc.add(Restrictions.ge(propertyName, entry.getValue())); //8=ge
										break;
						 case 9: dc.add(Restrictions.le(propertyName, entry.getValue())); //9=le
										break;
						 case 10: dc.add(Restrictions.between(propertyName, ((Object[])entry.getValue())[0], ((Object[])entry.getValue())[1])); //10=between
										break;
						 case 11: dc.add(Restrictions.or(Restrictions.eq(propertyName, entry.getValue()), Restrictions.isNull(propertyName))); break;
						 case 12: dc.add(Restrictions.or(Restrictions.ne(propertyName, entry.getValue()),Restrictions.isNotNull(propertyName))); break;
						 case 13:{
							 dc.createAlias("referrer", "r");
							 dc.add(Restrictions.eq(propertyName, entry.getValue())); //0 = eq
							 break;
						 }
						 case 14:{
							 dc.createAlias("referrer", "r");
							 dc.add(Restrictions.like(propertyName, "%"+entry.getValue()+"%", MatchMode.ANYWHERE));
							 break;
						 }
					 }
				 }								
			 } 
		 }
				
		 dc.setProjection(Projections.rowCount());
			
		 List<Integer> list = new ArrayList<Integer>();	
		 list = getHibernateTemplate().findByCriteria(dc);
		 int size = list.size() == 0 ? 0:list.get(0).intValue();
				
		 return size;				
	}
	
	@SuppressWarnings("unchecked")
	public int cout_size_Commen(Map conditionProperties,Map<String,Integer> compare) {
		DetachedCriteria  dc = DetachedCriteria.forClass(getPojoClass());
		//条件和值
		if(conditionProperties!=null&&conditionProperties.size()>0){
			 Set<Map.Entry> set=conditionProperties.entrySet();
			 
			 Set<Map.Entry<String,Integer>> set_compare=compare.entrySet();
			 			 
			 for(Map.Entry entry:set){
				 
				 String propertyName = String.valueOf(entry.getKey());
				 String proertyVale = String.valueOf(entry.getValue());
				 if (entry.getValue() instanceof String) {
					 if (entry.getValue().toString().length() == 0) {
						 continue;
					 }
				 }
				 if (entry.getValue() != null && entry.getValue()!="") {
					 int opertor = 0;
					 for (Map.Entry<String, Integer> compareOne:set_compare) {
						 if (compareOne.getKey().equals(propertyName)) {				
							 opertor  = compareOne.getValue().intValue();
							 break;
						 }
					 }				
					 switch (opertor) {
						 case 0: dc.add(Restrictions.eq(propertyName, entry.getValue())); //0 = eq
										break;
						 case 1: dc.add(Restrictions.ne(propertyName, entry.getValue())); // 1=noteq
										break;
						 case 2: dc.add(Restrictions.like(propertyName, "%"+entry.getValue()+"%", MatchMode.ANYWHERE)); // 2=like
										break;
						 case 3: dc.add(Restrictions.ilike(propertyName, "%"+entry.getValue()+"%", MatchMode.ANYWHERE)); //3=ilike
										break;
						 case 4: dc.add(Restrictions.in(String.valueOf(entry.getKey()), (Object[])entry.getValue())); //4=in	
										break;
						 case 5: dc.add(Restrictions.not(Restrictions.in(propertyName, (Object[])entry.getValue()))); //5=not in
										break;
						 case 6: dc.add(Restrictions.gt(propertyName, entry.getValue())); //6=gt
										break;
						 case 7: dc.add(Restrictions.lt(propertyName, entry.getValue())); //7=lt
										break;
						 case 8: dc.add(Restrictions.ge(propertyName, entry.getValue())); //8=ge
										break;
						 case 9: dc.add(Restrictions.le(propertyName, entry.getValue())); //9=le
										break;
						 case 10: dc.add(Restrictions.between(propertyName, ((Object[])entry.getValue())[0], ((Object[])entry.getValue())[1])); //10=between
										break;
						 case 11: dc.add(Restrictions.or(Restrictions.eq(propertyName, entry.getValue()), Restrictions.isNull(propertyName))); break;
						 case 12: dc.add(Restrictions.or(Restrictions.ne(propertyName, entry.getValue()),Restrictions.isNotNull(propertyName))); break;
						 case 13: {
							 dc.add(Restrictions.like(propertyName,"%"+entry.getValue()+"%", MatchMode.ANYWHERE));
						 			
						 	};
					 }
				 }								
			 } 
		 }
				
		 dc.setProjection(Projections.rowCount());
			
		 List<Integer> list = new ArrayList<Integer>();	
		 list = getHibernateTemplate().findByCriteria(dc);
		 int size = list.size() == 0 ? 0:list.get(0).intValue();
				
		 return size;				
	}
	public int cout_size1(Map conditionProperties,Map<String,Integer> compare) {
		DetachedCriteria  dc = DetachedCriteria.forClass(getPojoClass());
		//条件和值
		if(conditionProperties!=null&&conditionProperties.size()>0){
			 Set<Map.Entry> set=conditionProperties.entrySet();
			 
			 Set<Map.Entry<String,Integer>> set_compare=compare.entrySet();
			 			 
			 for(Map.Entry entry:set){
				 
				 String propertyName = String.valueOf(entry.getKey());
				 String proertyVale = String.valueOf(entry.getValue());
				 if (entry.getValue() instanceof String) {
					 if (entry.getValue().toString().length() == 0) {
						 continue;
					 }
				 }
				 if (entry.getValue() != null && entry.getValue()!="") {
					 int opertor = 0;
					 for (Map.Entry<String, Integer> compareOne:set_compare) {
						 if (compareOne.getKey().equals(propertyName)) {				
							 opertor  = compareOne.getValue().intValue();
							 break;
						 }
					 }				
					 switch (opertor) {
						 case 0: dc.add(Restrictions.eq(propertyName, entry.getValue())); //0 = eq
										break;
						 case 1: dc.add(Restrictions.ne(propertyName, entry.getValue())); // 1=noteq
										break;
						 case 2: dc.add(Restrictions.like(propertyName, "%"+entry.getValue()+"%", MatchMode.ANYWHERE)); // 2=like
										break;
						 case 3: dc.add(Restrictions.ilike(propertyName, "%"+entry.getValue()+"%", MatchMode.ANYWHERE)); //3=ilike
										break;
						 case 4: dc.add(Restrictions.in(String.valueOf(entry.getKey()), (Object[])entry.getValue())); //4=in	
										break;
						 case 5: dc.add(Restrictions.not(Restrictions.in(propertyName, (Object[])entry.getValue()))); //5=not in
										break;
						 case 6: dc.add(Restrictions.gt(propertyName, entry.getValue())); //6=gt
										break;
						 case 7: dc.add(Restrictions.lt(propertyName, entry.getValue())); //7=lt
										break;
						 case 8: dc.add(Restrictions.ge(propertyName, entry.getValue())); //8=ge
										break;
						 case 9: dc.add(Restrictions.le(propertyName, entry.getValue())); //9=le
										break;
						 case 10: dc.add(Restrictions.between(propertyName, ((Object[])entry.getValue())[0], ((Object[])entry.getValue())[1])); //10=between
										break;
						 case 11: dc.add(Restrictions.or(Restrictions.eq(propertyName, entry.getValue()), Restrictions.isNull(propertyName))); break;
						 case 12: dc.add(Restrictions.or(Restrictions.ne(propertyName, entry.getValue()),Restrictions.isNotNull(propertyName))); break;
						 
						 case 13:{
							 dc.createAlias("user", "t");
							 dc.add(Restrictions.eq(propertyName, entry.getValue())); //0 = eq
							 break;
						 }
						 case 14:{
							 dc.createAlias("user", "t");
							 dc.add(Restrictions.like(propertyName, "%"+entry.getValue()+"%", MatchMode.ANYWHERE));
							 break;
						 }
					 }
				 }								
			 } 
		 }
				
		 dc.setProjection(Projections.rowCount());
			
		 List<Integer> list = new ArrayList<Integer>();	
		 list = getHibernateTemplate().findByCriteria(dc);
		 int size = list.size() == 0 ? 0:list.get(0).intValue();
				
		 return size;				
	}
	
	public List findAllPagerList_new(Map<String,Object> conditionProperties,Map<String,Integer> compare,final int firstResult,final int maxResults, final String flag) {
		DetachedCriteria  dc = DetachedCriteria.forClass(getPojoClass());
		//条件和值
		if(conditionProperties!=null&&conditionProperties.size()>0){
			 Set<Entry<String, Object>> set=conditionProperties.entrySet();
			 
			 Set<Map.Entry<String,Integer>> set_compare=compare.entrySet();
			 			 
			 for(Map.Entry entry:set){
				 
				 String propertyName = String.valueOf(entry.getKey());
				 String proertyVale = String.valueOf(entry.getValue());
				 if (entry.getValue() instanceof String) {
					 if (entry.getValue().toString().length() == 0) {
						 continue;
					 }
				 }
				 if (entry.getValue() != null && entry.getValue()!="") {
					 int opertor = 0;
					 for (Map.Entry<String, Integer> compareOne:set_compare) {
						 if (compareOne.getKey().equals(propertyName)) {				
							 opertor  = compareOne.getValue().intValue();
							 break;
						 }
					 }				
					 switch (opertor) {
						 case 0: dc.add(Restrictions.eq(propertyName, entry.getValue())); //0 = eq
										break;
						 case 1: dc.add(Restrictions.ne(propertyName, entry.getValue())); // 1=noteq
										break;
						 case 2: dc.add(Restrictions.like(propertyName, "%"+entry.getValue()+"%", MatchMode.ANYWHERE)); // 2=like
										break;
						 case 3: dc.add(Restrictions.ilike(propertyName, "%"+entry.getValue()+"%", MatchMode.ANYWHERE)); //3=ilike
										break;
						 case 4: dc.add(Restrictions.in(String.valueOf(entry.getKey()), (Object[])entry.getValue())); //4=in	
										break;
						 case 5: dc.add(Restrictions.not(Restrictions.in(propertyName, (Object[])entry.getValue()))); //5=not in
										break;
						 case 6: dc.add(Restrictions.gt(propertyName, entry.getValue())); //6=gt
										break;
						 case 7: dc.add(Restrictions.lt(propertyName, entry.getValue())); //7=lt
										break;
						 case 8: dc.add(Restrictions.ge(propertyName, entry.getValue())); //8=ge
										break;
						 case 9: dc.add(Restrictions.le(propertyName, entry.getValue())); //9=le
										break;
						 case 10: dc.add(Restrictions.between(propertyName, ((Object[])entry.getValue())[0], ((Object[])entry.getValue())[1])); //10=between
										break;
						 case 11: dc.add(Restrictions.or(Restrictions.eq(propertyName, entry.getValue()), Restrictions.isNull(propertyName))); break;

					 }
				 }								
			 } 
		 }
		
		if ("page".equals(flag)) {
			return getHibernateTemplate().findByCriteria(dc, firstResult,maxResults);
		} 
		else if ("all".equals(flag)) {
			return getHibernateTemplate().findByCriteria(dc);
		}

		return null;
	}
	
	public List findAllPagerList(Map<String,Object> conditionProperties,Map<String,Integer> compare,Map<String,Boolean> sort,final int firstResult,final int maxResults, final String flag) {
		DetachedCriteria  dc = DetachedCriteria.forClass(getPojoClass());
		//条件和值
		if(conditionProperties!=null&&conditionProperties.size()>0){
			 Set<Entry<String, Object>> set=conditionProperties.entrySet();
			 
			 Set<Map.Entry<String,Integer>> set_compare=compare.entrySet();
			 			 
			 for(Map.Entry entry:set){
				 
				 String propertyName = String.valueOf(entry.getKey());
				 if (entry.getValue() instanceof String) {
					 if (entry.getValue().toString().length() == 0) {
						 continue;
					 }
				 }
				 if (entry.getValue() != null && entry.getValue()!="") {
					 int opertor = 0;
					 for (Map.Entry<String, Integer> compareOne:set_compare) {
						 if (compareOne.getKey().equals(propertyName)) {				
							 opertor  = compareOne.getValue().intValue();
							 break;
						 }
					 }
					 switch (opertor) {
						 case 0: dc.add(Restrictions.eq(propertyName, entry.getValue())); //0 = eq
										break;
						 case 1: dc.add(Restrictions.ne(propertyName, entry.getValue())); // 1=noteq
										break;
						 case 2: dc.add(Restrictions.like(propertyName, "%"+entry.getValue()+"%", MatchMode.ANYWHERE)); // 2=like
										break;
						 case 3: dc.add(Restrictions.ilike(propertyName, "%"+entry.getValue()+"%", MatchMode.ANYWHERE)); //3=ilike
										break;
						 case 4: dc.add(Restrictions.in(String.valueOf(entry.getKey()), (Object[])entry.getValue())); //4=in	
										break;
						 case 5: dc.add(Restrictions.not(Restrictions.in(propertyName, (Object[])entry.getValue()))); //5=not in
										break;
						 case 6: dc.add(Restrictions.gt(propertyName, entry.getValue())); //6=gt
										break;
						 case 7: dc.add(Restrictions.lt(propertyName, entry.getValue())); //7=lt
										break;
						 case 8: dc.add(Restrictions.ge(propertyName, entry.getValue())); //8=ge
										break;
						 case 9: dc.add(Restrictions.le(propertyName, entry.getValue())); //9=le
										break;
						 case 10: dc.add(Restrictions.between(propertyName, ((Object[])entry.getValue())[0], ((Object[])entry.getValue())[1])); //10=between
										break;
						 case 11: dc.add(Restrictions.or(Restrictions.eq(propertyName, entry.getValue()), Restrictions.isNull(propertyName))); break;
						 case 12: dc.add(Restrictions.or(Restrictions.ne(propertyName, entry.getValue()),Restrictions.isNotNull(propertyName))); break;
						 case 13: {
							 dc.add(Restrictions.like(propertyName,"%"+entry.getValue()+"%", MatchMode.ANYWHERE));
						 	};
					 }
					 
				 }								
			 } 
		 }
		//排序
		if(sort!=null&&sort.size()>0){
			Set<Entry<String, Boolean>> setSort=sort.entrySet();
			for (Map.Entry<String, Boolean> entry:setSort) {
				String propertyName = entry.getKey();
				boolean proertyVale = entry.getValue();
				if (StringUtils.isNotEmpty(propertyName)) {
					if (proertyVale) {
						dc.addOrder(Order.asc(propertyName));
					}
					else {
						dc.addOrder(Order.desc(propertyName));
					}
				}
			}
			
		}
		
		
		try {
			if ("page".equals(flag)) {
				return getHibernateTemplate().findByCriteria(dc, firstResult,maxResults);
			} 
			else if ("all".equals(flag)) {
				return getHibernateTemplate().findByCriteria(dc);
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return null;
	}
	public List findAllPagerList_new(Map<String,Object> conditionProperties,Map<String,Integer> compare,Map<String,Boolean> sort,final int firstResult,final int maxResults, final String flag) {
		DetachedCriteria  dc = DetachedCriteria.forClass(getPojoClass());
		//条件和值
		if(conditionProperties!=null&&conditionProperties.size()>0){
			 Set<Entry<String, Object>> set=conditionProperties.entrySet();
			 
			 Set<Map.Entry<String,Integer>> set_compare=compare.entrySet();
			 			 
			 for(Map.Entry entry:set){
				 
				 String propertyName = String.valueOf(entry.getKey());
				 String proertyVale = String.valueOf(entry.getValue());
				 if (entry.getValue() instanceof String) {
					 if (entry.getValue().toString().length() == 0) {
						 continue;
					 }
				 }
				 if (entry.getValue() != null && entry.getValue()!="") {
					 int opertor = 0;
					 for (Map.Entry<String, Integer> compareOne:set_compare) {
						 if (compareOne.getKey().equals(propertyName)) {				
							 opertor  = compareOne.getValue().intValue();
							 break;
						 }
					 }
					 switch (opertor) {
						 case 0: dc.add(Restrictions.eq(propertyName, entry.getValue())); //0 = eq
										break;
						 case 1: dc.add(Restrictions.ne(propertyName, entry.getValue())); // 1=noteq
										break;
						 case 2: dc.add(Restrictions.like(propertyName, "%"+entry.getValue()+"%", MatchMode.ANYWHERE)); // 2=like
										break;
						 case 3: dc.add(Restrictions.ilike(propertyName, "%"+entry.getValue()+"%", MatchMode.ANYWHERE)); //3=ilike
										break;
						 case 4: dc.add(Restrictions.in(String.valueOf(entry.getKey()), (Object[])entry.getValue())); //4=in	
										break;
						 case 5: dc.add(Restrictions.not(Restrictions.in(propertyName, (Object[])entry.getValue()))); //5=not in
										break;
						 case 6: dc.add(Restrictions.gt(propertyName, entry.getValue())); //6=gt
										break;
						 case 7: dc.add(Restrictions.lt(propertyName, entry.getValue())); //7=lt
										break;
						 case 8: dc.add(Restrictions.ge(propertyName, entry.getValue())); //8=ge
										break;
						 case 9: dc.add(Restrictions.le(propertyName, entry.getValue())); //9=le
										break;
						 case 10: dc.add(Restrictions.between(propertyName, ((Object[])entry.getValue())[0], ((Object[])entry.getValue())[1])); //10=between
										break;
						 case 11: dc.add(Restrictions.or(Restrictions.eq(propertyName, entry.getValue()), Restrictions.isNull(propertyName))); break;
						 case 12: dc.add(Restrictions.or(Restrictions.ne(propertyName, entry.getValue()),Restrictions.isNotNull(propertyName))); break;
						 case 13:{
							 dc.createAlias("referrer", "r");
							 dc.add(Restrictions.eq(propertyName, entry.getValue())); //0 = eq
							 break;
						 }
						 case 14:{
							 dc.createAlias("referrer", "r");
							 dc.add(Restrictions.like(propertyName, "%"+entry.getValue()+"%", MatchMode.ANYWHERE));
							 break;
						 }
					 }
					 
				 }								
			 } 
		 }
		//排序
		if(sort!=null&&sort.size()>0){
			Set<Entry<String, Boolean>> setSort=sort.entrySet();
			for (Map.Entry<String, Boolean> entry:setSort) {
				String propertyName = entry.getKey();
				boolean proertyVale = entry.getValue();
				if (StringUtils.isNotEmpty(propertyName)) {
					if (proertyVale) {
						dc.addOrder(Order.asc(propertyName));
					}
					else {
						dc.addOrder(Order.desc(propertyName));
					}
				}
			}
			
		}
		
		
		try {
			if ("page".equals(flag)) {
				return getHibernateTemplate().findByCriteria(dc, firstResult,maxResults);
			} 
			else if ("all".equals(flag)) {
				return getHibernateTemplate().findByCriteria(dc);
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return null;
	}
	public List findAllPagerList_new1(Map<String,Object> conditionProperties,Map<String,Integer> compare,Map<String,Boolean> sort,final int firstResult,final int maxResults, final String flag) {
		DetachedCriteria  dc = DetachedCriteria.forClass(getPojoClass());
		//条件和值
		if(conditionProperties!=null&&conditionProperties.size()>0){
			 Set<Entry<String, Object>> set=conditionProperties.entrySet();
			 
			 Set<Map.Entry<String,Integer>> set_compare=compare.entrySet();
			 			 
			 for(Map.Entry entry:set){
				 
				 String propertyName = String.valueOf(entry.getKey());
				 String proertyVale = String.valueOf(entry.getValue());
				 if (entry.getValue() instanceof String) {
					 if (entry.getValue().toString().length() == 0) {
						 continue;
					 }
				 }
				 if (entry.getValue() != null && entry.getValue()!="") {
					 int opertor = 0;
					 for (Map.Entry<String, Integer> compareOne:set_compare) {
						 if (compareOne.getKey().equals(propertyName)) {				
							 opertor  = compareOne.getValue().intValue();
							 break;
						 }
					 }
					 switch (opertor) {
						 case 0: dc.add(Restrictions.eq(propertyName, entry.getValue())); //0 = eq
										break;
						 case 1: dc.add(Restrictions.ne(propertyName, entry.getValue())); // 1=noteq
										break;
						 case 2: dc.add(Restrictions.like(propertyName, "%"+entry.getValue()+"%", MatchMode.ANYWHERE)); // 2=like
										break;
						 case 3: dc.add(Restrictions.ilike(propertyName, "%"+entry.getValue()+"%", MatchMode.ANYWHERE)); //3=ilike
										break;
						 case 4: dc.add(Restrictions.in(String.valueOf(entry.getKey()), (Object[])entry.getValue())); //4=in	
										break;
						 case 5: dc.add(Restrictions.not(Restrictions.in(propertyName, (Object[])entry.getValue()))); //5=not in
										break;
						 case 6: dc.add(Restrictions.gt(propertyName, entry.getValue())); //6=gt
										break;
						 case 7: dc.add(Restrictions.lt(propertyName, entry.getValue())); //7=lt
										break;
						 case 8: dc.add(Restrictions.ge(propertyName, entry.getValue())); //8=ge
										break;
						 case 9: dc.add(Restrictions.le(propertyName, entry.getValue())); //9=le
										break;
						 case 10: dc.add(Restrictions.between(propertyName, ((Object[])entry.getValue())[0], ((Object[])entry.getValue())[1])); //10=between
										break;
						 case 11: dc.add(Restrictions.or(Restrictions.eq(propertyName, entry.getValue()), Restrictions.isNull(propertyName))); break;
						 case 12: dc.add(Restrictions.or(Restrictions.ne(propertyName, entry.getValue()),Restrictions.isNotNull(propertyName))); break;
						 case 13:{
							 dc.createAlias("user", "t");
							 dc.add(Restrictions.eq(propertyName, entry.getValue())); //0 = eq
							 break;
						 }
						 case 14:{
							 dc.createAlias("user", "t");
							 dc.add(Restrictions.like(propertyName, "%"+entry.getValue()+"%", MatchMode.ANYWHERE));
							 break;
						 }
					 }
				 }								
			 } 
		 }
		//排序
		if(sort!=null&&sort.size()>0){
			Set<Entry<String, Boolean>> setSort=sort.entrySet();
			for (Map.Entry<String, Boolean> entry:setSort) {
				String propertyName = entry.getKey();
				boolean proertyVale = entry.getValue();
				if (StringUtils.isNotEmpty(propertyName)) {
					if (proertyVale) {
						dc.addOrder(Order.asc(propertyName));
					}
					else {
						dc.addOrder(Order.desc(propertyName));
					}
				}
			}
			
		}
		
		
		if ("page".equals(flag)) {
			return getHibernateTemplate().findByCriteria(dc, firstResult,maxResults);
		} 
		else if ("all".equals(flag)) {
			return getHibernateTemplate().findByCriteria(dc);
		}

		return null;
	}
	public List<Map> findAppointProperty(String property, String[] aliases, Type[] types) {
		DetachedCriteria  dc = DetachedCriteria.forClass(getPojoClass());
		 dc.setProjection(Projections.sqlProjection(property,aliases,types));
		
		 List list = getHibernateTemplate().findByCriteria(dc);		 
		 List<Map> listMap = new ArrayList<Map>();			
			for (int i=0; i<list.size(); i++) {				
				Object[] row = (Object[])list.get(i);				
				Map map = new HashMap();				
				String[] pname = StringUtils.split(property, ",");				
				for (int j=0;j<pname.length; j++) {
					map.put(pname[j], row[j]);
				}
				
				listMap.add(map);
			}
		 
		return listMap;
	}
	
	public int countEntityByPropertiName(DetachedCriteria dc,
			Map<String, Serializable> properties) {
		if (dc == null) {
			dc = DetachedCriteria.forClass(getPojoClass());
		}

		if (properties != null && properties.size() > 0) {
			Set<Map.Entry<String, Serializable>> set = properties.entrySet();
			for (Map.Entry<String, Serializable> entry : set) {
				dc.add(Restrictions.eq(entry.getKey(), entry.getValue()));
			}
		}
		dc.setProjection(Projections.rowCount());
		List<Integer> list = getHibernateTemplate().findByCriteria(dc);
		int size = list.size() == 0 ? 0 : list.get(0).intValue();
		return size;

	}

	public List findAllPagerList(Map<Integer, Object> propertyName,
			Map<Integer, Object> propertyValue, final boolean isAsc,
			final int firstResult, final int maxResults, final String flag) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());

		if (propertyName != null && propertyValue != null) {

			for (int i = 1; i <= propertyName.size(); i++) {
				dc.add(Restrictions.eq(String.valueOf(propertyName.get(i)),
						propertyValue.get(i)));
			}
		}

		if ("page".equals(flag)) {
			return getHibernateTemplate().findByCriteria(dc, firstResult,maxResults);
		} 
		else if ("all".equals(flag)) {
			return getHibernateTemplate().findByCriteria(dc);
		}

		return null;
	}

	public List findAllByProAndValue(Map<Integer, Object> propertyName,
			Map<Integer, Object> propertyValue) {

		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());

		if (propertyName != null && propertyValue != null) {

			for (int i = 1; i <= propertyName.size(); i++) {
				dc.add(Restrictions.eq(String.valueOf(propertyName.get(i)),
						propertyValue.get(i)));
			}
		}

		return getHibernateTemplate().findByCriteria(dc);
	}

	public List findAllPagerList(Map<Integer, Object> propertyName,
			Map<Integer, Object> propertyValue, Map<Integer, Object> operators,
			final boolean isAsc, final int firstResult, final int maxResults,
			final String flag) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());

		if (propertyName != null && propertyValue != null) {

			for (int i = 1; i <= propertyName.size(); i++) {
				String opertor = String.valueOf(operators.get(i));

				if (new CommUtils().isNullorEmpty(propertyValue.get(i))) {
					if ("eq".equals(opertor)) {
						dc.add(Restrictions.eq(String.valueOf(propertyName
								.get(i)), propertyValue.get(i)));
					} else if ("ilike".equals(opertor)) {
						dc.add(Restrictions.ilike(String.valueOf(propertyName
								.get(i)), "%"
								+ String.valueOf(propertyValue.get(i)) + "%",
								MatchMode.ANYWHERE));
					} else if ("like".equals(opertor)) {
						dc.add(Restrictions.like(String.valueOf(propertyName
								.get(i)), "%"
								+ String.valueOf(propertyValue.get(i)) + "%",
								MatchMode.ANYWHERE));
					} else if ("gt".equals(opertor)) {
						dc.add(Restrictions.gt(String.valueOf(propertyName
								.get(i)), propertyValue.get(i)));
					} else if ("lt".equals(opertor)) {
						dc.add(Restrictions.lt(String.valueOf(propertyName
								.get(i)), propertyValue.get(i)));
					} else if ("ge".equals(opertor)) {
						dc.add(Restrictions.ge(String.valueOf(propertyName
								.get(i)), propertyValue.get(i)));
					} else if ("le".equals(opertor)) {
						dc.add(Restrictions.le(String.valueOf(propertyName
								.get(i)), propertyValue.get(i)));
					}

					else if ("noeq".equals(opertor)) {
						dc.add(Restrictions.ne(String.valueOf(propertyName
								.get(i)), propertyValue.get(i)));
					}
				}
			}
		}

		if ("page".equals(flag)) {

			return getHibernateTemplate().findByCriteria(dc, firstResult,
					maxResults);
		} else if ("all".equals(flag)) {
			return getHibernateTemplate().findByCriteria(dc);
		}

		return null;
	}

	// 过滤大字串所用到的方法start
	public List query(DetachedCriteria dc,
			Map<String, Serializable> propertiesMap, int firstResult,
			int maxResults, String flag) {
		if (dc == null) {
			dc = DetachedCriteria.forClass(getPojoClass());
		}
		if (propertiesMap != null) {
			Set<String> pNames = propertiesMap.keySet();
			Iterator<String> it = pNames.iterator();
			while (it.hasNext()) {
				String pName = it.next();
				dc.add(Restrictions.eq(pName, propertiesMap.get(pName)));
			}
		}

		List<PropertyDescriptor> properties = dumpPojoProperties(dumpPreparedStatementMethods());
		List result = new ArrayList();
		String propertiesStr = "";
		String[] aliases = new String[properties.size()];
		Type[] types = new Type[properties.size()];
		for (int i = 0; i < properties.size(); i++) {
			aliases[i] = properties.get(i).getName();
			types[i] = getTypeMap().get(
					properties.get(i).getPropertyType().getName());
			if (i < properties.size() - 1)
				propertiesStr = propertiesStr + properties.get(i).getName()
						+ ",";
			else
				propertiesStr = propertiesStr + properties.get(i).getName();
		}
		dc.setProjection(Projections.sqlProjection(propertiesStr, aliases,
				types));
		List<Object[]> list = null;
		if (flag.equals("all")) {
			list = getHibernateTemplate().findByCriteria(dc);
		} else if (flag.equals("page")) {
			list = getHibernateTemplate().findByCriteria(dc, firstResult,
					maxResults);
		}
		for (Object[] values : list) {
			Object enty = null;
			try {
				enty = getPojoClass().newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i = 0; i < properties.size(); i++) {
				try {
					properties.get(i).getWriteMethod().invoke(enty, values[i]);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			result.add(enty);

		}
		return result;

	}

	public Map<String, Type> getTypeMap() {
		Map<String, Type> map = new HashMap<String, Type>();
		map.put("int", Hibernate.INTEGER);
		map.put("java.lang.Integer", Hibernate.INTEGER);
		map.put("double", Hibernate.DOUBLE);
		map.put("java.lang.Double", Hibernate.DOUBLE);
		map.put("java.util.Date", Hibernate.DATE);
		map.put("java.lang.String", Hibernate.STRING);
		map.put("java.io.Serializable", Hibernate.SERIALIZABLE);
		return map;
	}

	public List<PropertyDescriptor> dumpPojoProperties(
			Map<String, Method> pstmtMethods) {
		List<PropertyDescriptor> properties = new ArrayList<PropertyDescriptor>();
		BeanInfo classBean = null;
		try {
			classBean = Introspector.getBeanInfo(getPojoClass(), getPojoClass()
					.getSuperclass());
		} catch (IntrospectionException ex) {
			System.exit(1);
		}
		PropertyDescriptor[] tempProperties = classBean
				.getPropertyDescriptors();

		for (int i = 0; i < tempProperties.length; i++) {

			Class propertyType = tempProperties[i].getPropertyType();
			// System.out.println(propertyType.getName());
			if (propertyType.getName().equals("[B")) {
				continue;
			} else if (propertyType.getName().equals("java.sql.Blob")) {
				continue;
			}

			else if (pstmtMethods.get(propertyType.getName()) != null) {
				properties.add(tempProperties[i]);
			}
		}
		return properties;

	}

	public Map<String, Method> dumpPreparedStatementMethods() {
		BeanInfo bi = null;
		try {
			bi = Introspector.getBeanInfo(java.sql.PreparedStatement.class,
					java.sql.PreparedStatement.class.getSuperclass());
		} catch (IntrospectionException ex) {
			System.out.println("Couldn't introspect "
					+ java.sql.PreparedStatement.class.getName());
			System.exit(1);
		}
		MethodDescriptor[] methodsDesc = bi.getMethodDescriptors();
		Map<String, Method> methods = new Hashtable<String, Method>();
		// 过滤无用方法
		for (int i = 0; i < methodsDesc.length; i++) {
			Method method = methodsDesc[i].getMethod();
			Class[] parameterTypes = method.getParameterTypes();
			if (parameterTypes.length == 2) {

				if (parameterTypes[1].getName().equals("java.io.InputStream")) {
					if (!method.getName().equals("setBinaryStream")) {
						continue;
					}
				}

				if (method.getName().equals("setNull")) {
					continue;
				}

				if (parameterTypes[1].getName().equals("java.sql.Date")) {
					try {
						methods.put("java.util.Date",
								java.sql.PreparedStatement.class.getMethod(
										"setString", new Class[] { int.class,
												java.lang.String.class }));
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (parameterTypes[1].getName().equals("int")) {
					methods.put("java.lang.Integer", method);
				}
				if (parameterTypes[1].getName().equals("double")) {
					methods.put("java.lang.Double", method);
				}

				methods.put(parameterTypes[1].getName(), method);
			}

		}

		return methods;
	}
	
	public Object findPropertyBySql(String sql) {
		List lm = new ArrayList();
		Query query = this.getSession().createSQLQuery(sql);
		List list = query.list();
		//Iterator iter=list.iterator();
    //System.out.println(">>>>>>> iter "+iter.next());
    //System.out.println(">>>>>>> list.get(0) "+list.get(0));
    //Double countone = list.get(0);
    //System.out.println(">>>>>>> countone "+countone);
		return list.get(0);
		
   /* if (StringUtils.contains(sql, "sum")) {
    	//if (iter.hasNext()) {
    		return (Double)iter.next();
    	//}
    }*/
    //return 0.0;
    
	/*	while(iter.hasNext()){
     Object[] obj=(Object[]) iter.next();
     Map map = new HashMap();
     map.put("count", obj[0]);
     System.out.println(">>>>>>>> obj[0] "+obj[0]);
     lm.add(map);
    }
		return lm;*/
	}
	
	// 过滤大字串所用到的方法end

	public static void main(String args[]) {
		Map map = new HashMap();
		map.put(1, "A");
		map.put(2, 30);
		map.put(3, "C");

		for (int i = 1; i <= map.size(); i++) {
			System.out.println("map p  " + map.get(i));
		}
	}

	public List findbyHql(String hql){
		Query query = this.getSession().createQuery(hql);
		return query.list();
	}
	
	public List findBySql(String sql){
		SQLQuery query = this.getSession().createSQLQuery(sql);
		return query.list();
	}
	
}