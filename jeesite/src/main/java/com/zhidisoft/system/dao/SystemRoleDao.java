package com.zhidisoft.system.dao;

import com.zhidisoft.system.entity.SystemRole;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SystemRoleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(@Param("role")SystemRole role);

    SystemRole selectByPrimaryKey(@Param("id")Serializable rid);

    List<SystemRole> selectAll();

    int updateByPrimaryKey(SystemRole record);

	List<SystemRole> getRoleListByUserId(Serializable id);

	void removeFromUserRole(@Param("uids")String[] uids);

	void insertIntoUserRole(@Param("uids")String[] uids, @Param("rids")String[] rids);

	List<SystemRole> getRoleListByPage(@Param("page")Integer page, @Param("rows")Integer rows, @Param("rolename")String rolename);

	Integer count(@Param("rolename")String rolename);

	void insertIntoRoleFunctuion(@Param("rid")Integer rid, @Param("functionIds")String[] functionIds);

	void removeFromRoleFunction(@Param("rid")Integer rid);
	/**
	 * 批量删除
	 * @param roleIds
	 */
	void deleteByIds(@Param("roleIds")String[] roleIds);


}