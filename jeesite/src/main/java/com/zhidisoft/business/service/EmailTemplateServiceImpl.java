package com.zhidisoft.business.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhidisoft.business.dao.EmailTemplateDao;
import com.zhidisoft.business.entity.EmailTemplate;

@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {

	@Resource
	EmailTemplateDao emailTemplateDao;
	
	@Override
	public List<EmailTemplate> selectEmailByPage(Integer page, Integer rows, String subject) {
		// TODO Auto-generated method stub
		return emailTemplateDao.selectEmailByPage((page-1)*rows, rows, subject);
	}

	@Override
	public Integer getCount() {
		// TODO Auto-generated method stub
		return emailTemplateDao.getCount();
	}

	@Override
	public Integer addEmailTemplate(EmailTemplate emailTemplate) {
		// TODO Auto-generated method stub
		return emailTemplateDao.addEmailTemplate(emailTemplate);
	}

	@Override
	public List<EmailTemplate> selectAllTemplate() {
		// TODO Auto-generated method stub
		return emailTemplateDao.selectAll();
	}

	@Override
	public Integer editTemplate(EmailTemplate emailTemplate,Integer id) {
		// TODO Auto-generated method stub
		return emailTemplateDao.editTemplate(emailTemplate, id);
	}

	@Override
	public Integer removeTemplate(Integer id) {
		// TODO Auto-generated method stub
		return emailTemplateDao.removeTemplate(id);
	}

}
