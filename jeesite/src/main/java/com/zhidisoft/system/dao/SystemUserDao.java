package com.zhidisoft.system.dao;

import com.zhidisoft.system.entity.SystemUser;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SystemUserDao {
    int deleteByPrimaryKey(@Param("uids")String[] uids);

    int insert(SystemUser record);

    SystemUser selectByPrimaryKey(Serializable id);

    List<SystemUser> selectAll();

    int updateByPrimaryKey(SystemUser record);

	SystemUser getUserByUsername(String username);

	Integer editUser(@Param("user")SystemUser user);

	Integer modifypassword(@Param("user")SystemUser user);

	List<SystemUser> getListByPage(@Param("page")Integer page,@Param("rows") Integer rows,@Param("username")String username, @Param("phone")String phone, @Param("status")String status);

	Integer count(@Param("username")String username, @Param("phone")String phone, @Param("status")String status);

	void insertIntoUserRoles(@Param("uid")Integer uid, @Param("userRoleList")String[] userRoleList);

	void removeFromUserRole(Integer id);
}