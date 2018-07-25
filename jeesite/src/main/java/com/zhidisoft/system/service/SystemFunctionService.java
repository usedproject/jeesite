package com.zhidisoft.system.service;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.zhidisoft.system.entity.SystemFunction;
import com.zhidisoft.system.entity.SystemRole;
import com.zhidisoft.utils.EasyuiTreeNode;
import com.zhidisoft.utils.ZtreeNode;

public interface SystemFunctionService {

	List<SystemFunction> getFunctionListByRoles(List<SystemRole> roles);

	List<EasyuiTreeNode> getFunctionListByRolesAndId(Integer id, List<SystemRole> roles, Integer funcType, Integer status);

	List<EasyuiTreeNode> getListByParentId(Integer id);

	List<SystemFunction> getListByRoleId(Serializable rid);

	List<SystemFunction> getFunctionListByPage(Integer page, Integer rows, String funcname);

	Integer count(String funcname);

	List<ZtreeNode> selectAll();

	String getFuncNameById(String id);

	Integer addFunction(SystemFunction function,HttpSession session);

	SystemFunction getFunctionById(Serializable id);

	Integer editFunction(SystemFunction function,HttpSession session);

	void delete(String[] ids);

}
