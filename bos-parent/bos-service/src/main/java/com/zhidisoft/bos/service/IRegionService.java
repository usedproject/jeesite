package com.zhidisoft.bos.service;

import java.util.List;

import com.zhidisoft.bos.domain.Region;
import com.zhidisoft.bos.utils.PageBean;

public interface IRegionService {

	void addSRegion(Region model);

	void pageQuery(PageBean<Region> pageBean);

	void deleteRegionByIds(String[] split);

	void editRegion(Region model);

	Region findById(String id);

	void saveBatch(List<Region> regions);

	List<Region> findByQ(String q);

	List<Region> findAll();

}
