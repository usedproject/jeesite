package com.zhidisoft.bos.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhidisoft.bos.dao.IRegionDao;
import com.zhidisoft.bos.dao.base.impl.BaseDaoImpl;
import com.zhidisoft.bos.domain.Region;
import com.zhidisoft.bos.utils.PageBean;

@Repository
public class RegionDao extends BaseDaoImpl<Region> implements IRegionDao {

	
	public List<Region> findByQ(String q) {
		q=q.trim();
		String hql="FROM Region r WHERE r.shortcode LIKE ? "
				+ "	OR r.citycode LIKE ? OR r.province LIKE ? "
				+ "OR r.city LIKE ? OR r.district LIKE ?";
		return (List<Region>) this.getHibernateTemplate().find(hql, "%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%");
	}
}
