package com.zhidisoft.system.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhidisoft.system.entity.SystemFunction;
import com.zhidisoft.system.entity.SystemRole;
import com.zhidisoft.system.entity.SystemUser;
import com.zhidisoft.system.service.SystemFunctionService;
import com.zhidisoft.system.service.SystemRoleService;
import com.zhidisoft.system.service.SystemUserService;
import com.zhidisoft.utils.JsonResult;

/**
 * 这是处理系统用户模块的controller层
 * @author 张磊
 * @date 2018年3月16日
 */
@Controller
public class SystemUserController {
	
	@Resource
	SystemUserService userService;
	
	@Resource
	SystemRoleService roleService;
	
	@Resource
	SystemFunctionService functionService;
	
	/**
	 * 这是进行登录功能实现的具体方法
	 * @param user 前端传入的数据封装到user对象中
	 * @param rememberMe  前端是否进行勾选了remember按钮
	 * @param session	 将参数进行封装到session域中
	 * @param response	需要进行cookie的实现
	 * @param lastlogin 上次登录时间的Cookie获取
	 * @param request   获得请求，从请求中可以获得本次的ip地址
	 * @param lastip	从Cookie中获取上次访问的ip地址
	 * @return
	 */
	@RequestMapping("system/login")
	@ResponseBody
	public JsonResult login(SystemUser user,String rememberMe,
			HttpSession session,HttpServletResponse response,
			@CookieValue(value="lastlogin",required=false)String lastlogin,
			HttpServletRequest request,
			@CookieValue(value="lastip",required=false)String lastip) {
		//通过用户名获得数据库中的信息
		SystemUser exitUser= userService.getUserByUsername(user.getUsername());
		if (exitUser==null) {
			return JsonResult.buildFailureResult("用户名不存在");
		}
		if (exitUser!=null&&!exitUser.getPassword().equals(user.getPassword())) {
			return JsonResult.buildFailureResult("密码不正确");
		}
		//进行用户角色的判断
		List<SystemRole> roles=roleService.getRoleListByUserId(exitUser.getId());
		if (roles==null||roles.size()<1) {
			return JsonResult.buildFailureResult("请联系管理员分配角色");
		}
		//进行用户权限的判断
		List<SystemFunction> functions= functionService.getFunctionListByRoles(roles);
		if (functions==null||functions.size()<1) {
			return JsonResult.buildFailureResult("请联系管理员分配权限");
		}
		//进行记住按钮勾选的判断，及其功能的实现
		if (rememberMe!=null&&rememberMe.equals("on")) {
			Cookie cookie=new Cookie("username", exitUser.getUsername());
			cookie.setPath("/");
			cookie.setMaxAge(60*60*24*7);//7天
			Cookie cookie2=new Cookie("password", exitUser.getPassword());
			cookie2.setPath("/");
			cookie2.setMaxAge(60*60*24*7);
			response.addCookie(cookie);
			response.addCookie(cookie2);
		}
		
		//记录登录时间
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd  HH：mm：ss");
		String format = sf.format(new Date());
		Cookie cookie3=new Cookie("lastlogin", format);
		cookie3.setPath("/");
		cookie3.setMaxAge(60*60*24*7);//7天
		
		//记录登录的ip
		String ip="";
		if (request.getHeader("x-forwarded-for") == null) {  
			ip= request.getRemoteAddr();  
	    }else{
	    	ip= request.getHeader("x-forwarded-for"); 
	    }  
		Cookie cookie4=new Cookie("lastip", ip);
		cookie4.setPath("/");
		cookie4.setMaxAge(60*60*24*7);
		response.addCookie(cookie3);
		response.addCookie(cookie4);
		
		session.setAttribute("user", exitUser);
		session.setAttribute("roles", roles);
		session.setAttribute("functions", functions);
		session.setAttribute("lastlogin", lastlogin);
		session.setAttribute("lastip", lastip);
		return JsonResult.buildSuccessResult("登录成功");
	}
	
	/**
	 * 登录成功后进行页面的跳转
	 * @return
	 */
	@RequestMapping("/toindex")
	public String toindex() {
		return "index2";
	}
	
	/*@RequestMapping("/toedituser")
	public String toSavedresource() {
		return "userInfo/edituser";
	}*/
	
	/**
	 * 点击增加或修改按钮之后，弹出框的页面跳转
	 * @return
	 */
	@RequestMapping("/system/user/toadduser")
	public String toAddUser() {
		return "userInfo/adduser";
	}
	
	/**
	 * 用于修改用户的功能
	 * @param user 前端传送的数据封装到user参数中
	 * @return
	 */
	@RequestMapping("/system/edituser")
	@ResponseBody
	public JsonResult edituser(SystemUser user) {
		Boolean b= userService.editUser(user);
		if (!b) {
			return JsonResult.buildFailureResult("修改失败");
		}
		return JsonResult.buildSuccessResult("修改成功");
	}
	
	/**
	 * 修改密码的跳转功能
	 * @return
	 */
	@RequestMapping("/system/user/toModifyPassword")
	public String tomodifypassword(){
		return "userInfo/modifypassword";
	}
	
