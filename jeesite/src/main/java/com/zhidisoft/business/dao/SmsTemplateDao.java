package com.zhidisoft.business.dao;

import com.zhidisoft.business.entity.SmsTemplate;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SmsTemplateDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SmsTemplate record);

    SmsTemplate selectByPrimaryKey(Integer id);

    List<SmsTemplate> selectAll();

    int updateByPrimaryKey(SmsTemplate record);

	List<SmsTemplate> getSmsTemplateByPage(@Param("page")int page, @Param("rows")Integer rows, @Param("templateCode")String templateCode, @Param("subject")String subject);


	Integer getCount();

	Integer addSmsTemplate(@Param("smsTemplate")SmsTemplate smsTemplate);

	Integer editTemplateById(@Param("smsTemplate")SmsTemplate smsTemplate, @Param("id")String id);

	Integer removeTemplateById(@Param("id")String id);

}