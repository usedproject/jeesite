package com.zhidisoft.bos.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.zhidisoft.bos.dao.base.IbaseDao;
import com.zhidisoft.bos.domain.Staff;
import com.zhidisoft.bos.utils.PageBean;

public interface IStaffDao extends IbaseDao<Staff> {

	List<Staff> findByCriterria(DetachedCriteria detachedCriteria);

}
