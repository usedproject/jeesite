package com.zhidisoft.business.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhidisoft.business.entity.SmsRecord;
import com.zhidisoft.business.service.SmsRecordService;
import com.zhidisoft.utils.JsonResult;
import com.zhidisoft.utils.PageResult;

@Controller
@RequestMapping("/business/smsRecord")
public class SmsRecordController {
	
	@Resource
	SmsRecordService smsRecordService;
	
	/**
	 * 这是实现短信记录页面跳转的功能
	 * @return
	 */
	@RequestMapping("/listSmsRecord")
	public String toSms() {
		return "marketInfo/listSmsRecord";
	}
	
	/**
	 * 这是完成前端分页的功能
	 * @param smsRecord  前端参数封装
	 * @param page			第几页
	 * @param rows			几行
	 * @return
	 */
	@RequestMapping("/getList")
	@ResponseBody
	public JsonResult getList(SmsRecord smsRecord,Integer page,Integer rows) {
		List<SmsRecord> smsRecords= smsRecordService.getListByPage(smsRecord,page,rows);
		int total=smsRecordService.count(smsRecord);
		PageResult pageResult= new PageResult();
		pageResult.setRows(smsRecords);
		pageResult.setTotal(total);
		return JsonResult.buildSuccessResult("", pageResult);
	}
	
	/**
	 * 这是增加短信记录的跳转功能
	 * @return
	 */
	@RequestMapping("/toAddSmsRecord")
	public String toAddSmsRecord() {
		return "marketInfo/addSmsRecord";
	}
	
	/**
	 * 这是进行选择模块按钮的页面弹出事件
	 * @return
	 */
	@RequestMapping("/toSelectSmsTemplate")
	public String toSelectSmsTemplate() {
		return "marketInfo/selectSmsRecordTemplate";
	}
	
	/**
	 * 这是进行添加短信功能
	 * @param smsRecord
	 * @return
	 */
	@RequestMapping("/addSmsRecord")
	@ResponseBody
	public JsonResult addSmsRecord(SmsRecord smsRecord) {
		smsRecord.setSendtime(new Date());
		int i=smsRecordService.addSmsRecord(smsRecord);
		if (i>0) {
			return JsonResult.buildSuccessResult("添加成功");
		}
		
		return JsonResult.buildFailureResult("添加失败");
	}
	
	/**
	 * 这是展示短信发送详情的跳转页面
	 * @return
	 */
	@RequestMapping("/toDetailsmsRecord")
	public String toDetailsmsRecord(Integer id,Model model) {
		SmsRecord smsRecord=smsRecordService.selectById(id);
		model.addAttribute("smsRecord", smsRecord);
		return "marketInfo/detailSmsRecord";
	}
}
