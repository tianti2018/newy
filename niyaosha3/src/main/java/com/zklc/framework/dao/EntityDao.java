package com.zklc.framework.dao;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.Criterion;

import com.zklc.framework.exception.DbAppRunTimeException;
import com.zklc.framework.hibernate.persistent.JqgridPager;
import com.zklc.framework.hibernate.persistent.Pager;

/**
 * 
 * <p>
 * 功能 dao基类接口
 * </p>
 * <p>
 * Copyright 北京中科联城软件有限公司2013 All right reserved.
 * </p>
 * 
 * @author zhshg 时间 2013-4-11 下午4:42:46
 * @version 1.0 </br> 最后修改人 无
 * @param <T>
 * @param <PK>
 */
public interface EntityDao<T, PK> {
    /**
     * 
     * <p>
     * 保存或更新
     * </p>
     * @author zhshg 时间 2012-6-19 下午2:23:16
     * @param t
     */
	public void update(T t);
	
	public void saveOrUpdate(T t);
	/**
     * 
     * <p>
     * 保存对象集合
     * </p>
     * @author zhshg 时间 2012-6-19 下午2:23:16
     * @param t
     */
	public void saveOrUpdateAll(Collection collection);
	
	/**
     * 
     * <p>
     * 更新对象
     * </p>
     * @author zhshg 时间 2012-6-19 下午2:23:16
     * @param t
     */
	
	public Serializable save(T t);

	public void update(final String hql, final Object... params);
	/**
	 * 
	 * <p>
	 * 删除对象
	 * </p>
	 * @author zhshg 时间 2012-6-19 下午2:23:16
	 * @param t
	 */
	public void delete(T t);
	/**
     * 
     * <p>
     * 通过主键ID获取对象
     * </p>
     * @author zhshg 时间 2012-6-19 下午2:23:16
     * @param t
     */
	public T findById(PK id);
	/**
     * 
     * <p>
     * 查询所有数据
     * </p>
     * @author zhshg 时间 2012-6-19 下午2:23:16
     * @param t
     */
	public List<T> findAll();

	public Pager<T> findForPager(final String propertyName,
			final boolean isAsc, final int firstResult, final int maxResults,
			final Criterion... criterions);

	public List<T> findByProperty(String propertyName, Object value);

	public T findUniqueByProperty(final String propertyName, final Object value);

	/**
	 * 
	 * <p>
	 * 功能 根据属性值 查询对象是否已存在
	 * </p>
	 * @author zhshg 时间 2012-9-5 下午2:19:00
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public boolean isPropertyExist(final String propertyName, final Object value);

	public T findUniqueByHql(final String hql, final Object... params);
	/**
	 * 通过HQL语句查询表数据
	 * <p>
	 * 功能
	 * </p>
	 * @author zhshg 时间 2012-6-19 下午2:25:14
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<T> findByHql(String hql, Object... params);

	/**
	 * 
	 * <p>
	 * 功能:通过hql语句分页查询数据
	 * </p>
	 * @author zhshg 时间 2012-8-25 上午6:54:58
	 * @param hql
	 * @param map
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
    public List findByPageHql(String hql, Map<String, Object> map, int pageSize,int pageNo);
    

    /**
     * 
     * <p>
     * 功能  根据hql获取总数
     * </p>
     * @author zhshg 时间 2012-9-2 下午5:55:23
     * @param hql
     * @param map
     * @return
     */
    public int countByHql(String hql, Map<String, Object> map);
    
    /**
     * 
     * <p>
     * 功能  根据sql获取总数
     * </p>
     * @author zhshg 时间 2012-9-2 下午5:55:23
     * @param hql
     * @param map
     * @return
     */
    public int countBySql(String sql, List params);
    
    /**
     * 
     * <p>
     * 功能 查询page对象通过hql语句
     * </p>
     * @author zhshg 时间 2012-8-25 上午6:56:00
     * @param firstResult
     * @param maxResults
     * @param hql
     * @param params
     * @return
     */
	public Pager<T> findForPagerByHql(final int firstResult,
			final int maxResults, final String hql, final Object... params);
	
    /**
     * 
     * <p>
     * 功能 查询page对象通过hql语句
     * </p>
     * @author zhshg 时间 2012-8-25 上午6:56:00
     * @param firstResult
     * @param maxResults
     * @param hql
     * @param params
     * @return
     */
    public Pager<T> findForPagerByHql(Pager  pager, final String hql, final Object... params);
	/**
	 * 
	 * <p>
	 * 根据sql查询表返回结果集
	 * </p>
	 * @author zhshg 时间 2012-6-19 下午2:21:29
	 * @param entityClass
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<T> findBySql(final Class entityClass, String sql, List params);
	
	/**
	 * 
	 * <p>
	 * 功能 根据sql分页查询表返回指定对象结果集
	 * </p>
	 * @author zhshg 时间 2012-8-25 上午6:58:15
	 * @param entityClass
	 * @param sql
	 * @param params
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
    public List findByPageSql(Class entityClass, String sql, List params, int pageSize,int pageNo);
    
    /**
     * 
     * <p>
     * 功能 根据sql分页查询表返回数组集合
     * </p>
     * @author zhshg 时间 2012-8-25 上午6:58:15
     * @param entityClass
     * @param sql
     * @param params
     * @param pageSize
     * @param pageNo
     * @return
     */
    public List findByPageSql(String sql, List params, int pageSize,int pageNo);
    
