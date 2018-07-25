package com.zhidisoft.business.service;

import java.beans.IntrospectionException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.zhidisoft.business.entity.AccumulationFund;

public interface AccumulationFundService {

	List<AccumulationFund> getListByPage(Integer page, Integer rows, String accountno, String idcard);

	Integer count(String accountno, String idcard);

	List<AccumulationFund> selectAll();

	AccumulationFund selectByIdOrName(String name, String idcard);

	int edit(AccumulationFund accumulationFund,HttpSession session);

	int add(AccumulationFund accumulationFund,HttpSession session);

	AccumulationFund selectById(Integer id);


	XSSFWorkbook exportExcelInfo(String recordIds) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException;


	List<Map<String, Object>> getStatistics(Integer page, Integer rows, String customerName, String idcard,
			String accountno, String companyName);

	Integer getStatisticsCount(String customerName, String idcard, String accountno, String companyName);

	void uploadPayerCreditInfoExcel(InputStream in, MultipartFile excel) throws Exception;

	XSSFWorkbook exportExcelInfo(String customerName, String idcard, String accountno, String companyName);



}
