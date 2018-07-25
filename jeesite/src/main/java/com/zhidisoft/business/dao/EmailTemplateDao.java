package com.zhidisoft.business.dao;

import com.zhidisoft.business.entity.EmailTemplate;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface EmailTemplateDao {
    int deleteByPrimaryKey(Integer id);

    int insert(EmailTemplate record);

    EmailTemplate selectByPrimaryKey(Integer id);

    List<EmailTemplate> selectAll();

    int updateByPrimaryKey(EmailTemplate record);

	List<EmailTemplate> selectEmailByPage(@Param("page")int page, @Param("rows")Integer rows, @Param("subject")String subject);

	Integer getCount();

	Integer addEmailTemplate(@Param("emailTemplate")EmailTemplate emailTemplate);

	Integer editTemplate(@Param("emailTemplate")EmailTemplate emailTemplate, @Param("id")Integer id);

	Integer removeTemplate(@Param("id")Integer id);
}