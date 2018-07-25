package com.zhidisoft.business.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhidisoft.business.entity.SmsTemplate;
import com.zhidisoft.business.service.SmsTemplateService;
import com.zhidisoft.system.entity.SystemUser;
import com.zhidisoft.utils.JsonResult;

@Controller
public class SmsTemplateController {
	@Resource
	SmsTemplateService smsTemplateService;
	
	/*
	 * 跳转到短信模板列表的jsp页面
	 */
	@RequestMapping("company/system/smsTemplate")
	public String toListSmsTemplate() {
		return "smsTemplateInfo/listSmsTemplate";
	}
	
	/**
	 * 分页查询短信模板记录
	 * @param page
	 * @param rows
	 * @param templateCode
	 * @param subject
	 * @return
	 */
	@RequestMapping("/system/smsTemplate/listSmsTemplate")
	@ResponseBody
	public Map<String, Object> listSmsTemplate(Integer page, Integer rows, String templateCode, String subject) {
		List<SmsTemplate> smsTemplates = smsTemplateService.getSmsTemplateByPage(page, rows, templateCode, subject);
		Integer total = smsTemplateService.getCount();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", smsTemplates);
		return map;
	}
	
	/**
	 * 跳转到添加短信模板的jsp页面
	 * @return
	 */
	@RequestMapping("/system/smsTemplate/toAddTemplate")
	public String toAddTemplate() {
		return "smsTemplateInfo/addSmsTemplate";
	}
	
	/**
	 * 向表中添加短信模板
	 * @param smsTemplate
	 * @return
	 */
	@RequestMapping("/system/smsTemplate/addTemplate")
	@ResponseBody
	public JsonResult addTemplate(SmsTemplate smsTemplate, HttpSession session) {
		//从session中获取当前的用户
		SystemUser user = (SystemUser) session.getAttribute("user");
		//设定创建者的id
		smsTemplate.setCreateby(user.getId());
		smsTemplate.setCreatetime(new Date());
		Integer i = smsTemplateService.addSmsTemplate(smsTemplate);
		if(i<1) {
			return JsonResult.buildFailureResult("添加失败");
		}
		return JsonResult.buildSuccessResult("添加成功");
	}
	
	/**
	 * 跳转到修改短信模板的jsp页面
	 * @return
	 */
	@RequestMapping("/system/smsTemplate/toEditTemplate")
	public String toEditTemplate(Model model, String id) {
		model.addAttribute("id", id);
		return "smsTemplateInfo/editSmsTemplate";
	}
	
	/**
	 * 返回所有的短信模板的集合
	 * @return
	 */
	@RequestMapping("/system/smsTemplate/selectAllTemplate")
	@ResponseBody
	public List<SmsTemplate> selectAllTemplate() {
		List<SmsTemplate> smsTemplates = smsTemplateService.selectAllTemplate();
		return smsTemplates;
	}
	
	/**
	 * 根据id修改短信模板的记录
	 * @param smsTemplate
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/system/smsTemplate/editTemplate")
	@ResponseBody
	public JsonResult editTemplate(SmsTemplate smsTemplate, String id, HttpSession session) {
		//从session中获取当前的用户
		SystemUser user = (SystemUser) session.getAttribute("user");
		//设定修改者的id
		smsTemplate.setUpdateby(user.getId());
		smsTemplate.setUpdatetime(new Date());
		Integer i = smsTemplateService.editTemplateById(smsTemplate, id);
		if(i<1) {
			return JsonResult.buildFailureResult("修改失败");
		}
		return JsonResult.buildSuccessResult("修改成功");
	}
	
	/**
	 * 根据id删除短信模板的记录
	 * @param id
	 * @return
	 */
	@RequestMapping("/system/smsTemplate/removeTemplate")
	@ResponseBody
	public JsonResult removeTemplate(String id) {
		Integer i = smsTemplateService.removeTemplateById(id);
		if(i<1) {
			return JsonResult.buildFailureResult("删除失败");
		}
		return JsonResult.buildSuccessResult("删除成功");
	}
}
