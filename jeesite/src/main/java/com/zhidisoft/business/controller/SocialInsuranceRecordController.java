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

import com.zhidisoft.business.entity.SocialInsuranceRecord;
import com.zhidisoft.business.service.SocialInsuranceRecordService;
import com.zhidisoft.system.entity.SystemUser;
import com.zhidisoft.utils.JsonResult;

@Controller
@RequestMapping("/business/socialInsuranceRecord")
public class SocialInsuranceRecordController {
	@Resource
	SocialInsuranceRecordService service;
	
	/**
	 * 跳转到展示的jsp页面
	 */
	@RequestMapping("/shebaojilu")
	public String toList() {
		return "socialInsuranceRecordInfo/listSocialInsuranceRecord";
	}
	
	/**
	 * 跳转到缴费的jsp页面
	 * @return
	 */
	@RequestMapping("/shebaojiaofei")
	public String toPay() {
		return "socialInsurancePayInfo/listSocialInsurancePay";
	}
	
	/**
	 * 展示分页列表
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> getListByPage(Integer page, Integer rows, String customerName, String idcard, String sbcard) {
		List<SocialInsuranceRecord> insurances = service.getListByPage(page, rows, customerName, idcard, sbcard);
		Integer total = service.getCount(customerName, idcard, sbcard);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", insurances);
		return map;
	}
	
	/**
	 * 跳转到修改的jsp页面
	 */
	@RequestMapping("/toEdit")
	public String toAdd(String id, Model model) {
		model.addAttribute("id", id);
		return "socialInsuranceRecordInfo/editSocialInsuranceRecord";
	}
	
	/**
	 * 修改社保信息记录
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public JsonResult edit(SocialInsuranceRecord insuranceRecord, Integer id, HttpSession session) {
		SystemUser user = (SystemUser) session.getAttribute("user");
		insuranceRecord.setUpdateby(user.getUpdateby());
		insuranceRecord.setUpdatetime(new Date());
		Integer i = service.edit(insuranceRecord, id);
		if(i<1) {
			return JsonResult.buildFailureResult("修改失败！");
		}
		return JsonResult.buildSuccessResult("修改成功！");
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public JsonResult add(SocialInsuranceRecord insuranceRecord, HttpSession session) {
		SystemUser user = (SystemUser) session.getAttribute("user");
		insuranceRecord.setCreateby(user.getCreateby());
		insuranceRecord.setCreatetime(new Date());
		Integer i = service.add(insuranceRecord);
		if(i<1) {
			return JsonResult.buildFailureResult("添加失败！");
		}
		return JsonResult.buildSuccessResult("添加成功！");
	}
	
	/**
	 * 获取所有的记录
	 * @return
	 */
	@RequestMapping("/selectAll")
	@ResponseBody
	public List<SocialInsuranceRecord> selectAll() {
		List<SocialInsuranceRecord> insuranceRecords = service.selectAll();
		return insuranceRecords;
	}
	
	@RequestMapping("/getStatistics")
	@ResponseBody
	public Map<String, Object> getStatistics(Integer page, Integer rows, String customerName, String idcard, String sbcard, String companyName) {
		List<Map<String, Object>> insurances = service.getStatistics(page, rows, customerName, idcard, sbcard, companyName);
		Integer total = service.getStatisticCount(customerName, idcard, sbcard,companyName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", insurances);
		return map;
	}
	
	/**
	 * 导出为excel文件 
	 */
	@RequestMapping("/export")
	public void export(HttpServletResponse response,String customerName, String idcard, String sbcard, String companyName) throws InvocationTargetException, ClassNotFoundException,
			IllegalAccessException, IntrospectionException, ParseException, IOException {
		// 清除buffer缓存
		response.reset(); 
		//设置文件类型： application/vnd.ms-excel 
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		//设置响应头：  以附件的形式下载，并指定默认文件名为：table.xlsx
		response.setHeader("Content-Disposition", "attachment;filename=table.xlsx");

		
		XSSFWorkbook workbook = null;
		//userService生成要导出的Excel对象： XSSFWorkbook
		workbook = service.exportExcelInfo(customerName, idcard, sbcard,companyName);
		
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
}
