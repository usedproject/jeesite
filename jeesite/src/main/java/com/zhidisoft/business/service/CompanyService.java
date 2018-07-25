package com.zhidisoft.business.service;

import java.beans.IntrospectionException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.zhidisoft.business.entity.Company;

public interface CompanyService {

	List<Company> getListByPage(Company company, Integer page, Integer rows);

	Integer count(Company company);

	List<Company> checkName(String name);

	Integer addCompany(Company company, HttpSession session);

	Company selectById(Integer id);

	Integer editCompany(Company company, HttpSession session);

	List<Company> selectAll();

	XSSFWorkbook exportExcelInfo(String recaordIds) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException;

	void uploadPayerCreditInfoExcel(InputStream in, MultipartFile excel) throws Exception;

	void deleteByid(String[] ids);

}
