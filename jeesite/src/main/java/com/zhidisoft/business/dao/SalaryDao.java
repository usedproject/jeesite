package com.zhidisoft.business.dao;

import com.zhidisoft.business.entity.Salary;
import com.zhidisoft.utils.SalaryResult;
import com.zhidisoft.utils.StatisticsGongzi;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SalaryDao {

	int deleteByPrimaryKey(Integer id);


    Salary selectByPrimaryKey(Integer id);

    List<Salary> selectAll();

    int updateByPrimaryKey(Salary record);

	List<SalaryResult> selectSalaryByPage(@Param("page")int page, @Param("rows")Integer rows, @Param("customerName")String customerName, @Param("idcard")String idcard);

	
	
	Integer getCount(@Param("customerName")String customerName, @Param("idcard")String idcard);

	List<StatisticsGongzi> getStatistics(@Param("page")Integer page, @Param("rows")Integer rows, @Param("customerName")String customerName, @Param("idcard")String idcard,
			@Param("paycard")String paycard, @Param("companyName")String companyName);

	Integer getStatisticsCount(@Param("customerName")String customerName, @Param("idcard")String idcard,
			@Param("paycard")String paycard, @Param("companyName")String companyName);

	//判断该身份证号是否已经存在
	Integer check(@Param("checkId")String checkId);

	//向表中添加记录
	Integer insert(@Param("salary")Salary salary);


	List<Map<String, Object>> listGongzitiao(@Param("page")Integer page, @Param("rows")Integer rows, @Param("customerName")String customerName, @Param("idcard")String idcard);


	Integer getCountGongzitiao(@Param("customerName")String customerName, @Param("idcard")String idcard);




	List<SalaryResult> selectSalary(@Param("customerName")String customerName, @Param("idcard")String idcard);

	
}