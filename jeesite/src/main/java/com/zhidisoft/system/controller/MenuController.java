package com.zhidisoft.system.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhidisoft.system.entity.SystemFunction;
import com.zhidisoft.system.entity.SystemRole;
import com.zhidisoft.system.service.SystemFunctionService;
import com.zhidisoft.utils.EasyuiTreeNode;
import com.zhidisoft.utils.JsonResult;
/**
 * 这是菜单模块，用于加载主页中的树状菜单
 * @author 张磊
 * @date 2018年3月16日
 */
@RequestMapping("/menu")
@Controller
public class MenuController {
	
	@Resource
	SystemFunctionService functionService;

	/*
	 * 登录之后展示功能树的列表，其中id表示展开树时当前树自身的id
	 */
	@RequestMapping("/list")
	@ResponseBody
	public JsonResult list(HttpSession session,Integer id,Integer funcType,Integer status) {
		//获得session中的SystemRole的列表
		List<SystemRole> roles= (List<SystemRole>) session.getAttribute("roles");
		//通过id，跟角色列表获得function列表
		List<EasyuiTreeNode> treeNodes= functionService.getFunctionListByRolesAndId(id,roles,funcType,status);
		return JsonResult.buildSuccessResult("成功", treeNodes);
	}
	
}
