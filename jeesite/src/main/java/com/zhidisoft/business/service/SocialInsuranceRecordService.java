package com.zhidisoft.business.service;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.zhidisoft.business.entity.SocialInsuranceRecord;

public interface SocialInsuranceRecordService {

	List<SocialInsuranceRecord> getListByPage(Integer page, Integer rows, String customerName, String idcard,
			String sbcard);

	Integer getCount(String customerName, String idcard, String sbcard);

	List<SocialInsuranceRecord> selectAll();

	Integer edit(SocialInsuranceRecord insuranceRecord, Integer id);

	Integer add(SocialInsuranceRecord insuranceRecord);

	List<Map<String, Object>> getStatistics(Integer page, Integer rows, String customerName, String idcard,
			String sbcard, String companyName);

	Integer getStatisticCount(String customerName, String idcard, String sbcard, String companyName);

	XSSFWorkbook exportExcelInfo(String customerName, String idcard, String sbcard, String companyName) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException;
	
}
