package com.zhidisoft.bos.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.zhidisoft.bos.domain.Decidedzone;
import com.zhidisoft.bos.domain.Staff;
import com.zhidisoft.bos.utils.PageBean;

public interface IbaseDao<T> {
	public void save(T t);
	public void delete(T t);
	public void update(T t);
	public T findById(Serializable id);
	public void executeUpdate(String queryName, Object... objects);
	public void pageQuery(PageBean<T> pageBean) ;
	public void executeSql(String sql);
	public List<T> findAll() ;
	List<T> findByCriterria(DetachedCriteria detachedCriteria);
}
