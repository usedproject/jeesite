package com.zhidisoft.business.service;

import java.beans.IntrospectionException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.zhidisoft.business.entity.Customer;

public interface CustomerService {

	List<Customer> getListByPage(Customer customer, Integer page, Integer rows);

	Integer count(Customer customer);


	List<Customer> selectAll();


	int add(Customer customer, HttpSession session);

	Customer selectById(Integer id);

	int editCustomer(Customer customer, HttpSession session);

	Customer check(String name);

	Customer selectByNameOrIdCard(String name, String idcard);

	XSSFWorkbook exportExcelInfo(String recordIds) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException;

	void uploadPayerCreditInfoExcel(InputStream in, MultipartFile excel) throws Exception;

	int deleteById(String[] ids);



}
