package com.zhidisoft.business.service;

import java.util.List;

import com.zhidisoft.business.entity.EmailTemplate;

public interface EmailTemplateService {

	List<EmailTemplate> selectEmailByPage(Integer page, Integer rows, String subject);

	Integer getCount();

	Integer addEmailTemplate(EmailTemplate emailTemplate);

	List<EmailTemplate> selectAllTemplate();

	Integer editTemplate(EmailTemplate emailTemplate, Integer id);

	Integer removeTemplate(Integer id);
	
}
