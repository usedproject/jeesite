package com.zhidisoft.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhidisoft.bos.dao.IWorkBillDao;
import com.zhidisoft.bos.domain.Workbill;
import com.zhidisoft.bos.service.IWorkBillService;

@Service
public class WorkBillServiceImpl  implements IWorkBillService{
	
	@Resource
	private IWorkBillDao workBillDao;

	@Transactional(readOnly=false)
	public List<Workbill> findAll() {
		return workBillDao.findAll();
	}

}
