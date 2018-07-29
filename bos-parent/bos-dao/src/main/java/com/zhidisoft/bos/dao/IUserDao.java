package com.zhidisoft.bos.dao;

import com.zhidisoft.bos.dao.base.IbaseDao;
import com.zhidisoft.bos.domain.User;

public interface IUserDao extends IbaseDao<User>{
	
	public User login(String username,String password);

	public User findUserByUsername(String username);
}
