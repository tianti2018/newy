package com.zklc.framework.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.zklc.framework.dao.EntityDao;
import com.zklc.framework.exception.DbAppRunTimeException;
import com.zklc.framework.hibernate.persistent.JqgridPager;
import com.zklc.framework.log.LoggerFactory;
import com.zklc.framework.log.SystemLogger;
import com.zklc.framework.service.IBaseService;
import com.zklc.framework.util.DAOUtil;
/**
 * 
 * <p>
 * 功能 service基类
 * </p>
 * <p>
 * Copyright 北京中科联城软件有限公司2013 All right reserved.
 * </p>
 * 
 * @author zhshg 时间 2013-4-11 下午4:43:23
 * @version 1.0 </br> 最后修改人 无
 * @param <T>
 * @param <PK>
 */
public class BaseServiceImp<T, PK> implements IBaseService<T, PK> {

	@Autowired
	private EntityDao<T, PK> entityDao;

	protected Class<T> entityClass = (Class<T>) DAOUtil.getTypeArguments(
			BaseServiceImp.class, this.getClass()).get(0);
	
	protected final SystemLogger logger = LoggerFactory
            .getSystemLogger(getClass());
	

	public void save(T entity) {
		entityDao.save(entity);
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
		entityDao.update(hql, params);  
	}

	public void saveOrUpdateAll(Collection collection){
		entityDao.saveOrUpdateAll(collection);
	}
	
	public void update(T entity){
		entityDao.update(entity);
	}

	public void delete(T entity) {
		entityDao.delete(entity);
	}

	public List<T> findAll() {
		entityDao.setEntityClass(entityClass);
		return entityDao.findAll();
	}

	public T findById(PK id) {
		// return entityDao.get(getPojoClass(), id);
		entityDao.setEntityClass(entityClass);
		return entityDao.findById(id);
	}

	public List findAllPagerList(String propertyName, boolean isAsc,
			int firstResult, int maxResults, String flag,
			Map conditionProperties, Map<String, Integer> compare) {
		entityDao.setEntityClass(entityClass);
		if ("page".equals(flag)) {
			return entityDao.findByQueryMap(propertyName, isAsc, firstResult,
					maxResults, conditionProperties, compare);
		} else if ("all".equals(flag)) {
			return entityDao.findAll();
		}
		return null;
	}

	public List findBySql(Class entityClass, String sql, List params)
			 {
		return entityDao.findBySql(entityClass, sql, params);
	}

	@SuppressWarnings("rawtypes")
	public List findAllPagerList(Map conditionProperties,
			Map<String, Integer> compare, Map<String, Boolean> sort,
			int firstResult, int maxResults, String flag) {
		entityDao.setEntityClass(entityClass);
		return entityDao.findAllPagerList(conditionProperties, compare, sort,
				firstResult, maxResults, flag);
	}

	public int count_size(Map<String, Object> conditionProperties,
			Map<String, Integer> compare) {
		entityDao.setEntityClass(entityClass);
		return entityDao.count_size(conditionProperties, compare);
	}

	@Override
	public List findByHql(String hql, Object... params) {
		entityDao.setEntityClass(entityClass);
		return entityDao.findByHql(hql, params);
	}

  @Override
  public void deleteAll(String poId, List<PK> ids) {
    entityDao.setEntityClass(entityClass);
    entityDao.deleteAll(poId, ids);
  }

  @Override
  public void deleteAll(String propertyName, String propertyValue) {
    entityDao.setEntityClass(entityClass);
    entityDao.deleteAll(propertyName, propertyValue);
  }
  @Override
  public void deleteAll(Collection collection) {
    entityDao.deleteAll(collection);
  }
  /**
   * 
   * <p>
   * 功能 Jqgrid分页查询
   * </p>
   * @author Administrator 时间 2012-6-21 下午1:56:03
   * @param propertyName 删除PO的属性名称
   * @param propertyValue PO的ID值
   */
  public JqgridPager findForJqgridPager(JqgridPager jqgridPager){
	entityDao.setEntityClass(entityClass);
	return entityDao.findForJqgridPager(jqgridPager);	  
  }

@Override
public List findByPageSql(Class entityClass, String sql, List params,int pageSize,int pageNo) {
	return entityDao.findByPageSql(entityClass, sql, params, pageSize, pageNo);
}

@Override
public List findByPageSql(String sql, List params,int pageSize,int pageNo) {
    return entityDao.findByPageSql(sql, params, pageSize, pageNo);
}

@Override
public List findByPageHql(String hql, Map<String, Object> map, int pageSize,
		int pageNo) {
	return entityDao.findByPageHql(hql, map, pageSize, pageNo);
	
}

@Override
public List<T> findByProperty(String propertyName, Object value) {
	entityDao.setEntityClass(entityClass);
	return entityDao.findByProperty(propertyName, value);	
}

@Override
public boolean isPropertyExist(String propertyName, Object value) {
	entityDao.setEntityClass(entityClass);
	return entityDao.isPropertyExist(propertyName, value);
	
}

@Override
public int countByHql(String hql, Map<String, Object> map) {
	
	return entityDao.countByHql(hql, map);
	
}

@Override
public int countBySql(String sql, List params) {	
	// TODO Auto-generated method stub
	return entityDao.countBySql(sql, params);
	
}

@Override
public List findByPropertyAndValues(String propertyName, List values) {
	entityDao.setEntityClass(entityClass);
	return entityDao.findByPropertyAndValues(propertyName, values);
	
}

@Override
public List findBySql(String sql, List params) {
	// TODO Auto-generated method stub
	return  entityDao.findBySql(sql, params);
}

@Override
public T findUniqueByProperty(String propertyName, Object property) {
	List<T> list = entityDao.findByProperty(propertyName, property);
	if(list.size()>0){
		return list.get(0);
	}
	return null;
}

@Override
public void saveOrUpdate(T entity) {
	entityDao.saveOrUpdate(entity);
}
  
}