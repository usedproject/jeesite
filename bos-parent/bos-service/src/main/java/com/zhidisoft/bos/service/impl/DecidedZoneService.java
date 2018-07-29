package com.zhidisoft.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhidisoft.bos.dao.IDecidedZoneDao;
import com.zhidisoft.bos.dao.ISubAreaDao;
import com.zhidisoft.bos.domain.Decidedzone;
import com.zhidisoft.bos.domain.Subarea;
import com.zhidisoft.bos.service.IDecidedZoneService;
import com.zhidisoft.bos.utils.PageBean;

@Service
@Transactional
public class DecidedZoneService implements IDecidedZoneService {

	@Resource
	private IDecidedZoneDao decidedZoneDao;
	@Resource
	private ISubAreaDao subAreaDao;

	@Override
	@Transactional(readOnly=false)
	public void addDecidedZone(Decidedzone model, String[] subareaId) {
		decidedZoneDao.addDecidedZone(model);
		for (String id : subareaId) {
			Subarea subarea = subAreaDao.findById(id);
			subarea.setDecidedzone(model);
		}
	}

	@Override
	public void queryByPageBean(PageBean<Decidedzone> pageBean) {
		 decidedZoneDao.pageQuery(pageBean);
	}
}
