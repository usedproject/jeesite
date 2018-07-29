package com.zhidisoft.bos.web.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zhidisoft.bos.domain.Function;
import com.zhidisoft.bos.domain.User;
import com.zhidisoft.bos.service.IFunctionService;
import com.zhidisoft.bos.utils.BosUtils;
import com.zhidisoft.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {
	
	@Resource
	private IFunctionService functionService;

	public void ajaxList() {
		List<Function> functions= functionService.findTop();
		java2json(functions, new String[]{"parentFunction","code","description","page","generatemenu","zindex","roles"});
	}
	
	public String addFunction() {
		functionService.save(model);
		return "list";
	}
	
	public void list() {
		String page = model.getPage();
		if (page==null) {
			pageBean.setCurrentPage(0);
		}else{
			pageBean.setCurrentPage(Integer.parseInt(page));
		}
		functionService.pageQuery(pageBean);
		java2json(pageBean, new String[]{"currentPage","currentRows",
				"detachedCriteria","parentFunction","code","zindex","roles","children"});
	}
	
	public void findMenu() {
		//获得session中登录用户
		User loginUser = BosUtils.getLoginUser();
		String id = loginUser.getId();
		List<Function> functions=null;
		if (loginUser.getUsername().equals("admin")) {
			functions=functionService.findAllMenu();
		}else {
			functions=functionService.findMenu(id);
		}
		java2json(functions, new String[]{"parentFunction","roles","children"});
	}
}
