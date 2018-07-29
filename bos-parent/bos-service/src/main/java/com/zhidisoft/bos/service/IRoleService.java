package com.zhidisoft.bos.service;

import com.zhidisoft.bos.domain.Role;
import com.zhidisoft.bos.utils.PageBean;

public interface IRoleService {

	void save(Role model);

	void queryByPage(PageBean<Role> pageBean);

}
