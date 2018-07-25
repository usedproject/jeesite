package com.zhidisoft.business.dao;

import com.zhidisoft.business.entity.EmailRecord;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface EmailRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(EmailRecord record);

    EmailRecord selectByPrimaryKey(Integer id);

    List<EmailRecord> selectAll();

    int updateByPrimaryKey(EmailRecord record);
    /**
     * 这是通过前端参数进行分页的功能
     * @param emailRecord
     * @param page
     * @param rows
     * @return
     */
	List<EmailRecord> getListByPage(@Param("emailRecord")EmailRecord emailRecord, @Param("page")Integer page, @Param("rows")Integer rows);
	/**
	 * 这是进行分页统计记录条数的功能
	 * @param emailRecord
	 * @return
	 */
	int count(@Param("emailRecord")EmailRecord emailRecord);
}