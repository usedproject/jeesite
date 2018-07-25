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

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zhidisoft.business.dao.CustomerDao;
import com.zhidisoft.business.entity.Company;
import com.zhidisoft.business.entity.Customer;
import com.zhidisoft.system.entity.SystemUser;
import com.zhidisoft.utils.ExcelBean;
import com.zhidisoft.utils.ExcelUtils;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Resource
	CustomerDao customerDao;

	@Override
	public List<Customer> getListByPage(Customer customer, Integer page, Integer rows) {
		return customerDao.getListByPage(customer.getName(),customer.getIdcard(),customer.getCompanyid(),(page-1)*rows,rows);
	}

	@Override
	public Integer count(Customer customer) {
		return customerDao.count(customer.getName(),customer.getIdcard(),customer.getCompanyid());
	}


	@Override
	public List<Customer> selectAll() {
		// TODO Auto-generated method stub
		return customerDao.selectAll();
	}


	@Override
	public int add(Customer customer,HttpSession session) {
		if (customer.getAddress()=="") {
			customer.setAddress(null);
		}
		if (customer.getZipcode()=="") {
			customer.setZipcode(null);
		}
		if (customer.getSchool()=="") {
			customer.setSchool(null);
		}
		if (customer.getSpecialty()=="") {
			customer.setSpecialty(null);
		}
		customer.setCreatetime(new Date());
		SystemUser user= (SystemUser) session.getAttribute("user");
		customer.setCreateby(user.getId());
		if (customer.getRemark()=="") {
			customer.setRemark(null);
		}
		return customerDao.insert(customer);
	}

	@Override
	public Customer selectById(Integer id) {
		return customerDao.selectByPrimaryKey(id);
	}

	@Override
	public int editCustomer(Customer customer,HttpSession session) {
		SystemUser user= (SystemUser) session.getAttribute("user");
		customer.setUpdateby(user.getId());
		customer.setUpdatetime(new Date());
		return customerDao.updateByPrimaryKey(customer);
	}
	
	/**
	 * 这是通过前端校验客户是否存在的方法
	 * @param name  客户名
	 * @return
	 */
	@Override
	public Customer check(String name) {
		return customerDao.check(name);
	}

	@Override
	public Customer selectByNameOrIdCard(String name, String idcard) {
		return customerDao.selectByIdOrName(name, idcard);
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
		List<Customer> customers=customerDao.selectByIds(ids);
		List<ExcelBean> headerList =new ArrayList<>();
		Map<Integer, List<ExcelBean>> map=new LinkedHashMap<>();
		 XSSFWorkbook xssfWorkbook=null;
	    //设置  标题栏
		 headerList.add(new ExcelBean("序号","id",0));
		 headerList.add(new ExcelBean("客户名称","name",0));
		 headerList.add(new ExcelBean("身份证号","idcard",0));
		 headerList.add(new ExcelBean("性别","sex",0));
		 headerList.add(new ExcelBean("出生年月","birthday",0));
		 headerList.add(new ExcelBean("手机号码","phone",0));
		 headerList.add(new ExcelBean("客户联系地址","address",0));
		 headerList.add(new ExcelBean("邮编","zipcode",0));
		 headerList.add(new ExcelBean("电子邮件","email",0));
		 headerList.add(new ExcelBean("毕业学校","school",0));
		 headerList.add(new ExcelBean("工资薪酬","issalary",0));
		 headerList.add(new ExcelBean("社保信息","isshebao",0));
	     headerList.add(new ExcelBean("公积金","isgongjijin",0));

	    // 将标题栏信息 加入 到 map 集合中
	    map.put(0, headerList);
	    
	    // 定义excel文件中要生成的 工作表名 即：sheet的名称
	    String sheetName = "个人信息";
	   
	    //调用ExcelUtil工具类的方法，生成excel工作表对象
	    xssfWorkbook = ExcelUtils.createExcelFile(Customer.class, customers, map, sheetName);
	    
	    return xssfWorkbook;
		}

	/**
	 * 这是导入excel记录的方法
	 * @throws Exception 
	 */
	@Override
	public void uploadPayerCreditInfoExcel(InputStream in, MultipartFile excel) throws Exception {
		List<List<Object>> listob = ExcelUtils.getBankListByExcel(in, excel.getOriginalFilename());
		List<Customer> customers = new ArrayList<Customer>();
		for (int i = 0; i < listob.size(); i++) {
			List<Object> ob = listob.get(i);
			Customer customer = new Customer();
			customer.setName(String.valueOf(ob.get(1)));
		    customer.setIdcard(String.valueOf(ob.get(2)));
		    customer.setSex(String.valueOf(ob.get(3)));
		    customer.setBirthday(DateUtil.parseYYYYMMDDDate(String.valueOf(ob.get(4))));
		    customer.setPhone(String.valueOf(ob.get(5)));
		    customer.setEmail(String.valueOf(ob.get(6)));
		    customer.setAddress(String.valueOf(ob.get(7)));
		    customer.setZipcode(String.valueOf(ob.get(8)));
		    customer.setSchool(String.valueOf(ob.get(9)));
		    customer.setCustomertype(String.valueOf(ob.get(10)));
			// 省略其他属性的设置 ......
			customers.add(customer);
		}
		
		// 根据公司信用，判断数据库中是否有重复记录，如果没有，则说明需要将excel中的记录保存到数据库
		for (Customer customer : customers) {
			String idcard = customer.getIdcard();
			Customer customer2=customerDao.selectByIdcard(idcard);
			if (customer2==null) {
				customerDao.insert(customer);
			}
		}

	}

	@Override
	public int deleteById(String[] ids) {
		
		return customerDao.deleteById(ids);
	}
}
