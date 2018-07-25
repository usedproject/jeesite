package com.zhidisoft.business.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhidisoft.business.entity.NeedJob;
import com.zhidisoft.business.service.NeedJobService;
import com.zhidisoft.system.entity.SystemUser;
import com.zhidisoft.utils.JsonResult;
import com.zhidisoft.utils.PageResult;

@Controller
@RequestMapping("/business/needJob")
public class NeedJobController {
	
	@Resource
	NeedJobService needJobService;
	
	@RequestMapping("/toNeedJob")
	public String toNeedJob() {
		return "needJobInfo/listNeedJob";
	}
	
	/**
	 * 这是前端进行分页功能
	 * @return
	 */
	@RequestMapping("/getList")
	@ResponseBody
	public JsonResult getList(NeedJob needJob,Integer page,Integer rows) {
		List<NeedJob> needJobs=needJobService.getListPage(needJob,page,rows);
		Integer total=needJobService.count(needJob);
		PageResult pageResult=new PageResult();
		pageResult.setRows(needJobs);
		pageResult.setTotal(total);
		return JsonResult.buildSuccessResult("", pageResult);
	}
	
	/**
	 * 这是进行增加页面的跳转功能
	 * @return
	 */
	@RequestMapping("/toAddNeedJob")
	public String toAddNeedJob() {
		return "needJobInfo/addNeedJob";
	}
	
	/**
	 * 这是添加招聘信息的功能实现
	 * @param needJob 前端参数封装到的实体类
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public JsonResult add(NeedJob needJob,HttpSession session) {
		System.out.println(needJob);
		SystemUser user= (SystemUser) session.getAttribute("user");
		needJob.setCreateby(user.getId());
		needJob.setCreatetime(new Date());
		int i= needJobService.add(needJob);
		if (i>0) {
			return JsonResult.buildSuccessResult("添加成功");
		}
		return JsonResult.buildFailureResult("添加失败");
	}
	
	/**
	 * 这是进行编辑招聘信息的页面跳转功能
	 * @param id    编辑信息的id
	 * @param model
	 * @return
	 */
	@RequestMapping("/toEditNeedJob")
	public String toEditNeedJob(Integer id,Model model) {
		NeedJob needJob=needJobService.selectById(id);
		model.addAttribute("needJob", needJob);
		System.out.println(needJob);
		return "needJobInfo/editNeedJob";
	}
	
	@RequestMapping("/editNeedJob")
	@ResponseBody
	public JsonResult editNeedJob(NeedJob needJob,HttpSession session) {
		SystemUser user=(SystemUser) session.getAttribute("user");
		needJob.setUpdateby(user.getId());
		int i= needJobService.edit(needJob);
		if (i>0) {
			return JsonResult.buildSuccessResult("修改成功");
		}
		return JsonResult.buildFailureResult("修改失败");
	}
}
