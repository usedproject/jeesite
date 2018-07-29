package com.zhidisoft.bos.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhidisoft.bos.dao.IWorkordermanageDao;
import com.zhidisoft.bos.domain.Workordermanage;
import com.zhidisoft.bos.service.IWorkordermanageService;

@Service
public class WorkOrderManagerServiceImpl implements IWorkordermanageService {

	@Resource
	private IWorkordermanageDao workordermanageDao;
	@Transactional(readOnly=false)
	public void save(Workordermanage model) {
		workordermanageDao.save(model);
	}

}
