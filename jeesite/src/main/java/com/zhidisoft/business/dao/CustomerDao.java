package com.zhidisoft.business.dao;

import com.zhidisoft.business.entity.Customer;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CustomerDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    Customer selectByPrimaryKey(Integer id);

    List<Customer> selectAll();

    int updateByPrimaryKey(Customer record);
    /**
     * 这是完成customer列表的分页功能
     * @param name       	客户名
     * @param idcard		身份证号
     * @param companyid		所属公司	
     * @param i				第几页
     * @param rows			显示几行
     * @return
     */
	List<Customer> getListByPage(@Param("name")String name,@Param("idCard")String idcard,@Param("companyid")Integer companyid, @Param("page")Integer page, @Param("rows")Integer rows);
	
	/**
	 * 这是进行记录统计的方法
	 * @param name
	 * @param idcard
	 * @param companyid
	 * @return
	 */
	Integer count(@Param("name")String name, @Param("idCard")String idcard, @Param("companyid")Integer companyid);
	/**
	 * 这是通过身份证或姓名进行搜索的功能
	 * @param name		用户名
	 * @param idcard	身份证号
	 * @return
	 */
	Customer selectByIdOrName(@Param("name")String name, @Param("idcard")String idcard);
	
	/**
	 * 这是通过前端校验客户是否存在的方法
	 * @param name  客户名
	 * @return
	 */
	Customer check(@Param("name")String name);
	/**
	 * 通过传入的ids查出指定记录的方法
	 * @param ids
	 * @return
	 */
	List<Customer> selectByIds(@Param("ids")String[] ids);
	
	/**
	 * 这是通过身份证号进行查询客户的方法
	 * @param idcard
	 * @return
	 */
	Customer selectByIdcard(@Param("idcard")String idcard);
	/**
	 * 删除功能
	 * @param ids
	 * @return
	 */
	int deleteById(@Param("ids")String[] ids);
	
}