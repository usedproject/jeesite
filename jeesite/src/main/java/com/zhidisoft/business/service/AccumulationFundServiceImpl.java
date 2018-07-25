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

import com.zhidisoft.business.dao.AccumulationFundDao;
import com.zhidisoft.business.dao.CustomerDao;
import com.zhidisoft.business.entity.AccumulationFund;
import com.zhidisoft.business.entity.Company;
import com.zhidisoft.business.entity.Customer;
import com.zhidisoft.system.entity.SystemUser;
import com.zhidisoft.utils.DateUtil;
import com.zhidisoft.utils.ExcelBean;
import com.zhidisoft.utils.ExcelUtils;
import com.zhidisoft.utils.StatisticsGongjijin;
import com.zhidisoft.utils.StatisticsShebao;

@Service
public class AccumulationFundServiceImpl implements AccumulationFundService {
	
	@Resource
	AccumulationFundDao accumulationFunDao;
	
	@Resource
	CustomerDao customerDao;

	@Override
	public List<AccumulationFund> getListByPage(Integer page, Integer rows, String accountno, String idcard) {
		page=(page-1)*rows;
		return accumulationFunDao.getListByPage(page,rows,accountno,idcard);
	}

	@Override
	public Integer count(String accountno, String idcard) {
		
		return accumulationFunDao.count(accountno,idcard);
	}

	@Override
	public List<AccumulationFund> selectAll() {
		return accumulationFunDao.selectAll();
	}

	@Override
	public AccumulationFund selectByIdOrName(String name, String idcard) {
		return accumulationFunDao.selectByIdOrName(name,idcard);
	}

	@Override
	public int edit(AccumulationFund accumulationFund,HttpSession session) {
		SystemUser user= (SystemUser) session.getAttribute("user");
		accumulationFund.setUpdateby(user.getId());
		accumulationFund.setUpdatetime(new Date());
		return accumulationFunDao.updateByPrimaryKey(accumulationFund);
	}

	@Override
	public int add(AccumulationFund accumulationFund,HttpSession session) {
		SystemUser user= (SystemUser) session.getAttribute("user");
		accumulationFund.setCreateby(user.getId());
		accumulationFund.setCreatetime(new Date());
		return accumulationFunDao.insert(accumulationFund);
	}

	@Override
	public AccumulationFund selectById(Integer id) {
		return  accumulationFunDao.selectByPrimaryKey(id);
	}


	@Override
	public XSSFWorkbook exportExcelInfo(String recordIds) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException {
		String[] ids = recordIds.split(",");
		List<AccumulationFund> accumulationFunds=accumulationFunDao.selectByIds(ids);
		List<ExcelBean> headerList =new ArrayList<>();
		Map<Integer, List<ExcelBean>> map=new LinkedHashMap<>();
		 XSSFWorkbook xssfWorkbook=null;
		//设置  标题栏
	    headerList.add(new ExcelBean("编号","id",0));
	    headerList.add(new ExcelBean("客户姓名","name",0));
	    headerList.add(new ExcelBean("身份证号","idcard",0));
	    headerList.add(new ExcelBean("公积金账户","accountno",0));
	    headerList.add(new ExcelBean("缴费期间","paydate",0));
	    headerList.add(new ExcelBean("缴费金额","paymoney",0));
	    headerList.add(new ExcelBean("代理费用","proxyfee",0));
	    headerList.add(new ExcelBean("状态","status",0));
	    //headerList.add(new ExcelBean("创建时间","createtime",0));
	    //headerList.add(new ExcelBean("创建者","createby",0));

	    // 将标题栏信息 加入 到 map 集合中
	    map.put(0, headerList);
	    
	    // 定义excel文件中要生成的 工作表名 即：sheet的名称
	    String sheetName = "公司信息";
	   
	    //调用ExcelUtil工具类的方法，生成excel工作表对象
	    xssfWorkbook = ExcelUtils.createExcelFile(AccumulationFund.class, accumulationFunds, map, sheetName);
	    
	    return xssfWorkbook;
		}


