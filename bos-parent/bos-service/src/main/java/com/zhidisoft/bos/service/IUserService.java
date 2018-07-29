package com.zhidisoft.bos.service;

import com.zhidisoft.bos.domain.User;
import com.zhidisoft.bos.utils.PageBean;

public interface IUserService {
	
	User login(User model);

	void updateUser(User loginUser);

	void save(User model, String[] roleIds);

	void pageQuery(PageBean<User> pageBean);

}
