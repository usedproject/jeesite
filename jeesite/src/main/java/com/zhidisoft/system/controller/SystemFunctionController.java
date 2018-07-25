package com.zhidisoft.system.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhidisoft.system.entity.SystemFunction;
import com.zhidisoft.system.entity.SystemRole;
import com.zhidisoft.system.service.SystemFunctionService;
import com.zhidisoft.utils.EasyuiTreeNode;
import com.zhidisoft.utils.JsonResult;
import com.zhidisoft.utils.ZtreeNode;
/**
 * 这是功能模块的controller层
 * @author 张磊
 * @date 2018年3月16日
 */
@Controller
public class SystemFunctionController {
	
	@Resource
	SystemFunctionService functionService;
	
	/**
	 * 这是用户权限树状结构的后端方法，异步加载
	 * @param id 传入的是父权限的id
	 * @return
	 */
	@RequestMapping("/function/list")
	@ResponseBody
	public JsonResult list(Integer id) {
		List<EasyuiTreeNode> functions=	functionService.getListByParentId(id);
		return JsonResult.buildSuccessResult("成功", functions);
	}
	
	/**
	 * 这是用于展示所有权限的方法，同步加载
	 * @param rid 传入角色的id，进行判断之后将节点信息的相关属性设置
	 * @return
	 */
	@RequestMapping("/function/listAll")
	@ResponseBody
	public JsonResult listAll(String rid) {
		List<EasyuiTreeNode> functions=	functionService.getListByParentId(null);
		List<SystemFunction> roleFunctions = functionService.getListByRoleId(rid);
		setChildren(functions,roleFunctions);
		return JsonResult.buildSuccessResult("成功", functions);
	}
	
	/**
	 * 这是同步加载树状节点的方法内部的递归方法
	 * @param functions 全部的功能列表
	 * @param roleFunctions 指定用户的功能列表
	 */
	public void setChildren(List<EasyuiTreeNode> functions,List<SystemFunction> roleFunctions) {
		for (EasyuiTreeNode easyuiTreeNode : functions) {
			for (SystemFunction systemFunction : roleFunctions) {
				if (systemFunction.getId()==easyuiTreeNode.getId()) {
					easyuiTreeNode.setChecked(true);
				}
			}
			if (easyuiTreeNode.getAttributes().get("url")==null) {
				List<EasyuiTreeNode> children = functionService.getListByParentId(easyuiTreeNode.getId());
				easyuiTreeNode.setChildren(children);
				setChildren(children,roleFunctions);
			}
		}
	}
	
	/**
	 * 这是用于菜单列表跳转的方法
	 * @return
	 */
	@RequestMapping("/company/system/function")
	public String toFunctionList() {
		return "functionInfo/listFunction";
	}
	
	/**
	 * 这是菜单模块的分页功能
	 * @param page 第几页
	 * @param rows 第几行
	 * @param funcname
	 * @return
	 */
	@RequestMapping("/system/function/getList")
	@ResponseBody
	public JsonResult getList(Integer page,Integer rows,String funcname) {
		List<SystemFunction> functions=functionService.getFunctionListByPage(page,rows,funcname);
		Integer total=functionService.count(funcname);
		Map<String, Object> map=new HashMap<>();
		map.put("total", total);
		map.put("rows", functions);
		return JsonResult.buildSuccessResult("成功", map);
	}
	
	/**
	 * 这是增加菜单按钮的页面跳转
	 * @return
	 */
	@RequestMapping("/system/function/toAddFunction")
	public String toAddFunction() {
		return "functionInfo/addFunction";
	}
	
	/**
	 * 选择上级菜单的跳转页面
	 * @return
	 */
	@RequestMapping("/toSelectFunction")
	public String toSelectFunction() {
		return "functionInfo/selectFunction";
	}
	
	/**
	 * 获取ZTree节点的方法
	 * @return
	 */
	@RequestMapping("/company/system/getZtreeNode")
	@ResponseBody
	public List<ZtreeNode> getZtreeNode() {
		List<ZtreeNode> ztreeNodes= functionService.selectAll();
		return ztreeNodes;
	}
	
	/**
	 * 这是通过id获得菜单名的方法
	 * @param id
	 * @return
	 */
	@RequestMapping("/company/system/getFuncNameById")
	@ResponseBody
	public JsonResult getFuncNameById(String id) {
		String name=functionService.getFuncNameById(id);
		return JsonResult.buildSuccessResult("", name);
	}
	/**
	 * 这是用于实现增加菜单的功能
	 * @param function
	 * @return
	 */
	@RequestMapping("/system/function/add")
	@ResponseBody
	public JsonResult addFunction(SystemFunction function,Integer parentid,HttpSession session) {
		SystemFunction parentfunction = new SystemFunction();
		parentfunction.setId(parentid);
		function.setParentfunction(parentfunction);
		Integer i = functionService.addFunction(function,session);
		if (i>0) {
			return JsonResult.buildSuccessResult("添加成功");
		}else {
			return JsonResult.buildFailureResult("添加失败");
		}
	}
	
	/**
	 * 这是进行修改菜单的功能页面跳转
	 * @param id   传入的id用于进行被修改菜单信息获取
	 * @param model
	 * @return
	 */
	@RequestMapping("/system/user/toeditFunction")
	public String toEditFunction(Integer id,Model model) {
	    SystemFunction function=functionService.getFunctionById(id);
	    model.addAttribute("fc", function);
	    System.out.println(function);
	    return "functionInfo/editFunction";
	}
	
	/**
	 * 这是菜单模块的修改功能的实现方法
	 * @param function
	 * @param parentid
	 * @return
	 */
	@RequestMapping("/system/function/edit")
	@ResponseBody
	public JsonResult editFunction(SystemFunction function,Integer parentid,HttpSession session) {
		SystemFunction parentfunction = new SystemFunction();
		parentfunction.setId(parentid);
		function.setParentfunction(parentfunction);
	    Integer i= functionService.editFunction(function,session);
	    if (i>0) {
			return JsonResult.buildSuccessResult("修改成功");
		}
	    return JsonResult.buildFailureResult("修改失败");
	}
	
	/**
	 * 批量删除功能
	 * @param functionIds
	 * @return
	 */
	@RequestMapping("/system/function/delete")
	@ResponseBody
	public JsonResult delete(String functionIds) {
		String[] ids = functionIds.split(",");
		functionService.delete(ids);
		return JsonResult.buildSuccessResult("删除成功");
	}
}
