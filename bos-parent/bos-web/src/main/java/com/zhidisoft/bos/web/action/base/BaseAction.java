package com.zhidisoft.bos.web.action.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zhidisoft.bos.domain.Region;
import com.zhidisoft.bos.utils.BosUtils;
import com.zhidisoft.bos.utils.PageBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{
	
	public final String HOME="home";
	
	protected PageBean<T> pageBean=new PageBean<T>();
	
	DetachedCriteria detachedCriteria = null;
	
	
	public void setPage(int page) {
		pageBean.setCurrentPage(page);
	}

	public void setRows(int rows) {
		pageBean.setCurrentRows(rows);
	}
	
	protected T model;
	
	public BaseAction() {
		ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] actualTypeArguments = superclass.getActualTypeArguments();
		Class<T> paramClass=(Class<T>) actualTypeArguments[0];
		detachedCriteria=DetachedCriteria.forClass(paramClass);
		pageBean.setDetachedCriteria(detachedCriteria);
		try {
			model=paramClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public T getModel() {
		return model;
	}

	public void java2json(Object o,String[] excludes) {
		//指定哪些属性不需要传递到前端（不进行json转换）
				JsonConfig jsonConfig=new JsonConfig();
				jsonConfig.setExcludes(excludes);
				JSONObject jsonObject=JSONObject.fromObject(o,jsonConfig);
				try {
					BosUtils.getResponse().setContentType("text/json;charset=utf-8");
					BosUtils.getResponse().getWriter().print(jsonObject);
				} catch (IOException e) {
					e.printStackTrace();
				}
	}
	
	public void java2json(List o,String[] excludes) {
		//指定哪些属性不需要传递到前端（不进行json转换）
				JsonConfig jsonConfig=new JsonConfig();
				jsonConfig.setExcludes(excludes);
				JSONArray jsonObject=JSONArray.fromObject(o,jsonConfig);
				try {
					BosUtils.getResponse().setContentType("text/json;charset=utf-8");
					BosUtils.getResponse().getWriter().print(jsonObject);
				} catch (IOException e) {
					e.printStackTrace();
				}
	}
}
