package com.zhidisoft.bos.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.zhidisoft.bos.domain.Staff;
import com.zhidisoft.bos.utils.PageBean;

public interface IStaffService {

	void addStaff(Staff model);

	void pageQuery(PageBean<Staff> pageBean);

	void deleteStaffByIds(String[] split);

	void editStaff(Staff model);

	Staff findById(String id);

	List<Staff> findNotDelete(DetachedCriteria detachedCriteria);

}
