package com.zhidisoft.bos.dao.impl;

import org.springframework.stereotype.Repository;

import com.zhidisoft.bos.dao.IDecidedZoneDao;
import com.zhidisoft.bos.dao.base.impl.BaseDaoImpl;
import com.zhidisoft.bos.domain.Decidedzone;

@Repository
public class DecidedZoneDao extends BaseDaoImpl<Decidedzone> implements IDecidedZoneDao {

	@Override
	public void addDecidedZone(Decidedzone model) {
		this.getHibernateTemplate().save(model);
	}

}
