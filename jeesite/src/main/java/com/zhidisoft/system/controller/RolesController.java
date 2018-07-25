package com.zhidisoft.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhidisoft.system.entity.SystemRole;
import com.zhidisoft.system.entity.SystemUser;
import com.zhidisoft.system.service.SystemRoleService;
import com.zhidisoft.utils.JsonResult;

/**
 * 这是用于角色控制模块的实现controller层
 * @author 张磊
 * @date 2018年3月16日
 */
@Controller
public class RolesController {
	
	@Resource
	SystemRoleService roleService;
	
	/**
	 * 这是进行用户分配按钮的功能的页面跳转
	 * @param userIds 勾选的用户的id字符串
	 * @param model 
	 * @return
	 */
	@RequestMapping("system/role/toroleList")
	public String toroleList(String userIds,Model model) {
		model.addAttribute("userIds", userIds);
		return "roleInfo/rolelist";
	}
	
	/**
	 * 这是获取所有角色列表的ajax方法
	 * @return
	 */
	@RequestMapping("/system/role/getroleList")
	@ResponseBody
	public JsonResult getroleList() {
		List<SystemRole> roles= roleService.getRoleList();
		return JsonResult.buildSuccessResult("成功", roles);
	}
	
	/**
	 * 这是用户分配按钮功能实现的实现
	 * @param userIds 被勾选用户的id字符串
	 * @param roles  被勾选的角色id字符串
	 * @return
	 */
	@RequestMapping("/system/user_roles/save")
	@ResponseBody
	public JsonResult userRolesSave(String userIds,String roles) {
		String[] uids = userIds.split(",");
		roleService.removeFromUserRole(uids);
		if (roles!=null&&roles.length()>0) {
			String[] rids = roles.split(",");
			roleService.insertIntoUserRole(uids,rids);
		}
		return JsonResult.buildSuccessResult("分配成功");
	}

	/**
	 * 这是通过用户id进行获取角色信息列表的方法
	 * @param userIds
	 * @return
	 */
	@RequestMapping("/system/role/rolelist")
	@ResponseBody
	public JsonResult rolelist(String userIds) {
		List<SystemRole> roles=roleService.getRoleListByUserId(userIds);
		return JsonResult.buildSuccessResult("成功", roles);
	}
	
	/**
	 * 这是系统角色信息的页面跳转
	 * @return
	 */
	@RequestMapping("/company/system/systemRole")
	public String toRoleList() {
		return "roleInfo/listRole";
	}
	
	/**
	 * 这是角色信息列表的分页的实现
	 * @param page 		第几页
	 * @param rows		展示几行
	 * @param rolename  查询条件
	 * @return
	 */
	@RequestMapping("/system/role/list")
	@ResponseBody
	public JsonResult list(Integer page,Integer rows,String rolename) {
		List<SystemRole> roles=roleService.getRoleListByPage(page,rows,rolename);
		Integer total=roleService.count(rolename);
		Map<String, Object> map=new HashMap<>();
		map.put("total", total);
		map.put("rows", roles);
		return JsonResult.buildSuccessResult("成功", map);
	}
	
	/**
	 * 这是增加角色按钮的页面跳转功能
	 * @return
	 */
	@RequestMapping("/system/role/toaddrole")
	public String toaddrole() {
		return "roleInfo/addRole";
	}
	
	/**
	 * 这是增加或修改角色按钮的实现功能
	 * @param role        前端参数获取的信息封装到这个角色
	 * @param functionIds 勾选的功能id字符串
	 * @param session     从session中获取登录用户，用于修改或增加角色的相关字段设置
	 * @return
	 */
	@RequestMapping("/system/role/add")
	@ResponseBody
	public JsonResult add(SystemRole role,String functionIds,HttpSession session) {
		SystemUser user= (SystemUser) session.getAttribute("user");
		if (role.getId()==null) {
			roleService.saveRole(role,user);
			String[] functions = functionIds.split(",");
			roleService.insertIntoRoleFunctuion(role.getId(), functions);
			return JsonResult.buildSuccessResult("保存成功");
		}else {
			roleService.editRole(role,functionIds,user);
			return JsonResult.buildSuccessResult("修改成功");
		}
		
	}
	
	/**
	 * 这是编辑角色信息的页面跳转功能过呢
	 * @param rid    传入角色id
	 * @param model
	 * @return
	 */
	@RequestMapping("/system/role/toeditRole")
	public String toEditRole(String rid,Model model) {
		SystemRole role= roleService.selectByRoleId(rid);
		model.addAttribute("role", role);
		return "roleInfo/editRole";
	}
	
	/**
	 * 这是进行角色批量删除功能
	 * @param roles
	 * @return
	 */
	@RequestMapping("/system/role/delete")
	@ResponseBody
	public JsonResult delete(String roles) {
		String[] roleIds = roles.split(",");
		roleService.delete(roleIds);
		return JsonResult.buildSuccessResult("删除成功");
	}
}