	/**
	 * 修改密码功能的实现
	 * @param user 将修改密码的参数封装到user对象中
	 * @return
	 */
	@RequestMapping("/system/modifypassword")
	@ResponseBody
	public JsonResult modifypassword(SystemUser user,HttpSession session,String oldPassword) {
		SystemUser user2=(SystemUser) session.getAttribute("user");
		if (!user2.getPassword().equals(oldPassword)) {
			return JsonResult.buildFailureResult("请输入正确的原始密码");
		}
		Boolean b= userService.modifypassword(user);
		if (b) {
			return JsonResult.buildSuccessResult("修改成功");
		}
		return JsonResult.buildFailureResult("修改失败");
	}
	
	/**
	 * 用户列表的展示页面的跳转功能
	 * @return
	 */
	@RequestMapping("/company/system/userInfo")
	public String listUser() {
		return "userInfo/listuser";
	}
	
	/**
	 * 用户列表数据网格加载数据中的分页功能的实现
	 * @param page 第几页	
	 * @param rows 显示几行数据
	 * @param user 查询的参数封装到这个对象中
	 * @return
	 */
	@RequestMapping("/system/user/getList")
	@ResponseBody
	public Map<String, Object> getList(Integer page,Integer rows,SystemUser user) {
		Integer count= userService.count(user.getUsername(),user.getPhone(),user.getStatus());
		List<SystemUser> users=userService.getListByPage(page,rows,user.getUsername(),user.getPhone(),user.getStatus());
		Map<String, Object> map=new HashMap<>();
		map.put("total", count);
		map.put("rows", users);
		return map;
	}
	
	/**
	 * 这是增加用户的功能的实现
	 * @param user 前端参数封装到这个对象中，封装不了id，用ids进行传输
	 * @param userRoleList 前端传入的勾选的角色参数列表
	 * @param ids 如果是编辑功能的情况下才有这个参数，传入被编辑的用户的id
	 * @return
	 */
	@RequestMapping("/system/user/add")
	@ResponseBody
	public JsonResult add(SystemUser user,String[] userRoleList,Integer ids) {
		user.setId(ids);
		if(user.getId()==null){
			try {
				Integer uid= userService.saveUser(user);
				userService.insertIntoUserRoles(user.getId(),userRoleList);
			} catch (Exception e) {
				e.printStackTrace();
				return JsonResult.buildFailureResult("添加失败");
			}
			return JsonResult.buildSuccessResult("添加成功");
		}else{
			try {
				userService.editUser(user);
				userService.removeFromUserRole(user.getId());
				userService.insertIntoUserRoles(user.getId(), userRoleList);
			} catch (Exception e) {
				e.printStackTrace();
				return JsonResult.buildFailureResult("修改失败");
			}
			return JsonResult.buildSuccessResult("修改成功");
		}
	}
	
	/**
	 * 这是进行用户编辑页面的跳转，将通过id搜索出来的用户信息封装到request域中
	 * @param id   传入的用户id
	 * @param model 数据模型，进行信息的存放
	 * @return
	 */
	@RequestMapping("/system/user/toedituser")
	public String toeditUser(String id,Model model) {
		SystemUser user= userService.selectUserById(id);
		model.addAttribute("editUser", user);
		return "userInfo/edituser2";
	}
	
	/**
	 * 批量删除用户的功能
	 * @param userIds 一个字符串，用逗号隔开
	 * @return
	 */
	@RequestMapping("/system/user/delete")
	@ResponseBody
	public JsonResult delete(String userIds) {
		//获得用户id的数组
		String[] uids = userIds.split(",");
		try {
			userService.delete(uids);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.buildFailureResult("删除失败");
		}
		return JsonResult.buildSuccessResult("删除 成功");
	}
	
	/**
	 * 查询当前登录用户的个人信息
	 * @return
	 */
	@RequestMapping("/system/user/lookUser")
	public String toLookUser(HttpSession session, Model model) {
		SystemUser user = (SystemUser) session.getAttribute("user");
		model.addAttribute("id", user.getId());
		return "userInfo/edituser";
	}
	
	/**
	 * 这是进行前端验证的用户名是否存在的方法
	 * @param username
	 * @return
	 */
	@RequestMapping("system/user/check")
	@ResponseBody
	public boolean check(String username) {
		SystemUser user = userService.selectByUserName(username);
		if (user!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	
	@RequestMapping("/system/user/getByName")
	@ResponseBody
	public JsonResult getByName(String username) {
		SystemUser selectByUserName = userService.selectByUserName(username);
		if (selectByUserName!=null ) {
			return JsonResult.buildSuccessResult("", selectByUserName);
		}else {
			return JsonResult.buildFailureResult("");
		}
	}
	
	/**
	 * 这是进行前端验证的用户名是否存在的方法
	 * @param username
	 * @return
	 */
	@RequestMapping("system/user/check2")
	@ResponseBody
	public boolean check2(String username) {
		SystemUser user = userService.selectByUserName(username);
		if (user==null) {
			return true;
		}else {
			return false;
		}
	}
}
