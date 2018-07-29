package com.zhidisoft.bos.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhidisoft.bos.dao.IRoleDao;
import com.zhidisoft.bos.domain.Role;
import com.zhidisoft.bos.service.IRoleService;
import com.zhidisoft.bos.utils.PageBean;

@Service
public class RoleServiceImpl implements IRoleService {
	
	@Resource
	private IRoleDao roleDao;

	@Transactional(readOnly=false)
	public void save(Role model) {
		roleDao.save(model);
	}

	@Override
	public void queryByPage(PageBean<Role> pageBean) {
		roleDao.pageQuery(pageBean);
	}

}
