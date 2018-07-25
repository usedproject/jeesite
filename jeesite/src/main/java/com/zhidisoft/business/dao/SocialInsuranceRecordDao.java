package com.zhidisoft.business.dao;

import com.zhidisoft.business.entity.SocialInsuranceRecord;
import com.zhidisoft.utils.StatisticsShebao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SocialInsuranceRecordDao {
    int deleteByPrimaryKey(Integer id);

    //增加记录
    int insert(@Param("record")SocialInsuranceRecord record);

    SocialInsuranceRecord selectByPrimaryKey(Integer id);

    List<SocialInsuranceRecord> selectAll();

    int updateByPrimaryKey(@Param("record")SocialInsuranceRecord record, @Param("id")Integer id);
    
    //分页查询
  	List<SocialInsuranceRecord> getListByPage(@Param("page")Integer page, @Param("rows")Integer rows, @Param("customerName")String customerName, @Param("idcard")String idcard, @Param("sbcard")String sbcard);

  	//获取记录数量
	Integer getCount(@Param("customerName")String customerName, @Param("idcard")String idcard, @Param("sbcard")String sbcard);

	List<Map<String, Object>> getStatistics(@Param("page")Integer page, @Param("rows")Integer rows, @Param("customerName")String customerName, @Param("idcard")String idcard, @Param("sbcard")String sbcard, @Param("companyName")String companyName);

	Integer getStatisticCount(@Param("customerName")String customerName, @Param("idcard")String idcard, @Param("sbcard")String sbcard, @Param("companyName")String companyName);

	List<StatisticsShebao> getStatisticsBy(@Param("customerName")String customerName, @Param("idcard")String idcard, @Param("sbcard")String sbcard, @Param("companyName")String companyName);
}