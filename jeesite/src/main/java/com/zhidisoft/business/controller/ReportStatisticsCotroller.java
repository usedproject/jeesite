package com.zhidisoft.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhidisoft.utils.ExportToExcel;

@Controller
@RequestMapping("/business/statistics")
public class ReportStatisticsCotroller {

	/**
	 * 跳转到社保统计的jsp页面
	 * 
	 * @return
	 */
	@RequestMapping("/shebao")
	public String toShebao() {
		return "reportStatisticsInfo/listShebaoStatistics";
	}
	
	/**
	 * 跳转到公积金统计的jsp页面
	 * @return
	 */
	@RequestMapping("/gongjijin")
	public String toGongjijin() {
		return "reportStatisticsInfo/listGongjijinStatistics";
	}
	
	@RequestMapping("/gongzi")
	public String toGongzi() {
		return "reportStatisticsInfo/listGongziStatistics";
	}
	
	@RequestMapping("/rencai")
	public String toRencai() {
		return "reportStatisticsInfo/listRencaiStatistics";
	}
	
	@RequestMapping("/caiwu")
	public String toCaiwu() {
		return "reportStatisticsInfo/listCaiwuStatistics";
	}
	
}
