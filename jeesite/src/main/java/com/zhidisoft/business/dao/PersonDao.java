package com.zhidisoft.business.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;

import com.zhidisoft.business.entity.Person;

public interface PersonDao {
    int deleteByPrimaryKey(Integer id);
    
    /**
     * 这是添加人才信息的功能
     * @param person
     * @return
     */
    int insert(Person person);
    /**
     * 这是通过id进行搜索person的功能
     * @param id  客户id
     * @return
     */
    Person selectByPrimaryKey(Integer id);

    List<Person> selectAll();
    /**
     * 这是通过person进行修改的方法
     * @param record
     * @return
     */
    int updateByPrimaryKey(Person person);
    /**
     * 这是完成分页功能的方法
     * @param person  参数封装对象
     * @param page		第几页
     * @param rows		第几行
     * @return
     */
	List<Person> getListByPage(@Param("person")Person person,@Param("page")Integer page,@Param("rows")Integer rows);
	
	/**
	 * 这是进行记录统计的方法
	 * @param person  参数封装的对象
	 * @return
	 */
	Integer count(@Param("person")Person person);
	
	/**
	 * 这是通过身份证号进行搜索记录的方法
	 * @param idcard  身份证号
	 * @return
	 */
	Person selectByIdCard(@Param("idcard")String idcard);
}