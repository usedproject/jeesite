package com.zhidisoft.bos.dao;

import java.util.List;

import com.zhidisoft.bos.dao.base.IbaseDao;
import com.zhidisoft.bos.domain.Region;

public interface IRegionDao extends IbaseDao<Region> {

	List<Region> findByQ(String q);

}