	@Override
	public List<Map<String, Object>> getStatistics(Integer page, Integer rows, String customerName, String idcard,
			String accountno, String companyName) {
		// TODO Auto-generated method stub
		return accumulationFunDao.getStatistics((page-1)*rows, rows, customerName, idcard, accountno, companyName);
	}

	@Override
	public Integer getStatisticsCount(String customerName, String idcard, String accountno, String companyName) {
		// TODO Auto-generated method stub
		return accumulationFunDao.getStatisticsCount(customerName, idcard, accountno, companyName);
	}

	/**
	 * 这是导入excel记录的方法
	 * @throws Exception 
	 */
	@Override
	public void uploadPayerCreditInfoExcel(InputStream in, MultipartFile excel) throws Exception {
		List<List<Object>> listob = ExcelUtils.getBankListByExcel(in, excel.getOriginalFilename());
		List<AccumulationFund> accumulationFunds = new ArrayList<AccumulationFund>();
		List<Customer> customers=new ArrayList<Customer>();
		for (int i = 0; i < listob.size(); i++) {
			List<Object> ob = listob.get(i);
			AccumulationFund accumulationFund = new AccumulationFund();
			Customer customer=new Customer();
			customer.setName(String.valueOf(ob.get(0)));
			accumulationFund.setAccountno(String.valueOf(ob.get(1)));
			accumulationFund.setIdcard(String.valueOf(ob.get(2)));
			customer.setIdcard(String.valueOf(ob.get(2)));
			accumulationFund.setPaydate(String.valueOf(ob.get(3)));
		    accumulationFund.setPaymoney(Double.valueOf(String.valueOf(ob.get(4))));
		    accumulationFund.setProxyfee(Double.valueOf(String.valueOf(ob.get(5))));
		    accumulationFund.setStatus(String.valueOf(ob.get(6)));
			accumulationFunds.add(accumulationFund);
			customers.add(customer);
		}
		
		// 根据公司信用，判断数据库中是否有重复记录，如果没有，则说明需要将excel中的记录保存到数据库
		for (AccumulationFund accumulationFund : accumulationFunds) {
			String accountno = accumulationFund.getAccountno();
			AccumulationFund accumulationFund2=accumulationFunDao.selectByAccountno(accountno);
			if (accumulationFund2==null) {
				accumulationFunDao.insert(accumulationFund);
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

	@Override
	public XSSFWorkbook exportExcelInfo(String customerName, String idcard, String accountno, String companyName) {
		//根据条件查询数据，把数据装载到一个list中
	    List<StatisticsShebao> list = accumulationFunDao.getStatisticsBy(customerName, idcard, accountno, companyName);

	    List<ExcelBean> headerList = new ArrayList<>();
	    Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
	    XSSFWorkbook xssfWorkbook=null;
	    
	    //设置  标题栏
	    headerList.add(new ExcelBean("姓名","customername",0));
	    headerList.add(new ExcelBean("身份证号","idcard",0));
	    headerList.add(new ExcelBean("公积金账号","accountno",0));
	    headerList.add(new ExcelBean("所属公司","companyname",0));
	    headerList.add(new ExcelBean("公积金月数","counts",0));
	    headerList.add(new ExcelBean("公积金总额","paymoney",0));
	    headerList.add(new ExcelBean("代理费用总额","proxyfee",0));
	    headerList.add(new ExcelBean("状态","status",0));
	    
	    
	    // 将标题栏信息 加入 到 map 集合中
	    map.put(0, headerList);
	    
	    // 定义excel文件中要生成的 工作表名 即：sheet的名称
	    String sheetName = "用户信息";
	   
	    //调用ExcelUtil工具类的方法，生成excel工作表对象
	    try {
			xssfWorkbook = ExcelUtils.createExcelFile(StatisticsGongjijin.class, list, map, sheetName);
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | ClassNotFoundException
				| IntrospectionException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return xssfWorkbook;
	}
}
