package com.zhidisoft.bos.web.action;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zhidisoft.bos.domain.User;
import com.zhidisoft.bos.service.IUserService;
import com.zhidisoft.bos.utils.BosUtils;
import com.zhidisoft.bos.utils.MD5Utils;
import com.zhidisoft.bos.web.action.base.BaseAction;

import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	@Resource
	private IUserService userService;
	
	private String checkcode;
	
	private String[] roleIds;
	
	
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	private JSONObject json;
	
	public String login() {
		String SessionCheckCode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		if (StringUtils.isNotEmpty(checkcode)&&checkcode.equals(SessionCheckCode)) {
			/*User user= userService.login(model);
			if (user==null) {
				this.addActionError("用户名或密码不正确！！");
				return LOGIN;
			}else{
				ServletActionContext.getRequest().getSession().setAttribute("user", user);
				return HOME;
			}*/
			//修改登录逻辑，实现shiro认证
			Subject subject = SecurityUtils.getSubject();
			AuthenticationToken token = new UsernamePasswordToken(model.getUsername(),MD5Utils.md5(model.getPassword()));
			try{
				subject.login(token);
			}catch(AuthenticationException e){
				if (e instanceof UnknownAccountException ) {
					this.addActionError("用户名不正确");
				}
				if (e instanceof IncorrectCredentialsException) {
					this.addActionError("密码不正确");
				}
				return LOGIN;
			}
			User user = (User) subject.getPrincipal();
			ServletActionContext.getRequest().getSession().setAttribute("user", user);
			return HOME;
		}else {
			this.addActionError("验证码不正确");
			return LOGIN;
		}
			
	}
	
	public String loginOut(){
		ServletActionContext.getRequest().getSession().invalidate();
		return LOGIN;
	}
	
	public String editpassword() {
		User loginUser = BosUtils.getLoginUser();
		String newpassword = MD5Utils.md5(model.getPassword());
		loginUser.setPassword(newpassword);
		json=new JSONObject();
		try {
			userService.updateUser(loginUser);
			json.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("success", false);
			json.put("message", "密码修改失败");
		}
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String addUser() {
		userService.save(model,roleIds);
		return "list";
	}
	
	public void list() {
		userService.pageQuery(pageBean);
		java2json(pageBean, new String[]{"currentPage","currentRows",
				"detachedCriteria","password","noticebills","roles"});
	}
}
