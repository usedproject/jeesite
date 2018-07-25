package com.zhidisoft.business.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhidisoft.business.entity.PersonJob;
import com.zhidisoft.business.service.PersonJobService;
import com.zhidisoft.system.entity.SystemUser;
import com.zhidisoft.utils.JsonResult;
import com.zhidisoft.utils.PageResult;
import com.zhidisoft.utils.StatisticsRencai;

@Controller
@RequestMapping("/business/personJob")
public class PersonJobController {
	
	@Resource
	PersonJobService personJobService;
	
	/**
	 * 这是进行页面跳转的功能
	 * @return
	 */
	@RequestMapping("/toPersonJob")
	public String toPersonJob() {
		return "personJobInfo/listPersonJob";
	}
	
	/**
	 * 这是实现页面分页功能的代码
	 * @param personJob
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/getList")
	@ResponseBody
	public JsonResult getList(PersonJob personJob,Integer page,Integer rows) {
		List<PersonJob> personJobs=personJobService.getListByPage(personJob,(page-1)*rows,rows);
		Integer total=personJobService.count(personJob);
		PageResult pageResult=new PageResult();
		pageResult.setRows(personJobs);
		pageResult.setTotal(total);
		return JsonResult.buildSuccessResult("", pageResult);
	}
	
	/**
	 * 这是进行增加页面的跳转功能
	 * @return
	 */
	@RequestMapping("/toAddPersonJob")
	public String toAddPersonJob() {
		return "personJobInfo/addPersonJob";
	}
	
	/**
	 * 这是进行增加劳务添加的功能
	 * @param contract    合同
	 * @param personJob   参数封装成的对象
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/add")
	@ResponseBody
	public JsonResult addPersonJob(MultipartFile contract,PersonJob personJob,HttpSession session) throws IllegalStateException, IOException {
		SystemUser user= (SystemUser) session.getAttribute("user");
		personJob.setCreateby(user.getId());
		if (!contract.isEmpty()) {
			String filename = contract.getOriginalFilename();
			personJob.setContracturl(filename);
			contract.transferTo(new File("F://"+filename));
		}
		
		int i= personJobService.save(personJob);
		if (i>0) {
			return JsonResult.buildSuccessResult("添加成功");
		}
		return JsonResult.buildFailureResult("添加失败");
	}
	
	/**
	 * 这是完成编辑页面跳转的功能
	 * @param id  传过来的id值
	 * @return
	 */
	@RequestMapping("/toEditPersonJob")
	public String toEditPersonJob(Integer id,Model model) {
		PersonJob personJob= personJobService.selectById(id);
		model.addAttribute("personJob", personJob);
		return "personJobInfo/editPersonJob";
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public JsonResult edit(MultipartFile contract,PersonJob personJob,HttpSession session) throws IllegalStateException, IOException {
		SystemUser user= (SystemUser) session.getAttribute("user");
		personJob.setUpdateby(user.getId());
		if (!contract.isEmpty()) {
			String contracturl = personJob.getContracturl();
			File file=new File("F://"+contracturl);
			file.delete();
			String filename = contract.getOriginalFilename();
			personJob.setContracturl(filename);
			contract.transferTo(new File("F://"+filename));
			personJob.setContracturl(filename);
		}
		int i= personJobService.edit(personJob);
		if (i>0) {
			return JsonResult.buildSuccessResult("修改成功");
		}else {
			return JsonResult.buildFailureResult("修改失败");
		}
	}
	
	@RequestMapping("/getStatistics")
	@ResponseBody
	public Map<String, Object> getStatistics(Integer page, Integer rows, String customerName, String idcard, String phone, String companyName) {
		List<StatisticsRencai> list = personJobService.getStatistics(page, rows, customerName, idcard, phone, companyName);
		Integer total = personJobService.getStatisticsCount(customerName, idcard, phone, companyName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}
}
