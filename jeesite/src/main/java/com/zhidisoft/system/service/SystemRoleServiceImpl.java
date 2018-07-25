package com.zhidisoft.system.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhidisoft.system.dao.SystemRoleDao;
import com.zhidisoft.system.entity.SystemRole;
import com.zhidisoft.system.entity.SystemUser;

@Service
public class SystemRoleServiceImpl implements SystemRoleService {

	@Resource
	SystemRoleDao roleDao;
	
	@Override
	public List<SystemRole> getRoleListByUserId(Serializable id) {
		return roleDao.getRoleListByUserId(id);
	}

	@Override
	public List<SystemRole> getRoleList() {
		return roleDao.selectAll();
	}

	@Override
	public void removeFromUserRole(String[] uids) {
		roleDao.removeFromUserRole(uids);
	}

	@Override
	public void insertIntoUserRole(String[] uids, String[] rids) {
		roleDao.insertIntoUserRole(uids,rids);
	}

	@Override
	public List<SystemRole> getRoleListByPage(Integer page, Integer rows, String rolename) {
		return roleDao.getRoleListByPage((page-1)*rows,rows,rolename);
	}

	@Override
	public Integer count(String rolename) {
		return roleDao.count(rolename);
	}

	@Override
	public void saveRole(SystemRole role,SystemUser user) {
		role.setCreatetime(new Date());
		role.setCreateby(user.getId());
		roleDao.insert(role);
	}

	@Override
	public void insertIntoRoleFunctuion(Integer id, String[] functions) {
		roleDao.insertIntoRoleFunctuion(id,functions);
	}

	@Override
	public SystemRole selectByRoleId(Serializable rid) {
		return roleDao.selectByPrimaryKey(rid);
	}

	@Override
	public void editRole(SystemRole role,String functionIds,SystemUser user) {
		role.setUpdatetime(new Date());
		role.setUpdateby(user.getId());
		roleDao.updateByPrimaryKey(role);
		String[] functions = functionIds.split(",");
		roleDao.removeFromRoleFunction(role.getId());
		roleDao.insertIntoRoleFunctuion(role.getId(), functions);
	}

	@Override
	public void delete(String[] roleIds) {
		roleDao.deleteByIds(roleIds);
		
	}

}
