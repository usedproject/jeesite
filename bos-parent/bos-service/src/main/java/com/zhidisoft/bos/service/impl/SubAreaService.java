package com.zhidisoft.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhidisoft.bos.dao.ISubAreaDao;
import com.zhidisoft.bos.domain.Subarea;
import com.zhidisoft.bos.service.ISubAreaService;
import com.zhidisoft.bos.utils.PageBean;

@Service
@Transactional
public class SubAreaService implements ISubAreaService {

	@Resource
	private ISubAreaDao subAreaDao;
	
	@Transactional(readOnly=false)
	public void save(Subarea model) {
		subAreaDao.save(model);
	}

	@Override
	public void listSubArea(PageBean<Subarea> pageBean) {
		subAreaDao.pageQuery(pageBean);
	}

	@Override
	public List<Subarea> findAll() {
		List<Subarea> subareas = subAreaDao.findAll();
		return subareas;
	}

	@Override
	public List<Subarea> findNotAssociated(DetachedCriteria detachedCriteria) {
		List<Subarea> findNotAssociated = subAreaDao.findByCriterria(detachedCriteria);
		return findNotAssociated;
	}

	@Override
	public List<Subarea> findAssociatedById(String id) {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Subarea.class);
		detachedCriteria.add(Restrictions.eq("decidedzone.id", id));
		return subAreaDao.findByCriterria(detachedCriteria);
	}

	@Override
	public List<Object> findSubareasGroupByProvince() {
		return subAreaDao.findSubareasGroupByProvince();
	}

}
