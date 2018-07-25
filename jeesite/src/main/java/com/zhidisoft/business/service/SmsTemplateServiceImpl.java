package com.zhidisoft.business.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhidisoft.business.dao.SmsTemplateDao;
import com.zhidisoft.business.entity.SmsTemplate;
@Service
public class SmsTemplateServiceImpl implements SmsTemplateService {
	@Resource
	SmsTemplateDao smsTemplateDao;

	@Override
	public List<SmsTemplate> getSmsTemplateByPage(Integer page, Integer rows, String templateCode, String subject) {
		// TODO Auto-generated method stub
		return smsTemplateDao.getSmsTemplateByPage((page-1)*rows, rows, templateCode, subject);
	}

	@Override
	public Integer getCount() {
		// TODO Auto-generated method stub
		return smsTemplateDao.getCount();
	}

	@Override
	public Integer addSmsTemplate(SmsTemplate smsTemplate) {
		// TODO Auto-generated method stub
		return smsTemplateDao.addSmsTemplate(smsTemplate);
	}

	@Override
	public List<SmsTemplate> selectAllTemplate() {
		// TODO Auto-generated method stub
		return smsTemplateDao.selectAll();
	}

	@Override
	public Integer editTemplateById(SmsTemplate smsTemplate, String id) {
		// TODO Auto-generated method stub
		return smsTemplateDao.editTemplateById(smsTemplate, id);
	}

	@Override
	public Integer removeTemplateById(String id) {
		// TODO Auto-generated method stub
		return smsTemplateDao.removeTemplateById(id);
	}

}
