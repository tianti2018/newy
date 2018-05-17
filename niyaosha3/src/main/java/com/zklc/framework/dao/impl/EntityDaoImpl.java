package com.zklc.framework.dao.impl;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.zklc.framework.dao.EntityDao;
import com.zklc.framework.exception.AppRunTimeException;
import com.zklc.framework.exception.DbAppRunTimeException;
import com.zklc.framework.hibernate.persistent.JqgridPager;
import com.zklc.framework.hibernate.persistent.Pager;
import com.zklc.framework.log.LoggerFactory;
import com.zklc.framework.log.SystemLogger;
import com.zklc.framework.util.DAOUtil;


/**
 * Dao层接口的实现类 许多人习惯根据不多的业务逻辑定义不同的DAO层接口，如UserDao,NewsDao,CompanyDao等等，
 * 这样往往使得编码量十分庞大，而且带来了维护的困难，因此，抽取此DAO层接口，收录大部分 DAO层必须的方法，以供Service层调用。
 *
 * @version 1.0
 * @param <T>
 *            范型，指实体类
 * @param <PK>
 *            范型，指实体类主键的数据类型，如Integer,Long
 */
/**
 * 
 * <p>
 * 功能 dao基类
 * </p>
 * <p>
 * Copyright 北京中科联城软件有限公司2013 All right reserved.
 * </p>
 * 
 * @author zhshg 时间 2013-4-11 下午4:43:02
 * @version 1.0 </br> 最后修改人 无
 * @param <T>
 * @param <PK>
 */
@Repository(value = "entityDao")
public class EntityDaoImpl<T, PK extends Serializable> extends TwHibernateDaoSupport implements EntityDao<T, PK> {
  @SuppressWarnings("unchecked")
  private Class entityClass = (Class<T>) DAOUtil.getTypeArguments(EntityDaoImpl.class,this.getClass()).get(0);
  
  protected final SystemLogger logger = LoggerFactory.getSystemLogger(getClass());

  /** */
  /**
   * 保存实体 包括添加和修改
   *
   * @param t
   *            实体对象
   */
  public void update(T t) {
    try {
      getHibernateTemplate().saveOrUpdate(t);
    } catch (DataAccessException localDataAccessException) {
      throw new DbAppRunTimeException("保存 [" + t.getClass().getName() +
        "] 实例到数据库失败", localDataAccessException);
    }
  }
  
  public void saveOrUpdate(T t){
	  try {
	      getHibernateTemplate().saveOrUpdate(t);
	    } catch (DataAccessException localDataAccessException) {
	      throw new DbAppRunTimeException("保存 [" + t.getClass().getName() +
	        "] 实例到数据库失败", localDataAccessException);
	    }
  }

  /**
   * 保存实体 包括添加和修改
   *
   * @param t
   *            实体对象
   */
  public void saveOrUpdateAll(Collection collection) {
    try {
      getHibernateTemplate().saveOrUpdateAll(collection);
    } catch (DataAccessException localDataAccessException) {
      throw new DbAppRunTimeException("批量保存 [" + getEntityClass().getName() +
        "] 实例到数据库失败", localDataAccessException);
    }
  }
  
