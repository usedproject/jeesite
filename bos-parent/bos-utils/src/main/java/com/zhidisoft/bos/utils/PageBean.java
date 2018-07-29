package com.zhidisoft.bos.utils;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public class PageBean<T> {
	private int currentPage;
	private int currentRows;
	private DetachedCriteria detachedCriteria;
	private List<T> rows;
	private int total;
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getCurrentRows() {
		return currentRows;
	}
	public void setCurrentRows(int currentRows) {
		this.currentRows = currentRows;
	}
	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}
	public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
