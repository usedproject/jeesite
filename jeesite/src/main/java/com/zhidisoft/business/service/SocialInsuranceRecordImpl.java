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

import com.zhidisoft.business.dao.SocialInsuranceRecordDao;
import com.zhidisoft.business.entity.SocialInsuranceRecord;
import com.zhidisoft.utils.ExcelBean;
import com.zhidisoft.utils.ExcelUtils;
import com.zhidisoft.utils.SalaryResult;
import com.zhidisoft.utils.StatisticsShebao;

@Service
public class SocialInsuranceRecordImpl implements SocialInsuranceRecordService {

	@Resource
	SocialInsuranceRecordDao dao;
	@Override
	public List<SocialInsuranceRecord> getListByPage(Integer page, Integer rows, String customerName, String idcard,
			String sbcard) {
		// TODO Auto-generated method stub
		return dao.getListByPage((page-1)*rows, rows, customerName, idcard, sbcard);
	}
	@Override
	public Integer getCount(String customerName, String idcard, String sbcard) {
		// TODO Auto-generated method stub
		return dao.getCount(customerName, idcard, sbcard);
	}
	@Override
	public List<SocialInsuranceRecord> selectAll() {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}
	@Override
	public Integer edit(SocialInsuranceRecord insuranceRecord, Integer id) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKey(insuranceRecord, id);
	}
	@Override
	public Integer add(SocialInsuranceRecord insuranceRecord) {
		// TODO Auto-generated method stub
		return dao.insert(insuranceRecord);
	}
	@Override
	public List<Map<String, Object>> getStatistics(Integer page, Integer rows, String customerName, String idcard,
			String sbcard ,String companyName) {
		// TODO Auto-generated method stub
		return dao.getStatistics((page-1)*rows, rows, customerName, idcard, sbcard, companyName);
	}
	@Override
	public Integer getStatisticCount(String customerName, String idcard, String sbcard, String companyName) {
		// TODO Auto-generated method stub
		return dao.getStatisticCount(customerName, idcard, sbcard,companyName);
	}
	@Override
	public XSSFWorkbook exportExcelInfo(String customerName, String idcard, String sbcard, String companyName) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException {
		//根据条件查询数据，把数据装载到一个list中
	    List<StatisticsShebao> list = dao.getStatisticsBy(customerName, idcard, sbcard, companyName);

	    List<ExcelBean> headerList = new ArrayList<>();
	    Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
	    XSSFWorkbook xssfWorkbook=null;
	    
	    //设置  标题栏
	    headerList.add(new ExcelBean("姓名","name",0));
	    headerList.add(new ExcelBean("身份证号","idcard",0));
	    headerList.add(new ExcelBean("社保卡号","sdCard",0));
	    headerList.add(new ExcelBean("所属公司","cpname",0));
	    headerList.add(new ExcelBean("社保月数","counts",0));
	    headerList.add(new ExcelBean("社保总额","sums",0));
	    headerList.add(new ExcelBean("费用总额","proxyFee",0));
	    headerList.add(new ExcelBean("状态","status",0));
	    
	    
	    // 将标题栏信息 加入 到 map 集合中
	    map.put(0, headerList);
	    
	    // 定义excel文件中要生成的 工作表名 即：sheet的名称
	    String sheetName = "用户信息";
	   
	    //调用ExcelUtil工具类的方法，生成excel工作表对象
	    xssfWorkbook = ExcelUtils.createExcelFile(StatisticsShebao.class, list, map, sheetName);
	    
	    return xssfWorkbook;
	}
	
}
