package com.zhidisoft.business.service;

import java.util.List;

import com.zhidisoft.business.entity.SmsRecord;

public interface SmsRecordService {

	List<SmsRecord> getListByPage(SmsRecord smsRecord, Integer page, Integer rows);

	int count(SmsRecord smsRecord);

	int addSmsRecord(SmsRecord smsRecord);

	SmsRecord selectById(Integer id);

}
