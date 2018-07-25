package com.zhidisoft.business.dao;

import com.zhidisoft.business.entity.SmsRecord;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SmsRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SmsRecord record);

    SmsRecord selectByPrimaryKey(Integer id);

    List<SmsRecord> selectAll();

    int updateByPrimaryKey(SmsRecord record);

	List<SmsRecord> getListByPage(@Param("smsRecord")SmsRecord smsRecord, @Param("page")Integer page, @Param("rows")Integer rows);

	int count(@Param("smsRecord")SmsRecord smsRecord);
}