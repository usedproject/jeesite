package com.zhidisoft.business.service;

import java.beans.IntrospectionException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zhidisoft.business.dao.CompanyDao;
import com.zhidisoft.business.entity.Company;
import com.zhidisoft.system.entity.SystemUser;
import com.zhidisoft.utils.ExcelBean;
import com.zhidisoft.utils.ExcelUtils;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Resource
	private CompanyDao companyDao;

	@Override
	public List<Company> getListByPage(Company company, Integer page, Integer rows) {
		return companyDao.getListByPage(company.getName(),company.getCompanyno(),company.getIdcard(),(page-1)*rows,rows);
	}

	@Override
	public Integer count(Company company) {
		
		return companyDao.count(company.getName(),company.getCompanyno(),company.getIdcard());
	}

	@Override
	public List<Company> checkName(String name) {
		return companyDao.checkName(name);
	}

	@Override
	public Integer addCompany(Company company, HttpSession session) {
		if (company.getAddress()=="") {
			company.setAddress(null);
		}
		if (company.getRemark()=="") {
			company.setRemark(null);
		}
		SystemUser user= (SystemUser) session.getAttribute("user");
		company.setCreateby(user.getId());
		company.setCreatetime(new Date());
		return companyDao.insert(company);
	}

	@Override
	public Company selectById(Integer id) {
		return companyDao.selectByPrimaryKey(id);
	}

	@Override
	public Integer editCompany(Company company, HttpSession session) {
		if (company.getAddress()=="") {
			company.setAddress(null);
		}
		if (company.getRemark()=="") {
			company.setRemark(null);
		}
		SystemUser user= (SystemUser) session.getAttribute("user");
		company.setUpdateby(user.getId());
		company.setUpdatetime(new Date());
		return companyDao.updateByPrimaryKey(company);
	}

	@Override
	public List<Company> selectAll() {
		return companyDao.selectAll();
	}
	
	/**
	 * 导出信息到Excel中的方法
	 * @throws ParseException 
	 * @throws IntrospectionException 
	 * @throws ClassNotFoundException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Override
	public XSSFWorkbook exportExcelInfo(String recaordIds) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException {
		String[] ids = recaordIds.split(",");
		List<Company> companies=companyDao.selectByIds(ids);
		List<ExcelBean> headerList =new ArrayList<>();
		Map<Integer, List<ExcelBean>> map=new LinkedHashMap<>();
		 XSSFWorkbook xssfWorkbook=null;
	    //设置  标题栏
	    headerList.add(new ExcelBean("公司编号","id",0));
	    headerList.add(new ExcelBean("公司名称","name",0));
	    headerList.add(new ExcelBean("公司地址","address",0));
	    headerList.add(new ExcelBean("邮编","zipcode",0));
	    headerList.add(new ExcelBean("公司电话","telphone",0));
	    headerList.add(new ExcelBean("统一社会信用代码","companyno",0));
	    headerList.add(new ExcelBean("法人","owner",0));
	    headerList.add(new ExcelBean("身份证号","idcard",0));
	    headerList.add(new ExcelBean("法人电话","phone",0));
	    headerList.add(new ExcelBean("法人性别","sex",0));
	    headerList.add(new ExcelBean("电子邮件","email",0));
	    headerList.add(new ExcelBean("公司性质","ownership",0));
	    headerList.add(new ExcelBean("公司类别","companytype",0));
	    // 将标题栏信息 加入 到 map 集合中
	    map.put(0, headerList);
	    
	    // 定义excel文件中要生成的 工作表名 即：sheet的名称
	    String sheetName = "公司信息";
	   
	    //调用ExcelUtil工具类的方法，生成excel工作表对象
	    xssfWorkbook = ExcelUtils.createExcelFile(Company.class, companies, map, sheetName);
	    
	    return xssfWorkbook;
	}

	/**
	 * 这是导入excel记录的方法
	 * @throws Exception 
	 */
	@Override
	public void uploadPayerCreditInfoExcel(InputStream in, MultipartFile excel) throws Exception {
		List<List<Object>> listob = ExcelUtils.getBankListByExcel(in, excel.getOriginalFilename());
		List<Company> companyList = new ArrayList<Company>();
		for (int i = 0; i < listob.size(); i++) {
			List<Object> ob = listob.get(i);
			Company company = new Company();
			 	company.setName(String.valueOf(ob.get(1)));
		        company.setAddress(String.valueOf(ob.get(2)));
		        company.setCompanyno(String.valueOf(ob.get(3)));
		        company.setOwner(String.valueOf(ob.get(4)));
		        company.setIdcard(String.valueOf(ob.get(5)));
		        company.setSex(String.valueOf(ob.get(6)));
		        company.setOwnership(String.valueOf(ob.get(7)));		        
		        company.setZipcode(String.valueOf(ob.get(8)));
		        company.setRemark(String.valueOf(ob.get(9)));
			// 省略其他属性的设置 ......
			companyList.add(company);
		}
		
		// 根据公司信用，判断数据库中是否有重复记录，如果没有，则说明需要将excel中的记录保存到数据库
		for (Company company : companyList) {
			String companyno = company.getCompanyno();
			Company company2=companyDao.selectByCompanyNo(companyno);
			if (company2==null) {
				companyDao.insert(company);
			}
		}

	}

	@Override
	public void deleteByid(String[] ids) {
		companyDao.deleteById(ids);
	}

	

}
