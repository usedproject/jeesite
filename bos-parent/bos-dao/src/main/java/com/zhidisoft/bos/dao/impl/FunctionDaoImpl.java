package com.zhidisoft.bos.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.zhidisoft.bos.dao.IFunctionDao;
import com.zhidisoft.bos.dao.base.impl.BaseDaoImpl;
import com.zhidisoft.bos.domain.Function;
import com.zhidisoft.bos.utils.PageBean;

@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements IFunctionDao {

	@Override
	public List<Function> findTop() {
		String hql="From Function where parentFunction is null";
		return (List<Function>) this.getHibernateTemplate().find(hql);
	}

	@Override
	public List<Function> findFunctionListById(String id) {
		String hql="select f from Function f left outer join f.roles r "
				+ "left outer join r.users u where u.id=?";
		return (List<Function>) this.getHibernateTemplate().find(hql, id);
	}

	@Override
	public List<Function> findMenu(String id) {
		String hql="select destinct f from Function f left outer join f.roles r "
				+ "left outer join r.users u where u.id=? and f.generatemenu=1 order by f.zindex desc";
		return (List<Function>) this.getHibernateTemplate().find(hql, id);
	}
	
	public List<Function> findAllMenu() {
		String hql="select f from Function f  where  f.generatemenu=1 order by f.zindex desc";
		return (List<Function>) this.getHibernateTemplate().find(hql);
	}
	

}
