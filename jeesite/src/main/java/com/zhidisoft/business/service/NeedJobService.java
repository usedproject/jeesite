package com.zhidisoft.business.service;

import java.util.List;

import com.zhidisoft.business.entity.NeedJob;

public interface NeedJobService {

	List<NeedJob> getListPage(NeedJob needJob, Integer page, Integer rows);

	Integer count(NeedJob needJob);

	int add(NeedJob needJob);

	NeedJob selectById(Integer id);

	int edit(NeedJob needJob);

}
