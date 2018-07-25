package com.zhidisoft.business.service;

import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.stereotype.Service;

import com.zhidisoft.business.dao.EmailRecordDao;
import com.zhidisoft.business.entity.EmailRecord;
import com.zhidisoft.utils.MailUtils;

@Service
public class EmailRecordServiceImpl implements EmailRecordService {
	
	@Resource
	EmailRecordDao emailRecordDao;

	@Override
	public List<EmailRecord> getListByPage(EmailRecord emailRecord, Integer page, Integer rows) {
		page=(page-1)*rows;
		return emailRecordDao.getListByPage(emailRecord,page,rows);
	}

	@Override
	public int count(EmailRecord emailRecord) {
		
		return emailRecordDao.count(emailRecord);
	}

	@Override
	public int addEmailRecord(EmailRecord emailRecord) throws AddressException, MessagingException {
		String addr = emailRecord.getToAddr();
		String subject = emailRecord.getSubject();
		String content = emailRecord.getContent();
		MailUtils.sendMail(addr, content, subject);
		return emailRecordDao.insert(emailRecord);
	}

	@Override
	public EmailRecord selectById(Integer id) {
		return emailRecordDao.selectByPrimaryKey(id);
	}

}
