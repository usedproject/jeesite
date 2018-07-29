package com.zhidisoft.bos.realm;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhidisoft.bos.dao.IFunctionDao;
import com.zhidisoft.bos.dao.IUserDao;
import com.zhidisoft.bos.domain.Function;
import com.zhidisoft.bos.domain.User;


public class BOSRealm extends AuthorizingRealm{
	@Resource
	private IUserDao userDao;
	
	@Resource
	private IFunctionDao functionDao;
	
	//认证方法
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken passwordToken = (UsernamePasswordToken)token;
		//获得页面输入的用户名
		String username = passwordToken.getUsername();
		//根据用户名查询数据库中的密码
		User user = userDao.findUserByUsername(username);
		if(user == null){
			//页面输入的用户名不存在
			return null;
		}
		//简单认证信息对象
		AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		//框架负责比对数据库中的密码和页面输入的密码是否一致
		return info;
	}

	//授权方法
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		User user = (User) principals.getPrimaryPrincipal();
		List<Function> functions=null;
		if (user.getUsername().equals("admin")) {
			functions = functionDao.findAll();
		}else {
			//查找数据库数据，给相应对象进行授权
			functions= functionDao.findFunctionListById(user.getId());
		}
		for (Function function : functions) {
			info.addStringPermission(function.getCode());
		}
		return info;
	}
}