    /**
     * 
     * <p>
     * 通过查询条件map 返回查询结果
     * </p>
     * @author zhshg 时间 2012-6-19 下午2:22:20
     * @param propertyName
     * @param isAsc
     * @param firstResult
     * @param maxResults
     * @param conditionProperties
     * @param compare
     * @return
     */
	public List<T> findByQueryMap(final String propertyName,
			final boolean isAsc, final int firstResult, final int maxResults,
			final Map conditionProperties, final Map<String, Integer> compare);
	/**
	 * 描述：查询总数
	 * @param conditionProperties
	 * @param compare
	 * @return
	 */
	public int count_size(Map conditionProperties, Map<String, Integer> compare);

	/**
	 * 描述：分页查询
	 * 
	 * @param conditionProperties
	 * @param compare
	 * @param sort
	 * @param firstResult
	 * @param maxResults
	 * @param flag
	 * @return List
	 */
	public List findAllPagerList(Map conditionProperties,
			Map<String, Integer> compare, Map<String, Boolean> sort,
			int firstResult, int maxResults, String flag);

	public Class getEntityClass();

	public void setEntityClass(Class entityClass);
	/**
	 * 
	 * <p>
	 * 功能:存储过程执行方法 （hibernate配置存储过程）
	 * </p>
	 * @author zhshg 时间 2012-6-20 上午10:35:12
	 * @param procedure_QueryName
	 * @param parameter
	 * @return
	 */
    public List callProcedureByQueryName(String procedure_QueryName, List parameter) ;
    /**
     * 
     * <p>
     * 功能:存储过程执行方法
     * </p>
     * @author zhshg 时间 2012-6-20 上午10:35:17
     * @param procedure
     * @param INparameter
     * @param OUTparameter
     * @return
     */
    public List callProcedureBySql(String procedure, List INparameter, List OUTparameter);
    
    /**
     * 
     * <p>
     * 功能 批量删除
     * </p>
     * @author Administrator 时间 2012-6-21 下午1:56:03
     * @param poId 删除PO的ID属性名称
     * @param ids PO的所有ID值
     */
    public void deleteAll(String poId, List<PK> ids);
    
    /**
     * 
     * <p>
     * 功能 批量删除
     * </p>
     * @author Administrator 时间 2012-6-21 下午1:56:03
     * @param propertyName 删除PO的属性名称
     * @param propertyValue PO的ID值
     */
    public void deleteAll(String propertyName,String propertyValue);
    /**
     * 
     * <p>
     * 功能 批量删除
     * </p>
     * @author Administrator 时间 2012-6-21 下午1:56:03
     * @param propertyName 删除PO的属性名称
     * @param propertyValue PO的ID值
     */
    public void deleteAll(Collection collection);    
    /**
     * 
     * <p>
     * 功能 Jqgrid分页查询
     * </p>
     * @author Administrator 时间 2012-6-21 下午1:56:03
     * @param propertyName 删除PO的属性名称
     * @param propertyValue PO的ID值
     */
    public JqgridPager findForJqgridPager(JqgridPager jqgridPager);
    
    
    public Query getQuery(String hql, Map<String, Object> map, int pageSize,int pageNo);
    
    /**
     * 
     * <p>
     * 功能 指定hql语句获取query对象
     * </p>
     * @author zhshg 时间 2012-8-25 上午6:56:50
     * @param hql
     * @return
     */
    public Query createQuery(String hql);
    
    /**
     * 
     * <p>
     * 功能 指定sql语句获取query对象
     * </p>
     * @author zhshg 时间 2012-8-25 上午6:56:50
     * @param sql
     * @return
     */
    public Query createSqlQuery(String sql);
     
    /**
     * 
     * <p>
     * 功能 指定query对象set查询参数
     * </p>
     * @author zhshg 时间 2012-8-25 上午6:57:21
     * @param query
     * @param map
     * @return
     */
    public Query setParameter(Query query, Map<String, Object> map);
    /**
     * 
     * <p>
     * 功能 指定query对象设置分页数据返回结果
     * </p>
     * @author zhshg 时间 2012-8-25 上午6:57:21
     * @param query
     * @param map
     * @return
     */
    public Query setPageProperty(Query query, int pageSize, int pageNo) ;
    /**
     * 
     * <p>
     * 功能 根据属性名跟值集合 查询数据
     * </p>
     * @author zhshg 时间 2012-9-12 上午10:59:50
     * @param propertyName
     * @param values
     */
    public List findByPropertyAndValues(String propertyName, List  values);
    public List findBySql(String sql, List params) throws DbAppRunTimeException;
    
    public void setNamesUtf8mb4();
}
