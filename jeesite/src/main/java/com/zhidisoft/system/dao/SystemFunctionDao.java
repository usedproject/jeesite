package com.zhidisoft.system.dao;

import com.zhidisoft.system.entity.SystemFunction;
import com.zhidisoft.system.entity.SystemRole;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SystemFunctionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemFunction record);

    SystemFunction selectByPrimaryKey(Serializable id);

    SystemFunction selectById(Serializable id);
    
    List<SystemFunction> selectAll();

    int updateByPrimaryKey(SystemFunction record);

	List<SystemFunction> getFunctionListByRoles(@Param("list")List<Integer> list);

	List<SystemFunction> getFunctionListByRolesAndId(@Param("id")Integer id, @Param("roles")List<SystemRole> roles, @Param("funcType")Integer funcType, @Param("status")Integer status);

	List<SystemFunction> getFunctionListByParentId(@Param("id")Integer id);

	List<SystemFunction> getListByRoleId(@Param("id")Serializable id);

	List<SystemFunction> getFunctionListByPage(@Param("page")Integer page, @Param("rows")Integer rows, @Param("funcname")String funcname);

	Integer count(@Param("funcname")String funcname);

	String getFuncNameById(@Param("id")String id);

	void deleteByIds(@Param("ids")String[] ids);
}