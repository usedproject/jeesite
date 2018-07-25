package com.zhidisoft.business.dao;

import com.zhidisoft.business.entity.SocialInsurance;
import com.zhidisoft.utils.SocialInsuranceResult;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;

public interface SocialInsuranceDao {
	//根据id删除记录
    int deleteByPrimaryKey(@Param("id")String id);

    int insert(SocialInsurance record);

    SocialInsurance selectByPrimaryKey(Integer id);

    List<SocialInsurance> selectAll();

    int updateByPrimaryKey(SocialInsurance record);

    //分页查询
	List<SocialInsuranceResult> getListByPage(@Param("page")Integer page, @Param("rows")Integer rows, @Param("customerName")String customerName, @Param("idcard")String idcard, @Param("sbcard")String sbcard);

	//获取总记录数
	Integer getCount();

	//添加社保信息
	Integer addSocialInsurance(@Param("socialInsurance")SocialInsurance socialInsurance);

	//验证idcard是否已存在
	List<SocialInsurance> checkIdcard(@Param("idcard")String idcard);

	//根据id修改信息
	Integer updateByPrimaryKey(@Param("socialInsurance")SocialInsurance socialInsurance, @Param("id")String id);
	/**
	 * 导出文件到Excel的功能
	 * @param customerName
	 * @param idcard
	 * @param sbcard
	 * @return
	 */
	List<SocialInsuranceResult> selectByParams(@Param("customerName")String customerName, @Param("idcard")String idcard, @Param("sbcard")String sbcard);
	
	/**
	 * 这是通过身份证号查找社保信息的方法
	 * @param idcard
	 * @return
	 */
	SocialInsurance selectByIdCard(@Param("idcard")String idcard);

}