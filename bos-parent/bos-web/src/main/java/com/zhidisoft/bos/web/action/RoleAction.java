package com.zhidisoft.bos.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zhidisoft.bos.domain.Function;
import com.zhidisoft.bos.domain.Role;
import com.zhidisoft.bos.service.IRoleService;
import com.zhidisoft.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	private String functionIds;
	
	@Resource
	private IRoleService roleService;

	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}
	
	
	public String addRole() {
		//获得功能节点的字符串
		if (StringUtils.isNotBlank(functionIds)) {
			String[] strings = functionIds.split(",");
			for (String string : strings) {
				Function function = new Function(string);
				model.getFunctions().add(function);
			}
		}
		roleService.save(model);
		return "list";
	}
	
	public void list() {
		roleService.queryByPage(pageBean);
		java2json(pageBean, new String[]{"currentPage","currentRows","detachedCriteria","code","functions","users"});
	}
}
