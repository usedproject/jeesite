package com.zhidisoft.bos.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.zhidisoft.bos.domain.User;
import com.zhidisoft.bos.utils.BosUtils;

public class LoginIntercepter extends MethodFilterInterceptor {

	
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		User loginUser = BosUtils.getLoginUser();
		if (loginUser==null) {
			return "login";
		}
		return invocation.invoke();
	}

}
