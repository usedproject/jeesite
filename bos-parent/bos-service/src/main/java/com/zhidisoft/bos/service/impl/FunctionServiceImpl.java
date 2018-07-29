package com.zhidisoft.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhidisoft.bos.dao.IFunctionDao;
import com.zhidisoft.bos.domain.Function;
import com.zhidisoft.bos.service.IFunctionService;
import com.zhidisoft.bos.utils.PageBean;

@Service
public class FunctionServiceImpl implements IFunctionService {

	@Resource
	private IFunctionDao functionDao;
	
	@Override
	public List<Function> findAll() {
		return functionDao.findAll();
	}

	@Override
	@Transactional(readOnly=false)
	public void save(Function model) {
		String id = model.getParentFunction().getId();
		if (id.equals("")) {
			model.setParentFunction(null);
		}
		functionDao.save(model);
	}
	
	@Override
	public void pageQuery(PageBean<Function> pageBean) {
		
		functionDao.pageQuery(pageBean);
	}

	@Override
	public List<Function> findTop() {
		
		return functionDao.findTop();
	}

	@Override
	public List<Function> findMenu(String id) {
		
		return functionDao.findMenu(id);
	}

	@Override
	public List<Function> findAllMenu() {
		return functionDao.findAllMenu();
	}
}
