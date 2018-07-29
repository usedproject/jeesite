package com.zhidisoft.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhidisoft.bos.dao.IRegionDao;
import com.zhidisoft.bos.domain.Region;
import com.zhidisoft.bos.service.IRegionService;
import com.zhidisoft.bos.utils.PageBean;

@Service
@Transactional
public class RegionService implements IRegionService {

	@Resource
	private IRegionDao regionDao;
	
	
	public void addSRegion(Region model) {
		

	}

	
	public void pageQuery(PageBean<Region> pageBean) {
		regionDao.pageQuery(pageBean);

	}

	
	public void deleteRegionByIds(String[] split) {
		

	}

	
	public void editRegion(Region model) {
		

	}

	
	public Region findById(String id) {
		
		return null;
	}

	@Transactional(readOnly=false)
	public void saveBatch(List<Region> regions) {
		for (Region region : regions) {
			regionDao.save(region);
		}
	}

	
	public List<Region> findAll() {
		return regionDao.findAll();
	}
	
	
	public List<Region> findByQ(String q) {
		return regionDao.findByQ(q);
	}
}
