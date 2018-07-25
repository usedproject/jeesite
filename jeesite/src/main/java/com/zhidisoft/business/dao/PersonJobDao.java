package com.zhidisoft.business.dao;

import com.zhidisoft.business.entity.PersonJob;
import com.zhidisoft.utils.StatisticsRencai;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PersonJobDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PersonJob record);

    PersonJob selectByPrimaryKey(Integer id);

    List<PersonJob> selectAll();

    int updateByPrimaryKey(PersonJob record);

	List<PersonJob> getListByPage(@Param("personJob")PersonJob personJob, @Param("page")int page, @Param("rows")Integer rows);

	Integer count(@Param("personJob")PersonJob personJob);

	List<StatisticsRencai> getStatistics(@Param("page")Integer page, @Param("rows")Integer rows, @Param("customerName")String customerName, @Param("idcard")String idcard,
			@Param("phone")String phone, @Param("companyName")String companyName);
	
	Integer getStatisticsCount(@Param("customerName")String customerName, @Param("idcard")String idcard,
			@Param("phone")String phone, @Param("companyName")String companyName);
}