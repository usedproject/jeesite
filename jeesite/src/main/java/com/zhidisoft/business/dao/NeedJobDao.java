package com.zhidisoft.business.dao;

import com.zhidisoft.business.entity.NeedJob;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface NeedJobDao {
    int deleteByPrimaryKey(Integer id);
    
    /**
     * 这是进行招聘信息的添加功能
     * @param record
     * @return
     */
    int insert(NeedJob record);
    
    /**
     * 这是通过id获得招聘信息对象的功能
     * @param id
     * @return
     */
    NeedJob selectByPrimaryKey(@Param("id")Integer id);

    List<NeedJob> selectAll();

    int updateByPrimaryKey(NeedJob record);
    /**
     * 这是前端完成分页的功能
     * @param needJob   传入参数封装的对象
     * @param page		第几页
     * @param rows		第几行
     * @return			结果集合
     */
	List<NeedJob> getListByPage(@Param("needJob")NeedJob needJob,@Param("page")Integer page,@Param("rows")Integer rows);
	/**
	 * 这是前端完成分页的统计功能
	 * @param needJob   传入参数封装的对象
	 * @return
	 */
	Integer count(@Param("needJob")NeedJob needJob);
}