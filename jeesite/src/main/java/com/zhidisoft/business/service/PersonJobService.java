package com.zhidisoft.business.service;

import java.util.List;
import java.util.Map;

import com.zhidisoft.business.entity.PersonJob;
import com.zhidisoft.utils.StatisticsRencai;

public interface PersonJobService {

	List<PersonJob> getListByPage(PersonJob personJob, int i, Integer rows);

	Integer count(PersonJob personJob);

	int save(PersonJob personJob);

	PersonJob selectById(Integer id);

	int edit(PersonJob personJob);

	List<StatisticsRencai> getStatistics(Integer page, Integer rows, String customerName, String idcard,
			String phone, String companyName);

	Integer getStatisticsCount(String customerName, String idcard, String phone, String companyName);

}
