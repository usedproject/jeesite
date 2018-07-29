package com.zhidisoft.bos.dao;

import java.util.List;

import com.zhidisoft.bos.dao.base.IbaseDao;
import com.zhidisoft.bos.domain.Function;

public interface IFunctionDao extends IbaseDao<Function> {

	List<Function> findTop();

	List<Function> findFunctionListById(String id);

	List<Function> findMenu(String id);

	List<Function> findAllMenu();

}
