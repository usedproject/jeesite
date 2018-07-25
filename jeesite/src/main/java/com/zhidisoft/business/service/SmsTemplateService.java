package com.zhidisoft.business.service;

import java.util.List;

import com.zhidisoft.business.entity.SmsTemplate;

public interface SmsTemplateService {

	List<SmsTemplate> getSmsTemplateByPage(Integer page, Integer rows, String templateCode, String subject);

	Integer getCount();

	Integer addSmsTemplate(SmsTemplate smsTemplate);

	List<SmsTemplate> selectAllTemplate();

	Integer editTemplateById(SmsTemplate smsTemplate, String id);

	Integer removeTemplateById(String id);

}
