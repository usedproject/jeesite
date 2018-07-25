package com.zhidisoft.business.dao;

import com.zhidisoft.business.entity.AccumulationFund;
import com.zhidisoft.utils.StatisticsShebao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface AccumulationFundDao {
    int deleteByPrimaryKey(Integer id);
    /**
     * 这是保存公积金记录的方法
     * @param record
     * @return
     */
    int insert(AccumulationFund record);
    /**
     * 这是通过id值获得对象，并进行编辑的方法
     * @param id
     * @return
     */
    AccumulationFund selectByPrimaryKey(Integer id);
    /**
     * 这是通过全查询搜索出来的方法
     * @return
     */
    List<AccumulationFund> selectAll();

    int updateByPrimaryKey(AccumulationFund record);
    /**
     * 这是公积金分页的方法  只能显示个人客户中的确认代缴公积金的员工
     * @param page				第几页
     * @param rows				第几行
     * @param accountno			公积金号
     * @param idcard			身份证号
     * @return
     */
	List<AccumulationFund> getListByPage(@Param("page")Integer page, @Param("rows")Integer rows, @Param("accountno")String accountno,@Param("idcard")String idcard);

	/**
	 * 这是统计查询记录的方法
	 * @param accountno   公积金号
	 * @param idcard	     身份证号
	 * @return
	 */
	Integer count(@Param("accountno")String accountno, @Param("idcard")String idcard);
	
	AccumulationFund selectByIdOrName(@Param("name")String name,@Param("idcard")String idcard);

	
	/**
	 * 这是导出按钮的实现功能
	 * @param ids
	 * @return
	 */
	List<AccumulationFund> selectByIds(@Param("ids")String[] ids);

	/**
	 * 获取统计的信息
	 * @param page
	 * @param rows
	 * @param customerName
	 * @param idcard
	 * @param accountno
	 * @param companyName
	 * @return
	 */
	List<Map<String, Object>> getStatistics(@Param("page")Integer page, @Param("rows")Integer rows, @Param("customerName")String customerName, @Param("idcard")String idcard,
			@Param("accountno")String accountno, @Param("companyName")String companyName);
	
	Integer getStatisticsCount(@Param("customerName")String customerName, @Param("idcard")String idcard,
			@Param("accountno")String accountno, @Param("companyName")String companyName);
	/**
	 * 这是通过公积金号查找公积金账户的方法
	 * @param accountno
	 * @return
	 */
	AccumulationFund selectByAccountno(@Param("accountno")String accountno);
	
	//导出报表的方法
	List<StatisticsShebao> getStatisticsBy(@Param("customerName")String customerName, @Param("idcard")String idcard,
			@Param("accountno")String accountno, @Param("companyName")String companyName);

}