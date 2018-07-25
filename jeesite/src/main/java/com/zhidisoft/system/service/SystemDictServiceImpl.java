package com.zhidisoft.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.zhidisoft.system.dao.SystemDictDao;
import com.zhidisoft.system.entity.SystemDict;

@Service
public class SystemDictServiceImpl implements SystemDictService {
	@Resource
	SystemDictDao systemDictDao;
	@Override
	public List<SystemDict> getDictList(Integer page, Integer rows, String dictName, String dictStatus) {
		return systemDictDao.selectDictByPage((page-1)*rows, rows, dictName, dictStatus);
	}
	@Override
	public Integer getDictCount(String dictName, String dictStatus) {
		return systemDictDao.getDictCount(dictName, dictStatus);
	}
	@Override
	public List<String> getDictStatus() {
		return systemDictDao.getDictStatus();
	}
	
	@Override
	public List<SystemDict> selectAll(String name) {
		return systemDictDao.selectAll(name);
	}	
	
	public List<String> getDictName() {
		return systemDictDao.getDictName();
	}
	
	@Override
	public Integer addDict(SystemDict systemDict) {
		return systemDictDao.addDict(systemDict);
	}
	

	@Override
	public Integer editDict(SystemDict systemDict, String id) {
		// TODO Auto-generated method stub
		return systemDictDao.editDict(systemDict, id);
	}
	@Override
	public Integer removeDictById(String id) {
		// TODO Auto-generated method stub
		return systemDictDao.removeDict(id);
	}


}
