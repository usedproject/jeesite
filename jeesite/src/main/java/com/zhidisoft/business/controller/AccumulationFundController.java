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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhidisoft.business.entity.AccumulationFund;
import com.zhidisoft.business.service.AccumulationFundService;
import com.zhidisoft.utils.JsonResult;
import com.zhidisoft.utils.PageResult;

@Controller
@RequestMapping("/business/accumulationFund")
public class AccumulationFundController {
	
	@Resource
	AccumulationFundService accumulationFundService;
	
	/**
	 * 这是进行公积金列表页面跳转的方法
	 * @return
	 */
	@RequestMapping("/toAccumulationFund")
	public String toAccumulationFund() {
		return "accumulationFundInfo/listAccumulationFund";
	}
	
	/**
	 * 这是完成分页的通用方法
	 * @param page        第几页
	 * @param rows	                  显示几行
	 * @param accountno   公积金号
	 * @param idcard      身份证号
	 * @return
	 */
	@RequestMapping("/getList")
	@ResponseBody
	public JsonResult getList(Integer page,Integer rows,String accountno,String idcard) {
		List<AccumulationFund> accumulationFunds=accumulationFundService.getListByPage(page,rows,accountno,idcard);
		Integer total=accumulationFundService.count(accountno,idcard);
		PageResult pageResult=new PageResult();
		pageResult.setRows(accumulationFunds);
		pageResult.setTotal(total);
		return JsonResult.buildSuccessResult("", pageResult);
	}
	
	/**
	 * 这是进行增加页面弹出框页面跳转的功能实现
	 * @return
	 */
	@RequestMapping("/toAddAccumulationFund")
	public String toAddAccumulationFund() {
		return "accumulationFundInfo/addAccumulationFund";
	}
	
	/**
	 * 这是动态加载datalist的获取所有公积金信息的方法
	 * @return
	 */
	@RequestMapping("/selectAll")
	@ResponseBody
	public JsonResult selectAll() {
		List<AccumulationFund> accumulationFunds=accumulationFundService.selectAll();
		return JsonResult.buildSuccessResult("", accumulationFunds);
	}
	
	/**
	 * 这是通过前端传入客户姓名或者身份证号进行查询的方法
	 * @param name			客户姓名
	 * @param idcard		身份证号
	 * @return
	 */
	@RequestMapping("/selectByIdOrName")
	@ResponseBody
	public JsonResult selectByIdOrName(String name,String idcard) {
		AccumulationFund accumulationFund=accumulationFundService.selectByIdOrName(name,idcard);
		if (accumulationFund!=null) {
			return JsonResult.buildSuccessResult("", accumulationFund);
		}
		return JsonResult.buildFailureResult("此信息第一次输入");
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public JsonResult add(AccumulationFund accumulationFund,HttpSession session) {
		int i=0;
		if (accumulationFund.getId()!=null) {
			i= accumulationFundService.edit(accumulationFund,session);
		}else {
			i= accumulationFundService.add(accumulationFund,session);
		}
		if (i>0) {
			return JsonResult.buildSuccessResult("操作成功");
		}
		return JsonResult.buildFailureResult("操作失败");
	}
	
	/**
	 * 这是编辑公积金表的页面跳转按钮
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/toEditAccumulationFund")
	public String toEditAccumulationFund(Integer id,Model model) {
		AccumulationFund accumulationFund=accumulationFundService.selectById(id);
		model.addAttribute("af", accumulationFund);
		return "accumulationFundInfo/editAccumulationFund";
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
				workbook = accumulationFundService.exportExcelInfo(recordIds);
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
	 * 获取统计信息
	 * @return
	 */
	@RequestMapping("/getStatistics")
	@ResponseBody
	public Map<String, Object> getStatistics(Integer page, Integer rows, String customerName, String idcard, String accountno, String companyName) {
		List<Map<String, Object>> list = accumulationFundService.getStatistics(page, rows, customerName, idcard, accountno, companyName);
		Integer total = accumulationFundService.getStatisticsCount(customerName, idcard, accountno, companyName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

	/**
	 * 这是选择Excel文件页面跳转功能
	 * @return
	 */
	@RequestMapping("/toSelectExcel")
	public String toSelectExcel() {
		return "accumulationFundInfo/selectExcel";
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
			accumulationFundService.uploadPayerCreditInfoExcel(in, excel);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.buildFailureResult("文件导入失败");
		}
		if (in!=null) {
			in.close();
		}
		return JsonResult.buildSuccessResult("文件导入成功");
	}

	
	/**
	 * 公积金催缴跳转
	 * @return
	 */
	@RequestMapping("/tocuijiao")
	public String tocuijiao() {
		return "accumulationFundInfo/cuijiao";
	}

	
	/**
	 * 导出为excel文件 
	 */
	@RequestMapping("/exportStatistics")
	public void export(HttpServletResponse response,String customerName, String idcard, String accountno, String companyName) throws InvocationTargetException, ClassNotFoundException,
			IllegalAccessException, IntrospectionException, ParseException, IOException {
		// 清除buffer缓存
		response.reset(); 
		//设置文件类型： application/vnd.ms-excel 
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		//设置响应头：  以附件的形式下载，并指定默认文件名为：table.xlsx
		response.setHeader("Content-Disposition", "attachment;filename=table.xlsx");

		
		XSSFWorkbook workbook = null;
		//userService生成要导出的Excel对象： XSSFWorkbook
		workbook = accumulationFundService.exportExcelInfo(customerName, idcard, accountno,companyName);
		
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
