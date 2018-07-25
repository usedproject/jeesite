package com.zhidisoft.system.service;

import java.io.Serializable;
import java.util.List;

import com.zhidisoft.system.entity.SystemRole;
import com.zhidisoft.system.entity.SystemUser;

public interface SystemRoleService {

	List<SystemRole> getRoleListByUserId(Serializable id);

	List<SystemRole> getRoleList();

	void removeFromUserRole(String[] uids);

	void insertIntoUserRole(String[] uids, String[] rids);

	List<SystemRole> getRoleListByPage(Integer page, Integer rows, String rolename);

	Integer count(String rolename);

	void saveRole(SystemRole role, SystemUser user);

	void insertIntoRoleFunctuion(Integer id, String[] functions);

	SystemRole selectByRoleId(Serializable rid);

	void editRole(SystemRole role, String functionIds, SystemUser user);

	void delete(String[] roleIds);


}
