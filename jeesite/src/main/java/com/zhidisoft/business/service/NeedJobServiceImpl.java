package com.zhidisoft.business.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhidisoft.business.dao.NeedJobDao;
import com.zhidisoft.business.entity.NeedJob;

@Service
public class NeedJobServiceImpl implements NeedJobService {
	
	@Resource
	NeedJobDao needJobDao;
	
	@Override
	public List<NeedJob> getListPage(NeedJob needJob, Integer page, Integer rows) {
		page=(page-1)*rows;
		return needJobDao.getListByPage(needJob,page,rows);
	}

	@Override
	public Integer count(NeedJob needJob) {
		return needJobDao.count(needJob);
	}

	@Override
	public int add(NeedJob needJob) {
		if (needJob.getJobcontent().equals("")) {
			needJob.setJobcontent(null);
		}
		if (needJob.getRemark().equals("")) {
			needJob.setRemark(null);
		}
		return needJobDao.insert(needJob);
	}

	@Override
	public NeedJob selectById(Integer id) {
		
		return needJobDao.selectByPrimaryKey(id);
	}

	@Override
	public int edit(NeedJob needJob) {
		needJob.setUpdatetime(new Date());
		if (needJob.getJobcontent().equals("")) {
			needJob.setJobcontent(null);
		}
		if (needJob.getRemark().equals("")) {
			needJob.setRemark(null);
		}
		return needJobDao.updateByPrimaryKey(needJob);
	}

}
