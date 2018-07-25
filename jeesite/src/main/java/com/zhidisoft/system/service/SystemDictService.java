package com.zhidisoft.system.service;

import java.util.List;

import com.zhidisoft.system.entity.SystemDict;

public interface SystemDictService {

	List<SystemDict> getDictList(Integer page, Integer rows, String dictName, String dictStatus);

	Integer getDictCount(String dictName, String dictStatus);

	List<String> getDictStatus();


	List<SystemDict> selectAll(String name);

	List<String> getDictName();

	Integer addDict(SystemDict systemDict);
	
	Integer editDict(SystemDict systemDict, String id);

	Integer removeDictById(String id);

}
