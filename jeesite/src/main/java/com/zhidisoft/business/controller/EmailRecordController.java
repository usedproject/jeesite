package com.zhidisoft.business.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhidisoft.business.entity.EmailRecord;
import com.zhidisoft.business.service.EmailRecordService;
import com.zhidisoft.utils.JsonResult;
import com.zhidisoft.utils.PageResult;

@Controller
@RequestMapping("/business/emailRecord")
public class EmailRecordController {

	@Resource
	EmailRecordService emailRecordService;
	
	/**
	 * 这是完成前端的邮件发送列表的分页功能
	 * @param emailRecord  参数封装的对象
	 * @param page			第几页
	 * @param rows			几行
	 * @return
	 */
	@RequestMapping("/getList")
	@ResponseBody
	public JsonResult getList(EmailRecord emailRecord,Integer page,Integer rows) {
		List<EmailRecord> emailRecords=emailRecordService.getListByPage(emailRecord,page,rows);
		int total=emailRecordService.count(emailRecord);
		PageResult pageResult=new PageResult();
		pageResult.setRows(emailRecords);
		pageResult.setTotal(total);
		return JsonResult.buildSuccessResult("", pageResult);
	}
	
	/**
	 * 这是增加邮件记录的页面跳转功能
	 * @return
	 */
	@RequestMapping("/toAddEmailRecord")
	public String toAddEmailRecord() {
		return "marketInfo/addEmailRecord";
	}
	
	/**
	 * 这是选择邮件模板的页面跳转功能
	 * @return
	 */
	@RequestMapping("/toSelectEmailTemplate")
	public String toSelectSmsTemplate() {
		return "marketInfo/selectEmailTemplate";
	}
	
	/**
	 * 这是进行邮件记录增加的功能
	 * @return
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	@RequestMapping("/addEmailRecord")
	@ResponseBody
	public JsonResult addEmailRecord(EmailRecord emailRecord) throws AddressException, MessagingException {
		try {
			emailRecord.setSendtime(new Date());
			emailRecord.setStatus("0");
			int i=emailRecordService.addEmailRecord(emailRecord);
			return JsonResult.buildSuccessResult("发送成功");
		} catch (Exception e) {
			return JsonResult.buildFailureResult("发送失败");
		}
		
		
	}
	
	/**
	 * 这是展示邮件详情的跳转功能
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/toDetailEmailRecord")
	public String toDetailEmailRecord(Integer id,Model model) {
		EmailRecord emailRecord= emailRecordService.selectById(id);
		model.addAttribute("emailRecord", emailRecord);
		return "marketInfo/detailEmailRecord";
	}
}
