package com.zhidisoft.business.controller;

import java.beans.IntrospectionException;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
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

import com.zhidisoft.business.entity.Salary;
import com.zhidisoft.business.service.SalaryService;
import com.zhidisoft.system.entity.SystemUser;
import com.zhidisoft.utils.JsonResult;
import com.zhidisoft.utils.SalaryResult;
import com.zhidisoft.utils.StatisticsGongzi;

@Controller
@RequestMapping("/business/salary")
public class SalaryController {
	@Resource
	SalaryService salaryService;
	
	/**
	 * 返回字符串，跳转到列表的jsp页面
	 * @return
	 */
	@RequestMapping("/gongzichaxun")
	public String toListSalary() {
		return "salaryInfo/listSalary";
	}
	
	@RequestMapping("/daifagongzi")
	public String toAdd() {
		return "salaryInfo/addSalary";
	}
	
	@RequestMapping("/gongzitiao")
	public String toGongzitiao() {
		return "salaryInfo/listGongzitiao";
	}
	
	/**
	 * 分页查询工资
	 */
	@RequestMapping("/getList")
	@ResponseBody
	public Map<String, Object> selectSalaryByPage(Integer page, Integer rows,String customerName, String idcard) {
		List<SalaryResult> salaryList = salaryService.selectSalaryByPage(page, rows,customerName, idcard);
		Integer total = salaryService.getCount(customerName, idcard);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", salaryList);		
		return map;
		
	}
	
	/**
	 * 查询所有的工资
	 * @return
	 */
	@RequestMapping("/selectAll")
	@ResponseBody
	public List<Salary> selectAll() {
		List<Salary> salary = salaryService.selectAll();
		return salary;
	}
	
	
	/**
	 * 跳转到修改
	 * @return
	 */
	@RequestMapping("/toEdit")
	public String toEdit(Model model, String id) {
		model.addAttribute("id", id);
		return "salaryInfo/edit";
	}
	
	/**
	 * 导出为excel文件 
	 */
	@RequestMapping("/export")
	public void export(HttpServletResponse response,String customerName, String idcard) throws InvocationTargetException, ClassNotFoundException,
			IllegalAccessException, IntrospectionException, ParseException, IOException {
		// 清除buffer缓存
		response.reset(); 
		//设置文件类型： application/vnd.ms-excel 
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		//设置响应头：  以附件的形式下载，并指定默认文件名为：table.xlsx
		response.setHeader("Content-Disposition", "attachment;filename=table.xlsx");

		
		XSSFWorkbook workbook = null;
		//userService生成要导出的Excel对象： XSSFWorkbook
		workbook = salaryService.exportExcelInfo(customerName, idcard);
		
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
	 * 录入代发工资
	 */
	@RequestMapping("/add")
	@ResponseBody
	public JsonResult add(Salary salary, HttpSession session) {
		SystemUser user = (SystemUser) session.getAttribute("user");
		salary.setCreateby(user.getId());
		salary.setCreatetime(new Date());
		Integer i = salaryService.add(salary);
		if(i<1) {
			return JsonResult.buildFailureResult("录入失败");
		}
		return JsonResult.buildSuccessResult("录入成功");
	}
	
	@RequestMapping("/listGongzitiao")
	@ResponseBody
	public Map<String, Object> listGongzitiao(Integer page, Integer rows, String customerName, String idcard) {
		List<Map<String, Object>> list = salaryService.listGongzitiao(page, rows, customerName, idcard);
		Integer total  = salaryService.getCountGongzitiao(customerName, idcard);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", total);
		return map;
	}
	
	/**
	 * 获取统计信息的记录
	 */
	@RequestMapping("/getStatistics")
	@ResponseBody
	public Map<String, Object> getStatistics(Integer page, Integer rows, String customerName, String idcard, String paycard, String companyName) {
		List<StatisticsGongzi> list = salaryService.getStatistics(page, rows, customerName, idcard, paycard, companyName);
		Integer total = salaryService.getStatisticsCount(customerName, idcard, paycard, companyName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}
}
