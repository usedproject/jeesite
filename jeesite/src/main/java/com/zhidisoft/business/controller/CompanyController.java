package com.zhidisoft.business.controller;

import java.beans.IntrospectionException;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.print.DocFlavor.STRING;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhidisoft.business.entity.Company;
import com.zhidisoft.business.service.CompanyService;
import com.zhidisoft.system.entity.SystemUser;
import com.zhidisoft.utils.JsonResult;
import com.zhidisoft.utils.PageResult;

/**
 * 客户管理中的公司表的controller层
 * @author 张磊
 * @date 2018年3月17日
 */
@Controller
@RequestMapping("/business/company")
public class CompanyController {

	@Resource
	private CompanyService companyService;
	
	/**
	 * 这是公司列表的跳转页面
	 * @return
	 */
	@RequestMapping("/tocompany")
	public String toList() {
		return "clientInfo/listCompany";
	}
	
	/**
	 * 这是公司客户列表的分页功能的实现
	 * @param company
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/getList")
	@ResponseBody
	public JsonResult getList(Company company,Integer page,Integer rows) {
		List<Company> companyList=companyService.getListByPage(company,page,rows);
		Integer total=companyService.count(company);
		PageResult pageResult=new PageResult();
		pageResult.setRows(companyList);
		pageResult.setTotal(total);
		return JsonResult.buildSuccessResult("", pageResult);
	}
	
	/**
	 * 这是增加公司用户页面的跳转方法
	 * @return
	 */
	@RequestMapping("/toAddCompany")
	public String toAddCompany() {
		return "clientInfo/addCompany";
	}
	
	/**
	 * 这是前端远程验证是否重名的方法
	 * @return
	 */
	@RequestMapping("/check") 
	@ResponseBody
	public boolean checkName(String name) {
		List<Company> company= companyService.checkName(name);
		if (company==null||company.size()<1) {
			return true;
		}
		return false;
	}
	
	/**
	 * 这是用于增加公司客户的实现方法
	 * @param company
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public JsonResult addCompany(Company company,HttpSession session) {
		Integer i= companyService.addCompany(company,session);
		if (i>0) {
			return JsonResult.buildSuccessResult("添加成功");
		}
		return JsonResult.buildFailureResult("添加失败");
	}
	
	/**
	 * 这是修改公司客户信息的方法
	 * @param id    公司客户的id
	 * @param model 把公司客户存放到request域中
	 * @return
	 */
	@RequestMapping("/toEditCompany/{id}")
	public String toEditCompany(@PathVariable("id")Integer id,Model model) {
		Company company= companyService.selectById(id);
		model.addAttribute("company", company);
		return "clientInfo/editCompany";
	}
	
	/**
	 * 这是修改公司客户的方法
	 * @param company  前端参数封装到company中
	 * @param session  获取当前用户用于获取修改公司的用户信息
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public JsonResult editCompany(Company company,HttpSession session) {
		Integer i= companyService.editCompany(company,session);
		if (i>0) {
			return JsonResult.buildSuccessResult("修改成功");
		}
		return JsonResult.buildFailureResult("修改失败");
	}
	
	/**
	 * 这是通过前端ajax请求搜索所有公司的方法
	 * @return
	 */
	@RequestMapping("/selectAll")
	@ResponseBody
	public JsonResult selectAll() {
		List<Company> companies= companyService.selectAll();
		return JsonResult.buildSuccessResult("", companies);
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
				workbook = companyService.exportExcelInfo(recordIds);
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IntrospectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParseException e1) {
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
		return "clientInfo/selectExcel";
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
			companyService.uploadPayerCreditInfoExcel(in, excel);
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
		 companyService.deleteByid(ids);
		 return JsonResult.buildSuccessResult("删除成功");
	}
}
