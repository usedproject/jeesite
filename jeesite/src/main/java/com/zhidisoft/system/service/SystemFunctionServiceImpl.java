package com.zhidisoft.system.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.zhidisoft.system.dao.SystemFunctionDao;
import com.zhidisoft.system.entity.SystemFunction;
import com.zhidisoft.system.entity.SystemRole;
import com.zhidisoft.system.entity.SystemUser;
import com.zhidisoft.utils.EasyuiTreeNode;
import com.zhidisoft.utils.ZtreeNode;

@Service
public class SystemFunctionServiceImpl implements SystemFunctionService {

	@Resource
	SystemFunctionDao functionDao;
	
	@Override
	public List<SystemFunction> getFunctionListByRoles(List<SystemRole> roles) {
		
		List<Integer> list =new ArrayList<Integer>();
		
		for (SystemRole systemRole : roles) {
			list.add(systemRole.getId());
		}
		
		return functionDao.getFunctionListByRoles(list);
	}

	@Override
	public List<EasyuiTreeNode> getFunctionListByRolesAndId(Integer id, List<SystemRole> roles,Integer funcType,Integer status) {
		 List<SystemFunction> functions = functionDao.getFunctionListByRolesAndId(id,roles,funcType,status);
		 List<EasyuiTreeNode> list=new ArrayList<EasyuiTreeNode>();
		 if (functions!=null&&functions.size()>0) {
			for (SystemFunction function : functions) {
				EasyuiTreeNode easyuiTreeNode=new EasyuiTreeNode();
				easyuiTreeNode.setId(function.getId());
				easyuiTreeNode.setText(function.getFuncname());
				if (function.getFuncurl()!=null&&function.getFuncurl().length()>0) {
					easyuiTreeNode.getAttributes().put("url", function.getFuncurl());
				}else {
					easyuiTreeNode.setState("closed");
				}
				list.add(easyuiTreeNode);
			}
		}
		 
		 return list;
	}

	@Override
	public List<EasyuiTreeNode> getListByParentId(Integer id) {
		List<SystemFunction> functions= functionDao.getFunctionListByParentId(id);
		List<EasyuiTreeNode> list=new ArrayList<EasyuiTreeNode>();
		 if (functions!=null&&functions.size()>0) {
			for (SystemFunction function : functions) {
				EasyuiTreeNode easyuiTreeNode=new EasyuiTreeNode();
				easyuiTreeNode.setId(function.getId());
				easyuiTreeNode.setText(function.getFuncname());
				if (function.getFuncurl()!=null&&function.getFuncurl().length()>0) {
					easyuiTreeNode.getAttributes().put("url", function.getFuncurl());
				}else {
					easyuiTreeNode.setState("closed");
				}
				list.add(easyuiTreeNode);
			}
		}
		 return list;
	}

	@Override
	public List<SystemFunction> getListByRoleId(Serializable id) {
		
		return functionDao.getListByRoleId(id);
	}

	@Override
	public List<SystemFunction> getFunctionListByPage(Integer page, Integer rows, String funcname) {
		
		return functionDao.getFunctionListByPage((page-1)*rows,rows,funcname);
	}

	@Override
	public Integer count(String funcname) {
		return functionDao.count(funcname);
	}

	@Override
	public List<ZtreeNode> selectAll() {
		List<SystemFunction> all = functionDao.selectAll();
		List<ZtreeNode> list=new ArrayList<>();
		for (SystemFunction function : all) {
			ZtreeNode ztreeNode=new ZtreeNode();
			ztreeNode.setId(function.getId());
			ztreeNode.setName(function.getFuncname());
			ztreeNode.setNocheck(false);
			if (function.getParentfunction()!=null) {
				ztreeNode.setpId(function.getParentfunction().getId());
			}else {
				ztreeNode.setpId(null);
			}
			
			list.add(ztreeNode);
		}
		return list;
	}

	@Override
	public String getFuncNameById(String id) {
		return functionDao.getFuncNameById(id);
	}

	@Override
	public Integer addFunction(SystemFunction function,HttpSession session) {
		SystemUser user = (SystemUser) session.getAttribute("user");
		if (function.getFuncurl()!=null&& function.getFuncurl()=="") {
			function.setFuncurl(null);
		}
		function.setCreateby(user.getId());
		function.setCreatetime(new Date());
		return functionDao.insert(function);
	}

	@Override
	public SystemFunction getFunctionById(Serializable id) {
		return functionDao.selectById(id);
	}

	@Override
	public Integer editFunction(SystemFunction function,HttpSession session) {
		function.setUpdatetime(new Date());
		SystemUser user = (SystemUser) session.getAttribute("user");
		function.setUpdateby(user.getId());
		return functionDao.updateByPrimaryKey(function);
	}

	@Override
	public void delete(String[] ids) {
		functionDao.deleteByIds(ids);
	}

}
