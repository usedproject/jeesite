package com.zhidisoft.business.dao;

import com.zhidisoft.business.entity.Company;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CompanyDao {
    int deleteByPrimaryKey(Integer id);
    
    /**
     * 这是添加公司客户的方法
     * @param record 参数封装到公司对象中
     * @return
     */
    int insert(Company record);
    
    /**
     * 这是通过id查找公司客户的方法
     * @param id
     * @return
     */
    Company selectByPrimaryKey(Integer id);
    
    /**
     * 这是用于前端传递请求获得所有公司的方法用于填充所属公司下拉框
     * @return
     */
    List<Company> selectAll();
    /**
     * 这是用于修改公司客户的方法
     * @param record
     * @return
     */
    int updateByPrimaryKey(Company record);

    /**
     * 这是通过参数完成分页的功能的底层代码
     * @param name      公司名
     * @param companyno 信用号码
     * @param idcard    身份证号
     * @param page		第几页		
     * @param rows		几行
     * @return         	结果集
     */
	List<Company> getListByPage(@Param("name")String name, @Param("companyno")String companyno, @Param("idcard")String idcard, @Param("page")Integer page, @Param("rows")Integer rows);
	/**
	 * 这是通过参数完成分页记录统计的方法
	 * @param name
	 * @param companyno
	 * @param idcard
	 * @return
	 */
	Integer count(@Param("name")String name,@Param("companyno") String companyno,@Param("idcard")String idcard);
	
	/**
	 * 这是通过前端验证是否重名的方法
	 * @param name
	 * @return
	 */
	List<Company> checkName(String name);
	
	/**
	 * 根据传入的id数组进行筛选数据
	 * @param ids
	 * @return
	 */
	List<Company> selectByIds(@Param("ids")String[] ids);
	
	/**
	 * 这是通过公司信用号进行判断的方法
	 * @param companyno
	 * @return
	 */
	Company selectByCompanyNo(@Param("companyno")String companyno);
	/**
	 * 
	 * 批量删除
	 * @param ids
	 */
	void deleteById(@Param("ids")String[] ids);
}