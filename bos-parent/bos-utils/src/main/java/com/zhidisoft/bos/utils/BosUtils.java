package com.zhidisoft.bos.utils;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.zhidisoft.bos.domain.User;

public class BosUtils {
	
	public static String[] regionTempArr={"id","provice","city","district","postcode"};
	
	public static String[] subareaTempArr={"regionid","addresskey","startnum","endnum","single","position","provice"};
	
	public static String yellowStyle="yellow";
	
	public static String blueStyle="blue";
	
	public static HttpSession getSession(){
		return ServletActionContext.getRequest().getSession();
	}
	
	public static User getLoginUser() {
		User user = (User) BosUtils.getSession().getAttribute("user");
		return user;
	}
	
	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	
	public static boolean listIsEmpty(List list) {
		if (list==null||list.size()==0) {
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean listIsNotEmpty(List list) {
		return !listIsEmpty(list);
	}
}
