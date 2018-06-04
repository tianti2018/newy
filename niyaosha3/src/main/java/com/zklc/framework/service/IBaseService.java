package com.zklc.framework.service;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.zklc.framework.hibernate.persistent.JqgridPager;


public interface IBaseService<T, PK> {
	/**
	 * 描述：添加方法
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void save(final T entity);
	/**
	 * 
	 * @param hql
	 * @param params
	 */
	public void update(final String hql, final Object... params);
	/**
	 * 描述：批量添加
	 * 
	 * @param collection
	 * @throws Exception
	 */
	public void saveOrUpdateAll(Collection collection);
	
	public void saveOrUpdate(final T entity);
	
	/**
	 * 描述：更新方法
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void update(final T entity) ;

	/**
	 * 描述：删除方法
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void delete(final T entity) ;




	/**
	 * 描述：查询全部方法
	 * 
	 * @param entityClass
	 * @return
	 * @throws Exception
	 */
	public List<T> findAll();

	/**
	 * 描述：根据Id查找对象方法
	 * 
	 * @param entityClass
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public T findById( PK id);
	
	/**
	 * 根据sql查询信息
	 * @param entityClass
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List findBySql( Class entityClass,String sql, List params) ;
	
	
	
	/**
	 * 根据sql查询信息
	 * @param entityClass
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List findBySql(String sql, List params) ;
	
	
	/**
	 * 描述：分页查询
	 * @param entityClass
	 * @param propertyName
	 * @param isAsc
	 * @param firstResult
	 * @prarm maxResults
	 * @prarm flag
	 * @return
	 * @throws Exception
	 */
	public List findAllPagerList( String propertyName, boolean isAsc, int firstResult, int maxResults,  String flag,Map conditionProperties,Map<String,Integer> compare);
	
	/**
   * 描述：分页查询
   * @param  conditionProperties 
   * @param  compare
   * @param  sort
   * @param  firstResult
   * @param  maxResults
   * @param  flag
   * @return List
   */
  public List findAllPagerList(Map conditionProperties,Map<String,Integer> compare, Map<String,Boolean> sort, int firstResult, int maxResults, String flag);
  
  /**
   * 描述：得到总数
   * @param entityClass
   * @param firstResult
   * @prarm maxResults
   * @prarm flag
   * @return
   * @throws Exception
   */
  public int count_size(Map<String,Object> conditionProperties, Map<String,Integer> compare);
  
  /**
   * 描述：得到总数
   * @param entityClass
   * @param firstResult
   * @prarm maxResults
   * @prarm flag
   * @return
   * @throws Exception
   */
  public List findByHql(String hql,Object... params);
  
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
   * 功能 Jqgrid分页查询
   * </p>
   * @author Administrator 时间 2012-6-21 下午1:56:03
   * @param propertyName 删除PO的属性名称
   * @param propertyValue PO的ID值
   */
  public JqgridPager findForJqgridPager(JqgridPager jqgridPager);
  
  /**
   * 
   * <p>
   * 功能 批量删除
   * </p>
   * @author zhshg 时间 2012-8-31 上午11:19:52
   * @param 删除对象list
   */
  public void deleteAll(Collection collection);
  /**
   * 
   * <p>
 * 功能 分页查询 sql语句
   * </p>
   * @author zhshg 时间 2012-8-31 上午11:18:42
   * @param entityClass 指定接收VO
   * @param sql sql语句
   * @param params 参数list(sql中的？)
   * @param pageSize 显示行数
   * @param pageNo 第几页
   * @return
   */
  public List findByPageSql(Class entityClass, String sql, List params,int pageSize,int pageNo);
  
  /**
   * 
   * <p>
 * 功能 分页查询 sql语句
   * </p>
   * @author zhshg 时间 2012-8-31 上午11:18:42
   * @param sql sql语句
   * @param params 参数list(sql中的？)
   * @param pageSize 显示行数
   * @param pageNo 第几页
   * @return
   */
  public List findByPageSql(String sql, List params,int pageSize,int pageNo);
  
  
/**
 * 
 * <p>
 * 功能 分页查询 hql语句
 * </p> 
 * @author zhshg 时间 2012-8-31 上午11:17:18
 * @param hql    hql语句
 * @param map 参数值？
 * @param pageSize 显示行数
 * @param pageNo 第几页
 * @return
 */
  public List findByPageHql(String hql, Map<String, Object> map, int pageSize,int pageNo);
  
  
  /**
   * 根据属性值查询列表
   *
   * @param entityClass
   *            实体类名
   * @param propertyName
   *            属性名
   * @param value
   *            属性值
   * @return List列表
   */
  public List<T> findByProperty(String propertyName, Object value);
  
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
   * 功能 根据属性名跟值集合 查询数据
   * </p>
   * @author zhshg 时间 2012-9-12 上午10:59:50
   * @param propertyName
   * @param values
   */
  public List findByPropertyAndValues(String propertyName, List  values);
  
  /**
   * 获取唯一值
   * @param propertyName
   * @param property
   * @return
   */
}