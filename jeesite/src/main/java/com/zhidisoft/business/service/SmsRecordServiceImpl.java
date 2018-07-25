package com.zhidisoft.business.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhidisoft.business.dao.SmsRecordDao;
import com.zhidisoft.business.entity.SmsRecord;

@Service
public class SmsRecordServiceImpl implements SmsRecordService{
	
	@Resource
	SmsRecordDao smsRecordDao;

	@Override
	public List<SmsRecord> getListByPage(SmsRecord smsRecord, Integer page, Integer rows) {
		return smsRecordDao.getListByPage(smsRecord,(page-1)*rows,rows);
	}

	@Override
	public int count(SmsRecord smsRecord) {
		return smsRecordDao.count(smsRecord);
	}

	@Override
	public int addSmsRecord(SmsRecord smsRecord) {
		return smsRecordDao.insert(smsRecord);
	}

	@Override
	public SmsRecord selectById(Integer id) {
		return smsRecordDao.selectByPrimaryKey(id);
	}

}
