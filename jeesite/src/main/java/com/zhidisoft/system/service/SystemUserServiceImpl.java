package com.zhidisoft.system.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhidisoft.system.dao.SystemUserDao;
import com.zhidisoft.system.entity.SystemUser;

@Service
public class SystemUserServiceImpl implements SystemUserService {
	
	@Resource
	SystemUserDao userDao;
	
	public SystemUser getUserById(Integer userId) {
		return userDao.selectByPrimaryKey(userId);
	}

	public SystemUser getUserByUsername(String username) {
		
		return userDao.getUserByUsername(username);
	}

	@Override
	public Boolean editUser(SystemUser user) {
		Integer i= userDao.editUser(user);
		return i>0;
	}

	@Override
	public Boolean modifypassword(SystemUser user) {
		Integer i= userDao.modifypassword(user);
		return i>0;
	}

	@Override
	public Integer count(String username, String phone, String status) {
		
		return userDao.count(username,phone,status);
	}

	@Override
	public List<SystemUser> getListByPage(Integer page, Integer rows,String username, String phone, String status) {
		if (page==null||page<1) {
			page=1;
		}
		if (rows==null||rows<1) {
			rows=3;
		}
		
		return userDao.getListByPage((page-1)*rows,rows,username,phone,status);
	}

	@Override
	public Integer saveUser(SystemUser user) {
		
		return userDao.insert(user);
	}

	@Override
	public void insertIntoUserRoles(Integer uid, String[] userRoleList) {
		userDao.insertIntoUserRoles(uid,userRoleList);
	}

	@Override
	public SystemUser selectUserById(Serializable id) {
		
		return userDao.selectByPrimaryKey(id);
	}

	@Override
	public void removeFromUserRole(Integer id) {
		userDao.removeFromUserRole(id);
	}

	@Override
	public void delete(String[] uids) {
		userDao.deleteByPrimaryKey(uids);
	}

	@Override
	public SystemUser selectByUserName(String username) {
		return userDao.getUserByUsername(username);
	}
}
