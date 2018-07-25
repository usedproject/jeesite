package com.zhidisoft.business.service;

import java.beans.IntrospectionException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zhidisoft.business.dao.CustomerDao;
import com.zhidisoft.business.dao.SocialInsuranceDao;
import com.zhidisoft.business.entity.Company;
import com.zhidisoft.business.entity.Customer;
import com.zhidisoft.business.entity.SocialInsurance;
import com.zhidisoft.utils.ExcelBean;
import com.zhidisoft.utils.ExcelUtils;
import com.zhidisoft.utils.SocialInsuranceResult;

@Service
public class SocialInsuranceServiceImpl implements SocialInsuranceService {

	@Resource
	SocialInsuranceDao socialInsuranceDao;
	
	@Resource
	CustomerDao customerDao;
	
	@Override
	public List<SocialInsuranceResult> getListByPage(Integer page, Integer rows, String customerName, String idcard,
			String sbcard) {
		// TODO Auto-generated method stub
		return socialInsuranceDao.getListByPage((page-1)*rows, rows, customerName, idcard, sbcard);
	}

	@Override
	public Integer getCount() {
		// TODO Auto-generated method stub
		return socialInsuranceDao.getCount();
	}

	@Override
	public Integer addSocialInsurance(SocialInsurance socialInsurance) {
		// TODO Auto-generated method stub
		return socialInsuranceDao.addSocialInsurance(socialInsurance);
	}

	@Override
	public List<SocialInsurance> checkIdcard(String idcard) {
		// TODO Auto-generated method stub
		return socialInsuranceDao.checkIdcard(idcard);
	}

	@Override
	public List<SocialInsurance> selectAll() {
		// TODO Auto-generated method stub
		return socialInsuranceDao.selectAll();
	}

	@Override
	public Integer edit(SocialInsurance socialInsurance, String id) {
		// TODO Auto-generated method stub
		return socialInsuranceDao.updateByPrimaryKey(socialInsurance, id);
	}

	@Override
	public Integer remove(String id) {
		// TODO Auto-generated method stub
		return socialInsuranceDao.deleteByPrimaryKey(id);
	}

	/**
	 * 
	 */
	@Override
	public XSSFWorkbook exportExcelInfo(String customerName, String idcard, String sbcard) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException {
		List<SocialInsuranceResult> socialInsuranceResults=socialInsuranceDao.selectByParams(customerName,idcard,sbcard);
		List<ExcelBean> headerList =new ArrayList<>();
		Map<Integer, List<ExcelBean>> map=new LinkedHashMap<>();
		 XSSFWorkbook xssfWorkbook=null;
	    //设置  标题栏
	    headerList.add(new ExcelBean("客户名称","customerName",0));
	    headerList.add(new ExcelBean("身份证号","idcard",0));
	    headerList.add(new ExcelBean("社保账户","sbcard",0));
	    headerList.add(new ExcelBean("缴费基数","basepay",0));
	    headerList.add(new ExcelBean("应缴金额","mustpay",0));
	    headerList.add(new ExcelBean("个人比例","personratio",0));
	    headerList.add(new ExcelBean("养老保险","yanglao",0));
	    headerList.add(new ExcelBean("医疗保险","yiliao",0));
	    headerList.add(new ExcelBean("工商保险","gongshang",0));
	    headerList.add(new ExcelBean("失业保险","shiye",0));
	    headerList.add(new ExcelBean("生育保险","shengyu",0));
	    headerList.add(new ExcelBean("预交款日","paydate",0));
	    headerList.add(new ExcelBean("代理费用","proxyfee",0));
	    // 将标题栏信息 加入 到 map 集合中
	    map.put(0, headerList);
	    
	    // 定义excel文件中要生成的 工作表名 即：sheet的名称
	    String sheetName = "社保信息";
	   
	    //调用ExcelUtil工具类的方法，生成excel工作表对象
	    xssfWorkbook = ExcelUtils.createExcelFile(SocialInsuranceResult.class, socialInsuranceResults, map, sheetName);
	    
	    return xssfWorkbook;
	}
	
	/**
	 * 文件导入功能的实现
	 */
	@Override
	public void uploadPayerCreditInfoExcel(InputStream in, MultipartFile excel) throws Exception {
		List<List<Object>> listob = ExcelUtils.getBankListByExcel(in, excel.getOriginalFilename());
		List<SocialInsurance> socialInsurances = new ArrayList<SocialInsurance>();
		List<Customer> customers=new ArrayList<Customer>();
		
		for (int i = 0; i < listob.size(); i++) {
			List<Object> ob = listob.get(i);
			SocialInsurance socialInsurance = new SocialInsurance();
			Customer customer=new Customer();
			customer.setName(String.valueOf(ob.get(0)));
			socialInsurance.setIdcard(String.valueOf(ob.get(1)));
			customer.setIdcard(String.valueOf(ob.get(1)));
			socialInsurance.setSbcard(String.valueOf(ob.get(2)));
		    socialInsurance.setBasepay(Double.valueOf(String.valueOf(ob.get(3))));
		    socialInsurance.setMustpay(Double.valueOf(String.valueOf(ob.get(4))));
		    socialInsurance.setPersonratio(String.valueOf(ob.get(5)));
		    socialInsurance.setCompanyratio(String.valueOf(ob.get(6)));
		    socialInsurance.setYanglao(Double.valueOf(String.valueOf(ob.get(7))));
		    socialInsurance.setYiliao(Double.valueOf(String.valueOf(ob.get(8))));		        
		    socialInsurance.setGongshang(Double.valueOf(String.valueOf(ob.get(9))));
		    socialInsurance.setShiye(Double.valueOf(String.valueOf(ob.get(10))));
		    socialInsurance.setShengyu(Double.valueOf(String.valueOf(ob.get(11))));
		    socialInsurance.setPaydate(DateUtil.parseYYYYMMDDDate(String.valueOf(ob.get(12))));
		    socialInsurance.setProxyfee(Double.valueOf(String.valueOf(ob.get(13))));
			// 省略其他属性的设置 ......
			socialInsurances.add(socialInsurance);
			customers.add(customer);
		}
		
		// 根据公司信用，判断数据库中是否有重复记录，如果没有，则说明需要将excel中的记录保存到数据库
		for (SocialInsurance socialInsurance : socialInsurances) {
			String idcard = socialInsurance.getIdcard();
			SocialInsurance socialInsurance2=socialInsuranceDao.selectByIdCard(idcard);
			if (socialInsurance2==null) {
				socialInsuranceDao.insert(socialInsurance);
			}
		}
		
		for (Customer customer : customers) {
			String idcard = customer.getIdcard();
			Customer customer2=customerDao.selectByIdcard(idcard);
			if (customer2==null) {
				customerDao.insert(customer);
			}
		}

	}

}
