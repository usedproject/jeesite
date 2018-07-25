package com.zhidisoft.system.service;

import java.io.Serializable;
import java.util.List;

import com.zhidisoft.system.entity.SystemUser;

public interface SystemUserService {

	SystemUser getUserByUsername(String username);

	Boolean editUser(SystemUser user);

	Boolean modifypassword(SystemUser user);

	Integer count(String username, String phone, String status);

	List<SystemUser> getListByPage(Integer page, Integer rows, String username, String phone, String status);

	Integer saveUser(SystemUser user);

	void insertIntoUserRoles(Integer uid, String[] userRoleList);

	SystemUser selectUserById(Serializable id);

	void removeFromUserRole(Integer id);

	void delete(String[] uids);

	SystemUser selectByUserName(String username);

}
