package com.zhidisoft.business.controller;

import java.beans.IntrospectionException;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;

import com.zhidisoft.business.entity.SocialInsurance;
import com.zhidisoft.business.service.SocialInsuranceService;
import com.zhidisoft.system.entity.SystemUser;
import com.zhidisoft.utils.ExportToExcel;
import com.zhidisoft.utils.ImportExcel;
import com.zhidisoft.utils.JsonResult;
import com.zhidisoft.utils.SocialInsuranceResult;

@Controller
@RequestMapping("/business/socialInsurance")
public class SocialInsuranceController {
	@Resource
	SocialInsuranceService socialInsuranceService;
	
	/**
	 * 跳转到社保信息页面
	 * @return
	 */
	@RequestMapping("/shebaoxinxi")
	public String toSocialInfo() {
		return "socialInsuranceInfo/listSocialInsurance";
	}
	
	/**
	 * 分页展示列表
	 */
	@RequestMapping("/getList")
	@ResponseBody
	public Map<String, Object> getListByPage(Integer page, Integer rows, String customerName, String idcard, String sbcard) {
		List<SocialInsuranceResult> insurances = socialInsuranceService.getListByPage(page, rows, customerName, idcard, sbcard);
		Integer total = socialInsuranceService.getCount();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", insurances);
		return map;
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping("/toAdd")
	public String toAddSocialInsurance() {
		return "socialInsuranceInfo/addSocialInsurance";
	}
	
	/**
	 * 这是点击导出按钮的实现功能
	 */
	@RequestMapping("/exportToExcel")
	public void export(HttpServletResponse response,String customerName,String idcard,String sbcard) {
		// 清除buffer缓存
			response.reset(); 
		//设置文件类型： application/vnd.ms-excel 
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
		//设置响应头：  以附件的形式下载，并指定默认文件名为：table.xlsx
			response.setHeader("Content-Disposition", "attachment;filename=table.xlsx");
			XSSFWorkbook workbook = null;
		//userService生成要导出的Excel对象： XSSFWorkbook(sheet\header\row\cell)
			try {
				workbook = socialInsuranceService.exportExcelInfo(customerName,idcard,sbcard);
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
		return "socialInsuranceInfo/selectExcel";
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
			socialInsuranceService.uploadPayerCreditInfoExcel(in, excel);
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
	 * 插入社保记录
	 * @param socialInsurance
	 * @param session
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public JsonResult add(SocialInsurance socialInsurance, HttpSession session) {
		SystemUser user = (SystemUser) session.getAttribute("user");
		socialInsurance.setCreateby(user.getId());
		socialInsurance.setCreatetime(new Date());
		Integer i = socialInsuranceService.addSocialInsurance(socialInsurance);
		if(i<1) {
			return JsonResult.buildFailureResult("添加失败");
		} else {
			return JsonResult.buildSuccessResult("添加成功");
		}
	}
	
	/**
	 * 检验输入的idcard是否已经存在
	 */
	@RequestMapping("/check")
	@ResponseBody
	public boolean check(String idcard) {
		List<SocialInsurance> list = socialInsuranceService.checkIdcard(idcard);
		if(list.size()>0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 跳转到修改页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/toEdit")
	public String toEdit(String id, Model model) {
		model.addAttribute("id", id);
		return "socialInsuranceInfo/editSocialInsurance";
	}
	
	/**
	 * 根据id修改社保信息
	 * @param socialInsurance
	 * @param id
	 * @return
	 */
	@RequestMapping("edit")
	@ResponseBody
	public JsonResult edit(SocialInsurance socialInsurance, String id, HttpSession session) {
		SystemUser user = (SystemUser) session.getAttribute("user");
		socialInsurance.setUpdateby(user.getId());
		socialInsurance.setUpdatetime(new Date());
		Integer i = socialInsuranceService.edit(socialInsurance, id);
		if(i<1) {
			return JsonResult.buildFailureResult("修改失败！");
		}
		return JsonResult.buildSuccessResult("修改成功！");
	}
	
	@RequestMapping("remove")
	@ResponseBody
	public JsonResult remove(String id) {
		Integer i = socialInsuranceService.remove(id);
		if(i<1) {
			return JsonResult.buildFailureResult("删除失败！");
		}
		return JsonResult.buildSuccessResult("删除成功！");
	}
	
	/*
	 * 获取所有的社保信息 
	 */
	@RequestMapping("/selectAll")
	@ResponseBody
	public List<SocialInsurance> selectAll() {
		List<SocialInsurance> insurances = socialInsuranceService.selectAll();
		return insurances;
	}
	
	@RequestMapping("/tocuijiao")
	public String cuijiao() {
		return "socialInsuranceInfo/cuijiao";
	}
	
	
}
