package com.zhidisoft.bos.service;

import java.util.List;

import com.zhidisoft.bos.domain.Function;
import com.zhidisoft.bos.utils.PageBean;

public interface IFunctionService {

	List<Function> findAll();

	void save(Function model);

	void pageQuery(PageBean<Function> pageBean);

	List<Function> findTop();

	List<Function> findMenu(String id);

	List<Function> findAllMenu();

}
