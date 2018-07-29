package com.zhidisoft.bos.dao;

import com.zhidisoft.bos.dao.base.IbaseDao;
import com.zhidisoft.bos.domain.Decidedzone;

public interface IDecidedZoneDao extends IbaseDao<Decidedzone> {

	void addDecidedZone(Decidedzone model);

}
