package com.zhidisoft.business.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhidisoft.business.dao.PersonJobDao;
import com.zhidisoft.business.entity.PersonJob;
import com.zhidisoft.utils.StatisticsRencai;

@Service
public class PersonJobServiceImpl implements PersonJobService {
	
	@Resource
	PersonJobDao personJobDao;

	@Override
	public List<PersonJob> getListByPage(PersonJob personJob, int i, Integer rows) {
		return personJobDao.getListByPage(personJob,i,rows);
	}

	@Override
	public Integer count(PersonJob personJob) {
		return personJobDao.count(personJob);
	}

	@Override
	public int save(PersonJob personJob) {
		personJob.setCreatetime(new Date());
		if (personJob.getJobcontent().equals("")) {
			personJob.setJobcontent(null);
		}
		if (personJob.getRemark().equals("")) {
			personJob.setRemark(null);
		}
		return personJobDao.insert(personJob);
	}

	@Override
	public PersonJob selectById(Integer id) {
		return personJobDao.selectByPrimaryKey(id);
	}

	@Override
	public int edit(PersonJob personJob) {
		personJob.setUpdatetime(new Date());
		if (personJob.getJobcontent().equals("")) {
			personJob.setJobcontent(null);
		}
		if (personJob.getRemark().equals("")) {
			personJob.setRemark(null);
		}
		return personJobDao.updateByPrimaryKey(personJob);
	}

	@Override
	public List<StatisticsRencai> getStatistics(Integer page, Integer rows, String customerName, String idcard,
			String phone, String companyName) {
		// TODO Auto-generated method stub
		return personJobDao.getStatistics((page-1)*rows, rows, customerName, idcard, phone, companyName);
	}

	@Override
	public Integer getStatisticsCount(String customerName, String idcard, String phone, String companyName) {
		// TODO Auto-generated method stub
		return personJobDao.getStatisticsCount(customerName, idcard, phone, companyName);
	}

}