  /** */
  /**
   * 更新实体 可用于添加、修改、删除操作
   *
   * @param hql
   *            更新的HQL语句
   * @param params
   *            参数,可有项目或多项目,代替Hql中的"?"号
   */
  public void update(final String hql, final Object... params) {
    getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
          Query query = session.createQuery(hql);
          if(params!=null){
        	  for (int i = 0; i < params.length; i++) {
                  query.setParameter(i, params[i]);
                }
          }
          
          try {
        	  query.executeUpdate();
          }catch(DataAccessException localDataAccessException){
        	  throw new DbAppRunTimeException("执行更新语句"+hql+"错误", localDataAccessException);
          }
          return null;
        }
      });
  }

  /** */
  /**
   * 删除实体
   *
   * @param t
   *            实体对象
   */
  public void delete(T t) { 
    try {
    	getHibernateTemplate().delete(t);
    }catch(DataAccessException localDataAccessException){
  	  throw new DbAppRunTimeException("删除实体失败", localDataAccessException);
    }
  }

  public List findAll() {
    HibernateTemplate template = getHibernateTemplate();
    //template.setCacheQueries(true);// 设置启用二级缓存
    return template.findByCriteria(DetachedCriteria.forClass(entityClass));
  }

  /** */
  /**
   * 单查实体
   *
   * @param entityClass
   *            实体类名
   * @param id
   *            实体的ID
   * @return 实体对象
   */
  @SuppressWarnings("unchecked")
  public T findById(PK id) {
    return (T) getHibernateTemplate().get(entityClass, id);
  }

  /** */
  /**
   * 查询全部记录列表
   *
   * @param propertyName
   *            排序的参照属性
   * @param isAsc
   *            排序方式
   * @param criterions
   *            查询条件,可为0项或任意多项目
   * @return 记录List集
   */
  public List<T> findAll(final Class<T> entityClass, final String propertyName,
    final boolean isAsc, final Criterion criterions) {
    int firstResult = 0;
    int maxResults = 0; // 设置为0,则表示查询全部记录

    return findByCriteria(entityClass, propertyName, isAsc, firstResult,
      maxResults, criterions);
  }

  /** */
  /**
   * 查询列表
   *
   * @param entityClass
   *            实体类名
   * @param propertyName
   *            排序的参照属性
   * @param isAsc
   *            排序方式,true表示升序,false表示降序,当propertyName赋值为null时,此参数无效
   * @param firstResult
   *            开始记录序号
   * @param maxResults
   *            最大记录数
   * @param criterions
   *            查询条件,可有0项或任意多项目
   * @return 记录List集
   */
  @SuppressWarnings("unchecked")
  public List<T> findByQueryMap(final String propertyName, final boolean isAsc,
    final int firstResult, final int maxResults, final Map conditionProperties,
    final Map<String, Integer> compare) {
    DetachedCriteria dc = assembleQueryCondition(entityClass,
        conditionProperties, compare);
    Criteria criteria = dc.getExecutableCriteria(getHibernateTemplate()
                                                   .getSessionFactory()
                                                   .getCurrentSession());

    criteria.setCacheable(true);

    // 按某个属性排序
    if (null != propertyName) {
      if (isAsc) {
        criteria.addOrder(Order.asc(propertyName));
      } else {
        criteria.addOrder(Order.desc(propertyName));
      }
    }

    // 用于分页查询
    if (maxResults != 0) {
      criteria.setFirstResult(firstResult);
      criteria.setMaxResults(maxResults);
    }

    List<T> list = new ArrayList();

    try {
      list = criteria.list();
    } catch (DataAccessException localDataAccessException) {
      throw new DbAppRunTimeException("查询实例" + entityClass.getName() + "错误",
        localDataAccessException);
    }

    return list;
  }

  /**
   * <p>
   * 根据查询条件封装DetachedCriteria
   * </p>
   *
   * @author zhshg 时间 2012-6-19 下午2:15:07
   * @param entityClass
   * @param conditionProperties
   * @param compare
   * @return
   */
  public DetachedCriteria assembleQueryCondition(final Class<T> entityClass,
    Map conditionProperties, Map<String, Integer> compare) {
    DetachedCriteria dc = DetachedCriteria.forClass(entityClass);

    // 条件和值
    if ((conditionProperties != null) && (conditionProperties.size() > 0)) {
      Set<Map.Entry> set = conditionProperties.entrySet();

      Set<Map.Entry<String, Integer>> set_compare = compare.entrySet();

      for (Map.Entry entry : set) {
        String propertyName = String.valueOf(entry.getKey());
        String proertyVale = String.valueOf(entry.getValue());
        int opertor = 0;

        for (Map.Entry<String, Integer> compareOne : set_compare) {
          if (compareOne.getKey().equals(propertyName)) {
            opertor = compareOne.getValue().intValue();

            break;
          }
        }

        switch (opertor) {
        case 0:
          dc.add(Restrictions.eq(propertyName, proertyVale)); // 0
                                                              // =
                                                              // eq

          break;

        case 1:
          dc.add(Restrictions.ne(propertyName, proertyVale)); // 1=noteq

          break;

        case 2:
          dc.add(Restrictions.like(propertyName, "%" + proertyVale + "%",
              MatchMode.ANYWHERE)); // 2=like

          break;

        case 3:
          dc.add(Restrictions.ilike(propertyName, "%" + proertyVale + "%",
              MatchMode.ANYWHERE)); // 3=ilike

          break;

        case 4:
          dc.add(Restrictions.in(String.valueOf(entry.getKey()),
              (Object[]) entry.getValue())); // 4=in

          break;

        case 5:
          dc.add(Restrictions.not(Restrictions.in(propertyName,
                (Object[]) entry.getValue()))); // 5=not
                                                // in

          break;

        case 6:
          dc.add(Restrictions.gt(propertyName, proertyVale)); // 6=gt

          break;

        case 7:
          dc.add(Restrictions.lt(propertyName, proertyVale)); // 7=lt

          break;

        case 8:
          dc.add(Restrictions.ge(propertyName, proertyVale)); // 8=ge

          break;

        case 9:
          dc.add(Restrictions.le(propertyName, proertyVale)); // 9=le

          break;

        case 10:
          dc.add(Restrictions.between(propertyName,
              ((Object[]) entry.getValue())[0], ((Object[]) entry.getValue())[1])); // 10=between

          break;
        }
      }
    }

    return dc;
  }

  /** */
  /**
   * 查询总记录数
   *
   * @param entityClass
   *            实体类名
   * @param criterions
   *            查询条件,可有0项或任意多项
   * @return 总记录数
   */
  public int findCountsByCriteria(final Class<T> entityClass,
    final Criterion... criterions) {
    int totalCounts = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
            throws HibernateException, SQLException {
            Criteria criteria = session.createCriteria(entityClass);

            // 按属性条件查询
            for (Criterion criterion : criterions) {
              criteria.add(criterion);
            }

            int totalCounts = criteria.list().size();

            return totalCounts;
          }
        });

    return totalCounts;
  }

  /** */
  /**
   * 分页查询
   *
   * @param entityClass
   *            实体类名
   * @param propertyName
   *            排序参照属性
   * @param isAsc
   *            排序方式,true表示升序,false表示降序,当propertyName赋值为null时,此参数无效
   * @param firstResult
   *            开始记录序号
   * @param maxResults
   *            最大记录数
   * @param criterions
   *            查询条件,可为0项或任意多项目
   * @return 封装List和totalCounts的Pager对象
   */
  @SuppressWarnings("unchecked")
  public Pager<T> findForPager(final String propertyName, final boolean isAsc,
    final int firstResult, final int maxResults, final Criterion... criterions) {
    int totalCounts = findCountsByCriteria(entityClass, criterions);
    List<T> entityList = findByCriteria(entityClass, propertyName, isAsc,
        firstResult, maxResults, criterions);
    Pager pager = new Pager();
    pager.setTotalCounts(totalCounts);
    pager.setEntityList(entityList);

    return pager;
  }

  /** */
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
  public List<T> findByProperty(String propertyName, Object value) {
    Criterion criterion = Restrictions.eq(propertyName, value);
    List<T> list = findAll(entityClass, null, true, criterion);

    return list;
  }

  /** */
  /**
   * 根据属性值查询单个对象
   *
   * @param entityClass
   *            实体类名
   * @param propertyName
   *            属性名
   * @param value
   *            属性值
   * @return 实体对象
   */
  @SuppressWarnings("unchecked")
  public T findUniqueByProperty(final String propertyName, final Object value) {
    T t = (T) getHibernateTemplate().execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
            throws HibernateException, SQLException {
            Criteria criteria = session.createCriteria(entityClass)
                                       .add(Restrictions.eq(propertyName, value));
            T t = (T) criteria.uniqueResult();

            return t;
          }
        });

    return t;
  }

  /** */
  /**
   * 根据属性值查询实体是否存在
   *
   * @param entityClass
   *            实体类名
   * @param propertyName
   *            参照的属性名
   * @param value
   *            属性值
   * @return 存在则返回true,不存在则返回false
   */
  public boolean isPropertyExist(final String propertyName, final Object value) {
    boolean isExist = (Boolean) getHibernateTemplate().execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
            throws HibernateException, SQLException {
            Criteria criteria = session.createCriteria(entityClass)
                                       .add(Restrictions.eq(propertyName, value));
            boolean isEmpty = criteria.list().isEmpty();

            return !isEmpty;
          }
        });

    return isExist;
  }

  /** */
  /**
   * @param hql
   *            查询语句 用法如：可用于登录验证时，根据用户名、密码等信息查询用户
   * @param params
   *            参数数组,代替HQL中的"?"号,可有0项目或多项
   * @return 唯一实体，返回null则表示不存在配置的实体
   * @exception 如果查询的结果集不唯一
   *                ,则抛异常
   */
  @SuppressWarnings("unchecked")
  public T findUniqueByHql(final String hql, final Object... params) {
    T t = (T) getHibernateTemplate().execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
            throws HibernateException, SQLException {
            Query query = session.createQuery(hql);

            for (int i = 0; i < params.length; i++) {
              query.setParameter(i, params[i]);
            }

            T t = (T) query.uniqueResult();

            return t;
          }
        });

    return t;
  }

  /** */
  /**
   * 按HQL条件查询列表
   *
   * @param hql
   *            查询语句,支持连接查询和多条件查询
   * @param params
   *            参数数组,代替hql中的"?"号
   * @return 结果集List
   */
  @SuppressWarnings("unchecked")
  public List<T> findByHql(String hql, Object... params) {
    List list = getHibernateTemplate().find(hql, params);
    return list;
  }

	public List findByPageHql(String hql,
			Map<String, Object> map, int pageSize, int pageNo) {
		List dataList = new ArrayList();	
		try {
			dataList = this.getQuery(hql, map, pageSize, pageNo).list();
		} catch (DataAccessException e) {
			 throw new DbAppRunTimeException("查询数据库失败", e);
		}
		return dataList;
	}
  
  /** */
  /**
   * 按HQL分页查询
   *
   * @param firstResult
   *            开始记录号
   * @param maxResults
   *            最大记录数
   * @param hql
   *            查询语句,支持连接查询和多条件查询
   * @param params
   *            参数数组,代替餐hql中的"?"号
   * @return 封装List和total的Pager对象
   */
  @SuppressWarnings("unchecked")
  public Pager<T> findForPagerByHql(final int firstResult,
    final int maxResults, final String hql, final Object... params) {
    Pager<T> pager = (Pager<T>) getHibernateTemplate().execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
            throws HibernateException, SQLException {
            Query query = session.createQuery(hql);

            for (int position = 0; position < params.length; position++) {
              query.setParameter(position, params[position]);
            }
            int totalCounts = query.list().size(); // 总记录数
                                                   // 用于分页查询
            if (maxResults > 0) {
              query.setFirstResult(firstResult);
              query.setMaxResults(maxResults);
            }

            List<T> list = query.list();
            Pager<T> pager = new Pager<T>();
            pager.setEntityList(list);
            pager.setTotalCounts(totalCounts);

            return pager;
          }
        });

    return pager;
  }
  
  
  /** */
  /**
   * 按HQL分页查询
   *
   * @param firstResult
   *            开始记录号
   * @param maxResults
   *            最大记录数
   * @param hql
   *            查询语句,支持连接查询和多条件查询
   * @param params
   *            参数数组,代替餐hql中的"?"号
   * @return 封装List和total的Pager对象
   */
  @SuppressWarnings("unchecked")
  public Pager<T> findForPagerByHql(Pager  pager, final String hql, final Object... params) {	  
      Query query = getSession().createQuery(hql);
      for (int position = 0; position < params.length; position++) {
        query.setParameter(position, params[position]);
      }
      int totalCounts = query.list().size(); // 总记录数
      // 用于分页查询
      if (pager.getPageSize() > 0) {
        query.setFirstResult((pager.getCurrentPage()-1)*pager.getPageSize());
        query.setMaxResults(pager.getPageSize());
      }
      List<T> list = query.list();
      
      pager.setEntityList(list);
      pager.setTotalCounts(totalCounts);

      return pager;
    
	  
	  
  }
  
  

  /**
   * <p>
   * 根据sql查询表返回结果集
   * </p>
   *
   * @author zhshg 时间 2012-6-19 下午2:21:29
   * @param entityClass
   * @param sql
   * @param params
   * @return
   * @throws Exception
   */
  @Override
  public List findBySql(Class entityClass, String sql, List params)
    throws DbAppRunTimeException {
    List list = new ArrayList();
    Query query=null;
    try {
    	query=this.getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
      if (params!=null  && params.size() > 0) {
        for (int i = 0; i < params.size(); i++) {
        	query.setParameter(i, params.get(i));
        }
      }
      list = DAOUtil.queryMapsToList(entityClass, query.list());
    } catch (Exception e) {
      throw new DbAppRunTimeException("查询数据库失败", e);
    }

    return list;
  }
  
  
  /**
   * <p>
   * 根据sql查询表返回结果集
   * </p>
   *
   * @author zhshg 时间 2012-6-19 下午2:21:29
   * @param entityClass
   * @param sql
   * @param params
   * @return
   * @throws Exception
   */
  @Override
  public List findBySql(String sql, List params)
    throws DbAppRunTimeException {
    List list = new ArrayList();
    Query query=null;
    try {
    	query=this.getSession().createSQLQuery(sql);
      if (params!=null  && params.size() > 0) {
        for (int i = 0; i < params.size(); i++) {
        	query.setParameter(i, params.get(i));
        }
      }
      list =  query.list();
    } catch (Exception e) {
      throw new DbAppRunTimeException("查询数据库失败", e);
    }

    return list;
  }
  
  
  
  /**
   * <p>
   * 根据sql查询表返回结果集
   * </p>
   *
   * @author zhshg 时间 2012-6-19 下午2:21:29
   * @param entityClass
   * @param sql
   * @param params
   * @return
   * @throws Exception
   */
  @Override
  public List findByPageSql(Class entityClass, String sql, List params,
		      int pageSize,int pageNo) {
    PreparedStatement prepareStatement;
    List list = new ArrayList();
    Query query=null;
      try {
    	query=this.getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
      if (params!=null && params.size() > 0) {
        for (int i = 0; i < params.size(); i++) {
        	query.setParameter(i, params.get(i));
        }
      }
      if (pageNo != 0 && pageSize != 0) {  
          query.setFirstResult((pageNo - 1) * pageSize);  
          query.setMaxResults(pageSize);  
      } 
      
      list = DAOUtil.queryMapsToList(entityClass, query.list());
    } catch (Exception e) {
      throw new DbAppRunTimeException("查询数据库失败", e);
    }
    return list;
  }

  
  /**
   * <p>
   * 根据sql查询表返回结果集
   * </p>
   *
   * @author zhshg 时间 2012-6-19 下午2:21:29
   * @param entityClass
   * @param sql
   * @param params
   * @return
   * @throws Exception
   */
  @Override
  public List findByPageSql(String sql, List params,
              int pageSize,int pageNo) {
    PreparedStatement prepareStatement;
    List list = new ArrayList();
    Query query=null;
      try {
        query=this.getSession().createSQLQuery(sql);
      if (params.size() > 0) {
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i, params.get(i));
        }
      }
      if (pageNo != 0 && pageSize != 0) {  
          query.setFirstResult((pageNo - 1) * pageSize);  
          query.setMaxResults(pageSize);  
      } 
      
      list = query.list();
    } catch (Exception e) {
      throw new DbAppRunTimeException("查询数据库失败", e);
    }
    return list;
  }
  
  
  public List<T> findByCriteria(final Class<T> entityClass,
    final String propertyName, final boolean isAsc, final int firstResult,
    final int maxResults, final Criterion... criterions) {
    List<T> list = (List<T>) getHibernateTemplate().execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
            throws HibernateException, SQLException {
            Criteria criteria = session.createCriteria(entityClass);

            // 按属性条件查询
            for (Criterion criterion : criterions) {
              criteria.add(criterion);
            }

            // 按某个属性排序
            if (null != propertyName) {
              if (isAsc) {
                criteria.addOrder(Order.asc(propertyName));
              } else {
                criteria.addOrder(Order.desc(propertyName));
              }
            }

            // 用于分页查询
            if (maxResults != 0) {
              criteria.setFirstResult(firstResult);
              criteria.setMaxResults(maxResults);
            }

            List<T> list = criteria.list();

            return list;
          }
        });

    return list;
  }

  /**
   * 描述：返回持久化类PO
   *
   * @return Class
   */
  public Class getPojoClass() {
    return entityClass;
  }

  /**
   * 描述：生成匹配的查询条件
   *
   * @param conditionProperties
   *            jsp前台的fild的属性和值
   * @param compare
   *            fild的操作符集合
   * @param sort
   *            排序集合
   * @param flag
   *            0:不排序 1：排序
   * @return DetachedCriteria
   */
  public DetachedCriteria getDetachedCriteria(Map conditionProperties,
    Map<String, Integer> compare, Map<String, Boolean> sort, int flag) {
    // DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
    // System.out.println(entityClass.getName());
    DetachedCriteria dc = DetachedCriteria.forClass(entityClass);

    // 条件和值
    if ((conditionProperties != null) && (conditionProperties.size() > 0)) {
      Set<Map.Entry> set = conditionProperties.entrySet();
      Set<Map.Entry<String, Integer>> set_compare = compare.entrySet();
      Map joinClassNameMap=new HashMap();//用于记录已经createAlias的持久对象
      for (Map.Entry entry : set) {
        String propertyName = String.valueOf(entry.getKey());
        String proertyVale = String.valueOf(entry.getValue());
        
        // 如果是级联查询 则分属性 重新createCriteria 级联对象（目前仅支持两级）
        if(propertyName.indexOf(".")>0){
        	if(!joinClassNameMap.containsKey(propertyName.substring(0,propertyName.indexOf(".")))){
        		dc=dc.createAlias(propertyName.substring(0,propertyName.indexOf(".")),propertyName.substring(0,propertyName.indexOf("."))); 
        		joinClassNameMap.put(propertyName.substring(0,propertyName.indexOf(".")), propertyName.substring(0,propertyName.indexOf(".")));
        	}	
        }        
        
        if (entry.getValue() instanceof String) {
          if (entry.getValue().toString().length() == 0) {
            continue;
          }
        }

        if ((entry.getValue() != null) && (entry.getValue() != "")) {
          int opertor = 0;

          for (Map.Entry<String, Integer> compareOne : set_compare) {
            if (compareOne.getKey().equals(propertyName)) {
              opertor = compareOne.getValue().intValue();

              break;
            }
          }

          switch (opertor) {
          case 0:
            dc.add(Restrictions.eq(propertyName, entry.getValue())); // 0
                                                                     // =
                                                                     // eq

            break;

          case 1:
            dc.add(Restrictions.ne(propertyName, entry.getValue())); // 1=not eq

            break;

          case 2:
            dc.add(Restrictions.like(propertyName,
                "%" + entry.getValue() + "%", MatchMode.ANYWHERE)); // 2=like

            break;

          case 3:
            dc.add(Restrictions.not(Restrictions.like(propertyName,
                    "%" + entry.getValue() + "%", MatchMode.ANYWHERE))); // 3=not like

            break;

          case 4:
            dc.add(Restrictions.in(String.valueOf(entry.getKey()),
                (Object[]) entry.getValue())); // 4=in

            break;

          case 5:
            dc.add(Restrictions.not(Restrictions.in(propertyName,
                  (Object[]) entry.getValue()))); // 5=not in

            break;

          case 6:
        	propertyName= propertyName.substring(0,propertyName.indexOf("#")==-1?propertyName.length():propertyName.indexOf("#"));//用于区分属性相同情况
            dc.add(Restrictions.gt(propertyName, entry.getValue())); // 6=gt

            break;

          case 7:
        	propertyName= propertyName.substring(0,propertyName.indexOf("#")==-1?propertyName.length():propertyName.indexOf("#"));//用于区分属性相同情况
            dc.add(Restrictions.lt(propertyName, entry.getValue())); // 7=lt

            break;

          case 8:
        	propertyName= propertyName.substring(0,propertyName.indexOf("#")==-1?propertyName.length():propertyName.indexOf("#"));//用于区分属性相同情况
            dc.add(Restrictions.ge(propertyName, entry.getValue())); // 8=ge

            break;

          case 9:
        	propertyName= propertyName.substring(0,propertyName.indexOf("#")==-1?propertyName.length():propertyName.indexOf("#"));//用于区分属性相同情况
            dc.add(Restrictions.le(propertyName, entry.getValue())); // 9=le

            break;

          case 10:
            dc.add(Restrictions.between(propertyName,
                ((Object[]) entry.getValue())[0],
                ((Object[]) entry.getValue())[1])); // 10=between

            break;
            
          case 11:
              dc.add(Restrictions.not(Restrictions.between(propertyName,
                      ((Object[]) entry.getValue())[0],
                      ((Object[]) entry.getValue())[1]))); // 11=not between
              break;
          }
        }
      }
    }

    if (1 == flag) {
      if ((sort != null) && (sort.size() > 0)) {
        Set<Map.Entry<String, Boolean>> set = sort.entrySet();

        for (Map.Entry<String, Boolean> entry : set) {
          if (entry.getValue()) {
            dc.addOrder(Order.asc(entry.getKey()));
          } else {
            dc.addOrder(Order.desc(entry.getKey()));
          }
        }
      }
    }

    return dc;
  }

  /**
   * 描述：查询总数
   *
   * @param conditionProperties
   * @param compare
   * @return
   */
  @SuppressWarnings("unchecked")
  @Override
  public int count_size(Map conditionProperties, Map<String, Integer> compare) {
    DetachedCriteria dc = getDetachedCriteria(conditionProperties, compare,
        null, 0);
    dc.setProjection(Projections.rowCount());

    List<Long> list = new ArrayList<Long>();
    list = getHibernateTemplate().findByCriteria(dc);

    int size = (list.size() == 0) ? 0 : list.get(0).intValue();

    return size;
  }

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
  @SuppressWarnings("rawtypes")
  public List findAllPagerList(Map conditionProperties,
    Map<String, Integer> compare, Map<String, Boolean> sort, int firstResult,
    int maxResults, String flag) {
    DetachedCriteria dc = getDetachedCriteria(conditionProperties, compare,
    		sort, 1);

    if (flag.equals("page")) {
      return getHibernateTemplate().findByCriteria(dc, firstResult, maxResults);
    } else if (flag.equals("all")) {
      return getHibernateTemplate().findByCriteria(dc);
    }

    return null;
  }

  /**
   * <p>
   * 功能:存储过程执行方法 （hibernate配置存储过程）
   * </p>
   *
   * @author zhshg 时间 2012-6-20 上午10:35:12
   * @param procedure_QueryName
   * @param parameter
   * @return
   */
  @SuppressWarnings(value =  {
    "unchecked"}
  )
  public List callProcedureByQueryName(String procedure_QueryName,
    List parameter) throws DbAppRunTimeException {
    List returnList = new ArrayList();
    Query query = this.getSession().getNamedQuery("procedure_QueryName");

    for (int i = 0; i < parameter.size(); ++i) {
      query.setParameter(i, parameter.get(i));
    }

    try {
      returnList = query.list();
    } catch (DataAccessException localDataAccessException) {
      throw new DbAppRunTimeException("存储过程执行失败", localDataAccessException);
    }

    return returnList;
  }

  /**
   * <p>
   * 功能:存储过程执行方法
   * </p>
   *
   * @author zhshg 时间 2012-6-20 上午10:35:17
   * @param procedure
   * @param INparameter
   * @param OUTparameter
   * @return
   */
  public List callProcedureBySql(String procedure, List INparameter,
    List OUTparameter) throws AppRunTimeException {
    Connection conn = null;
    CallableStatement cs = null;
    ResultSet rs = null;

    List list = new ArrayList();

    try {
      conn = this.getSession().connection();

      String vCall = "{call " + procedure + "(";

      for (int i = 0; i < (INparameter.size() + OUTparameter.size()); i++) {
        vCall = vCall + ((i == 0) ? "?" : ",?");
      }

      vCall = vCall + ")}";
      cs = conn.prepareCall(vCall);

      // 设置传入参数值
      for (int x = 0; x < INparameter.size(); x++) {
        cs.setObject(x + 1, INparameter.get(x));
      }

      // 设置传出参数类型
      for (int j = 0; j < OUTparameter.size(); j++) {
        cs.registerOutParameter(INparameter.size() + j + 1,
          (Integer) OUTparameter.get(j));
      }

      cs.execute();

      for (int a = 0; a < OUTparameter.size(); a++) {
        list.add(cs.getObject(INparameter.size() + a + 1));
      }

      return list;
    } catch (Exception localDataAccessException) {
      throw new DbAppRunTimeException("存储过程执行失败", localDataAccessException);
    }
  }

  @Override
  public Class getEntityClass() {
    return entityClass;
  }

  @Override
  public void setEntityClass(Class entityClass) {
    this.entityClass = entityClass;
  }

  @Override
  public Serializable save(T t) {
    try {
      return getHibernateTemplate().save(t);
    } catch (DataAccessException localDataAccessException) {
      throw new DbAppRunTimeException("保存 [" + t.getClass().getName() +
        "] 实例到数据库失败", localDataAccessException);
    }
  }

  @SuppressWarnings("unchecked")
  public void deleteAll(String poId, List<PK> ids) {
    DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
    dc.add(Restrictions.in(poId, ids));

    List<T> list = getHibernateTemplate().findByCriteria(dc);

    if ((list != null) && (list.size() != 0)) {
      try {
        getHibernateTemplate().deleteAll(list);
      } catch (DataAccessException localDataAccessException) {
        throw new DbAppRunTimeException("执行全部 删除失败", localDataAccessException);
      }
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public void deleteAll(String propertyName, String propertyValue) {
    DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
    dc.add(Restrictions.eq(propertyName, propertyValue));
    List<T> list = getHibernateTemplate().findByCriteria(dc);
    if ((list != null) && (list.size() != 0)) {
      try {
        getHibernateTemplate().deleteAll(list);
      } catch (DataAccessException localDataAccessException) {
        throw new DbAppRunTimeException("执行全部 删除失败", localDataAccessException);
      }
    }
  }

@Override
public JqgridPager findForJqgridPager(JqgridPager jqgridPager) {
	jqgridPager.setRecords(count_size(jqgridPager.getQueryMap(), jqgridPager.getOpertorMap())) ;// 查询数量
    jqgridPager.setTotal((int)Math.ceil((double)jqgridPager.getRecords() / (double)jqgridPager.getRows())) ;//计算总页数
    //jqgridPager.setDatalist(findByQueryMap(jqgridPager.getSidx(), true, jqgridPager.getRows() * (Integer.parseInt(jqgridPager.getPage()) - 1), jqgridPager.getRows(), jqgridPager.getQueryMap(), jqgridPager.getOpertorMap())) ;// 查询数据
    Map sortmap=null;
    if(jqgridPager.getSidx()!=null && !"".equals(jqgridPager.getSidx())){
    	sortmap=new HashMap();
    	sortmap.put(jqgridPager.getSidx(), "desc".equals(jqgridPager.getSord())?false:true);
    }
    
    jqgridPager.setDatalist(findAllPagerList(jqgridPager.getQueryMap(), jqgridPager.getOpertorMap(), sortmap,  jqgridPager.getRows() * (Integer.parseInt(jqgridPager.getPage()) - 1), jqgridPager.getRows(), "page")) ;// 查询数据    
    return jqgridPager;
}

@Override
public void deleteAll(Collection collection) {
	try {
        getHibernateTemplate().deleteAll(collection);
        getHibernateTemplate().flush();
      } catch (DataAccessException localDataAccessException) {
        throw new DbAppRunTimeException("执行全部 删除失败", localDataAccessException);
      }
	
}

public Query getQuery(String hql, Map<String, Object> map, int pageSize,int pageNo) {  
    Query query = this.createQuery(hql);  
    query = this.setParameter(query, map);  
    query = this.setPageProperty(query, pageSize, pageNo);  
    return query;  
}  
  
public Query createQuery(String hql) {  
    return getSession().createQuery(hql);  
}  
  
public Query setParameter(Query query, Map<String, Object> map) {  
    
    if (map != null) {
        
        for(Entry<String, Object> entry : map.entrySet()){
            
            if(entry.getValue() instanceof Collection){
                query.setParameterList(entry.getKey(), (Collection) entry.getValue());
            }else{
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }
    }  
    return query;  
}  
  
public Query setPageProperty(Query query, int pageSize, int pageNo) {  
    if (pageNo != 0 && pageSize != 0) {  
        query.setFirstResult((pageNo - 1) * pageSize);  
        query.setMaxResults(pageSize);  
    }  
    return query;  
}

@Override
public int countByHql(String hql, Map<String, Object> map) {
	Query query = this.createQuery(hql);  
	query=this.setParameter(query, map);
	Integer count = (Integer)query.list().size();
	return count;
	
}

@Override
public int countBySql(String sql, List params) {
	
	Query query=this.getSession().createSQLQuery("SELECT COUNT(*)  CNT FROM  ("+sql+")  as mysqltab ").addScalar("CNT",Hibernate.INTEGER).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
    if (params!=null && params.size() > 0) {
      for (int i = 0; i < params.size(); i++) {
      	query.setParameter(i, params.get(i));
      }
    }
    List dataList=query.list();
    Map dataMap=(Map) dataList.get(0);
    
    return (Integer) dataMap.get("CNT");
}  

public List findByPropertyAndValues(String propertyName, List  values) {
    DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
    dc.add(Restrictions.in(propertyName, values));
    List<T> list = getHibernateTemplate().findByCriteria(dc);
	return list;
}

@Override
public Query createSqlQuery(String sql){
    return getSession().createSQLQuery(sql);  
}

@Override
public void setNamesUtf8mb4() {
	String setNamesSql = "SET NAMES utf8mb4";
	Query query = createSqlQuery(setNamesSql);
	query.executeUpdate();
}

}
