package com.zhidisoft.bos.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhidisoft.bos.dao.ISubAreaDao;
import com.zhidisoft.bos.dao.base.impl.BaseDaoImpl;
import com.zhidisoft.bos.domain.Subarea;

@Repository
public class SubAreaDao extends BaseDaoImpl<Subarea> implements ISubAreaDao {

	@Override
	public List<Object> findSubareasGroupByProvince() {
		String hql="select r.province,count(1) from Subarea s left OUTER join s.region r group by r.province";
		List<Object> list = (List<Object>) this.getHibernateTemplate().find(hql);
		return list;
	}


	
}
