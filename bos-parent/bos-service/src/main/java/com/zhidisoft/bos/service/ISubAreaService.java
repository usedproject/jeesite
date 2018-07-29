package com.zhidisoft.bos.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.zhidisoft.bos.domain.Subarea;
import com.zhidisoft.bos.utils.PageBean;

public interface ISubAreaService {

	void save(Subarea model);

	void listSubArea(PageBean<Subarea> pageBean);

	List<Subarea> findAll();

	List<Subarea> findNotAssociated(DetachedCriteria detachedCriteria);

	List<Subarea> findAssociatedById(String id);

	List<Object> findSubareasGroupByProvince();


}
