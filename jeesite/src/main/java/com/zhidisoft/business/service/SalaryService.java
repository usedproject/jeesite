package com.zhidisoft.business.service;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.zhidisoft.business.entity.Salary;
import com.zhidisoft.utils.SalaryResult;
import com.zhidisoft.utils.StatisticsGongzi;

public interface SalaryService {
	List<SalaryResult> selectSalaryByPage(Integer page, Integer rows, String customerName, String idcard);

	List<StatisticsGongzi> getStatistics(Integer page, Integer rows, String customerName, String idcard,
			String paycard, String companyName);

	Integer getStatisticsCount(String customerName, String idcard, String paycard, String companyName);

	Integer getCount(String customerId, String idcard);

	List<Salary> selectAll();

	Integer check(String checkId);

	Integer add(Salary salary);

	List<Map<String, Object>> listGongzitiao(Integer page, Integer rows, String customerName, String idcard);

	Integer getCountGongzitiao(String customerName, String idcard);

	XSSFWorkbook exportExcelInfo(String customerName, String idcard) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException;
	
}
