package com.zhidisoft.business.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;




import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhidisoft.business.entity.Customer;
import com.zhidisoft.business.service.CustomerService;
import com.zhidisoft.utils.JsonResult;
import com.zhidisoft.utils.PageResult;

/**
 * 这是个人客户表对应的controller层
 * @author 张磊
 * @date 2018年3月17日
 */
@RequestMapping("/business/customer")
@Controller
public class CustomerController {
	
	@Resource
	CustomerService customerService;
	
	/**
	 * 这是customer列表页面的跳转功能
	 * @return
	 */
	@RequestMapping("/toCustomer")
	public String toCustomer() {
		return "customerInfo/listCustomer";
	}
	
	/**
	 * 这是实现customer分页的具体方法
	 * @return
	 */
	@RequestMapping("/getList")
	@ResponseBody
	public JsonResult getList(Customer customer,Integer page,Integer rows) {
		List<Customer> customers=customerService.getListByPage(customer,page,rows);
		Integer total=customerService.count(customer);
		PageResult pageResult=new PageResult();
		pageResult.setRows(customers);
		pageResult.setTotal(total);
		return JsonResult.buildSuccessResult("", pageResult);
	}
	/**
	 * 获取所有的客户信息
	 * @return
	 */
	@RequestMapping("/selectAll")
	@ResponseBody
	public List<Map<String, Object>> selectAll() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Customer> customers = customerService.selectAll();
		for(Customer customer : customers) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", customer.getName());
			map.put("idcard", customer.getIdcard());
			map.put("companyid", customer.getCompanyid());
			map.put("phone", customer.getPhone());
			map.put("address", customer.getAddress());
			list.add(map);
		}
		return list;
	}
	/**
	 * 这是进行增加个人客户的页面跳转功能
	 * @return
	 */
	@RequestMapping("/toAddCustomer")
	public String toAddCustomer(String name,String idcard,Model model) {
		model.addAttribute("name", name);
		model.addAttribute("idcard", idcard);
		return "customerInfo/addCustomer";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public JsonResult add(Customer customer,HttpSession session,String issalary,String isshebao,String isgongjijin) {
		if (issalary!=null&& issalary.equals("on")) {
			customer.setIssalary("0");
		}
		if (isshebao!=null&&isshebao.equals("on")) {
			customer.setIsshebao("0");
		}
		if (isgongjijin!=null&&isgongjijin.equals("on")) {
			customer.setIsgongjijin("0");
		}
		int i= customerService.add(customer,session);
		if (i>0) {
			return JsonResult.buildSuccessResult("添加客户成功");
		}else {
			return JsonResult.buildFailureResult("添加客户失败");
		}
		
	}
	
	/**
	 * 这是进行编辑个人客户的页面跳转的功能
	 * @param id    前端被勾选的个人客户的id
	 * @param model
	 * @return
	 */
	@RequestMapping("/toEditCustomer")
	public String toEditCustomer(Integer id ,Model model) {
		Customer customer=customerService.selectById(id);
		model.addAttribute("customer", customer);
		return "customerInfo/editCustomer";
	}
	
	@RequestMapping("/editCustomer")
	@ResponseBody
	public JsonResult editCustomer(Customer customer,HttpSession session,String issalary,String isshebao,String isgongjijin) {
		if (issalary!=null&& issalary.equals("on")) {
			customer.setIssalary("0");
		}
		if (isshebao!=null&&isshebao.equals("on")) {
			customer.setIsshebao("0");
		}
		if (isgongjijin!=null&&isgongjijin.equals("on")) {
			customer.setIsgongjijin("0");
		}
		int i= customerService.editCustomer(customer,session);
		if (i>0) {
			return JsonResult.buildSuccessResult("修改成功");
		}else {
			return JsonResult.buildFailureResult("修改失败");
		}
	}
	
	/**
	 * 这是通过前端校验客户是否存在的方法
	 * @param name  客户名
	 * @return
	 */
	@RequestMapping("/check")
	@ResponseBody
	public boolean check(String name) {
		Customer customer= customerService.check(name);
		if (customer!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 这是通过ajax进行搜索个人客户的功能
	 * @param name    用户名
	 * @param idcard  身份证
	 * @return
	 */
	@RequestMapping("/selectByIdOrName")
	@ResponseBody
	public JsonResult selectByIdOrName(String name,String idcard) {
		Customer customer=customerService.selectByNameOrIdCard(name,idcard);
		if (customer!=null) {
			return JsonResult.buildSuccessResult("", customer);
		}
		return JsonResult.buildFailureResult("该用户不存在");
	}
	
	/**
	 * 这是点击导出按钮的实现功能
	 */
	@RequestMapping("/export")
	public void export(HttpServletResponse response,String recordIds) {
		// 清除buffer缓存
			response.reset(); 
		//设置文件类型： application/vnd.ms-excel 
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
		//设置响应头：  以附件的形式下载，并指定默认文件名为：table.xlsx
			response.setHeader("Content-Disposition", "attachment;filename=table.xlsx");
			XSSFWorkbook workbook = null;
		//userService生成要导出的Excel对象： XSSFWorkbook(sheet\header\row\cell)
			try {
				workbook = customerService.exportExcelInfo(recordIds);
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			OutputStream output;
			try {
				// 从response中获取输出流
				output = response.getOutputStream();			
				BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
				// 将要 导出的 Excel对象 写入 输出流
				workbook.write(bufferedOutPut);
				// 刷出 输出流中的所有数据
				bufferedOutPut.flush();
				// 关闭输出流
				bufferedOutPut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * 这是选择Excel文件页面跳转功能
	 * @return
	 */
	@RequestMapping("/toSelectExcel")
	public String toSelectExcel() {
		return "customerInfo/selectExcel";
	}
	
	/**
	 * 这是导入功能的实现
	 * @param excel
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/uploadExcel")
	@ResponseBody
	public JsonResult uploadExcel(MultipartFile excel) throws IOException {
		InputStream in =null;
		try {
			 in = excel.getInputStream();
			customerService.uploadPayerCreditInfoExcel(in, excel);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.buildFailureResult("文件导入失败");
		}
		if (in!=null) {
			in.close();
		}
		return JsonResult.buildSuccessResult("文件导入成功");
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public JsonResult delete(String userIds) {
		String[] ids = userIds.split(",");
		int i=customerService.deleteById(ids);
		if (i>0) {
			return JsonResult.buildSuccessResult("删除成功");
		}else {
			return JsonResult.buildFailureResult("删除失败");
		}
	}
	
}
