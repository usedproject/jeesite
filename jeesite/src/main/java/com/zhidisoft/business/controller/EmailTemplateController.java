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

import com.zhidisoft.business.entity.EmailTemplate;
import com.zhidisoft.business.service.EmailTemplateService;
import com.zhidisoft.system.entity.SystemUser;
import com.zhidisoft.utils.JsonResult;

@Controller
public class EmailTemplateController {
	@Resource
	EmailTemplateService emailTemplateService;
	
	/**
	 * 返回字符串，跳转到列表的jsp页面
	 * @return
	 */
	@RequestMapping("/company/system/emailTemplate")
	public String toListEmailTemplate() {
		return "emailTemplateInfo/listEmailTemplate";
	}
	
	/**
	 * 分页查询邮件模板
	 * @param page
	 * @param rows
	 * @param subject
	 * @return
	 */
	@RequestMapping("/company/system/listEmailTemplate")
	@ResponseBody
	public Map<String, Object> selectEmailByPage(Integer page, Integer rows, String subject) {
		List<EmailTemplate> emailTemplateList = emailTemplateService.selectEmailByPage(page, rows, subject);
		Integer total = emailTemplateService.getCount();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", emailTemplateList);
		return map;
	}
	
	/**
	 * 查询所有的邮件模板
	 * @return
	 */
	@RequestMapping("/system/emailTemplate/selectAllTemplate")
	@ResponseBody
	public List<EmailTemplate> selectAllTemplate() {
		List<EmailTemplate> emailTemplates = emailTemplateService.selectAllTemplate();
		return emailTemplates;
	}
	
	/**
	 * 跳转到添加邮件模板的jsp页面
	 * @return
	 */
	@RequestMapping("/system/emailTemplate/toAddTemplate")
	public String toAddTemplate() {
		return "emailTemplateInfo/addEmailTemplate";
	}
	
	/**
	 * 向邮件模板表中添加记录
	 * @param emailTemplate
	 * @param session
	 * @return
	 */
	@RequestMapping("/system/emailTemplate/addTemplate")
	@ResponseBody
	public JsonResult addTemplate(EmailTemplate emailTemplate, HttpSession session) {
		//从session中获取当前的用户
		SystemUser user = (SystemUser) session.getAttribute("user");
		//设定创建者的id
		emailTemplate.setCreateby(user.getId());
		//设定创建的时间
		emailTemplate.setCreatetime(new Date());
		Integer i = emailTemplateService.addEmailTemplate(emailTemplate);
		if(i<1) {
			return JsonResult.buildFailureResult("添加失败");
		}
		return JsonResult.buildSuccessResult("添加成功");
	}
	
	/**
	 * 跳转到修改邮件模板的jsp页面
	 * @return
	 */
	@RequestMapping("/system/emailTemplate/toEditTemplate")
	public String toEditTemplate(Model model, String id) {
		model.addAttribute("id", id);
		return "emailTemplateInfo/editEmailTemplate";
	}
	
	/**
	 * 修改邮件模板
	 * @param emailTemplate
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/system/emailTemplate/editTemplate")
	@ResponseBody
	public JsonResult editTemplate(EmailTemplate emailTemplate, Integer id, HttpSession session) {
		//从session中获取当前的用户
		SystemUser user = (SystemUser) session.getAttribute("user");
		//设定创建者的id
		emailTemplate.setUpdateby(user.getId());
		emailTemplate.setUpdatetime(new Date());
		Integer i = emailTemplateService.editTemplate(emailTemplate, id);
		if(i<1) {
			return JsonResult.buildFailureResult("修改失败");
		}
		return JsonResult.buildSuccessResult("修改成功");
	}
	
	@RequestMapping("system/emailTemplate/removeTemplate")
	@ResponseBody
	public JsonResult removeTemplate(Integer id) {
		Integer i = emailTemplateService.removeTemplate(id);
		if(i<1) {
			return JsonResult.buildFailureResult("删除失败");
		}
		return JsonResult.buildSuccessResult("删除成功");
	}
}
