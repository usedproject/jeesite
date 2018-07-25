package com.zhidisoft.business.service;

import java.beans.IntrospectionException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.zhidisoft.business.entity.SocialInsurance;
import com.zhidisoft.utils.SocialInsuranceResult;

public interface SocialInsuranceService {

	List<SocialInsuranceResult> getListByPage(Integer page, Integer rows, String customerName, String idcard, String sbcard);

	Integer getCount();

	Integer addSocialInsurance(SocialInsurance socialInsurance);

	List<SocialInsurance> checkIdcard(String idcard);

	List<SocialInsurance> selectAll();

	Integer edit(SocialInsurance socialInsurance, String id);

	Integer remove(String id);

	XSSFWorkbook exportExcelInfo(String customerName, String idcard, String sbcard) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException;

	void uploadPayerCreditInfoExcel(InputStream in, MultipartFile excel) throws Exception;

}
