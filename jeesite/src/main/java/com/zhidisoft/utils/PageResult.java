package com.zhidisoft.utils;

public class PageResult { 
	private Integer total;  // 总记录数
	private Object rows;  // 分页查询结果集
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Object getRows() {
		return rows;
	}
	public void setRows(Object rows) {
		this.rows = rows;
	}	
	
}
