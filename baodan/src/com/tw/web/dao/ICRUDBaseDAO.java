/*
 * ----------------------------------------------------------
 * FILE         : ICRUDBaseDAO
 * CREATEUSER   : Anston
 * CREATEDATE   : 2009/8/12
 * FILENAME     : ICRUDBaseDAO.java
 * DESCRIPTION  : 
 * MODIFIES     : 
 * MODIFIER     : 
 * MODIFIEDDATE :
 * COMMENT      : 
 * ----------------------------------------------------------
 */

package com.tw.web.dao;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.type.Type;

public interface ICRUDBaseDAO {

	public Serializable findById(Serializable id);
	

	@SuppressWarnings("unchecked")
	public List findAll();

	/**
	 * @param entity
	 * @return
	 */
	public Serializable create(Serializable entity);

	/**
	 * @param entity
	 */
	public int update(Serializable entity);

	/**
	 * @param entity
	 */
	public int delete(Serializable entity);
	
	public int deleteByProperty(String propertyName, Serializable pValue);
	/**
	 * @param id
	 */
	public int deleteById(Serializable id);
	
	public int saveOrUpdate(Serializable entity);
	
	@SuppressWarnings("unchecked")
	public List findEntityByPropertiName(String propertyName,Serializable pValue);
	
	
	/***
	 * 指定属性进行查询
	 */
	public List<Map> findAppointProperty(String property, String[] aliases, Type[] types); 
	
	/**
	 * 公用的分页方法
	 */
	@SuppressWarnings("unchecked")
	public List findAllPagerList(final String propertyName,final boolean isAsc,final int firstResult,final int maxResults, final String flag);
	public int cout_size_Commen(Map conditionProperties,Map<String,Integer> compare);
	public int cout_size(Map conditionProperties,Map<String,Integer> compare);
	public int cout_size1(Map conditionProperties,Map<String,Integer> compare);
	
	public List findAllPagerList(Map<String,Object> conditionProperties,Map<String,Integer> compare,Map<String,Boolean> sort,final int firstResult,final int maxResults, final String flag);
	/**
	 * 公用的分页方法3 推荐分页方法
	 * @param conditionProperties放的所有属性的名字
	 * @param compare放的所有属性对应的值
	 * @param operators操作符号
	 */
	@SuppressWarnings("unchecked")
	public List findAllPagerList_new(Map<String,Object> conditionProperties,Map<String,Integer> compare,final int firstResult,final int maxResults, final String flag);
	
	
	/**
	 * 
	 * @param conditionProperties放的所有属性的名字
	 * @param compare放的所有属性对应的值
	 * @param operators操作符号
	 * @param sort 排序
	 */
	@SuppressWarnings("unchecked")
	public List findAllPagerList_new1(Map<String,Object> conditionProperties,Map<String,Integer> compare,Map<String,Boolean> sort, final int firstResult,final int maxResults, final String flag);
	/**
	 * 公用的分页方法4 推荐分页方法
	 * @param conditionProperties放的所有属性的名字
	 * @param compare放的所有属性对应的值
	 * @param operators操作符号
	 * @param sort 排序
	 */
	@SuppressWarnings("unchecked")
	public List findAllPagerList_new(Map<String,Object> conditionProperties,Map<String,Integer> compare,Map<String,Boolean> sort, final int firstResult,final int maxResults, final String flag);
	
	public List findAllByProperty(Map conditionProperties,Map<String,Integer> compare);
	
	//
	public Object findPropertyBySql(String sql);
	
	/**
	 * 
	 * @param propertyName放的所有属性的名字
	 * @param propertyValue放的所有属性对应的值
	 */
	@SuppressWarnings("unchecked")
	public List findAllByProAndValue(Map<Integer, Object> propertyName, Map<Integer, Object> propertyValue);
	
	/**
	 * 公用的分页方法2
	 * @param propertyName放的所有属性的名字
	 * @param propertyValue放的所有属性对应的值
	 */
	@SuppressWarnings("unchecked")
	public List findAllPagerList(Map<Integer, Object> propertyName, Map<Integer, Object> propertyValue, final boolean isAsc,final int firstResult,final int maxResults, final String flag);
	
	/**
	 * 公用的分页方法2
	 * @param propertyName放的所有属性的名字
	 * @param propertyValue放的所有属性对应的值
	 * @param operators操作符号
	 */
	@SuppressWarnings("unchecked")
	public List findAllPagerList(Map<Integer, Object> propertyName, Map<Integer, Object> propertyValue,Map<Integer, Object> operators, final boolean isAsc,final int firstResult,final int maxResults, final String flag);
		

	//public List findEntityByPropertiName(DetachedCriteria dc,Map<String,Serializable> properties,Map<String,Serializable> order, int firstResult,int maxResults ,String flag );

	public List findEntityByPropertiName(DetachedCriteria dc,
			Map properties,Map<String, Integer> compare,
			Map<String, Boolean> isAscProperties, int firstResult,
			int maxResults, String flag);

	
	public int countEntityByPropertiName(DetachedCriteria dc,Map<String, Serializable> properties);
	//过滤大字串所用到的方法Start
	public List query(DetachedCriteria dc,Map<String, Serializable> propertiesMap,int firstResult,int maxResults,String flag);
		 
	
	public Map<String,Type> getTypeMap();
	 
	public List<PropertyDescriptor>  dumpPojoProperties(Map<String,Method> pstmtMethods);
	 
	public Map<String, Method> dumpPreparedStatementMethods( );
//过滤大字串所用到的方法end 
	
	/**
	 * find list by hql
	 * @param hql 
	 * @return
	 */
	public List findbyHql(String hql);
	
	/**
	 * find list by sql
	 * @param sql
	 * @return
	 */
	public List findBySql(String sql);
	
}