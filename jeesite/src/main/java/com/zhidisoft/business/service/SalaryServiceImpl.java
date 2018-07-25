package com.zhidisoft.business.service;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.zhidisoft.business.dao.SalaryDao;
import com.zhidisoft.business.entity.Salary;
import com.zhidisoft.utils.ExcelBean;
import com.zhidisoft.utils.ExcelUtils;
import com.zhidisoft.utils.SalaryResult;
import com.zhidisoft.utils.StatisticsGongzi;

@Service
public class SalaryServiceImpl implements SalaryService{
	
	@Resource
	SalaryDao salaryDao;
	
	@Override
	public List<SalaryResult> selectSalaryByPage(Integer page, Integer rows, String customerName, String idcard) {
		// TODO Auto-generated method stub
		return salaryDao.selectSalaryByPage((page-1)*rows, rows, customerName, idcard);
	}

	@Override
	public List<StatisticsGongzi> getStatistics(Integer page, Integer rows, String customerName, String idcard,
			String paycard, String companyName) {
		// TODO Auto-generated method stub
		return salaryDao.getStatistics((page-1)*rows, rows, customerName, idcard, paycard, companyName);
	}

	@Override
	public Integer getStatisticsCount(String customerName, String idcard, String paycard, String companyName) {
		// TODO Auto-generated method stub
		return salaryDao.getStatisticsCount(customerName, idcard, paycard, companyName);
	}

	@Override
	public Integer getCount(String customerId, String idcard) {
		// TODO Auto-generated method stub
		return salaryDao.getCount(customerId, idcard);
	}

	@Override
	public List<Salary> selectAll() {
		// TODO Auto-generated method stub
		return salaryDao.selectAll();
	}

	@Override
	public Integer check(String checkId) {
		// TODO Auto-generated method stub
		return salaryDao.check(checkId);
	}

	@Override
	public Integer add(Salary salary) {
		// TODO Auto-generated method stub
		return salaryDao.insert(salary);
	}

	@Override
	public List<Map<String, Object>> listGongzitiao(Integer page, Integer rows, String customerName, String idcard) {
		// TODO Auto-generated method stub
		return salaryDao.listGongzitiao((page-1)*rows, rows, customerName, idcard);
	}

	@Override
	public Integer getCountGongzitiao(String customerName, String idcard) {
		// TODO Auto-generated method stub
		return salaryDao.getCountGongzitiao(customerName, idcard);
	}

	@Override
	public XSSFWorkbook exportExcelInfo(String customerName, String idcard) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException {
		//根据条件查询数据，把数据装载到一个list中
	    List<SalaryResult> list = salaryDao.selectSalary(customerName, idcard);

	    List<ExcelBean> headerList = new ArrayList<>();
	    Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
	    XSSFWorkbook xssfWorkbook=null;
	    
	    //设置  标题栏
	    headerList.add(new ExcelBean("姓名","customerName",0));
	    headerList.add(new ExcelBean("身份证号","idcard",0));
	    headerList.add(new ExcelBean("银行卡号","paycard",0));
	    headerList.add(new ExcelBean("支付日期","paydate",0));
	    headerList.add(new ExcelBean("基本工资","basesalary",0));
	    headerList.add(new ExcelBean("奖金","bonuspay",0));
	    headerList.add(new ExcelBean("加班费","overtimepay",0));
	    headerList.add(new ExcelBean("社保扣费","shebaopay",0));
	    headerList.add(new ExcelBean("公积金扣费","gongjijinpay",0));
	    headerList.add(new ExcelBean("应交税款","taxpay",0));
	    headerList.add(new ExcelBean("应发工资","totalpay",0));
	    headerList.add(new ExcelBean("实发工资","mustpay",0));
	    headerList.add(new ExcelBean("代理费用","proxyfee",0));
	    
	    
	    // 将标题栏信息 加入 到 map 集合中
	    map.put(0, headerList);
	    
	    // 定义excel文件中要生成的 工作表名 即：sheet的名称
	    String sheetName = "用户信息";
	   
	    //调用ExcelUtil工具类的方法，生成excel工作表对象
	    xssfWorkbook = ExcelUtils.createExcelFile(SalaryResult.class, list, map, sheetName);
	    
	    return xssfWorkbook;

	}

}
