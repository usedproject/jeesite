package com.zhidisoft.business.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.zhidisoft.business.entity.EmailRecord;

public interface EmailRecordService {

	List<EmailRecord> getListByPage(EmailRecord emailRecord, Integer page, Integer rows);

	int count(EmailRecord emailRecord);

	int addEmailRecord(EmailRecord emailRecord) throws AddressException, MessagingException;

	EmailRecord selectById(Integer id);

}
