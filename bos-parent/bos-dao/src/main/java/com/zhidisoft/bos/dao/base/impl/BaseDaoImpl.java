package com.zhidisoft.bos.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.zhidisoft.bos.dao.base.IbaseDao;
import com.zhidisoft.bos.domain.Staff;
import com.zhidisoft.bos.utils.PageBean;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IbaseDao<T> {

	private Class<T> pamareClass;
	
	@Resource//根据类型注入spring工厂中的会话工厂对象sessionFactory
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	//在父类（BaseDaoImpl）的构造方法中动态获得entityClass
		public BaseDaoImpl() {
			ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
			//获得父类上声明的泛型数组
			Type[] actualTypeArguments = superclass.getActualTypeArguments();
			pamareClass = (Class<T>) actualTypeArguments[0];
		}
	
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	
	public T findById(Serializable id)  {
		return this.getHibernateTemplate().get(pamareClass, id);
	}

	public List<T> findAll() {
		String hql = "FROM " + pamareClass.getSimpleName();
		return (List<T>) this.getHibernateTemplate().find(hql);
	}
	
	@Override
	public void executeUpdate(String queryName, Object... objects) {
		Session currentSession = this.getSessionFactory().getCurrentSession();
		Query query = currentSession.getNamedQuery(queryName);
		int i = 0;
		for (Object object : objects) {
			//为HQL语句中的？赋值
			query.setParameter(i++, object);
		}
		//执行更新
		query.executeUpdate();
	}

	@Override
	public void pageQuery(PageBean<T> pageBean) {
		int currentPage = pageBean.getCurrentPage();
		int currentRows = pageBean.getCurrentRows();
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		//查询总记录数
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> countList = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		pageBean.setTotal(countList.get(0).intValue());
		//查询相关的数据
		int first=(currentPage-1)*currentRows;
		detachedCriteria.setProjection(null);
		//设置封装对象方式
		detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
		List<T> findByCriteria = (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria, first, currentRows);
		pageBean.setRows(findByCriteria);
	}

	@Override
	public void executeSql(String sql) {
		//Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List<T> findByCriterria(DetachedCriteria detachedCriteria) {
		List<T> findByCriteria = (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		return findByCriteria;
	}

	
}
