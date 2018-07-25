package com.zhidisoft.system.dao;

import com.zhidisoft.system.entity.SystemDict;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;

public interface SystemDictDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemDict record);

    SystemDict selectByPrimaryKey(Integer id);

    List<SystemDict> selectAll(@Param("name")String name);
    
    List<SystemDict> selectDictByPage(@Param("page")Integer page, @Param("rows")Integer rows, @Param("name")String dictName, @Param("status")String dictStatus);

    int updateByPrimaryKey(SystemDict record);

	Integer getDictCount(@Param("name")String dictName, @Param("status")String dictStatus);

	List<String> getDictStatus();

	List<String> getDictName();

	Integer addDict(@Param("systemDict")SystemDict systemDict);

	Integer editDict(@Param("systemDict")SystemDict systemDict, @Param("id")String id);

	Integer removeDict(@Param("id")String id);


}