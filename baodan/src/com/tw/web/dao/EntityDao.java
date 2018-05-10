package com.tw.web.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;

import com.tw.web.hibernate.persistent.Pager;

public interface EntityDao<T, PK> {
	
	public void saveOrUpdate(T t);
	
	public void update(final String hql,final Object... params);
	
	public void delete(T t);
	
	public void delete(Class<T> entityClass,PK id);
	
	public T get(Class<T> entityClass,PK id);
	 
	public List<T> findAll(final Class<T> entityClass,final String propertyName,final boolean isAsc,final Criterion criterions); 
	
	public List<T> findByCriteria(final Class<T> entityClass,final String propertyName,final boolean isAsc,final int firstResult,final int maxResults,final Criterion... criterions);
	
	public int findCountsByCriteria(final Class<T> entityClass,final Criterion... criterions);
	
	public Pager<T> findForPager(final Class<T> entityClass,final String propertyName,final boolean isAsc,final int firstResult,final int maxResults,final Criterion... criterions);
	
	public List<T> findByProperty(Class<T> entityClass,String propertyName,Object value);
	
	public T findUniqueByProperty(final Class<T> entityClass,final String propertyName,final Object value);
	
	public boolean isPropertyExist(final Class<T> entityClass,final String propertyName,final Object value);
	
	public T findUniqueByHql(final String hql, final Object... params);
	
	public List<T> findByHql(String hql,Object params);
	
	public Pager<T> findForPagerByHql(final int firstResult, final int maxResults, final String hql, final Object... params);
}
