package com.zhidisoft.bos.dao;

import java.util.List;

import com.zhidisoft.bos.dao.base.IbaseDao;
import com.zhidisoft.bos.domain.Subarea;

public interface ISubAreaDao extends IbaseDao<Subarea> {

	List<Object> findSubareasGroupByProvince();

}
