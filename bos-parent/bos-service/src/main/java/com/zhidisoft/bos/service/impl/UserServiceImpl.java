package com.zhidisoft.bos.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zhidisoft.bos.dao.IUserDao;
import com.zhidisoft.bos.domain.Role;
import com.zhidisoft.bos.domain.User;
import com.zhidisoft.bos.service.IUserService;
import com.zhidisoft.bos.utils.MD5Utils;
import com.zhidisoft.bos.utils.PageBean;

@Service
public class UserServiceImpl implements IUserService {
	
	@Resource
	private IUserDao userDao;

	@Override
	public User login(User model) {
		String username = model.getUsername();
		String password = model.getPassword();
		password=MD5Utils.md5(password);
		return userDao.login(username, password);
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void updateUser(User loginUser) {
		userDao.update(loginUser);
	}

	@Transactional(readOnly=false)
	public void save(User model, String[] roleIds) {
		model.setPassword(MD5Utils.md5(model.getPassword()));
		userDao.save(model);
		if (roleIds!=null&&roleIds.length>0) {
			for (String roleId : roleIds) {
				Role role=new Role();
				role.setId(roleId);
				model.getRoles().add(role);
			}
		}
	}

	@Override
	public void pageQuery(PageBean<User> pageBean) {
		userDao.pageQuery(pageBean);
	}

}
