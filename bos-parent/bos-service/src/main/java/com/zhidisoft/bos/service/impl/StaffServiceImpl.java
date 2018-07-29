package com.zhidisoft.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zhidisoft.bos.dao.IStaffDao;
import com.zhidisoft.bos.domain.Staff;
import com.zhidisoft.bos.service.IStaffService;
import com.zhidisoft.bos.utils.PageBean;

@Service
@Transactional(isolation=Isolation.DEFAULT,readOnly=false,propagation=Propagation.REQUIRED)
public class StaffServiceImpl implements IStaffService {
	
	@Resource
	private IStaffDao staffDao;

	@Override
	public void addStaff(Staff model) {
		staffDao.save(model);
	}

	@Override
	public void pageQuery(PageBean<Staff> pageBean) {
		staffDao.pageQuery(pageBean);
	}

	@Override
	public void deleteStaffByIds(String[] split) {
		if (split!=null&&split.length>0) {
			StringBuilder hql=new StringBuilder("update Staff set deltag = 1 where id in (");
			for (int i=0;i<split.length;i++) {
				if (i!=split.length-1) {
					hql.append(" '"+split[i]+ "',");
				}else {
					hql.append(" '"+split[i]+"'");
				}
			}
			hql.append(")");
			String sql=hql.toString();
			staffDao.executeSql(sql);
		}
	}

	@Override
	public void editStaff(Staff model) {
		staffDao.update(model);
	}

	@Override
	public Staff findById(String id) {
		return staffDao.findById(id);
	}

	@Override
	public List<Staff> findNotDelete(DetachedCriteria detachedCriteria) {
		return staffDao.findByCriterria(detachedCriteria);
	}


}
